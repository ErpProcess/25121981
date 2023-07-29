package ERP.Process.Commerciale.Stock.DocumentLot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "serie_article", schema = "stock")
public class SerieArticletBean extends GenericBean {

	private static final long serialVersionUID = 473815610245657165L;

 
	@EmbeddedId
	private PkSerie  pk = new PkSerie();
	
	@Column
	private String mvt_com_id="";
	
	@Transient
	private String detaille_serie = "";
	
	@Transient
	private String condition_fifo_lifo = "";
	
	@Transient
	private String condition_list_article="";
	
	@Transient
	private  String total_mnt_ht="";
	
	
	@Transient
	private  String total_mnt_tva="";
	
	
	@Transient
	private  String is_readonly="";
	
	
	@Transient
	private  String total_mnt_gen="";
	
	
	@Transient
	private  String total_quantite="";

	@Column
	private Date   date_serie;

	@Column
	private Date   date_fabrication;

	@Column
	private Date   date_utilisation;

	@Column
	private Date   date_peremption;

	@ManyToOne
	@JoinColumn(name = "nature_mvt_id", insertable = true, updatable = true)
	private NatureMvtCommercialeBean nature_mvt = new NatureMvtCommercialeBean();
	
	
	

	@Column
	private Double quantite;
	
	
	@Column
	private Double quantite_init;
	
	
	@Column
	private Double serie_achat_ht;
	
	
	@Column
	private Double serie_vente_ht;
	
	
	@Column
	private Integer serie_ordre;
	
	
	
	
	@Column
	private Double serie_achat_tva;
	
	
	@Transient
	private TarificationBean  vente = new TarificationBean();
	
	 
	@Transient
	private TarificationBean  nvente = new TarificationBean();
	
	 
	
	
	@Column
	private Double serie_vente_tva;
	
	
	@ManyToOne
	@JoinColumn(name = "achat_id", insertable = true, updatable = true,referencedColumnName="achat_id")
	private Reception_achatBean reception = new Reception_achatBean();
	
	
	
	@Transient
	private Double quantite_choisi;
 

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkCode_barre = new Code_barreBean();

	@ManyToOne
	@JoinColumn(name = "trf_prim_id", insertable = true, updatable = true)
	private TarificationPrtvArticleBean tarif = new TarificationPrtvArticleBean();
	

	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String indx_row = "";
	
	
 
	

	@Transient
	private String to_check = "";

	@Transient
	private String id_entite = "";

	 

	@Column
	private Boolean serie_bloque  ;

	 

	@Column
	private String observation = "";
	
	@Column
	private String selected ;
	
	

	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Date date_mod;
	
	@Column
	private java.sql.Time time_cre ;
	
	
	@Column
	private java.sql.Time time_mod ;
	
	
	
	
	

	 

	/*@ManyToOne
	@JoinColumns({           
		 @JoinColumn(name = "etablissement", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "societe", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean fk_etab_Bean = new EtablissementBean();*/
	
	
	
	@Transient
	private String condition_etat_serie = "";
	
	
	@ManyToOne
	@JoinColumn(name = "etat_serie", insertable = true, updatable = true)
	private Entite_etat_commercialeBean etat = new Entite_etat_commercialeBean();
	
	 
	

	 

	public String getCondition_etat_serie() {
		return condition_etat_serie;
	}

	public void setCondition_etat_serie(String condition_etat_serie) {
		this.condition_etat_serie = condition_etat_serie;
	}

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

	 
	public String getMvt_com_id() {
		return mvt_com_id;
	}

	public void setMvt_com_id(String mvt_com_id) {
		this.mvt_com_id = mvt_com_id;
	}

	 

	public Date getDate_serie() {
		return date_serie;
	}

	public void setDate_serie(Date date_serie) {
		this.date_serie = date_serie;
	}

	 

	public NatureMvtCommercialeBean getNature_mvt() {
		return nature_mvt;
	}

