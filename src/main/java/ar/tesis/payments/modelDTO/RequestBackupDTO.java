package ar.tesis.payments.modelDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class RequestBackupDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;

}
