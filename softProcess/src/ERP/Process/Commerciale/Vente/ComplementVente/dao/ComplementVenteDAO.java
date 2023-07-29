package ERP.Process.Commerciale.Vente.ComplementVente.dao;
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
import ERP.Process.Commerciale.Vente.ComplementVente.model.ComplementVenteBean;
import ERP.Process.Commerciale.Vente.ComplementVente.model.DetComplementBean;
import ERP.Process.Commerciale.Vente.ComplementVente.template.ComplementVenteTemplate;
import ERP.Process.Commerciale.Vente.RetourVente.model.DetRetourVenteBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
 
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
@Repository
public class ComplementVenteDAO extends  GenericWeb    {
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
	public List<ComplementVenteBean> doFindListComplementVente(ComplementVenteBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		  try{
		    String requette=" select  bean   FROM    ComplementVenteBean    bean    WHERE     1=1       ";
			 if( !StringUtils.isEmpty(beanSearch.getComp_vente_id()) )  
				    requette+="   AND   bean.comp_vente_id = '"+beanSearch.getComp_vente_id()+"'        ";    
			 if( !StringUtils.isEmpty(beanSearch.getVente().getVente_id()) )  
				    requette+="   AND   bean.vente.vente_id = '"+beanSearch.getVente().getVente_id()+"'        ";    
			 if(  beanSearch.getComp_vente_date() !=null )  
	    requette+="   AND   bean.comp_vente_date =  "+beanSearch.getComp_vente_date()+"         ";    
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
	public List<DetComplementBean  > doFindList_detaille_ComplementVente(ComplementVenteBean   beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<DetComplementBean>   lisf = new ArrayList<DetComplementBean>();
		try{
		    String requette=" select  bean   FROM    DetComplementBean    bean    WHERE   " +
		    		"   bean.pk.c_vente.comp_vente_id='"+beanSearch.getComp_vente_id()+"'       ";
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
			public List<DetComplementBean> doFindList_detaille_RetourVente(ComplementVenteBean beanSearch) throws Exception {
				Session session =  openSessionHibernate(sessionFactory);
				List<DetComplementBean>   lisf = new ArrayList<DetComplementBean>();
				try{
				    String requette=" select  bean   FROM    DetComplementBean    bean    WHERE   " +
				    		"   bean.pk.c_vente.comp_vente_id='"+beanSearch.getComp_vente_id()+"'       ";
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
			public List<DetComplementBean> doFindList_detaille_plusieur_complement(String lesVente ) throws Exception {
				Session session =  openSessionHibernate(sessionFactory);
				List<DetComplementBean>   lisf = new ArrayList<DetComplementBean>();
				try{
				    String requette=" select  bean   FROM    DetComplementBean    bean    WHERE   " +
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
			
			
			public Boolean doSaveComplmentVente(ComplementVenteBean beanSave) throws Exception {
			     boolean result=false; 
			     Session session =  openSessionHibernate(sessionFactory);
				try {
					daoNumSequentiel.getNumSeqSimple(beanSave,"comp_vente_id",session);
					setBeanTrace(beanSave);
					session.save(beanSave);
					List 	<DetComplementBean>list_editable_RetourVente = (List) getObjectValueModel( ComplementVenteTemplate.LIST_EDITABLE_COMPLEMNT_VENTE  );
					for (int i = 0; i < list_editable_RetourVente.size(); i++) {
						DetComplementBean dBean = list_editable_RetourVente.get(i);
						dBean.getPk().setC_vente(beanSave);
						setBeanTrace(dBean);
						session.save(dBean);
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
			
			
			
			 public Boolean doConfirmerComplementVente (ComplementVenteBean beanUp) throws Exception {
				    Session session =  openSessionHibernate(sessionFactory);
					boolean result  = false;
					try {
						   BeanSession bs    =  (BeanSession)getObjectValueModel(BEAN_SESSION);
						   ComplementVenteBean beanUpdate=(ComplementVenteBean) getObjectValueModel(FORM_BEAN);
						   
						   boolean  sup=ProcessDate.isStrictementSuperieur(beanUpdate.getComp_vente_date() , BDateTime.getDateActuel());
						    if(sup)
						    	throwNewException(" Date vente "+ProcessDate.getStringFormatDate(beanUpdate.getComp_vente_date())+"Sup a date system");
						   
						   List <DetComplementBean> listOfmyData=(List) getObjectValueModel(ComplementVenteTemplate.LIST_EDITABLE_COMPLEMNT_VENTE);
						 
						   String chaine="";
						   for(DetComplementBean beanvente:listOfmyData){
							 chaine=chaine+"'"+beanvente.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+"',";
						   }
						   chaine.length();
						   chaine=StringUtils.isEmpty(chaine)?"":chaine.substring(0, chaine.length()-1);
						   if(StringUtils.isEmpty(chaine))throwNewException("List D�taille Vide");
						 
						   List     list_lot_article   = doGetLot_artcicle(beanUpdate,chaine);
						   HashMap  map_resultat_stock = doGetStock_artcicle(beanUpdate,chaine);
						    
						   //this.saveTraceVersion1(beanUpdate, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
						
						    for (int i = 0; i < listOfmyData.size(); i++) {
						    	DetComplementBean detBean  = (DetComplementBean) listOfmyData.get(i);
								detBean.getPk().setC_vente(beanUpdate);
 								
								boolean resultTrai_personnaliser=false;//traitement_for_lot_personnaliser(beanUpdate,detBean,session);
								
								if(!resultTrai_personnaliser) {
								boolean resultTrai_generic=traitement_for_lot_generic(beanUpdate,detBean,list_lot_article,session);
								if(!resultTrai_generic) 
									throwNewException("Pas de Lot Pour l'article:"+detBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+" / "+
											detBean.getPk().getDetv().getPk().getFkcode_barre().getDesignation_libelle());
								}
								boolean resul=traitement_for_stock(beanUpdate,detBean,map_resultat_stock,session);
								
								if(!resul){
									throwNewException("Stock zero _ article:"+detBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+" / "+
											detBean.getPk().getDetv().getPk().getFkcode_barre().getDesignation_libelle());
									 
								}
								session.saveOrUpdate(detBean);
								
							}
							this.setUpdateValueFieldTraceOject(beanUpdate);
							session.saveOrUpdate(beanUpdate);
						 
					        
					     
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
			 
			private HashMap doGetStock_artcicle(ComplementVenteBean beanUpdate, String chaine) throws Exception {
			 
				HashMap  map_resultat= new HashMap();
				 try {
						Stock_articleBean beanMvtJourStock= new Stock_articleBean();
					 
						
						 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
						
						String res= "   AND    bean.pk.depot.depot_id="+beanUpdate.getVente().getDepot().getDepot_id()+"     " +
						 "      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
				"      where           beaK.pk.date_stock  <=  '"+ProcessDate.getStringFormatDate(beanUpdate.getComp_vente_date())+"'        " +
				         "       AND   bean.pk.fkCode_barre.pk.code_barre=beaK.pk.fkCode_barre.pk.code_barre     ";
				res+="   AND   beaK.pk.depot.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'"  ; 
				
				res+="           AND   beaK.pk.depot.depot_id=bean.pk.depot.depot_id      )     " ;
				beanMvtJourStock.setCondition_max_date_stock( res ); 
				beanMvtJourStock.setCondition_list_article("    AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")     ");
				
						
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

			public   List doGetLot_artcicle(ComplementVenteBean beanUpd, String chaine) throws Exception {
			 
				List list_lot_article = new ArrayList();
				 try {
					 
					 SerieArticletBean  beanSerie= new SerieArticletBean();
					 beanSerie.setCondition_list_article("   AND   bean.date_utilisation  <= '"+ProcessDate.getStringFormatDate(beanUpd.getComp_vente_date())+"'    " +
							                             "   AND   bean.quantite  > 0    " +
							 					         "   AND   ( bean.date_peremption  is null   OR    bean.date_peremption  > '"+ProcessDate.getStringFormatDate(beanUpd.getComp_vente_date())+"' )   " +
					 		"                                AND   bean.fkCode_barre.pk.code_barre in ( "+  chaine + ")    " );
					 
					 
					   beanSerie.getPk().setDepot(beanUpd.getVente().getDepot());
					   list_lot_article=serviceDocumentLot.doFetchDatafromServer(beanSerie);
					   
					  if(list_lot_article==null ||  list_lot_article.size()==0)
						   throwNewException(" il existe un ou plusieur  article(s) sans Lot ");
					   
					
				} catch (Exception e) {
					throw e;
				}
				return list_lot_article;
			}

			@SuppressWarnings("unchecked")
			private boolean traitement_for_stock(ComplementVenteBean beanUpdate, DetComplementBean detail_Bean, HashMap map_article_jour ,  Session session ) throws Exception {
			 
				boolean resultat= true;
				 
				
				 try {
					    BeanSession bs    =  (BeanSession)getObjectValueModel(BEAN_SESSION);
					    String date_vente =  ProcessDate.getStringFormatDate(beanUpdate.getComp_vente_date());// forcer a date system
						
					    String key_trait =""+
					    detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+  
					    detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+"�"+
						beanUpdate.getVente().getDepot().getDepot_id()+"�"+
						detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
						detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
					   
						 if(map_article_jour.get(key_trait)==null) {
							 
							 return false;
							
							 
						 }else{
							  
							 Stock_articleBean stock         = (Stock_articleBean)map_article_jour.get(key_trait);
							 String  date_stock              = ProcessDate.getStringFormatDate(stock.getPk().getDate_stock());
							 
							 Double  Vqte_vente              = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getQuantite_ajoute());
							 
							 Double  Vmnt_ht__vente          = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_ht_complement());
							 Double  Vmnt_tva_vente          = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_tva_complement());
							 
							 
							 Double  Sqte_Stock              = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_stock());
							 Double  SqteSvendu              = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getQuantite_vendu());
							 
							 Double  Smnt_ht__vente          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_ht_vente());
							 Double  Smnt_tva_vente          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getMnt_tva_vente());
							 
							 Double sold_stock_jr            = ProcessNumber.SOUSTRACTION(Sqte_Stock , Vqte_vente);
							 
							 if(sold_stock_jr.doubleValue()<0){
							    return false;
								    
							 } 
							 
							 if(date_vente.equals(date_stock)){
								 
								 stock.getPk().setDate_stock( ProcessDate.convert_String_to_Date(date_vente));
								 Double tot_qte_vendu            = ProcessNumber.addition(SqteSvendu , Vqte_vente);
								 Double tot_mnt_ht__vente        = ProcessNumber.addition(Smnt_ht__vente , Vmnt_ht__vente);
								 Double tot_mnt_tva_vente        = ProcessNumber.addition(Smnt_tva_vente , Vmnt_tva_vente);
								 stock.setSolde_stock   ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
								 stock.setQuantite_vendu(ProcessFormatNbr.FormatDouble_Troischiffre (tot_qte_vendu));
								 stock.setMnt_tva_vente(ProcessFormatNbr.FormatDouble_Troischiffre(tot_mnt_tva_vente));
								 stock.setMnt_ht_vente (ProcessFormatNbr.FormatDouble_Troischiffre( tot_mnt_ht__vente));
								 stock.getPk().setFkCode_barre(detail_Bean.getPk().getDetv().getPk().getFkcode_barre());
								 stock.getPk().getDepot().setDepot_id(beanUpdate.getVente().getDepot().getDepot_id());
								 //stock.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
								// stock.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
								 session.update(stock);
								
								 
							 }else{
								 stock = new Stock_articleBean();
								 stock.getPk().setDate_stock( ProcessDate.convert_String_to_Date(date_vente));
								 stock.setSolde_stock   ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
								 stock.setQuantite_vendu(ProcessFormatNbr.FormatDouble_Troischiffre (Vqte_vente));
								 stock.setMnt_tva_vente(ProcessFormatNbr.FormatDouble_Troischiffre(Vmnt_tva_vente));
								 stock.setMnt_ht_vente (ProcessFormatNbr.FormatDouble_Troischiffre( Vmnt_ht__vente));
								 stock.getPk().setFkCode_barre(detail_Bean.getPk().getDetv().getPk().getFkcode_barre());
								 stock.getPk().getDepot().setDepot_id(beanUpdate.getVente().getDepot().getDepot_id());
								// stock.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
								 //stock.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
								 session.saveOrUpdate(stock);
							 }
							 
							 String qString=""+
							 "   UPDATE  Stock_articleBean  bean    set   bean.solde_stock = bean.solde_stock - "+ProcessFormatNbr.FormatDouble_Troischiffre(Vqte_vente)+"  " +
						 		"                where   bean.pk.depot.depot_id="+beanUpdate.getVente().getDepot().getDepot_id()+"     "+
						 		//"                 AND    bean.fk_etab_Bean.pk_etab.etab_id='"+bs.getEtab_id()+"'  "+
						 		//"                 AND    bean.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'  "+
						 		"                 AND    bean.pk.fkCode_barre.pk.code_barre='"+detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+"'  "+
						 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id='"+detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'  "+
						 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  "+
						 		"                 AND    bean.pk.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+detail_Bean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  "+
						 		"                 AND    bean.pk.date_stock > '"+date_vente+"'  ";
						 		 
						 		 
							 session.createQuery(qString).executeUpdate();
						 
						 }
					 
				} catch (Exception e) {
					 resultat= false;
					 throw e;
				}
				return resultat;
				
			}

			private boolean traitement_for_lot_generic(ComplementVenteBean beanUpdate,DetComplementBean detBean, List list_lot_article, Session session  ) throws Exception {
				boolean  resultat=false;
				 try {
						String ref_article_vente    =  detBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre();
						double mayQ=detBean.getQuantite_ajoute().doubleValue();
						double qteInitTot= 0; 
						for (int k = 0; k < list_lot_article.size(); k++) {
						     SerieArticletBean  	serieBean=(SerieArticletBean) list_lot_article.get(k);
						     String ref_article_lot=serieBean.getFkCode_barre().getPk().getCode_barre();
						     if(  !ref_article_vente.equals(ref_article_lot) )continue;
						     qteInitTot=qteInitTot+serieBean.getQuantite().doubleValue();
						}
						if(qteInitTot<mayQ)
							return false;
						
						
						for (int k = 0; k < list_lot_article.size(); k++) {
							
						     SerieArticletBean  	 serieBean=(SerieArticletBean) list_lot_article.get(k);
						     String ref_article_lot  =serieBean.getFkCode_barre().getPk().getCode_barre();
						     if(  !ref_article_vente.equals(ref_article_lot) )continue;
						     
						     if(serieBean.getQuantite().doubleValue()>mayQ){
						    	
						    	 MouvementSerieBean  mvtBean = new MouvementSerieBean();
								 mvtBean.getPk().setSerieBean(serieBean);
								 mvtBean.setDate_mvt_serie(beanUpdate.getComp_vente_date());
								 mvtBean.getPk().setDocument_com_id(beanUpdate.getComp_vente_id());
								 mvtBean.getPk().getNat_mvt().setNature_mvt_id("c-ve");
								 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_complement());
								 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_complement());
								 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(mayQ);
								 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
								 mvtBean.setQuantite_operation(qteRes);
								 mvtBean.setTarif_operation_id(detBean.getPk().getDetv().getTarif().getTarif_vente_id());
								 session.save(mvtBean);
								 Double res_diff=ProcessNumber.SOUSTRACTION(serieBean.getQuantite(), mayQ);
								 serieBean.setQuantite(res_diff);
								 
								 if(res_diff==0)
									 serieBean.getEtat().setData_id("ter");
								 if(res_diff>0)
									 serieBean.getEtat().setData_id("mod");
								 
								 session.update(serieBean);
								 resultat=true;
								 break;
						     }
						     
		                  if(serieBean.getQuantite().doubleValue()<mayQ){
		                 	  
						    	 MouvementSerieBean  mvtBean = new MouvementSerieBean();
								 mvtBean.getPk().setSerieBean(serieBean);
								 mvtBean.setDate_mvt_serie(beanUpdate.getComp_vente_date());
								 mvtBean.getPk().setDocument_com_id(beanUpdate.getComp_vente_id());
								 mvtBean.getPk().getNat_mvt().setNature_mvt_id("c-ve");
								 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_complement());
								 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_complement());
								 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(serieBean.getQuantite());
								 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
								 mvtBean.setQuantite_operation(qteRes);
								 mvtBean.setTarif_operation_id(detBean.getPk().getDetv().getTarif().getTarif_vente_id());
								 Double resto=ProcessNumber.SOUSTRACTION(mayQ,  serieBean.getQuantite());
								 mayQ=resto;
								 session.save(mvtBean);
								 serieBean.setQuantite(new Double(0));
								 serieBean.getEtat().setData_id("ter");
								 session.update(serieBean);
								 resultat=true;
								 continue;
						    	 
						     }
		                  if(serieBean.getQuantite().doubleValue()==mayQ){
		                 	 
		                 	     MouvementSerieBean  mvtBean = new MouvementSerieBean();
								 mvtBean.getPk().setSerieBean(serieBean);
								 mvtBean.setDate_mvt_serie(beanUpdate.getComp_vente_date());
								 mvtBean.getPk().setDocument_com_id(beanUpdate.getComp_vente_id());
								 mvtBean.getPk().getNat_mvt().setNature_mvt_id("c-ve");
								 mvtBean.setMontant_tva_operation(detBean.getMontant_tva_complement());
								 mvtBean.setMontant_ht_operation(detBean.getMontant_ht_complement());
								 
								 Double qte=ProcessFormatNbr.FormatDouble_Troischiffre(mayQ);
								 Double qteRes=ProcessNumber.PRODUIT(qte, new Double(1));
								 mvtBean.setQuantite_operation(qteRes);
								 mvtBean.setTarif_operation_id(detBean.getPk().getDetv().getTarif().getTarif_vente_id());
								 session.save(mvtBean);
								 
								 
							     serieBean.getEtat().setData_id("ter");
								 
								 
								 serieBean.setQuantite(new Double(0));
								 session.update(serieBean);
								  
								 resultat=true;
								 break;
						     }

						 } 
					 
						 
				 } catch (Exception e) {  
					 resultat=false;
				     throw e;  
				 } 
				return resultat;
				
			}
			
			
			
			public Boolean doUpdateComplementVente(ComplementVenteBean beanUpdate)  throws Exception { 
			    boolean result=false; 
			    Session session =  openSessionHibernate(sessionFactory);
				try {
				 
					this.setUpdateValueFieldTraceOject(beanUpdate);
					session.update(beanUpdate);
					this.saveTrace(beanUpdate);
					 
				 
					 
					BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
				    if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier) ||  bs.getFct_id().equals(GenericActionBean.Fn_Confirmer)  ){
						 this.setUpdateValueFieldTraceOject(beanUpdate);
						 session.update(beanUpdate);
						 this.saveTrace(beanUpdate);
					 }
							
							List  <DetComplementBean>list_editable_RetourVente=(List) getObjectValueModel(ComplementVenteTemplate.LIST_EDITABLE_COMPLEMNT_VENTE );
							List  <DetComplementBean>list_editable_RetourVente_Origine=(List) getObjectValueModel( ComplementVenteTemplate.LIST_EDITABLE_COMPLEMNT_VENTE_ORIGINE );
							
							
					 
							
							if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier)){
								
								for (int i = 0; i < list_editable_RetourVente_Origine.size(); i++) {
									DetComplementBean beaSUp=list_editable_RetourVente_Origine.get(i);
									session.delete(beaSUp);
								}
								 
								 
								session.flush();
								
								for (int i = 0; i < list_editable_RetourVente.size(); i++) {
									DetComplementBean dBean = list_editable_RetourVente.get(i);
									dBean.getPk().setC_vente(beanUpdate);
									setBeanTrace(dBean);
									session.save(dBean);
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
		 
	 
		 @SuppressWarnings("unchecked")
			private boolean traitement_for_stock(    HashMap  map_id_Incident ,HashMap  mapAchat ,ComplementVenteBean beanUpdate, DetComplementBean detail_Bean, HashMap map_article_jour ,  Session session ) throws Exception {
			 
				boolean resultat= true;
				String  sens=null;//detail_Bean.getSens().getData_id();
				
				 try {
					     BeanSession bs    =  (BeanSession)getObjectValueModel(BEAN_SESSION);
					     String date_Retvente =  ProcessDate.getStringFormatDate(beanUpdate.getComp_vente_date());// forcer a date system
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
								 Double  Vqte_R_vente                = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getQuantite_ajoute());
								 Double  Vmnt_ht__Retvente           = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_ht_complement());
								 Double  Vmnt_tva_Retvente           = ProcessFormatNbr.FormatDouble_Troischiffre(detail_Bean.getMontant_tva_complement());
							 
							 
							 
								 Double  Sqte_Stock                  = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_stock());
								 Double  getSolde_achat_ht           = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_ht());
								 Double  getSolde_achat_tva          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_achat_tva());
								 Double  getSolde_vente_ht           = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_ht());
								 Double  getSolde_vente_tva          = ProcessFormatNbr.FormatDouble_Troischiffre(stock.getSolde_vente_tva());
							 
						
							 
