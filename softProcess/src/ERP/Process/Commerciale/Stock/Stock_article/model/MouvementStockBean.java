package ERP.Process.Commerciale.Stock.Stock_article.model;

import java.io.Serializable;
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
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;

@JsonAutoDetect
@Entity
@Table(name = "mvt_stock_article", schema = "stock")
public class MouvementStockBean implements Serializable, Cloneable {
 
	private static final long serialVersionUID = -6525001296430241631L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mvt_stock_article_id;
	
	
	@ManyToOne
	@JoinColumn(name = "tarif_vente_id", insertable = true, updatable = true,nullable=true)
	private TarificationBean tarifVente;

	@ManyToOne
	@JoinColumn(name = "tarif_prim_id", insertable = true, updatable = true,nullable=true)
	private TarificationPrtvArticleBean tarifAchat;
	 
	 
	  
	@Column
	private Double cout_unitaire_moyen_pondere;
	
	
	@Column
	private Double solde_stock_valeur;
	
	

	@Column
	private Double solde_stock;
	 
	@Column
	private String document_com_id = "";
	
	
	@ManyToOne
	@JoinColumn(name = "nature_mvt_id", insertable = true, updatable = true)
	private NatureMvtCommercialeBean nat_mvt = new NatureMvtCommercialeBean();
	 
  
	
	@Column
	private Double quantite_enter;
	
	@Column
	private Double quantite_sorti;
	
	
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String indx_row = "";

	@Transient
	private String to_check = "";

	@Transient
	private String id_entite = "";
	
	@Column	private Date  date_stock;
	  
	@ManyToOne
    @JoinColumn(name = "depot_id", insertable = true, updatable = true)
    private DepotStockageBean depot = new DepotStockageBean();
	 

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkCode_barre = new Code_barreBean();
	
	 
	

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
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise ;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id_vente", insertable = true, updatable = true)
	private DeviseBean  deviseVente ;
	
	

	public DeviseBean getDeviseVente() {
		return deviseVente;
	}

	public void setDeviseVente(DeviseBean deviseVente) {
		this.deviseVente = deviseVente;
	}

	public Integer getMvt_stock_article_id() {
		return mvt_stock_article_id;
	}

	public void setMvt_stock_article_id(Integer mvt_stock_article_id) {
		this.mvt_stock_article_id = mvt_stock_article_id;
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

	public Double getCout_unitaire_moyen_pondere() {
		return cout_unitaire_moyen_pondere;
	}

	public void setCout_unitaire_moyen_pondere(Double cout_unitaire_moyen_pondere) {
		this.cout_unitaire_moyen_pondere = cout_unitaire_moyen_pondere;
	}

	public Double getSolde_stock_valeur() {
		return solde_stock_valeur;
	}

	public void setSolde_stock_valeur(Double solde_stock_valeur) {
		this.solde_stock_valeur = solde_stock_valeur;
	}

	public Double getSolde_stock() {
		return solde_stock;
	}

	public void setSolde_stock(Double solde_stock) {
		this.solde_stock = solde_stock;
	}

	public String getDocument_com_id() {
		return document_com_id;
	}

	public void setDocument_com_id(String document_com_id) {
		this.document_com_id = document_com_id;
	}

	public NatureMvtCommercialeBean getNat_mvt() {
		return nat_mvt;
	}

	public void setNat_mvt(NatureMvtCommercialeBean nat_mvt) {
		this.nat_mvt = nat_mvt;
	}

	public Double getQuantite_enter() {
		return quantite_enter;
	}

	public void setQuantite_enter(Double quantite_enter) {
		this.quantite_enter = quantite_enter;
	}

	public Double getQuantite_sorti() {
		return quantite_sorti;
	}

	public void setQuantite_sorti(Double quantite_sorti) {
		this.quantite_sorti = quantite_sorti;
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

	public Date getDate_stock() {
		return date_stock;
	}

	public void setDate_stock(Date date_stock) {
		this.date_stock = date_stock;
	}

	public DepotStockageBean getDepot() {
		return depot;
	}

	public void setDepot(DepotStockageBean depot) {
		this.depot = depot;
	}

	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
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

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	
	
 

 
	 
}
