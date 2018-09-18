package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model;

 

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;

 

@JsonAutoDetect
@Entity
@Table(name = "Module",schema="administration")
public class ModuleBean implements Serializable,Cloneable{
 
	 
	   
	  
	@Id
	@GeneratedValue
	@Column(name = "mod_id", unique = true, nullable = false)
	private BigDecimal mod_id ;
	
	
	@Column(name = "mod_libelle")
	private String mod_libelle = "";
	
	@Column(name = "mod_obs")
	private String mod_obs = "";
	
	
	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	
	@Column(name = "mod_ordre")
	private Integer mod_ordre  ;
	
	
	
	@ManyToOne 
	@JoinColumn(name = "spack_id", insertable = true, updatable = true)
	private SousPackageBean   sousPackBean = new SousPackageBean();
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="mod_id", insertable = false, updatable = false)
	private Set <SousModuleBean>  listSousmodule  =  new TreeSet <SousModuleBean>();
	
	@Transient
	private List <SousModuleBean>listsmodule= new ArrayList<SousModuleBean>(); 
	
	 
	public List getListsmodule() {
		return listsmodule;
	}

	public void setListsmodule(List listsmodule) {
		this.listsmodule = listsmodule;
	}

	 

	public BigDecimal getMod_id() {
		return mod_id;
	}

	public void setMod_id(BigDecimal mod_id) {
		this.mod_id = mod_id;
	}

	 

	public String getMod_libelle() {
		return mod_libelle;
	}

	public void setMod_libelle(String mod_libelle) {
		this.mod_libelle = mod_libelle;
	}

	public String getMod_obs() {
		return mod_obs;
	}

	public void setMod_obs(String mod_obs) {
		this.mod_obs = mod_obs;
	}

	public Set<SousModuleBean> getListSousmodule() {
		return listSousmodule;
	}

	public void setListSousmodule(Set<SousModuleBean> listSousmodule) {
		this.listSousmodule = listSousmodule;
	}

	public SousPackageBean getSousPackBean() {
		return sousPackBean;
	}

	public void setSousPackBean(SousPackageBean sousPackBean) {
		this.sousPackBean = sousPackBean;
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

	public Integer getMod_ordre() {
		return mod_ordre;
	}

	public void setMod_ordre(Integer mod_ordre) {
		this.mod_ordre = mod_ordre;
	}

	 

	 

	 

	 

	

}
