package ERP.Process.Commerciale.Achat.Facture_Fournisseur.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
 
@JsonAutoDetect
@Entity
@Table(name = "det_facture_fournisseur", schema = "achat")
public class Det_Fact_FournisseurBean {

	@EmbeddedId
	private PkDetailleFactFournisseur pk = new PkDetailleFactFournisseur();
	 
	@Column
	private Double quantite;

	@Column
	private Double montant_tva_achat;

	@Column
	private Double montant_ht_achat;
	
	 

	@ManyToOne
	@JoinColumn(name = "tva_id", insertable = true, updatable = true, referencedColumnName = "tva_id")
	private TVABean tvaBean = new TVABean();
	 
	@Transient
	private String indx_row = "";
	
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	@Transient
	private String id_entite = "";

	@Column
	private Double tarif_unit_achat;
	 

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

	public void setPk(PkDetailleFactFournisseur pk) {
		this.pk = pk;
	}

	public PkDetailleFactFournisseur getPk() {
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

	 

	public TVABean getTvaBean() {
		return tvaBean;
	}

	public void setTvaBean(TVABean tvaBean) {
		this.tvaBean = tvaBean;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getMontant_tva_achat() {
		return montant_tva_achat;
	}

	public void setMontant_tva_achat(Double montant_tva_achat) {
		this.montant_tva_achat = montant_tva_achat;
	}

	public Double getMontant_ht_achat() {
		return montant_ht_achat;
	}

	public void setMontant_ht_achat(Double montant_ht_achat) {
		this.montant_ht_achat = montant_ht_achat;
	}

	public Double getTarif_unit_achat() {
		return tarif_unit_achat;
	}

	public void setTarif_unit_achat(Double tarif_unit_achat) {
		this.tarif_unit_achat = tarif_unit_achat;
	}

 
	 

	 
}
