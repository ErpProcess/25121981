package ERP.Process.Commerciale.Vente.FournitureVente.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.MouvementStockBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "det_fourniture_vente", schema = "vente")
public class DetFournitureVenteBean extends GenericBean {

	 
	private static final long serialVersionUID = -3140686640864359791L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer det_frn_id;

	@ManyToOne
	@JoinColumn(name = "frn_ve_id", insertable = true, updatable = true)
	private FournitureVenteBean fourniture;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkcode_barre = new Code_barreBean();
 
	@Column(name="is_vente" )
	private Boolean isVente = false;

	@Transient
	private String indx_row = "";

	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

	@Transient
	private String info = "";

	@Transient
	private String nature_produit = "";

	@Column
	private Double quantite;

	@Column
	private Double quantite_confirmer;

	@ManyToOne
	@JoinColumn(name = "tarif_vente_id", insertable = true, updatable = true)
	private TarificationBean tarifVente;

	@ManyToOne
	@JoinColumn(name = "trf_prim_id", insertable = true, updatable = true)
	private TarificationPrtvArticleBean tarifAchat;

	@Column
	private Double montant_tva_vente;

	@Column(name = "montant_remise_ligne")
	private Double montant_Remise_Ligne;

	@Column
	private Double taux_remise_ligne;

	@Column
	private Double montant_benefice;

	@Column
	private Double montant_ht_vente;

	@Transient
	private Double montant_ht_vente_reel;

	@Transient
	private Double montant_ttc_vente;

	@Transient
	private Double quantite_en_stock;

	@Transient
	private String methode_s = "G�n�ric";

	@Column
	private Double quantite_demander;

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
	
	
	@Column
	private Double cout_unit_moyen_pondere;
	 

	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "mvt_stock_id", insertable = true, updatable = true, nullable=true)
	private MouvementStockBean mvt_stock ;
	

	public Double getCout_unit_moyen_pondere() {
		return cout_unit_moyen_pondere;
	}

	public void setCout_unit_moyen_pondere(Double cout_unit_moyen_pondere) {
		this.cout_unit_moyen_pondere = cout_unit_moyen_pondere;
	}

	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise ;

	public Double getQuantite() {
		return quantite;
	}

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
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
	public Code_barreBean getFkcode_barre() {
		return fkcode_barre;
	}

	public void setFkcode_barre(Code_barreBean fkcode_barre) {
		this.fkcode_barre = fkcode_barre;
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

	public MouvementStockBean getMvt_stock() {
		return mvt_stock;
	}

	public void setMvt_stock(MouvementStockBean mvt_stock) {
		this.mvt_stock = mvt_stock;
	}

	public String getIndx_row() {
		return indx_row;
	}

	public Integer getDet_frn_id() {
		return det_frn_id;
	}

	public void setDet_frn_id(Integer det_frn_id) {
		this.det_frn_id = det_frn_id;
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

	public Double getQuantite_demander() {
		return quantite_demander;
	}

	public void setQuantite_demander(Double quantite_demander) {
		this.quantite_demander = quantite_demander;
	}

	public Double getQuantite_en_stock() {
		return quantite_en_stock;
	}

	public void setQuantite_en_stock(Double quantite_en_stock) {
		this.quantite_en_stock = quantite_en_stock;
	}

	public String getMethode_s() {
		return methode_s;
	}

	public void setMethode_s(String methode_s) {
		this.methode_s = methode_s;
	}

	public Double getQuantite_confirmer() {
		return quantite_confirmer;
	}

	public void setQuantite_confirmer(Double quantite_confirmer) {
		this.quantite_confirmer = quantite_confirmer;
	}

	public Double getMontant_Remise_Ligne() {
		return montant_Remise_Ligne;
	}

	public void setMontant_Remise_Ligne(Double montant_Remise_Ligne) {
		this.montant_Remise_Ligne = montant_Remise_Ligne;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Double getMontant_ht_vente_reel() {
		return montant_ht_vente_reel;
	}

	public void setMontant_ht_vente_reel(Double montant_ht_vente_reel) {
		this.montant_ht_vente_reel = montant_ht_vente_reel;
	}

	public Double getMontant_ttc_vente() {
		return montant_ttc_vente;
	}

	public void setMontant_ttc_vente(Double montant_ttc_vente) {
		this.montant_ttc_vente = montant_ttc_vente;
	}

	public Double getTaux_remise_ligne() {
		return taux_remise_ligne;
	}

	public void setTaux_remise_ligne(Double taux_remise_ligne) {
		this.taux_remise_ligne = taux_remise_ligne;
	}

	public Double getMontant_benefice() {
		return montant_benefice;
	}

	public void setMontant_benefice(Double montant_benefice) {
		this.montant_benefice = montant_benefice;
	}

	public String getNature_produit() {
		return nature_produit;
	}

	public void setNature_produit(String nature_produit) {
		this.nature_produit = nature_produit;
	}

	public FournitureVenteBean getFourniture() {
		return fourniture;
	}

	public void setFourniture(FournitureVenteBean fourniture) {
		this.fourniture = fourniture;
	}

	public TarificationBean getTarifVente() {
		return tarifVente;
	}

	public void setTarifVente(TarificationBean tarifVente) {
		this.tarifVente = tarifVente;
	}

	public TarificationPrtvArticleBean getTarifAchat() {
		return tarifAchat;
	}

	public void setTarifAchat(TarificationPrtvArticleBean tarifAchat) {
		this.tarifAchat = tarifAchat;
	}

	public Boolean getIsVente() {
		return isVente;
	}

	public void setIsVente(Boolean isVente) {
		this.isVente = isVente;
	}

}
