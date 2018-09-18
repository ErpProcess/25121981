package ERP.Process.Commerciale.Stock.MouvementStock.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;




@Embeddable
public class PkMvtSerie implements Serializable, Cloneable {
 
 
	private static final long serialVersionUID = -8743886976972058415L;


	 
	@Column
	private String document_com_id = "";
	
	
	@ManyToOne
	@JoinColumn(name = "nature_mvt_id", insertable = true, updatable = true)
	private NatureMvtCommercialeBean nat_mvt = new NatureMvtCommercialeBean();
	
	
	
	@ManyToOne
	@JoinColumns( {
	@JoinColumn(name = "num_serie", insertable = true, updatable = true, referencedColumnName = "num_serie"),
	@JoinColumn(name = "depot_id", insertable = true, updatable = true, referencedColumnName = "depot_id"),})
	private SerieArticletBean   serieBean = new SerieArticletBean();
	
	 
	 
	public NatureMvtCommercialeBean getNat_mvt() {
		return nat_mvt;
	}

	public void setNat_mvt(NatureMvtCommercialeBean nat_mvt) {
		this.nat_mvt = nat_mvt;
	}

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

	 
	 

}
