package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.dao;

import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;



 

 

 

public interface ISpoorDAO {

	List<SpoorBean> getSpoor(SpoorBean beanDAO);
	String  findmaxinteger();
	Boolean saveSpoor(SpoorBean beanDAO);
	Boolean saveNewTrace(SpoorBean beanDAO);
	Boolean updateSpoor(SpoorBean beanDAO);
	Boolean deleteSpoor(SpoorBean beanDAO);
	
}
