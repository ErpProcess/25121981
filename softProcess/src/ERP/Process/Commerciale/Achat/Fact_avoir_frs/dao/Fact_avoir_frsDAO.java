package ERP.Process.Commerciale.Achat.Fact_avoir_frs.dao;
import java.util.List;
import java.util.ArrayList;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.model.Fact_avoir_frsBean;
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.template.Fact_avoir_frsTemplate;
@Repository
public class Fact_avoir_frsDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<Fact_avoir_frsBean> doFindListFact_avoir_frs(Fact_avoir_frsBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		  try{
		    String requette=" select  bean   FROM    Fact_avoir_frsBean    bean    WHERE     1=1       ";
 			   if( !StringUtils.isEmpty(beanSearch.getAvoir_frs_id()) )  
	    			requette+="   AND   bean.avoir_frs_id = '"+beanSearch.getAvoir_frs_id()+"'        ";    
 			   if(  beanSearch.getAvoir_frs_date() !=null )  
	    			requette+="   AND   bean.avoir_frs_date = '"+ ProcessDate.getStringFormatDate(beanSearch.getAvoir_frs_date()) +"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getAvoir_frs_obs()) )  
	    			requette+="   AND   bean.avoir_frs_obs = '"+beanSearch.getAvoir_frs_obs()+"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getAvoir_frs_libelle()) )  
	    			requette+="   AND   bean.avoir_frs_libelle = '"+beanSearch.getAvoir_frs_libelle()+"'        ";    
 			   if(  beanSearch.getDate_cre() != null )  
	    			requette+="   AND   bean.date_cre = '"+ProcessDate.getStringFormatDate(beanSearch.getDate_cre())+"'        ";    
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
	public Boolean doSaveFact_avoir_frs(Fact_avoir_frsBean beanSave) throws Exception {
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
	public Boolean doUpdateFact_avoir_frs(Fact_avoir_frsBean beanUpdate)  throws Exception { 
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((Fact_avoir_frsBean) getObjectValueModel(FORM_BEAN), beanUpdate, Fact_avoir_frsTemplate.id_entite);
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
	public Boolean doDeleteFact_avoir_frs(Fact_avoir_frsBean beanDelete)  throws Exception  {
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
