package ERP.Process.Commerciale.Vente.Facture_comp_client.dao;
import java.util.List;
import java.util.ArrayList;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Vente.Facture_comp_client.model.Facture_comp_clientBean;
import ERP.Process.Commerciale.Vente.Facture_comp_client.template.Facture_comp_clientTemplate;
@Repository
public class Facture_comp_clientDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<Facture_comp_clientBean> doFindListFacture_comp_client(Facture_comp_clientBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		  try{
		    String requette=" select  bean   FROM    Facture_comp_clientBean    bean    WHERE     1=1       ";
 			   if( !StringUtils.isEmpty(beanSearch.getFact_comp_id()) )  
	    			requette+="   AND   bean.fact_comp_id = '"+beanSearch.getFact_comp_id()+"'        ";    
 			   if(   beanSearch.getFact_comp_date() !=null )  
	    			requette+="   AND   bean.fact_comp_date = "+beanSearch.getFact_comp_date()+"      ";    
 			   if( !StringUtils.isEmpty(beanSearch.getFact_comp_obs()) )  
	    			requette+="   AND   bean.fact_comp_obs = '"+beanSearch.getFact_comp_obs()+"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getFact_comp_libelle()) )  
	    			requette+="   AND   bean.fact_comp_libelle = '"+beanSearch.getFact_comp_libelle()+"'        ";    
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
	public Boolean doSaveFacture_comp_client(Facture_comp_clientBean beanSave) throws Exception {
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
	public Boolean doUpdateFacture_comp_client(Facture_comp_clientBean beanUpdate)  throws Exception { 
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((Facture_comp_clientBean) getObjectValueModel(FORM_BEAN), beanUpdate, Facture_comp_clientTemplate.id_entite);
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
	public Boolean doDeleteFacture_comp_client(Facture_comp_clientBean beanDelete)  throws Exception  {
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
