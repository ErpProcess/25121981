package ERP.Process.Commerciale.Achat.Facture_Fournisseur.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;

@Embeddable
public class PkDetailleFactFournisseur implements Serializable, Cloneable {

 
	private static final long serialVersionUID = 8148043059303337836L;



	@ManyToOne
	@JoinColumn(name = "fact_frs_id", insertable = true, updatable = false)
	private Facture_FournisseurBean facture_frs = new Facture_FournisseurBean();
	
	
	@ManyToOne
	@JoinColumn(name = "mvt_achat_id", insertable = true, updatable = false)
	private Mvt_achat_factureBean mvt_achat = new Mvt_achat_factureBean();

	
	 
	
	
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkCode_barre = new Code_barreBean();

	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}

	public Facture_FournisseurBean getFacture_frs() {
		return facture_frs;
	}

	public void setFacture_frs(Facture_FournisseurBean facture_frs) {
		this.facture_frs = facture_frs;
	}

	 

	public Mvt_achat_factureBean getMvt_achat() {
		return mvt_achat;
	}

	public void setMvt_achat(Mvt_achat_factureBean mvt_achat) {
		this.mvt_achat = mvt_achat;
	}

	 

}
