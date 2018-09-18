package ERP.Process.Commerciale.Vente.Client.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model.CompteBancaireBean;

@JsonAutoDetect
@Entity
@Table(name = "client", schema = "vente")
public class ClientBean extends GenericBean {
 
	private static final long serialVersionUID = -6212541479170688629L;
	@Id
	@Column
	private String clt_id = "";
	@Column
	private String clt_lib = "";
	@Column
	private String clt_adr = "";
	@Column
	private String clt_tel = "";
	
	@Column
	private Integer clt_ordre  ;
	
	@Column
	private String clt_method_export ;
	
	
	@Column
	private String clt_fax = "";
	@Column
	private String clt_email = "";
	@Column
	private String clt_numcpt = "";
	@Column
	private String clt_obs = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";
	@Column
	private String clt_bank = "";
	@Column
	private String clt_typ = "";
	 
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	
	 
	
	@ManyToOne
	@JoinColumn(name = "type_trf_id", insertable = true, updatable = true, referencedColumnName = "type_trf_id")
	private Type_tarificationBean typ_trfBean = new Type_tarificationBean();
	
	
	@ManyToOne
	@JoinColumn(name = "cptbanribrib", insertable = true, updatable = true, referencedColumnName = "cptbanribrib")
	private CompteBancaireBean compte = new CompteBancaireBean();
	

	public Type_tarificationBean getTyp_trfBean() {
		return typ_trfBean;
	}

	public void setTyp_trfBean(Type_tarificationBean typ_trfBean) {
		this.typ_trfBean = typ_trfBean;
	}

	public void setClt_lib(String clt_lib) {
		this.clt_lib = clt_lib;
	}

	public String getClt_lib() {
		return clt_lib;
	}

	public void setClt_adr(String clt_adr) {
		this.clt_adr = clt_adr;
	}

	public String getClt_adr() {
		return clt_adr;
	}

	public void setClt_tel(String clt_tel) {
		this.clt_tel = clt_tel;
	}

	public String getClt_tel() {
		return clt_tel;
	}

	public void setClt_fax(String clt_fax) {
		this.clt_fax = clt_fax;
	}

	public String getClt_fax() {
		return clt_fax;
	}

	public void setClt_email(String clt_email) {
		this.clt_email = clt_email;
	}

	public String getClt_email() {
		return clt_email;
	}

	public void setClt_numcpt(String clt_numcpt) {
		this.clt_numcpt = clt_numcpt;
	}

	public String getClt_numcpt() {
		return clt_numcpt;
	}

	public void setClt_obs(String clt_obs) {
		this.clt_obs = clt_obs;
	}

	public String getClt_obs() {
		return clt_obs;
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

	public void setClt_bank(String clt_bank) {
		this.clt_bank = clt_bank;
	}

	public String getClt_bank() {
		return clt_bank;
	}

	public void setClt_typ(String clt_typ) {
		this.clt_typ = clt_typ;
	}

	public String getClt_typ() {
		return clt_typ;
	}

	 

	public String getClt_id() {
		return clt_id;
	}

	public void setClt_id(String clt_id) {
		this.clt_id = clt_id;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public CompteBancaireBean getCompte() {
		return compte;
	}

	public void setCompte(CompteBancaireBean compte) {
		this.compte = compte;
	}

	public Integer getClt_ordre() {
		return clt_ordre;
	}

	public void setClt_ordre(Integer clt_ordre) {
		this.clt_ordre = clt_ordre;
	}

	public String getClt_method_export() {
		return clt_method_export;
	}

	public void setClt_method_export(String clt_method_export) {
		this.clt_method_export = clt_method_export;
	}
}
