package ERP.Process.Commerciale.Vente.Service.dao;
import java.util.List;
import java.util.ArrayList;
import org.springframework.util.StringUtils;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.Service.model.DetServiceBean;
import ERP.Process.Commerciale.Vente.Service.model.ServiceBean;
import ERP.Process.Commerciale.Vente.Service.template.ServiceTemplate;
@Repository
public class ServiceDAO extends  GenericWeb    {
		private SessionFactory sessionFactory;
		@Autowired
		public void setSessionFactory (SessionFactory sessionFactory) {
			 this.sessionFactory = sessionFactory;
		}
		@SuppressWarnings("unchecked")
		public List<ServiceBean> doFindListService(ServiceBean beanSearch) throws Exception {
			  Session session =  openSessionHibernate(sessionFactory);
			  List list_data= new ArrayList();
			  try{
			    String requette=" select  bean   FROM    ServiceBean    bean    WHERE     1=1       ";
	 			   if( !StringUtils.isEmpty(beanSearch.getSrv_id()) )  
		    			requette+="   AND   bean.srv_id = '"+beanSearch.getSrv_id()+"'        ";    
	 			   if( !StringUtils.isEmpty(beanSearch.getSrv_libelle()) )  
		    			requette+="   AND   bean.srv_libelle = '"+beanSearch.getSrv_libelle()+"'        ";    
	// 			   if( !StringUtils.isEmpty(beanSearch.getVente_id()) )  
	//	    			requette+="   AND   bean.vente_id = '"+beanSearch.getVente_id()+"'        ";    
	 			   if(  beanSearch.getSrv_date() != null )  
		    			requette+="   AND   bean.srv_date = '"+ ProcessDate.getStringFormatDate(beanSearch.getSrv_date())+"'        ";    
	 			   if( !StringUtils.isEmpty(beanSearch.getSrv_obs()) )  
		    			requette+="   AND   bean.srv_obs = '"+beanSearch.getSrv_obs()+"'        ";    
	 			  
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
		public List<DetServiceBean> doFindDetailListService(ProcedureVenteBean beanSearch) throws Exception {
			  Session session =  openSessionHibernate(sessionFactory);
			  List  list_data= new ArrayList();
			  
			  try{
				  
			    String requette=" select  bean   FROM    DetServiceBean    bean    WHERE     1=1       ";
	 			   
			    if (beanSearch.getVente_date() != null) 
			    	requette += "   AND  bean.service.venteSrv.factclient.fact_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getVente_date())+"'        ";
			    
			    if (   beanSearch.getVente_date2()!= null ) 
			    	requette += "   AND  bean.service.venteSrv.factclient.fact_date <=  '"+ProcessDate.getStringFormatDate(beanSearch.getVente_date2())+"'         ";
		    
		  
		        requette +=this.setSocieteEtabFetch(beanSearch,"bean.service.fk_etab_Bean", false);
	 			  
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
		public List<DetServiceBean> doFindDetailListServiceByVenteId(ProcedureVenteBean beanSearch) throws Exception {
			  Session session =  openSessionHibernate(sessionFactory);
			  List  list_data= new ArrayList();
			  
			  try{
				  
			    String requette=" select  bean   FROM    DetServiceBean    bean    WHERE     1=1       ";
	 			   

			    
				 if (beanSearch.getVente_date() != null) 
				    	requette += "   AND  bean.service.venteSrv.vente_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getVente_date())+"'        ";
				    
				if (   beanSearch.getVente_date2()!= null ) 
				    	requette += "   AND  bean.service.venteSrv.vente_date <=  '"+ProcessDate.getStringFormatDate(beanSearch.getVente_date2())+"'         ";
				
				if ( ! StringUtils.isEmpty(beanSearch.getVente_id())  ) 
			    	requette += "   AND  bean.service.venteSrv.vente_id = '"+beanSearch.getVente_id()+"'        ";
			    
			 
	 			  
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
		
		
		
		
		public Boolean doSaveService(ServiceBean beanSave) throws Exception {
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
		public Boolean doUpdateService(ServiceBean beanUpdate)  throws Exception { 
	Session session =  openSessionHibernate(sessionFactory);
		    boolean result=false; 
			try {
				setIdBean((ServiceBean) getObjectValueModel(FORM_BEAN), beanUpdate, ServiceTemplate.id_entite);
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
		public Boolean doDeleteService(ServiceBean beanDelete)  throws Exception  {
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
