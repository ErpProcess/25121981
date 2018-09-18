package ERP.Process.Commerciale.Vente.Parametrage.caisse.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.Parametrage.caisse.dao.caisseDAO;
import ERP.Process.Commerciale.Vente.Parametrage.caisse.model.CaisseBean;
import ERP.Process.Commerciale.Vente.Parametrage.caisse.template.caisseTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class caisseService  extends GenericWeb  {
	private caisseDAO daocaisse;
	@Autowired
	public void setDaocaisse(caisseDAO daocaisse) {
		this.daocaisse = daocaisse;
	}
	@Transactional(readOnly=true)
	public List<CaisseBean> doFetchDatafromServer(CaisseBean beanSearch) throws Exception {
		return daocaisse.doFindListcaisse(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(CaisseBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daocaisse.doSavecaisse(insertBean)){
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
	public Boolean  doUpdateRowData(CaisseBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daocaisse.doUpdatecaisse(updateBean)){
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
	public Boolean doDeleteRowData(CaisseBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daocaisse.doDeletecaisse(deleteBean)){
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
