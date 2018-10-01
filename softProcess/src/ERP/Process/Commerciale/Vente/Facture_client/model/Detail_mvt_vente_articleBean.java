package ERP.Process.Commerciale.Vente.Facture_client.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "detail_mvt_vente_article", schema = "vente")
public class Detail_mvt_vente_articleBean extends GenericBean  {

	 
 
	private static final long serialVersionUID = 3395585683796774717L;


	@EmbeddedId
	private PkDetMvtVenteArticle pk = new PkDetMvtVenteArticle();
	 
	 
	@Column
	private Double quantite;

	@Column
	private Double montant_tva_vente;

	@Column
	private Double montant_ht_vente;
	
	 
	 
	   
	@Transient
	private String indx_row = "";
	
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	@Transient
	private String id_entite = "";

	
	

	@ManyToOne
	@JoinColumn(name = "tarif_vente_id", insertable = true, updatable = true, nullable=true)
	private TarificationBean  tarif ;
	

	  
	 
	
	
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

	 

	public PkDetMvtVenteArticle getPk() {
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

	 

	 

	public void setPk(PkDetMvtVenteArticle pk) {
		this.pk = pk;
	}

	public TarificationBean getTarif() {
		return tarif;
	}

	public void setTarif(TarificationBean tarif) {
		this.tarif = tarif;
	}

	 
	 

	 
}
