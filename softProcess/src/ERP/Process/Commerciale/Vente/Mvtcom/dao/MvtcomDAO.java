package ERP.Process.Commerciale.Vente.Mvtcom.dao;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.Process.Commerciale.Vente.Mvtcom.model.MvtcomBean;
import ERP.Process.Commerciale.Vente.Mvtcom.template.MvtcomTemplate;

@Repository
public class MvtcomDAO extends GenericWeb {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<MvtcomBean> doFindListMvtcom(MvtcomBean beanSearch) throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		List list_data = new ArrayList();
		try {
			String requette = " select  bean   FROM    MvtcomBean    bean    WHERE     1=1       ";
			if (!Objects.isNull(beanSearch.getId()))
				requette += "   AND   bean.id = " + beanSearch.getId() + "       ";
			if (!StringUtils.isEmpty(beanSearch.getFull_name()))
				requette += "   AND   bean.name = '" + beanSearch.getFull_name() + "'        ";
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

	public Boolean doSaveMvtcom(MvtcomBean beanSave) throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		boolean result = false;
		try {
			this.setBeanTrace(beanSave);
			FileFactureFournisseur insertBean =(FileFactureFournisseur) getObjectValueModel("MultipartFile");
			if(insertBean!=null){
				this.setBeanTrace(insertBean);
				session.save(insertBean);
			}
			beanSave.setFile_id(insertBean.getFile_id());
			session.save(beanSave);
			//this.saveTrace(beanSave);
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

	public Boolean doUpdateMvtcom(MvtcomBean beanUpdate) throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		boolean result = false;
		try {
			setIdBean((MvtcomBean) getObjectValueModel(FORM_BEAN), beanUpdate, MvtcomTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.update(beanUpdate);
			//this.saveTrace(beanUpdate);
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

	public Boolean doDeleteMvtcom(MvtcomBean beanDelete) throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		boolean result = false;
		try {
			session.delete(beanDelete);
			//this.saveTrace(beanDelete);
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
