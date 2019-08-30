package ERP.Process.Commerciale.Achat.Reception_achat.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.service.Reception_achatService;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Achat.Retour_achat.template.Retour_achatTemplate;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Demande_Achat.model.Demande_achatBean;
import ERP.Process.Commerciale.Demande_Achat.model.Det_demande_AchatBean;
import ERP.Process.Commerciale.Demande_Achat.service.Demande_AchatService;
import ERP.Process.Commerciale.Demande_Achat.template.Demande_AchatTemplate;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.ResponsableLieu.model.ResponsableLieuBean;
import ERP.Process.Commerciale.Stock.ResponsableLieu.service.ResponsableLieuService;
import ERP.Process.Commerciale.TarificationPrtvArticle.dao.TarificationPrtvArticleDAO;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.service.DeviseService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.dao.UniteDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel.WriteExcel;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class Reception_achatActionManager extends Reception_achatTemplate {
 
  
	private static final long serialVersionUID = 1L;

	

	private Reception_achatService serviceReception_achat;
	@Autowired
	public void setServiceReception_achat(
			Reception_achatService serviceReception_achat) {
		this.serviceReception_achat = serviceReception_achat;
	}
	
	@Autowired Demande_AchatService   serviceDemande_Achat;
	
	private DocumentLotService serviceDocumentLot;

	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
		this.serviceDocumentLot = serviceDocumentLot;
	}
	 
	
	@Autowired
	FournisseurService     serviceFournisseur;
	
	
	@Autowired
	private DeviseService     serviceDevise;
	
	
	private      DepotStockageService       serviceDepotStockage;
	@Autowired
	public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
		this.serviceDepotStockage = serviceDepotStockage;
	}
	
	
    public   Code_barreService serviceCode_barre;
	@Autowired
	public void setServiceCode_barre(Code_barreService serviceCode_barre) {
		this.serviceCode_barre = serviceCode_barre;
	}
	
	
	private TarificationPrtvArticleDAO daoTarificationPrtvArticle;
	@Autowired
	public void setDaoTarificationPrtvArticle(TarificationPrtvArticleDAO daoTarificationPrtvArticle) {
		this.daoTarificationPrtvArticle = daoTarificationPrtvArticle;
	}
	 
	
	
	private UniteDAO daoUnite;
	@Autowired
	public void setDaoUnite(UniteDAO daoUnite) {
		this.daoUnite = daoUnite;
	}
	
	private ResponsableLieuService  serviceResponsableLieu;
	@Autowired
	public void setServiceResponsableLieu(ResponsableLieuService serviceResponsableLieu) {
	    this.serviceResponsableLieu = serviceResponsableLieu;
	} 
	 
	
	    
	
	public    ModelAndView doInitServletAction() {

			
		
		try {
			
			 setObjectValueModel(FORM_BEAN   , getObjectValueModel(MODEL_BEAN));
			 setObjectValueModel(SEARCH_BEAN , getObjectValueModel(MODEL_BEAN));
			 removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			 BeanSession bs      = (BeanSession)getObjectValueModel(BEAN_SESSION);
			 List listfournisseur= serviceFournisseur.dofetchDatafromServer(FournisseurBean.class.newInstance());
			 setObjectValueModel(LIST_FOURNISSEUR_RECEP_ACHAT , listfournisseur);
			 
			 
			 ResponsableLieuBean  resLieu =  new ResponsableLieuBean();
			 resLieu.getPk().getUsr().setUsr_id(bs.getUsr_id());
			 List <ResponsableLieuBean> listReslieux=serviceResponsableLieu.doFetchDatafromServer(resLieu);
			 if(listReslieux==null ||  listReslieux.size()==0)
				 throwNewException(" Utilisateur non affecté ");
			 List  listLieux= new ArrayList();
			 
			 DepotStockageBean  depot = null;
			 for(ResponsableLieuBean res:listReslieux){
				  if(depot==null) depot=res.getPk().getDepot();
				 listLieux.add(res.getPk().getDepot());
			 }
			 
			// setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			 
			 setObjectValueModel(LIST_DEPOT_STOCK , listLieux);
			 
			/// setObjectValueModel(LIST_DEPOT_STOCK               , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			 
			 
			 
			 
			 setObjectValueModel(LIST_EDITABLE_RECEP_ACHAT      , new ArrayList<Det_reception_achatBean>());
			 setObjectValueModel(HASHMAP_FRS, ProcessUtil.getHashMap_code_bean(listfournisseur, "frs_id"));
			 
			 
			 List list_devise=serviceDevise.doFetchDatafromServer(new DeviseBean());
			 setObjectValueModel("list_devise", list_devise);
			 
		     HashMap  map_devise=ProcessUtil.getHashMap_code_bean(list_devise, "dev_id");
		     setObjectValueModel("map_devise", map_devise);
			 
			 
			 setObjectValueModel("PDF_IS_CMD", "NON");
			 doLoadingLibelleOtherSModule("57");
			 doLoadingLibelleOtherSModule("118");
			 doLoadingLibelleOtherSModule("140");
			 doLoadingLibelleOtherSModule(Retour_achatTemplate.ID_SOUS_MODULE);
			 
			 
		 
			
			if (bs.getFct_id().equals(Fn_Servir)  ||  bs.getFct_id().equals(Fn_Accueillir)  ||  bs.getFct_id().equals(Fn_Contremander) ){
				setObjectValueModel("PDF_IS_CMD", "OUI");
				setObjectValueModel(MAP_FIELD_BEAN, Demande_AchatTemplate.MapfieldBean);
				bs.setSousmod_libelle_title(  (String) getObjectValueModel("srv_dem_achat"));
				setObjectValueModel("LIST_VIEW", LIST_VIEW_SERVIR);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_DEM);
				setObjectValueModel("nameGrid", NAME_GRID_DEM);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_SERVIR");
				return getViewFilterAjax_Servir(FILTER_VIEW_CMD);
			}
			
			if (bs.getFct_id().equals(Fn_appliquer)){
				bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_achat"));
				setObjectValueModel("LIST_VIEW", LIST_VIEW_SERVIR);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_DEM);
				setObjectValueModel("nameGrid", NAME_GRID_DEM);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_SERVIR");
				doLoadingLibelleOtherSModule("57");
				bs.setSousmod_libelle_title(" Demande Achat");
			}
			
			if (bs.getFct_id().equals(Fn_Nouveau) || bs.getFct_id().equals(Fn_Créer)  ) {
				 Reception_achatBean rBeanS = new Reception_achatBean();
				   
				 rBeanS.setAchat_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
				 rBeanS.setDepot(depot);
				 rBeanS.setDevise(bs.getSociete().getDevise());
				 setObjectValueModel(FORM_BEAN,rBeanS);
				 return getViewAdd(FORM_VIEW_EDIT);
				
			} else {
				
				
				  
				return getViewFilterAjax(FILTER_VIEW);
			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	public    ModelAndView doResetFormEdit() throws Exception {
		
		try {
			setObjectValueModel(LIST_ARTICLE_RECP_ACHAT    , new ArrayList());
			setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_ORIGINE, new ArrayList());
			setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_GRID   ,  new ArrayList());
			Reception_achatBean rBeanS = new Reception_achatBean();
			rBeanS.setAchat_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
			setObjectValueModel(FORM_BEAN,rBeanS);
		} catch (ParseException e) {
			throw e;
		}
	
		
		return getViewAdd(FORM_VIEW_EDIT);
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchArticleSuivantTarif(  Reception_achatBean detailBean  )throws Exception { 
		
		    PrintWriter out = getResponse().getWriter();
	        getResponse().setContentType(HTML_CONTENT_TYPE);
	     
	    try {
	    	TarificationPrtvArticleBean beanTarif= new  TarificationPrtvArticleBean();
		    HashMap mapfrs= (HashMap) getObjectValueModel(HASHMAP_FRS);
		    FournisseurBean  bean =(FournisseurBean) mapfrs.get(detailBean.getFrsBean().getFrs_id());
		    beanTarif.setGroupe(bean.getGroupe());
		    beanTarif.setDate_prim_trf(detailBean.getAchat_date());
		    HashMap   map_resul=daoTarificationPrtvArticle.doFindListTarificationPrtvArticle_ACHAT_DateMax(beanTarif);
		    List ListcodeBarr=(List) map_resul.get("list_des_reference");
		    setObjectValueModel(MAP_TARIFICATION, (HashMap) map_resul.get("mapTarif"));
			setObjectValueModel(LIST_ARTICLE_RECP_ACHAT    , ListcodeBarr);
			setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_ORIGINE, ProcessUtil.cloneList(ListcodeBarr));
			setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_GRID   , ProcessUtil.cloneList(ListcodeBarr));
			setObjectValueModel(FORM_BEAN, detailBean);
			getResponse().getWriter().print("erreur");
	    } catch (Exception e) {
	    	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
	    return null;
		
		

	}

	
	 
	public static ModelAndView doActualiser_List( ) throws Exception{
		  
		
		
		try {
			
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_RECEP_ACHAT);
			
			List list_article_recp_achat_origine =(List) getObjectValueModel(LIST_ARTICLE_RECP_ACHAT_ORIGINE);
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_reception_achatBean  achatBean= (Det_reception_achatBean) listOfmyData.get(i);
				String ar_id=(String) getValueObjSimpleFromList
				(achatBean.getPk().getFkCode_barre().getPk().getCode_barre(), list_article_recp_achat_origine, "pk.code_barre", "pk.ar_bean.pk_article.ar_id");
				achatBean.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().setAr_id(ar_id);
			}
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkCode_barre.pk.code_barre");
			List list_article_achatGrid = new ArrayList();
			List list_article_achat = new ArrayList();
			for (int i = 0; i < list_article_recp_achat_origine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_recp_achat_origine.get(i);
				
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_GRID,list_article_achatGrid);
					setObjectValueModel(LIST_ARTICLE_RECP_ACHAT,list_article_achat);
				}
				
			}
			getResponse().setContentType("text");
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
public static ModelAndView doActualiser_GRID( ) throws Exception{
		
		try {
			
			HashMap  map_Tarification=(HashMap) getObjectValueModel(MAP_TARIFICATION); 
			List listOData=(List) getObjectValueModel(LIST_EDITABLE_RECEP_ACHAT);
			JsonObject data = new JsonObject();
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		  
			for (int i = 0; i < listOData.size(); i++) {
				Det_reception_achatBean  newBean= (Det_reception_achatBean) listOData.get(i);
				
				
				data.addProperty("erreur"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),"");
				TarificationPrtvArticleBean  tarif  =(TarificationPrtvArticleBean) 
				map_Tarification.get(newBean.getPk().getFkCode_barre().getPk().getCode_barre());
				
		    	if(tarif!=null){
		    		
		    		if( newBean.getQuantite() == null  ) continue;
		    		if( newBean.getQuantite() < 0 ) {
		    			newBean.setQuantite(new Double(0));
		    			String err=" Il existe un ou plusieurs quantité(s) inférieur a zéro ";
		    			data.addProperty("erreur"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getPk().getFkCode_barre().getPk().getCode_barre(),"0");
		    			continue;
		    		}
		    		
		    		if(  bs.getFct_id().equals(Fn_Servir) && newBean.getQuantite_demander()!=null ){
		    			
		    		if( newBean.getQuantite() >  newBean.getQuantite_demander()   ) {
		    			newBean.setQuantite(new Double(0));
		    			String err=" Il existe un ou plusieurs quantité(s) supérieur  a la quantité  demandées   ";
		    			data.addProperty("erreur"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getPk().getFkCode_barre().getPk().getCode_barre(),"0");
		    			continue;
		    		}
		    		}
		    		
		    		 
		    		
		    		
		    		double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(tarif.getTarif_unit_article());
		    		double qte=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getQuantite());
		    		
		    		 
		    		if(priUnitachat!=newBean.getPrix_achat_origin().doubleValue()) {
		    			newBean.setPrix_achat_is_changed(true);
		    		}
		    		
		    		double montant_ht_achat=ProcessNumber.PRODUIT(priUnitachat, qte);
		    		newBean.setMontant_ht_achat(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat));
		    		
		    		
		    		Double montant_tva_achat=ProcessNumber.Pourcentage(montant_ht_achat, tarif.getTvaBean().getTva_value());
	    		           montant_tva_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_achat);
	    		           newBean.setMontant_tva_achat(montant_tva_achat);
	    		           
	    		           
		    		 Double number=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat);          
		    		 String Elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(number)  ;     
		    		 data.addProperty(newBean.getPk().getFkCode_barre().getPk().getCode_barre(),Elm);
		    		 newBean.setTarif(tarif);
		    	     
	    				         
		    	}
		     	 
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
	public static ModelAndView doCheked_unCheked( ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_RECEP_ACHAT);
			String to_check=getRequest().getParameter("statucheked")==null?"":getRequest().getParameter("statucheked");
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_reception_achatBean newBean =(Det_reception_achatBean) listOfmyData.get(i);
				newBean.setTo_check(to_check);
			}
			getResponse().setContentType(HTML_CONTENT_TYPE);
			} catch (JsonIOException e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
	@SuppressWarnings("unchecked")
	public   ModelAndView doAdd_row_EditableDataTableAjax( ) throws Exception{
		  
		
		
		try {
			
			List listOfmyData  =(List) getObjectValueModel(LIST_EDITABLE_RECEP_ACHAT);
			String code_barre  =getRequest().getParameter("ar_id")==null?"":getRequest().getParameter("ar_id");
			String designation =getRequest().getParameter("designation")==null?"":getRequest().getParameter("designation");
			String quantite    =getRequest().getParameter("quantite")==null?"":getRequest().getParameter("quantite");
			String observation           =getRequest().getParameter("observation")==null?"":getRequest().getParameter("observation");
			String date_utilisationx     =getRequest().getParameter("date_utilisationx")==null?"":getRequest().getParameter("date_utilisationx");
			String date_peremx           =getRequest().getParameter("date_peremptionx")==null?"":getRequest().getParameter("date_peremptionx");
			
			
			 
			List list_article_dem_achatOrigine =(List) getObjectValueModel(LIST_ARTICLE_RECP_ACHAT_ORIGINE);
			
			HashMap  mapdA=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkCode_barre.pk.code_barre");
			
			if(mapdA.get(code_barre)!=null)  throw new Exception("Existe déjà");
			
			
			HashMap  MAP_ARTICLE=ProcessUtil.getHashMap_code_bean(list_article_dem_achatOrigine, "pk.code_barre");
			Code_barreBean  cBean=(Code_barreBean) MAP_ARTICLE.get(code_barre);
			
			Det_reception_achatBean newBean= new Det_reception_achatBean();
			newBean.getPk().setFkCode_barre(cBean);
			newBean.getPk().getFkCode_barre().setDesignation_libelle(designation);
			newBean.setQuantite(new Double(quantite));
			
			 if(!StringUtils.isEmpty(date_peremx))
			  newBean.setDate_peremption( ProcessDate.convert_String_to_Date(date_peremx) );
			 
			 if(!StringUtils.isEmpty(date_utilisationx))
			newBean.setDate_utilisation( ProcessDate.convert_String_to_Date(date_utilisationx) );
			 else
			newBean.setDate_utilisation( ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()) );	 
			 
			
			newBean.setObservation(observation);
			 
			
		 
			 
			HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION);
			TarificationPrtvArticleBean  ss  =(TarificationPrtvArticleBean) mapTarification.get(code_barre);
	    	if(ss!=null){
	    		Double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_article());
	    		
	    		newBean.setPrix_achat_origin(  priUnitachat );
	    		
	    		
	    		Double qte=ProcessFormatNbr.ConvertStringToDouble(quantite);
	    		Double montant_ht_achat=ProcessNumber.PRODUIT(priUnitachat, qte);
	    		       montant_ht_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat);
	    		newBean.setMontant_ht_achat(montant_ht_achat);
	    		Double montant_tva_achat=ProcessNumber.Pourcentage(montant_ht_achat, ss.getTvaBean().getTva_value());
	    		       montant_tva_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_achat);
	    		newBean.setMontant_tva_achat(montant_tva_achat);
	    		newBean.setTarif(ss) ;
	    	}
	    	
			listOfmyData.add(newBean);
			 setObjectValueModel(LIST_EDITABLE_RECEP_ACHAT,listOfmyData);
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkCode_barre.pk.code_barre");
			
			
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_dem_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_dem_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_dem_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_GRID,list_article_dem_achatGrid);
					setObjectValueModel(LIST_ARTICLE_RECP_ACHAT,list_article_dem_achat);
				}
				
			}
			
			getResponse().setContentType(HTML_CONTENT_TYPE);
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
	public static ModelAndView doDelete_row_EditableDataTableAjax( ) throws Exception{
		  
		
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_RECEP_ACHAT);
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				
				Det_reception_achatBean newBean= (Det_reception_achatBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
			}
			if(!del) throw new Exception ((String) getObjectValueModel("_cochezAumoin"));
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkCode_barre.pk.code_barre");
			List list_article_achatOrigine =(List) getObjectValueModel(LIST_ARTICLE_RECP_ACHAT_ORIGINE);
			List list_article_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
				}
				
			}
			setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_GRID,list_article_achatGrid);
			setObjectValueModel(LIST_ARTICLE_RECP_ACHAT,list_article_dem_achat);
			 
			getResponse().setContentType("text");
			 
			} catch (Exception e) {
					getResponse().setContentType(HTML_CONTENT_TYPE);
					PrintWriter out = getResponse().getWriter();
					out.println(e.getMessage());
				    out.close();
			}
			return null;
	}
	

	
	
	

	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(Reception_achatBean searchBean) throws Throwable {
		try {
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if(bs.getFct_id().equals(Fn_Consulter) && bs.getSousmod_id().equals("154")){
				searchBean.setCondition_etat_achat("  AND  bean.modeBean.fct_id in ('"+Fn_Conserver+"','"+Fn_reception+"')   ");
				
			}
				
			if(bs.getFct_id().equals(Fn_Supprimer)   ){
				searchBean.setCondition_etat_achat("  AND  bean.modeBean.fct_id  not in ('"+Fn_Facturer+"' )   ");
			}
			
			if(bs.getFct_id().equals(Fn_Modifier) ){
				searchBean.setCondition_etat_achat("  AND  bean.modeBean.fct_id  in ('"+Fn_Créer+"','"+Fn_Nouveau+"','"+Fn_Modifier+"','"+Fn_Servir+"')   ");
			}
			
			 
			if(bs.getFct_id().equals(Fn_Conserver) ){
			  searchBean.setCondition_etat_achat("  AND  bean.modeBean.fct_id  in ('"+Fn_Transférer+"','"+Fn_reception+"')   ");
			}
			
			
			if(bs.getFct_id().equals(Fn_reception) ){
				  searchBean.setCondition_etat_achat("  AND  bean.modeBean.fct_id  in ('"+Fn_Transférer+"')   ");
			 }
			
			if(bs.getFct_id().equals(Fn_Transférer) ){
				  searchBean.setCondition_etat_achat("  AND  bean.modeBean.fct_id  not in ('"+Fn_Transférer+"','"+Fn_Annuler+"','"+Fn_Conserver+"','"+Fn_reception+"') " +
				  		"                               AND  bean.achat_date   <= '"+BDateTime.getDateActuel_system()+"'  ");
				  
			 }
			
			
	        if( bs.getFct_id().equals(Fn_Annuler) && bs.getSousmod_id().equals("154")  ){
				
				searchBean.setCondition_etat_achat("    AND  bean.modeBean.fct_id in ('"+Fn_reception+"')   ");
				
			}
			
			if(bs.getFct_id().equals(Fn_Annuler) && !bs.getSousmod_id().equals("154")){
				  searchBean.setCondition_etat_achat("  AND  bean.modeBean.fct_id  not in ('"+Fn_Transférer+"','"+Fn_Annuler+"','"+Fn_Conserver+"','"+Fn_reception+"') " +
				  		"                               AND  bean.achat_date   <= '"+BDateTime.getDateActuel_system()+"'  ");
			 }
			
			 if( bs.getFct_id().equals(Fn_Confirmer)   ){
					
			 searchBean.setCondition_etat_achat("    AND  bean.modeBean.fct_id in ('"+Fn_Nouveau+"','"+Fn_Servir+"','"+Fn_Modifier+"'" +
			 		" ,'"+Fn_Transférer+"','"+Fn_reception+"' )   ");
					
			 }
			 
			 if( bs.getFct_id().equals(Fn_Corriger)   ){
				 searchBean.setCondition_etat_achat("    AND  bean.modeBean.fct_id   in ('"+Fn_Confirmer+"'  )   ");
			  }
			 
			 
			List listDataSrv = serviceReception_achat.doFetchDatafromServer(searchBean);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
			
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	 
 
	
	
	
	public ModelAndView doFetchData_for_servir_demande(Reception_achatBean searchBean) throws Throwable {
		
		try {
            BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
            
            
            Demande_achatBean beandemBean =   searchBean.getDem_achat();
            
            
            
            if (bs.getFct_id().equals(Fn_Servir)){
            	
            	beandemBean.setCondition_etat("  AND  bean.modeBean.fct_id  in ('"+Fn_Valider+"','"+Fn_Créer+"','"+Fn_Modifier+"')   AND    bean.dem_date <= '"+BDateTime.getDateActuel_system()+"' ");
			}
			
			if (bs.getFct_id().equals(Fn_appliquer)){
				beandemBean.setCondition_etat("  AND  bean.modeBean.fct_id  in ('16')   ");
			}
			
		 
			
			setObjectValueModel(NAME_LIST_G,LIST_DATA_DEM);
			List<Demande_achatBean> listdemande=serviceDemande_Achat.dofetchDatafromServer(beandemBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listdemande);
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	public    ModelAndView doRetourToList_SERVIR() {
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_achat"));
			setObjectValueModel("LIST_VIEW", LIST_VIEW_SERVIR);
			setObjectValueModel(NAME_LIST_G ,LIST_DATA_DEM);
			setObjectValueModel("nameGrid", NAME_GRID_DEM);
			 
			setObjectValueModel(MAP_FIELD_BEAN, Demande_AchatTemplate.MapfieldBean);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_SERVIR");
		} catch (Exception e) {
			displayException(e);
		}
		
		return getViewFilterAjax_Servir(FILTER_VIEW_CMD);
	}

	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculer_Total() throws Exception {
		
		try {
			 Reception_achatBean rowBean = (Reception_achatBean) getObjectValueModel(FORM_BEAN);
			
			 
			 List <Det_reception_achatBean>List_det_recep_achat= new ArrayList<Det_reception_achatBean>();
			
			 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 if(bs.getFct_id().equals(Fn_Consulter)){
				 List_det_recep_achat   =serviceReception_achat.doFetchDeatil_reception_fromServer(rowBean);
			 }else{
				 List_det_recep_achat   =(List) getObjectValueModel(LIST_EDITABLE_RECEP_ACHAT);
			 }
			setObjectValueModel(LIST_EDITABLE_RECEP_ACHAT   , List_det_recep_achat);
			double tot_ht=0;
			double tot_tva=0;
			Double tot_qte=new Double(0);
			
			for (int i = 0; i < List_det_recep_achat.size(); i++) {
				Det_reception_achatBean  bean=List_det_recep_achat.get(i);
				tot_ht=ProcessNumber.addition(tot_ht, bean.getMontant_ht_achat());
				tot_tva=ProcessNumber.addition(tot_tva, bean.getMontant_tva_achat());
				tot_qte=ProcessNumber.addition(tot_qte, bean.getQuantite());
			}
			
			
			double avance_montant_achat   = ProcessFormatNbr.FormatDouble_Troischiffre(rowBean.getAvance_montant_achat());
			
			double total_mnt_genX   = ProcessNumber.addition(tot_ht, tot_tva);
			
			double total_mntAPayer  = ProcessNumber.SOUSTRACTION(total_mnt_genX, avance_montant_achat);
			 
			
			Reception_achatBean  reBean= new Reception_achatBean();
			reBean.setTotal_mnt_ht(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tot_ht));
			reBean.setTotal_mnt_tva(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tot_tva));
			reBean.setAvance_montant_achat(avance_montant_achat);
			
			reBean.setTotal_mnt_gen(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mntAPayer));
			
			reBean.setTotal_quantite(ProcessFormatNbr.Convert_using_Double_toString(tot_qte));
			
			setObjectValueModel(BEAN_TOTAL, reBean);
			
			JsonObject data = new JsonObject();
			data.addProperty("UU",reBean.getTotal_quantite());
		    data.addProperty("k", reBean.getTotal_mnt_ht());
		    data.addProperty("o",  reBean.getTotal_mnt_tva());
		    
		    data.addProperty("avance", ProcessFormatNbr.FormatDouble_To_String_Troischiffre(avance_montant_achat));
		    
		    data.addProperty( "p"    ,  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mntAPayer));
		    
		    getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(data.toString());
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	public    ModelAndView doSelectDetailleRow () throws Exception {

		
		
		String elmentSansTArif=""; 
	 
		try {
			
			String  demandeId="";
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			removeObjectModel(FORM_BEAN);
		
			List <Det_reception_achatBean> List_det_recep_achat = new ArrayList<Det_reception_achatBean>();
			
			
			if (bs.getFct_id().equals(Fn_Servir)){
				
				 elmentSansTArif="";
				 setObjectValueModel(MAP_FIELD_BEAN, Demande_AchatTemplate.MapfieldBean);
				 Demande_achatBean rowBean = (Demande_achatBean) getIndexFromDataGrid_v1(LIST_DATA_DEM);
				 setObjectValueModel(FORM_BEAN_DEMANDE, rowBean);
				 Reception_achatBean rBeanS = new Reception_achatBean();
				 rBeanS.setDem_achat((Demande_achatBean) rowBean);
				 rBeanS.setAchat_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
				 setObjectValueModel(FORM_BEAN,rBeanS);
				 List<Det_demande_AchatBean>  litdeatil=serviceDemande_Achat.dofetch_detaille_demande_achat((Demande_achatBean) rowBean);
				 
				 
				 TarificationPrtvArticleBean beanTarif= new  TarificationPrtvArticleBean();
				 HashMap mapfrs= (HashMap) getObjectValueModel(HASHMAP_FRS);
				 FournisseurBean  bean =(FournisseurBean) mapfrs.get(rowBean.getFrsBean().getFrs_id());
				 beanTarif.setGroupe(bean.getGroupe());
				 beanTarif.setDate_prim_trf(rBeanS.getAchat_date());
				 HashMap   map_resul=daoTarificationPrtvArticle.doFindListTarificationPrtvArticle_ACHAT_DateMax(beanTarif);
				 List ListcodeBarr=(List) map_resul.get("list_des_reference");
				 setObjectValueModel(MAP_TARIFICATION, (HashMap) map_resul.get("mapTarif"));
				  
				 for (int i = 0; i < litdeatil.size(); i++) {
					 Det_demande_AchatBean bAchatBean = litdeatil.get(i);
					 Det_reception_achatBean newBean = new Det_reception_achatBean();
					 newBean.getPk().setFkCode_barre(bAchatBean.getPk_det_dem_achat().getFkCode_barre());
					 newBean.setQuantite_demander(bAchatBean.getQuantite());
					 newBean.setQuantite(bAchatBean.getQuantite());
					 HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION);
				     TarificationPrtvArticleBean ss  =(TarificationPrtvArticleBean) mapTarification.get(bAchatBean.getPk_det_dem_achat().getFkCode_barre().getPk().getCode_barre());
				     
				     
				     
				     if(ss!=null){
				          
				    	 Double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_article());
				    	 Double qte=bAchatBean.getQuantite();
				    	 Double montant_ht_achat=ProcessNumber.PRODUIT(priUnitachat, qte);
				    	 montant_ht_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat);
				    	 newBean.setMontant_ht_achat(montant_ht_achat);
				    	 newBean.setTarif(ss)	; 
				    	 Double montant_tva_achat=ProcessNumber.Pourcentage(montant_ht_achat, ss.getTvaBean().getTva_value());
				    	 montant_tva_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_achat);
				    	 newBean.setMontant_tva_achat(montant_tva_achat);
				    	 newBean.setDate_utilisation( ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()) );	 
				    	 List_det_recep_achat.add(newBean);
				    	 
				    	}else{
				    		elmentSansTArif=elmentSansTArif+""+bAchatBean.getPk_det_dem_achat().getFkCode_barre().getPk().getCode_barre()+" / "+
				    		 bAchatBean.getPk_det_dem_achat().getFkCode_barre().getDesignation_libelle()+" <br> ";
				    	}
				 }
				 
				 	if(!StringUtils.isEmpty(elmentSansTArif)){
					     throwNewException(" Manque Tafification pour ce(s) articles :<bR> "+elmentSansTArif);
				     }
				 
				 
			}else{
				
				 Reception_achatBean  rowBeans = (Reception_achatBean) getIndexFromDataGrid_v1(LIST_DATA);
				 if(rowBeans.getDem_achat()!=null){
					 demandeId=rowBeans.getDem_achat().getDem_achat_id();
				 }
				 
				 List_det_recep_achat=serviceReception_achat.doFetchDeatil_reception_fromServer(rowBeans);
				 HashMap  map_reception=ProcessUtil.getHashMap_code_bean(List_det_recep_achat, "pk.fkCode_barre.pk.code_barre");
				 setObjectValueModel(FORM_BEAN, rowBeans);
				 HashMap  mapTeste= new HashMap();
				 
				 
				 if (bs.getFct_id().equals(Fn_Confirmer)){ 
						Reception_achatBean   achatBean=  new Reception_achatBean();
						achatBean.setCondition_etat_achat(" " +
						 		" AND  bean.modeBean.fct_id in ('"+Fn_Servir+"','"+Fn_Créer+"','"+Fn_Modifier+"','"+Fn_Transférer+"','"+Fn_Contremander+"')  " +
						 		" AND  bean.achat_date < '"+ProcessDate.getStringFormatDate(rowBeans.getAchat_date())+"' ");
						List list= serviceReception_achat.doFetchDatafromServer(achatBean);
						if(list!= null  &&    list.size()>0){
							//throwNewException(" il existe des vente non encore confirmer a une date inférieur a :"+ProcessDate.getStringFormatDate(rowBeans.getAchat_date()));
						}
					 } 
			}
				 
				 
				 
			
			setObjectValueModel(LIST_EDITABLE_RECEP_ACHAT         ,  List_det_recep_achat);
			setObjectValueModel(LIST_EDITABLE_RECEP_ACHAT_CLONE   ,  ProcessUtil.cloneList(List_det_recep_achat) );
			
			if (bs.getFct_id().equals(Fn_Consulter) && demandeId.equals("") )
				return getViewConsultA(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Consulter) && !demandeId.equals("") )
				return getViewConsultA(FORM_VIEW_DEM);
			
			
			if ( bs.getFct_id().equals(Fn_Modifier) && demandeId.equals("")   )
				return getViewUpdate(FORM_VIEW_EDIT);
			
			if ( bs.getFct_id().equals(Fn_Corriger)  )
				return getViewCorriger(FORM_VIEW_EDIT);
			
			
			if ( bs.getFct_id().equals(Fn_Modifier) && !demandeId.equals("")   )
				return getViewModif_Srv(FORM_SERVIR_DEMANDE_ACHAT);
			
			
			if (bs.getFct_id().equals(Fn_Transférer) && demandeId.equals("")   )
				return getViewTransferer(FORM_VIEW);
		 
			if (bs.getFct_id().equals(Fn_Transférer) && !demandeId.equals("")   )
				return getViewTransferer(FORM_VIEW_DEM);
			
			
			
			if (bs.getFct_id().equals(Fn_Annuler) && demandeId.equals("")   )
				return getViewAnnuler(FORM_VIEW);
				
			if (bs.getFct_id().equals(Fn_Annuler) && !demandeId.equals("")   )
				return getViewAnnuler(FORM_VIEW_DEM);
			
			
			
			if (bs.getFct_id().equals(Fn_reception) && demandeId.equals("")   )
				return getView_reception(FORM_VIEW);
				
			if (bs.getFct_id().equals(Fn_reception) && !demandeId.equals("")   )
				return getView_reception(FORM_VIEW_DEM);
			
			 
			
			
			if (bs.getFct_id().equals(Fn_Conserver)|| bs.getFct_id().equals(Fn_Confirmer))
				return getViewValider(FORM_VIEW);
			
			 
			
			if (bs.getFct_id().equals(Fn_Servir)){
				bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_achat"));
				return getViewServir_demande(FORM_SERVIR_DEMANDE_ACHAT);
			}
			
			
			if (bs.getFct_id().equals("17")){
				bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_achat"));
				return getViewUpdate(FORM_VIEW_EDIT);
			}
			
			 
			if (bs.getFct_id().equals("4"))
				return getViewDelete(FORM_VIEW);
			
		}catch (Exception e) {
			displayException(e);
		}
		
		if(StringUtils.isEmpty(elmentSansTArif)){
			
		return getViewFilterAjax(FILTER_VIEW);
		
		}else{
			setObjectValueModel("PDF_IS_CMD", "OUI");
			
			doLoadingLibelleOtherSModule("57");
			
			setObjectValueModel(MAP_FIELD_BEAN, Demande_AchatTemplate.MapfieldBean);
			setObjectValueModel("LIST_VIEW", LIST_VIEW_SERVIR);
			setObjectValueModel(NAME_LIST_G ,LIST_DATA_DEM);
			setObjectValueModel("nameGrid", NAME_GRID_DEM);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_SERVIR");
			return getViewFilterAjax_Servir(FILTER_VIEW_CMD);
	 
		}

	}
	
	 
	
	
	public static  ModelAndView doPrintPDF_detaille_Reception_achat() throws Exception   {
		 
		List   lisData=  (List) getObjectValueModel(LIST_EDITABLE_RECEP_ACHAT) ;
		Reception_achatBean  denBean= (Reception_achatBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+LIST_EDITABLE_RECEP_ACHAT+getRequest().getSession().getId()+".pdf");
	    FileOutputStream fs = new FileOutputStream(file);
	    GeneratePdf  genpdf= new GeneratePdf();
	     
		try {
			
			Document document=GeneratePdf.doGenerateDocumentFormat();
	        PdfPTable table = new PdfPTable(MapfieldBean_detaille.length);
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION); 
	        genpdf.doWriteHeaderDocument_PDF(document,fs,bSession);
	        doWriteEntete_reception_achat(document,denBean);
	    	Reception_achatBean rBeanS=(Reception_achatBean) getObjectValueModel(FORM_BEAN);
	        genpdf.doWriteTitle_Table(document,"Bon de réception N° "+rBeanS.getAchat_id());
	        genpdf.doWrite_Header_Table(table,MapfieldBean_detaille);
	        genpdf.doWrite_Data_Table(lisData,table,MapfieldBean_detaille);
	        document.add(table);
	        doWriteTotal_det__reception_achat(document,denBean,MapfieldBean_detaille);  
	        
	        
	       
	        document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write(LIST_EDITABLE_RECEP_ACHAT+getRequest().getSession().getId()+".pdf");
		} catch (Exception e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

     }
	
	private static void doWriteEntete_reception_achat(Document document,
			Reception_achatBean denBean) throws Exception {
	PdfPTable tabletitle = new PdfPTable(100);
	
    PdfPCell cell = new PdfPCell(new Phrase("Date ",GeneratePdf.FONT_12_bold));
    cell.setColspan(17);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tabletitle.addCell(cell);
    
    cell = new PdfPCell(new Phrase(  ": "+ProcessDate.getCurrentTimeStamp(denBean.getAchat_date()) ,GeneratePdf.FONT_12_normal));
    cell.setColspan(83);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tabletitle.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase("Heure ",GeneratePdf.FONT_12_bold));
    cell.setColspan(17);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tabletitle.addCell(cell);
    
    cell = new PdfPCell(new Phrase(  ": "+denBean.getTime_cre() ,GeneratePdf.FONT_12_normal));
    cell.setColspan(83);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tabletitle.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase("Fournisseur",GeneratePdf.FONT_12_bold));
    cell.setColspan(17);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tabletitle.addCell(cell);
    
    cell = new PdfPCell(new Phrase( ": "+denBean.getFrsBean().getFrsref() ,GeneratePdf.FONT_12_normal));
    cell.setColspan(83);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tabletitle.addCell(cell);
    document.add(tabletitle);
		
	}
	
	
	private static void doWriteTotal_det__reception_achat(Document document,Reception_achatBean denBean,String [][] mapFieldBean) throws Exception {
		
		try {
			 
           
           PdfPTable tableTOTAL = new PdfPTable(100);
           tableTOTAL.setWidthPercentage(90);
           
           PdfPCell cellS = new PdfPCell(new Phrase( "  Total"  ,GeneratePdf.SMALLBOLD));
           cellS.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellS.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
           cellS.setPaddingBottom(1);
           cellS.setColspan(100);
           cellS.setFixedHeight(17f);
           cellS.setBackgroundColor(BaseColor.WHITE);
           cellS.setBorderWidth(1f);
           cellS.setBorder(cellS.LEFT+cellS.RIGHT);
           tableTOTAL.addCell(cellS);
           Reception_achatBean  reBean= (Reception_achatBean) getObjectValueModel(BEAN_TOTAL);
       	
           PdfPCell cell = new PdfPCell(new Phrase(" Total Qté",GeneratePdf.FONT_12_bold));
           cell.setColspan(50);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           tableTOTAL.addCell(cell);
           
           cell = new PdfPCell(new Phrase( reBean.getTotal_quantite() ,GeneratePdf.FONT_12_normal));
           cell.setColspan(8);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           tableTOTAL.addCell(cell);
          
           
           cell = new PdfPCell(new Phrase("Total H.T",GeneratePdf.FONT_12_bold));
           cell.setColspan(23);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setFixedHeight(20f);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           tableTOTAL.addCell(cell);
           
           cell = new PdfPCell(new Phrase(reBean.getTotal_mnt_ht(),GeneratePdf.FONT_12_normal));
           cell.setColspan(19);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           tableTOTAL.addCell(cell);
           
           
            
           
           cell = new PdfPCell(new Phrase("Total T.V.A",GeneratePdf.FONT_12_bold));
           cell.setColspan(81);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           tableTOTAL.addCell(cell);
           
           cell = new PdfPCell(new Phrase(reBean.getTotal_mnt_tva() ,GeneratePdf.FONT_12_normal));
           cell.setColspan(19);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           tableTOTAL.addCell(cell);
           
           
           cell = new PdfPCell(new Phrase("Total Général",GeneratePdf.FONT_12_bold));
           cell.setColspan(81);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           tableTOTAL.addCell(cell);
           
           cell = new PdfPCell(new Phrase(reBean.getTotal_mnt_gen() ,GeneratePdf.FONT_12_normal));
           cell.setColspan(19);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           tableTOTAL.addCell(cell);
            
           document.add(tableTOTAL);
       		
           
            
           
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public static  ModelAndView doExportXls_detaille_reception_achat() {
		try {

			    List        lisData       =  (List)getObjectValueModel(LIST_EDITABLE_RECEP_ACHAT) ;
			    WriteExcel  dbexp= new WriteExcel();
			    WorkbookSettings wbSettings = new WorkbookSettings();
			    wbSettings.setLocale(new Locale("en", "EN"));
			    BeanSession bSe = (BeanSession) getObjectValueModel(BEAN_SESSION);
			    String title= (String) getObjectValueModel("list-"+getObjectValueModel(ENTITES));
			    if(title==null || title.equals(""))  title="Listes des "+bSe.getSousmod_libelle();
			    getResponse().setHeader("Content-Disposition", "attachment; filename="+title+".xls");
			    WritableWorkbook workbook = Workbook.createWorkbook(getResponse().getOutputStream(), wbSettings);
			    workbook.createSheet("Report", 0);
			    WritableSheet excelSheet = workbook.getSheet(0);
			    
			     
			    Reception_achatBean rBeanS=(Reception_achatBean) getObjectValueModel(FORM_BEAN);
		        
			    setObjectValueModel("titleHead","Bon de réception N° "+rBeanS.getAchat_id()) ;
			    dbexp.createTitleMap(excelSheet,MapfieldBean_detaille);
			    dbexp.creatheaderMap(excelSheet,MapfieldBean_detaille);
			    dbexp.createContentWithList(excelSheet,lisData,MapfieldBean_detaille);
			    workbook.write();
			    workbook.close();
			 
			     
		} catch (Exception e) {
			displayException(e);
		}
		return null;

	}

	public ModelAndView doAddData(Reception_achatBean detailBean)
			throws Throwable {
		try {
			serviceReception_achat.doCreateRowData(detailBean);
			setObjectValueModel(FORM_BEAN, detailBean);
			throwNewException("Reception effectuée avec succès");
		} catch (Exception e) {
      	  displayException(e);
      	  if(e.getMessage().equals("Reception effectuée avec succès"))
      	  TransfertError(e);
		}
		return getViewAdd_Commit(FORM_VIEW_EDIT);
	}
	
	public ModelAndView doServirData(Reception_achatBean detailBean) throws Throwable {
		boolean retour=false;
		try {
			serviceReception_achat.doCreateRowData(detailBean);
			setObjectValueModel(FORM_BEAN, detailBean);
			throwNewException("ins01");
		} catch (Exception e) {
			if(e.getMessage().equals("ins01")) 
				retour=true; 
			displayException(e);
			
		}
		
		if(retour) {
			return getViewAdd_Commit(FORM_SERVIR_DEMANDE_ACHAT);
		}else{  
			return getViewServir_demande(FORM_SERVIR_DEMANDE_ACHAT );}
		}
	

	public ModelAndView doUpdateData(Reception_achatBean beanUpBean) {
		try {
			serviceReception_achat.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	
	public ModelAndView doCorrigerData(Reception_achatBean beanUpBean) {
		try {
			serviceReception_achat.doCorrigerRowData(beanUpBean);
			remove_row_from_list(LIST_DATA);
			removeObjectModel(FORM_BEAN);
			removeObjectModel(SEARCH_BEAN);
			throwNewException("validaTion Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	public ModelAndView doStockerData(Reception_achatBean beanUpBean) {
		try {
			serviceReception_achat.doStockRowData(beanUpBean);
			remove_row_from_list(LIST_DATA);
			removeObjectModel(FORM_BEAN);
			removeObjectModel(SEARCH_BEAN);
			throwNewException("validaTion Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	public ModelAndView doCommitData(Reception_achatBean beanUpBean) {
		try {
			serviceReception_achat.doStockRowData(beanUpBean);
			Reception_achatBean rBeanS = new Reception_achatBean();
			rBeanS.setAchat_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
			setObjectValueModel(FORM_BEAN,rBeanS);
			setObjectValueModel(LIST_EDITABLE_RECEP_ACHAT      , new ArrayList<Det_reception_achatBean>());
			throwNewException("Confirmation effectuée avec succès");
		} catch (Exception e) {
			displayException(e);
		}
		 return getViewAfterAdd(FORM_VIEW_EDIT);
	}
	
	public ModelAndView doAnnulerData(Reception_achatBean beanUpBean) {
		try {
			serviceReception_achat.doAnnulerRowData(beanUpBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("Annulation Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	public ModelAndView doTRansferer_Data(Reception_achatBean beanUpBean) {
		try {
			 
			serviceReception_achat.doTransfererRowData(beanUpBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("Tranferer Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	public ModelAndView doRecep_Data(Reception_achatBean beanUpBean) {
		try {
			Reception_achatBean  detailBeanS =(Reception_achatBean) getObjectValueModel(FORM_BEAN );
			serviceReception_achat.doTransfererRowData(detailBeanS);
			remove_row_from_list(LIST_DATA);
			throwNewException("Réception  Valider !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	
	
	public    ModelAndView doPrintPDF_Action()   throws Exception { 
		 
		try {
			BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION); 
			List   lisData                         =  new ArrayList();  
			String [][]    mapFieldBean            =  new String [1][1] ; 
			String    title ="";
	       
		    String PDF_IS_CMD= (String) getObjectValueModel("PDF_IS_CMD");
			 if(PDF_IS_CMD.equals("OUI")){
				  lisData                         =  (List) getObjectValueModel(LIST_DATA_DEM) ;
				  mapFieldBean            =  Demande_AchatTemplate.MapfieldBean;  
				  setObjectValueModel(NAME_LIST_G,LIST_DATA_DEM);
				   title= (String)getObjectValueModel("list-"+"57") ; 
				 
			 }else{
				  lisData                         =  (List) getObjectValueModel(LIST_DATA) ;
				  mapFieldBean            =   MapfieldBean; 
				  title= (String)getObjectValueModel("list-"+bSession.getSousmod_id()) ; 
			 }
		
		      GeneratePdf  genpdf= new GeneratePdf();
		
			  File file = new File(getRequest().getRealPath("/")+"/temp/"+(String)getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
		      FileOutputStream fs = new FileOutputStream(file);
		      
		      String [][]    map_critere_de_recherche=    (String[][]) getObjectValueModel(MAP_CRITERE_DE_RECHERCHE) ;
		      Object searchBean=getObjectValueModel(SEARCH_BEAN);
		      PdfPTable table = new PdfPTable(mapFieldBean.length);
		      Document document=GeneratePdf.doGenerateDocumentFormat();
		      genpdf.doWriteHeaderDocument_PDF(document,fs,bSession);
			  if(map_critere_de_recherche!=null && map_critere_de_recherche.length>0)
			  genpdf.doWriteCritere_de_recherche_Table(document, searchBean,map_critere_de_recherche);
			  genpdf.doWriteTitle_Table(document,title);
			  genpdf.doWrite_Header_Table(table,mapFieldBean);
			  genpdf.doWrite_Data_Table(lisData,table,mapFieldBean);
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
	
	
	public static  ModelAndView doExportXls_achat() {
		try {

			  BeanSession bSe= (BeanSession) getObjectValueModel(BEAN_SESSION); 
				List   lisData                         =  new ArrayList();  
				String [][]    mapFieldBean            =  new String [1][1] ; 
				 String    titleHead ="";
				 
				      
				   
			       
				String PDF_IS_CMD= (String) getObjectValueModel("PDF_IS_CMD");
				 if(PDF_IS_CMD.equals("OUI")){
					  lisData                         =  (List) getObjectValueModel(LIST_DATA_DEM) ;
					  mapFieldBean            =  Demande_AchatTemplate.MapfieldBean;  
					  setObjectValueModel(NAME_LIST_G,LIST_DATA_DEM);
					  titleHead= (String)getObjectValueModel("list-"+"57") ; 
					  if(titleHead==null)titleHead="";
					 
				 }else{
					  lisData                         =  (List) getObjectValueModel(LIST_DATA) ;
					  mapFieldBean            =   MapfieldBean; 
					  titleHead =(String)getObjectValueModel("list-"+bSe.getSousmod_libelle()) ;
					  if(titleHead==null)
					    	titleHead = (String)getObjectValueModel("list-"+bSe.getSousmod_id()) ;
					    if(titleHead==null)titleHead="";
				 }
				setObjectValueModel("titleHead",titleHead) ;
			    WriteExcel  dbexp= new WriteExcel();
			    WorkbookSettings wbSettings = new WorkbookSettings();
			    wbSettings.setLocale(new Locale("en", "EN"));
			     
			     
			    getResponse().setHeader("Content-Disposition", "attachment; filename="+titleHead+".xls");
			    WritableWorkbook workbook = Workbook.createWorkbook(getResponse().getOutputStream(), wbSettings);
			    workbook.createSheet("Report", 0);
			    WritableSheet excelSheet = workbook.getSheet(0);
			    dbexp.createTitleMap(excelSheet,mapFieldBean);
			    dbexp.creatheaderMap(excelSheet,mapFieldBean);
			    dbexp.createContentWithList(excelSheet,lisData,mapFieldBean);
			    workbook.write();
			    workbook.close();
		} catch (Exception e) {
			displayException(e);
		}
		return null;

	}

	protected ModelAndView doDeleteData(Reception_achatBean beanDelBean) {
		try {
			Reception_achatBean beanDean =(Reception_achatBean) getObjectValueModel(FORM_BEAN);
			serviceReception_achat.doDeleteRowData(beanDean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}






	
}
