package ERP.Process.Commerciale.Vente.FournitureVente.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.FournitureVente.dao.FournitureVenteDAO;
import ERP.Process.Commerciale.Vente.FournitureVente.model.DetFournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.model.FournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.template.FournitureVenteTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class FournitureVenteService  extends GenericWeb  {
	private FournitureVenteDAO daoFournitureVente;
	@Autowired
	public void setDaoFournitureVente(FournitureVenteDAO daoFournitureVente) {
		this.daoFournitureVente = daoFournitureVente;
	}
	@Transactional(readOnly=true)
	public List<FournitureVenteBean> doFetchDatafromServer(FournitureVenteBean beanSearch) throws Exception {
		return daoFournitureVente.doFindListFournitureVente(beanSearch);
	}
	
	
	 
	
	@Transactional(readOnly=true)
	public List<DetFournitureVenteBean> doFetchDetailFourniturefromServer(FournitureVenteBean beanSearch) throws Exception {
		return daoFournitureVente.doFindDetailFourniturefromServer(beanSearch);
	}
	@Transactional(readOnly=true)
	public List<DetFournitureVenteBean> doFindDetailFournitureEdition(ProcedureVenteBean beanSearch) throws Exception {
		return daoFournitureVente.doFindDetailFournitureEdition(beanSearch);
	}
	
	
	
	@Transactional
	public Boolean doCreateRowData(FournitureVenteBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoFournitureVente.doSaveFournitureVente(insertBean)){
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
	public Boolean  doUpdateRowData(FournitureVenteBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoFournitureVente.doUpdateFournitureVente(updateBean)){
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
	public Boolean doDeleteRowData(FournitureVenteBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoFournitureVente.doDeleteFournitureVente(deleteBean)){
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
