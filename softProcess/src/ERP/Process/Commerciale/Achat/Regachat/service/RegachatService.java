package ERP.Process.Commerciale.Achat.Regachat.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Achat.Regachat.dao.RegachatDAO;
import ERP.Process.Commerciale.Achat.Regachat.model.EcheanceRegFrsBean;
import ERP.Process.Commerciale.Achat.Regachat.model.RegachatBean;
import ERP.Process.Commerciale.Achat.Regachat.template.RegachatTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class RegachatService  extends GenericWeb  {
	private RegachatDAO daoRegachat;
	@Autowired
	public void setDaoRegachat(RegachatDAO daoRegachat) {
		this.daoRegachat = daoRegachat;
	}
	@Transactional(readOnly=true)
	public List<RegachatBean> doFetchDatafromServer(RegachatBean beanSearch) throws Exception {
		return daoRegachat.doFindListRegachat(beanSearch);
	}
	@Transactional(readOnly=true)
	public List<EcheanceRegFrsBean> doFindListEcheanceReglement(RegachatBean beanSearch) throws Exception {
		return daoRegachat.doFindListEcheanceReglement(beanSearch);
	}
	
	
	@Transactional
	public Boolean doCreateRowData(RegachatBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoRegachat.doSaveRegachat(insertBean)){
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
		if(daoRegachat.doUpdateRegachat(updateBean)){
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
		    if(daoRegachat.doDeleteRegachat(deleteBean)){
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
