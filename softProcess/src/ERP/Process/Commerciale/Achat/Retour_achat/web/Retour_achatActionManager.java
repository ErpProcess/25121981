package ERP.Process.Commerciale.Achat.Retour_achat.web;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.service.Reception_achatService;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Achat.Retour_achat.model.Det_retour_achatBean;
import ERP.Process.Commerciale.Achat.Retour_achat.model.Retour_achatBean;
import ERP.Process.Commerciale.Achat.Retour_achat.service.Retour_achatService;
import ERP.Process.Commerciale.Achat.Retour_achat.template.Retour_achatTemplate;
import ERP.Process.Commerciale.Demande_Achat.template.Demande_AchatTemplate;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.DepotStockage.template.DepotStockageTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel.WriteExcel;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonObject;
public class Retour_achatActionManager extends Retour_achatTemplate {
     
	private static final long serialVersionUID = 798701726760415192L;
	private Retour_achatService  serviceRetour_achat;
	@Autowired
	public void setServiceRetour_achat(Retour_achatService serviceRetour_achat) {
	    this.serviceRetour_achat = serviceRetour_achat;
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


public    ModelAndView doInitServletAction() {

	
	try {
		
		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		setObjectValueModel(LIST_FOURNISSEUR_RECEP_ACHAT, serviceFournisseur.dofetchDatafromServer(FournisseurBean.class.newInstance()));
		setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
		
		doLoadingLibelleOtherSModule(Reception_achatTemplate.ID_SOUS_MODULE);
		doLoadingLibelleOtherSModule(Demande_AchatTemplate.ID_SOUS_MODULE);
		doLoadingLibelleOtherSModule(DepotStockageTemplate.ID_SOUS_MODULE);
		doLoadingLibelleOtherSModule(Retour_achatTemplate.ID_SOUS_MODULE);
		 
		
		if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
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
			setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Retour_achatTemplate.ID_SOUS_MODULE));
			setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Retour_achatTemplate.ID_SOUS_MODULE)) ;
			return getViewFilterAjax(FILTER_VIEW);

		}

	} catch (Exception e) {
		displayException(e);
		return getHome();
	}

}

@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(Retour_achatBean searchBean)throws Throwable {
		try {
			
            BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 
			
            /**************************************************Retour******************************************************/
            
			if(bs.getFct_id().equals(Fn_Valider) || bs.getFct_id().equals(Fn_Modifier) ){
			  searchBean.setCondition_etat_retour("  AND  bean.modeBean.fct_id  in ('"+Fn_Modifier+"','"+Fn_Créer+"')   ");
			}
			
			
			if(bs.getFct_id().equals(Fn_Annuler)  && bs.getSousmod_id().equals(Retour_achatTemplate.ID_SOUS_MODULE)){
				searchBean.setCondition_etat_retour("  AND  bean.modeBean.fct_id  in ('"+Fn_Modifier+"','"+Fn_Créer+"')   ");
			}
			
			if(bs.getFct_id().equals(Fn_Supprimer)){
				searchBean.setCondition_etat_retour("  AND  bean.modeBean.fct_id  in ('"+Fn_Modifier+"','"+Fn_Créer+"','"+Fn_Annuler+"')   ");
			}
			/********************************************************************************************************************/
			
			
			
			/**************************************************Stock******************************************************/
			if(bs.getFct_id().equals(Fn_Vérifier)){
				searchBean.setCondition_etat_retour("  AND  bean.modeBean.fct_id  in ('"+Fn_Valider+"')   ");
			}
			
			
			if(bs.getFct_id().equals(Fn_Consulter)  && bs.getSousmod_id().equals(Retour_achatTemplate.ID_SOUS_MODULE_STOCK)){
				searchBean.setCondition_etat_retour("  AND  bean.modeBean.fct_id  in ('"+Fn_Vérifier+"','"+Fn_appliquer+"')   ");
			}
			
			if(bs.getFct_id().equals(Fn_appliquer)   ){
				searchBean.setCondition_etat_retour("  AND  bean.modeBean.fct_id  in ('"+Fn_Vérifier+"')   ");
			}
			
			
			if(bs.getFct_id().equals(Fn_Annuler)  && bs.getSousmod_id().equals(Retour_achatTemplate.ID_SOUS_MODULE_STOCK)){
				searchBean.setCondition_etat_retour("  AND  bean.modeBean.fct_id in ('"+Fn_Valider+"','"+Fn_Vérifier+"')   ");
			}
			
			
			
			
			/********************************************************************************************************************/
			
			
			
			List listDataSrv = serviceRetour_achat.doFetchDatafromServer(searchBean);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
 		} catch (Exception e) {
 			getResponse().setStatus(500);
 			getResponse().setContentType(HTML_CONTENT_TYPE);
 			getResponse().getWriter().print(e.getMessage());
}
return null;
}

