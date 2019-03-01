package ERP.Process.Commerciale.Tarification.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Article.dao.ArticleDAO;
import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.service.ArticleService;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.service.TarificationService;
import ERP.Process.Commerciale.Tarification.template.TarificationTemplate;
import ERP.Process.Commerciale.TarificationLot.dao.TarificationLotDAO;
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.dao.TarificationPrtvArticleDAO;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.service.TarificationPrtvArticleService;
import ERP.Process.Commerciale.Type_tarification.dao.Type_tarificationDAO;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Type_tarification.service.Type_tarificationService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.service.DeviseService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel.WriteExcel;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class TarificationActionManager extends TarificationTemplate {
	 
 
	private static final long serialVersionUID = -5721791443285527403L;

	private TarificationService	serviceTarification;
	@Autowired
	public void setServiceTarification(TarificationService serviceTarification) {
		this.serviceTarification = serviceTarification;
	}	
		

	@Autowired  public   ArticleService			  serviceArticle;
	@Autowired  public  Code_barreService         serviceCode_barre;
	@Autowired  public  Type_tarificationService  serviceType_tarification;
	@Autowired  public  Type_tarificationDAO      daoType_tarification;
	@Autowired  public  TVAService                serviceTVA ;
	@Autowired  public  TarificationDAO           dAOTarification ;
	private TarificationPrtvArticleDAO daoTarificationPrtvArticle;
	@Autowired
	public void setDaoTarificationPrtvArticle(TarificationPrtvArticleDAO daoTarificationPrtvArticle) {
		this.daoTarificationPrtvArticle = daoTarificationPrtvArticle;
	}
	@Autowired  public  ArticleDAO                dAOArticle ;
	
	private  DocumentLotService    serviceDocumentLot;
	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
	    this.serviceDocumentLot = serviceDocumentLot;
	} 
	
	private TarificationLotDAO daoTarificationLot;
	@Autowired
	public void setDaoTarificationLot(TarificationLotDAO daoTarificationLot) {
		this.daoTarificationLot = daoTarificationLot;
	}
	@Autowired
	private DeviseService     serviceDevise;
	
	@Autowired Entite_etat_commercialeService   serviceEntite_etat_commerciale;

	public ModelAndView doInitServletAction() {

	

		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			setObjectValueModel("listmaxTarification", new ArrayList());
			setObjectValueModel("listmaxTarific", new ArrayList());
			List<ArticleBean> list_article_tarification = serviceArticle.dofetchDatafromServer(new ArticleBean());
			List<Type_tarificationBean> list_tyList = serviceType_tarification.doFetchDatafromServer(new Type_tarificationBean());
			setObjectValueModel(LIST_TYLIST, list_tyList);
			List<TVABean> list_tvList = serviceTVA.doFetchDatafromServer(new TVABean());
			HashMap mapTva =  ProcessUtil.getHashMap_code_bean(list_tvList, "tva_id");
			setObjectValueModel("mapTva", mapTva);
			setObjectValueModel(LIST_ARTICLE_TARIFICATION, list_article_tarification);
			Entite_etat_commercialeBean beanMode_prix = new Entite_etat_commercialeBean();
			beanMode_prix.setCode_entite("mode_prix_vente");
			setObjectValueModel("list_mode_prix",serviceEntite_etat_commerciale.dofetchDatafromServer(beanMode_prix));
			
			Entite_etat_commercialeBean beanMode_calcul_prix = new Entite_etat_commercialeBean();
			beanMode_calcul_prix.setCode_entite("mode_cal_puv");
			setObjectValueModel("list_mode_cal_puv",serviceEntite_etat_commerciale.dofetchDatafromServer(beanMode_calcul_prix));
			setObjectValueModel("list_devise", serviceDevise.doFetchDatafromServer(new DeviseBean()));
			
			
			setObjectValueModel(LIST_TVLIST, list_tvList);
			
		 
			
			if (bs.getFct_id().equals(Fn_Realiser) ) {
				return getViewAdd(FORM_VIEW);
				
			}else if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")) {
				return getViewAdd(FILTER_TO_ADD_TARIFICATION);
				
			} else {
				return getViewFilterAjax(FILTER_VIEW);
			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}
	}
	
	public static  ModelAndView doResetForm() {
		setObjectValueModel("listmaxTarification", new ArrayList());
		setObjectValueModel("listmaxTarific", new ArrayList());
		removeObjectModel(FORM_BEAN);
		return getViewAdd(FORM_VIEW);
	}
	
	
	
	public    ModelAndView doGetRowBeanTarif() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			TarificationBean rowBean = (TarificationBean) getIndexFromDataGrid_v1(LIST_DATA);
			
			if (bs.getFct_id().equals(Fn_Modifier) || bs.getFct_id().equals(Fn_Supprimer) ){
				String elmt=serviceTarification.doFetchTarif(rowBean.getTarif_vente_id());
				if(elmt.length()>0)
					throwNewException(" Cette Tarification est déjà Utilisée : <br>"+elmt);
			 }
			setObjectValueModel(FORM_BEAN, rowBean);
			setObjectValueModel("deviseData", rowBean);
			
			if (bs.getFct_id().equals(Fn_Consulter))
				return getViewConsult(FORM_VIEW);
			if (bs.getFct_id().equals(Fn_Modifier))
				return getViewUpdate(FORM_VIEW);
			if (bs.getFct_id().equals(Fn_Supprimer))
				return getViewDelete(FORM_VIEW);
			if (bs.getFct_id().equals(Fn_Confirmer))
				return getViewConfirm(FORM_VIEW);
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax(FILTER_VIEW);
		}
		return getHome();

	}
	
	public ModelAndView doConfirmData(TarificationBean beanUp) {	 
	 	try {
			 TarificationBean  beanUpBea  =	(TarificationBean) getObjectValueModel(ORIGINAL_FORM_BEAN);
			 TarificationBean  beanUpBean =(TarificationBean) ProcessUtil.cloneObject(beanUpBea) ;
			 serviceTarification.doConfirmRowData(beanUpBean); 
			 remove_row_from_list(LIST_DATA); 
			 removeObjectModel(FORM_BEAN);
			 throwNewException("ins01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
			
		@SuppressWarnings("unchecked")
		public ModelAndView doLoadSuivant(TarificationBean detailBean) throws Exception {
		
		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType(JSON_CONTENT_TYPE);
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			
			      
			  
				List listmaxTarification=dAOTarification.doFindListTarification(detailBean);
		 
				Code_barreBean searchBeanx=new Code_barreBean();
		        searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
			    searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
				 
				List listmaxTarn=serviceCode_barre.doFetchDatafromServerNew(searchBeanx);
				Code_barreBean  aBean=(Code_barreBean) listmaxTarn.get(0);
				setObjectValueModel(BEAN_CODEBARRE_ARTICL, aBean);
				
			
				
			 
			    JSONObject  jsonObj = new JSONObject();
			    HashMap maptyMap=new HashMap();
				if(listmaxTarification!=null  && listmaxTarification.size()>0) {
				  List listmaxTari=ProcessUtil.cloneList(listmaxTarification);
				  maptyMap=ProcessUtil.getHashMap_code_bean(listmaxTari, "pk.typ_trfBean.type_trf_id");	
				  TarificationBean bean_maxTarification=(TarificationBean) listmaxTarification.get(0);
				  jsonObj.put("datex", ProcessDate.getCurrentTimeStamp(bean_maxTarification.getDate_trf())  );
			      jsonObj.put("tvax",  bean_maxTarification.getTvaBean().getTva_id());
				  jsonObj.put("p_u_achat",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(0)  );
				  jsonObj.put("modex",    aBean.getPk().getAr_bean().getBean_mode_cal().getData_libelle());
				  jsonObj.put("mode_ids", aBean.getPk().getAr_bean().getBean_mode_cal().getData_id());
				  
				}else{
				 jsonObj.put("datex", "");
				 jsonObj.put("tvax","");
				 jsonObj.put("p_u_achat",""  );
				 jsonObj.put("modex", aBean.getPk().getAr_bean().getBean_mode_cal().getData_libelle());
				}
				
	      List  typTarif=(List) getObjectValueModel(LIST_TYLIST);
		  List listmaxTarific = new ArrayList();
		  for (int i = 0; i < typTarif.size(); i++) {
			  Type_tarificationBean tBean= (Type_tarificationBean) typTarif.get(i);
			  TarificationBean benTraif=(TarificationBean) maptyMap.get(String.valueOf(tBean.getType_trf_id()));
			  if(benTraif==null){
				  TarificationBean tBean2= new TarificationBean();
				  tBean2.setGroupe(tBean);
				  listmaxTarific.add(tBean2);
			  }else{
				  listmaxTarific.add(benTraif);
			  }
		  }
		  setObjectValueModel("listmaxTarification", listmaxTarification);
		  setObjectValueModel("listmaxTarific", listmaxTarific);
	      getResponse().getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			getResponse().setStatus(500);
			out.println(e.getMessage());
			out.close();
		}
		return null;
		
		}

		
		@SuppressWarnings("unchecked")
		public   ModelAndView doUpdateEditableTable() throws Exception{
		 
			   PrintWriter out = getResponse().getWriter();
			   getResponse().setContentType(HTML_CONTENT_TYPE);
			   ActionDataTablesManager  aTablesManager= new ActionDataTablesManager();
			   try {
				   String sValueId = getRequest().getParameter("sValueId") == null ? "" : getRequest().getParameter("sValueId");
				   if(!sValueId.equals("UIJ987654ù$$$")) 
					 aTablesManager.doTraiterColonne();
				   
				List listmaxTarific=   (List) getObjectValueModel("listmaxTarific");
				String prix_unit_achat = getRequest().getParameter("prix_unit_achat") == null ? "0" : getRequest().getParameter("prix_unit_achat");
				
				
			 
				 
			
				Code_barreBean  aBean= (Code_barreBean) getObjectValueModel(BEAN_CODEBARRE_ARTICL );
				
				for (int i = 0; i < listmaxTarific.size(); i++) {
					
					TarificationBean tBean =(TarificationBean) listmaxTarific.get(i);
					Double prix_u_achat=ProcessFormatNbr.ConvertStringToDouble(prix_unit_achat);
					
					if(aBean.getPk().getAr_bean().getBean_mode_cal().getData_id().equals("c")){
						
						
						Double doubval_coef=ProcessFormatNbr.FormatDouble_Troischiffre(tBean.getCoef_trf()==null?new Double(0):tBean.getCoef_trf());
						Double prix_unit_vente= ProcessNumber.PRODUIT(prix_u_achat,doubval_coef)  ;
						tBean.setTarif_unit_vente( ProcessFormatNbr.FormatDouble_Troischiffre(prix_unit_vente) );
						
						Double marge_vente =ProcessNumber.SOUSTRACTION(prix_unit_vente,prix_u_achat);
					           marge_vente =ProcessFormatNbr.FormatDouble_Troischiffre(marge_vente);
					           tBean.setMarge_vente(marge_vente);
					}
					
                    if(aBean.getPk().getAr_bean().getBean_mode_cal().getData_id().equals("m")){
                    	Double doubval_marge=ProcessFormatNbr.FormatDouble_Troischiffre(tBean.getMarge_vente()==null?new Double(0):tBean.getMarge_vente());
                    	Double prix_unit_vente= ProcessNumber.addition(prix_u_achat,doubval_marge)  ;
                    	tBean.setTarif_unit_vente( ProcessFormatNbr.FormatDouble_Troischiffre(prix_unit_vente) );
						
						Double doubval_coef=ProcessNumber.DIVISION(prix_unit_vente,prix_u_achat); 
			                   doubval_coef =ProcessFormatNbr.FormatDouble_Troischiffre(doubval_coef);
			                   tBean.setCoef_trf(doubval_coef);
					}
                    
                   
                    
                     if(aBean.getPk().getAr_bean().getBean_mode_cal().getData_id().equals("s")){
                    	 
                    	 Double prix_unit_vente=ProcessFormatNbr.FormatDouble_Troischiffre(tBean.getTarif_unit_vente()==null?new Double(0):tBean.getTarif_unit_vente());
 						  tBean.setTarif_unit_vente( prix_unit_vente );
 						 
 						Double marge_vente =ProcessNumber.SOUSTRACTION(prix_unit_vente,prix_u_achat);
 						        marge_vente =ProcessFormatNbr.FormatDouble_Troischiffre(marge_vente);
 						        tBean.setMarge_vente(marge_vente);
 						        
 						       Double doubval_coef=ProcessNumber.DIVISION(prix_unit_vente,prix_u_achat); 
 						              doubval_coef =ProcessFormatNbr.FormatDouble_Troischiffre(doubval_coef);
 						       tBean.setCoef_trf(doubval_coef);
 						
					}
					
					
					
				}
			   } catch (Exception e) {
				    getResponse().setStatus(500);
					out.println(e.getMessage());
					out.close();
				}
				return null ;
		}
		
	public ModelAndView doLoadAutocompleteList() throws ServletException, IOException, SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType("text/html");
		getResponse().setHeader("Cache-control", "no-cache, no-store");
		getResponse().setHeader("Pragma", "no-cache");
		getResponse().setHeader("Expires", "-1");
		String query = getRequest().getParameter("term");
		String typeSearch = getRequest().getParameter("typeSearch");
		String fcode = getRequest().getParameter("fieldcode");
		String flabel = getRequest().getParameter("fieldlabel");
		try {
			String ar_id = getRequest().getParameter("codeInput")==null?"":getRequest().getParameter("codeInput");
			Code_barreBean  cBean = new Code_barreBean();
			
			if(ar_id.equals("")) {
				cBean = new Code_barreBean();
			}else{
				cBean.getPk().getAr_bean().getPk_article().setAr_id(ar_id);
			}
			//cBean.setCondition_article_seulement_vente("  AND  pk.ar_bean.cathegorie.data_id in ('mar','syn')  ");
			
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
	        cBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
		    cBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
			List listData         = serviceCode_barre.doFetchDatafromServerNew(cBean);
			
			HashMap  mapArticle=ProcessUtil.getHashMap_code_bean(listData, "pk.code_barre");
			setObjectValueModel("mapArticle", mapArticle);
			
			if(  listData==null ||  listData.size()==0  )
				throwNewException("la liste demander est vide");
			Collection<JSONObject> items = ActionDataTablesManager.doRenderListJson(listData, query, typeSearch, fcode,flabel);
			out.println(items.toString());
			out.close();
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			out.println(e.getMessage());
			out.close();
		}
		return null;

	}
	
	
	
	public ModelAndView doLoadDepotFromLot(TarificationBean detailBean) throws  Exception {

			PrintWriter out = getResponse().getWriter();
			getResponse().setContentType("text/html");
			 
			String query 		 = getRequest().getParameter("term");
			String typeSearch 	 = getRequest().getParameter("typeSearch");
			String fcode		 = getRequest().getParameter("fieldcode");
			String flabel        = getRequest().getParameter("fieldlabel");
			
		try {
			String setNum_serie = getRequest().getParameter("codeInput")==null?"":getRequest().getParameter("codeInput");
			
			SerieArticletBean sBe = new SerieArticletBean();
			sBe.getPk().setNum_serie(setNum_serie);
			List  <SerieArticletBean> listLOt         = serviceDocumentLot.doFetchDatafromServer(sBe);
			List  <DepotStockageBean>listData         =  new ArrayList <DepotStockageBean>();
			for (int i = 0; i < listLOt.size(); i++) {
				SerieArticletBean sArticletBean =(SerieArticletBean) listLOt.get(i);
				listData.add(sArticletBean.getPk().getDepot());
			}
			if(  listData==null ||  listData.size()==0  )
				throwNewException("la liste demander est vide");
			Collection<JSONObject> items = doRenderList(listData, query, typeSearch, fcode,flabel);
			out.println(items.toString());
			out.close();
			
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			out.println(e.getMessage());
			out.close();
		}
		return null;

}
	
	
	public   Collection<JSONObject> doRenderList(List listData, String query, String typeSearch, String fcode,
			String flabel) throws IOException {

		Collection<JSONObject> items = new ArrayList<JSONObject>();
		 
		try {
			for (int i = 0; i < listData.size(); i++) {
				DepotStockageBean bean = (DepotStockageBean) listData.get(i);
				String elementChoiSi = "";
				JSONObject jsonObj = new JSONObject();
				if (typeSearch != null && typeSearch.equals("bylabel")) {
					
					    Object Viaelement=	 GenericWeb.getValueOject_with_name_field(bean, fcode);
						Viaelement = Viaelement != null ? Viaelement : "";
						jsonObj.put(fcode, Viaelement);
						
						Object Viaelem=	 GenericWeb.getValueOject_with_name_field(bean, flabel);
						Viaelem = Viaelem != null ? Viaelem : "";
						elementChoiSi = String.valueOf(Viaelem);
						jsonObj.put(flabel, elementChoiSi);
					

				}

				if (typeSearch != null && typeSearch.equals("byId")) {
					Object ViaelemeU=	 GenericWeb.getValueOject_with_name_field(bean, fcode);
					ViaelemeU = ViaelemeU != null ? ViaelemeU : "";
					elementChoiSi = String.valueOf(ViaelemeU);
					jsonObj.put(fcode, elementChoiSi);
					
					Object valueOfFieldlibo=	 GenericWeb.getValueOject_with_name_field(bean, flabel);
					valueOfFieldlibo = valueOfFieldlibo != null ? valueOfFieldlibo : "";
					 
					jsonObj.put(flabel, valueOfFieldlibo);
				}
				if (elementChoiSi.toLowerCase().startsWith(query.toLowerCase())) {
					items.add(jsonObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return items;
	}
	
	public ModelAndView doLoadCout(TarificationBean detailBean ) throws Exception {

		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType(HTML_CONTENT_TYPE);
		try {
			 
		 
			TarificationPrtvArticleBean beanSearch  = new TarificationPrtvArticleBean();
			beanSearch.setDate_prim_trf(BDateTime.getDateActuel());
			beanSearch.getGroupe().setGrp_prim_trf_id(Integer.parseInt(GROUPE_TARIF_ACHAT_STANDAR));
			beanSearch.setFkCode_barre(detailBean.getFkCode_barre());
			
			
			HashMap  mapArticle  =(HashMap) getObjectValueModel(  "mapArticle"  );
			Code_barreBean fkCode_barre=(Code_barreBean) mapArticle.get(detailBean.getFkCode_barre().getPk().getCode_barre());
			 
			 
			 
  			HashMap   mapresul =  daoTarificationPrtvArticle.doFindListTarificationPrtvArticle_ACHAT_DateMax(beanSearch);
			List list_TarificationPrtvArticle=	 (List) mapresul.get("list_TarificationPrtvArticle"  );
			TarificationPrtvArticleBean sss= new TarificationPrtvArticleBean();
			if(list_TarificationPrtvArticle!= null  && list_TarificationPrtvArticle.size()>0)
  			  sss=(TarificationPrtvArticleBean) list_TarificationPrtvArticle.get(0);
			 
				JsonObject data = new JsonObject();
				data.addProperty("codetarif",sss.getTarif_prim_id());
				setObjectValueModel("tarifAchat", sss.getTarif_unit_article());
			    data.addProperty("tarif_unit_achat"    ,  sss.getTarif_unit_article()==null?"" : ProcessFormatNbr.FormatDouble_To_String_Troischiffre(sss.getTarif_unit_article()));
			    data.addProperty("date_unit_achat"    , ProcessDate.getStringFormatDate( sss.getDate_prim_trf()));
			    data.addProperty("date_actu"    ,       ProcessDate.getStringFormatDate( BDateTime.getDateActuel()));
			    data.addProperty("tvaArticle"    ,      fkCode_barre.getPk().getAr_bean().getTva()==null?"": String.valueOf(fkCode_barre.getPk().getAr_bean().getTva().getTva_id()) );
			    
			    
			    getResponse().setContentType(JSON_CONTENT_TYPE);
				getResponse().getWriter().print(data.toString());
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			out.println(e.getMessage());
			out.close();
		}
		
		return null;
	}
	
 
	
	
	
public ModelAndView doLoadInfoVente(TarificationBean detailBean ) throws Exception {

		
		JsonObject data             =  new JsonObject();
		Double tarif_Unit_vente     =  new Double(0);
		Double margeBenefice        =  new Double(0);
		Double Coef_trf             =  new Double(0);
		Double  cout_achat          =  new Double(0);
		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType(HTML_CONTENT_TYPE);
		
	 
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		if( !bs.getFct_id().equals(Fn_Realiser)  ){
			detailBean  =(TarificationBean) getObjectValueModel("deviseData");
			detailBean.setInputFocus(detailBean.getBean_cal().getData_id());
		}
		String pattern ="0.000";
		if( detailBean.getDevise().getDev_id().intValue()==191  ||  detailBean.getDevise().getDev_id().intValue()==192   ){
			pattern ="0.00";
		}
		
		try {
			    if(detailBean.getCout()!=null){
				   cout_achat   = detailBean.getCout().getTarif_unit_article()==null? new Double(0):detailBean.getCout().getTarif_unit_article();
			    }
			       Coef_trf     = detailBean.getCoef_trf()==null? new Double(0):detailBean.getCoef_trf();
			    if(detailBean.getInputFocus().equals("coef")){
					         tarif_Unit_vente     = ProcessNumber.PRODUIT(cout_achat, Coef_trf);
					         margeBenefice        = tarif_Unit_vente-cout_achat;
			    }
			    if(detailBean.getInputFocus().equals("mrg")){
			    	if(detailBean.getMarge_vente()==null) throwNewException(" Veuillez entrer le marge de vente ");
			    	 margeBenefice        = detailBean.getMarge_vente()==null? new Double(0):detailBean.getMarge_vente();
			         tarif_Unit_vente     = ProcessNumber.addition(margeBenefice, cout_achat);
			         margeBenefice        = ProcessFormatNbr.FormatDouble_Troischiffre(margeBenefice);
			         Coef_trf=ProcessNumber.DIVISION(tarif_Unit_vente, cout_achat);
	             } 
			
				if(detailBean.getInputFocus().equals("htv")){
					if(detailBean.getTarif_unit_vente()==null) throwNewException(" Veuillez entrer le Prix unitaire de vente ");
					tarif_Unit_vente    =detailBean.getTarif_unit_vente()==null? new Double(0):detailBean.getTarif_unit_vente();
			    	
			    	
					    if(cout_achat.doubleValue()==0){
					    	cout_achat = new Double(0);
					    	margeBenefice =  new Double(0);
					    }else{
					    	tarif_Unit_vente     =ProcessFormatNbr.FormatDouble_Troischiffre(tarif_Unit_vente);
					    	cout_achat           =ProcessFormatNbr.FormatDouble_Troischiffre(cout_achat);
					    	margeBenefice        =ProcessNumber.SOUSTRACTION(tarif_Unit_vente, cout_achat);
					    	Coef_trf=ProcessNumber.DIVISION(tarif_Unit_vente, cout_achat);
					    }
			    }
				 
			    /*if(detailBean.getInputFocus().equals("remise")|| detailBean.getInputFocus().equals("tvio") ){
			    	if(detailBean.getTarif_unit_vente()==null) throwNewException(" Veuillez entrer le Prix de vente ");
			    	tarif_Unit_vente     =  detailBean.getTarif_unit_vente()==null? new Double(0):detailBean.getTarif_unit_vente();
			    	margeBenefice        =  detailBean.getMarge_vente()==null? new Double(0):detailBean.getMarge_vente();
			    }*/
			    data.addProperty("coef_trf", ProcessFormatNbr.FormatDouble_To_String_Troischiffre(Coef_trf)  ); 
			    data.addProperty("margeBenefice",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(margeBenefice));
			    data.addProperty("tarif_unit_vente",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tarif_Unit_vente));
			    data.addProperty("tarif_unit_achat"    ,  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(cout_achat));
			    
			    tarif_Unit_vente    = ProcessFormatNbr.FormatDouble_Troischiffre(tarif_Unit_vente);
				Double pourcentageRemise = detailBean.getTaux_remise()==null? new Double(0):detailBean.getTaux_remise();
				Double valeur_remise     = ProcessNumber.Pourcentage(tarif_Unit_vente, pourcentageRemise);
				data.addProperty("valeur_remise" ,ProcessFormatNbr.FormatDouble_To_String_Troischiffre(valeur_remise));
				
				
				Double tarif_vente_apres_remise   = tarif_Unit_vente-valeur_remise;
				data.addProperty("tarif_vente_remise",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tarif_vente_apres_remise));
				
				
				HashMap  map          =(HashMap) getObjectValueModel("mapTva");
				TVABean tvaBean       =(TVABean) map.get(String.valueOf(detailBean.getTvaBean().getTva_id()));
				Double valeur_de_laTva =ProcessNumber.Pourcentage(tarif_vente_apres_remise,tvaBean.getTva_value() );
				data.addProperty("valeur_de_laTva",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(valeur_de_laTva));
				
				
				Double tarif_vente_tt =tarif_vente_apres_remise+valeur_de_laTva;
				data.addProperty("tarif_vente_tt",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tarif_vente_tt));
				
				
				if(detailBean.getInputFocus().equals("ttc")){
					   Double         tarif_unit_article_ttc   =  detailBean.getTarif_unit_vente_tt();
					   Integer         tva   =  detailBean.getTvaBean().getTva_id();
					   
					   if(tva==null ){
						      throwNewException(" Veuillez entrer le TVA   ");
						 }
					   
					   Double         le_tva   = tvaBean.getTva_value();
					   
					   if(tarif_unit_article_ttc==null ){
						      throwNewException(" Veuillez entrer le Prix TTC   ");
							}
					         
							  //Double valeur_Unite     = ProcessFormatNbr.FormatDouble_ParameterChiffre( new Double(1.000),pattern);
							  //Double val_tva_de_Unite = ProcessNumber.Pourcentage(valeur_Unite,le_tva);
							  //Double val_ttc_de_Unite = ProcessNumber.addition(valeur_Unite,val_tva_de_Unite);
							  
							  Double  prix_unitVente_ttc   = ProcessFormatNbr.FormatDouble_ParameterChiffre(detailBean.getTarif_unit_vente_tt(),pattern);
							  Double  prix_unit_htVente    = new Double(0);
							  if(le_tva.doubleValue()==0)
								  prix_unit_htVente    = prix_unitVente_ttc;
//							  else
//								  prix_unit_htVente    = ProcessNumber.DIVISION(prix_unitVente_ttc, val_ttc_de_Unite);
							  
							  
							  Double valeur_tva         = ProcessNumber.Pourcentage(prix_unitVente_ttc, le_tva);
							  prix_unit_htVente         = ProcessNumber.SOUSTRACTION(prix_unitVente_ttc, valeur_tva);
							  
							  if(cout_achat.doubleValue()==0){
							    	cout_achat = new Double(0);
							    	margeBenefice =  new Double(0);
							    }else{
							    	prix_unit_htVente     =ProcessFormatNbr.FormatDouble_ParameterChiffre(prix_unit_htVente,pattern);
							    	cout_achat           =ProcessFormatNbr.FormatDouble_ParameterChiffre(cout_achat,pattern);
							    	margeBenefice        =ProcessNumber.SOUSTRACTION(prix_unit_htVente, cout_achat);
							    	Coef_trf=ProcessNumber.DIVISION(prix_unit_htVente, cout_achat);
							    }
							 data.addProperty("tarif_vente_tt",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(tarif_unit_article_ttc,pattern)); 
							 data.addProperty("valeur_de_laTva",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(valeur_tva,pattern)); 
							 data.addProperty("coef_trf", ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(Coef_trf,pattern));
							 data.addProperty("margeBenefice",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(margeBenefice,pattern));
							 data.addProperty("tarif_unit_vente",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(prix_unit_htVente,pattern)); 
							 data.addProperty("valeur_remise" ,"");
							 data.addProperty("tarif_vente_remise",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(prix_unit_htVente,pattern));
							  
						  
				}
		
			     
			    getResponse().setContentType(JSON_CONTENT_TYPE);
				getResponse().getWriter().print(data.toString());
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			out.println(e.getMessage());
			out.close();
		}
		
		return null;
	}


			 
	public ModelAndView doLoadInfo_article(TarificationBean detailBean ) throws Exception {

	PrintWriter out = getResponse().getWriter();
	getResponse().setContentType("text/html");
	try {
		
		 HashMap   mapArticle  =  (HashMap) getObjectValueModel("mapArticle");
		 Code_barreBean  cBean =  (Code_barreBean) mapArticle.get(detailBean.getFkCode_barre().getPk().getCode_barre());
		 String Cathegorie     =  cBean.getPk().getAr_bean().getCathegorie().getData_id();
		 HashMap  mapSerie     =  new HashMap();
		
		
		if(Cathegorie.equals("mar")){
 		}
		
		if(Cathegorie.equals("syn")){
			//dAOTarification.doFindDateMaxTarificationVente(beanSearch);
		}
		 
	} catch (Exception e) {
		getResponse().setStatus(500);
		getResponse().setContentType(HTML_CONTENT_TYPE);
		out.println(e.getMessage());
		out.close();
	}
	return null;

}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(TarificationBean searchBean) throws Throwable {
		try {
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			String condition="";//" AND  bean.tarif_lot is null ";
			
			if(bs.getFct_id().equals(Fn_Modifier) || bs.getFct_id().equals(Fn_Confirmer) ||  bs.getFct_id().equals(Fn_Supprimer)  ){
				condition+="  AND   bean.modeBean.fct_id in ('"+Fn_Realiser+"','"+Fn_Créer+"','"+Fn_Modifier+"','"+Fn_Choix+"')   ";
			}
			searchBean.setCondition_select_mode(condition);
			
			if(searchBean.getFkCode_barre().getPk().getAr_bean().getMode()!=null && 
					 searchBean.getFkCode_barre().getPk().getAr_bean().getMode().getData_id().equals("pd")){
			searchBean.setMethode_prix("   AND  (bean.tarif_lot is null or  bean.tarif_lot is false)  AND   bean.num_serie is null   ");
			searchBean.setMethode_prix2("  AND  (BA.tarif_lot is null   or  BA.tarif_lot is false)    AND   BA.num_serie is null   ");
			}
			
			if(searchBean.getFkCode_barre().getPk().getAr_bean().getMode()!=null && 
					 searchBean.getFkCode_barre().getPk().getAr_bean().getMode().getData_id().equals("pl")){
			searchBean.setMethode_prix("   AND   bean.tarif_lot is true    AND   bean.num_serie is not null   ");
			searchBean.setMethode_prix2("  AND   BA.tarif_lot is true      AND   BA.num_serie is not null   ");
			}
			
			 
        	 
			
			List listDataSrv = serviceTarification.doFetchDatafromServer(searchBean);
 			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	
	public    ModelAndView doPrintPDF_Tarif() {
		 
		
		GeneratePdf  genpdf= new GeneratePdf();
		
		try {
			List   lisData                         =    (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G)) ;
			String [][]    mapFieldBean            =    (String[][]) getObjectValueModel(MAP_FIELD_BEAN) ;
			File file = new File(getRequest().getRealPath("/")+"/temp/"+(String)getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	        String [][]    map_critere_de_recherche=    (String[][]) getObjectValueModel(MAP_CRITERE_DE_RECHERCHE) ;
	        Object searchBean=getObjectValueModel(SEARCH_BEAN);
			 FileOutputStream fs = new FileOutputStream(file);
			 Document document=GeneratePdf.doGenerateDocumentFormat();
		     PdfPTable table = new PdfPTable(mapFieldBean.length);
		    String    title =(String)getObjectValueModel(LIST_PDF_EXCEL) ;
		     if(title==null)
		     title =(String)getObjectValueModel("list-"+bSession.getSousmod_libelle()) ;
		     if(title==null)
		     title= (String)getObjectValueModel("list-"+bSession.getSousmod_id()) ; 
		    if(title==null)title="";
		    genpdf.doWriteHeaderDocument_PDF(document,fs,mapFieldBean,bSession);
			if(map_critere_de_recherche!=null && map_critere_de_recherche.length>0)
			genpdf.doWriteCritere_de_recherche_Table(document, searchBean,map_critere_de_recherche);
			genpdf.doWriteTitle_Table(document,title);
			genpdf.doWrite_Header_Table(table,mapFieldBean);
			
			doWrite_Data_Table(lisData,table,mapFieldBean);
		    document.add(table);
		    document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write((String) getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
		} catch (Throwable e) {
			displayException((Exception) e);
		} 
	 
	   return null;
     }
	
	
	public void doWrite_Data_Table(List lisData, PdfPTable table,
			String[][] mapFieldBean) throws Exception, SecurityException {
		  for(int i=0; i < lisData.size(); i++ ){
			   Object bean = (Object) lisData.get(i);
			    
			 for(int j = 0; j < mapFieldBean.length; j++){
				 
				        PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.REDFONT));
				        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
				        String ddddd=String.valueOf(obj);
				        if((j==mapFieldBean.length-4 ||  j==mapFieldBean.length-5) && !ddddd.equals("") ){
				        	 Double obsj =	 Double.valueOf(ddddd);
				        	 
				        	 String mnt=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(obsj);
				        	 
				        	 cell = new PdfPCell(new Phrase( mnt,GeneratePdf.REDFONT));
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

public    ModelAndView doExport_Xls() {
	try {

		List       lisData         =  (List)    getObjectValueModel((String) getObjectValueModel(NAME_LIST_G)) ;
		String [][]    mapFieldBean    = (String[][]) getObjectValueModel(MAP_FIELD_BEAN) ;
		
		WriteExcel  dbexp= new WriteExcel();
		dbexp.doExportExcel(lisData,mapFieldBean);
	} catch (Exception e) {
		displayException(e);
	}
	return null;

}


	public ModelAndView doAddData(TarificationBean detailBean) throws Throwable {
		try {
			serviceTarification.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		
		 //if(bs.getFct_id().equals(GenericActionBean.Fn_Realiser))
		  return getViewAdd(FORM_VIEW);
		 //else
		 // return getViewAdd(FILTER_TO_ADD_TARIFICATION);
	}

	public ModelAndView doUpdateData(TarificationBean beanUpBean) {
		try {
			serviceTarification.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(TarificationBean beanDelBean) {
		try {
			TarificationBean  beanDelBedan =(TarificationBean) getObjectValueModel(FORM_BEAN);
			serviceTarification.doDeleteRowData(beanDelBedan);
			removeObjectModel(FORM_BEAN);
			 
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	public    ModelAndView doTeste_Insert_Prix( TarificationBean beanSave  ) throws Exception{
		
		try {
			  String message=serviceTarification.doTesterTarificationInsert(beanSave);
			  getResponse().setContentType(HTML_CONTENT_TYPE);
			  getResponse().getWriter().print(message);
			} catch (Exception e) {
			  getResponse().setStatus(500);
			  getResponse().setContentType(HTML_CONTENT_TYPE);
			  getResponse().getWriter().print(e.getMessage());
			}
			return null;
	 
	}
	
	
	
}
