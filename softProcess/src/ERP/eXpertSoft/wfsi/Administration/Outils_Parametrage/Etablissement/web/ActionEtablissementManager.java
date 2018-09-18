package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.service.Data_entite_simpleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.service.DeviseService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.service.EtablissementService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.template.EtablissementTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.service.NumSequentielService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model.PaysvilleposteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.service.PaysvilleposteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.service.SocieteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class ActionEtablissementManager extends EtablissementTemplate {

	private EtablissementService  serviceEtablissement;
	@Autowired
	public void setServiceEtablissement(EtablissementService serviceEtablissement) {
		this.serviceEtablissement = serviceEtablissement;
	}
	
	 
	private SocieteService              serviceSociete;
	@Autowired
	public void setServiceSociete(SocieteService serviceSociete) {
		this.serviceSociete = serviceSociete;
	}
	private DeviseService     serviceDevise;
	@Autowired
	public void setServiceDevise(DeviseService serviceDevise) {
		this.serviceDevise = serviceDevise;
	}
	
	private Data_entite_simpleService   serviceData_entite_simple;
	@Autowired
	public void setServiceData_entite_simple(Data_entite_simpleService serviceData_entite_simple) {
		this.serviceData_entite_simple = serviceData_entite_simple;
	}
	
	
	public ModelAndView doInitServletAction() {
 
		
		try {
			setObjectValueModel("listSocioTa", serviceSociete.dofetchDatafromServer(new SocieteBean()));
			setObjectValueModel("list_etab_type", serviceData_entite_simple.dofetchDataByCodeEntite("etab_type"));
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			setObjectValueModel("list_devise", serviceDevise.doFetchDatafromServer(new DeviseBean()));
			if (bs.getFct_id().equals("1")) {
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
	public ModelAndView doFetchData(EtablissementBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceEtablissement.dofetchDatafromServer(searchBean);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (JsonIOException e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;

	}

	public ModelAndView doAddData(EtablissementBean detailBean) throws Throwable {
		try {
			 serviceEtablissement.doCreateRowData(detailBean);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	 

	public ModelAndView doRetourToHome(EtablissementBean pack) {
		return getHome();

	}

	public ModelAndView doUpdateData(EtablissementBean beanUpBean) {
		 
		try {
			serviceEtablissement.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);

			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(EtablissementBean beanDelBean) {

		 
		try {
			serviceEtablissement.doDeleteRowData(beanDelBean);
			 remove_row_from_list(LIST_DATA);
			throwNewException("Reussite delete");
			
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}
	 

}
