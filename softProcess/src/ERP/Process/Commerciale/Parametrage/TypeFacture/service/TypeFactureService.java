package ERP.Process.Commerciale.Parametrage.TypeFacture.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Parametrage.TypeFacture.dao.TypeFactureDAO;
import ERP.Process.Commerciale.Parametrage.TypeFacture.model.TypeFactureBean;
import ERP.Process.Commerciale.Parametrage.TypeFacture.template.TypeFactureTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class TypeFactureService  extends GenericWeb  {
	private TypeFactureDAO daoTypeFacture;
	@Autowired
	public void setDaoTypeFacture(TypeFactureDAO daoTypeFacture) {
		this.daoTypeFacture = daoTypeFacture;
	}
	@Transactional(readOnly=true)
	public List<TypeFactureBean> doFetchDatafromServer(TypeFactureBean beanSearch) throws Exception {
		return daoTypeFacture.doFindListTypeFacture(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(TypeFactureBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTypeFacture.doSaveTypeFacture(insertBean)){
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
	public Boolean  doUpdateRowData(TypeFactureBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTypeFacture.doUpdateTypeFacture(updateBean)){
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
	public Boolean doDeleteRowData(TypeFactureBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTypeFacture.doDeleteTypeFacture(deleteBean)){
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
