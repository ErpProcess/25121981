package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.web;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.service.SousModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.service.ProfileService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.service.EtablissementService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.service.SocieteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.model.configDevelopementBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.service.configDevelopementService;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.template.configDevelopementTemplate;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import groovy.lang.GroovyShell;

public class configDevelopementActionManager extends configDevelopementTemplate {
	
	 
	private static final long serialVersionUID = -5197688415696144963L;
	
	public static configDevelopementService serviceconfigDevelopement;

	@Autowired
	public void setServiceconfigDevelopement(configDevelopementService serviceconfigDevelopement) {
		this.serviceconfigDevelopement = serviceconfigDevelopement;
	}

	private SocieteService serviceSociete;

	@Autowired
	public void setServiceSociete(SocieteService serviceSociete) {
		this.serviceSociete = serviceSociete;
	}

	@Autowired
	private ProfileService serviceProfile;
	private EtablissementService serviceEtablissement;

	@Autowired
	public void setServiceEtablissement(EtablissementService serviceEtablissement) {
		this.serviceEtablissement = serviceEtablissement;
	}

	private SousModuleService serviceSousModule;

	@Autowired
	public void setServiceSousModule(SousModuleService serviceSousModule) {
		this.serviceSousModule = serviceSousModule;
	}

public   ModelAndView doInitServletAction() {
	 
	try {
		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		
        setObjectValueModel("listSocioTa", serviceSociete.dofetchDatafromServer(new SocieteBean()));
		
		setObjectValueModel("listProfile_for_utlisateur", serviceProfile.dofetchDatafromServer(new ProfileBean()));
		setObjectValueModel("listSousModuleForAfection", serviceSousModule.getSousModuleList(new SousModuleBean()));
		
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
public ModelAndView doFetchDataEtablissement( )throws Throwable {
	
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

public   static  JSONObject doLoadingConfigPrintDocument() throws Exception {
	configDevelopementBean beanSearch = new configDevelopementBean();
	BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
	beanSearch.getFk_etab_Bean().getPk_etab().setSoc_bean(bs.getSociete());
	beanSearch.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
	beanSearch.setApi_action("printDocument");
	JSONObject jsonRe    = new JSONObject( );
	List list=serviceconfigDevelopement.doFetchDatafromServer(beanSearch);
	if(list!=null  &&   list.size()>0) {
    	configDevelopementBean json_properties=(configDevelopementBean) list.get(0);
    	GroovyShell shell = new GroovyShell();
   	    shell.setVariable("bs", bs);
    	JSONObject json    = new JSONObject(json_properties.getJson_properties());
		JSONObject document     = json.getJSONObject("printDocument");
		JSONObject header       = document.getJSONObject("headerDocument");
		String entete="";
		if( header.has("ligne1Body")  &&   !StringUtils.isBlank(header.getString("ligne1Body")))
		entete+=shell.evaluate(header.getString("ligne1Body"))+"\n\r";
		
		if( header.has("ligne2Body")  &&   !StringUtils.isBlank(header.getString("ligne2Body")))
		entete+=shell.evaluate(header.getString("ligne2Body"))+"\n\r";

		if( header.has("ligne3Body")  &&   !StringUtils.isBlank(header.getString("ligne3Body")))
		entete+=shell.evaluate(header.getString("ligne3Body"))+"\n\r";
		
		if( header.has("ligne4Body")  &&   !StringUtils.isBlank(header.getString("ligne4Body")))
		entete+=shell.evaluate(header.getString("ligne4Body"))+"\n\r";
		
		if( header.has("ligne5Body")  &&   !StringUtils.isBlank(header.getString("ligne5Body")))
		entete+=shell.evaluate(header.getString("ligne5Body"))+"\n\r";
		
     	if( header.has("ligne6Body")  &&   !StringUtils.isBlank(header.getString("ligne6Body")))
  		 entete+=shell.evaluate(header.getString("ligne6Body"))+"\n\r";
     	
     	if( header.has("ligne6Body")  &&   !StringUtils.isBlank(header.getString("ligne6Body")))
     		 entete+=shell.evaluate(header.getString("ligne6Body"))+"\n\r";
    	
     	if( document.has("espaceTotalDesous") )
     		jsonRe.put("espaceTotalDesous", document.getInt("espaceTotalDesous"));
 
    	if( document.has("pageEvent") )
    		jsonRe.put("pageEvent", document.getBoolean("pageEvent"));
    	else
    		jsonRe.put("pageEvent", false);
     	
     	jsonRe.put("entete", entete);
     	
    	return  jsonRe;
    	
	}else {
		return null ;
	}
	

}


public   static  JSONObject doLoadingConfigPrintDocumentRetenue() throws Exception {
	configDevelopementBean beanSearch = new configDevelopementBean();
	BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
	beanSearch.getFk_etab_Bean().getPk_etab().setSoc_bean(bs.getSociete());
	beanSearch.getFk_etab_Bean().getPk_etab().setEtab_id(bs.getEtab_id());
	JSONObject jsonRe    = new JSONObject( );
	List list=serviceconfigDevelopement.doFetchDatafromServer(beanSearch);
	if(list!=null  &&   list.size()>0) {
    	configDevelopementBean json_properties=(configDevelopementBean) list.get(0);
//    	GroovyShell shell = new GroovyShell();
//   	    shell.setVariable("bs", bs);
    	JSONObject json    = new JSONObject(json_properties.getJson_properties());
		JSONObject document     = json.getJSONObject("printRetenue");
		JSONObject header       = document.getJSONObject("headerDocument");
		String entete="";
		if( header.has("ligne1Body")  &&   !StringUtils.isBlank(header.getString("ligne1Body")))
		entete+= header.getString("ligne1Body") +"\n\r";
		
		if( header.has("ligne2Body")  &&   !StringUtils.isBlank(header.getString("ligne2Body")))
		entete+= header.getString("ligne2Body") +"\n\r";

		if( header.has("ligne3Body")  &&   !StringUtils.isBlank(header.getString("ligne3Body")))
		entete+= header.getString("ligne3Body") +"\n\r";
		
		if( header.has("ligne4Body")  &&   !StringUtils.isBlank(header.getString("ligne4Body")))
		entete+= header.getString("ligne4Body") +"\n\r";
		
		if( header.has("ligne5Body")  &&   !StringUtils.isBlank(header.getString("ligne5Body")))
		entete+= header.getString("ligne5Body") +"\n\r";
		
     	if( header.has("ligne6Body")  &&   !StringUtils.isBlank(header.getString("ligne6Body")))
  		 entete+= header.getString("ligne6Body") +"\n\r";
     	
     	if( header.has("ligne7Body")  &&   !StringUtils.isBlank(header.getString("ligne7Body")))
     		 entete+= header.getString("ligne7Body") +"\n\r";
    	
     	if( document.has("espaceTotalDesous") )
     		jsonRe.put("espaceTotalDesous", document.getInt("espaceTotalDesous"));
 
    	if( document.has("pageEvent") )
    		jsonRe.put("pageEvent", document.getBoolean("pageEvent"));
    	else
    		jsonRe.put("pageEvent", false);
     	
     	jsonRe.put("entete", entete);
     	
    	return  jsonRe;
    	
	}else {
		return null ;
	}
	

}


@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(configDevelopementBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceconfigDevelopement.doFetchDatafromServer(searchBean);
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
public ModelAndView doAddData(configDevelopementBean detailBean) throws Throwable {
     try {
			setObjectValueModel(FORM_BEAN, detailBean);
            serviceconfigDevelopement.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(configDevelopementBean beanUpBean) {	 
	 	try {
	 serviceconfigDevelopement.doUpdateRowData(beanUpBean); 
			update_row_from_list(LIST_DATA, beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(configDevelopementBean beanDelBean) {
    try {
     serviceconfigDevelopement.doDeleteRowData(beanDelBean);
			    remove_row_from_list(LIST_DATA);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
