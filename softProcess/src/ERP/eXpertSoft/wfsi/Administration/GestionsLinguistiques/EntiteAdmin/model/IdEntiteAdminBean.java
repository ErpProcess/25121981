package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class IdEntiteAdminBean implements     Serializable, Cloneable{

	public IdEntiteAdminBean() {
		super();
	}

	@Column
	private String ent_id = "";

	@Column
	private String lang_id = "";

	public String getEnt_id() {
		return ent_id;
	}

	public void setEnt_id(String ent_id) {
		this.ent_id = ent_id;
	}

	public String getLang_id() {
		return lang_id;
	}

	public void setLang_id(String lang_id) {
		this.lang_id = lang_id;
	}
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
