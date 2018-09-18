package ERP.Process.Commerciale.Vente.ComplementVente.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Embeddable
public class PkDetComplement extends GenericBean {

 
	private static final long serialVersionUID = 6181530433494848430L;


	@ManyToOne
	@JoinColumn(name = "comp_vente_id", insertable = true, updatable = true)
	private ComplementVenteBean   c_vente = new ComplementVenteBean();
	 
	 
	@ManyToOne
	@JoinColumns( {
		    @JoinColumn(name = "vente_id", insertable = true, updatable = true, referencedColumnName = "vente_id"),
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private DetProcedureVenteBean     detv = new DetProcedureVenteBean();
	
	 
	 

	 

	public DetProcedureVenteBean getDetv() {
		return detv;
	}

	public void setDetv(DetProcedureVenteBean detv) {
		this.detv = detv;
	}

	public ComplementVenteBean getC_vente() {
		return c_vente;
	}

	public void setC_vente(ComplementVenteBean c_vente) {
		this.c_vente = c_vente;
	}

	 
 

	 

}
