package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.service;

 
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.NumSequentielBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.template.NumSequentielTemplate;

 
 
@Service
public class NumSequentielService  extends GenericWeb  {
	 
	public NumSequentielDAO daoNumSequentiel;
	
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	 
	@Transactional(readOnly=true)
	public List<NumSequentielBean> dofetchDatafromServer(NumSequentielBean beanSearch){

		return daoNumSequentiel.doFindListNumSequentiel(beanSearch);
	}
	
	 
	@Transactional
	public Boolean doCreateRowData(NumSequentielBean insertBean){
		 try {
		       if(daoNumSequentiel.saveNumSequentiel(insertBean)){
		       return true;
		       }else{
		    	return false;  
		       }
		       
		 } catch (Exception e) { 
			 return false;
		}
	}
	
	 
	
	@Transactional
	public Boolean doUpdateRowData(NumSequentielBean updateBean){
		if(daoNumSequentiel.updateNumSequentiel(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(NumSequentielTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(NumSequentielTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(NumSequentielBean deleteBean){
		if(daoNumSequentiel.dodeleteNumSequentiel(deleteBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			list.remove(Integer.parseInt(indexo));
			setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), list);
			return true;
		}else{
			return false;
		}
			
	}
 
	 
	 
	
 


	 
	 
	
	
	
}
