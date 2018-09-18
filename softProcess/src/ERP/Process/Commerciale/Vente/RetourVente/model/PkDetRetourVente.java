package ERP.Process.Commerciale.Vente.RetourVente.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Embeddable
public class PkDetRetourVente extends GenericBean {

 
	private static final long serialVersionUID = -192855438194917756L;


	@ManyToOne
	@JoinColumn(name = "ret_vente_id", insertable = true, updatable = true)
	private RetourVenteBean r_vente = new RetourVenteBean();
	 
	 
	@ManyToOne
	@JoinColumns( {
		    @JoinColumn(name = "vente_id", insertable = true, updatable = true, referencedColumnName = "vente_id"),
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private DetProcedureVenteBean     detv = new DetProcedureVenteBean();
	
	 
	 

	public RetourVenteBean getR_vente() {
		return r_vente;
	}

	public void setR_vente(RetourVenteBean r_vente) {
		this.r_vente = r_vente;
	}

	public DetProcedureVenteBean getDetv() {
		return detv;
	}

	public void setDetv(DetProcedureVenteBean detv) {
		this.detv = detv;
	}

	 
 

	 

}
