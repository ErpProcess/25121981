package ERP.Process.Commerciale.Achat.Retour_achat.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;

@Embeddable
public class PkDetailleRetour implements Serializable, Cloneable {
 
 
  
	private static final long serialVersionUID = 7482577538543698760L;




	@ManyToOne 
	@JoinColumn(name = "retour_id", insertable = true, updatable = true)
	private Retour_achatBean   retour = new Retour_achatBean();
	 
	 
	
	
	@ManyToOne
	@JoinColumns( {
		    @JoinColumn(name = "achat_id", insertable = true, updatable = true, referencedColumnName = "achat_id"),
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Det_reception_achatBean     det_re = new Det_reception_achatBean();
	
	
	 
	public Retour_achatBean getRetour() {
		return retour;
	}

	public void setRetour(Retour_achatBean retour) {
		this.retour = retour;
	}

	public Det_reception_achatBean getDet_re() {
		return det_re;
	}

	public void setDet_re(Det_reception_achatBean det_re) {
		this.det_re = det_re;
	}

	 
	 

}
