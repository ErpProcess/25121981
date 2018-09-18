package ERP.Process.Commerciale.TypeFamille.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.TypeFamille.dao.TypeFamilleDAO;
import ERP.Process.Commerciale.TypeFamille.model.TypeFamilleBean;
import ERP.Process.Commerciale.TypeFamille.template.TypeFamilleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class TypeFamilleService  extends GenericWeb  {
	private TypeFamilleDAO daoTypeFamille;
	@Autowired
	public void setDaoTypeFamille(TypeFamilleDAO daoTypeFamille) {
		this.daoTypeFamille = daoTypeFamille;
	}
	@Transactional(readOnly=true)
	public List<TypeFamilleBean> doFetchDatafromServer(TypeFamilleBean beanSearch) throws Exception {
		return daoTypeFamille.doFindListTypeFamille(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(TypeFamilleBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTypeFamille.doSaveTypeFamille(insertBean)){
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
	public Boolean  doUpdateRowData(TypeFamilleBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTypeFamille.doUpdateTypeFamille(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(TypeFamilleTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(TypeFamilleTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(TypeFamilleBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTypeFamille.doDeleteTypeFamille(deleteBean)){
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
