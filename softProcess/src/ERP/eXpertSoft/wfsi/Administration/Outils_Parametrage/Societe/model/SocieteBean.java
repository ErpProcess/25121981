package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;

@JsonAutoDetect
@Entity
@Table(name = "societe", schema = "administration")
public class SocieteBean implements Serializable,Cloneable {
	
 
	private static final long serialVersionUID = 1L;

	@Id
	@Column 
	private String soc_id = "";

	@Column 
	private String soc_lib = "";
	
	@Column
	private BigDecimal file_id;
	
	@Transient
	private FileFactureFournisseur   myFile ;
	
	@Column 
	private BigDecimal soc_ordre  ;
	
	
	@Column 
	private Integer tva_default  ;
	
	
	 
	public BigDecimal getFile_id() {
		return file_id;
	}

	public void setFile_id(BigDecimal file_id) {
		this.file_id = file_id;
	}

	public FileFactureFournisseur getMyFile() {
		return myFile;
	}

	public void setMyFile(FileFactureFournisseur myFile) {
		this.myFile = myFile;
	}

	@Column 
	private String  lib_arab ="";
	@Column 
	private String  adresse ="";
	
	@Column 
	private String  pays ="";
	
	@Column 
	private String  data_societe_langue ="";
	
	@Transient
	private  Map   maplang = new HashMap();
	
	 
	 
	public Map getMaplang() {
		return maplang;
	}

