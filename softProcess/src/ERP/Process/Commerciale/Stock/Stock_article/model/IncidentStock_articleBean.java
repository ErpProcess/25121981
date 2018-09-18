package ERP.Process.Commerciale.Stock.Stock_article.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "mvt_incident_stock", schema = "stock")
public class IncidentStock_articleBean extends GenericBean {
 
	private static final long serialVersionUID = 6535656992691377763L;

	@EmbeddedId
	private PkIncidentStock pk = new PkIncidentStock();

	@Column
	private Double quantite_incident;

	@ManyToOne
	@JoinColumn(name = "sens_incident_stock", insertable = true, updatable = true)
	private Entite_etat_commercialeBean sens  ;

	@Column
	private Double mnt_tva_incident;

	@Column
	private Double mnt_ht_incident;

	@Column
	private Time time_cre;
	@Column
	private Time time_mod;

	@Column
	private String usr_cre = "";

	@Column
	private Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private Date date_mod;

	@Transient
	private Date date_stock2;

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

	public Date getDate_stock2() {
		return date_stock2;
	}

	public void setDate_stock2(Date date_stock2) {
		this.date_stock2 = date_stock2;
	}

	public Double getQuantite_incident() {
		return quantite_incident;
	}

	public void setQuantite_incident(Double quantite_incident) {
		this.quantite_incident = quantite_incident;
	}

	public Entite_etat_commercialeBean getSens() {
		return sens;
	}

	public void setSens(Entite_etat_commercialeBean sens) {
		this.sens = sens;
	}

	public Double getMnt_tva_incident() {
		return mnt_tva_incident;
	}

	public void setMnt_tva_incident(Double mnt_tva_incident) {
		this.mnt_tva_incident = mnt_tva_incident;
	}

	public Double getMnt_ht_incident() {
		return mnt_ht_incident;
	}

	public void setMnt_ht_incident(Double mnt_ht_incident) {
		this.mnt_ht_incident = mnt_ht_incident;
	}

	public Time getTime_cre() {
		return time_cre;
	}

	public void setTime_cre(Time time_cre) {
		this.time_cre = time_cre;
	}

	public Time getTime_mod() {
		return time_mod;
	}

	public void setTime_mod(Time time_mod) {
		this.time_mod = time_mod;
	}

	public PkIncidentStock getPk() {
		return pk;
	}

	public void setPk(PkIncidentStock pk) {
		this.pk = pk;
	}

}
