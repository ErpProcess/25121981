package ERP.Process.Commerciale.Vente.Facture_client.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.Process.Commerciale.Vente.EditionVente.model.EditionVenteBean;
import ERP.Process.Commerciale.Vente.Facture_avoir_client.dao.Facture_avoir_clientDAO;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Detail_mvt_vente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
 
import ERP.Process.Commerciale.Vente.Facture_client.model.MvtVente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;


@Repository
public class Facture_clientDAO extends  GenericWeb    {
	
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
	public List<Facture_clientBean> doFindListFacture(Facture_clientBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List lisf= new ArrayList();
		try{
		     String requette=" select  bean   FROM    Facture_clientBean    bean    WHERE     1=1       ";
		     
			 if( !StringUtils.isEmpty(beanSearch.getFact_clt_id()) )  
				    requette+="   AND   bean.fact_clt_id ='"+beanSearch.getFact_clt_id()+"'        ";  
		     
			 if( !StringUtils.isEmpty(beanSearch.getFact_ref_id()) )  
				    requette+="   AND   bean.fact_ref_id ='"+beanSearch.getFact_ref_id()+"'        ";  
			 
			 if( !StringUtils.isEmpty(beanSearch.getClient().getClt_id()) )  
				    requette+="   AND   bean.client.clt_id = '"+beanSearch.getClient().getClt_id()+"'        ";
			 
			 if(  beanSearch.getTypefact().getType_fact_id() !=null )  
				    requette+="   AND   bean.typefact.type_fact_id =  "+beanSearch.getTypefact().getType_fact_id()+"       ";   
			 
			 if (beanSearch.getFact_date() != null) 
			    	requette += "   AND  bean.fact_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getFact_date())+"'        ";
			    
			if (   beanSearch.getFact_date2()!= null ) 
			    	requette += "   AND  bean.fact_date <=  '"+ProcessDate.getStringFormatDate(beanSearch.getFact_date2())+"'         ";
			 
			 if( !StringUtils.isEmpty(beanSearch.getCondition_select_mode()) )  
				    requette+="   "+beanSearch.getCondition_select_mode();  
			 
			 if( !StringUtils.isEmpty(beanSearch.getSelect_many_facture()) )  
				    requette+="   "+beanSearch.getSelect_many_facture();  
			 
			 if( beanSearch.getModeBean().getFct_id()!=null )  
				   requette+="   AND  bean.modeBean.fct_id  = "+beanSearch.getModeBean().getFct_id()+"        ";  
			 
			       requette+=this.setSocieteEtabFetch(beanSearch,"bean.etablissment", false);
			       
			       requette+= "   ORDER BY  date(bean.fact_date )   ASC      ";
			  
			   lisf= session.createQuery(requette).list();
			   commitTransaction(session);
	 } catch (Exception e) {  
	    	 rollbackTransaction(session) ;
	     throw e;  
	 } finally {  
		 session.close();  
	 }  
	 return  lisf;
	}

	
	@SuppressWarnings("unchecked")
	public List<MvtVente_articleBean> doFindList_detaille_mvt_fca_vente(Facture_clientBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List lisf= new ArrayList();
		 
		try{  
			 
		    String   requette  =" select  bean   FROM    MvtVente_articleBean    bean    WHERE     1=1       ";
			 
				     requette+="   AND   bean.fact.fact_clt_id = '"+beanSearch.getFact_clt_id()+"'        ";    
			 
			  
					   lisf= session.createQuery(requette).list();
						   
					   commitTransaction(session);
			 } catch (Exception e) {  
			    	 rollbackTransaction(session) ;
			     throw e;  
			 } finally {  
				 session.close();  
			 }  
			 return  lisf;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Detail_mvt_vente_articleBean> doFindList_detaille_mvt_Vente(	String  lesMvtVnete ) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory); 
		List lisf= new ArrayList();
		 
