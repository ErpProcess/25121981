package ERP.Process.Commerciale.Vente.ProcedureVente.web;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Article.model.ClientArticleBean;
import ERP.Process.Commerciale.Article.model.LieuxArticleBean;
import ERP.Process.Commerciale.Article.service.ArticleService;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.Process.Commerciale.FamilleArticle.service.FamilleArticleService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.ResponsableLieu.model.ResponsableLieuBean;
import ERP.Process.Commerciale.Stock.ResponsableLieu.service.ResponsableLieuService;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.service.Stock_articleService;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.service.TarificationService;
import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.DetCmdCltBean;
import ERP.Process.Commerciale.Vente.Commandeclient.service.CommandeclientService;
import ERP.Process.Commerciale.Vente.Commandeclient.template.CommandeclientTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.web.Facture_clientActionManager;
import ERP.Process.Commerciale.Vente.Facture_client.web.PrintPdfModelSPL;
import ERP.Process.Commerciale.Vente.FournitureVente.model.DetFournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.model.FournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.service.FournitureVenteService;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DeriverOperationVente;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.service.ProcedureVenteService;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.Service.model.DetServiceBean;
import ERP.Process.Commerciale.Vente.Service.model.ServiceBean;
import ERP.Process.Commerciale.Vente.Service.service.ServiceService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.service.DeviseService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.DeriverUnite;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.DetDeriverUnite;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.service.UniteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel.WriteExcel;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
public class ProcedureVenteActionManager extends ProcedureVenteTemplate {
   
	 
 
	private static final long serialVersionUID = -8175356954235186229L;
	private ProcedureVenteService  serviceProcedureVente;
	

	@Autowired
	public void setServiceProcedureVente(ProcedureVenteService serviceProcedureVente) {
	    this.serviceProcedureVente = serviceProcedureVente;
	} 
	
	@Autowired
	private FournitureVenteService    serviceFournitureVente;
	
	private ServiceService serviceService;
	@Autowired
	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}
	
	
	private      DepotStockageService       serviceDepotStockage;
	@Autowired
	public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
		this.serviceDepotStockage = serviceDepotStockage;
	}
	
	private ArticleService  serviceArticle;
	@Autowired
	public void setServiceProcedureVente(ArticleService serviceArticle) {
	    this.serviceArticle = serviceArticle;
	} 

	
	private ClientDAO daoClient;
	@Autowired
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}
	
    public   Code_barreService serviceCode_barre;
	@Autowired
	public void setServiceCode_barre(Code_barreService serviceCode_barre) {
		this.serviceCode_barre = serviceCode_barre;
	}
	
	
	public    TarificationDAO daoTarification;
	@Autowired
	public void setDaoTarification(TarificationDAO daoTarification) {
		this.daoTarification = daoTarification;
	}
	
	private DocumentLotService serviceDocumentLot;

	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
		this.serviceDocumentLot = serviceDocumentLot;
	}
	 
	private TarificationService serviceTarification;
	@Autowired
	public void setTarificationService(TarificationService serviceTarification) {
		this.serviceTarification = serviceTarification;
	}
	
	private ResponsableLieuService  serviceResponsableLieu;
	@Autowired
	public void setServiceResponsableLieu(ResponsableLieuService serviceResponsableLieu) {
	    this.serviceResponsableLieu = serviceResponsableLieu;
	} 
	    
	/*private ClientService serviceClient;
	@Autowired
	public void setServiceClient(ClientService serviceClient) {
		this.serviceClient = serviceClient;
	}*/
	
	
	
	private Stock_articleService serviceStock_article;
	@Autowired
	public void setServiceStock_article(Stock_articleService serviceStock_article) {
		this.serviceStock_article = serviceStock_article;
	}
	
	private CommandeclientService  serviceCommandeclient;
	@Autowired
	public void setServiceCommandeclient(CommandeclientService serviceCommandeclient) {
		this.serviceCommandeclient = serviceCommandeclient;
	}
	@Autowired
	private DeviseService     serviceDevise;
	
	@Autowired 
	private FamilleArticleService serviceFamilleArticle;
	
	
	private TVAService   serviceTVA;
	@Autowired
	public void setServiceTva(TVAService serviceTVA) {
		this.serviceTVA = serviceTVA;
	}
	
	
	private UniteService   serviceUnite;
	@Autowired
	public void setServiceUnite(UniteService serviceUnite) {
		this.serviceUnite = serviceUnite;
	}
	
	@SuppressWarnings("unchecked")
	public    ModelAndView doInitServletAction() {

	
		
		try {
			 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 setObjectValueModel(QTE_STOCK, new Double(0));
			 setObjectValueModel( FORM_BEAN , new ProcedureVenteBean() );
			 setObjectValueModel( SEARCH_BEAN , new ProcedureVenteBean() );
			 doLoadingLibelleOtherSModule(ID_SOUS_MODULE);
			 doLoadingLibelleOtherSModule(CommandeclientTemplate.ID_SOUS_MODULE);
			 setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+ID_SOUS_MODULE));
			 setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+ID_SOUS_MODULE)) ;
			 List list_devise=serviceDevise.doFetchDatafromServer(new DeviseBean());
			 setObjectValueModel("list_devise", list_devise);
			 
		     HashMap  map_devise=ProcessUtil.getHashMap_code_bean(list_devise, "dev_id");
		     setObjectValueModel("map_devise", map_devise);
			
			 
			 List list_des_tva= serviceTVA.doFetchDatafromServer(TVABean.class.newInstance());
			 setObjectValueModel(LIST_DES_TVA, list_des_tva);
			 
			 FamilleBean fBean = new FamilleBean();
			 fBean.setConditionDeSelection(" AND  type.typefam_id  in ('art','frn')   ");
			 setObjectValueModel("listFamArticleOfvente" , serviceFamilleArticle.dofetchDatafromServer(fBean));
			 
			 
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
			setObjectValueModel(LIST_DEPOT_STOCK , listLieux);
			 
			List list_client_d= daoClient.doFindListClient(ClientBean.class.newInstance());
			setObjectValueModel(LIST_CLIENT_VENTE, list_client_d);
			 
			HashMap  mapclientBen = ProcessUtil.getHashMap_code_bean(list_client_d, "clt_id");
			setObjectValueModel(MAP_CLIENT_BEN, mapclientBen);
			
	 
			List  <DetProcedureVenteBean>listGridEditable_VENTE=  new  ArrayList<DetProcedureVenteBean>();
			setObjectValueModel(LIST_EDITABLE_VENTE, listGridEditable_VENTE);
			
			HashMap   map_deriver_vente=  new HashMap();
			setObjectValueModel(MAP_DERIVER_VENTE, map_deriver_vente);
			
			
			List  <DetFournitureVenteBean>list_editable_fournitureVente=  new  ArrayList<DetFournitureVenteBean>();
			setObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE, list_editable_fournitureVente);
			
			
			List  <DetServiceBean>list_editable_Prestation=  new  ArrayList<DetServiceBean>();
			setObjectValueModel(LIST_EDITABLE_PRESTATION, list_editable_Prestation);
			
			ServiceBean detailSrvBean = new ServiceBean();
			detailSrvBean.setDeviseSrv(bs.getSociete().getDevise());
		
			
			FournitureVenteBean detailFrnBean = new FournitureVenteBean();
			detailFrnBean.setDeviseFr(bs.getSociete().getDevise());
			setObjectValueModel("detailFrnBean", detailFrnBean);
			setObjectValueModel("detailSrvBean", detailSrvBean);
			
			
		    
			
			bs.setSousmod_libelle_title(bs.getSousmod_libelle());
			
			if (bs.getFct_id().equals(Fn_Servir)  ||  bs.getFct_id().equals(Fn_Accueillir)  ||  bs.getFct_id().equals(Fn_Contremander) ){
				setObjectValueModel("PDF_IS_CMD", "OUI");
				setObjectValueModel(MAP_FIELD_BEAN, CommandeclientTemplate.MapfieldBean);
				bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_vente"));
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_SERVIR);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_DEM);
				setObjectValueModel(NAME_GRID_G, NAME_GRID_DEM);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_SERVIR");
				return getViewFilterAjax_Servir(FILTER_VIEW_CMD);
			}

			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ||   bs.getFct_id().equals(Fn_Facturer)   ) {
				setObjectValueModel(LIST_EDITABLE_VENTE, listGridEditable_VENTE);
				ProcedureVenteBean  devBean = new ProcedureVenteBean();
				devBean.setVente_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()) );
				devBean.setDepot(depot);
				devBean.setDevise(bs.getSociete().getDeviseVente());
			
			 
				 ClientBean cBean = new ClientBean();
				 cBean.setClt_typ("pass");
				 devBean.setClient(daoClient.doFindListClient(cBean).get(0));
				 
					
				setObjectValueModel("beanInito",  ProcessUtil.cloneObject(devBean)  );
				setObjectValueModel(FORM_BEAN, devBean);
				return getViewAdd(FORM_VIEW_CREER);
			}  else {
				setObjectValueModel(SEARCH_BEAN, new ProcedureVenteBean());
				return getViewFilterAjax(FILTER_VIEW);

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
	public    ModelAndView doResetFormVente() throws Exception {
		try {
			ProcedureVenteBean  devBean = (ProcedureVenteBean) getObjectValueModel("beanInito");
			setObjectValueModel(FORM_BEAN, devBean);
			List  <DetProcedureVenteBean>listGridEditable_VENTE=  new  ArrayList<DetProcedureVenteBean>();
			setObjectValueModel(LIST_EDITABLE_VENTE, listGridEditable_VENTE);
			setObjectValueModel(LIST_EDITABLE_PRESTATION  , new  ArrayList<DetServiceBean>());
			setObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE  , new  ArrayList<DetFournitureVenteBean>());
			 
		} catch (Exception e) {
			throw e;
		}
		
		return getViewAdd(FORM_VIEW_CREER);
		
	}
	
	
	public   ModelAndView doGet_Stock(ProcedureVenteBean bean ) throws Exception{
		  
		
		
		try {
		 
			 Stock_articleBean beanSBean= new Stock_articleBean();
			 
			 HashMap  mapCodBarre=(HashMap) getObjectValueModel("mapCodBarre"  );
			 Code_barreBean bCode_barreBean=(Code_barreBean) mapCodBarre.get(bean.getCode_barreX());
			 
			 if(  !StringUtils.isEmpty(  bCode_barreBean.getPk().getAr_bean().getCathegorie().getData_id()) &&  
					 
				( bCode_barreBean.getPk().getAr_bean().getCathegorie().getData_id().equals("mar") 	)
					 
			 ){
				 Double  doubleVal= new Double(0);	 
			     beanSBean.getPk().setFkCode_barre(bCode_barreBean);
			     beanSBean.getPk().setDepot(bean.getDepot());
			     beanSBean.setCondition_max_date_stock(  "    " +
						 "      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
					     "      where  beaK.pk.date_stock  <= '"+ProcessDate.getStringFormatDate(bean.getVente_date())+"'          " +
					     "           AND   beaK.pk.fkCode_barre.pk.code_barre=      bean.pk.fkCode_barre.pk.code_barre     "+
					     "      AND    beaK.pk.depot.depot_id="+beanSBean.getPk().getDepot().getDepot_id()+"      )                ");
				List llis=serviceStock_article.doFetchDatafromServer(beanSBean);
				Stock_articleBean beanR= new Stock_articleBean();
				
			
				if(llis!=null  &&  llis.size()>0){
				    beanR=(Stock_articleBean) llis.get(0);
				    doubleVal=beanR.getSolde_stock();
				}else{
				    beanR= new Stock_articleBean();
				} 
				getResponse().getWriter().print(doubleVal);
			 }else{
				 getResponse().getWriter().print("");
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
	
	
public   ModelAndView doGet_Stock_Founiture(ProcedureVenteBean bean ) throws Exception{
		  
		
		
		try {
			
		 
			 Stock_articleBean beanSBean= new Stock_articleBean();
			 
			 HashMap  mapCodBarre=(HashMap) getObjectValueModel( MAP_CODBARRE_FOURNITURE  );
			 Code_barreBean bCode_barreBean=(Code_barreBean) mapCodBarre.get(bean.getCode_barreFurniture());
			 
			 if(  !StringUtils.isEmpty(  bCode_barreBean.getPk().getAr_bean().getCathegorie().getData_id()) &&  
					 
				( bCode_barreBean.getPk().getAr_bean().getCathegorie().getData_id().equals("map") 	)
					 
			 ){
				 Double  doubleVal= new Double(0);	 
			     beanSBean.getPk().setFkCode_barre(bCode_barreBean);
			     beanSBean.getPk().setDepot(bean.getDepot());
			     beanSBean.setCondition_max_date_stock(  "    " +
						 "      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
					     "      where  beaK.pk.date_stock  <= '"+ProcessDate.getStringFormatDate(bean.getVente_date())+"'          " +
					     "           AND   beaK.pk.fkCode_barre.pk.code_barre=      bean.pk.fkCode_barre.pk.code_barre     "+
					     "      AND    beaK.pk.depot.depot_id="+beanSBean.getPk().getDepot().getDepot_id()+"      )                ");
				List llis=serviceStock_article.doFetchDatafromServer(beanSBean);
				Stock_articleBean beanR= new Stock_articleBean();
				
			
				if(llis!=null  &&  llis.size()>0){
				    beanR=(Stock_articleBean) llis.get(0);
				    doubleVal=beanR.getSolde_stock();
				}else{
				    beanR= new Stock_articleBean();
				} 
				getResponse().getWriter().print(doubleVal);
			 }else{
				 getResponse().getWriter().print("");
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
	
	 
	public   ModelAndView doActualiser_List( ) throws Exception{
		  
		
		
		try {
			
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_VENTE);
			
			List LIST_ARTICLE_ORIGINE =(List) getObjectValueModel(LIST_ARTICLE_VENTE_ORIGINE);
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetProcedureVenteBean  achatBean= (DetProcedureVenteBean) listOfmyData.get(i);
				String ar_id=(String) getValueObjSimpleFromList
				(achatBean.getPk().getFkcode_barre().getPk().getCode_barre(), LIST_ARTICLE_ORIGINE, "pk.code_barre", "pk.ar_bean.pk_article.ar_id");
				achatBean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().setAr_id(ar_id);
			}
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			List list_article_achatGrid = new ArrayList();
			List list_article_achat = new ArrayList();
			for (int i = 0; i < LIST_ARTICLE_ORIGINE.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) LIST_ARTICLE_ORIGINE.get(i);
				
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_VENTE_GRID,list_article_achatGrid);
					setObjectValueModel(LIST_ARTICLE_VENTE,list_article_achat);
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
	
	 
	
	
public   ModelAndView doActualiserGridPrestation( ProcedureVenteBean bean ) throws Exception{
		
		try {
 			List listService=(List) getObjectValueModel(LIST_EDITABLE_PRESTATION);
			JsonObject data = new JsonObject();
			for (int i = 0; i < listService.size(); i++) {
				DetServiceBean  newBean= (DetServiceBean) listService.get(i);
				
				data.addProperty("erreur"+newBean.getFkcode_barre().getPk().getCode_barre(),"");
	            TarificationBean  ss  = newBean.getTarifVente(); 
				if(ss==null)  throwNewException("Manque Tarification pour cette article");
		    	if(ss!=null){
		    		if( newBean.getQuantite() == null  ||  newBean.getQuantite() ==0) continue;
		    		if( newBean.getQuantite() < 0 ) {
		    			String err=" Il existe un ou plusieurs quantité(s) inférieur a zéro ";
		    			data.addProperty("erreur"+newBean.getFkcode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getFkcode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getFkcode_barre().getPk().getCode_barre(),"0");
		    			continue;
		    		}
		    		/*****************************************le cout ********************************************/
		    		Double getQuantiteX       = newBean.getQuantite()==null?new Double(0):newBean.getQuantite();
		    		Double                 qte=ProcessFormatNbr.FormatDouble_Troischiffre(getQuantiteX);
		    		Double  cout              = ss.getCout()==null?new Double(0):ss.getCout().getTarif_unit_article();
		    		Double	Prixcout          = cout==null?new Double(0):cout;
		    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
		    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
		    		
		    		/*****************************************  setInfo  ********************************************/
		    		
		    		Double priUnitvente=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
		    		
		    		/*****************************************Prix Unit Brute reel********************************************/
		    		
		    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
		  
		    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute);
		    		newBean.setMontant_ht_vente_reel(montant_ht_vente_brute);
		    		 
		    		/*******************************************Remise********************************************************/

	    		    /*******************************************montant_ht_vente *********************************************/   
	    		    
		    		newBean.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute));
		    		
		    		/*********************************************montant_tva_vente ******************************************/
		    		Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente_brute,ss.getTvaBean(),new DeviseBean());
		    		montant_tva_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_vente);
		     
		    		newBean.setMontant_tva_vente(montant_tva_vente);
		    		/*********************************************montant_ttc_vente *******************************************/
		    		
		    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente_brute, montant_tva_vente);
		    		newBean.setMontant_ttc_vente(montant_ttc_vente);
		    		
		    		/*********************************************montant_benefice *******************************************/
		    		if(le_cout.doubleValue()>0){
		    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, le_cout);
		    		montant_benefice          =ProcessFormatNbr.FormatDouble_Troischiffre(montant_benefice);
		    		newBean.setMontant_benefice(montant_benefice);
		    		}
		    		/**********************************************************************************************************/
		    		         
		    		String Elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(montant_ht_vente_brute)  ;     
		    		data.addProperty(newBean.getFkcode_barre().getPk().getCode_barre(),Elm);
	    				         
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
	
public   ModelAndView doActualiserGridFourniture( ProcedureVenteBean bean ) throws Exception{
		
		try {
			
 			List listOData=(List) getObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE);
			JsonObject data = new JsonObject();
			for (int i = 0; i < listOData.size(); i++) {
				DetFournitureVenteBean  newBean= (DetFournitureVenteBean) listOData.get(i);
				
				data.addProperty("erreur"+newBean.getFkcode_barre().getPk().getCode_barre(),"");
	            TarificationBean  ss  = newBean.getTarifVente(); 
				if(ss==null)  throwNewException("Manque Tarification pour cette article");
		    	if(ss!=null){
		    		if( newBean.getQuantite() == null  ||  newBean.getQuantite() ==0) continue;
		    		if( newBean.getQuantite() < 0 ) {
		    			String err=" Il existe un ou plusieurs quantité(s) inférieur a zéro ";
		    			data.addProperty("erreur"+newBean.getFkcode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getFkcode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getFkcode_barre().getPk().getCode_barre(),"0");
		    			continue;
		    		}
	 
		    		 
		    		/*****************************************le cout ********************************************/
		    		Double getQuantiteX       = newBean.getQuantite()==null?new Double(0):newBean.getQuantite();
		    		Double                 qte=ProcessFormatNbr.FormatDouble_Troischiffre(getQuantiteX);
		    		Double  cout              = ss.getCout()==null?new Double(0):ss.getCout().getTarif_unit_article();
		    		Double	Prixcout          = cout==null?new Double(0):cout;
		    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
		    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
		    		
		    		/*****************************************  setInfo  ********************************************/
		    		
		    		
		    		Double priUnitvente=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
		    		 
		    		      
		    		  
		    		
		    		
		    		/*****************************************Prix Unit Brute reel********************************************/
		    		
		    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
		  
		    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute);
		    		newBean.setMontant_ht_vente_reel(montant_ht_vente_brute);
		    		 
		    		/*******************************************Remise********************************************************/

	    		    /*******************************************montant_ht_vente *********************************************/   
	    		    
	    		 
	    		    
		    		newBean.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute));
		    		
		    		/*********************************************montant_tva_vente ******************************************/
		    		 
		    		Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente_brute,ss.getTvaBean(),new DeviseBean());
		    		montant_tva_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_vente);
		     
		    		newBean.setMontant_tva_vente(montant_tva_vente);
		    		
		    		/*********************************************montant_ttc_vente *******************************************/
		    		
		    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente_brute, montant_tva_vente);
		    		newBean.setMontant_ttc_vente(montant_ttc_vente);
		    		
		    		/*********************************************montant_benefice *******************************************/
		    		if(le_cout.doubleValue()>0){
		    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, le_cout);
		    		montant_benefice          =ProcessFormatNbr.FormatDouble_Troischiffre(montant_benefice);
		    		newBean.setMontant_benefice(montant_benefice);
		    		}
		    		/**********************************************************************************************************/
		    		         
		    		 String Elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(montant_ht_vente_brute)  ;     
		    		 data.addProperty(newBean.getFkcode_barre().getPk().getCode_barre(),Elm);
		    		
		    	     
	    				         
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
	
