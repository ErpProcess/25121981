package ERP.Process.Commerciale.Demande_Achat.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Demande_Achat.dao.Demande_AchatDAO;
import ERP.Process.Commerciale.Demande_Achat.model.Demande_achatBean;
import ERP.Process.Commerciale.Demande_Achat.model.Det_demande_AchatBean;
import ERP.Process.Commerciale.Demande_Achat.template.Demande_AchatTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class Demande_AchatService  extends GenericWeb  {
	
	
	
	private Demande_AchatDAO daoDemande_Achat;
	@Autowired
	public void setDaoDemande_Achat(Demande_AchatDAO daoDemande_Achat) {
		this.daoDemande_Achat = daoDemande_Achat;
	}
	
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	
	@Transactional(readOnly=true)
	public List<Demande_achatBean> dofetchDatafromServer(Demande_achatBean beanSearch) throws Exception {
		return daoDemande_Achat.doFindListDemande_Achat(beanSearch);
	}
	
	
	@Transactional(readOnly=true)
	public List<Det_demande_AchatBean> dofetch_detaille_demande_achat(Demande_achatBean beanSearch) throws Exception {
		return daoDemande_Achat.doFindList_detaille_Demande_Achat(beanSearch);
	}
	
	
	
	@Transactional
	public Boolean doCreateRowData(Demande_achatBean insertBean) throws Exception {
		 boolean result = false;
		 try {
			   daoNumSequentiel.getNumSeqSimple(insertBean,"dem_achat_id");
		       if(daoDemande_Achat.saveDemande_Achat(insertBean)){
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
	public Boolean doUpdateRowData(Demande_achatBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoDemande_Achat.updateDemande_Achat(updateBean)){
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
	public Boolean doValiderDemande_Achat(Demande_achatBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoDemande_Achat.ValiderDemande_Achat(updateBean)){
			
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
	public Boolean doDeleteRowData(Demande_achatBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoDemande_Achat.dodeleteDemande_Achat(deleteBean)){
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
