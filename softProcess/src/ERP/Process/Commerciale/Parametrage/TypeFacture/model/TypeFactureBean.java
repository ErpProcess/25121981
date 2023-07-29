package ERP.Process.Commerciale.Parametrage.TypeFacture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "type_facture", schema = "gestioncommerciale")
public class TypeFactureBean extends GenericBean {
	 
	private static final long serialVersionUID = -4580587270263005665L;
	@Id
	@Column
	private Integer type_fact_id;
	@Column
	private String type_fact_lib = "";

	public Integer getType_fact_id() {
		return type_fact_id;
	}

	public void setType_fact_id(Integer type_fact_id) {
		this.type_fact_id = type_fact_id;
	}

	public void setType_fact_lib(String type_fact_lib) {
		this.type_fact_lib = type_fact_lib;
	}

	public String getType_fact_lib() {
		return type_fact_lib;
	}
}
