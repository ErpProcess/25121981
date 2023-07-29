package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.format.annotation.DateTimeFormat;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model.PaysvilleposteBean;

 
@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "utilisateur", schema = "administration")
public class UtilisateurBean  implements  Serializable   {
	
    public UtilisateurBean() { 
    	super(); 
    	}

	@Id
	@Column(name = "usr_id", unique = true, nullable = false)
	private String usr_id = "";
	
	@Column
	private String usr_login = "";

	@ManyToOne
	@JoinColumn(name = "usr_cath", insertable = false, updatable = false)
	private Data_entite_simpleBean data_entite = new Data_entite_simpleBean();

	@ManyToOne
	@JoinColumn(name = "usr_piece", insertable = false, updatable = false)
	private Data_entite_simpleBean data_entite2 = new Data_entite_simpleBean();

	@ManyToOne
	@JoinColumn(name = "paycod", insertable = false, updatable = false)
	private PaysvilleposteBean bean_pays = new PaysvilleposteBean();

	 

	 
	@Column(name = "usr_pwd")
	private String usr_pwd = "";

	@Column(name = "usr_nom")
	private String usr_nom = "";

	@Column(name = "usr_pre")
	private String usr_pre = "";
	
	
	@Transient
	private String nom_prenom = "";
	

	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/YYYY", iso = DateTimeFormat.ISO.DATE, style = "SS")
	private java.util.Date usr_date_naiss;

	@Column(name = "usr_adr")
	private String usr_adr = "";

	@Column(name = "usr_tel")
	private Float usr_tel;

	@Column(name = "usr_gsm")
	private Float usr_gsm;

	@Column(name = "usr_mail")
	private String usr_mail = "";

 
	
	@ManyToOne
	@JoinColumn(name = "prf_id" , insertable = true , updatable = true ,referencedColumnName="prf_id")
	private ProfileBean pfrBean = new ProfileBean();
	
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean etab_bean = new EtablissementBean();
	
	

	@Column(name = "usr_type")
	private String usr_type;	

	@Column(name = "usr_etat")
	private String usr_etat;

	 

	@Column(name = "usr_obs")
	private String usr_obs = "";

	@Column(name = "usr_cre")
	private String usr_cre = "";

	@Column
	@Temporal(TemporalType.DATE)
	private java.util.Date date_cre;

	@Column(name = "usr_mod")
	private String usr_mod = "";

	@Column(name = "date_mod")
	private Date date_mod;

	@Column(name = "paycod")
	private String paycod = "";

	@Column(name = "usr_sexe")
	private String usr_sexe = "";

	@Column(name = "usr_lang")
	private String usr_lang = "";

	@Column(name = "usr_photo")
	private String usr_photo = "";

	@Column(name = "region")
	private String region = "";

	@Column(name = "code_postal")
	private String code_postal = "";

	@Column(name = "usr_civil")
	private String usr_civil = "";

	@Column(name = "usr_ville")
	private String usr_ville = "";

	@Column(name = "usr_piece")
	private String usr_piece = "";

	@Column(name = "usr_num_piece")
	private String usr_num_piece = "";

	@Column(name = "usr_cath")
	private String usr_cath = "";

	public String getUsr_sexe() {
		return usr_sexe;
	}

	public void setUsr_sexe(String usr_sexe) {
		this.usr_sexe = usr_sexe;
	}

	public String getUsr_lang() {
		return usr_lang;
	}

	public void setUsr_lang(String usr_lang) {
		this.usr_lang = usr_lang;
	}

	public String getUsr_photo() {
		return usr_photo;
	}

