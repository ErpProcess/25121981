package ERP.Process.Commerciale.Article.model;

 
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.DBSchemaTable;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;

@JsonAutoDetect
@Entity
@Table(name = "article", schema = DBSchemaTable.SCHEMA_GESTIONCOMMERCIALE)
public class ArticleBean extends GenericBean {

 
 
	private static final long serialVersionUID = -1310193466505549226L;

	@EmbeddedId
	private PKArticleBean pk_article = new PKArticleBean();

	@ManyToOne
	@JoinColumn(name = "fam_id", insertable = true, updatable = true)
	private FamilleBean fam_art = new FamilleBean();

	@ManyToOne
	@JoinColumn(name = "sitcod", insertable = true, updatable = true)
	private Data_entite_simpleBean bean_sitcod = new Data_entite_simpleBean();

	@ManyToOne
	@JoinColumn(name = "artyp", insertable = true, updatable = true)
	private Entite_etat_commercialeBean bean_artyp = new Entite_etat_commercialeBean();
	
	
	@ManyToOne
	@JoinColumn(name = "mode_calcul_prix_vente", insertable = true, updatable = true)
	private Entite_etat_commercialeBean bean_mode_cal = new Entite_etat_commercialeBean();
	
	
	@ManyToOne
	@JoinColumn(name = "art_cath", insertable = true, updatable = true)
	private Entite_etat_commercialeBean cathegorie = new Entite_etat_commercialeBean();
	
	
	
	@ManyToOne 
	@JoinColumn(name = "unite_id", insertable = true, updatable = true)
	private UniteBean unitBean = new UniteBean();
	
	
	@Transient
	private String designation = "";
	
	@Transient
	private String designation_libelle = "";

	@Column
	private String ar_libelle = "";
	
	@Column
	private Boolean ar_exonorer = false;
	
	
	@Column
	private Boolean possede_date_peremption = false;
	
	
	@Column
	private Boolean initialiser_au_inv = false;
	
	
	
	@Column
	private Boolean par_lot = false;
	
	 
	

	@Column
	private String arcodbar = "";

	@Transient
	private String condition_personnalised_list = "";

	@Transient
	private String condition_personnalised_list_degre = "";

	@Transient
	private Date date_prixX;
	
	@Transient
	private Double prix_achat;
	
	@Transient
	private Double prix_vente;
	

	
	@Transient
	private Double prix_achatttc;
	
	@Transient
	private Double prix_ventettc;
	
	
	
	@Transient
	private Integer  depot_id;
	
	@Transient
	private String clt_id ;
	
	@Transient
	private Double prix_ar;
	
	
	
	@Column
	private Date date_cre;
	@Column
	private Date date_mod;
	@Column
	private String usr_cre = "";
	@Column
	private String usr_mod = "";
	
