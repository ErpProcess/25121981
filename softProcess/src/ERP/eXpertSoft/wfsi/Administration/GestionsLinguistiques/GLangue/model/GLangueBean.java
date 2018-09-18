package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model;

 

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;

 

@JsonAutoDetect
@Entity
@Table(name = "langue",schema="administration")
public class GLangueBean implements Serializable {
 

	
	private String lang_id ="";
	private String lang_libelle = "";
	private String lang_obs = "";
	private String lang_abrv = "";
	
	private String lang_not_null = "";	 
	
	@Column(name = "lang_ordre")
	private String lang_ordre = "";
	
	 
	
	@Column(name = "usr_cre")
	private String usr_cre = "";
	
	@Column(name = "date_cre")
	private Date date_cre ;
	
	
	
	@Column(name = "usr_mod")
	private String usr_mod = "";
	
	@Column(name = "date_mod")
	private Date date_mod ;
	
	
 
	
 
	
	
	

	@Id
	@Column(name = "lang_id", unique = true, nullable = false)
	public String getLang_id() {
		return lang_id;
	}


	public void setLang_id(String lang_id) {
		this.lang_id = lang_id;
	}

	@Column(name = "lang_abrv")
	public String getLang_abrv() {
		return lang_abrv;
	}


	public void setLang_abrv(String lang_abrv) {
		this.lang_abrv = lang_abrv;
	}


	@Column(name = "lang_libelle")
	public String getLang_libelle() {
		return lang_libelle;
	}


	public void setLang_libelle(String lang_libelle) {
		this.lang_libelle = lang_libelle;
	}

	@Column(name = "lang_obs")
	public String getLang_obs() {
		return lang_obs;
	}


	public void setLang_obs(String lang_obs) {
		this.lang_obs = lang_obs;
	}

	 


	public String getUsr_cre() {
		return usr_cre;
	}


	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}


	public Date getDate_cre() {
		return date_cre;
	}


	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}


	public String getUsr_mod() {
		return usr_mod;
	}


	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}


	public Date getDate_mod() {
		return date_mod;
	}


	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}


	public String getLang_ordre() {
		return lang_ordre;
	}


	public void setLang_ordre(String lang_ordre) {
		this.lang_ordre = lang_ordre;
	}

	@Transient 
	public String getLang_not_null() {
		return lang_not_null;
	}


	public void setLang_not_null(String lang_not_null) {
		this.lang_not_null = lang_not_null;
	}


	 

	
 


	 
	 

	 

}
