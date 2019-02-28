package ERP.Process.Commerciale.Vente.RetourVente.web;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.service.Reception_achatService;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Article.template.ArticleTemplate;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Fournisseur.template.FournisseurTemplate;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Source_incidentBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.service.Nature_incident_mvt_retourService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.DocumentLot.template.DocumentLotTemplate;
import ERP.Process.Commerciale.Stock.MouvementStock.model.Incident_mvt_serieBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.template.TarificationPrtvArticleTemplate;
import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Client.template.ClientTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.dao.ProcedureVenteDAO;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.RetourVente.dao.RetourVenteDAO;
import ERP.Process.Commerciale.Vente.RetourVente.model.DetRetourVenteBean;
 
import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
import ERP.Process.Commerciale.Vente.RetourVente.service.RetourVenteService;
import ERP.Process.Commerciale.Vente.RetourVente.template.RetourVenteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
public class RetourVenteActionManager extends RetourVenteTemplate {
 
	private static final long serialVersionUID = -628021190129439223L;
	private RetourVenteService  serviceRetourVente;
	@Autowired
	public void setServiceRetourVente(RetourVenteService serviceRetourVente) {
	    this.serviceRetourVente = serviceRetourVente;
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
	
	
	private Reception_achatService serviceReception_achat;
	@Autowired
	public void setServiceReception_achat(
			Reception_achatService serviceReception_achat) {
		this.serviceReception_achat = serviceReception_achat;
	}
	
	
	private ClientDAO daoClient;
	@Autowired
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}
	@Autowired
	FournisseurService     serviceFournisseur;
	
	
	private TVAService   serviceTVA;
	@Autowired
	public void setServiceTva(TVAService serviceTVA) {
		this.serviceTVA = serviceTVA;
	}
	 
