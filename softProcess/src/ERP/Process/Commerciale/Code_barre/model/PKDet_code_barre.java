package ERP.Process.Commerciale.Code_barre.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;

@SuppressWarnings("serial")
@Embeddable
public class PKDet_code_barre implements Serializable,Cloneable {

	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "code_barre", insertable = true, updatable = true,referencedColumnName="code_barre"),
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	     @JoinColumn(name = "ar_id", insertable = true, updatable = true,referencedColumnName="ar_id"),
	})
	private Code_barreBean bean_cod_bar = new Code_barreBean();
	
	@ManyToOne 
	@JoinColumns({
		 @JoinColumn(name = "carac_id", insertable = true, updatable = true,referencedColumnName="carac_id"),
	     @JoinColumn(name = "det_carac_id", insertable = true, updatable = true,referencedColumnName="det_carac_id"),
	})
	private DetailCaracteristiqueBean  bean_det_carac= new  DetailCaracteristiqueBean();

	public Code_barreBean getBean_cod_bar() {
		return bean_cod_bar;
	}

	public void setBean_cod_bar(Code_barreBean bean_cod_bar) {
		this.bean_cod_bar = bean_cod_bar;
	}

	public DetailCaracteristiqueBean getBean_det_carac() {
		return bean_det_carac;
	}

	public void setBean_det_carac(DetailCaracteristiqueBean bean_det_carac) {
		this.bean_det_carac = bean_det_carac;
	}
	
	
	 

	 

}
