package ERP.Process.Commerciale.Vente.RetourVente.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "retour_vente", schema = "vente")
public class RetourVenteBean extends GenericBean {

 
	private static final long serialVersionUID = 3326866947666889575L;

	@Id
	@Column
	private String ret_vente_id = "";
	
	@ManyToOne
	@JoinColumn(name = "vente_id", insertable = true, updatable = true)
	private ProcedureVenteBean vente= new ProcedureVenteBean();
	@Column
	private Date ret_vente_date;
	
	@Transient
	private Date ret_vente_date2;
	
	
	
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();
	
	
	 
	
	@Transient
	private String condition_etat_retour="";
	
	@Transient
	private String list_re_vente_id="";
	 
	
	@Column
	private java.sql.Time time_cre;
	@Column
	private String re_vente_obs = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Time time_mod;

	 

 

	 

	public Date getRet_vente_date() {
		return ret_vente_date;
	}

	public void setRet_vente_date(Date ret_vente_date) {
		this.ret_vente_date = ret_vente_date;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setRe_vente_obs(String re_vente_obs) {
		this.re_vente_obs = re_vente_obs;
	}

	public String getRe_vente_obs() {
		return re_vente_obs;
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

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	public String getRet_vente_id() {
		return ret_vente_id;
	}

	public void setRet_vente_id(String ret_vente_id) {
		this.ret_vente_id = ret_vente_id;
	}

	public ProcedureVenteBean getVente() {
		return vente;
	}

	public void setVente(ProcedureVenteBean vente) {
		this.vente = vente;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public Date getRet_vente_date2() {
		return ret_vente_date2;
	}

	public void setRet_vente_date2(Date ret_vente_date2) {
		this.ret_vente_date2 = ret_vente_date2;
	}

	public String getCondition_etat_retour() {
		return condition_etat_retour;
	}

	public void setCondition_etat_retour(String condition_etat_retour) {
		this.condition_etat_retour = condition_etat_retour;
	}

	public String getList_re_vente_id() {
		return list_re_vente_id;
	}

	public void setList_re_vente_id(String list_re_vente_id) {
		this.list_re_vente_id = list_re_vente_id;
	}

	 
}
