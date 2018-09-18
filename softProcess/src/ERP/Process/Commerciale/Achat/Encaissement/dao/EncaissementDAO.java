package ERP.Process.Commerciale.Achat.Encaissement.dao;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
 
import ERP.Process.Commerciale.Achat.Encaissement.template.EncaissementTemplate;
import ERP.Process.Commerciale.Achat.Regachat.model.RegachatBean;
@Repository
public class EncaissementDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
		@SuppressWarnings("unchecked")
		public List<RegachatBean> doFindListEncaissement(RegachatBean beanSearch) throws Exception {
			  Session session =  openSessionHibernate(sessionFactory);
			  List list_data= new ArrayList();
			  try{
			    String requette=" select  bean   FROM    RegachatBean    bean    WHERE     1=1       ";
	 			   if( !StringUtils.isEmpty(beanSearch.getReg_frs_id()) )  
		    			requette+="   AND   bean.reg_frs_id = '"+beanSearch.getReg_frs_id()+"'        ";    
	 			  /* if( !StringUtils.isEmpty(beanSearch.getFact_frs_id()) )  
		    			requette+="   AND   bean.fact_frs_id = '"+beanSearch.getFact_frs_id()+"'        ";    
	 			   if( !StringUtils.isEmpty(beanSearch.getReg_mod()) )  
		    			requette+="   AND   bean.reg_mod = '"+beanSearch.getReg_mod()+"'        ";  */  
	 			   if(  beanSearch.getReg_date() !=null   )  
		    			requette+="   AND   bean.reg_date = '"+beanSearch.getReg_date()+"'        ";    
	 			   if(  beanSearch.getReg_nbr_echeance()!=null  )  
		    			requette+="   AND   bean.reg_nbr_echeance = '"+beanSearch.getReg_nbr_echeance()+"'        ";    
	 			   /*if( !StringUtils.isEmpty(beanSearch.getMontant_facture()) )  
		    			requette+="   AND   bean.montant_facture = '"+beanSearch.getMontant_facture()+"'        ";    
	 			   if( !StringUtils.isEmpty(beanSearch.getMontant_avance()) )  
		    			requette+="   AND   bean.montant_avance = '"+beanSearch.getMontant_avance()+"'        ";    
	 			   if( !StringUtils.isEmpty(beanSearch.getMontant_restant()) )  
		    			requette+="   AND   bean.montant_restant = '"+beanSearch.getMontant_restant()+"'        "; */   
	 			   if(  beanSearch.getDate_cre()!=null )  
		    			requette+="   AND   bean.date_cre = '"+beanSearch.getDate_cre()+"'        ";  
	 			   
	 			  if( !StringUtils.isEmpty(beanSearch.getCondition_juste_echeance()) )  
	 					requette+="  "+beanSearch.getCondition_juste_echeance()+"         "; 
	 			  
	 			  
	 			 if( !StringUtils.isEmpty(beanSearch.getCondition_mode()) )  
						requette+="  "+beanSearch.getCondition_mode()+"         "; 
	 			 
	 			 
	 				
	 				
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
		 
		public Boolean doSaveEncaissement(RegachatBean beanSave) throws Exception {
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
		public Boolean doUpdateEncaissement(RegachatBean beanUpdate)  throws Exception { 
	Session session =  openSessionHibernate(sessionFactory);
		    boolean result=false; 
			try {
				setIdBean((RegachatBean) getObjectValueModel(FORM_BEAN), beanUpdate, EncaissementTemplate.id_entite);
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
		public Boolean doDeleteEncaissement(RegachatBean beanDelete)  throws Exception  {
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
