package ERP.Process.Commerciale.Stock.Stock_article.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;

@Embeddable
public class PkIncidentStock implements Serializable, Cloneable {

 
	private static final long serialVersionUID = 4773347336036178401L;

	@Column
	private String stock_article_id = "";

	@ManyToOne
	@JoinColumn(name = "nat_incident_id", insertable = true, updatable = true)
	private Nature_incident_mvt_retourBean cause  ;

	public String getStock_article_id() {
		return stock_article_id;
	}

	public void setStock_article_id(String stock_article_id) {
		this.stock_article_id = stock_article_id;
	}

	public Nature_incident_mvt_retourBean getCause() {
		return cause;
	}

	public void setCause(Nature_incident_mvt_retourBean cause) {
		this.cause = cause;
	}

}
