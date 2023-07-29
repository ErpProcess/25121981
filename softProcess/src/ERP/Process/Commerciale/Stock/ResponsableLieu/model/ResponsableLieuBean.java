package ERP.Process.Commerciale.Stock.ResponsableLieu.model;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.Inventaire.model.PkInventaire;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
@JsonAutoDetect
@Entity
@Table(name = "affect_depot_usr", schema = "stock")
public class  ResponsableLieuBean   extends  GenericBean {
/**
	 * 
	 */
	private static final long serialVersionUID = -7681962783043687765L;
 
 
 @EmbeddedId
	private PkResponsable pk = new PkResponsable();
 
 
 @Column	private Integer  ordre;
 @Column	private String  usr_cre =""; 
 @Column	private java.sql.Date  date_cre;
 @Column	private String  usr_mod =""; 
 @Column	private java.sql.Date  date_mod;
	 
	public PkResponsable getPk() {
	return pk;
}
public void setPk(PkResponsable pk) {
	this.pk = pk;
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
