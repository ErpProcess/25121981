package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "tva", schema = "administration")
public class TVABean {
	@Id
	@GeneratedValue
	@Column
	private Integer tva_id;

	@Column
	private Double tva_value = new Double(0);

	@Column
	private String tva_abrv = "";
	@Column
	private String tva_libelle = "";
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String soc_id = "";

	public void setTva_abrv(String tva_abrv) {
		this.tva_abrv = tva_abrv;
	}

	public String getTva_abrv() {
		return tva_abrv;
	}

	public void setTva_libelle(String tva_libelle) {
		this.tva_libelle = tva_libelle;
	}

	public String getTva_libelle() {
		return tva_libelle;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	public void setSoc_id(String soc_id) {
		this.soc_id = soc_id;
	}

	public String getSoc_id() {
		return soc_id;
	}

	public Integer getTva_id() {
		return tva_id;
	}

	public void setTva_id(Integer tva_id) {
		this.tva_id = tva_id;
	}

	public Double getTva_value() {
		return tva_value;
	}

	public void setTva_value(Double tva_value) {
		this.tva_value = tva_value;
	}
}
