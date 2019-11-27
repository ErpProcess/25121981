package ERP.Process.Commerciale.Vente.Facture_client.model;

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

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Parametrage.TypeFacture.model.TypeFactureBean;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model.CompteBancaireBean;



@JsonAutoDetect
@Entity
@Table(name = "facture_client", schema = "vente")
public class Facture_clientBean extends GenericBean {
	
 
	private static final long serialVersionUID = -1699211604140866125L;


	@Id
	@Column
	private String fact_clt_id = "";
	
	@Column
	private String fact_ref_id = "";
	 
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "mod_reg_id", insertable = true, updatable = true)
	private ModeReglementBean   modReg ;
	
 
	@ManyToOne
	@JoinColumn(name = "clt_id", insertable = true, updatable = true)
	private ClientBean  client = new ClientBean();
	
	
	
	@ManyToOne
	@JoinColumn(name = "type_fact_id", insertable = true, updatable = true)
	private TypeFactureBean   typefact = new TypeFactureBean();
	
	@ManyToOne
	@JoinColumn(name = "etat_reg_facture", insertable = true, updatable = true)
	private Entite_etat_commercialeBean etat_reg = new Entite_etat_commercialeBean();
	
	
	@ManyToOne
	@JoinColumn(name = "cptbanribrib", insertable = true, updatable = true, referencedColumnName = "cptbanribrib")
	private CompteBancaireBean cpt_bank = new CompteBancaireBean();
	
	
	@Transient
	private String condition_select_mode="";
	
	
	@Transient
	private String select_many_facture="";
	
	@Transient
	private String avoir_and_reference="";
	 
	
	@Transient
	private  String total_quantite="";
	
	
	@Column
	private Date fact_date;
	
	@Transient
	private Date fact_date2;
	
	
	
	@Column
	private Date fact_date_edition;
	
	@Column
	private String observation="" ;

 
	@Transient
	private Double totnbrBox;
	
	
	public Double getTotnbrBox() {
		return totnbrBox;
	}

	public void setTotnbrBox(Double totnbrBox) {
		this.totnbrBox = totnbrBox;
	}

	@Column
	private Boolean exonere_tva = false;
	
	
	@Column
	private Double mnt_timb_fisc  ;
	
	@Column
	private Double total_tva_fact  ;
	
	@Column
	private Double total_ht_fact  ;
	
	
	
	
	@Column
	private Double avance_montant_vente  ;
	
	
	@Column
	private Double facture_remise  ;
	
	@Column
	private Double total_facture  ;
	
	@Column
	private Double net_a_payer  ;
	
	@Column
	private java.sql.Date date_cre;
	
	@Column
	private String usr_cre = "";
	
	
	@Column
	private java.sql.Date date_mod;
	
	
	@Column
	private String usr_mod = "";
	
	
	@Column
	private java.sql.Time time_cre;

	@Column
	private java.sql.Time time_mod;
	
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean etablissment = new EtablissementBean();
	
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise ;
	
	@Transient
	private  String btnPrintCertificat="display:none;";
	
	
	 
	@Transient
	private  Double retenuSource ;
	
	
	public String getBtnPrintCertificat() {
		return btnPrintCertificat;
	}

	public void setBtnPrintCertificat(String btnPrintCertificat) {
		this.btnPrintCertificat = btnPrintCertificat;
	}

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	public ClientBean getClient() {
		return client;
	}

	public void setClient(ClientBean client) {
		this.client = client;
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
	 

	public TypeFactureBean getTypefact() {
		return typefact;
	}

	public void setTypefact(TypeFactureBean typefact) {
		this.typefact = typefact;
	}

	 

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public EtablissementBean getEtablissment() {
		return etablissment;
	}

	public void setEtablissment(EtablissementBean etablissment) {
		this.etablissment = etablissment;
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

	 

	public String getFact_clt_id() {
		return fact_clt_id;
	}

	public void setFact_clt_id(String fact_clt_id) {
		this.fact_clt_id = fact_clt_id;
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

	public Boolean getExonere_tva() {
		return exonere_tva;
	}

	public void setExonere_tva(Boolean exonere_tva) {
		this.exonere_tva = exonere_tva;
	}

	public Double getMnt_timb_fisc() {
		return mnt_timb_fisc;
	}

	public void setMnt_timb_fisc(Double mnt_timb_fisc) {
		this.mnt_timb_fisc = mnt_timb_fisc;
	}

	public String getCondition_select_mode() {
		return condition_select_mode;
	}

	public void setCondition_select_mode(String condition_select_mode) {
		this.condition_select_mode = condition_select_mode;
	}

	public Double getAvance_montant_vente() {
		return avance_montant_vente;
	}

	public void setAvance_montant_vente(Double avance_montant_vente) {
		this.avance_montant_vente = avance_montant_vente;
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

 
	 

	public String getTotal_quantite() {
		return total_quantite;
	}

	public void setTotal_quantite(String total_quantite) {
		this.total_quantite = total_quantite;
	}

	public Date getFact_date2() {
		return fact_date2;
	}

	public void setFact_date2(Date fact_date2) {
		this.fact_date2 = fact_date2;
	}

	public String getSelect_many_facture() {
		return select_many_facture;
	}

	public void setSelect_many_facture(String select_many_facture) {
		this.select_many_facture = select_many_facture;
	}

	public String getAvoir_and_reference() {
		return avoir_and_reference;
	}

	public void setAvoir_and_reference(String avoir_and_reference) {
		this.avoir_and_reference = avoir_and_reference;
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

	public Double getFacture_remise() {
		return facture_remise;
	}

	public void setFacture_remise(Double facture_remise) {
		this.facture_remise = facture_remise;
	}

	public Entite_etat_commercialeBean getEtat_reg() {
		return etat_reg;
	}

	public void setEtat_reg(Entite_etat_commercialeBean etat_reg) {
		this.etat_reg = etat_reg;
	}

	public CompteBancaireBean getCpt_bank() {
		return cpt_bank;
	}

	public void setCpt_bank(CompteBancaireBean cpt_bank) {
		this.cpt_bank = cpt_bank;
	}
	
	public String getFact_ref_id() {
		return fact_ref_id;
	}

	public void setFact_ref_id(String fact_ref_id) {
		this.fact_ref_id = fact_ref_id;
	}

	public ModeReglementBean getModReg() {
		return modReg;
	}

	public void setModReg(ModeReglementBean modReg) {
		this.modReg = modReg;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Double getRetenuSource() {
		return retenuSource;
	}

	public void setRetenuSource(Double retenuSource) {
		this.retenuSource = retenuSource;
	}

	 
	 

	 
	 
}
