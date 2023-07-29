package ERP.Process.Commerciale.Achat.ComplementAchat.dao;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.Process.Commerciale.Achat.ComplementAchat.model.ComplementAchatBean;
import ERP.Process.Commerciale.Achat.ComplementAchat.model.Det_complment_achatBean;
import ERP.Process.Commerciale.Achat.ComplementAchat.template.ComplementAchatTemplate;
 
 
 
import ERP.Process.Commerciale.Stock.Inventaire.dao.InventaireDAO;
import ERP.Process.Commerciale.Stock.Inventaire.model.DetInventaireBean;
import ERP.Process.Commerciale.Stock.Inventaire.model.InventaireBean;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.Stock_article.dao.Stock_articleDAO;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
@Repository
public class ComplementAchatDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
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
	public List<ComplementAchatBean> doFindListComplementAchat(ComplementAchatBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    ComplementAchatBean    bean    WHERE     1=1       ";
		    
		    
		    
		 if( !StringUtils.isEmpty(beanSearch.getComplet_id()) )  
			    requette+="   AND   bean.complet_id = '"+beanSearch.getComplet_id()+"'        ";
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getAchat().getAchat_id()) )  
			    requette+="   AND   bean.achat.achat_id = '"+beanSearch.getAchat().getAchat_id()+"'        "; 
		 
		 
		 
		 if( !ProcessUtil.isEmpty(beanSearch.getComplet_date()) )  
			    requette+="   AND   bean.complet_date = '"+beanSearch.getComplet_date()+"'        ";   
		 
		 
		 
		 
		if (beanSearch.getComplet_date() != null) 
		    	requette += "   AND  bean.complet_date >=    '"+ProcessDate.getStringFormatDate(beanSearch.getComplet_date())+"'        ";
		    
		if (   beanSearch.getComplet_date2() != null  ) 
		    	requette += "   AND  bean.complet_date <= '"+ProcessDate.getStringFormatDate(beanSearch.getComplet_date2())+"'        ";
		
		
		
	 
		
		
		if ( !StringUtils.isEmpty(beanSearch.getAchat().getDem_achat().getDem_achat_id()))
			   requette += "   AND   bean.achat.dem_achat.dem_achat_id = '" +beanSearch.getAchat().getDem_achat().getDem_achat_id()+"'        ";
		
		
		if (!StringUtils.isEmpty(beanSearch.getAchat().getAchat_libelle()))
			   requette += "   AND   bean.achat.achat_libelle = '"+beanSearch.getAchat().getAchat_libelle()+"'        ";
		
	
		
		if (!StringUtils.isEmpty(beanSearch.getAchat().getFrsBean().getFrs_id()))
			requette += "   AND   bean.achat.frsBean.frs_id = '" + beanSearch.getAchat().getFrsBean().getFrs_id()+ "'        ";
		
		
		
		if (beanSearch.getAchat().getDepot().getDepot_id() != null)
			requette += "   AND   bean.achat.depot.depot_id = '" + beanSearch.getAchat().getDepot().getDepot_id()+"'        ";
		 
		  
	    
		 
		 
		 if(    !StringUtils.isEmpty(beanSearch.getCondition_etat_complement()) )  
			 
			    requette+=" "+beanSearch.getCondition_etat_complement()+"        ";
	
		 
		 
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveComplementAchat(ComplementAchatBean   beanSave) throws Exception {
	     boolean result=false; 
		try {
			//this.setBeanTrace(beanSave);
			//this.hibernateTemplate.save(beanSave);
			//this.saveTrace(beanSave);
			//result=true;
			
			
			
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			this.setBeanTrace(beanSave);
			List listOfmyData=(List) getObjectValueModel( ComplementAchatTemplate.LIST_EDITABLE_COMP_ACHAT);
			
			this.hibernateTemplate.save(beanSave);
		
			//this.saveTraceVersion1(beanSave, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_complment_achatBean detBean=(Det_complment_achatBean) listOfmyData.get(i);
				if(detBean.getQuantite_ajouter()!=null  &&  detBean.getQuantite_ajouter().doubleValue()>0){
				detBean.getPk().setComplement(beanSave);
				detBean.getNature_mvt().setNature_mvt_id("c-ac");
				 
				this.hibernateTemplate.save(detBean);
				result_detaille = true;
				}
			}
			//if(!result_detaille)throwNewException("err_inser_deaill");
			
			
			//this.hibernateTemplate.bulkUpdate(" UPDATE  Reception_achatBean b  set  b.retour_id="+beanSave.getRetour_id()+"   where   b.pk.achat_id='"+beanSave.getAchat().getPk().getAchat_id()+"' ");
			//this.saveTraceVersion_Master_detailles(listOfmyData, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			 
			//if( !StringUtils.isEmpty( beanSave.getDem_achat().getDem_achat_id()) )
			//
			
			result = true;
			
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	 
	
	public Boolean doUpdateDataInserver(ComplementAchatBean   beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			
			 List list_compList_clone = (List) getObjectValueModel(ComplementAchatTemplate.LIST_EDITABLE_COMP_ACHAT_ORG );
			 this.hibernateTemplate.deleteAll(list_compList_clone);
			 this.hibernateTemplate.flush();
		     //this.hibernateTemplate.clear();
			 List listOfmyData     = (List) getObjectValueModel(ComplementAchatTemplate.LIST_EDITABLE_COMP_ACHAT);
			 
			 boolean result_detaille = false;
				for (int i = 0; i < listOfmyData.size(); i++) {
					Det_complment_achatBean detBean=(Det_complment_achatBean) listOfmyData.get(i);
				    if( detBean.getQuantite_ajouter()==null) { continue; }
					if( detBean.getQuantite_ajouter()==0 ||  detBean.getQuantite_ajouter()<0) { continue;}
					detBean.getPk().setComplement(beanUpdate);
					detBean.getNature_mvt().setNature_mvt_id("c-ac");
					this.hibernateTemplate.save(detBean);
					result_detaille=true;
				}
			  if(!result_detaille)throwNewException("err_inser_deaill");
			 
			setIdBean((ComplementAchatBean) getObjectValueModel(FORM_BEAN), beanUpdate, ComplementAchatTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.saveOrUpdate(beanUpdate);
			//this.saveTrace(beanUpdate);
			 
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	
	public Boolean doDeleteDataFromServer(ComplementAchatBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
			
			ComplementAchatBean   rowBean = (ComplementAchatBean) getObjectValueModel(FORM_BEAN);
			List List_detail=(List) getObjectValueModel(ComplementAchatTemplate.LIST_EDITABLE_COMP_ACHAT    );
			hibernateTemplate.deleteAll(List_detail);
			hibernateTemplate.delete(rowBean);
	        //this.saveTrace(beanDelete);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	
	
 
	
	public Boolean doExcuteemethode_achat()  throws Exception  {
	    boolean result=false; 
		try {
			ComplementAchatBean   beanUpdate=(ComplementAchatBean) getObjectValueModel(FORM_BEAN);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
 			
 			if(bs.getFct_id().equals(GenericActionBean.Fn_Annuler) && bs.getSousmod_id().equals(ComplementAchatTemplate.ID_SOUS_MODULE_STOCK)){
 				beanUpdate.getModeBean().setFct_id(new BigDecimal("3"));
 				beanUpdate.getModeBean().setFct_libelle("Modifier");
 			}
 			
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result=true;
			
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	
	public Boolean doConservData()  throws Exception  {
		boolean result = false;
		/*
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(SESSION_BEAN);
			ComplementAchatBean   beanUpdate=(ComplementAchatBean) getObjectValueModel(FORM_BEAN);
			
			
			
			 List <Det_complment_achatBean> listOfmyData=(List) getObjectValueModel(ComplementAchatTemplate.LIST_EDITABLE_COMP_ACHAT);
			 String chaine="";
			 HashMap  mapArticleLot = new HashMap();
			 for(Det_complment_achatBean beanRetour:listOfmyData){
				 chaine=chaine+"'"+beanRetour.getPk().getFkCode_barre().getPk().getCode_barre()+"',";
				 String KEY=beanRetour.getPk().getFkCode_barre().getPk().getCode_barre()+"$"+beanRetour.getLot().getNum_lot();
				 if( mapArticleLot.get(KEY)==null){
				     mapArticleLot.put(KEY, beanRetour);
				 }
			 }
			 chaine=StringUtils.isEmpty(chaine)?"":chaine.substring(0, chaine.length()-1);
			 if(StringUtils.isEmpty(chaine))throwNewException("list detaille vide");
			 
			 
			 HashMap  mapArticleLotInven = new HashMap();
			 InventaireBean beanSearch= new InventaireBean();
		     String condition_Select_dateMax_Inventaire="   "+
		     "      AND    bean.pk.inventaire.pk.depot_stocks.depot_id="+beanUpdate.getAchat().getPk().getDepot().getDepot_id()+""+
		     "      AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")   "+
		     
		     "      AND    bean.pk.inventaire.pk.inv_date in ( select  max( bhk.pk.inventaire.pk.inv_date ) from  DetInventaireBean bhk    " +
		     "      where  bhk.pk.inventaire.pk.inv_date<='"+ProcessDate.getStringFormatDate(beanUpdate.getComplet_date())+"'        " +
	 
		     "      AND    bhk.pk.inventaire.pk.depot_stocks.depot_id="+beanUpdate.getAchat().getPk().getDepot().getDepot_id()+"      )               ";
		     
		       beanSearch.setCondition_Select_dateMax_Inventaire(condition_Select_dateMax_Inventaire);
		     
		       
		     List<DetInventaireBean> list_det_invenTaire=daoInventaire.doFindList_det_Inventaire(beanSearch); 
		     HashMap  mapInven= new HashMap();
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
							 Det_complment_achatBean  beatour= (Det_complment_achatBean) mapArticleLot.get(object);
							  erruer=erruer+""+beatour.getPk().getFkCode_barre().getDesignation_libelle()+"<br>";
						 }
					
				}
		    	 if(  mapArticleLotInven.size()==0) throwNewException(" Manque Inventaire ");
		    	 if(erruer.length()>0  )  throwNewException(" Manque Inventaire pour ces articles :<br>"+erruer);
			 
		       }else{
			      throwNewException(" Manque Inventaire ");
		         } 
			 
		 
			 
			 
			 
			 
			 
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.saveOrUpdate(beanUpdate);
			
			
			 
			 
			 
			Stock_articleBean beanSBean= new Stock_articleBean();
			beanSBean.getPk().setDate_stock(beanUpdate.getComplet_date());
			beanSBean.setDate_stock2(beanUpdate.getComplet_date());
		 
			beanSBean.setCondition_list_article("   AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")     ");
			List lisStock_article = daoStock_article.doFindListStock_article(beanSBean);
			HashMap  mapArticle_jour_courant =  new HashMap();
			for (int i = 0; i < lisStock_article.size(); i++) {
				Stock_articleBean sBean= (Stock_articleBean) lisStock_article.get(i);
				String key =
					ProcessDate.getStringFormatDate(sBean.getPk().getDate_stock())+"$"+
					sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"$"+  
				    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"$"+
				    sBean.getPk().getLot().getNum_lot()+"$"+
				    sBean.getPk().getDepot().getDepot_id()+"$"+
				    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"$"+
				    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
				mapArticle_jour_courant.put(key, sBean);
			}
			 
			
			Stock_articleBean beanMvtJour_avant= new Stock_articleBean();
			beanMvtJour_avant.setCondition_max_date_stock(  "    " +
			 "      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
		     "      where  beaK.pk.date_stock <  '"+ProcessDate.getStringFormatDate(beanUpdate.getComplet_date())+"'        " +
		     "      AND    beaK.pk.depot.depot_id="+beanUpdate.getAchat().getPk().getDepot().getDepot_id()+"      )               "); 
			beanMvtJour_avant.setCondition_list_article("     AND    bean.pk.fkCode_barre.pk.code_barre in ( "+  chaine + ")     ");
			List lisStock_max_date_article = daoStock_article.doFindListStock_article(beanMvtJour_avant);
			HashMap  mapArticle_jour_uterieur=  new HashMap();
			for (int i = 0; i < lisStock_max_date_article.size(); i++) {
				Stock_articleBean sBean= (Stock_articleBean) lisStock_max_date_article.get(i);
				String key_max_jour =
					 
					sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"$"+  
				    sBean.getPk().getFkCode_barre().getPk().getCode_barre()+"$"+
				    sBean.getPk().getLot().getNum_lot()+"$"+
				    sBean.getPk().getDepot().getDepot_id()+"$"+
				    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"$"+
				    sBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
				
				mapArticle_jour_uterieur.put(key_max_jour, sBean);
			}
			 
			for (int i = 0; i < listOfmyData.size(); i++) {
				
				Det_complment_achatBean   detBean=(Det_complment_achatBean) listOfmyData.get(i);
				
				detBean.getPk().setComplement(beanUpdate);
				detBean.getNature_mvt().setNature_mvt_id("c-ac");
				
				MouvementSerieBean  mStockBean = new MouvementSerieBean();
				mStockBean.getDepot().setDepot_id(beanUpdate.getAchat().getPk().getDepot().getDepot_id());
				mStockBean.getPk().setDate_mvt_stock(beanUpdate.getComplet_date());
				mStockBean.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
				mStockBean.getPk().setDocument_com_id(beanUpdate.getComplet_id());
				mStockBean.getNat_mvtcom().setNature_mvt_id("c-ac");
				mStockBean.setMontant_tva(detBean.getMontant_tva_achat());
				mStockBean.setMontant_ht(detBean.getMontant_ht_achat());
				mStockBean.setQuantite(new Double(detBean.getQuantite_ajouter()));
				mStockBean.getPk().setLot(detBean.getLot());
				mStockBean.getEta().getPk_etab().setEtab_id(bs.getEtab_id());
				mStockBean.getEta().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
				this.hibernateTemplate.save(mStockBean);
				String date_courant_du_retour=ProcessDate.getStringFormatDate(beanUpdate.getComplet_date())+"$";
				
				String keyretour =
				 
					detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"$"+  
					detBean.getPk().getFkCode_barre().getPk().getCode_barre()+"$"+
					detBean.getLot().getNum_lot()+"$"+
					beanUpdate.getAchat().getPk().getDepot().getDepot_id()+"$"+
					detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"$"+
					detBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
				
				
				String key_jour_courant =date_courant_du_retour+keyretour;
				String key_max_jour_mvt =keyretour;
				
				 if(mapArticle_jour_courant.get(key_jour_courant)==null) {
					 
					 
					 Stock_articleBean    stock = new Stock_articleBean();
					 
					 Stock_articleBean beanUlter= (Stock_articleBean) mapArticle_jour_uterieur.get(key_max_jour_mvt);
					 Double setQuantite_Soltd=ProcessNumber.SOMME(beanUlter.getSolde_stock(), detBean.getQuantite_ajouter());
					 stock.setSolde_stock( ProcessFormatNbr.FormatDouble_Troischiffre(setQuantite_Soltd) );
					  
					 
					 stock.getPk().setDate_stock(beanUpdate.getComplet_date());
					 stock.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
					 stock.getPk().getDepot().setDepot_id(beanUpdate.getAchat().getPk().getDepot().getDepot_id());
					 stock.getPk().setLot(detBean.getLot());
					 
					 stock.setQuantite_entri(detBean.getQuantite_ajouter());
					 
					 stock.setDate_trf(detBean.getDate_trf());
					 stock.getTyp_trfBean().setType_trf_id(detBean.getTyp_trfBean().getType_trf_id());
					 stock.setTvaBean(detBean.getTvaBean());
					 stock.setPrix_unit_achat(detBean.getPrix_unit_achat());
					 stock.setPrix_unit_vente(detBean.getPrix_unit_vente());
					 stock.setMnt_tva_entre(detBean.getMontant_tva_achat());
					 stock.setMnt_ht_entre(detBean.getMontant_ht_achat());
					 
					 
					 stock.getEta().getPk_etab().setEtab_id(bs.getEtab_id());
					 stock.getEta().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
					 	
					 this.hibernateTemplate.save(stock);	 
					 
				 }else{
					 
					 
						 
					 Stock_articleBean sBean= (Stock_articleBean) mapArticle_jour_courant.get(key_max_jour_mvt);
					 
					 
					 
					 Double setQuantite_ajouter=ProcessNumber.SOMME(sBean.getQuantite_entri(), detBean.getQuantite_ajouter());
					 sBean.setQuantite_entri(  ProcessFormatNbr.FormatDouble_Troischiffre(setQuantite_ajouter) );
					 
					 Double setQuantite_sold=ProcessNumber.SOMME(sBean.getSolde_stock(), detBean.getQuantite_ajouter());
					 sBean.setSolde_stock( ProcessFormatNbr.FormatDouble_Troischiffre(setQuantite_sold) );
					 
				 
					 Double setMnt_tva_entre=ProcessNumber.SOMME(sBean.getMnt_tva_entre(), detBean.getMontant_tva_achat());
					 sBean.setMnt_tva_entre( ProcessFormatNbr.FormatDouble_Troischiffre(setMnt_tva_entre) );
					 
					 Double setMnt_ht_entre=ProcessNumber.SOMME(sBean.getMnt_ht_entre(), detBean.getMontant_ht_achat());
					 sBean.setMnt_ht_entre( ProcessFormatNbr.FormatDouble_Troischiffre(setMnt_ht_entre) );
					 
					 sBean.getEta().getPk_etab().setEtab_id(bs.getEtab_id());
					 sBean.getEta().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
					 this.hibernateTemplate.update(sBean);	 
				 }
			 
			}
			 
			 
			     
			 
				 
		    
		     
			 result = true;
			 
			 
			 
			 
			 
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}*/
		return result;
	}
	
}
