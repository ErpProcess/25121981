package ERP.Process.Commerciale.Stock.ResponsableLieu.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Stock.ResponsableLieu.model.ResponsableLieuBean;
import ERP.Process.Commerciale.Stock.ResponsableLieu.template.ResponsableLieuTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Repository
public class ResponsableLieuDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<ResponsableLieuBean> doFindListResponsableLieu(ResponsableLieuBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		  try{
		    String requette=" select  bean   FROM    ResponsableLieuBean    bean    WHERE     1=1       ";
 			   if(  (beanSearch.getPk().getDepot().getDepot_id()!=null) )  
	    			requette+="   AND   bean.pk.depot.depot_id = "+beanSearch.getPk().getDepot().getDepot_id()+"        "; 
 			   
 			   if( !StringUtils.isEmpty(beanSearch.getPk().getUsr().getUsr_id()) )  
	    			requette+="   AND   bean.pk.usr.usr_id = '"+beanSearch.getPk().getUsr().getUsr_id()+"'        ";    
 			   
 			   if(  (beanSearch.getOrdre()!=null) )  
	    			 requette+="   AND   bean.ordre ="+beanSearch.getOrdre()+"       "; 
 			   
 			        requette +=this.setSocieteEtabFetch(beanSearch,"bean.pk.depot.fk_etab_Bean", false);
 			   
 			   
 			        requette+="   ORDER BY  bean.ordre  asc       "; 
 			   
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
	public Boolean doSaveResponsableLieu(ResponsableLieuBean beanSave) throws Exception {
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
	public Boolean doUpdateResponsableLieu(ResponsableLieuBean beanUpdate)  throws Exception { 
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((ResponsableLieuBean) getObjectValueModel(FORM_BEAN), beanUpdate, ResponsableLieuTemplate.id_entite);
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
	public Boolean doDeleteResponsableLieu(ResponsableLieuBean beanDelete)  throws Exception  {
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
