package ERP.Process.Commerciale.TarificationLot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "tarification_vente_serie", schema = "gestioncommerciale")

public class TarificationLotBean  extends  GenericBean {
 
	private static final long serialVersionUID = 7996455363080548732L;

	@Column
	private String tarif_vente_serie_id  ;
	
	@EmbeddedId
	private PkTarifLot  pk = new PkTarifLot();

	public PkTarifLot getPk() {
		return pk;
	}

	public void setPk(PkTarifLot pk) {
		this.pk = pk;
	}
	
	@Column
	private java.sql.Time time_cre ;
	
	
	@Column
	private java.sql.Time time_mod ;
	
	
	
	@Column
	private  Date date_cre;
	
	
	@Column
	private  Date date_tarif;
	
	

	@Column
	private String usr_cre = "";

	@Column
	private  Date date_mod;

	@Column
	private String usr_mod = "";
	
	
	@ManyToOne 
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean  modeBean =  new FonctionBean();
	
	
	@Column
	private String clt_id  ;
	
	@Column
	private Double tarif_unit_vente  ;
	
	
	
	
	
	 
	

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
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

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

 

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public Date getDate_tarif() {
		return date_tarif;
	}

	public void setDate_tarif(Date date_tarif) {
		this.date_tarif = date_tarif;
	}

	public String getTarif_vente_serie_id() {
		return tarif_vente_serie_id;
	}

	public void setTarif_vente_serie_id(String tarif_vente_serie_id) {
		this.tarif_vente_serie_id = tarif_vente_serie_id;
	}

	public String getClt_id() {
		return clt_id;
	}

	public void setClt_id(String clt_id) {
		this.clt_id = clt_id;
	}

	public Double getTarif_unit_vente() {
		return tarif_unit_vente;
	}

	public void setTarif_unit_vente(Double tarif_unit_vente) {
		this.tarif_unit_vente = tarif_unit_vente;
	}

	 
}
