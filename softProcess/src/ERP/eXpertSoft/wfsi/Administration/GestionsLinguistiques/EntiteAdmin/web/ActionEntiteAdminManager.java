package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jquery.datatables.controller.DataEditableTablesParamUtility;
import jquery.datatables.controller.EditableDataTableRequestParam;
import jquery.datatables.model.Company;
import jquery.datatables.model.DataRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.service.ModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.service.PackageSysService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.service.SousModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.service.SousPackageService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.IdEntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.service.EntiteAdminService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.util.EntiteAdminTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.IdLiblleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.service.GlibelleService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.util.GLibelleTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.web.ActionGlibelleManager;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model.TypelibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.service.TypelibelleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.MessageBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

public class ActionEntiteAdminManager extends EntiteAdminTemplate {

	public static EntiteAdminService serviceEntiteAdmin;
	private TypelibelleService typelibelleService;
	private GLangueService glangueservice;
	private PackageSysService   servicePackageSys;
	private SousPackageService     serviceSousPack;
	private ModuleService       serviceModule;
	private SousModuleService   serviceSousModule;
	private static List listData;
	
	
	@Autowired
	public void setGlangueservice(GLangueService glangueservice) {
		this.glangueservice = glangueservice;
	}
	@Autowired
	public void setServicePackageSys(PackageSysService servicePackageSys) {
		this.servicePackageSys = servicePackageSys;
	}
	@Autowired
	public void setServiceSousPack(SousPackageService serviceSousPack) {
		this.serviceSousPack = serviceSousPack;
	}

	@Autowired
	public void setServiceModule(ModuleService serviceModule) {
		this.serviceModule = serviceModule;
	}

	@Autowired
	public void setServiceSousModule(SousModuleService serviceSousModule) {
		this.serviceSousModule = serviceSousModule;
	}

	@Autowired
	public void setGLangueService(GLangueService glangueservice) {
		this.glangueservice = glangueservice;
	}

	@Autowired
	public void setServiceEntiteAdmin(EntiteAdminService serviceEntiteAdmin) {
		this.serviceEntiteAdmin = serviceEntiteAdmin;
	}

	@Autowired
	public void setTypelibelleService(TypelibelleService typelibelleService) {
		this.typelibelleService = typelibelleService;
	}
 