@SuppressWarnings("unchecked") 
public ModelAndView doFetch_Achat_Data(Retour_achatBean searchBean)throws Throwable {
		try {
			 
			
			searchBean.getAchat().setCondition_etat_achat("   AND  bean.modeBean.fct_id  in ('"+Fn_Conserver+"' , '"+Fn_Confirmer+"' )  ");
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
		
		 
		List listOData=(List) getObjectValueModel(LIST_EDITABLE_RETOUR_ACHAT);
		JsonObject data = new JsonObject();
	    BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
	  
		for (int i = 0; i < listOData.size(); i++) {
			Det_retour_achatBean     newBean= (Det_retour_achatBean) listOData.get(i);
			 
	    	 
			
			
			    data.addProperty("erreur"+newBean.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre(),"");
			  
	    	 
	    		if( newBean.getQuantite_retourne() == null  ) continue;
	    		if( newBean.getQuantite_retourne() < 0 ) {
	    			newBean.setQuantite_retourne(new Double(0));
	    		
	    			String err="Erreur . quantité(s) inférieur a zéro ";
	    			data.addProperty("erreur"+newBean.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre(),err);
	    			data.addProperty("Qte"+newBean.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre(),"0");
	    			data.addProperty(newBean.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre(),"0");
	    			break;
	    			
	    		}
	    		
	    		if(   newBean.getQuantite_retourne()!=null ){
	    			
	    		if( newBean.getPk().getDet_re().getQuantite() <  newBean.getQuantite_retourne()   ) {
	    			newBean.setQuantite_retourne(new Double(0));
	    			String err="Erreur . quantité(s) retournée(s) supérieur  a la quantité  Initiales   ";
	    			data.addProperty("erreur"+newBean.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre(),err);
	    			data.addProperty("Qte"+newBean.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre(),"0");
	    			data.addProperty(newBean.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre(),"0");
	    			break;
	    		}
	    		}
	      
	    			 
	    		double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getPk().getDet_re().getTarif().getTarif_unit_article());
	    		double qte=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getQuantite_retourne());
	    		 
	    		
	    	    
	    		
	    		
	    		
	    		double montant_ht_achat=ProcessNumber.PRODUIT(priUnitachat, qte);
	    		newBean.setMontant_ht_retour(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat));
	    		
	    		Double montant_tva_achat=ProcessNumber.Pourcentage(montant_ht_achat, newBean.getPk().getDet_re().getTarif().getTvaBean().getTva_value());
    		           montant_tva_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_achat);
    		           newBean.setMontant_tva_retour(montant_tva_achat);
	    		 Double number=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat);          
	    		 String elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(number)  ;     
	    		 data.addProperty(newBean.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre(),elm);
	    		 
    				         
	    	 
	     	 
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
			    mapFieldBean            =   Retour_achatTemplate.MapfieldBean;
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
			    mapFieldBean            =   Retour_achatTemplate.MapfieldBean;
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
		Retour_achatBean  rowBean = (Retour_achatBean) getObjectValueModel(FORM_BEAN);
		 
		List <Det_retour_achatBean > List_detail_retour= new ArrayList <Det_retour_achatBean > (rowBean.getList_retour());
		 
		 
		setObjectValueModel(LIST_EDITABLE_RETOUR_ACHAT   , List_detail_retour);
		
		double tot_ht=0;
		double tot_tva=0;
		Double tot_qte=new Double(0);
		
		for (int i = 0; i < List_detail_retour.size(); i++) {
			Det_retour_achatBean  bean=List_detail_retour.get(i);
			tot_ht=ProcessNumber.addition(tot_ht, bean.getMontant_ht_retour());
			tot_tva=ProcessNumber.addition(tot_tva, bean.getMontant_tva_retour());
			tot_qte=ProcessNumber.addition(tot_qte, bean.getQuantite_retourne());
		}
		double total_mnt_gen=ProcessNumber.addition(tot_ht, tot_tva); 
		Det_retour_achatBean  reBean= new Det_retour_achatBean();
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
		removeObjectModel(FORM_BEAN_ACHAT);
		BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		
		
		
		if (bs.getFct_id().equals(Fn_Nouveau) || bs.getFct_id().equals(Fn_Créer) ){
			
			setObjectValueModel("LIST_VIEW", LIST_VIEW_ACHAT);
			setObjectValueModel("nameList" , LIST_DATA_ACHAT);
			setObjectValueModel("nameGrid",  NAME_GRID_ACHAT);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_ACHAT");
			
			Reception_achatBean rowBean = (Reception_achatBean) getIndexFromDataGrid_v1(LIST_DATA_ACHAT);
			Retour_achatBean rBean = new Retour_achatBean();
			rBean.setAchat(rowBean);
			rBean.setRetour_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
			setObjectValueModel(FORM_BEAN, rBean);
			setObjectValueModel(ORIGINAL_FORM_BEAN,ProcessUtil.cloneObject(rBean));
			 
			 List<Det_reception_achatBean> Listdetail_achat=serviceReception_achat.doFetchDetailAchat(rowBean);
			 Retour_achatBean  retourBean = new Retour_achatBean();
			 retourBean.getAchat().setAchat_id(rowBean.getAchat_id()) ;
			 List <Retour_achatBean>list_Data_retour=serviceRetour_achat.doFetchDatafromServer(retourBean);
			 
			 HashMap  map_retour_achat= new  HashMap();
			 if(list_Data_retour!=null &&  list_Data_retour.size()>0){
				 for(Retour_achatBean bean_re:list_Data_retour){
					 
					 if(!String.valueOf(bean_re.getModeBean().getFct_id()).equals(Fn_appliquer)){
						throwNewException("*** il existe encore une retour non appliquer au stock .   a") ;
					 }
					 
					 List list_detail_re_article=new ArrayList(bean_re.getList_retour());
					 
					 for (int k = 0; k < list_detail_re_article.size(); k++) {
						 Det_retour_achatBean beantrie=(Det_retour_achatBean) list_detail_re_article.get(k);
							String key=    beantrie.getPk().getDet_re().getPk().getFkCode_barre().getPk().getCode_barre() ;
							List lis_el=(List) map_retour_achat.get(key);
							if(lis_el==null)lis_el= new ArrayList();
							lis_el.add(beantrie);
							map_retour_achat.put(key, lis_el);
						}
					 
					 
						
						
				 }
				 
			 }
			
			 
			 
			 
			
			List <Det_retour_achatBean > List_detail_retour= new ArrayList();
			
			for (Det_reception_achatBean bean_Reception:Listdetail_achat) {
				Det_retour_achatBean  bean_retour = new Det_retour_achatBean();
				bean_retour.getPk().getDet_re().getPk().setFkCode_barre(bean_Reception.getPk().getFkCode_barre());
			
				
				List list_article=(List) map_retour_achat.get(bean_Reception.getPk().getFkCode_barre().getPk().getCode_barre());
				if(list_article==null){
				bean_retour.getPk().getDet_re().setQuantite(bean_Reception.getQuantite());
				bean_retour.setQuantite_calcul(bean_Reception.getQuantite());
				
				bean_retour.getPk().setDet_re(bean_Reception);
				}else{
					/*double sss=0;
					for (int i = 0; i < list_article.size(); i++) {
						Det_retour_achatBean sqqsdqsd= (Det_retour_achatBean) list_article.get(i);
						sss=ProcessNumber.addition(sss, sqqsdqsd.getQuantite_retourne());
					}
					double reswqdsq=ProcessNumber.SOUSTRACTION(bean_Reception.getQuantite(), sss);
					bean_retour.setQuantite_calcul(reswqdsq);*/
				}
				/*bean_retour.setUnitBean(bean_Reception.getUnitBean());
				 
				bean_retour.setLot(bean_Reception.getPk().getLot());
				bean_retour.setPrix_unit_achat(bean_Reception.getPrix_unit_achat());
				bean_retour.setPrix_unit_vente(bean_Reception.getPrix_unit_vente());
				bean_retour.setTvaBean(bean_Reception.getTvaBean());
				bean_retour.setTyp_trfBean(bean_Reception.getTyp_trfBean());*/
				List_detail_retour.add(bean_retour);
				
			}
			
			
			setObjectValueModel(LIST_EDITABLE_RETOUR_ACHAT          ,  List_detail_retour);
			setObjectValueModel(LIST_EDITABLE_RETOUR_ACHAT_ORG          , ProcessUtil.cloneList(List_detail_retour) );
			
			
			 
			return getViewAdd_retour(FORM_VIEW_EDIT);
			
			
		}else{
			
			setObjectValueModel("LIST_VIEW", LIST_VIEW);
			setObjectValueModel("nameList" , LIST_DATA);
			setObjectValueModel("nameGrid",  NAME_GRID);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_AJAX_FETCH");
			setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Retour_achatTemplate.ID_SOUS_MODULE));
			setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Retour_achatTemplate.ID_SOUS_MODULE)) ;
			
			Retour_achatBean   rowBean = (Retour_achatBean) getIndexFromDataGrid_v1(LIST_DATA);
			List <Det_retour_achatBean > List_detail_retour= new ArrayList <Det_retour_achatBean > (rowBean.getList_retour());
			setObjectValueModel(LIST_EDITABLE_RETOUR_ACHAT          ,  List_detail_retour);
			setObjectValueModel(LIST_EDITABLE_RETOUR_ACHAT_ORG          , ProcessUtil.cloneList(List_detail_retour) );
			
			
			setObjectValueModel(FORM_BEAN, rowBean);
			setObjectValueModel(ORIGINAL_FORM_BEAN,ProcessUtil.cloneObject(rowBean));
		}
		 
		if (bs.getFct_id().equals(Fn_Consulter))
			return getViewConsult(FORM_VIEW);
		
		
		if (bs.getFct_id().equals(Fn_Annuler))
			return getViewAnnuler(FORM_VIEW);
		
		
		if (bs.getFct_id().equals(Fn_Confirmer))
			return getViewValider(FORM_VIEW);
		
		
		if (bs.getFct_id().equals(Fn_appliquer))
			return getViewAppliquer(FORM_VIEW);
		
		
		if (bs.getFct_id().equals(Fn_Modifier))
			return getViewUpdate(FORM_VIEW_EDIT);
		
		
		if (bs.getFct_id().equals(Fn_Supprimer))
			return getViewDelete(FORM_VIEW);
		
		if (bs.getFct_id().equals(Fn_Vérifier))
			return getViewVerifier(FORM_VIEW);
		
		
		
		
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
			setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Retour_achatTemplate.ID_SOUS_MODULE));
			setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Retour_achatTemplate.ID_SOUS_MODULE)) ;
			
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
 

