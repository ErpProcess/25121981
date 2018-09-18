package ERP.Process.Commerciale.Stock.MvtExceptionnel.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.MvtExceptionnel.dao.MvtExceptionnelDAO;
import ERP.Process.Commerciale.Stock.MvtExceptionnel.model.MvtExceptionnelBean;
import ERP.Process.Commerciale.Stock.MvtExceptionnel.template.MvtExceptionnelTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class MvtExceptionnelService  extends GenericWeb  {
	private MvtExceptionnelDAO daoMvtExceptionnel;
	@Autowired
	public void setDaoMvtExceptionnel(MvtExceptionnelDAO daoMvtExceptionnel) {
		this.daoMvtExceptionnel = daoMvtExceptionnel;
	}
	@Transactional(readOnly=true)
	public List<MvtExceptionnelBean> doFetchDatafromServer(MvtExceptionnelBean beanSearch) throws Exception {
		return daoMvtExceptionnel.doFindListMvtExceptionnel(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(MvtExceptionnelBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoMvtExceptionnel.doSaveMvtExceptionnel(insertBean)){
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
	public Boolean  doUpdateRowData(MvtExceptionnelBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoMvtExceptionnel.doUpdateMvtExceptionnel(updateBean)){
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
	public Boolean doDeleteRowData(MvtExceptionnelBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoMvtExceptionnel.doDeleteMvtExceptionnel(deleteBean)){
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
