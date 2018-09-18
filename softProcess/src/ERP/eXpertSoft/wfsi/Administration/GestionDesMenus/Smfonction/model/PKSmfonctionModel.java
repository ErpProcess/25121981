package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;

 

@Embeddable
public class PKSmfonctionModel  implements Serializable,Cloneable{
	 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8515843139141620997L;


	@ManyToOne
	@JoinColumn(name = "sousmod_id" , insertable = true, updatable = true )
	private SousModuleBean soumBean= new SousModuleBean();
	 
	
	@ManyToOne
	@JoinColumn(name = "fct_id" , insertable = true, updatable = true )
	private FonctionBean fcBean=new FonctionBean();
	 
 

	
	public SousModuleBean getSoumBean() {
		return soumBean;
	}

	public void setSoumBean(SousModuleBean soumBean) {
		this.soumBean = soumBean;
	}
 
	
	public FonctionBean getFcBean() {
		return fcBean;
	}

	public void setFcBean(FonctionBean fcBean) {
		this.fcBean = fcBean;
	}
	
 
 
    
}
