package ERP.Process.Commerciale.Vente.Devis.model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Achat.Retour_achat.model.Det_retour_achatBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "devis", schema = "vente")
public class DevisBean extends GenericBean {

	private static final long serialVersionUID = 8432430373740234287L;

	@Id
	@Column
	private String devis_id = "";
	@Column
	private Date dev_date;

	@Transient
	private Date dev_date2;

	@Transient
	private String condition_select_mode = "";

	@Column
	private String dev_obs = "";

	@Column
	private String dev_lib = "";

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "devis_id", insertable = true, updatable = false, referencedColumnName = "devis_id")
	private Set<DetDevisBean> list_detaille = new TreeSet<DetDevisBean>();

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean etablissment = new EtablissementBean();

	@ManyToOne
	@JoinColumn(name = "clt_id", insertable = true, updatable = true)
	private ClientBean client = new ClientBean();

	@Column
	private Double dev_remise;

	@Column
	private Double dev_mnt_ht;

	@Column
	private Double dev_mnt_tva;

	@Column
	private Double dev_mnt_total;

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

	public void setDev_obs(String dev_obs) {
		this.dev_obs = dev_obs;
	}

	public String getDev_obs() {
		return dev_obs;
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

	public String getDevis_id() {
		return devis_id;
	}

	public void setDevis_id(String devis_id) {
		this.devis_id = devis_id;
	}

	public ClientBean getClient() {
		return client;
	}

	public void setClient(ClientBean client) {
		this.client = client;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public Date getDev_date() {
		return dev_date;
	}

	public void setDev_date(Date dev_date) {
		this.dev_date = dev_date;
	}

	public EtablissementBean getEtablissment() {
		return etablissment;
	}

	public void setEtablissment(EtablissementBean etablissment) {
		this.etablissment = etablissment;
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

	public String getDev_lib() {
		return dev_lib;
	}

	public void setDev_lib(String dev_lib) {
		this.dev_lib = dev_lib;
	}

	public Set<DetDevisBean> getList_detaille() {
		return list_detaille;
	}

	public void setList_detaille(Set<DetDevisBean> list_detaille) {
		this.list_detaille = list_detaille;
	}

	public Date getDev_date2() {
		return dev_date2;
	}

	public void setDev_date2(Date dev_date2) {
		this.dev_date2 = dev_date2;
	}

	public String getCondition_select_mode() {
		return condition_select_mode;
	}

	public void setCondition_select_mode(String condition_select_mode) {
		this.condition_select_mode = condition_select_mode;
	}

	public Double getDev_remise() {
		return dev_remise;
	}

	public void setDev_remise(Double dev_remise) {
		this.dev_remise = dev_remise;
	}

	public Double getDev_mnt_ht() {
		return dev_mnt_ht;
	}

	public void setDev_mnt_ht(Double dev_mnt_ht) {
		this.dev_mnt_ht = dev_mnt_ht;
	}

	public Double getDev_mnt_tva() {
		return dev_mnt_tva;
	}

	public void setDev_mnt_tva(Double dev_mnt_tva) {
		this.dev_mnt_tva = dev_mnt_tva;
	}

	public Double getDev_mnt_total() {
		return dev_mnt_total;
	}

	public void setDev_mnt_total(Double dev_mnt_total) {
		this.dev_mnt_total = dev_mnt_total;
	}

}
