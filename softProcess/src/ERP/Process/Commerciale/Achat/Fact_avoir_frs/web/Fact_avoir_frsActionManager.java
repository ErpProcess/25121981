package ERP.Process.Commerciale.Achat.Fact_avoir_frs.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import ERP.Process.Commerciale.Achat.Fact_avoir_frs.model.Fact_avoir_frsBean;
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.service.Fact_avoir_frsService;
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.template.Fact_avoir_frsTemplate;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Det_Fact_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Detail_mvt_achat_factureBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.service.Facture_FournisseurService;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.template.Facture_FournisseurTemplate;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Achat.Retour_achat.dao.Retour_achatDAO;
import ERP.Process.Commerciale.Achat.Retour_achat.model.Det_retour_achatBean;
import ERP.Process.Commerciale.Demande_Achat.template.Demande_AchatTemplate;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.dao.TypeAvoirDAO;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model.TypeAvoirBean;
 
 

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;


public class Fact_avoir_frsActionManager extends Fact_avoir_frsTemplate {
	
	
 
	private static final long serialVersionUID = -2777326779266972961L;
	
	    private Fact_avoir_frsService  serviceFact_avoir_frs;
       
		@Autowired
		public void setServiceFact_avoir_frs(Fact_avoir_frsService serviceFact_avoir_frs) {
		    this.serviceFact_avoir_frs = serviceFact_avoir_frs;
		} 
		
		private Facture_FournisseurService serviceFacture_Fournisseur;

		@Autowired
		public void setServiceFacture_Fournisseur(
				Facture_FournisseurService serviceFacture_Fournisseur) {
			this.serviceFacture_Fournisseur = serviceFacture_Fournisseur;
		}
		
		private Retour_achatDAO daoRetour_achat;
		@Autowired
		public void setDaoRetour_achat(Retour_achatDAO daoRetour_achat) {
			this.daoRetour_achat = daoRetour_achat;
		}
		
		@Autowired
		FournisseurService     serviceFournisseur;
	
		private TVAService   serviceTVA;
		@Autowired
		public void setServiceTva(TVAService serviceTVA) {
			this.serviceTVA = serviceTVA;
		}
		private TypeAvoirDAO   daoTypeAvoir;
		@Autowired
		public void setDaoTypeAvoir(TypeAvoirDAO daoTypeAvoir) {
			this.daoTypeAvoir = daoTypeAvoir;
		}
		
