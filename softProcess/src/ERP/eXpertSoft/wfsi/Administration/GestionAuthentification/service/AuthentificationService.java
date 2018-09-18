package ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.dao.AuthentificationDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.model.AuthentificationBean;



/**
 * Contact Service
 * 
 */
@Service
public class AuthentificationService {
	
	private AuthentificationDAO authentificationDAO;

	@Transactional
	public Boolean CreatePackSystem(AuthentificationBean authentificationBean){
		if(authentificationDAO.saveAuthentification(authentificationBean))
			return true;
		else
			return  false;
			
	}
	
	
	@Transactional
	public Boolean UpdatePackSystem(AuthentificationBean authentificationBean){
		if(authentificationDAO.updateAuthentification(authentificationBean))
			return true;
		else
			return false;
			
	}
	
	
	@Transactional
	public Boolean DeletePackSystem(AuthentificationBean authentificationBean){
		if(authentificationDAO.deleteAuthentification(authentificationBean))
			return true;
		else
			return false;
			
	}
	
	
	
	/**
	 * Get all authentificationBeans
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<AuthentificationBean> getAuthentificationList(AuthentificationBean authentificationBean){
		return authentificationDAO.getAuthentifications(authentificationBean);
	}
	
	 
	
	@Autowired
	public void setAuthentificationDAO(AuthentificationDAO authentificationDAO) {
		this.authentificationDAO = authentificationDAO;
	}



	
}
