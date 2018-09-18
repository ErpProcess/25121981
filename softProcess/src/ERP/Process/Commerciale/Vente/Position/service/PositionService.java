package ERP.Process.Commerciale.Vente.Position.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Vente.Position.dao.PositionDAO;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class PositionService  extends GenericWeb  {
	private PositionDAO daoPosition;
	@Autowired
	public void setDaoPosition(PositionDAO daoPosition) {
		this.daoPosition = daoPosition;
	}
	@Transactional(readOnly=true)
	public List<DetProcedureVenteBean> doFetchDatafromServer(ProcedureVenteBean beanSearch) throws Exception {
		return daoPosition.doFindListPosition(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(ProcedureVenteBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoPosition.doSavePosition(insertBean)){
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
	public Boolean  doUpdateRowData(ProcedureVenteBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoPosition.doUpdatePosition(updateBean)){
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
	public Boolean doDeleteRowData(ProcedureVenteBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoPosition.doDeletePosition(deleteBean)){
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
