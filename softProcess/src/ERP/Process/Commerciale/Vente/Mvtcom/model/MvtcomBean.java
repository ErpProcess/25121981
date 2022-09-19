package ERP.Process.Commerciale.Vente.Mvtcom.model;
 
 
import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "mvtcom", schema = "gestioncommerciale")
public class  MvtcomBean   extends  GenericBean {
 
 
	private static final long serialVersionUID = 1L;

@Id
 @GeneratedValue
 @Column	private Integer  id;
	
 @Column	private String  full_name  ; 
 
 @Column	private String  compte  ; 
 
 @Column	private String  additional_info  ; 
  
 @Column
 private    BigDecimal file_id;
 
 @Transient
 private Map<String, Object> map ;
  
  
 public BigDecimal getFile_id() {
	return file_id;
}
public void setFile_id(BigDecimal file_id) {
	this.file_id = file_id;
}
@Transient
 private String choixPanel="";
 
 
 
	public String getFull_name() {
	return full_name;
}
public void setFull_name(String full_name) {
	this.full_name = full_name;
}
public String getCompte() {
	return compte;
}
public void setCompte(String compte) {
	this.compte = compte;
}
public String getAdditional_info() {
	return additional_info;
}
public void setAdditional_info(String additional_info) {
	this.additional_info = additional_info;
}
 
public String getChoixPanel() {
	return choixPanel;
}
public void setChoixPanel(String choixPanel) {
	this.choixPanel = choixPanel;
}
	public void setId (Integer  id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
 
	
	}
