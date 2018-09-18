package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.service;

 
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.dao.PaysvilleposteDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model.PaysvilleposteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.template.PaysvilleposteTemplate;

 
 
@Service
public class PaysvilleposteService  extends GenericWeb  {
	 
	private PaysvilleposteDAO daoPaysvilleposte;
	 
	@Transactional(readOnly=true)
	public List<PaysvilleposteBean> dofetchDatafromServer(PaysvilleposteBean beanSearch){
		return daoPaysvilleposte.doFindListPaysvilleposte(beanSearch);
	}
	
	 
	
	 
	@Transactional
	public Boolean doCreateRowData(PaysvilleposteBean insertBean){
		 try {
		       if(daoPaysvilleposte.savePaysvilleposte(insertBean)){
		       return true;
		       }else{
		    	return false;  
		       }
		       
		 } catch (Exception e) { 
			 return false;
		}
	}
	
	 
	
	@Transactional
	public Boolean doUpdateRowData(PaysvilleposteBean updateBean){
		if(daoPaysvilleposte.updatePaysvilleposte(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(PaysvilleposteTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(PaysvilleposteTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(PaysvilleposteBean deleteBean){
		if(daoPaysvilleposte.dodeletePaysvilleposte(deleteBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			list.remove(Integer.parseInt(indexo));
			setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), list);
			return true;
		}else{
			return false;
		}
			
	}
 
	 
	 
	
 
	@Autowired
	public void setdaoPaysvilleposte(PaysvilleposteDAO daoPaysvilleposte) {
		this.daoPaysvilleposte = daoPaysvilleposte;
	}

	 
	 
	
	
	
}
