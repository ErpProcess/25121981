package ERP.Process.Commerciale.Vente.RetourVente.model;

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
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "incident_det_retour_vente", schema = "vente")
public class Incident_det_retour_venteBean extends GenericBean {
 
	  
	private static final long serialVersionUID = -8242198702062544195L;

	@EmbeddedId
	private PkIncidentDetRetourVente pk = new PkIncidentDetRetourVente();

	@Transient
	private String indx_row = "";

	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

	@Column
	private Double quantite_retourne;

	@Column
	private Double montant_tva_retour;

	@Column
	private Double montant_ht_retour;



	@Column
	private String observation = "";

	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";

	

	@ManyToOne
	@JoinColumn(name = "sens_operation", insertable = true, updatable = true)
	private Entite_etat_commercialeBean sens = new Entite_etat_commercialeBean();
	
	 

	 
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getIndx_row() {
		return indx_row;
	}

	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}

	public String getIndx_row_next() {
		return indx_row_next;
	}

	public void setIndx_row_next(String indx_row_next) {
		this.indx_row_next = indx_row_next;
	}

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	 

	public Double getQuantite_retourne() {
		return quantite_retourne;
	}

	public void setQuantite_retourne(Double quantite_retourne) {
		this.quantite_retourne = quantite_retourne;
	}

	public Double getMontant_tva_retour() {
		return montant_tva_retour;
	}

	public void setMontant_tva_retour(Double montant_tva_retour) {
		this.montant_tva_retour = montant_tva_retour;
	}

	public Double getMontant_ht_retour() {
		return montant_ht_retour;
	}

	public void setMontant_ht_retour(Double montant_ht_retour) {
		this.montant_ht_retour = montant_ht_retour;
	}

	 

	public Entite_etat_commercialeBean getSens() {
		return sens;
	}

	public void setSens(Entite_etat_commercialeBean sens) {
		this.sens = sens;
	}

	public PkIncidentDetRetourVente getPk() {
		return pk;
	}

	public void setPk(PkIncidentDetRetourVente pk) {
		this.pk = pk;
	}

}
