package ERP.Process.Commerciale.Stock.TransfertLot.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.TransfertLot.model.TransfertLotBean;
import ERP.Process.Commerciale.Stock.TransfertLot.service.TransfertLotService;
import ERP.Process.Commerciale.Stock.TransfertLot.template.TransfertLotTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;


public class TransfertLotActionManager extends TransfertLotTemplate {
  
	private static final long serialVersionUID = 6344762290933335554L;
	
	
	private TransfertLotService  serviceTransfertLot;
	@Autowired
	public void setServiceTransfertLot(TransfertLotService serviceTransfertLot) {
	    this.serviceTransfertLot = serviceTransfertLot;
	} 
	
	private DocumentLotService serviceDocumentLot;
	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
		this.serviceDocumentLot = serviceDocumentLot;
	}
	
	
	public    ModelAndView doInitServletAction() {
		 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			SerieArticletBean beanSearch = new SerieArticletBean();
			beanSearch.setCondition_list_article("  AND  bean.quantite > 0   AND  ( bean.serie_bloque  is null  or  bean.serie_bloque = false )  ");
			List listDataSrv = serviceDocumentLot.doFetchDatafromServer(beanSearch);
			 
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
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
	public ModelAndView doFetchData(TransfertLotBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceTransfertLot.doFetchDatafromServer(searchBean);
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
	public ModelAndView doAddData(TransfertLotBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceTransfertLot.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(TransfertLotBean beanUpBean) {	 
		 	try {
		 serviceTransfertLot.doUpdateRowData(beanUpBean); 
				update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(TransfertLotBean beanDelBean) {
	    try {
	     serviceTransfertLot.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
