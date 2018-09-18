package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.dao.DeviseDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.template.DeviseTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class DeviseService  extends GenericWeb  {
	private DeviseDAO daoDevise;
	@Autowired
	public void setDaoDevise(DeviseDAO daoDevise) {
		this.daoDevise = daoDevise;
	}
	@Transactional(readOnly=true)
	public List<DeviseBean> doFetchDatafromServer(DeviseBean beanSearch) throws Exception {
		return daoDevise.doFindListDevise(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(DeviseBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoDevise.doSaveDevise(insertBean)){
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
	public Boolean  doUpdateRowData(DeviseBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoDevise.doUpdateDevise(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(DeviseTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(DeviseTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(DeviseBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoDevise.doDeleteDevise(deleteBean)){
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
