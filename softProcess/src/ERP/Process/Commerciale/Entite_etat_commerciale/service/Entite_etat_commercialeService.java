package ERP.Process.Commerciale.Entite_etat_commerciale.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Entite_etat_commerciale.dao.Entite_etat_commercialeDAO;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.template.Entite_etat_commercialeTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Entite_etat_commercialeService  extends GenericWeb  {
	private Entite_etat_commercialeDAO daoEntite_etat_commerciale;
	@Autowired
	public void setDaoEntite_etat_commerciale(Entite_etat_commercialeDAO daoEntite_etat_commerciale) {
		this.daoEntite_etat_commerciale = daoEntite_etat_commerciale;
	}
	@Transactional(readOnly=true)
	public List<Entite_etat_commercialeBean> dofetchDatafromServer(Entite_etat_commercialeBean beanSearch) throws Exception {
		return daoEntite_etat_commerciale.doFindListEntite_etat_commerciale(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(Entite_etat_commercialeBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoEntite_etat_commerciale.doSaveEntite_etat_commerciale(insertBean)){
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
	public Boolean  doUpdateRowData(Entite_etat_commercialeBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoEntite_etat_commerciale.doUpdateEntite_etat_commerciale(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(Entite_etat_commercialeTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(Entite_etat_commercialeTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(Entite_etat_commercialeBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoEntite_etat_commerciale.doDeleteEntite_etat_commerciale(deleteBean)){
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
