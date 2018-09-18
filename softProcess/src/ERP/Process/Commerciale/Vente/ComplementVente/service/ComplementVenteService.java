package ERP.Process.Commerciale.Vente.ComplementVente.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.ComplementVente.dao.ComplementVenteDAO;
import ERP.Process.Commerciale.Vente.ComplementVente.model.ComplementVenteBean;
import ERP.Process.Commerciale.Vente.ComplementVente.model.DetComplementBean;
import ERP.Process.Commerciale.Vente.ComplementVente.template.ComplementVenteTemplate;
import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class ComplementVenteService  extends GenericWeb  {
	private ComplementVenteDAO daoComplementVente;
	@Autowired
	public void setDaoComplementVente(ComplementVenteDAO daoComplementVente) {
		this.daoComplementVente = daoComplementVente;
	}
	@Transactional(readOnly=true)
	public List<ComplementVenteBean> doFetchDatafromServer(ComplementVenteBean beanSearch) throws Exception {
		return daoComplementVente.doFindListComplementVente(beanSearch);
	}
	@Transactional(readOnly=true)
	public List<DetComplementBean> doFindList_detaille_ComplementVente(ComplementVenteBean beanSearch) throws Exception {
		return daoComplementVente.doFindList_detaille_ComplementVente(beanSearch);
	}
	
	
	
	@Transactional
	public Boolean doCreateRowData(ComplementVenteBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoComplementVente.doSaveComplmentVente(insertBean)){
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
	public Boolean  doConfirmStockRowData(ComplementVenteBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoComplementVente.doConfirmerComplementVente(updateBean)){
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
	public Boolean  doUpdateRowData(ComplementVenteBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoComplementVente.doUpdateComplementVente(updateBean)){
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
	public Boolean doDeleteRowData(ComplementVenteBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoComplementVente.doDeleteComplmentVente(deleteBean)){
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
