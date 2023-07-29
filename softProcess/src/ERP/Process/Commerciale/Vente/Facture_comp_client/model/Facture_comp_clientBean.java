package ERP.Process.Commerciale.Vente.Facture_comp_client.model;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
@JsonAutoDetect
@Entity
@Table(name = "facture_comp_client", schema = "vente")
public class  Facture_comp_clientBean   extends  GenericBean {
 
	private static final long serialVersionUID = -913812622142341750L;
@Id
 @Column	private String  fact_comp_id =""; 
 @Column	private java.sql.Date  fact_comp_date;
 @Column	private String  fact_comp_obs =""; 
 @Column	private String  fact_comp_libelle =""; 
 @Column	private java.sql.Date  date_cre;
 @Column	private String  usr_cre =""; 
 @Column	private java.sql.Date  date_mod;
 @Column	private String  usr_mod =""; 
 @Column	private BigDecimal  mode_op;
 @Column	private java.sql.Time  time_cre;
 @Column	private java.sql.Time  time_mod;
 @Column	private String  fact_clt_id =""; 
	public void setFact_comp_id (String  fact_comp_id) {
		this.fact_comp_id = fact_comp_id;
	}
	public String getFact_comp_id() {
		return fact_comp_id;
	}
	public void setFact_comp_date (java.sql.Date  fact_comp_date) {
		this.fact_comp_date = fact_comp_date;
	}
	public java.sql.Date getFact_comp_date() {
		return fact_comp_date;
	}
	public void setFact_comp_obs (String  fact_comp_obs) {
		this.fact_comp_obs = fact_comp_obs;
	}
	public String getFact_comp_obs() {
		return fact_comp_obs;
	}
	public void setFact_comp_libelle (String  fact_comp_libelle) {
		this.fact_comp_libelle = fact_comp_libelle;
	}
	public String getFact_comp_libelle() {
		return fact_comp_libelle;
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
	public void setMode_op (BigDecimal  mode_op) {
		this.mode_op = mode_op;
	}
	public BigDecimal getMode_op() {
		return mode_op;
	}
	public void setTime_cre (java.sql.Time  time_cre) {
		this.time_cre = time_cre;
	}
	public java.sql.Time getTime_cre() {
		return time_cre;
	}
	public void setTime_mod (java.sql.Time  time_mod) {
		this.time_mod = time_mod;
	}
	public java.sql.Time getTime_mod() {
		return time_mod;
	}
	public void setFact_clt_id (String  fact_clt_id) {
		this.fact_clt_id = fact_clt_id;
	}
	public String getFact_clt_id() {
		return fact_clt_id;
	}
	}
