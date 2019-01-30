package ERP.Process.Commerciale.Article.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;
import org.hibernate.ejb.criteria.predicate.IsEmptyPredicate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.model.ClientArticleBean;
import ERP.Process.Commerciale.Article.model.LieuxArticleBean;
import ERP.Process.Commerciale.Article.service.ArticleService;
import ERP.Process.Commerciale.Article.template.ArticleTemplate;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.Process.Commerciale.FamilleArticle.service.FamilleArticleService;
import ERP.Process.Commerciale.Stock.DepotStockage.dao.DepotStockageDAO;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Client.service.ClientService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.service.Data_entite_simpleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.service.EtablissementService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.service.SocieteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.service.UniteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

public class ArticleActionManager extends ArticleTemplate {
	
	
	private static final long serialVersionUID = -3247943906529641740L;

	 
	
	private ArticleService  serviceArticle;
	@Autowired
	public void setServiceProcedureVente(ArticleService serviceArticle) {
	    this.serviceArticle = serviceArticle;
	} 
	
	@Autowired FamilleArticleService serviceFamilleArticle;
	
	@Autowired Data_entite_simpleService   serviceData_entite_simple;
	
	@Autowired Entite_etat_commercialeService   serviceEntite_etat_commerciale;
	
	 
	private ClientService serviceClient;
	@Autowired
	public void setServiceClient(ClientService serviceClient) {
		this.serviceClient = serviceClient;
	}
	
	private DepotStockageDAO daoDepotStockage;
	@Autowired
	public void setDaoDepotStockage(DepotStockageDAO daoDepotStockage) {
		this.daoDepotStockage = daoDepotStockage;
	}
	 
	
	 private Code_barreService serviceCode_barre;
		
	 @Autowired
	 public void setServiceCode_barre(Code_barreService serviceCode_barre) {
		 this.serviceCode_barre = serviceCode_barre;
	}
	
	private SocieteService              serviceSociete;
	@Autowired
	public void setServiceSociete(SocieteService serviceSociete) {
		this.serviceSociete = serviceSociete;
	}
	
	private EtablissementService        serviceEtablissement;
	@Autowired
	public void setServiceEtablissement(EtablissementService serviceEtablissement) {
		this.serviceEtablissement = serviceEtablissement;
	}
	
    private UniteService  serviceUnite;
    @Autowired
    public void setServiceUnite(UniteService serviceUnite) {
        this.serviceUnite = serviceUnite;
    } 
	
    @Autowired  public  TVAService                serviceTVA ;
	
