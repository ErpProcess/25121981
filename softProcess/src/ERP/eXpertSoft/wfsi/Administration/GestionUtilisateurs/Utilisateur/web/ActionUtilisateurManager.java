package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.web;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.service.ProfileService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.service.UtilisateurService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.template.UtilisateurTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.service.Data_entite_simpleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.service.EtablissementService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model.PaysvilleposteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.service.PaysvilleposteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.service.SocieteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class ActionUtilisateurManager extends UtilisateurTemplate {

	
	
	 
	private static final long serialVersionUID = 7512236664118714896L;
	private UtilisateurService          serviceUtilisateur;
	@Autowired
	public void setServiceUtilisateur(UtilisateurService serviceUtilisateur) {
		this.serviceUtilisateur = serviceUtilisateur;
	}
	
	private Data_entite_simpleService   serviceData_entite_simple;
	@Autowired
	public void setServiceData_entite_simple(Data_entite_simpleService serviceData_entite_simple) {
		this.serviceData_entite_simple = serviceData_entite_simple;
	}
	
	
	private PaysvilleposteService       servicePaysvilleposte;
	@Autowired
	public void setServicePaysvilleposte(PaysvilleposteService servicePaysvilleposte) {
		this.servicePaysvilleposte = servicePaysvilleposte;
	}
	
	private GLangueService              serviceGLangue;
	@Autowired
	public void setServiceGLangue(GLangueService serviceGLangue) {
		this.serviceGLangue = serviceGLangue;
	}
	
	private SocieteService              serviceSociete;
	@Autowired
	public void setServiceSociete(SocieteService serviceSociete) {
		this.serviceSociete = serviceSociete;
	}
	
	private EtablissementService        serviceEtablissement;
	@Autowired
	public void setServiceEtablissement(EtablissementService serviceEtablissement) {
		this.serviceEtablissement = serviceEtablissement;
	}
	
	@Autowired
	private ProfileService              serviceProfile;
	 
	
	
	
	
	
	public ModelAndView doInitServletAction() {
		try {
			
		
		
		setObjectValueModel("listUsr_cath", serviceData_entite_simple.dofetchDataByCodeEntite("USR_CATH"));
		setObjectValueModel("listUsr_piece", serviceData_entite_simple.dofetchDataByCodeEntite("typ_piece"));
		setObjectValueModel("listUsr_sexe",serviceData_entite_simple.dofetchDataByCodeEntite("sexe_usr"));
		setObjectValueModel("listUsr_civil",serviceData_entite_simple.dofetchDataByCodeEntite("situ_usr"));
		setObjectValueModel("listUsr_etat",serviceData_entite_simple.dofetchDataByCodeEntite("ETAT_USR"));
		setObjectValueModel("listUsr_type",serviceData_entite_simple.dofetchDataByCodeEntite("type_usr"));
		setObjectValueModel("listCountry", servicePaysvilleposte.dofetchDatafromServer(new PaysvilleposteBean()));
		setObjectValueModel("listLanG", serviceGLangue.dofetchDatafromServer(new GLangueBean()));
		setObjectValueModel("listSocioTa", serviceSociete.dofetchDatafromServer(new SocieteBean()));
		
		setObjectValueModel("listProfile_for_utlisateur", serviceProfile.dofetchDatafromServer(new ProfileBean()));
		
		
		
		
		// java.util.Date today = new java.util.Date();
		   // System.out.println("dddddddddddddddddddddddddddddddddddd"+new java.sql.Time(today.getTime())); 
		
		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		 

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
	public ModelAndView doFetchDataEtablissement(UtilisateurBean searchBean)throws Throwable {
		
		PrintWriter out = getResponse().getWriter();
        getResponse().setContentType("text/html");
        getResponse().setHeader("Cache-control", "no-cache, no-store");
        getResponse().setHeader("Pragma", "no-cache");
        getResponse().setHeader("Expires", "-1");
        String  codeInput = getRequest().getParameter("codeInput");
        EtablissementBean beanEtab=new EtablissementBean();
        beanEtab.getPk_etab().getSoc_bean().setSoc_id(codeInput);
        List   listData = serviceEtablissement.dofetchDatafromServer(beanEtab);
       
        String query = getRequest().getParameter("term");
        String typeSearch = getRequest().getParameter("typeSearch");
        String fcode = getRequest().getParameter("fieldcode");
        String flabel = getRequest().getParameter("fieldlabel");
        try {
        	 Collection<JSONObject> items=ActionDataTablesManager.doRenderListJson(listData, query, typeSearch, fcode, flabel);
             out.println(items.toString());
             out.close();
        } catch (Exception e) {
        	getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			out.println(e.getMessage());
		    out.close();
		}
        return null;
		
		
		
		
	 

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(UtilisateurBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceUtilisateur.dofetchDatafromServer(searchBean, false);
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

	public ModelAndView doAddData(UtilisateurBean detailBean) throws Throwable {
		try {
			 setObjectValueModel(FORM_BEAN, detailBean);
			 serviceUtilisateur.doCreateRowData(detailBean);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	 

	 

	public ModelAndView doUpdateData(UtilisateurBean beanUpBean) {
		try {
			serviceUtilisateur.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean); 
			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(UtilisateurBean beanDelBean) {
		try {
			serviceUtilisateur.doDeleteRowData(beanDelBean);
			 remove_row_from_list(LIST_DATA);
			 removeObjectModel(FORM_BEAN);
             throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}
	


	

}
