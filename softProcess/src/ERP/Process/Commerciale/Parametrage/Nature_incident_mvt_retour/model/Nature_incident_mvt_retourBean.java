package ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "nature_incident_mvt_com", schema = "gestioncommerciale")
public class Nature_incident_mvt_retourBean extends GenericBean {

 
	
	private static final long serialVersionUID = 6374200574178089292L;
	
	
	@Id
	@Column
	private Integer nature_incident_id;
	@Column
	private String nature_incident_lib = "";
	@Column
	private String nature_incident_sense = "";
	@Column
	private String nature_incident_type = "";

	@ManyToOne
	@JoinColumn(name = "src_incident_retour_id", insertable = true, updatable = true)
	private Source_incidentBean source = new Source_incidentBean();

	public Source_incidentBean getSource() {
		return source;
	}

	public void setSource(Source_incidentBean source) {
		this.source = source;
	}

	public String getNature_incident_lib() {
		return nature_incident_lib;
	}

	public void setNature_incident_lib(String nature_incident_lib) {
		this.nature_incident_lib = nature_incident_lib;
	}

	public String getNature_incident_sense() {
		return nature_incident_sense;
	}

	public void setNature_incident_sense(String nature_incident_sense) {
		this.nature_incident_sense = nature_incident_sense;
	}

	public String getNature_incident_type() {
		return nature_incident_type;
	}

	public void setNature_incident_type(String nature_incident_type) {
		this.nature_incident_type = nature_incident_type;
	}

	public Integer getNature_incident_id() {
		return nature_incident_id;
	}

	public void setNature_incident_id(Integer nature_incident_id) {
		this.nature_incident_id = nature_incident_id;
	}

}
