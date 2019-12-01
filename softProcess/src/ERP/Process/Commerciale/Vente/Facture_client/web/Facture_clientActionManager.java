package ERP.Process.Commerciale.Vente.Facture_client.web;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.service.ModeReglementService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Client.template.ClientTemplate;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.DetCmdCltBean;
import ERP.Process.Commerciale.Vente.Commandeclient.template.CommandeclientTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Detail_mvt_vente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.MvtVente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.service.Facture_clientService;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.FournitureVente.model.DetFournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.model.FournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.service.FournitureVenteService;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.service.ProcedureVenteService;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.Service.model.DetServiceBean;
import ERP.Process.Commerciale.Vente.Service.service.ServiceService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.NumSeqReserve;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model.CompteBancaireBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.service.CompteBancaireService;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.model.configDevelopementBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.service.configDevelopementService;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import groovy.lang.GroovyShell;

import com.google.gson.JsonObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


public class Facture_clientActionManager extends Facture_clientTemplate {
	
 
	private static final long serialVersionUID = 7178009925664163073L;
	
	private Facture_clientService  serviceFacture;
	
	@Autowired
	private configDevelopementService serviceconfigDevelopement;
	
	
	@Autowired
	public void setServiceFacture(Facture_clientService serviceFacture) {
	    this.serviceFacture = serviceFacture;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
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
	
	private ClientDAO daoClient;
	@Autowired
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}
	
