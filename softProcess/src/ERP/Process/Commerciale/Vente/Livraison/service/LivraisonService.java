package ERP.Process.Commerciale.Vente.Livraison.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.Livraison.dao.LivraisonDAO;
import ERP.Process.Commerciale.Vente.Livraison.model.LivraisonBean;
import ERP.Process.Commerciale.Vente.Livraison.template.LivraisonTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class LivraisonService  extends GenericWeb  {
	private LivraisonDAO daoLivraison;
	@Autowired
	public void setDaoLivraison(LivraisonDAO daoLivraison) {
		this.daoLivraison = daoLivraison;
	}
	@Transactional(readOnly=true)
	public List<LivraisonBean> doFetchDatafromServer(LivraisonBean beanSearch) throws Exception {
		return daoLivraison.doFindListLivraison(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(LivraisonBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoLivraison.doSaveLivraison(insertBean)){
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
	public Boolean  doUpdateRowData(LivraisonBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoLivraison.doUpdateLivraison(updateBean)){
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
	public Boolean doDeleteRowData(LivraisonBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoLivraison.doDeleteLivraison(deleteBean)){
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
