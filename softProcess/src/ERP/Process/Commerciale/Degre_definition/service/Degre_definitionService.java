package ERP.Process.Commerciale.Degre_definition.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Degre_definition.dao.Degre_definitionDAO;
import ERP.Process.Commerciale.Degre_definition.model.Degre_definitionBean;
import ERP.Process.Commerciale.Degre_definition.template.Degre_definitionTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Degre_definitionService  extends GenericWeb  {
	private Degre_definitionDAO daoDegre_definition;
	@Autowired
	public void setDaoDegre_definition(Degre_definitionDAO daoDegre_definition) {
		this.daoDegre_definition = daoDegre_definition;
	}
	@Transactional(readOnly=true)
	public List<Degre_definitionBean> doFetchDatafromServer(Degre_definitionBean beanSearch) throws Exception {
		return daoDegre_definition.doFindListDegre_definition(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(Degre_definitionBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoDegre_definition.doSaveDegre_definition(insertBean)){
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
	public Boolean  doUpdateRowData(Degre_definitionBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoDegre_definition.doUpdateDegre_definition(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(Degre_definitionTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(Degre_definitionTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(Degre_definitionBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoDegre_definition.doDeleteDegre_definition(deleteBean)){
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
