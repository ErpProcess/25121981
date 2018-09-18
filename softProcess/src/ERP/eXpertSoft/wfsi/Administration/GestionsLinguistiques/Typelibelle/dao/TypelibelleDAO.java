package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model.TypelibelleBean;

/**
 * Contact DAO class.
 * 
 */
@Repository
public   class TypelibelleDAO implements ITypelibelleDAO {

	private HibernateTemplate hibernateTemplate;
	
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<TypelibelleBean> getListTypelibelle(TypelibelleBean beanSearch) {
		
		   String requette=" from  TypelibelleBean bean where 1=1   ";
		
//		if(beanSearch.getLib_libelle()!=null && !beanSearch.getLib_libelle().equals("") ){
//			requette+="  AND  bean.lib_libelle  LIKE  '"+beanSearch.getLib_libelle()+"' ";
//		}
		
		
		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
 

	public Boolean saveTypelibelle(TypelibelleBean beanSv) {

		try {
			hibernateTemplate.save(beanSv);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateTypelibelle(TypelibelleBean beanUp) {

		try {
			hibernateTemplate.update(beanUp);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean deleteTypelibelle(TypelibelleBean beanDel) {

		try {
			hibernateTemplate.delete(beanDel);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}
