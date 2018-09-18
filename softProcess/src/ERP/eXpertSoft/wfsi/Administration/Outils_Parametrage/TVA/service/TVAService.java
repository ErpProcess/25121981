package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.dao.TVADAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.template.TVATemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class TVAService  extends GenericWeb  {
	private TVADAO daoTVA;
	@Autowired
	public void setDaoTVA(TVADAO daoTVA) {
		this.daoTVA = daoTVA;
	}
	@Transactional(readOnly=true)
	public List<TVABean> doFetchDatafromServer(TVABean beanSearch) throws Exception {
		return daoTVA.doFindListTVA(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(TVABean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTVA.doSaveTVA(insertBean)){
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
	public Boolean  doUpdateRowData(TVABean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTVA.doUpdateTVA(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(TVATemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(TVATemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(TVABean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTVA.doDeleteTVA(deleteBean)){
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
