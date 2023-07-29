package ERP.Process.Commerciale.Degre_definition.web;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.service.ArticleService;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.Process.Commerciale.Caracteristique.service.CaracteristiqueService;
import ERP.Process.Commerciale.Degre_definition.model.Degre_definitionBean;
import ERP.Process.Commerciale.Degre_definition.service.Degre_definitionService;
import ERP.Process.Commerciale.Degre_definition.template.Degre_definitionTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class Degre_definitionActionManager extends Degre_definitionTemplate {
	
	
	private Degre_definitionService serviceDegre_definition;

	@Autowired
	public void setServiceDegre_definition(
			Degre_definitionService serviceDegre_definition) {
		this.serviceDegre_definition = serviceDegre_definition;
	}
	
	 @Autowired
	 private    ArticleService      serviceArticle;
	 
	 @Autowired
	 private    CaracteristiqueService        serviceCaracteristique;
	
	public    ModelAndView doInitServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		
		try {
			  ArticleBean articlBeanSearch=new ArticleBean();
			if (bs.getFct_id().equals("15")  ) {
				articlBeanSearch.setCondition_personnalised_list(" NOT IN  ( SELECT  c.pkBean.art_Bean.pk_article.ar_id   from  Degre_definitionBean c  )  ");
				setObjectValueModel(LIST_DES_ARTICLE, serviceArticle.dofetchDatafromServer(articlBeanSearch));
				return getViewAdd(FORM_VIEW);
			} else {
				articlBeanSearch.setCondition_personnalised_list(" IN  ( SELECT  c.pkBean.art_Bean.pk_article.ar_id   from  Degre_definitionBean c  )       ");
				setObjectValueModel(LIST_DES_ARTICLE, serviceArticle.dofetchDatafromServer(articlBeanSearch));
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}  
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchDataCaracteristique(Degre_definitionBean searchBean) throws Throwable {
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		try {
			String   TYPE_LOAD= getRequest().getParameter("TYPE_LOAD")==null?"":getRequest().getParameter("TYPE_LOAD");
			String   ID_AR= getRequest().getParameter("ID_AR")==null?"":getRequest().getParameter("ID_AR");
			CaracteristiqueBean beaCaracteristiqueBean= new CaracteristiqueBean();
			if(StringUtils.equals(TYPE_LOAD, "C")){
				beaCaracteristiqueBean= new CaracteristiqueBean();
			}else{
				beaCaracteristiqueBean.setArticle_id_caracteristique(ID_AR);
			}
			List listDataSrv = serviceCaracteristique.dofetchDatafromServer(beaCaracteristiqueBean);
			setObjectValueModel(LIST_DES_CARCTERISTIQUE, listDataSrv);
		} catch (JsonIOException e) {
			getResponse().setStatus(500);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	public static  ModelAndView doSelect_Row() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Object rowBean = getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("2"))
				return getViewConsult(FORM_VIEW_CONSULT);
			if (bs.getFct_id().equals("3"))
				return getViewUpdate(FORM_VIEW);
			if (bs.getFct_id().equals("13"))
				return getViewDelete(FORM_VIEW );
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(Degre_definitionBean searchBean)
			throws Throwable {
		try {
			List listDataSrv = serviceDegre_definition.doFetchDatafromServer(searchBean);
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

	public ModelAndView doAddData(Degre_definitionBean detailBean)
			throws Throwable {
		try {
			serviceDegre_definition.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(Degre_definitionBean beanUpBean) {
		try {
			serviceDegre_definition.doUpdateRowData(beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(Degre_definitionBean beanDelBean) {
		try {
			serviceDegre_definition.doDeleteRowData(beanDelBean);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
