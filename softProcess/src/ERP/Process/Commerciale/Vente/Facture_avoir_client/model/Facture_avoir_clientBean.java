package ERP.Process.Commerciale.Vente.Facture_avoir_client.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model.TypeAvoirBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;



@JsonAutoDetect
@Entity
@Table(name = "facture_avoir_client", schema = "vente")
public class Facture_avoir_clientBean extends GenericBean {
 
 
	private static final long serialVersionUID = 5981576954407651055L;

	@Id
	@Column
	private String avoir_id = "";
	
	@ManyToOne
	@JoinColumn(name = "fact_clt_id", insertable = true, updatable = false)
	private Facture_clientBean   factclient = new Facture_clientBean();
	
	
	@ManyToOne
	@JoinColumn(name = "type_avoir_id", insertable = true, updatable = true)
	private TypeAvoirBean  typeAvoir = new TypeAvoirBean();
	
	@Column
	private String avoir_libelle="";
	
	
	@Column
	private Date avoir_date;
	
	@Transient
	private Date avoir_date2;
	
	
	@Column
	private String avoir_obs="";
	  
	  
	 
	
	
	
	
	
	
	@Transient
	private String condition_select_mode="";
	
	 
	
	
 
	
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
	
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	 

	 
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
	 

	 

	 

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	 

	public String getCondition_select_mode() {
		return condition_select_mode;
	}

	public void setCondition_select_mode(String condition_select_mode) {
		this.condition_select_mode = condition_select_mode;
	}

 

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}

	public String getAvoir_id() {
		return avoir_id;
	}

	public void setAvoir_id(String avoir_id) {
		this.avoir_id = avoir_id;
	}

	public Facture_clientBean getFactclient() {
		return factclient;
	}

	public void setFactclient(Facture_clientBean factclient) {
		this.factclient = factclient;
	}

	public String getAvoir_libelle() {
		return avoir_libelle;
	}

	public void setAvoir_libelle(String avoir_libelle) {
		this.avoir_libelle = avoir_libelle;
	}

	public Date getAvoir_date() {
		return avoir_date;
	}

	public void setAvoir_date(Date avoir_date) {
		this.avoir_date = avoir_date;
	}

	public String getAvoir_obs() {
		return avoir_obs;
	}

	public void setAvoir_obs(String avoir_obs) {
		this.avoir_obs = avoir_obs;
	}

	public TypeAvoirBean getTypeAvoir() {
		return typeAvoir;
	}

	public void setTypeAvoir(TypeAvoirBean typeAvoir) {
		this.typeAvoir = typeAvoir;
	}

	public Date getAvoir_date2() {
		return avoir_date2;
	}

	public void setAvoir_date2(Date avoir_date2) {
		this.avoir_date2 = avoir_date2;
	}

	 

	 
}
