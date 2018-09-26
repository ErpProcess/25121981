package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.web;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.template.UniteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.service.UniteService;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import com.google.gson.JsonIOException;

public class UniteActionManager extends UniteTemplate {
	
	private UniteService serviceUnite;
	@Autowired
	public void setServiceUnite(UniteService serviceUnite) {
		this.serviceUnite = serviceUnite;
	}
	@Autowired  public  Code_barreService      serviceCode_barre;

	protected ModelAndView doInitServletAction() {

		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Code_barreBean searchBean = new Code_barreBean();
			searchBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
			searchBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean()
					.setSoc_id(bs.getSoc_id());
			List list_articleWithUnit = serviceCode_barre.doFetchDatafromServerNew(searchBean);
			setObjectValueModel("list_articleWithUnit", list_articleWithUnit);
			HashMap  map_article=ProcessUtil.getHashMap_code_bean(list_articleWithUnit, "pk.code_barre");
			setObjectValueModel("map_article_unite", map_article);
			
			if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")) {
				return getViewAdd(FORM_VIEW);
			} else {
				return getViewFilterAjax(FILTER_VIEW);

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(UniteBean searchBean) throws Throwable {
		try {
			List listDataSrv = serviceUnite.doFetchDatafromServer(searchBean);
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

	public ModelAndView doAddData(UniteBean detailBean) throws Throwable {
		try {
			serviceUnite.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(UniteBean beanUpBean) {
		try {
			serviceUnite.doUpdateRowData(beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(UniteBean beanDelBean) {
		try {
			serviceUnite.doDeleteRowData(beanDelBean);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
