package ERP.Process.Commerciale.Stock.Stock_article.model;

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

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "stock_article", schema = "stock")
public class Stock_articleBean extends GenericBean {

	private static final long serialVersionUID = -6380011267166838147L;

	@EmbeddedId
	private PkStock pk = new PkStock();

	@Column
	private Double quantite_init;

	@Column
	private Double quantite_recept;
	
	@Column
	private Double quantite_vendu;

	@Transient
	private Double mnt_ttc_recept;
	
	@Transient
	private Double mnt_ttc_vendu;
	
	
	@Column
	private Double mnt_tva_recept;

	@Column
	private Double mnt_ht_recept;

	

	@Column
	private Double mnt_tva_vente;

	@Column
	private Double mnt_ht_vente;

	@Column
	private Double solde_stock;
	
	@Column
	private Double solde_stock_valeur;
	
	 
	
	@Column
	private Double solde_achat_ht;
	
	
	@Column
	private Double cout_unitaire_moyen_pondere;
	
	 
	@Column
	private Double solde_vente_ht;
	
	@Column
	private Double solde_achat_tva;
	
	
	@Column
	private Double solde_vente_tva;
	
	
	
	@Column
	private String stock_article_id = "";
	

	@Column
	private String usr_cre = "";
	
	
	
	@Column
	private Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private Date date_mod;

	@Transient
	private Date date_stock2;
	
	
	
	@Transient
	private Double quantite_perte;
	
	
	@Transient
	private Double quantite_retour;
	
	

	@Transient
	private String condition_list_article = "";

	@Transient
	private String condition_max_date_stock = "";
	
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise ;
	
	
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id_vente", insertable = true, updatable = true)
	private DeviseBean  deviseVente ;

	/*@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etablissement", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "societe", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}*/

	public DeviseBean getDevise() {
		return devise;
	}

	public DeviseBean getDeviseVente() {
		return deviseVente;
	}

	public void setDeviseVente(DeviseBean deviseVente) {
		this.deviseVente = deviseVente;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public PkStock getPk() {
		return pk;
	}

	public void setPk(PkStock pk) {
		this.pk = pk;
	}

	public Double getQuantite_init() {
		return quantite_init;
	}

	public void setQuantite_init(Double quantite_init) {
		this.quantite_init = quantite_init;
	}

	public Double getQuantite_recept() {
		return quantite_recept;
	}

	public void setQuantite_recept(Double quantite_recept) {
		this.quantite_recept = quantite_recept;
	}

	public Double getQuantite_vendu() {
		return quantite_vendu;
	}

	public void setQuantite_vendu(Double quantite_vendu) {
		this.quantite_vendu = quantite_vendu;
	}

	public Double getSolde_stock() {
		return solde_stock;
	}

	public void setSolde_stock(Double solde_stock) {
		this.solde_stock = solde_stock;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public Date getDate_stock2() {
		return date_stock2;
	}

	public void setDate_stock2(Date date_stock2) {
		this.date_stock2 = date_stock2;
	}

	public String getCondition_list_article() {
		return condition_list_article;
	}

	public void setCondition_list_article(String condition_list_article) {
		this.condition_list_article = condition_list_article;
	}

	public String getCondition_max_date_stock() {
		return condition_max_date_stock;
	}

	public void setCondition_max_date_stock(String condition_max_date_stock) {
		this.condition_max_date_stock = condition_max_date_stock;
	}

	public Double getMnt_tva_recept() {
		return mnt_tva_recept;
	}

	public void setMnt_tva_recept(Double mnt_tva_recept) {
		this.mnt_tva_recept = mnt_tva_recept;
	}

	public Double getMnt_ht_recept() {
		return mnt_ht_recept;
	}

	public void setMnt_ht_recept(Double mnt_ht_recept) {
		this.mnt_ht_recept = mnt_ht_recept;
	}

	public Double getMnt_tva_vente() {
		return mnt_tva_vente;
	}

	public void setMnt_tva_vente(Double mnt_tva_vente) {
		this.mnt_tva_vente = mnt_tva_vente;
	}

	public Double getMnt_ht_vente() {
		return mnt_ht_vente;
	}

	public void setMnt_ht_vente(Double mnt_ht_vente) {
		this.mnt_ht_vente = mnt_ht_vente;
	}

	 

	public Double getSolde_achat_ht() {
		return solde_achat_ht;
	}

	public void setSolde_achat_ht(Double solde_achat_ht) {
		this.solde_achat_ht = solde_achat_ht;
	}

	public Double getSolde_vente_ht() {
		return solde_vente_ht;
	}

	public void setSolde_vente_ht(Double solde_vente_ht) {
		this.solde_vente_ht = solde_vente_ht;
	}

	public Double getSolde_achat_tva() {
		return solde_achat_tva;
	}

	public void setSolde_achat_tva(Double solde_achat_tva) {
		this.solde_achat_tva = solde_achat_tva;
	}

	public Double getSolde_vente_tva() {
		return solde_vente_tva;
	}

	public void setSolde_vente_tva(Double solde_vente_tva) {
		this.solde_vente_tva = solde_vente_tva;
	}

	public String getStock_article_id() {
		return stock_article_id;
	}

	public void setStock_article_id(String stock_article_id) {
		this.stock_article_id = stock_article_id;
	}

	public Double getSolde_stock_valeur() {
		return solde_stock_valeur;
	}

	public void setSolde_stock_valeur(Double solde_stock_valeur) {
		this.solde_stock_valeur = solde_stock_valeur;
	}

	public Double getQuantite_perte() {
		return quantite_perte;
	}

	public void setQuantite_perte(Double quantite_perte) {
		this.quantite_perte = quantite_perte;
	}

	public Double getQuantite_retour() {
		return quantite_retour;
	}

	public void setQuantite_retour(Double quantite_retour) {
		this.quantite_retour = quantite_retour;
	}

	public Double getMnt_ttc_recept() {
		return mnt_ttc_recept;
	}

	public void setMnt_ttc_recept(Double mnt_ttc_recept) {
		this.mnt_ttc_recept = mnt_ttc_recept;
	}

	public Double getMnt_ttc_vendu() {
		return mnt_ttc_vendu;
	}

	public void setMnt_ttc_vendu(Double mnt_ttc_vendu) {
		this.mnt_ttc_vendu = mnt_ttc_vendu;
	}

	public Double getCout_unitaire_moyen_pondere() {
		return cout_unitaire_moyen_pondere;
	}

	public void setCout_unitaire_moyen_pondere(Double cout_unitaire_moyen_pondere) {
		this.cout_unitaire_moyen_pondere = cout_unitaire_moyen_pondere;
	}

}
