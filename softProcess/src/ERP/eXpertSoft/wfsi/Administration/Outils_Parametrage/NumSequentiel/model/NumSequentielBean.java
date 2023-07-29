package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "num_sequentiel", schema = "administration")
public class NumSequentielBean {

	@EmbeddedId
	private PKNumSeqBean pknumseqbean = new PKNumSeqBean();

	@Column(name = "numero")
	private String numero = "";
	
	@Column(name = "jour")
	private String jour = "";
	
	

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public PKNumSeqBean getPknumseqbean() {
		return pknumseqbean;
	}

	public void setPknumseqbean(PKNumSeqBean pknumseqbean) {
		this.pknumseqbean = pknumseqbean;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

}
