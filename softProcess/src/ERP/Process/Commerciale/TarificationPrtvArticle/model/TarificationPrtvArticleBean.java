package ERP.Process.Commerciale.TarificationPrtvArticle.model;

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

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;

@JsonAutoDetect
@Entity
@Table(name = "tarification_primitive_article", schema = "gestioncommerciale")
public class TarificationPrtvArticleBean extends GenericBean {

	private static final long serialVersionUID = 6164328983235323601L;
	
	@Id
	@Column
	private String tarif_prim_id = "";

	@Column
	private Date date_prim_trf;

	  
	@ManyToOne
	@JoinColumn(name = "grp_prim_trf_id", insertable = true, updatable = true, referencedColumnName = "grp_prim_trf_id")
	private GrpTarifPrimitivBean groupe = new GrpTarifPrimitivBean();
	
	

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkCode_barre = new Code_barreBean();

	 
	
	
	@ManyToOne
	@JoinColumn(name = "tva_id", insertable = true, updatable = true, referencedColumnName = "tva_id")
	private TVABean tvaBean = new TVABean();
	
	
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true , updatable = true ,  referencedColumnName = "fct_id"  )
	private FonctionBean modeBean = new FonctionBean();
	
	
	@ManyToOne
	@JoinColumn(name = "mode_calcul", insertable = true, updatable = true)
	private Entite_etat_commercialeBean mode_cal = new Entite_etat_commercialeBean();
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etablissement", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "societe", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise ;
	
	
	@Transient
	private  String condition_etat_achat="";
	
	@Transient
	private  String inputFocus="";
	
	

	@Column
	private Double tarif_unit_article;
	
	
	
	@Column
	private Double valeur_tva;
	
	@Column
	private Double tarif_unit_ttc;
	
	

	@Column
	private Double taux_remise;

	@Column
	private String usr_cre = "";

	@Column
	private java.sql.Time time_cre;

	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";

	@Column
	private java.sql.Date date_mod;

	@Column
	private java.sql.Time time_mod;

	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";
	@Transient
	private String to_check = "";
	@Transient
	private String id_entite = "";

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

	 

	public String getTarif_prim_id() {
		return tarif_prim_id;
	}

	public void setTarif_prim_id(String tarif_prim_id) {
		this.tarif_prim_id = tarif_prim_id;
	}

	public Date getDate_prim_trf() {
		return date_prim_trf;
	}

	public void setDate_prim_trf(Date date_prim_trf) {
		this.date_prim_trf = date_prim_trf;
	}

	 
	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}

	 

	public Double getTarif_unit_article() {
		return tarif_unit_article;
	}

	public void setTarif_unit_article(Double tarif_unit_article) {
		this.tarif_unit_article = tarif_unit_article;
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

	public TVABean getTvaBean() {
		return tvaBean;
	}

	public void setTvaBean(TVABean tvaBean) {
		this.tvaBean = tvaBean;
	}

	public GrpTarifPrimitivBean getGroupe() {
		return groupe;
	}

	public void setGroupe(GrpTarifPrimitivBean groupe) {
		this.groupe = groupe;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public String getCondition_etat_achat() {
		return condition_etat_achat;
	}

	public void setCondition_etat_achat(String condition_etat_achat) {
		this.condition_etat_achat = condition_etat_achat;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public String getInputFocus() {
		return inputFocus;
	}

	public void setInputFocus(String inputFocus) {
		this.inputFocus = inputFocus;
	}

	public Double getTarif_unit_ttc() {
		return tarif_unit_ttc;
	}

	public void setTarif_unit_ttc(Double tarif_unit_ttc) {
		this.tarif_unit_ttc = tarif_unit_ttc;
	}

	public Double getValeur_tva() {
		return valeur_tva;
	}

	public void setValeur_tva(Double valeur_tva) {
		this.valeur_tva = valeur_tva;
	}

	public Entite_etat_commercialeBean getMode_cal() {
		return mode_cal;
	}

	public void setMode_cal(Entite_etat_commercialeBean mode_cal) {
		this.mode_cal = mode_cal;
	}

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	 

}
