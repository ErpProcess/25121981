package ERP.Process.Commerciale.Vente.RetourVente.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
 
import ERP.Process.Commerciale.Stock.MouvementStock.model.Incident_mvt_serieBean;
 
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;



@JsonAutoDetect
@Entity
@Table(name = "det_retour_vente", schema = "vente")
public class DetRetourVenteBean extends GenericBean {

	private static final long serialVersionUID = -1293275674896191577L;

	@EmbeddedId
	private PkDetRetourVente pk = new PkDetRetourVente();

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

	@Transient
	private Double quantite_en_stock;

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
	
	@Transient
	private Incident_mvt_serieBean incident = new Incident_mvt_serieBean();
	
	
	@Transient
	private Reception_achatBean recep = new Reception_achatBean();
	
	@Transient
	private Det_reception_achatBean d_recep = new Det_reception_achatBean();
	
	
	

	@ManyToOne
	@JoinColumn(name = "nature_incident_fk", insertable = true, updatable = true)
	private Nature_incident_mvt_retourBean   cause = new Nature_incident_mvt_retourBean();

	@ManyToOne
	@JoinColumn(name = "sens_operation", insertable = true, updatable = true)
	private Entite_etat_commercialeBean sens = new Entite_etat_commercialeBean();
	
	
	 
	
	

	public PkDetRetourVente getPk() {
		return pk;
	}

	public void setPk(PkDetRetourVente pk) {
		this.pk = pk;
	}

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

	public Double getQuantite_en_stock() {
		return quantite_en_stock;
	}

	public void setQuantite_en_stock(Double quantite_en_stock) {
		this.quantite_en_stock = quantite_en_stock;
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

	public Incident_mvt_serieBean getIncident() {
		return incident;
	}

	public void setIncident(Incident_mvt_serieBean incident) {
		this.incident = incident;
	}

	public Reception_achatBean getRecep() {
		return recep;
	}

	public void setRecep(Reception_achatBean recep) {
		this.recep = recep;
	}

	 

	public Det_reception_achatBean getD_recep() {
		return d_recep;
	}

	public void setD_recep(Det_reception_achatBean d_recep) {
		this.d_recep = d_recep;
	}

}
