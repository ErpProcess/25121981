package ERP.Process.Commerciale.Vente.Facture_comp_client.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.Facture_comp_client.dao.Facture_comp_clientDAO;
import ERP.Process.Commerciale.Vente.Facture_comp_client.model.Facture_comp_clientBean;
import ERP.Process.Commerciale.Vente.Facture_comp_client.template.Facture_comp_clientTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Facture_comp_clientService  extends GenericWeb  {
	private Facture_comp_clientDAO daoFacture_comp_client;
	@Autowired
	public void setDaoFacture_comp_client(Facture_comp_clientDAO daoFacture_comp_client) {
		this.daoFacture_comp_client = daoFacture_comp_client;
	}
	@Transactional(readOnly=true)
	public List<Facture_comp_clientBean> doFetchDatafromServer(Facture_comp_clientBean beanSearch) throws Exception {
		return daoFacture_comp_client.doFindListFacture_comp_client(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(Facture_comp_clientBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoFacture_comp_client.doSaveFacture_comp_client(insertBean)){
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
	public Boolean  doUpdateRowData(Facture_comp_clientBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoFacture_comp_client.doUpdateFacture_comp_client(updateBean)){
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
	public Boolean doDeleteRowData(Facture_comp_clientBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoFacture_comp_client.doDeleteFacture_comp_client(deleteBean)){
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