								 Double sold_stock_jr                = Sqte_Stock;
								 if(sens.equals("ret")){
									   sold_stock_jr                 = ProcessNumber.addition(Sqte_Stock , Vqte_R_vente );
								 }
								 
								if(sold_stock_jr.doubleValue()<0) return false;
							 
							    if(sens.equals("ret")){
								 Double NewSolde_achat_ht     = ProcessNumber.addition(getSolde_achat_ht   , mnt_Ht_ACHat);
								 Double NewSolde_achat_tva    = ProcessNumber.addition(getSolde_achat_tva , mnt_tva_ACHat);
								 stock.setSolde_achat_ht(  NewSolde_achat_ht );
								 stock.setSolde_achat_tva( NewSolde_achat_tva );
								 }
								 Double NewgetSolde_vente_ht     = ProcessNumber.SOUSTRACTION(getSolde_vente_ht   , Vmnt_ht__Retvente);
								 Double NewgetSolde_vente_tva    = ProcessNumber.SOUSTRACTION(getSolde_vente_tva, Vmnt_tva_Retvente);
								 stock.setSolde_vente_ht(NewgetSolde_vente_ht);
								 stock.setSolde_vente_tva(NewgetSolde_vente_tva);
								 stock.setSolde_stock   ( ProcessFormatNbr.FormatDouble_Troischiffre(sold_stock_jr) );
								 stock.getPk().setFkCode_barre(detail_Bean.getPk().getDetv().getPk().getFkcode_barre());
								 stock.getPk().getDepot().setDepot_id(beanUpdate.getVente().getDepot().getDepot_id());
								// stock.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
								// stock.getFk_etab_Bean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
								 
