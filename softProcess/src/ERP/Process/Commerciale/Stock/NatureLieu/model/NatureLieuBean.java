package ERP.Process.Commerciale.Stock.NatureLieu.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
@JsonAutoDetect
@Entity
@Table(name = "nature_lieu", schema = "stock")
public class  NatureLieuBean   extends  GenericBean {
 
	private static final long serialVersionUID = 3859966059389969460L;

@Id
 @GeneratedValue
 @Column	private Integer  nat_lieu_id;
 
 @Column	private String  nat_lieu_libelle =""; 
 @Column	private Integer  ordre;
 @Column	private String  usr_cre =""; 
 @Column	private java.sql.Date  date_cre;
 @Column	private String  usr_mod =""; 
 @Column	private java.sql.Date  date_mod;
	public void setNat_lieu_id (Integer  nat_lieu_id) {
		this.nat_lieu_id = nat_lieu_id;
	}
	public Integer getNat_lieu_id() {
		return nat_lieu_id;
	}
	public void setNat_lieu_libelle (String  nat_lieu_libelle) {
		this.nat_lieu_libelle = nat_lieu_libelle;
	}
	public String getNat_lieu_libelle() {
		return nat_lieu_libelle;
	}
	public void setOrdre (Integer  ordre) {
		this.ordre = ordre;
	}
	public Integer getOrdre() {
		return ordre;
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
	}
