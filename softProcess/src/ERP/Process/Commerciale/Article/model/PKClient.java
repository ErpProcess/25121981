package ERP.Process.Commerciale.Article.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;

 
@Embeddable
public class PKClient implements Serializable,Cloneable{
	
 
	private static final long serialVersionUID = 1L;


	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), })
	private Code_barreBean     ref = new Code_barreBean();
	
	
	@ManyToOne
	@JoinColumn(name = "clt_id", insertable = true, updatable = true)
	private ClientBean client = new ClientBean();


	public Code_barreBean getRef() {
		return ref;
	}


	public void setRef(Code_barreBean ref) {
		this.ref = ref;
	}


	public ClientBean getClient() {
		return client;
	}


	public void setClient(ClientBean client) {
		this.client = client;
	}


	 


	 


	 
 
}
