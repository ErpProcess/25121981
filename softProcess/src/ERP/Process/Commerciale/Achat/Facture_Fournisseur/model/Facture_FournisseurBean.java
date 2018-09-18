package ERP.Process.Commerciale.Achat.Facture_Fournisseur.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Parametrage.TypeFacture.model.TypeFactureBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;

@JsonAutoDetect
@Entity
@Table(name = "facture_fournisseur", schema = "achat")
public class Facture_FournisseurBean {
	
	@Id
	@Column
	private String fact_frs_id = "";
	 
	
	@ManyToOne 
	@JoinColumn(name = "frs_id", insertable = true, updatable = true)
	private FournisseurBean      frs =  new FournisseurBean();
	
	@ManyToOne
	@JoinColumn(name = "file_id", insertable = true, updatable = true )
	private FileFactureFournisseur   myFile = new FileFactureFournisseur();
	 
	
	@ManyToOne
	@JoinColumn(name = "type_fact_id", insertable = true, updatable = true )
	private TypeFactureBean   typefact = new TypeFactureBean();
	
	
	@Column
	private  Date fact_date;
	
	
	@Transient
	private  Date fact_date2;
	
	
	
	
	
	@Column
	private Double facture_remise  ;
	
	@Column
	private Double total_facture  ;
	
	@Column
	private Double net_a_payer  ;
	
	@Column
	private Double total_tva_fact  ;
	
	@Column
	private Double total_ht_fact  ;
	
	
	
	@Column
	private java.sql.Time time_cre;

	@Column
	private java.sql.Time time_mod;
	@Column
	private Date fact_date_edition;
	
	
	@Transient
	private String indx_row = "";
	
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	@Transient
	private String id_entite = "";


	@Column
	private  Double avance_montant_achat;
	
	
	@Column
	private Boolean exonere_tva = false;
	@Column
	private Double montant_timbre_fisc;
	 
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();
	
	
	@ManyToOne
	@JoinColumn(name = "etat_reg_facture", insertable = true, updatable = true)
	private Entite_etat_commercialeBean etat_reg = new Entite_etat_commercialeBean();
	
	
	@Transient
	private String condition_select_mode="";
	
	
	@Transient
	private String select_many_facture="";
	
	
	
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean etablissment = new EtablissementBean();

	 
	 

	public String getFact_frs_id() {
		return fact_frs_id;
	}

	public void setFact_frs_id(String fact_frs_id) {
		this.fact_frs_id = fact_frs_id;
	}

	 

	 
 

	public Date getFact_date() {
		return fact_date;
	}

	public void setFact_date(Date fact_date) {
		this.fact_date = fact_date;
	}

	 
	 
	 
 

	public Date getFact_date_edition() {
		return fact_date_edition;
	}

	public void setFact_date_edition(Date fact_date_edition) {
		this.fact_date_edition = fact_date_edition;
	}

	public TypeFactureBean getTypefact() {
		return typefact;
	}

	public void setTypefact(TypeFactureBean typefact) {
		this.typefact = typefact;
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

	public EtablissementBean getEtablissment() {
		return etablissment;
	}

	public void setEtablissment(EtablissementBean etablissment) {
		this.etablissment = etablissment;
	}

	 

	public void setMontant_timbre_fisc(Double montant_timbre_fisc) {
		this.montant_timbre_fisc = montant_timbre_fisc;
	}

 

	public FournisseurBean getFrs() {
		return frs;
	}

	public void setFrs(FournisseurBean frs) {
		this.frs = frs;
	}

	public Double getMontant_timbre_fisc() {
		return montant_timbre_fisc;
	}

	 

	public Boolean getExonere_tva() {
		return exonere_tva;
	}

	public void setExonere_tva(Boolean exonere_tva) {
		this.exonere_tva = exonere_tva;
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

	public Double getAvance_montant_achat() {
		return avance_montant_achat;
	}

	public void setAvance_montant_achat(Double avance_montant_achat) {
		this.avance_montant_achat = avance_montant_achat;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public String getCondition_select_mode() {
		return condition_select_mode;
	}

	public void setCondition_select_mode(String condition_select_mode) {
		this.condition_select_mode = condition_select_mode;
	}

	public String getSelect_many_facture() {
		return select_many_facture;
	}

	public void setSelect_many_facture(String select_many_facture) {
		this.select_many_facture = select_many_facture;
	}

	public Double getFacture_remise() {
		return facture_remise;
	}

	public void setFacture_remise(Double facture_remise) {
		this.facture_remise = facture_remise;
	}

	public Double getTotal_facture() {
		return total_facture;
	}

	public void setTotal_facture(Double total_facture) {
		this.total_facture = total_facture;
	}

	public Double getNet_a_payer() {
		return net_a_payer;
	}

	public void setNet_a_payer(Double net_a_payer) {
		this.net_a_payer = net_a_payer;
	}

	public Double getTotal_tva_fact() {
		return total_tva_fact;
	}

	public void setTotal_tva_fact(Double total_tva_fact) {
		this.total_tva_fact = total_tva_fact;
	}

	public Double getTotal_ht_fact() {
		return total_ht_fact;
	}

	public void setTotal_ht_fact(Double total_ht_fact) {
		this.total_ht_fact = total_ht_fact;
	}

	public Date getFact_date2() {
		return fact_date2;
	}

	public void setFact_date2(Date fact_date2) {
		this.fact_date2 = fact_date2;
	}

	public Entite_etat_commercialeBean getEtat_reg() {
		return etat_reg;
	}

	public void setEtat_reg(Entite_etat_commercialeBean etat_reg) {
		this.etat_reg = etat_reg;
	}

	public FileFactureFournisseur getMyFile() {
		return myFile;
	}

	public void setMyFile(FileFactureFournisseur myFile) {
		this.myFile = myFile;
	}

	 
}
