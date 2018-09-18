package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.service;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.dao.SocieteDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.template.SocieteTemplate;

 
 
@Service
public class SocieteService  extends GenericWeb  {
	 
	private SocieteDAO daoSociete;
	
	private NumSequentielDAO daoNumSequentiel;
	 
	
	
	@Autowired
	public void setDaoSociete(SocieteDAO daoSociete) {
		this.daoSociete = daoSociete;
	}
	
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	@Transactional(readOnly=true)
	public List<SocieteBean> dofetchDatafromServer(SocieteBean beanSearch){
		return daoSociete.doFindListSociete(beanSearch);
	}
	
	 
	@Transactional
	public Boolean doCreateRowData(SocieteBean insertBean){
		 try {
			    daoNumSequentiel.getNumSeqSimple(insertBean, "soc_id");
		       if(daoSociete.saveSociete(insertBean)){
		       return true;
		       }else{
		    	return false;  
		       }
		       
		 } catch (Exception e) { 
			 return false;
		}
	}
	
	@Transactional
	public Boolean doUpdateRowData(SocieteBean updateBean){
		
		
		if(daoSociete.updateSociete(updateBean)){
		
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(SocieteBean deleteBean){
		if(daoSociete.dodeleteSociete(deleteBean)){
			return true;
		}else{
			return false;
		}
			
	}
 
	 
	 
	
 
	

	 
	 
	
	
	
}
