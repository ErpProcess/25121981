package ERP.Process.Commerciale.AchatDivers.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.AchatDivers.dao.AchatDiversDAO;
import ERP.Process.Commerciale.AchatDivers.model.AchatDiversBean;
import ERP.Process.Commerciale.AchatDivers.template.AchatDiversTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class AchatDiversService  extends GenericWeb  {
	private AchatDiversDAO daoAchatDivers;
	@Autowired
	public void setDaoAchatDivers(AchatDiversDAO daoAchatDivers) {
		this.daoAchatDivers = daoAchatDivers;
	}
	@Transactional(readOnly=true)
	public List<AchatDiversBean> doFetchDatafromServer(AchatDiversBean beanSearch) throws Exception {
		return daoAchatDivers.doFindListAchatDivers(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(AchatDiversBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoAchatDivers.doSaveAchatDivers(insertBean)){
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
	public Boolean  doUpdateRowData(AchatDiversBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoAchatDivers.doUpdateAchatDivers(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(AchatDiversTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(AchatDiversTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(AchatDiversBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoAchatDivers.doDeleteAchatDivers(deleteBean)){
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
