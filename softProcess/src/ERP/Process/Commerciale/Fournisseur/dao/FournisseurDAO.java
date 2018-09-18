package ERP.Process.Commerciale.Fournisseur.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.template.FournisseurTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
 

@Repository
public class FournisseurDAO extends GenericWeb {
	@Autowired
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
   
    
	@SuppressWarnings("unchecked")
	public List<FournisseurBean> doFindListFrournisseur( FournisseurBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<FournisseurBean>   lisf = new ArrayList<FournisseurBean>();
		try {
			 String requette = " select  bean   FROM    FournisseurBean    bean    WHERE     1=1       ";
		     requette += setSocieteEtabFetch(beanSearch, "bean.fk_etab_Bean", true);
			 lisf= session.createQuery(requette).list();
			 commitTransaction(session);
	 } catch (Exception e) {  
	     if (sessionIsTrue(session)) 
	    	 rollbackTransaction(session) ;
	     throw e;  
	 } finally {  
		 session.close();  
	 }  
	 return  lisf;
	}
	 
	public Boolean doSaveFrournisseur(FournisseurBean beanSave)throws Exception {
		boolean result = false;
		Session hibernateSession =   this.sessionFactory.openSession();
		Transaction tx  =   hibernateSession.beginTransaction();
		try {
			this.setBeanTrace(beanSave);
			hibernateSession.persist(beanSave);
			this.saveTrace(beanSave);
			tx.commit();
			result = true;
		} catch (Exception ex) {
			result = false;
		     if (tx!=null) tx.rollback();  
		     throw ex;  
		 } finally {  
			 hibernateSession.close();  
		 }  
		return result;
	}

	public Boolean doUpdateFrournisseur(FournisseurBean beanUpdate)
			throws Exception {
		boolean result = false;
		Session hibernateSession =   this.sessionFactory.openSession();
		Transaction tx  =   hibernateSession.beginTransaction();
		try {
			setIdBean((FournisseurBean) getObjectValueModel(FORM_BEAN),beanUpdate, FournisseurTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			hibernateSession.update(beanUpdate);
			tx.commit();
			saveTrace(beanUpdate);
			result = true;
		} catch (Exception ex) {
			result = false;
			  if (tx!=null) tx.rollback();  
			throw ex;
		} finally {  
			 hibernateSession.close();  
		 }  
		return result;
	}

	public Boolean doDeleteFrournisseur(FournisseurBean beanDelete) throws Exception {
		boolean result = false;
		Session hibernateSession =   this.sessionFactory.openSession();
		Transaction tx  =   hibernateSession.beginTransaction();
		try {
			hibernateSession.delete(beanDelete);
			this.saveTrace(beanDelete);
			tx.commit();
			result = true;
		} catch (Exception ex) {
			result = false;
			if (tx!=null) tx.rollback();  
			throw ex;
		}finally {  
			 hibernateSession.close();  
		 }  
		return result;
	}
}
