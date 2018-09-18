package ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Source_incidentBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.template.Nature_incident_mvt_retourTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Repository
public class Nature_incident_mvt_retourDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Nature_incident_mvt_retourBean> doFindListNature_incident_mvt_retour(Nature_incident_mvt_retourBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List list_data= new ArrayList();
		try{
		    String requette=" select  bean   FROM    Nature_incident_mvt_retourBean    bean    WHERE     1=1       ";
	 	 if(  beanSearch.getNature_incident_id()!=null )  
				    requette+="   AND   bean.nature_incident_id = "+beanSearch.getNature_incident_id()+"        ";    
			 if( !StringUtils.isEmpty(beanSearch.getNature_incident_lib()) )  
				    requette+="   AND   bean.nature_incident_id = '"+beanSearch.getNature_incident_lib()+"'        ";    
			 if( !StringUtils.isEmpty(beanSearch.getNature_incident_sense()) )  
				    requette+="   AND   bean.nature_incident_sens = '"+beanSearch.getNature_incident_sense()+"'        ";    
			 if( !StringUtils.isEmpty(beanSearch.getNature_incident_type()) )  
	    requette+="   AND   bean.nature_incident_type = '"+beanSearch.getNature_incident_type()+"'        ";  
				  list_data= session.createQuery(requette).list();
				  commitTransaction(session);
			 } catch (Exception e) {  
			     if (sessionIsTrue(session)) 
			    	 rollbackTransaction(session) ;
				     throw e;  
				 } finally {  
				 session.close();  
			 }  
			 return  list_data;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Source_incidentBean> doFindListSourceIncident(Source_incidentBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List list_data= new ArrayList();
		try{ 
		    String requette=" select  bean   FROM    Source_incidentBean    bean    WHERE     1=1       ";
	  
				  list_data= session.createQuery(requette).list();
				  commitTransaction(session);
			 } catch (Exception e) {  
			     if (sessionIsTrue(session)) 
			    	 rollbackTransaction(session) ;
				     throw e;  
				 } finally {  
				 session.close();  
			 }  
			 return  list_data;
	}
	
	
	
	public Boolean doSaveNature_incident_mvt_retour(Nature_incident_mvt_retourBean beanSave) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
	     boolean result=false; 
		try {
			this.setBeanTrace(beanSave);
			daoNumSequentiel.getNumSeqSimple(beanSave,"nature_incident_id",session);
		 
			session.save(beanSave);
			this.saveTrace(beanSave);
			result=true;
			commitTransaction(session);
		 } catch (Exception ex) {
			 result=false;
			 rollbackTransaction(session) ;
		     throw ex;
		 } finally {  
			 session.close();  
		 } 
	      return result;
	}
	public Boolean doUpdateNature_incident_mvt_retour(Nature_incident_mvt_retourBean beanUpdate)  throws Exception { 
		Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((Nature_incident_mvt_retourBean) getObjectValueModel(FORM_BEAN), beanUpdate, Nature_incident_mvt_retourTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result=true;
			commitTransaction(session);
		 } catch (Exception ex) {
			 result=false;
			 rollbackTransaction(session) ;
		     throw ex;
		 } finally {  
			 session.close();  
		 } 
	      return result;
	}
	public Boolean doDeleteNature_incident_mvt_retour(Nature_incident_mvt_retourBean beanDelete)  throws Exception  {
		Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			session.delete(beanDelete);
	        this.saveTrace(beanDelete);
			result=true;
			commitTransaction(session);
		 } catch (Exception ex) {
			 result=false;
			 rollbackTransaction(session) ;
		     throw ex;
		 } finally {  
			 session.close();  
		 } 
	      return result;
	}
}
