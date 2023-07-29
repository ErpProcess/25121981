package ERP.Process.Commerciale.DetailCaracteristique.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "det_caracteristique", schema = "gestioncommerciale")
public class  DetailCaracteristiqueBean  implements Serializable,Cloneable {
	
	
	 @EmbeddedId 
	 private PKDet_caracteristique  pk_det_carac = new PKDet_caracteristique();
	 
	
     public PKDet_caracteristique getPk_det_carac() {
			return pk_det_carac;
     }
	 
     public void setPk_det_carac(PKDet_caracteristique pk_det_carac) {
			this.pk_det_carac = pk_det_carac;
	 }
	 
	 @Transient private String  is_checked = "";
	 @Transient private String  ordre = "";
	 @Column	private String  det_carac_libelle =""; 
	 @Column	private Date    date_cre;
	 @Column	private String  usr_cre =""; 
	 @Column	private Date    date_mod;
	 @Column	private String  usr_mod =""; 
	 @Transient private String  map_data_list = "";
	 
	 
	  
	 
	 
	public String getMap_data_list() {
		return map_data_list;
	}
	public void setMap_data_list(String map_data_list) {
		this.map_data_list = map_data_list;
	}
	public void setDet_carac_libelle (String  det_carac_libelle) {
		this.det_carac_libelle = det_carac_libelle;
	}
	public String getDet_carac_libelle() {
		return det_carac_libelle;
	}
 
 
	public void setUsr_cre (String  usr_cre) {
		this.usr_cre = usr_cre;
	}
	public String getUsr_cre() {
		return usr_cre;
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
	public void setUsr_mod (String  usr_mod) {
		this.usr_mod = usr_mod;
	}
	public String getUsr_mod() {
		return usr_mod;
	}
	
	
	public String getIs_checked() {
		return is_checked;
	}
	public void setIs_checked(String is_checked) {
		this.is_checked = is_checked;
	}
	public String getOrdre() {
		return ordre;
	}
	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}
	 
	}
