package ERP.Process.Commerciale.AchatDivers.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 

import org.apache.commons.lang.StringUtils;
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  
import ERP.Process.Commerciale.AchatDivers.model.AchatDiversBean;
import ERP.Process.Commerciale.AchatDivers.template.AchatDiversTemplate;
import ERP.Process.Commerciale.AchatDivers.service.AchatDiversService;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.Process.Commerciale.Degre_definition.model.Degre_definitionBean;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class AchatDiversActionManager extends AchatDiversTemplate {
     private AchatDiversService  serviceAchatDivers;
@Autowired
public void setServiceAchatDivers(AchatDiversService serviceAchatDivers) {
    this.serviceAchatDivers = serviceAchatDivers;
} 
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(AchatDiversBean searchBean)throws Throwable {
			try {
				 removeObjectModel("TotalACHAT");
				List listDataSrv = serviceAchatDivers.doFetchDatafromServer(searchBean);
				Double toto= new Double(0);
				for (int i = 0; i < listDataSrv.size(); i++) {
					AchatDiversBean  bDiversBean=  (AchatDiversBean) listDataSrv.get(i);
					toto=toto+bDiversBean.getPrix_achat();
				}
				AchatDiversBean beanTo= new AchatDiversBean();
				beanTo.setLibelle_achat("Total");
				beanTo.setPrix_achat(toto);
			    setObjectValueModel("TotalACHAT", toto);
				
				setObjectValueModel(LIST_DATA, listDataSrv);
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
	public ModelAndView doloadTOT() throws Throwable {
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		try {
			 Double  TotalACHAT=(Double) getObjectValueModel("TotalACHAT");
			 if(TotalACHAT==null)
			 getResponse().getWriter().print("");
			 else
			 getResponse().getWriter().print(String.valueOf(TotalACHAT));
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	 


public ModelAndView doAddData(AchatDiversBean detailBean) throws Throwable {
     try {
            serviceAchatDivers.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(AchatDiversBean beanUpBean) {	 
	 	try {
	 serviceAchatDivers.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(AchatDiversBean beanDelBean) {
    try {
     serviceAchatDivers.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
