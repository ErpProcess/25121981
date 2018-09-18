package ERP.Process.Commerciale.Vente.ReglementFactClt.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "echeance_reglement", schema = "vente")
public class EcheanceRegCltBean extends GenericBean {

	private static final long serialVersionUID = 4111384595213936193L;

	@EmbeddedId
	private PkEcheanceClt pk = new PkEcheanceClt();

	@ManyToOne
	@JoinColumn(name = "echeance_mod", insertable = true, updatable = true)
	private Entite_etat_commercialeBean echMode = new Entite_etat_commercialeBean();

	@Column
	private Double echean_montant;

	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";

	@ManyToOne
	@JoinColumn(name = "etat_reg_ech", insertable = true, updatable = true)
	private Entite_etat_commercialeBean etatRegEch = new Entite_etat_commercialeBean();

	@Column
	private Time time_cre;
	@Column
	private Time time_mod;

	@Column
	private String num_piece_ech = "";

	@Transient
	private String indx_row = "";

	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

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

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public Time getTime_cre() {
		return time_cre;
	}

	public void setTime_cre(Time time_cre) {
		this.time_cre = time_cre;
	}

	public Time getTime_mod() {
		return time_mod;
	}

	public void setTime_mod(Time time_mod) {
		this.time_mod = time_mod;
	}

	public String getNum_piece_ech() {
		return num_piece_ech;
	}

	public void setNum_piece_ech(String num_piece_ech) {
		this.num_piece_ech = num_piece_ech;
	}

	public PkEcheanceClt getPk() {
		return pk;
	}

	public void setPk(PkEcheanceClt pk) {
		this.pk = pk;
	}

	 

	public Entite_etat_commercialeBean getEtatRegEch() {
		return etatRegEch;
	}

	public void setEtatRegEch(Entite_etat_commercialeBean etatRegEch) {
		this.etatRegEch = etatRegEch;
	}

	public Double getEchean_montant() {
		return echean_montant;
	}

	public void setEchean_montant(Double echean_montant) {
		this.echean_montant = echean_montant;
	}

	public Entite_etat_commercialeBean getEchMode() {
		return echMode;
	}

	public void setEchMode(Entite_etat_commercialeBean echMode) {
		this.echMode = echMode;
	}
}
