package ERP.Process.Commerciale.Achat.Retour_achat.dao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Achat.Retour_achat.model.Det_retour_achatBean;
import ERP.Process.Commerciale.Achat.Retour_achat.model.Retour_achatBean;
import ERP.Process.Commerciale.Achat.Retour_achat.template.Retour_achatTemplate;
import ERP.Process.Commerciale.Stock.Inventaire.dao.InventaireDAO;
import ERP.Process.Commerciale.Stock.Stock_article.dao.Stock_articleDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
 
@Repository
public class Retour_achatDAO extends  GenericWeb    {
	
	
	private SessionFactory sessionFactory;
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	private Stock_articleDAO daoStock_article;
	@Autowired
	public void setDaoStock_article(Stock_articleDAO daoStock_article) {
		this.daoStock_article = daoStock_article;
	}
	
	
	private InventaireDAO daoInventaire;
	@Autowired
	public void setDaoInventaire(InventaireDAO daoInventaire) {
		this.daoInventaire = daoInventaire;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Retour_achatBean> doFindListRetour_achat(Retour_achatBean beanSearch) throws Exception {
		
		
		Session session =  openSessionHibernate(sessionFactory);
		List<Retour_achatBean>   lisf = new ArrayList<Retour_achatBean>();
		try{
			
			
			
	 
		    String requette=" select  bean   FROM    Retour_achatBean    bean    WHERE     1=1       ";
		    
			 if( !StringUtils.isEmpty(beanSearch.getRetour_id()) )  
				 
				    requette+="   AND   bean.retour_id = '"+beanSearch.getRetour_id()+"'        ";  
			 
			if (beanSearch.getRetour_date() != null) 
			    	requette += "   AND  bean.retour_date >=    '"+ProcessDate.getStringFormatDate(beanSearch.getRetour_date())+"'        ";
			    
			if (   beanSearch.getRetour_date2() != null  ) 
			    	requette += "   AND  bean.retour_date <= '"+ProcessDate.getStringFormatDate(beanSearch.getRetour_date2())+"'        ";
			
			
			if (!StringUtils.isEmpty(beanSearch.getAchat().getAchat_id()))
				   requette += "   AND   bean.achat.achat_id = '"+beanSearch.getAchat().getAchat_id()+"'        ";
			
			if ( !StringUtils.isEmpty(beanSearch.getAchat().getDem_achat().getDem_achat_id()))
				   requette += "   AND   bean.achat.dem_achat.dem_achat_id = '" +beanSearch.getAchat().getDem_achat().getDem_achat_id()+"'        ";
			
			
			if (!StringUtils.isEmpty(beanSearch.getAchat().getAchat_libelle()))
				   requette += "   AND   bean.achat.achat_libelle = '"+beanSearch.getAchat().getAchat_libelle()+"'        ";
			
		
			
			if (!StringUtils.isEmpty(beanSearch.getAchat().getFrsBean().getFrs_id()))
				requette += "   AND   bean.achat.frsBean.frs_id = '" + beanSearch.getAchat().getFrsBean().getFrs_id()+ "'        ";
			
			
			
			if (beanSearch.getAchat().getDepot().getDepot_id() != null)
				requette += "   AND   bean.achat.depot.depot_id = '" + beanSearch.getAchat().getDepot().getDepot_id()+"'        ";
			 
			  
		    
			 
			 
			 if(    !StringUtils.isEmpty(beanSearch.getCondition_etat_retour()) )  
				 
				    requette+=" "+beanSearch.getCondition_etat_retour()+"        ";
			 
			 
			 
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
	public List<Det_retour_achatBean> doFindList_detaille_plusieur_RetourVente(String lesachat ) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<Det_retour_achatBean>   lisf = new ArrayList<Det_retour_achatBean>();
		try{
		    String requette=" select  bean   FROM    Det_retour_achatBean    bean    WHERE   " +
		    		"   bean.pk.det_re.pk.recepBean.achat_id  in ("+lesachat+"  )     ";
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
	
	
	
	public Boolean doSaveRetour_achat(Retour_achatBean beanSave) throws Exception {
		 boolean result=false; 
	     Session session =  openSessionHibernate(sessionFactory);
		try {
			//this.setBeanTrace(beanSave);
			//this.hibernateTemplate.save(beanSave);
			//this.saveTrace(beanSave);
			//result=true;
			
			
			
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			this.setBeanTrace(beanSave);
			List listOfmyData=(List) getObjectValueModel( Retour_achatTemplate.LIST_EDITABLE_RETOUR_ACHAT);
			
			session.save(beanSave);
		
			//this.saveTraceVersion1(beanSave, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_retour_achatBean detBean=(Det_retour_achatBean) listOfmyData.get(i);
				if(detBean.getQuantite_retourne()!=null  &&  detBean.getQuantite_retourne().doubleValue()>0){
				detBean.getPk().setRetour(beanSave);
				session.save(detBean);
				result_detaille = true;
				}
			}
			if(!result_detaille)throwNewException("err_inser_deaill");
			
			
			//this.saveTraceVersion_Master_detailles(listOfmyData, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			 
			//if( !StringUtils.isEmpty( beanSave.getDem_achat().getDem_achat_id()) )
			//
			
			result=true;
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
	public Boolean doUpdateRetour_achat(Retour_achatBean beanUpdate)  throws Exception { 
		 boolean result=false; 
	     Session session =  openSessionHibernate(sessionFactory);
		try {
			
			 List list_retour_clone = (List) getObjectValueModel(Retour_achatTemplate.LIST_EDITABLE_RETOUR_ACHAT_ORG );
		 
			 
			 for (int i = 0; i < list_retour_clone.size(); i++) {
					Det_retour_achatBean beaSUp=(Det_retour_achatBean) list_retour_clone.get(i);
					session.delete(beaSUp);
				}
			 
			 
			 session.flush();
		     //this.hibernateTemplate.clear();
			 List listOfmyData     = (List) getObjectValueModel(Retour_achatTemplate.LIST_EDITABLE_RETOUR_ACHAT);
			 
			 boolean result_detaille = false;
				for (int i = 0; i < listOfmyData.size(); i++) {
					Det_retour_achatBean detBean=(Det_retour_achatBean) listOfmyData.get(i);
				    if( detBean.getQuantite_retourne()==null) { continue; }
					if( detBean.getQuantite_retourne()==0 ||  detBean.getQuantite_retourne()<0) { continue;}
					detBean.getPk().setRetour(beanUpdate);
					 
					session.save(detBean);
					result_detaille=true;
				}
			  if(!result_detaille)throwNewException("err_inser_deaill");
			 
			setIdBean((Retour_achatBean) getObjectValueModel(FORM_BEAN), beanUpdate, Retour_achatTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.saveOrUpdate(beanUpdate);
			this.saveTrace(beanUpdate);
			 
			result=true;
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
	
	public Boolean doDeleteRetour_achat(Retour_achatBean beanDelete)  throws Exception  {
		 boolean result=false; 
	     Session session =  openSessionHibernate(sessionFactory);
		try {
			
			Retour_achatBean   rowBean = (Retour_achatBean) getObjectValueModel(FORM_BEAN);
			List List_detail_retour=(List) getObjectValueModel(Retour_achatTemplate.LIST_EDITABLE_RETOUR_ACHAT    );
			 for (int i = 0; i < List_detail_retour.size(); i++) {
					Det_retour_achatBean beaSUp=(Det_retour_achatBean) List_detail_retour.get(i);
					session.delete(beaSUp);
				}
			 
			session.delete(rowBean);
	        this.saveTrace(beanDelete);
	    	result=true;
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
	
	public Boolean doExcuteemethode_achat()  throws Exception  {
		 boolean result=false; 
	     Session session =  openSessionHibernate(sessionFactory);
		try {
			Retour_achatBean beanUpdate=(Retour_achatBean) getObjectValueModel(FORM_BEAN);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
 			
 			if(bs.getFct_id().equals(GenericActionBean.Fn_Annuler) && bs.getSousmod_id().equals(Retour_achatTemplate.ID_SOUS_MODULE_STOCK)){
 				beanUpdate.getModeBean().setFct_id(new BigDecimal("3"));
 				beanUpdate.getModeBean().setFct_libelle("Modifier");
 			}
 			
			 session.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result=true;
			
			result=true;
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
	
 
	
	
	
}
