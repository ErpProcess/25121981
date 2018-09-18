package ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "src_incident_mvt_retour", schema = "gestioncommerciale")
public class Source_incidentBean extends GenericBean {

	 
	private static final long serialVersionUID = -7024869949518558319L;

	@Id
	@Column
	private Integer src_incident_ret_vente_id;

	@Column
	private String src_incident_ret_vente_lib = "";

	public String getSrc_incident_ret_vente_lib() {
		return src_incident_ret_vente_lib;
	}

	public void setSrc_incident_ret_vente_lib(String src_incident_ret_vente_lib) {
		this.src_incident_ret_vente_lib = src_incident_ret_vente_lib;
	}

	public Integer getSrc_incident_ret_vente_id() {
		return src_incident_ret_vente_id;
	}

	public void setSrc_incident_ret_vente_id(Integer src_incident_ret_vente_id) {
		this.src_incident_ret_vente_id = src_incident_ret_vente_id;
	}

}
