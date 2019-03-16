package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.model.configDevelopementBean;

@Repository
public class configDevelopementDAO extends GenericWeb {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<configDevelopementBean> doFindListconfigDevelopement(configDevelopementBean beanSearch)
			throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		List list_data = new ArrayList();
		try {
			String requette = " select  bean   FROM    configDevelopementBean    bean    WHERE     1=1       ";
			
			if (beanSearch.getConfig_id() != null)
				requette += "   AND   bean.config_id = " + beanSearch.getConfig_id() + "       ";
			
			if (beanSearch.getFk_etab_Bean() != null
					&& !StringUtils.isEmpty(beanSearch.getFk_etab_Bean().getPk_etab().getSoc_bean().getSoc_id()))
				requette += "   AND   bean.fk_etab_Bean.pk_etab.soc_bean.soc_id= '"
						+ beanSearch.getFk_etab_Bean().getPk_etab().getSoc_bean().getSoc_id() + "'        ";
			
			if (beanSearch.getFk_etab_Bean() != null
					&& !StringUtils.isEmpty(beanSearch.getFk_etab_Bean().getPk_etab().getEtab_id()))
				requette += "   AND   bean.fk_etab_Bean.pk_etab.etab_id= '"
						+ beanSearch.getFk_etab_Bean().getPk_etab().getEtab_id() + "'        ";
			
			if (beanSearch.getSousMod() != null && beanSearch.getSousMod().getSousmod_id() != null)
				requette += "   AND   bean.sousMod.sousmod_id = " + beanSearch.getSousMod().getSousmod_id() + "       ";
			
			
			if ( !StringUtils.isEmpty( beanSearch.getApi_action() ))
				requette += "   AND  bean.api_action = '" + beanSearch.getApi_action()+"'       ";
			
			list_data = session.createQuery(requette).list();
			commitTransaction(session);
		} catch (Exception e) {
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return list_data;
	}

	public Boolean doSaveconfigDevelopement(configDevelopementBean beanSave) throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		boolean result = false;
		try {
			// this.setBeanTrace(beanSave);
			session.save(beanSave);
			// this.saveTrace(beanSave);
			result = true;
			commitTransaction(session);
		} catch (Exception ex) {
			result = false;
			rollbackTransaction(session);
			throw ex;
		} finally {
			session.close();
		}
		return result;
	}

	public Boolean doUpdateconfigDevelopement(configDevelopementBean beanUpdate) throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		boolean result = false;
		try {
			session.update(beanUpdate);
			result = true;
			commitTransaction(session);
		} catch (Exception ex) {
			result = false;
			rollbackTransaction(session);
			throw ex;
		} finally {
			session.close();
		}
		return result;
	}

	public Boolean doDeleteconfigDevelopement(configDevelopementBean beanDelete) throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		boolean result = false;
		try {
			session.delete(beanDelete);
			result = true;
			commitTransaction(session);
		} catch (Exception ex) {
			result = false;
			rollbackTransaction(session);
			throw ex;
		} finally {
			session.close();
		}
		return result;
	}
}
