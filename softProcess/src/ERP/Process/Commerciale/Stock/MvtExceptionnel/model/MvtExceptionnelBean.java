package ERP.Process.Commerciale.Stock.MvtExceptionnel.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "mvt_exceptionnel", schema = "stock")
public class MvtExceptionnelBean extends GenericBean {

	private static final long serialVersionUID = -7285171166196233789L;
	@Id
	@GenericGenerator(name = "mvt_except_id", strategy = "ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.web.CustomIdGenerator")
	@GeneratedValue(generator = "mvt_except_id")
	@Column
	private String mvt_excep_id;

	@Column
	@DateTimeFormat(style = "dd/MM/yyyy")
	private Date mvt_excep_date;
	@Column
	private BigDecimal mode_op;
	@Column
	private java.sql.Time time_cre;
	@Column
	private String mvt_excep_obs = "";
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String soc_id = "";
	@Column
	private String etab_id = "";
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Time time_mod;

	public String getMvt_excep_id() {
		return mvt_excep_id;
	}

	public void setMvt_excep_id(String mvt_excep_id) {
		this.mvt_excep_id = mvt_excep_id;
	}

	public Date getMvt_excep_date() {
		return mvt_excep_date;
	}

	public void setMvt_excep_date(Date mvt_excep_date) {
		this.mvt_excep_date = mvt_excep_date;
	}

	public void setMode_op(BigDecimal mode_op) {
		this.mode_op = mode_op;
	}

	public BigDecimal getMode_op() {
		return mode_op;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setMvt_excep_obs(String mvt_excep_obs) {
		this.mvt_excep_obs = mvt_excep_obs;
	}

	public String getMvt_excep_obs() {
		return mvt_excep_obs;
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

	public void setSoc_id(String soc_id) {
		this.soc_id = soc_id;
	}

	public String getSoc_id() {
		return soc_id;
	}

	public void setEtab_id(String etab_id) {
		this.etab_id = etab_id;
	}

	public String getEtab_id() {
		return etab_id;
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
}
