package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.model;

 

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

 

 

@JsonAutoDetect
@Entity
@Table(name = "error",schema="administration")
public class ErrorBean implements Serializable {
 

	@Id
	@Column(name = "error_id", unique = true, nullable = false)
	private String error_id ="";
	
	@Column(name = "error_libelle")
	private String error_libelle = "";
	
	@Column(name = "error_obs")
	private String error_obs = "";
	
	@Column(name = "error_abrv")
	private String error_abrv = "";

	public String getError_id() {
		return error_id;
	}

	public void setError_id(String error_id) {
		this.error_id = error_id;
	}

	public String getError_libelle() {
		return error_libelle;
	}

	public void setError_libelle(String error_libelle) {
		this.error_libelle = error_libelle;
	}

	public String getError_obs() {
		return error_obs;
	}

	public void setError_obs(String error_obs) {
		this.error_obs = error_obs;
	}

	public String getError_abrv() {
		return error_abrv;
	}

	public void setError_abrv(String error_abrv) {
		this.error_abrv = error_abrv;
	}
	
 
	
	
	

	
	 


	
 


	 
	 

	 

}