								 String stock_article_id="";
								 
								 if(date_Retvente.equals(date_stock)){
									 
	 								 //String key=stock.getStock_article_id()+"�"+detail_Bean.getCause().getNature_incident_id();
									 IncidentStock_articleBean  mp =(IncidentStock_articleBean) map_id_Incident.get("") ;
									  
									  
									 session.update(stock);
									 
								 }else{
									 stock.getPk().setDate_stock(beanUpdate.getComp_vente_date());
									 stock.setQuantite_recept(null);
									 stock.setQuantite_vendu(null);
									 stock.setMnt_ht_recept(null);
									 stock.setMnt_ht_vente(null);
									 stock.setMnt_tva_recept(null);
									 stock.setMnt_tva_vente(null);
								     daoNumSequentiel.getNumSeqSimple(stock,"stock_article_id",session);
								     stock_article_id=stock.getStock_article_id();
								     
									 session.save(stock);
								 }
							 
								 if(sens.equals("ret")){
									 
									 String qString=""+
									 "   UPDATE  Stock_articleBean  bean    set   bean.solde_stock = bean.solde_stock + "+ProcessFormatNbr.FormatDouble_Troischiffre(Vqte_R_vente)+"  " +
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
						 }
					 
				} catch (Exception e) {
					 resultat= false;
					 throw e;
				}
				return resultat;
				
			}
		 
		 
		  
		 
					public Boolean doDeleteComplmentVente(ComplementVenteBean bealete)  throws Exception  {
					    boolean result=false; 
					    Session session =  openSessionHibernate(sessionFactory);
						try {
							ComplementVenteBean beanDelete = (ComplementVenteBean) getObjectValueModel(FORM_BEAN);
							List  <DetComplementBean>list_editable_RetourVente=(List) getObjectValueModel(ComplementVenteTemplate.LIST_EDITABLE_COMPLEMNT_VENTE );
							
							
							for (int i = 0; i < list_editable_RetourVente.size(); i++) {
								DetComplementBean beaSUp=list_editable_RetourVente.get(i);
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
