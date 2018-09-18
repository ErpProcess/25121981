package ERP.Process.Commerciale.TarificationLot.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;


@Embeddable
public class PkTarifLot implements Serializable, Cloneable {
 
	private static final long serialVersionUID = -934111650366925015L;

	@ManyToOne
	@JoinColumn(name = "tarif_vente_id", insertable = true, updatable = true,referencedColumnName="tarif_vente_id")
	private TarificationBean vente = new TarificationBean();
	
	
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "num_serie", insertable = true, updatable = true, referencedColumnName = "num_serie"),
			@JoinColumn(name = "depot_id", insertable = true, updatable = true, referencedColumnName = "depot_id"),})
	private SerieArticletBean   lot = new SerieArticletBean();

	public TarificationBean getVente() {
		return vente;
	}

	public void setVente(TarificationBean vente) {
		this.vente = vente;
	}

	public SerieArticletBean getLot() {
		return lot;
	}

	public void setLot(SerieArticletBean lot) {
		this.lot = lot;
	}

	 
	 
	
 
	 

	 

}
