package ERP.Process.Commerciale.Vente.Devis.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.service.TarificationService;
import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Client.template.ClientTemplate;
import ERP.Process.Commerciale.Vente.Devis.model.DetDevisBean;
import ERP.Process.Commerciale.Vente.Devis.model.DevisBean;
import ERP.Process.Commerciale.Vente.Devis.service.DevisService;
import ERP.Process.Commerciale.Vente.Devis.template.DevisTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
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

import com.google.gson.JsonObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class DevisActionManager extends DevisTemplate {
	 
	private static final long serialVersionUID = 838549513562291731L;
	
	private DevisService serviceDevis;
	@Autowired
	public void setServiceDevis(DevisService serviceDevis) {
		this.serviceDevis = serviceDevis;
	}
	
	private TVAService   serviceTVA;
	@Autowired
	public void setServiceTva(TVAService serviceTVA) {
		this.serviceTVA = serviceTVA;
	}
	
	/*private ClientService serviceClient;
	@Autowired
	public void setServiceClient(ClientService serviceClient) {
		this.serviceClient = serviceClient;
	}*/
	
	private ClientDAO daoClient;
	@Autowired
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}
	private TarificationService serviceTarification;
	@Autowired
	public void setTarificationService(TarificationService serviceTarification) {
		this.serviceTarification = serviceTarification;
	}
	
	public    TarificationDAO daoTarification;
	@Autowired
	public void setDaoTarification(TarificationDAO daoTarification) {
		this.daoTarification = daoTarification;
	}

	@Autowired Code_barreService      serviceCode_barre;

	@SuppressWarnings("unchecked")
	public   ModelAndView doInitServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		 
			
		
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		try {
			 doLoadingLibelleOtherSModule("5");
			 doLoadingLibelleOtherSModule("62");
			 doLoadingLibelleOtherSModule(ClientTemplate.ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule("75");
			 doLoadingLibelleOtherSModule("57");
		     doLoadingLibelleOtherSModule("118");
			 doLoadingLibelleOtherSModule("140");
			 doLoadingLibelleOtherSModule(ID_SOUS_MODULE);
			 
			 setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+DevisTemplate.ID_SOUS_MODULE));
			 setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+DevisTemplate.ID_SOUS_MODULE)) ;
			
			 List list_client_devis= daoClient.doFindListClient(ClientBean.class.newInstance());
			 setObjectValueModel(LIST_CLIENT_DEVIS, list_client_devis);
			 
			 List list_des_tva= serviceTVA.doFetchDatafromServer(TVABean.class.newInstance());
			 setObjectValueModel(LIST_DES_TVA, list_des_tva);
			 
			 HashMap  mapclientBen = ProcessUtil.getHashMap_code_bean(list_client_devis, "clt_id");
			 setObjectValueModel(MAP_CLIENT_BEN, mapclientBen);
			 List  <DetDevisBean>listGridEditable_Devis=  new  ArrayList<DetDevisBean>();
			 setObjectValueModel(LIST_EDITABLE_DEVIS, listGridEditable_Devis);
			 bs.setSousmod_libelle_title(bs.getSousmod_libelle());
		 

			if (bs.getFct_id().equals(Fn_Creer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
				
				DevisBean  devBean = new DevisBean();
				devBean.setDev_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()) );
				setObjectValueModel(FORM_BEAN, devBean);
				return getViewAdd(FORM_VIEW_EDIT);
			} else if(bs.getFct_id().equals(Fn_Contremander)){   
				
				
				bs.setSousmod_libelle_title((String)getObjectValueModel("cmd_frs"));
				setObjectValueModel(SEARCH_BEAN, new DevisBean());
				return getViewFilterAjax(FILTER_VIEW);
				
			} else {
				
				setObjectValueModel(SEARCH_BEAN, new DevisBean());
				return getViewFilterAjax(FILTER_VIEW);

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	public    ModelAndView doResetFormDev() {
		setObjectValueModel(LIST_EDITABLE_DEVIS, new  ArrayList<DetDevisBean>());
		setObjectValueModel(FORM_BEAN, new DevisBean());
		return getViewAdd( FORM_VIEW_EDIT );
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchSuivant(DevisBean searchBean)throws Exception { 
		
		PrintWriter out = getResponse().getWriter();
        getResponse().setContentType(HTML_CONTENT_TYPE);
         
        
      
        
        HashMap  mapclient = (HashMap)getObjectValueModel( MAP_CLIENT_BEN);
        ClientBean  ben=(ClientBean) mapclient.get(searchBean.getClient().getClt_id());
        
        
        
        
        
        TarificationBean tBean = new TarificationBean();
        tBean.setDate_trf(searchBean.getDev_date());
      
       
        List    list_Tarification_vente  =   daoTarification.doFind_DateMax_TarificationVente(tBean);
   	 	HashMap mapTarification_vente    =   serviceTarification.doTraiTerListTarif_vente(list_Tarification_vente);
   	 	HashMap mapTarific_vente         =   ProcessUtil.getHashMap_Key_List_FromList(list_Tarification_vente, "groupe.type_trf_id");
   	
   	 
   	 	setObjectValueModel(MAP_TARIFICATION,  mapTarification_vente);
   	 	setObjectValueModel(MAP_TOGET_LIST_TARIFICA, mapTarific_vente);
        
        
   	    HashMap  mapTypeTarif = (HashMap)getObjectValueModel(MAP_TOGET_LIST_TARIFICA);
        List listTarif=(List)mapTypeTarif.get(String.valueOf(ben.getTyp_trfBean().getType_trf_id()));
        List listTarif_public=(List)mapTypeTarif.get(GROUPE_TARIF_VENTE_PUBLIC); 
        
        if(listTarif==null) listTarif= new ArrayList();
        if(listTarif_public==null) listTarif_public= new ArrayList();
        
        HashMap  mapDeclient  = new HashMap();
        List  List_fetchDatafromSe= new ArrayList();
        for (int i = 0; i < listTarif.size(); i++) {
        	TarificationBean  ta=(TarificationBean)listTarif.get(i);
        	List_fetchDatafromSe.add(ta.getFkCode_barre());
        	String Key=ta.getFkCode_barre().getPk().getCode_barre();
        	String Client=(String) mapDeclient.get(Key);
        	if(Client==null){
        		mapDeclient.put(Key, "existe");
        	}
        }
        
        for (int i = 0; i < listTarif_public.size(); i++) {
        	TarificationBean  ta=(TarificationBean)listTarif_public.get(i);
        	String Key=ta.getFkCode_barre().getPk().getCode_barre();
        	String Client=(String) mapDeclient.get(Key);
        	if(Client==null){
        		List_fetchDatafromSe.add(ta.getFkCode_barre());
        	}
        }
	
         setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE        , List_fetchDatafromSe);
		 setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE_ORIGINE, ProcessUtil.cloneList(List_fetchDatafromSe));
		 setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE_GRID   , ProcessUtil.cloneList(List_fetchDatafromSe));
		 HashMap mapCodBarre=(HashMap) ProcessUtil.getHashMap_code_bean(List_fetchDatafromSe, "pk.code_barre"); 
		 setObjectValueModel("mapCodBarre", mapCodBarre);
         
        try {
        	
        } catch (Exception e) {
        	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
        return null;
		
		
	
	}
	
	 
	/*@SuppressWarnings("unchecked")
	public ModelAndView doFetchArticleSuivantTarif()throws Exception { 
		
		PrintWriter out = getResponse().getWriter();
        getResponse().setContentType(JSON_CONTENT_TYPE);
        
        List  fetchDatafromSe = (List) getObjectValueModel(LIST_ARTICLE_DEVIS_VENTE        );
        String query = getRequest().getParameter("term");
        String typeSearch = getRequest().getParameter("typeSearch");
        String fcode = getRequest().getParameter("fieldcode");
        String flabel = getRequest().getParameter("fieldlabel");
        try {
        	 Collection<JSONObject> items=ActionDataTablesManager.doRenderListJson(fetchDatafromSe, query, typeSearch, fcode, flabel);
             out.println(items.toString());
             out.close();
        } catch (Exception e) {
        	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
        return null;
	
	}*/
	 
	
	
	@SuppressWarnings("unchecked")
	public   ModelAndView doAdd_row_Editable( ) throws Exception{
		  
		
		
		try {
			List   listOfmyData  =(List) getObjectValueModel(LIST_EDITABLE_DEVIS);
			String code_barre   =getRequest().getParameter("ar_id")==null?"":getRequest().getParameter("ar_id");
			String designation  =getRequest().getParameter("designation")==null?"":getRequest().getParameter("designation");
			String quantite     =getRequest().getParameter("quantite")==null?"":getRequest().getParameter("quantite");
			String observation  =getRequest().getParameter("observation")==null?"":getRequest().getParameter("observation");
			String clt_id       =getRequest().getParameter("clt_id")==null?"":getRequest().getParameter("clt_id");
			HashMap  map        = (HashMap) getObjectValueModel("mapclientBen");
			ClientBean   beancl =(ClientBean) map.get(clt_id);
			 
			DevisBean   searchBean= (DevisBean) getObjectValueModel(FORM_BEAN); 
			searchBean.getClient().setClt_id(clt_id);
			setObjectValueModel(FORM_BEAN,searchBean); 
			
			 
			List list_article_dem_achatOrigine =(List) getObjectValueModel(LIST_ARTICLE_DEVIS_VENTE_ORIGINE);
			
			HashMap  mapdA=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.code_barre.pk.code_barre");
			
			if(mapdA.get(code_barre)!=null)  throw new Exception("Existe déjà");
			
			
			HashMap  MAP_ARTICLE=ProcessUtil.getHashMap_code_bean(list_article_dem_achatOrigine, "pk.code_barre");
			Code_barreBean  cBean=(Code_barreBean) MAP_ARTICLE.get(code_barre);
			
			DetDevisBean newBean= new DetDevisBean();
			newBean.getPk().setCode_barre(cBean);
			newBean.getPk().getCode_barre().setDesignation_libelle(designation);
			newBean.setQuantite(new Double(quantite));
			newBean.setObservation(observation);
			 
			
		 
			
			HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION);
	 
			
			TarificationBean  ss  =(TarificationBean) mapTarification.get(code_barre+"µ"+beancl.getTyp_trfBean().getType_trf_id());
			
			if(ss==null)  
				 ss  =(TarificationBean) mapTarification.get(code_barre+"µ"+GROUPE_TARIF_VENTE_PUBLIC);
			
			if(ss==null)  throwNewException("Manque Tarification pour cette article");
			
			
	    	if(ss!=null){
	    		 
	    		newBean.setTarif(ss);
	    		Double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
	    		Double qte=ProcessFormatNbr.ConvertStringToDouble(quantite);
	    		Double montant_ht_vente=ProcessNumber.PRODUIT(priUnitachat, qte);
	    		montant_ht_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente);
	    		newBean.setMontant_ht_vente(montant_ht_vente);
	    		
	    		 
	    		Double montant_tva_vente=ProcessNumber.Pourcentage(montant_ht_vente, ss.getTvaBean().getTva_value());
	    		montant_tva_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_vente);
	    		newBean.setMontant_tva_vente(montant_tva_vente);
	    		 
	    	}
	    	
			listOfmyData.add(newBean);
			 setObjectValueModel(LIST_EDITABLE_DEVIS,listOfmyData);
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.code_barre.pk.code_barre");
			
			
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_dem_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_dem_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_dem_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE_GRID,list_article_dem_achatGrid);
					setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE     ,list_article_dem_achat);
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
	public   ModelAndView doDelete_row_Editable( ) throws Exception{
		  
		
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_DEVIS);
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				
				DetDevisBean newBean= (DetDevisBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
			}
			if(!del) throw new Exception ((String) getObjectValueModel("_cochezAumoin"));
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.code_barre.pk.code_barre");
			List list_article_achatOrigine =(List) getObjectValueModel(LIST_ARTICLE_DEVIS_VENTE_ORIGINE);
			List list_article_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
				}
				
			}
			setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE_GRID,list_article_achatGrid);
			setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE,list_article_dem_achat);
			 
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
	public   ModelAndView doCheked_unCheked( ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_DEVIS);
			String to_check=getRequest().getParameter("statucheked")==null?"":getRequest().getParameter("statucheked");
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetDevisBean newBean =(DetDevisBean) listOfmyData.get(i);
				newBean.setTo_check(to_check);
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
	public static ModelAndView doActualiser_List( ) throws Exception{
		  
		
		
		try {
			
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_DEVIS);
			
			List list_article_recp_achat_origine =(List) getObjectValueModel(LIST_ARTICLE_DEVIS_VENTE_ORIGINE);
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetDevisBean  achatBean= (DetDevisBean) listOfmyData.get(i);
				String ar_id=(String) getValueObjSimpleFromList
				(achatBean.getPk().getCode_barre().getPk().getCode_barre(), list_article_recp_achat_origine, "pk.code_barre", "pk.ar_bean.pk_article.ar_id");
				achatBean.getPk().getCode_barre().getPk().getAr_bean().getPk_article().setAr_id(ar_id);
			}
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.code_barre.pk.code_barre");
			List list_article_achatGrid = new ArrayList();
			List list_article_achat = new ArrayList();
			for (int i = 0; i < list_article_recp_achat_origine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_recp_achat_origine.get(i);
				
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE_GRID,list_article_achatGrid);
					setObjectValueModel(LIST_ARTICLE_DEVIS_VENTE,list_article_achat);
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
	
