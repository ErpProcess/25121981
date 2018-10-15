package ERP.Process.Commerciale.Achat.Reception_achat.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
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
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
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
	
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Autowired
	private SessionFactory sessionFactory;
    public void setSessionFactoryTwo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	public List<Reception_achatBean> doFindListReception_achat( Reception_achatBean beanSearch) throws Exception {
		
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
		
		
		if ( !StringUtils.isBlank(beanSearch.getCondition_etat_achat()) )
			
			requette += "   " + beanSearch.getCondition_etat_achat()+"        ";
		
		    requette +=this.setSocieteEtabFetch(beanSearch,"bean.fk_etab_Bean", false);
		
		 
		return hibernateTemplate.find(requette);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Det_reception_achatBean> doFindList_detail_Reception_achat( Reception_achatBean beanSearch) throws Exception {
		
		   String requette = " select  bean   FROM    Det_reception_achatBean    bean    WHERE    " +
		   		"     bean.pk.recepBean.achat_id='"+beanSearch.getAchat_id()+"' ";
		 
		
		return hibernateTemplate.find(requette);
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
			session.persist(beanSave);
			this.saveTrace(beanSave) ; 
			
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_reception_achatBean detBean=(Det_reception_achatBean) listOfmyData.get(i);
				if( detBean.getQuantite()==null) { continue; }
				if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
				detBean.getPk().setRecepBean(beanSave);
				session.persist(detBean);
				result_detaille=true;
			}
			if(!result_detaille)throwNewException("err_inser_deaill");
				
			this.saveTraceVersion_Master_detailles(listOfmyData, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			  
			if( !StringUtils.isBlank( beanSave.getDem_achat().getDem_achat_id()) )
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

	public Boolean doUpdateReception_achat(Reception_achatBean beanUpdate) throws Exception {
	 
		
		 
		
		boolean result = false;
		try {
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 List list_recep_clone = (List) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT_CLONE );
			 this.hibernateTemplate.deleteAll(list_recep_clone);
			 this.hibernateTemplate.flush();
		     this.hibernateTemplate.clear();
			 List listOfmyData     = (List) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT);
			 Reception_achatBean  beanReCEP=(Reception_achatBean) getObjectValueModel(FORM_BEAN);
			 
			 
			
			 
			 boolean result_detaille = false;
				for (int i = 0; i < listOfmyData.size(); i++) {
					Det_reception_achatBean detBean=(Det_reception_achatBean) listOfmyData.get(i);
				    if( detBean.getQuantite()==null) { continue; }
					if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
					detBean.getPk().setRecepBean(beanUpdate);
					this.hibernateTemplate.save(detBean);
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
			 this.hibernateTemplate.saveOrUpdate(beanUpdate);
			 this.saveTraceVersion1(beanUpdate, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
			 result = true;
		 
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
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
							sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"§"+  
						    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"§"+
						    sBean.getPk().getDepot().getDepot_id()+"§"+
						    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"§"+
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
			    	  throwNewException("List Détaille Vide");
			 
			  
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
		         if( !StringUtils.isBlank( beanUpdate.getDem_achat().getDem_achat_id()) )
		        	 session.createQuery( " UPDATE  Demande_achatBean b  set  b.modeBean.fct_id="+bs.getFct_id()+"   where  " +
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
					detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"§"+  
					detBean.getPk().getFkCode_barre().getPk().getCode_barre()+"§"+
					beanUpdate.getDepot().getDepot_id()+"§"+
					detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"§"+
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
					    	 stock = new  Stock_articleBean();
					    	 daoNumSequentiel.getNumSeqSimple(stock,"stock_article_id",session);
					    	 stock.getPk().setDate_stock(beanUpdate.getAchat_date());
							 stock.setSolde_stock    ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
							 stock.setQuantite_recept(ProcessFormatNbr.FormatDouble_Troischiffre (Vqte_reception));
							 stock.setMnt_tva_recept(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept));
							 stock.setMnt_ht_recept (ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__recept));
							 stock.setSolde_achat_tva(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept));
							 stock.setSolde_achat_ht(ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__recept));
							 stock.setCout_unitaire_moyen_pondere(prix_unit_moyen_pond);
							 stock.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
							 stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
							 stock.setDevise(detBean.getTarif().getDevise());
							 //stock.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
							 //stock.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
						     session.saveOrUpdate(stock);
					     }
							 
					  }else{
						  
						 stock = new  Stock_articleBean();
						 daoNumSequentiel.getNumSeqSimple(stock,"stock_article_id",session);
						 stock.getPk().setDate_stock(beanUpdate.getAchat_date());
						 stock.setSolde_stock    ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
						 stock.setQuantite_recept(ProcessFormatNbr.FormatDouble_Troischiffre (Vqte_reception));
						 stock.setMnt_tva_recept(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept));
						 stock.setMnt_ht_recept (ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__recept));
						 stock.setSolde_achat_tva(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_recept));
						 stock.setSolde_achat_ht(ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__recept));
						 stock.setCout_unitaire_moyen_pondere(ProcessFormatNbr.FormatDouble_ParameterChiffre(detBean.getTarif().getTarif_unit_ttc(), detBean.getTarif().getDevise().getChiffre_pattern()));
						  
						 stock.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
						 stock.getPk().getDepot().setDepot_id(beanUpdate.getDepot().getDepot_id());
						 stock.setDevise(detBean.getTarif().getDevise());
						 //stock.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
						 //stock.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
					     session.saveOrUpdate(stock);
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
     public Boolean doValiderReception_achat(Reception_achatBean beanUpd) throws Exception {
    	 
		boolean result = false;
		/*
		try {
			
			 BeanSession bs =(BeanSession)getObjectValueModel(SESSION_BEAN);
			 Reception_achatBean beanUpdate=(Reception_achatBean) getObjectValueModel(FORM_BEAN);
			 List <Det_reception_achatBean> listOfmyData=(List) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT);
			 String chaine="";
			 
			 HashMap  mapArticleLot = new HashMap();
			 for(Det_reception_achatBean beanRecep:listOfmyData){
				 chaine=chaine+"'"+beanRecep.getPk().getFkCode_barre().getPk().getCode_barre()+"',";
				 
				 String KEY=beanRecep.getPk().getFkCode_barre().getPk().getCode_barre()+"§"+"";//beanRecep.getPk().gets
				 if( mapArticleLot.get(KEY)==null){
				     mapArticleLot.put(KEY, beanRecep);
				 }
				 
			 }
			 chaine=StringUtils.isEmpty(chaine)?"":chaine.substring(0, chaine.length()-1);
			 if(StringUtils.isEmpty(chaine))throwNewException("List Détaille Vide");
			
			
			 InventaireBean beanSearch= new InventaireBean();
		     String condition_Select_dateMax_Inventaire="   "+
		     "      AND    bean.pk.inventaire.pk.depot_stocks.depot_id="+beanUpdate.getPk().getDepot().getDepot_id()+""+
		     "      AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")   "+
		     
		     "      AND    bean.pk.inventaire.pk.inv_date in ( select  max( bhk.pk.inventaire.pk.inv_date ) from  DetInventaireBean bhk    " +
		     "      where  bhk.pk.inventaire.pk.inv_date<='"+ProcessDate.getStringFormatDate(beanUpdate.getPk().getAchat_date())+"'        " +
		     "      AND    bhk.pk.inventaire.pk.depot_stocks.depot_id="+beanUpdate.getPk().getDepot().getDepot_id()+"      )               ";
		     beanSearch.setCondition_Select_dateMax_Inventaire(condition_Select_dateMax_Inventaire);
		    
		       
		     List<DetInventaireBean> list_det_invenTaire=daoInventaire.doFindList_det_Inventaire(beanSearch); 
		    
		     HashMap  mapArticleLotInven = new HashMap();
		     if(list_det_invenTaire!=null &&  list_det_invenTaire.size()>0){
		    	 for (int i = 0; i < list_det_invenTaire.size(); i++) {
		    		 DetInventaireBean detInven=list_det_invenTaire.get(i);
		    		 
		    		 
		    		 if( detInven.getPk().getInventaire().getModeBean().getFct_id()!= null  &&
		    				 String.valueOf(detInven.getPk().getInventaire().getModeBean().getFct_id()).equals(GenericActionBean.Fn_Valider)){
		    			 
			    		 String KEY2=detInven.getPk().getFkCode_barre().getPk().getCode_barre()+"$"+detInven.getPk().getLot().getNum_lot();
			    		 if( mapArticleLotInven.get(KEY2)==null){
			    			 mapArticleLotInven.put(KEY2, "existe");
			    			
			    		 }
		    		 }
		    		 
				 }
		    	 String erruer="";
		    	 Set s_mapArticleLot= mapArticleLot.keySet();
		    	 for (Iterator iter = s_mapArticleLot.iterator(); iter.hasNext();) {
					  String object = (String) iter.next();
						 if( mapArticleLotInven.get(object)==null){
							 Det_reception_achatBean  beaREcp= (Det_reception_achatBean) mapArticleLot.get(object);
							  erruer=erruer+""+beaREcp.getPk().getFkCode_barre().getDesignation_libelle()+"<br>";
						 }
					
				}
		    	 if(  mapArticleLotInven.size()==0) throwNewException(" Manque Inventaire ");
		    	 if(erruer.length()>0  )  throwNewException(" Manque Inventaire pour ces articles :<br>"+erruer);
			 
		       }else{
			      throwNewException(" Manque Inventaire ");
		         } 
		     
		     
		     
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.saveOrUpdate(beanUpdate);
			this.saveTraceVersion1(beanUpdate, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
			 
			 
			
			Stock_articleBean beanSBean= new Stock_articleBean();
			beanSBean.getPk().setDate_stock(beanUpdate.getPk().getAchat_date());
			beanSBean.setDate_stock2(beanUpdate.getPk().getAchat_date());
			
			beanSBean.setCondition_list_article("   AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")     ");
			List lisStock_article = daoStock_article.doFindListStock_article(beanSBean);
			
			HashMap  map_article_jour_courant=  new HashMap();
			
			for (int i = 0; i < lisStock_article.size(); i++) {
				Stock_articleBean sBean= (Stock_articleBean) lisStock_article.get(i);
				String key =
					ProcessDate.getStringFormatDate(sBean.getPk().getDate_stock())+"$"+
					sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"$"+  
				    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"$"+
				    sBean.getPk().getSerie().getNum_serie()+"$"+
				    sBean.getPk().getDepot().getDepot_id()+"$"+
				    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"$"+
				    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
				map_article_jour_courant.put(key, sBean);
			}
			
			
			HashMap  map_article_jour_uterieur=  new HashMap();
			Stock_articleBean beanMvtJour_avant= new Stock_articleBean();
			if(map_article_jour_courant==null ||  map_article_jour_courant.size()==0){
				
				beanMvtJour_avant.setCondition_max_date_stock(  "    " +
				 "      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
			     "      where  beaK.pk.date_stock <  '"+ProcessDate.getStringFormatDate(beanUpdate.getPk().getAchat_date())+"'        " +
			     "      AND    beaK.pk.depot.depot_id="+beanUpdate.getPk().getDepot().getDepot_id()+"      )               "); 
				beanMvtJour_avant.setCondition_list_article("     AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")     ");
				
				List lisStock_max_date_article = daoStock_article.doFindListStock_article(beanMvtJour_avant);
				for (int i = 0; i < lisStock_max_date_article.size(); i++) {
					Stock_articleBean sBean= (Stock_articleBean) lisStock_max_date_article.get(i);
					String key_max_jour =
					 
						sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"$"+  
					    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"$"+
					    sBean.getPk().getSerie().getNum_serie()+"$"+
					    sBean.getPk().getDepot().getDepot_id()+"$"+
					    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"$"+
					    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
					    map_article_jour_uterieur.put(key_max_jour, sBean);
				}
				
			}
			
			
			
			
			 
			 
			    
			 
				for (int i = 0; i < listOfmyData.size(); i++) {
					
					Det_reception_achatBean detBean  = (Det_reception_achatBean) listOfmyData.get(i);
					
					detBean.getPk().setRecepBean(beanUpdate);
					detBean.getPk().getNature_mvt().setNature_mvt_id("ac");
					
					SerieArticletBean  beanSerie=  new SerieArticletBean();
					
					beanSerie.setNum_serie("");
					beanSerie.setMvt_com_id(beanUpdate.getPk().getAchat_id());
					beanSerie.setDate_mvt(beanUpdate.getPk().getAchat_date());
					beanSerie.setFkCode_barre(detBean.getPk().getFkCode_barre());
					beanSerie.getNature_mvt().setNature_mvt_id("ac");
					beanSerie.setMontant_tva_achat(detBean.getPk().getMontant_tva_achat());
					beanSerie.setMontant_tva_achat(detBean.getPk().getMontant_ht_achat());
					beanSerie.setQuantite(new Double(detBean.getPk().getQuantite()));
					beanSerie.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
					beanSerie.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
					beanSerie.setTvaBean(detBean.getTvaBean());
					beanSerie.setUnitBean(detBean.getUnitBean());
					beanSerie.setDate_fabrication(detBean.getDate_fabrication());
					beanSerie.setDate_utilisation(detBean.getDate_utilisation());
					beanSerie.setDate_peremption(detBean.getDate_peremption());
					beanSerie.setTyp_trfBean(detBean.getTyp_trfBean());
					beanSerie.setDate_trf(detBean.getDate_trf());
					beanSerie.setPrix_unit_achat(detBean.getPrix_unit_achat());
					beanSerie.setPrix_unit_vente(detBean.getPrix_unit_vente());
					beanSerie.setDepot(beanUpdate.getPk().getDepot());
					beanSerie.setObservation(detBean.getObservation());
					beanSerie.setEtat_serie("cree");
					this.hibernateTemplate.save(beanSerie);
					
					
					
					MouvementSerieBean  mvtBean = new MouvementSerieBean();
					mvtBean.getDepot().setDepot_id(beanSerie.getDepot().getDepot_id());
					mvtBean.getPk().setDate_mvt_stock(beanUpdate.getPk().getAchat_date());
					mvtBean.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
					mvtBean.getPk().setDocument_com_id(beanUpdate.getPk().getAchat_id());
					mvtBean.getNat_mvtcom().setNature_mvt_id("ac");
					mvtBean.setMontant_tva(detBean.getPk().getMontant_tva_achat());
					mvtBean.setMontant_ht(detBean.getPk().getMontant_ht_achat());
					 
					mvtBean.getPk().setSerieBean(beanSerie);
					mvtBean.getEta().getPk_etab().setEtab_id(bs.getEtab_id());
					mvtBean.getEta().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
					mvtBean.setQuantite_enter(detBean.getPk().getQuantite());
					mvtBean.setQuantite_restante(detBean.getPk().getQuantite());
					mvtBean.setPrix_unit_achat(detBean.getPrix_unit_achat());
					mvtBean.setPrix_unit_vente(detBean.getPrix_unit_vente());
					beanSerie.setTyp_trfBean(detBean.getTyp_trfBean());
					beanSerie.setDate_trf(detBean.getDate_trf());
					
					
					 this.hibernateTemplate.save(mvtBean);
					 
					 
					 Stock_articleBean    stock = new Stock_articleBean();
					 stock.setSolde_stock( ProcessFormatNbr.FormatDouble_Troischiffre(detBean.getPk().getQuantite()) );
					 stock.getPk().setDate_stock(beanUpdate.getPk().getAchat_date());
					 stock.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
					 stock.getPk().getDepot().setDepot_id(beanUpdate.getPk().getDepot().getDepot_id());
					 stock.getPk().setSerie(beanSerie);
					 stock.setQuantite_entri(detBean.getPk().getQuantite());
					 stock.setDate_trf(detBean.getDate_trf());
					 stock.getTyp_trfBean().setType_trf_id(detBean.getTyp_trfBean().getType_trf_id());
					 stock.setTvaBean(detBean.getTvaBean());
					 stock.setPrix_unit_achat(detBean.getPrix_unit_achat());
					 stock.setPrix_unit_vente(detBean.getPrix_unit_vente());
					 stock.setMnt_tva_entre(detBean.getPk().getMontant_tva_achat());
					 stock.setMnt_ht_entre(detBean.getPk().getMontant_ht_achat());
					 stock.getEta().getPk_etab().setEtab_id(bs.getEtab_id());
					 stock.getEta().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
					 this.hibernateTemplate.save(stock);	 
					
					
					
				 
				}
		       if( !StringUtils.isBlank( beanUpdate.getDemande_id()) )
				this.hibernateTemplate.bulkUpdate(" UPDATE  Demande_achatBean b  set  b.modeBean.fct_id="+bs.getFct_id()+"   where   b.dem_achat_id='"+beanUpdate.getDemande_id()+"' ");
		     
			 result = true;
		 
			 
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}*/
		return result;
	}
     
     
     public Boolean doExcuterTransactionForAchat(Reception_achatBean beanUpda) throws Exception {
 		boolean result = false;
 		try {
 			 Reception_achatBean  beanAnnul= (Reception_achatBean) getObjectValueModel(FORM_BEAN);
 			 
 			 this.setUpdateValueFieldTraceOject(beanAnnul);
 			 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
 			
 			 if(bs.getFct_id().equals(GenericActionBean.Fn_Annuler) && bs.getSousmod_id().equals("154")){
 			 beanAnnul.getModeBean().setFct_id(new BigDecimal("3"));
 			 beanAnnul.getModeBean().setFct_libelle("Modifier");
 			}
 			 
 			 this.hibernateTemplate.update(beanAnnul);
 			 this.saveTrace(beanAnnul );
 			 result = true;
 		} catch (Exception ex) {
 			result = false;
 			this.hibernateTemplate.clear();
 			throw ex;
 		}
 		return result;
 	}
     
     public Boolean doTransfererReception_achat(Reception_achatBean beanUpdate) throws Exception {
  		boolean result = false;
  		try {
  			 this.setUpdateValueFieldTraceOject(beanUpdate);
  			 this.hibernateTemplate.saveOrUpdate(beanUpdate);
  			 this.saveTraceVersion1(beanUpdate, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
  			 result = true;
  		} catch (Exception ex) {
  			result = false;
  			this.hibernateTemplate.clear();
  			throw ex;
  		}
  		return result;
  	}
     
     

	public Boolean doDeleteReception_achat(Reception_achatBean beanDelete)
			throws Exception {
		boolean result = false;
		try {
			
		 
			         
			 List <Det_reception_achatBean>List_det_recep_achat= (List<Det_reception_achatBean>) getObjectValueModel(Reception_achatTemplate.LIST_EDITABLE_RECEP_ACHAT);
			 hibernateTemplate.deleteAll(List_det_recep_achat);
			 this.saveTraceVersion_Master_detailles(List_det_recep_achat, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			 hibernateTemplate.flush();
			 hibernateTemplate.delete(beanDelete);
			 this.saveTrace(beanDelete);
			 if(  beanDelete.getDem_achat()!=null &&     !StringUtils.isEmpty(beanDelete.getDem_achat().getDem_achat_id()))
					this.hibernateTemplate.bulkUpdate(" UPDATE  Demande_achatBean b  set  b.modeBean.fct_id="+GenericActionBean.Fn_Contremander+"   where   b.dem_achat_id='"+beanDelete.getDem_achat().getDem_achat_id()+"'   ");
			 
			
			result = true;
			
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
	}
}
