package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.dao.GLangueDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.util.GLangueTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;



/**
 * Contact Service
 * 
 */
@Service
public class GLangueService  extends GenericWeb {
	
	private GLangueDAO      daoGLangue;
	
	
	@Autowired
	public void setDaoGLangue(GLangueDAO daoGLangue) {
		this.daoGLangue = daoGLangue;
	}

	@Transactional
	public Boolean doCreateRowData(GLangueBean insertBean){
		 try {
		       if(daoGLangue.saveGLangue(insertBean)){
		      
		       return true;
		       }else{
		    	return false;  
		       }
		       
		 } catch (Exception e) { 
			 return false;
		}
	}
	
	
	
	@Transactional
	public Boolean doUpdateRowData(GLangueBean updateBean){
		if(daoGLangue.updateGLangue(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(GLangueTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(GLangueTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(GLangueBean deleteBean){
		if(daoGLangue.dodeleteGLangue(deleteBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			list.remove(Integer.parseInt(indexo));
			setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), list);
			return true;
		}else{
			return false;
		}
			
	}
	 
	@Transactional(readOnly=true)
	public List<GLangueBean> dofetchDatafromServer(GLangueBean beanSearch){
		return daoGLangue.doFindListGLangue(beanSearch);
	}
	
	 
	
	@Transactional(readOnly=true)
	public List<GLangueBean> dofetchALLDatafromServer(GLangueBean beanSearch){
		return daoGLangue.doFindALLListGLangue(beanSearch);
	}



	
}
