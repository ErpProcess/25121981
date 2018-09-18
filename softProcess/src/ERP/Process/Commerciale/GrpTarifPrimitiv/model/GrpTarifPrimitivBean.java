package ERP.Process.Commerciale.GrpTarifPrimitiv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "groupe_tarification_primitive", schema = "gestioncommerciale")
public class GrpTarifPrimitivBean extends GenericBean {
	 
	 
	private static final long serialVersionUID = -6270837748230357851L;
	
	@Id
	@GeneratedValue
	@Column
	private Integer grp_prim_trf_id;
	@Column
	private String grp_trf_lib = "";
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Date date_mod;
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	
	@Column
	private Integer ordre;

	public void setGrp_trf_lib(String grp_trf_lib) {
		this.grp_trf_lib = grp_trf_lib;
	}

	public String getGrp_trf_lib() {
		return grp_trf_lib;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public Integer getGrp_prim_trf_id() {
		return grp_prim_trf_id;
	}

	public void setGrp_prim_trf_id(Integer grp_prim_trf_id) {
		this.grp_prim_trf_id = grp_prim_trf_id;
	}
}
