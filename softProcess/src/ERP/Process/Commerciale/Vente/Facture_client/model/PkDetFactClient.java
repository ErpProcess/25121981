package ERP.Process.Commerciale.Vente.Facture_client.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@Embeddable
public class PkDetFactClient  extends GenericBean  {

	 
	private static final long serialVersionUID = -6114506181778113230L;

	@ManyToOne
	@JoinColumn(name = "fact_clt_id", insertable = true, updatable = false)
	private Facture_clientBean   factclient = new Facture_clientBean();

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), }) 
	private Code_barreBean fkcode_barre = new Code_barreBean();
	
	
 
	 
	 
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "mvt_vente_id", insertable = true, updatable = true, referencedColumnName = "mvt_vente_id" )
	private MvtVente_articleBean mvtVente = new MvtVente_articleBean();
	 
	
	 

	public MvtVente_articleBean getMvtVente() {
		return mvtVente;
	}

	public void setMvtVente(MvtVente_articleBean mvtVente) {
		this.mvtVente = mvtVente;
	}

	public Facture_clientBean getFactclient() {
		return factclient;
	}

	public void setFactclient(Facture_clientBean factclient) {
		this.factclient = factclient;
	}

	 

	public Code_barreBean getFkcode_barre() {
		return fkcode_barre;
	}

	public void setFkcode_barre(Code_barreBean fkcode_barre) {
		this.fkcode_barre = fkcode_barre;
	}

	 

	 

}
