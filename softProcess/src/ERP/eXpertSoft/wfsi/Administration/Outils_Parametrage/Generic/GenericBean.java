package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class GenericBean implements Serializable, Cloneable {
 
	private static final long serialVersionUID = 955590610586472907L;
	
	
	@Transient
	public static final String SHEMA_VENTE			= "vente";
	
	@Transient
	private String				indx_row			= "";
	

	@Transient
	private String				indx_row_next		= "";
	
	
	
	@Transient
	private String				operation		= "";
	
	
	
	@Transient
	private String				to_check			= ""; //checked
	

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	public String getIndx_row() {
		return indx_row;
	}

	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}

	public String getIndx_row_next() {
		return indx_row_next;
	}

	public void setIndx_row_next(String indx_row_next) {
		this.indx_row_next = indx_row_next;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
