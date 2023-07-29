package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;

@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "souspackage", schema = "administration")
public class SousPackageBean implements Serializable,Cloneable{

	@Id 
	@Column 
	@GeneratedValue
	private BigDecimal spack_id;
	@Column private BigDecimal spack_ordre;
	@Column private String spack_libelle = "";
	@Column private String spack_obs = "";
	@Column private String spack_action = "";
	
	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	
	@ManyToOne 
	@JoinColumn(name = "pack_id")
	private PackageSysBean packageSys = new PackageSysBean();

	
	public PackageSysBean getPackageSys() {
		return packageSys;
	}

	public void setPackageSys(PackageSysBean packageSys) {
		this.packageSys = packageSys;
	}

	
	 

	 
	public String getSpack_libelle() {
		return spack_libelle;
	}

	public void setSpack_libelle(String spack_libelle) {
		this.spack_libelle = spack_libelle;
	}

	 
	public String getSpack_obs() {
		return spack_obs;
	}

	public void setSpack_obs(String spack_obs) {
		this.spack_obs = spack_obs;
	}

 
	 
	 
	public String getSpack_action() {
		return spack_action;
	}

	public void setSpack_action(String spack_action) {
		this.spack_action = spack_action;
	}

	public BigDecimal getSpack_id() {
		return spack_id;
	}

	public void setSpack_id(BigDecimal spack_id) {
		this.spack_id = spack_id;
	}

	 

	public BigDecimal getSpack_ordre() {
		return spack_ordre;
	}

	public void setSpack_ordre(BigDecimal spack_ordre) {
		this.spack_ordre = spack_ordre;
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

	 
 
 

	 

}
