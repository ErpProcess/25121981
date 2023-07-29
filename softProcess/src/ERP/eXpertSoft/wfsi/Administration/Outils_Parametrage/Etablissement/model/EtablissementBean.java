package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;

@JsonAutoDetect
@Entity
@Table(name = "etablissement", schema = "administration")
public class EtablissementBean implements Serializable,Cloneable{

	 
 
   
	private static final long serialVersionUID = -3692631020310992665L;


	@EmbeddedId 
	private PKEtablissement pk_etab  = new PKEtablissement();
	
	
	@Column(name = "etab_lib")
	private String etab_lib = "";
	
	@Column(name = "etab_ordre")
	private BigDecimal etab_ordre  ;
	
 
	
	
	@Column(name = "etab_adrs")
	private String etab_adrs ;
	
	@Column(name = "etab_email")
	private String etab_email ;
	
	@Column(name = "etab_code_comptable")
	private String etab_code_comptable ;
	
	
	@Column(name = "etab_tel")
	private BigDecimal etab_tel ;
	
	
	@Column(name = "etab_fax")
	private BigDecimal etab_fax ;
	
	
	@Column(name = "formatage")
	private String formatage ;
	 
	
	@ManyToOne 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise =  new DeviseBean();
	  
	
	@ManyToOne
	@JoinColumn(name = "etab_type", insertable = true, updatable = true)
	private Data_entite_simpleBean type = new Data_entite_simpleBean();
	   
	  

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

	public String getEtab_lib() {
		return etab_lib;
	}

	public void setEtab_lib(String etab_lib) {
		this.etab_lib = etab_lib;
	}

	public BigDecimal getEtab_ordre() {
		return etab_ordre;
	}

	public void setEtab_ordre(BigDecimal etab_ordre) {
		this.etab_ordre = etab_ordre;
	}

	public PKEtablissement getPk_etab() {
		return pk_etab;
	}

	public void setPk_etab(PKEtablissement pk_etab) {
		this.pk_etab = pk_etab;
	}

	 

	public String getEtab_adrs() {
		return etab_adrs;
	}

	public void setEtab_adrs(String etab_adrs) {
		this.etab_adrs = etab_adrs;
	}

	public String getEtab_email() {
		return etab_email;
	}

	public void setEtab_email(String etab_email) {
		this.etab_email = etab_email;
	}

	public String getEtab_code_comptable() {
		return etab_code_comptable;
	}

	public void setEtab_code_comptable(String etab_code_comptable) {
		this.etab_code_comptable = etab_code_comptable;
	}

	public BigDecimal getEtab_tel() {
		return etab_tel;
	}

	public void setEtab_tel(BigDecimal etab_tel) {
		this.etab_tel = etab_tel;
	}

	public BigDecimal getEtab_fax() {
		return etab_fax;
	}

	public void setEtab_fax(BigDecimal etab_fax) {
		this.etab_fax = etab_fax;
	}

	public Data_entite_simpleBean getType() {
		return type;
	}

	public void setType(Data_entite_simpleBean type) {
		this.type = type;
	}
	
	

	 

}
