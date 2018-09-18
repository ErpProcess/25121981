package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.dao.GLangueDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.dao.GlibelleDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;



/**
 * Contact Service
 * 
 */
@Service
public class GlibelleService {
	
	private GlibelleDAO daoGlibelle;

	 
	public Boolean CreateRowData(List listLibbelle){  
		if(daoGlibelle.saveGlibelle(listLibbelle))
			return true;
		else
			return false;
			
	}
	
	public Boolean CreateNameColunm(GlibelleBean beanIns){  
		if(daoGlibelle.saveGlibelleColunm(beanIns))
			return true;
		else
			return false;
			
	}
	
	
	@Transactional
	public Boolean UpdateRowData(GlibelleBean beanUpdate){
		if(daoGlibelle.updateGlibelle(beanUpdate))
			return true;
		else
			return false;
			
	}
	
	
	@Transactional
	public Boolean DeleteRowData(GlibelleBean beanDelete){
		if(daoGlibelle.deleteGlibelle(beanDelete))
			return true;
		else
			return false;
			
	}
	/**
	 * Get all packageSyss
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<GlibelleBean> getListDataServer(GlibelleBean beanSerach){
		return daoGlibelle.getListGlibelle(beanSerach);
	}
	
	 
	
	 

	 
	
	@Autowired
	public void setDaoGlibelle(GlibelleDAO daoGlibelle) {
		this.daoGlibelle = daoGlibelle;
	}



	
}
