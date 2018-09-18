package ERP.Process.Commerciale.Vente.Commandeclient.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Vente.Commandeclient.dao.CommandeclientDAO;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.DetCmdCltBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class CommandeclientService  extends GenericWeb  {
	private CommandeclientDAO daoCommandeclient;
	@Autowired
	public void setDaoCommandeclient(CommandeclientDAO daoCommandeclient) {
		this.daoCommandeclient = daoCommandeclient;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	@Transactional(readOnly=true)
	public List<CommandeclientBean> doFetchDatafromServer(CommandeclientBean beanSearch) throws Exception {
		return daoCommandeclient.doFindListCommandeclient(beanSearch);
	}
	
	
	@Transactional(readOnly=true)
	public List<DetCmdCltBean> doFetchDetailleDatafromServer(CommandeclientBean beanSearch) throws Exception {
		return daoCommandeclient.doFindList_detaille_Commandeclient(beanSearch);
	}
	
	
	
	@Transactional
	public Boolean doCreateRowData(CommandeclientBean insertBean) throws Exception {
		 boolean result = false;
		 try {
			    daoNumSequentiel.getNumSeqSimple(insertBean,"cmd_id");
		       if(daoCommandeclient.doSaveData(insertBean)){ 
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
	public Boolean  doUpdateRowData(CommandeclientBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoCommandeclient.doUpdateBean(updateBean)){
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
	public Boolean  doExcuterRowData(CommandeclientBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoCommandeclient.doExcuterTransaction(updateBean)){
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
	public Boolean doDeleteRowData(CommandeclientBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoCommandeclient.doDeleteBean(deleteBean)){
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
