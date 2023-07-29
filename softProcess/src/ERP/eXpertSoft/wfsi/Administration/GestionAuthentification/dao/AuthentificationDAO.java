package ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.model.AuthentificationBean;


/**
 * Contact DAO class.
 * 
 */
@Repository
public   class AuthentificationDAO implements IAuthentificationDAO {

	private HibernateTemplate hibernateTemplate;
	
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	 
	 

	@SuppressWarnings("unchecked")
	public List<AuthentificationBean> getAuthentifications(AuthentificationBean authentificationBean) {
		
		   String requette=" from AuthentificationBean bean where 1=1 ";
		
	  
		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
 

	public Boolean saveAuthentification(AuthentificationBean authentificationBean) {

		try {
			hibernateTemplate.save(authentificationBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateAuthentification(AuthentificationBean authentificationBean) {

		try {
			hibernateTemplate.update(authentificationBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean deleteAuthentification(AuthentificationBean authentificationBean) {

		try {
			hibernateTemplate.delete(authentificationBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}
