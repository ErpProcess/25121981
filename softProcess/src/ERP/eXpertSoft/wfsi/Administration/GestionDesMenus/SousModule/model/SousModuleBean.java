package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;

@JsonAutoDetect
@Entity
@Table(name = "sousmodule", schema = "administration")
public class SousModuleBean implements Serializable,Cloneable{

	 
 
	private static final long serialVersionUID = 1L;




	@Id
	@GeneratedValue
	@Column(name = "sousmod_id", unique = true, nullable = false)
	private BigDecimal sousmod_id;
	
	
	   

	@Column(name = "sousmod_libelle")
	private String sousmod_libelle = "";

	@Column(name = "sousmod_obs")
	private String sousmod_obs = "";

	@Column(name = "sousmod_action")
	private String sousmod_action = "";

	@Column(name = "sousmod_icon")
	private String sousmod_icon = "";

	@Column(name = "jsp_folder")
	private String jsp_folder  ;

	@Column(name = "java_pack")
	private String java_pack ;

	@Column
	private String sousmod_table = "";

	@Column
	private String soumod_schema = "";
	
	
	@Column
	private Integer sousmod_ordre ;
	
	
	 

	@ManyToOne 
	@JoinColumn(name = "mod_id" , insertable = true, updatable = true )
	private ModuleBean moduleBean = new ModuleBean();
	 
	
	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "smfction", schema = "administration", 
			joinColumns = { @JoinColumn(name = "sousmod_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "fct_id") })
	private Set<FonctionBean> listFction = new TreeSet<FonctionBean>();
	
	
 

	
	@Transient
	private List listf = new ArrayList();

 

	public List getListf() {
		return listf;
	}

	public void setListf(List listf) {
		this.listf = listf;
	}

	public Set<FonctionBean> getListFction() {
		return listFction;
	}

	public void setListFction(Set<FonctionBean> listFction) {
		this.listFction = listFction;
	}

	public SousModuleBean() {

	}

	 

	public BigDecimal getSousmod_id() {
		return sousmod_id;
	}

	public void setSousmod_id(BigDecimal sousmod_id) {
		this.sousmod_id = sousmod_id;
	}

	public String getSousmod_libelle() {
		return sousmod_libelle;
	}

	public void setSousmod_libelle(String sousmod_libelle) {
		this.sousmod_libelle = sousmod_libelle;
	}

	public String getSousmod_obs() {
		return sousmod_obs;
	}

	public void setSousmod_obs(String sousmod_obs) {
		this.sousmod_obs = sousmod_obs;
	}

	public String getSousmod_action() {
		return sousmod_action;
	}

	public void setSousmod_action(String sousmod_action) {
		this.sousmod_action = sousmod_action;
	}

	public String getSousmod_icon() {
		return sousmod_icon;
	}

	public void setSousmod_icon(String sousmod_icon) {
		this.sousmod_icon = sousmod_icon;
	}

	public ModuleBean getModuleBean() {
		return moduleBean;
	}

	public void setModuleBean(ModuleBean moduleBean) {
		this.moduleBean = moduleBean;
	}

	public String getSousmod_table() {
		return sousmod_table;
	}

	public void setSousmod_table(String sousmod_table) {
		this.sousmod_table = sousmod_table;
	}

	public String getSoumod_schema() {
		return soumod_schema;
	}

	public void setSoumod_schema(String soumod_schema) {
		this.soumod_schema = soumod_schema;
	}

	public String getJsp_folder() {
		return jsp_folder;
	}

	public void setJsp_folder(String jsp_folder) {
		this.jsp_folder = jsp_folder;
	}

	public String getJava_pack() {
		return java_pack;
	}

	public void setJava_pack(String java_pack) {
		this.java_pack = java_pack;
	}

	public Integer getSousmod_ordre() {
		return sousmod_ordre;
	}

	public void setSousmod_ordre(Integer sousmod_ordre) {
		this.sousmod_ordre = sousmod_ordre;
	}

	 
	 
	 
	
	 

}
