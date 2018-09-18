package ERP.Process.Commerciale.Tarification.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;

 
@JsonAutoDetect
@Entity
@Table(name = "tarification_vente_article", schema = "gestioncommerciale")
public class TarificationBean  extends GenericBean {
 
	private static final long serialVersionUID = 570616731981339205L;

	@Id 
	@Column
	private String tarif_vente_id ;
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	     @JoinColumn(name = "ar_id", insertable = true, updatable = true,referencedColumnName="ar_id"),
	     @JoinColumn(name = "code_barre", insertable = true, updatable = true,referencedColumnName="code_barre"),
	})
	private Code_barreBean  fkCode_barre = new Code_barreBean();
	
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise ;
	
	@ManyToOne
	@JoinColumn(name = "tarif_prim_id", insertable = true, updatable = true,referencedColumnName="tarif_prim_id",nullable=true)
	private TarificationPrtvArticleBean cout = new TarificationPrtvArticleBean();
	 
	
	
	
	@ManyToOne
	@JoinColumn(name = "mode_calcul_prix_vente", insertable = true, updatable = true)
	private Entite_etat_commercialeBean bean_cal = new Entite_etat_commercialeBean();
	
	
	@Column
	private Date date_trf;
	
	
	@Transient
	private Date date_trf2;
	
	@Transient
	private Date date_trf3;
	
	
	@Transient
	private Boolean dernierPrix;
	
	

	 
	@ManyToOne
	@JoinColumn(name = "type_trf_id" , insertable = true , updatable = true ,referencedColumnName="type_trf_id")
	private Type_tarificationBean groupe = new Type_tarificationBean();
	
	
	
	@Column
	private Double	 tarif_unit_vente;
	
	
	@Column
	private Double	 tarif_unit_vente_tt;
	
	 
	@Column
	private Double	 tarif_vente_remise;
	
	 
	
	@Column
	private String num_serie  ;
	
	
	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean depot = new DepotStockageBean();
	
	 
	
	@Column
	private Boolean tarif_lot  ;
	
	@Transient
	private String condition_select_mode=""  ;
	
	@Transient
	private String inputFocus=""  ;
	
	
	@Transient
	private String condition_cathegorie=""  ;
	
	
	@ManyToOne
	@JoinColumn(name = "tva_id", insertable = true, updatable = true, referencedColumnName = "tva_id")
	private TVABean tvaBean = new TVABean();
	
	
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true , updatable = true ,  referencedColumnName = "fct_id"  )
	private FonctionBean modeBean = new FonctionBean();
	
	
 

	@Column
	private Double coef_trf;

	@Column
	private Double marge_vente;

	@Column
	private Double taux_remise;

	 

	

	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Date date_mod;

	@Transient
	private String indx_row = "";
	
	
	@Transient
	private String indx_row_next = "";
	
	
	@Transient
	private String to_check = "";
	
	
	@Transient
	private String id_entite = "";
	
	
	@Transient
	private String methode_prix = "";
	
	
	@Transient
	private String methode_prix2 = "";

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	 
 

	public Double getCoef_trf() {
		return coef_trf;
	}

	public void setCoef_trf(Double coef_trf) {
		this.coef_trf = coef_trf;
	}

	public Double getTaux_remise() {
		return taux_remise;
	}

	public void setTaux_remise(Double taux_remise) {
		this.taux_remise = taux_remise;
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

	 

	public Double getMarge_vente() {
		return marge_vente;
	}

	public void setMarge_vente(Double marge_vente) {
		this.marge_vente = marge_vente;
	}

	public String getMethode_prix() {
		return methode_prix;
	}

	public void setMethode_prix(String methode_prix) {
		this.methode_prix = methode_prix;
	}

	public String getTarif_vente_id() {
		return tarif_vente_id;
	}

	public void setTarif_vente_id(String tarif_vente_id) {
		this.tarif_vente_id = tarif_vente_id;
	}

	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}

	 

	 

	public Type_tarificationBean getGroupe() {
		return groupe;
	}

	public void setGroupe(Type_tarificationBean groupe) {
		this.groupe = groupe;
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

	public Date getDate_trf() {
		return date_trf;
	}

	public void setDate_trf(Date date_trf) {
		this.date_trf = date_trf;
	}

	public TarificationPrtvArticleBean getCout() {
		return cout;
	}

	public void setCout(TarificationPrtvArticleBean cout) {
		this.cout = cout;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public DepotStockageBean getDepot() {
		return depot;
	}

	public void setDepot(DepotStockageBean depot) {
		this.depot = depot;
	}

	 

	public Boolean getTarif_lot() {
		return tarif_lot;
	}

	public void setTarif_lot(Boolean tarif_lot) {
		this.tarif_lot = tarif_lot;
	}

	public String getCondition_select_mode() {
		return condition_select_mode;
	}

	public void setCondition_select_mode(String condition_select_mode) {
		this.condition_select_mode = condition_select_mode;
	}

	public Date getDate_trf2() {
		return date_trf2;
	}

	public void setDate_trf2(Date date_trf2) {
		this.date_trf2 = date_trf2;
	}

	public Boolean getDernierPrix() {
		return dernierPrix;
	}

	public void setDernierPrix(Boolean dernierPrix) {
		this.dernierPrix = dernierPrix;
	}

	public Date getDate_trf3() {
		return date_trf3;
	}

	public void setDate_trf3(Date date_trf3) {
		this.date_trf3 = date_trf3;
	}

	public Double getTarif_unit_vente_tt() {
		return tarif_unit_vente_tt;
	}

	public void setTarif_unit_vente_tt(Double tarif_unit_vente_tt) {
		this.tarif_unit_vente_tt = tarif_unit_vente_tt;
	}

	public String getInputFocus() {
		return inputFocus;
	}

	public void setInputFocus(String inputFocus) {
		this.inputFocus = inputFocus;
	}

	public Double getTarif_vente_remise() {
		return tarif_vente_remise;
	}

	public void setTarif_vente_remise(Double tarif_vente_remise) {
		this.tarif_vente_remise = tarif_vente_remise;
	}

	public String getNum_serie() {
		return num_serie;
	}

	public void setNum_serie(String num_serie) {
		this.num_serie = num_serie;
	}

	public String getMethode_prix2() {
		return methode_prix2;
	}

	public void setMethode_prix2(String methode_prix2) {
		this.methode_prix2 = methode_prix2;
	}

	public Entite_etat_commercialeBean getBean_cal() {
		return bean_cal;
	}

	public void setBean_cal(Entite_etat_commercialeBean bean_cal) {
		this.bean_cal = bean_cal;
	}

	public String getCondition_cathegorie() {
		return condition_cathegorie;
	}

	public void setCondition_cathegorie(String condition_cathegorie) {
		this.condition_cathegorie = condition_cathegorie;
	}

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	 

	 
}
