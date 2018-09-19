package ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.dao.ModeReglementDAO;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.template.ModeReglementTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class ModeReglementService  extends GenericWeb  {
	private ModeReglementDAO daoModeReglement;
	@Autowired
	public void setDaoModeReglement(ModeReglementDAO daoModeReglement) {
		this.daoModeReglement = daoModeReglement;
	}
	@Transactional(readOnly=true)
	public List<ModeReglementBean> doFetchDatafromServer(ModeReglementBean beanSearch) throws Exception {
		return daoModeReglement.doFindListModeReglement(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(ModeReglementBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoModeReglement.doSaveModeReglement(insertBean)){
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
	public Boolean  doUpdateRowData(ModeReglementBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoModeReglement.doUpdateModeReglement(updateBean)){
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
	public Boolean doDeleteRowData(ModeReglementBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoModeReglement.doDeleteModeReglement(deleteBean)){
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
