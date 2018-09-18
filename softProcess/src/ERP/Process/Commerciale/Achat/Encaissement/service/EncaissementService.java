package ERP.Process.Commerciale.Achat.Encaissement.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Achat.Encaissement.dao.EncaissementDAO;
 
import ERP.Process.Commerciale.Achat.Encaissement.template.EncaissementTemplate;
import ERP.Process.Commerciale.Achat.Regachat.model.RegachatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class EncaissementService  extends GenericWeb  {
	private EncaissementDAO daoEncaissement;
	@Autowired
	public void setDaoEncaissement(EncaissementDAO daoEncaissement) {
		this.daoEncaissement = daoEncaissement;
	}
	@Transactional(readOnly=true)
	public List<RegachatBean> doFetchDatafromServer(RegachatBean beanSearch) throws Exception {
		return daoEncaissement.doFindListEncaissement(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(RegachatBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoEncaissement.doSaveEncaissement(insertBean)){
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
	public Boolean  doUpdateRowData(RegachatBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoEncaissement.doUpdateEncaissement(updateBean)){
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
	public Boolean doDeleteRowData(RegachatBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoEncaissement.doDeleteEncaissement(deleteBean)){
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
