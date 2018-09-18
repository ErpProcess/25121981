package ERP.Process.Commerciale.Stock.MouvementStock.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;




@Embeddable
public class PkIncidentMvtSerie implements Serializable, Cloneable {
 
 
 
	private static final long serialVersionUID = 2817395864402079794L;


	@Column
	private String document_com_id = "";
	
	@Column
	private String mvt_incident_id = "";
	
	@ManyToOne
	@JoinColumn(name = "nat_incident_id", insertable = true, updatable = true)
	private Nature_incident_mvt_retourBean cause  ;
	
	
	
	@ManyToOne
	@JoinColumn(name = "nature_mvt_id", insertable = true, updatable = true)
	private NatureMvtCommercialeBean origin_mvt = new NatureMvtCommercialeBean();
	
	
	@ManyToOne
	@JoinColumn(name = "nat_mvt_serie_id", insertable = true, updatable = true)
	private NatureMvtCommercialeBean incid_mvt = new NatureMvtCommercialeBean();
	
	
	
	
	
	@ManyToOne
	@JoinColumns( {
	@JoinColumn(name = "num_serie", insertable = true, updatable = true, referencedColumnName = "num_serie"),
	@JoinColumn(name = "depot_id", insertable = true, updatable = true, referencedColumnName = "depot_id"),})
	private SerieArticletBean   serieBean = new SerieArticletBean();
	
	 
	 
	 

	public String getDocument_com_id() {
		return document_com_id;
	}

	public void setDocument_com_id(String document_com_id) {
		this.document_com_id = document_com_id;
	}

	public SerieArticletBean getSerieBean() {
		return serieBean;
	}

	public void setSerieBean(SerieArticletBean serieBean) {
		this.serieBean = serieBean;
	}

	public Nature_incident_mvt_retourBean getCause() {
		return cause;
	}

	public void setCause(Nature_incident_mvt_retourBean cause) {
		this.cause = cause;
	}

	public String getMvt_incident_id() {
		return mvt_incident_id;
	}

	public void setMvt_incident_id(String mvt_incident_id) {
		this.mvt_incident_id = mvt_incident_id;
	}

	public NatureMvtCommercialeBean getOrigin_mvt() {
		return origin_mvt;
	}

	public void setOrigin_mvt(NatureMvtCommercialeBean origin_mvt) {
		this.origin_mvt = origin_mvt;
	}

	public NatureMvtCommercialeBean getIncid_mvt() {
		return incid_mvt;
	}

	public void setIncid_mvt(NatureMvtCommercialeBean incid_mvt) {
		this.incid_mvt = incid_mvt;
	}

	 
	 
	 

}
