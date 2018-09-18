package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;


@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "data_entite_simple", schema = "administration")
public class Data_entite_simpleBean  implements Serializable,Cloneable  {

	

	
	@Id
	@Column 
	private String data_id  ;
	
	@Column(name = "code_entite")
	private String code_entite  ;
	
	
	@Column(name = "data_ordre")
	private Integer data_ordre ;
	
	
	
	@Column(name = "data_libelle")
	private String data_libelle = "";
	
	@Column(name = "libelle_entite")
	private String libelle_entite = "";
 
	 

	 

	public String getData_libelle() {
		return data_libelle;
	}

	public void setData_libelle(String data_libelle) {
		this.data_libelle = data_libelle;
	}

	public String getLibelle_entite() {
		return libelle_entite;
	}

	public void setLibelle_entite(String libelle_entite) {
		this.libelle_entite = libelle_entite;
	}

	 

	public String getCode_entite() {
		return code_entite;
	}

	public void setCode_entite(String code_entite) {
		this.code_entite = code_entite;
	}

	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
	}

	public Integer getData_ordre() {
		return data_ordre;
	}

	public void setData_ordre(Integer data_ordre) {
		this.data_ordre = data_ordre;
	}
	
 
	
	

	 

}
