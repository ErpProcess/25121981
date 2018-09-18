package ERP.Process.Commerciale.Article.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;

 
@Embeddable
public class PKArticleBean implements Serializable,Cloneable{
	
	 
	private static final long serialVersionUID = -7227486542268644969L;

	@Column
	private String ar_id = "";

	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean etabBean = new EtablissementBean();


	public String getAr_id() {
		return ar_id;
	}


	public void setAr_id(String ar_id) {
		this.ar_id = ar_id;
	}


	public EtablissementBean getEtabBean() {
		return etabBean;
	}


	public void setEtabBean(EtablissementBean etabBean) {
		this.etabBean = etabBean;
	}


	 
 
}
