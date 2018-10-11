package ERP.Process.Commerciale.Vente.Client.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Type_tarification.service.Type_tarificationService;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Client.service.ClientService;
import ERP.Process.Commerciale.Vente.Client.template.ClientTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model.CompteBancaireBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.service.CompteBancaireService;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

public class ClientActionManager extends ClientTemplate {
	 
	private static final long serialVersionUID = 203276103945303182L;
	
	private Type_tarificationService   serviceType_tarification;
	@Autowired
	public void setServiceTarification(Type_tarificationService serviceType_tarification) {
		this.serviceType_tarification = serviceType_tarification;
	}
	
	private ClientService serviceClient;
	@Autowired
	public void setServiceClient(ClientService serviceClient) {
		this.serviceClient = serviceClient;
	}
	 private CompteBancaireService  serviceCompteBancaire;
		@Autowired
		public void setServiceCompteBancaire(CompteBancaireService serviceCompteBancaire) {
		    this.serviceCompteBancaire = serviceCompteBancaire;
		} 
	
	public    ModelAndView doInitServletAction() {

		
		
		
	 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			List list_type_tarification=serviceType_tarification.doFetchDatafromServer(Type_tarificationBean.class.newInstance());
			setObjectValueModel(LIST_TYPE_TARIF_CLIENT, list_type_tarification);
			
			 
			setObjectValueModel(LIST_CPT_BANK, serviceCompteBancaire.doFetchDatafromServer(CompteBancaireBean.class.newInstance()));
			
			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
				return getViewAdd( FORM_VIEW );
			} else {
				return getViewFilterAjax( FILTER_VIEW );

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(ClientBean searchBean) throws Throwable {
		try {
			setObjectValueModel(SEARCH_BEAN, searchBean);
			List listDataSrv = serviceClient.doFetchDatafromServer(searchBean);
			
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			getResponse().setStatus(500);
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	public ModelAndView doAddData(ClientBean detailBean) throws Throwable {
		try {
			 String dataSocieteLng_ar= getRequest().getParameter("dataSocieteLng_ar");
			 String dataSocieteLng_en= getRequest().getParameter("dataSocieteLng_en");
			 JSONObject json        = new JSONObject();
			 json.put("ar",  convertStringToHashMap(dataSocieteLng_ar));
			 json.put("en", convertStringToHashMap(dataSocieteLng_en));
			 String data_societe_langue=json.toString();
			detailBean.setData_client_langue(data_societe_langue);
			setObjectValueModel(FORM_BEAN, detailBean);
			serviceClient.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}
	
	public    ModelAndView doGetRowDataBean() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			ClientBean rowBean = (ClientBean)getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			if( !StringUtils.isBlank(rowBean.getData_client_langue()) ) {
			JSONObject jsonObj = new JSONObject(rowBean.getData_client_langue());
			HashMap<String, Object> yourHashMap = new Gson().fromJson(jsonObj.toString(), HashMap.class);
			Map<String,Object> yearMap = yourHashMap;  
			rowBean.setMaplang(yearMap);
			}
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("2"))
				return getViewConsult((String) getObjectValueModel("FORM_VIEW"));
			if (bs.getFct_id().equals("3"))
				return getViewUpdate((String) getObjectValueModel("FORM_VIEW"));
			if (bs.getFct_id().equals("4"))
				return getViewDelete((String) getObjectValueModel("FORM_VIEW"));
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}

	public ModelAndView doUpdateData(ClientBean beanUpBean) {
		try {
			
			 String dataSocieteLng_ar= getRequest().getParameter("dataSocieteLng_ar");
			 String dataSocieteLng_en= getRequest().getParameter("dataSocieteLng_en");
			 JSONObject json        = new JSONObject();
			 json.put("ar",  convertStringToHashMap(dataSocieteLng_ar) );
			 json.put("en", convertStringToHashMap(dataSocieteLng_en) );
			 String data_societe_langue=json.toString();
			 beanUpBean.setData_client_langue(data_societe_langue);
			 
			serviceClient.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(ClientBean beanDelBean) {
		try {
			serviceClient.doDeleteRowData(beanDelBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
