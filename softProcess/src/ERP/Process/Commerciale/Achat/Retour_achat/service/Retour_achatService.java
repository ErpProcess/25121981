package ERP.Process.Commerciale.Achat.Retour_achat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Achat.Retour_achat.dao.Retour_achatDAO;
import ERP.Process.Commerciale.Achat.Retour_achat.model.Retour_achatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class Retour_achatService  extends GenericWeb  {
	private Retour_achatDAO daoRetour_achat;
	@Autowired
	public void setDaoRetour_achat(Retour_achatDAO daoRetour_achat) {
		this.daoRetour_achat = daoRetour_achat;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	@Transactional(readOnly=true)
	public List<Retour_achatBean> doFetchDatafromServer(Retour_achatBean beanSearch) throws Exception {
		return daoRetour_achat.doFindListRetour_achat(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(Retour_achatBean insertBean) throws Exception {
		 boolean result = false;
		 try {
			    daoNumSequentiel.getNumSeqSimple(insertBean,"retour_id");
		       if(daoRetour_achat.doSaveRetour_achat(insertBean)){
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
	public Boolean doUpdateActionRowData( ) throws Exception {
		 boolean result = false;
		 try {
		    if(daoRetour_achat.doExcuteemethode_achat()){
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
	public Boolean doAppliquerRowData( ) throws Exception {
		 boolean result = false;
		 try {
		    //if( daoRetour_achat.doAppliquer_Retour_achat()){
		        result = true;
		    
		 } catch (Exception e) { 
			 result = false;
			 throw e;
		}
   return result; 
	} 
	
	
	
	
	@Transactional
	public Boolean  doUpdateRowData(Retour_achatBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoRetour_achat.doUpdateRetour_achat(updateBean)){
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
	public Boolean doDeleteRowData(Retour_achatBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoRetour_achat.doDeleteRetour_achat(deleteBean)){
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
