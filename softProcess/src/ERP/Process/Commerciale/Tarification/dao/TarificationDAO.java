package ERP.Process.Commerciale.Tarification.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.template.TarificationTemplate;
import ERP.Process.Commerciale.Vente.Commandeclient.model.DetCmdCltBean;
import ERP.Process.Commerciale.Vente.Devis.model.DetDevisBean;
import ERP.Process.Commerciale.Vente.Devis.model.DevisBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Detail_mvt_vente_articleBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessWebUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;


@Repository
public class TarificationDAO extends  GenericWeb    {
	
	
	@Autowired
	private SessionFactory sessionFactory;
    public void setSessionFactoryTwo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    } 
	
	 private NumSequentielDAO daoNumSequentiel;
		@Autowired
		public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
			this.daoNumSequentiel = daoNumSequentiel;
		}
		
		
		
	@SuppressWarnings("unchecked")
	public List<TarificationBean> doFindListTarification(TarificationBean beanSearch) throws Exception {
		
		
		 Session session =  openSessionHibernate(sessionFactory);
	 	 List<TarificationBean>   lisf = new ArrayList<TarificationBean>();
	 		try {
		
		 String requette=" select  bean   FROM    TarificationBean    bean    WHERE     1=1       ";
		 
		 if( !StringUtils.isEmpty(beanSearch.getTarif_vente_id()) )  
			    requette+="   AND   bean.tarif_vente_id = '"+beanSearch.getTarif_vente_id()+"'        ";
		 
		 if( !StringUtils.isEmpty(beanSearch.getFkCode_barre().getPk().getCode_barre()) )  
			    requette+="   AND   bean.fkCode_barre.pk.code_barre = '"+beanSearch.getFkCode_barre().getPk().getCode_barre()+"'        ";
		 
		 if( !StringUtils.isEmpty(beanSearch.getFkCode_barre().getPk().getAr_bean().getFam_art().getFam_id()) )  
			    requette+="   AND   bean.fkCode_barre.pk.ar_bean.fam_art.fam_id = '"+beanSearch.getFkCode_barre().getPk().getAr_bean().getFam_art().getFam_id()+"'        ";
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getOperation()) &&    beanSearch.getTaux_remise()!=null  )  
			    requette+="   AND   bean.taux_remise  "+beanSearch.getOperation()+"  "+beanSearch.getTaux_remise()+"       ";
		 
		 
		 if( beanSearch.getGroupe()!=null  &&  beanSearch.getGroupe().getType_trf_id()!= null  )  
			    requette+="   AND   bean.groupe.type_trf_id ="+beanSearch.getGroupe().getType_trf_id()+"        ";
		 
		 if( beanSearch.getDepot()!=null  &&  beanSearch.getDepot().getDepot_id()!= null  )  
			    requette+="   AND   bean.depot.depot_id ="+beanSearch.getDepot().getDepot_id()+"        ";
		 
		 if(  !StringUtils.isEmpty(beanSearch.getNum_serie())  )  
			    requette+="   AND   bean.num_serie ='"+beanSearch.getNum_serie()+"'        ";
		 
		 
		 if(beanSearch.getCout() !=null  &&  beanSearch.getCout().getTarif_unit_article()!= null  )  
			    requette+="   AND   bean.cout.tarif_unit_article ="+beanSearch.getCout().getTarif_unit_article()+"        ";
		 
		 if( beanSearch.getTarif_unit_vente()!= null  )  
			    requette+="   AND   bean.tarif_unit_vente ="+beanSearch.getTarif_unit_vente()+"        ";
		 
		 if(  beanSearch.getTarif_lot()!= null  )  
			    requette+="   AND   bean.tarif_lot ="+beanSearch.getTarif_lot()+"        ";
		 
		 if (beanSearch.getDate_trf() != null)
				requette += "   AND  bean.date_trf >='"+ProcessDate.getStringFormatDate(beanSearch.getDate_trf())+"'        ";

	     if (beanSearch.getDate_trf2() != null)
				requette += "   AND  bean.date_trf <='"+ProcessDate.getStringFormatDate(beanSearch.getDate_trf2())+"'        ";
	    
		 if(   !StringUtils.isEmpty(beanSearch.getCondition_select_mode())   )  
			    requette+="    "+beanSearch.getCondition_select_mode()+"        ";
		 
		 if(  !StringUtils.isEmpty(beanSearch.getMethode_prix())  )  
			    requette+="   "+beanSearch.getMethode_prix()+"        "; 
		 
		    if (beanSearch.getDernierPrix() != null  &&  beanSearch.getDernierPrix().equals(true) ){
			
			 Date dateToSelect=BDateTime.getDateActuel();
			 if(beanSearch.getDate_trf3()!=null) 
			     dateToSelect=beanSearch.getDate_trf3();
			   
			    requette+="    AND           " +
			    		"                bean.date_trf IN ( select  MAX(BA.date_trf)  from  TarificationBean BA           " +
			    		"        where   bean.fkCode_barre.pk.code_barre =  BA.fkCode_barre.pk.code_barre     ";
			    
			 if( !StringUtils.isEmpty(beanSearch.getFkCode_barre().getPk().getCode_barre()) )  
				requette+="   AND   BA.fkCode_barre.pk.code_barre = '"+beanSearch.getFkCode_barre().getPk().getCode_barre()+"'        ";
			 
			 if(  !StringUtils.isEmpty(beanSearch.getMethode_prix2())  )
				   requette+="   "+beanSearch.getMethode_prix2()+"        "; 
			 
			 if( beanSearch.getGroupe().getType_trf_id()!= null  )  
				    requette+="   AND   BA.groupe.type_trf_id ="+beanSearch.getGroupe().getType_trf_id()+"        ";
			    
			    requette+=  ProcessWebUtil.doChargerSociete_Article("BA.fkCode_barre");
			    
			    requette+=    "   AND   BA.date_trf  <='"+ProcessDate.getStringFormatDate(dateToSelect)+"'      )      " ;
			      
              }
		         requette+=  ProcessWebUtil.doChargerSociete_Article("bean.fkCode_barre");
		    
		     	if(!StringUtils.isEmpty(beanSearch.getCondition_cathegorie())) 
					requette+="     AND  bean.fkCode_barre.pk.ar_bean.cathegorie.data_id in ("+beanSearch.getCondition_cathegorie()+") ";
		 
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
	public String  doFetchTarif(String beanSearch) throws Exception {
		 
		 
     Session session =  openSessionHibernate(sessionFactory);
	 String string="";  
	 		try {
	 			
	  List <DetProcedureVenteBean>  lisDetProcedure = session.createQuery(" select  bean   FROM DetProcedureVenteBean bean     WHERE  bean.tarif.tarif_vente_id='"+beanSearch+"'       ").list();
	  for (DetProcedureVenteBean bean:lisDetProcedure ) {
		  string=string+"Vente N°:"+bean.getPk().getVente().getVente_id()+"<br>";
	  }
	  
	  List <DetCmdCltBean>  lisDetCmdClt = session.createQuery(" select  bean   FROM DetCmdCltBean         bean     WHERE  bean.tarif.tarif_vente_id='"+beanSearch+"'       ").list();
	  for (DetCmdCltBean bean:lisDetCmdClt ) {
		  string=string+"Commande N°:"+bean.getPk().getCmd().getCmd_id()+"<br>";
	  }
	  
	  List <DevisBean>  lisDetDevis = session.createQuery(" select   x   FROM DetDevisBean   bean ,DevisBean x     WHERE  x.devis_id=bean.pk.devis.devis_id    and   bean.tarif.tarif_vente_id='"+beanSearch+"'       ").list();
	  for (DevisBean bean:lisDetDevis ) {
		  string=string+"Devis N°:"+bean.getDevis_id()+"<br>";
	  }
	  
	  /*List <Detail_mvt_vente_articleBean>  lisDet_mvt_vente = session.createQuery(" select bean FROM Detail_mvt_vente_articleBean bean WHERE  bean.tarif.tarif_vente_id='"+beanSearch.getTarif_vente_id()+"'       ").list();
	  for (Detail_mvt_vente_articleBean bean:lisDet_mvt_vente ) {
		  string=string+"Devis N°:"+bean.getPk().getDevis().getDevis_id()+"<br>";
	  }*/
		
	  
	  
	 //commitTransaction(session);
			
	 } catch (Exception e) {  
	     if (sessionIsTrue(session)) 
	    	 rollbackTransaction(session) ;
	     throw e;  
	 } finally {  
		 session.close();  
	 }  
	 return  string;
		
     }
	 
	
	
			
			@SuppressWarnings("unchecked")
			public List<TarificationBean> doFind_DateMax_TarificationVente(TarificationBean beanSearch) throws Exception {
				
				 Session session =  openSessionHibernate(sessionFactory);
			 		List<TarificationBean>   lisf = new ArrayList<TarificationBean>();
			 		try {
				
			 			
			 			
			    String requette=" select  bean   FROM    TarificationBean    bean    WHERE     1=1       ";
			 
			 
			    if(  beanSearch.getDepot().getDepot_id()!=null )  
				    requette+="    AND      bean.depot.depot_id = "+beanSearch.getDepot().getDepot_id()+"       ";
			 
			    	requette+=setSocieteEtabForArticle("bean.fkCode_barre", false);
			   
				    requette+="    AND           " +
				    		"               bean.date_trf IN ( select  MAX(BA.date_trf)  from  TarificationBean BA           " +
				    		"       where   bean.fkCode_barre.pk.code_barre =  BA.fkCode_barre.pk.code_barre   " +
				    		"              " ;
				 
				    requette+=setSocieteEtabForArticle("BA.fkCode_barre", false);
				    requette+="       AND   BA.date_trf  <='"+ProcessDate.getStringFormatDate(beanSearch.getDate_trf())+"'      )      " ;
			 
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
	
	
	public Boolean doSaveTarification(TarificationBean beanSave) throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try { 
			this.setBeanTrace(beanSave);
			
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 HashMap  mapArticle  =(HashMap) getObjectValueModel(  "mapArticle"  );
			 Code_barreBean fkCode_barre=(Code_barreBean) mapArticle.get(beanSave.getFkCode_barre().getPk().getCode_barre());
			 beanSave.setFkCode_barre(fkCode_barre);
			 beanSave.setDepot(null);
			 beanSave.setTarif_lot(null);
			 beanSave.setNum_serie(null);
			 daoNumSequentiel.getNumSeqSimple(beanSave,"tarif_vente_id");
			 if(beanSave.getCout().getTarif_prim_id()==null || beanSave.getCout().getTarif_prim_id().equals(""))
			 beanSave.setCout(null);
			 session.save(beanSave);
			 
// 
//			 
//			 session.createQuery( 
//					"  UPDATE  Code_barreBean  c  set    c.prix_vente="+beanSave.getTarif_unit_vente()+"  ,  c.prix_ventettc="+mode+"   "
//			 		+ "   where   c.pk.code_barre='"+beanSave.getFkCode_barre().getPk().getCode_barre()+"'        "
//			 	    + "     AND   c.pk.ar_bean.pk_article.ar_id ='"+beanSave.getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'   "
//			 	    + "     AND   c.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id ='"+beanSave.getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'   "
//			        + "     AND   c.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id ='"+beanSave.getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'   "
//			 	    + "        "  ).executeUpdate();
			 
			 /*if(bs.getFct_id().equals(GenericActionBean.Fn_Realiser)){
			  *  Code_barreBean fkCode_barre=(Code_barreBean) mapArticle.get(beanSave.getFkCode_barre().getPk().getCode_barre());
			 beanSave.setFkCode_barre(fkCode_barre);
			 beanSave.setDepot(null);
			 beanSave.setTarif_lot(null);
			 beanSave.setNum_serie(null);
			 daoNumSequentiel.getNumSeqSimple(beanSave,"tarif_vente_id");
			 if(beanSave.getCout().getTarif_prim_id()==null || beanSave.getCout().getTarif_prim_id().equals(""))
			 beanSave.setCout(null);
			 session.save(beanSave);
			}else{
				List listmaxTarific=(List) getObjectValueModel("listmaxTarific" );
				Code_barreBean  aBean= (Code_barreBean) getObjectValueModel(    TarificationTemplate .BEAN_CODEBARRE_ARTICL );
				for (int i = 0; i < listmaxTarific.size(); i++) {
					 TarificationBean tBean= (TarificationBean) listmaxTarific.get(i);
					 tBean.setDate_trf(beanSave.getDate_trf());
					 tBean.setTvaBean(beanSave.getTvaBean());
					 tBean.setFkCode_barre(aBean);
					 setPk_Soc_Etab(tBean, "pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id", "pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id");
					 session.save(tBean);
				} } */
				
			 
			
			
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
	public Boolean doUpdateTarification(TarificationBean beanUpdate)  throws Exception { 
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			 TarificationBean beanUpda =(TarificationBean) getObjectValueModel(FORM_BEAN);
			 beanUpdate.setFkCode_barre(beanUpda.getFkCode_barre());
			 
			 if(beanUpdate.getDepot()==null || beanUpdate.getDepot().getDepot_id()==null)
			 beanUpdate.setDepot(null);
			 if(beanUpdate.getNum_serie()!=null &&  !beanUpdate.getNum_serie().equals("")){
				 beanUpdate.setTarif_lot(true);
			 }else{
				 beanUpdate.setTarif_lot(null);
				 beanUpdate.setNum_serie(null);
			 }
			 
			if(beanUpdate.getCout().getTarif_prim_id()==null || beanUpdate.getCout().getTarif_prim_id().equals(""))
			beanUpdate.setCout(null);
			setUpdateValueFieldTraceOject(beanUpdate);
			session.update(beanUpdate);
			this.saveTrace(beanUpdate);
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
	public Boolean doConfirmRowData(TarificationBean beanUpdate)  throws Exception { 
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			//setIdBean((TarificationBean) getObjectValueModel(FORM_BEAN), beanUpdate, TarificationTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			beanUpdate.setDepot(null);
			beanUpdate.setTarif_lot(null);
			beanUpdate.setNum_serie(null);
			session.update(beanUpdate);
			//this.saveTrace(beanUpdate);
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
	
	public Boolean doDeleteTarification(TarificationBean beanDelete)  throws Exception  {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			session.delete(beanDelete);
	        //this.saveTrace(beanDelete);
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
	
	
	@SuppressWarnings("unchecked")
	public HashMap doFindListTarificationVenteArticle_DateMax(TarificationBean beanSearch,Session session) throws Exception {
		 
		 HashMap mapresul  =  new HashMap();
		 HashMap mapTarif  =  new HashMap();
		 mapresul.put("mapTarif", mapTarif);
		 mapresul.put("list_des_reference", new ArrayList()); 
		 try {
			
	
		 
	     String requette="   select  bean   FROM    TarificationBean    bean    WHERE     1=1       ";
	  
		    requette+="      AND      bean.groupe.type_trf_id="+beanSearch.getGroupe().getType_trf_id()+"   " ;
		    
		    requette+=       setModeOperation("bean.modeBean", GenericActionBean.Fn_Confirmer);
		    
		    requette+=setSocieteEtabForArticle("bean.fkCode_barre", false);
		            
		    requette+="    AND      bean.date_trf IN ( select  MAX(BA.date_trf)  from  TarificationBean BA               " +
		    		"       where   bean.fkCode_barre.pk.code_barre =  BA.fkCode_barre.pk.code_barre                     " +
		    		"         AND   bean.groupe.type_trf_id=BA.groupe.type_trf_id                                        ";
		    
		    requette+=setSocieteEtabForArticle("BA.fkCode_barre", false);
		    
		    
		    requette+=       setModeOperation("BA.modeBean", GenericActionBean.Fn_Confirmer);
		    
		    requette+="         AND   BA.date_trf  <= '"+ProcessDate.getStringFormatDate(beanSearch.getDate_trf())+"'   )  "; 
		    
		    List<TarificationBean>  list_TarificationVente= session.createQuery(requette).list();
		      mapTarif  =	ProcessUtil.getHashMap_code_bean(list_TarificationVente, "fkCode_barre.pk.code_barre");
		    List list_des_reference= new ArrayList();
		    for (int i = 0; i < list_TarificationVente.size(); i++) {
		    	TarificationBean tPrtvArticleBean =list_TarificationVente.get(i);
		    	list_des_reference.add(tPrtvArticleBean.getFkCode_barre());
			}
		    mapresul.put("mapTarif", mapTarif);
		    mapresul.put("list_des_reference", list_des_reference); 
			} catch (Exception e) {
				throw e;
			}
		return mapresul;
}
	
}
