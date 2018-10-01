package ERP.Process.Commerciale.Vente.Service.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.Service.dao.ServiceDAO;
import ERP.Process.Commerciale.Vente.Service.model.DetServiceBean;
import ERP.Process.Commerciale.Vente.Service.model.ServiceBean;
import ERP.Process.Commerciale.Vente.Service.template.ServiceTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class ServiceService  extends GenericWeb  {
	private ServiceDAO daoService;
	@Autowired
	public void setDaoService(ServiceDAO daoService) {
		this.daoService = daoService;
	}
	@Transactional(readOnly=true)
	public List<ServiceBean> doFetchDatafromServer(ServiceBean beanSearch) throws Exception {
		return daoService.doFindListService(beanSearch);
	}
	
	
	@Transactional(readOnly=true)
	public List<DetServiceBean> doFetchDetailfromServer(ProcedureVenteBean beanSearch) throws Exception {
		return daoService.doFindDetailListService(beanSearch);
	}
	
	
	@Transactional
	public Boolean doCreateRowData(ServiceBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoService.doSaveService(insertBean)){
		       result = true;
		       }else{
		    	result = false;  
		       }
		 } catch (Exception e) { 
			 result = false;
			 throw e;
		}
		return result; 
	}
	@Transactional
	public Boolean  doUpdateRowData(ServiceBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoService.doUpdateService(updateBean)){
		    result = true;
		       }else{
		    	result = false;  
		     }
		 } catch (Exception e) { 
			 result = false;
			 throw e;
		}
	 return result; 
	}
	@Transactional
	public Boolean doDeleteRowData(ServiceBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoService.doDeleteService(deleteBean)){
		        result = true;
		      }else{
		    	result = false;  
		     }
		 } catch (Exception e) { 
			 result = false;
			 throw e;
		}
   return result; 
	}
}
