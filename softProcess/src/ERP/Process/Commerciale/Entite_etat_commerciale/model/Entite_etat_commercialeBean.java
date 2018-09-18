package ERP.Process.Commerciale.Entite_etat_commerciale.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;

@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "entite_etat_commerciale", schema = "gestioncommerciale")
public class Entite_etat_commercialeBean implements Serializable,Cloneable {

 
	@Id
	@Column
	private String data_id  ;
	
	@Column
	private String code_entite = "";
	

	@Column
	private String libelle_entite = "";
	@Column
	private String data_libelle = "";
	@Column
	private Integer data_ordre;

	@Column
	private String obs_entite = "";
	@Column
	private String obs_det_entite = "";

	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";
	@Transient
	private String to_check = "";
	@Transient
	private String id_entite = "";

	/*@ManyToOne
	@JoinColumn(name = "soc_id", insertable = true, updatable = true)
	private SocieteBean socBean = new SocieteBean();*/

	public String getObs_entite() {
		return obs_entite;
	}

	public void setObs_entite(String obs_entite) {
		this.obs_entite = obs_entite;
	}

	public String getObs_det_entite() {
		return obs_det_entite;
	}

	public void setObs_det_entite(String obs_det_entite) {
		this.obs_det_entite = obs_det_entite;
	}

	public void setLibelle_entite(String libelle_entite) {
		this.libelle_entite = libelle_entite;
	}

	public String getLibelle_entite() {
		return libelle_entite;
	}

	public void setData_libelle(String data_libelle) {
		this.data_libelle = data_libelle;
	}

	public String getData_libelle() {
		return data_libelle;
	}

	public Integer getData_ordre() {
		return data_ordre;
	}

	public void setData_ordre(Integer data_ordre) {
		this.data_ordre = data_ordre;
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

	/*public SocieteBean getSocBean() {
		return socBean;
	}

	public void setSocBean(SocieteBean socBean) {
		this.socBean = socBean;
	}*/

}
