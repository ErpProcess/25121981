package ERP.Process.Commerciale.Achat.Reception_achat.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.Stock_article.model.MouvementStockBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "det_reception_achat", schema = "achat")
public class Det_reception_achatBean  extends  GenericBean{

	 
	private static final long serialVersionUID = 4065762969698707885L;
	

	@EmbeddedId
	private PkDetailleReception pk = new PkDetailleReception();

 

	@Column
	private Double quantite;

	@Column
	private Double montant_tva_achat;

	@Column
	private Double montant_ht_achat;

	@Column
	private Double quantite_demander;

	@ManyToOne
	@JoinColumn(name = "trf_prim_id", insertable = true, updatable = true,referencedColumnName="tarif_prim_id")
	private TarificationPrtvArticleBean tarif = new TarificationPrtvArticleBean();
	
 
	
	@Column
	private Double cout_unit_moyen_pondere;
	 
	 
 
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "mvt_stock_id", insertable = true, updatable = true, nullable=true)
	private MouvementStockBean mvt_stock ;
	 
	 
	@Transient
	private String indx_row = "";
	
	
	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

	@Transient
	private String id_entite = "";

	 

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

	@Column
	private Date date_fabrication;
	
	@Column
	private Date date_utilisation;
	
	@Column
	private Date date_peremption;
	
	@Transient
	private Double prix_achat_origin;
	
	
	@Transient
	private boolean prix_achat_is_changed =false;
	

	public Date getDate_fabrication() {
		return date_fabrication;
	}

	public void setDate_fabrication(Date date_fabrication) {
		this.date_fabrication = date_fabrication;
	}

	public Date getDate_utilisation() {
		return date_utilisation;
	}

	public void setDate_utilisation(Date date_utilisation) {
		this.date_utilisation = date_utilisation;
	}

	public Date getDate_peremption() {
		return date_peremption;
	}

	public void setDate_peremption(Date date_peremption) {
		this.date_peremption = date_peremption;
	}

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

	public void setPk(PkDetailleReception pk) {
		this.pk = pk;
	}

	public PkDetailleReception getPk() {
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

	 

	public Double getQuantite_demander() {
		return quantite_demander;
	}

	public void setQuantite_demander(Double quantite_demander) {
		this.quantite_demander = quantite_demander;
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

	public TarificationPrtvArticleBean getTarif() {
		return tarif;
	}

	public void setTarif(TarificationPrtvArticleBean tarif) {
		this.tarif = tarif;
	}

	public Double getCout_unit_moyen_pondere() {
		return cout_unit_moyen_pondere;
	}

	public void setCout_unit_moyen_pondere(Double cout_unit_moyen_pondere) {
		this.cout_unit_moyen_pondere = cout_unit_moyen_pondere;
	}

	public MouvementStockBean getMvt_stock() {
		return mvt_stock;
	}

	public void setMvt_stock(MouvementStockBean mvt_stock) {
		this.mvt_stock = mvt_stock;
	}

	public Double getPrix_achat_origin() {
		return prix_achat_origin;
	}

	public void setPrix_achat_origin(Double prix_achat_origin) {
		this.prix_achat_origin = prix_achat_origin;
	}

	public boolean isPrix_achat_is_changed() {
		return prix_achat_is_changed;
	}

	public void setPrix_achat_is_changed(boolean prix_achat_is_changed) {
		this.prix_achat_is_changed = prix_achat_is_changed;
	}

	 
	

}
