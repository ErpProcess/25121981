package ERP.Process.Commerciale.Achat.Reception_achat.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;


@Embeddable
public class PkDetailleReception implements Serializable, Cloneable {

 
	private static final long serialVersionUID = 5763093927223154677L;



	@ManyToOne
	@JoinColumn(name = "achat_id", insertable = true, updatable = true)
	private Reception_achatBean recepBean = new Reception_achatBean();
	


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

	public Reception_achatBean getRecepBean() {
		return recepBean;
	}

	public void setRecepBean(Reception_achatBean recepBean) {
		this.recepBean = recepBean;
	}

}
