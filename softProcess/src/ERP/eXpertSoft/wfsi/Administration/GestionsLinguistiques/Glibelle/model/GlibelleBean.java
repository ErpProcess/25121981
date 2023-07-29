package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model;

 

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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

 

@JsonAutoDetect
@Entity
@Table(name = "libelle_system",schema="administration")
public class GlibelleBean implements Serializable {
 
	
	public GlibelleBean() {
		super();
	
	}

	@EmbeddedId
	private IdLiblleBean idLiblleBean= new IdLiblleBean() ;
	private String lib_libelle = "";
	private String lib_abrv = "";
	private String type_lib_id = "";
	@Column private String sousmod_id = "";
	
	@Transient  private String indx_row = "";
	@Transient  private String indx_row_next = "";
	
	
	@Column
	private String mod_id = "";
	@Column
	private String pack_id = "";
	@Column
	private String spack_id = "";

	
	
	@Transient
	private String langlibelle = "";
	
 

	
	@Column(name = "lib_libelle")
	public String getLib_libelle() {
		return lib_libelle;
	}

	public void setLib_libelle(String lib_libelle) {
		this.lib_libelle = lib_libelle;
	}
	
	public String getSousmod_id() {
		return sousmod_id;
	}

	public void setSousmod_id(String sousmod_id) {
		this.sousmod_id = sousmod_id;
	}


	
	@Column(name = "lib_abrv")
	public String getLib_abrv() {
		return lib_abrv;
	}

	public void setLib_abrv(String lib_abrv) {
		this.lib_abrv = lib_abrv;
	}

	 
		 

    @Column(name = "type_lib_id" ,insertable=true, updatable=true )
	public String getType_lib_id() {
		return type_lib_id;
	}

	public void setType_lib_id(String type_lib_id) {
		this.type_lib_id = type_lib_id;
	}

	
 
	 

	
	public String getLanglibelle() {
		return langlibelle;
	}

	public void setLanglibelle(String langlibelle) {
		this.langlibelle = langlibelle;
	}

	public IdLiblleBean getIdLiblleBean() {
		return idLiblleBean;
	}

	public void setIdLiblleBean(IdLiblleBean idLiblleBean) {
		this.idLiblleBean = idLiblleBean;
	}

	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public String getPack_id() {
		return pack_id;
	}

	public void setPack_id(String pack_id) {
		this.pack_id = pack_id;
	}

	public String getSpack_id() {
		return spack_id;
	}

	public void setSpack_id(String spack_id) {
		this.spack_id = spack_id;
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



	
	 

}
