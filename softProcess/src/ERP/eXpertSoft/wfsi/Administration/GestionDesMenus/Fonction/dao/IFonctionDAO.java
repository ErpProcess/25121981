package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.dao;

import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;

 

public interface IFonctionDAO {

	List<FonctionBean> getFonctions();
	void deleteFonction(int id);
	Boolean saveFonction(FonctionBean moduleBean);
	
}
