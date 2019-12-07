package ar.tesis.payments.modelDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseBackupDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<String> backups;
	private String code;
	private String message;
	private String detailedMessage;
	private String url;

}
