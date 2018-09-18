package ERP.Process.Commerciale.Type_tarification.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Type_tarification.dao.Type_tarificationDAO;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Type_tarification.template.Type_tarificationTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Type_tarificationService  extends GenericWeb  {
	private Type_tarificationDAO daoType_tarification;
	@Autowired
	public void setDaoType_tarification(Type_tarificationDAO daoType_tarification) {
		this.daoType_tarification = daoType_tarification;
	}
	@Transactional(readOnly=true)
	public List<Type_tarificationBean> doFetchDatafromServer(Type_tarificationBean beanSearch) throws Exception {
		return daoType_tarification.doFindListType_tarification(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(Type_tarificationBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoType_tarification.doSaveType_tarification(insertBean)){
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
	public Boolean  doUpdateRowData(Type_tarificationBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoType_tarification.doUpdateType_tarification(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(Type_tarificationTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(Type_tarificationTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(Type_tarificationBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoType_tarification.doDeleteType_tarification(deleteBean)){
			    String indexo = (String) getObjectValueModel(INDEX_ROW);
			    List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			    list.remove(Integer.parseInt(indexo));
			    setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), list);
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
