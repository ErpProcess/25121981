package ERP.Process.Commerciale.Vente.Mvtcom.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.Mvtcom.dao.MvtcomDAO;
import ERP.Process.Commerciale.Vente.Mvtcom.model.MvtcomBean;
import ERP.Process.Commerciale.Vente.Mvtcom.template.MvtcomTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class MvtcomService  extends GenericWeb  {
	private MvtcomDAO daoMvtcom;
	@Autowired
	public void setDaoMvtcom(MvtcomDAO daoMvtcom) {
		this.daoMvtcom = daoMvtcom;
	}
	@Transactional(readOnly=true)
	public List<MvtcomBean> doFetchDatafromServer(MvtcomBean beanSearch) throws Exception {
		return daoMvtcom.doFindListMvtcom(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(MvtcomBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoMvtcom.doSaveMvtcom(insertBean)){
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
	public Boolean  doUpdateRowData(MvtcomBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoMvtcom.doUpdateMvtcom(updateBean)){
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
	public Boolean doDeleteRowData(MvtcomBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoMvtcom.doDeleteMvtcom(deleteBean)){
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
