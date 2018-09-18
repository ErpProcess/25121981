package ERP.Process.Commerciale.Code_barre.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;



@JsonAutoDetect
@Entity
@Table(name = "det_code_barre", schema = "gestioncommerciale")
public class Det_code_barre  implements Serializable,Cloneable  {

	@EmbeddedId
	private PKDet_code_barre pk_det_codBare = new PKDet_code_barre();
	 
	 
	 

	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";
	@Transient
	private String to_check = "";
	@Transient
	private String id_entite = "";
	
	 
	@Column
	private Integer num_ligne ;
	 
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";

	 

	 

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
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

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	public String getId_entite() {
		return id_entite;
	}

	public void setId_entite(String id_entite) {
		this.id_entite = id_entite;
	}

	public PKDet_code_barre getPk_det_codBare() {
		return pk_det_codBare;
	}

	public void setPk_det_codBare(PKDet_code_barre pk_det_codBare) {
		this.pk_det_codBare = pk_det_codBare;
	}

	public Integer getNum_ligne() {
		return num_ligne;
	}

	public void setNum_ligne(Integer num_ligne) {
		this.num_ligne = num_ligne;
	}

	 
	 

	 
	 

	 

	 

}
