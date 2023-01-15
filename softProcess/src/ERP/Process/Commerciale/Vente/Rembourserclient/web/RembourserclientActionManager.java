package ERP.Process.Commerciale.Vente.Rembourserclient.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.service.Facture_clientService;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.EcheanceRegCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.template.ReglementFactCltTemplate;
import ERP.Process.Commerciale.Vente.Rembourserclient.template.RembourserclientTemplate;
import ERP.Process.Commerciale.Vente.Rembourserclient.service.RembourserclientService;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class RembourserclientActionManager extends RembourserclientTemplate {
     private RembourserclientService  serviceRembourserclient;
@Autowired
public void setServiceRembourserclient(RembourserclientService serviceRembourserclient) {
    this.serviceRembourserclient = serviceRembourserclient;
} 
private Facture_clientService  serviceFacture;


@Autowired
public void setServiceFacture(Facture_clientService serviceFacture) {
    this.serviceFacture = serviceFacture;
}



@Autowired Entite_etat_commercialeService   serviceEntite_etat_commerciale;

public    ModelAndView doInitServletAction() {
	 
	try {
		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		doLoadingLibelleOtherSModule(ProcedureVenteTemplate.ID_SOUS_MODULE);
		doLoadingLibelleOtherSModule(Facture_clientTemplate.ID_SOUS_MODULE);
		doLoadingLibelleOtherSModule(ID_SOUS_MODULE_REGLE_MENT);
		
		Entite_etat_commercialeBean beanSearBean = new Entite_etat_commercialeBean();
		beanSearBean.setCode_entite("reg_mod");
		setObjectValueModel(LIST_MODE_REGLMENT,serviceEntite_etat_commerciale.dofetchDatafromServer(beanSearBean));
		

		Entite_etat_commercialeBean beanSn = new Entite_etat_commercialeBean();
		beanSn.setCode_entite("reg_nature");
		setObjectValueModel(LIST_NATURE_REGLEMENT,serviceEntite_etat_commerciale.dofetchDatafromServer(beanSn));
		
		
		
		
		if (bs.getFct_id().equals(Fn_Creer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
			
			setObjectValueModel(MAP_FIELD_BEAN, Facture_clientTemplate.MapfieldBean);
			setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
			setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
			setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_FACT");
			setObjectValueModel(FORM_BEAN, new Facture_clientBean());
			setObjectValueModel(SEARCH_BEAN, new Facture_clientBean());
			
			return getViewFilterFacture(FILTER_VIEW_FACTURE);
			
			 
		} else {
			
			return getViewFilterAjax( FILTER_VIEW );

		}

	} catch (Exception e) {
		displayException(e);
		return getHome();
	}

}

@SuppressWarnings("unchecked") 
public ModelAndView doFetchDataFacture(Facture_clientBean searchBean)throws Throwable {
		try {
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		    //searchBean.setCondition_select_mode("  AND   bean.modeBean.fct_id    in ('"+Fn_Confirmer+"','"+Fn_Envoyer+"')   ");
		    
		    searchBean.setSelect_many_facture("    AND   bean.typefact.type_fact_id in ('1','3')  " );
		    
		    if(bs.getSousmod_id().equals(ReglementFactCltTemplate.ID_SOUS_MODULE_REMBOURCEMENT))
		    searchBean.setSelect_many_facture("    AND   bean.typefact.type_fact_id in ('2')  " );
			    
		    
		    setObjectValueModel(SEARCH_BEAN, searchBean);
			setObjectValueModel(MAP_FIELD_BEAN, Facture_clientTemplate.MapfieldBean);
			setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
			setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
			setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
			setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_FACT");
			List listDataSrv = serviceFacture.doFetchDatafromServer(searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
 		} catch (Exception e) {
 			 getResponse().setStatus(500);
 			 e.printStackTrace();
 			 getResponse().setContentType(HTML_CONTENT_TYPE);
 			 getResponse().getWriter().print(e.getMessage());
}
return null;
}




protected    ModelAndView doLoadGridEcheance(ReglementFactCltBean searchBean ) {
	
	try {
		getResponse().setContentType(HTML_CONTENT_TYPE);
		List <EcheanceRegCltBean> list_des_echeances  = new ArrayList<EcheanceRegCltBean>();
		List  <EcheanceRegCltBean>list_des=(List) getObjectValueModel( LIST_DES_ECHEANCES );
		
		if(list_des!=null  &&  list_des.size()>0) {
			list_des_echeances=list_des;
			
			if( searchBean.getReg_nbr_echeance().intValue() > list_des_echeances.size()){
				
				int dif=searchBean.getReg_nbr_echeance().intValue()-list_des_echeances.size();
				for (int i = 0; i < dif; i++) {
					list_des_echeances.add(new EcheanceRegCltBean());
				}
				
				
				
			}
			
			if( searchBean.getReg_nbr_echeance().intValue() < list_des_echeances.size()){
				int dif=list_des_echeances.size()-searchBean.getReg_nbr_echeance().intValue();
				
				for (int i = 0; i < dif; i++) {
					list_des_echeances.remove(i);
					dif--;
					i--;
				}
			}
			
			 
		}else{
			if(searchBean.getReg_nbr_echeance()!=null  &&  searchBean.getReg_nbr_echeance()>0){
				for (int i = 0; i < searchBean.getReg_nbr_echeance(); i++) {
					list_des_echeances.add(new EcheanceRegCltBean() );
				}
			}
			
		}
	
		setObjectValueModel(LIST_DES_ECHEANCES, list_des_echeances);
		
		if(list_des_echeances.size()>0){
			 getResponse().getWriter().write("oui");
		}else{
			 getResponse().getWriter().write("non");
		}
		  
		  
		 
		 
	} catch (Exception e) {
		displayException(e);
	}
	return null;
}

public    ModelAndView doGetRowBean() {

	try {
		removeObjectModel(FORM_BEAN);
		BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		
		
		if (bs.getFct_id().equals(Fn_Creer) || bs.getFct_id().equals(Fn_Nouveau) ){
			setObjectValueModel(MAP_FIELD_BEAN, Facture_clientTemplate.MapfieldBean);
			setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
			setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
			setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
			Facture_clientBean beanFact = (Facture_clientBean) getIndexFromDataGrid_v1(LIST_DATA_FACTURE);
			ReglementFactCltBean   rowBean =  new ReglementFactCltBean();
			rowBean.setFactclient(beanFact);
			
			setObjectValueModel(FORM_BEAN, rowBean);
			return getViewAdd(FORM_EDIT_VIEW);
		}else{
			ReglementFactCltBean beanRegFact = (ReglementFactCltBean) getIndexFromDataGrid_v1(LIST_DATA);
			List list_des_echeances = serviceRembourserclient.doFetchDataEcheanceReglementfromServer(beanRegFact);
			setObjectValueModel(LIST_DES_ECHEANCES, list_des_echeances);
			setObjectValueModel(LIST_DES_ECHEANCES_ORIGINE, ProcessUtil.cloneList(list_des_echeances) );
			setObjectValueModel(FORM_BEAN, beanRegFact);
		}
		
		if (bs.getFct_id().equals("2"))
			return getViewConsult(FORM_VIEW);
		if (bs.getFct_id().equals("3"))
			return getViewUpdate(FORM_EDIT_VIEW);
		if (bs.getFct_id().equals("4"))
			return getViewDelete(FORM_VIEW);
		if (bs.getFct_id().equals(Fn_Confirmer) ||  bs.getFct_id().equals(Fn_Regler) )
			return getViewConfirm(FORM_VIEW);
		
		if (bs.getFct_id().equals(Fn_Annuler))
			return getViewAnnuler(FORM_VIEW);
		
		
	} catch (Exception e) {
		displayException(e);
		return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
	}
	return getHome();

}



@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(ReglementFactCltBean searchBean)throws Throwable {
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			if(bs.getSousmod_id().equals(ID_SOUS_MODULE_ECHENACE)){
				searchBean.setCondition_juste_echeance("  AND   bean.reg_nbr_echeance is not null   and    bean.reg_nbr_echeance   > 0  " +
						"   AND  bean.modeBean.fct_id="+Fn_Confirmer+"   ");
			}
			
			if(bs.getSousmod_id().equals(ID_SOUS_MODULE_REMBOURCEMENT)){
				searchBean.setSelect_avoir("     AND   bean.factclient.typefact.type_fact_id  = 2  ");
			}
			
			List listDataSrv = serviceRembourserclient.doFetchDatafromServer(searchBean);
			
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
public ModelAndView doAddData(ReglementFactCltBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
			
			
            serviceRembourserclient.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(ReglementFactCltBean beanUpBean) {	 
	 	try {
	 		
	 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
	 serviceRembourserclient.doUpdateRowData(beanUpBean); 
	
	 
	 if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier)){
		update_row_from_list(LIST_DATA, beanUpBean); 
	    throwNewException("mod01");
	 } else{
		 update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("ins01");
	 }	 
		 
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(ReglementFactCltBean beanDelBean) {
    try {
     serviceRembourserclient.doDeleteRowData(beanDelBean);
remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
