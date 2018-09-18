package ERP.Process.Commerciale.Vente.Parametrage.Transport.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.dao.TransportDAO;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.model.TransportBean;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.template.TransportTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class TransportService  extends GenericWeb  {
	private TransportDAO daoTransport;
	@Autowired
	public void setDaoTransport(TransportDAO daoTransport) {
		this.daoTransport = daoTransport;
	}
	@Transactional(readOnly=true)
	public List<TransportBean> doFetchDatafromServer(TransportBean beanSearch) throws Exception {
		return daoTransport.doFindListTransport(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(TransportBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTransport.doSaveTransport(insertBean)){
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
	public Boolean  doUpdateRowData(TransportBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTransport.doUpdateTransport(updateBean)){
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
	public Boolean doDeleteRowData(TransportBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTransport.doDeleteTransport(deleteBean)){
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
