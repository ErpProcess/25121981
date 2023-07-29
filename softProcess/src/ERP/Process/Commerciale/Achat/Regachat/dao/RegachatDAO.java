package ERP.Process.Commerciale.Achat.Regachat.dao;
import java.util.List;
import java.util.ArrayList;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Achat.Regachat.model.EcheanceRegFrsBean;
import ERP.Process.Commerciale.Achat.Regachat.model.RegachatBean;
import ERP.Process.Commerciale.Achat.Regachat.template.RegachatTemplate;
 
 
 
 
 
@Repository
public class RegachatDAO extends  GenericWeb    {
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
	public List<RegachatBean> doFindListRegachat(RegachatBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List<RegachatBean> list_data= new ArrayList<RegachatBean>();
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
 			 
 	                requette+=this.setSocieteEtabFetch(beanSearch,"bean.fk_etab_Bean", true);
 		 		
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
	public List<EcheanceRegFrsBean> doFindListEcheanceReglement(RegachatBean   beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory); 
		List lisf= new ArrayList();
		try{
		 String requette=" select  bean   FROM    EcheanceRegFrsBean    bean    WHERE     1=1       ";
			    requette+="   AND   bean.pk.reg.reg_frs_id = '"+beanSearch.getReg_frs_id()+"'        "; 
	 
		   lisf= session.createQuery(requette).list();
			commitTransaction(session);
	 } catch (Exception e) {  
	     if (sessionIsTrue(session)) 
	    	 rollbackTransaction(session) ;
	     throw e;  
	 } finally {  
		 session.close();  
	 }  
	 return  lisf;
		 
			 
	}
	
	public Boolean doSaveRegachat(RegachatBean beanSave) throws Exception {
			Session session =  openSessionHibernate(sessionFactory);
	    
		boolean result = false;
		String nature="foui";
		
		try {
			
			 
			
			
			daoNumSequentiel.getNumSeqSimple(beanSave,"reg_frs_id",session);
			List  <EcheanceRegFrsBean>list_des_echeances=(List) getObjectValueModel( RegachatTemplate.LIST_DES_ECHEANCES );
			this.setBeanTrace(beanSave);
			beanSave.getReg_type().setData_id("reg");
			if(list_des_echeances!=null  &&  list_des_echeances.size()>0){
				beanSave.setReg_nbr_echeance(list_des_echeances.size());
			}
			session.save(beanSave);
			if(list_des_echeances!=null  &&  list_des_echeances.size()>0){
				for (int i = 0; i < list_des_echeances.size(); i++) {
					EcheanceRegFrsBean eCltBean =list_des_echeances.get(i);
					eCltBean.getPk().setReg(beanSave);
					this.setBeanTrace(eCltBean);
					nature="fech";
					session.save(eCltBean);
				}
			}
			session.createQuery( " UPDATE  Facture_FournisseurBean  b  set  " +
					"            b.etat_reg.data_id='"+nature+"'   " +
							"     where   b.fact_frs_id='"+beanSave.getFact_frs().getFact_frs_id()+"'    ").executeUpdate();
			
			
			commitTransaction(session);
			result = true;
		 } catch (Exception e) {  
			 result = false;
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
	public Boolean doUpdateRegachat(RegachatBean beanUpdate)  throws Exception { 
	Session session =  openSessionHibernate(sessionFactory);
	String nature="foui";
		boolean result = false;
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier) ||  bs.getFct_id().equals(GenericActionBean.Fn_Confirmer)  
					||  bs.getFct_id().equals(GenericActionBean.Fn_Rectifier) ||  bs.getFct_id().equals(GenericActionBean.Fn_Regler)  ){
			  setUpdateValueFieldTraceOject(beanUpdate);
			  beanUpdate.getReg_type().setData_id("reg");
			  session.update(beanUpdate);
			  saveTrace(beanUpdate);
			}
			List  <EcheanceRegFrsBean>list_des_echeances=(List) getObjectValueModel( RegachatTemplate.LIST_DES_ECHEANCES );
			List  <EcheanceRegFrsBean>list_des_echeances_Origine=(List) getObjectValueModel( RegachatTemplate.LIST_DES_ECHEANCES_ORIGINE );
			
			if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier)  ||  bs.getFct_id().equals(GenericActionBean.Fn_Rectifier)   ){
				
				for (int i = 0; i < list_des_echeances_Origine.size(); i++) {
					EcheanceRegFrsBean beaSUp=list_des_echeances_Origine.get(i);
					session.delete(beaSUp);
				}
				session.flush();
				for (int i = 0; i < list_des_echeances.size(); i++) {
					EcheanceRegFrsBean beanUp=list_des_echeances.get(i);
					beanUp.getPk().setReg(beanUpdate);
					setBeanTrace(beanUp);
					session.save(beanUp);
					nature="fech";
				}
			 }
			 
			if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier))
			session.createQuery( " UPDATE  Facture_FournisseurBean  b  set  " +
					"            b.etat_reg.data_id='"+nature+"'   " +
							"     where   b.fact_frs_id='"+beanUpdate.getFact_frs().getFact_frs_id()+"'    ").executeUpdate();
			
			
			result = true;
			commitTransaction(session);
		 } catch (Exception e) {  
			 result = false;
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
	public Boolean doDeleteRegachat(RegachatBean beanDel)  throws Exception  {
Session session =  openSessionHibernate(sessionFactory);
	    
		boolean result = false;
		try {
			RegachatBean beanDelete = (RegachatBean) getObjectValueModel(FORM_BEAN);
			 
			 
			List  <EcheanceRegFrsBean>list_des_echeances=(List) getObjectValueModel( RegachatTemplate.LIST_DES_ECHEANCES );
			if(list_des_echeances!=null  &&  list_des_echeances.size()>0)
			for (int i = 0; i < list_des_echeances.size(); i++) {
				EcheanceRegFrsBean BeanSup=	list_des_echeances.get(i);
				session.delete(BeanSup);
				 
			}
			session.delete(beanDelete);
	    	result = true;
	    	
	    	session.createQuery( " UPDATE  Facture_FournisseurBean  b  set  " +
					"            b.etat_reg.data_id='fnon'   " +
							"     where   b.fact_frs_id='"+beanDelete.getFact_frs().getFact_frs_id()+"'    ").executeUpdate();
	    	
			commitTransaction(session);
			 
		 } catch (Exception e) {  
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
}
