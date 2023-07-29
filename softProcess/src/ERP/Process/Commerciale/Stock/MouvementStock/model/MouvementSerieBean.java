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

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;

@JsonAutoDetect
@Entity
@Table(name = "mvt_serie_article", schema = "stock")
public class MouvementSerieBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -8392215213112219104L;

	@EmbeddedId
	private PkMvtSerie pk = new PkMvtSerie();

	@Column
	private Date date_mvt_serie;
	
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String indx_row = "";

	@Transient
	private String to_check = "";

	@Transient
	private String id_entite = "";
	
	@Column
	private String tarif_operation_id = "";

	@Column
	private Double montant_ht_operation;

	@Column
	private Double montant_tva_operation;

	@Column
	private Double quantite_operation;
	
	@Transient
	private String tarif_unit_vente = "";
	
	@Transient
	private String tarif_unit_achat = "";
	
	@ManyToOne
	@JoinColumn(name = "nat_incident_id", insertable = true, updatable = true)
	private Nature_incident_mvt_retourBean cause  ;
	
	
	@ManyToOne
	@JoinColumn(name = "sens_incident_serie", insertable = true, updatable = true)
	private Entite_etat_commercialeBean sens ;
	

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

	
	
	public Nature_incident_mvt_retourBean getCause() {
		return cause;
	}

	public void setCause(Nature_incident_mvt_retourBean cause) {
		this.cause = cause;
	}

	public Entite_etat_commercialeBean getSens() {
		return sens;
	}

	public void setSens(Entite_etat_commercialeBean sens) {
		this.sens = sens;
	}

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

	public PkMvtSerie getPk() {
		return pk;
	}

	public void setPk(PkMvtSerie pk) {
		this.pk = pk;
	}

	public Double getQuantite_operation() {
		return quantite_operation;
	}

	public void setQuantite_operation(Double quantite_operation) {
		this.quantite_operation = quantite_operation;
	}

	 
	public Double getMontant_ht_operation() {
		return montant_ht_operation;
	}

	public void setMontant_ht_operation(Double montant_ht_operation) {
		this.montant_ht_operation = montant_ht_operation;
	}

	public Double getMontant_tva_operation() {
		return montant_tva_operation;
	}

	public void setMontant_tva_operation(Double montant_tva_operation) {
		this.montant_tva_operation = montant_tva_operation;
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

	public Date getDate_mvt_serie() {
		return date_mvt_serie;
	}

	public void setDate_mvt_serie(Date date_mvt_serie) {
		this.date_mvt_serie = date_mvt_serie;
	}

}
