package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.dao.EntiteAdminDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
 



/**
 * Contact Service
 * 
 */
@Service
public class EntiteAdminService {
	
	private EntiteAdminDAO daoEntiteAdmin;

	 
	public Boolean CreateRowData_with_List(List listEntiteAdmin){  
		if(daoEntiteAdmin.saveEntiteAdmin_with_List(listEntiteAdmin))
			return true;
		else
			return false;
			
	}
	
	
	public Boolean CreateRowData(EntiteAdminBean beanInsert,List listLangueEntite  ) throws Exception{  
		 
		if(daoEntiteAdmin.saveEntiteAdmin(beanInsert,listLangueEntite))
			return true;
		else
			return false;
			
	}
	
	public Boolean CreateNameColunm(EntiteAdminBean beanIns){  
		if(daoEntiteAdmin.saveEntiteAdminColunm(beanIns))
			return true;
		else
			return false;
			
	}
	
	
	@Transactional
	public Boolean UpdateRowData(EntiteAdminBean beanUpdate) throws Exception {
		
		try {
			
		
		 daoEntiteAdmin.updateEntiteAdmin(beanUpdate) ;
			
		 
		
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
	
	@Transactional
	public Boolean DeleteRowData(EntiteAdminBean beanDelete){
		if(daoEntiteAdmin.deleteEntiteAdmin(beanDelete))
			return true;
		else
			return false;
			
	}
	/**
	 * Get all packageSyss
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<EntiteAdminBean> getListDataServer(EntiteAdminBean beanSerach){
		return daoEntiteAdmin.getListEntiteAdmin(beanSerach);
	}
	
	 
	
	 

	 
	
	@Autowired
	public void setDaoEntiteAdmin(EntiteAdminDAO daoEntiteAdmin) {
		this.daoEntiteAdmin = daoEntiteAdmin;
	}



	
}
