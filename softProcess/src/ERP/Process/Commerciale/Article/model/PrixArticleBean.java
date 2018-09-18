package ERP.Process.Commerciale.Article.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "prix_article", schema = "gestioncommerciale")
public class PrixArticleBean  extends  GenericBean{
    
	
	@EmbeddedId 
	private PKPrix_article  pk_prix_ar= new PKPrix_article();

	@Column     private Double prix_ar;
	
	@Transient  private String indx_row = "";
	@Transient  private String indx_row_next = "";
	
	@Transient  private String to_check = "";
    @Transient  private String id_entite = "";
    
    @Column    private Date date_cre;
	@Column    private Date date_mod;
	@Column    private String usr_cre = "";
	@Column    private String usr_mod = "";
    
    


	public PKPrix_article getPk_prix_ar() {
		return pk_prix_ar;
	}


	public void setPk_prix_ar(PKPrix_article pk_prix_ar) {
		this.pk_prix_ar = pk_prix_ar;
	}


	 


	public String getIndx_row() {
		return indx_row;
	}


	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}


	public String getIndx_row_next() {
		return indx_row_next;
	}


	public void setIndx_row_next(String indx_row_next) {
		this.indx_row_next = indx_row_next;
	}


	public String getTo_check() {
		return to_check;
	}


	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}


	public String getId_entite() {
		return id_entite;
	}


	public void setId_entite(String id_entite) {
		this.id_entite = id_entite;
	}


	public Date getDate_cre() {
		return date_cre;
	}


	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}


	public Date getDate_mod() {
		return date_mod;
	}


	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}


	public String getUsr_cre() {
		return usr_cre;
	}


	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}


	public String getUsr_mod() {
		return usr_mod;
	}


	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}


	public Double getPrix_ar() {
		return prix_ar;
	}


	public void setPrix_ar(Double prix_ar) {
		this.prix_ar = prix_ar;
	}


	 
	 
}
