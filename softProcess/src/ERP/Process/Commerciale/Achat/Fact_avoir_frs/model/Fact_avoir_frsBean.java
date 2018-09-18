package ERP.Process.Commerciale.Achat.Fact_avoir_frs.model;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import javax.persistence.EmbeddedId;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model.TypeAvoirBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
@JsonAutoDetect
@Entity
@Table(name = "fact_avoir_frs", schema = "achat")
public class  Fact_avoir_frsBean   extends  GenericBean {
 
	private static final long serialVersionUID = -8278305240913599775L;
@Id
 @Column	private String  avoir_frs_id =""; 
 @Column	private Date  avoir_frs_date;
 @Column	private String  avoir_frs_obs =""; 
 @Column	private String  avoir_frs_libelle =""; 
 @Column	private java.sql.Date  date_cre;
 @Column	private String  usr_cre =""; 
 @Column	private java.sql.Date  date_mod;
 @Column	private String  usr_mod =""; 
 @Column	private BigDecimal  mode_op;
 @Column	private java.sql.Time  time_cre;
 @Column	private java.sql.Time  time_mod;
 
 
 
 @ManyToOne
	@JoinColumn(name = "fact_frs_id", insertable = true, updatable = false)
	private Facture_FournisseurBean   factfrs = new Facture_FournisseurBean();
	
	
	@ManyToOne
	@JoinColumn(name = "type_avoir_id", insertable = true, updatable = true)
	private TypeAvoirBean  typeAvoir = new TypeAvoirBean();
	
	
	public void setAvoir_frs_id (String  avoir_frs_id) {
		this.avoir_frs_id = avoir_frs_id;
	}
	public String getAvoir_frs_id() {
		return avoir_frs_id;
	}
	 
	public Date getAvoir_frs_date() {
		return avoir_frs_date;
	}
	public void setAvoir_frs_date(Date avoir_frs_date) {
		this.avoir_frs_date = avoir_frs_date;
	}
	public void setAvoir_frs_obs (String  avoir_frs_obs) {
		this.avoir_frs_obs = avoir_frs_obs;
	}
	public String getAvoir_frs_obs() {
		return avoir_frs_obs;
	}
	public void setAvoir_frs_libelle (String  avoir_frs_libelle) {
		this.avoir_frs_libelle = avoir_frs_libelle;
	}
	public String getAvoir_frs_libelle() {
		return avoir_frs_libelle;
	}
	public void setDate_cre (java.sql.Date  date_cre) {
		this.date_cre = date_cre;
	}
	public java.sql.Date getDate_cre() {
		return date_cre;
	}
	public void setUsr_cre (String  usr_cre) {
		this.usr_cre = usr_cre;
	}
	public String getUsr_cre() {
		return usr_cre;
	}
	public void setDate_mod (java.sql.Date  date_mod) {
		this.date_mod = date_mod;
	}
	public java.sql.Date getDate_mod() {
		return date_mod;
	}
	public void setUsr_mod (String  usr_mod) {
		this.usr_mod = usr_mod;
	}
	public String getUsr_mod() {
		return usr_mod;
	}
	public void setMode_op (BigDecimal  mode_op) {
		this.mode_op = mode_op;
	}
	public BigDecimal getMode_op() {
		return mode_op;
	}
	public void setTime_cre (java.sql.Time  time_cre) {
		this.time_cre = time_cre;
	}
	public java.sql.Time getTime_cre() {
		return time_cre;
	}
	public void setTime_mod (java.sql.Time  time_mod) {
		this.time_mod = time_mod;
	}
	public java.sql.Time getTime_mod() {
		return time_mod;
	}
	 
	public Facture_FournisseurBean getFactfrs() {
		return factfrs;
	}
	public void setFactfrs(Facture_FournisseurBean factfrs) {
		this.factfrs = factfrs;
	}
	public TypeAvoirBean getTypeAvoir() {
		return typeAvoir;
	}
	public void setTypeAvoir(TypeAvoirBean typeAvoir) {
		this.typeAvoir = typeAvoir;
	}
	}
