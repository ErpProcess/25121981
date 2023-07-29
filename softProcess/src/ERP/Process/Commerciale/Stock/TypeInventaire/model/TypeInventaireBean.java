package ERP.Process.Commerciale.Stock.TypeInventaire.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;

@JsonAutoDetect
@Entity
@Table(name = "type_inventaire", schema = "stock")
public class TypeInventaireBean {
	@Id
	@GeneratedValue
	@Column
	private Integer type_inv_id;
	@Column
	private String type_inv_libelle = "";

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Date date_mod;

	public void setType_inv_libelle(String type_inv_libelle) {
		this.type_inv_libelle = type_inv_libelle;
	}

	public String getType_inv_libelle() {
		return type_inv_libelle;
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

	public Integer getType_inv_id() {
		return type_inv_id;
	}

	public void setType_inv_id(Integer type_inv_id) {
		this.type_inv_id = type_inv_id;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}
}
