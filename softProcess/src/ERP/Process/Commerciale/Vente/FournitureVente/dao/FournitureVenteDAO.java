package ERP.Process.Commerciale.Vente.FournitureVente.dao;
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
import ERP.Process.Commerciale.Vente.FournitureVente.model.DetFournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.model.FournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.template.FournitureVenteTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
@Repository
public class FournitureVenteDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<DetFournitureVenteBean> doFindDetailFourniturefromServer(FournitureVenteBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		     
		  try{
		    String requette=" select  bean   FROM    DetFournitureVenteBean    bean    WHERE     1=1       ";
   
 			   if(   beanSearch.getVenteFrn()!=null  &&   !StringUtils.isEmpty(beanSearch.getVenteFrn().getVente_id()) )  
	    			requette+="   AND   bean.fourniture.venteFrn.vente_id = '"+beanSearch.getVenteFrn().getVente_id()+"'        ";  
// 			   else 
// 					requette+="   AND   bean.fourniture.vente_id = 'bidan01'        ";  

 				 if (beanSearch.getVenteFrn().getVente_date() != null) 
				    	requette += "   AND  bean.fourniture.venteFrn.vente_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getVenteFrn().getVente_date())+"'        ";
				    
				if (   beanSearch.getVenteFrn().getVente_date2()!= null ) 
				    	requette += "   AND  bean.fourniture.venteFrn.vente_date <=  '"+ProcessDate.getStringFormatDate(beanSearch.getVenteFrn().getVente_date2())+"'         ";
				
				
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
	public List<DetFournitureVenteBean> doFindDetailFournitureEdition(ProcedureVenteBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		     
		  try{
		    String requette=" select  bean   FROM    DetFournitureVenteBean    bean    WHERE     1=1       ";
 
		  
		    
			 if (beanSearch.getVente_date() != null) 
			    	requette += "   AND  bean.fourniture.venteFrn.factclient.fact_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getVente_date())+"'        ";
			    
			 if (   beanSearch.getVente_date2()!= null ) 
			    	requette += "   AND  bean.fourniture.venteFrn.factclient.fact_date <=  '"+ProcessDate.getStringFormatDate(beanSearch.getVente_date2())+"'         ";
		    
		  
		    requette +=this.setSocieteEtabFetch(beanSearch,"bean.fourniture.fk_etab_Bean", false);
 			   
  
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
	public List<FournitureVenteBean> doFindListFournitureVente(FournitureVenteBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		  try{
		    String requette=" select  bean   FROM    FournitureVenteBean    bean    WHERE     1=1       ";
 			   if( !StringUtils.isEmpty(beanSearch.getFrn_ve_id()) )  
	    			requette+="   AND   bean.frn_ve_id = '"+beanSearch.getFrn_ve_id()+"'        ";    
 			   if( !StringUtils.isEmpty(beanSearch.getFrn_ve_libelle()) )  
	    			requette+="   AND   bean.frn_ve_libelle = '"+beanSearch.getFrn_ve_libelle()+"'        ";    
 			 
 			   
 			  if(   beanSearch.getVenteFrn()!=null  &&   !StringUtils.isEmpty(beanSearch.getVenteFrn().getVente_id()) )  
	    			requette+="   AND   bean.venteFrn.vente_id = '"+beanSearch.getVenteFrn().getVente_id()+"'        ";   
 			   
 			   if(  beanSearch.getFrn_ve_date()!=null )  
	    			requette+="   AND   bean.frn_ve_date = '"+ProcessDate.getStringFormatDate(beanSearch.getFrn_ve_date())+"'        ";    
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
	public Boolean doSaveFournitureVente(FournitureVenteBean beanSave) throws Exception {
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
	public Boolean doUpdateFournitureVente(FournitureVenteBean beanUpdate)  throws Exception { 
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((FournitureVenteBean) getObjectValueModel(FORM_BEAN), beanUpdate, FournitureVenteTemplate.id_entite);
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
	public Boolean doDeleteFournitureVente(FournitureVenteBean beanDelete)  throws Exception  {
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
