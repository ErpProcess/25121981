package ERP.Process.Commerciale.Vente.RetourVente.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.MouvementStock.model.Incident_mvt_serieBean;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.Stock_article.dao.Stock_articleDAO;
import ERP.Process.Commerciale.Stock.Stock_article.model.IncidentStock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.DetRetourVenteBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.Incident_det_retour_venteBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
import ERP.Process.Commerciale.Vente.RetourVente.template.RetourVenteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
@Repository
public class RetourVenteDAO extends  GenericWeb    {

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
	
	
	@SuppressWarnings("unchecked")
	public List<RetourVenteBean> doFindListRetourVente(RetourVenteBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<RetourVenteBean>   lisf = new ArrayList<RetourVenteBean>();
		try{
		    String requette=" select  bean   FROM    RetourVenteBean    bean    WHERE     1=1       ";
		    	if( !StringUtils.isEmpty(beanSearch.getRet_vente_id()) )  
			    requette+="   AND   bean.ret_vente_id = '"+beanSearch.getRet_vente_id()+"'        ";    
				 if( !StringUtils.isEmpty(beanSearch.getVente().getVente_id()) )  
			    requette+="   AND   bean.vente.vente_id = '"+beanSearch.getVente().getVente_id()+"'        ";    
				 
				 if (!StringUtils.isEmpty(beanSearch.getVente().getClient().getClt_id()))
						requette += "   AND   bean.vente.client.clt_id ='"+beanSearch.getVente().getClient().getClt_id()+"'        ";
				 
			 
					if (beanSearch.getRet_vente_date() != null)
						requette += "   AND  bean.ret_vente_date >='"+ProcessDate.getStringFormatDate(beanSearch.getRet_vente_date())+"'        ";

					if (beanSearch.getRet_vente_date2() != null)
						requette += "   AND  bean.ret_vente_date <='"+ProcessDate.getStringFormatDate(beanSearch.getRet_vente_date2())+"'        ";
					
				 
				 
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
	public List<Incident_det_retour_venteBean> doFindIncidentDetaille_ListProcedureVente(RetourVenteBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<Incident_det_retour_venteBean>   lisf = new ArrayList<Incident_det_retour_venteBean>();
		try {
		    String requette   =   "   select  bean   FROM    Incident_det_retour_venteBean    bean    WHERE     1=1       ";
				   requette  +=   "   AND    bean.pk.r_vente.ret_vente_id='"+beanSearch.getRet_vente_id()+"'         ";
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
	public List<DetRetourVenteBean> doFindList_detaille_RetourVente(RetourVenteBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<DetRetourVenteBean>   lisf = new ArrayList<DetRetourVenteBean>();
		try{
		    String requette=" select  bean   FROM    DetRetourVenteBean    bean    WHERE  1=1  " ; 
		    
			 if (!StringUtils.isEmpty(beanSearch.getRet_vente_id()))
					requette += "   AND    bean.pk.r_vente.ret_vente_id='"+beanSearch.getRet_vente_id()+"'         ";
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
	public List<DetRetourVenteBean> doFindList_detaille_RetourVente(DetRetourVenteBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<DetRetourVenteBean>   lisf = new ArrayList<DetRetourVenteBean>();
		try{
		    String requette=" select  bean   FROM    DetRetourVenteBean    bean    WHERE  1=1  " ; 
		    
			 if (!StringUtils.isEmpty(beanSearch.getPk().getR_vente().getRet_vente_id()))
					requette += "   AND    bean.pk.r_vente.ret_vente_id='"+beanSearch.getPk().getR_vente().getRet_vente_id()+"'         ";
			 
			 if (!StringUtils.isEmpty(beanSearch.getSens().getData_id()))
					requette += "   AND    bean.sens.data_id='"+beanSearch.getSens().getData_id()+"'         ";
			 
			 if ( beanSearch.getCause().getNature_incident_id()!=null)
					requette += "   AND    bean.cause.data_id='"+beanSearch.getCause().getNature_incident_id()+"'         ";
			 
			 if (!StringUtils.isEmpty(beanSearch.getPk().getR_vente().getVente().getVente_id()))
					requette += "   AND    bean.pk.r_vente.vente.vente_id='"+beanSearch.getPk().getR_vente().getVente().getVente_id()+"'         ";
			 
			 
			 
			 
			 
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
	public List<Incident_mvt_serieBean> doFindList_detaille_Incident_serie(String List_re_vente_id) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<Incident_mvt_serieBean>   lisf = new ArrayList<Incident_mvt_serieBean>();
		try{
		    String requette=" select  bean   FROM    Incident_mvt_serieBean    bean    WHERE   " +
		    		"   bean.pk.mvt_incident_id  in ("+List_re_vente_id+")       ";
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
	public List<DetRetourVenteBean> doFindList_detaille_plusieur_RetourVente(String lesVente ) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<DetRetourVenteBean>   lisf = new ArrayList<DetRetourVenteBean>();
		try{
		    String requette=" select  bean   FROM    DetRetourVenteBean    bean    WHERE   " +
		    		"   bean.pk.detv.pk.vente.vente_id  in ("+lesVente+"  )     ";
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
	
	
	public Boolean doSaveRetourVente(RetourVenteBean beanSave) throws Exception {
	     boolean result=false; 
	     Session session =  openSessionHibernate(sessionFactory);
		try {
			daoNumSequentiel.getNumSeqSimple(beanSave,"ret_vente_id",session);
			setBeanTrace(beanSave);
			session.save(beanSave);
			List 	<DetRetourVenteBean>list_editable_RetourVente = (List) getObjectValueModel( RetourVenteTemplate.LIST_EDITABLE_RETOUR_VENTE  );
			for (int i = 0; i < list_editable_RetourVente.size(); i++) {
				DetRetourVenteBean dBean = list_editable_RetourVente.get(i);
				
				if(dBean.getTo_check().equals("")) continue;
				Incident_det_retour_venteBean  IncidBEan= new Incident_det_retour_venteBean();
				dBean.getPk().setR_vente(beanSave);
				IncidBEan.getPk().setCause(dBean.getCause());
				IncidBEan.setSens(dBean.getSens());
				IncidBEan.getPk().setDetv(dBean.getPk().getDetv());
				IncidBEan.getPk().setR_vente(beanSave);
				IncidBEan.setQuantite_retourne(dBean.getQuantite_retourne());
				IncidBEan.setObservation(dBean.getObservation());
				IncidBEan.setMontant_ht_retour(dBean.getMontant_ht_retour());
				IncidBEan.setMontant_tva_retour(dBean.getMontant_tva_retour());
				setBeanTrace(dBean);
				session.save(dBean);
				
				setBeanTrace(IncidBEan);
				session.save(IncidBEan);
			}
			saveTrace(beanSave);
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
	public Boolean doUpdateRetourVente(RetourVenteBean beanUpdate)  throws Exception { 
	    boolean result=false; 
	    Session session =  openSessionHibernate(sessionFactory);
		try {
		 
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.update(beanUpdate);
			this.saveTrace(beanUpdate);
			 
		 
			 
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		    if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier) ||  bs.getFct_id().equals(GenericActionBean.Fn_Confirmer) ||  bs.getFct_id().equals(GenericActionBean.Fn_Affirmer)   ){
				 this.setUpdateValueFieldTraceOject(beanUpdate);
				 session.update(beanUpdate);
				 this.saveTrace(beanUpdate);
			 }
					
					List  <DetRetourVenteBean>list_editable_RetourVente=(List) getObjectValueModel(RetourVenteTemplate.LIST_EDITABLE_RETOUR_VENTE );
					List  <DetRetourVenteBean>list_editable_RetourVente_Origine=(List) getObjectValueModel( RetourVenteTemplate.LIST_EDITABLE_RETOUR_VENTE_ORIGINE );
					
					
					List  <Incident_det_retour_venteBean>list_incident_det_vente_origine=(List) getObjectValueModel( RetourVenteTemplate.LIST_INCIDENT_DET_VENTE_ORIGINE );
					
					if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier) ||  bs.getFct_id().equals(GenericActionBean.Fn_Affirmer) ){
						
						for (int i = 0; i < list_editable_RetourVente_Origine.size(); i++) {
							DetRetourVenteBean beaSUp=list_editable_RetourVente_Origine.get(i);
							session.delete(beaSUp);
						}
						 
						for (int i = 0; i < list_incident_det_vente_origine.size(); i++) {
							Incident_det_retour_venteBean beaSUp=list_incident_det_vente_origine.get(i);
							session.delete(beaSUp);
						}
						session.flush();
						
						for (int i = 0; i < list_editable_RetourVente.size(); i++) {
							DetRetourVenteBean dBean = list_editable_RetourVente.get(i);
							Incident_det_retour_venteBean  IncidBEan= new Incident_det_retour_venteBean();
							
							 
							dBean.getPk().setR_vente(beanUpdate);
							
							
							IncidBEan.getPk().setCause(dBean.getCause());
							IncidBEan.setSens(dBean.getSens());
							IncidBEan.getPk().setDetv(dBean.getPk().getDetv());
							IncidBEan.getPk().setR_vente(beanUpdate);
							IncidBEan.setQuantite_retourne(dBean.getQuantite_retourne());
							IncidBEan.setObservation(dBean.getObservation());
							IncidBEan.setMontant_ht_retour(dBean.getMontant_ht_retour());
							IncidBEan.setMontant_tva_retour(dBean.getMontant_tva_retour());
						
							
							setBeanTrace(dBean);
							session.save(dBean);
							
							setBeanTrace(IncidBEan);
							session.save(IncidBEan);
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
		  
	
	 public Boolean doConfirmerRetourVente_article(RetourVenteBean beanUp) throws Exception {
		    Session session =  openSessionHibernate(sessionFactory);
			boolean result  = false;
			try {
				   BeanSession bs    =  (BeanSession)getObjectValueModel(BEAN_SESSION);
				   RetourVenteBean beanUpdate=(RetourVenteBean) getObjectValueModel(FORM_BEAN);
				   
				   boolean  sup=ProcessDate.isStrictementSuperieur(beanUpdate.getRet_vente_date(), BDateTime.getDateActuel());
				    if(sup)
				    	throwNewException(" Date retour "+ProcessDate.getStringFormatDate(beanUpdate.getRet_vente_date())+" sup�rieur a date system");
				   
				   List <DetRetourVenteBean> listOfmyData=(List) getObjectValueModel(RetourVenteTemplate.LIST_EDITABLE_RETOUR_VENTE);
				 
				   String chaine="";
				   for(DetRetourVenteBean beanvente:listOfmyData){
					 chaine=chaine+"'"+beanvente.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+"',";
				   }
				   chaine.length();
				   chaine=StringUtils.isEmpty(chaine)?"":chaine.substring(0, chaine.length()-1);
				   if(StringUtils.isEmpty(chaine))throwNewException("List D�taille Vide");
				 
				   List     list_lot_article   = doGetLot_artcicle(beanUpdate,chaine);
				   HashMap  map_retourn        = doGetStock_artcicle(beanUpdate,chaine);
				   
				   HashMap  map_resultat_stock =(HashMap) map_retourn.get("map_resultat");
				   HashMap  map_id_Incident =(HashMap) map_retourn.get("map_id_Incident");
			 
				    
				   //this.saveTraceVersion1(beanUpdate, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
				
				    for (int i = 0; i < listOfmyData.size(); i++) {
				    	DetRetourVenteBean detBean  = (DetRetourVenteBean) listOfmyData.get(i);
						detBean.getPk().setR_vente(beanUpdate);
						
						//detBean.setQuantite_confirmer(detBean.getQuantite());
						//boolean resultTrai_personnaliser=false;//traitement_for_lot_personnaliser(beanUpdate,detBean,session);
						
						HashMap  mapAchat =  traitement_for_lot_generic(beanUpdate,detBean,list_lot_article,session);
						                     traitement_for_stock(map_id_Incident,mapAchat,beanUpdate,detBean,map_resultat_stock,session);
						//traitement_for_Incident_stock(mapAchat,beanUpdate,detBean,map_resultat_stock,session);
						
						 
						session.saveOrUpdate(detBean);
						
					}
					this.setUpdateValueFieldTraceOject(beanUpdate);
					session.saveOrUpdate(beanUpdate);
				 
			      /* if( !StringUtils.isEmpty( beanUpdate.getCommande().getCmd_id()) ){
				     session.createQuery("    UPDATE   CommandeclientBean b  set   b.modeBean.fct_id="+bs.getFct_id()+"  " +
				     		"       where   b.cmd_id='"+beanUpdate.getCommande().getCmd_id()+"'    ").executeUpdate();
			       }*/
			     
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
	 
	 
	 private void traitement_for_Incident_stock(HashMap mapAchat,
			RetourVenteBean beanUpdate, DetRetourVenteBean detBean,
			HashMap map_resultat_stock, Session session) throws Exception {
		try {
			
			
			
		} catch (Exception e) {
		 throw e;
		}
		
	}


	private HashMap traitement_for_lot_generic(RetourVenteBean beanUpdate,DetRetourVenteBean detBean, List list_lot_article, Session session  ) throws Exception {
			boolean  resultat=false;
			HashMap  mapAchat = new HashMap();
			 try {
				  
					 String ref_article_vente    =  detBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre();
					 double mayQte_retour        =  detBean.getQuantite_retourne().doubleValue();
					 String  sens=detBean.getSens().getData_id();
					
					
					
						for (int k = 0; k < list_lot_article.size(); k++) {
							 
							 MouvementSerieBean      mvtSerie      =   (MouvementSerieBean) list_lot_article.get(k);
						     SerieArticletBean  	serieBean      =   mvtSerie.getPk().getSerieBean();
						     
						     String ref_article_lot                =   serieBean.getFkCode_barre().getPk().getCode_barre();
						     if(  !ref_article_vente.equals(ref_article_lot) )  continue;
		                 	 
						     Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(mayQte_retour);
							 Double qteIncident=ProcessNumber.PRODUIT(qte, new Double(1));
							 
						         Incident_mvt_serieBean mvtBean = new Incident_mvt_serieBean();
								 mvtBean.getPk().setSerieBean(serieBean);
								 mvtBean.setDate_mvt_incident(beanUpdate.getRet_vente_date());
								 mvtBean.getPk().setCause(detBean.getCause());
								 mvtBean.setSens(detBean.getSens());
								 mvtBean.getPk().setDocument_com_id(mvtSerie.getPk().getDocument_com_id());
								 mvtBean.getPk().setOrigin_mvt(mvtSerie.getPk().getNat_mvt());
								 mvtBean.getPk().setMvt_incident_id(beanUpdate.getRet_vente_id());
								 mvtBean.getPk().getIncid_mvt().setNature_mvt_id("r-ve");
								 mvtBean.setMontant_tva_incident(detBean.getMontant_tva_retour());
								 mvtBean.setMontant_ht_incident(detBean.getMontant_ht_retour());
								 mvtBean.setQuantite_incident(qteIncident);
								 mvtBean.setTarif_operation_id(detBean.getPk().getDetv().getTarif().getTarif_vente_id());
								 session.save(mvtBean);
 
								 
								 MouvementSerieBean  mvtSeriBean = new MouvementSerieBean();
								 mvtSeriBean.getPk().setSerieBean(serieBean);
								 mvtSeriBean.setDate_mvt_serie(beanUpdate.getRet_vente_date());
								 mvtSeriBean.getPk().setDocument_com_id(beanUpdate.getRet_vente_id());
								 
								 
								 mvtSeriBean.getPk().getNat_mvt().setNature_mvt_id("r-ve");
								 mvtSeriBean.setMontant_tva_operation(detBean.getMontant_tva_retour());
								 mvtSeriBean.setMontant_ht_operation(detBean.getMontant_ht_retour());
								 
								 Double qteRetour=ProcessFormatNbr.FormatDouble_Troischiffre(mayQte_retour);
								 mvtSeriBean.setQuantite_operation(qteRetour);
								 mvtSeriBean.setTarif_operation_id(detBean.getPk().getDetv().getTarif().getTarif_vente_id());
								 
								 mvtSeriBean.setCause(detBean.getCause());
								 mvtSeriBean.setSens(detBean.getSens());
								 
								 session.save(mvtSeriBean);
								 
								 
								 if(sens.equals("ret")){
								 Double res_tot=ProcessNumber.addition(serieBean.getQuantite(), mayQte_retour);
								 serieBean.setQuantite(res_tot);
								 serieBean.getEtat().setData_id("mod");
								 
								 /**********************************************dimunier la vente ****************************************/
								 Double soldeVenteHt=ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getSerie_vente_ht());
								 Double soldeVentetva=ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getSerie_vente_tva());
								 Double New_soldeVenteHt=ProcessNumber.SOUSTRACTION(soldeVenteHt, detBean.getMontant_ht_retour());
								 Double New_soldeVentetva=ProcessNumber.SOUSTRACTION(soldeVentetva, detBean.getMontant_tva_retour());
								 serieBean.setSerie_vente_tva(New_soldeVentetva);
								 serieBean.setSerie_vente_ht(New_soldeVenteHt);
								 
								 /*******************************************nouvelle entre ds le stock ******* ***************************/
								 
							 
								 Double mnt_Ht_ACHat=ProcessNumber.PRODUIT(serieBean.getTarif().getTarif_unit_article(), qteIncident);
								 Double mnt_tva_ACHat=ProcessNumber.Pourcentage(mnt_Ht_ACHat, serieBean.getTarif().getTvaBean().getTva_value());
								 
								 mapAchat.put("mnt_Ht_ACHat"+ref_article_vente, mnt_Ht_ACHat);
								 mapAchat.put("mnt_tva_ACHat"+ref_article_vente, mnt_tva_ACHat);
								 
								 
								 Double soldeACHATHt   =ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getSerie_achat_ht());
								 Double soldeachattva  =ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getSerie_achat_tva());
								 
								 
								 Double New_soldeACHATHt=ProcessNumber.addition(soldeACHATHt, mnt_Ht_ACHat);
								 Double New_soldeachattva=ProcessNumber.addition(soldeachattva, mnt_tva_ACHat);
								 serieBean.setSerie_achat_ht(New_soldeACHATHt);
								 serieBean.setSerie_achat_tva(New_soldeachattva);
								 /****************************************************************************************************/
								 }
								 
								 if(sens.equals("per")){
									
									 if( serieBean.getQuantite().doubleValue()>0)
									 serieBean.getEtat().setData_id("mod");
									 else
									 serieBean.getEtat().setData_id("ter");
									 /**********************************************dimunier la vente ****************************************/
									 Double soldeVenteHt=ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getSerie_vente_ht());
									 Double soldeVentetva=ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getSerie_vente_tva());
									 Double New_soldeVenteHt=ProcessNumber.SOUSTRACTION(soldeVenteHt, detBean.getMontant_ht_retour());
									 Double New_soldeVentetva=ProcessNumber.SOUSTRACTION(soldeVentetva, detBean.getMontant_tva_retour());
									 serieBean.setSerie_vente_tva(New_soldeVentetva);
									 serieBean.setSerie_vente_ht(New_soldeVenteHt);
									 /****************************************************************************************************/
									 }
								 
								 session.update(serieBean); 
								 resultat=true;
								 break;
						      
						 } 
						
						
						
						
					
					 
					
					
					
				 
					 
			 } catch (Exception e) {  
				 resultat=false;
			     throw e;  
			 } 
			return mapAchat;
			
		}

	 
	 
	 private HashMap doGetStock_artcicle(RetourVenteBean beanUpdate, String chaine) throws Exception {
		 
		    HashMap  map_retourn= new HashMap();
			HashMap  map_resultat= new HashMap();
			HashMap  map_id_Incident= new HashMap();
			String elment="";
			 try {
				    BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);	
					Stock_articleBean beanMvtJourStock= new Stock_articleBean();
					String res= "   AND    bean.pk.depot.depot_id="+beanUpdate.getVente().getDepot().getDepot_id()+"     " +
							 "      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
					"      where           beaK.pk.date_stock  <=  '"+ProcessDate.getStringFormatDate(beanUpdate.getRet_vente_date())+"'        " +
					         "       AND   bean.pk.fkCode_barre.pk.code_barre=beaK.pk.fkCode_barre.pk.code_barre     ";
					res+="   AND   beaK.pk.depot.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'"  ; 
					
					res+="           AND   beaK.pk.depot.depot_id=bean.pk.depot.depot_id      )     " ;
					beanMvtJourStock.setCondition_max_date_stock( res ); 
					beanMvtJourStock.setCondition_list_article("    AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")     ");
					 
					
				    List lisStock_max_date_article = daoStock_article.doFindListStock_article(beanMvtJourStock);
				    
					HashMap  map_id_stock= new HashMap();
				    for (int i = 0; i < lisStock_max_date_article.size(); i++) {
							Stock_articleBean sBean= (Stock_articleBean) lisStock_max_date_article.get(i);
							String key_max_jour =
								sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
							    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"�"+
							    sBean.getPk().getDepot().getDepot_id()+"�"+
							    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
							    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
							map_resultat.put(key_max_jour, sBean);
							
							String SSSS=(String) map_id_stock.get(sBean.getStock_article_id());
							if(SSSS==null && !sBean.getStock_article_id().equals("")){
								elment=elment+"'"+sBean.getStock_article_id()+"',";
							}
								
					}
				    if(elment.length()>0){
				    	String chain=elment.substring(0, elment.length()-1);
				    	List <IncidentStock_articleBean> listIncident_Stock_max_article = daoStock_article.doFindListIncident_Stock_article(chain);
				    	for ( IncidentStock_articleBean  bbb:  listIncident_Stock_max_article  ) {
							String key=bbb.getPk().getStock_article_id()+"�"+bbb.getPk().getCause().getNature_incident_id();
							IncidentStock_articleBean  value=(IncidentStock_articleBean) map_id_Incident.get(key);
							if(value==null)map_id_Incident.put(key, bbb);
						}
				    	
				    }
				    
				   
					  
				
			} catch (Exception e) {
				 throw e;
			}
			
			map_retourn.put("map_resultat", map_resultat);
			map_retourn.put("map_id_Incident", map_id_Incident);
			
			return map_retourn;
		}
	 
 
	 @SuppressWarnings("unchecked")
		private boolean traitement_for_stock(    HashMap  map_id_Incident ,HashMap  mapAchat ,RetourVenteBean beanUpdate, DetRetourVenteBean detail_Bean, HashMap map_article_jour ,  Session session ) throws Exception {
		 
			boolean resultat= true;
			String  sens=detail_Bean.getSens().getData_id();
			
			 try {
				     BeanSession bs    =  (BeanSession)getObjectValueModel(BEAN_SESSION);
				     String date_Retvente =  ProcessDate.getStringFormatDate(beanUpdate.getRet_vente_date());// forcer a date system
				     String ref_article_vente = detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre();
					 Stock_articleBean stock             =  new Stock_articleBean();
					
				     String key_trait =""+
				     detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
				     detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+"�"+
					 beanUpdate.getVente().getDepot().getDepot_id()+"�"+
					 detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
					 detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
				   
					 Double mnt_Ht_ACHat   = (Double) mapAchat.get("mnt_Ht_ACHat"+ref_article_vente );
					 Double mnt_tva_ACHat  = (Double) mapAchat.get("mnt_tva_ACHat"+ref_article_vente );
					 if( mnt_Ht_ACHat ==null )mnt_Ht_ACHat= new Double(0);
					 if( mnt_tva_ACHat ==null )mnt_tva_ACHat= new Double(0);
					 
					 if(map_article_jour.get(key_trait)==null) {
						 return false;
						 
					 }else{
						  
						                   stock                 = (Stock_articleBean)map_article_jour.get(key_trait);
							 String  date_stock                  = ProcessDate.getStringFormatDate(stock.getPk().getDate_stock());
							 Double  Vqte_R_vente                = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getQuantite_retourne());
							 Double  Vmnt_ht__Retvente           = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_ht_retour());
							 Double  Vmnt_tva_Retvente           = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_tva_retour());
						 
						 
						 
							 Double  Sqte_Stock                  = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_stock());
							 Double  getSolde_achat_ht         = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_ht());
							 Double  getSolde_achat_tva          	 = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_tva());
							 Double  getSolde_vente_ht           = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_ht());
							 Double  getSolde_vente_tva          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_tva());
						 
					
						 
							 Double sold_stock_jr                = Sqte_Stock;
							 if(sens.equals("ret")){
								   sold_stock_jr                 = ProcessNumber.addition(Sqte_Stock , Vqte_R_vente );
							 }
							 
							if(sold_stock_jr.doubleValue()<0) return false;
						 
						    if(sens.equals("ret")){
							/* Double NewSolde_achat_ht     = ProcessNumber.addition(getSolde_achat_ht   , mnt_Ht_ACHat);
							 Double NewSolde_achat_tva    = ProcessNumber.addition(getSolde_achat_tva , mnt_tva_ACHat);
							 stock.setSolde_achat_ht(  NewSolde_achat_ht );
							 stock.setSolde_achat_tva( NewSolde_achat_tva );*/
						    }
							 Double NewgetSolde_vente_ht     = ProcessNumber.SOUSTRACTION(getSolde_vente_ht   , Vmnt_ht__Retvente);
							 Double NewgetSolde_vente_tva    = ProcessNumber.SOUSTRACTION(getSolde_vente_tva, Vmnt_tva_Retvente);
							 stock.setSolde_vente_ht(NewgetSolde_vente_ht);
							 stock.setSolde_vente_tva(NewgetSolde_vente_tva);
							 stock.setSolde_stock   ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
							 stock.getPk().setFkCode_barre(detail_Bean.getPk().getDetv().getPk().getFkcode_barre());
							 stock.getPk().getDepot().setDepot_id(beanUpdate.getVente().getDepot().getDepot_id());
						     
							 //stock.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
							// stock.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
							 
							 String stock_article_id="";
							 
							 if(date_Retvente.equals(date_stock)){
								 
 								 String key=stock.getStock_article_id()+"�"+detail_Bean.getCause().getNature_incident_id();
								 IncidentStock_articleBean  mp =(IncidentStock_articleBean) map_id_Incident.get(key) ;
								  if( mp==null ){
									 IncidentStock_articleBean incidentBean = new IncidentStock_articleBean();
								     incidentBean.getPk().setStock_article_id(stock.getStock_article_id());
								     incidentBean.getPk().setCause(detail_Bean.getCause());
								     incidentBean.setSens(detail_Bean.getSens());
								     incidentBean.setQuantite_incident(detail_Bean.getQuantite_retourne());
								     incidentBean.setMnt_tva_incident(detail_Bean.getMontant_tva_retour());
								     incidentBean.setMnt_ht_incident(detail_Bean.getMontant_ht_retour());
								     setBeanTrace(incidentBean);
								     session.save(incidentBean);
								     
								  }else{
									 
									  IncidentStock_articleBean incidentBean = new IncidentStock_articleBean();
									  incidentBean.getPk().setStock_article_id(stock.getStock_article_id());
									  incidentBean.getPk().setCause(detail_Bean.getCause());
									  incidentBean.setSens(detail_Bean.getSens());
									  
									  Double NewqteRet=ProcessNumber.addition(detail_Bean.getQuantite_retourne(), mp.getQuantite_incident()) ;
									  Double NewgetMontant_tva_retour=ProcessNumber.addition(detail_Bean.getMontant_tva_retour(), mp.getMnt_tva_incident()) ;
									  Double NewgetMontant_ht_retour=ProcessNumber.addition(detail_Bean.getMontant_ht_retour(), mp.getMnt_ht_incident()) ;
									  
									  
									  incidentBean.setQuantite_incident(NewqteRet);
									  incidentBean.setMnt_tva_incident(NewgetMontant_tva_retour);
									  incidentBean.setMnt_ht_incident(NewgetMontant_ht_retour);
									  setBeanTrace(incidentBean);
									  session.update(incidentBean); 
									 
								  }
								  
								 session.update(stock);
								 
							 }else{
								 stock.getPk().setDate_stock(beanUpdate.getRet_vente_date());
								 stock.setQuantite_recept(null);
								 stock.setQuantite_vendu(null);
								 stock.setMnt_ht_recept(null);
								 stock.setMnt_ht_vente(null);
								 stock.setMnt_tva_recept(null);
								 stock.setMnt_tva_vente(null);
							     daoNumSequentiel.getNumSeqSimple(stock,"stock_article_id",session);
							     stock_article_id=stock.getStock_article_id();
							     IncidentStock_articleBean incidentBean = new IncidentStock_articleBean();
							     incidentBean.getPk().setStock_article_id(stock_article_id);
							     incidentBean.getPk().setCause(detail_Bean.getCause());
							     incidentBean.setSens(detail_Bean.getSens());
							     incidentBean.setQuantite_incident(detail_Bean.getQuantite_retourne());
							     incidentBean.setMnt_tva_incident(detail_Bean.getMontant_tva_retour());
							     incidentBean.setMnt_ht_incident(detail_Bean.getMontant_ht_retour());
							     setBeanTrace(incidentBean);
							     session.save(incidentBean);
								 session.save(stock);
							 }
						 
							 
							 String ligne="";
							 if(sens.equals("ret")){
								 ligne="  ,bean.solde_stock = bean.solde_stock + "+ProcessFormatNbr.FormatDouble_Troischiffre(Vqte_R_vente)+"  ";
							 }
								 
								 
								 String qString=""+
								 "   UPDATE  Stock_articleBean  bean    set   bean.solde_vente_tva = bean.solde_vente_tva - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_Retvente)+"  , " +
								 
								 "                                            bean.solde_vente_ht = bean.solde_vente_ht - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_ht__Retvente)+"    " +
								 "                      "+ligne+"                        " +
								 
							 		"                where   bean.pk.depot.depot_id="+beanUpdate.getVente().getDepot().getDepot_id()+"     "+
							 		//"                 AND    bean.fk_etab_Bean.pk_etab.etab_id='"+bs.getEtab_id()+"'  "+
							 		//"                 AND    bean.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'  "+
							 		"                 AND    bean.pk.fkCode_barre.pk.code_barre='"+detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+"'  "+
							 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id='"+detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'  "+
							 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  "+
							 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  "+
							 		"                 AND    bean.pk.date_stock > '"+date_Retvente+"'  ";
							 		 
							 		  
								  session.createQuery(qString).executeUpdate();
							 
							  
					 }
				 
			} catch (Exception e) {
				 resultat= false;
				 throw e;
			}
			return resultat;
			
		}
	 
	 
	 public   List doGetLot_artcicle(RetourVenteBean beanUpd, String chaine) throws Exception {
		 
			    List list_lot_article = new ArrayList();
			 try {
				 
				   SerieArticletBean  beanSerie= new SerieArticletBean();
				   beanSerie.setCondition_list_article("   AND    bean.pk.document_com_id='"+beanUpd.getVente().getVente_id()+"'   " );
				   list_lot_article=serviceDocumentLot.doFetch_detailleLotfromServer(beanSerie);
				   if(list_lot_article==null ||  list_lot_article.size()==0)
				    throwNewException(" il existe un ou plusieur  article(s) sans Lot ");
				   
				
			} catch (Exception e) {
				throw e;
			}
			return list_lot_article;
		}
	 
				public Boolean doDeleteRetourVente(RetourVenteBean bealete)  throws Exception  {
				    boolean result=false; 
				    Session session =  openSessionHibernate(sessionFactory);
					try {
						RetourVenteBean beanDelete = (RetourVenteBean) getObjectValueModel(FORM_BEAN);
						List  <DetRetourVenteBean>list_editable_RetourVente=(List) getObjectValueModel(RetourVenteTemplate.LIST_EDITABLE_RETOUR_VENTE );
						
						List  <Incident_det_retour_venteBean>list_incident_det_vente_origine=(List) getObjectValueModel( RetourVenteTemplate.LIST_INCIDENT_DET_VENTE_ORIGINE );
						
						for (int i = 0; i < list_editable_RetourVente.size(); i++) {
							DetRetourVenteBean beaSUp=list_editable_RetourVente.get(i);
							session.delete(beaSUp);
						}
						
						for (int i = 0; i < list_incident_det_vente_origine.size(); i++) {
							Incident_det_retour_venteBean beaSUp=list_incident_det_vente_origine.get(i);
							session.delete(beaSUp);
						}

						session.delete(beanDelete);
				        saveTrace(beanDelete);
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
