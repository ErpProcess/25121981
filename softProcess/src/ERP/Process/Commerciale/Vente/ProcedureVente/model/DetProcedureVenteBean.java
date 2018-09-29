package ERP.Process.Commerciale.Vente.ProcedureVente.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "det_op_vente", schema = "vente")
public class DetProcedureVenteBean extends GenericBean {

	private static final long serialVersionUID = 3940320112629297780L;

	@EmbeddedId
	private PkDetProcedureVente pk = new PkDetProcedureVente();
	
	@Transient
	private String				indx_row			= "";
	
	
	@Transient
	private String				indx_row_next		= "";

	
	@Transient
	private String				to_check			= "";
	
	@Transient
	private String				info			    = "";
	
	@Transient
	private String				nature_produit		= "";
	
	
	
	
	@Column
	private Double quantite;
	
	@Column
	private Double quantite_confirmer;
	
	
	@ManyToOne
	@JoinColumn(name = "tarif_vente_id", insertable = true, updatable = true)
	private TarificationBean  tarif = new TarificationBean();
	
	
	@ManyToOne
	@JoinColumn(name = "drv_vente_id", insertable = true, updatable = true,nullable=true)
	private DeriverOperationVente  drv  ;
	
	
	
	@Column
	private Double montant_tva_vente;
	
	
	@Column (name = "montant_remise_ligne")
	private Double montant_Remise_Ligne;
	
	@Column
	private Double taux_remise_ligne;
	
	@Column
	private Double montant_benefice;
	
	 
	@Column
	private Double montant_ht_vente;
	
	
	@Transient
	private Double montant_ht_vente_reel;
	
	
	@Transient
	private Double montant_ttc_vente;
	
	
	
	
	
	
	
	
	 

	@Transient
	private Double quantite_en_stock;
	
	
	@Transient
	private String	 methode_s	 = "Généric";
	
	
	@Column
	private Double quantite_demander;
	
	@Column
	private String observation = "";

	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";

	 
	

	
	
 
	public PkDetProcedureVente getPk() {
		return pk;
	}

	public void setPk(PkDetProcedureVente pk) {
		this.pk = pk;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	 

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	 

	public Double getMontant_tva_vente() {
		return montant_tva_vente;
	}

	public void setMontant_tva_vente(Double montant_tva_vente) {
		this.montant_tva_vente = montant_tva_vente;
	}

	public Double getMontant_ht_vente() {
		return montant_ht_vente;
	}

	public void setMontant_ht_vente(Double montant_ht_vente) {
		this.montant_ht_vente = montant_ht_vente;
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

	public Double getQuantite_demander() {
		return quantite_demander;
	}

	public void setQuantite_demander(Double quantite_demander) {
		this.quantite_demander = quantite_demander;
	}

	public Double getQuantite_en_stock() {
		return quantite_en_stock;
	}

	public void setQuantite_en_stock(Double quantite_en_stock) {
		this.quantite_en_stock = quantite_en_stock;
	}

	public String getMethode_s() {
		return methode_s;
	}

	public void setMethode_s(String methode_s) {
		this.methode_s = methode_s;
	}

	

	public TarificationBean getTarif() {
		return tarif;
	}

	public void setTarif(TarificationBean tarif) {
		this.tarif = tarif;
	}

	public Double getQuantite_confirmer() {
		return quantite_confirmer;
	}

	public void setQuantite_confirmer(Double quantite_confirmer) {
		this.quantite_confirmer = quantite_confirmer;
	}

	public Double getMontant_Remise_Ligne() {
		return montant_Remise_Ligne;
	}

	public void setMontant_Remise_Ligne(Double montant_Remise_Ligne) {
		this.montant_Remise_Ligne = montant_Remise_Ligne;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Double getMontant_ht_vente_reel() {
		return montant_ht_vente_reel;
	}

	public void setMontant_ht_vente_reel(Double montant_ht_vente_reel) {
		this.montant_ht_vente_reel = montant_ht_vente_reel;
	}

	public Double getMontant_ttc_vente() {
		return montant_ttc_vente;
	}

	public void setMontant_ttc_vente(Double montant_ttc_vente) {
		this.montant_ttc_vente = montant_ttc_vente;
	}

	public Double getTaux_remise_ligne() {
		return taux_remise_ligne;
	}

	public void setTaux_remise_ligne(Double taux_remise_ligne) {
		this.taux_remise_ligne = taux_remise_ligne;
	}

	public Double getMontant_benefice() {
		return montant_benefice;
	}

	public void setMontant_benefice(Double montant_benefice) {
		this.montant_benefice = montant_benefice;
	}

	public String getNature_produit() {
		return nature_produit;
	}

	public void setNature_produit(String nature_produit) {
		this.nature_produit = nature_produit;
	}

	public DeriverOperationVente getDrv() {
		return drv;
	}

	public void setDrv(DeriverOperationVente drv) {
		this.drv = drv;
	}

	 

	 

	 

	 
}
