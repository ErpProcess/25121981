package ERP.Process.Commerciale.Vente.ProcedureVente.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.MouvementStock.service.MouvementStockService;
import ERP.Process.Commerciale.Stock.Stock_article.dao.Stock_articleDAO;
import ERP.Process.Commerciale.Stock.Stock_article.model.IncidentStock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.MouvementStockBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Detail_mvt_vente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.MvtVente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.FournitureVente.model.DetFournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.model.FournitureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DeriverOperationVente;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.RetourVente.model.DetRetourVenteBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.Incident_det_retour_venteBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
import ERP.Process.Commerciale.Vente.Service.model.DetServiceBean;
import ERP.Process.Commerciale.Vente.Service.model.ServiceBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

@Repository
public class ProcedureVenteDAO extends  GenericWeb    {
	
	
	
	private Stock_articleDAO daoStock_article;
	@Autowired
	public void setDaoStock_article(Stock_articleDAO daoStock_article) {
		this.daoStock_article = daoStock_article;
	}
 
	  
	private DocumentLotService serviceDocumentLot;
	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
		this.serviceDocumentLot = serviceDocumentLot;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	private SessionFactory sessionFactory;

	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@SuppressWarnings("unchecked")
	public List<DetProcedureVenteBean> doFindDetaille_ListProcedureVente(ProcedureVenteBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		
		List<DetProcedureVenteBean>   lisf = new ArrayList<DetProcedureVenteBean>();
		try {
		    String requette   =  "   select  bean   FROM    DetProcedureVenteBean    bean    WHERE     1=1       ";
		    
		    
		    if (!StringUtils.isEmpty(beanSearch.getVente_id()))
				   requette += "   AND   bean.pk.vente.vente_id ='"+beanSearch.getVente_id()+"'        ";
		    
		 
		    
		    if (!StringUtils.isEmpty(beanSearch.getCondition_de_prix()))
				   requette += "   "+beanSearch.getCondition_de_prix()+"         ";
		    
		    
		 
				    
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
	public List<DetProcedureVenteBean> doFindDetailleListProcedureVenteEdition(ProcedureVenteBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		
		List<DetProcedureVenteBean>   lisf = new ArrayList<DetProcedureVenteBean>();
		try {
		    String requette   =  "   select  bean   FROM    DetProcedureVenteBean    bean    WHERE     1=1       ";
		    
		    
		    if (!StringUtils.isEmpty(beanSearch.getVente_id()))
				   requette += "   AND   bean.pk.vente.vente_id ='"+beanSearch.getVente_id()+"'        ";
		    
			 if (beanSearch.getVente_date() != null) 
			    	requette += "   AND  bean.pk.vente.vente_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getVente_date())+"'        ";
			    
			if (   beanSearch.getVente_date2()!= null ) 
			    	requette += "   AND  bean.pk.vente.vente_date <=  '"+ProcessDate.getStringFormatDate(beanSearch.getVente_date2())+"'         ";
		    
		  
		    requette +=this.setSocieteEtabFetch(beanSearch,"bean.pk.vente.fk_etab_Bean", false);
				    
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
	public List<ProcedureVenteBean> doFindListProcedureVente(ProcedureVenteBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<ProcedureVenteBean>   lisf = new ArrayList<ProcedureVenteBean>();
		try {
		    String requette=" select  bean   FROM    ProcedureVenteBean    bean    WHERE     1=1       ";
		    
		    if (!StringUtils.isEmpty(beanSearch.getVente_id()))
				requette += "   AND   bean.vente_id = '"+beanSearch.getVente_id()+"'        ";
			if (!StringUtils.isEmpty(beanSearch.getVente_libelle()))
				requette += "   AND   bean.vente_libelle ='"+ beanSearch.getVente_libelle()+"'        ";

			if (!StringUtils.isEmpty(beanSearch.getClient().getClt_id()))
				requette += "   AND   bean.client.clt_id ='"+beanSearch.getClient().getClt_id()+"'        ";

			if (beanSearch.getVente_date() != null)
				requette += "   AND  bean.vente_date >='"+ProcessDate.getStringFormatDate(beanSearch.getVente_date())+"'        ";

			if (beanSearch.getVente_date2() != null)
				requette += "   AND  bean.vente_date <='"+ProcessDate.getStringFormatDate(beanSearch.getVente_date2())+"'        ";

			if (!StringUtils.isEmpty(beanSearch.getCondition_select_mode()))
				requette += "" + beanSearch.getCondition_select_mode();
			
			if (!StringUtils.isEmpty(beanSearch.getCondition_select_vente_non_confirmer()))
				requette += "" + beanSearch.getCondition_select_vente_non_confirmer();
			    requette +=this.setSocieteEtabFetch(beanSearch,"bean.fk_etab_Bean", false);
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
	
	
	
	public Boolean doSaveProcedureVente(ProcedureVenteBean beanSave, FournitureVenteBean    fVenteBean , ServiceBean    service) throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			this.setBeanTrace(beanSave);
			List listOfmyData=(List) getObjectValueModel( ProcedureVenteTemplate.LIST_EDITABLE_VENTE);
			ProcedureVenteBean beanTotal =(ProcedureVenteBean) getObjectValueModel(ProcedureVenteTemplate.BEAN_TOTAL);
			beanSave.setVente_remise(beanTotal.getVente_remise());
			beanSave.setVente_mnt_ht(beanTotal.getVente_mnt_ht());
			beanSave.setVente_mnt_tva(beanTotal.getVente_mnt_tva());
			
			 if(bs.getFct_id().equals(GenericActionBean.Fn_Facturer)){
			  Double timbre=ProcessFormatNbr.FormatDouble_ParameterChiffre(bs.getSociete().getMontant_timbre_fiscal(),"0.000");
			  Double getVente_mnt_total=  ProcessNumber.SOUSTRACTION(beanTotal.getVente_mnt_total(), timbre )   ;
			  beanSave.setVente_mnt_total(getVente_mnt_total);
			 }else {
				 beanSave.setVente_mnt_total(beanTotal.getVente_mnt_total()); 
			 }
			beanSave.setMarge_benefice_vente(beanTotal.getMarge_benefice_vente());
			session.save(beanSave);
//			HashMap  map_deriver_vente  =(HashMap) getObjectValueModel(ProcedureVenteTemplate.MAP_DERIVER_VENTE);
//			if( map_deriver_vente!=null   &&  map_deriver_vente.size() >0 ) {
//				Set setMap_deriver_vente=map_deriver_vente.keySet();
//				for (Iterator iter = setMap_deriver_vente.iterator(); iter.hasNext();) {
//					String codeBarr = (String) iter.next();
//					DeriverOperationVente dVente = (DeriverOperationVente) map_deriver_vente.get(codeBarr);
//					if(dVente!=null ) {
//						this.setBeanTrace(dVente);
//						session.save(dVente);
//					}
//				}
//			}
//			
			//this.saveTraceVersion1( beanSave  , ProcedureVenteTemplate.MapfieldBean  , ProcedureVenteTemplate.id_entite_pricipale  , ProcedureVenteTemplate.entites);
			
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetProcedureVenteBean detBean=(DetProcedureVenteBean) listOfmyData.get(i);
				if( detBean.getQuantite()==null) { continue; }
				if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
				detBean.getPk().setVente(beanSave);
//				if( map_deriver_vente!=null   &&  map_deriver_vente.size() >0 ) {
//					DeriverOperationVente dVente = (DeriverOperationVente) map_deriver_vente.get(detBean.getPk().getFkcode_barre().getPk().getCode_barre());
//					if(dVente!=null  ) {
//						detBean.setDrv(dVente);
//					}
//				}
				if(detBean.isPrix_vente_is_changed()) {
					TarificationBean  tarifOrigin =   detBean.getTarif() ;
					TarificationBean  tarifNew = new TarificationBean();
					
					
					HashMap  mapclient  =  (HashMap)getObjectValueModel( ProcedureVenteTemplate.MAP_CLIENT_BEN);
					ClientBean  ben     =  (ClientBean) mapclient.get(beanSave.getClient().getClt_id());
					Type_tarificationBean groupe = new Type_tarificationBean();
					groupe.setType_trf_lib(BDateTime.getDateActuel_system()+" "+ProcessDate.getTime(new Date())+" "+ben.getClt_id()+"/"+ben.getClt_lib());
					setBeanTrace(groupe);
					session.save(groupe);
					
					tarifNew.setGroupe(groupe);
					tarifNew.setBean_cal(tarifOrigin.getBean_cal());
					tarifNew.setFkCode_barre(tarifOrigin.getFkCode_barre());
					tarifNew.setDevise(tarifOrigin.getDevise());
					tarifNew.setTvaBean(tarifOrigin.getTvaBean());
					tarifNew.setDepot(null);
					tarifNew.setDate_trf(beanSave.getVente_date());
					tarifNew.setTarif_unit_vente(tarifOrigin.getTarif_unit_vente());
					tarifNew.setValeur_de_laTva(ProcessNumber.getMontantTvaByMontantHT(tarifOrigin.getTarif_unit_vente(), tarifOrigin.getTvaBean(), new DeviseBean()));
					tarifNew.setTarif_unit_vente_tt(ProcessNumber.getMontantTTCByMontantHT(tarifOrigin.getTarif_unit_vente(), tarifOrigin.getTvaBean(),  new DeviseBean() ));
					tarifNew.setTarif_lot(null);
					tarifNew.setNum_serie(null);
					daoNumSequentiel.getNumSeqSimple(tarifNew,"tarif_vente_id",session);
					if(tarifOrigin.getCout()!=null && (tarifOrigin.getCout().getTarif_prim_id()==null || tarifOrigin.getCout().getTarif_prim_id().equals("")))
					tarifNew.setCout(null);
					this.setBeanTrace(tarifNew);
					session.save(tarifNew);
					
					detBean.setTarif(tarifNew);  
				}
					
				session.save(detBean);
				result_detaille=true;
			}
			
				 
			//this.saveTraceVersion_Master_detailles(listOfmyData, ProcedureVenteTemplate.MapfieldBean_detaille, ProcedureVenteTemplate.id_entite_detaille, ProcedureVenteTemplate.entite_detaille);
			 
			if( !StringUtils.isEmpty( beanSave.getCommande().getCmd_id()) )
			  session.createQuery("   UPDATE  CommandeclientBean b  set  b.modeBean.fct_id="+bs.getFct_id()+"  " +
			  		"                 where   b.cmd_id='"+beanSave.getCommande().getCmd_id()+"' ").executeUpdate();
			
			boolean result_f = TraitementVenteAvecFourniture(fVenteBean,beanSave,session);
			boolean result_s = TraitementVenteAvecPrestation(service,beanSave, session);
			if(!result_detaille &&  !result_f  && !result_s)throwNewException("err_inser_deaill");
			
			 result = true;
			 commitTransaction(session);
			 setObjectValueModel(FORM_BEAN, beanSave);
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
	
	
	 private boolean TraitementVenteAvecFourniture(FournitureVenteBean    fVenteBean, ProcedureVenteBean beanVente	,Session session) throws Exception   {
		 
		 boolean insert=false;
		 List listOfmyData_fourniture=(List) getObjectValueModel( ProcedureVenteTemplate.LIST_EDITABLE_FOURNITURE_VENTE);
			if(listOfmyData_fourniture!=null &&  listOfmyData_fourniture.size()>0){
					daoNumSequentiel.getNumSeqSimple(fVenteBean,"frn_ve_id",session);
					this.setBeanTrace(fVenteBean);
					fVenteBean.setFrn_ve_libelle(beanVente.getVente_libelle());
					fVenteBean.setVenteFrn(beanVente);
					fVenteBean.setClient(beanVente.getClient());
					fVenteBean.setFrn_ve_date(beanVente.getVente_date());
					fVenteBean.setDepot(beanVente.getDepot());
					session.save(fVenteBean);
					for (int i = 0; i < listOfmyData_fourniture.size(); i++) {
						DetFournitureVenteBean  detBean=(DetFournitureVenteBean) listOfmyData_fourniture.get(i);
						if( detBean.getQuantite()==null) { continue; }
						if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
						detBean.setFourniture(fVenteBean);
						session.save(detBean);
						insert=true;
					}
			 
			}
			return insert;
		
	}
	 
 private boolean TraitementVenteAvecPrestation(ServiceBean    service ,ProcedureVenteBean beanVente	,Session session) throws Exception   {
	 boolean insert=false;
		 List listOfmyDataPrestation=(List) getObjectValueModel( ProcedureVenteTemplate.LIST_EDITABLE_PRESTATION);
			if(listOfmyDataPrestation!=null &&  listOfmyDataPrestation.size()>0){
					daoNumSequentiel.getNumSeqSimple(service,"srv_id",session);	 
					this.setBeanTrace(service);
					service.setSrv_libelle(beanVente.getVente_libelle());
					service.setVenteSrv(beanVente);
					service.setClient(beanVente.getClient());
					service.setSrv_date(beanVente.getVente_date());
					service.setDepot(beanVente.getDepot());
					session.save(service);
					for (int i = 0; i < listOfmyDataPrestation.size(); i++) {
						DetServiceBean  detBean=(DetServiceBean) listOfmyDataPrestation.get(i);
						if( detBean.getQuantite()==null) { continue; }
						if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
						detBean.setService(service);
						session.save(detBean);
						insert=true;
					}
			 
			}
			return insert;
	}

	public Boolean doConfirmerVente_article(ProcedureVenteBean beanUp, FournitureVenteBean    fVenteBean , ServiceBean    service) throws Exception {
		    Session session =  openSessionHibernate(sessionFactory);
			boolean result  = false;
			try {  
				 result  = TraitementVenteArticleMarchandise(beanUp,session);
				List <DetFournitureVenteBean> listOfmyData=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_FOURNITURE_VENTE);
				if(listOfmyData!=null  &&  listOfmyData.size()>0){
					TraitementFournitureVente(listOfmyData,beanUp,session);
				}
				session.createQuery( " UPDATE  ProcedureVenteBean b  set      b.modeBean.fct_id="+GenericActionBean.Fn_Confirmer+"   " +
						"      where   b.vente_id='"+beanUp.getVente_id()+"' ").executeUpdate();
				
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
	
	public void doRetourModeOrigin( String entite, String mode , String idRow ) throws Exception {
	    Session session =  openSessionHibernate(sessionFactory);
		try {  
			 session.createQuery(  " UPDATE  "+entite+" b  set    b.modeBean.fct_id="+mode+"    where   b.vente_id='"+idRow+"' "  ).executeUpdate();
			 commitTransaction(session);
		 } catch (Exception e) {  
		     rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
	}
	 
	private void TraitementFournitureVente(List <DetFournitureVenteBean> listOfmyData,ProcedureVenteBean beanUp, Session session) throws Exception {
		   ProcedureVenteBean beanUpdate=(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
		   beanUpdate.setFifo(beanUp.getFifo());
		   boolean  sup=ProcessDate.isStrictementSuperieur(beanUpdate.getVente_date(), BDateTime.getDateActuel());
		    if(sup)
		    	throwNewException(" Date vente "+ProcessDate.getStringFormatDate(beanUpdate.getVente_date())+"Sup a date system");
		    
		
		 
		   String chaine="";
		   for(DetFournitureVenteBean beanvente:listOfmyData){
			   if(  !StringUtils.isEmpty(  beanvente.getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id()) &&  
						  beanvente.getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id().equals("map") 	)
			 chaine=chaine+"'"+beanvente.getFkcode_barre().getPk().getCode_barre()+"',"; 
		   }
		   List     list_lot_article   = new ArrayList();
		   HashMap  map_resultat_stock = new HashMap();
		   if(chaine.length()>0){
			   chaine=chaine.substring(0, chaine.length()-1);
		       list_lot_article   = doGetLot_artcicle(beanUpdate,chaine);
		       map_resultat_stock = doGetStock_artcicle(beanUpdate,chaine);
		     }
		
		    for (int i = 0; i < listOfmyData.size(); i++) {
				DetFournitureVenteBean   detBean  = (DetFournitureVenteBean) listOfmyData.get(i);
				
				 if(  !StringUtils.isEmpty(  detBean.getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id()) &&  
						 detBean.getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id().equals("syn") 	
						 ) continue;
					 
				detBean.getFourniture().setVenteFrn(beanUpdate);
				detBean.setQuantite_confirmer(detBean.getQuantite());
				
				boolean resultTrai_personnaliser=false;//traitement_for_lot_personnaliser(beanUpdate,detBean,session);
				
				if(!resultTrai_personnaliser && (list_lot_article!=null &&  list_lot_article.size()>0) ) {
				HashMap resultTrai_generic=traitement_for_lot_generic_fourniture(beanUpdate,detBean,list_lot_article,session);
				String  resultMessage = (String) resultTrai_generic.get("retournErreur");
				Boolean resultretour  = (Boolean) resultTrai_generic.get("retour");
				if(!resultretour) 
					throwNewException(resultMessage+"Pour l'article:"+detBean.getFkcode_barre().getPk().getCode_barre()+" / "+
							detBean.getFkcode_barre().getDesignation_libelle());
				}
				
				if( list_lot_article!=null &&  list_lot_article.size()>0 && chaine.length()>0 ) {
					boolean resul=traitement_for_stock_fourniture(beanUpdate,detBean,map_resultat_stock,session);
					if(!resul){
						throwNewException("Stock zero _ article:"+detBean.getFkcode_barre().getPk().getCode_barre()+" / "+
								detBean.getFkcode_barre().getDesignation_libelle());
						 
					}
				}
				session.saveOrUpdate(detBean);
				
			}
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.saveOrUpdate(beanUpdate);
		 
	       if( !StringUtils.isEmpty( beanUpdate.getCommande().getCmd_id()) ){
		     session.createQuery("    UPDATE   CommandeclientBean b  set   b.modeBean.fct_id="+GenericActionBean.Fn_Livrer+"  " +
		     		"       where   b.cmd_id='"+beanUpdate.getCommande().getCmd_id()+"'    ").executeUpdate();
	       }
		
	}
	
	
	private void TraitementCorrectionFournitureVente(List <DetFournitureVenteBean> listOfmyData,ProcedureVenteBean beanUp, Session session) throws Exception {
		   ProcedureVenteBean beanUpdate=(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
		   beanUpdate.setFifo(beanUp.getFifo());
		   boolean  sup=ProcessDate.isStrictementSuperieur(beanUpdate.getVente_date(), BDateTime.getDateActuel());
		    if(sup)
		    	throwNewException(" Date vente "+ProcessDate.getStringFormatDate(beanUpdate.getVente_date())+"Sup a date system");
		    
		
		 
		   String chaine="";
		   for(DetFournitureVenteBean beanvente:listOfmyData){
			   if(  !StringUtils.isEmpty(  beanvente.getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id()) &&  
						  beanvente.getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id().equals("map") 	)
			 chaine=chaine+"'"+beanvente.getFkcode_barre().getPk().getCode_barre()+"',"; 
		   }
		   List     list_lot_article   = new ArrayList();
		   HashMap  map_resultat_stock = new HashMap();
		   if(chaine.length()>0){
			   chaine=chaine.substring(0, chaine.length()-1);
		       list_lot_article   = doGetLot_artcicle(beanUpdate,chaine);
		       map_resultat_stock = doGetStock_artcicle(beanUpdate,chaine);
		     }
		
		    for (int i = 0; i < listOfmyData.size(); i++) {
				DetFournitureVenteBean   detBean  = (DetFournitureVenteBean) listOfmyData.get(i);
				
				 if(  !StringUtils.isEmpty(  detBean.getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id()) &&  
						 detBean.getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id().equals("syn") 	
						 ) continue;
					 
				detBean.getFourniture().setVenteFrn(beanUpdate);
				detBean.setQuantite_confirmer(detBean.getQuantite());
				
				boolean resultTrai_personnaliser=false;//traitement_for_lot_personnaliser(beanUpdate,detBean,session);
				
				if(!resultTrai_personnaliser && (list_lot_article!=null &&  list_lot_article.size()>0) ) {
				HashMap resultTrai_generic=traitement_for_lot_generic_fourniture(beanUpdate,detBean,list_lot_article,session);
				String  resultMessage = (String) resultTrai_generic.get("retournErreur");
				Boolean resultretour  = (Boolean) resultTrai_generic.get("retour");
				if(!resultretour) 
					throwNewException(resultMessage+"Pour l'article:"+detBean.getFkcode_barre().getPk().getCode_barre()+" / "+
							detBean.getFkcode_barre().getDesignation_libelle());
				}
				
				if( list_lot_article!=null &&  list_lot_article.size()>0 && chaine.length()>0 ) {
					boolean resul=traitement_for_stock_fourniture(beanUpdate,detBean,map_resultat_stock,session);
					if(!resul){
						throwNewException("Stock zero _ article:"+detBean.getFkcode_barre().getPk().getCode_barre()+" / "+
								detBean.getFkcode_barre().getDesignation_libelle());
						 
					}
				}
				session.saveOrUpdate(detBean);
				
			}
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.saveOrUpdate(beanUpdate);
		 
	       if( !StringUtils.isEmpty( beanUpdate.getCommande().getCmd_id()) ){
		     session.createQuery("    UPDATE   CommandeclientBean b  set   b.modeBean.fct_id="+GenericActionBean.Fn_Livrer+"  " +
		     		"       where   b.cmd_id='"+beanUpdate.getCommande().getCmd_id()+"'    ").executeUpdate();
	       }
		
	}

	private boolean TraitementVenteArticleMarchandise(  ProcedureVenteBean beanUp, Session session) throws Exception {
		
 		   ProcedureVenteBean beanUpdate=(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
		   beanUpdate.setFifo(beanUp.getFifo());
		   boolean  sup=ProcessDate.isStrictementSuperieur(beanUpdate.getVente_date(), BDateTime.getDateActuel());
		    if(sup)
		    	throwNewException(" Date vente "+ProcessDate.getStringFormatDate(beanUpdate.getVente_date())+"Sup a date system");
		   
		   List <DetProcedureVenteBean> listOfmyData=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_VENTE);
		 
		   String chaine="";
		   for(DetProcedureVenteBean beanvente:listOfmyData){
			   if(  !StringUtils.isEmpty(  beanvente.getPk().getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id()) &&  
						  beanvente.getPk().getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id().equals("mar") 	)
			 chaine=chaine+"'"+beanvente.getPk().getFkcode_barre().getPk().getCode_barre()+"',"; 
		   }
		   List     list_lot_article   = new ArrayList();
		   HashMap  map_resultat_stock = new HashMap();
		   if(chaine.length()>0){
			   chaine=chaine.substring(0, chaine.length()-1);
		       list_lot_article   = doGetLot_artcicle(beanUpdate,chaine);
		       map_resultat_stock = doGetStock_artcicle(beanUpdate,chaine);
		     }
		
		    for (int i = 0; i < listOfmyData.size(); i++) {
				DetProcedureVenteBean detBean  = (DetProcedureVenteBean) listOfmyData.get(i);
				
				 if(  !StringUtils.isEmpty(  detBean.getPk().getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id()) &&  
					  detBean.getPk().getFkcode_barre().getPk().getAr_bean().getCathegorie().getData_id().equals("syn") )continue;
				 
					 
				detBean.getPk().setVente( beanUpdate );
				detBean.setQuantite_confirmer(detBean.getQuantite());
				boolean resultTrai_personnaliser=false;   //traitement_for_lot_personnaliser(beanUpdate,detBean,session);
				
				
				if(!resultTrai_personnaliser && (list_lot_article!=null &&  list_lot_article.size()>0) ) {
				HashMap resultTrai_generic=traitement_for_lot_generic(beanUpdate,detBean,list_lot_article,session);
				String  resultMessage = (String) resultTrai_generic.get("retournErreur");
				Boolean resultretour  = (Boolean) resultTrai_generic.get("retour");
//				if(!resultretour) 
//					throwNewException(resultMessage+"Pour l'article:"+detBean.getPk().getFkcode_barre().getPk().getCode_barre()+" / "+
//							detBean.getPk().getFkcode_barre().getDesignation_libelle());
				}
				
				//if( list_lot_article!=null &&  list_lot_article.size()>0 && chaine.length()>0 ) {
				boolean resul=traitement_for_stock(beanUpdate,detBean,map_resultat_stock,session);
//					if(!resul){
//						throwNewException("Stock zero _ article:"+detBean.getPk().getFkcode_barre().getPk().getCode_barre()+" / "+
//								detBean.getPk().getFkcode_barre().getDesignation_libelle());
//						 
//					}
				//}
				session.saveOrUpdate(detBean);
				
			}
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.saveOrUpdate(beanUpdate);
		 
	       if( !StringUtils.isEmpty( beanUpdate.getCommande().getCmd_id()) ){
		     session.createQuery("    UPDATE   CommandeclientBean b  set   b.modeBean.fct_id="+GenericActionBean.Fn_Livrer+"  " +
		     		"                 where   b.cmd_id='"+beanUpdate.getCommande().getCmd_id()+"'    ").executeUpdate();
	       }
		return true;
	}
	
	
	
	 
	 
	private void TraitementCorrectionVenteArticleMarchandise(  ProcedureVenteBean beanUp, Session session) throws Exception {
		
		   ProcedureVenteBean beanUpdate=(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
		   List <DetProcedureVenteBean> listOfmyDataClone=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_VENTE_CLONE);
		   if(listOfmyDataClone!=null &&  listOfmyDataClone.size()>0) {
		   traitementCorrectionLotGeneric(beanUpdate,session);
		   traitementCorrectionStockArticleMarchandis(beanUpdate,listOfmyDataClone,session);
		   }
	 
	}
	
	private void TraitementCorrectionVenteArticleFourntire(  ProcedureVenteBean beanUp, Session session) throws Exception {
		
		   ProcedureVenteBean beanUpdate=(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
		   List <DetFournitureVenteBean> listOfmyDataClone=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_FOURNITURE_VENTE_CLONE);
		   if(listOfmyDataClone!=null &&  listOfmyDataClone.size()>0) {
			   DetFournitureVenteBean dBean = listOfmyDataClone.get(0);
			   traitementCorrectionLotGenericfourniture(dBean.getFourniture(),session);
			   traitementCorrectionStockArticlefourniture(beanUpdate,listOfmyDataClone,session);
		   }
	 
	}

	private HashMap doGetStock_artcicle(ProcedureVenteBean beanUpdate, String chaine) throws Exception {
	 
		HashMap  map_resultat= new HashMap();
		 try {
			    BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);	 
				Stock_articleBean beanMvtJourStock= new Stock_articleBean();
				String res= "   AND    bean.pk.depot.depot_id="+beanUpdate.getDepot().getDepot_id()+"     " +
				"      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
				"      where  beaK.pk.date_stock  <=  '"+ProcessDate.getStringFormatDate(beanUpdate.getVente_date())+"'        " +
				"        AND    bean.pk.fkCode_barre.pk.code_barre=beaK.pk.fkCode_barre.pk.code_barre      ";
			 
				res+="   AND   beaK.pk.depot.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'"  ; 
				
				res+="  AND    beaK.pk.depot.depot_id= bean.pk.depot.depot_id        )     " ;
				beanMvtJourStock.setCondition_max_date_stock( res ); 
				beanMvtJourStock.setCondition_list_article("      AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")       ");
				
				 
				
				 
				
				
			    List lisStock_max_date_article = daoStock_article.doFindListStock_article(beanMvtJourStock);
			    
			    
			    for (int i = 0; i < lisStock_max_date_article.size(); i++) {
						Stock_articleBean sBean= (Stock_articleBean) lisStock_max_date_article.get(i);
						String key_max_jour =
							sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
						    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"�"+
						    sBean.getPk().getDepot().getDepot_id()+"�"+
						    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
						    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
						map_resultat.put(key_max_jour, sBean);
				}
					
				  
			
		} catch (Exception e) {
			 throw e;
		}
		return map_resultat;
	}
	
	
	
	private void traitementCorrectionStockArticleMarchandis(ProcedureVenteBean beanUpdate,   List <DetProcedureVenteBean> listOfmyDataClone,  Session session ) throws Exception {
		 HashMap  map_article_jour= new HashMap();
		 try {
				Stock_articleBean beanMvtJourStock= new Stock_articleBean();
				String res=  "   AND  bean.pk.depot.depot_id ="+beanUpdate.getDepot().getDepot_id()+"     " +
				             "   AND  bean.pk.date_stock  ='"+ProcessDate.getStringFormatDate(beanUpdate.getVente_date())+"'   " ;
				beanMvtJourStock.setCondition_max_date_stock( res ); 
			    List lisStock_max_date_article = daoStock_article.doFindListStock_article(beanMvtJourStock);
			    for (int i = 0; i < lisStock_max_date_article.size(); i++) {
						Stock_articleBean sBean= (Stock_articleBean) lisStock_max_date_article.get(i);
						String key_max_jour =
							sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
						    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"�"+
						    sBean.getPk().getDepot().getDepot_id()+"�"+
						    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
						    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
						map_article_jour.put(key_max_jour, sBean);
				}
			    
				   for (int i = 0; i < listOfmyDataClone.size(); i++) {
					   
				   
						 DetProcedureVenteBean detail_Bean  = (DetProcedureVenteBean) listOfmyDataClone.get(i);
						 String date_vente =  ProcessDate.getStringFormatDate(beanUpdate.getVente_date()); 
					     String keyTrait =""+
					     detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
					     detail_Bean.getPk().getFkcode_barre().getPk().getCode_barre()+"�"+
						 beanUpdate.getDepot().getDepot_id()+"�"+
						 detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
						 detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
					     Double prix_unit_moyen_pond = new Double(0);
					 
						 if(map_article_jour.get(keyTrait)==null) {
						 
							 
						 }else{
							  
							     Stock_articleBean stock          = (Stock_articleBean)map_article_jour.get(keyTrait);
							     prix_unit_moyen_pond             = stock.getCout_unitaire_moyen_pondere()!=null?stock.getCout_unitaire_moyen_pondere(): new Double(0);             
								 String  date_stock               = ProcessDate.getStringFormatDate(stock.getPk().getDate_stock());
								 Double  qte_enDet_vente          = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getQuantite());
								 
							 
								 
								 
								 Double  Vmnt_ht__Retvente        = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_ht_vente());
								 Double  Vmnt_tva_Retvente        = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_tva_vente());
								 
								 Double  qte_Stock               = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_stock());
								 
								 
								 Double  qte_Sortie_stock         = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getQuantite_vendu());
								 
								 Double  mntStock_ht              = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_ht_vente());
								 Double  mntStock_tva             = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_tva_vente());
								 
//								 Double  getSolde_vente_ht        = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_ht());
//								 Double  getSolde_vente_tva       = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_tva());
								 
								 Double  sold_stock_jr            = ProcessNumber.addition(qte_Stock , qte_enDet_vente );
								 Double  sold_sortie_stock        = ProcessNumber.SOUSTRACTION(qte_Sortie_stock , qte_enDet_vente );
								 
								 Double  Newgetmnt_vente_ht     = ProcessNumber.SOUSTRACTION(mntStock_ht   , Vmnt_ht__Retvente);
								 Double  Newgetmnt_vente_tva    = ProcessNumber.SOUSTRACTION(mntStock_tva, Vmnt_tva_Retvente);
								 
								 
								 
								 stock.setMnt_tva_vente(ProcessFormatNbr.FormatDouble_Troischiffre(Newgetmnt_vente_tva));
								 stock.setMnt_ht_vente (ProcessFormatNbr.FormatDouble_Troischiffre( Newgetmnt_vente_ht));
								 
								 stock.setSolde_stock ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
								 stock.setQuantite_vendu(ProcessFormatNbr.FormatDouble_Troischiffre(sold_sortie_stock));
								 
								 stock.getPk().setFkCode_barre(detail_Bean.getPk().getFkcode_barre());
								 stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
								 session.update(stock);
					 
								 
							 
								 
								 String ligne="";
								 
									 ligne="  ,bean.solde_stock = bean.solde_stock + "+ProcessFormatNbr.FormatDouble_Troischiffre(qte_enDet_vente)+"  ";
								 
									 
									 
									 String qString=""+
									 "   UPDATE  Stock_articleBean  bean    set   bean.solde_vente_tva = bean.solde_vente_tva - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_Retvente)+"  , " +
									 
									 "                                            bean.solde_vente_ht = bean.solde_vente_ht - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_ht__Retvente)+"    " +
									 "                      "+ligne+"                        " +
									 
								 		"                where   bean.pk.depot.depot_id="+beanUpdate.getDepot().getDepot_id()+"     "+
								 		"                 AND    bean.pk.fkCode_barre.pk.code_barre='"+detail_Bean.getPk().getFkcode_barre().getPk().getCode_barre()+"'  "+
								 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id='"+detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'  "+
								 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  "+
								 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  "+
								 		"                 AND    bean.pk.date_stock > '"+date_vente+"'  ";
								 		 
								 		  
									  session.createQuery(qString).executeUpdate();	
 
						 }
			 }	    
			    
			
		} catch (Exception e) {
			 throw e;
		}
	}
	
	
	private void traitementCorrectionStockArticlefourniture(ProcedureVenteBean beanUpdate,   List <DetFournitureVenteBean> listOfmyDataClone,  Session session ) throws Exception {
		 HashMap  map_article_jour= new HashMap();
		 try {
				Stock_articleBean beanMvtJourStock= new Stock_articleBean();
				String res=  "   AND  bean.pk.depot.depot_id ="+beanUpdate.getDepot().getDepot_id()+"     " +
				             "   AND  bean.pk.date_stock  ='"+ProcessDate.getStringFormatDate(beanUpdate.getVente_date())+"'   " ;
				beanMvtJourStock.setCondition_max_date_stock( res ); 
			    List lisStock_max_date_article = daoStock_article.doFindListStock_article(beanMvtJourStock);
			    for (int i = 0; i < lisStock_max_date_article.size(); i++) {
						Stock_articleBean sBean= (Stock_articleBean) lisStock_max_date_article.get(i);
						String key_max_jour =
							sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
						    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"�"+
						    sBean.getPk().getDepot().getDepot_id()+"�"+
						    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
						    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
						map_article_jour.put(key_max_jour, sBean);
				}
			    
				   for (int i = 0; i < listOfmyDataClone.size(); i++) {
					   
				   
					     DetFournitureVenteBean detail_Bean  = (DetFournitureVenteBean) listOfmyDataClone.get(i);
						 String date_vente =  ProcessDate.getStringFormatDate(beanUpdate.getVente_date()); 
					     String keyTrait =""+
					     detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
					     detail_Bean.getFkcode_barre().getPk().getCode_barre()+"�"+
						 beanUpdate.getDepot().getDepot_id()+"�"+
						 detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
						 detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
					     Double prix_unit_moyen_pond = new Double(0);
					 
						 if(map_article_jour.get(keyTrait)==null) {
						 
							 
						 }else{
							  
							     Stock_articleBean stock          = (Stock_articleBean)map_article_jour.get(keyTrait);
							     prix_unit_moyen_pond             = stock.getCout_unitaire_moyen_pondere()!=null?stock.getCout_unitaire_moyen_pondere(): new Double(0);             
								 String  date_stock               = ProcessDate.getStringFormatDate(stock.getPk().getDate_stock());
								 Double  Vqte_R_vente             = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getQuantite());
								 Double  Vmnt_ht__Retvente        = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_ht_vente());
								 Double  Vmnt_tva_Retvente        = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_tva_vente());
								 
								 Double  qte_entre_stock          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getQuantite_recept());
								 Double  qte_Sortie_stock         = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getQuantite_vendu());
								 
