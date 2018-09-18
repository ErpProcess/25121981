package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.template.FonctionTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;


/**
 * Contact DAO class.
 * 
 */


@Repository
public class FonctionDAO  extends  GenericWeb{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<FonctionBean> getFonctions(FonctionBean searchBean ) {
		
		try
        {
			
			String  requette="    from FonctionBean uta     where 1=1      ";
			
			
            return hibernateTemplate.find(requette);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
	}

	 
	public Boolean saveFonction(FonctionBean beanSave) {

		try {
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			this.saveTrace(beanSave);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateFonction(FonctionBean beanUpdate) {

		try {
			this.setIdBean((FonctionBean) getObjectValueModel(FORM_BEAN), beanUpdate, FonctionTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeleteFonction(FonctionBean beanDelete) {

		try {
			this.setIdBean((FonctionBean) getObjectValueModel(FORM_BEAN), beanDelete, FonctionTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanDelete);
			this.hibernateTemplate.delete(beanDelete);
			this.saveTrace(beanDelete);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}
