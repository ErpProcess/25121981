package ERP.Process.Commerciale.Vente.Rembourserclient.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Vente.ReglementFactClt.model.EcheanceRegCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.template.ReglementFactCltTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
@Repository
public class RembourserclientDAO extends  GenericWeb    {
	 
	private SessionFactory sessionFactory;
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReglementFactCltBean> doFindListReglementFactClt(ReglementFactCltBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List lisf= new ArrayList();
		try{
		 String requette=" select  bean   FROM    ReglementFactCltBean    bean    WHERE     1=1       ";
		 if( !StringUtils.isEmpty(beanSearch.getReg_id()) )  
			    requette+="   AND   bean.reg_id = '"+beanSearch.getReg_id()+"'        ";    
		 if( !StringUtils.isEmpty(beanSearch.getFactclient().getFact_clt_id()) )  
			    requette+="   AND   bean.factclient.fact_clt_id = '"+beanSearch.getFactclient().getFact_clt_id()+"'        "; 
		 
		 if(  beanSearch.getModReg().getMod_id()!=null    )  
			    requette+="   AND   bean.modReg.mod_id = "+beanSearch.getModReg().getMod_id()+"       ";   
		 
		 if( (beanSearch.getReg_date()!=null) )  
			    requette+="   AND   bean.reg_date = '"+beanSearch.getReg_date()+"'        ";    
		 if( (beanSearch.getReg_nbr_echeance()!=null) )  
			    requette+="   AND   bean.reg_nbr_echeance ="+beanSearch.getReg_nbr_echeance()+"       ";    
		 if( beanSearch.getMontant_facture()!=null)  
			    requette+="   AND   bean.montant_facture ="+beanSearch.getMontant_facture()+"      ";    
		 if( beanSearch.getMontant_avance()!=null )  
			    requette+="   AND   bean.montant_avance ="+beanSearch.getMontant_avance()+"     ";    
		 if(  beanSearch.getMontant_restant()!=null )  
			    requette+="   AND   bean.montant_restant = "+beanSearch.getMontant_restant()+"       ";  
		
			/*BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			if(bs.getSousmod_id().equals(ReglementFactCltTemplate.ID_SOUS_MODULE)){
				
		        requette+= setModeOperationConfig("bean.modeBean");
			 }*/
		 if( !StringUtils.isEmpty(beanSearch.getCondition_juste_echeance()) )  
			    requette+="  "+beanSearch.getCondition_juste_echeance()+"       ";   
		 
		 if( !StringUtils.isEmpty(beanSearch.getSelect_avoir()) )  
			    requette+="  "+beanSearch.getSelect_avoir()+"       ";   
		  
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
	
	@SuppressWarnings("unchecked")
	public List<EcheanceRegCltBean> doFindListEcheanceReglement(ReglementFactCltBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory); 
		List lisf= new ArrayList();
		try{
		 String requette=" select  bean   FROM    EcheanceRegCltBean    bean    WHERE     1=1       ";
			    requette+="   AND   bean.pk.reg.reg_id = '"+beanSearch.getReg_id()+"'        "; 
	 
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
	public Boolean doSaveReglementFactClt(ReglementFactCltBean beanSave) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
	    
		boolean result = false;
		try {
			daoNumSequentiel.getNumSeqSimple(beanSave,"reg_id",session);
			List  <EcheanceRegCltBean>list_des_echeances=(List) getObjectValueModel( ReglementFactCltTemplate.LIST_DES_ECHEANCES );
			this.setBeanTrace(beanSave);
			Double montant_facture= ProcessNumber.PRODUIT( beanSave.getMontant_facture(), new Double(-1));
			Double   montant_restant=  ProcessNumber.PRODUIT( beanSave.getMontant_restant(), new Double(-1));
			beanSave.setMontant_facture(montant_facture);
			beanSave.setMontant_restant(montant_restant);
			beanSave.getReg_type().setData_id("rem");
			session.save(beanSave);
			//this.saveTrace(beanSave);
			if(beanSave.getReg_nbr_echeance()!=null  &&  beanSave.getReg_nbr_echeance()>0)
			for (int i = 0; i < list_des_echeances.size(); i++) {
				EcheanceRegCltBean eCltBean =list_des_echeances.get(i);
				eCltBean.getPk().setReg(beanSave);
				this.setBeanTrace(eCltBean);
				session.save(eCltBean);
			}
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
	public Boolean doUpdateReglementFactClt(ReglementFactCltBean beanUpdate)  throws Exception { 
		Session session =  openSessionHibernate(sessionFactory);
	    
		boolean result = false;
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier) ||  bs.getFct_id().equals(GenericActionBean.Fn_Confirmer)  ){
			  this.setUpdateValueFieldTraceOject(beanUpdate);
			  
			  beanUpdate.getReg_type().setData_id("rem");
			  session.update(beanUpdate);
			  this.saveTrace(beanUpdate);
			}
			
			List  <EcheanceRegCltBean>list_des_echeances=(List) getObjectValueModel( ReglementFactCltTemplate.LIST_DES_ECHEANCES );
			List  <EcheanceRegCltBean>list_des_echeances_Origine=(List) getObjectValueModel( ReglementFactCltTemplate.LIST_DES_ECHEANCES_ORIGINE );
			
			if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier)){
				
				for (int i = 0; i < list_des_echeances_Origine.size(); i++) {
					EcheanceRegCltBean beaSUp=list_des_echeances_Origine.get(i);
					session.delete(beaSUp);
				}
				session.flush();
				for (int i = 0; i < list_des_echeances.size(); i++) {
					EcheanceRegCltBean beanUp=list_des_echeances.get(i);
					beanUp.getPk().setReg(beanUpdate);
					setBeanTrace(beanUp);
					session.save(beanUp);
				}
			 }
			if(bs.getFct_id().equals(GenericActionBean.Fn_Annuler)){
				for (int i = 0; i < list_des_echeances.size(); i++) {
					EcheanceRegCltBean beaSUp=list_des_echeances.get(i);
					if(  ! StringUtils.isEmpty(beaSUp.getTo_check()) )
					session.delete(beaSUp);
				}
			}
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
	public Boolean doDeleteReglementFactClt(ReglementFactCltBean beaSup)  throws Exception  {
			Session session =  openSessionHibernate(sessionFactory);
	    
		boolean result = false;
		try {
			ReglementFactCltBean beanDelete = (ReglementFactCltBean) getObjectValueModel(FORM_BEAN);
			boolean resultX = false;
			 
			List  <EcheanceRegCltBean>list_des_echeances=(List) getObjectValueModel( ReglementFactCltTemplate.LIST_DES_ECHEANCES );
			if(list_des_echeances!=null  &&  list_des_echeances.size()>0)
			for (int i = 0; i < list_des_echeances.size(); i++) {
				EcheanceRegCltBean BeanSup=	list_des_echeances.get(i);
				session.delete(BeanSup);
				resultX = true;
			}
			 
			session.delete(beanDelete);
	    	result = true;
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
