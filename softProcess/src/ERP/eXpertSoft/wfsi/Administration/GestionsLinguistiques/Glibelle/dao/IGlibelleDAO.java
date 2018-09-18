package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.dao;

import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;



 

 

 

public interface IGlibelleDAO {

	List<GlibelleBean> getListGlibelle(GlibelleBean beanlist);
	Boolean saveGlibelle(List listLibbelle);
	Boolean updateGlibelle(GlibelleBean beanUp);
	Boolean deleteGlibelle(GlibelleBean beanDel);
	Boolean saveGlibelleColunm(GlibelleBean bean);
	
}