public   ModelAndView doActualiser_GRID( ProcedureVenteBean detailBean ) throws Exception{
		
		try {
 			List listOData=(List) getObjectValueModel(LIST_EDITABLE_VENTE);
 			HashMap  map_deriver_vente  =(HashMap) getObjectValueModel(MAP_DERIVER_VENTE);
			JsonObject data = new JsonObject();
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			String pattern ="0.000";
			if( detailBean.getDevise().getDev_id().intValue()==191  ||  detailBean.getDevise().getDev_id().intValue()==192   ){
				pattern ="0.00";
			}
			
		 
			Double remise_ala_caisse    			  =  new Double(0);
			Double totalre_mise          			  =  new Double(0);
			Double remise_ligne_pr_la_caisse          =  new Double(0);
			Double avance                   = new Double(0);
			Double getTaux_remise_alacaisse = detailBean.getTaux_remise_alacaisse()==null?new Double(0):detailBean.getTaux_remise_alacaisse();
			Double getAvance_montant_vente  = detailBean.getAvance_montant_vente()==null?new Double(0):detailBean.getAvance_montant_vente();
			Double getMontant_vente_recu    = detailBean.getMontant_vente_recu()==null?new Double(0):detailBean.getMontant_vente_recu();
			getTaux_remise_alacaisse =ProcessFormatNbr.FormatDouble_ParameterChiffre(getTaux_remise_alacaisse,pattern);
			
			
			Double tot_ht_brute =new Double(0);
			Double tot_ht_net    =new Double(0);
			Double tot_ttc    =new Double(0);
			Double tot_tva=new Double(0);
		    Double tot_qte=new Double(0);
			Double marge_benefice_vente=new Double(0);
			 
			
			 ProcedureVenteBean    beanTotal = new ProcedureVenteBean();
			 beanTotal.setAvance_montant_vente(avance)  ; 
			 beanTotal.setMarge_benefice_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(marge_benefice_vente,pattern));
			
			
			for (int i = 0; i < listOData.size(); i++) {
				DetProcedureVenteBean  newBean= (DetProcedureVenteBean) listOData.get(i);
				
				data.addProperty("erreur"+newBean.getPk().getFkcode_barre().getPk().getCode_barre(),"");
	            TarificationBean  ss  = newBean.getTarif();  
				if(ss==null)  throwNewException("Manque Tarification pour cette article");
		    	if(ss!=null){
		    		if( newBean.getQuantite() == null  ||  newBean.getQuantite() ==0) continue;
		    		if( newBean.getQuantite() < 0 ) {
		    			String err=" Il existe un ou plusieurs quantité(s) inférieur a zéro ";
		    			data.addProperty("erreur"+newBean.getPk().getFkcode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getPk().getFkcode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getPk().getFkcode_barre().getPk().getCode_barre(),"0");
		    			continue;
		    		}
		    		 
		    		/*****************************************le cout ********************************************/
		    		Double getQuantiteX       = newBean.getQuantite()==null?new Double(0):newBean.getQuantite();
		    		Double                 qte=ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX,pattern);
		    		Double  cout              = ss.getCout()==null?new Double(0):ss.getCout().getTarif_unit_article();
		    		Double	Prixcout          = cout==null?new Double(0):cout;
		    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
		    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
		    		
		    		/*****************************************  setInfo  ********************************************/
		    		
		    		
		    		Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(ss.getTarif_unit_vente(),pattern);
		    		if( newBean.getPrix_vente_origin()!=null &&  priUnitvente.doubleValue()!=newBean.getPrix_vente_origin()) {
		    			newBean.setPrix_vente_is_changed(true);
		    		}
		    		
		    		DeriverUnite  drvUnite=newBean.getPk().getFkcode_barre().getPk().getAr_bean().getUnitBean().getDrv();
		    		newBean.setUnite(newBean.getPk().getFkcode_barre().getPk().getAr_bean().getUnitBean().getUnite_lib());
		    		if(drvUnite!=null) {
		    			List <DetDeriverUnite> listDrv = serviceUnite.doFetchDetDeriverUniteByDrvId(drvUnite.getDrv_id())  ;
		    			for (int r = 0; r < listDrv.size();r++) {
		    				DetDeriverUnite deUnite = listDrv.get(r);
		    				DeriverOperationVente dVente = new DeriverOperationVente();
		    				Double qteOpe=ProcessNumber.doMath(newBean.getQuantite(), deUnite.getDrv_coef(), deUnite.getDrv_oper().charAt(0));
		    				dVente.setDrv_oper(deUnite.getDrv_oper());
		    				dVente.setDrv_coef(deUnite.getDrv_coef());
		    				dVente.setFkcode_barre(deUnite.getFkcode_barre());
		    				dVente.setQuantite(qteOpe);
		    				map_deriver_vente.put(newBean.getPk().getFkcode_barre().getPk().getCode_barre(), dVente);
		    				newBean.setUnite(newBean.getPk().getFkcode_barre().getPk().getAr_bean().getUnitBean().getUnite_lib()+":"+ qteOpe);
						}
		    				
		    		} 
		    		 
		    		
		    		/*****************************************Prix Unit Brute reel********************************************/
		    		
		    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
		    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente_brute,pattern);
		    		newBean.setMontant_ht_vente_reel(montant_ht_vente_brute);
		    		tot_ht_brute=ProcessNumber.addition(tot_ht_brute, montant_ht_vente_brute);
		    		/*******************************************Remise********************************************************/
		    		
		    		Double taux_remiseligne     = ss.getTaux_remise()==null?new Double(0):ss.getTaux_remise();
					Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
					                    tot_taux=ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_taux,pattern);
					newBean.setTaux_remise_ligne(tot_taux);
					Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(newBean.getMontant_ht_vente_reel(), tot_taux);
					newBean.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
					totalre_mise=ProcessNumber.addition(totalre_mise, montant_Remise_Ligne);
	    		    /*******************************************montant_ht_vente *********************************************/   
	    		    
	    		    Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, montant_Remise_Ligne);
	    		    montant_ht_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern);
		    		newBean.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern));
		    		tot_ht_net=ProcessNumber.addition(tot_ht_net, montant_ht_vente);
		    		/*********************************************montant_tva_vente ******************************************/
		    		 
		    		 
		    		Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente,ss.getTvaBean(),new DeviseBean());
		    		montant_tva_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_tva_vente,pattern);
		    		newBean.setMontant_tva_vente(montant_tva_vente);
		    		tot_tva=ProcessNumber.addition(tot_tva, montant_tva_vente); 
		    		/*********************************************montant_ttc_vente *******************************************/
		    		
		    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
		    		newBean.setMontant_ttc_vente(montant_ttc_vente);
		    		tot_ttc=ProcessNumber.addition(tot_ttc, montant_ttc_vente);    
		    		/*********************************************montant_benefice *******************************************/
		    		if(le_cout.doubleValue()>0){
		    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
		    		montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice,pattern);
		    		newBean.setMontant_benefice(montant_benefice);
		    		marge_benefice_vente=ProcessNumber.addition(marge_benefice_vente, montant_benefice);
		    		}
		    		/**********************************************************************************************************/
		    		         
		    		 String Elm=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(montant_ht_vente, pattern)  ;     
		    		 data.addProperty(newBean.getPk().getFkcode_barre().getPk().getCode_barre(),Elm);
	    				         
		    	}
		     	 
			}
			
			 beanTotal.setVente_remise_alacaisse(remise_ala_caisse);
			 totalre_mise  = ProcessFormatNbr.FormatDouble_ParameterChiffre(totalre_mise,pattern);
			 beanTotal.setVente_remise(totalre_mise); 
			 if(detailBean.getNext_Focus()!=null &&  !detailBean.getNext_Focus().equals("")){
				  setObjectValueModel("next_Focus", detailBean.getNext_Focus());
			 }
			 beanTotal.setVente_mnt_total(ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_ttc,pattern));
			 beanTotal.setVente_mnt_tva(ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_tva,pattern));
			 beanTotal.setVente_mnt_ht(ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_ht_net,pattern));
			 setObjectValueModel(BEAN_TOTAL, beanTotal);
			 String ssss=(String) getObjectValueModel("next_Focus");
			 if(ssss!=null &&  !ssss.equals(""))
			 data.addProperty("next_Focus",ssss);
			  
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

