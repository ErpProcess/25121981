package ERP.Process.Commerciale.Vente.Devis.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Vente.Devis.dao.DevisDAO;
import ERP.Process.Commerciale.Vente.Devis.model.DevisBean;
import ERP.Process.Commerciale.Vente.Devis.template.DevisTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

@Service
public class DevisService  extends GenericWeb  {
	private DevisDAO daoDevis;
	@Autowired
	public void setDaoDevis(DevisDAO daoDevis) {
		this.daoDevis = daoDevis;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	@Transactional(readOnly=true)
	public List<DevisBean> doFetchDatafromServer(DevisBean beanSearch) throws Exception {
		return daoDevis.doFindListDevis(beanSearch);
	}
	
	
	
	
	@Transactional
	public Boolean doCreateRowData(DevisBean insertBean) throws Exception {
		 boolean result = false;
		 try {
			    daoNumSequentiel.getNumSeqSimple(insertBean,"devis_id");
		       if(daoDevis.doSaveDevis(insertBean)){
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
	public Boolean  doUpdateRowData(DevisBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoDevis.doUpdateDevis(updateBean)){
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
	public Boolean doDeleteRowData(DevisBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoDevis.doDeleteDevis(deleteBean)){
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
	public Boolean  doExcuterRowData(DevisBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		 daoDevis.doExcuterTransactionForAchat(updateBean); 
		 } catch (Exception e) { 
			 result = false;
			 throw e;
		}
	 return result; 
	}
	 
	 
}
