package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.model;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model.SmfonctionModel;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;


@Embeddable
public class  PKPrivi implements Serializable  {
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "prf_id" , insertable = true, updatable = true )
	private ProfileBean  pfrBean= new  ProfileBean();
	 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		 @JoinColumn(name = "sousmod_id", insertable = true, updatable = true,referencedColumnName="sousmod_id"),
	     @JoinColumn(name = "fct_id", insertable = true, updatable = true,referencedColumnName="fct_id"),
	})
	private SmfonctionModel  smfonctionmodel= new  SmfonctionModel();
	 
	public PKPrivi() {
		super();
		 
	}
	 
	public ProfileBean getPfrBean() {
		return pfrBean;
	}

	public void setPfrBean(ProfileBean pfrBean) {
		this.pfrBean = pfrBean;
	}

	public SmfonctionModel getSmfonctionmodel() {
		return smfonctionmodel;
	}

	public void setSmfonctionmodel(SmfonctionModel smfonctionmodel) {
		this.smfonctionmodel = smfonctionmodel;
	}

	 
	 

	 

	 
	 
 
	
	 
	}
