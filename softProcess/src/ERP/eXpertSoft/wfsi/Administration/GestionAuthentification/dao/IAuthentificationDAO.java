package ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.dao;

import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.model.AuthentificationBean;


 

 

 

public interface IAuthentificationDAO {

	List<AuthentificationBean>  getAuthentifications(AuthentificationBean authentificationBean);
	Boolean saveAuthentification(AuthentificationBean authentificationBean);
	Boolean updateAuthentification(AuthentificationBean authentificationBean);
	Boolean deleteAuthentification(AuthentificationBean authentificationBean);
	
}
