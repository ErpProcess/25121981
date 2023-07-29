package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model;

 

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.Columns;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

 

@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "package",schema="administration")
public class PackageSysBean   implements Serializable,Cloneable{
	@Id
	@GeneratedValue
	@Column private BigDecimal pack_id ;
	@Column private String pack_libelle = "";
	@Column private String pack_obs = "";
	@Column private Integer pack_ordre ;
	@Column private String pack_icon = "";
	@Transient private String pack_icond = "";
	@Transient private String racourci_soupack = "";
	@Transient private String racourci_soupack_pack_id = "";
	
	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="pack_id",insertable=true,updatable=true)
	private Set <SousPackageBean>  sousPack  =  new TreeSet <SousPackageBean>();
	 
 
	@Transient
	private List list_sous_mod = new  ArrayList();


	public Set<SousPackageBean> getSousPack() {
		return sousPack;
	}


	public void setSousPack(Set<SousPackageBean> sousPack) {
		this.sousPack = sousPack;
	}


	public String getPack_libelle() {
		return pack_libelle;
	}
	

	public void setPack_libelle(String pack_libelle) {
		this.pack_libelle = pack_libelle;
	}

	 
	public String getPack_obs() {
		return pack_obs;
	}

	public void setPack_obs(String pack_obs) {
		this.pack_obs = pack_obs;
	}
	
	
	 

 
	 

	 

	 
	public String getPack_icon() {
		return pack_icon;
	}

	public void setPack_icon(String pack_icon) {
		this.pack_icon = pack_icon;
	}

	 
	public String getPack_icond() {
		return pack_icond;
	}

	public void setPack_icond(String pack_icond) {
		this.pack_icond = pack_icond;
	}
	
	
	 
	public String getRacourci_soupack() {
		return racourci_soupack;
	}

	public void setRacourci_soupack(String racourci_soupack) {
		this.racourci_soupack = racourci_soupack;
	}

	 
	public String getRacourci_soupack_pack_id() {
		return racourci_soupack_pack_id;
	}

	public void setRacourci_soupack_pack_id(String racourci_soupack_pack_id) {
		this.racourci_soupack_pack_id = racourci_soupack_pack_id;
	}


	public BigDecimal getPack_id() {
		return pack_id;
	}


	public void setPack_id(BigDecimal pack_id) {
		this.pack_id = pack_id;
	}


	 


	public Integer getPack_ordre() {
		return pack_ordre;
	}


	public void setPack_ordre(Integer pack_ordre) {
		this.pack_ordre = pack_ordre;
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


	public List getList_sous_mod() {
		return list_sous_mod;
	}


	public void setList_sous_mod(List list_sous_mod) {
		this.list_sous_mod = list_sous_mod;
	}

	 

	

	 
	 

	 

	 

	

}
