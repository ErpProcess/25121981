package ERP.Process.Commerciale.Stock.DocumentLot.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.DocumentLot.template.DocumentLotTemplate;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.MouvementStock.service.MouvementStockService;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.service.TarificationService;
import ERP.Process.Commerciale.Tarification.template.TarificationTemplate;
import ERP.Process.Commerciale.Tarification.web.TarificationActionManager;
import ERP.Process.Commerciale.TarificationLot.service.TarificationLotService;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Type_tarification.service.Type_tarificationService;
import ERP.Process.Commerciale.Type_tarification.template.Type_tarificationTemplate;
import ERP.Process.Commerciale.Vente.Devis.service.DevisService;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.service.ProcedureVenteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonObject;


public class DocumentLotActionManager extends DocumentLotTemplate {
 
	private static final long serialVersionUID = 4813916834084312495L;
	
	private DocumentLotService serviceDocumentLot;
	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
		this.serviceDocumentLot = serviceDocumentLot;
	}
	
	private DevisService serviceDevis;
	@Autowired
	public void setServiceDevis(DevisService serviceDevis) {
		this.serviceDevis = serviceDevis;
	}
	
	
	private TarificationService	serviceTarification;
	@Autowired
	public void setServiceTarification(TarificationService serviceTarification) {
		this.serviceTarification = serviceTarification;
	}	
	
	
	@Autowired  public  Type_tarificationService  serviceType_tarification;
		
	
	private MouvementStockService  serviceMouvementStock;
	@Autowired
	public void setServiceMouvementStock(MouvementStockService serviceMouvementStock) {
	    this.serviceMouvementStock = serviceMouvementStock;
	} 
	
	private TarificationLotService  serviceTarificationLot;
	@Autowired
	public void setServiceTarificationLot(TarificationLotService serviceTarificationLot) {
	    this.serviceTarificationLot = serviceTarificationLot;
	} 
	
	@Autowired  public  TarificationDAO           dAOTarification ;
	
	 private Code_barreService serviceCode_barre;
		
		@Autowired
		public void setServiceCode_barre(Code_barreService serviceCode_barre) {
			this.serviceCode_barre = serviceCode_barre;
	}
		@Autowired Entite_etat_commercialeService   serviceEntite_etat_commerciale;

		 
	public    ModelAndView doIniServletAction() {

		
		try {
			
			removeObjectModel(FORM_BEAN );
			removeObjectModel(SEARCH_BEAN );
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			test_ACT_CONSULT_INTO =false; 
			doLoadingLibelleOtherSModule(ID_SOUS_MODULE);
			SerieArticletBean beanSearch = new SerieArticletBean();
			doLoadingLibelleOtherSModule(Type_tarificationTemplate.ID_SOUS_MODULE);
			List<Type_tarificationBean> list_tyList = serviceType_tarification.doFetchDatafromServer(new Type_tarificationBean());
			setObjectValueModel(TarificationTemplate.LIST_TYLIST, list_tyList);
			
			Entite_etat_commercialeBean beanMode_prix = new Entite_etat_commercialeBean();
			beanMode_prix.setCode_entite("mode_prix_vente");
			setObjectValueModel("list_mode_prix",serviceEntite_etat_commerciale.dofetchDatafromServer(beanMode_prix));
			
			Entite_etat_commercialeBean beanMode_choix_lot = new Entite_etat_commercialeBean();
			beanMode_choix_lot.setCode_entite("mode_choix_lot");
			setObjectValueModel("list_choix_lotx",serviceEntite_etat_commerciale.dofetchDatafromServer(beanMode_choix_lot));
			
			 setObjectValueModel(LIST_DETAILLE_MVT_LOT, new ArrayList());
			
			 if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
				return getViewAdd(FORM_VIEW);
				
			}else if( bs.getFct_id().equals(Fn_Choix)  ){
				
				 beanSearch.setCondition_list_article("  AND  bean.quantite > 0   AND  ( bean.serie_bloque  is null  or  bean.serie_bloque = false )  ");
				 List listDataSrv = serviceDocumentLot.doFetchDatafromServer(beanSearch);
				 List list_des_articles     = new ArrayList();
				 List list_des_depot        = new ArrayList();
				 HashMap  map_bidan         = new HashMap();
				 HashMap  map_bidan_deux    = new HashMap();
				 HashMap  map_bidan_select  = new HashMap();
				 
				 for (int i = 0; i < listDataSrv.size(); i++) {
					 SerieArticletBean beanx=(SerieArticletBean) listDataSrv.get(i);
					 if( !StringUtils.isEmpty(beanx.getSelected())  ){
						 map_bidan_select.put(beanx.getFkCode_barre().getPk().getCode_barre(), "TRUE");
						 continue;
					 }
					 if( map_bidan.get(beanx.getFkCode_barre().getPk().getCode_barre())==null){
						 map_bidan.put(beanx.getFkCode_barre().getPk().getCode_barre(), "TRUE");
						 list_des_articles.add(beanx.getFkCode_barre());
					 }
					 
					 if( map_bidan_deux.get(beanx.getPk().getDepot().getDepot_id())==null){
						 map_bidan_deux.put(beanx.getPk().getDepot().getDepot_id(), "TRUE");
						 list_des_depot.add(beanx.getPk().getDepot());
					 }
				}
				 int dddd=list_des_articles.size();
				 for (int i = 0; i < dddd; i++) {
					 Code_barreBean beanBarre=(Code_barreBean) list_des_articles.get(i);
					 if(map_bidan_select.get(beanBarre.getPk().getCode_barre())!=null) {
						 list_des_articles.remove(i);
						 dddd--;
						 i--;
					 }
				 }
				 setObjectValueModel("list_des_articles", list_des_articles);
				 HashMap mapddcode_barre=ProcessUtil.getHashMap_code_bean(list_des_articles, "pk.code_barre");
				 setObjectValueModel("mapddcode_barre", mapddcode_barre);
				 setObjectValueModel("list_des_depot", list_des_depot);
				 
				return getViewChoix(FORM_CHOIX_VIEW);
				
				
			}else {
				
				
				 
			   
				if(bs.getSousmod_id().equals(ID_SOUS_MODULE_METHODE)  ){
					
					beanSearch.setCondition_list_article("  AND  bean.quantite > 0     " +
			 		"                                AND  bean.selected='X' ");
			 List listDataSrv = serviceDocumentLot.doFetchDatafromServer(beanSearch);
			 List list_des_articles = new ArrayList();
			 List list_des_depot = new ArrayList();
			 HashMap  map_bidan = new HashMap();
			 HashMap  map_bidan_deux = new HashMap();
			 HashMap  map_bidan_select = new HashMap();
			 
			 for (int i = 0; i < listDataSrv.size(); i++) {
				 SerieArticletBean beanx=(SerieArticletBean) listDataSrv.get(i);
				  
				 if( map_bidan.get(beanx.getFkCode_barre().getPk().getCode_barre())==null){
					 map_bidan.put(beanx.getFkCode_barre().getPk().getCode_barre(), "TRUE");
					 list_des_articles.add(beanx.getFkCode_barre());
				 }
				 
				 if( map_bidan_deux.get(beanx.getPk().getDepot().getDepot_id())==null){
					 map_bidan_deux.put(beanx.getPk().getDepot().getDepot_id(), "TRUE");
					 list_des_depot.add(beanx.getPk().getDepot());
				 }
			}
			 
			 setObjectValueModel("list_des_articles", list_des_articles);
			 HashMap mapddcode_barre=ProcessUtil.getHashMap_code_bean(list_des_articles, "pk.code_barre");
			 setObjectValueModel("mapddcode_barre", mapddcode_barre);
			 
			 setObjectValueModel("list_des_depot", list_des_depot);
					if(bs.getFct_id().equals(Fn_Modifier))
					return getViewChoix(FORM_CHOIX_VIEW);
					else
					 return getViewFilterAjax(FILTER_CHOIX_VIEW);
					
					
					
				}else{
					
					return getViewFilterAjax(FILTER_VIEW);
				}
				
				
				

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}
	}
	
	public    ModelAndView doReset_Form() {
		 removeObjectModel(FORM_BEAN);
		 List list_editable_article_lot_init =(List) getObjectValueModel(LIST_DETAILLE_MVT_LOT_CLONE);
		 setObjectValueModel(LIST_DETAILLE_MVT_LOT, list_editable_article_lot_init);
		return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
	}
	
	public    ModelAndView doReset_Form_choix() {
		 removeObjectModel(FORM_BEAN);
		 removeObjectModel(SEARCH_BEAN);
		 setObjectValueModel("list_des_lots_for_select", new ArrayList());
		 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		 if(bs.getFct_id().equals(Fn_Choix))
		 return getViewChoix( FORM_CHOIX_VIEW);
		 else
		 return getViewFilterAjax( FILTER_CHOIX_VIEW); 
	}
	
	public    ModelAndView doSelect_RowBean() {

		try {
			 
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			 if (bs.getFct_id().equals(Fn_Rectifier)){
				 
				return getViewConsult(FORM_VIEW_CONSULT);
			} 
			SerieArticletBean rowBean = (SerieArticletBean) getIndexFromDataGrid_v1(LIST_DATA);
			setObjectValueModel(FORM_BEAN, rowBean);
			MouvementSerieBean beanSearch= new MouvementSerieBean();
			beanSearch.getPk().setSerieBean(rowBean);
			List<MouvementSerieBean>    listmvtLot =  serviceMouvementStock.doFetchDatafromServer(beanSearch);
			
			String ssdsd="";
			
		 	 for (int i = 0; i < listmvtLot.size(); i++) {
		    	 MouvementSerieBean bean =listmvtLot.get(i);
		         if(bean.getPk().getNat_mvt().getNature_mvt_id().equals("ve")){
		        	//Double tarif=ProcessNumber.DIVISION(bean.getMontant_ht_operation(), bean.getQuantite_operation());
		        	//bean.setTarif_unit_vente( ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tarif));
		        	ssdsd=ssdsd+"'"+bean.getTarif_operation_id()+"',";
		         }
		        	 
			}
			 HashMap  mapTarifVente= new HashMap();
			 if(ssdsd.length()>0){
				 ssdsd=ssdsd.substring(0, ssdsd.length()-1) ;
				  TarificationBean  beanSearchT  = new TarificationBean();
				  beanSearchT.setMethode_prix("   AND   bean.tarif_vente_id  in ( "+ssdsd+" )       ");
				  List tarif=dAOTarification.doFindListTarification(beanSearchT);
				  mapTarifVente= ProcessUtil.getHashMap_code_bean(tarif, "tarif_vente_id");
			 }  
			 
			
			
		    for (int i = 0; i < listmvtLot.size(); i++) {
		    	 MouvementSerieBean bean =listmvtLot.get(i);
		    	 if(bean.getPk().getNat_mvt().getNature_mvt_id().equals("ac"))
		    		 bean.setTarif_unit_achat(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(bean.getPk().getSerieBean().getTarif().getTarif_unit_article()));
		    		
		       if(bean.getPk().getNat_mvt().getNature_mvt_id().equals("ve")){
		        	 TarificationBean  beantarif=(TarificationBean) mapTarifVente.get(bean.getTarif_operation_id());
		        	 if(beantarif==null)
		        	 bean.setTarif_unit_vente("0");
		        	 else
		        		 bean.setTarif_unit_vente(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(beantarif.getTarif_unit_vente()));
		        		 
		         }
		        	 
			} 
			
			setObjectValueModel(LIST_DETAILLE_MVT_LOT, listmvtLot);
			setObjectValueModel(LIST_EDITABLE_ARTICLE_LOT_ORIGIN, ProcessUtil.cloneList(listmvtLot) );
			
			
			if (bs.getFct_id().equals("2")){
				return getViewConsult(FORM_VIEW_CONSULT);
			}
			
			if (bs.getFct_id().equals(Fn_Etat)   ){
				return getViewUpdate(FORM_VIEW);
			}
			
			if (bs.getFct_id().equals("3")   ){
				return getViewUpdate(FORM_VIEW);
			}
			
			if (bs.getFct_id().equals("4")){
			 
				return getViewDelete(FORM_VIEW_CONSULT);
			}
			
			 
			
		} catch (Exception e) {
			displayException(e);
		}
		return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

	}
	
	public    ModelAndView doSelect_Detail_lot() throws Exception {

		try {
			SerieArticletBean rowBean = (SerieArticletBean) getObjectValueModel(FORM_BEAN);
			
			SerieArticletBean bean = new SerieArticletBean();
		 
			List <SerieArticletBean> list_editable= new ArrayList<SerieArticletBean>();
			setObjectValueModel(LIST_DETAILLE_MVT_LOT, list_editable);
			setObjectValueModel(LIST_EDITABLE_ARTICLE_LOT_ORIGIN, ProcessUtil.cloneList(list_editable) );
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;

	}

	public    ModelAndView doGET_test_consult() throws Exception {

		try {
			 
		} catch (Exception e) {
			displayException(e);
		}
		return  getViewConsult(FORM_VIEW_CONSULT);

	}
	public ModelAndView doFetchLotData(SerieArticletBean searchBean) throws Throwable {
		try {
			
            BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			searchBean.setCondition_list_article("  AND  bean.quantite > 0   AND  ( bean.serie_bloque  is null  or  bean.serie_bloque = false ) " +
					"                               AND  (bean.selected is null or   bean.selected='')  ");
			
			if(bs.getSousmod_id().equals(ID_SOUS_MODULE_METHODE) && ! bs.getFct_id().equals(Fn_Choix)){
				searchBean.setCondition_list_article("  AND  bean.quantite > 0    AND  bean.selected='X' ");
			 }
            if(bs.getSousmod_id().equals(ID_SOUS_MODULE_METHODE) &&   bs.getFct_id().equals(Fn_Modifier)){
				searchBean.setCondition_list_article("  AND  bean.quantite > 0    ");
			}
            TarificationBean tar= new TarificationBean();
            tar.setDernierPrix(true);
            tar.setMethode_prix("   AND  (bean.tarif_lot is null or  bean.tarif_lot is false)  AND   bean.num_serie is null   AND  bean.depot.depot_id  is null  ");
            tar.setMethode_prix2("  AND  (BA.tarif_lot is null   or  BA.tarif_lot is false)    AND   BA.num_serie is null     AND  bean.depot.depot_id  is null   ");
            tar.setFkCode_barre(searchBean.getFkCode_barre());
            tar.setGroupe(searchBean.getVente().getGroupe());
            List<TarificationBean> listDatafromServer  = serviceTarification.doFetchDatafromServer(tar);
            TarificationBean tarica= new TarificationBean();
            if(listDatafromServer!=null  && listDatafromServer.size()>0)
            	tarica=listDatafromServer.get(0);
			List listDataSrv                           = serviceDocumentLot.doFetchDatafromServer(searchBean);
			 
			for (int i = 0; i < listDataSrv.size(); i++) {
	        	SerieArticletBean  sBean =(SerieArticletBean) listDataSrv.get(i) ;	
	        	sBean.setVente(tarica);
	        	if( StringUtils.isNotEmpty(sBean.getSelected()) ){
	        		sBean.setTo_check("checked");
	        	}
	        	 TarificationBean nvente  =  new TarificationBean();
	        	 TarificationBean search2 =  new TarificationBean();
	        	 search2.setDernierPrix(true);
	        	 search2.setFkCode_barre( sBean.getFkCode_barre());
	        	 search2.setGroupe(searchBean.getVente().getGroupe());
	        	 search2.setTarif_lot(true);
	        	 search2.setDepot(sBean.getPk().getDepot());
	        	 search2.setNum_serie(sBean.getPk().getNum_serie());
	             List<TarificationBean> listDatafro2  = serviceTarification.doFetchDatafromServer(search2);
	             sBean.setIs_readonly("") ;
		         if(listDatafro2!=null  && listDatafro2.size()>0){
		        	 nvente=listDatafro2.get(0);
		             sBean.setNvente(nvente) ;
		             //sBean.setIs_readonly("readonly") ;
		         } 
		         
		         
			}
			
			 if(bs.getSousmod_id().equals(ID_SOUS_MODULE_METHODE) &&   bs.getFct_id().equals(Fn_Modifier)){
			 setObjectValueModel("list_des_lots_for_select_modif", ProcessUtil.cloneList(listDataSrv));	 
				 }
			setObjectValueModel(SEARCH_BEAN, searchBean);
			setObjectValueModel("list_des_lots_for_select", listDataSrv);
			
			
			 HashMap mapddcode_barre = (HashMap) getObjectValueModel("mapddcode_barre"  );
			 Code_barreBean   bCode_barreBean=  (Code_barreBean) mapddcode_barre.get( searchBean.getFkCode_barre().getPk().getCode_barre()) ;
			 String choixModePrix="pd";
			 String choix_Lot="arb";
			 if(bCode_barreBean.getPk().getAr_bean().getMode()!=null 
		    		 &&  !bCode_barreBean.getPk().getAr_bean().getMode().getData_id().equals("")){
				 choixModePrix=bCode_barreBean.getPk().getAr_bean().getMode().getData_id();
			 }
			 if(bCode_barreBean.getPk().getAr_bean().getChoix()!=null 
		    		 &&  !bCode_barreBean.getPk().getAr_bean().getChoix().getData_id().equals("")){
				 choix_Lot=bCode_barreBean.getPk().getAr_bean().getChoix().getData_id();
			 }
			 JSONObject json        = new JSONObject();
			 json.put("ret", bs.getFct_id());
			 json.put("choixModePrix", choixModePrix);
			 json.put("choix_Lot", choix_Lot);
			 getResponse().setContentType(JSON_CONTENT_TYPE);      
			 getResponse().getWriter().write(json.toString());
			 
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	
	public ModelAndView doSUPP_choixLotData(SerieArticletBean searchBean) throws Throwable {
		try {
            BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
            List list_des_lots_for_select =(List) getObjectValueModel("list_des_lots_for_select" );
			serviceDocumentLot.doSuppChoixRowData(searchBean,list_des_lots_for_select);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			setObjectValueModel("list_des_lots_for_select", new ArrayList());
		    throwNewException("Opération effectuée");
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	
	public ModelAndView doFetchData(SerieArticletBean searchBean) throws Throwable {
		try {
             BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			List listDataSrv = serviceDocumentLot.doFetchDatafromServer(searchBean);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	public ModelAndView doAddData(SerieArticletBean detailBean) throws Throwable {
		try {
			serviceDocumentLot.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}
	
	

	
	
	public   ModelAndView doTeste_List_saisie( SerieArticletBean beanSave  ) throws Exception{
			
			try {
				String message="";
			 
				List list_des_lots_for_select =(List) getObjectValueModel("list_des_lots_for_select" );
				if(list_des_lots_for_select==null) list_des_lots_for_select = new ArrayList();
				/*****************************************************choi prix de lot************************************************************/
				HashMap  maplot= new HashMap();
				boolean lotchoisi=false;
				if(     beanSave.getFkCode_barre().getPk().getAr_bean().getMode()!=null  &&  
						beanSave.getFkCode_barre().getPk().getAr_bean().getMode().getData_id().equals("pl")){
					int kio=0;
					for (int i = 0; i < list_des_lots_for_select.size(); i++) {
						SerieArticletBean  serBean = 	(SerieArticletBean) list_des_lots_for_select.get(i);
						Double prixArticle=serBean.getVente().getTarif_unit_vente();
						if(prixArticle==null){  message=" Veuillez  entrer le prix principale d'article  "; break;	 }
						
						if(serBean.getTo_check().equals("checked")){
							lotchoisi=true;
							Double dbl=serBean.getNvente().getTarif_unit_vente();
							
							if(dbl==null){  message=" Veuillez  entrer les prix de lot choisis "; break;	 }
							
							String truU=(String) maplot.get(dbl);
							if(truU==null &&  kio==0){
								kio++;
								maplot.put(serBean.getNvente().getTarif_unit_vente(), "yes");
								continue;
							}
							if(truU==null &&  kio>0){
								message=" Les Lots choisis ont des prix différents ";	 
							}
						}
					}
				} 
				if(!lotchoisi &&
						(beanSave.getFkCode_barre().getPk().getAr_bean().getMode()!=null  &&  
								beanSave.getFkCode_barre().getPk().getAr_bean().getMode().getData_id().equals("pl"))	
						){
					message=" Aucun prix de Lots choisis pour cet article  ";	
				}
				/*************************************************choi lot arbit ou par selection************************************************************/
				 
				boolean lotnat_choisi=false;
				if(beanSave.getFkCode_barre().getPk().getAr_bean().getChoix()!=null  &&  
						beanSave.getFkCode_barre().getPk().getAr_bean().getChoix().getData_id().equals("sel")){
					for (int i = 0; i < list_des_lots_for_select.size(); i++) {
						SerieArticletBean  serBean = 	(SerieArticletBean) list_des_lots_for_select.get(i);
						if(serBean.getTo_check().equals("checked")){
							lotnat_choisi=true;
						}
					}
				} 
				if(!lotnat_choisi &&
						
						(beanSave.getFkCode_barre().getPk().getAr_bean().getChoix()!=null  &&  
								beanSave.getFkCode_barre().getPk().getAr_bean().getChoix().getData_id().equals("sel"))
								
				){
					message=" Aucun Lots côchez pour le mode de selection  " ;	
				}
				
				
				
				if(beanSave.getFkCode_barre().getPk().getAr_bean().getMode()!=null  &&  
						beanSave.getFkCode_barre().getPk().getAr_bean().getMode().getData_id().equals("pl")){
					 
					for (int i = 0; i < list_des_lots_for_select.size(); i++) {
						SerieArticletBean  serBean = 	(SerieArticletBean) list_des_lots_for_select.get(i);
						Double prixArticle=serBean.getVente().getTarif_unit_vente();
						if(prixArticle==null){  message=" Veuillez  entrer le prix principale d'article  "; break;	 }
						
						if(serBean.getTo_check().equals("checked")){
							lotchoisi=true;
							Double dbl=serBean.getNvente().getTarif_unit_vente();
							if(dbl==null){  message=" Veuillez  entrer les prix de lot choisis "; break;	 }
							
							TarificationBean  saveTarifLot = new TarificationBean();
							saveTarifLot.setDate_trf(BDateTime.getDateActuel());
							saveTarifLot.setFkCode_barre(serBean.getFkCode_barre());
							saveTarifLot.setCout(serBean.getVente().getCout());
							saveTarifLot.setTarif_lot(true);
							saveTarifLot.setTvaBean(serBean.getFkCode_barre().getPk().getAr_bean().getTva());
							saveTarifLot.setNum_serie(serBean.getPk().getNum_serie());
							saveTarifLot.setDepot(serBean.getPk().getDepot());
							saveTarifLot.setTarif_unit_vente(serBean.getNvente().getTarif_unit_vente());
							saveTarifLot.setGroupe(beanSave.getVente().getGroupe());
							String messageRe=serviceTarification.doTesterTarificationInsert(saveTarifLot);
							if(messageRe.length()>0){  message=messageRe; break;	 }
						 
						}
					}
				 } 
				  getResponse().setContentType(HTML_CONTENT_TYPE);
				  getResponse().getWriter().print(message);
				} catch (Exception e) {
					getResponse().setStatus(500);
					getResponse().setContentType(HTML_CONTENT_TYPE);
					getResponse().getWriter().print(e.getMessage());
				}
				return null;
		 
		}
	
	public ModelAndView doAddChoix(SerieArticletBean detailBean) throws Throwable {
		try {
			 
			List list_des_lots_for_select =(List) getObjectValueModel("list_des_lots_for_select" );
			serviceDocumentLot.doAddChoixRowData(detailBean,list_des_lots_for_select);
			removeObjectModel(FORM_BEAN);
			removeObjectModel(SEARCH_BEAN);
			removeObjectModel("list_des_lots_for_select");
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		   return getViewChoix( FORM_CHOIX_VIEW);
	}
	
	
public ModelAndView doCalculer_Total() throws Exception {
		
		try {
			// rowBean = (MouvementSerieBean) getObjectValueModel(FORM_BEAN);
			
			SerieArticletBean rowBean = (SerieArticletBean) getObjectValueModel(FORM_BEAN);
			
			 
			 List <MouvementSerieBean>listmvtLot= new ArrayList<MouvementSerieBean>();
			 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 if(bs.getFct_id().equals(Fn_Consulter)){
					MouvementSerieBean beanSearch= new MouvementSerieBean();
					beanSearch.getPk().setSerieBean(rowBean);
					listmvtLot =  serviceMouvementStock.doFetchDatafromServer(beanSearch);
					
					String ssdsd="";
					
					 for (int i = 0; i < listmvtLot.size(); i++) {
				    	 MouvementSerieBean bean =listmvtLot.get(i);
				         if(bean.getPk().getNat_mvt().getNature_mvt_id().equals("ve")){
				        	Double tarif=ProcessNumber.DIVISION(bean.getMontant_ht_operation(), bean.getQuantite_operation());
				        	bean.setTarif_unit_vente( ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tarif));
				        	ssdsd=ssdsd+"'"+bean.getTarif_operation_id()+"',";
				         }
				        	 
					}
					 HashMap  mapTarifVente= new HashMap();
					 if(ssdsd.length()>0){
						 ssdsd=ssdsd.substring(0, ssdsd.length()-1) ;
						  TarificationBean  beanSearchT  = new TarificationBean();
						  beanSearchT.setMethode_prix("   AND   bean.tarif_vente_id  in ( "+ssdsd+" )       ");
						  List tarif=dAOTarification.doFindListTarification(beanSearchT);
						  mapTarifVente= ProcessUtil.getHashMap_code_bean(tarif, "tarif_vente_id");
					 }
					 
					
					
				    for (int i = 0; i < listmvtLot.size(); i++) {
				    	 MouvementSerieBean bean =listmvtLot.get(i);
				    	 if(bean.getPk().getNat_mvt().getNature_mvt_id().equals("ac"))
				    		 bean.setTarif_unit_achat(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(bean.getPk().getSerieBean().getTarif().getTarif_unit_article()));
				    		
				         if(bean.getPk().getNat_mvt().getNature_mvt_id().equals("ve")){
				        	 TarificationBean  beantarif=(TarificationBean) mapTarifVente.get(bean.getTarif_operation_id());
				        	 if(beantarif==null)
				        	 bean.setTarif_unit_vente("0");
				        	 else
				        		 bean.setTarif_unit_vente(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(beantarif.getTarif_unit_vente()));
				        		 
				         }
				        	 
					}
					 
					setObjectValueModel(LIST_DETAILLE_MVT_LOT, listmvtLot);
			 }else{
				    listmvtLot   =(List) getObjectValueModel(LIST_DETAILLE_MVT_LOT);
			 }
			 
			 
			double tot_ht=0;
			double tot_tva=0;
			Double tot_qte=new Double(0);
			
			for (int i = 0; i < listmvtLot.size(); i++) {
				MouvementSerieBean  bean=listmvtLot.get(i);
				tot_ht=ProcessNumber.doMath(tot_ht, bean.getMontant_ht_operation(),bean.getPk().getNat_mvt().getNature_operation().charAt(0));
				tot_tva=ProcessNumber.doMath(tot_tva, bean.getMontant_tva_operation(),bean.getPk().getNat_mvt().getNature_operation().charAt(0));
				tot_qte=ProcessNumber.doMath(tot_qte, bean.getQuantite_operation(),bean.getPk().getNat_mvt().getNature_operation().charAt(0));
			}
			
			
			 
			double total_mnt_genX   = ProcessNumber.addition(tot_ht, tot_tva);
			
			 
			
			SerieArticletBean    reBean= new SerieArticletBean();
			reBean.setTotal_mnt_ht(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tot_ht));
			reBean.setTotal_mnt_tva(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tot_tva));
			reBean.setTotal_quantite(ProcessFormatNbr.Convert_using_Double_toString(tot_qte));
			
			setObjectValueModel(BEAN_TOTAL, reBean);
			
			JsonObject data = new JsonObject();
			data.addProperty("tq",reBean.getTotal_quantite());
		    data.addProperty("tht", reBean.getTotal_mnt_ht());
		    data.addProperty("ttva",  reBean.getTotal_mnt_tva());
		    data.addProperty("tg",  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mnt_genX));
		    
		    getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(data.toString());
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	public  static  ModelAndView doCancelUpdate() {
		Object  obj=getObjectValueModel(ORIGINAL_FORM_BEAN);
		setObjectValueModel(FORM_BEAN, obj);
		
		List  list_editable_article_lot_origin =(List) getObjectValueModel(LIST_EDITABLE_ARTICLE_LOT_ORIGIN);
		setObjectValueModel(LIST_DETAILLE_MVT_LOT, list_editable_article_lot_origin);
		return getViewUpdate((String) getObjectValueModel("FORM_VIEW"));
	}

	public ModelAndView doUpdateData(SerieArticletBean beanUpBean) {
		try {
			serviceDocumentLot.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(SerieArticletBean beanDelBean) {
		try {
			serviceDocumentLot.doDeleteRowData(beanDelBean);
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			list.remove(Integer.parseInt(indexo));
			setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), list);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	 
}
