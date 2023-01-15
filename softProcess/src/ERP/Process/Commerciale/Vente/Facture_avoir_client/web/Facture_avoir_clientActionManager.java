package ERP.Process.Commerciale.Vente.Facture_avoir_client.web;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.template.CommandeclientTemplate;
import ERP.Process.Commerciale.Vente.Facture_avoir_client.dao.Facture_avoir_clientDAO;
 
import ERP.Process.Commerciale.Vente.Facture_avoir_client.model.Facture_avoir_clientBean;
import ERP.Process.Commerciale.Vente.Facture_avoir_client.template.Facture_avoir_clientTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.dao.Facture_clientDAO;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Detail_mvt_vente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.MvtVente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.service.Facture_clientService;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.dao.TypeAvoirDAO;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model.TypeAvoirBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.service.ProcedureVenteService;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.RetourVente.dao.RetourVenteDAO;
import ERP.Process.Commerciale.Vente.RetourVente.model.DetRetourVenteBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;


public class Facture_avoir_clientActionManager extends Facture_avoir_clientTemplate {
	 
	    private static final long serialVersionUID = -3888376321878083343L;
	
	
		 
	    private Facture_clientService  serviceFacture;
		
		
		@Autowired
		public void setServiceFacture(Facture_clientService serviceFacture) {
		    this.serviceFacture = serviceFacture;
		}
		
		private ClientDAO daoClient;
		@Autowired
		public void setDaoClient(ClientDAO daoClient) {
			this.daoClient = daoClient;
		}
		
	
		
		private Facture_avoir_clientDAO daoFacture_avoir_client;
		@Autowired
		public void setDaoFacture_avoir_client(Facture_avoir_clientDAO daoFacture_avoir_client) {
			this.daoFacture_avoir_client = daoFacture_avoir_client;
		}
		
		
		
		private RetourVenteDAO    daoRetourVente;
		@Autowired
		public void setDaoRetourVente(RetourVenteDAO daoRetourVente) {
		    this.daoRetourVente = daoRetourVente;
		}
		
		private Facture_clientDAO daoFacture;
		@Autowired
		public void setDaoFacture(Facture_clientDAO daoFacture) {
			this.daoFacture = daoFacture;
		}
		
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
				setObjectValueModel(FORM_BEAN, new Facture_clientBean());
				setObjectValueModel(SEARCH_BEAN, new Facture_clientBean());
				
				BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				doLoadingLibelleOtherSModule(ProcedureVenteTemplate.ID_SOUS_MODULE);
				doLoadingLibelleOtherSModule(CommandeclientTemplate.ID_SOUS_MODULE);
				doLoadingLibelleOtherSModule( Facture_clientTemplate.ID_SOUS_MODULE);
			 
				setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
				setObjectValueModel(LIST_AVOIR_TYPE  , daoTypeAvoir.doFindListTypeAvoir(TypeAvoirBean.class.newInstance()));
				
				List list_client_d= daoClient.doFindListClient(ClientBean.class.newInstance());
				setObjectValueModel(LIST_CLIENT_VENTE, list_client_d);
			 
				List list_des_tva= serviceTVA.doFetchDatafromServer(TVABean.class.newInstance());
			    setObjectValueModel(LIST_DES_TVA, list_des_tva);
					 
