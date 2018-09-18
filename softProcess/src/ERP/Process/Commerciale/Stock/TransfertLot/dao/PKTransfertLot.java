package ERP.Process.Commerciale.Stock.TransfertLot.dao;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;


 
@Embeddable
public class  PKTransfertLot implements Serializable,Cloneable  {
	
	
 
	private static final long serialVersionUID = 3376945945530185791L;



	@Column	private String  transfert_id =""; 
	 
	@ManyToOne
	@JoinColumns( {
	@JoinColumn(name = "num_serie", insertable = true, updatable = true, referencedColumnName = "num_serie"),
	@JoinColumn(name = "depot_id_emet", insertable = true, updatable = true, referencedColumnName = "depot_id"),})
	private SerieArticletBean   lot = new SerieArticletBean();
	
	  
	@ManyToOne
	@JoinColumn(name = "depot_id_recep", insertable = true, updatable = true)
	private DepotStockageBean recep = new DepotStockageBean();


	public String getTransfert_id() {
		return transfert_id;
	}


	public void setTransfert_id(String transfert_id) {
		this.transfert_id = transfert_id;
	}


	public SerieArticletBean getLot() {
		return lot;
	}


	public void setLot(SerieArticletBean lot) {
		this.lot = lot;
	}


	public DepotStockageBean getRecep() {
		return recep;
	}


	public void setRecep(DepotStockageBean recep) {
		this.recep = recep;
	}

  

	
	
	 
	 
	}
