package ERP.Process.Commerciale.Vente.Rembourserclient.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Vente.ReglementFactClt.model.EcheanceRegCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.Process.Commerciale.Vente.Rembourserclient.dao.RembourserclientDAO;
import ERP.Process.Commerciale.Vente.Rembourserclient.template.RembourserclientTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class RembourserclientService  extends GenericWeb  {
	private RembourserclientDAO daoRembourserclient;
	@Autowired
	public void setDaoRembourserclient(RembourserclientDAO daoRembourserclient) {
		this.daoRembourserclient = daoRembourserclient;
	}
	@Transactional(readOnly=true)
	public List<ReglementFactCltBean> doFetchDatafromServer(ReglementFactCltBean beanSearch) throws Exception {
		return daoRembourserclient.doFindListReglementFactClt(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<EcheanceRegCltBean> doFetchDataEcheanceReglementfromServer(ReglementFactCltBean beanSearch) throws Exception {
		return daoRembourserclient.doFindListEcheanceReglement(beanSearch);
	}
	
	
	@Transactional
	public Boolean doCreateRowData(ReglementFactCltBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoRembourserclient.doSaveReglementFactClt(insertBean)){
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
	public Boolean  doUpdateRowData(ReglementFactCltBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoRembourserclient.doUpdateReglementFactClt(updateBean)){
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
	public Boolean doDeleteRowData(ReglementFactCltBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoRembourserclient.doDeleteReglementFactClt(deleteBean)){
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
