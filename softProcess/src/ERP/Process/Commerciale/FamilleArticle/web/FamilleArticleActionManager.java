package ERP.Process.Commerciale.FamilleArticle.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.Process.Commerciale.FamilleArticle.service.FamilleArticleService;
import ERP.Process.Commerciale.FamilleArticle.template.FamilleArticleTemplate;
import ERP.Process.Commerciale.TypeFamille.model.TypeFamilleBean;
import ERP.Process.Commerciale.TypeFamille.service.TypeFamilleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
public class FamilleArticleActionManager extends FamilleArticleTemplate {
	
	
	private FamilleArticleService  serviceFamilleArticle;
	@Autowired
	public void setServiceFamilleArticle(FamilleArticleService serviceFamilleArticle) {
	    this.serviceFamilleArticle = serviceFamilleArticle;
	} 
    private TypeFamilleService  serviceTypeFamille;
    @Autowired
    public void setServiceTypeFamille(TypeFamilleService serviceTypeFamille) {
        this.serviceTypeFamille = serviceTypeFamille;
    } 

	public    ModelAndView doInitServletAction() {
		 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 
			setObjectValueModel(TYPE_FAMILLE_ARTICLE, serviceTypeFamille.doFetchDatafromServer(TypeFamilleBean.class.newInstance()) );
			
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
	public ModelAndView doFetchData(FamilleBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceFamilleArticle.dofetchDatafromServer(searchBean);
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
	public ModelAndView doAddData(FamilleBean detailBean) throws Throwable {
	     try {
	            serviceFamilleArticle.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("Insertion Reussit");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(FamilleBean beanUpBean) {	 
		 	try {
		 serviceFamilleArticle.doUpdateRowData(beanUpBean); 
		 throwNewException("Update Reussit");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(FamilleBean beanDelBean) {
	    try {
	     serviceFamilleArticle.doDeleteRowData(beanDelBean);
	     throwNewException("Reussite delete");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
