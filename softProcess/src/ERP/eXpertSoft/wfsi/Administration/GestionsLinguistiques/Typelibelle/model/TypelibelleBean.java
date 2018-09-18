package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model;

 

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;

 

@JsonAutoDetect
@Entity
@Table(name = "typelibelle",schema="administration")
public class TypelibelleBean implements Serializable {
 
	
	private String type_lib_id="" ;
	private String type_libelle = "";
	 
	@Id
	@Column(name = "type_lib_id", unique = true, nullable = false)
	public String getType_lib_id() {
		return type_lib_id;
	}

	public void setType_lib_id(String type_lib_id) {
		this.type_lib_id = type_lib_id;
	}

	
	@Column(name = "type_libelle")
	public String getType_libelle() {
		return type_libelle;
	}

	public void setType_libelle(String type_libelle) {
		this.type_libelle = type_libelle;
	}

	 

	
	 

}
