package ERP.Process.Commerciale.Stock.ResponsableLieu.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;

@Embeddable
public class PkResponsable implements Serializable, Cloneable {

	 
	private static final long serialVersionUID = 5581409824375990236L;

	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean depot = new DepotStockageBean();

	@ManyToOne
	@JoinColumn(name = "usr_id", insertable = true, updatable = true)
	private UtilisateurBean usr = new UtilisateurBean();

	public UtilisateurBean getUsr() {
		return usr;
	}

	public void setUsr(UtilisateurBean usr) {
		this.usr = usr;
	}

	public DepotStockageBean getDepot() {
		return depot;
	}

	public void setDepot(DepotStockageBean depot) {
		this.depot = depot;
	}

}
