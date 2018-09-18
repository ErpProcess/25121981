package ERP.Process.Commerciale.Vente.Parametrage.Transport.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.model.TransportBean;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.template.TransportTemplate;
@Repository
public class TransportDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<TransportBean> doFindListTransport(TransportBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList(); 
		  try{
		    String requette=" select  bean   FROM    TransportBean    bean    WHERE     1=1       ";
			 if( beanSearch.getTrans_id() !=null )  
				    requette+="   AND   bean.trans_id = '"+beanSearch.getTrans_id()+"'        ";    
			 if( !StringUtils.isEmpty(beanSearch.getTrans_libelle()) )  
				    requette+="   AND   bean.trans_libelle = '"+beanSearch.getTrans_libelle()+"'        ";    
			 if( !StringUtils.isEmpty(beanSearch.getTrans_type()) )  
				    requette+="   AND   bean.trans_type = '"+beanSearch.getTrans_type()+"'        ";    
			 if( !StringUtils.isEmpty(beanSearch.getTrans_obs()) )  
				    requette+="   AND   bean.trans_obs = '"+beanSearch.getTrans_obs()+"'        ";    
			 
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
	public Boolean doSaveTransport(TransportBean beanSave) throws Exception {
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
	public Boolean doUpdateTransport(TransportBean beanUpdate)  throws Exception { 
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((TransportBean) getObjectValueModel(FORM_BEAN), beanUpdate, TransportTemplate.id_entite);
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
	public Boolean doDeleteTransport(TransportBean beanDelete)  throws Exception  {
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
