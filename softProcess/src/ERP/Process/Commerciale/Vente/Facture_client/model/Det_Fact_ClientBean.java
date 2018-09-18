package ERP.Process.Commerciale.Vente.Facture_client.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;

@JsonAutoDetect
@Entity
@Table(name = "det_facture_client", schema = "vente")

public class Det_Fact_ClientBean extends GenericBean  {

	 
	private static final long serialVersionUID = 5331082932232404572L;


	@EmbeddedId
	private PkDetFactClient pk = new PkDetFactClient();
	 
	 
	@Column
	private Double quantite;
	
	@Column
	private Double montant_ttc_vente;
	
	
	@Transient
	private Double montant_ht_vente_reel;
	
	@Column
	private Double montant_remise_ligne;
	
	
	@Column
	private Double taux_remise_ligne;
	 
	@Column
	private Double montant_tva_vente;

	@Column
	private Double montant_ht_vente;
	
	
	@Transient
	private String nbrBoxes;
	
	 
	 
	@Transient
	private String indx_row = "";
	
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	@Transient
	private String id_entite = "";
	
	@ManyToOne
	@JoinColumn(name = "tva_id", insertable = true, updatable = true, referencedColumnName = "tva_id")
	private TVABean tvaBean = new TVABean();
	

	@Column
	private Double tarif_unit_vente;

	@Column
	private String observation = "";

	@Column
	private java.sql.Date date_cre;
	
	@Column
	private String usr_cre = "";
	
	@Column
	private java.sql.Date date_mod;
	
	@Column
	private String usr_mod = "";
	
	
	

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

	 

	 

	 

	 

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setPk(PkDetFactClient pk) {
		this.pk = pk;
	}

	public PkDetFactClient getPk() {
		return pk;
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

	public String getId_entite() {
		return id_entite;
	}

	public void setId_entite(String id_entite) {
		this.id_entite = id_entite;
	}

	 

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	 
	public Double getMontant_tva_vente() {
		return montant_tva_vente;
	}

	public void setMontant_tva_vente(Double montant_tva_vente) {
		this.montant_tva_vente = montant_tva_vente;
	}

	public Double getMontant_ht_vente() {
		return montant_ht_vente;
	}

	public void setMontant_ht_vente(Double montant_ht_vente) {
		this.montant_ht_vente = montant_ht_vente;
	}

	public Double getTarif_unit_vente() {
		return tarif_unit_vente;
	}

	public void setTarif_unit_vente(Double tarif_unit_vente) {
		this.tarif_unit_vente = tarif_unit_vente;
	}

	public TVABean getTvaBean() {
		return tvaBean;
	}

	public void setTvaBean(TVABean tvaBean) {
		this.tvaBean = tvaBean;
	}

	public Double getMontant_ht_vente_reel() {
		return montant_ht_vente_reel;
	}

	public void setMontant_ht_vente_reel(Double montant_ht_vente_reel) {
		this.montant_ht_vente_reel = montant_ht_vente_reel;
	}

	public Double getTaux_remise_ligne() {
		return taux_remise_ligne;
	}

	public void setTaux_remise_ligne(Double taux_remise_ligne) {
		this.taux_remise_ligne = taux_remise_ligne;
	}

	public Double getMontant_remise_ligne() {
		return montant_remise_ligne;
	}

	public void setMontant_remise_ligne(Double montant_remise_ligne) {
		this.montant_remise_ligne = montant_remise_ligne;
	}

	public Double getMontant_ttc_vente() {
		return montant_ttc_vente;
	}

	public void setMontant_ttc_vente(Double montant_ttc_vente) {
		this.montant_ttc_vente = montant_ttc_vente;
	}

	public String getNbrBoxes() {
		return nbrBoxes;
	}

	public void setNbrBoxes(String nbrBoxes) {
		this.nbrBoxes = nbrBoxes;
	}

	 
 
	 

	 
}
