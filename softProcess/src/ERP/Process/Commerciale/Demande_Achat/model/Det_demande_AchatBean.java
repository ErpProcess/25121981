package ERP.Process.Commerciale.Demande_Achat.model;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;
@JsonAutoDetect
@Entity
@Table(name = "det_demande_achat", schema = "achat")
public class  Det_demande_AchatBean implements Serializable,Cloneable {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8480928056326517051L;

	@EmbeddedId 
	 private PKDet_dem_achat  pk_det_dem_achat = new PKDet_dem_achat();
	 
	 @Transient  private String  indx_row = "";
     @Transient  private String  indx_row_next = "";
	 @Transient  private String  to_check = "";
	 @Transient  private String  id_entite = "";
	 @Column	 private Integer num_ligne ; 
	 @Column	 private Double  quantite ;  
	 @Column	 private String  observation =""; 
	 @Column	 private String  sitcod =""; 
	 @Column	 private String  usr_cre = "";
     @Column	 private String  usr_mod = "";
	 @Column	 private Date    date_cre  ;
	 @Column	 private Date    date_mod  ;
	  
	 @ManyToOne 
		@JoinColumn(name = "unite_id", insertable = true, updatable = true)
		private UniteBean unitBean = new UniteBean();
	 
 
	public UniteBean getUnitBean() {
		return unitBean;
	}
	public void setUnitBean(UniteBean unitBean) {
		this.unitBean = unitBean;
	}
	public Integer getNum_ligne() {
		return num_ligne;
	}
	public void setNum_ligne(Integer num_ligne) {
		this.num_ligne = num_ligne;
	}
	 
	 

	public void setObservation (String  observation) {
		this.observation = observation;
	}
	public String getObservation() {
		return observation;
	}
	public void setSitcod (String  sitcod) {
		this.sitcod = sitcod;
	}
	public String getSitcod() {
		return sitcod;
	}
	public PKDet_dem_achat getPk_det_dem_achat() {
		return pk_det_dem_achat;
	}
	public void setPk_det_dem_achat(PKDet_dem_achat pk_det_dem_achat) {
		this.pk_det_dem_achat = pk_det_dem_achat;
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
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
 
	}
