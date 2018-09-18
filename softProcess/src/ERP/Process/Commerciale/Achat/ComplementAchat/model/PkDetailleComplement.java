package ERP.Process.Commerciale.Achat.ComplementAchat.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;

@Embeddable
public class PkDetailleComplement implements Serializable, Cloneable {
 
 
	private static final long serialVersionUID = 7059033347864980473L;

	@ManyToOne 
	@JoinColumn(name = "complet_id", insertable = true, updatable = true)
	private ComplementAchatBean     complement = new ComplementAchatBean();
	 
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkCode_barre = new Code_barreBean();

	public ComplementAchatBean getComplement() {
		return complement;
	}

	public void setComplement(ComplementAchatBean complement) {
		this.complement = complement;
	}

	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}
	
	 
 

	 

}
