package ERP.Process.Commerciale.Achat.Retour_achat.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table( name = "det_retour_achat"  , schema = "achat")
public class Det_retour_achatBean  extends  GenericBean{
 
	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 8793811804094737885L;

	@EmbeddedId
	private PkDetailleRetour pk = new PkDetailleRetour();

	@Transient
	private String indx_row = "";
	
	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

	@Transient
	private String id_entite = "";
	
	
	 
	
	@Column
	private Double quantite_retourne;
	
	
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
	
	 
	/*
	@ManyToOne
	@JoinColumn(name = "cause_ret_vente_id", insertable = true, updatable = true)
	private Nature_incident_mvt_retourBean   cause = new Nature_incident_mvt_retourBean();

	@ManyToOne
	@JoinColumn(name = "sens_operation", insertable = true, updatable = true)
	private Entite_etat_commercialeBean sens = new Entite_etat_commercialeBean();

	*/

	@Column
	private Double montant_tva_retour;

	@Column
	private Double montant_ht_retour;
	
	 

	
	
 
	

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

	 
	 
 
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setPk(PkDetailleRetour pk) {
		this.pk = pk;
	}

	public PkDetailleRetour getPk() {
		return pk;
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

	 
	 

	 

	 
	public Double getMontant_tva_retour() {
		return montant_tva_retour;
	}

	public void setMontant_tva_retour(Double montant_tva_retour) {
		this.montant_tva_retour = montant_tva_retour;
	}

	public Double getMontant_ht_retour() {
		return montant_ht_retour;
	}

	public void setMontant_ht_retour(Double montant_ht_retour) {
		this.montant_ht_retour = montant_ht_retour;
	}

	public Double getQuantite_retourne() {
		return quantite_retourne;
	}

	public void setQuantite_retourne(Double quantite_retourne) {
		this.quantite_retourne = quantite_retourne;
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

 

	 

}