		try{  
			  String   requette  =" select  bean   FROM    Detail_mvt_vente_articleBean    bean    WHERE     1=1       ";
			  requette+="   AND   bean.pk.mvt_vente.mvt_vente_id in  ( "+lesMvtVnete+"   )   ";    
			  
					   lisf= session.createQuery(requette).list();
						   
					   commitTransaction(session);
			 } catch (Exception e) {  
			    	 rollbackTransaction(session) ;
			     throw e;  
			 } finally {  
				 session.close();  
			 }  
			 return  lisf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Det_Fact_ClientBean> doFindList_detaille_Facture(Facture_clientBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List lisf = new ArrayList();
		try{
		    String requette=" select  bean   FROM    Det_Fact_ClientBean    bean    WHERE     1=1       ";
			 
				    requette+="   AND   bean.pk.factclient.fact_clt_id ='"+beanSearch.getFact_clt_id()+"'        "; 
				    
				  
			   
			   lisf= session.createQuery(requette).list();
			   commitTransaction(session);
			   
				 
	 } catch (Exception e) {  
	    	 rollbackTransaction(session) ;
	     throw e;  
	 } finally {  
		 session.close();  
	 }  
	 return  lisf;
	 
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Det_Fact_ClientBean> doFindByCriteriaList_detaille_Facture(EditionVenteBean searchBean) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List lisf = new ArrayList();
		try{
		    String requette=" select  bean   FROM    Det_Fact_ClientBean    bean    WHERE     1=1       ";
			 
				   // requette+="   AND   bean.pk.factclient.fact_clt_id ='"+beanSearch.getFact_clt_id()+"'        "; 
				    
					 if (searchBean.getDate_debut() != null) 
					    	requette += "   AND  bean.pk.factclient.fact_date >= '"+ProcessDate.getStringFormatDate(searchBean.getDate_debut())+"'        ";
					    
					if (   searchBean.getDate_fin()!= null ) 
					    	requette += "   AND  bean.pk.factclient.fact_date <=  '"+ProcessDate.getStringFormatDate(searchBean.getDate_fin())+"'         ";
					
					       
					
					  requette+=this.setSocieteEtabFetch(searchBean," bean.pk.factclient.etablissment", false);
					  requette+= "   ORDER BY  date(bean.pk.factclient.fact_date )   ASC      ";
					 
			   lisf= session.createQuery(requette).list();
			   commitTransaction(session);
			   
				 
	 } catch (Exception e) {  
	    	 rollbackTransaction(session) ;
	     throw e;  
	 } finally {  
		 session.close();  
	 }  
	 return  lisf;
	 
	}
	
	
	public Boolean doSaveFacture(Facture_clientBean beanSaveS) throws Exception {
	    
		Session session =  openSessionHibernate(sessionFactory);
	    
		boolean result = false;
		try {
			//BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			Facture_clientBean beanSave= (Facture_clientBean) getObjectValueModel(FORM_BEAN );
			setBeanTrace(beanSave);
			beanSave.getTypefact().setType_fact_id(new Integer(1));
			daoNumSequentiel.getNumSeqSimple(beanSave,"fact_clt_id",session,"F");
			 
			Facture_clientBean beanTotal =(Facture_clientBean) getObjectValueModel(Facture_clientTemplate.BEAN_TOTAL_FACTURE_CLIENT);
			beanSave.setTotal_facture(   beanTotal.getTotal_facture());
			beanSave.setNet_a_payer( beanTotal.getNet_a_payer());
			beanSave.setFacture_remise(beanTotal.getFacture_remise());
			 
			if(beanSaveS.getModReg()!=null && beanSaveS.getModReg().getMod_id()!=null)
			beanSave.setModReg(beanSaveS.getModReg());
			else
		    beanSave.setModReg(null);
			
			
			beanSave.setFact_ref_id(beanSaveS.getFact_ref_id());
			beanSave.setCpt_bank(beanSaveS.getCpt_bank());
			beanSave.getEtat_reg().setData_id("fnon");
			beanSave.setObservation(beanSaveS.getObservation());
			beanSave.setFact_date(beanSaveS.getFact_date());
			beanSave.setFact_date_edition(beanSaveS.getFact_date_edition());
			session.save(beanSave);
			saveTrace(beanSave);
			List listOfmyData   =(List) getObjectValueModel( Facture_clientTemplate.LIST_DATA_DET_FACT);
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_Fact_ClientBean detBean=(Det_Fact_ClientBean) listOfmyData.get(i);
				
				if( detBean.getQuantite()==null) { continue; }
				if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
				
				MvtVente_articleBean  mvt_vente = detBean.getPk().getMvtVente();
				daoNumSequentiel.getNumSeqSimple(mvt_vente,"mvt_vente_id",session);
				 
				setBeanTrace(mvt_vente);
				session.save(mvt_vente);
				List lisdet=mvt_vente.getList_detail_mvt_vente();
				for (int j = 0; j < lisdet.size(); j++) {
					Detail_mvt_vente_articleBean  bDetail_mvt_vente = (Detail_mvt_vente_articleBean) lisdet.get(j);
					bDetail_mvt_vente.getPk().setMvt_vente(mvt_vente);
					session.save(bDetail_mvt_vente);
					session.createQuery( " UPDATE  ProcedureVenteBean b  set  " +
							"            b.modeBean.fct_id="+GenericActionBean.Fn_Facturer+" , b.factclient.fact_clt_id='"+beanSave.getFact_clt_id()+"' " +
									"     where   b.vente_id='"+bDetail_mvt_vente.getPk().getVente().getVente_id()+"' ").executeUpdate();
				}
				detBean.getPk().setFactclient(beanSave);
				detBean.getPk().setMvtVente(mvt_vente);
				session.save(detBean);
				result_detaille=true;
				 
			}
			if(!result_detaille)throwNewException("err_inser_deaill");
				
			//saveTraceVersion_Master_detailles(listOfmyData, Facture_clientTemplate.MapfieldBean_detaille, Facture_clientTemplate.id_entite_detaille, Facture_clientTemplate.entite_detaille);
			 
			 
			
			
			result = true;
			commitTransaction(session);
		 } catch (Exception e) {  
			 result = false;
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
	 
	
	
	  public Boolean doExcuterTransaction(Facture_clientBean beanUpda) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
	 		boolean result = false;
	 		try {
	 			 Facture_clientBean  bean= (Facture_clientBean) getObjectValueModel(FORM_BEAN);
	 			 Facture_clientBean  beanConfirm= (Facture_clientBean) ProcessUtil.cloneObject(bean);
	 			 
	 			 this.setUpdateValueFieldTraceOject(beanConfirm);
	 			beanConfirm.getEtat_reg().setData_id("fnon");
	 			 session.saveOrUpdate(beanConfirm);
	 			 this.saveTrace(beanConfirm);
	 			 result = true;
	 			 commitTransaction(session);
			 } catch (Exception e) {  
				 result = false;
			    	 rollbackTransaction(session) ;
			     throw e;  
			 } finally {  
				 session.close();  
			 }  
			return result;
	 		 
	 	}
	  
	  
	public Boolean doDeleteFacture(Facture_clientBean beanDelete)  throws Exception  {
		Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			Facture_clientBean   beandelfact = (Facture_clientBean) getObjectValueModel(FORM_BEAN);

			List<Det_Fact_ClientBean> list_det       = (List<Det_Fact_ClientBean>)  getObjectValueModel(Facture_clientTemplate.LIST_DATA_DET_FACT);
			
			List<MvtVente_articleBean> List_det_mvt  = (List<MvtVente_articleBean>) getObjectValueModel(Facture_clientTemplate.LIST_DATA_DET_FACT_MVT_VENTE);
			
			if(list_det!=null  &&  list_det.size()>0)
			for (int i = 0; i < list_det.size(); i++) {
				Det_Fact_ClientBean detFacture=(Det_Fact_ClientBean) list_det.get(i);
					      String   requette2  =" select  bean   FROM    Detail_mvt_vente_articleBean    bean    WHERE     1=1       ";
					      requette2+="   AND   bean.pk.mvt_vente.mvt_vente_id = '"+detFacture.getPk().getMvtVente().getMvt_vente_id()+"'        ";    
						   List <Detail_mvt_vente_articleBean> lisf= session.createQuery(requette2).list();
						   for (int j = 0; j < lisf.size(); j++) {
							   Detail_mvt_vente_articleBean detMvtVenteBean=lisf.get(j);
							   session.createQuery(" UPDATE  ProcedureVenteBean b  " +
										"   set  b.factclient.fact_clt_id= NULL  ,  b.modeBean.fct_id="+GenericActionBean.Fn_Confirmer+"   " +
												"  where      b.vente_id='"+detMvtVenteBean.getPk().getVente().getVente_id()+"' ").executeUpdate(); 
							  
							   session.delete(detMvtVenteBean);
							   
				}
				
			    if(list_det!=null  &&  list_det.size()>0) {
			    	 session.delete(detFacture);
				     session.flush();
				     session.clear();
				     session.delete(detFacture.getPk().getMvtVente());
			    }
			    
				
			}
			
			 
			
			if(list_det!=null  &&  list_det.size()>0) {	 
				
				saveTraceVersion_Master_detailles(list_det,
						  Facture_clientTemplate.MapfieldBean_detaille,
						  Facture_clientTemplate.id_entite_detaille,
						  Facture_clientTemplate.entite_detaille);
			}
			
			session.delete(beandelfact);
			saveTrace(beandelfact);
			result = true;
			commitTransaction(session);
		 } catch (Exception e) {  
			 result = false;
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	    
	}
}
