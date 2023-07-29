package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model;

 

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

 

@JsonAutoDetect
@Entity
@Table(name = "fonction",schema="administration")
public class FonctionBean implements Serializable,Cloneable{
 
 
	private static final long serialVersionUID = 3278030291381884339L;

	@Id
	@Column
	private BigDecimal fct_id ;
	
	@Column(name = "fct_libelle")
	private String fct_libelle = "";
	
	@Column(name = "fct_obs")
	private String fct_obs = "";
	
	
	@Column(name = "fct_action")
	private String fct_action = "";
	
	@Column(name = "fct_icon")
	private String fct_icon = "";
	
	@Transient private Integer ordre;
	
	@Transient private String  view_smfct_action = "";
	
	@Transient private String  data_action = "";
 

	 


	public String getData_action() {
		return data_action;
	}


	public void setData_action(String data_action) {
		this.data_action = data_action;
	}


	public Integer getOrdre() {
		return ordre;
	}


	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}


	public BigDecimal getFct_id() {
		return fct_id;
	}


	public void setFct_id(BigDecimal fct_id) {
		this.fct_id = fct_id;
	}


	public String getFct_libelle() {
		return fct_libelle;
	}


	public void setFct_libelle(String fct_libelle) {
		this.fct_libelle = fct_libelle;
	}


	public String getFct_obs() {
		return fct_obs;
	}


	public void setFct_obs(String fct_obs) {
		this.fct_obs = fct_obs;
	}


	public String getFct_action() {
		return fct_action;
	}


	public void setFct_action(String fct_action) {
		this.fct_action = fct_action;
	}


	public String getFct_icon() {
		return fct_icon;
	}


	public void setFct_icon(String fct_icon) {
		this.fct_icon = fct_icon;
	}

	
 
 

	 


	public String getView_smfct_action() {
		return view_smfct_action;
	}


	public void setView_smfct_action(String view_smfct_action) {
		this.view_smfct_action = view_smfct_action;
	}

	
	 


	 
	

	 

	 

	 

	 

	

}