	public    ModelAndView doInitServletAction() {

	
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		try {
			// setObjectValueModel(QTE_STOCK, new Double(0));
			 setObjectValueModel( FORM_BEAN , new RetourVenteBean() );
			 setObjectValueModel( SEARCH_BEAN , new RetourVenteBean() );
			 doLoadingLibelleOtherSModule(ProcedureVenteTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(ClientTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(RetourVenteTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(Reception_achatTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(FournisseurTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(DocumentLotTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(TarificationPrtvArticleTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(ArticleTemplate.ID_SOUS_MODULE);
			 
			 Entite_etat_commercialeBean beanSearchs = new Entite_etat_commercialeBean();
			 beanSearchs.setCode_entite("retVente");
			 setObjectValueModel("list_ret_vente",serviceEntite_etat_commerciale.dofetchDatafromServer(beanSearchs));
				
				
			 setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			 List list_client_d= daoClient.doFindListClient(ClientBean.class.newInstance());
			 setObjectValueModel(LIST_CLIENT_VENTE, list_client_d);
			 
			 setObjectValueModel( LIST_CAUSE_RETOUR ,  serviceCause_ret_vente.doFetchDatafromServer(Nature_incident_mvt_retourBean.class.newInstance()) );
			 setObjectValueModel( LIST_SRC_CAUSE_RETOUR ,  serviceCause_ret_vente.doFindListSourceIncident(Source_incidentBean.class.newInstance()) );
			 
			 
			 
			 Entite_etat_commercialeBean beanSn = new Entite_etat_commercialeBean();
			 beanSn.setCode_entite("retVente");
			 setObjectValueModel(LIST_SENS_STOCK,serviceEntite_etat_commerciale.dofetchDatafromServer(beanSn));
			 
			 
			 List listfournisseur= serviceFournisseur.dofetchDatafromServer(FournisseurBean.class.newInstance());
			 setObjectValueModel(LIST_FOURNISSEUR_R_VENTE , listfournisseur);
			 
			 
			 List list_des_tva= serviceTVA.doFetchDatafromServer(TVABean.class.newInstance());
			 setObjectValueModel(LIST_DES_TVA, list_des_tva);
			 
			 /*doLoadingLibelleOtherSModule(CommandeclientTemplate.ID_SOUS_MODULE);
			 setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+ID_SOUS_MODULE));
			 setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+ID_SOUS_MODULE)) ;
		    
			
			 List    list_Tarification_vente  =   daoTarification.doFindDateMaxTarificationVente(TarificationBean.class.newInstance());
			 HashMap mapTarification_vente    =   serviceTarification.doTraiTerListTarif_vente(list_Tarification_vente);
			 HashMap mapTarific_vente         =   getHashMap_Key_List_FromList(list_Tarification_vente, "groupe.type_trf_id");
			
			 
			 setObjectValueModel(MAP_TARIFICATION,  mapTarification_vente);
			 setObjectValueModel(MAP_LIST_TARIFICA, mapTarific_vente);
			 
			 setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			 List list_client_d= daoClient.doFindListClient(ClientBean.class.newInstance());
			 setObjectValueModel(LIST_CLIENT_VENTE, list_client_d);
			 
			 HashMap  mapclientBen = getHashMap_code_bean(list_client_d, "clt_id");
			 setObjectValueModel(MAP_CLIENT_BEN, mapclientBen);
			
	 
			List  <DetProcedureVenteBean>listGridEditable_VENTE=  new  ArrayList<DetProcedureVenteBean>();
			setObjectValueModel(LIST_EDITABLE_VENTE, listGridEditable_VENTE);
			bs.setSousmod_libelle_title(bs.getSousmod_libelle());*/
			
			

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
				
			} else if(bs.getFct_id().equals(Fn_Consulter) && bs.getSousmod_id().equals("230") ){ 
				
			 
				 setObjectValueModel( SEARCH_BEAN , new DetRetourVenteBean() );
				return getViewFilterAjaxAffirmer(FILTER_VIEW_AFFIRMER);
				
			} else if(bs.getFct_id().equals(Fn_Contremander)){   
				bs.setSousmod_libelle_title((String)getObjectValueModel("cmd_frs"));
				setObjectValueModel(SEARCH_BEAN, new ProcedureVenteBean());
				return getViewFilterAjax(FILTER_VIEW);
				
			} else {
				
				 setObjectValueModel( FORM_BEAN , new RetourVenteBean() );
				 setObjectValueModel( SEARCH_BEAN , new RetourVenteBean() );
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
	
	public    ModelAndView doGetRowBeanRetour() {

		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			RetourVenteBean  retourVenteBean = new RetourVenteBean();
			removeObjectModel(FORM_BEAN);
			
			if (bs.getFct_id().equals(Fn_Créer)){
				 
				 
				setObjectValueModel(MAP_FIELD_BEAN, ProcedureVenteTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_VENTE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_VENTE);
				setObjectValueModel(NAME_GRID_G, NAME_GRID_VENTE);
				
				ProcedureVenteBean rowBean = (ProcedureVenteBean) getIndexFromDataGrid_v1(LIST_DATA_VENTE);
				retourVenteBean.setVente(rowBean);
				List<DetProcedureVenteBean> list_det_vente = daoProcedureVente.doFindDetaille_ListProcedureVente(rowBean);
				
				List<DetRetourVenteBean> list_editable_RetourVente =  new ArrayList<DetRetourVenteBean>();
				
				for (int i = 0; i < list_det_vente.size(); i++) {
					DetProcedureVenteBean detProce=list_det_vente.get(i);
					DetRetourVenteBean newBean= new DetRetourVenteBean();
					newBean.getPk().setDetv(detProce);
					list_editable_RetourVente.add(newBean);
				}
				setObjectValueModel(LIST_EDITABLE_RETOUR_VENTE, list_editable_RetourVente);
				setObjectValueModel(LIST_EDITABLE_RETOUR_VENTE_ORIGINE, ProcessUtil.cloneList(list_editable_RetourVente) );
				setObjectValueModel(FORM_BEAN, retourVenteBean);
				return getViewAdd(FORM_EDIT_VIEW);
			}else{
				RetourVenteBean beanSearch = (RetourVenteBean) getIndexFromDataGrid_v1(LIST_DATA);
				List<DetRetourVenteBean> list_editable_RetourVente =serviceRetourVente.doFetchDetaillefromServer(beanSearch);
				setObjectValueModel(LIST_EDITABLE_RETOUR_VENTE, list_editable_RetourVente);
				setObjectValueModel(LIST_EDITABLE_RETOUR_VENTE_ORIGINE, ProcessUtil.cloneList(list_editable_RetourVente) );
				
//				List<Incident_det_retour_venteBean> list_Incident_det_vente = daoRetourVente.doFindIncidentDetaille_ListProcedureVente(beanSearch) ;
//				setObjectValueModel(LIST_INCIDENT_DET_VENTE, list_Incident_det_vente);
//				setObjectValueModel(LIST_INCIDENT_DET_VENTE_ORIGINE, list_Incident_det_vente);
				setObjectValueModel(FORM_BEAN, beanSearch);
				
			}
			
			
			if (bs.getFct_id().equals("2"))
				return getViewConsult(FORM_VIEW);
			
			if (bs.getFct_id().equals("3")   )
				return getViewUpdate(FORM_EDIT_VIEW);
			
			if (  bs.getFct_id().equals(Fn_Affirmer) )
				return getViewAffirmer(FORM_EDIT_VIEW);
			
			
			
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
			
			 
			List listOData=(List) getObjectValueModel(LIST_EDITABLE_RETOUR_VENTE);
			JsonObject data = new JsonObject();
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			RetourVenteBean  retBean=(RetourVenteBean) getObjectValueModel(FORM_BEAN); 
			
			
			for (int i = 0; i < listOData.size(); i++) {
				DetRetourVenteBean  newBean= (DetRetourVenteBean) listOData.get(i);
				
				
				data.addProperty("erreur"+newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),"");
	            TarificationBean  ss  = newBean.getPk().getDetv().getTarif();
				 
		    		if( newBean.getQuantite_retourne() == null  ||  newBean.getQuantite_retourne() ==0) continue;
		    		
		    		if( newBean.getQuantite_retourne() < 0 ) {
		    			String err=" Il existe un ou plusieurs quantité(s) inférieur a zéro ";
		    			data.addProperty("erreur"+newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre(),"0");
		    			continue;
		    			
		    		}
		    	 
		    		double priUnitVENTE=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
		    		double qte=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getQuantite_retourne());
		    		
		    		newBean.setQuantite_retourne(qte);
		    		
		    		double montant_ht_Retour=ProcessNumber.PRODUIT(priUnitVENTE, qte);
		    		newBean.setMontant_ht_retour(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_Retour));
		    		
		    		Double montant_tva_retour=ProcessNumber.Pourcentage(montant_ht_Retour, ss.getTvaBean().getTva_value());
		    		montant_tva_retour=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_retour);
	    		           newBean.setMontant_tva_retour(montant_tva_retour);
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
	public ModelAndView doFetchData(RetourVenteBean searchBean)throws Throwable {
			try {
				  BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				  if(bs.getFct_id().equals(Fn_Affirmer)   ){
						searchBean.setCondition_etat_retour("  AND  bean.modeBean.fct_id  in ('"+Fn_Confirmer+"')   ");
					}
				  List listDataSrv = serviceRetourVente.doFetchDatafromServer(searchBean);
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
	
	
	public static  ModelAndView doPrintPDF_Affirmer() {
		 
		List   lisData                         =    (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G)) ;
		
	  String [][] Mapfield_retourVenteToAchatBean  = new String[][]{
			{ "recep.frsBean.frsref", "30" },{ "recep.achat_id", "40" },
			{ "pk.detv.pk.fkcode_barre.designation_libelle", "55" },
			{ "incident.pk.serieBean.quantite_init", "15" },
			{ "quantite_retourne", "15" },{ "incident.pk.serieBean.tarif.tarif_unit_article", "30" },{ "d_recep.montant_ht_achat", "30" },
			{ "cause.nature_incident_lib", "30" },{ "sens.data_libelle", "15" }  };
		
		String [][]    mapFieldBean            =     Mapfield_retourVenteToAchatBean ;
		
		 
		
		GeneratePdf  genpdf= new GeneratePdf();
		try {
		 
			
			    File file = new File(getRequest().getRealPath("/")+"/temp/"+(String)getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
		        FileOutputStream fs = new FileOutputStream(file);
		        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
		        String [][]    map_critere_de_recherche=    (String[][]) getObjectValueModel(MAP_CRITERE_DE_RECHERCHE) ;
		        Object searchBean=getObjectValueModel(SEARCH_BEAN);
		 
		   try {
			   Document document=GeneratePdf.doGenerateDocumentFormat();
		        PdfPTable table = new PdfPTable(mapFieldBean.length);
		        
		        String    title =(String)getObjectValueModel(LIST_PDF_EXCEL) ;
		        if(title==null)
		             title =(String)getObjectValueModel("list-"+bSession.getSousmod_libelle()) ;
		        if(title==null)
		           title= (String)getObjectValueModel("Liste_ret_br") ; 
		        if(title==null)title="";
		        
		        genpdf.doWriteHeaderDocument_PDF(document,fs,mapFieldBean,bSession);
			    
			    genpdf.doWriteTitle_Table(document,title);
			    doWrite_Header_Table_Retour(table,mapFieldBean);
			    doWrite_Data(lisData,table,mapFieldBean);
			  
		        document.add(table);
		        doWrite_Tva_Total_Piece(    lisData,  document) ;
		        document.close();
		        
		   } catch (Exception e) {
				throw  e;
			}
			
			
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write((String) getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
		} catch (Throwable e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

}
	
	
	  public static void doWrite_Tva_Total_Piece(List   lisData,Document document) throws Exception {
			
			try {
				 
	           
	           PdfPTable table_des_tva = new PdfPTable(100);
	           table_des_tva.setWidthPercentage(96);
	           
	           
	           
	           
	           /******************************************* Entete  tableau des tva *************************************/
	           PdfPCell cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
	           cell.setColspan(100);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	           cell.setBorder(cell.TOP);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase("Taux",GeneratePdf.FONT_12_bold));
	           cell.setColspan(10);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase("Base",GeneratePdf.FONT_12_bold));
	           cell.setColspan(21);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase("Montant",GeneratePdf.FONT_12_bold));
	           cell.setColspan(23);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
	           cell.setColspan(49);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	           cell.setBorder(cell.NO_BORDER);
	           table_des_tva.addCell(cell);
	           /********************************************************************************************************/
	           
	           
	           
	           
	           HashMap  mapTvaImpression= (HashMap) getObjectValueModel("mapTvaImpression");
	           
	           Set sss_mapTvaImpression=mapTvaImpression.keySet();
	           
	           for (Iterator iter = sss_mapTvaImpression.iterator(); iter.hasNext();) {
				
	        	   String key = (String ) iter.next();
				
				   String elmme=  (String) mapTvaImpression.get(key);
				   String[] ligne=   elmme.split("£");
				
				   cell = new PdfPCell(new Phrase(key,GeneratePdf.FONT_12_bold));
		           cell.setColspan(10);
		           cell.setFixedHeight(20f);
		           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
		           table_des_tva.addCell(cell);
		           
		           cell = new PdfPCell(new Phrase(ligne[0],GeneratePdf.FONT_12_bold));
		           cell.setColspan(21);
		           cell.setFixedHeight(20f);
		           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
		           table_des_tva.addCell(cell);
		           
		           cell = new PdfPCell(new Phrase(ligne[1],GeneratePdf.FONT_12_bold));
		           cell.setColspan(23);
		           cell.setFixedHeight(20f);
		           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
		           table_des_tva.addCell(cell);
		           
		           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
		           cell.setColspan(49);
		           cell.setFixedHeight(20f);
		           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		           cell.setBorder(cell.NO_BORDER);
		           table_des_tva.addCell(cell);
			   } 
	           
	           JSONArray  list_total  =  (JSONArray) getObjectValueModel("list_total");
	           
	           
	           PdfPTable table_total = new PdfPTable(100);
	           table_total.setWidthPercentage(96);
	           
	           /****************************************************************************************************************/
	           
	            
	           int  init=20;
	           int size=mapTvaImpression.size();
	           int space=size*20;
	           space=space+init;
	           space=space*-1;
	           
	           /****************************************************************************************************************/
	           
	           
	           table_des_tva.setSpacingAfter(Float.valueOf(String.valueOf(space)));
	           for (int i = 0; i < list_total.length(); ++i) {
	        	   
	        	   if(i==0 || i==1 ||  i==5 || i==6  ) continue;
	        	    JSONObject rec = list_total.getJSONObject(i);
	        	    String titre = rec.getString("value1"); 
	        	    String value = rec.getString("value2");
	        	    
	        	    cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_bold));
	                cell.setColspan(55);
	                cell.setFixedHeight(20f);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setBorder(cell.NO_BORDER);
	                table_total.addCell(cell);
	                
	                
	                cell = new PdfPCell(new Phrase(titre,GeneratePdf.FONT_12_bold));
	                cell.setColspan(18);
	                cell.setFixedHeight(20f);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	                table_total.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(value ,GeneratePdf.FONT_12_normal));
	                cell.setColspan(27);
	                cell.setFixedHeight(20f);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	                table_total.addCell(cell);
	        	}
	           document.add(table_des_tva);
	           document.add(table_total);
	          
			} catch (Exception e) {
			}
		
		}
	  
	  
	public static void doWrite_Header_Table_Retour(PdfPTable table, String[][] mapFieldBean) throws Exception {
		
	    int PaddingBottom=5;
        int[] columnWidths = new int[mapFieldBean.length] ;
        for(int i = 0; i < mapFieldBean.length; i++){
      	columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
		}
        table.setWidthPercentage(96);
        table.setWidths(columnWidths);
        
          for(int i = 0; i < mapFieldBean.length; i++){
    	     
    	     String titrehead="";
    	   
    	     if(mapFieldBean[i][0].indexOf(".")>0){
				 final String[] lesColunmooo = mapFieldBean[i][0].split("\\.");
				 String getel=lesColunmooo[lesColunmooo.length-1];
				 titrehead=(String) getObjectValueModel(getel);
    	     }else{
    	    	 titrehead=(String) getObjectValueModel(mapFieldBean[i][0]);
    	     } 
    	     
    	     if(mapFieldBean[i][0].startsWith("modeBean"))  titrehead=(String) getObjectValueModel("_mode");
    	     
    	  
    	   
    	     
    	     PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":titrehead  ,GeneratePdf.SMALLBOLD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setPaddingBottom(PaddingBottom);
		    cell.setBackgroundColor(GeneratePdf.colorHeader);
		    cell.setBorderWidth(1f);
		    table.addCell(cell);
		}
	
}
	 public static void doWrite_Data (List lisData, PdfPTable table,
				String[][] mapFieldBean) throws Exception, SecurityException {
			  for(int i=0; i < lisData.size(); i++ ){
				   Object bean = (Object) lisData.get(i);
				    
				 for(int j = 0; j < mapFieldBean.length; j++){
					 
					        PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.REDFONT));
					 
					        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
					        
					        if(j==mapFieldBean.length-4 || j==mapFieldBean.length-3){
			    	    	  Double elm=Double.valueOf(String.valueOf(obj));
			  	        	 cell = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(elm) ,GeneratePdf.REDFONT)); 
			  	        	  cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			    	         }else{
			    	        	  cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.REDFONT));
			    	        	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	          } 
					  
					      
					      
					        cell.setPaddingBottom(5);
					        cell.setBorderWidth(0.1f);
					        cell.setBorderColor(WebColors.getRGBColor("#787878"));
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        table.addCell(cell);
						   
					    }   
				   
	       }
			
		}
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData_apres_affirmer(DetRetourVenteBean searchBean)throws Throwable {
			try {
			      List<DetRetourVenteBean> listDataSrv=serviceRetourVente.doFetchDetaillefromServer(searchBean);
			      String chaine="";
			      HashMap  map_debu=  new HashMap();
			      for (int i = 0; i < listDataSrv.size(); i++) {
			    	  DetRetourVenteBean bVenteBean =listDataSrv.get(i);
			    	  String key_re_vente =
			    		  bVenteBean.getPk().getR_vente().getRet_vente_id()+"§"+  
			    		  bVenteBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"§"+  
			    		  bVenteBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+"§"+
			    		  bVenteBean.getPk().getR_vente().getVente().getDepot().getDepot_id()+"§"+
			    		  bVenteBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"§"+
			    		  bVenteBean.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id();
			    	  if(map_debu.get(key_re_vente)==null){
			    		  map_debu.put(key_re_vente, bVenteBean);
			    		  chaine+="'"+bVenteBean.getPk().getR_vente().getRet_vente_id()+"',";
					  }
			    	  
			    	  
				  }chaine=chaine.substring(0, chaine.length()-1);
				  List<Incident_mvt_serieBean> List_des_retour=serviceRetourVente.doFindList_detaille_Incident_serie(chaine);
				  String chaineAchat="";
 				  
			      HashMap  map_serie=  new HashMap();
				  for (int i = 0; i < List_des_retour.size(); i++) {
					  Incident_mvt_serieBean beanInc =List_des_retour.get(i);
					  if(beanInc.getPk().getIncid_mvt().getNature_mvt_id().equals("r-ve")){
					      String key_serie =
						  beanInc.getPk().getMvt_incident_id()+"§"+  
						  beanInc.getPk().getSerieBean().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"§"+  
						  beanInc.getPk().getSerieBean().getFkCode_barre().getPk().getCode_barre()+"§"+
						  beanInc.getPk().getSerieBean().getPk().getDepot().getDepot_id()+"§"+
						  beanInc.getPk().getSerieBean().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"§"+
						  beanInc.getPk().getSerieBean().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id(); 
					      DetRetourVenteBean   det_re=  (DetRetourVenteBean) map_debu.get(key_serie);
					      if(det_re==null ) continue;
					      det_re.setIncident(beanInc);
					     if(map_serie.get(key_serie)==null){
						   map_serie.put(key_serie, beanInc);
						   chaineAchat+="'"+beanInc.getPk().getSerieBean().getMvt_com_id()+"',";
						 }
				       }
				  }
				  chaineAchat=chaineAchat.substring(0, chaineAchat.length()-1);
				 
				  Reception_achatBean beanSearch  = new Reception_achatBean();
				  beanSearch.setCondition_etat_achat("    AND   bean.achat_id  in ("+chaineAchat+") ");
				  List<Reception_achatBean> listdesrecep=serviceReception_achat.doFetchDatafromServer(beanSearch);
				  HashMap  mapfour= new HashMap();
				  for (int i = 0; i < listdesrecep.size(); i++) {
					  Reception_achatBean beanIncS =listdesrecep.get(i);
					  if(mapfour.get(beanIncS.getAchat_id())==null){
						  mapfour.put(beanIncS.getAchat_id(), beanIncS);
					   }
				  }
				  
				  List<DetRetourVenteBean> listDataFinal=  new ArrayList<DetRetourVenteBean>();
				  Set ssd= map_debu.keySet();
				  for (Iterator ite = ssd.iterator(); ite.hasNext();) {
					  String object = (String) ite.next();
					  DetRetourVenteBean   det_re=(DetRetourVenteBean) map_debu.get(object);
					  Reception_achatBean beanIncS= (Reception_achatBean) mapfour.get(det_re.getIncident().getPk().getSerieBean().getMvt_com_id());
					  
					  if(beanIncS==null)continue;
					  
					  
					  if (!StringUtils.isEmpty(searchBean.getRecep().getFrsBean().getFrs_id())){
						if(!beanIncS.getFrsBean().getFrs_id().equals(searchBean.getRecep().getFrsBean().getFrs_id()))  
							continue;
					   }
							 
					  det_re.setRecep(beanIncS);
					  
					  
					    double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(det_re.getIncident().getPk().getSerieBean().getTarif().getTarif_unit_article());
			    		double qte=ProcessFormatNbr.FormatDouble_Troischiffre(det_re.getQuantite_retourne());
			    		
			    		double montant_ht_achat=ProcessNumber.PRODUIT(priUnitachat, qte);
			    		det_re.getD_recep().setMontant_ht_achat(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat));
			    		
			    		
			    		Double montant_tva_achat=ProcessNumber.Pourcentage(montant_ht_achat, det_re.getIncident().getPk().getSerieBean().getTarif().getTvaBean().getTva_value());
		    		           montant_tva_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_achat);
		    		           det_re.getD_recep().setMontant_tva_achat(montant_tva_achat);
					  
					  
					  
					  listDataFinal.add(det_re);
				   }
				  setObjectValueModel(SEARCH_BEAN, searchBean);
				  AjaxDataTablesUtility.doInitJQueryGrid(listDataFinal);
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculer_Total_affirmation(   ) throws Exception {
		
		try {
			 
			 
			List<DetRetourVenteBean>  List_detaille=  (List<DetRetourVenteBean>) getObjectValueModel(LIST_DATA);
 
			Double avance  = new Double(0);
			Double remise  =new Double(0);
			
			
			double tot_ht=0;
			double tot_tva=0;
			Double tot_qte=new Double(0);
			
			HashMap   mapTva = new HashMap();
			for (int i = 0; i < List_detaille.size(); i++) {
				DetRetourVenteBean  bean=List_detaille.get(i);
				
				
				tot_ht=ProcessNumber.addition(tot_ht, bean.getD_recep().getMontant_ht_achat());
				tot_tva=ProcessNumber.addition(tot_tva, bean.getD_recep().getMontant_tva_achat());
				tot_qte=ProcessNumber.addition(tot_qte, bean.getQuantite_retourne());
				
				List  listTva = (List) mapTva.get(bean.getPk().getDetv().getTarif().getTvaBean().getTva_libelle());
				if(listTva==null)listTva= new ArrayList();
				listTva.add(bean);
				mapTva.put(bean.getPk().getDetv().getTarif().getTvaBean().getTva_libelle(), listTva);
				
			}
			
			 ProcedureVenteBean    beanTotal = new ProcedureVenteBean();
			 
			
			 beanTotal.setVente_remise(remise);
			 beanTotal.setAvance_montant_vente(avance)  ; 
			     
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
					 		DetRetourVenteBean  bean=(DetRetourVenteBean) listTva.get(i);
							le_Ht=ProcessNumber.addition(le_Ht, bean.getD_recep().getMontant_ht_achat());
							le_Ht_brute=ProcessNumber.addition(le_Ht_brute, bean.getD_recep().getMontant_ht_achat());
							leTva=ProcessNumber.addition(leTva, bean.getD_recep().getMontant_tva_achat());
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
			 element.put("td1","3");
			 element.put("value1","Brut.H.T");
			 element.put("td2","4");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(le_Ht_brute));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","3");
			 element.put("value1","Remise");
			 element.put("td2","4");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(remise));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","3");
			 element.put("value1","Net.H.T");
			 element.put("td2","4");
			 double ht_apres_remise=  ProcessNumber.SOUSTRACTION(le_Ht_brute, remise)  ;
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(ht_apres_remise));
			 list_total.put(element);
			 beanTotal.setVente_mnt_ht(ProcessFormatNbr.FormatDouble_Troischiffre(ht_apres_remise));
			 
			
			 
			 
			 
			 beanTotal.setVente_mnt_tva(ProcessFormatNbr.FormatDouble_Troischiffre(total_leTva));
			 element = new JSONObject();
			 element.put("td1","3");
			 element.put("value1","Total TVA");
			 element.put("td2","4");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_leTva));
			 list_total.put(element);
			 
			 
			 double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
			 beanTotal.setVente_mnt_total(ProcessFormatNbr.FormatDouble_Troischiffre(total_mnt_gen));
			 
			 element = new JSONObject();
			 element.put("td1","3");
			 element.put("value1","Total TTC");
			 element.put("td2","4");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mnt_gen));
			 list_total.put(element);
			 
			 element = new JSONObject();
			 element.put("td1","3");
			 element.put("value1","Avance");
			 element.put("td2","4");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(avance));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","3");
			 element.put("value1","Net à payer");
			 element.put("td2","4");
			 double net_a_payer=ProcessNumber.SOUSTRACTION(total_mnt_gen, avance);
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(net_a_payer));
			 list_total.put(element);
			 
			 
			 
			 json.put("list_total", list_total);
			 setObjectValueModel("list_total", list_total);
			 setObjectValueModel(BEAN_TOTAL, beanTotal);
			 getResponse().setContentType(JSON_CONTENT_TYPE);      
			 getResponse().getWriter().write(json.toString());
			 
			  
		    
		    
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	
	 }
	
	
	public ModelAndView doAddData(RetourVenteBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceRetourVente.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_EDIT_VIEW);
		}
	public ModelAndView doUpdateData(RetourVenteBean beanUpBean) {	 
		 	try {
		 serviceRetourVente.doUpdateRowData(beanUpBean); 
		 update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	
	
	public ModelAndView doConfirmStockData(RetourVenteBean beanUpBean) {	 
	 	try {
	        serviceRetourVente.doConfirmStockRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	        throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
	
	
	
	public ModelAndView doDeleteData(RetourVenteBean beanDelBean) {
	    try {
	     serviceRetourVente.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