					  if (bs.getFct_id().equals(Fn_Creer)   ) {
						    setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
							setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACT); 
							setObjectValueModel(NAME_GRID_G, NAME_GRID_FACT);
							setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_FACTURE_DOIT");
							return getView_FilterFacture_doit(FILTER_VIEW_FACTURE);
						} else {
							
							setObjectValueModel(FORM_BEAN, new Facture_avoir_clientBean());
							setObjectValueModel(SEARCH_BEAN, new Facture_avoir_clientBean());
							return getViewFilterAjax(FILTER_VIEW);
						}
			} catch (Exception e) {
				displayException(e);
				return getHome();
			}
			 
		}
		
		
		
		@SuppressWarnings("unchecked") 
		public ModelAndView doFetchData(Facture_avoir_clientBean searchBean)throws Throwable {
				try {
					List listDataSrv = daoFacture_avoir_client.doFindListFactureAvoir(searchBean);
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
		
		@SuppressWarnings("unchecked") 
		public ModelAndView doFetchDataFactureDoit(Facture_clientBean searchBean)throws Throwable {
				try {
					List listDataSrv = daoFacture_avoir_client.doFindListFactureclient(searchBean);
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
				Facture_clientBean rowBean =  new Facture_clientBean();
				
				if (bs.getFct_id().equals(Fn_Creer)){
				  rowBean = (Facture_clientBean) getIndexFromDataGrid_v1(LIST_DATA_FACT);
				  Facture_avoir_clientBean  factBean= new Facture_avoir_clientBean();
				  factBean.setFactclient(rowBean);
					setObjectValueModel(FORM_BEAN, factBean);
				}else{
					
					Facture_avoir_clientBean avoirBEan = (Facture_avoir_clientBean) getIndexFromDataGrid_v1(LIST_DATA);
					Facture_clientBean beanSearch =  new Facture_clientBean();
					beanSearch.setFact_clt_id(avoirBEan.getAvoir_id());
					
					List list_facture=serviceFacture.doFetchDatafromServer(beanSearch);
					Facture_clientBean facturAvoir    =  (Facture_clientBean) list_facture.get(0);
					
					Facture_clientBean facture =  avoirBEan.getFactclient();
					
					avoirBEan.setAvoir_date(facturAvoir.getFact_date());
					 
					
					List list_detaille=serviceFacture.doFetchDetaillefromServer(facturAvoir);
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
		
		public    ModelAndView doLoad_avoir_survente(Facture_avoir_clientBean beanSearch) throws Exception {

			try {
				
				setObjectValueModel(LIST_RETOURVENTE_RESULTAT, new ArrayList<DetRetourVenteBean>());
				
				List<Det_Fact_ClientBean>   list_det_facture=  daoFacture.doFindList_detaille_Facture(beanSearch.getFactclient());
				String mvtVente="";
				for ( Det_Fact_ClientBean bean:list_det_facture) {
					mvtVente=mvtVente+"'"+bean.getPk().getMvtVente().getMvt_vente_id()+"',";
				}
				if(mvtVente.length()>0){
					String  lesMvtVnete=mvtVente.substring(0, mvtVente.length()-1);
					List<Detail_mvt_vente_articleBean>   list_detvente=daoFacture.doFindList_detaille_mvt_Vente(lesMvtVnete);
					String lesVente="";
					
					for ( Detail_mvt_vente_articleBean beanven:list_detvente) {
						lesVente=lesVente+"'"+beanven.getPk().getVente().getVente_id()+"',";
					}
					
					if(lesVente.length()>0){
					lesVente=lesVente.substring(0, lesVente.length()-1);
					 List<DetRetourVenteBean> listdesRetour =daoRetourVente.doFindList_detaille_plusieur_RetourVente(lesVente);
					 setObjectValueModel(LIST_RETOURVENTE_RESULTAT, listdesRetour);
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
		public ModelAndView doCalculer_Total(Facture_clientBean detadilBean ) throws Exception {
			
			try {
				 
				Facture_clientBean   rowBean =  new Facture_clientBean();
				List <Det_Fact_ClientBean >List_detaille= new ArrayList<Det_Fact_ClientBean>();
				BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				if(bs.getFct_id().equals(Fn_Generer)   ){
					 rowBean = (Facture_clientBean) getObjectValueModel(FORM_BEAN);
					 List_detaille=(List<Det_Fact_ClientBean>) getObjectValueModel(LIST_DATA_DET_FACT);
				}else{
					 
					Facture_avoir_clientBean avoirBEan = (Facture_avoir_clientBean) getObjectValueModel(FORM_BEAN);
					Facture_clientBean beanSearch =  new Facture_clientBean();
					beanSearch.setFact_clt_id(avoirBEan.getAvoir_id());
					rowBean=avoirBEan.getFactclient();
					List_detaille=serviceFacture.doFetchDetaillefromServer(beanSearch);
						 
				}
				
				
				setObjectValueModel(LIST_DATA_DET_FACT, List_detaille);
				setObjectValueModel(LIST_DATA_DET_FACT_CLONE, ProcessUtil.cloneList(List_detaille) );  
				
				
				double tot_ht=0;
				double tot_tva=0;
				Double tot_qte=new Double(0);
				
				HashMap   mapTva = new HashMap();
				for (int i = 0; i < List_detaille.size(); i++) {
					Det_Fact_ClientBean  bean=List_detaille.get(i);
					tot_ht=ProcessNumber.addition(tot_ht, bean.getMontant_ht_vente());
					tot_tva=ProcessNumber.addition(tot_tva, bean.getMontant_tva_vente());
					tot_qte=ProcessNumber.addition(tot_qte, bean.getQuantite());
					
					List  listTva = (List) mapTva.get(bean.getTvaBean().getTva_libelle());
					if(listTva==null)listTva= new ArrayList();
					listTva.add(bean);
					mapTva.put(bean.getTvaBean().getTva_libelle(), listTva);
					
				}
				Facture_clientBean    reBean = new Facture_clientBean();
				 
				 double remise=ProcessFormatNbr.FormatDouble_Troischiffre(new Double(0));
				 reBean.setFacture_remise(remise);
				 reBean.setAvance_montant_vente(new Double(0))  ; 
				     
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
						 		Det_Fact_ClientBean  bean=(Det_Fact_ClientBean) listTva.get(i);
								le_Ht=ProcessNumber.addition(le_Ht, bean.getMontant_ht_vente());
								le_Ht_brute=ProcessNumber.addition(le_Ht_brute, bean.getMontant_ht_vente());
								leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_vente());
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
				 setObjectValueModel(BEAN_TOTAL_FACTURE_CLIENT, reBean);
				 getResponse().setContentType(JSON_CONTENT_TYPE);      
				 getResponse().getWriter().write(json.toString());
				
			} catch (Exception e) {
				getResponse().setContentType(HTML_CONTENT_TYPE);
				getResponse().getWriter().print(e.getMessage());
			}
			return null;
		}	
		
		public ModelAndView doAddData(Facture_avoir_clientBean detailBean) throws Throwable {
		     try {
					setObjectValueModel(FORM_BEAN, detailBean);
					daoFacture_avoir_client.doSaveFacture_avoir_client(detailBean);
		            removeObjectModel(FORM_BEAN);
		            throwNewException("ins01");
		          } catch (Exception e) {
		            displayException(e);
		          }
		        return getViewAdd(FORM_VIEW);
			}
		public ModelAndView doUpdateData(Facture_clientBean beanUpBean) {	 
			 	try {
			 		daoFacture_avoir_client.doUpdateFacture_avoir_client(beanUpBean);
					update_row_from_list(LIST_DATA, beanUpBean); 
			 throwNewException("mod01");
			 	} catch (Exception e) {
			 	displayException(e);
			 }
			return getViewList_Ajax(FILTER_VIEW);
				}
		public ModelAndView doDeleteData(Facture_clientBean beanDelBean) {
		    try {
		    	daoFacture_avoir_client.doDeleteFacture_avoir_client(beanDelBean);
					    remove_row_from_list(LIST_DATA);
		     throwNewException("sup01");
		       } catch (Exception e) {
		       displayException(e);
		       }
		    return getViewList_Ajax(FILTER_VIEW);
		   }
 }
