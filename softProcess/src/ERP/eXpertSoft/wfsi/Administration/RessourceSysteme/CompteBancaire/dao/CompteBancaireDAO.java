package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.dao;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model.CompteBancaireBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.template.CompteBancaireTemplate;
@Repository
public class CompteBancaireDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<CompteBancaireBean> doFindListCompteBancaire(CompteBancaireBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		  try{
		    String requette=" select  bean   FROM    CompteBancaireBean    bean    WHERE     1=1       ";
 			   if( !StringUtils.isEmpty(beanSearch.getCptbanribrib()) )  
	    			requette+="   AND   bean.cptbanribrib = '"+beanSearch.getCptbanribrib()+"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getCptbanribrs()) )  
	    			requette+="   AND   bean.cptbanribrs = '"+beanSearch.getCptbanribrs()+"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getCptbanadr()) )  
	    			requette+="   AND   bean.cptbanadr = '"+beanSearch.getCptbanadr()+"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getBancod()) )  
	    			requette+="   AND   bean.bancod = '"+beanSearch.getBancod()+"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getCptbanjoucod()) )  
	    			requette+="   AND   bean.cptbanjoucod = '"+beanSearch.getCptbanjoucod()+"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getCptbancptcom()) )  
	    			requette+="   AND   bean.cptbancptcom = '"+beanSearch.getCptbancptcom()+"'        ";    
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
	public Boolean doSaveCompteBancaire(CompteBancaireBean beanSave) throws Exception {
Session session =  openSessionHibernate(sessionFactory);
	     boolean result=false; 
		try {
			this.setBeanTrace(beanSave);
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
	public Boolean doUpdateCompteBancaire(CompteBancaireBean beanUpdate)  throws Exception { 
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((CompteBancaireBean) getObjectValueModel(FORM_BEAN), beanUpdate, CompteBancaireTemplate.id_entite);
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
	public Boolean doDeleteCompteBancaire(CompteBancaireBean beanDelete)  throws Exception  {
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