	public ModelAndView doInitServletAction() {
          try {
		BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		setObjectValueModel(FORM_BEAN, new EntiteAdminBean());
		setObjectValueModel(SEARCH_BEAN, new EntiteAdminBean());
		removeObjectModel("listPreparerEntiteAdmin");
		removeObjectModel(LIST_DATA);
		
		List listPackageSys=servicePackageSys.getPackageSysList(new PackageSysBean());
		setObjectValueModel("listPackageSys", listPackageSys);
		List listLangue = glangueservice.dofetchALLDatafromServer(new GLangueBean());
		
		
		
		setObjectValueModel("listLangueEntite", listLangue);
		List listType = typelibelleService.getListDataServer(new TypelibelleBean());
		setObjectValueModel("listType", listType);
		
		if (bs.getFct_id().equals("1")) {
			return getViewInit_cree(FORM_INIT_CREE_ENTITEADMIN);
		}else if(bs.getFct_id().equals("12")){
			return getViewAdd(FORM_UNIQUE_CREE_ENTITEADMIN);
		}else {
			setObjectValueModel("sousModuleListio", serviceSousModule.getSousModuleList(new SousModuleBean()));
			return getViewFilterAjax(FILTER_VIEW);
		}
		
		
          } catch (Exception e) {
        		return getHome();
          }

	}
 

	
	public static  ModelAndView doResetForm() {
		removeObjectModel( "listPreparerEntiteAdmin"  );
		return getViewInit_cree(FORM_INIT_CREE_ENTITEADMIN);
	}
	
public ModelAndView doFetchDataSousPackage()throws Throwable {
		
		PrintWriter out = getResponse().getWriter();
        getResponse().setContentType("text/html");
        
        String  codeInput = getRequest().getParameter("codeInput");
        
        SousPackageBean beanSearch=new  SousPackageBean();
        beanSearch.getPackageSys().setPack_id(BigDecimal.valueOf(Integer.parseInt(codeInput)));
        List   listData = serviceSousPack.getSousPackListVi(beanSearch);
        
        setObjectValueModel("listDataSousPack", listData);
       
        String query = getRequest().getParameter("term");
        String typeSearch = getRequest().getParameter("typeSearch");
        String fcode = getRequest().getParameter("fieldcode");
        String flabel = getRequest().getParameter("fieldlabel");
        
          
        
        System.out.println(query);
      
         
        Collection<JSONObject> items = new ArrayList<JSONObject>();
        try {
        for(int i=0; i<listData.size(); i++) {
        	Object bean =(Object) listData.get(i);
        	String element="";
        	 
        	JSONObject  jsonObj = new JSONObject ();
        	if(typeSearch!=null && typeSearch.equals("bylabel")){
        		Class<?> c = bean.getClass();
        		Field f = c.getDeclaredField(flabel);
        		f.setAccessible(true);
        		Object valueOfField =f.get(bean);
        		element = String.valueOf(valueOfField);
        		jsonObj.put(flabel,element);
        		
        		Field fcodo = c.getDeclaredField(fcode);
        		fcodo.setAccessible(true);
        		Object valueOfFieldcodo = fcodo.get(bean);
        		String elementcodo =String.valueOf(valueOfFieldcodo);
        		jsonObj.put(fcode,elementcodo);
        		
        	}
        	 
        	if(typeSearch!=null && typeSearch.equals("byId")){
        		Class<?> c = bean.getClass();
        		Field f = c.getDeclaredField(fcode);
        		f.setAccessible(true);
        		Object valueOfField = f.get(bean);
        		element =String.valueOf(valueOfField).toLowerCase();
        		jsonObj.put(fcode,element);
        		
        		Field flibo = c.getDeclaredField(flabel);
        		flibo.setAccessible(true);
        		Object valueOfFieldlibo = flibo.get(bean);
        		String elementlibo =String.valueOf(valueOfFieldlibo);
        		jsonObj.put(flabel,elementlibo);
        		
        	}
         
        	  if(element.toLowerCase().startsWith(query.toLowerCase())   ) {
        		  items.add(jsonObj);
                }
        }
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

public ModelAndView doFetchDataModule()throws Throwable {
	
	PrintWriter out = getResponse().getWriter();
    getResponse().setContentType("text/html");
    String  codeInput = getRequest().getParameter("codeInput");
    ModuleBean beanSearch=new  ModuleBean();
    beanSearch.getSousPackBean().setSpack_id(BigDecimal.valueOf(Integer.parseInt(codeInput)) );
    List   listData = serviceModule.getSomeModules(beanSearch);
    
    setObjectValueModel("listDataModule", listData);
   
    String query = getRequest().getParameter("term");
    String typeSearch = getRequest().getParameter("typeSearch");
    String fcode = getRequest().getParameter("fieldcode");
    String flabel = getRequest().getParameter("fieldlabel");
    
      
    
    System.out.println(query);
  
     
    Collection<JSONObject> items = new ArrayList<JSONObject>();
    try {
    for(int i=0; i<listData.size(); i++) {
    	Object bean =(Object) listData.get(i);
    	String element="";
    	 
    	JSONObject  jsonObj = new JSONObject ();
    	if(typeSearch!=null && typeSearch.equals("bylabel")){
    		Class<?> c = bean.getClass();
    		Field f = c.getDeclaredField(flabel);
    		f.setAccessible(true);
    		Object valueOfField =f.get(bean);
    		element = String.valueOf(valueOfField);
    		jsonObj.put(flabel,element);
    		
    		Field fcodo = c.getDeclaredField(fcode);
    		fcodo.setAccessible(true);
    		Object valueOfFieldcodo = fcodo.get(bean);
    		String elementcodo =String.valueOf(valueOfFieldcodo);
    		jsonObj.put(fcode,elementcodo);
    		
    	}
    	 
    	if(typeSearch!=null && typeSearch.equals("byId")){
    		Class<?> c = bean.getClass();
    		Field f = c.getDeclaredField(fcode);
    		f.setAccessible(true);
    		Object valueOfField = f.get(bean);
    		element =String.valueOf(valueOfField).toLowerCase();
    		jsonObj.put(fcode,element);
    		
    		Field flibo = c.getDeclaredField(flabel);
    		flibo.setAccessible(true);
    		Object valueOfFieldlibo = flibo.get(bean);
    		String elementlibo =String.valueOf(valueOfFieldlibo);
    		jsonObj.put(flabel,elementlibo);
    		
    	}
     
    	  if(element.toLowerCase().startsWith(query.toLowerCase())   ) {
    		  items.add(jsonObj);
            }
    }
    out.println(items.toString());
    out.close();
    } catch (Exception e) {
		getResponse().setContentType(HTML_CONTENT_TYPE);
		out.println(e.getMessage());
		out.close();
	}
    return null;
}


    public ModelAndView doFetchDataSOUSModule()throws Throwable {
	
	PrintWriter out = getResponse().getWriter();
    getResponse().setContentType("text/html");
    String  codeInput = getRequest().getParameter("codeInput");
   
    //List   listData = serviceSousModule.getSousModules(codeInput);
    
   List   listData = serviceSousModule.getSousModuleList(SousModuleBean.class.newInstance());
    
    setObjectValueModel("listSousModuleAjax", listData);
    String query = getRequest().getParameter("term");
    String typeSearch = getRequest().getParameter("typeSearch");
    String fcode = getRequest().getParameter("fieldcode");
    String flabel = getRequest().getParameter("fieldlabel");
    Collection<JSONObject> items = new ArrayList<JSONObject>();
    try {
    for(int i=0; i<listData.size(); i++) {
    	Object bean =(Object) listData.get(i);
    	String element="";
    	JSONObject  jsonObj = new JSONObject ();
    	if(typeSearch!=null && typeSearch.equals("bylabel")){
    		Class<?> c = bean.getClass();
    		Field f = c.getDeclaredField(flabel);
    		f.setAccessible(true);
    		Object valueOfField =f.get(bean);
    		element = String.valueOf(valueOfField);
    		jsonObj.put(flabel,element);
    		
    		Field fcodo = c.getDeclaredField(fcode);
    		fcodo.setAccessible(true);
    		Object valueOfFieldcodo = fcodo.get(bean);
    		String elementcodo =String.valueOf(valueOfFieldcodo);
    		jsonObj.put(fcode,elementcodo);
    		
    	}
    	 
    	if(typeSearch!=null && typeSearch.equals("byId")){
    		Class<?> c = bean.getClass();
    		Field f = c.getDeclaredField(fcode);
    		f.setAccessible(true);
    		Object valueOfField = f.get(bean);
    		element =String.valueOf(valueOfField).toLowerCase();
    		jsonObj.put(fcode,element);
    		
    		Field flibo = c.getDeclaredField(flabel);
    		flibo.setAccessible(true);
    		Object valueOfFieldlibo = flibo.get(bean);
    		String elementlibo =String.valueOf(valueOfFieldlibo);
    		jsonObj.put(flabel,elementlibo);
    		
    	}
     
    	  if(element.toLowerCase().startsWith(query.toLowerCase())   ) {
    		  items.add(jsonObj);
            }
    }
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
   


    public ModelAndView doLoadColumnsFromEntite()throws Throwable {
    	
    	PrintWriter out = getResponse().getWriter();
        getResponse().setContentType("text/html");
        String  codeInput = getRequest().getParameter("codeInput");
        EntiteAdminBean beanSerach=new EntiteAdminBean();
        beanSerach.setSousmod_id(codeInput);
        List   listData = serviceEntiteAdmin.getListDataServer(beanSerach);
        String query =      getRequest().getParameter("term");
        String typeSearch = getRequest().getParameter("typeSearch");
        String fcode =      getRequest().getParameter("fieldcode");
        String flabel =     getRequest().getParameter("fieldlabel");
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
public static ModelAndView doPreparerEntiteToInsert( EntiteAdminBean detailBean ) throws Throwable{
	 
	try {
		 
		  
		  List   listDataTrie = (List) getObjectValueModel(getRequest().getParameter("nameList"));
		 
		  if(listDataTrie==null ||  listDataTrie.size()==0){
			  EntiteAdminBean   beanSearch = new  EntiteAdminBean();
			  beanSearch.setSousmod_id(detailBean.getSousmod_id());
			  beanSearch.getPk_entite_admin().setLang_id(detailBean.getPk_entite_admin().getLang_id());
		      List listData=serviceEntiteAdmin.getListDataServer(beanSearch);
			  HashMap  mapData=new HashMap();
			  if(listData!=null  &&  listData.size()>0){
					  for (int i = 0; i < listData.size(); i++) {
						  EntiteAdminBean beanS=(EntiteAdminBean) listData.get(i);
						  mapData.put(beanS.getColumn_name(), beanS.getPk_entite_admin().getEnt_id());
					    }
			   }
				  Connection con = null;
			      Class.forName("org.postgresql.Driver");
			      con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Process_DB","postgres", "manager");
			      DatabaseMetaData meta = con.getMetaData();
			      ResultSet res = meta.getColumns(null,null, detailBean.getTable_name(), null);
			      listDataTrie =new LinkedList<Object>();
			      while (res.next()) {
			         //System.out.println("    "+res.getString("TABLE_SCHEM") + ", "+res.getString("TABLE_NAME") + ", "+res.getString("COLUMN_NAME") + ", "+res.getString("TYPE_NAME") + ", "+res.getInt("COLUMN_SIZE")+ ", "+res.getString("NULLABLE")); 
			         if(mapData!=null  &&  mapData.size()>0){
			        	 String key=(String) mapData.get(res.getString("COLUMN_NAME"));
			        	       if(key==null) {
			        	    	     EntiteAdminBean   beanIns = new  EntiteAdminBean();
					        		 String id1=detailBean.getSousmod_id()+"/"+res.getString("COLUMN_NAME");
					        		 String id2=detailBean.getPk_entite_admin().getLang_id();
					        		 beanIns.setId_entite(id1+"£"+id2);
							         beanIns.getPk_entite_admin().setLang_id(detailBean.getPk_entite_admin().getLang_id());
							         beanIns.getPk_entite_admin().setEnt_id(detailBean.getSousmod_id()+"/"+res.getString("COLUMN_NAME"));
							         beanIns.setEnt_libelle(res.getString("COLUMN_NAME"));
							         beanIns.setType_lib_id("0002");
							         beanIns.setTo_check("");
							         beanIns.setEnt_abrv(res.getString("COLUMN_NAME"));
							         beanIns.setSousmod_id(detailBean.getSousmod_id());
							         beanIns.setMod_id(detailBean.getMod_id());
							         beanIns.setPack_id(detailBean.getPack_id());
							         beanIns.setSpack_id(detailBean.getSpack_id());
							         beanIns.setTable_schem(res.getString("TABLE_SCHEM")!=null?res.getString("TABLE_SCHEM"):"");
							         beanIns.setTable_name(res.getString("TABLE_NAME")!=null?res.getString("TABLE_NAME"):"");
							         beanIns.setColumn_name(res.getString("COLUMN_NAME")!=null?res.getString("COLUMN_NAME"):"");
							         beanIns.setType_name(res.getString("TYPE_NAME")!=null?res.getString("TYPE_NAME"):"");
							         beanIns.setColumn_size(String.valueOf(res.getInt("COLUMN_SIZE"))!=null?String.valueOf(res.getInt("COLUMN_SIZE")):"");
							         listDataTrie.add(beanIns);
			        	    	     }else{
			        	    	    	continue; 
			        	    	     }
			         } else{
			        		 EntiteAdminBean   beanIns = new  EntiteAdminBean();
			        		 String id1=detailBean.getSousmod_id()+"/"+res.getString("COLUMN_NAME");
			        		 String id2=detailBean.getPk_entite_admin().getLang_id();
			        		 beanIns.setId_entite(id1+"£"+id2);
					         beanIns.getPk_entite_admin().setLang_id(detailBean.getPk_entite_admin().getLang_id());
					         beanIns.getPk_entite_admin().setEnt_id(detailBean.getSousmod_id()+"/"+res.getString("COLUMN_NAME"));
					         beanIns.setEnt_libelle(res.getString("COLUMN_NAME"));
					         beanIns.setType_lib_id("0002");
					         beanIns.setTo_check("");
					         beanIns.setEnt_abrv(res.getString("COLUMN_NAME"));
					         beanIns.setSousmod_id(detailBean.getSousmod_id());
					         beanIns.setMod_id(detailBean.getMod_id());
					         beanIns.setPack_id(detailBean.getPack_id());
					         beanIns.setSpack_id(detailBean.getSpack_id());
					         beanIns.setTable_schem(res.getString("TABLE_SCHEM")!=null?res.getString("TABLE_SCHEM"):"");
					         beanIns.setTable_name(res.getString("TABLE_NAME")!=null?res.getString("TABLE_NAME"):"");
					         beanIns.setColumn_name(res.getString("COLUMN_NAME")!=null?res.getString("COLUMN_NAME"):"");
					         beanIns.setType_name(res.getString("TYPE_NAME")!=null?res.getString("TYPE_NAME"):"");
					         beanIns.setColumn_size(String.valueOf(res.getInt("COLUMN_SIZE"))!=null?String.valueOf(res.getInt("COLUMN_SIZE")):"");
					         listDataTrie.add(beanIns);
			        		 
			        	 }
			         }
			      res.close();
			      con.close();
			      setObjectValueModel(getRequest().getParameter("nameList"),listDataTrie);
		  }
		JsonObject jsonResponse = new JsonObject(); 
		JsonArray data = new JsonArray();  
		EditableDataTableRequestParam   parameter=DataEditableTablesParamUtility.getParam(getRequest());
		List   listDataAjax=DataEditableTablesParamUtility.doGetAzizWild3asoul(parameter,listDataTrie, jsonResponse, data, getRequest(), getResponse());
		setObjectValueModel("kk"+parameter.sNameList,listDataAjax);
		jsonResponse.add(JSON_DATA_RESPONSE, data);
		getResponse().setContentType(JSON_CONTENT_TYPE);
		getResponse().getWriter().print(jsonResponse.toString());
		} catch (JsonIOException e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
}



public ModelAndView getTAbleAnd_Schema() throws JSONException, IOException {
	    
	 List   listSousModuleAjax = (List) getObjectValueModel("listSousModuleAjax");
	 String LiginIndex=getRequest().getParameter("vapToSend");
	 boolean teste=false;
	 JSONArray  listData = new JSONArray();
	 for (int i = 0; i < listSousModuleAjax.size(); i++) {
		 SousModuleBean bea= (SousModuleBean) listSousModuleAjax.get(i);
		 if(String.valueOf(bea.getSousmod_id()).equals(LiginIndex)){
			 JSONObject  element = new JSONObject();
			 element.put("table",bea.getSousmod_table());
			 element.put("schema",bea.getSoumod_schema());
			 listData.put(element);
			 teste=true;
			 break;
		 }
		
	 }
         getResponse().setStatus(200);
	  if ( teste ) {
		  JSONObject json      = new JSONObject();
		  json.put("myliste", listData);
		  getResponse().setContentType("application/json");      
		  getResponse().getWriter().write(json.toString());
	    } else {
	       getResponse().getWriter().write("not found");
	    }
	 
		return null;
}



@SuppressWarnings("unchecked")
public static ModelAndView doUpdateEditableDataTableAjax( ) throws Throwable, Throwable{
	
		 
		EditableDataTableRequestParam parameter =(EditableDataTableRequestParam) getObjectValueModel(EditableDataTableParam);
		
		List  listOfmyData=(List) getObjectValueModel(parameter.sNameList);
	    String hid_act    =  getRequest().getParameter("hid_act")==null?"": getRequest().getParameter("hid_act");
	    
	    
		if(hid_act.equals("tochek")){
			String idTosend   =  getRequest().getParameter("idTosend")==null?"":getRequest().getParameter("idTosend");
		    String val_cheked =  getRequest().getParameter("val_cheked")==null?"": getRequest().getParameter("val_cheked");
		    int f=idTosend.indexOf("£");
		    String id1=idTosend.substring(0, f);
			String id2=idTosend.substring(f+1);
			for (int i = 0; i < listOfmyData.size(); i++) {
				 EntiteAdminBean  bean=(EntiteAdminBean) listOfmyData.get(i);
					if(bean.getPk_entite_admin().getEnt_id().equals(id1) &&  bean.getPk_entite_admin().getLang_id().equals(id2)  )
					{
						bean.setTo_check(val_cheked);
						getResponse().getWriter().print(val_cheked);
						return null ;
					}
				}
			
		}else if(hid_act.equals("updateListCorr")){
			String idTosend    =  getRequest().getParameter("idTosendsd")==null?"":getRequest().getParameter("idTosendsd");
			String name_column =  getRequest().getParameter("name_column")==null?"":getRequest().getParameter("name_column");
			String value       =  getRequest().getParameter("value")==null?"":getRequest().getParameter("value");
		    int f=idTosend.indexOf("£");
		    String id1=idTosend.substring(0, f);
			String id2=idTosend.substring(f+1);
			for (int i = 0; i < listOfmyData.size(); i++) {
				 EntiteAdminBean  bean=(EntiteAdminBean) listOfmyData.get(i);
					if(bean.getPk_entite_admin().getEnt_id().equals(id1) &&  bean.getPk_entite_admin().getLang_id().equals(id2)  )
					{
						bean.getPk_entite_admin().setLang_id(value);
						getResponse().getWriter().print(value==null?"":value);
						return null ;
					}
				}
		}else{
			String name_column =  getRequest().getParameter("name_column")==null?"":getRequest().getParameter("name_column");
			String id = getRequest().getParameter("id")==null?"":getRequest().getParameter("id");
			int f=id.indexOf("£");
			String id1=id.substring(0, f);
			String id2=id.substring(f+1);
			String value = getRequest().getParameter("value");
			for (int i = 0; i < listOfmyData.size(); i++) {
			 EntiteAdminBean  bean=(EntiteAdminBean) listOfmyData.get(i);
			 if(bean.getPk_entite_admin().getEnt_id().equals(id1) &&  bean.getPk_entite_admin().getLang_id().equals(id2)  )
				{
					Field field = bean.getClass().getDeclaredField(name_column);
					field.setAccessible(true);
					field.set(bean, value==null?"c":value);
					getResponse().getWriter().print(value==null?"c":value);
					return null ;
				}
			}
		}
		getResponse().getWriter().print("Error - Object cannot be found");
		return null ;
 
	
 
}



	@SuppressWarnings("unchecked")
	public ModelAndView doFetchDataAjax(EntiteAdminBean searchBean)
			throws Throwable, Throwable {

		try {
			List listDataSrv = serviceEntiteAdmin.getListDataServer(searchBean);
			setObjectValueModel(LIST_DATA, listDataSrv);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;

	}
	
	public    ModelAndView doSelectRow() {

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
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}

 
	
	public ModelAndView doAddData(EntiteAdminBean beanInsert) {
 
		 
		try{
		  List listLangueEntite  =(List) getObjectValueModel("listLangueEntite");
		  serviceEntiteAdmin.CreateRowData(beanInsert,listLangueEntite) ;
		  throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_UNIQUE_CREE_ENTITEADMIN);		
		}
	
	public ModelAndView doAddData_With_List(EntiteAdminBean beanInsert) {

		String tr = "";
		List listLibbelleEntite = (List) getObjectValueModel("listPreparerEntiteAdmin");
		try{
		 if(listLibbelleEntite==null || listLibbelleEntite.size()==0)
			 throwNewException("Impossibel  d'inserer une Liste Vide");
		 
		  serviceEntiteAdmin.CreateRowData_with_List(listLibbelleEntite) ;
		  throwNewException("Insertion Reussit");
		
		} catch (Exception e) {
			displayException(e);
		}
			  return getViewInit_cree(FORM_INIT_CREE_ENTITEADMIN);
		}
	

	public ModelAndView doRetourToHome(EntiteAdminBean pack) {
		return getHome();

	}

	public ModelAndView doUpdateData(EntiteAdminBean beanUpBean) {
		 
		 
		try {
			serviceEntiteAdmin.UpdateRowData(beanUpBean);
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

	public ModelAndView doDeleteData(EntiteAdminBean beanDelBean) {
		JsonResponse res = new JsonResponse();

		EntiteAdminBean pSysBean = (EntiteAdminBean) getObjectValueModel(FORM_BEAN);
		try {
			serviceEntiteAdmin.DeleteRowData(beanDelBean);
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
