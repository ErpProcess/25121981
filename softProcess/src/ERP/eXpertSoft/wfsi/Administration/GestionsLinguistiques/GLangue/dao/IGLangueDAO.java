package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.dao;

import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;



 

 

 

public interface IGLangueDAO {

	List<GLangueBean> doFindListGLangue(GLangueBean gLangueBean);
	Boolean saveGLangue(GLangueBean gLangueBean);
	Boolean updateGLangue(GLangueBean gLangueBean);
	Boolean dodeleteGLangue(GLangueBean gLangueBean);
	
}
