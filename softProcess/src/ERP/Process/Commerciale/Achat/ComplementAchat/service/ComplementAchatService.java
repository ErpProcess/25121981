
package ERP.Process.Commerciale.Achat.ComplementAchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Achat.ComplementAchat.dao.ComplementAchatDAO;
import ERP.Process.Commerciale.Achat.ComplementAchat.model.ComplementAchatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;

@Service
public class ComplementAchatService  extends GenericWeb  {
	
	
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	private ComplementAchatDAO daoComplementAchat;
	@Autowired
	public void setDaoComplementAchat(ComplementAchatDAO daoComplementAchat) {
		this.daoComplementAchat = daoComplementAchat;
	}
	@Transactional(readOnly=true)
	public List<ComplementAchatBean> doFetchDatafromServer(ComplementAchatBean beanSearch) throws Exception {
		return daoComplementAchat.doFindListComplementAchat(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(ComplementAchatBean insertBean) throws Exception {
		 boolean result = false;
		 try {
			 
			    daoNumSequentiel.getNumSeqSimple(insertBean,"complet_id");
		       if(daoComplementAchat.doSaveComplementAchat(insertBean)){ 
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
	public Boolean  doUpdateRowData(ComplementAchatBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoComplementAchat.doUpdateDataInserver(updateBean)){
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
	public Boolean doDeleteRowData(ComplementAchatBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoComplementAchat.doDeleteDataFromServer(deleteBean)){
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
		    if(daoComplementAchat.doExcuteemethode_achat()){
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
	public Boolean doConservData( ) throws Exception {
		 boolean result = false;
		 try {
		    if(daoComplementAchat.doConservData()){
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
