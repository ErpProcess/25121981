package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.dao.ErrorDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.model.ErrorBean;

 


 



/**
 * Contact Service
 * 
 */
@Service
public class ErrorService {
	
	private ErrorDAO daoEroor;

	@Transactional
	public Boolean CreateRowData(ErrorBean eBean){
		if(daoEroor.saveError(eBean))
			return true;
		else
			return false;
			
	}
	@Transactional
	public Boolean UpdateRowData(ErrorBean ErrorBean){
		if(daoEroor.updateError(ErrorBean))
			return true;
		else
			return false;
			
	}
	
	
	@Transactional
	public Boolean DeleteRowData(ErrorBean ErrorBean){
		if(daoEroor.deleteError(ErrorBean))
			return true;
		else
			return false;
			
	}
	/**
	 * Get all packageSyss
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ErrorBean> getListDataServer(ErrorBean errBean){
		return daoEroor.getError(errBean);
	}
	
	 
	
	@Autowired
	public void setErrorDAO(ErrorDAO daoEroor) {
		this.daoEroor = daoEroor;
	}



	
}
