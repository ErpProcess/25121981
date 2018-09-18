package ERP.Process.Commerciale.Entite_etat_commerciale.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.Entite_etat_commerciale.template.Entite_etat_commercialeTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class Entite_etat_commercialeActionManager extends
		Entite_etat_commercialeTemplate {
	  static Entite_etat_commercialeService serviceEntite_etat_commerciale;

	@Autowired
	public void setServiceEntite_etat_commerciale(
			Entite_etat_commercialeService serviceEntite_etat_commerciale) {
		this.serviceEntite_etat_commerciale = serviceEntite_etat_commerciale;
	}
	
	
	public static  ModelAndView doInit_ServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		List <Entite_etat_commercialeBean> list_detail_entite_for_cree= new ArrayList<Entite_etat_commercialeBean>();
		setObjectValueModel(LIST_DETAIL_ENTITE_FOR_CREE, list_detail_entite_for_cree);
		try {

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
	public static ModelAndView doAdd_row_EditableDataTableAjax( ) throws Exception{
		  
		
		
		try {
		 
			String nameVector=getRequest().getParameter("nameList");
			List listOfmyData =(List) getObjectValueModel(nameVector);
			String data_id      =getRequest().getParameter("data_idx")==null?"":getRequest().getParameter("data_idx");
			String data_libelle =getRequest().getParameter("data_libellex")==null?"":getRequest().getParameter("data_libellex");
			
			String data_ordre=getRequest().getParameter("data_ordrex")==null?"0":getRequest().getParameter("data_ordrex");
			 
			String obs_det_entite=getRequest().getParameter("obs_det_entitex")==null?"":getRequest().getParameter("obs_det_entitex");
			
			HashMap  mapdA=ProcessUtil.getHashMap_code_bean(listOfmyData, "data_id");
			if(mapdA.get(data_id)!=null)
				throw new Exception("existe  edeja");
			
			Entite_etat_commercialeBean newBean= new Entite_etat_commercialeBean();
			newBean.setData_id(data_id);
			newBean.setData_libelle(data_libelle);
			
			newBean.setData_ordre(new Integer(data_ordre));
			newBean.setObs_det_entite(obs_det_entite);
			 
			listOfmyData.add(newBean);
			 
			getResponse().setContentType(HTML_CONTENT_TYPE);
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doDelete_row_EditableDataTableAjax( ) throws Exception{
		try {
			String nameVector=getRequest().getParameter("nameList");
			List listOfmyData =(List) getObjectValueModel(nameVector);
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				
				Entite_etat_commercialeBean   newBean= (Entite_etat_commercialeBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
				
			}
			if(!del) throw new Exception ("Cochez au moin une ligne");
			getResponse().setContentType("text");
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doCheked_unCheked( ) throws Exception{
		
		try {
			String nameVector=getRequest().getParameter("nameList");
			List listOfmyData =(List) getObjectValueModel(nameVector);
			String to_check=getRequest().getParameter("statucheked")==null?"":getRequest().getParameter("statucheked");
			for (int i = 0; i < listOfmyData.size(); i++) {
				Entite_etat_commercialeBean newBean =(Entite_etat_commercialeBean) listOfmyData.get(i);
				newBean.setTo_check(to_check);
			}
			getResponse().setContentType(HTML_CONTENT_TYPE);
			} catch (JsonIOException e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
	public static  ModelAndView doSelect_Row() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Entite_etat_commercialeBean rowBean = (Entite_etat_commercialeBean) getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			setObjectValueModel(FORM_BEAN, rowBean);
			
			List listDataSrv = serviceEntite_etat_commerciale.dofetchDatafromServer(rowBean);
			setObjectValueModel(LIST_DETAIL_ENTITE_FOR_CREE_TO_SUPP,  ProcessUtil.cloneList(listDataSrv) );
			setObjectValueModel(LIST_DETAIL_ENTITE_FOR_CREE, listDataSrv);
			if (bs.getFct_id().equals("2"))
				return getViewConsult(FORM_CONSULT_VIEW);
			if (bs.getFct_id().equals("3"))
				return getViewUpdate(FORM_VIEW);
			if (bs.getFct_id().equals("4"))
				return getViewDelete(FORM_CONSULT_VIEW);
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax(FILTER_VIEW);
		}
		return getHome();

	}
	

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(Entite_etat_commercialeBean searchBean)
			throws Throwable {
		try {
			List listDataSrv = serviceEntite_etat_commerciale
					.dofetchDatafromServer(searchBean);
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

	public ModelAndView doAddData(Entite_etat_commercialeBean detailBean) throws Throwable {
		try { 
			if( ((List)(getObjectValueModel(LIST_DETAIL_ENTITE_FOR_CREE))).size()==0)
				throwNewException("listdetailVide");
			serviceEntite_etat_commerciale.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(Entite_etat_commercialeBean beanUpBean) {
		try {
			if( ((List)(getObjectValueModel(LIST_DETAIL_ENTITE_FOR_CREE))).size()==0)
				throwNewException("listdetailVide");
			serviceEntite_etat_commerciale.doUpdateRowData(beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(Entite_etat_commercialeBean beanDelBean) {
		try {
			serviceEntite_etat_commerciale.doDeleteRowData(beanDelBean);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
