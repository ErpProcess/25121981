package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import javax.persistence.Transient;

public class dataTableBean {

	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";
	@Transient
	private String chaineColumn = "";
	@Transient
	private String id_entite = "";

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

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	public String getChaineColumn() {
		return chaineColumn;
	}

	public void setChaineColumn(String chaineColumn) {
		this.chaineColumn = chaineColumn;
	}

	public String getId_entite() {
		return id_entite;
	}

	public void setId_entite(String id_entite) {
		this.id_entite = id_entite;
	}
}
