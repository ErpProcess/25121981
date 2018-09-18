package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.service;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.dao.WebRootFolderDAO;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.WebRootFolderTemplate;

 
 
@Service
public class WebRootFolderService  extends GenericWeb  {
	 
	private WebRootFolderDAO daoWebRootFolder;
	
	private NumSequentielDAO daoNumSequentiel;
	 
	
	
	@Autowired
	public void setDaoWebRootFolder(WebRootFolderDAO daoWebRootFolder) {
		this.daoWebRootFolder = daoWebRootFolder;
	}
	
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	

	
	 
	@Transactional
	public Boolean doCreateRowData(List  listForInsert) throws Exception{
		 
		 try {
		       if(daoWebRootFolder.saveWebRootFolder(listForInsert)){
		       return true;
		       }else{
		    	return false;  
		       }
		       
		 } catch (Exception e) { 
			throw e;
		}
	}
	
	
	 
	 
	@Transactional(readOnly=true)
	public List<WebRootFolderBean> getListDataServer(WebRootFolderBean beanSerach){
		return daoWebRootFolder.doFindListWebRootFolder(beanSerach);
	}
	
	
 
	

	 
	 
	
	
	
}