public   ModelAndView doActualiser_methode( ) throws Exception{
	
	try {
		
			List   list_mothode   =(List) getObjectValueModel(LIST_SERIE_PROVENTE);
			List   listOfmyData   =(List) getObjectValueModel(LIST_EDITABLE_VENTE);
			HashMap  mapbidan = new HashMap();
			HashMap  mapbidan_cak = new HashMap();
			for (int i = 0; i < list_mothode.size(); i++) {
				SerieArticletBean  serieBean= (SerieArticletBean) list_mothode.get(i);
				
				if(!serieBean.getTo_check().equals("")){
					mapbidan.put(serieBean.getFkCode_barre().getPk().getCode_barre(), "Traiter");
					mapbidan_cak.put(serieBean.getFkCode_barre().getPk().getCode_barre(), "Traiter");
				}else{
					mapbidan.put(serieBean.getFkCode_barre().getPk().getCode_barre(), "Généric");
				}
			}
			
			
		 
			JsonObject data = new JsonObject();
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetProcedureVenteBean  venBean= (DetProcedureVenteBean) listOfmyData.get(i);
				
				if(list_mothode==null ||  list_mothode.size()==0  ){
					data.addProperty(venBean.getPk().getFkcode_barre().getPk().getCode_barre(),venBean.getMethode_s());
					continue;
				}
				
				if(mapbidan.get(venBean.getPk().getFkcode_barre().getPk().getCode_barre())==null){
				data.addProperty(venBean.getPk().getFkcode_barre().getPk().getCode_barre(),"Généric");
				venBean.setMethode_s("Généric");
				continue;
				}
				if(mapbidan_cak.get(venBean.getPk().getFkcode_barre().getPk().getCode_barre())!=null){
				data.addProperty(venBean.getPk().getFkcode_barre().getPk().getCode_barre(),"Traiter");
				venBean.setMethode_s("Traiter");
				continue;
				}
				if(mapbidan.get(venBean.getPk().getFkcode_barre().getPk().getCode_barre())!=null
						&&   
						mapbidan.get(venBean.getPk().getFkcode_barre().getPk().getCode_barre()).equals("Généric") ){
					data.addProperty(venBean.getPk().getFkcode_barre().getPk().getCode_barre(),"Généric");
					venBean.setMethode_s("Généric");
				}
				 
		     }
			setObjectValueModel(LIST_EDITABLE_VENTE,listOfmyData);
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
public ModelAndView doFetchArticleSuivantTarif(    ProcedureVenteBean searchBean   )throws Exception { 
	
	 PrintWriter out = getResponse().getWriter();
     getResponse().setContentType(HTML_CONTENT_TYPE);
    try {
    	if(searchBean.getChoixPanel().equals("article")){
    		chargerInformationForTarif(searchBean);
    	}
    	if(searchBean.getChoixPanel().equals("fourniture")){
    		chargerInformationForTarifFourniture(searchBean);
    	}
    	
    	if(searchBean.getChoixPanel().equals("prestation")){
    		chargerInformationTarifPrestation(searchBean);
    	}
    	
    	 
    	 
    } catch (Exception e) {
    	getResponse().setStatus(500);
		out.println(e.getMessage());
	    out.close();
	}
    return null;
	
	

}


  private void chargerInformationForTarifFourniture(ProcedureVenteBean searchBean ) throws Exception {
	    try {
	  
	     
	     
	     HashMap  mapclient  =  (HashMap)getObjectValueModel( MAP_CLIENT_BEN);
	     ClientBean  ben     =  (ClientBean) mapclient.get(searchBean.getClient().getClt_id());
	     TarificationBean tBean = new TarificationBean();
	     tBean.setDernierPrix(true);
	     tBean.setDate_trf3(searchBean.getVente_date());
	     tBean.setCondition_cathegorie("'map'");
	     tBean.getFkCode_barre().getPk().getAr_bean().getFam_art().setFam_id(searchBean.getFam_id());

	    // tBean.setMethode_prix("   AND  (bean.tarif_lot is null or  bean.tarif_lot is false)  AND   bean.num_serie is null   AND  bean.depot.depot_id  is null   ");
	     //tBean.setMethode_prix2("  AND  (BA.tarif_lot is null   or  BA.tarif_lot is false)    AND   BA.num_serie is null     AND  bean.depot.depot_id  is null   ");
         List<TarificationBean> list_Tarification_vente  = serviceTarification.doFetchDatafromServer(tBean);
         
	    
 
		 HashMap mapTarification_vente_article      =   serviceTarification.doTraiTerListTarif_vente(list_Tarification_vente);
		 
//		 LieuxArticleBean   lieuxArticleBean = new LieuxArticleBean();
//		 lieuxArticleBean.getPk().setLieu(searchBean.getDepot());
//		 ClientArticleBean   cltBean = new ClientArticleBean();
//		 cltBean.getPk().setClient(searchBean.getClient());
		 
//		 List<LieuxArticleBean>  list_LieuxArcticle    =   serviceArticle.doFindLieuxArcticle(lieuxArticleBean);
//		 List<ClientArticleBean> list_ProduitClient    =   serviceArticle.doFindProduitClient(cltBean);
//		 HashMap mapClientArticleBean                  =   ProcessUtil.getHashMap_code_bean(list_ProduitClient, "pk.ref.pk.code_barre");
//		 HashMap mapLieuxArticleBean                   =   ProcessUtil.getHashMap_code_bean(list_LieuxArcticle, "pk.ref.pk.code_barre");
		 HashMap mapTarific_vente                      =   ProcessUtil.getHashMap_Key_List_FromList(list_Tarification_vente, "groupe.type_trf_id");
		
		 
		 setObjectValueModel("map_tarification_fourniture",  mapTarification_vente_article);
		 setObjectValueModel("map_list_tarifica_fourniture", mapTarific_vente);
	    
	    
		 
	    List listTarif_par_groupe  = (List)mapTarific_vente.get(String.valueOf(ben.getTyp_trfBean().getType_trf_id()));
	    List listTarif_public      = (List)mapTarific_vente.get(GROUPE_TARIF_VENTE_PUBLIC); 
	    
	    if(listTarif_par_groupe==null)           listTarif_par_groupe= new ArrayList();
	    if(listTarif_public==null)               listTarif_public= new ArrayList();
	    HashMap  map_article   = new HashMap();
	    List  list_article     = new ArrayList();
	    for (int i = 0; i < listTarif_par_groupe.size(); i++) {
	    	TarificationBean  ta=(TarificationBean)listTarif_par_groupe.get(i);
	    	String Key=ta.getFkCode_barre().getPk().getCode_barre();
	    	String article=(String) map_article.get(Key);
	    	 
	    	if(article==null  ) {//&& mapClientArticleBean.get(Key)!=null  &&  mapLieuxArticleBean.get(Key)!=null){
	    		list_article.add(ta.getFkCode_barre());
	    		map_article.put(Key, "existe");
	    	}
	    }
		    for (int i = 0; i < listTarif_public.size(); i++) {
		    	TarificationBean  ta=(TarificationBean)listTarif_public.get(i);
		    	String Key=ta.getFkCode_barre().getPk().getCode_barre();
		    	String ar=(String) map_article.get(Key);
		    	if(ar==null  ) {// && mapClientArticleBean.get(Key)!=null  &&  mapLieuxArticleBean.get(Key)!=null  ){
		    		list_article.add(ta.getFkCode_barre());
		    	}
		    }
	     setObjectValueModel("list_article_vente_fourniture"        , list_article);
		 setObjectValueModel("list_article_vente_fourniture_origine", ProcessUtil.cloneList(list_article));
		 setObjectValueModel("list_article_vente_fourniture_grid"   , ProcessUtil.cloneList(list_article));
		 HashMap mapCodBarre=(HashMap) ProcessUtil.getHashMap_code_bean(list_article, "pk.code_barre"); 
		 setObjectValueModel("mapCodBarreFourniture", mapCodBarre);
	} catch (Exception e) {
		throw e;
	}
	
}

  
  private void chargerInformationTarifPrestation(ProcedureVenteBean searchBean ) throws Exception {
	    try {
	  
	     
	     
	     HashMap  mapClient  =  (HashMap)getObjectValueModel( MAP_CLIENT_BEN);
	     ClientBean  ben     =  (ClientBean) mapClient.get(searchBean.getClient().getClt_id());
	     TarificationBean tBean = new TarificationBean();
	     tBean.setDernierPrix(true);
	     tBean.setDate_trf3(searchBean.getVente_date());
	     tBean.setCondition_cathegorie("'srv'");
	     tBean.getFkCode_barre().getPk().getAr_bean().getFam_art().setFam_id(searchBean.getFam_id());

	     //tBean.setMethode_prix("   AND  (bean.tarif_lot is null or  bean.tarif_lot is false)  AND   bean.num_serie is null   AND  bean.depot.depot_id  is null   ");
	     //tBean.setMethode_prix2("  AND  (BA.tarif_lot is null   or  BA.tarif_lot is false)    AND   BA.num_serie is null     AND  bean.depot.depot_id  is null   ");
         List<TarificationBean> list_Tarification_vente  = serviceTarification.doFetchDatafromServer(tBean);
		 HashMap mapTarification_vente_article      =   serviceTarification.doTraiTerListTarif_vente(list_Tarification_vente);
		 
//		 LieuxArticleBean   lieuxServiceBean = new LieuxArticleBean();
//		 lieuxServiceBean.getPk().setLieu(searchBean.getDepot());
//		 ClientArticleBean   cltBean = new ClientArticleBean();
//		 cltBean.getPk().setClient(searchBean.getClient());
		 
//		 List<LieuxArticleBean>  listLieuxService      =   serviceArticle.doFindLieuxArcticle(lieuxServiceBean);
//		 List<ClientArticleBean> listServiceClient     =   serviceArticle.doFindProduitClient(cltBean);
//		 HashMap mapClientArticleBean                  =   ProcessUtil.getHashMap_code_bean(listServiceClient, "pk.ref.pk.code_barre");
//		 HashMap mapLieuxArticleBean                   =   ProcessUtil.getHashMap_code_bean(listLieuxService, "pk.ref.pk.code_barre");
		 HashMap mapTarific_vente                      =   ProcessUtil.getHashMap_Key_List_FromList(list_Tarification_vente, "groupe.type_trf_id");
		
		 
		 setObjectValueModel(MAP_TARIFICATION_SERVICE,  mapTarification_vente_article);
		 setObjectValueModel(MAP_LIST_TARIFICA_SERVICE, mapTarific_vente);
	    
	    
		 
	    List listTarif_par_groupe  = (List)mapTarific_vente.get(String.valueOf(ben.getTyp_trfBean().getType_trf_id()));
	    List listTarif_public      = (List)mapTarific_vente.get(GROUPE_TARIF_VENTE_PUBLIC); 
	    
	    if(listTarif_par_groupe==null)           listTarif_par_groupe= new ArrayList();
	    if(listTarif_public==null)               listTarif_public= new ArrayList();
	    HashMap  mapService   = new HashMap();
	    List  list_article     = new ArrayList();
	    for (int i = 0; i < listTarif_par_groupe.size(); i++) {
	    	TarificationBean  ta=(TarificationBean)listTarif_par_groupe.get(i);
	    	String Key=ta.getFkCode_barre().getPk().getCode_barre();
	    	String article=(String) mapService.get(Key);
	    	 
	    	if(article==null ) { // && mapClientArticleBean.get(Key)!=null  &&  mapLieuxArticleBean.get(Key)!=null){
	    		list_article.add(ta.getFkCode_barre());
	    		mapService.put(Key, "existe");
	    	}
	    }
		    for (int i = 0; i < listTarif_public.size(); i++) {
		    	TarificationBean  ta=(TarificationBean)listTarif_public.get(i);
		    	String Key=ta.getFkCode_barre().getPk().getCode_barre();
		    	String ar=(String) mapService.get(Key);
		    	if(ar==null ) { //  && mapClientArticleBean.get(Key)!=null  &&  mapLieuxArticleBean.get(Key)!=null  ){
		    		list_article.add(ta.getFkCode_barre());
		    	}
		    }
	     setObjectValueModel(LIST_ARTICLE_VENTE_SERVICE         , list_article);
		 setObjectValueModel(LIST_ARTICLE_VENTE_SERVICE_ORIGINE , ProcessUtil.cloneList(list_article));
		 setObjectValueModel(LIST_ARTICLE_VENTE_SERVICE_GRID    , ProcessUtil.cloneList(list_article));
		 
		 HashMap mapCodBarre=(HashMap) ProcessUtil.getHashMap_code_bean(list_article, "pk.code_barre"); 
		 setObjectValueModel(MAP_CODBARRE_SERVICE, mapCodBarre);
	} catch (Exception e) {
		throw e;
	}
	
}
  
  private void chargerInformationForTarif(ProcedureVenteBean searchBean ) throws Exception {
	    try {
	  
	     
	     
	     HashMap  mapclient  =  (HashMap)getObjectValueModel( MAP_CLIENT_BEN);
	     ClientBean  ben     =  (ClientBean) mapclient.get(searchBean.getClient().getClt_id());
	     TarificationBean tBean = new TarificationBean();
	     tBean.setDernierPrix(true);
	     tBean.setDate_trf3(searchBean.getVente_date());
	     tBean.setCondition_cathegorie("'mar','syn'");
	     tBean.getFkCode_barre().getPk().getAr_bean().getFam_art().setFam_id(searchBean.getFam_id());
	     tBean.setGroupe(ben.getTyp_trfBean());
	     //tBean.setMethode_prix("   AND  (bean.tarif_lot is null or  bean.tarif_lot is false)  AND   bean.num_serie is null   AND  bean.depot.depot_id  is null   ");
	    // tBean.setMethode_prix2("  AND  (BA.tarif_lot is null   or  BA.tarif_lot is false)    AND   BA.num_serie is null     AND  bean.depot.depot_id  is null   ");
         List<TarificationBean> list_Tarification_vente  = serviceTarification.doFetchDatafromServer(tBean);
       
	    
	     //List    list_Tarification_vente          =   daoTarification.doFind_DateMax_TarificationVente(tBean);
		 HashMap mapTarification_vente_article      =   serviceTarification.doTraiTerListTarif_vente(list_Tarification_vente);
		 
		 LieuxArticleBean   lieuxArticleBean = new LieuxArticleBean();
		 lieuxArticleBean.getPk().setLieu(searchBean.getDepot());
		 ClientArticleBean   cltBean = new ClientArticleBean();
		 cltBean.getPk().setClient(searchBean.getClient());
		 
//		 List<LieuxArticleBean>  list_LieuxArcticle    =   serviceArticle.doFindLieuxArcticle(lieuxArticleBean);
//		 List<ClientArticleBean> list_ProduitClient    =   serviceArticle.doFindProduitClient(cltBean);
//		 HashMap mapClientArticleBean                  =   ProcessUtil.getHashMap_code_bean(list_ProduitClient, "pk.ref.pk.code_barre");
//		 HashMap mapLieuxArticleBean                   =   ProcessUtil.getHashMap_code_bean(list_LieuxArcticle, "pk.ref.pk.code_barre");
		 HashMap mapTarific_vente                      =   ProcessUtil.getHashMap_Key_List_FromList(list_Tarification_vente, "groupe.type_trf_id");
		
		 
		 setObjectValueModel(MAP_TARIFICATION,  mapTarification_vente_article);
		 setObjectValueModel(MAP_LIST_TARIFICA, mapTarific_vente);
	    
	    
		HashMap  mapTypeTarif      = (HashMap)getObjectValueModel(MAP_LIST_TARIFICA);
	    List listTarif_par_groupe  = (List)mapTypeTarif.get(String.valueOf(ben.getTyp_trfBean().getType_trf_id()));
	    List listTarif_public      = (List)mapTypeTarif.get(GROUPE_TARIF_VENTE_PUBLIC); 
	    
	    if(listTarif_par_groupe==null)           listTarif_par_groupe= new ArrayList();
	    if(listTarif_public==null)               listTarif_public= new ArrayList();
	    HashMap  map_article   = new HashMap();
	    List  list_article     = new ArrayList();
	    for (int i = 0; i < listTarif_par_groupe.size(); i++) {
	    	TarificationBean  ta=(TarificationBean)listTarif_par_groupe.get(i);
	    	String Key=ta.getFkCode_barre().getPk().getCode_barre();
	    	String article=(String) map_article.get(Key);
	    	 
	    	 if(article==null  ) { //&& mapClientArticleBean.get(Key)!=null  &&  mapLieuxArticleBean.get(Key)!=null){
	    		list_article.add(ta.getFkCode_barre());
	    		map_article.put(Key, "existe");
	    	 }
	    }
		    for (int i = 0; i < listTarif_public.size(); i++) {
		    	TarificationBean  ta=(TarificationBean)listTarif_public.get(i);
		    	String Key=ta.getFkCode_barre().getPk().getCode_barre();
		    	String ar=(String) map_article.get(Key);
		    	 if(ar==null ) { //  && mapClientArticleBean.get(Key)!=null  &&  mapLieuxArticleBean.get(Key)!=null  ){
		    		list_article.add(ta.getFkCode_barre());
		    	 }
		    }
	     setObjectValueModel(LIST_ARTICLE_VENTE        , list_article);
		 setObjectValueModel(LIST_ARTICLE_VENTE_ORIGINE, ProcessUtil.cloneList(list_article));
		 setObjectValueModel(LIST_ARTICLE_VENTE_GRID   , ProcessUtil.cloneList(list_article));
		 HashMap mapCodBarre=(HashMap) ProcessUtil.getHashMap_code_bean(list_article, "pk.code_barre"); 
		 setObjectValueModel("mapCodBarre", mapCodBarre);
	} catch (Exception e) {
		throw e;
	}
	
}

	@SuppressWarnings("unchecked")
	public   ModelAndView doCheked_unCheked( ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_VENTE);
			String to_check=getRequest().getParameter("statucheked")==null?"":getRequest().getParameter("statucheked");
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetProcedureVenteBean newBean =(DetProcedureVenteBean) listOfmyData.get(i);
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
	
	 
	public   ModelAndView doAdd_row_Editable( ProcedureVenteBean detailBean  ) throws Exception{
		  
		 
		
		try {
			
			List listOfmyData  =(List) getObjectValueModel(LIST_EDITABLE_VENTE);
			HashMap  map_deriver_vente  =(HashMap) getObjectValueModel(MAP_DERIVER_VENTE);
			
			 
			
			
			
			setObjectValueModel(FORM_BEAN,detailBean); 
			List list_article_Origine =(List) getObjectValueModel(LIST_ARTICLE_VENTE_ORIGINE);
			HashMap  map_articl=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			if(map_articl.get(detailBean.getCode_barreX())!=null)  throw new Exception("Existe déjà");
			Double  quantite_en_stock= new Double(0);
			HashMap  mapCodBarre=(HashMap) getObjectValueModel("mapCodBarre"  );
			Code_barreBean bCode_barreBean=(Code_barreBean) mapCodBarre.get(detailBean.getCode_barreX());
			
		    if(  !StringUtils.isEmpty(  bCode_barreBean.getPk().getAr_bean().getCathegorie().getData_id()) &&  
					 
						(  bCode_barreBean.getPk().getAr_bean().getCathegorie().getData_id().equals("mar") 	)
							 
			){
			Stock_articleBean beanSBean= new Stock_articleBean();
		    beanSBean.getPk().getFkCode_barre().getPk().setCode_barre(detailBean.getCode_barreX());
		    beanSBean.getPk().setDepot(detailBean.getDepot());
		    beanSBean.setCondition_max_date_stock(  "    " +
					 "      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
				     "      where  beaK.pk.date_stock  <=  '"+ProcessDate.getStringFormatDate(detailBean.getVente_date())+"'        " +
				     "           AND    beaK.pk.fkCode_barre.pk.code_barre=bean.pk.fkCode_barre.pk.code_barre     "+
				     "           AND    beaK.pk.depot.depot_id="+beanSBean.getPk().getDepot().getDepot_id()+"      )               ");
			List llis=serviceStock_article.doFetchDatafromServer(beanSBean);
			Stock_articleBean beanR= new Stock_articleBean();
		
		
				if(llis!=null  &&  llis.size()>0){
				    beanR=(Stock_articleBean) llis.get(0);
				    quantite_en_stock=beanR.getSolde_stock();
//				    if(quantite_en_stock.doubleValue()==0)
//				    	throwNewException("cet article n'est pas disponible en stock");
//				    if(quantite_en_stock.doubleValue()<detailBean.getQuantiteX().doubleValue())
//				    	throwNewException("cette quantite n'est pas disponible en stock");
				    
				}else{
				    beanR= new Stock_articleBean();
				   // throwNewException("cet article n'est pas disponible en stock");
				} 
			 }
			
			
			HashMap  MAP_ARTICLE=ProcessUtil.getHashMap_code_bean(list_article_Origine, "pk.code_barre");
			Code_barreBean  cBean=(Code_barreBean) MAP_ARTICLE.get(detailBean.getCode_barreX());
			DetProcedureVenteBean beanLigne= new DetProcedureVenteBean();
			beanLigne.getPk().setFkcode_barre(cBean);
			beanLigne.setQuantite(detailBean.getQuantiteX());
			beanLigne.setObservation("");
			
			
			HashMap  map        = (HashMap) getObjectValueModel(MAP_CLIENT_BEN);
			ClientBean   beancl = (ClientBean) map.get(detailBean.getClient().getClt_id()); 
			String pattern ="0.000";
			if( detailBean.getDevise().getDev_id().intValue()==191  ||  detailBean.getDevise().getDev_id().intValue()==192   ){
				pattern ="0.00";
			}
		 
			Double getTaux_remise_alacaisse = detailBean.getTaux_remise_alacaisse()==null?new Double(0):detailBean.getTaux_remise_alacaisse();
			TarificationBean ss= definitionTarification_devente(  detailBean,bCode_barreBean,beancl);
			 
	    	if(ss!=null){
	     
	    		beanLigne.setTarif(ss);
	    		beanLigne.setQuantite_en_stock(quantite_en_stock);
	    		
	    		DeriverUnite  drvUnite=cBean.getPk().getAr_bean().getUnitBean().getDrv();
	    		beanLigne.setUnite(cBean.getPk().getAr_bean().getUnitBean().getUnite_lib());
	    		if(drvUnite!=null && drvUnite.getDrv_id()!=null) {
	    			List <DetDeriverUnite> listDrv = serviceUnite.doFetchDetDeriverUniteByDrvId(drvUnite.getDrv_id())  ;
	    			for (int r = 0; r < listDrv.size();r++) {
	    				DetDeriverUnite deUnite = listDrv.get(r);
	    				DeriverOperationVente dVente = new DeriverOperationVente();
	    				Double qteOpe=ProcessNumber.doMath(beanLigne.getQuantite(), deUnite.getDrv_coef(), deUnite.getDrv_oper().charAt(0));
	    				dVente.setDrv_oper(deUnite.getDrv_oper());
	    				dVente.setDrv_coef(deUnite.getDrv_coef());
	    				dVente.setFkcode_barre(deUnite.getFkcode_barre());
	    				dVente.setQuantite(qteOpe);
	    				map_deriver_vente.put(detailBean.getCode_barreX(), dVente);
	    				beanLigne.setUnite(cBean.getPk().getAr_bean().getUnitBean().getUnite_lib()+":"+ qteOpe);
					}
	    		} 
	    		
	    	
	    		/*****************************************le cout ********************************************/
	    		Double getQuantiteX       = detailBean.getQuantiteX()==null?new Double(0):detailBean.getQuantiteX();
	    		Double                 qte=ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX,pattern); 
	    		Double  cout              = ss.getCout()==null?new Double(0):ss.getCout().getTarif_unit_article();
	    		Double	Prixcout          = cout==null?new Double(0):cout;
	    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
	    		le_cout=ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout,pattern); 
	    		
	    		/*****************************************  setInfo  ********************************************/
	    		
	    		String libelle_desi=beanLigne.getPk().getFkcode_barre().getDesignation_libelle();
	    		 
	    		
	    		String groupe=beanLigne.getTarif().getGroupe().getType_trf_lib();
				String lot=beanLigne.getTarif().getTarif_lot()!=null && beanLigne.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
				String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
				beanLigne.setInfo(libelle_desi+natureprix);
	    		Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(ss.getTarif_unit_vente(),pattern); 
	    		
	    		beanLigne.setPrix_vente_origin(    priUnitvente );
	    		
	    		
	    		/*****************************************Prix Unit Brute reel********************************************/
	    		
	    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
	    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente_brute,pattern); 
	    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
	    		 
	    		/*******************************************Remise********************************************************/
	    		
	    		Double taux_remiseligne     = ss.getTaux_remise()==null?new Double(0):ss.getTaux_remise();
				Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
				                    tot_taux=ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_taux,pattern); 
				beanLigne.setTaux_remise_ligne(tot_taux);
				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_vente_reel(), tot_taux);
				beanLigne.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));

    		    /*******************************************montant_ht_vente *********************************************/   
    		    
    		    Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, montant_Remise_Ligne);
    		    montant_ht_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern); 
    		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern) );
	    		
	    		/*********************************************montant_tva_vente ******************************************/
	    		 
	    		 
	    		Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente,ss.getTvaBean(),new DeviseBean());
	    		montant_tva_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_tva_vente,pattern); 
	    		beanLigne.setMontant_tva_vente(montant_tva_vente);
	    		
	    		/*********************************************montant_ttc_vente *******************************************/
	    		
	    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
	    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
	    		
	    		/*********************************************montant_benefice *******************************************/
	    		if(le_cout.doubleValue()>0){
	    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
	    		montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice,pattern); 
	    		beanLigne.setMontant_benefice(montant_benefice);
	    		}
	    		/**********************************************************************************************************/
	    		 
	    		
	    	}
	    	
			listOfmyData.add(beanLigne);
			setObjectValueModel(LIST_EDITABLE_VENTE,listOfmyData);
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			
			
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_Origine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_Origine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_dem_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_VENTE_GRID,list_article_dem_achatGrid);
					setObjectValueModel(LIST_ARTICLE_VENTE,list_article_dem_achat);
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
	
	
public   ModelAndView doAdd_row_Fourniture( ProcedureVenteBean detailBean  ) throws Exception{
		  
	 
		
		try { 
			 
			List listOfmyData  =(List) getObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE);
			setObjectValueModel(FORM_BEAN,detailBean); 
			List list_article_Origine =(List) getObjectValueModel(LIST_ARTICLE_VENTE_FOURNITURE_ORIGINE);
			HashMap  map_articl=ProcessUtil.getHashMap_code_bean(listOfmyData, "fkcode_barre.pk.code_barre");
			if(map_articl.get(detailBean.getCode_barreFurniture())!=null)  throw new Exception("Existe déjà");
			Double  quantite_en_stock= new Double(0);
			HashMap  mapCodBarre=(HashMap) getObjectValueModel(MAP_CODBARRE_FOURNITURE );
			Code_barreBean bCode_barreBean=(Code_barreBean) mapCodBarre.get(detailBean.getCode_barreFurniture());
			
			 if(  !StringUtils.isEmpty(  bCode_barreBean.getPk().getAr_bean().getCathegorie().getData_id()) &&  
					 
						(  bCode_barreBean.getPk().getAr_bean().getCathegorie().getData_id().equals("map") 	)
							 
					 ){
			Stock_articleBean beanSBean= new Stock_articleBean();
		    beanSBean.getPk().getFkCode_barre().getPk().setCode_barre(detailBean.getCode_barreFurniture());
		    beanSBean.getPk().setDepot(detailBean.getDepot());
		    beanSBean.setCondition_max_date_stock(  "    " +
					 "      AND    bean.pk.date_stock  in  ( select  max( beaK.pk.date_stock ) from  Stock_articleBean beaK    " +
				     "      where  beaK.pk.date_stock  <=  '"+ProcessDate.getStringFormatDate(detailBean.getVente_date())+"'        " +
				     "           AND    beaK.pk.fkCode_barre.pk.code_barre=bean.pk.fkCode_barre.pk.code_barre     "+
				     "           AND    beaK.pk.depot.depot_id="+beanSBean.getPk().getDepot().getDepot_id()+"      )               ");
			List llis=serviceStock_article.doFetchDatafromServer(beanSBean);
			Stock_articleBean beanR= new Stock_articleBean();
		
		
				if(llis!=null  &&  llis.size()>0){
				    beanR=(Stock_articleBean) llis.get(0);
				    quantite_en_stock=beanR.getSolde_stock();
//				    if(quantite_en_stock.doubleValue()==0)
//				    	throwNewException("cet article n'est pas disponible en stock");
//				    if(quantite_en_stock.doubleValue()<  detailBean.getQuantiteFourniture().doubleValue())
//				    	throwNewException("cette quantite n'est pas disponible en stock");
				    
				}else{
				    beanR= new Stock_articleBean();
				   // throwNewException("cet article n'est pas disponible en stock");
				} 
			 }
			
			
			HashMap  MAP_ARTICLE=ProcessUtil.getHashMap_code_bean(list_article_Origine, "pk.code_barre");
			Code_barreBean  cBean=(Code_barreBean) MAP_ARTICLE.get(detailBean.getCode_barreFurniture());
			DetFournitureVenteBean   beanLigne= new DetFournitureVenteBean();
			beanLigne.setFkcode_barre(cBean);
			beanLigne.setQuantite( detailBean.getQuantiteFourniture());
			beanLigne.setObservation("");
			beanLigne.setIsVente(detailBean.getIsVente());
			
			HashMap  map        = (HashMap) getObjectValueModel(MAP_CLIENT_BEN);
			ClientBean   beancl = (ClientBean) map.get(detailBean.getClient().getClt_id()); 
			
		 
			 
			TarificationBean ss = definitionTarification_deventeFouniture(  detailBean  ,bCode_barreBean,beancl);
			
			
			 
	    	if(ss!=null){
	    	
	    		beanLigne.setTarifVente(ss);
	    		beanLigne.setQuantite_en_stock(quantite_en_stock);
	    		/*****************************************le cout ********************************************/
	    		Double getQuantiteX       = detailBean.getQuantiteFourniture()==null?new Double(0):detailBean.getQuantiteFourniture();
	    		Double                 qte=ProcessFormatNbr.FormatDouble_Troischiffre(getQuantiteX);
	    		Double  cout              = ss.getCout()==null?new Double(0):ss.getCout().getTarif_unit_article();
	    		Double	Prixcout          = cout==null?new Double(0):cout;
	    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
	    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
	    		
	    		/*****************************************  setInfo  ********************************************/
	    		
	    		String libelle_desi=beanLigne.getFkcode_barre().getDesignation_libelle();
	    		 
	    		
	    		String groupe=beanLigne.getTarifVente().getGroupe().getType_trf_lib();
				String lot=beanLigne.getTarifVente().getTarif_lot()!=null && beanLigne.getTarifVente().getTarif_lot().equals(true)?" * de lot   ":"";
				String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
				beanLigne.setInfo(libelle_desi+natureprix);
	    		Double priUnitvente=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
	    		
	    		
	    		/*****************************************Prix Unit Brute reel********************************************/
	    		
	    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
	    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute);
	    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
    		     
    		    Double montant_ht_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute);
    		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente));
	    		
	    		/*********************************************montant_tva_vente ******************************************/
	    		 
	    		Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente,ss.getTvaBean(),new DeviseBean());
	    		montant_tva_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_vente);
	    		beanLigne.setMontant_tva_vente(montant_tva_vente);
	    		
	    		/*********************************************montant_ttc_vente *******************************************/
	    		
	    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
	    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
	    		
	    		/*********************************************montant_benefice *******************************************/
	    		if(le_cout.doubleValue()>0){
	    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
	    		montant_benefice          =ProcessFormatNbr.FormatDouble_Troischiffre(montant_benefice);
	    		beanLigne.setMontant_benefice(montant_benefice);
	    		
	    		}
	    		/**********************************************************************************************************/
	    		 
	    		
	    	}
	    	
			listOfmyData.add(beanLigne);
			setObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE,listOfmyData);
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "fkcode_barre.pk.code_barre");
			
			
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_Origine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_Origine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_dem_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_VENTE_FOURNITURE_GRID,list_article_dem_achatGrid);
					setObjectValueModel(LIST_ARTICLE_VENTE_FOURNITURE,list_article_dem_achat);
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


