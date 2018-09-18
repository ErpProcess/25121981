package ERP.Process.Commerciale.TypeFamille.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;


@JsonAutoDetect
@Entity
@Table(name = "typefamille", schema = "gestioncommerciale")
public class TypeFamilleBean {
	
	@Id
	@Column
	private String typefam_id = "";
	@Column
	private String typefam_lib = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";
	@Column
	private String usr_cre = "";
	@Column
	private String soc_id = "";
	@Column
	private String etab_id = "";

	public void setTypefam_id(String typefam_id) {
		this.typefam_id = typefam_id;
	}

	public String getTypefam_id() {
		return typefam_id;
	}

	public void setTypefam_lib(String typefam_lib) {
		this.typefam_lib = typefam_lib;
	}

	public String getTypefam_lib() {
		return typefam_lib;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
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

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setSoc_id(String soc_id) {
		this.soc_id = soc_id;
	}

	public String getSoc_id() {
		return soc_id;
	}

	public void setEtab_id(String etab_id) {
		this.etab_id = etab_id;
	}

	public String getEtab_id() {
		return etab_id;
	}
}
