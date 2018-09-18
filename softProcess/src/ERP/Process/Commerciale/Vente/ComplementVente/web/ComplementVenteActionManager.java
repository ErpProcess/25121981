package ERP.Process.Commerciale.Vente.ComplementVente.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.service.Nature_incident_mvt_retourService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Client.template.ClientTemplate;
import ERP.Process.Commerciale.Vente.ComplementVente.model.ComplementVenteBean;
import ERP.Process.Commerciale.Vente.ComplementVente.model.DetComplementBean;
import ERP.Process.Commerciale.Vente.ComplementVente.service.ComplementVenteService;
import ERP.Process.Commerciale.Vente.ComplementVente.template.ComplementVenteTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.dao.ProcedureVenteDAO;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.RetourVente.dao.RetourVenteDAO;
import ERP.Process.Commerciale.Vente.RetourVente.model.DetRetourVenteBean;
 
import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

public class ComplementVenteActionManager extends ComplementVenteTemplate {
 
	private static final long serialVersionUID = -2461688848816426666L;
	private ComplementVenteService serviceComplementVente;

	@Autowired
	public void setServiceComplementVente( ComplementVenteService serviceComplementVente) {
		this.serviceComplementVente = serviceComplementVente;
	}
	
	
	 private Nature_incident_mvt_retourService serviceCause_ret_vente;
		@Autowired
		public void setServiceCause_ret_vente(Nature_incident_mvt_retourService serviceCause_ret_vente) {
		    this.serviceCause_ret_vente = serviceCause_ret_vente;
		} 
	
	private ProcedureVenteDAO   daoProcedureVente;
	@Autowired
	public void setDaoProcedureVente(ProcedureVenteDAO daoProcedureVente) {
		    this.daoProcedureVente = daoProcedureVente;
	} 
	
	@Autowired Entite_etat_commercialeService   serviceEntite_etat_commerciale;
	
