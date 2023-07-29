package ERP.Process.Commerciale.Vente.Client.dao;

import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Client.template.ClientTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessWebUtil;

@Repository
public class ClientDAO extends GenericWeb {
	
	  
	 
	@Autowired
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Transactional
	public List<ClientBean> doFindListClient(ClientBean beanSearch) throws Exception {
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String requette = " select  bean   FROM    ClientBean    bean    WHERE     1=1       ";
		
		
		if (!StringUtils.isEmpty(beanSearch.getClt_id()))
			requette += "   AND   bean.clt_id = '"+beanSearch.getClt_id()+"'        ";
		
		if (!StringUtils.isEmpty(beanSearch.getClt_lib()))
			requette += "   AND   bean.clt_lib = '"+beanSearch.getClt_lib()+"'        ";
		
		
		if (!StringUtils.isEmpty(beanSearch.getClt_adr()))
			requette += "   AND   bean.clt_adr = '"+beanSearch.getClt_adr()+"'        ";
		
		
		if (!StringUtils.isEmpty(beanSearch.getClt_tel()))
			requette += "   AND   bean.clt_tel = '"+beanSearch.getClt_tel()+"'        ";
		
		if (!StringUtils.isEmpty(beanSearch.getClt_typ()))
			requette += "   AND   bean.clt_typ = '"+beanSearch.getClt_typ()+"'        ";
		
		
		if (!StringUtils.isEmpty(beanSearch.getClt_fax()))
			requette += "   AND   bean.clt_fax = '"+beanSearch.getClt_fax()+"'        ";
		
		
		if (!StringUtils.isEmpty(beanSearch.getFk_etab_Bean().getPk_etab().getEtab_id()))
		  requette    +="  AND   bean.fk_etab_Bean.pk_etab.etab_id='"+beanSearch.getFk_etab_Bean().getPk_etab().getEtab_id()+"'     ";
		
		    
		   requette   += ProcessWebUtil.doChargerSociete_Fetch("bean.fk_etab_Bean");
		   
		   requette   += "   ORDER BY    bean.clt_ordre  ASC       ";
		   
		   
		 
		 List<ClientBean> list2 =  session.createQuery(requette).list();
		 
		return list2;
	}
	@Transactional
	public Boolean doSaveClient(ClientBean beanSave) throws Exception {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			this.setBeanTrace(beanSave);
			session.persist(beanSave);
			this.saveTrace(beanSave);
			tx.commit();
	 } catch (Exception e) {  
	     if (tx!=null) tx.rollback();  
	     throw e;  
	 } finally {  
		 session.close();  
	 }  
		return true;
	}

	public Boolean doUpdateClient(ClientBean beanUpdate) throws Exception {
		boolean result = false;
		 
		try {
			setIdBean((ClientBean) getObjectValueModel(FORM_BEAN), beanUpdate,ClientTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			getHibernateSession(sessionFactory).update(beanUpdate);
			this.saveTrace(beanUpdate);
			result = true;
		} catch (Exception ex) {
			result = false;
			getHibernateSession(sessionFactory).clear();
			throw ex;
		}
		return result;
	}

	public Boolean doDeleteClient(ClientBean beanDelete) throws Exception {
		boolean result = false;
		 
		try {
			getHibernateSession(sessionFactory).delete(beanDelete);
			this.saveTrace(beanDelete);
			result = true;
		} catch (Exception ex) {
			result = false;
			getHibernateSession(sessionFactory).clear();
			throw ex;
		}
		return result;
	}
}
