package ERP.Process.Commerciale.Achat.ComplementAchat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;

@JsonAutoDetect
@Entity
@Table( name = "det_complement_achat_frs"  , schema = "achat")
public class Det_complment_achatBean  extends  GenericBean{
 
	private static final long serialVersionUID = 8032688938079053713L;

	@EmbeddedId
	private PkDetailleComplement pk = new PkDetailleComplement();

	@Transient
	private String indx_row = "";
	
	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

	@Transient
	private String id_entite = "";
	
	
	@Transient
	private Double quantite_ajouterx  ;
	
	@Column
	private Double quantite;
	
	
	@Column
	private Double quantite_ajouter= new Double(0);
	

	@Transient
	private Double quantite_initiale;
	

	
	
	@Transient
	private Double quantite_calcul ;
	
	
	@Transient
	private Double quantite_calcul_for_modif ;
	
	
	
	@Transient
	private  String total_mnt_ht="";
	
	
	@Transient
	private  String total_mnt_tva="";
	
	
	@Transient
	private  String total_mnt_gen="";
	
	
	@Transient
	private  String total_quantite="";
	
	
	 

	@ManyToOne
	@JoinColumn(name = "nature_mvt_id", insertable = true, updatable = true)
	private NatureMvtCommercialeBean nature_mvt = new NatureMvtCommercialeBean();

	
	@ManyToOne
	@JoinColumn(name = "unite_id", insertable = true, updatable = true)
	private UniteBean unitBean = new UniteBean();

	 

	@ManyToOne
	@JoinColumn(name = "tva_id", insertable = true, updatable = true, referencedColumnName = "tva_id")
	private TVABean tvaBean = new TVABean();
	
	@ManyToOne
	@JoinColumn(name = "type_trf_id" , insertable = true , updatable = true ,referencedColumnName="type_trf_id")
	private Type_tarificationBean typ_trfBean = new Type_tarificationBean();

	

	@Column
	private Double montant_tva_achat;

	@Column
	private Double montant_ht_achat;
	
	@Column
	private Double prix_unit_achat;

	@Column
	private Double prix_unit_vente;

	
	
	@Column
	private Date date_trf;

	

	@Column
	private String observation = "";

	@Column
	private java.sql.Date date_cre;

	@Column
	private String usr_cre = "";

	@Column
	private java.sql.Date date_mod;

	@Column
	private String usr_mod = "";

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

	public Double getPrix_unit_achat() {
		return prix_unit_achat;
	}

	public void setPrix_unit_achat(Double prix_unit_achat) {
		this.prix_unit_achat = prix_unit_achat;
	}

	public Double getPrix_unit_vente() {
		return prix_unit_vente;
	}

	public void setPrix_unit_vente(Double prix_unit_vente) {
		this.prix_unit_vente = prix_unit_vente;
	}

	 
 
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setPk(PkDetailleComplement pk) {
		this.pk = pk;
	}

	public PkDetailleComplement getPk() {
		return pk;
	}

	public Date getDate_trf() {
		return date_trf;
	}

	public void setDate_trf(Date date_trf) {
		this.date_trf = date_trf;
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

	public String getId_entite() {
		return id_entite;
	}

	public void setId_entite(String id_entite) {
		this.id_entite = id_entite;
	}

	public UniteBean getUnitBean() {
		return unitBean;
	}

	public void setUnitBean(UniteBean unitBean) {
		this.unitBean = unitBean;
	}

	public TVABean getTvaBean() {
		return tvaBean;
	}

	public void setTvaBean(TVABean tvaBean) {
		this.tvaBean = tvaBean;
	}

	 

	public NatureMvtCommercialeBean getNature_mvt() {
		return nature_mvt;
	}

	public void setNature_mvt(NatureMvtCommercialeBean nature_mvt) {
		this.nature_mvt = nature_mvt;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getMontant_tva_achat() {
		return montant_tva_achat;
	}

	public void setMontant_tva_achat(Double montant_tva_achat) {
		this.montant_tva_achat = montant_tva_achat;
	}

	public Double getMontant_ht_achat() {
		return montant_ht_achat;
	}

	public void setMontant_ht_achat(Double montant_ht_achat) {
		this.montant_ht_achat = montant_ht_achat;
	}

	 

	 

	 

	public Type_tarificationBean getTyp_trfBean() {
		return typ_trfBean;
	}

	public void setTyp_trfBean(Type_tarificationBean typ_trfBean) {
		this.typ_trfBean = typ_trfBean;
	}

	public Double getQuantite_initiale() {
		return quantite_initiale;
	}

	public void setQuantite_initiale(Double quantite_initiale) {
		this.quantite_initiale = quantite_initiale;
	}

	public Double getQuantite_calcul() {
		return quantite_calcul;
	}

	public void setQuantite_calcul(Double quantite_calcul) {
		this.quantite_calcul = quantite_calcul;
	}

	public Double getQuantite_calcul_for_modif() {
		return quantite_calcul_for_modif;
	}

	public void setQuantite_calcul_for_modif(Double quantite_calcul_for_modif) {
		this.quantite_calcul_for_modif = quantite_calcul_for_modif;
	}

	public String getTotal_mnt_ht() {
		return total_mnt_ht;
	}

	public void setTotal_mnt_ht(String total_mnt_ht) {
		this.total_mnt_ht = total_mnt_ht;
	}

	public String getTotal_mnt_tva() {
		return total_mnt_tva;
	}

	public void setTotal_mnt_tva(String total_mnt_tva) {
		this.total_mnt_tva = total_mnt_tva;
	}

	public String getTotal_mnt_gen() {
		return total_mnt_gen;
	}

	public void setTotal_mnt_gen(String total_mnt_gen) {
		this.total_mnt_gen = total_mnt_gen;
	}

	public String getTotal_quantite() {
		return total_quantite;
	}

	public void setTotal_quantite(String total_quantite) {
		this.total_quantite = total_quantite;
	}

	public Double getQuantite_ajouter() {
		return quantite_ajouter;
	}

	public void setQuantite_ajouter(Double quantite_ajouter) {
		this.quantite_ajouter = quantite_ajouter;
	}

	public Double getQuantite_ajouterx() {
		return quantite_ajouterx;
	}

	public void setQuantite_ajouterx(Double quantite_ajouterx) {
		this.quantite_ajouterx = quantite_ajouterx;
	}

	 

	 

	 

}
