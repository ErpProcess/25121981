package ERP.Process.Commerciale.Vente.ReglementFactClt.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Vente.ReglementFactClt.dao.ReglementFactCltDAO;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.EcheanceRegCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.template.ReglementFactCltTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class ReglementFactCltService  extends GenericWeb  {
	private ReglementFactCltDAO daoReglementFactClt;
	@Autowired
	public void setDaoReglementFactClt(ReglementFactCltDAO daoReglementFactClt) {
		this.daoReglementFactClt = daoReglementFactClt;
	}
	@Transactional(readOnly=true)
	public List<ReglementFactCltBean> doFetchDatafromServer(ReglementFactCltBean beanSearch) throws Exception {
		return daoReglementFactClt.doFindListReglementFactClt(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<EcheanceRegCltBean> doFetchDataEcheanceReglementfromServer(ReglementFactCltBean beanSearch) throws Exception {
		return daoReglementFactClt.doFindListEcheanceReglement(beanSearch);
	}
	
	
	@Transactional
	public Boolean doCreateRowData(ReglementFactCltBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoReglementFactClt.doSaveReglementFactClt(insertBean)){
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
		if(daoReglementFactClt.doUpdateReglementFactClt(updateBean)){
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
		    if(daoReglementFactClt.doDeleteReglementFactClt(deleteBean)){
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
