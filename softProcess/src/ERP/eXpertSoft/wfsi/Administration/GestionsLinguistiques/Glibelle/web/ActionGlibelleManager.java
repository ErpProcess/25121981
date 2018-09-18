package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jquery.datatables.model.Company;
import jquery.datatables.model.DataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.IdLiblleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.service.GlibelleService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.util.GLibelleTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model.TypelibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.service.TypelibelleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.MessageBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

public class ActionGlibelleManager extends GLibelleTemplate {

	public static GlibelleService serviceGlibelle;
	private TypelibelleService typelibelleService;
	private GLangueService glangueservice;

	@Autowired
	public void setGLangueService(GLangueService glangueservice) {
		this.glangueservice = glangueservice;
	}

	@Autowired
	public void setServiceGlibelle(GlibelleService serviceGlibelle) {
		this.serviceGlibelle = serviceGlibelle;
	}

	@Autowired
	public void setTypelibelleService(TypelibelleService typelibelleService) {
		this.typelibelleService = typelibelleService;
	}

	public ModelAndView doInitServletAction() {

		BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		setObjectValueModel(FORM_BEAN, new GlibelleBean());
		setObjectValueModel(SEARCH_BEAN, new GlibelleBean());
		removeObjectModel("listlibLangue");

		removeObjectModel(LIST_DATA);
		List listLangue = glangueservice
				.dofetchDatafromServer(new GLangueBean());
		setObjectValueModel("listLangue", listLangue);

		List listType = typelibelleService
				.getListDataServer(new TypelibelleBean());
		setObjectValueModel("listType", listType);

		if (bs.getFct_id().equals("1")) {

			List listlang = new ArrayList();
			for (int i = 0; i < listLangue.size(); i++) {
				GLangueBean gBean = (GLangueBean) listLangue.get(i);
				GlibelleBean beanInsert = new GlibelleBean();
				IdLiblleBean idbean = new IdLiblleBean();
				idbean.setLang_id(gBean.getLang_id());
				beanInsert.setIdLiblleBean(idbean);
				beanInsert.setLanglibelle(gBean.getLang_libelle());
				listlang.add(beanInsert);
			}
			setObjectValueModel("listlibLangue", listlang);

			return getViewAdd(FORM_VIEW);
		} else {

			return getViewFilterAjax(FILTER_VIEW);
		}

	}
	
	
	public  ModelAndView doupdate( ) throws ServletException, IOException {
		
		String id = getRequest().getParameter("id") ;
		//int columnId = Integer.parseInt(getRequest().getParameter("columnId"));
		int columnPosition = Integer.parseInt(getRequest().getParameter("columnPosition"));
		//int rowId = Integer.parseInt(getRequest().getParameter("rowId"));
		String value = getRequest().getParameter("value");
		List<GlibelleBean> listlibLangue =(List<GlibelleBean>) getObjectValueModel("listlibLangue");
		
		for(GlibelleBean bean: listlibLangue)
		{
			if(bean.getIdLiblleBean().getLang_id().equals(id))
			{
				switch (columnPosition)
	            {
	                case 2:
	                	bean.setLib_libelle(value);
	                    break;
	                case 3:
	                	bean.setLib_abrv(value);
	                    break;
	                default:
	                    break;
	            }
				getResponse().getWriter().print(value);
				return null;
			}
		}
		getResponse().getWriter().print("Error - company cannot be found");
		return null;
	}

	public   ModelAndView doSelectRow() { 

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Object rowBean = getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("2"))
				return getViewConsult(PAGE_FORM_Update);
			if (bs.getFct_id().equals("3"))
				return getViewUpdate(PAGE_FORM_Update);
			if (bs.getFct_id().equals("4"))
				return getViewDelete(PAGE_FORM_Update);
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax(LIST_VIEW);
		}
		return getHome();

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchDataAjax(GlibelleBean searchBean)
			throws Throwable, Throwable {

		try {
			List listDataSrv = serviceGlibelle.getListDataServer(searchBean);
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

	public ModelAndView doAdd_row_editable_table_ajax() throws Throwable, Throwable {
		
		
		String tr = "";
		List   lisData=(List) getObjectValueModel("listlibLangue");
		try{
			GlibelleBean beanJdid= new GlibelleBean();
			beanJdid.getIdLiblleBean().setLang_id("gy");
			beanJdid.setLanglibelle("gray");
			lisData.add(beanJdid);

		} catch (Exception e) {
 
		}
			
			
			return null;
		}
	
	public ModelAndView doAddData(GlibelleBean beanInsert) {

		String tr = "";
		List listLibbelle = (List) getObjectValueModel("listlibLangue");
		try{
		for (int i = 0; i < listLibbelle.size(); i++) {
			GlibelleBean bean = (GlibelleBean) listLibbelle.get(i);
			bean.setType_lib_id(beanInsert.getType_lib_id());
			bean.getIdLiblleBean().setLib_id(
					beanInsert.getIdLiblleBean().getLib_id());
		}

		 serviceGlibelle.CreateRowData(listLibbelle) ;
		  throwNewException("Insertion Reussit");
		
		} catch (Exception e) {
			displayException(e);
		}
			
			
			return getViewAdd(FORM_VIEW);
		}

	public ModelAndView doRetourToHome(GlibelleBean pack) {
		return getHome();

	}

	public ModelAndView doUpdateData(GlibelleBean beanUpBean) {
		JsonResponse res = new JsonResponse();
		GlibelleBean pSysBean = (GlibelleBean) getObjectValueModel(FORM_BEAN);
		try {
			serviceGlibelle.UpdateRowData(beanUpBean);
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List listoo = (List) getObjectValueModel(LIST_DATA);
			listoo.remove(Integer.parseInt(indexo));
			listoo.add(beanUpBean);
			setObjectValueModel(LIST_DATA, listoo);

			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(GlibelleBean beanDelBean) {
		JsonResponse res = new JsonResponse();

		GlibelleBean pSysBean = (GlibelleBean) getObjectValueModel(FORM_BEAN);
		try {
			serviceGlibelle.DeleteRowData(beanDelBean);
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List listoo = (List) getObjectValueModel(LIST_DATA);
			listoo.remove(Integer.parseInt(indexo));
			setObjectValueModel(LIST_DATA, listoo);
			throwNewException("Reussite delete");
			
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}

}
