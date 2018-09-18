package ERP.Process.Commerciale.Vente.Facture_client.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@Embeddable
public class PkDetMvtVenteArticle  extends GenericBean  {
 
	private static final long serialVersionUID = 1974204903810959471L;

	@ManyToOne
    @JoinColumn(name = "nature_mvt_id", insertable = true, updatable = true)
	private NatureMvtCommercialeBean nat_mvt = new NatureMvtCommercialeBean();
	
	
	@ManyToOne
    @JoinColumn(name = "mvt_vente_id", insertable = true, updatable = true)
	private MvtVente_articleBean mvt_vente = new MvtVente_articleBean();
	
	
	@ManyToOne
	@JoinColumn(name = "vente_id", insertable = true, updatable = true)
	private ProcedureVenteBean vente= new ProcedureVenteBean();
	 
	 
	@Column
	private String document_com_id="";
	
	
	
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), }) 
	private Code_barreBean fkcode_barre = new Code_barreBean();


	 


	 

	public NatureMvtCommercialeBean getNat_mvt() {
		return nat_mvt;
	}


	public void setNat_mvt(NatureMvtCommercialeBean nat_mvt) {
		this.nat_mvt = nat_mvt;
	}


	 

	public String getDocument_com_id() {
		return document_com_id;
	}


	public void setDocument_com_id(String document_com_id) {
		this.document_com_id = document_com_id;
	}


	public Code_barreBean getFkcode_barre() {
		return fkcode_barre;
	}


	public void setFkcode_barre(Code_barreBean fkcode_barre) {
		this.fkcode_barre = fkcode_barre;
	}


	public MvtVente_articleBean getMvt_vente() {
		return mvt_vente;
	}


	public void setMvt_vente(MvtVente_articleBean mvt_vente) {
		this.mvt_vente = mvt_vente;
	}


	public ProcedureVenteBean getVente() {
		return vente;
	}


	public void setVente(ProcedureVenteBean vente) {
		this.vente = vente;
	}


	 

}
