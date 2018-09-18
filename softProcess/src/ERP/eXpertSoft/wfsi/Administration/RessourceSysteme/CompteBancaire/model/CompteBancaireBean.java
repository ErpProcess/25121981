package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
@JsonAutoDetect
@Entity
@Table(name = "compte_bancaire", schema = "administration")
public class  CompteBancaireBean   extends  GenericBean {
	
 
	private static final long serialVersionUID = -2973649405349662476L;
 @Id
 @Column	private String  cptbanribrib =""; 
 @Column	private String  cptbanribrs =""; 
  
 @Column	private String  cptbanadr =""; 
 @Column	private String  bancod =""; 
 @Column	private String  cptbanjoucod =""; 
 @Column	private String  cptbancptcom =""; 
 @Column	private String  sitcod =""; 
 @Column	private String  usr_cre =""; 
 @Column	private java.sql.Date  date_mod;
 @Column	private java.sql.Date  date_cre;
 @Column	private String  usr_mod =""; 
 
	public void setCptbanribrib (String  cptbanribrib) {
		this.cptbanribrib = cptbanribrib;
	}
	public String getCptbanribrib() {
		return cptbanribrib;
	}
	public void setCptbanribrs (String  cptbanribrs) {
		this.cptbanribrs = cptbanribrs;
	}
	public String getCptbanribrs() {
		return cptbanribrs;
	}
	public void setCptbanadr (String  cptbanadr) {
		this.cptbanadr = cptbanadr;
	}
	public String getCptbanadr() {
		return cptbanadr;
	}
	public void setBancod (String  bancod) {
		this.bancod = bancod;
	}
	public String getBancod() {
		return bancod;
	}
	public void setCptbanjoucod (String  cptbanjoucod) {
		this.cptbanjoucod = cptbanjoucod;
	}
	public String getCptbanjoucod() {
		return cptbanjoucod;
	}
	public void setCptbancptcom (String  cptbancptcom) {
		this.cptbancptcom = cptbancptcom;
	}
	public String getCptbancptcom() {
		return cptbancptcom;
	}
	public void setSitcod (String  sitcod) {
		this.sitcod = sitcod;
	}
	public String getSitcod() {
		return sitcod;
	}
	public void setUsr_cre (String  usr_cre) {
		this.usr_cre = usr_cre;
	}
	public String getUsr_cre() {
		return usr_cre;
	}
	public void setDate_mod (java.sql.Date  date_mod) {
		this.date_mod = date_mod;
	}
	public java.sql.Date getDate_mod() {
		return date_mod;
	}
	public void setDate_cre (java.sql.Date  date_cre) {
		this.date_cre = date_cre;
	}
	public java.sql.Date getDate_cre() {
		return date_cre;
	}
	public void setUsr_mod (String  usr_mod) {
		this.usr_mod = usr_mod;
	}
	public String getUsr_mod() {
		return usr_mod;
	}
	}
