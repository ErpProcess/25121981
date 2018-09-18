package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.service;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.dao.UtilisateurDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.template.UtilisateurTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;

 
 
@Service
public class UtilisateurService  extends GenericWeb  {
	 
	private UtilisateurDAO daoUtilisateur;
	@Autowired
	public void setDaoUtilisateur(UtilisateurDAO daoUtilisateur) {
		this.daoUtilisateur = daoUtilisateur;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	
	 
	@Transactional
	public Boolean doCreateRowData(UtilisateurBean insertBean){
		 try {
			    daoNumSequentiel.getNumSeqSimple(insertBean,"usr_id");
		       if(daoUtilisateur.saveUtilisateur(insertBean)){
		       return true;
		       }else{
		    	return false;  
		       }
		       
		 } catch (Exception e) { 
			 return false;
		}
	}
	
	
	@Transactional(readOnly=true)
	public List<UtilisateurBean> dofetchDatafromServer(UtilisateurBean beanSearch){
		return daoUtilisateur.doFindListUtilisateur(beanSearch);
	}
	
	@Transactional
	public Boolean doUpdateRowData(UtilisateurBean updateBean) throws Exception, Exception{
		if(daoUtilisateur.updateUtilisateur(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(UtilisateurTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(Integer.parseInt(indexo),updateBean);
			setObjectValueModel(UtilisateurTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(UtilisateurBean deleteBean){
		if(daoUtilisateur.dodeleteUtilisateur(deleteBean)){
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
