package ERP.Process.Commerciale.Fournisseur.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;

@JsonAutoDetect
@Entity
@Table(name = "fournisseur", schema = "gestioncommerciale")
public class FournisseurBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -6734895062504066152L;
	
	@Id
	@Column
	private String frs_id = "";
	@Column
	private String frsref = "";
	@Column
	private String frsadr = "";
	@Column
	private String frstel = "";
	@Column
	private String frsfax = "";
	@Column
	private String frsemail = "";
	@Column
	private String frsnumcpt = "";
	@Column
	private BigDecimal frsech;
	@Column
	private String frsobs = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";
	@Column
	private String bancod = "";
	@Column
	private String frstyp = "";

	@ManyToOne
	@JoinColumn(name = "grp_prim_trf_id", insertable = true, updatable = true, referencedColumnName = "grp_prim_trf_id")
	private GrpTarifPrimitivBean groupe = new GrpTarifPrimitivBean();

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	 

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public void setFrs_id(String frs_id) {
		this.frs_id = frs_id;
	}

	public String getFrs_id() {
		return frs_id;
	}

	public void setFrsref(String frsref) {
		this.frsref = frsref;
	}

	public String getFrsref() {
		return frsref;
	}

	public String getFrsadr() {
		return frsadr;
	}

	public void setFrsadr(String frsadr) {
		this.frsadr = frsadr;
	}

	public void setFrstel(String frstel) {
		this.frstel = frstel;
	}

	public String getFrstel() {
		return frstel;
	}

	public void setFrsfax(String frsfax) {
		this.frsfax = frsfax;
	}

	public String getFrsfax() {
		return frsfax;
	}

	public void setFrsemail(String frsemail) {
		this.frsemail = frsemail;
	}

	public String getFrsemail() {
		return frsemail;
	}

	public void setFrsnumcpt(String frsnumcpt) {
		this.frsnumcpt = frsnumcpt;
	}

	public String getFrsnumcpt() {
		return frsnumcpt;
	}

	public void setFrsech(BigDecimal frsech) {
		this.frsech = frsech;
	}

	public BigDecimal getFrsech() {
		return frsech;
	}

	public void setFrsobs(String frsobs) {
		this.frsobs = frsobs;
	}

	public String getFrsobs() {
		return frsobs;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setBancod(String bancod) {
		this.bancod = bancod;
	}

	public String getBancod() {
		return bancod;
	}

	public void setFrstyp(String frstyp) {
		this.frstyp = frstyp;
	}

	public String getFrstyp() {
		return frstyp;
	}

	public GrpTarifPrimitivBean getGroupe() {
		return groupe;
	}

	public void setGroupe(GrpTarifPrimitivBean groupe) {
		this.groupe = groupe;
	}

}