	private RetourVenteDAO daoRetourVente;
	@Autowired
	public void setDaoRetourVente(RetourVenteDAO daoRetourVente) {
		this.daoRetourVente = daoRetourVente;
	}
	
	
	private      DepotStockageService       serviceDepotStockage;
	@Autowired
	public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
		this.serviceDepotStockage = serviceDepotStockage;
	}
	
	
	private ClientDAO daoClient;
	@Autowired
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}
	
	
	public    ModelAndView doInitServletAction() {

		
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		try {
			// setObjectValueModel(QTE_STOCK, new Double(0));
			 setObjectValueModel( FORM_BEAN , new ComplementVenteBean() );
			 setObjectValueModel( SEARCH_BEAN , new ComplementVenteBean() );
			 doLoadingLibelleOtherSModule(ProcedureVenteTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(ClientTemplate.ID_SOUS_MODULE);
			 
			 setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			 List list_client_d= daoClient.doFindListClient(ClientBean.class.newInstance());
			 setObjectValueModel(LIST_CLIENT_VENTE, list_client_d);
			 
			 setObjectValueModel( LIST_CAUSE_RETOUR ,  serviceCause_ret_vente.doFetchDatafromServer(Nature_incident_mvt_retourBean.class.newInstance()) );
			 
			 Entite_etat_commercialeBean beanSn = new Entite_etat_commercialeBean();
			 beanSn.setCode_entite("retVente");
			 setObjectValueModel(LIST_SENS_STOCK,serviceEntite_etat_commerciale.dofetchDatafromServer(beanSn));
			 
			 
		 
			 
			 
			
			

			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
				
				setObjectValueModel( FORM_BEAN , new ProcedureVenteBean() );
				setObjectValueModel( SEARCH_BEAN , new ProcedureVenteBean() );
				//bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_vente"));
				setObjectValueModel(MAP_FIELD_BEAN, ProcedureVenteTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_VENTE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_VENTE);
				setObjectValueModel(NAME_GRID_G, NAME_GRID_VENTE);
				
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_VENTE");
				setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+ProcedureVenteTemplate.ID_SOUS_MODULE));
				setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+ProcedureVenteTemplate.ID_SOUS_MODULE)) ;
				return getViewFilter_vente(FILTER_VIEW_VENTE);
				
				
			} else if(bs.getFct_id().equals(Fn_Contremander)){   
				bs.setSousmod_libelle_title((String)getObjectValueModel("cmd_frs"));
				setObjectValueModel(SEARCH_BEAN, new ProcedureVenteBean());
				return getViewFilterAjax(FILTER_VIEW);
				
			} else {
				
				 setObjectValueModel( FORM_BEAN , new ComplementVenteBean() );
				 setObjectValueModel( SEARCH_BEAN , new ComplementVenteBean() );
				return getViewFilterAjax(FILTER_VIEW);

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
	public ModelAndView doLoadSelectList() throws Exception{

	 
	 
		
		List listData = (List) getObjectValueModel(LIST_CAUSE_RETOUR);
		List listData2 = (List) getObjectValueModel(LIST_SENS_STOCK);
	 
		try {
			JSONObject json = new JSONObject();
			JSONArray items  =  doRenderListJson_Select(listData  , "nature_incident_id", "nature_incident_lib");
			JSONArray items2 =  doRenderListJson_Select(listData2 , "data_id"           , "data_libelle");
			json.put("mylistec", items);
			json.put("mylistes", items2);
			getResponse().setContentType("application/json");
			getResponse().getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

}
	
	
	public static JSONArray doRenderListJson_Select(List listData, String fcode, String flabel) throws IOException {

		JSONArray items = new JSONArray();
	

		try {
			for (int i = 0; i < listData.size(); i++) {
				Object bean = (Object) listData.get(i);
				String elementChoiSi = "";
				JSONObject jsonObj = new JSONObject();
				Class<?> c = bean.getClass();
				if (fcode.indexOf(".") > 0) {
					final String[] lesColunmooo = fcode.split("\\.");
					Object object = bean;
					for (int k = 0; k < lesColunmooo.length; k++) {
						Field fieldo = object.getClass().getDeclaredField(lesColunmooo[k]);
						fieldo.setAccessible(true);
						Object obj = fieldo.get(object);
						object = obj;
					}
					String Viaelement = String.valueOf(object);
					Viaelement = Viaelement != null ? Viaelement : "";
					jsonObj.put("keyx", Viaelement);
				} else {

					Field fcodo = c.getDeclaredField(fcode);
					fcodo.setAccessible(true);
					Object valueOfFieldcodo = fcodo.get(bean);
					String elementcodo = String.valueOf(valueOfFieldcodo);
					jsonObj.put("keyx", elementcodo);
				}

				Field fESD = c.getDeclaredField(flabel);
				fESD.setAccessible(true);
				Object valueOfField = fESD.get(bean);
				elementChoiSi = String.valueOf(valueOfField);
				jsonObj.put("valuex", elementChoiSi);
				items.put(jsonObj);

			}

			return  items;
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchDataVente(ProcedureVenteBean searchBean)throws Throwable {
			try {
				searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id  in ('"+Fn_Confirmer+"','"+Fn_Facturer+"')  ");
				setObjectValueModel(MAP_FIELD_BEAN, ProcedureVenteTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_VENTE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_VENTE);
				setObjectValueModel(NAME_GRID_G, NAME_GRID_VENTE);
				List listDataSrv = daoProcedureVente.doFindListProcedureVente(searchBean);
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
	
	public    ModelAndView doGetRowBeanComplement() {

		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			ComplementVenteBean cVenteBean = new ComplementVenteBean();
			removeObjectModel(FORM_BEAN);
			
			if (bs.getFct_id().equals(Fn_Créer)){
				 
				 
				setObjectValueModel(MAP_FIELD_BEAN, ProcedureVenteTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_VENTE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_VENTE);
				setObjectValueModel(NAME_GRID_G, NAME_GRID_VENTE);
				
				ProcedureVenteBean rowBean = (ProcedureVenteBean) getIndexFromDataGrid_v1(LIST_DATA_VENTE);
				cVenteBean.setVente(rowBean);
				List<DetProcedureVenteBean> list_det_vente = daoProcedureVente.doFindDetaille_ListProcedureVente(rowBean);
				
				List<DetComplementBean> list_editable_ComplementVente =  new ArrayList<DetComplementBean>();
				
				for (int i = 0; i < list_det_vente.size(); i++) {
					DetProcedureVenteBean detProce=list_det_vente.get(i);
					DetComplementBean newBean= new DetComplementBean();
					newBean.getPk().setDetv(detProce);
					list_editable_ComplementVente.add(newBean);
				}
				setObjectValueModel(LIST_EDITABLE_COMPLEMNT_VENTE, list_editable_ComplementVente);
				setObjectValueModel(LIST_EDITABLE_COMPLEMNT_VENTE_ORIGINE, ProcessUtil.cloneList(list_editable_ComplementVente) );
				setObjectValueModel(FORM_BEAN, cVenteBean);
				return getViewAdd(FORM_EDIT_VIEW);
			}else{
				ComplementVenteBean beanSearch = (ComplementVenteBean) getIndexFromDataGrid_v1(LIST_DATA);
				List<DetComplementBean> list_editable_ComplementVente =  serviceComplementVente.doFindList_detaille_ComplementVente(beanSearch);
				setObjectValueModel(LIST_EDITABLE_COMPLEMNT_VENTE, list_editable_ComplementVente);
				setObjectValueModel(LIST_EDITABLE_COMPLEMNT_VENTE_ORIGINE, ProcessUtil.cloneList(list_editable_ComplementVente) );
				setObjectValueModel(FORM_BEAN, beanSearch);
				/* List<Incident_det_retour_venteBean> list_Incident_det_vente = null;//daoComplementVente.doFindIncidentDetaille_ListProcedureVente(beanSearch) ;
				setObjectValueModel(LIST_INCIDENT_DET_VENTE, list_Incident_det_vente);
				setObjectValueModel(LIST_INCIDENT_DET_VENTE_ORIGINE,   ProcessUtil.cloneList(list_Incident_det_vente));
				setObjectValueModel(FORM_BEAN, beanSearch);*/
				
			}
			
			
			if (bs.getFct_id().equals("2"))
				return getViewConsult(FORM_VIEW);
			
			if (bs.getFct_id().equals("3"))
				return getViewUpdate(FORM_EDIT_VIEW);
			
			if (bs.getFct_id().equals("4"))
				return getViewDelete(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Confirmer))
				return getViewConfirm(FORM_VIEW);
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}
	
	
public static ModelAndView doActualiser_GRID( ) throws Exception{
		
		try {
			
			 
			List listOData=(List) getObjectValueModel(LIST_EDITABLE_COMPLEMNT_VENTE);
			JsonObject data = new JsonObject();
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		 
			
			
			for (int i = 0; i < listOData.size(); i++) {
				DetComplementBean  newBean= (DetComplementBean) listOData.get(i);
				
				
				data.addProperty("erreur"+newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),"");
	            TarificationBean  ss  = newBean.getPk().getDetv().getTarif();
				 
		    		if( newBean.getQuantite_ajoute() == null  ||  newBean.getQuantite_ajoute() ==0) continue;
		    		
		    		if( newBean.getQuantite_ajoute() < 0 ) {
		    			String err=" Il existe un ou plusieurs quantité(s) inférieur a zéro ";
		    			data.addProperty("erreur"+newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),"0");
		    			continue;
		    			
		    		}
		    	 
		    		double priUnitVENTE=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
		    		double qte=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getQuantite_ajoute());
		    		
		    		newBean.setQuantite_ajoute(qte);
		    		
		    		double montant_ht_Retour=ProcessNumber.PRODUIT(priUnitVENTE, qte);
		    		newBean.setMontant_ht_complement(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_Retour));
		    		
		    		Double montant_tva_retour=ProcessNumber.Pourcentage(montant_ht_Retour, ss.getTvaBean().getTva_value());
		    		montant_tva_retour=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_retour);
	    		           newBean.setMontant_tva_complement(montant_tva_retour);
		    		Double number=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_Retour);          
		    		String Elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(number)  ;     
		    		data.addProperty(newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),Elm);
		    	 
		     	 
			}
			  getResponse().setContentType(JSON_CONTENT_TYPE);
			  getResponse().getWriter().print(data.toString());
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(ComplementVenteBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceComplementVente.doFetchDatafromServer(searchBean);
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
	
	
	public ModelAndView doAddData(ComplementVenteBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceComplementVente.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_EDIT_VIEW);
		}
	public ModelAndView doUpdateData(ComplementVenteBean beanUpBean) {	 
		 	try {
		 serviceComplementVente.doUpdateRowData(beanUpBean); 
		 update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	
	
	public ModelAndView doConfirmStockData(ComplementVenteBean beanUpBean) {	 
	 	try {
	 serviceComplementVente.doConfirmStockRowData(beanUpBean); 
	 remove_row_from_list(LIST_DATA); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
	
	
	
	public ModelAndView doDeleteData(ComplementVenteBean beanDelBean) {
	    try {
	     serviceComplementVente.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }

}
