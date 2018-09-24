package ERP.Process.Commerciale.Vente.EditionVente.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.EditionVente.dao.EditionVenteDAO;
import ERP.Process.Commerciale.Vente.EditionVente.model.EditionVenteBean;
import ERP.Process.Commerciale.Vente.EditionVente.template.EditionVenteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class EditionVenteService  extends GenericWeb  {
	private EditionVenteDAO daoEditionVente;
	@Autowired
	public void setDaoEditionVente(EditionVenteDAO daoEditionVente) {
		this.daoEditionVente = daoEditionVente;
	}
	@Transactional(readOnly=true)
	public List<EditionVenteBean> doFetchDatafromServer(EditionVenteBean beanSearch) throws Exception {
		return daoEditionVente.doFindListEditionVente(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(EditionVenteBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoEditionVente.doSaveEditionVente(insertBean)){
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
	public Boolean  doUpdateRowData(EditionVenteBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoEditionVente.doUpdateEditionVente(updateBean)){
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
	public Boolean doDeleteRowData(EditionVenteBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoEditionVente.doDeleteEditionVente(deleteBean)){
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
