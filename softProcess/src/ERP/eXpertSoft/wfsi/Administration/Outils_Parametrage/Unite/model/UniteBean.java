package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "unite", schema = "administration")
public class UniteBean {
	@Id
	@GeneratedValue
	@Column
	private Integer unite_id ;
	@Column
	private String unite_lib = "";
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String soc_id = "";
	
	@Column
	private String unite_abrv = "";
	
	@Column
	private Integer unite_valeur ;
	
	@Column
	private Double unite_coef ;
	
	@Transient
	private String opreration = "";
	
	@Transient
	private String code_barre = "";
	
	@ManyToOne
	@JoinColumn(name = "drv_id", insertable = true, updatable = false)
	private DeriverUnite drv = new DeriverUnite();

	public void setUnite_lib(String unite_lib) {
		this.unite_lib = unite_lib;
	}

	public String getUnite_lib() {
		return unite_lib;
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

	public Integer getUnite_id() {
		return unite_id;
	}

	public void setUnite_id(Integer unite_id) {
		this.unite_id = unite_id;
	}

	public void setSoc_id(String soc_id) {
		this.soc_id = soc_id;
	}

	public String getSoc_id() {
		return soc_id;
	}

	public String getUnite_abrv() {
		return unite_abrv;
	}

	public void setUnite_abrv(String unite_abrv) {
		this.unite_abrv = unite_abrv;
	}

	public Integer getUnite_valeur() {
		return unite_valeur;
	}

	public void setUnite_valeur(Integer unite_valeur) {
		this.unite_valeur = unite_valeur;
	}

	public Double getUnite_coef() {
		return unite_coef;
	}

	public void setUnite_coef(Double unite_coef) {
		this.unite_coef = unite_coef;
	}

	public String getOpreration() {
		return opreration;
	}

	public void setOpreration(String opreration) {
		this.opreration = opreration;
	}

	public String getCode_barre() {
		return code_barre;
	}

	public void setCode_barre(String code_barre) {
		this.code_barre = code_barre;
	}

	public DeriverUnite getDrv() {
		return drv;
	}

	public void setDrv(DeriverUnite drv) {
		this.drv = drv;
	}

	 
}
