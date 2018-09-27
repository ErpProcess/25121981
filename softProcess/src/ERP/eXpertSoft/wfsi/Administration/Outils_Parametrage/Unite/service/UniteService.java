package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.dao.UniteDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.DetDeriverUnite;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.template.UniteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class UniteService  extends GenericWeb  {
	private UniteDAO daoUnite;
	@Autowired
	public void setDaoUnite(UniteDAO daoUnite) {
		this.daoUnite = daoUnite;
	}
	@Transactional(readOnly=true)
	public List<UniteBean> doFetchDatafromServer(UniteBean beanSearch) throws Exception {
		return daoUnite.doFindListUnite(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<DetDeriverUnite> doFetchDetDeriverUniteByDrvId(Integer drv_id) throws Exception {
		return daoUnite.doFetchDetDeriverUniteByDrvId(drv_id);
	}
	
	
	
	@Transactional
	public Boolean doCreateRowData(UniteBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoUnite.doSaveUnite(insertBean)){
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
	public Boolean  doUpdateRowData(UniteBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoUnite.doUpdateUnite(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(UniteTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(UniteTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(UniteBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoUnite.doDeleteUnite(deleteBean)){
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