@SuppressWarnings("unchecked")
public static ModelAndView doActualiser_GRID( ) throws Exception{
		
		try {
			
		 
				List listOData=(List) getObjectValueModel(LIST_EDITABLE_DEVIS);
				JsonObject data = new JsonObject();
				DevisBean   searchBean= (DevisBean) getObjectValueModel(FORM_BEAN); 
			for (int i = 0; i < listOData.size(); i++) {
				DetDevisBean newBean= (DetDevisBean) listOData.get(i);
				
				
				data.addProperty("erreur"+newBean.getPk().getCode_barre().getPk().getCode_barre(),"");
				HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION);
				HashMap  map        = (HashMap) getObjectValueModel(MAP_CLIENT_BEN);
				ClientBean   beancl = (ClientBean) map.get(searchBean.getClient().getClt_id()); 
				
				 
				TarificationBean  ss  =(TarificationBean) mapTarification.get(newBean.getPk().getCode_barre().getPk().getCode_barre()+"µ"+beancl.getTyp_trfBean().getType_trf_id());
				
				if(ss==null)  
					 ss  =(TarificationBean) mapTarification.get(newBean.getPk().getCode_barre().getPk().getCode_barre()+"µ"+GROUPE_TARIF_VENTE_PUBLIC);
				
				if(ss==null)  throwNewException("Manque Tarification pour cette article");
				
				
				
		    	if(ss!=null){
		    		if( newBean.getQuantite() == null  ) continue;
		    		if( newBean.getQuantite() < 0 ) {
		    			newBean.setQuantite(new Double(0));
		    		
		    			String err=" Il existe un ou plusieurs quantité(s) inférieur a zéro ";
		    			data.addProperty("erreur"+newBean.getPk().getCode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getPk().getCode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getPk().getCode_barre().getPk().getCode_barre(),"0");
		    			continue;
		    			
		    		}
		    		
		    	 
		    		
		    	 
		    		newBean.setTarif(ss);
		    		double priUnitVente=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
		    		
		    		
		    		double qte=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getQuantite());
		    		
		    		double montant_ht_Vente=ProcessNumber.PRODUIT(priUnitVente, qte);
		    		newBean.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_Vente));
		    		newBean.setQuantite(qte);
		    		
		    		Double montant_tva_Vente=ProcessNumber.Pourcentage(montant_ht_Vente, ss.getTvaBean().getTva_id());
		    		montant_tva_Vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_Vente);
	    		           newBean.setMontant_tva_vente(montant_tva_Vente);
	    		           
		    		 Double number=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_Vente);          
		    		 String Elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(number)  ;     
		    		 data.addProperty(newBean.getPk().getCode_barre().getPk().getCode_barre(),Elm);
		    		
		    	     
	    				         
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
	public ModelAndView doFetchData(DevisBean searchBean) throws Throwable {
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if(  bs.getFct_id().equals(Fn_Confirmer)  ||  bs.getFct_id().equals(Fn_Modifier)  ||  bs.getFct_id().equals(Fn_Supprimer)  ){
				searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id  not in ('"+Fn_Confirmer+"')   ");
			}
			
			List listDataSrv = serviceDevis.doFetchDatafromServer(searchBean);
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
	
public    ModelAndView doSelectRow () throws Exception {

		
		
	 
		try {
			
	 
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 
		
			 
		    DevisBean  rowBeans = (DevisBean) getIndexFromDataGrid_v1(LIST_DATA);
			List list_detail=new ArrayList(rowBeans.getList_detaille());
		    HashMap  map_reception=ProcessUtil.getHashMap_code_bean(list_detail, "pk.code_barre.pk.code_barre");
		    setObjectValueModel(FORM_BEAN, rowBeans);
			 
		 
			setObjectValueModel(LIST_EDITABLE_DEVIS        ,  list_detail);
			setObjectValueModel(LIST_EDITABLE_DEVIS_ORIGIN   ,  ProcessUtil.cloneList(list_detail) );
			
			if (bs.getFct_id().equals(Fn_Consulter)   )
				return getViewConsult_Pdf_ex(FORM_VIEW);
			
			if ( bs.getFct_id().equals(Fn_Confirmer)    )
				return getViewConfirm(FORM_VIEW);
			
			
			if ( bs.getFct_id().equals(Fn_Modifier)    )
				return getViewUpdate(FORM_VIEW_EDIT);
			 
			 
			 
			if (bs.getFct_id().equals(Fn_Supprimer))
				return getViewDelete(FORM_VIEW);
			
		}catch (Exception e) {
			displayException(e);
			
		}
		return getViewFilterAjax(FILTER_VIEW);
		
} 

	public ModelAndView doAddData(DevisBean detailBean) throws Throwable {
		try {
			setObjectValueModel(FORM_BEAN, detailBean);
			serviceDevis.doCreateRowData(detailBean);
			setObjectValueModel(LIST_EDITABLE_DEVIS, new  ArrayList<DetDevisBean>());
			setObjectValueModel(FORM_BEAN,new DevisBean());
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW_EDIT);
	}
	
	public ModelAndView doConfirmData(DevisBean beanUpBean) {
		try {
			serviceDevis.doExcuterRowData (beanUpBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doUpdateData(DevisBean beanUpBean) {
		try {
			serviceDevis.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculer_Total(DevisBean     detailBean ) throws Exception {
		
		try {
			DevisBean   rowBean = (DevisBean) getObjectValueModel(FORM_BEAN);
			List <DetDevisBean >List_detaille= new ArrayList<DetDevisBean>();
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if(bs.getFct_id().equals(Fn_Creer) ||  bs.getFct_id().equals(Fn_Modifier) ){
				 List_detaille=(List<DetDevisBean>) getObjectValueModel(LIST_EDITABLE_DEVIS);
			}else{
				 List_detaille=new ArrayList<DetDevisBean>(rowBean.getList_detaille());   
			}
			
			
			setObjectValueModel(LIST_EDITABLE_DEVIS, List_detaille);
			setObjectValueModel(LIST_EDITABLE_DEVIS_ORIGIN, ProcessUtil.cloneList(List_detaille) );
			
			
			double tot_ht=0;
			double tot_tva=0;
			Double tot_qte=new Double(0);
			
			HashMap   mapTva = new HashMap();
			for (int i = 0; i < List_detaille.size(); i++) {
				DetDevisBean  bean=List_detaille.get(i);
				tot_ht=ProcessNumber.addition(tot_ht, bean.getMontant_ht_vente());
				tot_tva=ProcessNumber.addition(tot_tva, bean.getMontant_tva_vente());
				tot_qte=ProcessNumber.addition(tot_qte, bean.getQuantite());
				
				List  listTva = (List) mapTva.get(bean.getTarif().getTvaBean().getTva_libelle());
				if(listTva==null)listTva= new ArrayList();
				listTva.add(bean);
				mapTva.put(bean.getTarif().getTvaBean().getTva_libelle(), listTva);
				
			}
			 DevisBean    reBean = new DevisBean();
			 double remise=ProcessFormatNbr.FormatDouble_Troischiffre(detailBean.getDev_remise());
			 reBean.setDev_remise(remise);
			    
			     
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
							DetDevisBean  bean=(DetDevisBean) listTva.get(i);
							le_Ht=ProcessNumber.addition(le_Ht, bean.getMontant_ht_vente());
							le_Ht_brute=ProcessNumber.addition(le_Ht_brute, bean.getMontant_ht_vente());
							leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_vente());
						 }
					 	 
					 	 
					 	
					 	if(mapTvabidan.get(reBean.getDev_remise())==null &&  reBean.getDev_remise().doubleValue()> 0  ){
					 		
					 		if(reBean.getDev_remise().doubleValue() < le_Ht.doubleValue() ){
					 			le_Ht=ProcessNumber.SOUSTRACTION(le_Ht, reBean.getDev_remise().doubleValue());
						 		String prec=tva.replace("%", "");
						 		leTva=ProcessNumber.Pourcentage(le_Ht, new Double(prec));
						 		mapTvabidan.put(reBean.getDev_remise(),"true");
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
			 element.put("td1","2");
			 element.put("value1","Brut.H.T");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(le_Ht_brute));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Remise");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(remise));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Net.H.T");
			 element.put("td2","3");
			 double ht_apres_remise=  ProcessNumber.SOUSTRACTION(le_Ht_brute, remise)  ;
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(ht_apres_remise));
			 list_total.put(element);
			 reBean.setDev_mnt_ht(ProcessFormatNbr.FormatDouble_Troischiffre(ht_apres_remise));
			 
			
			 
			 
			 
			 reBean.setDev_mnt_tva(ProcessFormatNbr.FormatDouble_Troischiffre(total_leTva));
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Total TVA");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_leTva));
			 list_total.put(element);
			 
			 
			 double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
			 reBean.setDev_mnt_total(ProcessFormatNbr.FormatDouble_Troischiffre(total_mnt_gen));
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Total TTC");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mnt_gen));
			 list_total.put(element);
			 json.put("list_total", list_total);
			 setObjectValueModel("list_total", list_total);
			 setObjectValueModel(BEAN_TOTAL, reBean);
			 getResponse().setContentType(JSON_CONTENT_TYPE);      
			 getResponse().getWriter().write(json.toString());
			 
			  
		    
		    
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	public    ModelAndView doPrintPDF_detaille() throws Exception   {
		 
		List   lisData=  (List) getObjectValueModel(LIST_EDITABLE_DEVIS) ;
		DevisBean  denBean= (DevisBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+LIST_EDITABLE_DEVIS+getRequest().getSession().getId()+".pdf");
	    FileOutputStream fs = new FileOutputStream(file);
	    GeneratePdf  genpdf= new GeneratePdf();
		try {
			 Document document=GeneratePdf.doGenerateDocumentFormat();
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	        genpdf.doWriteHeaderDocument_PDF(document,fs,bSession);
	        
	        doWriteEntete(document,denBean);
	        doWrite_Header_ContentTable(document,96,MapfieldBean_detaille);
	        doWrite_Data_Table (lisData,document,96,MapfieldBean_detaille);
	        doWrite_Tva_Total_Piece(lisData,document);  
	        
	        document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write(LIST_EDITABLE_DEVIS+getRequest().getSession().getId()+".pdf");
		} catch (Exception e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

     }
	
	  public   void doWrite_Data_Table(List lisData, Document document,int poucentage,String[][] mapFieldBean) throws Exception, SecurityException {
		  
		    PdfPTable table = new PdfPTable(mapFieldBean.length);
		    int PaddingBottom=5;
	        int[] columnWidths = new int[mapFieldBean.length] ;
	        for(int i = 0; i < mapFieldBean.length; i++){
	      	columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
			}
	        table.setWidthPercentage(poucentage);
	        table.setWidths(columnWidths);
		  
			  for(int i=0; i < lisData.size(); i++ ){
				   Object bean = (Object) lisData.get(i);
				    
				 for(int j = 0; j < mapFieldBean.length; j++){
					 
					        PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.REDFONT));
					 
					        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
					        
					        if(j==mapFieldBean.length-1 || j==mapFieldBean.length-2){
					        	Double elm=Double.valueOf(String.valueOf(obj));
					        	
					        	cell = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(elm) ,GeneratePdf.REDFONT));
						        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						        cell.setPaddingBottom(5);
						        cell.setBorderWidth(0.1f);
						        cell.setBorderColor(WebColors.getRGBColor("#787878"));
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
						        
					        }else{
					        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.REDFONT));
						        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						        cell.setPaddingBottom(5);
						        cell.setBorderWidth(0.1f);
						        cell.setBorderColor(WebColors.getRGBColor("#787878"));
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        }
					        table.addCell(cell);
					    }   
				   
	           }
			  
			   /********************************************************************************************************/
	           int sizelist=lisData.size();
	           int toolha=sizelist*20;
	           int resul=380 - toolha;
	           float toul_contenu_tab=Float.valueOf(String.valueOf(resul));
	           /********************************************************************************************************/
	            
	           for(int j = 0; j < mapFieldBean.length; j++){
			            PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.REDFONT));
				        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				        cell.setPaddingBottom(5);
				        cell.setBorderWidth(0.1f);
				        cell.setFixedHeight(toul_contenu_tab);
				        cell.setBorderColor(WebColors.getRGBColor("#787878"));
				        cell.setBackgroundColor(BaseColor.WHITE);
				        cell.setBorder(cell.LEFT+cell.RIGHT);
			            table.addCell(cell);
			    }   
			  document.add(table);
		}
	  
	  
	  public void doWrite_Header_ContentTable(Document document,int poucentage, String[][] mapFieldBean) throws Exception {
		
		  
		  PdfPTable table = new PdfPTable(mapFieldBean.length);
	    int PaddingBottom=5;
        int[] columnWidths = new int[mapFieldBean.length] ;
        for(int i = 0; i < mapFieldBean.length; i++){
      	columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
		}
        table.setWidthPercentage(poucentage);
        table.setWidths(columnWidths);
      for(int i = 0; i < mapFieldBean.length; i++){
    	     
    	     String titrehead="";
    	     
    	     if(mapFieldBean[i][0].indexOf(".")>0){
				 final String[] lesColunmooo = mapFieldBean[i][0].split("\\.");
				 String getel=lesColunmooo[lesColunmooo.length-1];
				 
				 titrehead=(String) getObjectValueModel(getel);
				 if(getel.startsWith("code_barre")   ) titrehead=(String) getObjectValueModel("_code_barre");
				 
    	     }else{
    	    	 titrehead=(String) getObjectValueModel(mapFieldBean[i][0]);
    	    	 
    	     } 
    	     
    	     if(mapFieldBean[i][0].startsWith("modeBean"))  titrehead=(String) getObjectValueModel("_mode");
    	     
    	     
    	     
    	     if(mapFieldBean[i][0].equals("quantite")) titrehead=(String) getObjectValueModel("__qte");
    	     
    	    PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":titrehead  ,GeneratePdf.SMALLBOLD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setPaddingBottom(PaddingBottom);
		    cell.setBackgroundColor(GeneratePdf.colorHeader);
		    cell.setBorderWidth(1f);
		    table.addCell(cell);
		}
      document.add(table);
}
	
	
	
	public void doWriteEntete(Document document, DevisBean denBean) throws Exception {
	PdfPTable tableTopHeader = new PdfPTable(100);
	tableTopHeader.setWidthPercentage(92);
	 
	    
	PdfPCell cell = new PdfPCell(new Phrase(" Devis N° ",GeneratePdf.FONT_12_bold));
    cell.setColspan(12);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(" : "+denBean.getDevis_id(),GeneratePdf.FONT_12_normal));
    cell.setColspan(28);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase(" Client ",GeneratePdf.FONT_12_bold));
    cell.setColspan(18);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.LEFT);
    tableTopHeader.addCell(cell);
    
    String getClient=denBean.getDev_lib().equals("")?denBean.getClient().getClt_lib():denBean.getDev_lib();
    cell = new PdfPCell(new Phrase(" : "+getClient ,GeneratePdf.FONT_12_normal));
    cell.setColspan(42);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    
     
    
    
    cell = new PdfPCell(new Phrase(" Date ",GeneratePdf.FONT_12_bold));
    cell.setColspan(12);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(denBean.getDev_date()) ,GeneratePdf.FONT_12_normal));
    cell.setColspan(28);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase(" Adresse ",GeneratePdf.FONT_12_bold));
    cell.setColspan(18);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_adr() ,GeneratePdf.FONT_12_normal));
    cell.setColspan(42);
    cell.setFixedHeight(40f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase("   ",GeneratePdf.FONT_12_bold));
    cell.setColspan(12);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(  "   " ,GeneratePdf.FONT_12_normal));
    cell.setColspan(28);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase(" Matricule fiscal ",GeneratePdf.FONT_12_bold));
    cell.setColspan(18);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT+cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_obs() ,GeneratePdf.FONT_12_normal));
    cell.setColspan(42);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT +cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    
    
    
    
    
    
    cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_normal));
    cell.setColspan(100);
    cell.setFixedHeight(30f);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    document.add(tableTopHeader);
		
	}
	
	
	  public void doWrite_Tva_Total_Piece(List   lisData,Document document) throws Exception {
		
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
        	    JSONObject rec = list_total.getJSONObject(i);
        	    String titre = rec.getString("value1"); 
        	    String value = rec.getString("value2");
        	    
        	    cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_bold));
                cell.setColspan(60);
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
                cell.setColspan(22);
                cell.setFixedHeight(20f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
                table_total.addCell(cell);
        	}
           document.add(table_des_tva);
           document.add(table_total);
          
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public static  ModelAndView doExportXls_detaille() {
		try {

			    List        lisData       =  (List)getObjectValueModel(LIST_EDITABLE_DEVIS) ;
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
			    
			     
			    DevisBean   rBeanS=(DevisBean) getObjectValueModel(FORM_BEAN);
		        
			    setObjectValueModel("titleHead","Devis N° "+rBeanS.getDevis_id()) ;
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
	public ModelAndView doDeleteData(DevisBean beanDelBean) {
		try {
			serviceDevis.doDeleteRowData(beanDelBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
