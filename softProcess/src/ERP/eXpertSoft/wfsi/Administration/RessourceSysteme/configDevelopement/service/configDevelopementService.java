package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.dao.configDevelopementDAO;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.model.configDevelopementBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.template.configDevelopementTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class configDevelopementService  extends GenericWeb  {
	private configDevelopementDAO daoconfigDevelopement;
	@Autowired
	public void setDaoconfigDevelopement(configDevelopementDAO daoconfigDevelopement) {
		this.daoconfigDevelopement = daoconfigDevelopement;
	}
	@Transactional(readOnly=true)
	public List<configDevelopementBean> doFetchDatafromServer(configDevelopementBean beanSearch) throws Exception {
		return daoconfigDevelopement.doFindListconfigDevelopement(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(configDevelopementBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoconfigDevelopement.doSaveconfigDevelopement(insertBean)){
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
	public Boolean  doUpdateRowData(configDevelopementBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoconfigDevelopement.doUpdateconfigDevelopement(updateBean)){
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
	public Boolean doDeleteRowData(configDevelopementBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoconfigDevelopement.doDeleteconfigDevelopement(deleteBean)){
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
