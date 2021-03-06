package ERP.Process.Commerciale.Vente.EditionVente.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EtatVenteProduit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1556458170124129924L;

	private Date date;

	private String invoice = "";

	private String client = "";
	
	private int rowSpanDate = 1;
	private boolean isrowSpanDate = false;

	private int rowSpanDetFacture = 1;
	private boolean isrowSpanDetFact = false;

	private String description = "";

	private Double qte;

	private Double nbrBox;

	private Double prixUnit;

	private Double total;

	private List listDetailVente;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public Double getNbrBox() {
		return nbrBox;
	}

	public void setNbrBox(Double nbrBox) {
		this.nbrBox = nbrBox;
	}

	public Double getPrixUnit() {
		return prixUnit;
	}

	public void setPrixUnit(Double prixUnit) {
		this.prixUnit = prixUnit;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List getListDetailVente() {
		return listDetailVente;
	}

	public void setListDetailVente(List listDetailVente) {
		this.listDetailVente = listDetailVente;
	}

	public int getRowSpanDate() {
		return rowSpanDate;
	}

	public void setRowSpanDate(int rowSpanDate) {
		this.rowSpanDate = rowSpanDate;
	}

	public int getRowSpanDetFacture() {
		return rowSpanDetFacture;
	}

	public void setRowSpanDetFacture(int rowSpanDetFacture) {
		this.rowSpanDetFacture = rowSpanDetFacture;
	}

	public boolean isIsrowSpanDate() {
		return isrowSpanDate;
	}

	public void setIsrowSpanDate(boolean isrowSpanDate) {
		this.isrowSpanDate = isrowSpanDate;
	}

	public boolean isIsrowSpanDetFact() {
		return isrowSpanDetFact;
	}

	public void setIsrowSpanDetFact(boolean isrowSpanDetFact) {
		this.isrowSpanDetFact = isrowSpanDetFact;
	}

}
