package ERP.Process.Commerciale.Vente.Facture_client.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "deriver_det_fact_vente", schema = "vente")
public class DeriverDetFactClientVente extends GenericBean {

 
	private static final long serialVersionUID = -6645439272716730594L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer drv_det_fact_id;

	@Transient
	private String indx_row = "";

	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

	@Transient
	private String info = "";

	@Transient
	private String nature_produit = "";

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkcode_barre;

	@Column
	private String drv_oper = "";

	@Column
	private Double drv_coef;

	@Column
	private Double quantite;

	@Column
	private Double tarif_unit_vente;

	@Column
	private Double montant_tva_vente;

	@Column
	private Double montant_ht_vente;

	@Transient
	private Double montant_ttc_vente;

	@Column
	private Date date_cre;

	@Column
	private String usr_cre = "";

	@Column
	private Date date_mod;

	@Column
	private String usr_mod = "";

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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getNature_produit() {
		return nature_produit;
	}

	public void setNature_produit(String nature_produit) {
		this.nature_produit = nature_produit;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
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

	public Double getMontant_ttc_vente() {
		return montant_ttc_vente;
	}

	public void setMontant_ttc_vente(Double montant_ttc_vente) {
		this.montant_ttc_vente = montant_ttc_vente;
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

	public Code_barreBean getFkcode_barre() {
		return fkcode_barre;
	}

	public void setFkcode_barre(Code_barreBean fkcode_barre) {
		this.fkcode_barre = fkcode_barre;
	}

	public String getDrv_oper() {
		return drv_oper;
	}

	public void setDrv_oper(String drv_oper) {
		this.drv_oper = drv_oper;
	}

	public Double getDrv_coef() {
		return drv_coef;
	}

	public void setDrv_coef(Double drv_coef) {
		this.drv_coef = drv_coef;
	}

	public Integer getDrv_det_fact_id() {
		return drv_det_fact_id;
	}

	public void setDrv_det_fact_id(Integer drv_det_fact_id) {
		this.drv_det_fact_id = drv_det_fact_id;
	}

	public Double getTarif_unit_vente() {
		return tarif_unit_vente;
	}

	public void setTarif_unit_vente(Double tarif_unit_vente) {
		this.tarif_unit_vente = tarif_unit_vente;
	}
	
	

}
