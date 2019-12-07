package ar.tesis.payments.service;

import ar.tesis.payments.modelDTO.ResponseBackupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ManagementServiceInterface {

	ResponseBackupDTO makeBackup();
	
	ResponseEntity<?> restoreBackup(String id);

	ResponseBackupDTO listBackup();
}
