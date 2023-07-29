package ERP.Process.Commerciale.Vente.Livraison.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.Process.Commerciale.Vente.Livraison.model.LivraisonBean;
import ERP.Process.Commerciale.Vente.Livraison.template.LivraisonTemplate;
@Repository
public class LivraisonDAO extends  GenericWeb    {
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
	public List<LivraisonBean> doFindListLivraison(LivraisonBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		  try{
		    String requette=" select  bean   FROM    LivraisonBean    bean    WHERE     1=1       ";
			 if( !StringUtils.isEmpty(beanSearch.getLiv_id()) )  
				    requette+="   AND   bean.liv_id = '"+beanSearch.getLiv_id()+"'        ";    
			 if( !StringUtils.isEmpty(beanSearch.getLiv_libelle()) )  
				    requette+="   AND   bean.liv_libelle = '"+beanSearch.getLiv_libelle()+"'        ";    
			 if(  beanSearch.getLiv_date() !=null)  
				    requette+="   AND   bean.liv_date = '"+beanSearch.getLiv_date()+"'        ";    
			 if(  beanSearch.getLiv_type() !=null)  
				    requette+="   AND   bean.liv_type = "+beanSearch.getLiv_type()+"       "; 
			 
			 if( !StringUtils.isEmpty(beanSearch.getLiv_obs()) )  
				    requette+="   AND   bean.liv_obs = '"+beanSearch.getLiv_obs()+"'        "; 
			 
			        requette+="   AND   bean.liv_id != ''      "; 
			 
			 
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
	public Boolean doSaveLivraison(LivraisonBean beanSave) throws Exception {
			Session session =  openSessionHibernate(sessionFactory);
	     boolean result=false; 
		try {
			
			
			this.setBeanTrace(beanSave);
			daoNumSequentiel.getNumSeqSimple(beanSave,"liv_id",session);
			session.save(beanSave);
			session.createQuery( " UPDATE  ProcedureVenteBean bean  set  " +
					"              bean.liv_id='"+beanSave.getLiv_id()+"'   " +
							"      where   bean.vente_id='"+beanSave.getVente().getVente_id()+"' ").executeUpdate();
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
	public Boolean doUpdateLivraison(LivraisonBean beanUpdate)  throws Exception { 
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((LivraisonBean) getObjectValueModel(FORM_BEAN), beanUpdate, LivraisonTemplate.id_entite);
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
	public Boolean doDeleteLivraison(LivraisonBean beanDelete)  throws Exception  {
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			
			this.setBeanTrace(beanDelete);
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
