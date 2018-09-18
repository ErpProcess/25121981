package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao;

import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.NumSequentielBean;


 

public interface INumSequentielDAO {

	List<NumSequentielBean> doFindListNumSequentiel(NumSequentielBean beanSearch);
	Boolean saveNumSequentiel(NumSequentielBean beanSave);
	Boolean updateNumSequentiel(NumSequentielBean beanUpdate);
	Boolean dodeleteNumSequentiel(NumSequentielBean beanDelete);
	
	 void getNumSeqSimple(Object bean,String code_num_attribute ) throws Exception ;
 
	
}
