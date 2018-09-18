package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.dao.CompteBancaireDAO;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model.CompteBancaireBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.template.CompteBancaireTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class CompteBancaireService  extends GenericWeb  {
	private CompteBancaireDAO daoCompteBancaire;
	@Autowired
	public void setDaoCompteBancaire(CompteBancaireDAO daoCompteBancaire) {
		this.daoCompteBancaire = daoCompteBancaire;
	}
	@Transactional(readOnly=true)
	public List<CompteBancaireBean> doFetchDatafromServer(CompteBancaireBean beanSearch) throws Exception {
		return daoCompteBancaire.doFindListCompteBancaire(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(CompteBancaireBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoCompteBancaire.doSaveCompteBancaire(insertBean)){
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
	public Boolean  doUpdateRowData(CompteBancaireBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoCompteBancaire.doUpdateCompteBancaire(updateBean)){
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
	public Boolean doDeleteRowData(CompteBancaireBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoCompteBancaire.doDeleteCompteBancaire(deleteBean)){
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
