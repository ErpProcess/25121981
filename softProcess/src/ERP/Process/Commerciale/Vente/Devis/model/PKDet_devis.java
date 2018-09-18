package ERP.Process.Commerciale.Vente.Devis.model;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;


@SuppressWarnings("serial")
@Embeddable
public class  PKDet_devis implements Serializable,Cloneable  {
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	     @JoinColumn(name = "ar_id", insertable = true, updatable = true,referencedColumnName="ar_id"),
	     @JoinColumn(name = "code_barre", insertable = true, updatable = true,referencedColumnName="code_barre"),
	})
	private Code_barreBean  code_barre = new Code_barreBean();
	
	@ManyToOne 
	@JoinColumn(name = "devis_id", insertable = true, updatable = true)
	private DevisBean    devis = new DevisBean();



	public Code_barreBean getCode_barre() {
		return code_barre;
	}



	public void setCode_barre(Code_barreBean code_barre) {
		this.code_barre = code_barre;
	}



	public DevisBean getDevis() {
		return devis;
	}



	public void setDevis(DevisBean devis) {
		this.devis = devis;
	}
	 
	 

	 


	 

 
	 
	}
