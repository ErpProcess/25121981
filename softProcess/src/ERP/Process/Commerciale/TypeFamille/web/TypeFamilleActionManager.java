package ERP.Process.Commerciale.TypeFamille.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.TypeFamille.model.TypeFamilleBean;
import ERP.Process.Commerciale.TypeFamille.service.TypeFamilleService;
import ERP.Process.Commerciale.TypeFamille.template.TypeFamilleTemplate;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
public class TypeFamilleActionManager extends TypeFamilleTemplate {
     private TypeFamilleService  serviceTypeFamille;
@Autowired
public void setServiceTypeFamille(TypeFamilleService serviceTypeFamille) {
    this.serviceTypeFamille = serviceTypeFamille;
} 
@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(TypeFamilleBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceTypeFamille.doFetchDatafromServer(searchBean);
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
	public ModelAndView doAddData(TypeFamilleBean detailBean) throws Throwable {
	     try {
	            serviceTypeFamille.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(TypeFamilleBean beanUpBean) {	 
		 	try {
		 serviceTypeFamille.doUpdateRowData(beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(TypeFamilleBean beanDelBean) {
	    try {
	     serviceTypeFamille.doDeleteRowData(beanDelBean);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
