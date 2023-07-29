package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model;

 

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model.TypelibelleBean;

 

@Embeddable
public class IdLiblleBean implements Serializable {
	
	public IdLiblleBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Column(name = "lib_id")
	private String lib_id="" ;
	
	
	@Column(name = "lang_id")
	private String lang_id = "";


	public String getLib_id() {
		return lib_id;
	}


	public void setLib_id(String lib_id) {
		this.lib_id = lib_id;
	}


	public String getLang_id() {
		return lang_id;
	}


	public void setLang_id(String lang_id) {
		this.lang_id = lang_id;
	}

	 

	
 



	
	 

}
