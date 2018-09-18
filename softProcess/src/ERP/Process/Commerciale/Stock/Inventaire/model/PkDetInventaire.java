package ERP.Process.Commerciale.Stock.Inventaire.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;

@Embeddable
public class PkDetInventaire implements Serializable, Cloneable {

	private static final long serialVersionUID = -7818740010329952469L;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "inv_date", insertable = true, updatable = false, referencedColumnName = "inv_date"),
			@JoinColumn(name = "inv_id", insertable = true, updatable = false, referencedColumnName = "inv_id"),
			@JoinColumn(name = "depot_id", insertable = true, updatable = false, referencedColumnName = "depot_id") })
	private InventaireBean inventaire = new InventaireBean();

	 

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre") })
	private Code_barreBean fkCode_barre = new Code_barreBean();

	@Column
	private Double montant_tva;

	@Column
	private Double montant_ht;

	@Column
	private Double quantite;

	@ManyToOne
	@JoinColumn(name = "nature_mvt_id", insertable = true, updatable = true)
	private NatureMvtCommercialeBean nature_mvt = new NatureMvtCommercialeBean();

	public InventaireBean getInventaire() {
		return inventaire;
	}

	public void setInventaire(InventaireBean inventaire) {
		this.inventaire = inventaire;
	}

	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}

	public Double getMontant_tva() {
		return montant_tva;
	}

	public void setMontant_tva(Double montant_tva) {
		this.montant_tva = montant_tva;
	}

	public Double getMontant_ht() {
		return montant_ht;
	}

	public void setMontant_ht(Double montant_ht) {
		this.montant_ht = montant_ht;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	 

	public NatureMvtCommercialeBean getNature_mvt() {
		return nature_mvt;
	}

	public void setNature_mvt(NatureMvtCommercialeBean nature_mvt) {
		this.nature_mvt = nature_mvt;
	}

}
