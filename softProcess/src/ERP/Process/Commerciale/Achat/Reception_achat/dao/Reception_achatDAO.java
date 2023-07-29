package ERP.Process.Commerciale.Achat.Reception_achat.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.Inventaire.dao.InventaireDAO;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.MouvementStock.service.MouvementStockService;
import ERP.Process.Commerciale.Stock.Stock_article.dao.Stock_articleDAO;
import ERP.Process.Commerciale.Stock.Stock_article.model.MouvementStockBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

@Repository
public class Reception_achatDAO extends GenericWeb {
	
	
	private Stock_articleDAO daoStock_article;
	@Autowired
	public void setDaoStock_article(Stock_articleDAO daoStock_article) {
		this.daoStock_article = daoStock_article;
	}
	
	 
	
	private TarificationDAO daoTarification;
	@Autowired
	public void setDaoTarification(TarificationDAO daoTarification) {
		this.daoTarification = daoTarification;
	}
	
	
	private MouvementStockService  serviceMouvementStock;
	@Autowired
	public void setServiceMouvementStock(MouvementStockService serviceMouvementStock) {
	    this.serviceMouvementStock = serviceMouvementStock;
	} 
	private DocumentLotService serviceDocumentLot;
	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
		this.serviceDocumentLot = serviceDocumentLot;
	}
	
	
	private InventaireDAO daoInventaire;
	@Autowired
	public void setDaoInventaire(InventaireDAO daoInventaire) {
		this.daoInventaire = daoInventaire;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	 
	
	@Autowired
	private SessionFactory sessionFactory;
    public void setSessionFactoryTwo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	public List<Reception_achatBean> doFindListReception_achat( Reception_achatBean beanSearch) throws Exception {
		
	Session session =  openSessionHibernate(sessionFactory);
		
		List<Reception_achatBean>   lisf = new ArrayList<Reception_achatBean>();
		try {
			
			
		 
		   String requette = " select  bean   FROM    Reception_achatBean    bean    WHERE     1=1       ";
		    
		if (beanSearch.getAchat_date() != null) 
		    	requette += "   AND  bean.achat_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getAchat_date())+"'        ";
		    
		if (  !StringUtils.isEmpty( beanSearch.getAchat_date2() ) ) 
		    	requette += "   AND  bean.achat_date <= '"+beanSearch.getAchat_date2()+"'        ";
		
		if (!StringUtils.isEmpty(beanSearch.getAchat_id()))
			   requette += "   AND   bean.achat_id = '"+beanSearch.getAchat_id()+"'        ";
		
		if ( !StringUtils.isEmpty(beanSearch.getDem_achat().getDem_achat_id()))
			   requette += "   AND   bean.dem_achat.dem_achat_id = '" +beanSearch.getDem_achat().getDem_achat_id()+"'        ";
		
		
		if (!StringUtils.isEmpty(beanSearch.getAchat_libelle()))
			   requette += "   AND   bean.achat_libelle = '"+beanSearch.getAchat_libelle()+"'        ";
		
	
		
		if (!StringUtils.isEmpty(beanSearch.getFrsBean().getFrs_id()))
			requette += "   AND   bean.frsBean.frs_id = '" + beanSearch.getFrsBean().getFrs_id()+ "'        ";
		
		if (beanSearch.getDepot().getDepot_id() != null)
			requette += "   AND   bean.depot.depot_id = '" + beanSearch.getDepot().getDepot_id()+"'        ";
		
		
		if ( !StringUtils.isEmpty(beanSearch.getCondition_etat_achat()) )
			
			requette += "   " + beanSearch.getCondition_etat_achat()+"        ";
		
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
	
	
	@SuppressWarnings("unchecked")
	public List<Det_reception_achatBean> doFindList_detail_Reception_achat( Reception_achatBean beanSearch) throws Exception {
		
		Session session = openSessionHibernate(sessionFactory);

		List<Det_reception_achatBean> lisf = new ArrayList<Det_reception_achatBean>();
		try {

			String requette = " select  bean   FROM    Det_reception_achatBean    bean    WHERE  1= 1    ";
			
		    if (!StringUtils.isEmpty(beanSearch.getAchat_id()))
				   requette += "   AND   bean.pk.recepBean.achat_id ='"+beanSearch.getAchat_id()+"'        ";
		    
			 if (beanSearch.getAchat_date() != null) 
			    	requette += "   AND  bean.pk.recepBean.achat_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getAchat_date())+"'        ";
			    
			if (beanSearch.getAchat_date2()!= null ) 
			    	requette += "   AND  bean.pk.recepBean.achat_date <=  '"+ProcessDate.getStringFormatDate(beanSearch.getAchat_date())+"'         ";
		    
		  
		    requette +=this.setSocieteEtabFetch(beanSearch,"bean.pk.recepBean.fk_etab_Bean", false);

			lisf = session.createQuery(requette).list();
			commitTransaction(session);
		} catch (Exception e) {
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return lisf;
 
	}

	public Boolean doSaveReception_achat(Reception_achatBean beanSave) throws Exception {
		boolean result = false;
		Session session =   openSessionHibernate(sessionFactory) ;
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			this.setBeanTrace(beanSave);
			List listOfmyData=(List) getObjectValueModel( Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT);
			if (bs.getFct_id().equals(GenericActionBean.Fn_Servir)){
			beanSave.setFrsBean(beanSave.getDem_achat().getFrsBean());
			} 
			
			Reception_achatBean bTotalAchat = (Reception_achatBean) getObjectValueModel(Reception_achatTemplate.BEAN_TOTAL);
			if(bTotalAchat!=null) { 
			beanSave.setAchat_mnt_ht(bTotalAchat.getAchat_mnt_ht());
			beanSave.setAchat_mnt_tva(bTotalAchat.getAchat_mnt_tva());
			beanSave.setAchat_mnt_total(bTotalAchat.getAchat_mnt_total());
			}
			session.persist(beanSave);
			this.saveTrace(beanSave) ; 
			
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_reception_achatBean detBean=(Det_reception_achatBean) listOfmyData.get(i);
				if( detBean.getQuantite()==null) { continue; }
				if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
				detBean.getPk().setRecepBean(beanSave);
				
				if(detBean.isPrix_achat_is_changed()) {
					TarificationPrtvArticleBean  tarifOrigin =   detBean.getTarif() ;
					TarificationPrtvArticleBean  tarifNew = new TarificationPrtvArticleBean();
				 
					HashMap  mapfrs =  (HashMap)getObjectValueModel( Reception_achatTemplate.HASHMAP_FRS);
					FournisseurBean  ben     =  (FournisseurBean) mapfrs.get(beanSave.getFrsBean().getFrs_id());
					GrpTarifPrimitivBean groupe = new GrpTarifPrimitivBean();
					groupe.setGrp_trf_lib(BDateTime.getDateActuel_system()+" "+ProcessDate.getTime(new Date())+" "+ben.getFrs_id()+"/"+ben.getFrsref());
					setBeanTrace(groupe);
					session.save(groupe);
					
					tarifNew.setGroupe(groupe);
					tarifNew.setMode_cal(tarifOrigin.getMode_cal());
					tarifNew.setFkCode_barre(tarifOrigin.getFkCode_barre());
					tarifNew.setDevise(tarifOrigin.getDevise());
					tarifNew.setTvaBean(tarifOrigin.getTvaBean());
					 
					tarifNew.setDate_prim_trf(beanSave.getAchat_date());
					tarifNew.setTarif_unit_article(tarifOrigin.getTarif_unit_article());
					tarifNew.setValeur_tva(ProcessNumber.getMontantTvaByMontantHT(tarifOrigin.getTarif_unit_article(), tarifOrigin.getTvaBean(), new DeviseBean()));
					tarifNew.setTarif_unit_ttc(ProcessNumber.getMontantTTCByMontantHT(tarifOrigin.getTarif_unit_article(), tarifOrigin.getTvaBean(),  new DeviseBean() ));
					 
					daoNumSequentiel.getNumSeqSimple(tarifNew,"tarif_prim_id",session);
					this.setBeanTrace(tarifNew);
					session.save(tarifNew);
					detBean.setTarif(tarifNew);  
				}
				
				session.persist(detBean);
				result_detaille=true;
			}
			if(!result_detaille)throwNewException("err_inser_deaill");
				
			this.saveTraceVersion_Master_detailles(listOfmyData, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			  
			if( !StringUtils.isEmpty( beanSave.getDem_achat().getDem_achat_id()) )
				 session.createQuery(" UPDATE  Demande_achatBean b  set  b.modeBean.fct_id="+bs.getFct_id()+"  " +
				 		"  where   b.dem_achat_id='"+beanSave.getDem_achat().getDem_achat_id()+"' ").executeUpdate();
			commitTransaction(session);
			result = true;
		} catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		
		 }  
		
		return result;
	}
	
	
	public Boolean doCorrigerAchatArticle(Reception_achatBean beanUpdate)  throws Exception { 
	    boolean result=false; 
	    Session session =  openSessionHibernate(sessionFactory);
		try {
			TraitementCorrectionVenteArticle(beanUpdate,session);
			session.createQuery( " UPDATE  Reception_achatBean b  set      b.modeBean.fct_id="+GenericActionBean.Fn_Modifier+"   " +
							"      where   b.achat_id='"+beanUpdate.getAchat_id()+"' ").executeUpdate();
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
	
	private void TraitementCorrectionVenteArticle(  Reception_achatBean beanUp, Session session) throws Exception {
		
		   Reception_achatBean beanUpdate=(Reception_achatBean) getObjectValueModel(ORIGINAL_FORM_BEAN);
		   List <Det_reception_achatBean> listOfmyDataClone=(List) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT_CLONE);
		   if(listOfmyDataClone!=null &&  listOfmyDataClone.size()>0) {
		   traitementCorrectionLotGeneric(beanUpdate,session);
		   traitementCorrectionStockArticleMarchandis(beanUpdate,listOfmyDataClone,session);
		   }
	 
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
	
	private void  traitementCorrectionLotGeneric(Reception_achatBean  beanUpdate , Session session  ) throws Exception {
		 
		 try {
			   List  listmvtLotArticle   = doGetLotArtcicleDejaSauvgarderByVenteId(beanUpdate.getAchat_id());
				for (int k = 0; k < listmvtLotArticle.size(); k++) {
					     MouvementSerieBean  	mvtSeriBean =(MouvementSerieBean) listmvtLotArticle.get(k);
//					     HashMap  mapDevise= (HashMap) getObjectValueModel("map_devise");
//					     DeviseBean devise =(DeviseBean) mapDevise.get( String.valueOf(beanUpdate.getDevise().getDev_id()) );
//				    	 Double Qte= ProcessFormatNbr.FormatDouble_ParameterChiffre(mvtSeriBean.getQuantite_operation(),devise.getChiffre_pattern()) ;
//				    	 SerieArticletBean  serie=mvtSeriBean.getPk().getSerieBean();
//				    	 Double qteserie= ProcessFormatNbr.FormatDouble_ParameterChiffre(serie.getQuantite(),devise.getChiffre_pattern()) ; 
//				    	 Double qtTotal= ProcessNumber.addition(qteserie, Qte);
//				    	 serie.setQuantite(ProcessFormatNbr.FormatDouble_ParameterChiffre(qtTotal, devise.getChiffre_pattern())); 
//				    	 serie.getEtat().setData_id("mod");
				    	 session.delete(mvtSeriBean);
				    	 session.delete(mvtSeriBean.getPk().getSerieBean());
				 } 
				
		 } catch (Exception e) {  
		     throw e;  
		 } 
		 
		 
	}
	
	private void traitementCorrectionStockArticleMarchandis(Reception_achatBean beanUpdate,   List <Det_reception_achatBean> listOfmyDataClone,  Session session ) throws Exception {
		 HashMap  map_article_jour= new HashMap();
		 try {
				Stock_articleBean beanMvtJourStock= new Stock_articleBean();
				String res=  "   AND  bean.pk.depot.depot_id ="+beanUpdate.getDepot().getDepot_id()+"     " +
				             "   AND  bean.pk.date_stock  ='"+ProcessDate.getStringFormatDate(beanUpdate.getAchat_date())+"'   " ;
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
					   
				   
					     Det_reception_achatBean detail_Bean  = (Det_reception_achatBean) listOfmyDataClone.get(i);
						 String date_reception =  ProcessDate.getStringFormatDate(beanUpdate.getAchat_date()); 
					     String keyTrait =""+
					     detail_Bean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
					     detail_Bean.getPk().getFkCode_barre().getPk().getCode_barre()+"�"+
						 beanUpdate.getDepot().getDepot_id()+"�"+
						 detail_Bean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
						 detail_Bean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
					     Double prix_unit_moyen_pond = new Double(0);
					 
						 if(map_article_jour.get(keyTrait)==null) {
						 
							 continue;
						 }else{
							  
							     Stock_articleBean stock          = (Stock_articleBean)map_article_jour.get(keyTrait);
							     prix_unit_moyen_pond             = stock.getCout_unitaire_moyen_pondere()!=null?stock.getCout_unitaire_moyen_pondere(): new Double(0);             
								 Double  qteReception             = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getQuantite());
								 Double  Vmnt_ht__recept          = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_ht_achat());
								 Double  Vmnt_tva_recept          = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_tva_achat());
								  
								 
								 Double  qteStock                 = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_stock());
								 Double  qteEntreStock            = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getQuantite_recept());
								 Double  solde_achat_ht           = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_ht());
								 Double  solde_achat_tva          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_tva());
								 
								 Double  m_recep_ht               = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_ht_recept());
								 Double  m_recep_tva              = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_tva_recept());
								 
								 Double  sold_stock_jr            = ProcessNumber.SOUSTRACTION(qteStock , qteReception );
								 Double  sold_entre_stock         = ProcessNumber.SOUSTRACTION(qteEntreStock , qteReception );
								 
								 Double  newSolde_achat_ht        = ProcessNumber.SOUSTRACTION(solde_achat_ht   , Vmnt_ht__recept);
								 Double  newSolde_achat_tva       = ProcessNumber.SOUSTRACTION(solde_achat_tva, Vmnt_tva_recept);
								 
								 Double  newMnt_achat_ht          = ProcessNumber.SOUSTRACTION(m_recep_ht   , Vmnt_ht__recept);
								 Double  newMnt_achat_tva         = ProcessNumber.SOUSTRACTION(m_recep_tva, Vmnt_tva_recept);
								 
								 
								 stock.setSolde_stock ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
								 stock.setQuantite_recept(ProcessFormatNbr.FormatDouble_Troischiffre(sold_entre_stock));
								 stock.getPk().setFkCode_barre(detail_Bean.getPk().getFkCode_barre());
								 stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
								  
								 stock.setMnt_tva_recept(ProcessFormatNbr.FormatDouble_Troischiffre(newMnt_achat_tva));
								 stock.setMnt_ht_recept (ProcessFormatNbr.FormatDouble_Troischiffre(newMnt_achat_ht));
								 
								 stock.setSolde_achat_tva(ProcessFormatNbr.FormatDouble_Troischiffre(newSolde_achat_tva));
								 stock.setSolde_achat_ht(ProcessFormatNbr.FormatDouble_Troischiffre( newSolde_achat_ht));
								 session.update(stock);
									  
								 String qString=""+
												 "   UPDATE  Stock_articleBean  bean    set   bean.solde_stock = bean.solde_stock - "+ProcessFormatNbr.FormatDouble_Troischiffre(qteReception)+",  " +
												 "                                            bean.solde_achat_tva = bean.solde_achat_tva - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept)+",  " +
												 "                                            bean.solde_achat_ht  = bean.solde_achat_ht - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_ht__recept)+"  " +
											 		"                where   bean.pk.depot.depot_id="+beanUpdate.getDepot().getDepot_id()+"     "+
											 		//"               AND    bean.fk_etab_Bean.pk_etab.etab_id='"+bs.getEtab_id()+"'  "+
											 		//"               AND    bean.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'  "+
											 		"                 AND    bean.pk.fkCode_barre.pk.code_barre='"+detail_Bean.getPk().getFkCode_barre().getPk().getCode_barre()+"'  "+
											 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id='"+detail_Bean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'  "+
											 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+detail_Bean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  "+
											 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+detail_Bean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  "+
											 		"                 AND    bean.pk.date_stock > '"+date_reception+"'  ";
											 		 
											 		 
						        session.createQuery(qString).executeUpdate();

						 }
			 }	    
			    
			
		} catch (Exception e) {
			 throw e;
		}
	}

	public Boolean doUpdateReception_achat(Reception_achatBean beanUpdate) throws Exception {
	 
		
		 
		
		boolean result = false;
		Session session =   openSessionHibernate(sessionFactory) ;
		try {
			
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 List list_recep_clone = (List) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT_CLONE );
			 for (int i = 0; i < list_recep_clone.size(); i++) {
				Det_reception_achatBean det_reception_achatBean =(Det_reception_achatBean) list_recep_clone.get(i);
				session.delete(det_reception_achatBean);
				if(det_reception_achatBean.getMvt_stock()!=null)
					session.delete(det_reception_achatBean.getMvt_stock());
			}
			 
			 session.flush();
			 session.clear();
			 List listOfmyData     = (List) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT);
			 Reception_achatBean  beanReCEP=(Reception_achatBean) getObjectValueModel(FORM_BEAN);
			 boolean result_detaille = false;
				for (int i = 0; i < listOfmyData.size(); i++) {
					Det_reception_achatBean detBean=(Det_reception_achatBean) listOfmyData.get(i);
				    if( detBean.getQuantite()==null) { continue; }
					if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
					detBean.getPk().setRecepBean(beanUpdate);
					detBean.setMvt_stock(null);
					session.save(detBean);
					result_detaille=true;
				}
			  if(!result_detaille)throwNewException("err_inser_deaill");	
			 this.saveTraceVersion_Master_detailles(listOfmyData, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			 this.setUpdateValueFieldTraceOject(beanUpdate);
			 
			 if (beanReCEP.getDem_achat()!=null &&  !beanReCEP.getDem_achat().getDem_achat_id().equals("")){
				 beanUpdate.setDem_achat(beanReCEP.getDem_achat());
				 beanUpdate.setFrsBean(beanUpdate.getDem_achat().getFrsBean());
				 beanUpdate.setDemande_id(beanReCEP.getDem_achat().getDem_achat_id());
			  }
			 session.saveOrUpdate(beanUpdate);
			 this.saveTraceVersion1(beanUpdate, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
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
	private HashMap doGetStock_artcicle(Reception_achatBean beanUpdate, String chaine) throws Exception {
		
		HashMap  map_resultat= new HashMap();
		
		 try {
				Stock_articleBean beanMvtJourStock= new Stock_articleBean();
				String quString= "    " +
				"      AND    bean.pk.depot.depot_id="+beanUpdate.getDepot().getDepot_id()+"  "	+	
				"      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
				"      where  beaK.pk.date_stock  <= '"+ProcessDate.getStringFormatDate(beanUpdate.getAchat_date())+"'        " ;
				//quString+=this.setSocieteEtabFetch(beanUpdate,"beaK.fk_etab_Bean", true);
				
				quString+="      AND    beaK.pk.fkCode_barre.pk.code_barre =bean.pk.fkCode_barre.pk.code_barre    "+
				"                AND    beaK.pk.depot.depot_id=bean.pk.depot.depot_id     )" ;
				 
				
				beanMvtJourStock.setCondition_max_date_stock( quString ); 
				
				
				
				beanMvtJourStock.setCondition_list_article("     AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")     ");
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
	
    public Boolean doValiderReception_article(Reception_achatBean beanUpd) throws Exception {
   	 
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		
		try {
			
			      BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				  Reception_achatBean beanUpdate=(Reception_achatBean) getObjectValueModel(FORM_BEAN);
				  List <Det_reception_achatBean> listOfmyData=(List) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT);
				  String chaine="";
				  for(Det_reception_achatBean beanRecep:listOfmyData){
					 chaine=chaine+"'"+beanRecep.getPk().getFkCode_barre().getPk().getCode_barre()+"',";
				  }
			      chaine=StringUtils.isEmpty(chaine)?"":chaine.substring(0, chaine.length()-1);
			      
			      if(StringUtils.isEmpty(chaine))
			    	  throwNewException("List D�taille Vide");
			 
			  
			      HashMap  map_resultat_stock       = doGetStock_artcicle(beanUpdate,chaine);
			      HashMap  map_resultat  			= doGetLastTarificationVente(beanUpdate,chaine,session);
			      HashMap  map_prixVente  			= (HashMap) map_resultat.get("mapTarif");
			     
			     
				 for (int i = 0; i < listOfmyData.size(); i++) {
					Det_reception_achatBean detBean  = (Det_reception_achatBean) listOfmyData.get(i);
					detBean.getPk().setRecepBean(beanUpdate);
					SerieArticletBean  beanSerie= createSerieArticle(detBean,  session);
					createMvtSerieArticle(beanSerie,detBean,  session);
					traitement_for_TarifVenteSerieBean(beanSerie, map_prixVente, session);
					traitement_for_stock(beanUpdate,detBean,map_resultat_stock,  session);
				  }
					
				 this.setUpdateValueFieldTraceOject(beanUpdate);
				 session.saveOrUpdate(beanUpdate);
				 saveTrace(beanUpdate);
				 
				 session.createQuery( " UPDATE  Reception_achatBean b  set      b.modeBean.fct_id="+GenericActionBean.Fn_Confirmer+"   " +
							"      where   b.achat_id='"+beanUpdate.getAchat_id()+"' ").executeUpdate();
				 
		         if( !StringUtils.isEmpty( beanUpdate.getDem_achat().getDem_achat_id()) )
		        	 session.createQuery( " UPDATE  Demande_achatBean b  set  b.modeBean.fct_id="+GenericActionBean.Fn_Confirmer+"    where  " +
		        	 		"  b.dem_achat_id='"+beanUpdate.getDemande_id()+"' ").executeUpdate();
		     
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
    
    
	private void traitement_for_TarifVenteSerieBean(SerieArticletBean beanSerie,HashMap map_resultat_prixVente,
			Session session) throws Exception {
		try {
			String  cathegorie=beanSerie.getFkCode_barre().getPk().getAr_bean().getCathegorie().getData_id();
			TarificationBean beanSearch  =(TarificationBean) map_resultat_prixVente.get(beanSerie.getFkCode_barre().getPk().getCode_barre());
			
			if( cathegorie.equals("mar")  &&   beanSearch !=null ){
				TarificationLotBean  tLotBean = new TarificationLotBean();
				tLotBean.getPk().setLot(beanSerie);
				tLotBean.getPk().setVente(beanSearch);
				setBeanTrace(tLotBean);
				session.save(tLotBean);
			}
			
		} catch (Exception e) {
		throw e;
		}
		
	}

	private HashMap doGetLastTarificationVente(Reception_achatBean beanUpdate, String chaine,Session session) throws Exception {
		     HashMap mapresul  =  new HashMap();
		try {
			 TarificationBean beanTarif= new  TarificationBean();
			 beanTarif.getGroupe().setType_trf_id(Integer.parseInt(GROUPE_TARIF_VENTE_PUBLIC));
			 beanTarif.setDate_trf(beanUpdate.getAchat_date());
			 mapresul  = daoTarification.doFindListTarificationVenteArticle_DateMax(beanTarif,session);
		} catch (Exception e) {
		 throw e;
		}
		return mapresul;
	}

	private void createMvtSerieArticle(SerieArticletBean beanSerie,Det_reception_achatBean detBean,Session session ) throws Exception {
		 try {
			    MouvementSerieBean  mvtBean = new MouvementSerieBean();
				mvtBean.getPk().setSerieBean(beanSerie);
				mvtBean.setDate_mvt_serie(detBean.getPk().getRecepBean().getAchat_date());
				mvtBean.getPk().setDocument_com_id(detBean.getPk().getRecepBean().getAchat_id());
				mvtBean.getPk().getNat_mvt().setNature_mvt_id("ac");
				mvtBean.setMontant_tva_operation(detBean.getMontant_tva_achat());
				mvtBean.setMontant_ht_operation(detBean.getMontant_ht_achat());
				mvtBean.setQuantite_operation(detBean.getQuantite());
				mvtBean.setTarif_operation_id(detBean.getTarif().getTarif_prim_id());
				setBeanTrace(mvtBean);
				session.persist(mvtBean);
		} catch (Exception e) {
			e.getStackTrace();
			throw e;
		}
		
	}



	private SerieArticletBean  createSerieArticle(Det_reception_achatBean detBean,Session session ) throws Exception {
		      SerieArticletBean  beanSerie=  new SerieArticletBean();
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			daoNumSequentiel.getNumSeqSimple(beanSerie, "pk.num_serie");
			beanSerie.getPk().setDepot( detBean.getPk().getRecepBean().getDepot());
			beanSerie.setMvt_com_id(detBean.getPk().getRecepBean().getAchat_id());
			beanSerie.getReception().setAchat_id(detBean.getPk().getRecepBean().getAchat_id());
			beanSerie.setDate_serie(detBean.getPk().getRecepBean().getAchat_date());
			beanSerie.setFkCode_barre(detBean.getPk().getFkCode_barre());
			beanSerie.getNature_mvt().setNature_mvt_id("ac");
			
			beanSerie.setQuantite(new Double(detBean.getQuantite()));
			beanSerie.setQuantite_init(new Double(detBean.getQuantite()));
			
			//beanSerie.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
			//beanSerie.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
			beanSerie.setTarif(detBean.getTarif());
			beanSerie.setDate_fabrication(detBean.getDate_fabrication());
			beanSerie.setDate_utilisation(detBean.getDate_utilisation());
			beanSerie.setDate_peremption(detBean.getDate_peremption());
			beanSerie.setObservation(detBean.getObservation());
			beanSerie.getEtat().setData_id("cre");
			setBeanTrace(beanSerie);
			session.persist(beanSerie); 
			
		} catch (Exception e) {
			e.getStackTrace();
			throw e;
		}
		return  beanSerie;
		
	}



	private void traitement_for_stock(Reception_achatBean beanUpdate, Det_reception_achatBean detBean, HashMap map_article_jour ,Session session   ) throws Exception {
		
		 try {
			 
			        String date_reception=ProcessDate.getStringFormatDate(beanUpdate.getAchat_date());//forcer a date system ;;;;;;; c pas date achat
				    String key_trait =""+
					detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
					detBean.getPk().getFkCode_barre().getPk().getCode_barre()+"�"+
					beanUpdate.getDepot().getDepot_id()+"�"+
					detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
					detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
				
				     Stock_articleBean stock          = new Stock_articleBean(); 
				    
					  
				      
				     Double  Vqte_reception           = ProcessFormatNbr.FormatDouble_Troischiffre(detBean.getQuantite());
					 Double  Vmnt_ht__recept          = ProcessFormatNbr.FormatDouble_Troischiffre(detBean.getMontant_ht_achat());
					 Double  Vmnt_tva_recept          = ProcessFormatNbr.FormatDouble_Troischiffre(detBean.getMontant_tva_achat());
					 
					 if(map_article_jour.get(key_trait)!=null) { 
					     stock  = (Stock_articleBean) map_article_jour.get(key_trait); 
					 } 
					 
					 Double  Sqte_Stock               = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_stock());
					 Double  SqteSRecep               = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getQuantite_recept());
					 Double  Smnt_ht__recept          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_ht_recept());
					 Double  Smnt_tva_recept          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_tva_recept());
					 
					 Double  Stock_montant_ht_recept  = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_ht());
					 Double  Stock_montant_tva_recept = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_tva());
					 
					 Double sold_stock_jr             = ProcessNumber.addition(Sqte_Stock , Vqte_reception);	      
					 Double tot_qte_recept            = ProcessNumber.addition(SqteSRecep , Vqte_reception);
					 Double tot_mnt_ht__recept        = ProcessNumber.addition(Smnt_ht__recept , Vmnt_ht__recept);
					 Double tot_mnt_tva_recept        = ProcessNumber.addition(Smnt_tva_recept , Vmnt_tva_recept);
					 
					 
					 Double tot_Stock_Mnt_ht_recept   = ProcessNumber.addition(Stock_montant_ht_recept , Vmnt_ht__recept);
					 Double tot_Stock_Mnt_tva_recept  = ProcessNumber.addition(Stock_montant_tva_recept , Vmnt_tva_recept);
					 Double prix_unit_moyen_pond= new Double(0);
					 
					
				    
				     
					 if(map_article_jour.get(key_trait)!=null) { 
					     stock               = (Stock_articleBean) map_article_jour.get(key_trait); 
					     String  date_stock  = ProcessDate.getStringFormatDate(stock.getPk().getDate_stock());
					    
					    
					    
					     if(stock.getCout_unitaire_moyen_pondere()==null) {
					    	  prix_unit_moyen_pond=
					    			  ProcessFormatNbr.FormatDouble_ParameterChiffre(detBean.getTarif().getTarif_unit_ttc(), 
					    					  detBean.getTarif().getDevise().getChiffre_pattern());
					     }else{
					    	 
					    	 Double cout_unitaire_moyen_pondere_ancien=stock.getCout_unitaire_moyen_pondere()!=null ?
						    		 ProcessFormatNbr.FormatDouble_ParameterChiffre(stock.getCout_unitaire_moyen_pondere(), detBean.getTarif().getDevise().getChiffre_pattern()) 
						    		 :  new Double(0);
						    Double qteStock_prix_Pondere_ancien=ProcessNumber.PRODUIT(cout_unitaire_moyen_pondere_ancien, Sqte_Stock);
						    
						    Double prix_nouveau=detBean.getTarif().getTarif_unit_ttc()!=null ?
						    		 ProcessFormatNbr.FormatDouble_ParameterChiffre(detBean.getTarif().getTarif_unit_ttc(), detBean.getTarif().getDevise().getChiffre_pattern()) 
						    		 :  new Double(0);
						    Double qteStock_prix_Pondere_nouveau=ProcessNumber.PRODUIT(prix_nouveau, Vqte_reception);
						    Double sommeDeuxBorn=ProcessNumber.addition(qteStock_prix_Pondere_ancien, qteStock_prix_Pondere_nouveau);
						    Double sommeStockNouVEauEntere=ProcessNumber.addition(Sqte_Stock, Vqte_reception);
						    prix_unit_moyen_pond= ProcessNumber.DIVISION(sommeDeuxBorn, sommeStockNouVEauEntere) ;
						    prix_unit_moyen_pond= ProcessFormatNbr.FormatDouble_ParameterChiffre(prix_unit_moyen_pond, 
						    		detBean.getTarif().getDevise().getChiffre_pattern()); 
					    	
					     }
					     
					     if(date_reception.equals(date_stock)){
					    	 //((200 * 5) + (100 * 8)) / (200 + 100) = 6 euros.
							 stock.getPk().setDate_stock(beanUpdate.getAchat_date());
							 stock.setSolde_stock    ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
							 stock.setQuantite_recept(ProcessFormatNbr.FormatDouble_Troischiffre (tot_qte_recept));
							 stock.setMnt_tva_recept(ProcessFormatNbr.FormatDouble_Troischiffre(tot_mnt_tva_recept));
							 stock.setMnt_ht_recept (ProcessFormatNbr.FormatDouble_Troischiffre( tot_mnt_ht__recept));
							 
							 stock.setSolde_achat_tva(ProcessFormatNbr.FormatDouble_Troischiffre(tot_Stock_Mnt_tva_recept));
							 stock.setSolde_achat_ht(ProcessFormatNbr.FormatDouble_Troischiffre( tot_Stock_Mnt_ht_recept));
							 stock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
							 
							 stock.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
							 stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
							 stock.setDevise(detBean.getTarif().getDevise());
							 //stock.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
							 //stock.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
						     session.saveOrUpdate(stock);
					     }else{
					    	 Stock_articleBean newStock = new  Stock_articleBean();
					    	 daoNumSequentiel.getNumSeqSimple(newStock,"stock_article_id",session);
					    	 newStock.getPk().setDate_stock(beanUpdate.getAchat_date());
					    	 newStock.setSolde_stock    ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
					    	 newStock.setQuantite_recept(ProcessFormatNbr.FormatDouble_Troischiffre (Vqte_reception));
					    	 newStock.setMnt_tva_recept(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept));
					    	 newStock.setMnt_ht_recept (ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__recept));
					    	 newStock.setSolde_achat_tva(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept));
					    	 newStock.setSolde_achat_ht(ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__recept));
					    	 newStock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
					    	 newStock.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
					    	 newStock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
					    	 newStock.setDevise(detBean.getTarif().getDevise());
							 //stock.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
							 //stock.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
						     session.saveOrUpdate(newStock);
					     }
							 
					  }else{
						  
						 Stock_articleBean stockNew = new  Stock_articleBean();
						 daoNumSequentiel.getNumSeqSimple(stockNew,"stock_article_id",session);
						 stockNew.getPk().setDate_stock(beanUpdate.getAchat_date());
						 stockNew.setSolde_stock    ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
						 stockNew.setQuantite_recept(ProcessFormatNbr.FormatDouble_Troischiffre (Vqte_reception));
						 stockNew.setMnt_tva_recept(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept));
						 stockNew.setMnt_ht_recept (ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__recept));
						 stockNew.setSolde_achat_tva(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept));
						 stockNew.setSolde_achat_ht(ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__recept));
						 stockNew.setCout_unitaire_moyen_pondere(ProcessFormatNbr.FormatDouble_ParameterChiffre(detBean.getTarif().getTarif_unit_ttc(), detBean.getTarif().getDevise().getChiffre_pattern()));
						 stockNew.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
						 stockNew.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
						 stockNew.setDevise(detBean.getTarif().getDevise());
					     session.saveOrUpdate(stockNew);
					  }
					  MouvementStockBean  mvtStock        = new MouvementStockBean(); 
				      mvtStock.setDate_stock(beanUpdate.getAchat_date());
				      mvtStock.setFkCode_barre(detBean.getPk().getFkCode_barre());
				      mvtStock.setDocument_com_id(detBean.getPk().getRecepBean().getAchat_id());
				      mvtStock.getNat_mvt().setNature_mvt_id("ac");
				      mvtStock.setQuantite_enter(Vqte_reception);
				      mvtStock.setTarifAchat(detBean.getTarif());
					  mvtStock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
					  mvtStock.setDepot(beanUpdate.getDepot());
					  mvtStock.setDevise(detBean.getTarif().getDevise());
					  mvtStock.setSolde_stock(sold_stock_jr);
					  session.saveOrUpdate(mvtStock);
					  detBean.setCout_unit_moyen_pondere(prix_unit_moyen_pond);
					  detBean.setMvt_stock(mvtStock);
					  session.update(detBean);
				     
				     String qString=""+
					 "   UPDATE  Stock_articleBean  bean    set   bean.solde_stock = bean.solde_stock + "+ProcessFormatNbr.FormatDouble_Troischiffre(Vqte_reception)+",  " +
					 "                                            bean.solde_achat_tva = bean.solde_achat_tva + "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept)+",  " +
					 "                                            bean.solde_achat_ht = bean.solde_achat_ht + "+ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_ht__recept)+"  " +
				 		"                where   bean.pk.depot.depot_id="+beanUpdate.getDepot().getDepot_id()+"     "+
				 		//"                 AND    bean.fk_etab_Bean.pk_etab.etab_id='"+bs.getEtab_id()+"'  "+
				 		//"                 AND    bean.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.code_barre='"+detBean.getPk().getFkCode_barre().getPk().getCode_barre()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id='"+detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  "+
				 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  "+
				 		"                 AND    bean.pk.date_stock > '"+date_reception+"'  ";
				 		 
				 		 
					 session.createQuery(qString).executeUpdate();
				 
			 
		} catch (Exception e) {
			 throw e;
		}
		
	}
     
     
     
     public Boolean doExcuterTransactionForAchat(Reception_achatBean beanUpda) throws Exception {
    		boolean result = false;
    		Session session =  openSessionHibernate(sessionFactory);
    		
    		try {
 			 Reception_achatBean  beanAnnul= (Reception_achatBean) getObjectValueModel(FORM_BEAN);
 			 
 			 this.setUpdateValueFieldTraceOject(beanAnnul);
 			 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
 			
 			 if(bs.getFct_id().equals(GenericActionBean.Fn_Annuler) && bs.getSousmod_id().equals("154")){
 			 beanAnnul.getModeBean().setFct_id(new BigDecimal("3"));
 			 beanAnnul.getModeBean().setFct_libelle("Modifier");
 			}
 			 
 			 session.update(beanAnnul);
 			 this.saveTrace(beanAnnul );
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
     
     public Boolean doTransfererReception_achat(Reception_achatBean beanUpdate) throws Exception {
    		boolean result = false;
    		Session session =  openSessionHibernate(sessionFactory);
    		
    		try {
  			 this.setUpdateValueFieldTraceOject(beanUpdate);
  			 session.saveOrUpdate(beanUpdate);
  			 this.saveTraceVersion1(beanUpdate, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
  			 result = true;
  		} catch (Exception ex) {
  			result = false;
  			session.clear();
  			throw ex;
  		}
  		return result;
  	}
     
     

	public Boolean doDeleteReception_achat(Reception_achatBean beanDelete)
			throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		
		try {
			 List <Det_reception_achatBean>List_det_recep_achat= (List<Det_reception_achatBean>) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT);
			 for (int i = 0; i < List_det_recep_achat.size(); i++) {
					Det_reception_achatBean det_reception_achatBean =(Det_reception_achatBean) List_det_recep_achat.get(i);
					session.delete(det_reception_achatBean);
					if(det_reception_achatBean.getMvt_stock()!=null)
					 session.delete(det_reception_achatBean.getMvt_stock());
				}
			 this.saveTraceVersion_Master_detailles(List_det_recep_achat, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			 session.flush();
			 session.delete(beanDelete);
			 this.saveTrace(beanDelete);
			 if(  beanDelete.getDem_achat()!=null &&     !StringUtils.isEmpty(beanDelete.getDem_achat().getDem_achat_id()))
			 session.createQuery(" UPDATE  Demande_achatBean b  set  b.modeBean.fct_id="+GenericActionBean.Fn_Contremander+"  "
				 		+ "  where   b.dem_achat_id='"+beanDelete.getDem_achat().getDem_achat_id()+"'   ").executeUpdate();
			 
			
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
