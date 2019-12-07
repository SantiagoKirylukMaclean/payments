package ar.tesis.payments.service;


import ar.tesis.payments.modelDTO.ResponseBackupDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("ManagementService")
public class ManagementService implements ManagementServiceInterface {

	@Value("${spring.datasource.username}")
	private String dbUser;
	@Value("${spring.datasource.password}")
	private String dbPass;
	@Value("${database.backup.folder}")
	private String savePath;
	@Value("${database.backup.maximum.files}")
	private String maxFiles;
	@Value("${database.backup.mysql.folder}")
	private String mysqlFolder;

	@Override
	public ResponseBackupDTO makeBackup() {
		ResponseBackupDTO respuesta = new ResponseBackupDTO();

		try {
			String finalSavePath = new String();
			File f1 = new File(savePath);
			f1.mkdir();

			String nombreBackup = "backup_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".sql";
			finalSavePath = savePath + nombreBackup;

			String executeCmd = mysqlFolder + "mysqldump -u" + dbUser + " -p" + dbPass + " payments -r " + finalSavePath;

			/* NOTE: Executing the command here */
			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			/*
			 * NOTE: processComplete=0 if correctly executed, will contain other values if
			 * not
			 */
			if (processComplete == 0) {
				respuesta.setCode("00");
				respuesta.setMessage("Backup " + nombreBackup + " completado correctamente.");
				return respuesta;
			} else {
				respuesta.setCode("BKP");
				respuesta.setMessage("Ha ocurrido un error al realizar el backup");
				return respuesta;
			}
		} catch (Exception e) {
			respuesta.setCode("BKP");
			respuesta.setMessage("Ha ocurrido un error al realizar el backup");
			respuesta.setDetailedMessage(getStackTrace(e));

			try {
				File f1 = new File(savePath);
				f1.delete();
			} catch (Exception e1) {
			}

			return respuesta;
		}
	}

	@Override
	public ResponseEntity<?> restoreBackup(String id) {
		ResponseBackupDTO respuesta = new ResponseBackupDTO();

		try {
			String finalSavePath = new String();
			finalSavePath = savePath + id;

			String[] executeCmd = new String[] { mysqlFolder + "mysql", "payments5", "-u" + dbUser, "-p" + dbPass, "-e",
					" source " + finalSavePath };

			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				respuesta.setCode("00");
				respuesta.setMessage("Backup " + id + " restaurado correctamente.");
				return ResponseEntity.status(HttpStatus.OK).body(respuesta);
			} else {
				respuesta.setCode("BKP");
				respuesta.setMessage("Ha ocurrido un error al realizar el restore");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
			}
		} catch (Exception e) {
			respuesta.setCode("BKP");
			respuesta.setMessage("Ha ocurrido un error al realizar el restore");
			respuesta.setDetailedMessage(getStackTrace(e));

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@Override
	public ResponseBackupDTO listBackup() {
		ResponseBackupDTO respuesta = new ResponseBackupDTO();

		
		try {
			respuesta.setBackups(Files.walk(Paths.get(savePath))
					.filter(Files::isRegularFile)
					.map(path -> path.toFile().getName())
					.collect(Collectors.toList()));
			return respuesta;
		} catch (IOException e) {

			respuesta.setCode("BKP");
			respuesta.setMessage("Ha ocurrido un error al listar los backups realizados");
			respuesta.setDetailedMessage(getStackTrace(e));

			return respuesta;
		}
	}

	private String getStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

}