	public void setUsr_photo(String usr_photo) {
		this.usr_photo = usr_photo;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getUsr_civil() {
		return usr_civil;
	}

	public void setUsr_civil(String usr_civil) {
		this.usr_civil = usr_civil;
	}

	public String getUsr_ville() {
		return usr_ville;
	}

	public void setUsr_ville(String usr_ville) {
		this.usr_ville = usr_ville;
	}

	public String getUsr_piece() {
		return usr_piece;
	}

	public void setUsr_piece(String usr_piece) {
		this.usr_piece = usr_piece;
	}

	public String getUsr_num_piece() {
		return usr_num_piece;
	}

	public void setUsr_num_piece(String usr_num_piece) {
		this.usr_num_piece = usr_num_piece;
	}

	public String getUsr_cath() {
		return usr_cath;
	}

	public void setUsr_cath(String usr_cath) {
		this.usr_cath = usr_cath;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsr_pwd() {
		return usr_pwd;
	}

	public void setUsr_pwd(String usr_pwd) {
		this.usr_pwd = usr_pwd;
	}

	public String getUsr_nom() {
		return usr_nom;
	}

	public void setUsr_nom(String usr_nom) {
		this.usr_nom = usr_nom;
	}

	public String getUsr_pre() {
		return usr_pre;
	}

	public void setUsr_pre(String usr_pre) {
		this.usr_pre = usr_pre;
	}

	public String getUsr_mail() {
		return usr_mail;
	}

	public void setUsr_mail(String usr_mail) {
		this.usr_mail = usr_mail;
	}

	public String getUsr_adr() {
		return usr_adr;
	}

	public void setUsr_adr(String usr_adr) {
		this.usr_adr = usr_adr;
	}

	 

	public String getUsr_obs() {
		return usr_obs;
	}

	public void setUsr_obs(String usr_obs) {
		this.usr_obs = usr_obs;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public java.util.Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(java.util.Date date_cre) {
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

	public Float getUsr_tel() {
		return usr_tel;
	}

	public void setUsr_tel(Float usr_tel) {
		this.usr_tel = usr_tel;
	}

	public Float getUsr_gsm() {
		return usr_gsm;
	}

	public void setUsr_gsm(Float usr_gsm) {
		this.usr_gsm = usr_gsm;
	}

	public String getUsr_type() {
		return usr_type;
	}

	public void setUsr_type(String usr_type) {
		this.usr_type = usr_type;
	}

	public String getUsr_etat() {
		return usr_etat;
	}

	public void setUsr_etat(String usr_etat) {
		this.usr_etat = usr_etat;
	}

 

	public String getPaycod() {
		return paycod;
	}

	public void setPaycod(String paycod) {
		this.paycod = paycod;
	}

	public Data_entite_simpleBean getData_entite() {
		return data_entite;
	}

	public void setData_entite(Data_entite_simpleBean data_entite) {
		this.data_entite = data_entite;
	}

	public Data_entite_simpleBean getData_entite2() {
		return data_entite2;
	}

	public void setData_entite2(Data_entite_simpleBean data_entite2) {
		this.data_entite2 = data_entite2;
	}

	public PaysvilleposteBean getBean_pays() {
		return bean_pays;
	}

	public void setBean_pays(PaysvilleposteBean bean_pays) {
		this.bean_pays = bean_pays;
	}

	 

 

	public java.util.Date getUsr_date_naiss() {

		return usr_date_naiss;

	}

	public void setUsr_date_naiss(java.util.Date usr_date_naiss) {
		this.usr_date_naiss = usr_date_naiss;
	}

	public ProfileBean getPfrBean() {
		return pfrBean;
	}

	public void setPfrBean(ProfileBean pfrBean) {
		this.pfrBean = pfrBean;
	}

	public EtablissementBean getEtab_bean() {
		return etab_bean;
	}

	public void setEtab_bean(EtablissementBean etab_bean) {
		this.etab_bean = etab_bean;
	}

	public String getNom_prenom() {
		return nom_prenom;
	}

	public void setNom_prenom(String nom_prenom) {
		this.nom_prenom = nom_prenom;
	}

	public String getUsr_login() {
		return usr_login;
	}

	public void setUsr_login(String usr_login) {
		this.usr_login = usr_login;
	}

}
