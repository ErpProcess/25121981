package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.dao.GLangueDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.dao.TypelibelleDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model.TypelibelleBean;



/**
 * Contact Service
 * 
 */
@Service
public class TypelibelleService {
	
	private TypelibelleDAO daoTypelibelle;

	@Transactional
	public Boolean CreateRowData(TypelibelleBean  beanInsert){  
		if(daoTypelibelle.saveTypelibelle(beanInsert))
			return true;
		else
			return false;
			
	}
	@Transactional
	public Boolean UpdateRowData(TypelibelleBean beanUpdate){
		if(daoTypelibelle.updateTypelibelle(beanUpdate))
			return true;
		else
			return false;
			
	}
	
	
	@Transactional
	public Boolean DeleteRowData(TypelibelleBean beanDelete){
		if(daoTypelibelle.deleteTypelibelle(beanDelete))
			return true;
		else
			return false;
			
	}
	/**
	 * Get all packageSyss
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<TypelibelleBean> getListDataServer(TypelibelleBean beanSerach){
		return daoTypelibelle.getListTypelibelle(beanSerach);
	}
	
	 
	
	 

	 
	
	@Autowired
	public void setDaoTypelibelle(TypelibelleDAO daoTypelibelle) {
		this.daoTypelibelle = daoTypelibelle;
	}



	
}
