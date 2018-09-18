package ERP.Process.Commerciale.DetailCaracteristique.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;

@SuppressWarnings("serial")
@Embeddable
public class PKDet_caracteristique implements Serializable,Cloneable {

	@ManyToOne
	@JoinColumn(name = "carac_id", insertable = true, updatable = true)
	private CaracteristiqueBean bean_carac = new CaracteristiqueBean();

	@Column
	private String det_carac_id = "";

	public CaracteristiqueBean getBean_carac() {
		return bean_carac;
	}

	public void setBean_carac(CaracteristiqueBean bean_carac) {
		this.bean_carac = bean_carac;
	}

	public String getDet_carac_id() {
		return det_carac_id;
	}

	public void setDet_carac_id(String det_carac_id) {
		this.det_carac_id = det_carac_id;
	}

}