	public void setNature_mvt(NatureMvtCommercialeBean nature_mvt) {
		this.nature_mvt = nature_mvt;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

 

	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
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

	 
 

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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

	 
	 
	 

	 

	public PkSerie getPk() {
		return pk;
	}

	public void setPk(PkSerie pk) {
		this.pk = pk;
	}

	public String getCondition_fifo_lifo() {
		return condition_fifo_lifo;
	}

	public void setCondition_fifo_lifo(String condition_fifo_lifo) {
		this.condition_fifo_lifo = condition_fifo_lifo;
	}

	public String getCondition_list_article() {
		return condition_list_article;
	}

	public void setCondition_list_article(String condition_list_article) {
		this.condition_list_article = condition_list_article;
	}

	public String getIndx_row() {
		return indx_row;
	}

	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}

	public Double getQuantite_choisi() {
		return quantite_choisi;
	}

	public void setQuantite_choisi(Double quantite_choisi) {
		this.quantite_choisi = quantite_choisi;
	}

	public String getDetaille_serie() {
		return detaille_serie;
	}

	public void setDetaille_serie(String detaille_serie) {
		this.detaille_serie = detaille_serie;
	}

	public TarificationPrtvArticleBean getTarif() {
		return tarif;
	}

	public void setTarif(TarificationPrtvArticleBean tarif) {
		this.tarif = tarif;
	}

	public Entite_etat_commercialeBean getEtat() {
		return etat;
	}

	public void setEtat(Entite_etat_commercialeBean etat) {
		this.etat = etat;
	}

	public String getTotal_mnt_ht() {
		return total_mnt_ht;
	}

	public void setTotal_mnt_ht(String total_mnt_ht) {
		this.total_mnt_ht = total_mnt_ht;
	}

	public String getTotal_mnt_tva() {
		return total_mnt_tva;
	}

	public void setTotal_mnt_tva(String total_mnt_tva) {
		this.total_mnt_tva = total_mnt_tva;
	}

	public String getTotal_mnt_gen() {
		return total_mnt_gen;
	}

	public void setTotal_mnt_gen(String total_mnt_gen) {
		this.total_mnt_gen = total_mnt_gen;
	}

	public String getTotal_quantite() {
		return total_quantite;
	}

	public void setTotal_quantite(String total_quantite) {
		this.total_quantite = total_quantite;
	}

	public Double getQuantite_init() {
		return quantite_init;
	}

	public void setQuantite_init(Double quantite_init) {
		this.quantite_init = quantite_init;
	}

	public Double getSerie_achat_ht() {
		return serie_achat_ht;
	}

	public void setSerie_achat_ht(Double serie_achat_ht) {
		this.serie_achat_ht = serie_achat_ht;
	}

	public Double getSerie_vente_ht() {
		return serie_vente_ht;
	}

	public void setSerie_vente_ht(Double serie_vente_ht) {
		this.serie_vente_ht = serie_vente_ht;
	}

	public Double getSerie_achat_tva() {
		return serie_achat_tva;
	}

	public void setSerie_achat_tva(Double serie_achat_tva) {
		this.serie_achat_tva = serie_achat_tva;
	}

	public Double getSerie_vente_tva() {
		return serie_vente_tva;
	}

	public void setSerie_vente_tva(Double serie_vente_tva) {
		this.serie_vente_tva = serie_vente_tva;
	}

	public Reception_achatBean getReception() {
		return reception;
	}

	public void setReception(Reception_achatBean reception) {
		this.reception = reception;
	}

	public Boolean getSerie_bloque() {
		return serie_bloque;
	}

	public void setSerie_bloque(Boolean serie_bloque) {
		this.serie_bloque = serie_bloque;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public Integer getSerie_ordre() {
		return serie_ordre;
	}

	public void setSerie_ordre(Integer serie_ordre) {
		this.serie_ordre = serie_ordre;
	}

 

	public TarificationBean getVente() {
		return vente;
	}

	public void setVente(TarificationBean vente) {
		this.vente = vente;
	}

	public TarificationBean getNvente() {
		return nvente;
	}

	public void setNvente(TarificationBean nvente) {
		this.nvente = nvente;
	}

	public String getIs_readonly() {
		return is_readonly;
	}

	public void setIs_readonly(String is_readonly) {
		this.is_readonly = is_readonly;
	}

	 

	 

	 
	 
	 

}