	private      DepotStockageService       serviceDepotStockage;
	@Autowired
	public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
		this.serviceDepotStockage = serviceDepotStockage;
	}
	
	private TVAService   serviceTVA;
	@Autowired
	public void setServiceTva(TVAService serviceTVA) {
		this.serviceTVA = serviceTVA;
	}
	
	@Autowired
	Entite_etat_commercialeService serviceEntite_etat_commerciale;
	
	 private CompteBancaireService  serviceCompteBancaire;
		@Autowired
		public void setServiceCompteBancaire(CompteBancaireService serviceCompteBancaire) {
		    this.serviceCompteBancaire = serviceCompteBancaire;
		} 
		 private ModeReglementService  serviceModeReglement;
		  @Autowired
		  public void setServiceModeReglement(ModeReglementService serviceModeReglement) {
		      this.serviceModeReglement = serviceModeReglement;
		  } 
		  
		  
		   public   ModelAndView doTesteList( ) throws Exception{
				
			   
				 BeanSession  bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		 
				try {
					
			    	String message="";
			    	
				    	 if(  bs.getFct_id().equals(Fn_Générer) ) {
				    	 NumSeqReserve numSeqReserve  = new NumSeqReserve();
					     numSeqReserve.setCode_num("fact_clt_id");
				    	 
				    	 List<NumSeqReserve> lisnum=daoNumSequentiel.doFetchNumSequentielReseve(numSeqReserve); 
					    	if(lisnum!=null  &&   lisnum.size()>0) {
					    		for (NumSeqReserve numSeqReser  : lisnum) {
					    			message+="©"+numSeqReser.getNumero();
								}
					    	} 
				         }
				    
				     
				     
					  getResponse().setContentType(HTML_CONTENT_TYPE);
					  getResponse().getWriter().print(message);
					} catch (Exception e) {
						getResponse().setStatus(500);
						getResponse().setContentType(HTML_CONTENT_TYPE);
						PrintWriter out = getResponse().getWriter();
						out.println(e.getMessage());
					    out.close();
					}
					return null;
			 
			}
		   
		   
		   
	public    ModelAndView doInitServletAction() {

		
		
		try {
			
			
			setObjectValueModel(FORM_BEAN, new Facture_clientBean());
			setObjectValueModel(SEARCH_BEAN, new Facture_clientBean());
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			doLoadingLibelleOtherSModule(ProcedureVenteTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(CommandeclientTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(ID_SOUS_MODULE_AVOIR);
			 
			
			 setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			 List list_client_d= daoClient.doFindListClient(ClientBean.class.newInstance());
			 setObjectValueModel(LIST_CLIENT_VENTE, list_client_d);
			 
			 
			 setObjectValueModel(LIST_MODE_REGLMENT,serviceModeReglement.doFetchDatafromServer(ModeReglementBean.class.newInstance()));
			 
				
			 List list_des_tva= serviceTVA.doFetchDatafromServer(TVABean.class.newInstance());
			 setObjectValueModel(LIST_DES_TVA, list_des_tva);
			 
			   
			 
			  
			  CompteBancaireBean compteBancaireBean = new CompteBancaireBean();
			  Data_entite_simpleBean bean_sitcod = new Data_entite_simpleBean();
			  bean_sitcod.setData_id("A");
			  compteBancaireBean.setBean_sitcod(bean_sitcod);
			  setObjectValueModel(LIST_CPT_BANK, serviceCompteBancaire.doFetchDatafromServer(compteBancaireBean));
			 
			 if(bs.getSousmod_id().equals(ID_SOUS_MODULE)){
				 
				  if (bs.getFct_id().equals(Fn_Générer)   ){
						setObjectValueModel("PDF_IS_CMD", "OUI");
						setObjectValueModel(MAP_FIELD_BEAN, ProcedureVenteTemplate.MapfieldBean);
						setObjectValueModel(LIST_VIEW_G, LIST_VIEW_VENTE);
						setObjectValueModel(NAME_LIST_G ,LIST_DATA_VENTE); 
						setObjectValueModel(NAME_GRID_G, NAME_GRID_VENTE);
						setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_VENTE");
						setObjectValueModel(FORM_BEAN, new ProcedureVenteBean());
						setObjectValueModel(SEARCH_BEAN, new ProcedureVenteBean());
						return getViewFilterAjax_Vente(FILTER_VIEW_VENTE);
					}else  if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
						
						return getViewAdd(FORM_VIEW);
						
					} else {
						return getViewFilterAjax(FILTER_VIEW);

					}
				 
			 }
			 
			 if(bs.getSousmod_id().equals(ID_SOUS_MODULE_AVOIR)){
				 
				  if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
						
						return getViewFilterAjax(FILTER_VIEW);
						
					} else {
						return getViewFilterAjax(FILTER_VIEW);

					}
			 }

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}
		return getHome();
	}
	
	
	public ModelAndView doPrintRetenuSource() throws Exception {
		PrintPdfModelSPL print = new PrintPdfModelSPL();
		try {
			Facture_clientBean fBean = new Facture_clientBean();
			fBean.setFact_clt_id(getRequest().getParameter("factId"));
			List list=serviceFacture.doFetchDatafromServer(fBean);
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			configDevelopementBean beanSearch = new configDevelopementBean();
			beanSearch.getFk_etab_Bean().getPk_etab().setSoc_bean(bs.getSociete());
			beanSearch.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
			List listParam=serviceconfigDevelopement.doFetchDatafromServer(beanSearch);
			double baseRetenue=-1;
			double pourcentage=-1;
			if(listParam!=null  &&   listParam.size()>0) {
		    	configDevelopementBean json_properties=(configDevelopementBean) listParam.get(0);
		    	JSONObject json    = new JSONObject(json_properties.getJson_properties());
				JSONObject retenuSource     = json.getJSONObject("retenuSource");
				baseRetenue       = retenuSource.getDouble("baseRetenue");
				pourcentage       = retenuSource.getDouble("pourcentage");
			}
			
			print.doPrintRetenuSource((Facture_clientBean) list.get(0),pourcentage);
		} catch (Exception e) {
			displayException(e);
		}
		return null;
	}
	
	public    ModelAndView doResetForm_apres_facture() {
		setObjectValueModel("PDF_IS_CMD", "OUI");
		setObjectValueModel(MAP_FIELD_BEAN, ProcedureVenteTemplate.MapfieldBean);
		setObjectValueModel(LIST_VIEW_G, LIST_VIEW_VENTE);
		setObjectValueModel(NAME_LIST_G ,LIST_DATA_VENTE); 
		setObjectValueModel(NAME_GRID_G, NAME_GRID_VENTE);
		setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_VENTE");
		setObjectValueModel(FORM_BEAN, new ProcedureVenteBean());
		setObjectValueModel(SEARCH_BEAN, new ProcedureVenteBean());
		return getViewFilterAjax_Vente(FILTER_VIEW_VENTE);  
	}
	
	
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(Facture_clientBean searchBean)throws Throwable {
			try {
				
				BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				if (bs.getFct_id().equals(Fn_Confirmer) ||  bs.getFct_id().equals(Fn_Annuler) ){
					searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id  not in ('"+Fn_Confirmer+"','"+Fn_Envoyer+"' )    "
							+ "   AND  bean.etat_reg.data_id='fnon'    ");
					//TODO  teste is false for  find  facture in  list   a supp into entity reglment 
				}  
				configDevelopementBean beanSearch = new configDevelopementBean();
				beanSearch.getFk_etab_Bean().getPk_etab().setSoc_bean(bs.getSociete());
				beanSearch.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
				List list=serviceconfigDevelopement.doFetchDatafromServer(beanSearch);
				double baseRetenue=-1;
				double pourcentage=-1;
				if(list!=null  &&   list.size()>0) {
			    	configDevelopementBean json_properties=(configDevelopementBean) list.get(0);
			    	JSONObject json    = new JSONObject(json_properties.getJson_properties());
					JSONObject retenuSource     = json.getJSONObject("retenuSource");
					baseRetenue       = retenuSource.getDouble("baseRetenue");
					pourcentage       = retenuSource.getDouble("pourcentage");
				}
				List listDataSrv = serviceFacture.doFetchDatafromServer(searchBean);
				
				Double totGridFctClient = new Double(0);
				Double totRetenueFctClient = new Double(0);
				for (int i = 0; i < listDataSrv.size(); i++) {
					Facture_clientBean  reBean	=(Facture_clientBean) listDataSrv.get(i);
					totGridFctClient=ProcessNumber.addition(totGridFctClient,  ProcessFormatNbr.FormatDouble_Troischiffre(reBean.getTotal_facture()) );
					if( baseRetenue > -1 &&   pourcentage >-1 && reBean.getTotal_facture()!=null  &&
							reBean.getTotal_facture() >  baseRetenue   ) {
						Double retenu=ProcessNumber.Pourcentage(reBean.getTotal_facture(), pourcentage);
						reBean.setRetenuSource( ProcessFormatNbr.FormatDouble_Troischiffre(retenu)  );
						totRetenueFctClient=ProcessNumber.addition(totRetenueFctClient,  ProcessFormatNbr.FormatDouble_Troischiffre(retenu) );
					}
				}
				setObjectValueModel("totGridFctClient", totGridFctClient);
				setObjectValueModel("totRetenueFctClient", totRetenueFctClient);
				
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
	public ModelAndView doFetchDataVente(ProcedureVenteBean searchBean)throws Throwable {
			try {
				BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				if (bs.getFct_id().equals(Fn_Générer)){
					searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id in ('"+Fn_Confirmer+"')   ");
				} 
				
				List  listDataSrv= serviceProcedureVente.doFetchDatafromServer(searchBean);
				for (int i = 0; i < listDataSrv.size(); i++) {
					ProcedureVenteBean pBean	=(ProcedureVenteBean) listDataSrv.get(i);
					pBean.setTo_check("");
				}
				setObjectValueModel("list_Brute", ProcessUtil.cloneList(listDataSrv) );
					
				setObjectValueModel(SEARCH_BEAN, searchBean);
				setObjectValueModel(LIST_DATA_VENTE, listDataSrv);
				//AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public    ModelAndView doGenererInterfaceFacture(Facture_clientBean  searchBean ) {

		try {
			 
	     //BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
	     List <ProcedureVenteBean >  procedurevente_list_facture =   (List)  getObjectValueModel(LIST_DATA_VENTE);
	     ClientBean clientBean = null;
	     String pattern ="0.000";
	     
	     Double avance_montant_vente =new Double(0);
	     Double remise_facture       =new Double(0);
	     
	     Facture_clientBean factureBean = new Facture_clientBean();
	     
	     List<Det_Fact_ClientBean> liste_detaille_facture= new ArrayList<Det_Fact_ClientBean>();
	     
	     HashMap  map_article= new HashMap();
	     HashMap  map_fourniture= new HashMap();
	     HashMap  map_Service= new HashMap();
	     
	     HashMap  map_detail_codBarre= new HashMap();
	      
	     
	     for (ProcedureVenteBean  beanVente:procedurevente_list_facture) {
	    	 if(  StringUtils.isEmpty(beanVente.getTo_check()) ) continue;
	    	 
	    	 if( beanVente.getDevise().getDev_id().intValue()==191  ||  beanVente.getDevise().getDev_id().intValue()==192   ){
					pattern ="0.00";
			  } 
	    	 
	    	 Double mntAvance=ProcessFormatNbr.FormatDouble_ParameterChiffre(beanVente.getAvance_montant_vente(),pattern);
	    	 Double mntRemise=ProcessFormatNbr.FormatDouble_ParameterChiffre(beanVente.getVente_remise(),pattern);
	    	 
	    	 if(clientBean==null) clientBean=beanVente.getClient();
	    	 avance_montant_vente=ProcessNumber.addition(avance_montant_vente, mntAvance);
	    	 remise_facture=ProcessNumber.addition(remise_facture, mntRemise);
	    	 factureBean.setDevise(beanVente.getDevise());
	    	 
	    	 List <DetProcedureVenteBean>list_detaill_Vente=serviceProcedureVente.doFetch_detDatafromServer(beanVente) ;
	    	 for (DetProcedureVenteBean beand:list_detaill_Vente) {
	    		 String keyString  =    beand.getPk().getFkcode_barre().getPk().getCode_barre()+"£"+
	    		                        beand.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"£"+
	    		                        beand.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"£"+
	    		                        beand.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"£";
	    		List list= (List) map_article.get(keyString);
	    		if(list==null)list= new ArrayList();
	    		list.add(beand);
	    		map_article.put(keyString, list);
	    		if(map_detail_codBarre.get(keyString)==null){
	    			map_detail_codBarre.put(keyString, beand.getPk().getFkcode_barre());
	    		}
	    		
	    	 }
	    	 
	    	 FournitureVenteBean fourBean= new FournitureVenteBean();
			 fourBean.setVenteFrn(beanVente);
			 List <DetFournitureVenteBean>listDetFournitureVente  =  serviceFournitureVente.doFetchDetailFourniturefromServer(fourBean);
			 for (DetFournitureVenteBean beanfrns:listDetFournitureVente) {
				 
				  if(ProcessUtil.doesObjectContainField(beanfrns, "isVente")) {
					  Object obj=	 GenericWeb.getValueOject_with_name_field(beanfrns, "isVente");
					  Boolean   isVenteData = (Boolean) obj;
					  if(!isVenteData) continue;
				  }
				  
	    		 String keyString  =    beanfrns.getFkcode_barre().getPk().getCode_barre()+"£"+
	    				 beanfrns.getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"£"+
	    				 beanfrns.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"£"+
	    				 beanfrns.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"£";
	    		List list= (List) map_fourniture.get(keyString);
	    		if(list==null)list= new ArrayList();
	    		list.add(beanfrns);
	    		map_fourniture.put(keyString, list);
	    		if(map_detail_codBarre.get(keyString)==null){
	    			map_detail_codBarre.put(keyString, beanfrns.getFkcode_barre());
	    		}
	    	 }
			 
			 
			 List <DetServiceBean>listDetServiceBean =  serviceService.doFindDetailListServiceByVenteId(beanVente);
			 for (DetServiceBean beanserv:listDetServiceBean) {
				 
				 if(ProcessUtil.doesObjectContainField(beanserv, "isVente")) {
					  Object obj=	 GenericWeb.getValueOject_with_name_field(beanserv, "isVente");
					  Boolean   isVenteData = (Boolean) obj;
					  if(!isVenteData) continue;
				  }
				 
				 
	    		 String keyString  =    beanserv.getFkcode_barre().getPk().getCode_barre()+"£"+
	    				                beanserv.getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"£"+
	    				                beanserv.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"£"+
	    				                beanserv.getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"£";
	    		List list= (List) map_Service.get(keyString);
	    		if(list==null)list= new ArrayList();
	    		list.add(beanserv);
	    		map_Service.put(keyString, list);
	    		if(map_detail_codBarre.get(keyString)==null){
	    			map_detail_codBarre.put(keyString, beanserv.getFkcode_barre());
	    		}
	    	 }
 
	     }
	     
	     Set  set_map_fourniture=map_fourniture.keySet();
	     List listMvt_Vente= new ArrayList();
	     for (Iterator itera = set_map_fourniture.iterator(); itera.hasNext();) {
			 String produit = (String) itera.next();
			 
			 Code_barreBean  fkcode_barre=(Code_barreBean) map_detail_codBarre.get(produit);
			 MvtVente_articleBean  mvt_article= new MvtVente_articleBean();
			 
			 Double  quantiteGen	= new Double(0);
			 Double  quantiteBox	= new Double(0);
			 Double  mnt_ht		    = new Double(0);
			 Double  mnt_tva	    = new Double(0);
			 
			 Double  montant_ht_vente_reel	    = new Double(0);
			 Double  montant_Remise_Ligne_gen	    = new Double(0);
			 Double  taux_remise_ligne_gen	    = new Double(0);
			 
			 
			 
			 List list_detail_mvt_vente= new ArrayList();
			 TVABean  tvaBean	= new TVABean();
			 List listMvt=(List) map_fourniture.get(produit);
			 
			 for (int i = 0; i < listMvt.size(); i++) {
				 DetFournitureVenteBean detProVente = (DetFournitureVenteBean) listMvt.get(i);
				 tvaBean=detProVente.getTarifVente().getTvaBean();
				  
				 
				 if(ProcessUtil.doesObjectContainField(detProVente, "isVente")) {
					  Object obj=	 GenericWeb.getValueOject_with_name_field(detProVente, "isVente");
					  Boolean   isVenteData = (Boolean) obj;
					  if(!isVenteData) continue;
				  }
				 
				 Detail_mvt_vente_articleBean detMvt = new Detail_mvt_vente_articleBean();
				 detMvt.getPk().getMvt_vente().setMvt_vente_id("");
				 detMvt.getPk().setVente(detProVente.getFourniture().getVenteFrn());
				 detMvt.getPk().setDocument_com_id(detProVente.getFourniture().getVenteFrn().getVente_id()) ;
				 detMvt.getPk().getNat_mvt().setNature_mvt_id("ve");
				 detMvt.setTarif(detProVente.getTarifVente());
				 detMvt.getPk().setFkcode_barre(detProVente.getFkcode_barre());
				 detMvt.setQuantite(detProVente.getQuantite());
				 
				
				 
				 
				 
				/*****************************************le cout ********************************************/
		        /*Double  getQuantiteX      = detProVente.getQuantite()==null?new Double(0):detProVente.getQuantite();
		    	Double  qte               = ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX);
		    	Double  cout              = detProVente.getTarif().getCout()==null?new Double(0):detProVente.getTarif().getCout().getTarif_unit_article();
		    	Double	Prixcout          = cout==null?new Double(0):cout;
		    	Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
		    					  le_cout = ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout);*/
		    		
		    	/*****************************************  setInfo  ********************************************/
		    		
		    	/*String libelle_desi=detProVente.getPk().getFkcode_barre().getDesignation_libelle();
		    	String groupe=detProVente.getTarif().getGroupe().getType_trf_lib();
				String lot=detProVente.getTarif().getTarif_lot()!=null && detProVente.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
				String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
				detProVente.setInfo(libelle_desi+natureprix);*/
		    	
				 quantiteGen  =ProcessNumber.addition(quantiteGen, detProVente.getQuantite());
				 
		    		
		    		
		    	/*****************************************Prix Unit Brute reel********************************************/
				Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(detProVente.getTarifVente().getTarif_unit_vente(),pattern);	
		    	Double montant_ht_ligne_reel=ProcessNumber.PRODUIT(priUnitvente, detProVente.getQuantite());
		    	montant_ht_ligne_reel=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_ligne_reel,pattern);
		    	
		    	montant_ht_vente_reel=ProcessNumber.addition(montant_ht_vente_reel,montant_ht_ligne_reel);
		    		 
		    	/*******************************************Remise********************************************************/
		    		
		    	Double taux_remiseligne     = detProVente.getTaux_remise_ligne()==null?new Double(0):detProVente.getTaux_remise_ligne();
				Double tot_taux             = ProcessFormatNbr.FormatDouble_ParameterChiffre(taux_remiseligne,pattern);
				taux_remise_ligne_gen=ProcessNumber.addition(taux_remise_ligne_gen,tot_taux); 
				
				
				
				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(montant_ht_ligne_reel, tot_taux);
				detProVente.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
				montant_Remise_Ligne_gen=ProcessNumber.addition(montant_Remise_Ligne_gen,montant_Remise_Ligne); 

	    		/*******************************************montant_ht_vente *********************************************/   
	    		    
	    		Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_reel, montant_Remise_Ligne);
	    		montant_ht_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern);
	    		detProVente.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern));
	    		detMvt.setMontant_ht_vente(montant_ht_vente);
	    		mnt_ht=ProcessNumber.addition(mnt_ht, montant_ht_vente);
		    		
		    	/*********************************************montant_tva_vente ******************************************/
		    		 
		    	 
		    	
		    	Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente, detProVente.getTarifVente().getTvaBean(),detProVente.getDevise());
		    	
		    	montant_tva_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_tva_vente,pattern);
		    	detProVente.setMontant_tva_vente(montant_tva_vente);
		    	detMvt.setMontant_tva_vente(montant_tva_vente);
		    	mnt_tva=ProcessNumber.addition(mnt_tva, montant_tva_vente);
		    	
		    		
		    	/*********************************************montant_ttc_vente *******************************************/
		    		
		    	Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
		    	detProVente.setMontant_ttc_vente(montant_ttc_vente);
		    		
		    	/*********************************************montant_benefice *******************************************/
		    	/*if(le_cout.doubleValue()>0){
		    	 Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
		    	montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice);
		    	detProVente.setMontant_benefice(montant_benefice);
		    	}*/
		    	/**********************************************************************************************************/
				 
				 list_detail_mvt_vente.add(detMvt);
			}
			mvt_article.setFkcode_barre(fkcode_barre); 
			mvt_article.setTvaBean(tvaBean);
			mvt_article.setQuantite(quantiteGen);
			mvt_article.setNbrBox(quantiteBox);
			mvt_article.setMontant_ht_vente(mnt_ht);
			mvt_article.setMontant_tva_vente(mnt_tva);
			mvt_article.setMontant_ht_vente_reel(montant_ht_vente_reel);
			mvt_article.setMontant_Remise_Ligne(montant_Remise_Ligne_gen);
			mvt_article.setTaux_remise_ligne(taux_remise_ligne_gen);
			mvt_article.setList_detail_mvt_vente(list_detail_mvt_vente);
			listMvt_Vente.add(mvt_article);
		 }
	     
	     
	     Set  set_map_service=map_Service.keySet();
	     for (Iterator itera = set_map_service.iterator(); itera.hasNext();) {
			 String produit = (String) itera.next();
			 
			 Code_barreBean  fkcode_barre=(Code_barreBean) map_detail_codBarre.get(produit);
			 MvtVente_articleBean  mvt_article= new MvtVente_articleBean();
			 
			 Double  quantiteGen	= new Double(0);
			 Double  quantiteBox	= new Double(0);
			 Double  mnt_ht		    = new Double(0);
			 Double  mnt_tva	    = new Double(0);
			 
			 Double  montant_ht_vente_reel	    = new Double(0);
			 Double  montant_Remise_Ligne_gen	    = new Double(0);
			 Double  taux_remise_ligne_gen	    = new Double(0);
			 
			 
			 
			 List list_detail_mvt_vente= new ArrayList();
			 TVABean  tvaBean	= new TVABean();
			 List listMvt=(List) map_Service.get(produit);
			 
			 for (int i = 0; i < listMvt.size(); i++) {
				 DetServiceBean detProVente = (DetServiceBean) listMvt.get(i);
				 tvaBean=detProVente.getTarifVente().getTvaBean();
				  
				 
				 if(ProcessUtil.doesObjectContainField(detProVente, "isVente")) {
					  Object obj=	 GenericWeb.getValueOject_with_name_field(detProVente, "isVente");
					  Boolean   isVenteData = (Boolean) obj;
					  if(!isVenteData) continue;
				  }
				 
				 Detail_mvt_vente_articleBean detMvt = new Detail_mvt_vente_articleBean();
				 detMvt.getPk().getMvt_vente().setMvt_vente_id("");
				 detMvt.getPk().setVente(detProVente.getService().getVenteSrv());
				 detMvt.getPk().setDocument_com_id(detProVente.getService().getVenteSrv().getVente_id()) ;
				 detMvt.getPk().getNat_mvt().setNature_mvt_id("ve");
				 detMvt.setTarif(detProVente.getTarifVente());
				 detMvt.getPk().setFkcode_barre(detProVente.getFkcode_barre());
				 detMvt.setQuantite(detProVente.getQuantite());
				 
				
				 
				 
				 
				/*****************************************le cout ********************************************/
		        /*Double  getQuantiteX      = detProVente.getQuantite()==null?new Double(0):detProVente.getQuantite();
		    	Double  qte               = ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX);
		    	Double  cout              = detProVente.getTarif().getCout()==null?new Double(0):detProVente.getTarif().getCout().getTarif_unit_article();
		    	Double	Prixcout          = cout==null?new Double(0):cout;
		    	Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
		    					  le_cout = ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout);*/
		    		
		    	/*****************************************  setInfo  ********************************************/
		    		
		    	/*String libelle_desi=detProVente.getPk().getFkcode_barre().getDesignation_libelle();
		    	String groupe=detProVente.getTarif().getGroupe().getType_trf_lib();
				String lot=detProVente.getTarif().getTarif_lot()!=null && detProVente.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
				String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
				detProVente.setInfo(libelle_desi+natureprix);*/
		    	
				 quantiteGen  =ProcessNumber.addition(quantiteGen, detProVente.getQuantite());
				 
		    		
		    		
		    	/*****************************************Prix Unit Brute reel********************************************/
				Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(detProVente.getTarifVente().getTarif_unit_vente(),pattern);	
		    	Double montant_ht_ligne_reel=ProcessNumber.PRODUIT(priUnitvente, detProVente.getQuantite());
		    	montant_ht_ligne_reel=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_ligne_reel,pattern);
		    	
		    	montant_ht_vente_reel=ProcessNumber.addition(montant_ht_vente_reel,montant_ht_ligne_reel);
		    		 
		    	/*******************************************Remise********************************************************/
		    		
		    	Double taux_remiseligne     = detProVente.getTaux_remise_ligne()==null?new Double(0):detProVente.getTaux_remise_ligne();
				Double tot_taux             = ProcessFormatNbr.FormatDouble_ParameterChiffre(taux_remiseligne,pattern);
				taux_remise_ligne_gen=ProcessNumber.addition(taux_remise_ligne_gen,tot_taux); 
				
				
				
				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(montant_ht_ligne_reel, tot_taux);
				detProVente.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
				montant_Remise_Ligne_gen=ProcessNumber.addition(montant_Remise_Ligne_gen,montant_Remise_Ligne); 

	    		/*******************************************montant_ht_vente *********************************************/   
	    		    
	    		Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_reel, montant_Remise_Ligne);
	    		montant_ht_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern);
	    		detProVente.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern));
	    		detMvt.setMontant_ht_vente(montant_ht_vente);
	    		mnt_ht=ProcessNumber.addition(mnt_ht, montant_ht_vente);
		    		
		    	/*********************************************montant_tva_vente ******************************************/
		    		 
		    	 
		    	
		    	Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente, detProVente.getTarifVente().getTvaBean(),detProVente.getDevise());
		    	
		    	montant_tva_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_tva_vente,pattern);
		    	detProVente.setMontant_tva_vente(montant_tva_vente);
		    	detMvt.setMontant_tva_vente(montant_tva_vente);
		    	mnt_tva=ProcessNumber.addition(mnt_tva, montant_tva_vente);
		    	
		    		
		    	/*********************************************montant_ttc_vente *******************************************/
		    		
		    	Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
		    	detProVente.setMontant_ttc_vente(montant_ttc_vente);
		    		
		    	/*********************************************montant_benefice *******************************************/
		    	/*if(le_cout.doubleValue()>0){
		    	 Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
		    	montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice);
		    	detProVente.setMontant_benefice(montant_benefice);
		    	}*/
		    	/**********************************************************************************************************/
				 
				 list_detail_mvt_vente.add(detMvt);
			}
			mvt_article.setFkcode_barre(fkcode_barre); 
			mvt_article.setTvaBean(tvaBean);
			mvt_article.setQuantite(quantiteGen);
			
			mvt_article.setNbrBox(quantiteBox);
			mvt_article.setMontant_ht_vente(mnt_ht);
			mvt_article.setMontant_tva_vente(mnt_tva);
			mvt_article.setMontant_ht_vente_reel(montant_ht_vente_reel);
			mvt_article.setMontant_Remise_Ligne(montant_Remise_Ligne_gen);
			mvt_article.setTaux_remise_ligne(taux_remise_ligne_gen);
			mvt_article.setList_detail_mvt_vente(list_detail_mvt_vente);
			listMvt_Vente.add(mvt_article);
		 }
	     
	     
	     Set mapio_map_article=map_article.keySet();
	     for (Iterator itera = mapio_map_article.iterator(); itera.hasNext();) {
			 String produit = (String) itera.next();
			
			 
			 Code_barreBean  fkcode_barre=(Code_barreBean) map_detail_codBarre.get(produit);
			 MvtVente_articleBean  mvt_article= new MvtVente_articleBean();
			 
			 Double  quantiteGen	= new Double(0);
			 Double  quantiteBox	= new Double(0);
			 Double  mnt_ht		    = new Double(0);
			 Double  mnt_tva	    = new Double(0);
			 
			 Double  montant_ht_vente_reel	    = new Double(0);
			 Double  montant_Remise_Ligne_gen	    = new Double(0);
			 Double  taux_remise_ligne_gen	    = new Double(0);
			 
			 
			 
			 List list_detail_mvt_vente= new ArrayList();
			 TVABean  tvaBean	= new TVABean();
			 List listMvt=(List) map_article.get(produit);
			 
			 for (int i = 0; i < listMvt.size(); i++) {
				 DetProcedureVenteBean detProVente = (DetProcedureVenteBean) listMvt.get(i);
				 tvaBean=detProVente.getTarif().getTvaBean();
				  
				 
				
				 
				 Detail_mvt_vente_articleBean detMvt = new Detail_mvt_vente_articleBean();
				 detMvt.getPk().getMvt_vente().setMvt_vente_id("");
				 detMvt.getPk().setVente(detProVente.getPk().getVente());
				 detMvt.getPk().setDocument_com_id(detProVente.getPk().getVente().getVente_id()) ;
				 detMvt.getPk().getNat_mvt().setNature_mvt_id("ve");
				 detMvt.setTarif(detProVente.getTarif());
				 detMvt.getPk().setFkcode_barre(detProVente.getPk().getFkcode_barre());
				 detMvt.setQuantite(detProVente.getQuantite());
				 
				
				 
				 
				 
				/*****************************************le cout ********************************************/
		        /*Double  getQuantiteX      = detProVente.getQuantite()==null?new Double(0):detProVente.getQuantite();
		    	Double  qte               = ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX);
		    	Double  cout              = detProVente.getTarif().getCout()==null?new Double(0):detProVente.getTarif().getCout().getTarif_unit_article();
		    	Double	Prixcout          = cout==null?new Double(0):cout;
		    	Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
		    					  le_cout = ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout);*/
		    		
		    	/*****************************************  setInfo  ********************************************/
		    		
		    	/*String libelle_desi=detProVente.getPk().getFkcode_barre().getDesignation_libelle();
		    	String groupe=detProVente.getTarif().getGroupe().getType_trf_lib();
				String lot=detProVente.getTarif().getTarif_lot()!=null && detProVente.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
				String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
				detProVente.setInfo(libelle_desi+natureprix);*/
		    	
				 quantiteGen  =ProcessNumber.addition(quantiteGen, detProVente.getQuantite());
				 if(detProVente.getDrv()!=null) {
					 quantiteBox  =ProcessNumber.addition(quantiteBox, detProVente.getDrv().getQuantite());
				 }
				
		    		
		    		
		    	/*****************************************Prix Unit Brute reel********************************************/
				Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(detProVente.getTarif().getTarif_unit_vente(),pattern);	
		    	Double montant_ht_ligne_reel=ProcessNumber.PRODUIT(priUnitvente, detProVente.getQuantite());
		    	montant_ht_ligne_reel=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_ligne_reel,pattern);
		    	
		    	montant_ht_vente_reel=ProcessNumber.addition(montant_ht_vente_reel,montant_ht_ligne_reel);
		    		 
		    	/*******************************************Remise********************************************************/
		    		
		    	Double taux_remiseligne     = detProVente.getTaux_remise_ligne()==null?new Double(0):detProVente.getTaux_remise_ligne();
				Double tot_taux             = ProcessFormatNbr.FormatDouble_ParameterChiffre(taux_remiseligne,pattern);
				taux_remise_ligne_gen=ProcessNumber.addition(taux_remise_ligne_gen,tot_taux); 
				
				
				
				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(montant_ht_ligne_reel, tot_taux);
				detProVente.setMontant_Remise_Ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
				montant_Remise_Ligne_gen=ProcessNumber.addition(montant_Remise_Ligne_gen,montant_Remise_Ligne); 

	    		/*******************************************montant_ht_vente *********************************************/   
	    		    
	    		Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_reel, montant_Remise_Ligne);
	    		montant_ht_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern);
	    		detProVente.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente,pattern));
	    		detMvt.setMontant_ht_vente(montant_ht_vente);
	    		mnt_ht=ProcessNumber.addition(mnt_ht, montant_ht_vente);
		    		
		    	/*********************************************montant_tva_vente ******************************************/
		    		 
		     
		    	Double montant_tva_vente= ProcessNumber.getMontantTvaByMontantHT(montant_ht_vente, detProVente.getTarif().getTvaBean(),detProVente.getDevise());
		    	montant_tva_vente=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_tva_vente,pattern);
		    	detProVente.setMontant_tva_vente(montant_tva_vente);
		    	detMvt.setMontant_tva_vente(montant_tva_vente);
		    	mnt_tva=ProcessNumber.addition(mnt_tva, montant_tva_vente);
		    	
		    		
		    	/*********************************************montant_ttc_vente *******************************************/
		    		
		    	Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
		    	detProVente.setMontant_ttc_vente(montant_ttc_vente);
		    		
		    	/*********************************************montant_benefice *******************************************/
		    	/*if(le_cout.doubleValue()>0){
		    	 Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
		    	montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice);
		    	detProVente.setMontant_benefice(montant_benefice);
		    	}*/
		    	/**********************************************************************************************************/
				 
				 list_detail_mvt_vente.add(detMvt);
			}
			mvt_article.setFkcode_barre(fkcode_barre); 
			mvt_article.setTvaBean(tvaBean);
			mvt_article.setQuantite(quantiteGen);
			
			mvt_article.setNbrBox(quantiteBox);
			mvt_article.setMontant_ht_vente(mnt_ht);
			mvt_article.setMontant_tva_vente(mnt_tva);
			mvt_article.setMontant_ht_vente_reel(montant_ht_vente_reel);
			mvt_article.setMontant_Remise_Ligne(montant_Remise_Ligne_gen);
			mvt_article.setTaux_remise_ligne(taux_remise_ligne_gen);
			mvt_article.setList_detail_mvt_vente(list_detail_mvt_vente);
			listMvt_Vente.add(mvt_article);
		 }
	     
	     for (int i = 0; i < listMvt_Vente.size(); i++) {
	    	 
	    	 MvtVente_articleBean  mvt_article= (MvtVente_articleBean) listMvt_Vente.get(i);
	    	 
	    	 Det_Fact_ClientBean  beanDetailleFacture= new Det_Fact_ClientBean();
	    	 beanDetailleFacture.getPk().setMvtVente(mvt_article);
	    	 beanDetailleFacture.getPk().setFkcode_barre(mvt_article.getFkcode_barre());
	    	 beanDetailleFacture.setQuantite(mvt_article.getQuantite());
	    	 beanDetailleFacture.setNbrBoxes(mvt_article.getNbrBox());
	    	 Double mntht=ProcessFormatNbr.FormatDouble_ParameterChiffre(mvt_article.getMontant_ht_vente(),pattern);
	    	 
	    	 Double mntht_reel=ProcessFormatNbr.FormatDouble_ParameterChiffre(mvt_article.getMontant_ht_vente_reel(),pattern);
	    	 
	    	 
	    	 Double mnttva=ProcessFormatNbr.FormatDouble_ParameterChiffre(mvt_article.getMontant_tva_vente(),pattern);
	    	 Double tarif_unit_vente=ProcessNumber.DIVISION(mntht_reel, mvt_article.getQuantite());
	    	 beanDetailleFacture.setMontant_ht_vente(mntht);
	    	 beanDetailleFacture.setMontant_tva_vente(mnttva);
	    	 beanDetailleFacture.setTarif_unit_vente(tarif_unit_vente);
	    	 beanDetailleFacture.setTvaBean(mvt_article.getTvaBean());
	    	 beanDetailleFacture.setMontant_ht_vente_reel( mvt_article.getMontant_ht_vente_reel());
	    	 beanDetailleFacture.setMontant_remise_ligne( mvt_article.getMontant_Remise_Ligne());
	    	 beanDetailleFacture.setTaux_remise_ligne( mvt_article.getTaux_remise_ligne());
	    	 beanDetailleFacture.setObservation("");
	    	 liste_detaille_facture.add(beanDetailleFacture);
		 }
	     factureBean.setAvance_montant_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(avance_montant_vente,pattern));
	     factureBean.setFact_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
	     factureBean.setClient(clientBean);
	     factureBean.setCpt_bank(clientBean.getCompte());
	   
	      
	     
	     setObjectValueModel(LIST_DATA_DET_FACT,liste_detaille_facture);
	     setObjectValueModel(LIST_DATA_DET_MVT_VENTE,listMvt_Vente);
	     setObjectValueModel(FORM_BEAN, factureBean);
	     
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax_Vente(FILTER_VIEW_VENTE);
		}
		
		return getViewPreparerAdd(FORM_VIEW);

	}
	
	
	public    ModelAndView doRetourToList_Vente() {
		
		setObjectValueModel(MAP_FIELD_BEAN, ProcedureVenteTemplate.MapfieldBean);
		setObjectValueModel(LIST_VIEW_G, LIST_VIEW_VENTE);
		setObjectValueModel(NAME_LIST_G ,LIST_DATA_VENTE); 
		setObjectValueModel(NAME_GRID_G, NAME_GRID_VENTE);
		List  listDataTrie=(List) getObjectValueModel((String)getObjectValueModel(NAME_LIST_G)  );
		setObjectValueModel(DATA_LIST_AJAX,listDataTrie);
		setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_VENTE");
	 
		
		return getViewFilterAjax_Vente(FILTER_VIEW_VENTE);
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculer_Total(Facture_clientBean detailBean ) throws Exception {
		try {
			BeanSession bs                =(BeanSession)getObjectValueModel(BEAN_SESSION);
			Facture_clientBean   rowBean = (Facture_clientBean) getObjectValueModel(FORM_BEAN);
			List <Det_Fact_ClientBean >List_detaille= new ArrayList<Det_Fact_ClientBean>();
			String pattern ="0.000";
	    	if(rowBean.getDevise() !=null  && ( rowBean.getDevise().getDev_id().intValue()==191  ||  rowBean.getDevise().getDev_id().intValue()==192 )  ){
					pattern ="0.00";
			} 
			
			Double getAvance_montant_vente  = detailBean.getAvance_montant_vente()==null?new Double(0):detailBean.getAvance_montant_vente();
			Double avance                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(getAvance_montant_vente,pattern);
			Double timbre                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(bs.getSociete().getMontant_timbre_fiscal(),pattern);
			 
			if(rowBean.getClient().getClt_exonorer().booleanValue()==true) {
				timbre= new Double(0);
			}
			if(bs.getFct_id().equals(Fn_Générer)  ||  bs.getFct_id().equals(Fn_Facturer)   ){
				 List_detaille=(List<Det_Fact_ClientBean>) getObjectValueModel(LIST_DATA_DET_FACT);
			}else{
				List_detaille=serviceFacture.doFetchDetaillefromServer(rowBean);
				getAvance_montant_vente  = rowBean.getAvance_montant_vente()==null?new Double(0):rowBean.getAvance_montant_vente();
				avance                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(getAvance_montant_vente,pattern);
			}
			
			setObjectValueModel(LIST_DATA_DET_FACT, List_detaille);
			setObjectValueModel(LIST_DATA_DET_FACT_CLONE, ProcessUtil.cloneList(List_detaille) );  
			
			
			Double totalre_mise          			  =  new Double(0);
			Double tot_ht_brute =new Double(0);
			Double tot_ht_net    =new Double(0);
			Double tot_tva=new Double(0);
			Double tot_qte=new Double(0);
			Double tot_qteBox=new Double(0);
			
			
			HashMap   map_des_Tva = new HashMap();
			for (int i = 0; i < List_detaille.size(); i++) {
				Det_Fact_ClientBean  beanLigne=List_detaille.get(i);
				
				 
				/*****************************************le cout ********************************************/
	    		Double getQuantiteX       = beanLigne.getQuantite()==null?new Double(0):beanLigne.getQuantite();
	    		Double getQuantiteBox       = beanLigne.getNbrBoxes()==null?new Double(0):beanLigne.getNbrBoxes();
	    		Double                 qte= ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX,pattern);
	    		/*Double  cout              = beanLigne.getTarif().getCout()==null?new Double(0):beanLigne.getTarif().getCout().getTarif_unit_article(,pattern);
	    		Double	Prixcout          = cout==null?new Double(0):cout;
	    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
	    		le_cout					  = ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout);*/
	    		
	    		/*****************************************  setInfo  ********************************************/
	    		
	    		/*****************************************  setInfo  ********************************************/
	    		Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(beanLigne.getTarif_unit_vente(),pattern);
	    		/*****************************************Prix Unit Brute reel********************************************/
	    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
	    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente_brute,pattern);
	    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
	    		tot_qte         =ProcessNumber.addition(tot_qte, qte);
	    		
	    		Double       qteBox= ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteBox,pattern);
	    		tot_qteBox      =ProcessNumber.addition(tot_qteBox, qteBox);
	    		
	    		
				tot_ht_brute=ProcessNumber.addition(tot_ht_brute, montant_ht_vente_brute);
	    		 
	    		/*******************************************Remise********************************************************/
				 
				Double taux_remiseligne     = beanLigne.getTaux_remise_ligne()==null?new Double(0):beanLigne.getTaux_remise_ligne();
				taux_remiseligne=ProcessFormatNbr.FormatDouble_ParameterChiffre(taux_remiseligne,pattern);
				beanLigne.setTaux_remise_ligne(taux_remiseligne);
				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_vente_reel(), taux_remiseligne);
				beanLigne.setMontant_remise_ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
				totalre_mise=ProcessNumber.addition(totalre_mise, montant_Remise_Ligne);
 
    		    /*******************************************montant_ht_vente *********************************************/   
    		    
    		    tot_ht_net=ProcessNumber.addition(tot_ht_net, beanLigne.getMontant_ht_vente());
	    		
	    		/*********************************************montant_tva_vente ******************************************/
	    		 
	    		tot_tva=ProcessNumber.addition(tot_tva, beanLigne.getMontant_tva_vente());
	    		
	    		/*********************************************montant_ttc_vente *******************************************/
	    		Double  montant_ttc_vente=ProcessNumber.addition(beanLigne.getMontant_ht_vente(), beanLigne.getMontant_tva_vente());
	    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
	    		
	    		/*********************************************montant_benefice *******************************************/
	    		/*if(le_cout.doubleValue()>0){
	    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
	    		montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice);
	    		beanLigne.setMontant_benefice(montant_benefice);
	    		marge_benefice_vente=ProcessNumber.addition(marge_benefice_vente, montant_benefice);*/
	    		//}
	    		/**********************************************************************************************************/
				 
				
				List  listTva = (List) map_des_Tva.get(beanLigne.getTvaBean().getTva_libelle());
				if(listTva==null)listTva= new ArrayList();
				listTva.add(beanLigne);
				map_des_Tva.put(beanLigne.getTvaBean().getTva_libelle(), listTva);
				
				/**********************************************************************************************************/
			}
			
			 Facture_clientBean    beanTotal = new Facture_clientBean();
			 beanTotal.setAvance_montant_vente(avance)  ; 
			 beanTotal.setTotnbrBox(tot_qteBox);
			 beanTotal.setTotal_quantite(String.valueOf(tot_qte));
			 //beanTotal.setMarge_benefice_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(marge_benefice_vente));
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
					 
					 if(map_des_Tva.get(beanTva.getTva_libelle())!=null){
						 List listTva  =(List) map_des_Tva.get(beanTva.getTva_libelle());
						 String  tva   = beanTva.getTva_libelle();
						  	
						 Double le_Ht_netLigne  = new Double(0);
					     Double leTva  = new Double(0);
					 	 for (int i = 0; i < listTva.size(); i++) {
					 		Det_Fact_ClientBean  bean=(Det_Fact_ClientBean) listTva.get(i);
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
			 
			 /*if( detailBean.getTaux_remise_alacaisse()!=null  &&  le_Ht_Reel.doubleValue() >0 ){
				 remise_ala_caisse  = ProcessNumber.Pourcentage(le_Ht_Reel, detailBean.getTaux_remise_alacaisse());
				 remise_ala_caisse  = ProcessFormatNbr.FormatDouble_ParameterChiffre(remise_ala_caisse);
			 }*/
			 //beanTotal.setVente_remise_alacaisse(remise_ala_caisse);
			 totalre_mise  = ProcessFormatNbr.FormatDouble_ParameterChiffre(totalre_mise,pattern);
			 beanTotal.setFacture_remise(totalre_mise); 
			 json.put("vente_remise",  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(totalre_mise,pattern));	
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Remise");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(totalre_mise, pattern));
			 list_total.put(element);
			
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Net.H.T");
			 element.put("td2","5");
			 Double ht_apres_remise =  ProcessNumber.SOUSTRACTION(le_Ht_Reel, totalre_mise)  ;
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(ht_apres_remise,pattern));
			 list_total.put(element);
			 beanTotal.setTotal_ht_fact(ProcessFormatNbr.FormatDouble_ParameterChiffre(ht_apres_remise,pattern));
			 
			
			 
			 
			 
			
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1"," Total TVA");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(total_leTva,pattern));
			 beanTotal.setTotal_tva_fact(ProcessFormatNbr.FormatDouble_ParameterChiffre(total_leTva,pattern));
			 list_total.put(element);
			 
			 
			 Double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Timbre");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(timbre,pattern));
			 list_total.put(element);
			 total_mnt_gen=ProcessNumber.addition(total_mnt_gen,timbre);
			 beanTotal.setMnt_timb_fisc(timbre);
		
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Total TTC");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(total_mnt_gen,rowBean.getDevise(),false, true) );
			 json.put("vente_mnt_total",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(total_mnt_gen,rowBean.getDevise(),false, true) );
			 beanTotal.setTotal_facture(ProcessFormatNbr.FormatDouble_ParameterChiffre(total_mnt_gen,pattern));
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
			 beanTotal.setNet_a_payer(ProcessFormatNbr.FormatDouble_ParameterChiffre(net_a_payer,pattern));
			 json.put("vente_mnt_net_a_payer",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(net_a_payer,pattern));
			 list_total.put(element);
			   
			 
			 json.put("list_total", list_total);
			 setObjectValueModel("list_total", list_total);
			 setObjectValueModel(BEAN_TOTAL_FACTURE_CLIENT, beanTotal);
			 
			 getResponse().setContentType(JSON_CONTENT_TYPE);      
			 getResponse().getWriter().write(json.toString());
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void doTraitmentTotal(Facture_clientBean detailBean ) throws Exception {
		try {
			BeanSession bs                =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 
			List <Det_Fact_ClientBean >List_detaille= new ArrayList<Det_Fact_ClientBean>();
			String pattern ="0.000";
			 HashMap  mapDevise= (HashMap) getObjectValueModel("map_devise");
			if(detailBean.getDevise() !=null  &&  mapDevise!=null) {
				   DeviseBean devise =(DeviseBean) mapDevise.get( String.valueOf(detailBean.getDevise().getDev_id()) );
				   detailBean.setDevise(devise);
			}
		 
		  
		     
	    	if(  ( detailBean.getDevise().getDev_id().intValue()==191  ||  detailBean.getDevise().getDev_id().intValue()==192 )  ){
					pattern ="0.00";
			} 
			
			Double getAvance_montant_vente  = detailBean.getAvance_montant_vente()==null?new Double(0):detailBean.getAvance_montant_vente();
			Double avance                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(getAvance_montant_vente,pattern);
			Double timbre                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(bs.getSociete().getMontant_timbre_fiscal(),pattern);
			 
			if(detailBean.getClient().getClt_exonorer().booleanValue()==true) {
				timbre= new Double(0);
			}
			if(bs.getFct_id().equals(Fn_Générer)  ||  bs.getFct_id().equals(Fn_Facturer)   ){
				 List_detaille=(List<Det_Fact_ClientBean>) getObjectValueModel(LIST_DATA_DET_FACT);
			}else{
				List_detaille=serviceFacture.doFetchDetaillefromServer(detailBean);
				getAvance_montant_vente  = detailBean.getAvance_montant_vente()==null?new Double(0):detailBean.getAvance_montant_vente();
				avance                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(getAvance_montant_vente,pattern);
			}
			
			setObjectValueModel(LIST_DATA_DET_FACT, List_detaille);
			setObjectValueModel(LIST_DATA_DET_FACT_CLONE, ProcessUtil.cloneList(List_detaille) );  
			
			
			Double totalre_mise          			  =  new Double(0);
			Double tot_ht_brute =new Double(0);
			Double tot_ht_net    =new Double(0);
			Double tot_tva=new Double(0);
			Double tot_qte=new Double(0);
			Double tot_qteBox=new Double(0);
			
			
			HashMap   map_des_Tva = new HashMap();
			for (int i = 0; i < List_detaille.size(); i++) {
				Det_Fact_ClientBean  beanLigne=List_detaille.get(i);
				
				 
				/*****************************************le cout ********************************************/
	    		Double getQuantiteX       = beanLigne.getQuantite()==null?new Double(0):beanLigne.getQuantite();
	    		Double getQuantiteBox       = beanLigne.getNbrBoxes()==null?new Double(0):beanLigne.getNbrBoxes();
	    		Double                 qte= ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX,pattern);
	    		/*Double  cout              = beanLigne.getTarif().getCout()==null?new Double(0):beanLigne.getTarif().getCout().getTarif_unit_article(,pattern);
	    		Double	Prixcout          = cout==null?new Double(0):cout;
	    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
	    		le_cout					  = ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout);*/
	    		
	    		/*****************************************  setInfo  ********************************************/
	    		
	    		/*****************************************  setInfo  ********************************************/
	    		Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(beanLigne.getTarif_unit_vente(),pattern);
	    		/*****************************************Prix Unit Brute reel********************************************/
	    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
	    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente_brute,pattern);
	    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
	    		tot_qte         =ProcessNumber.addition(tot_qte, qte);
	    		
	    		Double       qteBox= ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteBox,pattern);
	    		tot_qteBox      =ProcessNumber.addition(tot_qteBox, qteBox);
	    		
	    		
				tot_ht_brute=ProcessNumber.addition(tot_ht_brute, montant_ht_vente_brute);
	    		 
	    		/*******************************************Remise********************************************************/
				 
				Double taux_remiseligne     = beanLigne.getTaux_remise_ligne()==null?new Double(0):beanLigne.getTaux_remise_ligne();
				taux_remiseligne=ProcessFormatNbr.FormatDouble_ParameterChiffre(taux_remiseligne,pattern);
				beanLigne.setTaux_remise_ligne(taux_remiseligne);
				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_vente_reel(), taux_remiseligne);
				beanLigne.setMontant_remise_ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
				totalre_mise=ProcessNumber.addition(totalre_mise, montant_Remise_Ligne);
 
    		    /*******************************************montant_ht_vente *********************************************/   
    		    
    		    tot_ht_net=ProcessNumber.addition(tot_ht_net, beanLigne.getMontant_ht_vente());
	    		
	    		/*********************************************montant_tva_vente ******************************************/
	    		 
	    		tot_tva=ProcessNumber.addition(tot_tva, beanLigne.getMontant_tva_vente());
	    		
	    		/*********************************************montant_ttc_vente *******************************************/
	    		Double  montant_ttc_vente=ProcessNumber.addition(beanLigne.getMontant_ht_vente(), beanLigne.getMontant_tva_vente());
	    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
	    		
	    		/*********************************************montant_benefice *******************************************/
	    		/*if(le_cout.doubleValue()>0){
	    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
	    		montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice);
	    		beanLigne.setMontant_benefice(montant_benefice);
	    		marge_benefice_vente=ProcessNumber.addition(marge_benefice_vente, montant_benefice);*/
	    		//}
	    		/**********************************************************************************************************/
				 
				
				List  listTva = (List) map_des_Tva.get(beanLigne.getTvaBean().getTva_libelle());
				if(listTva==null)listTva= new ArrayList();
				listTva.add(beanLigne);
				map_des_Tva.put(beanLigne.getTvaBean().getTva_libelle(), listTva);
				
				/**********************************************************************************************************/
			}
			
			 Facture_clientBean    beanTotal = new Facture_clientBean();
			 beanTotal.setAvance_montant_vente(avance)  ; 
			 beanTotal.setTotnbrBox(tot_qteBox);
			 beanTotal.setTotal_quantite(String.valueOf(tot_qte));
			 //beanTotal.setMarge_benefice_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(marge_benefice_vente));
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
					 
					 if(map_des_Tva.get(beanTva.getTva_libelle())!=null){
						 List listTva  =(List) map_des_Tva.get(beanTva.getTva_libelle());
						 String  tva   = beanTva.getTva_libelle();
						  	
						 Double le_Ht_netLigne  = new Double(0);
					     Double leTva  = new Double(0);
					 	 for (int i = 0; i < listTva.size(); i++) {
					 		Det_Fact_ClientBean  bean=(Det_Fact_ClientBean) listTva.get(i);
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
			 
			 /*if( detailBean.getTaux_remise_alacaisse()!=null  &&  le_Ht_Reel.doubleValue() >0 ){
				 remise_ala_caisse  = ProcessNumber.Pourcentage(le_Ht_Reel, detailBean.getTaux_remise_alacaisse());
				 remise_ala_caisse  = ProcessFormatNbr.FormatDouble_ParameterChiffre(remise_ala_caisse);
			 }*/
			 //beanTotal.setVente_remise_alacaisse(remise_ala_caisse);
			 totalre_mise  = ProcessFormatNbr.FormatDouble_ParameterChiffre(totalre_mise,pattern);
			 beanTotal.setFacture_remise(totalre_mise); 
			 json.put("vente_remise",  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(totalre_mise,pattern));	
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Remise");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(totalre_mise, pattern));
			 list_total.put(element);
			
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Net.H.T");
			 element.put("td2","5");
			 Double ht_apres_remise =  ProcessNumber.SOUSTRACTION(le_Ht_Reel, totalre_mise)  ;
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(ht_apres_remise,pattern));
			 list_total.put(element);
			 beanTotal.setTotal_ht_fact(ProcessFormatNbr.FormatDouble_ParameterChiffre(ht_apres_remise,pattern));
			 
			
			 
			 
			 
			
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1"," Total TVA");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(total_leTva,pattern));
			 beanTotal.setTotal_tva_fact(ProcessFormatNbr.FormatDouble_ParameterChiffre(total_leTva,pattern));
			 list_total.put(element);
			 
			 
			 Double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Timbre");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(timbre,pattern));
			 list_total.put(element);
			 total_mnt_gen=ProcessNumber.addition(total_mnt_gen,timbre);
			 beanTotal.setMnt_timb_fisc(timbre);
		
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Total TTC");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(total_mnt_gen,detailBean.getDevise(),false, true) );
			 json.put("vente_mnt_total",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(total_mnt_gen,detailBean.getDevise(),false, true) );
			 beanTotal.setTotal_facture(ProcessFormatNbr.FormatDouble_ParameterChiffre(total_mnt_gen,pattern));
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
			 beanTotal.setNet_a_payer(ProcessFormatNbr.FormatDouble_ParameterChiffre(net_a_payer,pattern));
			 json.put("vente_mnt_net_a_payer",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(net_a_payer,pattern));
			 list_total.put(element);
			   
			 
			 json.put("list_total", list_total);
			 setObjectValueModel("list_total", list_total);
			 setObjectValueModel(BEAN_TOTAL_FACTURE_CLIENT, beanTotal);
		 
			
		} catch (Exception e) {
			throw e;
		}
 
		 
	}
	
	  
	public    ModelAndView doGet_Row_Bean() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		 
			if (bs.getFct_id().equals(Fn_Générer)){
				setObjectValueModel(MAP_FIELD_BEAN, ProcedureVenteTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_VENTE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_VENTE); 
				setObjectValueModel(NAME_GRID_G, NAME_GRID_VENTE);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_VENTE");
				setObjectValueModel(SEARCH_BEAN, new ProcedureVenteBean());
				
				 String  theIdRow=getRequest().getParameter("theIdRow");
				 int index = Integer.parseInt(theIdRow);
				 List listPagin       = (List)(List) getObjectValueModel("IndeXo"+LIST_DATA_VENTE);
				 ProcedureVenteBean rowBean                = (ProcedureVenteBean) listPagin.get(index);
				 setObjectValueModel(FORM_BEAN, rowBean);
				 List List_detaille_vente  =  serviceProcedureVente.doFetch_detDatafromServer(rowBean);
				 HashMap  map_vente   = ProcessUtil.getHashMap_code_bean(List_detaille_vente, "pk.fkcode_barre.pk.code_barre");
				 setObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_VENTE         ,  List_detaille_vente);
				  
				 
			} else{
				
				Facture_clientBean rowBean = (Facture_clientBean) getIndexFromDataGrid_v1(LIST_DATA);
				List list_detaille=serviceFacture.doFetchDetaillefromServer(rowBean);
				//List list_detaille_mvt=serviceFacture.doFetchDetailleMvt_fact_vente(rowBean);
				//setObjectValueModel(LIST_DATA_DET_FACT_MVT_VENTE, list_detaille_mvt);
				setObjectValueModel(LIST_DATA_DET_FACT, list_detaille);
				setObjectValueModel(LIST_DATA_DET_FACT_CLONE, ProcessUtil.cloneList(list_detaille) );  
				if( bs.getSoc_id().equals("6") ) {
					rowBean.setBtnPrintCertificat("display:block;");
				}else {
					rowBean.setBtnPrintCertificat("display:none;");
				}
				setObjectValueModel(FORM_BEAN, rowBean);
			} 
			 
			 
			if (bs.getFct_id().equals(Fn_Consulter))
				return getViewConsult_Pdf_ex(FORM_VIEW);
			
			
			if (bs.getFct_id().equals(Fn_Générer))
				return getViewConsult_Vente(FORM_VIEW_VENTE);

			if (bs.getFct_id().equals(Fn_Annuler))
				return getViewDelete(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Confirmer))
				return getViewConfirm(FORM_VIEW);
			
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax(FILTER_VIEW);
		}
		
		return getViewFilterAjax(FILTER_VIEW);

	}
	
	
	
	public ModelAndView doAddData(Facture_clientBean detailBean) throws Exception {
		
		boolean insert=false;
	     try {
	            serviceFacture.doCreateRowData(detailBean);
	            throwNewException("ins01");
	          } catch (Exception e) {
	        	  if(e.getMessage().equals("ins01")) {
	        		  insert=true;
	        		  String numios= getRequest().getParameter("numios");
		        	    if(  numios!=null &&  !numios.equals("null") ) {
		        	    	Facture_clientBean beanSave= (Facture_clientBean) getObjectValueModel(FORM_BEAN );
		        	        NumSeqReserve numSeqReserve  = new NumSeqReserve();
		  		    	    numSeqReserve.setCode_num("fact_clt_id");
		  		    	    numSeqReserve.setFk_etab_Bean(beanSave.getEtablissment());
		  		    	    numSeqReserve.setNumero(numios);
		        	        daoNumSequentiel.doDeleteNumSequentielReseve(numSeqReserve); 
		        	    }
	        	  }
	            displayException(e);
	          }
	          
	          if(insert)
	          return getViewAdd_after_save(FORM_VIEW);
	          else
	           return getViewPreparerAdd(FORM_VIEW);  
		}
	
	public    ModelAndView doPrintPDF_detaille() throws Exception   {
		 
		List   lisData=  (List) getObjectValueModel(LIST_DATA_DET_FACT) ;
		Facture_clientBean    denBean= (Facture_clientBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
	    FileOutputStream fs = new FileOutputStream(file);
	    GeneratePdf  genpdf= new GeneratePdf();
		try {
			
		
			Document document = new Document(PageSize.A4, 5, 5, 5, 25);
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	        JSONObject doc = genpdf.doWriteHeaderDocument_PDF(document,fs,bSession);
	        
	        doWriteEntete(document,denBean); 
	        doWrite_Header_ContentTable(document,96,MapfieldBean_detaille);
	        doWrite_Data_Table (denBean,lisData,document,96,MapfieldBean_detaille,doc);
	        doWrite_Tva_Total_Piece(lisData,document,doc);   
	        
	        
	        Facture_clientBean  reBean= (Facture_clientBean) getObjectValueModel(BEAN_TOTAL_FACTURE_CLIENT);
	        String affich_mont =ProcessFormatNbr.convertNumberToLetterEURO( reBean.getTotal_facture() );
	        
	        PdfPTable tabletitle = new PdfPTable(100);
		    PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_normal));
	        cell.setColspan(100);
	        cell.setFixedHeight(10f);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Arrêtée la présente facture à la somme de :",GeneratePdf.Normal_10_times_roman));
	        cell.setColspan(100);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase(affich_mont,GeneratePdf.Bold_10_times_roman));
	        cell.setColspan(100);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_normal));
	        cell.setColspan(100);
	        cell.setFixedHeight(10f);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase("Virement bancaire :",GeneratePdf.Normal_10_times_roman));
	        cell.setColspan(25);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase(denBean.getCpt_bank().getCptbanribrs()+"   /   "+denBean.getCpt_bank().getCptbanribrib(),GeneratePdf.Bold_10_times_roman));
	        cell.setColspan(75);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        
	        document.add(tabletitle);
	        
	       
	        document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write(LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
			setObjectValueModel(LIST_DATA_DET_FACT, new ArrayList<>() );
		} catch (Exception e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

     }
	 
	
	
	public ModelAndView doPrintFactureModelKobbi() throws Exception {
		PrintPdfModeleKobbi pModeleKobbi = new PrintPdfModeleKobbi();
		try {
			pModeleKobbi.printfacture();
		} catch (Exception e) {
			displayException(e);
		}
		return null;
	}
	
	public ModelAndView doPrintFactureSPL() throws Exception {
		PrintPdfModelSPL print = new PrintPdfModelSPL();
		try {
			print.doPrintPDF_detaille();
		} catch (Exception e) {
			displayException(e);
		}
		return null;
	}
	 
	
	public ModelAndView doPrintExportManchaKobbi() throws Exception {
		PrintPdfModeleKobbi pModeleKobbi = new PrintPdfModeleKobbi();
		try {
			pModeleKobbi.printExportManchafacture();
		} catch (Exception e) {
			displayException(e);
		}
		return null;
	}
	
	
	
	 public   void doWriteEntete(Document document, Facture_clientBean denBean) throws Exception {
		PdfPTable tableTopHeader = new PdfPTable(100);
		tableTopHeader.setWidthPercentage(96);
		 
		    
		PdfPCell cell = new PdfPCell(new Phrase(" Facture N° ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(13);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(" : "+denBean.getFact_clt_id(),GeneratePdf.FONT_12_normal));
	    cell.setColspan(27);
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
	    
	    
	    cell = new PdfPCell(new Phrase(" : "+denBean.getClient().getClt_lib() ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(42);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.TOP+cell.RIGHT);
	    tableTopHeader.addCell(cell);
	    
	     
	    
	    
	    cell = new PdfPCell(new Phrase(" Date ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(13);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(denBean.getFact_date_edition()!=null?denBean.getFact_date_edition():denBean.getFact_date()) ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(27);
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
	    
	    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(100);
	    cell.setFixedHeight(10f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    document.add(tableTopHeader);
			
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
    	     
    	      
    	     
    	     
    	     
    	    PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":titrehead  ,GeneratePdf.SMALLBOLD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setPaddingBottom(PaddingBottom);
		    cell.setBackgroundColor(GeneratePdf.colorHeader);
		    cell.setBorderWidth(1f);
		    table.addCell(cell);
		}
      document.add(table);
}
	
	
  public   void doWrite_Data_Table(Facture_clientBean    denBean, List lisData, Document document,int poucentage,String[][] mapFieldBean,  JSONObject doc) throws Exception, SecurityException {
	  
	    PdfPTable table = new PdfPTable(mapFieldBean.length);
		String pattern ="0.000";
    	if( denBean.getDevise()!=null  && ( denBean.getDevise().getDev_id().intValue()==191  ||  denBean.getDevise().getDev_id().intValue()==192   ) ){
				pattern ="0.00";
		} 
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
				        if(j==mapFieldBean.length-3  ){
				        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.REDFONT));
					        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					        cell.setPaddingBottom(5);
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        table.addCell(cell);
					        continue;
				        }
				        if(j==mapFieldBean.length-1 || j==mapFieldBean.length-2){
				        	Double elm=Double.valueOf(String.valueOf(obj));
				        	cell = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(elm,pattern),GeneratePdf.REDFONT));
					        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					        cell.setPaddingBottom(5);
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        
				        }else{
				        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.REDFONT));
					        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					        cell.setPaddingBottom(5);
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
         int resul=    doc.getInt("espaceTotalDesous") - toolha;
         float toul_contenu_tab=Float.valueOf(String.valueOf(resul));
         /********************************************************************************************************/
          
         for(int j = 0; j < mapFieldBean.length; j++){
		            PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.REDFONT));
			        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        cell.setPaddingBottom(5);
			        cell.setFixedHeight(toul_contenu_tab);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        cell.setBorder(cell.LEFT+cell.RIGHT);
		            table.addCell(cell);
		    }   
		  document.add(table);
	}
	public void doWriteEntete(Document document, CommandeclientBean   denBean) throws Exception {
	PdfPTable tableTopHeader = new PdfPTable(100);
	tableTopHeader.setWidthPercentage(96);
	 
	    
	PdfPCell cell = new PdfPCell(new Phrase("Facture Pro Forma N°",GeneratePdf.FONT_12_bold));
    cell.setColspan(24);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(" : "+denBean.getCmd_id(),GeneratePdf.FONT_12_normal));
    cell.setColspan(23);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase(" Client ",GeneratePdf.FONT_12_bold));
    cell.setColspan(13);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.LEFT);
    tableTopHeader.addCell(cell);
    
    String getClient=denBean.getCmd_libelle().equals("")?denBean.getClient().getClt_lib():denBean.getCmd_libelle();
    cell = new PdfPCell(new Phrase(" : "+getClient ,GeneratePdf.FONT_12_normal));
    cell.setColspan(40);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    
     
    
    
    cell = new PdfPCell(new Phrase("Date",GeneratePdf.FONT_12_bold));
    cell.setColspan(24);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(denBean.getCmd_date()) ,GeneratePdf.FONT_12_normal));
    cell.setColspan(23);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase(" Adresse ",GeneratePdf.FONT_12_bold));
    cell.setColspan(13);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_adr() ,GeneratePdf.FONT_12_normal));
    cell.setColspan(40);
    cell.setFixedHeight(40f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase("   ",GeneratePdf.FONT_12_bold));
    cell.setColspan(47);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
     
    
    
    
    cell = new PdfPCell(new Phrase(" Matricule fiscal :",GeneratePdf.FONT_12_bold));
    cell.setColspan(18);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT+cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( "   "+denBean.getClient().getClt_obs() ,GeneratePdf.FONT_12_normal));
    cell.setColspan(35);
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
	
	
	  public void doWrite_Tva_Total_Piece(List   lisData,Document document , JSONObject doc) throws Exception {
		
		try {
			 
           
           PdfPTable table_des_tva = new PdfPTable(100);
           table_des_tva.setWidthPercentage(96);
           
           
           
           
           /******************************************* Entete  tableau des tva *************************************/
           PdfPCell cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
           cell.setColspan(100);
           cell.setFixedHeight(10f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Taux",GeneratePdf.FONT_12_bold));
           cell.setColspan(10);
          // cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Base",GeneratePdf.FONT_12_bold));
           cell.setColspan(21);
           //cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Montant",GeneratePdf.FONT_12_bold));
           cell.setColspan(23);
           //cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
           cell.setColspan(49);
           //cell.setFixedHeight(20f);
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
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[0],GeneratePdf.FONT_12_bold));
	           cell.setColspan(21);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[1],GeneratePdf.FONT_12_bold));
	           cell.setColspan(23);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
	           cell.setColspan(49);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	           cell.setBorder(cell.NO_BORDER);
	           table_des_tva.addCell(cell);
		   } 
           
           JSONArray  list_total  =  (JSONArray) getObjectValueModel("list_total");
           
           
           PdfPTable table_total = new PdfPTable(100);
           table_total.setWidthPercentage(96);
           
           /****************************************************************************************************************/
           
            
           int  init=13;
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
                cell.setColspan(55);
                cell.setFixedHeight(17f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.NO_BORDER);
                table_total.addCell(cell);
                
                
                cell = new PdfPCell(new Phrase(titre,GeneratePdf.FONT_12_bold));
                cell.setColspan(18);
                cell.setFixedHeight(17f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
                table_total.addCell(cell);
                
                cell = new PdfPCell(new Phrase(value ,GeneratePdf.FONT_12_normal));
                cell.setColspan(27);
                cell.setFixedHeight(17f);
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
		public ModelAndView doCalculerTotalGrid( Facture_clientBean detailBean ) throws Exception {
			
			try {
				Double totGridFctClient= (Double) getObjectValueModel("totGridFctClient");
				Double totRetenueFctClient= (Double) getObjectValueModel("totRetenueFctClient");
				
				 JSONObject  json = new JSONObject();
				 json.put("total_facture",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totGridFctClient));
				 json.put("client.clt_lib","Total");
				 json.put("retenuSource",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totRetenueFctClient));
				 setObjectValueModel("totalList", json);
				 getResponse().setContentType(JSON_CONTENT_TYPE);      
				 getResponse().getWriter().write(json.toString());
 
			} catch (Exception e) {
				getResponse().setContentType(HTML_CONTENT_TYPE);
				getResponse().getWriter().print(e.getMessage());
			}
			return null;
		}
	
	public ModelAndView doUpdateData(Facture_clientBean beanUpBean) {	 
		 	try {
		 serviceFacture.doUpdateRowData(beanUpBean); 
		 remove_row_from_list(LIST_DATA); 
		 throwNewException("ins01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(Facture_clientBean beanDelBean) throws Throwable {
	    try {
	     serviceFacture.doDeleteRowData(beanDelBean);
	     remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       if(e.getMessage().equals("sup01")) {
	    	   Facture_clientBean   beandelfact = (Facture_clientBean) getObjectValueModel(FORM_BEAN);
	    	   NumSeqReserve numSeqReserve  = new NumSeqReserve();
	    	   numSeqReserve.setCode_num("fact_clt_id");
	    	   numSeqReserve.setFk_etab_Bean(beandelfact.getEtablissment());
	    	   numSeqReserve.setNumero(beandelfact.getFact_clt_id());
	    	   numSeqReserve.setDate_time_cre(new Date());
	    	   daoNumSequentiel.doInsertNumSequentielReseve(numSeqReserve); 
    	   }
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
