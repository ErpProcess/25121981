package ERP.Process.Commerciale.Achat.ComplementAchat.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import ERP.Process.Commerciale.Achat.ComplementAchat.model.ComplementAchatBean;
import ERP.Process.Commerciale.Achat.ComplementAchat.model.Det_complment_achatBean;
import ERP.Process.Commerciale.Achat.ComplementAchat.service.ComplementAchatService;
import ERP.Process.Commerciale.Achat.ComplementAchat.template.ComplementAchatTemplate;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.service.Reception_achatService;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
 
 
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Demande_Achat.template.Demande_AchatTemplate;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.DepotStockage.template.DepotStockageTemplate;
 
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel.WriteExcel;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

public class ComplementAchatActionManager extends ComplementAchatTemplate {
	 
	 
 
	private static final long serialVersionUID = -3742682478235672925L;
	
	 public   Code_barreService serviceCode_barre;
		@Autowired
		public void setServiceCode_barre(Code_barreService serviceCode_barre) {
			this.serviceCode_barre = serviceCode_barre;
		}
		private DocumentLotService serviceDocumentLot;

		@Autowired
		public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
			this.serviceDocumentLot = serviceDocumentLot;
		}	
		
	private ComplementAchatService serviceComplementAchat;

	@Autowired
	public void setServiceComplementAchat(
			ComplementAchatService serviceComplementAchat) {
		this.serviceComplementAchat = serviceComplementAchat;
	}
	
	private Reception_achatService serviceReception_achat;
	@Autowired
	public void setServiceReception_achat(
			Reception_achatService serviceReception_achat) {
		this.serviceReception_achat = serviceReception_achat;
	}


	@Autowired
	FournisseurService     serviceFournisseur;
	
	private      DepotStockageService       serviceDepotStockage;
	@Autowired
	public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
		this.serviceDepotStockage = serviceDepotStockage;
	}
	
	public    TarificationDAO daoTarification;
	@Autowired
	public void setDaoTarification(TarificationDAO daoTarification) {
		this.daoTarification = daoTarification;
	}
	
	public    ModelAndView doInitServletAction() {

		
		try {
			
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			setObjectValueModel(LIST_FOURNISSEUR_RECEP_ACHAT, serviceFournisseur.dofetchDatafromServer(FournisseurBean.class.newInstance()));
			setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			
			
			 
			
			
			
		 
			 
			
			 
			
			 HashMap   mapresul=null;//daoTarification.doFindListTarification_ACHAT_DateMax(TarificationBean.class.newInstance());
			 List list_Tarification=  (List) (mapresul.get("list1")== null ? new ArrayList(): mapresul.get("list1"));  
			 List list_detaille_recep=(List) (mapresul.get("list2")== null ? new ArrayList(): mapresul.get("list2"));  
			 setObjectValueModel(LIST_ARTICLE_RECP_ACHAT        , list_detaille_recep);
				setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_ORIGINE, ProcessUtil.cloneList(list_detaille_recep) );
				setObjectValueModel(LIST_ARTICLE_RECP_ACHAT_GRID   , ProcessUtil.cloneList(list_detaille_recep));
				setObjectValueModel(LIST_EDITABLE_COMP_ACHAT       , new ArrayList<Det_complment_achatBean>());
			
			
			
			HashMap mapTarification=ProcessUtil.getHashMap_code_bean(list_Tarification , "pk.fkCode_barre.pk.code_barre");
			setObjectValueModel(MAP_TARIFICATION, mapTarification);
			
			
			doLoadingLibelleOtherSModule(Reception_achatTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(Demande_AchatTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(DepotStockageTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(ComplementAchatTemplate.ID_SOUS_MODULE);
			 
			
			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
				setObjectValueModel("LIST_VIEW", LIST_VIEW_ACHAT);
				setObjectValueModel("nameList" , LIST_DATA_ACHAT);
				setObjectValueModel("nameGrid",  NAME_GRID_ACHAT);
				
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_ACHAT");
				setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Reception_achatTemplate.ID_SOUS_MODULE));
				setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Reception_achatTemplate.ID_SOUS_MODULE)) ;
				return getViewFilter_achat(FILTER_VIEW_ACHAT);
				
			} else {
				setObjectValueModel("LIST_VIEW", LIST_VIEW);
				setObjectValueModel("nameList" , LIST_DATA);
				setObjectValueModel("nameGrid",  NAME_GRID);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_AJAX_FETCH");
				setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+ComplementAchatTemplate.ID_SOUS_MODULE));
				setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+ComplementAchatTemplate.ID_SOUS_MODULE)) ;
				
				return getViewFilterAjax(FILTER_VIEW);

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}

	@SuppressWarnings("unchecked") 
	public ModelAndView doFetch_Achat_Data(ComplementAchatBean   searchBean)throws Throwable {
			try {
				 
				
				searchBean.getAchat().setCondition_etat_achat("   AND  bean.modeBean.fct_id  in ('"+Fn_Conserver+"' )  ");
				List listDataSrv_achat  =  serviceReception_achat.doFetchDatafromServer(searchBean.getAchat());
				setObjectValueModel(SEARCH_BEAN, searchBean);
				setObjectValueModel(NAME_LIST_G  , LIST_DATA_ACHAT);
				setObjectValueModel(NAME_GRID_G , NAME_GRID_ACHAT);
				setObjectValueModel(MAP_FIELD_BEAN , Reception_achatTemplate.MapfieldBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv_achat);
				
	 		} catch (Exception e) {
	 			getResponse().setStatus(500);
	 			getResponse().setContentType(HTML_CONTENT_TYPE);
	 			getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	 
 

	public static ModelAndView doActualiser_GRID( ) throws Exception{
		
		try {
			
			 
			List listOData=(List) getObjectValueModel(LIST_EDITABLE_COMP_ACHAT);
			JsonObject data = new JsonObject();
		    BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		  
			for (int i = 0; i < listOData.size(); i++) {
				Det_complment_achatBean     newBean= (Det_complment_achatBean) listOData.get(i);
				 
		    	 
				
				
				    data.addProperty("erreur"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),"");
				  
		    	 
		    		if( newBean.getQuantite_ajouter() == null  ) continue;
		    		if( newBean.getQuantite_ajouter() < 0 ) {
		    			newBean.setQuantite_ajouter(new Double(0));
		    		
		    			String err="Erreur . quantité(s) inférieur a zéro ";
		    			data.addProperty("erreur"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),err);
		    			data.addProperty("Qte"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),"0");
		    			data.addProperty(newBean.getPk().getFkCode_barre().getPk().getCode_barre(),"0");
		    			break;
		    			
		    		}
		    		
		    	 
		      
		    			 
		    		double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getPrix_unit_achat());
		    		double qte=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getQuantite_ajouter());
		    		 
		    		
		    	     double qte_restantedddddd=0;
		    		 if(bs.getFct_id().equals(Fn_Modifier)){
		    			   qte_restantedddddd=ProcessNumber.addition(newBean.getQuantite_calcul_for_modif(), qte) ;
		 	    		    newBean.setQuantite(qte_restantedddddd);
		    		 }else{
		    			  qte_restantedddddd=ProcessNumber.addition(newBean.getQuantite_calcul(), qte) ;
		 	    		  newBean.setQuantite(qte_restantedddddd);
		    		 }
		    		
		    		
		    		
		    		double montant_ht_achat=ProcessNumber.PRODUIT(priUnitachat, qte);
		    		newBean.setMontant_ht_achat(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat));
		    		
		    		Double montant_tva_achat=ProcessNumber.Pourcentage(montant_ht_achat, newBean.getTvaBean().getTva_value());
	    		           montant_tva_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_achat);
	    		           newBean.setMontant_tva_achat(montant_tva_achat);
		    		 Double number=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat);          
		    		 String elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(number)  ;     
		    		 
		    		 
		    		 data.addProperty(newBean.getPk().getFkCode_barre().getPk().getCode_barre(),elm);
		    		 data.addProperty("Res"+newBean.getPk().getFkCode_barre().getPk().getCode_barre(),String.valueOf(qte_restantedddddd));
	    				         
		    	 
		     	 
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

	public static  ModelAndView doPrintPDF() {
		 
		
		
		List   lisData                         =    null; 
		String [][]    mapFieldBean            =    null; 
		
		GeneratePdf  genpdf= new GeneratePdf();
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
				    lisData                         = (List) getObjectValueModel(LIST_DATA_ACHAT) ;
				    mapFieldBean            =  Reception_achatTemplate.MapfieldBean;
				    setObjectValueModel(NAME_LIST_G,LIST_DATA_ACHAT);
			}else{
				    lisData                         =   (List) getObjectValueModel(LIST_DATA );
				    mapFieldBean            =   ComplementAchatTemplate.MapfieldBean;
				    setObjectValueModel(NAME_LIST_G,LIST_DATA);
			}
			
			genpdf.createGenericPdfDocument(lisData,mapFieldBean);
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write((String) getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
		} catch (Throwable e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

	}


	public static  ModelAndView doExportXls() {
		try {

			List       lisData         =  null;
			String [][]    mapFieldBean    =null;
			
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
				    lisData                         = (List) getObjectValueModel(LIST_DATA_ACHAT) ;
				    mapFieldBean            =  Reception_achatTemplate.MapfieldBean;
				    setObjectValueModel(NAME_LIST_G,LIST_DATA_ACHAT);
			}else{
				    lisData                         =   (List) getObjectValueModel(LIST_DATA );
				    mapFieldBean            =   ComplementAchatTemplate.MapfieldBean;
				    setObjectValueModel(NAME_LIST_G,LIST_DATA);
			}
			
			WriteExcel  dbexp= new WriteExcel();
			dbexp.doExportExcel(lisData,mapFieldBean);
		} catch (Exception e) {
			displayException(e);
		}
		return null;

	}


	@SuppressWarnings("unchecked")
	public ModelAndView doCalculer_Total() throws Exception {
		
		try {
			ComplementAchatBean  rowBean = (ComplementAchatBean) getObjectValueModel(FORM_BEAN);
			 
			List <Det_complment_achatBean > List_detail_complement= new ArrayList   (rowBean.getList_complment());
			 
			 
			setObjectValueModel(LIST_EDITABLE_COMP_ACHAT  , List_detail_complement);
			
			double tot_ht=0;
			double tot_tva=0;
			Double tot_qte=new Double(0);
			
			for (int i = 0; i < List_detail_complement.size(); i++) {
				Det_complment_achatBean  bean=List_detail_complement.get(i);
				tot_ht=ProcessNumber.addition(tot_ht, bean.getMontant_ht_achat());
				tot_tva=ProcessNumber.addition(tot_tva, bean.getMontant_tva_achat());
				tot_qte=ProcessNumber.addition(tot_qte, bean.getQuantite_ajouter());
			}
			double total_mnt_gen=ProcessNumber.addition(tot_ht, tot_tva); 
			Det_complment_achatBean  reBean= new Det_complment_achatBean();
			reBean.setTotal_mnt_ht(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tot_ht));
			reBean.setTotal_mnt_tva(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(tot_tva));
			reBean.setTotal_mnt_gen(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mnt_gen));
			reBean.setTotal_quantite(ProcessFormatNbr.Convert_using_Double_toString(tot_qte));
			setObjectValueModel(BEAN_TOTAL, reBean);
			
			JsonObject data = new JsonObject();
			data.addProperty("UU",reBean.getTotal_quantite());
		    data.addProperty("k", reBean.getTotal_mnt_ht());
		    data.addProperty("o", reBean.getTotal_mnt_tva());
		    data.addProperty("p", reBean.getTotal_mnt_gen());
		    getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(data.toString());
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}



	public    ModelAndView doSelectRow() {

		try {
			removeObjectModel(FORM_BEAN);
			 
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			
			
			if (bs.getFct_id().equals(Fn_Nouveau) || bs.getFct_id().equals(Fn_Créer) ){
				
				setObjectValueModel("LIST_VIEW", LIST_VIEW_ACHAT);
				setObjectValueModel("nameList" , LIST_DATA_ACHAT);
				setObjectValueModel("nameGrid",  NAME_GRID_ACHAT);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_ACHAT");
				
				Reception_achatBean rowBean = (Reception_achatBean) getIndexFromDataGrid_v1(LIST_DATA_ACHAT);
				ComplementAchatBean rBean = new ComplementAchatBean();
				rBean.setAchat(rowBean);
				rBean.setComplet_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
				setObjectValueModel(FORM_BEAN, rBean);
				setObjectValueModel(ORIGINAL_FORM_BEAN,ProcessUtil.cloneObject(rBean));
				 
				 List list_detail=serviceReception_achat.doFetchDeatil_reception_fromServer(rowBean);
				 ComplementAchatBean  comBean = new ComplementAchatBean();
				 comBean.getAchat().setAchat_id(rowBean.getAchat_id()) ;
				 List <ComplementAchatBean>list_Data_complemnt=serviceComplementAchat.doFetchDatafromServer(comBean);
				 
				 HashMap  map_complment_achat= new  HashMap();
				 if(list_Data_complemnt!=null &&  list_Data_complemnt.size()>0){
					 for(ComplementAchatBean bean_re:list_Data_complemnt){
						 
						 if(!String.valueOf(bean_re.getModeBean().getFct_id()).equals(Fn_Conserver)){
							throwNewException("*** il existe encore un complément non appliquer au stock .") ;
						 }
						 
						 List list_detail_re_article=new ArrayList(bean_re.getList_complment());
						 
						 for (int k = 0; k < list_detail_re_article.size(); k++) {
							 Det_complment_achatBean beantrie=(Det_complment_achatBean) list_detail_re_article.get(k);
								String key=    beantrie.getPk().getFkCode_barre().getPk().getCode_barre() ;
								List lis_el=(List) map_complment_achat.get(key);
								if(lis_el==null)lis_el= new ArrayList();
								lis_el.add(beantrie);
								map_complment_achat.put(key, lis_el);
							}
						 
						 
							
							
					 }
					 
				 }
				
				 
				 
				 //rowBean.setList_detail(   new HashSet<Det_reception_achatBean>(list_detail)  ) ;	 
				 List <Det_reception_achatBean> Listdetail_achat= new ArrayList( );
				
				List <Det_complment_achatBean > List_detail_cmplment= new ArrayList();
				
				for (Det_reception_achatBean bean_Reception:Listdetail_achat) {
					Det_complment_achatBean  bean_complement = new Det_complment_achatBean();
					bean_complement.getPk().setFkCode_barre(bean_Reception.getPk().getFkCode_barre());
					bean_complement.setQuantite_initiale(bean_Reception.getQuantite());
					
					
					List list_article=(List) map_complment_achat.get(bean_Reception.getPk().getFkCode_barre().getPk().getCode_barre());
					if(list_article==null){
					bean_complement.setQuantite(bean_Reception.getQuantite());
					bean_complement.setQuantite_calcul(bean_Reception.getQuantite());
					
					}else{
						double sss=0;
						for (int i = 0; i < list_article.size(); i++) {
							Det_complment_achatBean sqqsdqsd= (Det_complment_achatBean) list_article.get(i);
							sss=ProcessNumber.addition(sss, sqqsdqsd.getQuantite_ajouter());
						}
						double reswqdsq=ProcessNumber.addition(bean_Reception.getQuantite(), sss);
						bean_complement.setQuantite(reswqdsq);
						bean_complement.setQuantite_calcul(reswqdsq);
					}
					/*bean_complement.setUnitBean(bean_Reception.getUnitBean());
					 
					//bean_complement.setLot(bean_Reception.getPk().getLot());
					bean_complement.setPrix_unit_achat(bean_Reception.getPrix_unit_achat());
					bean_complement.setPrix_unit_vente(bean_Reception.getPrix_unit_vente());
					bean_complement.setTvaBean(bean_Reception.getTvaBean());
					bean_complement.setTyp_trfBean(bean_Reception.getTyp_trfBean());*/
					List_detail_cmplment.add(bean_complement);
					
				}
				
				
				setObjectValueModel(LIST_EDITABLE_COMP_ACHAT         ,  List_detail_cmplment);
				setObjectValueModel(LIST_EDITABLE_COMP_ACHAT_ORG         , ProcessUtil.cloneList(List_detail_cmplment) );
				
				
				 
				return getViewAddComplement(FORM_VIEW_EDIT);
				
				
			}else{
				
				setObjectValueModel("LIST_VIEW", LIST_VIEW);
				setObjectValueModel("nameList" , LIST_DATA);
				setObjectValueModel("nameGrid",  NAME_GRID);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_AJAX_FETCH");
				setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+ComplementAchatTemplate.ID_SOUS_MODULE));
				setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+ComplementAchatTemplate.ID_SOUS_MODULE)) ;
				
				ComplementAchatBean   rowBean = (ComplementAchatBean) getIndexFromDataGrid_v1(LIST_DATA);
				List <Det_complment_achatBean > List_detail_complement= new ArrayList   (rowBean.getList_complment());
				setObjectValueModel(LIST_EDITABLE_COMP_ACHAT              ,  List_detail_complement);
				setObjectValueModel(LIST_EDITABLE_COMP_ACHAT_ORG          , ProcessUtil.cloneList(List_detail_complement) );
				
				for (Det_complment_achatBean bean_complement:List_detail_complement) {
					double sss=ProcessNumber.addition(bean_complement.getQuantite(), bean_complement.getQuantite_ajouter());
					bean_complement.setQuantite_calcul_for_modif(sss);
				}
				setObjectValueModel(FORM_BEAN, rowBean);
				setObjectValueModel(ORIGINAL_FORM_BEAN,ProcessUtil.cloneObject(rowBean));
			}
			 
			if (bs.getFct_id().equals(Fn_Consulter))
				return getViewConsult(FORM_VIEW);
			
			
			if (bs.getFct_id().equals(Fn_Annuler))
				return getViewAnnuler(FORM_VIEW);
			
			
		
			
			
			if (bs.getFct_id().equals(Fn_Conserver))
				return getViewConservData(FORM_VIEW);
			
			
			if (bs.getFct_id().equals(Fn_Modifier))
				return getViewUpdate(FORM_VIEW_EDIT);
			
			
			if (bs.getFct_id().equals(Fn_Supprimer))
				return getViewDelete(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_reception))
				return getViewReception(FORM_VIEW);
			
			
			if (bs.getFct_id().equals(Fn_Transférer)    )
				return getViewTransfer(FORM_VIEW);
			
			
			 
			
		} catch (Exception e) {
			
			displayException(e);
			
			if(e.getMessage().startsWith("***")){
				
				setObjectValueModel(MAP_FIELD_BEAN, Reception_achatTemplate.MapfieldBean);
	  			setObjectValueModel("LIST_VIEW", LIST_VIEW_ACHAT);
	  			setObjectValueModel("nameList" , LIST_DATA_ACHAT);
	  			setObjectValueModel("nameGrid",  NAME_GRID_ACHAT);
	  			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_ACHAT");
	  			setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Reception_achatTemplate.ID_SOUS_MODULE));
	  			setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Reception_achatTemplate.ID_SOUS_MODULE)) ;
	  			
			return getViewFilter_achat(FILTER_VIEW_ACHAT);
			
			}else{
				
				setObjectValueModel("LIST_VIEW", LIST_VIEW);
				setObjectValueModel("nameList" , LIST_DATA);
				setObjectValueModel("nameGrid",  NAME_GRID);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_AJAX_FETCH");
				setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+ComplementAchatTemplate.ID_SOUS_MODULE));
				setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+ComplementAchatTemplate.ID_SOUS_MODULE)) ;
				
			return getViewFilterAjax(FILTER_VIEW);
			
			}
				
		}
		return getHome();

	}


	public static  ModelAndView doRetourToList_Achat() {
		try {
			setObjectValueModel("LIST_VIEW", LIST_VIEW_ACHAT);
			setObjectValueModel("nameList" , LIST_DATA_ACHAT);
			setObjectValueModel("nameGrid",  NAME_GRID_ACHAT);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_ACHAT");
			setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Reception_achatTemplate.ID_SOUS_MODULE));
			setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Reception_achatTemplate.ID_SOUS_MODULE)) ;
		} catch (Exception e) {
			displayException(e);
		}
		return getViewFilter_achat(  FILTER_VIEW_ACHAT );
	}
	 
