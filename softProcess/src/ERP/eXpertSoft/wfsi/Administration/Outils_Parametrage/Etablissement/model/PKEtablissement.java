package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;


@SuppressWarnings("serial")
@Embeddable
public class  PKEtablissement implements Serializable,Cloneable  {
	
	
	
	
	@Column(name = "etab_id", nullable = false)
	private String etab_id = "";
	
	@ManyToOne 
	@JoinColumn(name = "soc_id", insertable = true, updatable = true)
	private SocieteBean   soc_bean = new SocieteBean();

	public String getEtab_id() {
		return etab_id;
	}

	public void setEtab_id(String etab_id) {
		this.etab_id = etab_id;
	}

	public SocieteBean getSoc_bean() {
		return soc_bean;
	}

	public void setSoc_bean(SocieteBean soc_bean) {
		this.soc_bean = soc_bean;
	}
	
	 
	 
	 
	 

 

 
	 
	}
