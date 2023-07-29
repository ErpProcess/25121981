package ERP.Process.Commerciale.Caracteristique.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;

@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "caracteristique", schema = "gestioncommerciale")
public class CaracteristiqueBean  implements Serializable,Cloneable {
	
	@Id
	@Column    private String  carac_id = "";
	@Column    private String  carac_libelle = "";
	@Transient private String  is_checked = "";
	@Transient private Integer ordre ;
	
	@Transient  private String  indx_row = "";
    @Transient  private String  indx_row_next = "";
	@Transient  private String  to_check = "";
	@Transient  private String  id_entite = "";
	
	@Transient  private String  article_id_caracteristique = "";

	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean etab_bean = new EtablissementBean();

	@Column
	private Date date_cre  ;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";
	
 
	@Transient
	private Set<DetailCaracteristiqueBean> list_detcarac =new LinkedHashSet<DetailCaracteristiqueBean>(); 
	 
	public String getCarac_id() {
		return carac_id;
	}

	public void setCarac_id(String carac_id) {
		this.carac_id = carac_id;
	}

	public String getCarac_libelle() {
		return carac_libelle;
	}

	public void setCarac_libelle(String carac_libelle) {
		this.carac_libelle = carac_libelle;
	}

 

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	 

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
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

	
	 

	public String getIs_checked() {
		return is_checked;
	}

	public void setIs_checked(String is_checked) {
		this.is_checked = is_checked;
	}

	 
	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
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

	public Set<DetailCaracteristiqueBean> getList_detcarac() {
		return list_detcarac;
	}

	public void setList_detcarac(Set<DetailCaracteristiqueBean> list_detcarac) {
		this.list_detcarac = list_detcarac;
	}

	public String getArticle_id_caracteristique() {
		return article_id_caracteristique;
	}

	public void setArticle_id_caracteristique(String article_id_caracteristique) {
		this.article_id_caracteristique = article_id_caracteristique;
	}

	public EtablissementBean getEtab_bean() {
		return etab_bean;
	}

	public void setEtab_bean(EtablissementBean etab_bean) {
		this.etab_bean = etab_bean;
	}

	 

	
	 

	 
 

}
