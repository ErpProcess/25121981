package ERP.Process.Commerciale.TarificationLot.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import ERP.Process.Commerciale.Article.dao.ArticleDAO;
import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.service.ArticleService;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.template.TarificationTemplate;
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.Process.Commerciale.TarificationLot.service.TarificationLotService;
import ERP.Process.Commerciale.TarificationLot.template.TarificationLotTemplate;
import ERP.Process.Commerciale.Type_tarification.dao.Type_tarificationDAO;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Type_tarification.service.Type_tarificationService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;



public class TarificationLotActionManager extends TarificationLotTemplate {
  
	private static final long serialVersionUID = -4360925424235872959L;
	
	private  DocumentLotService    serviceDocumentLot;
	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
	    this.serviceDocumentLot = serviceDocumentLot;
	} 
	
	@Autowired  public   ArticleService			  serviceArticle;
	@Autowired  public  Code_barreService         serviceCode_barre;
	@Autowired  public  Type_tarificationService  serviceType_tarification;
	@Autowired  public  Type_tarificationDAO      daoType_tarification;
	@Autowired  public  TVAService                serviceTVA ;
	@Autowired  public  TarificationDAO           dAOTarification ;
	@Autowired  public  ArticleDAO                dAOArticle ;
	
	private TarificationLotService  serviceTarificationLot;
	@Autowired
	public void setServiceTarificationLot(TarificationLotService serviceTarificationLot) {
	    this.serviceTarificationLot = serviceTarificationLot;
	} 
	
	public    ModelAndView doInitServletAction() {
		
	 
		try {
			setObjectValueModel(FORM_BEAN, new TarificationLotBean());
			setObjectValueModel(SEARCH_BEAN, new TarificationLotBean());
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			
			doLoadingLibelleOtherSModule(TarificationTemplate.ID_SOUS_MODULE);//tarification de vente libelle
			
			setObjectValueModel("listmaxTarification", new ArrayList());
			setObjectValueModel("listmaxTarific", new ArrayList());
			List<ArticleBean> list_article_tarification = serviceArticle.dofetchDatafromServer(new ArticleBean());
			List<Type_tarificationBean> list_type_List = serviceType_tarification.doFetchDatafromServer(new Type_tarificationBean());
			List<TVABean> list_tvList = serviceTVA.doFetchDatafromServer(new TVABean());
			
			setObjectValueModel(LIST_ARTICLE_TARIFICATION, list_article_tarification);
			setObjectValueModel(LIST_TYLIST, list_type_List);
			setObjectValueModel(LIST_TVLIST, list_tvList);
			
		 
			
			if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
				return getViewAdd(FORM_VIEW_EDIT);
			} else {
				return getViewFilterAjax(FILTER_VIEW);

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
	public ModelAndView doLoadInfo_article(TarificationLotBean detailBean ) throws Exception {

		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType("text/html");
		try {
			
			HashMap   mapArticle  =  (HashMap) getObjectValueModel("mapArticle");
			Code_barreBean  cBean =  (Code_barreBean) mapArticle.get(detailBean.getPk().getVente().getFkCode_barre().getPk().getCode_barre());
			String Cathegorie     =  cBean.getPk().getAr_bean().getCathegorie().getData_id();
			HashMap  mapSerie=  new HashMap();
			
			
			if(Cathegorie.equals("mar")){
				SerieArticletBean sBe = new SerieArticletBean();
				sBe.setCondition_etat_serie( "  AND  bean.etat.data_id in ( 'cre' , 'mod' )   and  quantite > 0     ");
				sBe.setFkCode_barre(cBean);
				List   listLOt = serviceDocumentLot.doFetchDatafromServer(sBe);
				for (int i = 0; i < listLOt.size(); i++) {
					 SerieArticletBean  sBean = (SerieArticletBean) listLOt.get(i);
					 String  detaille_serie= ProcessDate.getStringFormatDate(sBean.getDate_serie())+"          "+sBean.getFkCode_barre().getPk().getCode_barre()+" / "+
					 sBean.getFkCode_barre().getDesignation_libelle();
					 sBean.setDetaille_serie(detaille_serie);
					 mapSerie.put(sBean.getPk().getNum_serie(), sBean);
				}
				
				setObjectValueModel(LIST_LOT_ARTICLE_FETCH, listLOt);
				setObjectValueModel("mapSerie", mapSerie);
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
	public ModelAndView doLoadCout(TarificationLotBean detailBean ) throws Exception {

		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType(HTML_CONTENT_TYPE);
		try {
			HashMap   mapSe  =  (HashMap) getObjectValueModel("mapSerie"  );
			SerieArticletBean   sss =(SerieArticletBean) mapSe.get(detailBean.getPk().getLot().getPk().getNum_serie());
			 
			JsonObject data = new JsonObject();
			data.addProperty("codetarif",sss.getTarif().getTarif_prim_id());
			data.addProperty("tarif_unit_achat"    ,sss.getTarif().getTarif_unit_article());
			data.addProperty("datetarif_achat",ProcessDate.getStringFormatDate(sss.getTarif().getDate_prim_trf()));
			
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
	
	public ModelAndView doLoadDepotFromLot(TarificationLotBean detailBean) throws  Exception {

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
		Collection<JSONObject> items = ActionDataTablesManager.doRenderListJson( listData, query, typeSearch, fcode,flabel);
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
		
		cBean.setCondition_article_seulement_vente("  AND  pk.ar_bean.cathegorie.data_id in ('mar','syn')  ");
		
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

	@SuppressWarnings("unchecked")
	public ModelAndView doLoadSuivant(TarificationLotBean detailBean) throws Exception {
	
	PrintWriter out = getResponse().getWriter();
	getResponse().setContentType(JSON_CONTENT_TYPE);
	try {
		
		
		
		      if(detailBean.getPk().getVente().getMethode_prix()!=null){
				  
			  }else{
				  
			  }
		  
		      
			List listmaxTarification=null;//dAOTarification.doFindListTarification(detailBean);
			Code_barreBean  beanSearch= new Code_barreBean();
			//beanSearch.setPk(detailBean.getFkCode_barre().getPk());
			
			List listmaxTarn=serviceCode_barre.doFetchDatafromServerNew(beanSearch);
			Code_barreBean  aBean=(Code_barreBean) listmaxTarn.get(0);
			//setObjectValueModel(BEAN_CODEBARRE_ARTICL, aBean);
			
		
			
		 
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
	
	
	public    ModelAndView doGetRowBeanTarif() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Object rowBean = getIndexFromDataGrid_v1(LIST_DATA);
			setObjectValueModel(FORM_BEAN, rowBean);
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
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(TarificationLotBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceTarificationLot.doFetchDatafromServer(searchBean);
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
	public ModelAndView doAddData(TarificationLotBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceTarificationLot.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(TarificationLotBean beanUpBean) {	 
		 	try {
		 serviceTarificationLot.doUpdateRowData(beanUpBean); 
		 update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	
	public ModelAndView doConfirmData(TarificationLotBean beanUpBean) {	 
	 	try {
	 serviceTarificationLot.doconfirmRowData(beanUpBean); 
	 update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("ins01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
	public ModelAndView doDeleteData(TarificationLotBean beanDelBean) {
	    try {
	     serviceTarificationLot.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
