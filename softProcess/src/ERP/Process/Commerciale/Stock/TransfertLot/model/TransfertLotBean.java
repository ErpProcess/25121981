package ERP.Process.Commerciale.Stock.TransfertLot.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.TransfertLot.dao.PKTransfertLot;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "transfert_serie_lieu", schema = "stock")
public class TransfertLotBean extends GenericBean {

	private static final long serialVersionUID = -7466766785514075414L;

	@EmbeddedId
	private PKTransfertLot pk = new PKTransfertLot();

	@Column
	private Date date_transfert;
	@Column
	private Double quantite_transfert  ;
	@Column
	private String observation = "";
	@Column
	private String usr_cre = "";
	@Column
	private Date date_cre;
	@Column
	private Time time_cre;
	@Column
	private String usr_mod = "";
	@Column
	private Date date_mod;
	@Column
	private Time time_mod;
	@Column
	private String etat_de_transfert = "";

	 

	public Double getQuantite_transfert() {
		return quantite_transfert;
	}

	public void setQuantite_transfert(Double quantite_transfert) {
		this.quantite_transfert = quantite_transfert;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getObservation() {
		return observation;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public PKTransfertLot getPk() {
		return pk;
	}

	public void setPk(PKTransfertLot pk) {
		this.pk = pk;
	}

	public Date getDate_transfert() {
		return date_transfert;
	}

	public void setDate_transfert(Date date_transfert) {
		this.date_transfert = date_transfert;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	public void setEtat_de_transfert(String etat_de_transfert) {
		this.etat_de_transfert = etat_de_transfert;
	}

	public String getEtat_de_transfert() {
		return etat_de_transfert;
	}
}
