package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.dao;

import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model.TypelibelleBean;



 

 

 

public interface ITypelibelleDAO {

	List<TypelibelleBean> getListTypelibelle(TypelibelleBean beanlist);
	Boolean saveTypelibelle(TypelibelleBean beanSave);
	Boolean updateTypelibelle(TypelibelleBean beanUp);
	Boolean deleteTypelibelle(TypelibelleBean beanDel);
	
}
