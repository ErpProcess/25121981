package ERP.Process.Commerciale.Stock.DepotStockage.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.template.DepotStockageTemplate;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.NatureLieu.model.NatureLieuBean;
import ERP.Process.Commerciale.Stock.NatureLieu.service.NatureLieuService;
import ERP.Process.Commerciale.Stock.NatureLieu.template.NatureLieuTemplate;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class DepotStockageActionManager extends DepotStockageTemplate {
	
	
     private DepotStockageService  serviceDepotStockage;
@Autowired
public void setServiceDepotStockage(DepotStockageService serviceDepotStockage) {
    this.serviceDepotStockage = serviceDepotStockage;
} 

private NatureLieuService    serviceNatureLieu;
@Autowired
public void setServiceserviceNatureLieu(NatureLieuService serviceNatureLieu) {
    this.serviceNatureLieu = serviceNatureLieu;
} 


public    ModelAndView doInitServletAction() {
 
	try {
		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		
		 setObjectValueModel(NatureLieuTemplate.LIST_DATA              , serviceNatureLieu.doFetchDatafromServer(NatureLieuBean.class.newInstance()));
		 
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
public ModelAndView doFetchData(DepotStockageBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceDepotStockage.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(DepotStockageBean detailBean) throws Throwable {
     try {
            serviceDepotStockage.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(DepotStockageBean beanUpBean) {	 
	 	try {
	 serviceDepotStockage.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(DepotStockageBean beanDelBean) {
    try {
     serviceDepotStockage.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
