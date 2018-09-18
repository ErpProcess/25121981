package ERP.Process.Commerciale.DetailCaracteristique.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.DetailCaracteristique.dao.DetailCaracteristiqueDAO;
import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
import ERP.Process.Commerciale.DetailCaracteristique.template.DetailCaracteristiqueTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class DetailCaracteristiqueService  extends GenericWeb  {
	private DetailCaracteristiqueDAO daoDetailCaracteristique;
	@Autowired
	public void setDaoDetailCaracteristique(DetailCaracteristiqueDAO daoDetailCaracteristique) {
		this.daoDetailCaracteristique = daoDetailCaracteristique;
	}
	@Transactional(readOnly=true)
	public List<DetailCaracteristiqueBean> doFetchDatafromServer(DetailCaracteristiqueBean beanSearch) throws Exception {
		return daoDetailCaracteristique.doFindListDetailCaracteristique(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(DetailCaracteristiqueBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoDetailCaracteristique.doSaveDetailCaracteristique(insertBean)){
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
	public Boolean  doUpdateRowData(DetailCaracteristiqueBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoDetailCaracteristique.doUpdateDetailCaracteristique(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(DetailCaracteristiqueTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(DetailCaracteristiqueTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(DetailCaracteristiqueBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoDetailCaracteristique.doDeleteDetailCaracteristique(deleteBean)){
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
