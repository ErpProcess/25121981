package ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
@JsonAutoDetect
@Entity
@Table(name = "nature_mvt_commerciale", schema = "gestioncommerciale")
public class  NatureMvtCommercialeBean  extends GenericBean {
/**
	 * 
	 */
	private static final long serialVersionUID = 2137270611400194523L;
@Id
 @Column	private String  nature_mvt_id =""; 
 @Column	private String  nature_mvt_libelle =""; 
 @Column	private Integer  nature_mvt_ordre;
 @Column	private String  etab_id =""; 
 @Column	private String  soc_id =""; 
 @Column	private String  usr_cre =""; 
 @Column	private java.sql.Date  date_cre;
 @Column	private String  usr_mod =""; 
 @Column	private java.sql.Date  date_mod;
 @Column	private String  nature_operation =""; 
 
	public void setNature_mvt_id (String  nature_mvt_id) {
		this.nature_mvt_id = nature_mvt_id;
	}
	public String getNature_mvt_id() {
		return nature_mvt_id;
	}
	public void setNature_mvt_libelle (String  nature_mvt_libelle) {
		this.nature_mvt_libelle = nature_mvt_libelle;
	}
	public String getNature_mvt_libelle() {
		return nature_mvt_libelle;
	}
	public void setNature_mvt_ordre (Integer  nature_mvt_ordre) {
		this.nature_mvt_ordre = nature_mvt_ordre;
	}
	public Integer getNature_mvt_ordre() {
		return nature_mvt_ordre;
	}
	public void setEtab_id (String  etab_id) {
		this.etab_id = etab_id;
	}
	public String getEtab_id() {
		return etab_id;
	}
	public void setSoc_id (String  soc_id) {
		this.soc_id = soc_id;
	}
	public String getSoc_id() {
		return soc_id;
	}
	public void setUsr_cre (String  usr_cre) {
		this.usr_cre = usr_cre;
	}
	public String getUsr_cre() {
		return usr_cre;
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
	public void setDate_mod (java.sql.Date  date_mod) {
		this.date_mod = date_mod;
	}
	public java.sql.Date getDate_mod() {
		return date_mod;
	}
	public void setNature_operation (String  nature_operation) {
		this.nature_operation = nature_operation;
	}
	public String getNature_operation() {
		return nature_operation;
	}
	}