								 Double  Sqte_Stock               = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_stock());
								 Double  getSolde_achat_ht        = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_ht());
								 Double  getSolde_achat_tva       = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_tva());
								 Double  getSolde_vente_ht        = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_ht());
								 Double  getSolde_vente_tva       = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_tva());
								 Double  sold_stock_jr            = ProcessNumber.addition(Sqte_Stock , Vqte_R_vente );
								 Double  sold_sortie_stock        = ProcessNumber.SOUSTRACTION(qte_Sortie_stock , Vqte_R_vente );
								 Double  NewgetSolde_vente_ht     = ProcessNumber.SOUSTRACTION(getSolde_vente_ht   , Vmnt_ht__Retvente);
								 Double  NewgetSolde_vente_tva    = ProcessNumber.SOUSTRACTION(getSolde_vente_tva, Vmnt_tva_Retvente);
								 stock.setSolde_vente_ht(NewgetSolde_vente_ht);
								 stock.setSolde_vente_tva(NewgetSolde_vente_tva);
								 stock.setSolde_stock ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
								 stock.setQuantite_vendu( ProcessFormatNbr.FormatDouble_Troischiffre(sold_sortie_stock)  );
								 stock.getPk().setFkCode_barre(detail_Bean.getFkcode_barre());
								 stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
								 session.update(stock);
					 
								 
							 
								 
								 String ligne="";
								 
									 ligne="  ,bean.solde_stock = bean.solde_stock + "+ProcessFormatNbr.FormatDouble_Troischiffre(Vqte_R_vente)+"  ";
								 
									 
									 
									 String qString=""+
									 "   UPDATE  Stock_articleBean  bean    set   bean.solde_vente_tva = bean.solde_vente_tva - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_Retvente)+"  , " +
									 
									 "                                            bean.solde_vente_ht = bean.solde_vente_ht - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_ht__Retvente)+"    " +
									 "                      "+ligne+"                        " +
									 
								 		"                where   bean.pk.depot.depot_id="+beanUpdate.getDepot().getDepot_id()+"     "+
								 		"                 AND    bean.pk.fkCode_barre.pk.code_barre='"+detail_Bean.getFkcode_barre().getPk().getCode_barre()+"'  "+
								 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id='"+detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'  "+
								 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  "+
								 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  "+
								 		"                 AND    bean.pk.date_stock > '"+date_vente+"'  ";
								 		 
								 		  
									  session.createQuery(qString).executeUpdate();	

						 }
			 }	    
			    
			
		} catch (Exception e) {
			 throw e;
		}
	}

	public   List doGetLot_artcicle(ProcedureVenteBean beanUpd, String chaine) throws Exception {
	 
		List list_lot_article = new ArrayList();
		 try {
			 
			 SerieArticletBean  beanSerie= new SerieArticletBean();
			 beanSerie.setCondition_list_article("   AND   bean.date_utilisation  <= '"+ProcessDate.getStringFormatDate(beanUpd.getVente_date())+"'    " +
					                             "   AND   bean.quantite  > 0    " +
					 					         "   AND   ( bean.date_peremption  is null   OR    bean.date_peremption  > '"+ProcessDate.getStringFormatDate(beanUpd.getVente_date())+"' )   " +
			 		"                                AND   bean.fkCode_barre.pk.code_barre in ( "+  chaine + ")    " );
			 
			 if(beanUpd.getFifo()) 
			   beanSerie.setCondition_fifo_lifo("  ORDER BY  bean.date_serie  ASC    ");
			 else
			   beanSerie.setCondition_fifo_lifo("  ORDER BY  bean.date_serie  DESC   ");
			 
			   beanSerie.getPk().setDepot(beanUpd.getDepot());
			   list_lot_article=serviceDocumentLot.doFetchDatafromServer(beanSerie);
			   
//			  if(list_lot_article==null ||  list_lot_article.size()==0)
//				   throwNewException(" il existe un ou plusieur  article(s) sans Lot ");
			   
			
		} catch (Exception e) {
			throw e;
		}
		return list_lot_article;
	}
	
	
	public   List doGetLotArtcicleDejaSauvgarderByVenteId(String  document_com_id  ) throws Exception {
		 
		List list_lot_article = new ArrayList();
		 try {
			   SerieArticletBean  beanSerie= new SerieArticletBean();
			   beanSerie.setDetaille_serie("   AND   bean.pk.document_com_id ='"+document_com_id+"'    ");
			   list_lot_article=serviceDocumentLot.doFetch_detailleLotfromServer(beanSerie);
			  
		} catch (Exception e) {
			throw e;
		}
		return list_lot_article;
	}
	
	
	
	
	

	@SuppressWarnings("unchecked")
	private boolean traitement_for_stock(ProcedureVenteBean beanUpdate, DetProcedureVenteBean detail_Bean, HashMap map_article_jour ,  Session session ) throws Exception {
	 
		boolean resultat= true;
		 
		
		 try {
			    
			    String date_vente =  ProcessDate.getStringFormatDate(beanUpdate.getVente_date());// forcer a date system
			    String pattern ="0.000";
			    
				if(beanUpdate.getDevise()!=null &&  beanUpdate.getDevise().getDev_id().intValue()==191  ||  beanUpdate.getDevise().getDev_id().intValue()==192   ){
					pattern ="0.00";
				}
				 
			    String key_trait =""+
			    detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
			    detail_Bean.getPk().getFkcode_barre().getPk().getCode_barre()+"�"+
				beanUpdate.getDepot().getDepot_id()+"�"+
				detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
				detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
			    Double prix_unit_moyen_pond = new Double(0);
			    Stock_articleBean stock         = new Stock_articleBean();
//			    if(map_article_jour.get(key_trait)==null) {
//			    	return false;
//			    }
				 if(map_article_jour.get(key_trait)!=null) {
					 stock         = (Stock_articleBean)map_article_jour.get(key_trait);
				 }  
		 
					 prix_unit_moyen_pond            = stock.getCout_unitaire_moyen_pondere()!=null?stock.getCout_unitaire_moyen_pondere(): new Double(0);
					 
					 String  date_stock              = ProcessDate.getStringFormatDate(stock.getPk().getDate_stock());
					 
					 Double  Vqte_vente              = ProcessFormatNbr.FormatDouble_ParameterChiffre(detail_Bean.getQuantite(),pattern);
					 
					 Double  Vmnt_ht__vente          = ProcessFormatNbr.FormatDouble_ParameterChiffre(detail_Bean.getMontant_ht_vente(),pattern);
					 Double  Vmnt_tva_vente          = ProcessFormatNbr.FormatDouble_ParameterChiffre(detail_Bean.getMontant_tva_vente(),pattern);
					 
					 Double  Sqte_Stock              = ProcessFormatNbr.FormatDouble_ParameterChiffre(stock.getSolde_stock(),pattern);
					 Double  SqteSvendu              = ProcessFormatNbr.FormatDouble_ParameterChiffre(stock.getQuantite_vendu(),pattern);
					 
					 Double  Smnt_ht__vente          = ProcessFormatNbr.FormatDouble_ParameterChiffre(stock.getMnt_ht_vente(),pattern);
					 Double  Smnt_tva_vente          = ProcessFormatNbr.FormatDouble_ParameterChiffre(stock.getMnt_tva_vente(),pattern);
					 
					 Double sold_stock_jr            = ProcessNumber.SOUSTRACTION(Sqte_Stock , Vqte_vente);
					 
					 Double  Stock_montant_ht_V      = ProcessFormatNbr.FormatDouble_ParameterChiffre(stock.getSolde_vente_ht(),pattern);
					 Double  Stock_montant_tva_V     = ProcessFormatNbr.FormatDouble_ParameterChiffre(stock.getSolde_vente_tva(),pattern);
					 
					 Double tot_Stock_Mnt_ht_vente   = ProcessNumber.addition(Stock_montant_ht_V , Vmnt_ht__vente);
					 Double tot_Stock_Mnt_tva_vente  = ProcessNumber.addition(Stock_montant_tva_V , Vmnt_tva_vente);
					 
					 
//					 if(sold_stock_jr.doubleValue()<0){
//					    return false;
//						    
//					 } 
					 
					 if(date_vente.equals(date_stock)){
						 
						 stock.getPk().setDate_stock( ProcessDate.convert_String_to_Date(date_vente));
						 Double tot_qte_vendu            = ProcessNumber.addition(SqteSvendu , Vqte_vente);
						 Double tot_mnt_ht__vente        = ProcessNumber.addition(Smnt_ht__vente , Vmnt_ht__vente);
						 Double tot_mnt_tva_vente        = ProcessNumber.addition(Smnt_tva_vente , Vmnt_tva_vente);
						 stock.setSolde_stock   ( ProcessFormatNbr.FormatDouble_ParameterChiffre(sold_stock_jr,pattern));
						 stock.setQuantite_vendu(ProcessFormatNbr.FormatDouble_ParameterChiffre (tot_qte_vendu,pattern));
						 stock.setMnt_tva_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_mnt_tva_vente,pattern));
						 stock.setMnt_ht_vente (ProcessFormatNbr.FormatDouble_ParameterChiffre( tot_mnt_ht__vente,pattern));
						 
						 
						 stock.setSolde_vente_tva(ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_Stock_Mnt_tva_vente,pattern));
						 stock.setSolde_vente_ht(ProcessFormatNbr.FormatDouble_ParameterChiffre( tot_Stock_Mnt_ht_vente,pattern));
						 stock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
						 
						 stock.getPk().setFkCode_barre(detail_Bean.getPk().getFkcode_barre());
						 stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
						 stock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
						 stock.setDevise(stock.getDevise());
						 stock.setDeviseVente(detail_Bean.getDevise());
						 session.saveOrUpdate(stock);
						
						 
					 }else{
						 Stock_articleBean Newstock = new Stock_articleBean();
						 Newstock.getPk().setDate_stock( ProcessDate.convert_String_to_Date(date_vente));
						 daoNumSequentiel.getNumSeqSimple(Newstock,"stock_article_id",session);
						 Newstock.setSolde_vente_tva(ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_Stock_Mnt_tva_vente,pattern));
						 Newstock.setSolde_vente_ht(ProcessFormatNbr.FormatDouble_ParameterChiffre( tot_Stock_Mnt_ht_vente,pattern));
						 
						 Newstock.setSolde_stock   ( ProcessFormatNbr.FormatDouble_ParameterChiffre(sold_stock_jr,pattern));
						 Newstock.setQuantite_vendu(ProcessFormatNbr.FormatDouble_ParameterChiffre (Vqte_vente,pattern));
						 Newstock.setMnt_tva_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(Vmnt_tva_vente,pattern));
						 Newstock.setMnt_ht_vente (ProcessFormatNbr.FormatDouble_ParameterChiffre( Vmnt_ht__vente,pattern));

						 Newstock.getPk().setFkCode_barre(detail_Bean.getPk().getFkcode_barre());
						 Newstock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
						 Newstock.setDevise(stock.getDevise());
						 Newstock.setDeviseVente(detail_Bean.getDevise());
						 Newstock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
						 session.saveOrUpdate(Newstock);
					 }
					 
					  MouvementStockBean  mvtStock        = new MouvementStockBean(); 
				      mvtStock.setDate_stock(beanUpdate.getVente_date());
				      mvtStock.setFkCode_barre(detail_Bean.getPk().getFkcode_barre());
				      mvtStock.setDocument_com_id(detail_Bean.getPk().getVente().getVente_id());
				      mvtStock.getNat_mvt().setNature_mvt_id("ve");
				      mvtStock.setQuantite_sorti(Vqte_vente);
				      mvtStock.setTarifVente(detail_Bean.getTarif());
					  mvtStock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
					  mvtStock.setDepot(beanUpdate.getDepot());
					  mvtStock.setSolde_stock(sold_stock_jr);
					  mvtStock.setDevise(stock.getDevise());
					  mvtStock.setDeviseVente(detail_Bean.getDevise());
					  session.saveOrUpdate(mvtStock);
					  detail_Bean.setCout_unit_moyen_pondere(prix_unit_moyen_pond);
					  detail_Bean.setMvt_stock(mvtStock);
					 
					  session.saveOrUpdate(detail_Bean);   
					 
					 String qString=""+
					 "   UPDATE  Stock_articleBean  bean    set   bean.solde_stock = bean.solde_stock - "+ProcessFormatNbr.FormatDouble_ParameterChiffre(Vqte_vente,pattern)+" , " +
					 
					 "                                            bean.solde_vente_tva = bean.solde_vente_tva + "+ProcessFormatNbr.FormatDouble_ParameterChiffre(Vmnt_tva_vente,pattern)+",  " +
					 "                                            bean.solde_vente_ht = bean.solde_vente_ht + "+ProcessFormatNbr.FormatDouble_ParameterChiffre(Vmnt_ht__vente,pattern)+"  " +
					 
				 		"                where   bean.pk.depot.depot_id="+beanUpdate.getDepot().getDepot_id()+"     "+
				 		"                 AND    bean.pk.fkCode_barre.pk.code_barre='"+detail_Bean.getPk().getFkcode_barre().getPk().getCode_barre()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id='"+detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+detail_Bean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  "+
				 		"                 AND    bean.pk.date_stock > '"+date_vente+"'  ";
				 		 
				 		 
					 session.createQuery(qString).executeUpdate();
				 
				 
			 
		} catch (Exception e) {
			 resultat= false;
			 throw e;
		}
		return resultat;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	private boolean traitement_for_stock_fourniture(ProcedureVenteBean beanUpdate, DetFournitureVenteBean detail_Bean, HashMap map_article_jour ,  Session session ) throws Exception {
	 
		boolean resultat= true;
		 
		
		 try {
			    BeanSession bs    =  (BeanSession)getObjectValueModel(BEAN_SESSION);
			    String date_vente =  ProcessDate.getStringFormatDate(beanUpdate.getVente_date());// forcer a date system
				
			    String key_trait =""+
			    detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
			    detail_Bean.getFkcode_barre().getPk().getCode_barre()+"�"+
				beanUpdate.getDepot().getDepot_id()+"�"+
				detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
				detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
			   
				 if(map_article_jour.get(key_trait)==null) {
					 
					 return false;
					
					 
				 }else{
					  
					 Stock_articleBean stock         = (Stock_articleBean)map_article_jour.get(key_trait);
					 String  date_stock              = ProcessDate.getStringFormatDate(stock.getPk().getDate_stock());
					 
					 Double  Vqte_vente              = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getQuantite());
					 
					 Double  Vmnt_ht__vente          = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_ht_vente());
					 Double  Vmnt_tva_vente          = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_tva_vente());
					 
					 
					 Double  Sqte_Stock              = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_stock());
					 Double  SqteSvendu              = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getQuantite_vendu());
					 
					 Double  Smnt_ht__vente          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_ht_vente());
					 Double  Smnt_tva_vente          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_tva_vente());
					 
					 Double sold_stock_jr            = ProcessNumber.SOUSTRACTION(Sqte_Stock , Vqte_vente);
					 
					 
					 Double  Stock_montant_ht_V      = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_ht());
					 Double  Stock_montant_tva_V     = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_tva());
					 
					 Double tot_Stock_Mnt_ht_vente   = ProcessNumber.addition(Stock_montant_ht_V , Vmnt_ht__vente);
					 Double tot_Stock_Mnt_tva_vente  = ProcessNumber.addition(Stock_montant_tva_V , Vmnt_tva_vente);
					 Double prix_unit_moyen_pond = new Double(0);
					 
					 if(sold_stock_jr.doubleValue()<0){
					    return false;
						    
					 } 
					 prix_unit_moyen_pond= stock.getCout_unitaire_moyen_pondere()!=null?stock.getCout_unitaire_moyen_pondere(): new Double(0);
					 
					 if(date_vente.equals(date_stock)){
						 
						 stock.getPk().setDate_stock( ProcessDate.convert_String_to_Date(date_vente));
						 Double tot_qte_vendu            = ProcessNumber.addition(SqteSvendu , Vqte_vente);
						 Double tot_mnt_ht__vente        = ProcessNumber.addition(Smnt_ht__vente , Vmnt_ht__vente);
						 Double tot_mnt_tva_vente        = ProcessNumber.addition(Smnt_tva_vente , Vmnt_tva_vente);
						 stock.setSolde_stock   ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
						 stock.setQuantite_vendu(ProcessFormatNbr.FormatDouble_Troischiffre (tot_qte_vendu));
						 stock.setMnt_tva_vente(ProcessFormatNbr.FormatDouble_Troischiffre(tot_mnt_tva_vente));
						 stock.setMnt_ht_vente (ProcessFormatNbr.FormatDouble_Troischiffre( tot_mnt_ht__vente));
						 
						 
						 stock.setSolde_vente_tva(ProcessFormatNbr.FormatDouble_Troischiffre(tot_Stock_Mnt_tva_vente));
						 stock.setSolde_vente_ht(ProcessFormatNbr.FormatDouble_Troischiffre( tot_Stock_Mnt_ht_vente));
						 
						 
						 stock.getPk().setFkCode_barre(detail_Bean.getFkcode_barre());
						 stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
						 stock.setDevise(stock.getDevise());
						 stock.setDeviseVente(detail_Bean.getFourniture().getDeviseFr());
						 stock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
						 session.saveOrUpdate(stock);
						
						 
					 }else{
						 Stock_articleBean new_stock = new Stock_articleBean();
						 new_stock.getPk().setDate_stock( ProcessDate.convert_String_to_Date(date_vente));
						 daoNumSequentiel.getNumSeqSimple(new_stock,"stock_article_id",session);
						 new_stock.setSolde_vente_tva(ProcessFormatNbr.FormatDouble_Troischiffre(tot_Stock_Mnt_tva_vente));
						 new_stock.setSolde_vente_ht(ProcessFormatNbr.FormatDouble_Troischiffre( tot_Stock_Mnt_ht_vente));
						 
						 new_stock.setSolde_stock   ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
						 new_stock.setQuantite_vendu(ProcessFormatNbr.FormatDouble_Troischiffre (Vqte_vente));
						 new_stock.setMnt_tva_vente(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_vente));
						 new_stock.setMnt_ht_vente (ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__vente));
						 
					 
						 new_stock.getPk().setFkCode_barre(detail_Bean.getFkcode_barre());
						 new_stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
						 new_stock.setDevise(stock.getDevise());
						 new_stock.setDeviseVente(detail_Bean.getFourniture().getDeviseFr());
						 new_stock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
 						 session.saveOrUpdate(new_stock);
					 }
					 
					  MouvementStockBean  mvtStock        = new MouvementStockBean(); 
				      mvtStock.setDate_stock(beanUpdate.getVente_date());
				      mvtStock.setFkCode_barre(detail_Bean.getFkcode_barre());
				      mvtStock.setDocument_com_id(detail_Bean.getFourniture().getFrn_ve_id());
				      mvtStock.getNat_mvt().setNature_mvt_id("cf");
				      mvtStock.setQuantite_sorti(Vqte_vente);
				      mvtStock.setTarifVente(detail_Bean.getTarifVente());
					  mvtStock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
					  mvtStock.setDepot(beanUpdate.getDepot());
					  mvtStock.setSolde_stock(sold_stock_jr);
					  mvtStock.setDevise(stock.getDevise());
					  mvtStock.setDeviseVente(detail_Bean.getFourniture().getDeviseFr());
					  session.saveOrUpdate(mvtStock);
					  detail_Bean.setCout_unit_moyen_pondere(prix_unit_moyen_pond);
					  detail_Bean.setMvt_stock(mvtStock);
					  session.update(detail_Bean);  
					 
					 String qString=""+
					 "   UPDATE  Stock_articleBean  bean    set   bean.solde_stock = bean.solde_stock - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vqte_vente)+" , " +
					 
					 "                                            bean.solde_vente_tva = bean.solde_vente_tva + "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_vente)+",  " +
					 "                                            bean.solde_vente_ht = bean.solde_vente_ht + "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_ht__vente)+"  " +
					 
				 		"                where   bean.pk.depot.depot_id="+beanUpdate.getDepot().getDepot_id()+"     "+
 				 		"                 AND    bean.pk.fkCode_barre.pk.code_barre='"+detail_Bean.getFkcode_barre().getPk().getCode_barre()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id='"+detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+detail_Bean.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  "+
				 		"                 AND    bean.pk.date_stock > '"+date_vente+"'  ";
				 		 
				 		 
					 session.createQuery(qString).executeUpdate();
				 
				 }
			 
		} catch (Exception e) {
			 resultat= false;
			 throw e;
		}
		return resultat;
		
	}
	
	
	private void  traitementCorrectionLotGeneric(ProcedureVenteBean beanUpdate , Session session  ) throws Exception {
	 
		 try {
			 
			   List  listmvtLotArticle   = doGetLotArtcicleDejaSauvgarderByVenteId(beanUpdate.getVente_id());
				for (int k = 0; k < listmvtLotArticle.size(); k++) {
					     MouvementSerieBean  	mvtSeriBean =(MouvementSerieBean) listmvtLotArticle.get(k);
					     HashMap  mapDevise= (HashMap) getObjectValueModel("map_devise");
					     DeviseBean devise =(DeviseBean) mapDevise.get( String.valueOf(beanUpdate.getDevise().getDev_id()) );
				    	 Double Qte= ProcessFormatNbr.FormatDouble_ParameterChiffre(mvtSeriBean.getQuantite_operation(),devise.getChiffre_pattern()) ;
				    	 SerieArticletBean  serie=mvtSeriBean.getPk().getSerieBean();
				    	 Double qteserie= ProcessFormatNbr.FormatDouble_ParameterChiffre(serie.getQuantite(),devise.getChiffre_pattern()) ; 
				    	 Double qtTotal= ProcessNumber.addition(qteserie, Qte);
				    	 serie.setQuantite(ProcessFormatNbr.FormatDouble_ParameterChiffre(qtTotal, devise.getChiffre_pattern())); 
				    	 serie.getEtat().setData_id("mod");
				    	 session.update(serie); 
				    	 session.delete(mvtSeriBean);
				 } 
				
		 } catch (Exception e) {  
		     throw e;  
		 } 
		 
		 
	}
	
	private void  traitementCorrectionLotGenericfourniture( FournitureVenteBean   beanUpdate , Session session  ) throws Exception {
		 
		 try {
			 
			   List  listmvtLotArticle   = doGetLotArtcicleDejaSauvgarderByVenteId(beanUpdate.getFrn_ve_id());
				for (int k = 0; k < listmvtLotArticle.size(); k++) {
					     MouvementSerieBean  	mvtSeriBean =(MouvementSerieBean) listmvtLotArticle.get(k);
					     HashMap  mapDevise= (HashMap) getObjectValueModel("map_devise");
					     DeviseBean devise =(DeviseBean) mapDevise.get( String.valueOf(beanUpdate.getDeviseFr().getDev_id()) );
				    	 Double Qte= ProcessFormatNbr.FormatDouble_ParameterChiffre(mvtSeriBean.getQuantite_operation(),devise.getChiffre_pattern()) ;
				    	 SerieArticletBean  serie=mvtSeriBean.getPk().getSerieBean();
				    	 Double qteserie= ProcessFormatNbr.FormatDouble_ParameterChiffre(serie.getQuantite(),devise.getChiffre_pattern()) ; 
				    	 Double qtTotal= ProcessNumber.addition(qteserie, Qte);
				    	 serie.setQuantite(ProcessFormatNbr.FormatDouble_ParameterChiffre(qtTotal, devise.getChiffre_pattern())); 
				    	 serie.getEtat().setData_id("mod");
				    	 session.update(serie); 
				    	 session.delete(mvtSeriBean);
				 } 
				
		 } catch (Exception e) {  
		     throw e;  
		 } 
		 
		 
	}

	private HashMap  traitement_for_lot_generic(ProcedureVenteBean beanUpdate,DetProcedureVenteBean detBean, List list_lot_article, Session session  ) throws Exception {
		Boolean  resultat=false;
		String   retournErreur="";
		HashMap  mapRes= new HashMap();
		 try {
				String ref_article_vente    =  detBean.getPk().getFkcode_barre().getPk().getCode_barre();
				double mayQ=detBean.getQuantite().doubleValue();
				double qteInitTot= 0; 
				boolean  lotchoix=false;
				
				 
				for (int k = 0; k < list_lot_article.size(); k++) {
				     SerieArticletBean  	serieBean=(SerieArticletBean) list_lot_article.get(k);
				     String ref_article_lot=serieBean.getFkCode_barre().getPk().getCode_barre();
				     if(  !ref_article_vente.equals(ref_article_lot) )continue;
				     
				     if( detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix()!=null &&
				    	 !StringUtils.isEmpty( detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix().getData_id())	&& 
				         detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix().getData_id().equals("sel")){
				    	 lotchoix=true;
				    	 if(serieBean.getSelected()!=null &&  !serieBean.getSelected().equals(""))  {    qteInitTot=qteInitTot+serieBean.getQuantite().doubleValue();}
				      }
				     if( detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix()==null || 
				    	 StringUtils.isEmpty( detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix().getData_id()) ||
					     detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix().getData_id().equals("arb")){
					     qteInitTot=qteInitTot+serieBean.getQuantite().doubleValue();
					  }
				    
				}
				
				if( qteInitTot<mayQ  &&  lotchoix==true ){
					mapRes.put("retournErreur", "La quantit� en Lot choisie n'est pas disponible");
					mapRes.put("retour", false);
					return mapRes;
				}
				
				if( qteInitTot<mayQ  &&  lotchoix==false ){
					mapRes.put("retournErreur", "La quantit� en Lot    n'est pas disponible");
					mapRes.put("retour", false);
					return mapRes;
				}
					
				
				
				for (int k = 0; k < list_lot_article.size(); k++) {
					
				     SerieArticletBean  	serieBean=(SerieArticletBean) list_lot_article.get(k);
				     String ref_article_lot=serieBean.getFkCode_barre().getPk().getCode_barre();
				     if(  !ref_article_vente.equals(ref_article_lot) )continue;
				     
				     if( detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix()!=null &&
				    		 !StringUtils.isEmpty( detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix().getData_id())	&& 
					     detBean.getPk().getFkcode_barre().getPk().getAr_bean().getChoix().getData_id().equals("sel")){
					     if(    StringUtils.isEmpty(serieBean.getSelected()))  {  continue;   }
					 }
				     
				     if(serieBean.getQuantite().doubleValue()>mayQ){
				    	 MouvementSerieBean  mvtBean = new MouvementSerieBean();
						 mvtBean.getPk().setSerieBean(serieBean);
						 mvtBean.setDate_mvt_serie(beanUpdate.getVente_date());
						// mvtBean.getPk().setFkCode_barre(detBean.getPk().getFkcode_barre());
						 mvtBean.getPk().setDocument_com_id(beanUpdate.getVente_id());
						 mvtBean.getPk().getNat_mvt().setNature_mvt_id("ve");
						 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_vente());
						 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_vente());
						 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(mayQ);
						 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
						 mvtBean.setQuantite_operation(qteRes);
						 mvtBean.setTarif_operation_id(detBean.getTarif().getTarif_vente_id());
						 session.saveOrUpdate(mvtBean);
						 Double res_diff=ProcessNumber.SOUSTRACTION(serieBean.getQuantite(), mayQ);
						 serieBean.setQuantite(res_diff);
						 
						 if(res_diff==0)
							 serieBean.getEtat().setData_id("ter");
						 if(res_diff>0)
							 serieBean.getEtat().setData_id("mod");
						 
						 session.saveOrUpdate(serieBean);
						 resultat=true;
						 break;
				     }
				     
                  if(serieBean.getQuantite().doubleValue()<mayQ){
                 	  
				    	 MouvementSerieBean  mvtBean = new MouvementSerieBean();
						 mvtBean.getPk().setSerieBean(serieBean);
						 mvtBean.setDate_mvt_serie(beanUpdate.getVente_date());
						 //mvtBean.getPk().setFkCode_barre(detBean.getPk().getFkcode_barre());
						 mvtBean.getPk().setDocument_com_id(beanUpdate.getVente_id());
						 mvtBean.getPk().getNat_mvt().setNature_mvt_id("ve");
						 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_vente());
						 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_vente());
						 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getQuantite());
						 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
						 mvtBean.setQuantite_operation(qteRes);
						 mvtBean.setTarif_operation_id(detBean.getTarif().getTarif_vente_id());
						 Double resto=ProcessNumber.SOUSTRACTION(mayQ,  serieBean.getQuantite());
						 mayQ=resto;
						 session.saveOrUpdate(mvtBean);
						 serieBean.setQuantite(new Double(0));
						 serieBean.getEtat().setData_id("ter");
						 session.saveOrUpdate(serieBean);
						 resultat=true;
						 continue;
				    	 
				     }
                  if(serieBean.getQuantite().doubleValue()==mayQ){
                 	 
                 	     MouvementSerieBean  mvtBean = new MouvementSerieBean();
						 mvtBean.getPk().setSerieBean(serieBean);
						 mvtBean.setDate_mvt_serie(beanUpdate.getVente_date());
						 //mvtBean.getPk().setFkCode_barre(detBean.getPk().getFkcode_barre());
						 mvtBean.getPk().setDocument_com_id(beanUpdate.getVente_id());
						 mvtBean.getPk().getNat_mvt().setNature_mvt_id("ve");
						 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_vente());
						 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_vente());
						 
						 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(mayQ);
						 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
						 mvtBean.setQuantite_operation(qteRes);
						 mvtBean.setTarif_operation_id(detBean.getTarif().getTarif_vente_id());
						 session.saveOrUpdate(mvtBean);
						 
						 
					     serieBean.getEtat().setData_id("ter");
						 
						 
						 serieBean.setQuantite(new Double(0));
						 session.saveOrUpdate(serieBean);
						  
						 resultat=true;
						 break;
				     }

				 } 
				 
		 } catch (Exception e) {  
		     throw e;  
		 } 
			mapRes.put("retournErreur", "");
			mapRes.put("retour", resultat);
			return mapRes;
	}
	
	
	private HashMap  traitement_for_lot_generic_fourniture(ProcedureVenteBean beanUpdate,DetFournitureVenteBean detBean, List list_lot_article, Session session  ) throws Exception {
		Boolean  resultat=false;
		String   retournErreur="";
		HashMap  mapRes= new HashMap();
		 try {
				String ref_article_vente    =  detBean.getFkcode_barre().getPk().getCode_barre();
				double mayQ=detBean.getQuantite().doubleValue();
				double qteInitTot= 0; 
				boolean  lotchoix=false;
				 
				for (int k = 0; k < list_lot_article.size(); k++) {
				     SerieArticletBean  	serieBean=(SerieArticletBean) list_lot_article.get(k);
				     String ref_article_lot=serieBean.getFkCode_barre().getPk().getCode_barre();
				     if(  !ref_article_vente.equals(ref_article_lot) )continue;
				     
				     if( detBean.getFkcode_barre().getPk().getAr_bean().getChoix()!=null &&
				    	 !StringUtils.isEmpty( detBean.getFkcode_barre().getPk().getAr_bean().getChoix().getData_id())	&& 
				         detBean.getFkcode_barre().getPk().getAr_bean().getChoix().getData_id().equals("sel")){
				    	 lotchoix=true;
				    	 if(serieBean.getSelected()!=null &&  !serieBean.getSelected().equals(""))  {    qteInitTot=qteInitTot+serieBean.getQuantite().doubleValue();}
				      }
				     if( detBean.getFkcode_barre().getPk().getAr_bean().getChoix()==null || 
				    	 StringUtils.isEmpty( detBean.getFkcode_barre().getPk().getAr_bean().getChoix().getData_id()) ||
					     detBean.getFkcode_barre().getPk().getAr_bean().getChoix().getData_id().equals("arb")){
					     qteInitTot=qteInitTot+serieBean.getQuantite().doubleValue();
					  }
				    
				}
				
				if( qteInitTot<mayQ  &&  lotchoix==true ){
					mapRes.put("retournErreur", "La quantit� en Lot choisie n'est pas disponible");
					mapRes.put("retour", false);
					return mapRes;
				}
				
				if( qteInitTot<mayQ  &&  lotchoix==false ){
					mapRes.put("retournErreur", "La quantit� en Lot    n'est pas disponible");
					mapRes.put("retour", false);
					return mapRes;
				}
					
				
				
				for (int k = 0; k < list_lot_article.size(); k++) {
					
				     SerieArticletBean  	serieBean=(SerieArticletBean) list_lot_article.get(k);
				     String ref_article_lot=serieBean.getFkCode_barre().getPk().getCode_barre();
				     if(  !ref_article_vente.equals(ref_article_lot) )continue;
				     
				     if( detBean.getFkcode_barre().getPk().getAr_bean().getChoix()!=null &&
				    		 !StringUtils.isEmpty( detBean.getFkcode_barre().getPk().getAr_bean().getChoix().getData_id())	&& 
					     detBean.getFkcode_barre().getPk().getAr_bean().getChoix().getData_id().equals("sel")){
					     if(    StringUtils.isEmpty(serieBean.getSelected()))  {  continue;   }
					 }
				     
				     if(serieBean.getQuantite().doubleValue()>mayQ){
				    	 MouvementSerieBean  mvtBean = new MouvementSerieBean();
						 mvtBean.getPk().setSerieBean(serieBean);
						 mvtBean.setDate_mvt_serie(beanUpdate.getVente_date());
						 mvtBean.getPk().setDocument_com_id(detBean.getFourniture().getFrn_ve_id());
						 mvtBean.getPk().getNat_mvt().setNature_mvt_id("cf");
						 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_vente());
						 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_vente());
						 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(mayQ);
						 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
						 mvtBean.setQuantite_operation(qteRes);
						 mvtBean.setTarif_operation_id(detBean.getTarifVente().getTarif_vente_id());
						 session.save(mvtBean);
						 Double res_diff=ProcessNumber.SOUSTRACTION(serieBean.getQuantite(), mayQ);
						 serieBean.setQuantite(res_diff);
						 
						 if(res_diff==0)
							 serieBean.getEtat().setData_id("ter");
						 if(res_diff>0)
							 serieBean.getEtat().setData_id("mod");
						 
						 session.saveOrUpdate(serieBean);
						 resultat=true;
						 break;
				     }
				     
                  if(serieBean.getQuantite().doubleValue()<mayQ){
                 	  
				    	 MouvementSerieBean  mvtBean = new MouvementSerieBean();
						 mvtBean.getPk().setSerieBean(serieBean);
						 mvtBean.setDate_mvt_serie(beanUpdate.getVente_date());
						 mvtBean.getPk().setDocument_com_id(detBean.getFourniture().getFrn_ve_id());
						 mvtBean.getPk().getNat_mvt().setNature_mvt_id("cf");
						 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_vente());
						 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_vente());
						 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getQuantite());
						 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
						 mvtBean.setQuantite_operation(qteRes);
						 mvtBean.setTarif_operation_id(detBean.getTarifVente().getTarif_vente_id());
						 Double resto=ProcessNumber.SOUSTRACTION(mayQ,  serieBean.getQuantite());
						 mayQ=resto;
						 session.save(mvtBean);
						 serieBean.setQuantite(new Double(0));
						 serieBean.getEtat().setData_id("ter");
						 session.saveOrUpdate(serieBean);
						 resultat=true;
						 continue;
				    	 
				     }
                  if(serieBean.getQuantite().doubleValue()==mayQ){
                 	 
                 	     MouvementSerieBean  mvtBean = new MouvementSerieBean();
						 mvtBean.getPk().setSerieBean(serieBean);
						 mvtBean.setDate_mvt_serie(beanUpdate.getVente_date());
						 mvtBean.getPk().setDocument_com_id(detBean.getFourniture().getFrn_ve_id());
						 mvtBean.getPk().getNat_mvt().setNature_mvt_id("cf");
						 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_vente());
						 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_vente());
						 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(mayQ);
						 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
						 mvtBean.setQuantite_operation(qteRes);
						 mvtBean.setTarif_operation_id(detBean.getTarifVente().getTarif_vente_id());
						 session.save(mvtBean);
					     serieBean.getEtat().setData_id("ter");
						 serieBean.setQuantite(new Double(0));
						 session.saveOrUpdate(serieBean);
						  
						 resultat=true;
						 break;
				     }

				 } 
				 
		 } catch (Exception e) {  
		     throw e;  
		 } 
			mapRes.put("retournErreur", "");
			mapRes.put("retour", resultat);
			return mapRes;
	}

	private boolean traitement_for_lot_personnaliser(ProcedureVenteBean beanUpdate, DetProcedureVenteBean detBean,Session session) throws Exception {
	 
		boolean result=false;
		try {
		  
			String ref_article_vente    =  detBean.getPk().getFkcode_barre().getPk().getCode_barre();
			List list_lot_personnaliser =  (List) getObjectValueModel( ProcedureVenteTemplate.LIST_SERIE_PROVENTE+ref_article_vente);
			if(list_lot_personnaliser!=null)
			for (int k = 0; k < list_lot_personnaliser.size(); k++) {
			     SerieArticletBean  	beanPersonnaliser=(SerieArticletBean) list_lot_personnaliser.get(k);
			     MouvementSerieBean  mvtBean = new MouvementSerieBean();
				 mvtBean.getPk().setSerieBean(beanPersonnaliser);
				 mvtBean.setDate_mvt_serie(beanUpdate.getVente_date());
				 //mvtBean.getPk().setFkCode_barre(detBean.getPk().getFkcode_barre());
				 mvtBean.getPk().setDocument_com_id(beanUpdate.getVente_id());
				 
				 
				 Double quantite_choisi=ProcessFormatNbr.FormatDouble_Troischiffre(beanPersonnaliser.getQuantite_choisi());
				 mvtBean.getPk().getNat_mvt().setNature_mvt_id("ve");
				 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_vente());
				 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_vente());
				
			
				 Double qteRes=ProcessNumber.PRODUIT(quantite_choisi, new Double(1));
				 mvtBean.setQuantite_operation(qteRes);
				 mvtBean.setTarif_operation_id(detBean.getTarif().getTarif_vente_id());
				
				 session.save(mvtBean);
				 Double qte_dispo=ProcessFormatNbr.FormatDouble_Troischiffre(beanPersonnaliser.getQuantite());
				 Double qte_result=ProcessNumber.SOUSTRACTION(qte_dispo, quantite_choisi);
				 beanPersonnaliser.setQuantite(ProcessFormatNbr.FormatDouble_Troischiffre(qte_result));
				 if(qte_result==0)
					 beanPersonnaliser.getEtat().setData_id("ter");
				 if(qte_result>0)
					 beanPersonnaliser.getEtat().setData_id("mod");
				 
				 session.update(beanPersonnaliser);
				 result=true;
			}
			
		 
		 } catch (Exception e) {  
			 result = false;
		     throw e;  
		 } 
		return result;
		
	}

	public Boolean doUpdateProcedureVente(ProcedureVenteBean beanUpdate)  throws Exception { 
	    boolean result=false; 
	    Session session =  openSessionHibernate(sessionFactory);
		try {
			List <DetProcedureVenteBean> listOrigin=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_VENTE_CLONE);
			List <DetProcedureVenteBean> listInsert=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_VENTE);
			List <DetFournitureVenteBean> listOriginFrour=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_FOURNITURE_VENTE_CLONE);
			List <DetFournitureVenteBean> listInsertFour=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_FOURNITURE_VENTE);
			List <DetServiceBean> listOriginService=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_PRESTATION_CLONE);
			List <DetServiceBean> listInsertService=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_PRESTATION);
			setIdBean((ProcedureVenteBean) getObjectValueModel(FORM_BEAN), beanUpdate, ProcedureVenteTemplate.id_entite);
			FournitureVenteBean  fournitureVenteBean = new FournitureVenteBean();
			ServiceBean serviceBean = new ServiceBean();
			
			this.setUpdateValueFieldTraceOject(beanUpdate);
			for (DetProcedureVenteBean beArticle:listOrigin) {
				fournitureVenteBean.setVenteFrn(beanUpdate);
				serviceBean.setVenteSrv(beanUpdate);
				
				session.delete(beArticle);
				if(beArticle.getMvt_stock()!=null)
				session.delete(beArticle.getMvt_stock());
				if(beArticle.getDrv()!=null)
				session.delete(beArticle.getDrv());
			}
			for (DetFournitureVenteBean beanFour:listOriginFrour) {
				fournitureVenteBean =(FournitureVenteBean) ProcessUtil.cloneObject(beanFour.getFourniture()) ;
				session.delete(beanFour);
				if(beanFour.getMvt_stock()!=null)
				session.delete(beanFour.getMvt_stock());
			}
			for (DetServiceBean beanService:listOriginService) {
				serviceBean =(ServiceBean) ProcessUtil.cloneObject(beanService.getService()) ;
				session.delete(beanService);
			}
			session.flush();
			session.clear();
			
			HashMap  map_deriver_vente  =(HashMap) getObjectValueModel(ProcedureVenteTemplate.MAP_DERIVER_VENTE);
			if( map_deriver_vente!=null   &&  map_deriver_vente.size() >0 ) {
				Set setMap_deriver_vente=map_deriver_vente.keySet();
				for (Iterator iter = setMap_deriver_vente.iterator(); iter.hasNext();) {
					String codeBarr = (String) iter.next();
					DeriverOperationVente dVente = (DeriverOperationVente) map_deriver_vente.get(codeBarr);
					if(dVente!=null) {
						this.setBeanTrace(dVente);
						session.save(dVente);
					}
				}
			}
			
			
			
		 
			
			for (DetProcedureVenteBean bxe:listInsert) {
				
				bxe.getPk().setVente(beanUpdate);
				bxe.setMvt_stock(null);
				if( map_deriver_vente!=null   &&  map_deriver_vente.size() >0 ) {
					DeriverOperationVente dVente = (DeriverOperationVente) map_deriver_vente.get(bxe.getPk().getFkcode_barre().getPk().getCode_barre());
					if(dVente!=null) {
						bxe.setDrv(dVente);
					}
				}
				
				if(bxe.isPrix_vente_is_changed()) {
					TarificationBean  tarifOrigin =   bxe.getTarif() ;
					TarificationBean  tarifNew = new TarificationBean();
					
					
					HashMap  mapclient  =  (HashMap)getObjectValueModel( ProcedureVenteTemplate.MAP_CLIENT_BEN);
					ClientBean  ben     =  (ClientBean) mapclient.get(beanUpdate.getClient().getClt_id());
					Type_tarificationBean groupe = new Type_tarificationBean();
					groupe.setType_trf_lib(BDateTime.getDateActuel_system()+" "+ProcessDate.getTime(new Date())+" "+ben.getClt_id()+"/"+ben.getClt_lib());
					setBeanTrace(groupe);
					session.save(groupe);
					
					tarifNew.setGroupe(groupe);
					tarifNew.setBean_cal(tarifOrigin.getBean_cal());
					tarifNew.setFkCode_barre(tarifOrigin.getFkCode_barre());
					tarifNew.setDevise(tarifOrigin.getDevise());
					tarifNew.setTvaBean(tarifOrigin.getTvaBean());
					tarifNew.setDepot(null);
					tarifNew.setDate_trf(beanUpdate.getVente_date());
					tarifNew.setTarif_unit_vente(tarifOrigin.getTarif_unit_vente());
					tarifNew.setValeur_de_laTva(ProcessNumber.getMontantTvaByMontantHT(tarifOrigin.getTarif_unit_vente(), tarifOrigin.getTvaBean(), new DeviseBean()));
					tarifNew.setTarif_unit_vente_tt(ProcessNumber.getMontantTTCByMontantHT(tarifOrigin.getTarif_unit_vente(), tarifOrigin.getTvaBean(),  new DeviseBean() ));
					tarifNew.setTarif_lot(null);
					tarifNew.setNum_serie(null);
					daoNumSequentiel.getNumSeqSimple(tarifNew,"tarif_vente_id",session);
					if(tarifOrigin.getCout()!=null && (tarifOrigin.getCout().getTarif_prim_id()==null || tarifOrigin.getCout().getTarif_prim_id().equals("")))
					tarifNew.setCout(null);
					this.setBeanTrace(tarifNew);
					session.save(tarifNew);
					bxe.setTarif(tarifNew);  
				}
				
				session.save(bxe);
			}
			
			for (DetFournitureVenteBean detfourn:listInsertFour) {
				if( detfourn.getQuantite()==null) { continue; }
				if( detfourn.getQuantite().doubleValue()==0 ||  detfourn.getQuantite().doubleValue()<0) { continue;}
				
				if( StringUtils.isEmpty(fournitureVenteBean.getFrn_ve_id()) ) {
					daoNumSequentiel.getNumSeqSimple(fournitureVenteBean,"frn_ve_id",session);
					this.setBeanTrace(fournitureVenteBean);
					fournitureVenteBean.setFrn_ve_libelle(beanUpdate.getVente_libelle());
					fournitureVenteBean.setVenteFrn(beanUpdate);
					fournitureVenteBean.setClient(beanUpdate.getClient());
					fournitureVenteBean.setFrn_ve_date(beanUpdate.getVente_date());
					fournitureVenteBean.setDepot(beanUpdate.getDepot());
					session.save(fournitureVenteBean);
				}
				
				detfourn.setFourniture(fournitureVenteBean);
				detfourn.setIsVente(false);
				detfourn.setMvt_stock(null);
				session.save(detfourn);
			}
			
			for (DetServiceBean detService:listInsertService) {
				if( detService.getQuantite()==null) { continue; }
				if( detService.getQuantite().doubleValue()==0 ||  detService.getQuantite().doubleValue()<0) { continue;}
				
				if( StringUtils.isEmpty(serviceBean.getSrv_id()) ) {
					daoNumSequentiel.getNumSeqSimple(serviceBean,"srv_id",session);	
					this.setBeanTrace(serviceBean);
					serviceBean.setSrv_libelle(beanUpdate.getVente_libelle());
					serviceBean.setVenteSrv(beanUpdate);
					serviceBean.setClient(beanUpdate.getClient());
					serviceBean.setSrv_date(beanUpdate.getVente_date());
					serviceBean.setDepot(beanUpdate.getDepot());
					session.save(serviceBean);
				}
				
				detService.setService(serviceBean);
				detService.setIsVente(false);
				session.save(detService);
				
			}
			ProcedureVenteBean beanTotal =(ProcedureVenteBean) getObjectValueModel(ProcedureVenteTemplate.BEAN_TOTAL);
			beanUpdate.setVente_remise(beanTotal.getVente_remise());
			beanUpdate.setVente_mnt_ht(beanTotal.getVente_mnt_ht());
			beanUpdate.setVente_mnt_tva(beanTotal.getVente_mnt_tva());
			beanUpdate.setVente_mnt_total(beanTotal.getVente_mnt_total());
			beanUpdate.setMarge_benefice_vente(beanTotal.getMarge_benefice_vente());
			session.update(beanUpdate);
			session.createQuery( " UPDATE  ProcedureVenteBean b  set      b.modeBean.fct_id="+GenericActionBean.Fn_Modifier+"   " +
					"      where   b.vente_id='"+beanUpdate.getVente_id()+"' ").executeUpdate();
			
			saveTrace(beanUpdate);
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
	
	
	public Boolean doCorrigerProcedureVente(ProcedureVenteBean beanUpdate)  throws Exception { 
	    boolean result=false; 
	    Session session =  openSessionHibernate(sessionFactory);
		try {
			TraitementCorrectionVenteArticleMarchandise(beanUpdate,session);
			TraitementCorrectionVenteArticleFourntire(beanUpdate,session);
			session.createQuery( " UPDATE  ProcedureVenteBean b  set      b.modeBean.fct_id="+GenericActionBean.Fn_Modifier+"   " +
							"      where   b.vente_id='"+beanUpdate.getVente_id()+"' ").executeUpdate();
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
	
public Boolean doSaveFacture( ProcedureVenteBean detailBean, List  liste_detaille_facture) throws Exception {
	    
		Session session =  openSessionHibernate(sessionFactory);
		
		 
	    
		boolean result = false;
		try {
			 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 ProcedureVenteBean beanUpdate=(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
			 beanUpdate.setFifo(detailBean.getFifo());
		     result  = TraitementVenteArticleMarchandise(detailBean,session);
		     List <DetFournitureVenteBean> listOfmyData=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_FOURNITURE_VENTE);
			 if(listOfmyData!=null  &&  listOfmyData.size()>0){
					TraitementFournitureVente(listOfmyData,detailBean,session);
			 }
		    HashMap  mapclientBen   =(HashMap) getObjectValueModel(ProcedureVenteTemplate.MAP_CLIENT_BEN);
		    ClientBean  cliBean = (ClientBean) mapclientBen.get(detailBean.getClient().getClt_id());
		    ProcedureVenteBean beanTotal =(ProcedureVenteBean) getObjectValueModel(ProcedureVenteTemplate.BEAN_TOTAL);
		    Facture_clientBean beanSaveS = new Facture_clientBean();
		    beanSaveS.setFact_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
		    beanSaveS.setClient(cliBean);
		    beanSaveS.setCpt_bank(cliBean.getCompte());
			beanSaveS.getTypefact().setType_fact_id(new Integer(1));
			beanSaveS.setTotal_ht_fact(beanTotal.getVente_mnt_ht());
			beanSaveS.setTotal_tva_fact(beanTotal.getVente_mnt_tva());
			
			Double timbre=ProcessFormatNbr.FormatDouble_ParameterChiffre(bs.getSociete().getMontant_timbre_fiscal(),"0.000");
			//Double getVente_mnt_total=  ProcessNumber.addition(beanTotal.getVente_mnt_total(), timbre )   ;
			beanSaveS.setTotal_facture(beanTotal.getVente_mnt_total());
			
			beanSaveS.setNet_a_payer(beanTotal.getVente_mnt_net_a_payer());
			beanSaveS.setFacture_remise(beanTotal.getVente_remise());
			beanSaveS.setMnt_timb_fisc(beanTotal.getMontant_timbre_fiscal());
			beanSaveS.setAvance_montant_vente(beanTotal.getAvance_montant_vente());
			beanSaveS.getEtat_reg().setData_id("fnon");
			
			String numios= getRequest().getParameter("numiosFacture");
			
			  if(numios==null || numios.equals("null") ) {
				   daoNumSequentiel.getNumSeqSimple(beanSaveS,"fact_clt_id",session,"F");
			  }else {
				  beanSaveS.setFact_clt_id(numios);
			  }
			 
			  
			  
			  
		 
			
			if(beanSaveS.getModReg()!=null && beanSaveS.getModReg().getMod_id()!=null)
				beanSaveS.setModReg(beanSaveS.getModReg());
				else
			beanSaveS.setModReg(null);
			beanSaveS.setDevise(beanUpdate.getDevise());
			setBeanTrace(beanSaveS);
			session.save(beanSaveS);
			//saveTrace(beanSaveS);
			 
			boolean result_detaille = false;
			for (int i = 0; i < liste_detaille_facture.size(); i++) {
				Det_Fact_ClientBean detBean=(Det_Fact_ClientBean) liste_detaille_facture.get(i);
				
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
							"            b.modeBean.fct_id="+GenericActionBean.Fn_Facturer+"   " +
									"     where   b.vente_id='"+bDetail_mvt_vente.getPk().getVente().getVente_id()+"' ").executeUpdate();
				}
				detBean.getPk().setFactclient(beanSaveS);
				detBean.getPk().setMvtVente(mvt_vente);
				session.save(detBean);
				result_detaille=true;
				 
			}
			if(!result_detaille)throwNewException("err_inser_deaill");
			setObjectValueModel("factureImprimer", beanSaveS);
			setObjectValueModel("detailFcatureImprimer", liste_detaille_facture);
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


	
	public Boolean doDeleteProcedureVente(ProcedureVenteBean beanDelete)  throws Exception  {
	    boolean result=false; 
	    Session session =  openSessionHibernate(sessionFactory);
		try {

			List <DetProcedureVenteBean> listOrigin=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_VENTE_CLONE);
			List <DetProcedureVenteBean> listInsert=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_VENTE);
			List <DetFournitureVenteBean> listOriginFrour=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_FOURNITURE_VENTE_CLONE);
			List <DetFournitureVenteBean> listInsertFour=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_FOURNITURE_VENTE);
			List <DetServiceBean> listOriginService=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_PRESTATION_CLONE);
			List <DetServiceBean> listInsertService=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_PRESTATION);
		 
			FournitureVenteBean  fournitureVenteBean = new FournitureVenteBean();
			ServiceBean serviceBean = new ServiceBean();
			 
		    for (DetProcedureVenteBean beArticle:listOrigin) {
				session.delete(beArticle);
				if(beArticle.getMvt_stock()!=null)
				session.delete(beArticle.getMvt_stock());
				if(beArticle.getDrv()!=null)
				session.delete(beArticle.getDrv());
			}
			for (DetFournitureVenteBean beanFour:listOriginFrour) {
				fournitureVenteBean =(FournitureVenteBean) ProcessUtil.cloneObject(beanFour.getFourniture()) ;
				session.delete(beanFour);
				if(beanFour.getMvt_stock()!=null)
				session.delete(beanFour.getMvt_stock());
			}
			
			for (DetServiceBean beanService:listOriginService) {
				serviceBean =(ServiceBean) ProcessUtil.cloneObject(beanService.getService()) ;
				session.delete(beanService);
			}
			session.flush();
			session.clear();
			 
			session.delete(fournitureVenteBean); 
			session.delete(serviceBean); 
			session.delete(beanDelete);
			saveTrace(beanDelete);
			
			 if(  beanDelete.getCommande()!=null &&     !StringUtils.isEmpty(beanDelete.getCommande().getCmd_id()))
				 session.createQuery(" UPDATE  CommandeclientBean b  set  b.modeBean.fct_id="+GenericActionBean.Fn_Contremander+"  " +
						 		     " where   b.cmd_id='"+beanDelete.getCommande().getCmd_id()+"'   ").executeUpdate();
			 
			 
			 
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
