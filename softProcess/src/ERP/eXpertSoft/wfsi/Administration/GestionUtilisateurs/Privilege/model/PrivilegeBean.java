package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;

@JsonAutoDetect
@Entity
@Table(name = "privilege", schema = "administration")
public class  PrivilegeBean  implements Serializable  {
	
	@EmbeddedId 
	private  PKPrivi  pkPriv = new PKPrivi();
	
	public PrivilegeBean() {
		super();
	}
	@Column  private String rangess="";
	
	@ManyToOne
	@JoinColumn(name = "pack_id" , insertable = true, updatable = true )
	private PackageSysBean  packBean= new  PackageSysBean();
	
	
	@ManyToOne
	@JoinColumn(name = "spack_id" , insertable = true, updatable = true )
	private SousPackageBean    spackBean= new  SousPackageBean();
	
	
	@ManyToOne
	@JoinColumn(name = "mod_id" , insertable = true, updatable = true )
	private ModuleBean    modBean= new  ModuleBean();
	
	
	
	
	
	
	
	public PackageSysBean getPackBean() {
		return packBean;
	}
	public void setPackBean(PackageSysBean packBean) {
		this.packBean = packBean;
	}
	public SousPackageBean getSpackBean() {
		return spackBean;
	}
	public void setSpackBean(SousPackageBean spackBean) {
		this.spackBean = spackBean;
	}
	public ModuleBean getModBean() {
		return modBean;
	}
	public void setModBean(ModuleBean modBean) {
		this.modBean = modBean;
	}
	public String getRangess() {
		return rangess;
	}
	public void setRangess(String rangess) {
		this.rangess = rangess;
	}
	public PKPrivi getPkPriv() {
		return pkPriv;
	}
	public void setPkPriv(PKPrivi pkPriv) {
		this.pkPriv = pkPriv;
	}
	 
	 
	 
 
	 
	}
