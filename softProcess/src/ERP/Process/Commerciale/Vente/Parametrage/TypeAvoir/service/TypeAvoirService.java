package ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.dao.TypeAvoirDAO;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model.TypeAvoirBean;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.template.TypeAvoirTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class TypeAvoirService  extends GenericWeb  {
	private TypeAvoirDAO daoTypeAvoir;
	@Autowired
	public void setDaoTypeAvoir(TypeAvoirDAO daoTypeAvoir) {
		this.daoTypeAvoir = daoTypeAvoir;
	}
	@Transactional(readOnly=true)
	public List<TypeAvoirBean> doFetchDatafromServer(TypeAvoirBean beanSearch) throws Exception {
		return daoTypeAvoir.doFindListTypeAvoir(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(TypeAvoirBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTypeAvoir.doSaveTypeAvoir(insertBean)){
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
	public Boolean  doUpdateRowData(TypeAvoirBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTypeAvoir.doUpdateTypeAvoir(updateBean)){
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
	public Boolean doDeleteRowData(TypeAvoirBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTypeAvoir.doDeleteTypeAvoir(deleteBean)){
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
