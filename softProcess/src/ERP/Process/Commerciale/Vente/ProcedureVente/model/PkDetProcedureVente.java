package ERP.Process.Commerciale.Vente.ProcedureVente.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Embeddable
public class PkDetProcedureVente extends GenericBean {

	private static final long serialVersionUID = 9060147087641162699L;

	@ManyToOne
	@JoinColumn(name = "vente_id", insertable = true, updatable = true)
	private ProcedureVenteBean vente = new ProcedureVenteBean();

	 
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean     fkcode_barre = new Code_barreBean();
	

	public Code_barreBean getFkcode_barre() {
		return fkcode_barre;
	}

	public void setFkcode_barre(Code_barreBean fkcode_barre) {
		this.fkcode_barre = fkcode_barre;
	}

	public ProcedureVenteBean getVente() {
		return vente;
	}

	public void setVente(ProcedureVenteBean vente) {
		this.vente = vente;
	}

	 

}
