package ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.dao.Nature_incident_mvt_retourDAO;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Source_incidentBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.template.Nature_incident_mvt_retourTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Nature_incident_mvt_retourService  extends GenericWeb  {
	private Nature_incident_mvt_retourDAO daoNature_incident_mvt_retour;
	@Autowired
	public void setDaoNature_incident_mvt_retour(Nature_incident_mvt_retourDAO daoNature_incident_mvt_retour) {
		this.daoNature_incident_mvt_retour = daoNature_incident_mvt_retour;
	}
	@Transactional(readOnly=true)
	public List<Nature_incident_mvt_retourBean> doFetchDatafromServer(Nature_incident_mvt_retourBean beanSearch) throws Exception {
		return daoNature_incident_mvt_retour.doFindListNature_incident_mvt_retour(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<Source_incidentBean> doFindListSourceIncident(Source_incidentBean beanSearch) throws Exception {
		return daoNature_incident_mvt_retour.doFindListSourceIncident(beanSearch);
	}
	
	
	@Transactional
	public Boolean doCreateRowData(Nature_incident_mvt_retourBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoNature_incident_mvt_retour.doSaveNature_incident_mvt_retour(insertBean)){
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
	public Boolean  doUpdateRowData(Nature_incident_mvt_retourBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoNature_incident_mvt_retour.doUpdateNature_incident_mvt_retour(updateBean)){
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
	public Boolean doDeleteRowData(Nature_incident_mvt_retourBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoNature_incident_mvt_retour.doDeleteNature_incident_mvt_retour(deleteBean)){
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
