package ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.dao.NatureMvtCommercialeDAO;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.template.NatureMvtCommercialeTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class NatureMvtCommercialeService  extends GenericWeb  {
	private NatureMvtCommercialeDAO daoNatureMvtCommerciale;
	@Autowired
	public void setDaoNatureMvtCommerciale(NatureMvtCommercialeDAO daoNatureMvtCommerciale) {
		this.daoNatureMvtCommerciale = daoNatureMvtCommerciale;
	}
	@Transactional(readOnly=true)
	public List<NatureMvtCommercialeBean> doFetchDatafromServer(NatureMvtCommercialeBean beanSearch) throws Exception {
		return daoNatureMvtCommerciale.doFindListNatureMvtCommerciale(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(NatureMvtCommercialeBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoNatureMvtCommerciale.doSaveNatureMvtCommerciale(insertBean)){
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
	public Boolean  doUpdateRowData(NatureMvtCommercialeBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoNatureMvtCommerciale.doUpdateNatureMvtCommerciale(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(NatureMvtCommercialeTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(NatureMvtCommercialeTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(NatureMvtCommercialeBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoNatureMvtCommerciale.doDeleteNatureMvtCommerciale(deleteBean)){
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
