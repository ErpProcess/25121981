package ERP.Process.Commerciale.GrpTarifPrimitiv.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.Process.Commerciale.GrpTarifPrimitiv.service.GrpTarifPrimitivService;
import ERP.Process.Commerciale.GrpTarifPrimitiv.template.GrpTarifPrimitivTemplate;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
public class GrpTarifPrimitivActionManager extends GrpTarifPrimitivTemplate {
     
	private static final long serialVersionUID = 7059077823619598551L;
	
	
	private GrpTarifPrimitivService  serviceGrpTarifPrimitiv;
	@Autowired
	public void setServiceGrpTarifPrimitiv(GrpTarifPrimitivService serviceGrpTarifPrimitiv) {
	    this.serviceGrpTarifPrimitiv = serviceGrpTarifPrimitiv;
	} 
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(GrpTarifPrimitivBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceGrpTarifPrimitiv.doFetchDatafromServer(searchBean);
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
	public ModelAndView doAddData(GrpTarifPrimitivBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceGrpTarifPrimitiv.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(GrpTarifPrimitivBean beanUpBean) {	 
		 	try {
		 serviceGrpTarifPrimitiv.doUpdateRowData(beanUpBean); 
				update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(GrpTarifPrimitivBean beanDelBean) {
	    try {
	     serviceGrpTarifPrimitiv.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
