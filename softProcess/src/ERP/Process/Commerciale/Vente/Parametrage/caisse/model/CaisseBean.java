package ERP.Process.Commerciale.Vente.Parametrage.caisse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "caisse_vente", schema = "vente")
public class CaisseBean extends GenericBean {

	private static final long serialVersionUID = 930061521881026287L;

	@Id
	@GeneratedValue
	@Column
	private Integer caisse_id;

	@Column
	private String caisse_libelle = "";
	@Column
	private Integer caisse_type;
	@Column
	private String caisse_obs = "";
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";

	@Column
	private java.sql.Time time_cre;
	@Column
	private java.sql.Time time_mod;

	public void setCaisse_libelle(String caisse_libelle) {
		this.caisse_libelle = caisse_libelle;
	}

	public String getCaisse_libelle() {
		return caisse_libelle;
	}

	public void setCaisse_type(Integer caisse_type) {
		this.caisse_type = caisse_type;
	}

	public Integer getCaisse_type() {
		return caisse_type;
	}

	public void setCaisse_obs(String caisse_obs) {
		this.caisse_obs = caisse_obs;
	}

	public String getCaisse_obs() {
		return caisse_obs;
	}

	 
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

	 

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	public Integer getCaisse_id() {
		return caisse_id;
	}

	public void setCaisse_id(Integer caisse_id) {
		this.caisse_id = caisse_id;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}
}
