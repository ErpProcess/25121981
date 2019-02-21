package ERP.Process.Commerciale.Achat.Facture_Fournisseur.dao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Det_Fact_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Detail_mvt_achat_factureBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Mvt_achat_factureBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.template.Facture_FournisseurTemplate;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Detail_mvt_vente_articleBean;
 
 
 
 
@Repository
public class Facture_FournisseurDAO extends  GenericWeb    {
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
	public List<Facture_FournisseurBean> doFindListFacture_Fournisseur(Facture_FournisseurBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List lisf= new ArrayList();
		try{
			
		    String requette=" select  bean   FROM    Facture_FournisseurBean    bean    WHERE     1=1       ";
		    
		 if( !StringUtils.isEmpty(beanSearch.getFact_frs_id()) )  
			       requette+="   AND   bean.fact_frs_id = '"+beanSearch.getFact_frs_id()+"'        ";   
		    
		 if( !StringUtils.isEmpty(beanSearch.getFrs().getFrs_id()) )  
			       requette+="   AND   bean.frs.frs_id = '"+beanSearch.getFrs().getFrs_id()+"'        ";    
		 
		 if(  beanSearch.getTypefact().getType_fact_id() !=null )  
			    requette+="   AND   bean.typefact.type_fact_id =  "+beanSearch.getTypefact().getType_fact_id()+"       ";
		 
		 if (beanSearch.getFact_date() != null) 
		    	requette += "   AND  bean.fact_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getFact_date())+"'        ";
		    
		if (   beanSearch.getFact_date2()!= null ) 
		    	requette += "   AND  bean.fact_date <=  '"+ProcessDate.getStringFormatDate(beanSearch.getFact_date2())+"'         ";
		 
		 if(  !StringUtils.isEmpty(beanSearch.getCondition_select_mode()) )  
			    requette+="   "+beanSearch.getCondition_select_mode();  
		 
		 if( !StringUtils.isEmpty(beanSearch.getSelect_many_facture()) )  
			    requette+="   "+beanSearch.getSelect_many_facture();  
		 
		 if( beanSearch.getModeBean().getFct_id()!=null )  
			    requette+="   AND  bean.modeBean.fct_id  = "+beanSearch.getModeBean().getFct_id()+"        ";  
		 
		        requette+=this.setSocieteEtabFetch(beanSearch,"bean.etablissment", false);
		  
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
	
	
 	public List findImageFile(BigDecimal file_id) {
		Session session =  openSessionHibernate(sessionFactory);
		List lisf= new ArrayList();
		try{
		    String requette=" select  bean   FROM    FileFactureFournisseur    bean    WHERE     bean.file_id="+file_id+"       ";
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
	public List<Det_Fact_FournisseurBean> doFetchDetailfromServer(Facture_FournisseurBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List lisf= new ArrayList();
		try{
			
		    String requette=" select  bean   FROM    Det_Fact_FournisseurBean    bean    WHERE     1=1       ";
		    if( !StringUtils.isEmpty(beanSearch.getFact_frs_id()) )  
			    requette+="   AND   bean.pk.facture_frs.fact_frs_id = '"+beanSearch.getFact_frs_id()+"'        "; 
		     
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
	
	
	
	
	
	
	
	public Boolean doSaveFacture_Fournisseur(Facture_FournisseurBean beanSaveS) throws Exception {
		
		
		
		Session session =  openSessionHibernate(sessionFactory);
	    
		boolean result = false;
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			Facture_FournisseurBean beanSave= (Facture_FournisseurBean) getObjectValueModel(FORM_BEAN );
			setBeanTrace(beanSave);
			beanSave.getTypefact().setType_fact_id(new Integer(1));
			daoNumSequentiel.getNumSeqSimple(beanSave,"fact_frs_id",session,"F");
			
			FileFactureFournisseur insertBean =(FileFactureFournisseur) getObjectValueModel("MultipartFile");
			if(insertBean!=null){
				insertBean.setFact_frs_id(beanSave.getFact_frs_id());
				this.doSaveFileFacture_Fournisseur(insertBean);
				FileOutputStream stream = new FileOutputStream("D://" + insertBean.getFile_name());
				try {
				    stream.write(insertBean.getFile_byte());
				} finally {
				    stream.close();
				}
			}
			beanSave.setMyFile(insertBean);
			 
			Facture_FournisseurBean beanTotal =(Facture_FournisseurBean) getObjectValueModel(Facture_FournisseurTemplate.BEAN_TOTAL_FACTURE_FRS);
			beanSave.setTotal_facture(   beanTotal.getTotal_facture());
			beanSave.setNet_a_payer( beanTotal.getNet_a_payer());
			beanSave.setFacture_remise(beanTotal.getFacture_remise());
			beanSave.setTotal_ht_fact(beanTotal.getTotal_ht_fact());
			beanSave.setTotal_tva_fact(beanTotal.getTotal_tva_fact());
			beanSave.getEtat_reg().setData_id("fnon");
			session.save(beanSave);
			saveTrace(beanSave);
			
			List listOfmyData   =(List) getObjectValueModel( Facture_FournisseurTemplate.LIST_DATA_DET_FACT);
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_Fact_FournisseurBean detBean=(Det_Fact_FournisseurBean) listOfmyData.get(i);
				
				if( detBean.getQuantite()==null) { continue; }
				if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
				
				Mvt_achat_factureBean mvt_achat = detBean.getPk().getMvt_achat();
				daoNumSequentiel.getNumSeqSimple(mvt_achat,"mvt_achat_id",session);
				 
				setBeanTrace(mvt_achat);
				session.save(mvt_achat);
				List lisdet=mvt_achat.getList_detail_mvt_vente();
				for (int j = 0; j < lisdet.size(); j++) {
					Detail_mvt_achat_factureBean  bDetail_mvt_achat = (Detail_mvt_achat_factureBean) lisdet.get(j);
					bDetail_mvt_achat.getPk().setMvt_achat(mvt_achat);
					session.save(bDetail_mvt_achat);
				}
				detBean.getPk().setFacture_frs(beanSave);
				detBean.getPk().setMvt_achat(mvt_achat);
				session.save(detBean);
				result_detaille=true;
				 
			}
			if(!result_detaille)throwNewException("err_inser_deaill");
			
			String   les_reception =(String) getObjectValueModel("les_reception"  )	 ;
			
			if(les_reception.length()>0)   
			session.createQuery(" UPDATE   Reception_achatBean b  set  							    " +
					        "              b.modeBean.fct_id="+GenericActionBean.Fn_Facturer+"      " +
							"     where    b.achat_id  in ("+les_reception+")   ").executeUpdate();
			
			
			
				
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
	
	
	@SuppressWarnings("unchecked")
	public List<Detail_mvt_achat_factureBean> doFindList_detaille_mvt_achat(	String  lesMvt_achat) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory); 
		List lisf= new ArrayList();
		 
		try{  
			  String   requette  =" select  bean   FROM    Detail_mvt_achat_factureBean    bean    WHERE     1=1       ";
			  requette+="   AND   bean.pk.mvt_achat.mvt_achat_id in  ( "+lesMvt_achat+"   )   ";    
			  
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
	
	
	public Boolean doSaveFileFacture_Fournisseur(FileFactureFournisseur fileFactureFournisseur)  throws Exception { 
	    Session session =  openSessionHibernate(sessionFactory);
 		boolean result = false;
 		try {
 			 setBeanTrace(fileFactureFournisseur);
 			 session.saveOrUpdate(fileFactureFournisseur);
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
	
	
	public Boolean doUpdateFacture_Fournisseur(Facture_FournisseurBean beanUpdate)  throws Exception { 
		    Session session =  openSessionHibernate(sessionFactory);
	 		boolean result = false;
	 		try {
	 			 Facture_FournisseurBean  beanFrs= (Facture_FournisseurBean) getObjectValueModel(FORM_BEAN);
	 			 Facture_FournisseurBean  beanFrsRT=  (Facture_FournisseurBean) ProcessUtil.cloneObject(beanFrs);
	 			 
	 			 setUpdateValueFieldTraceOject( beanFrsRT) ;
	 			 session.saveOrUpdate(beanFrsRT);
	 			 beanFrsRT.getEtat_reg().setData_id("fnon");
	 			 this.saveTrace(beanFrsRT);
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
	public Boolean doDeleteFacture_Fournisseur(Facture_FournisseurBean beanDelete)  throws Exception  {
		
		
		
		
		
		
		
		Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			Facture_FournisseurBean   beandelfact = (Facture_FournisseurBean) getObjectValueModel(FORM_BEAN);

			List<Det_Fact_FournisseurBean> List_det       = (List<Det_Fact_FournisseurBean>)  getObjectValueModel(Facture_FournisseurTemplate.LIST_DATA_DET_FACT);
			
			
			
			
			for (int i = 0; i < List_det.size(); i++) {
				Det_Fact_FournisseurBean detFacture=(Det_Fact_FournisseurBean) List_det.get(i);
					      String   requette2  =" select  bean   FROM    Detail_mvt_achat_factureBean    bean    WHERE     1=1       ";
					      requette2+="   AND   bean.pk.mvt_achat.mvt_achat_id = '"+detFacture.getPk().getMvt_achat().getMvt_achat_id()+"'        ";    
						   List <Detail_mvt_achat_factureBean> lisf= session.createQuery(requette2).list();
						   for (int j = 0; j < lisf.size(); j++) {
							   Detail_mvt_achat_factureBean detMvtVenteBean=lisf.get(j);
							   session.createQuery(" UPDATE  Reception_achatBean b  " +
										"   set  b.modeBean.fct_id="+GenericActionBean.Fn_Confirmer+"   " +
												"  where   b.achat_id='"+detMvtVenteBean.getPk().getDocument_com_id()+"' ").executeUpdate(); 
							  
							   session.delete(detMvtVenteBean);
							   
				}
						   
			     session.delete(detFacture);
			     session.flush();
			     session.clear();
			     session.delete(detFacture.getPk().getMvt_achat());
				
			}
			
			 
			
			 
			saveTraceVersion_Master_detailles(List_det,
					Facture_FournisseurTemplate.MapfieldBean_detaille,
					Facture_FournisseurTemplate.id_entite_detaille,
					Facture_FournisseurTemplate.entite_detaille);
			if(beandelfact.getMyFile()!=null && beandelfact.getMyFile().getFile_id()!=null)
			session.delete(beandelfact.getMyFile());
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