public   ModelAndView doAdd_row_EditableDataTableAjax( Det_complment_achatBean  detailBean ) throws Exception{
		  
		
		
		try {
			List listOfmyData  =(List) getObjectValueModel(LIST_EDITABLE_COMP_ACHAT);
			
			  
			 
			List list_article_dem_achatOrigine =(List) getObjectValueModel(LIST_ARTICLE_RECP_ACHAT_ORIGINE);
			
			HashMap  mapdA=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkCode_barre.pk.code_barre");
			
			if(mapdA.get(detailBean.getPk().getFkCode_barre().getPk().getCode_barre())!=null)  throw new Exception("Existe déjà");
			
			
			HashMap  MAP_ARTICLE=ProcessUtil.getHashMap_code_bean(list_article_dem_achatOrigine, "pk.code_barre");
			Code_barreBean  cBeanf=(Code_barreBean) MAP_ARTICLE.get(detailBean.getPk().getFkCode_barre().getPk().getCode_barre());
			Det_complment_achatBean newBean= detailBean;
			newBean.getPk().setFkCode_barre(cBeanf);
			newBean.setUnitBean(cBeanf.getPk().getAr_bean().getUnitBean());
			
		 
			
			HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION);
			TarificationBean  ss  =(TarificationBean) mapTarification.get(cBeanf.getPk().getCode_barre());
	    	if(ss!=null){
	    		/*newBean.setDate_trf(ss.getPk().getDate_trf());
	    		newBean.setTvaBean(ss.getPk().getTvaBean());
	    		newBean.setTyp_trfBean(ss.getPk().getTyp_trfBean());
	    		newBean.setPrix_unit_achat(ss.getPk().getPrix_unit_achat());
	    		newBean.setPrix_unit_vente(ss.getPk().getPrix_unit_vente());
	    		Double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getPk().getPrix_unit_achat());
	    		Double qte= newBean.getQuantite_ajouterx()==null? new Double(0):newBean.getQuantite_ajouterx();
	    		Double montant_ht_achat=ProcessNumber.PRODUIT(priUnitachat, qte);
	    		       montant_ht_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat);
	    		newBean.setMontant_ht_achat(montant_ht_achat);
	    		Double montant_tva_achat=ProcessNumber.Pourcentage(montant_ht_achat, ss.getPk().getTvaBean().getTva_value());
	    		       montant_tva_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_achat);
	    		newBean.setMontant_tva_achat(montant_tva_achat);
	    		newBean.setQuantite_ajouter(qte);
	    		
	    		newBean.setQuantite(new Double(0));*/
	    	}
	    	
			listOfmyData.add(newBean);
			setObjectValueModel(LIST_EDITABLE_COMP_ACHAT,listOfmyData);
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkCode_barre.pk.code_barre");
			
			
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat     = new ArrayList();
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

 

	public static  ModelAndView doResetForm() {
		try {
			
			 
			 ComplementAchatBean  reBean=  (ComplementAchatBean) getObjectValueModel(ORIGINAL_FORM_BEAN);
			 List list_rrr=(List) getObjectValueModel(LIST_EDITABLE_COMP_ACHAT_ORG    );
			 
			 setObjectValueModel(FORM_BEAN,reBean);
			 setObjectValueModel(LIST_EDITABLE_COMP_ACHAT,list_rrr);
			 
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAddComplement(FORM_VIEW_EDIT);
	}



	public ModelAndView doAddData(ComplementAchatBean detailBean) throws Throwable {
		
		
		  boolean tes=false;
	     try {
	    	 
	    	    setObjectValueModel(FORM_BEAN,detailBean);
	            serviceComplementAchat.doCreateRowData(detailBean);
	            throwNewException("ins01");
	          } catch (Exception e) {
	        	  if(e.getMessage().equals("ins01"))
	        		  tes=true;
	        		  
	            displayException(e);
	          }
	          if(tes){
	        	removeObjectModel(FORM_BEAN);
	            remove_row_from_list(LIST_DATA_ACHAT);
	  			setObjectValueModel(MAP_FIELD_BEAN, Reception_achatTemplate.MapfieldBean);
	  			setObjectValueModel("LIST_VIEW", LIST_VIEW_ACHAT);
	  			setObjectValueModel("nameList" , LIST_DATA_ACHAT);
	  			setObjectValueModel("nameGrid",  NAME_GRID_ACHAT);
	  			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_ACHAT");
	  			setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Reception_achatTemplate.ID_SOUS_MODULE));
	  			setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Reception_achatTemplate.ID_SOUS_MODULE)) ;
	          return getViewFilter_achat(FILTER_VIEW_ACHAT);
	          }else {
	              return getViewAddComplement(FORM_VIEW_EDIT);
	          }
	           
		}
		
		public ModelAndView doTransferData( ) {
			try {
				serviceComplementAchat.doUpdateActionRowData();
				remove_row_from_list(LIST_DATA);
				throwNewException("validaTion Ok !");
			} catch (Exception e) {
				displayException(e);
			}
			return getViewList_Ajax(FILTER_VIEW);
		}
		
		
		public ModelAndView doAnnulerData( ) {
			try {
				serviceComplementAchat.doUpdateActionRowData();
				remove_row_from_list(LIST_DATA);
				throwNewException("Annulation Ok !");
			} catch (Exception e) {
				displayException(e);
			}
			return getViewList_Ajax(FILTER_VIEW);
		}

	public ModelAndView doReceptionData( ) {
		try {
			serviceComplementAchat.doUpdateActionRowData();
			remove_row_from_list(LIST_DATA);
			throwNewException("Verification Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(ComplementAchatBean searchBean)throws Throwable {
			try {
				
	            BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				 
				
	            /**************************************************complement******************************************************/
	            
				if(bs.getFct_id().equals(Fn_Transférer) || bs.getFct_id().equals(Fn_Modifier) ){
				  searchBean.setCondition_etat_complement("  AND  bean.modeBean.fct_id  in ('"+Fn_Modifier+"','"+Fn_Créer+"','"+Fn_Nouveau+"')   ");
				}
				
				
				if(bs.getFct_id().equals(Fn_Annuler)  && bs.getSousmod_id().equals(ComplementAchatTemplate.ID_SOUS_MODULE)){
					searchBean.setCondition_etat_complement("  AND  bean.modeBean.fct_id  in ('"+Fn_Modifier+"','"+Fn_Créer+"')   ");
				}
				
				if(bs.getFct_id().equals(Fn_Supprimer)){
					searchBean.setCondition_etat_complement("  AND  bean.modeBean.fct_id  in ('"+Fn_Modifier+"','"+Fn_Créer+"','"+Fn_Annuler+"' ,'"+Fn_Nouveau+"')   ");
				}
				/********************************************************************************************************************/
				
				
				
				/**************************************************Stock******************************************************/
				if(bs.getFct_id().equals(Fn_reception)){
					searchBean.setCondition_etat_complement("  AND  bean.modeBean.fct_id  in ('"+Fn_Transférer+"')   ");
				}
				
				
				if(bs.getFct_id().equals(Fn_Consulter)  && bs.getSousmod_id().equals(ComplementAchatTemplate.ID_SOUS_MODULE_STOCK)){
					searchBean.setCondition_etat_complement("  AND  bean.modeBean.fct_id  in ('"+Fn_reception+"','"+Fn_Conserver+"')   ");
				}
				
				if(bs.getFct_id().equals(Fn_Conserver)   ){
					searchBean.setCondition_etat_complement("  AND  bean.modeBean.fct_id  in ('"+Fn_reception+"')   ");
				}
				
				
				if(bs.getFct_id().equals(Fn_Annuler)  && bs.getSousmod_id().equals(ComplementAchatTemplate.ID_SOUS_MODULE_STOCK)){
					searchBean.setCondition_etat_complement("  AND  bean.modeBean.fct_id in ('"+Fn_Transférer+"','"+Fn_reception+"')   ");
				}
				
				/********************************************************************************************************************/
				
				
				
				List listDataSrv = serviceComplementAchat.doFetchDatafromServer(searchBean);
				setObjectValueModel(SEARCH_BEAN, searchBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	 		} catch (Exception e) {
	 			getResponse().setStatus(500);
	 			getResponse().setContentType(HTML_CONTENT_TYPE);
	 			getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	
		public ModelAndView doConserverData( ) {
			try {
				setObjectValueModel("LIST_VIEW", LIST_VIEW);
				setObjectValueModel("nameList" , LIST_DATA);
				setObjectValueModel("nameGrid",  NAME_GRID);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_AJAX_FETCH");
				setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+ComplementAchatTemplate.ID_SOUS_MODULE));
				setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+ComplementAchatTemplate.ID_SOUS_MODULE)) ;
				
				serviceComplementAchat.doConservData();
				remove_row_from_list(LIST_DATA);
				throwNewException(" AppliquerData Ok !");
			} catch (Exception e) {
				displayException(e);
			}
			return getViewList_Ajax(FILTER_VIEW);
		}

	public ModelAndView doUpdateData(ComplementAchatBean beanUpBean) {	 
		 	try {
		        serviceComplementAchat.doUpdateRowData(beanUpBean); 
		        update_row_from_list(LIST_DATA,beanUpBean);
			     throwNewException("mod01");
				 	} catch (Exception e) {
				 	displayException(e);
				 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(ComplementAchatBean beanDelBean) {
		    try {
		      serviceComplementAchat.doDeleteRowData(beanDelBean);
			  remove_row_from_list(LIST_DATA);
		     throwNewException("sup01");
		       } catch (Exception e) {
		       displayException(e);
		       }
		    return getViewList_Ajax(FILTER_VIEW);
		   }
}
