package ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model.TypeAvoirBean;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.template.TypeAvoirTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

@Repository
public class TypeAvoirDAO extends  GenericWeb    {
	
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	 
	public List<TypeAvoirBean> doFindListTypeAvoir(TypeAvoirBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List list_data= new ArrayList();
		try{
		  String requette=" select  bean   FROM    TypeAvoirBean    bean    WHERE     1=1       ";
		 if( beanSearch.getType_avoir_id() !=null )  
			     requette+="   AND   bean.type_avoir_id ="+beanSearch.getType_avoir_id()+"       ";    
		 if( !StringUtils.isEmpty(beanSearch.getType_avoir_lib()) )  
			     requette+="   AND   bean.type_avoir_lib = '"+beanSearch.getType_avoir_lib()+"'        ";    
		 if(  beanSearch.getType_avoir_res()!=null )  
	             requette+="   AND   bean.type_avoir_res = "+beanSearch.getType_avoir_res()+"        ";    
			 list_data = session.createQuery(requette).list();
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
	
	public Boolean doSaveTypeAvoir(TypeAvoirBean beanSave) throws Exception {
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
	public Boolean doUpdateTypeAvoir(TypeAvoirBean beanUpdate)  throws Exception { 
		Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((TypeAvoirBean) getObjectValueModel(FORM_BEAN), beanUpdate, TypeAvoirTemplate.id_entite);
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
	public Boolean doDeleteTypeAvoir(TypeAvoirBean beanDelete)  throws Exception  {
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
