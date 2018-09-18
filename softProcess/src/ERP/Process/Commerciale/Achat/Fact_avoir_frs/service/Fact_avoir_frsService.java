package ERP.Process.Commerciale.Achat.Fact_avoir_frs.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.dao.Fact_avoir_frsDAO;
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.model.Fact_avoir_frsBean;
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.template.Fact_avoir_frsTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Fact_avoir_frsService  extends GenericWeb  {
	private Fact_avoir_frsDAO daoFact_avoir_frs;
	@Autowired
	public void setDaoFact_avoir_frs(Fact_avoir_frsDAO daoFact_avoir_frs) {
		this.daoFact_avoir_frs = daoFact_avoir_frs;
	}
	@Transactional(readOnly=true)
	public List<Fact_avoir_frsBean> doFetchDatafromServer(Fact_avoir_frsBean beanSearch) throws Exception {
		return daoFact_avoir_frs.doFindListFact_avoir_frs(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(Fact_avoir_frsBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoFact_avoir_frs.doSaveFact_avoir_frs(insertBean)){
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
	public Boolean  doUpdateRowData(Fact_avoir_frsBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoFact_avoir_frs.doUpdateFact_avoir_frs(updateBean)){
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
	public Boolean doDeleteRowData(Fact_avoir_frsBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoFact_avoir_frs.doDeleteFact_avoir_frs(deleteBean)){
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
