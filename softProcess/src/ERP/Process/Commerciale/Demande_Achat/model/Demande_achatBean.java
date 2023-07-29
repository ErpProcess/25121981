package ERP.Process.Commerciale.Demande_Achat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.DBSchemaTable;

@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "demande_achat", schema = DBSchemaTable.SCHEMA_ACHAT)
public class Demande_achatBean implements Serializable,Cloneable {
	
	@Id
	@Column
	private String dem_achat_id = "";
	
	@Column
	private Date   dem_date    ;
	
	
	@Transient
	private Date   dem_date2    ;
	
	
	 
	
	@ManyToOne 
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean  modeBean =  new FonctionBean();
	
	
	@Column private String dem_obs = "";
	
	@ManyToOne 
	@JoinColumn(name = "frs_id", insertable = true, updatable = true)
	private FournisseurBean      frsBean =  new FournisseurBean();
	
	@Column	private String  usr_cre = "";
	@Column	private String  usr_mod = "";
	@Column	private Date    date_cre  ;
	@Column	private Date    date_mod  ;
	@Transient  private String auto_Load_Edit_Grid = "false";
	@Transient  private String condition_etat = "";
	
	
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean etab_bean = new EtablissementBean();
	
	 

	public EtablissementBean getEtab_bean() {
		return etab_bean;
	}

	public void setEtab_bean(EtablissementBean etab_bean) {
		this.etab_bean = etab_bean;
	}

	public void setDem_achat_id(String dem_achat_id) {
		this.dem_achat_id = dem_achat_id;
	}

	public String getDem_achat_id() {
		return dem_achat_id;
	}
 
	public Date getDem_date() {
		return dem_date;
	}

	public void setDem_date(Date dem_date) {
		this.dem_date = dem_date;
	}

	 

	public String getDem_obs() {
		return dem_obs;
	}

	public void setDem_obs(String dem_obs) {
		this.dem_obs = dem_obs;
	}

	 

	public String getAuto_Load_Edit_Grid() {
		return auto_Load_Edit_Grid;
	}

	public void setAuto_Load_Edit_Grid(String auto_Load_Edit_Grid) {
		this.auto_Load_Edit_Grid = auto_Load_Edit_Grid;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public FournisseurBean getFrsBean() {
		return frsBean;
	}

	public void setFrsBean(FournisseurBean frsBean) {
		this.frsBean = frsBean;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public String getCondition_etat() {
		return condition_etat;
	}

	public void setCondition_etat(String condition_etat) {
		this.condition_etat = condition_etat;
	}

	public Date getDem_date2() {
		return dem_date2;
	}

	public void setDem_date2(Date dem_date2) {
		this.dem_date2 = dem_date2;
	}

	 
	 

 

}