		private      DepotStockageService       serviceDepotStockage;
		@Autowired
		public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
			this.serviceDepotStockage = serviceDepotStockage;
		}
	
	
	public    ModelAndView doInitServletAction() {

		
		
		try {
			setObjectValueModel(FORM_BEAN, new Facture_FournisseurBean());
			setObjectValueModel(SEARCH_BEAN, new Facture_FournisseurBean());
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			doLoadingLibelleOtherSModule(Reception_achatTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(Demande_AchatTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(Facture_FournisseurTemplate.ID_SOUS_MODULE);
		 
			setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			setObjectValueModel(LIST_AVOIR_TYPE  , daoTypeAvoir.doFindListTypeAvoir(TypeAvoirBean.class.newInstance()));
			List list_frs_d= serviceFournisseur.dofetchDatafromServer(FournisseurBean.class.newInstance());
			setObjectValueModel(LIST_FRS_ACHAT, list_frs_d);
			List list_des_tva= serviceTVA.doFetchDatafromServer(TVABean.class.newInstance());
		    setObjectValueModel(LIST_DES_TVA, list_des_tva);
				 
				  if (bs.getFct_id().equals(Fn_Creer)   ) {
					    setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
						setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACT); 
						setObjectValueModel(NAME_GRID_G, NAME_GRID_FACT);
						setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_FACTURE_DOIT");
						return getView_FilterFacture_doit(FILTER_VIEW_FACTURE);
					} else {
						setObjectValueModel(FORM_BEAN, new Fact_avoir_frsBean());
						setObjectValueModel(SEARCH_BEAN, new Fact_avoir_frsBean());
						return getViewFilterAjax(FILTER_VIEW);
					}
		} catch (Exception e) {
			displayException(e);
			return getHome();
		}
		 
	}
	
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchDataFactureDoit(Facture_FournisseurBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceFacture_Fournisseur.doFetchDatafromServer(searchBean);
				setObjectValueModel(SEARCH_BEAN, searchBean);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACT); 
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	
	
	public    ModelAndView doGet_RowBean() {

		try {
			//removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Facture_FournisseurBean  rowBean =  new Facture_FournisseurBean();
			
			if (bs.getFct_id().equals(Fn_Creer)){
			  rowBean = (Facture_FournisseurBean) getIndexFromDataGrid_v1(LIST_DATA_FACT);
			  Fact_avoir_frsBean factBean= new Fact_avoir_frsBean();
			  factBean.setFactfrs(rowBean);
				setObjectValueModel(FORM_BEAN, factBean);
			}else{
				
				Fact_avoir_frsBean avoirBEan = (Fact_avoir_frsBean) getIndexFromDataGrid_v1(LIST_DATA);
				Facture_FournisseurBean beanSearch =  new Facture_FournisseurBean();
				beanSearch.setFact_frs_id(avoirBEan.getAvoir_frs_id());
				
				List list_facture=serviceFacture_Fournisseur.doFetchDatafromServer(beanSearch);
				Facture_FournisseurBean facturAvoir    =  (Facture_FournisseurBean) list_facture.get(0);
				
				Facture_FournisseurBean facture =  avoirBEan.getFactfrs();
				
				avoirBEan.setAvoir_frs_date(facturAvoir.getFact_date());
				 
				
				List list_detaille=serviceFacture_Fournisseur.doFetchDetailfromServer(facturAvoir);
				setObjectValueModel(LIST_DATA_DET_FACT, list_detaille);
				setObjectValueModel(LIST_DATA_DET_FACT_CLONE, ProcessUtil.cloneList(list_detaille) );  
				setObjectValueModel(FORM_BEAN, avoirBEan);
				
			}
			
			
		
			
			if (bs.getFct_id().equals(Fn_Creer))
				return getViewAdd(FORM_VIEW_EDIT);
			
			if (bs.getFct_id().equals(Fn_Consulter))
				return getViewConsult_Pdf_ex( FORM_VIEW );
			
			if (bs.getFct_id().equals(Fn_Modifier))
				return getViewUpdate(FORM_VIEW_EDIT);
			
			if (bs.getFct_id().equals(Fn_Supprimer))
				return getViewDelete( FORM_VIEW );
			
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax( FILTER_VIEW );
		}
		return getHome();

	}
	
	
	public    ModelAndView doLoad_avoir_survente(Fact_avoir_frsBean beanSearch) throws Exception {

		try {
			
			setObjectValueModel(LIST_RETOUR_FRS_RESULTAT , new ArrayList<Det_retour_achatBean>());
			
			List<Det_Fact_FournisseurBean>   list_det_facture=  serviceFacture_Fournisseur.doFetchDetailfromServer(beanSearch.getFactfrs());
			String mvtVente="";
			for ( Det_Fact_FournisseurBean bean:list_det_facture) {
				mvtVente=mvtVente+"'"+bean.getPk().getMvt_achat().getMvt_achat_id()+"',";
			}
			if(mvtVente.length()>0){
				String  lesMvtVnete=mvtVente.substring(0, mvtVente.length()-1);
				List<Detail_mvt_achat_factureBean>   list_det_achat=serviceFacture_Fournisseur.doFindList_detaille_mvt_achat(lesMvtVnete);
				String lesachat="";
				
				for ( Detail_mvt_achat_factureBean beanven:list_det_achat) {
					lesachat=lesachat+"'"+beanven.getPk().getDocument_com_id()+"',";
				}
				
				if(lesachat.length()>0){
					lesachat=lesachat.substring(0, lesachat.length()-1);
				  List<Det_retour_achatBean> listdesRetour =null;//daoRetour_achat.doFindListRetour_achat(beanSearch)
				 setObjectValueModel(LIST_RETOUR_FRS_RESULTAT , listdesRetour);
				} 
			}
			JsonObject data = new JsonObject();	 
		    getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(data.toString());
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;

	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculer_Total(Facture_FournisseurBean detadilBean ) throws Exception {
		
		try {
			 
			Facture_FournisseurBean   rowBean =  new Facture_FournisseurBean();
			List <Det_Fact_FournisseurBean >List_detaille= new ArrayList<Det_Fact_FournisseurBean>();
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if(bs.getFct_id().equals(Fn_Generer)   ){
				 rowBean = (Facture_FournisseurBean) getObjectValueModel(FORM_BEAN);
				 List_detaille=(List<Det_Fact_FournisseurBean>) getObjectValueModel(LIST_DATA_DET_FACT);
			}else{
				 
				Fact_avoir_frsBean avoirBEan = (Fact_avoir_frsBean) getObjectValueModel(FORM_BEAN);
				Facture_FournisseurBean beanSearch =  new Facture_FournisseurBean();
				beanSearch.setFact_frs_id(avoirBEan.getAvoir_frs_id());
				rowBean=avoirBEan.getFactfrs();
				List_detaille=serviceFacture_Fournisseur.doFetchDetailfromServer(beanSearch)  ;
					 
			}
			
			
			setObjectValueModel(LIST_DATA_DET_FACT, List_detaille);
			setObjectValueModel(LIST_DATA_DET_FACT_CLONE, ProcessUtil.cloneList(List_detaille) );  
			
			
			double tot_ht=0;
			double tot_tva=0;
			Double tot_qte=new Double(0);
			
			HashMap   mapTva = new HashMap();
			for (int i = 0; i < List_detaille.size(); i++) {
				Det_Fact_FournisseurBean  bean=List_detaille.get(i);
				tot_ht=ProcessNumber.addition(tot_ht, bean.getMontant_ht_achat());
				tot_tva=ProcessNumber.addition(tot_tva, bean.getMontant_tva_achat());
				tot_qte=ProcessNumber.addition(tot_qte, bean.getQuantite());
				
				List  listTva = (List) mapTva.get(bean.getTvaBean().getTva_libelle());
				if(listTva==null)listTva= new ArrayList();
				listTva.add(bean);
				mapTva.put(bean.getTvaBean().getTva_libelle(), listTva);
				
			}
			Facture_FournisseurBean    reBean = new Facture_FournisseurBean();
			 
			 double remise=ProcessFormatNbr.FormatDouble_Troischiffre(new Double(0));
			 reBean.setFacture_remise(remise);
			 reBean.setAvance_montant_achat(new Double(0))  ; 
			     
			     JSONObject json        = new JSONObject();
			     JSONArray  listDataTva = new JSONArray();
			     JSONArray  list_total  = new JSONArray();
			  
			     JSONObject  el_zero = new JSONObject();
			     el_zero.put("td1","0");
			     el_zero.put("value1","");
			     listDataTva.put(el_zero);
				 
				 /*********************************************************ENTETE***************************************************/
				 el_zero = new JSONObject();
				 el_zero.put("td1","0");
				 el_zero.put("value1","Taux");
			     el_zero.put("td2","1");
				 el_zero.put("value2","Base");
			     el_zero.put("td3","2");
				 el_zero.put("value3","Montant");
				 listDataTva.put(el_zero);
				 
				 /********************************************************************************************************************/
				 
				 HashMap   mapTvaImpression = new HashMap();
				 
				 HashMap   mapTvabidan = new HashMap();
				 Double    total_leTva  = new Double(0);
				 List <TVABean> list_des_tva=  (List) getObjectValueModel(LIST_DES_TVA);
				 Double le_Ht_brute  = new Double(0);
				 for (int j = 0; j < list_des_tva.size(); j++) {
					 TVABean beanTva=list_des_tva.get(j);
					 
					 if(mapTva.get(beanTva.getTva_libelle())!=null){
						 List listTva  =(List) mapTva.get(beanTva.getTva_libelle());
						 String  tva   = beanTva.getTva_libelle();
						  	
						 Double le_Ht  = new Double(0);
					     Double leTva  = new Double(0);
					    
					     
					     
					 	 for (int i = 0; i < listTva.size(); i++) {
					 		Det_Fact_FournisseurBean  bean=(Det_Fact_FournisseurBean) listTva.get(i);
							le_Ht=ProcessNumber.addition(le_Ht, bean.getMontant_ht_achat());
							le_Ht_brute=ProcessNumber.addition(le_Ht_brute, bean.getMontant_ht_achat());
							leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_achat());
						 }
					 	 
					 	 
					 	
					  
					 	 
					 	 
					 	if(mapTvabidan.get(reBean.getFacture_remise())==null &&  reBean.getFacture_remise().doubleValue()> 0  ){
					 		
					 		if(reBean.getFacture_remise().doubleValue() < le_Ht.doubleValue() ){
					 			le_Ht=ProcessNumber.SOUSTRACTION(le_Ht, reBean.getFacture_remise().doubleValue());
						 		String prec=tva.replace("%", "");
						 		leTva=ProcessNumber.Pourcentage(le_Ht, new Double(prec));
						 		mapTvabidan.put(reBean.getFacture_remise(),"true");
						      }
					 		
					 	}
					 	total_leTva=  ProcessNumber.addition(total_leTva, leTva);
					 	  
						 
						 JSONObject  element = new JSONObject();
						 element.put("td1","0");
						 element.put("value1",String.valueOf(tva));
					     
					     String base=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(le_Ht);
					     
						 element.put("td2","1");
						 element.put("value2",base);
					     
						 element.put("td3","2");
						 String tvaB=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(leTva);
						  
						  
						 element.put("value3",tvaB);
						 listDataTva.put(element);
						 
						 mapTvaImpression.put(tva, base+"£"+tvaB);
						  
					 }
				 }
			 
			 json.put("list_tva", listDataTva);
			 setObjectValueModel("mapTvaImpression", mapTvaImpression);
			
			 
			 
			
			 JSONObject  element = new JSONObject();
			 
			 
			 
			 
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Net.H.T");
			 element.put("td2","3");
			 double ht_apres_remise=  ProcessNumber.SOUSTRACTION(le_Ht_brute, remise)  ;
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(ht_apres_remise));
			 list_total.put(element);
			 reBean.setTotal_ht_fact(ProcessFormatNbr.FormatDouble_Troischiffre(ht_apres_remise));
			 
			
			 
			 
			 
			 reBean.setTotal_tva_fact(ProcessFormatNbr.FormatDouble_Troischiffre(total_leTva));
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Total TVA");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_leTva));
			 list_total.put(element);
			 
			 
			 double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
			 reBean.setTotal_facture(ProcessFormatNbr.FormatDouble_Troischiffre(total_mnt_gen));
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Total TTC");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mnt_gen));
			 list_total.put(element);
			 
			 
			 
			 
			 
			 json.put("list_total", list_total);
			 setObjectValueModel("list_total", list_total);
			 setObjectValueModel(BEAN_TOTAL_FACTURE_FRS, reBean);
			 getResponse().setContentType(JSON_CONTENT_TYPE);      
			 getResponse().getWriter().write(json.toString());
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}	
	
	
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(Fact_avoir_frsBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceFact_avoir_frs.doFetchDatafromServer(searchBean);
				setObjectValueModel(SEARCH_BEAN, searchBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	public ModelAndView doAddData(Fact_avoir_frsBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceFact_avoir_frs.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(Fact_avoir_frsBean beanUpBean) {	 
		 	try {
		 serviceFact_avoir_frs.doUpdateRowData(beanUpBean); 
				update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(Fact_avoir_frsBean beanDelBean) {
	    try {
	     serviceFact_avoir_frs.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
