package ERP.Process.Commerciale.Stock.MouvementStock.model;

import java.io.Serializable;
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

@JsonAutoDetect
@Entity
@Table(name = "mvt_incident_serie", schema = "stock")
public class Incident_mvt_serieBean implements Serializable, Cloneable {
 
  
	private static final long serialVersionUID = -4004776593682949634L;

	@EmbeddedId
	private PkIncidentMvtSerie pk = new PkIncidentMvtSerie();

	@Column
	private Date date_mvt_incident;
	
	@ManyToOne
	@JoinColumn(name = "sens_incident_serie", insertable = true, updatable = true)
	private Entite_etat_commercialeBean sens ;
	
	 
	
	
	
	@Transient
	private String indx_row_next = "";
	
	
	@Transient
	private String fournisseur_id = "";
	
	
	@Transient
	private String indx_row = "";

	@Transient
	private String to_check = "";

	@Transient
	private String id_entite = "";
	
	@Column
	private String tarif_operation_id = "";

	@Column
	private Double montant_ht_incident;

	@Column
	private Double montant_tva_incident;

	@Column
	private Double quantite_incident;

	
	@Transient
	private String tarif_unit_vente = "";
	
	@Transient
	private String tarif_unit_achat = "";
	

	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Date date_mod;

	@Column
	private java.sql.Time time_cre;

	@Column
	private java.sql.Time time_mod;

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

	public String getTarif_operation_id() {
		return tarif_operation_id;
	}

	public void setTarif_operation_id(String tarif_operation_id) {
		this.tarif_operation_id = tarif_operation_id;
	}

	public String getIndx_row_next() {
		return indx_row_next;
	}

	public void setIndx_row_next(String indx_row_next) {
		this.indx_row_next = indx_row_next;
	}

	public String getIndx_row() {
		return indx_row;
	}

	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	public String getId_entite() {
		return id_entite;
	}

	public void setId_entite(String id_entite) {
		this.id_entite = id_entite;
	}

	public String getTarif_unit_vente() {
		return tarif_unit_vente;
	}

	public void setTarif_unit_vente(String tarif_unit_vente) {
		this.tarif_unit_vente = tarif_unit_vente;
	}

	public String getTarif_unit_achat() {
		return tarif_unit_achat;
	}

	public void setTarif_unit_achat(String tarif_unit_achat) {
		this.tarif_unit_achat = tarif_unit_achat;
	}

	public Date getDate_mvt_incident() {
		return date_mvt_incident;
	}

	public void setDate_mvt_incident(Date date_mvt_incident) {
		this.date_mvt_incident = date_mvt_incident;
	}

	public PkIncidentMvtSerie getPk() {
		return pk;
	}

	public void setPk(PkIncidentMvtSerie pk) {
		this.pk = pk;
	}

	public Double getMontant_ht_incident() {
		return montant_ht_incident;
	}

	public void setMontant_ht_incident(Double montant_ht_incident) {
		this.montant_ht_incident = montant_ht_incident;
	}

	public Double getMontant_tva_incident() {
		return montant_tva_incident;
	}

	public void setMontant_tva_incident(Double montant_tva_incident) {
		this.montant_tva_incident = montant_tva_incident;
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

	public String getFournisseur_id() {
		return fournisseur_id;
	}

	public void setFournisseur_id(String fournisseur_id) {
		this.fournisseur_id = fournisseur_id;
	}

 

	 
	 

}
