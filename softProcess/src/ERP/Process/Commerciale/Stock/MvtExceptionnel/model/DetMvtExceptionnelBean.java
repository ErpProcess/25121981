package ERP.Process.Commerciale.Stock.MvtExceptionnel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "det_mvt_exceptionnel", schema = "stock")
public class DetMvtExceptionnelBean extends GenericBean {

	private static final long serialVersionUID = -1293275674896191577L;

	@Column
	@Id
	@GeneratedValue
	private Number det_mvt_excep_id;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkCode_barre = new Code_barreBean();

	@Transient
	private String indx_row = "";

	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

	@Column
	private Double quantite;

	@Column
	private Double montant_tva;

	@Column
	private Double montant_ht;

	@Transient
	private Double quantite_en_stock;

	@Column
	private String observation = "";

	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getIndx_row() {
		return indx_row;
	}

	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}

	public String getIndx_row_next() {
		return indx_row_next;
	}

	public void setIndx_row_next(String indx_row_next) {
		this.indx_row_next = indx_row_next;
	}

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	public Double getQuantite_en_stock() {
		return quantite_en_stock;
	}

	public void setQuantite_en_stock(Double quantite_en_stock) {
		this.quantite_en_stock = quantite_en_stock;
	}

	public Number getDet_mvt_excep_id() {
		return det_mvt_excep_id;
	}

	public void setDet_mvt_excep_id(Number det_mvt_excep_id) {
		this.det_mvt_excep_id = det_mvt_excep_id;
	}

	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getMontant_tva() {
		return montant_tva;
	}

	public void setMontant_tva(Double montant_tva) {
		this.montant_tva = montant_tva;
	}

	public Double getMontant_ht() {
		return montant_ht;
	}

	public void setMontant_ht(Double montant_ht) {
		this.montant_ht = montant_ht;
	}

}
