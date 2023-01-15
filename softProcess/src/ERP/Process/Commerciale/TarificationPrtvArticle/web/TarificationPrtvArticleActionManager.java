package ERP.Process.Commerciale.TarificationPrtvArticle.web;

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

import ERP.Process.Commerciale.Article.dao.ArticleDAO;
import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.service.ArticleService;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.Process.Commerciale.GrpTarifPrimitiv.service.GrpTarifPrimitivService;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.dao.TarificationPrtvArticleDAO;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.service.TarificationPrtvArticleService;
import ERP.Process.Commerciale.TarificationPrtvArticle.template.TarificationPrtvArticleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.service.DeviseService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessWebUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class TarificationPrtvArticleActionManager extends TarificationPrtvArticleTemplate {
	 
 
	private static final long serialVersionUID = 1633166156162649311L;

	private TarificationPrtvArticleService	serviceTarificationPrtvArticle;
	@Autowired
	public void setServiceTarificationPrtvArticle(TarificationPrtvArticleService serviceTarificationPrtvArticle) {
		this.serviceTarificationPrtvArticle = serviceTarificationPrtvArticle;
	}
	
	private GrpTarifPrimitivService  	serviceGrpTarifPrimitiv;
	@Autowired
	public void setServiceGrpTarifPrimitiv(GrpTarifPrimitivService serviceGrpTarifPrimitiv) {
		this.serviceGrpTarifPrimitiv = serviceGrpTarifPrimitiv;
	}

	@Autowired  public ArticleService	serviceArticle;
	
	@Autowired  public  Code_barreService      serviceCode_barre;
	
	 
	@Autowired Entite_etat_commercialeService   serviceEntite_etat_commerciale;

	 
	
	@Autowired  public  TVAService      serviceTVA ;
	
	@Autowired  public  TarificationPrtvArticleDAO      dAOTarificationPrtvArticle ;
	@Autowired  public  ArticleDAO      dAOArticle ;
	
	@Autowired
	private DeviseService     serviceDevise;

	public ModelAndView doInitServletAction() {

		

		try {
			
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Code_barreBean searchBean = new Code_barreBean();
		 
			
			 
			searchBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
			searchBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
			
			List  list_article_TarificationPrtvArticle    = serviceCode_barre.doFetchDatafromServerNew(searchBean);
			HashMap  map_article=ProcessUtil.getHashMap_code_bean(list_article_TarificationPrtvArticle, "pk.code_barre");
			setObjectValueModel(MAP_ARTICLE, map_article);
			setObjectValueModel(LIST_ARTICLE_TARIF_PRTV_ARTICLE, list_article_TarificationPrtvArticle);
			
			
			setObjectValueModel("list_devise", serviceDevise.doFetchDatafromServer(new DeviseBean()));
			
			//List<TarificationPrtvArticleBean> list_tyList = serviceTarificationPrtvArticle.doFetchDatafromServer(new TarificationPrtvArticleBean());
			 
			
			
			setObjectValueModel(LIST_GRP_TARIFI_PRTV ,serviceGrpTarifPrimitiv.doFetchDatafromServer(GrpTarifPrimitivBean.class.newInstance()));
			List  list_tva_list_cout=serviceTVA.doFetchDatafromServer(new TVABean());
			HashMap  mapTva_cout=ProcessUtil.getHashMap_code_bean(list_tva_list_cout, "tva_id");
			setObjectValueModel(LIST_TVA_LIST, list_tva_list_cout);
			setObjectValueModel("mapTva_cout", mapTva_cout);
			
			Entite_etat_commercialeBean modecal = new Entite_etat_commercialeBean();
			modecal.setCode_entite("mode_calcul_cout_u");
			setObjectValueModel("list_mode_calcul_cout_unit",serviceEntite_etat_commerciale.dofetchDatafromServer(modecal));
			
			
			 

			if (bs.getFct_id().equals(Fn_Creer) || bs.getFct_id().equals(Fn_Nouveau)) {
				//return getViewAdd(FILTER_TO_ADD_TarificationPrtvArticle);
				TarificationPrtvArticleBean  tarifBean = new TarificationPrtvArticleBean();
				tarifBean.setDate_prim_trf(BDateTime.getDateActuel());
				setObjectValueModel(FORM_BEAN, tarifBean);
				return getViewAdd(FORM_VIEW);
				
			} else {
				
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}
	}
	
	
	
	
	public    ModelAndView doGet_Row_Bean() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Object rowBean = getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("2"))
				return getViewConsult( FORM_VIEW );
			if (bs.getFct_id().equals("3"))
				return getViewUpdate( FORM_VIEW );
			if (bs.getFct_id().equals("4"))
				return getViewDelete( FORM_VIEW );
			if (bs.getFct_id().equals(Fn_Confirmer))
				return getViewConfirm( FORM_VIEW );
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax( FILTER_VIEW );
		}
		return getHome();

	}
	
	
	public static  ModelAndView doResetForm() {
		setObjectValueModel("listmaxTarificationPrtvArticle", new ArrayList());
		setObjectValueModel("listmaxTarific", new ArrayList());
		removeObjectModel(FORM_BEAN);
		return getViewAdd(FORM_VIEW);
	}
	
			
		@SuppressWarnings("unchecked")
		public ModelAndView doLoadSuivant(TarificationPrtvArticleBean detailBean) throws Exception {
		
		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType(JSON_CONTENT_TYPE);
		try {
			
			 
				List listmaxTarificationPrtvArticle=dAOTarificationPrtvArticle.doFindListTarificationPrtvArticle(detailBean);
				Code_barreBean  beanSearch= new Code_barreBean();
				beanSearch.setPk(detailBean.getFkCode_barre().getPk());
				BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
				  
				
				 
				beanSearch.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
				beanSearch.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
			    
				List listmaxTarn=serviceCode_barre.doFetchDatafromServerNew(beanSearch);
				Code_barreBean  aBean=(Code_barreBean) listmaxTarn.get(0);
				setObjectValueModel(BEAN_CODEBARRE_ARTICL, aBean);
				
			
				
			 
			     JSONObject  jsonObj = new JSONObject();
			     HashMap maptyMap=new HashMap();
				if(listmaxTarificationPrtvArticle!=null  && listmaxTarificationPrtvArticle.size()>0) {
				  List listmaxTari=ProcessUtil.cloneList(listmaxTarificationPrtvArticle);
				  maptyMap=ProcessUtil.getHashMap_code_bean(listmaxTari, "pk.typ_trfBean.type_trf_id");	
				  TarificationPrtvArticleBean bean_maxTarificationPrtvArticle=(TarificationPrtvArticleBean) listmaxTarificationPrtvArticle.get(0);
				  jsonObj.put("datex", ProcessDate.getCurrentTimeStamp(bean_maxTarificationPrtvArticle.getDate_prim_trf())  );
			      //jsonObj.put("tvax",  bean_maxTarificationPrtvArticle.getTvaBean().getTva_id());
				  //jsonObj.put("p_u_achat",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(bean_maxTarificationPrtvArticle.getPk().getPrix_unit_achat())  );
				  jsonObj.put("modex",    aBean.getPk().getAr_bean().getBean_mode_cal().getData_libelle());
				  jsonObj.put("mode_ids", aBean.getPk().getAr_bean().getBean_mode_cal().getData_id());
				  
				}else{
				 jsonObj.put("datex", "");
				 jsonObj.put("tvax","");
				 jsonObj.put("p_u_achat",""  );
				 jsonObj.put("modex", aBean.getPk().getAr_bean().getBean_mode_cal().getData_libelle());
				}
				
	      List  typTarif=(List) getObjectValueModel(LIST_TVA_LIST);
		  List listmaxTarific = new ArrayList();
		  for (int i = 0; i < typTarif.size(); i++) {
			  TarificationPrtvArticleBean tBean= (TarificationPrtvArticleBean) typTarif.get(i);
			  TarificationPrtvArticleBean benTraif=(TarificationPrtvArticleBean) maptyMap.get(String.valueOf(""));
			  if(benTraif==null){
				  TarificationPrtvArticleBean tBean2= new TarificationPrtvArticleBean();
				  //tBean2.getPk().setTyp_trfBean(tBean);
				  listmaxTarific.add(tBean2);
			  }else{
				  listmaxTarific.add(benTraif);
			  }
		  }
		  setObjectValueModel("listmaxTarificationPrtvArticle", listmaxTarificationPrtvArticle);
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
		public static ModelAndView doUpdateEditableTable() throws Exception{
		 
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
					
					TarificationPrtvArticleBean tBean =(TarificationPrtvArticleBean) listmaxTarific.get(i);
					Double prix_u_achat=ProcessFormatNbr.ConvertStringToDouble(prix_unit_achat);
					
					if(aBean.getPk().getAr_bean().getBean_mode_cal().getData_id().equals("c")){
						
						
						//Double doubval_coef=ProcessFormatNbr.FormatDouble_Troischiffre(tBean.getCoef_trf()==null?new Double(0):tBean.getCoef_trf());
						//Double prix_unit_vente= ProcessNumber.PRODUIT(prix_u_achat,doubval_coef)  ;
						//tBean.getPk().setPrix_unit_vente( ProcessFormatNbr.FormatDouble_Troischiffre(prix_unit_vente) );
						
						//Double marge_vente =ProcessNumber.SOUSTRACTION(prix_unit_vente,prix_u_achat);
					         //  marge_vente =ProcessFormatNbr.FormatDouble_Troischiffre(marge_vente);
					          // tBean.setMarge_vente(marge_vente);
					}
					
                    if(aBean.getPk().getAr_bean().getBean_mode_cal().getData_id().equals("m")){
                    	//Double doubval_marge=ProcessFormatNbr.FormatDouble_Troischiffre(tBean.getMarge_vente()==null?new Double(0):tBean.getMarge_vente());
                    	//Double prix_unit_vente= ProcessNumber.SOMME(prix_u_achat,doubval_marge)  ;
						//tBean.getPk().setPrix_unit_vente( ProcessFormatNbr.FormatDouble_Troischiffre(prix_unit_vente) );
						
						//Double doubval_coef=ProcessNumber.DIVISION(prix_unit_vente,prix_u_achat); 
			                  // doubval_coef =ProcessFormatNbr.FormatDouble_Troischiffre(doubval_coef);
			                   //tBean.setCoef_trf(doubval_coef);
					}
                    
                   
                    
                     if(aBean.getPk().getAr_bean().getBean_mode_cal().getData_id().equals("s")){
                    	 
                    	 
 						
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
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			 
			
			cBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
			cBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
			
			List listData         = serviceCode_barre.doFetchDatafromServerNew(cBean);
			
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
	public ModelAndView doFetchData(TarificationPrtvArticleBean searchBean) throws Throwable {
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		   if( bs.getFct_id().equals(Fn_Confirmer)  || bs.getFct_id().equals(Fn_Supprimer) || bs.getFct_id().equals(Fn_Modifier)   ){
					searchBean.setCondition_etat_achat("    AND  bean.modeBean.fct_id not in ('"+Fn_Confirmer+"')   ");
			 } 
			List listDataSrv = serviceTarificationPrtvArticle.doFetchDatafromServer(searchBean);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (JsonIOException e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	public ModelAndView doAddData(TarificationPrtvArticleBean detailBean) throws Throwable {
		try {
			setObjectValueModel(FORM_BEAN, detailBean);
			HashMap   map_article=  (HashMap) getObjectValueModel(MAP_ARTICLE);
			Code_barreBean  fkCode_barre=(Code_barreBean) map_article.get(detailBean.getFkCode_barre().getPk().getCode_barre());
			detailBean.setFkCode_barre(fkCode_barre);
			serviceTarificationPrtvArticle.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		  return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(TarificationPrtvArticleBean beanUpBean) {
		try {
			HashMap  	 map_article = (HashMap) getObjectValueModel( TarificationPrtvArticleTemplate.MAP_ARTICLE );
			Code_barreBean  beanArBean=(Code_barreBean) map_article.get(beanUpBean.getFkCode_barre().getPk().getCode_barre());
			beanUpBean.setFkCode_barre(beanArBean);
			serviceTarificationPrtvArticle.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
public ModelAndView doCalculate_PU(TarificationPrtvArticleBean detailBean ) throws Exception {

		
		JsonObject data             = new JsonObject();
		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType(HTML_CONTENT_TYPE);
		try {
			 
				if(detailBean.getTvaBean()==null  ||  detailBean.getTvaBean().getTva_id()==null){
					
					 data.addProperty("tarif_unit_article", "" );
					 data.addProperty("valeur_tva", "" );
					 data.addProperty("tarif_unit_ttc",  "" ); 
					 
					throwNewException(" Veuillez entrer le TVA  du prix unitaire ");
				}
			  
			   HashMap  mapTva_cout    =(HashMap) getObjectValueModel("mapTva_cout");
			   TVABean tvaBean         =(TVABean) mapTva_cout.get(String.valueOf(detailBean.getTvaBean().getTva_id()));
			   Double         le_tva   = tvaBean.getTva_value();
				 
			   
			   if(detailBean.getInputFocus().equals("HT")){
				   Double         getTarif_unit_article   = detailBean.getTarif_unit_article();
					if(getTarif_unit_article==null ){
				      throwNewException(" Veuillez entrer le Prix unitaire  H.T ");
				      data.addProperty("tarif_unit_ttc",   "" ); 
				      data.addProperty("valeur_tva", "" );
				      data.addProperty("tarif_unit_article", "" );
					}
					  Double   prix_unit  = ProcessFormatNbr.FormatDouble_Troischiffre(detailBean.getTarif_unit_article());
					  Double valeur_tva   = ProcessNumber.Pourcentage(getTarif_unit_article, le_tva);
					  Double prix_unit_ttc   = ProcessNumber.addition(prix_unit, valeur_tva);
					  data.addProperty("tarif_unit_article",  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(prix_unit)  );
					  data.addProperty("valeur_tva",          ProcessFormatNbr.FormatDouble_To_String_Troischiffre(valeur_tva)  );
					  data.addProperty("tarif_unit_ttc",      ProcessFormatNbr.FormatDouble_To_String_Troischiffre(prix_unit_ttc)  ); 
					  
			    }
			
			   if(detailBean.getInputFocus().equals("TT")){
				   Double         getTarif_unit_article   = detailBean.getTarif_unit_ttc();
					if(getTarif_unit_article==null ){
				      throwNewException(" Veuillez entrer le Prix TTC   ");
				      data.addProperty("tarif_unit_ttc",   "" ); 
				      data.addProperty("valeur_tva", "" );
				      data.addProperty("tarif_unit_article", "" );
					}
					
					  Double valeur_Unite     = ProcessFormatNbr.FormatDouble_Troischiffre(new Double(1.000));
					  Double val_tva_de_Unite = ProcessNumber.Pourcentage(valeur_Unite,le_tva);
					  Double val_ttc_de_Unite = ProcessNumber.addition(valeur_Unite,val_tva_de_Unite);
					  
					  Double  prix_unit_ttc   = ProcessFormatNbr.FormatDouble_Troischiffre(detailBean.getTarif_unit_ttc());
					  
					  Double  prix_unit_ht    = new Double(0);
					  if(le_tva.doubleValue()==0)
						  prix_unit_ht    = prix_unit_ttc;
					  else
						  prix_unit_ht    = ProcessNumber.DIVISION(prix_unit_ttc, val_ttc_de_Unite);
					  
					  Double valeur_tva      = ProcessNumber.Pourcentage(prix_unit_ht, le_tva);
					  data.addProperty("tarif_unit_article",  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(prix_unit_ht)  );
					  data.addProperty("valeur_tva",          ProcessFormatNbr.FormatDouble_To_String_Troischiffre(valeur_tva)  );
					  data.addProperty("tarif_unit_ttc",      ProcessFormatNbr.FormatDouble_To_String_Troischiffre(prix_unit_ttc)  ); 
					  
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


	
	public ModelAndView doConfirmData(TarificationPrtvArticleBean beanUpBean) {
		try {
			HashMap  	 map_article   = (HashMap) getObjectValueModel( TarificationPrtvArticleTemplate.MAP_ARTICLE );
			Code_barreBean  beanArBean = (Code_barreBean) map_article.get(beanUpBean.getFkCode_barre().getPk().getCode_barre());
			beanUpBean.setFkCode_barre(beanArBean);
			serviceTarificationPrtvArticle.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA,beanUpBean);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(TarificationPrtvArticleBean beanDelBean) {
		try {
			TarificationPrtvArticleBean tarifBean=(TarificationPrtvArticleBean) getObjectValueModel(FORM_BEAN);
			serviceTarificationPrtvArticle.doDeleteRowData(tarifBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
