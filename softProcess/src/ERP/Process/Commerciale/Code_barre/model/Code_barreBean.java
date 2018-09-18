package ERP.Process.Commerciale.Code_barre.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;



@JsonAutoDetect
@Entity
@Table(name = "code_barre", schema = "gestioncommerciale")
public class Code_barreBean implements Serializable,Cloneable {
 
	private static final long serialVersionUID = -8287840855185157553L;

	@EmbeddedId
	private PkCodeBarre pk = new PkCodeBarre();
	
	@Transient  private String  indx_row = "";
    @Transient  private String  indx_row_next = "";
	@Transient  private String  to_check = "";
	@Transient  private String  id_entite = "";
	@Transient private List <Det_code_barre> list_detail_cod_bar = new ArrayList<Det_code_barre>();
 
	@Column
	private String designation = "";
	
	@Column
	private String designation_libelle = "";
	
	
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";
	
	 
	
	@Transient  
	private String condition_article_seulement_vente="";

	 

	 

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
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

	public List<Det_code_barre> getList_detail_cod_bar() {
		return list_detail_cod_bar;
	}

	public void setList_detail_cod_bar(List<Det_code_barre> list_detail_cod_bar) {
		this.list_detail_cod_bar = list_detail_cod_bar;
	}

	public PkCodeBarre getPk() {
		return pk;
	}

	public void setPk(PkCodeBarre pk) {
		this.pk = pk;
	}

	public String getDesignation_libelle() {
		return designation_libelle;
	}

	public void setDesignation_libelle(String designation_libelle) {
		this.designation_libelle = designation_libelle;
	}

	public String getCondition_article_seulement_vente() {
		return condition_article_seulement_vente;
	}

	public void setCondition_article_seulement_vente(
			String condition_article_seulement_vente) {
		this.condition_article_seulement_vente = condition_article_seulement_vente;
	}

	 

}
