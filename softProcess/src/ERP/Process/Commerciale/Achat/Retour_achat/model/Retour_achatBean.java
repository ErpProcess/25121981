package ERP.Process.Commerciale.Achat.Retour_achat.model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "retour_achat", schema = "achat")
public class Retour_achatBean extends  GenericBean {

 
	private static final long serialVersionUID = 5004362238587668442L;

	@Id
	@Column
	private String retour_id = "";

	@Column
	private Date retour_date;
	 
	
	@Transient
	private Date retour_date2;
	 

	@ManyToOne
	@JoinColumn(name = "achat_id", insertable = true, updatable = true)
	private Reception_achatBean achat = new Reception_achatBean();
	

	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	 
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "retour_id", insertable = true, updatable = false, referencedColumnName = "retour_id")
	private Set <Det_retour_achatBean>  list_retour  =  new TreeSet <Det_retour_achatBean>();

	
	
	
	
	@Transient
	private String condition_etat_retour = "";
	
	
	@Column
	private java.sql.Time time_cre;
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

	public void setRetour_id(String retour_id) {
		this.retour_id = retour_id;
	}

	public String getRetour_id() {
		return retour_id;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
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

	public Date getRetour_date() {
		return retour_date;
	}

	public void setRetour_date(Date retour_date) {
		this.retour_date = retour_date;
	}

	public Reception_achatBean getAchat() {
		return achat;
	}

	public void setAchat(Reception_achatBean achat) {
		this.achat = achat;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	 

	public Set<Det_retour_achatBean> getList_retour() {
		return list_retour;
	}

	public void setList_retour(Set<Det_retour_achatBean> list_retour) {
		this.list_retour = list_retour;
	}

	public String getCondition_etat_retour() {
		return condition_etat_retour;
	}

	public void setCondition_etat_retour(String condition_etat_retour) {
		this.condition_etat_retour = condition_etat_retour;
	}

	 
	public Date getRetour_date2() {
		return retour_date2;
	}

	public void setRetour_date2(Date retour_date2) {
		this.retour_date2 = retour_date2;
	}

	 
}