	@Column
	private String ar_obs = "";
	
	
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true , updatable = true ,  referencedColumnName = "fct_id"  )
	private FonctionBean modeBean = new FonctionBean();
	
	@ManyToOne
	@JoinColumn(name = "mode_choix_prix_vente", insertable = true , updatable = true    )
	private Entite_etat_commercialeBean mode = new Entite_etat_commercialeBean();
	
	
	@ManyToOne
	@JoinColumn(name = "mode_choix_lot", insertable = true , updatable = true    )
	private Entite_etat_commercialeBean choix = new Entite_etat_commercialeBean();
	
	
	@ManyToOne
	@JoinColumn(name = "tva_id", insertable = true, updatable = true, referencedColumnName = "tva_id")
	private TVABean tva = new TVABean();
	 
	
	@Column
	private Double stock_maximum ;
	@Column
	private Double stock_minimum  ;
	
	 
	
	 

	@Transient
	private  Map   maplang = new HashMap();
	
	@Column 
	private String  data_article_langue ="";
	 

 

	public Map getMaplang() {
		return maplang;
	}

	public void setMaplang(Map maplang) {
		this.maplang = maplang;
	}

	public String getData_article_langue() {
		return data_article_langue;
	}

	public void setData_article_langue(String data_article_langue) {
		this.data_article_langue = data_article_langue;
	}

	public Double getStock_maximum() {
		return stock_maximum;
	}

	public void setStock_maximum(Double stock_maximum) {
		this.stock_maximum = stock_maximum;
	}

	public Double getStock_minimum() {
		return stock_minimum;
	}

	public void setStock_minimum(Double stock_minimum) {
		this.stock_minimum = stock_minimum;
	}

	public void setAr_libelle(final String ar_libelle) {
		this.ar_libelle = ar_libelle;
	}

	public String getAr_libelle() {
		return ar_libelle;
	}

	public void setArcodbar(final String arcodbar) {
		this.arcodbar = arcodbar;
	}

	public String getArcodbar() {
		return arcodbar;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(final Date date_cre) {
		this.date_cre = date_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(final Date date_mod) {
		this.date_mod = date_mod;
	}

	public void setUsr_cre(final String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_mod(final String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setAr_obs(final String ar_obs) {
		this.ar_obs = ar_obs;
	}

	public String getAr_obs() {
		return ar_obs;
	}

	public FamilleBean getFam_art() {
		return fam_art;
	}

	public void setFam_art(final FamilleBean fam_art) {
		this.fam_art = fam_art;
	}

	public Date getDate_prixX() {
		return date_prixX;
	}

	public void setDate_prixX(final Date date_prixX) {
		this.date_prixX = date_prixX;
	}

	public Data_entite_simpleBean getBean_sitcod() {
		return bean_sitcod;
	}

	public void setBean_sitcod(final Data_entite_simpleBean bean_sitcod) {
		this.bean_sitcod = bean_sitcod;
	}

	public Double getPrix_ar() {
		return prix_ar;
	}

	public void setPrix_ar(final Double prix_ar) {
		this.prix_ar = prix_ar;
	}

	public Entite_etat_commercialeBean getBean_artyp() {
		return bean_artyp;
	}

	public void setBean_artyp(final Entite_etat_commercialeBean bean_artyp) {
		this.bean_artyp = bean_artyp;
	}

	public String getCondition_personnalised_list() {
		return condition_personnalised_list;
	}

	public void setCondition_personnalised_list(
			final String condition_personnalised_list) {
		this.condition_personnalised_list = condition_personnalised_list;
	}

	public String getCondition_personnalised_list_degre() {
		return condition_personnalised_list_degre;
	}

	public void setCondition_personnalised_list_degre(
			final String condition_personnalised_list_degre) {
		this.condition_personnalised_list_degre = condition_personnalised_list_degre;
	}

	public PKArticleBean getPk_article() {
		return pk_article;
	}

	public void setPk_article(final PKArticleBean pk) {
		this.pk_article = pk;
	}

	public Entite_etat_commercialeBean getBean_mode_cal() {
		return bean_mode_cal;
	}

	public void setBean_mode_cal(Entite_etat_commercialeBean bean_mode_cal) {
		this.bean_mode_cal = bean_mode_cal;
	}

	public UniteBean getUnitBean() {
		return unitBean;
	}

	public void setUnitBean(UniteBean unitBean) {
		this.unitBean = unitBean;
	}

	public Boolean getAr_exonorer() {
		return ar_exonorer;
	}

	public void setAr_exonorer(Boolean ar_exonorer) {
		this.ar_exonorer = ar_exonorer;
	}

	public Boolean getPossede_date_peremption() {
		return possede_date_peremption;
	}

	public void setPossede_date_peremption(Boolean possede_date_peremption) {
		this.possede_date_peremption = possede_date_peremption;
	}

	public Boolean getInitialiser_au_inv() {
		return initialiser_au_inv;
	}

	public void setInitialiser_au_inv(Boolean initialiser_au_inv) {
		this.initialiser_au_inv = initialiser_au_inv;
	}

	public Entite_etat_commercialeBean getCathegorie() {
		return cathegorie;
	}

	public void setCathegorie(Entite_etat_commercialeBean cathegorie) {
		this.cathegorie = cathegorie;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public Entite_etat_commercialeBean getMode() {
		return mode;
	}

	public void setMode(Entite_etat_commercialeBean mode) {
		this.mode = mode;
	}

	public Boolean getPar_lot() {
		return par_lot;
	}

	public void setPar_lot(Boolean par_lot) {
		this.par_lot = par_lot;
	}

	public TVABean getTva() {
		return tva;
	}

	public void setTva(TVABean tva) {
		this.tva = tva;
	}

	 

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDesignation_libelle() {
		return designation_libelle;
	}

	public void setDesignation_libelle(String designation_libelle) {
		this.designation_libelle = designation_libelle;
	}

	public Entite_etat_commercialeBean getChoix() {
		return choix;
	}

	public void setChoix(Entite_etat_commercialeBean choix) {
		this.choix = choix;
	}

	public Double getPrix_achat() {
		return prix_achat;
	}

	public void setPrix_achat(Double prix_achat) {
		this.prix_achat = prix_achat;
	}

	public Double getPrix_vente() {
		return prix_vente;
	}

	public void setPrix_vente(Double prix_vente) {
		this.prix_vente = prix_vente;
	}

	public Integer getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(Integer depot_id) {
		this.depot_id = depot_id;
	}

	public String getClt_id() {
		return clt_id;
	}

	public void setClt_id(String clt_id) {
		this.clt_id = clt_id;
	}

	public Double getPrix_achatttc() {
		return prix_achatttc;
	}

	public void setPrix_achatttc(Double prix_achatttc) {
		this.prix_achatttc = prix_achatttc;
	}

	public Double getPrix_ventettc() {
		return prix_ventettc;
	}

	public void setPrix_ventettc(Double prix_ventettc) {
		this.prix_ventettc = prix_ventettc;
	}

	 

}
