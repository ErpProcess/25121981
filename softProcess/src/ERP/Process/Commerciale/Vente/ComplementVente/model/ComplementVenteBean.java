package ERP.Process.Commerciale.Vente.ComplementVente.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "complement_vente", schema = "vente")
public class ComplementVenteBean extends GenericBean {

	private static final long serialVersionUID = 5416177106222834393L;
	@Id
	@Column
	private String comp_vente_id = "";
	@ManyToOne
	@JoinColumn(name = "vente_id", insertable = true, updatable = true)
	private ProcedureVenteBean vente = new ProcedureVenteBean();
	@Column
	private Date comp_vente_date;

	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	@Column
	private java.sql.Time time_cre;
	@Column
	private String comp_vente_obs = "";
	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Time time_mod;

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setComp_vente_obs(String comp_vente_obs) {
		this.comp_vente_obs = comp_vente_obs;
	}

	public String getComp_vente_obs() {
		return comp_vente_obs;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
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

	public String getComp_vente_id() {
		return comp_vente_id;
	}

	public void setComp_vente_id(String comp_vente_id) {
		this.comp_vente_id = comp_vente_id;
	}

	public Date getComp_vente_date() {
		return comp_vente_date;
	}

	public void setComp_vente_date(Date comp_vente_date) {
		this.comp_vente_date = comp_vente_date;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
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

	public ProcedureVenteBean getVente() {
		return vente;
	}

	public void setVente(ProcedureVenteBean vente) {
		this.vente = vente;
	}
}
