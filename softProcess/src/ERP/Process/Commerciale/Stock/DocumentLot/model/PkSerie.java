package ERP.Process.Commerciale.Stock.DocumentLot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;

@Embeddable
public class PkSerie implements Serializable, Cloneable {
 
 
	private static final long serialVersionUID = -9105249206392960754L;


	@Column
	private String num_serie = "";

	 
	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean depot = new DepotStockageBean();

	 

	
	 
	
	public DepotStockageBean getDepot() {
		return depot;
	}

	public void setDepot(DepotStockageBean depot) {
		this.depot = depot;
	}

	public String getNum_serie() {
		return num_serie;
	}

	public void setNum_serie(String num_serie) {
		this.num_serie = num_serie;
	}

	 

}
