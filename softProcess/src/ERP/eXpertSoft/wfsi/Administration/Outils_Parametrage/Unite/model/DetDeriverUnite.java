package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;

@JsonAutoDetect
@Entity
@Table(name = "detail_derviver_unite", schema = "administration")
public class DetDeriverUnite {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer det_drv_id;

	@ManyToOne
	@JoinColumn(name = "drv_id", insertable = true, updatable = false)
	private DeriverUnite drv = new DeriverUnite();

	@Column
	private String drv_oper = "";
	
	@Column
	private Double drv_coef;

	@Column
	private String usr_cre = "";

	@Column
	private Date date_cre;

	@Column
	private String usr_mod = "";

	@Column
	private Date date_mod;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkcode_barre = new Code_barreBean();

	public Integer getDet_drv_id() {
		return det_drv_id;
	}

	public void setDet_drv_id(Integer det_drv_id) {
		this.det_drv_id = det_drv_id;
	}

	public DeriverUnite getDrv() {
		return drv;
	}

	public void setDrv(DeriverUnite drv) {
		this.drv = drv;
	}

	public String getDrv_oper() {
		return drv_oper;
	}

	public void setDrv_oper(String drv_oper) {
		this.drv_oper = drv_oper;
	}

	public Double getDrv_coef() {
		return drv_coef;
	}

	public void setDrv_coef(Double drv_coef) {
		this.drv_coef = drv_coef;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public Code_barreBean getFkcode_barre() {
		return fkcode_barre;
	}

	public void setFkcode_barre(Code_barreBean fkcode_barre) {
		this.fkcode_barre = fkcode_barre;
	}
	

}
