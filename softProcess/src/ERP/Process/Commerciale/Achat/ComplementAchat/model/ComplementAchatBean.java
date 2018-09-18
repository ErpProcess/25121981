package ERP.Process.Commerciale.Achat.ComplementAchat.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "complement_achat_frs", schema = "achat")
public class ComplementAchatBean extends  GenericBean  {
 
 
 
	private static final long serialVersionUID = 3463749048261289882L;

	@Id
	@Column
	private String complet_id = "";

	@Column
	private Date complet_date;

	@Transient
	private Date complet_date2;

	@ManyToOne
	@JoinColumn(name = "achat_id", insertable = true, updatable = true)
	private Reception_achatBean achat = new Reception_achatBean();

	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	 

	
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "complet_id", insertable = true, updatable = false, referencedColumnName = "complet_id")
	private Set<Det_complment_achatBean> list_complment = new HashSet<Det_complment_achatBean>();

	
	@Transient
	private String condition_etat_complement = "";
	
	 

	@Column
	private java.sql.Time time_cre;
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	
	@Column
	private String complement_obs = "";
	

	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Time time_mod;
	
	
	public String getComplet_id() {
		return complet_id;
	}
	public void setComplet_id(String complet_id) {
		this.complet_id = complet_id;
	}
	public Date getComplet_date() {
		return complet_date;
	}
	public void setComplet_date(Date complet_date) {
		this.complet_date = complet_date;
	}
	public Date getComplet_date2() {
		return complet_date2;
	}
	public void setComplet_date2(Date complet_date2) {
		this.complet_date2 = complet_date2;
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
	 
	public Set<Det_complment_achatBean> getList_complment() {
		return list_complment;
	}
	public void setList_complment(Set<Det_complment_achatBean> list_complment) {
		this.list_complment = list_complment;
	}
	 
	 
	public java.sql.Time getTime_cre() {
		return time_cre;
	}
	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}
	public java.sql.Date getDate_cre() {
		return date_cre;
	}
	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}
	public String getUsr_cre() {
		return usr_cre;
	}
	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}
	public java.sql.Date getDate_mod() {
		return date_mod;
	}
	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}
	public String getUsr_mod() {
		return usr_mod;
	}
	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}
	public java.sql.Time getTime_mod() {
		return time_mod;
	}
	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}
	public String getCondition_etat_complement() {
		return condition_etat_complement;
	}
	public void setCondition_etat_complement(String condition_etat_complement) {
		this.condition_etat_complement = condition_etat_complement;
	}
	public String getComplement_obs() {
		return complement_obs;
	}
	public void setComplement_obs(String complement_obs) {
		this.complement_obs = complement_obs;
	}
	 
	 

}
