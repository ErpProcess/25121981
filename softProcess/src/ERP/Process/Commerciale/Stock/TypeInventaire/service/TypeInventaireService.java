package ERP.Process.Commerciale.Stock.TypeInventaire.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.TypeInventaire.dao.TypeInventaireDAO;
import ERP.Process.Commerciale.Stock.TypeInventaire.model.TypeInventaireBean;
import ERP.Process.Commerciale.Stock.TypeInventaire.template.TypeInventaireTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class TypeInventaireService  extends GenericWeb  {
	
	
	private TypeInventaireDAO daoTypeInventaire;
	@Autowired
	public void setDaoTypeInventaire(TypeInventaireDAO daoTypeInventaire) {
		this.daoTypeInventaire = daoTypeInventaire;
	}
	@Transactional(readOnly=true)
	public List<TypeInventaireBean> doFetchDatafromServer(TypeInventaireBean beanSearch) throws Exception {
		return daoTypeInventaire.doFindListTypeInventaire(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(TypeInventaireBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTypeInventaire.doSaveTypeInventaire(insertBean)){
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
	public Boolean  doUpdateRowData(TypeInventaireBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTypeInventaire.doUpdateTypeInventaire(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(TypeInventaireTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(TypeInventaireTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(TypeInventaireBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTypeInventaire.doDeleteTypeInventaire(deleteBean)){
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