	public     ModelAndView doInitServletAction() {

		
		 
		try {
			doLoadingLibelleOtherSModule("52");
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			
			FamilleBean fBean = new FamilleBean();
 
			
			setObjectValueModel("listSocioTa", serviceSociete.dofetchDatafromServer(new SocieteBean()));
			Entite_etat_commercialeBean beanSearchs = new Entite_etat_commercialeBean();
			beanSearchs.setCode_entite("mode_cal_puv");
			setObjectValueModel("list_mode_calcul",serviceEntite_etat_commerciale.dofetchDatafromServer(beanSearchs));
			
			setObjectValueModel("listUsr_etat",serviceData_entite_simple.dofetchDataByCodeEntite("ETAT_USR"));
			Entite_etat_commercialeBean beanSearch = new Entite_etat_commercialeBean();
			beanSearch.setCode_entite("type_ar");
			setObjectValueModel("list_unite_article",serviceUnite.doFetchDatafromServer(UniteBean.class.newInstance()));
			setObjectValueModel("list_type_article",serviceEntite_etat_commerciale.dofetchDatafromServer(beanSearch));
			
			Entite_etat_commercialeBean beanSearchart_cath = new Entite_etat_commercialeBean();
			beanSearchart_cath.setCode_entite("art_cath");
			setObjectValueModel("list_cathegori_article",serviceEntite_etat_commerciale.dofetchDatafromServer(beanSearchart_cath));
			
			Entite_etat_commercialeBean beanMode_prix = new Entite_etat_commercialeBean();
			beanMode_prix.setCode_entite("mode_prix_vente");
			setObjectValueModel("list_mode_prix",serviceEntite_etat_commerciale.dofetchDatafromServer(beanMode_prix));
			
			Entite_etat_commercialeBean beanMode_choix_lot = new Entite_etat_commercialeBean();
			beanMode_choix_lot.setCode_entite("mode_choix_lot");
			setObjectValueModel("list_choix_lot",serviceEntite_etat_commerciale.dofetchDatafromServer(beanMode_choix_lot));
			
			
			setObjectValueModel("list_societe",serviceSociete.dofetchDatafromServer(SocieteBean.class.newInstance()));
			
			
			List<TVABean> list_tvList = serviceTVA.doFetchDatafromServer(new TVABean());
			HashMap mapTva =  ProcessUtil.getHashMap_code_bean(list_tvList, "tva_id");
			setObjectValueModel("mapTva", mapTva);
			setObjectValueModel("list_tvList",list_tvList);
			 
			Code_barreBean searchBeanx=new Code_barreBean();
		    //searchBeanx.getPk().getAr_bean().getPk_article().setAr_id(ar_id) ;
		    searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
			
			if(bs.getSousmod_id().equals("52")) {
				fBean.setConditionDeSelection(" AND  type.typefam_id  in ('art','frn')   ");
				setObjectValueModel("list_FamilleArticle" , serviceFamilleArticle.dofetchDatafromServer(fBean));
			}else {
				fBean.setConditionDeSelection(" AND  type.typefam_id  in ('srv')   ");
				setObjectValueModel("list_FamilleArticle" , serviceFamilleArticle.dofetchDatafromServer(fBean));
			}
			
			if (bs.getFct_id().equals(Fn_Nouveau)) { 
				 
				setObjectValueModel("listDepotStockageInit" , daoDepotStockage.doFindListDepotStockage(DepotStockageBean.class.newInstance()));
				setObjectValueModel("listClientInit" ,serviceClient.doFetchDatafromServer(ClientBean.class.newInstance()));
				 
				
				return getViewAdd(FORM_VIEW);
				
			}else if(bs.getFct_id().equals(Fn_Client)){	
				
				setObjectValueModel(FORM_BEAN, new ClientArticleBean());
				
				return getViewProduit_client(FORM_VIEW_CLIENT);
				
			}else if(bs.getFct_id().equals(Fn_Lieux)){
				setObjectValueModel(FORM_BEAN, new LieuxArticleBean());
				return getViewLieux(FORM_VIEW_LIEUX);
				
			}else if(bs.getFct_id().equals(Fn_Créer)){
				 
				
				return getViewAdd(FORM_SERVICE);
				
			} else {
				return getViewFilterAjax(FILTER_VIEW);
			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchlieux_produit(LieuxArticleBean searchBean) throws Exception{
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			
			setObjectValueModel("etab_id", "");
			searchBean.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setSoc_bean(bs.getSociete());
			List list_des_lieux_for_select       =  serviceArticle.doFindLieuxArcticle(searchBean);
			List <DepotStockageBean> list_depot  =  daoDepotStockage.doFindListDepotStockage(DepotStockageBean.class.newInstance());
			
			List lisf =  new ArrayList () ;
			HashMap  mapbidan =  new HashMap();
			for (int i = 0; i < list_des_lieux_for_select.size(); i++) {
				LieuxArticleBean dsfsdf= (LieuxArticleBean) list_des_lieux_for_select.get(i);
				dsfsdf.setTo_check("checked");
				lisf.add(dsfsdf);
				mapbidan.put(dsfsdf.getPk().getLieu().getDepot_id(), "true");
			}
			
			 
			for ( DepotStockageBean fd:list_depot) {
				if(mapbidan.get(fd.getDepot_id())==null){
					LieuxArticleBean  dfdsfdsf= new LieuxArticleBean();
					dfdsfdsf.getPk().setLieu(fd);
					dfdsfdsf.getPk().getRef().getPk().setCode_barre(searchBean.getPk().getRef().getPk().getCode_barre());
					dfdsfdsf.getPk().getRef().getPk().getAr_bean().getPk_article().setAr_id(searchBean.getPk().getRef().getPk().getAr_bean().getPk_article().getAr_id());
					dfdsfdsf.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setSoc_bean(bs.getSociete());
					dfdsfdsf.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(searchBean.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id());
					lisf.add(dfdsfdsf);
				}
				
			}
			setObjectValueModel("lisfS"   , lisf);
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchProduit_client(ClientArticleBean searchBean) throws Exception{
		try {
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			setObjectValueModel("etab_id", "");
			searchBean.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setSoc_bean(bs.getSociete());
			List list_des_produit_for_select   =  serviceArticle.doFindProduitClient(searchBean);
			List <ClientBean> list_client      =  serviceClient.doFetchDatafromServer(ClientBean.class.newInstance());
			List lisfinal =  new ArrayList () ;
			HashMap  mapbidan =  new HashMap();
			for (int i = 0; i < list_des_produit_for_select.size(); i++) {
				ClientArticleBean dsfsdf= (ClientArticleBean) list_des_produit_for_select.get(i);
				dsfsdf.setTo_check("checked");
				lisfinal.add(dsfsdf);
				mapbidan.put(dsfsdf.getPk().getClient().getClt_id(), "true");
			}
			 
			for ( ClientBean client:list_client) {
				if(mapbidan.get(client.getClt_id())==null){
					ClientArticleBean  clBean= new ClientArticleBean();
					clBean.getPk().setClient(client);
					clBean.getPk().getRef().getPk().setCode_barre(searchBean.getPk().getRef().getPk().getCode_barre());
					clBean.getPk().getRef().getPk().getAr_bean().getPk_article().setAr_id(searchBean.getPk().getRef().getPk().getAr_bean().getPk_article().getAr_id());
					clBean.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setSoc_bean(bs.getSociete());
					clBean.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(searchBean.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id());
					lisfinal.add(clBean);
				}
				
			}
			setObjectValueModel("list_produit_client"   , lisfinal);
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(ArticleBean searchBean) throws Exception{
		try {
			//List <ArticleBean>listDataSrv = serviceArticle.dofetchDatafromServer(searchBean);
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			searchBean.getPk_article().getEtabBean().getPk_etab().setSoc_bean(bs.getSociete());
			//searchBean.setEtablissment(searchBean.getPk_article().getEtabBean().getPk_etab().getEtab_id());
			List <Code_barreBean>list  = serviceCode_barre.doFetchDatafromServer_article(searchBean);
			List <ArticleBean> listDataSrv = new ArrayList<ArticleBean>();
			for (Code_barreBean  bd: list) {
				ArticleBean  arBanX=bd.getPk().getAr_bean();
				ArticleBean  arBan= (ArticleBean) ProcessUtil.cloneObject(arBanX);
				arBan.setArcodbar(bd.getPk().getCode_barre());
				arBan.setDesignation(bd.getDesignation());
				arBan.setDesignation_libelle(bd.getDesignation_libelle());
				
				if(bs.getSousmod_id().equals(ID_SOUS_MODULE_PRESTATION)    ){
					if( bd.getPk().getAr_bean().getCathegorie()!=null  && 
							 bd.getPk().getAr_bean().getCathegorie().getData_id().equals("srv")   ) 
					listDataSrv.add(arBan);
				}else{
					if( bd.getPk().getAr_bean().getCathegorie()!=null  && 
							 !bd.getPk().getAr_bean().getCathegorie().getData_id().equals("srv")   ) 
					listDataSrv.add(arBan);
				}
				
			}
			
			
			setObjectValueModel(SEARCH_BEAN , searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	public    ModelAndView doSelectRowArticle() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			ArticleBean rowBean = (ArticleBean) getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			if( !StringUtils.isBlank(rowBean.getData_article_langue()) ) {
				JSONObject jsonObj = new JSONObject(rowBean.getData_article_langue());
				HashMap<String, Object> yourHashMap = new Gson().fromJson(jsonObj.toString(), HashMap.class);
				Map<String,Object> yearMap = yourHashMap;  
				rowBean.setMaplang(yearMap);
			}
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("2"))
				return getViewConsult((String) getObjectValueModel("FORM_VIEW"));
			if (bs.getFct_id().equals("3"))
				return getViewUpdate(FORM_VIEW_MODIFIER);
			if (bs.getFct_id().equals("4"))
				return getViewDelete((String) getObjectValueModel("FORM_VIEW"));
			if (bs.getFct_id().equals(Fn_Etat)){
				return getViewSituation(FORM_SITUATION_ARTICLE);
			}
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchDataEtablissement(ArticleBean searchBean)throws Exception { 
		
		PrintWriter out = getResponse().getWriter();
        getResponse().setContentType(JSON_CONTENT_TYPE);
        EtablissementBean beanEtab=new EtablissementBean();
        BeanSession bs      = (BeanSession)getObjectValueModel(BEAN_SESSION);
        beanEtab.getPk_etab().setSoc_bean(bs.getSociete());
        List   listData = serviceEtablissement.dofetchDatafromServer(beanEtab);
        String query = getRequest().getParameter("term");
        String typeSearch = getRequest().getParameter("typeSearch");
        String fcode = getRequest().getParameter("fieldcode");
        String flabel = getRequest().getParameter("fieldlabel");
        try {
        	 Collection<JSONObject> items=ActionDataTablesManager.doRenderListJson(listData, query, typeSearch, fcode, flabel);
             out.println(items.toString());
             out.close();
        } catch (Exception e) {
        	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
        return null;
	 
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchDataEtablissement_Produit_client(ClientArticleBean searchBean)throws Exception { 
		
		PrintWriter out = getResponse().getWriter();
        getResponse().setContentType(JSON_CONTENT_TYPE);
        EtablissementBean beanEtab=new EtablissementBean();
        BeanSession bs      = (BeanSession)getObjectValueModel(BEAN_SESSION);
        beanEtab.getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
        List   listData = serviceEtablissement.dofetchDatafromServer(beanEtab);
        String query = getRequest().getParameter("term");
        String typeSearch = getRequest().getParameter("typeSearch");
        String fcode = getRequest().getParameter("fieldcode");
        String flabel = getRequest().getParameter("fieldlabel");
        try {
        	 Collection<JSONObject> items=ActionDataTablesManager.doRenderListJson(listData, query, typeSearch, fcode, flabel);
             out.println(items.toString());
             out.close();
        } catch (Exception e) {
        	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
        return null;
	 
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData_articleFrom_Etab_Produit_client(ClientArticleBean searchBean)throws Exception { 
		
		PrintWriter out   =getResponse().getWriter();
        getResponse().setContentType(JSON_CONTENT_TYPE);
        
        BeanSession bs    =(BeanSession)getObjectValueModel(BEAN_SESSION);
        String  etab_id   = getRequest().getParameter("codeInput");
        ArticleBean searchBeanx =new ArticleBean();
        searchBeanx.getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
        searchBeanx.getPk_article().getEtabBean().getPk_etab().setEtab_id(etab_id);
        setObjectValueModel("etab_id", etab_id);
        
        List   listData   = serviceArticle.dofetchDatafromServer(searchBeanx);
        String query      = getRequest().getParameter("term");
        String typeSearch = getRequest().getParameter("typeSearch");
        String fcode      = getRequest().getParameter("fieldcode");
        String flabel     = getRequest().getParameter("fieldlabel");
        try {
        	 Collection<JSONObject> items=ActionDataTablesManager.doRenderListJson(listData, query, typeSearch, fcode, flabel);
             out.println(items.toString());
             out.close();
        } catch (Exception e) {
        	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
        return null;
	 
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData_RefFrom_article_client(ClientArticleBean searchBean)throws Exception { 
		
		PrintWriter out   = getResponse().getWriter();
        getResponse().setContentType(JSON_CONTENT_TYPE);
        String  ar_id     = getRequest().getParameter("codeInput");
        BeanSession bs    =(BeanSession)getObjectValueModel(BEAN_SESSION);
        Code_barreBean searchBeanx=new Code_barreBean();
        searchBeanx.getPk().getAr_bean().getPk_article().setAr_id(ar_id) ;
        String etab_id    =(String)getObjectValueModel("etab_id");
       
        if(!StringUtils.isEmpty(etab_id))
        searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(etab_id);
        
        searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
        
        List   <Code_barreBean>  list_des_articles_client   = serviceCode_barre.doFetchDatafromServerNew(searchBeanx);
		setObjectValueModel("list_des_articles_clients",list_des_articles_client);
		setObjectValueModel("list_des_articles", ProcessUtil.cloneList(list_des_articles_client) );
        String query      = getRequest().getParameter("term");
        String typeSearch = getRequest().getParameter("typeSearch");
        String fcode      = getRequest().getParameter("fieldcode");
        String flabel     = getRequest().getParameter("fieldlabel");
        try {
        	 Collection<JSONObject> items=ActionDataTablesManager.doRenderListJson(list_des_articles_client, query, typeSearch, fcode, flabel);
             out.println(items.toString());
             out.close();
        } catch (Exception e) {
        	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
        return null;
	 
	}
	
	
	public ModelAndView doAddData(ArticleBean detailBean) throws Exception {
		  BeanSession bs      = (BeanSession)getObjectValueModel(BEAN_SESSION);
		try {
			setObjectValueModel(FORM_BEAN, detailBean);
			detailBean.getBean_sitcod().setData_id("A");
			
			
			 String dataSocieteLng_ar= getRequest().getParameter("dataSocieteLng_ar");
			 String dataSocieteLng_en= getRequest().getParameter("dataSocieteLng_en");
			 
			 JSONObject json        = new JSONObject();
			 boolean enter=false;
			 if( !StringUtils.isBlank(dataSocieteLng_ar) ) { enter=true; json.put("ar",  convertStringToHashMap(dataSocieteLng_ar)); }
			 if( !StringUtils.isBlank(dataSocieteLng_en) ) { enter=true; json.put("en", convertStringToHashMap(dataSocieteLng_en));  }
			 
			 if(enter ) { 
			 String data_societe_langue=json.toString();
			 detailBean.setData_article_langue(data_societe_langue);
			 }
			
			if(bs.getFct_id().equals("5")){
				detailBean.getBean_mode_cal().setData_id("coef");
				detailBean.getCathegorie().setData_id("srv");
				detailBean.getUnitBean().setUnite_id(97);
				detailBean.getMode().setData_id("pd");
				detailBean.getChoix().setData_id("arb");
			}
			
			if(serviceArticle.doCreateRowData(detailBean)){
			removeObjectModel(FORM_BEAN); 
			throwNewException("ins01");
			} 
	     	}catch(Exception e){
	     		displayException(e);
		    } 
	     	if(bs.getFct_id().equals("5"))
	     		return getViewAdd(FORM_SERVICE);
	     	else
		return getViewAdd(FORM_VIEW);
	}
	
	
	 
	
	public ModelAndView doAddAffectLieux( ArticleBean detailBean )   throws Exception  {
		try {
			
			if(detailBean==null){
				 setObjectValueModel("lisfS"  , new ArrayList() );
				 setObjectValueModel(FORM_BEAN, new LieuxArticleBean());
				return getViewLieux(FORM_VIEW_LIEUX);
			}
			List lisf=(List) getObjectValueModel("lisfS"   );
			serviceArticle.doAffectlieuxarticle(lisf);
			 
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewLieux(FORM_VIEW_LIEUX);
	}
	
	public ModelAndView doAddAffectProduit_client( ClientArticleBean detailBean )   throws Exception  {
		try {
			
			if(detailBean==null){
				 setObjectValueModel("list_produit_client"  , new ArrayList() );
				 setObjectValueModel(FORM_BEAN, new ClientArticleBean());
				return getViewProduit_client(FORM_VIEW_CLIENT);
			}
			List lisf=(List) getObjectValueModel("list_produit_client"   );
			serviceArticle.doAffect_client_article(detailBean,lisf);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewProduit_client(FORM_VIEW_CLIENT);
	}
	
	
	
	public  static  ModelAndView doCancelInsertPrix() {
		Object  obj=getObjectValueModel(ORIGINAL_FORM_BEAN);
		setObjectValueModel(FORM_BEAN, obj);
		return getViewPrix_ar((String) getObjectValueModel("FORM_VIEW"));
	}
	

	public ModelAndView doUpdateData(ArticleBean beanUpBean) {
		try {
			setObjectValueModel("moda", "tout");
			String dataSocieteLng_ar= getRequest().getParameter("dataSocieteLng_ar");
			String dataSocieteLng_en= getRequest().getParameter("dataSocieteLng_en");
			JSONObject json        = new JSONObject();
			json.put("ar",  convertStringToHashMap(dataSocieteLng_ar));
			json.put("en", convertStringToHashMap(dataSocieteLng_en));
			String data_article_langue=json.toString();
			beanUpBean.setData_article_langue(data_article_langue);
			serviceArticle.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	public ModelAndView doUpdateDataSituation(ArticleBean beanUpBean) {
		try {
			setObjectValueModel("moda", "situ");
			serviceArticle.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(ArticleBean beanDelBeans) {
		try {
			 
			ArticleBean beanDelBean = (ArticleBean) getObjectValueModel(FORM_BEAN);
			serviceArticle.doDeleteRowData(beanDelBean);
			remove_row_from_list(LIST_DATA);
			removeObjectModel(FORM_BEAN);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
