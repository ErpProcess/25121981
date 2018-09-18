package ERP.Process.Commerciale.Stock.Inventaire.model;
import java.io.Serializable;
import java.util.Date;

import javassist.bytecode.stackmap.TypeTag;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.TypeInventaire.model.TypeInventaireBean;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;


 
@Embeddable
public class  PkInventaire implements Serializable,Cloneable  {
	
	
	 
	 
	private static final long serialVersionUID = 5902451092858622820L;

	@Column
	private Date inv_date;
	
	@Column
	private String inv_id = "";
	
	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean   depot_stocks = new DepotStockageBean();

	public Date getInv_date() {
		return inv_date;
	}

	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}

	public DepotStockageBean getDepot_stocks() {
		return depot_stocks;
	}

	public void setDepot_stocks(DepotStockageBean depot_stocks) {
		this.depot_stocks = depot_stocks;
	}

	public String getInv_id() {
		return inv_id;
	}

	public void setInv_id(String inv_id) {
		this.inv_id = inv_id;
	}
	 
 
 
	 
	}
