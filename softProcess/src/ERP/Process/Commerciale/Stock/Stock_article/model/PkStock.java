package ERP.Process.Commerciale.Stock.Stock_article.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
 

@Embeddable
public class PkStock implements Serializable, Cloneable {

 
	private static final long serialVersionUID = 2446298586392074352L;

	@Column	private Date  date_stock;
	  
	@ManyToOne
    @JoinColumn(name = "depot_id", insertable = true, updatable = true)
    private DepotStockageBean depot = new DepotStockageBean();
	 

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean fkCode_barre = new Code_barreBean();
	
	

	 

	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}

	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}

	 

 
	public Date getDate_stock() {
		return date_stock;
	}

	public void setDate_stock(Date date_stock) {
		this.date_stock = date_stock;
	}

	public DepotStockageBean getDepot() {
		return depot;
	}

	public void setDepot(DepotStockageBean depot) {
		this.depot = depot;
	}

	 

}