public static  ModelAndView doResetForm() {
	try {
		
		 
		 Retour_achatBean  reBean=  (Retour_achatBean) getObjectValueModel(ORIGINAL_FORM_BEAN);
		 List list_rrr=(List) getObjectValueModel(LIST_EDITABLE_RETOUR_ACHAT_ORG    );
		 
		 setObjectValueModel(FORM_BEAN,reBean);
		 setObjectValueModel(LIST_EDITABLE_RETOUR_ACHAT,list_rrr);
		 
	} catch (Exception e) {
		displayException(e);
	}
	return getViewAdd_retour(FORM_VIEW_EDIT);
}



public ModelAndView doAddData(Retour_achatBean detailBean) throws Throwable {
	
	
	  boolean tes=false;
     try {
    	 
    	    setObjectValueModel(FORM_BEAN,detailBean);
            serviceRetour_achat.doCreateRowData(detailBean);
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
              return getViewAdd_retour(FORM_VIEW_EDIT);
          }
           
	}
	
	public ModelAndView doValiderData( ) {
		try {
			serviceRetour_achat.doUpdateActionRowData();
			remove_row_from_list(LIST_DATA);
			throwNewException("validaTion Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	
	public ModelAndView doAnnulerData( ) {
		try {
			serviceRetour_achat.doUpdateActionRowData();
			remove_row_from_list(LIST_DATA);
			throwNewException("Annulation Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

public ModelAndView doVerificationData( ) {
	try {
		serviceRetour_achat.doUpdateActionRowData();
		remove_row_from_list(LIST_DATA);
		throwNewException("Verification Ok !");
	} catch (Exception e) {
		displayException(e);
	}
	return getViewList_Ajax(FILTER_VIEW);
}

	
	public ModelAndView doAppliquerData( ) {
		try {
			setObjectValueModel("LIST_VIEW", LIST_VIEW);
			setObjectValueModel("nameList" , LIST_DATA);
			setObjectValueModel("nameGrid",  NAME_GRID);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_AJAX_FETCH");
			setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Retour_achatTemplate.ID_SOUS_MODULE));
			setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Retour_achatTemplate.ID_SOUS_MODULE)) ;
			
			serviceRetour_achat.doAppliquerRowData();
			remove_row_from_list(LIST_DATA);
			throwNewException(" AppliquerData Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

public ModelAndView doUpdateData(Retour_achatBean beanUpBean) {	 
	 	try {
	        serviceRetour_achat.doUpdateRowData(beanUpBean); 
	        update_row_from_list(LIST_DATA,beanUpBean);
		     throwNewException("mod01");
			 	} catch (Exception e) {
			 	displayException(e);
			 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(Retour_achatBean beanDelBean) {
	    try {
	      serviceRetour_achat.doDeleteRowData(beanDelBean);
		  remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
