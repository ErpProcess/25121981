package ERP.Process.Commerciale.FamilleArticle.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.TypeFamille.model.TypeFamilleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;

@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "famille", schema = "gestioncommerciale")
public class FamilleBean  implements Serializable,Cloneable   {
	@Id
	@Column
	private String fam_id = "";
	@Column
	private String fam_lib = "";
	@Column
	private Integer fam_ordre;
 
	@ManyToOne
	@JoinColumn(name = "fam_type", insertable = true, updatable = true)
	private TypeFamilleBean type = new TypeFamilleBean();
	
	@Transient
	private String conditionDeSelection = "";
	
	@Column
	private String fam_obs = "";
	@Column
	private String tvacod = "";
	@Column
	private String sitcod = "";
	@Column
	private String usr_cre = "";
	@Column
	private String usr_mod = "";
	@Column
	private Date date_cre;
	
	@Column
	private Date date_mod;
	
	@Column
	private BigDecimal mode_op;
	 
	@ManyToOne
    @JoinColumns( {
				@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
				@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	 

	public String getConditionDeSelection() {
		return conditionDeSelection;
	}

	public void setConditionDeSelection(String conditionDeSelection) {
		this.conditionDeSelection = conditionDeSelection;
	}

	public void setFam_id(String fam_id) {
		this.fam_id = fam_id;
	}

	public String getFam_id() {
		return fam_id;
	}

	public void setFam_lib(String fam_lib) {
		this.fam_lib = fam_lib;
	}

	public String getFam_lib() {
		return fam_lib;
	}

	public Integer getFam_ordre() {
		return fam_ordre;
	}

	public void setFam_ordre(Integer fam_ordre) {
		this.fam_ordre = fam_ordre;
	}

 

	public TypeFamilleBean getType() {
		return type;
	}

	public void setType(TypeFamilleBean type) {
		this.type = type;
	}

	public void setFam_obs(String fam_obs) {
		this.fam_obs = fam_obs;
	}

	public String getFam_obs() {
		return fam_obs;
	}

	public void setTvacod(String tvacod) {
		this.tvacod = tvacod;
	}

	public String getTvacod() {
		return tvacod;
	}

	public void setSitcod(String sitcod) {
		this.sitcod = sitcod;
	}

	public String getSitcod() {
		return sitcod;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public BigDecimal getMode_op() {
		return mode_op;
	}

	public void setMode_op(BigDecimal mode_op) {
		this.mode_op = mode_op;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	 

}
