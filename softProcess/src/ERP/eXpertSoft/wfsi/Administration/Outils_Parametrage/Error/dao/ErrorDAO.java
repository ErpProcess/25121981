package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.model.ErrorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

 

/**
 * Contact DAO class.
 * 
 */
@Repository
public   class ErrorDAO  extends  GenericWeb   {

	private HibernateTemplate hibernateTemplate;
	
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<ErrorBean> getError(ErrorBean eBean) {
		
		   String requette=" from ErrorBean bean  where 1=1  ";
		
//		if(ErrorBean.getLang_libelle()!=null && !ErrorBean.getLang_libelle().equals("") ){
//			requette+="  AND  bean.pack_libelle  LIKE  '"+ErrorBean.getLang_libelle()+"' ";
//		}
		
		
		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
 

	public Boolean saveError(ErrorBean eBean) {

		try {
			hibernateTemplate.save(eBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateError(ErrorBean eBean) {

		try {
			hibernateTemplate.update(eBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean deleteError(ErrorBean eBean) {

		try {
			hibernateTemplate.delete(eBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}