	public void setMaplang(Map maplang) {
		this.maplang = maplang;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getData_societe_langue() {
		return data_societe_langue;
	}

	public void setData_societe_langue(String data_societe_langue) {
		this.data_societe_langue = data_societe_langue;
	}

	@Column 
	private String  adresse_arabe ="";
	@Column 
	private String  registre_commerce ="";
	@Column 
	private String   matricule_fiscale ="";
	@Column 
	private String    telephone ="";
	@Column 
	private String    fax ="";
	@Column 
	private Date    date_version_commerciale  ;
	@Column 
	private BigDecimal    capital ;
	@Column 
	private Double   montant_timbre_fiscal ;
	@Column 
	private String   code_journal_fr ="";
	@Column 
	private String   code_compte_fr_1 ="";
	@Column 
	private String    code_compte_fr_cc ="";
	@Column 
	private String   code_fr_centralisateur ="";
	@Column 
	private String    code_fr_centralisateur_cc ="";
	@Column 
	private String    compte_pr_ab_cmd ="";
	@Column 
	private String    compte_pr_excep ="";
	@Column 
	private String   compte_pr_consom ="";
	@Column 
	private String    compte_tva_transp ="";
	@Column 
	private String   compte_tva_compt ="";
	@Column 
	private String   compte_timbre_f ="";
	@Column 
	private String   code_journal_facturation ="";
	@Column 
	private String    code_cl ="";
	
	
	@Column(name = "formatage")
	private String formatage ;
	
	
	@ManyToOne 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise =  new DeviseBean();
	  
	
	@ManyToOne 
	@JoinColumn(name = "devise_vente_id", insertable = true, updatable = true)
	private DeviseBean  deviseVente =  new DeviseBean();
	

	public DeviseBean getDeviseVente() {
		return deviseVente;
	}

	public void setDeviseVente(DeviseBean deviseVente) {
		this.deviseVente = deviseVente;
	}

	public String getFormatage() {
		return formatage;
	}

	public void setFormatage(String formatage) {
		this.formatage = formatage;
	}

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	public BigDecimal getSoc_ordre() {
		return soc_ordre;
	}

	public void setSoc_ordre(BigDecimal soc_ordre) {
		this.soc_ordre = soc_ordre;
	}

	public String getSoc_id() {
		return soc_id;
	}

	public void setSoc_id(String soc_id) {
		this.soc_id = soc_id;
	}

	public String getSoc_lib() {
		return soc_lib;
	}

	public void setSoc_lib(String soc_lib) {
		this.soc_lib = soc_lib;
	}

	public String getLib_arab() {
		return lib_arab;
	}

	public void setLib_arab(String lib_arab) {
		this.lib_arab = lib_arab;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getAdresse_arabe() {
		return adresse_arabe;
	}

	public void setAdresse_arabe(String adresse_arabe) {
		this.adresse_arabe = adresse_arabe;
	}

	public String getRegistre_commerce() {
		return registre_commerce;
	}

	public void setRegistre_commerce(String registre_commerce) {
		this.registre_commerce = registre_commerce;
	}

	public String getMatricule_fiscale() {
		return matricule_fiscale;
	}

	public void setMatricule_fiscale(String matricule_fiscale) {
		this.matricule_fiscale = matricule_fiscale;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Date getDate_version_commerciale() {
		return date_version_commerciale;
	}

	public void setDate_version_commerciale(Date date_version_commerciale) {
		this.date_version_commerciale = date_version_commerciale;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public Double getMontant_timbre_fiscal() {
		return montant_timbre_fiscal;
	}

	public void setMontant_timbre_fiscal(Double montant_timbre_fiscal) {
		this.montant_timbre_fiscal = montant_timbre_fiscal;
	}

	public String getCode_journal_fr() {
		return code_journal_fr;
	}

	public void setCode_journal_fr(String code_journal_fr) {
		this.code_journal_fr = code_journal_fr;
	}

	public String getCode_compte_fr_1() {
		return code_compte_fr_1;
	}

	public void setCode_compte_fr_1(String code_compte_fr_1) {
		this.code_compte_fr_1 = code_compte_fr_1;
	}

	public String getCode_compte_fr_cc() {
		return code_compte_fr_cc;
	}

	public void setCode_compte_fr_cc(String code_compte_fr_cc) {
		this.code_compte_fr_cc = code_compte_fr_cc;
	}

	public String getCode_fr_centralisateur() {
		return code_fr_centralisateur;
	}

	public void setCode_fr_centralisateur(String code_fr_centralisateur) {
		this.code_fr_centralisateur = code_fr_centralisateur;
	}

	public String getCode_fr_centralisateur_cc() {
		return code_fr_centralisateur_cc;
	}

	public void setCode_fr_centralisateur_cc(String code_fr_centralisateur_cc) {
		this.code_fr_centralisateur_cc = code_fr_centralisateur_cc;
	}

	public String getCompte_pr_ab_cmd() {
		return compte_pr_ab_cmd;
	}

	public void setCompte_pr_ab_cmd(String compte_pr_ab_cmd) {
		this.compte_pr_ab_cmd = compte_pr_ab_cmd;
	}

	public String getCompte_pr_excep() {
		return compte_pr_excep;
	}

	public void setCompte_pr_excep(String compte_pr_excep) {
		this.compte_pr_excep = compte_pr_excep;
	}

	public String getCompte_pr_consom() {
		return compte_pr_consom;
	}

	public void setCompte_pr_consom(String compte_pr_consom) {
		this.compte_pr_consom = compte_pr_consom;
	}

	public String getCompte_tva_transp() {
		return compte_tva_transp;
	}

	public void setCompte_tva_transp(String compte_tva_transp) {
		this.compte_tva_transp = compte_tva_transp;
	}

	public String getCompte_tva_compt() {
		return compte_tva_compt;
	}

	public void setCompte_tva_compt(String compte_tva_compt) {
		this.compte_tva_compt = compte_tva_compt;
	}

	public String getCompte_timbre_f() {
		return compte_timbre_f;
	}

	public void setCompte_timbre_f(String compte_timbre_f) {
		this.compte_timbre_f = compte_timbre_f;
	}

	public String getCode_journal_facturation() {
		return code_journal_facturation;
	}

	public void setCode_journal_facturation(String code_journal_facturation) {
		this.code_journal_facturation = code_journal_facturation;
	}

	public String getCode_cl() {
		return code_cl;
	}

	public void setCode_cl(String code_cl) {
		this.code_cl = code_cl;
	}

	public Integer getTva_default() {
		return tva_default;
	}

	public void setTva_default(Integer tva_default) {
		this.tva_default = tva_default;
	}

	 
	 

}
