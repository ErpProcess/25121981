package ERP.Process.Commerciale.Vente.EditionVente.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Transient;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
 

public class EtatDepenseProduit implements Serializable {

 
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -71747269456198952L;

	private Date date;

	private String invoice = "";

	private String client = "";
	private String clientId = "";
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	private int     rowSpanDate = 1;
	private boolean isrowSpanDate = false;

	//private int     rowSpanDetFacture = 1;
	//private boolean isrowSpanDetFact = false;

	private String description = "";

	private Double qteFish;
	
	private Double prixUnitFish;
	
	private Double prixtotFish;
	
	private Double trsprt;
	
	private Double transit;
	
	private Double mdOuevre;

	private Double nbrBox;
	private Double prixUnitPoly;
	
	private Double prixtotPoly;
	
	
	private Double Embal;
	

	private Double scot_glace;
	
	private Double douane;
	
	private Double chambreCom;
	
	private Double transAe;

	private Double total;
 
	private DeviseBean  devise  = new DeviseBean();
	
	private HashMap     mapFourniture     = new HashMap();
	private HashMap     mapPrestation     = new HashMap();

	
	 
	private String				indx_row			= "";
	
	
	 
	private String				indx_row_next		= "";

	
	 
	private String				to_check			= "";
 
	
 
	
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

	 

	public Double getNbrBox() {
		return nbrBox;
	}

	public void setNbrBox(Double nbrBox) {
		this.nbrBox = nbrBox;
	}

	 

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	 
	public int getRowSpanDate() {
		return rowSpanDate;
	}

	public void setRowSpanDate(int rowSpanDate) {
		this.rowSpanDate = rowSpanDate;
	}

//	public int getRowSpanDetFacture() {
//		return rowSpanDetFacture;
//	}
//
//	public void setRowSpanDetFacture(int rowSpanDetFacture) {
//		this.rowSpanDetFacture = rowSpanDetFacture;
//	}

	public boolean isIsrowSpanDate() {
		return isrowSpanDate;
	}

	public void setIsrowSpanDate(boolean isrowSpanDate) {
		this.isrowSpanDate = isrowSpanDate;
	}

//	public boolean isIsrowSpanDetFact() {
//		return isrowSpanDetFact;
//	}
//
//	public void setIsrowSpanDetFact(boolean isrowSpanDetFact) {
//		this.isrowSpanDetFact = isrowSpanDetFact;
//	}

	public Double getQteFish() {
		return qteFish;
	}

	public void setQteFish(Double qteFish) {
		this.qteFish = qteFish;
	}

	public Double getPrixUnitFish() {
		return prixUnitFish;
	}

	public void setPrixUnitFish(Double prixUnitFish) {
		this.prixUnitFish = prixUnitFish;
	}

	public Double getPrixtotFish() {
		return prixtotFish;
	}

	public void setPrixtotFish(Double prixtotFish) {
		this.prixtotFish = prixtotFish;
	}

	public Double getTrsprt() {
		return trsprt;
	}

	public void setTrsprt(Double trsprt) {
		this.trsprt = trsprt;
	}

	public Double getTransit() {
		return transit;
	}

	public void setTransit(Double transit) {
		this.transit = transit;
	}

	public Double getMdOuevre() {
		return mdOuevre;
	}

	public void setMdOuevre(Double mdOuevre) {
		this.mdOuevre = mdOuevre;
	}

	public Double getPrixUnitPoly() {
		return prixUnitPoly;
	}

	public void setPrixUnitPoly(Double prixUnitPoly) {
		this.prixUnitPoly = prixUnitPoly;
	}

	public Double getPrixtotPoly() {
		return prixtotPoly;
	}

	public void setPrixtotPoly(Double prixtotPoly) {
		this.prixtotPoly = prixtotPoly;
	}

	public Double getEmbal() {
		return Embal;
	}

	public void setEmbal(Double embal) {
		Embal = embal;
	}

	public Double getScot_glace() {
		return scot_glace;
	}

	public void setScot_glace(Double scot_glace) {
		this.scot_glace = scot_glace;
	}

	public Double getDouane() {
		return douane;
	}

	public void setDouane(Double douane) {
		this.douane = douane;
	}

	public Double getChambreCom() {
		return chambreCom;
	}

	public void setChambreCom(Double chambreCom) {
		this.chambreCom = chambreCom;
	}

	public Double getTransAe() {
		return transAe;
	}

	public void setTransAe(Double transAe) {
		this.transAe = transAe;
	}

	public String getIndx_row() {
		return indx_row;
	}

	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}

	public String getIndx_row_next() {
		return indx_row_next;
	}

	public void setIndx_row_next(String indx_row_next) {
		this.indx_row_next = indx_row_next;
	}

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	public HashMap getMapFourniture() {
		return mapFourniture;
	}

	public void setMapFourniture(HashMap mapFourniture) {
		this.mapFourniture = mapFourniture;
	}

	public HashMap getMapPrestation() {
		return mapPrestation;
	}

	public void setMapPrestation(HashMap mapPrestation) {
		this.mapPrestation = mapPrestation;
	}
	

}
