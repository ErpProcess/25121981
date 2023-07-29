package ERP.Process.Commerciale.Achat.Facture_Fournisseur.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.template.TarificationPrtvArticleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "detail_mvt_achat_facture", schema = "achat")
public class Detail_mvt_achat_factureBean extends GenericBean  {

 
 
 
	private static final long serialVersionUID = 97045033048529604L;


	@EmbeddedId
	private PkDetmvt_achat_facture pk = new PkDetmvt_achat_facture();
	 
	 
	@Column
	private Double quantite;

	@Column
	private Double montant_tva_achat;

	@Column
	private Double montant_ht_achat;
	
	 
	 
	   
	@Transient
	private String indx_row = "";
	
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	@Transient
	private String id_entite = "";

	
	

	@ManyToOne
	@JoinColumn(name = "tarif_prim_id", insertable = true, updatable = true)
	private TarificationPrtvArticleBean  tarif = new TarificationPrtvArticleBean();
	

	  
	 
	
	
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

	 

	public PkDetmvt_achat_facture getPk() {
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

	 
	 

	 

	public void setPk(PkDetmvt_achat_facture pk) {
		this.pk = pk;
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

	public TarificationPrtvArticleBean getTarif() {
		return tarif;
	}

	public void setTarif(TarificationPrtvArticleBean tarif) {
		this.tarif = tarif;
	}

	 
	 

	 
}
