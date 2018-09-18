package ERP.Process.Commerciale.AchatDivers.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.format.annotation.DateTimeFormat;
@JsonAutoDetect
@Entity
@Table(name = "achat_non_stocker", schema = "gestioncommerciale")
public class  AchatDiversBean  {
	 @Id
	 @GeneratedValue
	 @Column	private Integer  ach_non_id; 
	 
	 @Column	private String  libelle_achat =""; 
	 @Column	private Double  prix_achat ; 
	 @Column	private String  observation =""; 
	 
	 
	 @Column 
	 @Temporal(TemporalType.DATE)
	 @DateTimeFormat(pattern = "dd/MM/YYYY", iso = DateTimeFormat.ISO.DATE, style = "SS")
 	 private java.util.Date  date_achat;
	 
	 @Column	private java.sql.Date  date_cre;
	 @Column	private String  usr_cre =""; 
	 @Column	private java.sql.Date  date_mod;
	 @Column	private String  usr_mod =""; 
	 @Column	private String  soc_id =""; 
	 @Column	private String  etab_id =""; 
	 
	public void setLibelle_achat (String  libelle_achat) {
		this.libelle_achat = libelle_achat;
	}
	public String getLibelle_achat() {
		return libelle_achat;
	}
	 
	public void setObservation (String  observation) {
		this.observation = observation;
	}
	public String getObservation() {
		return observation;
	}
	 
	 	public void setDate_cre (java.sql.Date  date_cre) {
		this.date_cre = date_cre;
	}
	public java.sql.Date getDate_cre() {
		return date_cre;
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
	public void setUsr_mod (String  usr_mod) {
		this.usr_mod = usr_mod;
	}
	public String getUsr_mod() {
		return usr_mod;
	}
	public void setSoc_id (String  soc_id) {
		this.soc_id = soc_id;
	}
	public String getSoc_id() {
		return soc_id;
	}
	public void setEtab_id (String  etab_id) {
		this.etab_id = etab_id;
	}
	public String getEtab_id() {
		return etab_id;
	}
	public Integer getAch_non_id() {
		return ach_non_id;
	}
	public void setAch_non_id(Integer ach_non_id) {
		this.ach_non_id = ach_non_id;
	}
	public Double getPrix_achat() {
		return prix_achat;
	}
	public void setPrix_achat(Double prix_achat) {
		this.prix_achat = prix_achat;
	}
	public java.util.Date getDate_achat() {
		return date_achat;
	}
	public void setDate_achat(java.util.Date date_achat) {
		this.date_achat = date_achat;
	}
	}
