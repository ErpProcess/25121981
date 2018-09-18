package ERP.Process.Commerciale.Fournisseur.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  

import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Fournisseur.template.FournisseurTemplate;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.Process.Commerciale.GrpTarifPrimitiv.service.GrpTarifPrimitivService;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class FournisseurActionManager extends FournisseurTemplate {
	
	
	
     private FournisseurService  serviceFrournisseur;
	@Autowired
	public void setServiceFrournisseur(FournisseurService serviceFrournisseur) {
	    this.serviceFrournisseur = serviceFrournisseur;
	} 

	
	private GrpTarifPrimitivService  	serviceGrpTarifPrimitiv;
	@Autowired
	public void setServiceGrpTarifPrimitiv(GrpTarifPrimitivService serviceGrpTarifPrimitiv) {
		this.serviceGrpTarifPrimitiv = serviceGrpTarifPrimitiv;
	}

		public    ModelAndView doInitServletAction() {
			 
			try {
				setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
				setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
				removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
				BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				 setObjectValueModel(LIST_GRP_TARIFI_PRTV ,serviceGrpTarifPrimitiv.doFetchDatafromServer(GrpTarifPrimitivBean.class.newInstance()));
				if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
					return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
				} else {
					return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		
				}
		
			} catch (Exception e) {
				displayException(e);
				return getHome();
			}
		
		}



@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(FournisseurBean searchBean)throws Exception {
		try {
			List listDataSrv = serviceFrournisseur.dofetchDatafromServer(searchBean);
			setObjectValueModel(LIST_DATA, listDataSrv);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
 		} catch (JsonIOException e) {
 			e.printStackTrace();
 			getResponse().setContentType(HTML_CONTENT_TYPE);
 			getResponse().getWriter().print(e.getMessage());
}
return null;
}
public ModelAndView doAddData(FournisseurBean detailBean) throws Exception {
     try {
            serviceFrournisseur.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(FournisseurBean beanUpBean) {	 
	 	try {
	 serviceFrournisseur.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(FournisseurBean beanDelBean) {
    try {
     serviceFrournisseur.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