public   ModelAndView doAdd_row_Prestation( ProcedureVenteBean detailBean  ) throws Exception{
	  
	 
	
	try { 
		 
		List listOfmyData  =(List) getObjectValueModel(LIST_EDITABLE_PRESTATION);
		setObjectValueModel(FORM_BEAN,detailBean); 
		List list_article_Origine =(List) getObjectValueModel(LIST_ARTICLE_VENTE_SERVICE_ORIGINE);
		
	 
		
		HashMap  map_articl=ProcessUtil.getHashMap_code_bean(listOfmyData, "fkcode_barre.pk.code_barre");
		if(map_articl.get(detailBean.getCode_barreService())!=null)  throw new Exception("Existe déjà");
		Double  quantite_en_stock= new Double(0);
		HashMap  mapCodBarre=(HashMap) getObjectValueModel(MAP_CODBARRE_SERVICE );
		Code_barreBean bCode_barreBean=(Code_barreBean) mapCodBarre.get(detailBean.getCode_barreFurniture());
 
		
		
		HashMap  MAP_ARTICLE=ProcessUtil.getHashMap_code_bean(list_article_Origine, "pk.code_barre");
		Code_barreBean  cBean=(Code_barreBean) MAP_ARTICLE.get(detailBean.getCode_barreService());
		DetServiceBean   beanLigne= new DetServiceBean();
		beanLigne.setFkcode_barre(cBean);
		beanLigne.setQuantite( detailBean.getQuantiteService());
		beanLigne.setObservation("");
		beanLigne.setIsVente(detailBean.getIsVentePrestation());
		
		HashMap  map        = (HashMap) getObjectValueModel(MAP_CLIENT_BEN);
		ClientBean   beancl = (ClientBean) map.get(detailBean.getClient().getClt_id()); 
		
	 
		 
		TarificationBean ss= definitionTarificationService(  detailBean  ,bCode_barreBean,beancl);
		
		
		 
    	if(ss!=null){
    	
    		beanLigne.setTarifVente(ss);
    	 
    		/*****************************************le cout ********************************************/
    		Double getQuantiteX       = detailBean.getQuantiteService()==null?new Double(0):detailBean.getQuantiteService();
    		Double                 qte=ProcessFormatNbr.FormatDouble_Troischiffre(getQuantiteX);
    		Double  cout              = ss.getCout()==null?new Double(0):ss.getCout().getTarif_unit_article();
    		Double	Prixcout          = cout==null?new Double(0):cout;
    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
    		
    		/*****************************************  setInfo  ********************************************/
    		
    		String libelle_desi=beanLigne.getFkcode_barre().getDesignation_libelle();
    		 
    		
    		String groupe=beanLigne.getTarifVente().getGroupe().getType_trf_lib();
			String lot=beanLigne.getTarifVente().getTarif_lot()!=null && beanLigne.getTarifVente().getTarif_lot().equals(true)?" * de lot   ":"";
			String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
			beanLigne.setInfo(libelle_desi+natureprix);
    		Double priUnitvente=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
    		
    		/*****************************************Prix Unit Brute reel********************************************/
    		
    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute);
    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
		     
		    Double montant_ht_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute);
		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente));
    		
    		/*********************************************montant_tva_vente ******************************************/
		 
		    
		    Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente,ss.getTvaBean(),new DeviseBean());
    		montant_tva_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_vente);
    		beanLigne.setMontant_tva_vente(montant_tva_vente);
    		
    		/*********************************************montant_ttc_vente *******************************************/
    		
    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
    		
    		/*********************************************montant_benefice *******************************************/
    		if(le_cout.doubleValue()>0){
    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
    		montant_benefice          =ProcessFormatNbr.FormatDouble_Troischiffre(montant_benefice);
    		beanLigne.setMontant_benefice(montant_benefice);
    		}
    		/**********************************************************************************************************/
    		 
    		
    	}
    	
		listOfmyData.add(beanLigne);
		setObjectValueModel(LIST_EDITABLE_PRESTATION,listOfmyData);
		
		HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "fkcode_barre.pk.code_barre");
		
		
		List list_article_dem_achatGrid = new ArrayList();
		List list_article_dem_achat = new ArrayList();
		for (int i = 0; i < list_article_Origine.size(); i++) {
			Code_barreBean    bean  =(Code_barreBean) list_article_Origine.get(i);
			if(mapd.get(bean.getPk().getCode_barre())==null){
				list_article_dem_achatGrid.add(bean);
				list_article_dem_achat.add(bean);
				setObjectValueModel(LIST_ARTICLE_VENTE_SERVICE_GRID,list_article_dem_achatGrid);
				setObjectValueModel(LIST_ARTICLE_VENTE_SERVICE,list_article_dem_achat);
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
	
private TarificationBean definitionTarification_deventeFouniture( ProcedureVenteBean detailBean  ,Code_barreBean bCode_barreBean , ClientBean beancl ) throws Exception{
	 
	TarificationBean  ss  = null; 
	 try {
		
	   HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION_FOURNITURE);
	   ss          = (TarificationBean) mapTarification.get(detailBean.getCode_barreFurniture()+"µ"+beancl.getTyp_trfBean().getType_trf_id());
     if(bCode_barreBean.getPk().getAr_bean().getMode()!=null 
    		 &&  bCode_barreBean.getPk().getAr_bean().getMode().getData_id().equals("pl")){
    	 SerieArticletBean searchBean  = new SerieArticletBean();
    	 searchBean.setCondition_list_article("  AND  bean.quantite > 0   AND  ( bean.serie_bloque  is null  or  bean.serie_bloque = false ) " +
    						"                    AND  bean.selected='X' ");
    	 searchBean.setFkCode_barre(bCode_barreBean);
    	 searchBean.getPk().setDepot(detailBean.getDepot());
    	 List<SerieArticletBean> list_DataSrv              = serviceDocumentLot.doFetchDatafromServer(searchBean);
    	 String  Lot= "" ;
    	 Double qt= new Double(0);
    	 for (int i = 0; i < list_DataSrv.size(); i++) {
    		 SerieArticletBean sBean =  list_DataSrv.get(i);
    		 qt=ProcessNumber.addition(qt, sBean.getQuantite());
    		 Lot=sBean.getPk().getNum_serie();
		 }
    	 if(qt.doubleValue()<detailBean.getQuantiteFourniture().doubleValue())
		    	throwNewException("cette quantité n'est pas disponible en Lot choisis");
    	 
    	 
    	 TarificationBean search2= new TarificationBean();
    	 search2.setDernierPrix(true);
    	 search2.setDate_trf3(detailBean.getVente_date());
    	 search2.setFkCode_barre(bCode_barreBean);
    	 search2.setGroupe(beancl.getTyp_trfBean());
    	 search2.setTarif_lot(true);
    	 search2.setDepot(detailBean.getDepot());
    	 search2.setNum_serie(Lot);
         List<TarificationBean> listDatafro2  = serviceTarification.doFetchDatafromServer(search2);
         if(listDatafro2!=null  && listDatafro2.size()>0 && listDatafro2.size()>1){
        	 throwNewException("Il ya deux prix de lot  dans la même journée pour cet article. Veuillez Vérifier les Tarifs de vente ");
         }
         if(listDatafro2!=null  && listDatafro2.size()>0 && listDatafro2.size()==1){
        	 ss=listDatafro2.get(0);
        	String groupe=ss.getGroupe().getType_trf_lib();
			String lot=ss.getTarif_lot()!=null && ss.getTarif_lot().equals(true)?" * de lot   ":"";
         }else{
        	 throwNewException("Manque Tarification de Lot pour cette article"); 
         }
     }else {
     }
     
     
	
	if(ss==null)  
	{ 
		ss  =(TarificationBean) mapTarification.get(detailBean.getCode_barreFurniture()+"µ"+GROUPE_TARIF_VENTE_PUBLIC);
	}
	if(ss==null)  throwNewException("Manque Tarification pour cette article");
	
	 } catch (Exception e) {
			throw e;
		}
	 
	return ss;
}


private TarificationBean definitionTarificationService( ProcedureVenteBean detailBean  ,Code_barreBean bCode_barreBean , ClientBean beancl ) throws Exception{
	 
	TarificationBean  ss  = null; 
	 try {
		
		   HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION_SERVICE);
		   ss          = (TarificationBean) mapTarification.get(detailBean.getCode_barreService()+"µ"+beancl.getTyp_trfBean().getType_trf_id());
			if(ss==null)  
			{ 
				ss  =(TarificationBean) mapTarification.get(detailBean.getCode_barreFurniture()+"µ"+GROUPE_TARIF_VENTE_PUBLIC);
			}
			if(ss==null)  throwNewException("Manque Tarification pour cette article");
			
			 } catch (Exception e) {
					throw e;
				}
	 
	return ss;
}


	private TarificationBean definitionTarification_devente( ProcedureVenteBean detailBean ,Code_barreBean bCode_barreBean , ClientBean beancl ) throws Exception{
		 
		TarificationBean  ss  = null; 
		 try {
			
		   HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION);
		   ss          = (TarificationBean) mapTarification.get(detailBean.getCode_barreX()+"µ"+beancl.getTyp_trfBean().getType_trf_id());
	     if(bCode_barreBean.getPk().getAr_bean().getMode()!=null 
	    		 &&  bCode_barreBean.getPk().getAr_bean().getMode().getData_id().equals("pl")){
	    	 SerieArticletBean searchBean  = new SerieArticletBean();
	    	 searchBean.setCondition_list_article("  AND  bean.quantite > 0   AND  ( bean.serie_bloque  is null  or  bean.serie_bloque = false ) " +
	    						"                    AND  bean.selected='X' ");
	    	 searchBean.setFkCode_barre(bCode_barreBean);
	    	 searchBean.getPk().setDepot(detailBean.getDepot());
	    	 List<SerieArticletBean> list_DataSrv              = serviceDocumentLot.doFetchDatafromServer(searchBean);
	    	 String  Lot= "" ;
	    	 Double qt= new Double(0);
	    	 for (int i = 0; i < list_DataSrv.size(); i++) {
	    		 SerieArticletBean sBean =  list_DataSrv.get(i);
	    		 qt=ProcessNumber.addition(qt, sBean.getQuantite());
	    		 Lot=sBean.getPk().getNum_serie();
			 }
	    	 if(qt.doubleValue()<detailBean.getQuantiteX().doubleValue())
			    	throwNewException("cette quantité n'est pas disponible en Lot choisis");
	    	 
	    	 
        	 TarificationBean search2= new TarificationBean();
        	 search2.setDernierPrix(true);
        	 search2.setDate_trf3(detailBean.getVente_date());
        	 search2.setFkCode_barre(bCode_barreBean);
        	 search2.setGroupe(beancl.getTyp_trfBean());
        	 search2.setTarif_lot(true);
        	 search2.setDepot(detailBean.getDepot());
        	 search2.setNum_serie(Lot);
             List<TarificationBean> listDatafro2  = serviceTarification.doFetchDatafromServer(search2);
             if(listDatafro2!=null  && listDatafro2.size()>0 && listDatafro2.size()>1){
            	 throwNewException("Il ya deux prix de lot  dans la même journée pour cet article. Veuillez Vérifier les Tarifs de vente ");
             }
	         if(listDatafro2!=null  && listDatafro2.size()>0 && listDatafro2.size()==1){
	        	 ss=listDatafro2.get(0);
	        	String groupe=ss.getGroupe().getType_trf_lib();
				String lot=ss.getTarif_lot()!=null && ss.getTarif_lot().equals(true)?" * de lot   ":"";
	         }else{
	        	 throwNewException("Manque Tarification de Lot pour cette article"); 
	         }
	     }else {
	     }
	     
	     
		
		if(ss==null)  
		{ 
			ss  =(TarificationBean) mapTarification.get(detailBean.getCode_barreX()+"µ"+GROUPE_TARIF_VENTE_PUBLIC);
		}
		if(ss==null)  throwNewException("Manque Tarification pour cette article");
		
		 } catch (Exception e) {
				throw e;
			}
		 
		return ss;
	}

	@SuppressWarnings("unchecked")
	public   ModelAndView doDelete_row_Editable( ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_VENTE);
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				DetProcedureVenteBean newBean= (DetProcedureVenteBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
			}
			if(!del) throw new Exception ((String) getObjectValueModel("_cochezAumoin"));
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			List list_article_achatOrigine =(List) getObjectValueModel(LIST_EDITABLE_VENTE_CLONE);
			List list_article_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
				}
				
			}
			setObjectValueModel(LIST_ARTICLE_VENTE_GRID,list_article_achatGrid);
			setObjectValueModel(LIST_ARTICLE_VENTE,list_article_dem_achat);
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
	public   ModelAndView doDeleteRowService( ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_PRESTATION);
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				DetServiceBean   newBean= (DetServiceBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
			}
			if(!del) throw new Exception ((String) getObjectValueModel("_cochezAumoin"));
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "fkcode_barre.pk.code_barre");
			List list_article_achatOrigine =(List) getObjectValueModel(LIST_EDITABLE_PRESTATION_CLONE);
			List list_article_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
				}
				
			}
			setObjectValueModel(LIST_ARTICLE_VENTE_SERVICE_GRID,list_article_achatGrid);
			setObjectValueModel(LIST_ARTICLE_VENTE_SERVICE,list_article_dem_achat);
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
	public   ModelAndView doDeleteRowFourniture( ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE);
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				DetFournitureVenteBean   newBean= (DetFournitureVenteBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
			}
			if(!del) throw new Exception ((String) getObjectValueModel("_cochezAumoin"));
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			List list_article_achatOrigine =(List) getObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE_CLONE);
			List list_article_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
				}
				
			}
			setObjectValueModel(LIST_ARTICLE_VENTE_FOURNITURE_GRID,list_article_achatGrid);
			setObjectValueModel(LIST_ARTICLE_VENTE_FOURNITURE,list_article_dem_achat);
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
	public ModelAndView doFetchData(ProcedureVenteBean searchBean)throws Throwable {
			try {
				
				BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				if(bs.getFct_id().equals(Fn_Modifier)  ){
					searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id in ('"+Fn_Créer+"','"+Fn_Modifier+"','"+Fn_Servir+"')   ");
				}
				if(bs.getFct_id().equals(Fn_Confirmer) ){
					searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id in ('"+Fn_Créer+"','"+Fn_Modifier+"','"+Fn_Servir+"')   ");
				}
				
				if(bs.getFct_id().equals(Fn_Transférer) ){
					searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id in ('"+Fn_Créer+"','"+Fn_Modifier+"','"+Fn_Servir+"')   ");
				}
				
				if(bs.getFct_id().equals(Fn_Corriger) ){
					searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id in ('"+Fn_Confirmer+"')   ");
				}
				
				if(bs.getFct_id().equals(Fn_Supprimer) ){
					searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id not in ('"+Fn_Facturer+"')   ");
				}
				 
				List listDataSrv = serviceProcedureVente.doFetchDatafromServer(searchBean);
				Double totgrid= new Double(0);
				 
				for (int i = 0; i < listDataSrv.size(); i++) {
					ProcedureVenteBean  reBean	=(ProcedureVenteBean) listDataSrv.get(i);
					totgrid=ProcessNumber.addition(totgrid,  ProcessFormatNbr.FormatDouble_Troischiffre(reBean.getVente_mnt_total()) );
				}
				setObjectValueModel("totGridVente", totgrid);
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
	public ModelAndView doFetchDataSerie(ProcedureVenteBean searchBe)throws Throwable {
			try {
				ProcedureVenteBean pBean     =(ProcedureVenteBean) getObjectValueModel(FORM_BEAN) ;
				String  article_request      =getRequest().getParameter("id_articlo")==null?"":getRequest().getParameter("id_articlo");
				List   listOfmyData          =(List) getObjectValueModel(LIST_EDITABLE_VENTE);
				HashMap  map_eff             =ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
				DetProcedureVenteBean  bvente=(DetProcedureVenteBean) map_eff.get(article_request);
				List     listOfData          =(List)   getObjectValueModel(LIST_SERIE_PROVENTE);
				String   article_session     =(String) getObjectValueModel(ARTICLO);
				HashMap   maptRai            =(HashMap) getObjectValueModel("mapokil");
				
				boolean enter=false;
				if(article_session!=null && !article_session.equals("")){
					if(article_session.equals(article_request)){
						enter=false;
					}else{
						enter=true;
					}
				}else{
					enter=true;
				}
				
				
				 if( !enter  &&  listOfData==null ){  setObjectValueModel(LIST_SERIE_PROVENTE,new ArrayList());  }
				
				 if( enter ){
					 
				     if(maptRai==null){
						   maptRai= new HashMap();
					   }
					   String   maptRaidd   = (String) maptRai.get(LIST_SERIE_PROVENTE+article_request);
					   if(maptRaidd==null){
						   maptRai.put(LIST_SERIE_PROVENTE+article_request, "pddcdfefokll");
						   setObjectValueModel("mapokil",maptRai);
					   }
				     
					 setObjectValueModel(ARTICLO,article_request);
					 List lis_lot_article=(List) getObjectValueModel(LIST_SERIE_PROVENTE+article_request);
				     if(lis_lot_article!=null){
				      setObjectValueModel(LIST_SERIE_PROVENTE, lis_lot_article);
			        }else{
			    	 
			    	 SerieArticletBean  beanSerie= new SerieArticletBean();
					 beanSerie.setCondition_list_article("   AND   bean.date_utilisation  <= '"+ProcessDate.getStringFormatDate(searchBe.getVente_date())+"'    " +
							                             "   AND   bean.quantite  > 0  "+
							 					         "   AND  " +
							" ( bean.date_peremption  is null   OR    bean.date_peremption  > '"+ProcessDate.getStringFormatDate(searchBe.getVente_date())+"' )   " +
					 		"                                AND   bean.fkCode_barre.pk.code_barre in ( '"+  article_request + "')    " );
					 
					 if(searchBe.getFifo()) 
					   beanSerie.setCondition_fifo_lifo("  ORDER BY  bean.date_serie  ASC    ");
					 else
					   beanSerie.setCondition_fifo_lifo("  ORDER BY  bean.date_serie  DESC   ");
					 
					   beanSerie.getPk().setDepot(pBean.getDepot());
					   List listDataSrv=serviceDocumentLot.doFetchDatafromServer(beanSerie);
					   setObjectValueModel(LIST_SERIE_PROVENTE+article_request, listDataSrv);
					   setObjectValueModel(LIST_SERIE_PROVENTE, listDataSrv);
					   
					   //JsonArray data = new JsonArray();
					   //EditableDataTableRequestParam  parameter = DataEditableTablesParamUtility.getParam(request);
					   //List listOfmyDataTwo = DataEditableTablesParamUtility.doGetAzizWild3asoul(parameter, listDataSrv,jsonResponse, data, request, response);
					   
				    	
				  }
				
				
				 
				 }
				 JsonObject data = new JsonObject();
				 
				data.addProperty("depot", pBean.getDepot().getDepot_libelle());
			    data.addProperty("venteId", pBean.getVente_id());
			    data.addProperty("ref", bvente.getPk().getFkcode_barre().getPk().getCode_barre());
			    data.addProperty("lib", bvente.getPk().getFkcode_barre().getDesignation_libelle());
			    data.addProperty("Qte", bvente.getQuantite());
			     
			    
			    
			    
			    getResponse().setContentType(JSON_CONTENT_TYPE);
				getResponse().getWriter().print(data.toString());
			    
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	
	
public ModelAndView doFetchData_Commande(ProcedureVenteBean searchBean) throws Throwable {
		
		try {
            BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
            
            
            CommandeclientBean beandemBean =   searchBean.getCommande();
            
            
            
            if (bs.getFct_id().equals(Fn_Servir) ||  bs.getFct_id().equals(Fn_Contremander)  ){
            	beandemBean.setCondition_select_mode("  " +
            			"   AND  bean.modeBean.fct_id  in ('"+Fn_Confirmer+"' ,'"+Fn_Créer+"','"+Fn_Nouveau+"','"+Fn_Modifier+"') " +
            	"           AND   bean.cmd_date <='"+BDateTime.getDateActuel_system()+"'   ");
			}
            
            
            
            
			
			if (bs.getFct_id().equals(Fn_appliquer)){
				beandemBean.setCondition_select_mode("  AND  bean.modeBean.fct_id  in ('16')   ");
			} 
			 
			setObjectValueModel(NAME_LIST_G,LIST_DATA_DEM);
			List<CommandeclientBean> listdemande=serviceCommandeclient.doFetchDatafromServer(beandemBean);
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
			bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_vente"));
			setObjectValueModel(LIST_VIEW_G, LIST_VIEW_SERVIR);
			setObjectValueModel(NAME_LIST_G ,LIST_DATA_DEM);
			setObjectValueModel(NAME_GRID_G, NAME_GRID_DEM);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_SERVIR");
			setObjectValueModel(MAP_FIELD_BEAN, CommandeclientTemplate.MapfieldBean);
			 
		} catch (Exception e) {
			displayException(e);
		}
		
		return getViewFilterAjax_Servir(FILTER_VIEW_CMD);
	}


	public ModelAndView doAddData(ProcedureVenteBean detailBean, FournitureVenteBean    fVenteBean , ServiceBean    service ) throws Throwable {
	     try {
				 
				setObjectValueModel("detailFrnBean", fVenteBean);
				setObjectValueModel("detailSrvBean", service);
	            serviceProcedureVente.doCreateRowData(detailBean,fVenteBean,service);
	            throwNewException("ins01");
	          } catch (Exception e) {
	        	  displayException(e);
	        	  serviceProcedureVente.doRetourModeOrigin("ProcedureVenteBean", Fn_Créer, detailBean.getVente_id());
	        	  if(e.getMessage().equals("ins01")) 
	        	    TransfertError(e);
	        	  
	          }
	        return getViewAdd_Commit(FORM_VIEW_CREER);
		}
	
	
	public ModelAndView doCommitData(   ProcedureVenteBean beanUpfBean, FournitureVenteBean    fVenteBean , ServiceBean    service) throws Exception {	 
		
		 ProcedureVenteBean  detailBean=	(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
	 	try {
	    ServiceBean  srvBean=	(ServiceBean) getObjectValueModel("detailSrvBean");
	    FournitureVenteBean  frnBean=	(FournitureVenteBean) getObjectValueModel("detailFrnBean");
	    serviceProcedureVente.doConfirmRowData(detailBean,frnBean,srvBean); 
//	    ProcedureVenteBean  devBean = (ProcedureVenteBean) getObjectValueModel("beanInito");
//		setObjectValueModel(FORM_BEAN, devBean);
//		List  <DetProcedureVenteBean>listGridEditable_VENTE=  new  ArrayList<DetProcedureVenteBean>();
//		setObjectValueModel(LIST_EDITABLE_VENTE, listGridEditable_VENTE);
//		setObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE  , new  ArrayList<DetFournitureVenteBean>());
//		setObjectValueModel(LIST_EDITABLE_PRESTATION  , new  ArrayList<DetServiceBean>());
	    throwNewException("Confirmation effectuée avec succès");
	 	} catch (Exception e) {
		 	displayException(e);
		 	if(e.getMessage().equals("Confirmation effectuée avec succès")) {
	           TransfertError(e);
		 	}else {
		 		 serviceProcedureVente.doRetourModeOrigin("ProcedureVenteBean", Fn_Créer, detailBean.getVente_id());
		 	}
	   }
	 	
	return getViewAfterAdd(FORM_VIEW_CREER);
		}
	
	
	public ModelAndView doFacturerData(ProcedureVenteBean beanUpfBean) throws Exception {	 
		
		  ProcedureVenteBean  detailBean=	(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
	 	try {
	    BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
	    serviceProcedureVente.doCharger_la_facture(detailBean); 
	    ProcedureVenteBean  devBean = (ProcedureVenteBean) getObjectValueModel("beanInito");
		setObjectValueModel(FORM_BEAN, devBean);
		List  <DetProcedureVenteBean>listGridEditable_VENTE=  new  ArrayList<DetProcedureVenteBean>();
		setObjectValueModel(LIST_EDITABLE_VENTE, listGridEditable_VENTE);
		setObjectValueModel(LIST_EDITABLE_PRESTATION  , new  ArrayList<DetServiceBean>());
		setObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE  , new  ArrayList<DetFournitureVenteBean>());
	    throwNewException("Facturation effectuée avec succès");
	 	} catch (Exception e) {
	 	displayException(e);
	 	 if(e.getMessage().equals("Facturation effectuée avec succès")) {
            TransfertError(e);
	 	  }else {
	 		 serviceProcedureVente.doRetourModeOrigin("ProcedureVenteBean", Fn_Créer, detailBean.getVente_id());
	 	  }
	 }
	return getViewAfterAdd(FORM_VIEW_CREER);
		}
	
	
	public ModelAndView doPrintFactureSPL() throws Exception {
		PrintPdfModelSPL print = new PrintPdfModelSPL();
		try {
			List   lisData=  (List) getObjectValueModel("detailFcatureImprimer") ;
			List <TVABean> list_des_tva=  (List) getObjectValueModel(LIST_DES_TVA);
			Facture_clientBean    factureImprimer= (Facture_clientBean) getObjectValueModel("factureImprimer") ;
			setObjectValueModel(Facture_clientTemplate.LIST_DATA_DET_FACT, lisData) ;
			setObjectValueModel(Facture_clientTemplate.LIST_DES_TVA, list_des_tva) ;
			setObjectValueModel(FORM_BEAN,factureImprimer ) ;
			setObjectValueModel(Facture_clientTemplate.BEAN_TOTAL_FACTURE_CLIENT, factureImprimer);
			Facture_clientActionManager fManager = new Facture_clientActionManager();
			fManager.doTraitmentTotal(factureImprimer);
			print.doPrintPDF_detaille();
			
			setObjectValueModel("detailFcatureImprimer", new ArrayList<>()) ;
			setObjectValueModel(LIST_DES_TVA , new ArrayList<>() );
			setObjectValueModel("factureImprimer" , new Facture_clientBean()) ;
			setObjectValueModel(Facture_clientTemplate.LIST_DATA_DET_FACT, new ArrayList<>()) ;
			setObjectValueModel(Facture_clientTemplate.LIST_DES_TVA, new ArrayList<>()) ;
			setObjectValueModel(FORM_BEAN,new ProcedureVenteBean()) ;
			setObjectValueModel(Facture_clientTemplate.BEAN_TOTAL_FACTURE_CLIENT, new Facture_clientBean());
			
		} catch (Exception e) {
			displayException(e);
		}
		return null;
	}
	 
	
	
	
	
	public ModelAndView doServirData( ProcedureVenteBean detailBean, FournitureVenteBean    fVenteBean , ServiceBean    service) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceProcedureVente.doCreateRowData(detailBean,      fVenteBean ,      service);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd_Commit(FORM_VIEW_SERVIR);
		}
	
	
	public ModelAndView doUpdateData(ProcedureVenteBean beanUpBean, FournitureVenteBean    fVenteBean , ServiceBean    service) {	 
		 	try {
		        serviceProcedureVente.doUpdateRowData(beanUpBean); 
				update_row_from_list(LIST_DATA, beanUpBean); 
		        throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	
	public ModelAndView doCorrigerData(ProcedureVenteBean beanUpBean, FournitureVenteBean    fVenteBean , ServiceBean    service) {	 
	 	try {
	        serviceProcedureVente.doCorrigerProcedureVente(beanUpBean,fVenteBean,service); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	        throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
	
	
	public ModelAndView doConfirmData(ProcedureVenteBean beanUpBean, FournitureVenteBean    fVenteBean , ServiceBean    service) throws Exception {	
		 ProcedureVenteBean beanUpdate=(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
		 	try {
		 		
		 serviceProcedureVente.doConfirmRowData(beanUpBean,fVenteBean,service); 
//		 remove_row_from_list(LIST_DATA); 
//		 removeObjectModel(FORM_BEAN);
		 throwNewException("Confirmation effectuée avec succès");
	 	} catch (Exception e) {
		 	displayException(e);
		 	if(e.getMessage().equals("Confirmation effectuée avec succès")) {
	        TransfertError(e);
	 	      }else {
	 		 serviceProcedureVente.doRetourModeOrigin("ProcedureVenteBean", Fn_Créer, beanUpdate.getVente_id());
	 	    }
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}

	
	public ModelAndView doDeleteData(ProcedureVenteBean beanDelBean) {
	    try {
	    	ProcedureVenteBean beanDean =(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
	        serviceProcedureVente.doDeleteRowData(beanDean);
			remove_row_from_list(LIST_DATA);
			removeObjectModel(FORM_BEAN);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
	
	
	
	
	
	public    ModelAndView doSelectDetailleRow () throws Exception {

		
		
		String elmentSansTArif=""; 
		String elmentSansTArif_modifier=""; 
		try {
			 String  demandeId="";
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 removeObjectModel(FORM_BEAN); //liste_detaille_vente
			 removeObjectModel(LIST_SERIE_PROVENTE); //liste_detaille_vente
			 removeObjectModel(ARTICLO); //liste_detaille_vente
			 HashMap   maptRai   =(HashMap) getObjectValueModel("mapokil");
			 if(maptRai!= null){
				  Set  sss=maptRai.keySet();
				  for (Iterator ite = sss.iterator(); ite.hasNext();) {
					 String object = (String) ite.next();
					 removeObjectModel(object); 
			       }
			 }
			 List<DetProcedureVenteBean>  List_detaille_vente =  new ArrayList<DetProcedureVenteBean>();
			
			if (bs.getFct_id().equals(Fn_Servir) || bs.getFct_id().equals(Fn_Contremander)  ){
				
				 elmentSansTArif="";
				 setObjectValueModel(MAP_FIELD_BEAN, CommandeclientTemplate.MapfieldBean);
				 CommandeclientBean commande = (CommandeclientBean) getIndexFromDataGrid_v1(LIST_DATA_DEM);
				 Double getTaux_remise_alacaisse = commande.getTaux_remise_alacaisse()==null?new Double(0):commande.getTaux_remise_alacaisse();
				 Double getCommande_remise = commande.getCommande_remise()==null?new Double(0):commande.getCommande_remise();

				 ProcedureVenteBean infoVente= new ProcedureVenteBean();
				 infoVente.setCommande(commande);
				 infoVente.setVente_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
				 infoVente.setClient(commande.getClient());
				 infoVente.setVente_libelle(commande.getCmd_libelle());
				 infoVente.setDepot(commande.getDepot());
				 infoVente.setTaux_remise_alacaisse(getTaux_remise_alacaisse);
				 infoVente.setVente_remise(getCommande_remise);
				 chargerInformationForTarif(infoVente);
				 setObjectValueModel(FORM_BEAN,infoVente);
				 List<DetCmdCltBean>  liste_detaille= serviceCommandeclient.doFetchDetailleDatafromServer(commande) ;   
				
				  
				 for (int i = 0; i < liste_detaille.size(); i++) {
					 DetCmdCltBean ligne_commande= liste_detaille.get(i);
					 
					 DetProcedureVenteBean beanLigne = new DetProcedureVenteBean();
					 
					 beanLigne.getPk().setFkcode_barre(ligne_commande.getPk().getFkcode_barre());
					 beanLigne.setQuantite_demander(ligne_commande.getQuantite()); 
					 beanLigne.setQuantite(ligne_commande.getQuantite());
					 TarificationBean ss=ligne_commande.getTarif();
					 if(ss==null)  throwNewException("Manque Tarification pour cette article");
				     
						if(ss!=null){
						beanLigne.setTarif(ss);
						
						/*****************************************le cout ********************************************/
			    		Double getQuantiteX       = ligne_commande.getQuantite()==null?new Double(0):ligne_commande.getQuantite();
			    		Double                 qte=ProcessFormatNbr.FormatDouble_Troischiffre(getQuantiteX);
			    		Double  cout              = ss.getCout()==null?new Double(0):ss.getCout().getTarif_unit_article();
			    		Double	Prixcout          = cout==null?new Double(0):cout;
			    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
			    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
			    		
			    		/*****************************************  setInfo  ********************************************/
			    		
			    		String libelle_desi=beanLigne.getPk().getFkcode_barre().getDesignation_libelle();
			    		String groupe=beanLigne.getTarif().getGroupe().getType_trf_lib();
						String lot=beanLigne.getTarif().getTarif_lot()!=null && beanLigne.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
						String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
						beanLigne.setInfo(libelle_desi+natureprix);
			    		Double priUnitvente=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
			    		  
			    		
			    		
			    		/*****************************************Prix Unit Brute reel********************************************/
			    		
			    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
			    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute);
			    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
			    		 
			    		/*******************************************Remise********************************************************/
			    		
			    		Double taux_remiseligne     = ss.getTaux_remise()==null?new Double(0):ss.getTaux_remise();
						Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
						                    tot_taux=ProcessFormatNbr.FormatDouble_Troischiffre(tot_taux);
						beanLigne.setTaux_remise_ligne(tot_taux);
						Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_vente_reel(), tot_taux);
						beanLigne.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_Troischiffre(montant_Remise_Ligne));

		    		    /*******************************************montant_ht_vente *********************************************/   
		    		    
		    		    Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, montant_Remise_Ligne);
		    		    montant_ht_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente);
		    		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente));
			    		
			    		/*********************************************montant_tva_vente ******************************************/
			    		 
			    	 
			    		Double montant_tva_vente      = ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente,ss.getTvaBean(),new DeviseBean());
			    		montant_tva_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_vente);
			    		beanLigne.setMontant_tva_vente(montant_tva_vente);
			    		
			    		/*********************************************montant_ttc_vente *******************************************/
			    		
			    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
			    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
			    		
			    		/*********************************************montant_benefice *******************************************/
			    		if(le_cout.doubleValue()>0){
			    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
			    		montant_benefice          =ProcessFormatNbr.FormatDouble_Troischiffre(montant_benefice);
			    		beanLigne.setMontant_benefice(montant_benefice);
			    		}
			    		/**********************************************************************************************************/
						
						
						List_detaille_vente.add(beanLigne);
						    	 
						 }else{
						    elmentSansTArif=elmentSansTArif+""+ligne_commande.getPk().getFkcode_barre().getPk().getCode_barre()+" / "+
						    ligne_commande.getPk().getFkcode_barre().getDesignation_libelle()+" <br> ";
						  }
				   }
				 
				 	if(!StringUtils.isEmpty(elmentSansTArif)){
					     throwNewException(" Manque Tafification pour ce(s) articles :<bR> "+elmentSansTArif);
				     }
				 
				 
			}else{
				
				 ProcedureVenteBean  rowBeans = (ProcedureVenteBean) getIndexFromDataGrid_v1(LIST_DATA);
				 if(rowBeans.getCommande()!=null){
					 demandeId=rowBeans.getCommande().getCmd_id();
				 } 
				 setObjectValueModel(FORM_BEAN, rowBeans);
				 List_detaille_vente  =  serviceProcedureVente.doFetch_detDatafromServer(rowBeans);
				 for (DetProcedureVenteBean  dBean : List_detaille_vente) {
					 dBean.setPrix_vente_origin(dBean.getTarif().getTarif_unit_vente());
				}
				 FournitureVenteBean fourBean= new FournitureVenteBean();
				 fourBean.setVenteFrn(rowBeans);
				 List <DetFournitureVenteBean>listDetFournitureVente  =  serviceFournitureVente.doFetchDetailFourniturefromServer(fourBean);
				 setObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE        ,  listDetFournitureVente);
				 setObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE_CLONE   ,  ProcessUtil.cloneList(listDetFournitureVente) );

				 List <DetServiceBean>listDetServiceBean =  serviceService.doFindDetailListServiceByVenteId(rowBeans);
				 setObjectValueModel(LIST_EDITABLE_PRESTATION       ,  listDetServiceBean);
				 setObjectValueModel(LIST_EDITABLE_PRESTATION_CLONE,  ProcessUtil.cloneList(listDetServiceBean) );
			}
				 
				 
				 
			
			setObjectValueModel(LIST_EDITABLE_VENTE         ,  List_detaille_vente);
			setObjectValueModel(LIST_EDITABLE_VENTE_CLONE   ,  ProcessUtil.cloneList(List_detaille_vente) );
			
			if (bs.getFct_id().equals(Fn_Consulter)   )
				return getViewConsult_Pdf_ex(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Consulter) && !demandeId.equals("") )
				return getViewConsult_Pdf_ex(FORM_VIEW);
			
			
			if ( bs.getFct_id().equals(Fn_Modifier) && demandeId.equals("")   )
				return getViewUpdate(FORM_VIEW_CREER);
			
			
			  if ( bs.getFct_id().equals(Fn_Corriger)  )
					return getViewCorriger(FORM_VIEW_CREER);
			
			if ( bs.getFct_id().equals(Fn_Modifier) && !demandeId.equals("")   )
				return getViewModif_Srv(FORM_VIEW_SERVIR);
			
			
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
			
			
			
			if (bs.getFct_id().equals(Fn_Conserver))
				return getViewValider(FORM_VIEW);
			
			
			if (bs.getFct_id().equals(Fn_Servir)){
				bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_vente"));
				return getViewServir_commande(FORM_VIEW_SERVIR);
			}
			
			
			if (bs.getFct_id().equals(Fn_Contremander)){
				bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_vente"));
				return getViewContremander_commande(FORM_VIEW_SERVIR);
			}
			
			
			if (bs.getFct_id().equals(Fn_appliquer)){
				bs.setSousmod_libelle_title((String) getObjectValueModel("srv_dem_vente"));
				return getViewUpdate(FORM_VIEW_CREER);
			}
			
			
			if (bs.getFct_id().equals(Fn_Confirmer)   )
				return getViewConfirm(FORM_VIEW);
			 
			if (bs.getFct_id().equals(Fn_Supprimer))
				return getViewDelete(FORM_VIEW);
			
		}catch (Exception e) {
			displayException(e);
		}
		
		if(StringUtils.isEmpty(elmentSansTArif)){
			
		return getViewFilterAjax(FILTER_VIEW);
		
		}else{
			setObjectValueModel("PDF_IS_CMD", "OUI");
			
			doLoadingLibelleOtherSModule("57");
			
			setObjectValueModel(MAP_FIELD_BEAN,CommandeclientTemplate.MapfieldBean);
			setObjectValueModel("LIST_VIEW", LIST_VIEW_SERVIR);
			setObjectValueModel(NAME_LIST_G ,LIST_DATA_DEM);
			setObjectValueModel("nameGrid", NAME_GRID_DEM);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_SERVIR");
			return getViewFilterAjax_Servir(FILTER_VIEW_CMD);
	 
		}

	}
	
	 
	
	public    ModelAndView doPrintPDF_detaille() throws Exception   {
		 
		List   lisData=  (List) getObjectValueModel(LIST_EDITABLE_VENTE) ;
		List   list_detailleFourniture =  (List<DetFournitureVenteBean>) getObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE);   
		List   list_detailleService    =  (List<DetServiceBean>) getObjectValueModel(LIST_EDITABLE_PRESTATION);   
		 
		ProcedureVenteBean      denBean= (ProcedureVenteBean) getObjectValueModel(FORM_BEAN) ;
		HashMap  mapclient  =  (HashMap)getObjectValueModel( ProcedureVenteTemplate.MAP_CLIENT_BEN);
		ClientBean  ben     =  (ClientBean) mapclient.get(denBean.getClient().getClt_id());
		denBean.setClient(ben);
		File file = new File(getRequest().getRealPath("/")+"/temp/"+LIST_EDITABLE_VENTE+getRequest().getSession().getId()+".pdf");
	    FileOutputStream fs = new FileOutputStream(file);
	    GeneratePdf  genpdf= new GeneratePdf();

	    String [][] mapfieldBean_detaille  = 
	    		new String[][]{
	    		          { "pk.fkcode_barre.pk.code_barre", "30" }, 
	    		          { "pk.fkcode_barre.designation_libelle", "100" },
	    		          { "quantite", "20" },
	    		          { "tarif.tarif_unit_vente", "30" },
	    		          { "taux_remise_ligne", "20" },
	    		          { "montant_ht_vente", "50" }
	    		          };    
	    		          
    
						   
	     String [][] mapfieldBeanFourniture  = 
	        		new String[][]{
	        		          { "fkcode_barre.pk.code_barre", "30" }, 
	        		          { "fkcode_barre.designation_libelle", "100" },
	        		          { "quantite", "20" },
	        		          { "tarifVente.tarif_unit_vente", "30" },
	        		          { "taux_remise_ligne", "20" },
	        		          { "montant_ht_vente", "50" }
	      };
        
	     String [][] mapfieldBeanPrestation  = 
	        		new String[][]{
	        		          { "fkcode_barre.pk.code_barre", "30" }, 
	        		          { "fkcode_barre.designation_libelle", "100" },
	        		          { "quantite", "20" },
	        		          { "tarifVente.tarif_unit_vente", "30" },
	        		          { "taux_remise_ligne", "20" },
	        		          { "montant_ht_vente", "50" }
	          };
		try {
			Document document=GeneratePdf.doGenerateDocumentFormat();
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	        genpdf.doWriteHeaderDocument_PDF(document,fs,bSession);
	        
	        doWriteEntete(document,denBean);
	        doWrite_Header_ContentTable(document,96,mapfieldBean_detaille);
	        PdfPTable table = new PdfPTable(mapfieldBean_detaille.length);
	        
	        int listsDatasize=0;
	        if(lisData!=null  &&  lisData.size()>0) {
	        	listsDatasize=listsDatasize+lisData.size();
	        	doWrite_Data_Table (table,lisData,document,96,mapfieldBean_detaille);
	        }   
	        if(list_detailleFourniture!=null  &&  list_detailleFourniture.size()>0) {
	         	listsDatasize=listsDatasize+list_detailleFourniture.size();
	            doWrite_Data_Table (table,list_detailleFourniture,document,96,mapfieldBeanFourniture);
	        }   
	        if(list_detailleService!=null  &&  list_detailleService.size()>0) {
	        	listsDatasize=listsDatasize+list_detailleService.size();
	        	doWrite_Data_Table (table,list_detailleService,document,96,mapfieldBeanPrestation);
	        }
	        
	        if(listsDatasize>0) {
	          doWriteRowVideApresData(table, listsDatasize, document, mapfieldBean_detaille);
	          doWrite_Tva_Total_Piece(lisData,document);  
	        }
	        document.close();
	        ProcedureVenteBean  devBean = (ProcedureVenteBean) getObjectValueModel("beanInito");
			setObjectValueModel(FORM_BEAN, devBean);
			List  <DetProcedureVenteBean>listGridEditable_VENTE=  new  ArrayList<DetProcedureVenteBean>();
			setObjectValueModel(LIST_EDITABLE_VENTE, listGridEditable_VENTE);
			setObjectValueModel(LIST_EDITABLE_PRESTATION  , new  ArrayList<DetServiceBean>());
			setObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE  , new  ArrayList<DetFournitureVenteBean>());
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write(LIST_EDITABLE_VENTE+getRequest().getSession().getId()+".pdf");
		} catch (Exception e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

     }
	public   void doWriteRowVideApresData(PdfPTable table ,int listsDatasize, Document document ,String[][] mapFieldBean) throws Exception, SecurityException {
		
		  /********************************************************************************************************/
        int sizelist=listsDatasize;
        int toolha=sizelist*20;
        int resul=380 - toolha;
        float toul_contenu_tab=Float.valueOf(String.valueOf(resul));
        /********************************************************************************************************/
         
        for(int j = 0; j < mapFieldBean.length; j++){
		            PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.Normal_9_times_roman));
			        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        cell.setPaddingBottom(5);
			        cell.setFixedHeight(toul_contenu_tab);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        cell.setBorder(cell.LEFT+cell.RIGHT);
		            table.addCell(cell);
		    }   
		  document.add(table);
		
	}
	  public   void doWrite_Data_Table(PdfPTable table,List lisData, Document document,int poucentage,String[][] mapFieldBean) throws Exception, SecurityException {
		  
		  
		  
	        int[] columnWidths = new int[mapFieldBean.length] ;
	        for(int i = 0; i < mapFieldBean.length; i++){
	      	columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
			}
	        table.setWidthPercentage(poucentage);
	        table.setWidths(columnWidths);
		  
			  for(int i=0; i < lisData.size(); i++ ){
				   Object bean = (Object) lisData.get(i);
				   
				  if(ProcessUtil.doesObjectContainField(bean, "isVente")) {
					  Object obj=	 GenericWeb.getValueOject_with_name_field(bean, "isVente");
					  Boolean   isVenteData = (Boolean) obj;
					  if(!isVenteData) continue;
				  }
				    
				 for(int j = 0; j < mapFieldBean.length; j++){
					 
					        PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.Normal_9_times_roman));
					 
					        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
					        
					        if(j==mapFieldBean.length-3 ||  j==mapFieldBean.length-1 ){
					        	Double elm=Double.valueOf(String.valueOf(obj));
					        	cell = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(elm) ,GeneratePdf.Normal_9_times_roman));
						        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						        cell.setPaddingBottom(5);
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
						        
					        }else{
					        	 
					        
					         cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.Normal_9_times_roman));
					       	 if(j==mapFieldBean.length-2)
					       		cell = new PdfPCell(new Phrase(ProcessFormatNbr.addPourcentage(String.valueOf(obj)) ,GeneratePdf.Normal_9_times_roman));
				        		 
						        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						        cell.setPaddingBottom(5);
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        }
					        table.addCell(cell);
					    }   
				   
	           }
			  
			 
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
    	     
    	      
    	     
    	     
    	     
    	    PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":titrehead  ,GeneratePdf.Bold_9_times_roman));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setPaddingBottom(PaddingBottom);
		    cell.setBackgroundColor(GeneratePdf.colorHeader);
		    cell.setBorderWidth(1f);
		    table.addCell(cell);
		}
      document.add(table);
}
	
	
	
	public void doWriteEntete(Document document, ProcedureVenteBean   denBean) throws Exception {
	PdfPTable tableTopHeader = new PdfPTable(100);
	tableTopHeader.setWidthPercentage(96);
	 
	    
	PdfPCell cell = new PdfPCell(new Phrase("Bon Livraison N°",GeneratePdf.Bold_9_times_roman));
    cell.setColspan(24);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(" : "+denBean.getVente_id(),GeneratePdf.Normal_9_times_roman));
    cell.setColspan(23);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase(" Client ",GeneratePdf.Bold_9_times_roman));
    cell.setColspan(13);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.LEFT);
    tableTopHeader.addCell(cell);
    
    String getClient=denBean.getVente_libelle().equals("")?denBean.getClient().getClt_lib():denBean.getVente_libelle();
    cell = new PdfPCell(new Phrase(" : "+getClient ,GeneratePdf.Normal_9_times_roman));
    cell.setColspan(40);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    
     
    
    
    cell = new PdfPCell(new Phrase("Date",GeneratePdf.Bold_9_times_roman));
    cell.setColspan(24);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(denBean.getVente_date()) ,GeneratePdf.Normal_9_times_roman));
    cell.setColspan(23);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase(" Adresse ",GeneratePdf.Bold_9_times_roman));
    cell.setColspan(13);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_adr() ,GeneratePdf.Normal_9_times_roman));
    cell.setColspan(40);
    cell.setFixedHeight(40f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase("   ",GeneratePdf.Bold_9_times_roman));
    cell.setColspan(47);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
     
    
    
    
    cell = new PdfPCell(new Phrase(" Matricule fiscal :",GeneratePdf.Bold_9_times_roman));
    cell.setColspan(18);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT+cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( "   "+denBean.getClient().getClt_obs() ,GeneratePdf.Normal_9_times_roman));
    cell.setColspan(35);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT +cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    
    
    
    
    
    
    cell = new PdfPCell(new Phrase("",GeneratePdf.Normal_9_times_roman));
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
           PdfPCell cell = new PdfPCell(new Phrase( "" ,GeneratePdf.Normal_9_times_roman));
           cell.setColspan(100);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Taux",GeneratePdf.Bold_9_times_roman));
           cell.setColspan(10);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Base",GeneratePdf.Bold_9_times_roman));
           cell.setColspan(21);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Montant",GeneratePdf.Bold_9_times_roman));
           cell.setColspan(23);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.Normal_9_times_roman));
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
			
			   cell = new PdfPCell(new Phrase(key,GeneratePdf.Normal_9_times_roman));
	           cell.setColspan(10);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[0],GeneratePdf.Normal_9_times_roman));
	           cell.setColspan(21);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[1],GeneratePdf.Normal_9_times_roman));
	           cell.setColspan(23);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.Normal_9_times_roman));
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
        	    
        	    cell = new PdfPCell(new Phrase("",GeneratePdf.Bold_9_times_roman));
                cell.setColspan(55);
                cell.setFixedHeight(20f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.NO_BORDER);
                table_total.addCell(cell);
                
                
                cell = new PdfPCell(new Phrase(titre,GeneratePdf.Bold_9_times_roman));
                cell.setColspan(18);
                cell.setFixedHeight(20f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
                table_total.addCell(cell);
                
                cell = new PdfPCell(new Phrase(value ,GeneratePdf.Normal_9_times_roman));
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

	 
	
	 
	
	@SuppressWarnings("unchecked")
	public    ModelAndView doExportXls_detaille() {
		try {

			    List        lisData       =  (List)getObjectValueModel(LIST_EDITABLE_VENTE) ;
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
			    
			     
			    ProcedureVenteBean rBeanS=(ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
		        
			    setObjectValueModel("titleHead","Vente N° "+rBeanS.getVente_id()) ;
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
	
	public    ModelAndView doPrintPDF_Action()   throws Exception { 
		 
		try {
			  BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION); 
		List   lisData                         =  new ArrayList();  
		String [][]    mapFieldBean            =  new String [1][1] ; 
		 String    title ="";
	       
		String PDF_IS_CMD= (String) getObjectValueModel("PDF_IS_CMD");
		 if(PDF_IS_CMD.equals("OUI")){
			  lisData                         =  (List) getObjectValueModel(LIST_DATA_DEM) ;
			  mapFieldBean            =  ProcedureVenteTemplate.MapfieldBean;  
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
		 
		   
		        Document document=GeneratePdf.doGenerateDocumentFormat();
		        PdfPTable table = new PdfPTable(mapFieldBean.length);
		        
		       
		        
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
					  mapFieldBean            =  ProcedureVenteTemplate.MapfieldBean;  
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
	
	 
	public ModelAndView doCalculerTotalFourniture() throws Exception {
		
		try {
			 List <DetFournitureVenteBean> detFournitureList   = (List) getObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE);
			 Double totMntTTC=new Double(0);
			 Double totMntHT=new Double(0);
			 for (int i = 0; i < detFournitureList.size(); i++) {
					DetFournitureVenteBean  beanLigne= (DetFournitureVenteBean) detFournitureList.get(i);
					totMntHT=ProcessNumber.addition(totMntHT, beanLigne.getMontant_ht_vente() );
			    	Double  montant_ttc_vente=ProcessNumber.addition(beanLigne.getMontant_ht_vente(), beanLigne.getMontant_tva_vente());
			        beanLigne.setMontant_ttc_vente(montant_ttc_vente);
			    	totMntTTC=ProcessNumber.addition(totMntTTC, montant_ttc_vente );
			     	 
			}
			JsonObject data = new JsonObject();
			data.addProperty("totMntTTC",  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totMntTTC) );
			data.addProperty("totMntHT",   ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totMntHT) );
		    getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(data.toString());
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculerTotalGrid( ProcedureVenteBean detailBean ) throws Exception {
		
		try {
			Double totGrid= (Double) getObjectValueModel("totGridVente");
			getResponse().getWriter().print(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totGrid));
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculerTotal(ProcedureVenteBean detailBean ) throws Exception {
		
		try {
			ProcedureVenteBean   rowBean  = (ProcedureVenteBean) getObjectValueModel(FORM_BEAN);
			List <DetProcedureVenteBean > List_detaille  = new ArrayList<DetProcedureVenteBean>();
			List <DetFournitureVenteBean > List_detailleFourniture = new ArrayList<DetFournitureVenteBean>();
			List <DetServiceBean >  List_detailleService     = new ArrayList<DetServiceBean>();
			
			BeanSession bs                =(BeanSession)getObjectValueModel(BEAN_SESSION);
			Double remise_ala_caisse    			  =  new Double(0);
			Double totalre_mise          			  =  new Double(0);
			Double remise_ligne_pr_la_caisse          =  new Double(0);
			String pattern ="0.000";
			Double avance                   = new Double(0);
			Double getTaux_remise_alacaisse = detailBean.getTaux_remise_alacaisse()==null?new Double(0):detailBean.getTaux_remise_alacaisse();
			Double getAvance_montant_vente  = detailBean.getAvance_montant_vente()==null?new Double(0):detailBean.getAvance_montant_vente();
			Double getMontant_vente_recu    = detailBean.getMontant_vente_recu()==null?new Double(0):detailBean.getMontant_vente_recu();
			
			if(bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Servir)  
					|| bs.getFct_id().equals(Fn_Modifier)  ||  bs.getFct_id().equals(Fn_Facturer)   ||  bs.getFct_id().equals(Fn_Corriger)   ){
				
				 List_detaille=(List<DetProcedureVenteBean>) getObjectValueModel(LIST_EDITABLE_VENTE);
				 List_detailleFourniture =  (List<DetFournitureVenteBean>) getObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE);   
				 List_detailleService    =  (List<DetServiceBean>) getObjectValueModel(LIST_EDITABLE_PRESTATION);   
					
					
				 if( detailBean.getDevise().getDev_id().intValue()==191  ||   detailBean.getDevise().getDev_id().intValue()==192   ){
						pattern ="0.00";
				 } 
				 avance                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(getAvance_montant_vente,pattern);
				 setObjectValueModel(FORM_BEAN,detailBean);
			}else{
				
				if( rowBean.getDevise().getDev_id().intValue()==191  ||  rowBean.getDevise().getDev_id().intValue()==192   ){
					pattern ="0.00";
				} 
				List_detaille = serviceProcedureVente.doFetch_detDatafromServer(rowBean);
				List_detailleFourniture =  (List<DetFournitureVenteBean>) getObjectValueModel(LIST_EDITABLE_FOURNITURE_VENTE);   
				List_detailleService    =  (List<DetServiceBean>) getObjectValueModel(LIST_EDITABLE_PRESTATION);   
				setObjectValueModel(LIST_EDITABLE_VENTE, List_detaille);
				getTaux_remise_alacaisse = rowBean.getTaux_remise_alacaisse()==null?new Double(0):rowBean.getTaux_remise_alacaisse();
				getAvance_montant_vente  = rowBean.getAvance_montant_vente()==null?new Double(0):rowBean.getAvance_montant_vente();
				avance                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(getAvance_montant_vente,pattern);
				getMontant_vente_recu    = rowBean.getMontant_vente_recu()==null?new Double(0):rowBean.getMontant_vente_recu();
				
			}
			
			       
			 Double tot_ht_brute =new Double(0);
			 Double tot_ht_net    =new Double(0);
			 Double tot_tva=new Double(0);
			 Double tot_qte=new Double(0);
			 Double marge_benefice_vente=new Double(0);
			 HashMap  map_des_Tva = new HashMap();
			 HashMap  map_des_Tvafrn = new HashMap();
			 HashMap  map_des_TvaServ = new HashMap();
			
			 calculTotalforArticleMachandise(List_detaille,pattern,tot_ht_brute,tot_ht_net,tot_tva,tot_qte,marge_benefice_vente,map_des_Tva,totalre_mise,
						  getTaux_remise_alacaisse );
			 
			 calculTotalforFourntire(List_detailleFourniture,pattern,tot_ht_brute,tot_ht_net,tot_tva,tot_qte,marge_benefice_vente,map_des_Tvafrn,totalre_mise,
					  getTaux_remise_alacaisse );
			 
			 
			 calculTotalforService(List_detailleService,pattern,tot_ht_brute,tot_ht_net,tot_tva,tot_qte,marge_benefice_vente,map_des_TvaServ,totalre_mise,
					  getTaux_remise_alacaisse );
			 
			
			 ProcedureVenteBean    beanTotal = new ProcedureVenteBean();
			 beanTotal.setAvance_montant_vente(avance)  ; 
			 beanTotal.setMarge_benefice_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(marge_benefice_vente,pattern));
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
				 
				 
				 Double    total_leTva  = new Double(0);
				 List <TVABean> list_des_tva=  (List) getObjectValueModel(LIST_DES_TVA);
				 Double le_Ht_Net  = new Double(0);
				 Double le_Ht_Reel  = new Double(0);
				 for (int j = 0; j < list_des_tva.size(); j++) {
					 TVABean beanTva=list_des_tva.get(j);
					 
					 if(map_des_Tva.get(beanTva.getTva_libelle())!=null ||  map_des_Tvafrn.get(beanTva.getTva_libelle())!=null   || map_des_TvaServ.get(beanTva.getTva_libelle())!=null   ){
						 List listTva  =(List) map_des_Tva.get(beanTva.getTva_libelle());
						 List listTvaFrn  =(List) map_des_Tvafrn.get(beanTva.getTva_libelle());
						 List listTvaSrv  =(List) map_des_TvaServ.get(beanTva.getTva_libelle());
						 
						 String  tva   = beanTva.getTva_libelle();
						 Double le_Ht_netLigne  = new Double(0);
					     Double leTva  = new Double(0);
					     
					     if(listTva!=null)
					 	 for (int i = 0; i < listTva.size(); i++) {
					 		DetProcedureVenteBean  bean=(DetProcedureVenteBean) listTva.get(i);
					 		le_Ht_netLigne=ProcessNumber.addition(le_Ht_netLigne, bean.getMontant_ht_vente());
							le_Ht_Net=ProcessNumber.addition(le_Ht_Net, bean.getMontant_ht_vente());
							le_Ht_Reel=ProcessNumber.addition(le_Ht_Reel, bean.getMontant_ht_vente_reel());
							leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_vente());
						 }
					     
					     if(listTvaFrn!=null)
						 	 for (int i = 0; i < listTvaFrn.size(); i++) {
						 		DetFournitureVenteBean bean=(DetFournitureVenteBean) listTvaFrn.get(i);
						 		le_Ht_netLigne=ProcessNumber.addition(le_Ht_netLigne, bean.getMontant_ht_vente());
								le_Ht_Net=ProcessNumber.addition(le_Ht_Net, bean.getMontant_ht_vente());
								le_Ht_Reel=ProcessNumber.addition(le_Ht_Reel, bean.getMontant_ht_vente_reel());
								leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_vente());
						 }
					     if(listTvaSrv!=null)
						 	 for (int i = 0; i < listTvaSrv.size(); i++) {
						 		DetServiceBean bean=(DetServiceBean) listTvaSrv.get(i);
						 		le_Ht_netLigne=ProcessNumber.addition(le_Ht_netLigne, bean.getMontant_ht_vente());
								le_Ht_Net=ProcessNumber.addition(le_Ht_Net, bean.getMontant_ht_vente());
								le_Ht_Reel=ProcessNumber.addition(le_Ht_Reel, bean.getMontant_ht_vente_reel());
								leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_vente());
						 }
					 	 
					 	 
					 	 
					 	 total_leTva=  ProcessNumber.addition(total_leTva, leTva);
						 
						 JSONObject  element = new JSONObject();
						 element.put("td1","0");
						 element.put("value1",String.valueOf(tva));
					     
					     String base=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(le_Ht_netLigne,pattern);
					     
						 element.put("td2","1");
						 element.put("value2",base);
					     
						 element.put("td3","2");
						 String tvaB=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(leTva,pattern);
						  
						 element.put("value3",tvaB);
						 listDataTva.put(element);
						 mapTvaImpression.put(tva, base+"£"+tvaB);
					 }
				 }
			 
			 json.put("list_tva", listDataTva);
			 setObjectValueModel("mapTvaImpression", mapTvaImpression);
			  
			  
			 JSONObject  element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Brut.H.T");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(le_Ht_Reel,pattern));
			 list_total.put(element);
			 
			 if( detailBean.getTaux_remise_alacaisse()!=null  &&  le_Ht_Reel.doubleValue() >0 ){
				 remise_ala_caisse  = ProcessNumber.Pourcentage(le_Ht_Reel, detailBean.getTaux_remise_alacaisse());
				 remise_ala_caisse  = ProcessFormatNbr.FormatDouble_ParameterChiffre(remise_ala_caisse,pattern);
			 }
			 beanTotal.setVente_remise_alacaisse(remise_ala_caisse);
			 totalre_mise  = ProcessFormatNbr.FormatDouble_ParameterChiffre(totalre_mise,pattern);
			 beanTotal.setVente_remise(totalre_mise); 
			 json.put("vente_remise",  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(totalre_mise,pattern));	
			 json.put("vente_remise_alacaisse",  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(remise_ala_caisse,pattern));
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Remise");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(totalre_mise,pattern));
			 list_total.put(element);
			
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Net.H.T");
			 element.put("td2","5");
			 Double ht_apres_remise =  ProcessNumber.SOUSTRACTION(le_Ht_Reel, totalre_mise)  ;
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(ht_apres_remise,pattern));
			 list_total.put(element);
			 beanTotal.setVente_mnt_ht(ProcessFormatNbr.FormatDouble_ParameterChiffre(ht_apres_remise,pattern));
			 
			
			 
			 
			 
			
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1"," Total TVA");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(total_leTva,pattern));
			 beanTotal.setVente_mnt_tva(ProcessFormatNbr.FormatDouble_ParameterChiffre(total_leTva,pattern));
			 list_total.put(element);
			 
			 
			 Double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
			 if(bs.getFct_id().equals(Fn_Facturer)){
				 Double timbre=ProcessFormatNbr.FormatDouble_ParameterChiffre(bs.getSociete().getMontant_timbre_fiscal(),pattern);
				 element = new JSONObject();
				 element.put("td1","4");
				 element.put("value1","Timbre");
				 element.put("td2","5");
				 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(timbre,pattern));
				 list_total.put(element);
				 total_mnt_gen=ProcessNumber.addition(total_mnt_gen,timbre);
				 beanTotal.setMontant_timbre_fiscal(timbre);
			 }
			 
			 
		
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Total TTC");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(total_mnt_gen,pattern));
			 json.put("vente_mnt_total",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(total_mnt_gen,pattern));
			 beanTotal.setVente_mnt_total(ProcessFormatNbr.FormatDouble_ParameterChiffre(total_mnt_gen,pattern));
			 list_total.put(element);
			  
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Avance");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(avance,pattern));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Net à payer");
			 element.put("td2","5");
			 Double net_a_payer=ProcessNumber.SOUSTRACTION(total_mnt_gen, avance);
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(net_a_payer,pattern));
			 beanTotal.setVente_mnt_net_a_payer(ProcessFormatNbr.FormatDouble_ParameterChiffre(net_a_payer,pattern));
			 json.put("vente_mnt_net_a_payer",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(net_a_payer,pattern));
			 list_total.put(element);
			 
			 Double  montant_vente_rendu = new Double(0);
			 if(getMontant_vente_recu.doubleValue()>0 &&  net_a_payer.doubleValue()>0 &&  getMontant_vente_recu.doubleValue()>=net_a_payer){
				 montant_vente_rendu=ProcessNumber.SOUSTRACTION(getMontant_vente_recu, net_a_payer);
				 json.put("montant_vente_rendu",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(montant_vente_rendu,pattern));
				 json.put("montant_vente_recu",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(getMontant_vente_recu,pattern));
			 }else{
				 json.put("montant_vente_recu",   "");
				 json.put("montant_vente_rendu",   "");
			 }
			 beanTotal.setMontant_vente_rendu(montant_vente_rendu);
			 beanTotal.setMontant_vente_recu(getMontant_vente_recu);
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

	private void calculTotalforArticleMachandise(List <DetProcedureVenteBean > List_detaille,String pattern,
			Double tot_ht_brute ,
	        Double tot_ht_net    ,
	        Double tot_tva,
	        Double tot_qte,
	        Double marge_benefice_vente,
	        HashMap  map_des_Tva  , Double totalre_mise ,	Double getTaux_remise_alacaisse ) throws Exception {
		HashMap  map_deriver_vente  =(HashMap) getObjectValueModel(MAP_DERIVER_VENTE);
		for (int i = 0; i < List_detaille.size(); i++) {
			DetProcedureVenteBean  beanLigne=List_detaille.get(i);
			
			String lib=beanLigne.getPk().getFkcode_barre().getDesignation_libelle();
			String groupe=beanLigne.getTarif().getGroupe().getType_trf_lib();
			String lot=beanLigne.getTarif().getTarif_lot()!=null && beanLigne.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
			String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
			beanLigne.setInfo(lib+natureprix);
			/*****************************************le cout ********************************************/
    		Double getQuantiteX       = beanLigne.getQuantite()==null?new Double(0):beanLigne.getQuantite();
    		Double                 qte=ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX,pattern);
    		Double  cout              = beanLigne.getTarif().getCout()==null?new Double(0):beanLigne.getTarif().getCout().getTarif_unit_article();
    		Double	Prixcout          = cout==null?new Double(0):cout;
    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
    		le_cout=ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout,pattern);
    		
    		
    		DeriverUnite  drvUnite=beanLigne.getPk().getFkcode_barre().getPk().getAr_bean().getUnitBean().getDrv();
    		beanLigne.setUnite(beanLigne.getPk().getFkcode_barre().getPk().getAr_bean().getUnitBean().getUnite_lib());
    		
    		if(drvUnite!=null) {
    			List <DetDeriverUnite> listDrv = serviceUnite.doFetchDetDeriverUniteByDrvId(drvUnite.getDrv_id())  ;
    			for (int r = 0; r < listDrv.size();r++) {
    				DetDeriverUnite deUnite = listDrv.get(r);
    				DeriverOperationVente dVente = new DeriverOperationVente();
    				Double qteOpe=ProcessNumber.doMath(beanLigne.getQuantite(), deUnite.getDrv_coef(), deUnite.getDrv_oper().charAt(0));
    				dVente.setDrv_oper(deUnite.getDrv_oper());
    				dVente.setDrv_coef(deUnite.getDrv_coef());
    				dVente.setFkcode_barre(deUnite.getFkcode_barre());
    				dVente.setQuantite(qteOpe);
    				map_deriver_vente.put(beanLigne.getPk().getFkcode_barre().getPk().getCode_barre(), dVente);
    				beanLigne.setUnite(beanLigne.getPk().getFkcode_barre().getPk().getAr_bean().getUnitBean().getUnite_lib()+":"+ qteOpe);
				}
    				
    		} 
    		

    		 
    		
    		/*****************************************  setInfo  ********************************************/
    		Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(beanLigne.getTarif().getTarif_unit_vente(),pattern);
    		/*****************************************Prix Unit Brute reel********************************************/
    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente_brute,pattern);
    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
    		tot_qte     =ProcessNumber.addition(tot_qte, qte);
			tot_ht_brute=ProcessNumber.addition(tot_ht_brute, beanLigne.getMontant_ht_vente_reel());
    		/*******************************************Remise********************************************************/
    		Double taux_remiseligne     = beanLigne.getTarif().getTaux_remise()==null?new Double(0):beanLigne.getTarif().getTaux_remise();
			Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
			                    tot_taux=ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_taux,pattern);
			beanLigne.setTaux_remise_ligne(tot_taux);
			Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_vente_reel(), tot_taux);
			beanLigne.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
			totalre_mise=ProcessNumber.addition(totalre_mise, montant_Remise_Ligne);
		    /*******************************************montant_ht_vente *********************************************/   
		    Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, montant_Remise_Ligne);
		    montant_ht_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern);
		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern));
		    tot_ht_net=ProcessNumber.addition(tot_ht_net, beanLigne.getMontant_ht_vente());
    		/*********************************************montant_tva_vente ******************************************/
    	 
    		Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente,beanLigne.getTarif().getTvaBean(),new DeviseBean());
    		
    		montant_tva_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_tva_vente,pattern);
    		beanLigne.setMontant_tva_vente(montant_tva_vente);
    		tot_tva=ProcessNumber.addition(tot_tva, beanLigne.getMontant_tva_vente());
    		/*********************************************montant_ttc_vente *******************************************/
    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
    		/*********************************************montant_benefice *******************************************/
    		if(le_cout.doubleValue()>0){
    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
    		montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice,pattern);
    		beanLigne.setMontant_benefice(montant_benefice);
    		marge_benefice_vente=ProcessNumber.addition(marge_benefice_vente, montant_benefice);
    		}
    		/**********************************************************************************************************/
			List  listTva = (List) map_des_Tva.get(beanLigne.getTarif().getTvaBean().getTva_libelle());
			if(listTva==null)listTva= new ArrayList();
			listTva.add(beanLigne);
			map_des_Tva.put(beanLigne.getTarif().getTvaBean().getTva_libelle(), listTva);
			/**********************************************************************************************************/
		}		
	}
	
	
	private void calculTotalforFourntire(List <DetFournitureVenteBean > List_detaille,String pattern,
			Double tot_ht_brute ,
	        Double tot_ht_net    ,
	        Double tot_tva,
	        Double tot_qte,
	        Double marge_benefice_vente,
	        HashMap  map_des_Tva  , Double totalre_mise ,	Double getTaux_remise_alacaisse ) throws Exception {
		for (int i = 0; i < List_detaille.size(); i++) {
			DetFournitureVenteBean  beanLigne=List_detaille.get(i);
			
			if(!beanLigne.getIsVente()) continue;
				
			String lib=beanLigne.getFkcode_barre().getDesignation_libelle();
			String groupe=beanLigne.getTarifVente().getGroupe().getType_trf_lib();
			String lot=beanLigne.getTarifVente().getTarif_lot()!=null && beanLigne.getTarifVente().getTarif_lot().equals(true)?" * de lot   ":"";
			String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
			beanLigne.setInfo(lib+natureprix);
			/*****************************************le cout ********************************************/
    		Double getQuantiteX       = beanLigne.getQuantite()==null?new Double(0):beanLigne.getQuantite();
    		Double                 qte=ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX,pattern);
    		Double  cout              = beanLigne.getTarifVente().getCout()==null?new Double(0):beanLigne.getTarifVente().getCout().getTarif_unit_article();
    		Double	Prixcout          = cout==null?new Double(0):cout;
    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
    		le_cout=ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout,pattern);
    		 
    		
    		/*****************************************  setInfo  ********************************************/
    		Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(beanLigne.getTarifVente().getTarif_unit_vente(),pattern);
    		/*****************************************Prix Unit Brute reel********************************************/
    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente_brute,pattern);
    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
    		if( beanLigne.getIsVente()  ) tot_qte     =ProcessNumber.addition(tot_qte, qte);
    		if( beanLigne.getIsVente()  ) tot_ht_brute=ProcessNumber.addition(tot_ht_brute, beanLigne.getMontant_ht_vente_reel());
    		/*******************************************Remise********************************************************/
    		Double taux_remiseligne     = beanLigne.getTarifVente().getTaux_remise()==null?new Double(0):beanLigne.getTarifVente().getTaux_remise();
			Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
			                    tot_taux=ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_taux,pattern);
			beanLigne.setTaux_remise_ligne(tot_taux);
			Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_vente_reel(), tot_taux);
			beanLigne.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
			if( beanLigne.getIsVente()  ) totalre_mise=ProcessNumber.addition(totalre_mise, montant_Remise_Ligne);
		    /*******************************************montant_ht_vente *********************************************/   
		    Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, montant_Remise_Ligne);
		    montant_ht_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern);
		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern));
		    if( beanLigne.getIsVente()  )   tot_ht_net=ProcessNumber.addition(tot_ht_net, beanLigne.getMontant_ht_vente());
    		/*********************************************montant_tva_vente ******************************************/
    		 
    		
    		Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente,beanLigne.getTarifVente().getTvaBean(),new DeviseBean());
    		
    		montant_tva_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_tva_vente,pattern);
    		beanLigne.setMontant_tva_vente(montant_tva_vente);
    		if( beanLigne.getIsVente()  )  tot_tva=ProcessNumber.addition(tot_tva, beanLigne.getMontant_tva_vente());
    		/*********************************************montant_ttc_vente *******************************************/
    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
    		/*********************************************montant_benefice *******************************************/
    		if(le_cout.doubleValue()>0  &&  beanLigne.getIsVente()   ){
    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
    		montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice,pattern);
    		beanLigne.setMontant_benefice(montant_benefice);
    		marge_benefice_vente=ProcessNumber.addition(marge_benefice_vente, montant_benefice);
    		}
    		/**********************************************************************************************************/
    		if( beanLigne.getIsVente()  ) {
			List  listTva = (List) map_des_Tva.get(beanLigne.getTarifVente().getTvaBean().getTva_libelle());
			if(listTva==null)listTva= new ArrayList();
			listTva.add(beanLigne);
			map_des_Tva.put(beanLigne.getTarifVente().getTvaBean().getTva_libelle(), listTva);
    		}
			/**********************************************************************************************************/
		}		
	}
	
	
	private void calculTotalforService(List <DetServiceBean > List_detaille,String pattern,
			Double tot_ht_brute ,
	        Double tot_ht_net    ,
	        Double tot_tva,
	        Double tot_qte,
	        Double marge_benefice_vente,
	        HashMap  map_des_Tva  , Double totalre_mise ,	Double getTaux_remise_alacaisse ) throws Exception {
		for (int i = 0; i < List_detaille.size(); i++) {
			DetServiceBean  beanLigne=List_detaille.get(i);
			
			
				
			String lib=beanLigne.getFkcode_barre().getDesignation_libelle();
			String groupe=beanLigne.getTarifVente().getGroupe().getType_trf_lib();
			String lot=beanLigne.getTarifVente().getTarif_lot()!=null && beanLigne.getTarifVente().getTarif_lot().equals(true)?" * de lot   ":"";
			String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
			beanLigne.setInfo(lib+natureprix);
			/*****************************************le cout ********************************************/
    		Double getQuantiteX       = beanLigne.getQuantite()==null?new Double(0):beanLigne.getQuantite();
    		Double                 qte=ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX,pattern);
    		Double  cout              = beanLigne.getTarifVente().getCout()==null?new Double(0):beanLigne.getTarifVente().getCout().getTarif_unit_article();
    		Double	Prixcout          = cout==null?new Double(0):cout;
    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
    		le_cout=ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout,pattern);
    		 
    		
    		/*****************************************  setInfo  ********************************************/
    		Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(beanLigne.getTarifVente().getTarif_unit_vente(),pattern);
    		/*****************************************Prix Unit Brute reel********************************************/
    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente_brute,pattern);
    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
    		if(beanLigne.getIsVente()) tot_qte     =ProcessNumber.addition(tot_qte, qte);
    		if(beanLigne.getIsVente()) tot_ht_brute=ProcessNumber.addition(tot_ht_brute, beanLigne.getMontant_ht_vente_reel());
    		/*******************************************Remise********************************************************/
    		Double taux_remiseligne     = beanLigne.getTarifVente().getTaux_remise()==null?new Double(0):beanLigne.getTarifVente().getTaux_remise();
			Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
			                    tot_taux=ProcessFormatNbr.FormatDouble_ParameterChiffre(tot_taux,pattern);
			beanLigne.setTaux_remise_ligne(tot_taux);
			Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_vente_reel(), tot_taux);
			beanLigne.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
			if(beanLigne.getIsVente())  totalre_mise=ProcessNumber.addition(totalre_mise, montant_Remise_Ligne);
		    /*******************************************montant_ht_vente *********************************************/   
		    Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, montant_Remise_Ligne);
		    montant_ht_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern);
		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern));
		    if(beanLigne.getIsVente())   tot_ht_net=ProcessNumber.addition(tot_ht_net, beanLigne.getMontant_ht_vente());
    		/*********************************************montant_tva_vente ******************************************/
    		 
    		Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente,beanLigne.getTarifVente().getTvaBean(),new DeviseBean());
    		
    		montant_tva_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_tva_vente,pattern);
    		beanLigne.setMontant_tva_vente(montant_tva_vente);
    		if(beanLigne.getIsVente()) tot_tva=ProcessNumber.addition(tot_tva, beanLigne.getMontant_tva_vente());
    		/*********************************************montant_ttc_vente *******************************************/
    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
    		/*********************************************montant_benefice *******************************************/
    		if(le_cout.doubleValue()>0 &&   beanLigne.getIsVente()  ){
    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
    		montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice,pattern);
    		beanLigne.setMontant_benefice(montant_benefice);
    		marge_benefice_vente=ProcessNumber.addition(marge_benefice_vente, montant_benefice);
    		}
    		/**********************************************************************************************************/
    		if( beanLigne.getIsVente()  ){
			List  listTva = (List) map_des_Tva.get(beanLigne.getTarifVente().getTvaBean().getTva_libelle());
			if(listTva==null)listTva= new ArrayList();
			listTva.add(beanLigne);
			map_des_Tva.put(beanLigne.getTarifVente().getTvaBean().getTva_libelle(), listTva);
    		}
			/**********************************************************************************************************/
		}		
	}


}




