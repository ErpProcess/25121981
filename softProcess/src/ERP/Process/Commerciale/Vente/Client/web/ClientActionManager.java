package ERP.Process.Commerciale.Vente.Client.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Type_tarification.service.Type_tarificationService;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Client.service.ClientService;
import ERP.Process.Commerciale.Vente.Client.template.ClientTemplate;
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
			BeanSession  bs =(BeanSession) getObjectValueModel(BEAN_SESSION);
			
		 
			
			setObjectValueModel(FORM_BEAN, detailBean);
			serviceClient.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(ClientBean beanUpBean) {
		try {
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
