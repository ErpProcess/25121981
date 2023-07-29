package ERP.Process.Commerciale.Vente.ReglementFactClt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Embeddable
public class PkEcheanceClt extends GenericBean {

 
	private static final long serialVersionUID = -6515783771294365821L;

	@ManyToOne
	@JoinColumn(name = "reg_id", insertable = true, updatable = true)
	private ReglementFactCltBean reg = new ReglementFactCltBean();
	
	@Column
	private Date echean_date;

	public ReglementFactCltBean getReg() {
		return reg;
	}

	public void setReg(ReglementFactCltBean reg) {
		this.reg = reg;
	}

	public Date getEchean_date() {
		return echean_date;
	}

	public void setEchean_date(Date echean_date) {
		this.echean_date = echean_date;
	}

	 
	 
 
 
	 

}
