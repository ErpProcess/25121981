package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import jquery.datatables.controller.DataEditableTablesParamUtility;
import jquery.datatables.controller.EditableDataTableRequestParam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.service.ModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.service.PackageSysService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.service.SousModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.service.SousPackageService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.service.WebRootFolderService;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.WebRootFolderTemplate;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.daoEntite;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.filterView;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.formView;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.listView;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.modelEntite;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.serviceEntite;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.templateEntite;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.webActionManager;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.webcontroller;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class ActionWebRootFolderManager extends WebRootFolderTemplate {

	
	
	private    WebRootFolderService          serviceWebRootFolder;
	
	private    PackageSysService          servicePackageSys;
	@Autowired
	public void setServicePackageSys(PackageSysService servicePackageSys) {
		this.servicePackageSys = servicePackageSys;
	}
	
	private    SousPackageService          serviceSousPackageSys;
	@Autowired
	public void setServiceSousPackage(SousPackageService serviceSousPackageSys) {
		this.serviceSousPackageSys = serviceSousPackageSys;
	}
	
	
	
	private    ModuleService          serviceModule;
	@Autowired
	public void setServiceModule(ModuleService serviceModule) {
		this.serviceModule = serviceModule;
	}
	 
	
	
	private    GLangueService          serviceGLangue;
	@Autowired
	public void setServiceGLangue(GLangueService serviceGLangue) {
		this.serviceGLangue = serviceGLangue;
	}
	
	
	@Autowired
	public void setServiceWebRootFolder(WebRootFolderService serviceWebRootFolder) {
		this.serviceWebRootFolder = serviceWebRootFolder;
	}
	
	private SousModuleService        serviceSousModule;
	@Autowired
	public void setServiceSousModule(SousModuleService serviceSousModule) {
		this.serviceSousModule = serviceSousModule;
	}
	
	 
	
	
	public ModelAndView doInitServletAction() {
		try {
	    setObjectValueModel("listSousModuleForCree", serviceSousModule.dofetchDatafromServer(null));
		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		
		setObjectValueModel("listAttributeEntite" , new ArrayList());
		
			if (bs.getFct_id().equals("5")) {
				return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
			} else {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	public ModelAndView getTAbleAnd_Schema() throws JSONException, IOException {
	    
		 List   listSousModuleAjax = (List) getObjectValueModel("listSousModuleForCree");
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
	public   ModelAndView doPreparerEntite( WebRootFolderBean detailBean ) throws Throwable{
		 
		  HashMap  mapDataPrimaryKey=new HashMap();
		  HashMap  mapDataForeinKey=new HashMap();
		 
		try {
			  List   listDataTrie = (List) getObjectValueModel(getRequest().getParameter("nameList"));
			  if(listDataTrie==null ||  listDataTrie.size()==0){
				  WebRootFolderBean   beanSearch = new  WebRootFolderBean();
				  beanSearch.setSousmod_id(detailBean.getSousmod_id());
				  beanSearch.getPk_web_root().setLang_id(detailBean.getPk_web_root().getLang_id());
				  
				  List list_desLanque=serviceGLangue.dofetchALLDatafromServer(new GLangueBean());
			      List listData=serviceWebRootFolder.getListDataServer(beanSearch);
				  HashMap  mapData=new HashMap();
				 
				  if(listData!=null  &&  listData.size()>0){
						  for (int i = 0; i < listData.size(); i++) {
							  WebRootFolderBean beanS=(WebRootFolderBean) listData.get(i);
							  mapData.put(beanS.getColumn_name(), beanS.getPk_web_root().getEnt_id());
						    }
				   }
					  Connection con = null;
				      Class.forName("org.postgresql.Driver");
				      con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Process_DB","postgres", "manager");
				      DatabaseMetaData meta = con.getMetaData();
				      ResultSet res = meta.getColumns(null,null, detailBean.getTable_name(), null);
				      ResultSet resPrimaryKey =meta.getPrimaryKeys(null, null, detailBean.getTable_name());
				      while (resPrimaryKey.next()) {
				    	  mapDataPrimaryKey.put(resPrimaryKey.getString("COLUMN_NAME"), "primary" );
				      }
				      ResultSet resForeinKey =meta.getExportedKeys(null, null, detailBean.getTable_name());
				      /*while (resForeinKey.next()) {
				    	  mapDataForeinKey.put(resForeinKey.getString("COLUMN_NAME"), "forRenky" );
				      }*/
				      
				      listDataTrie =new LinkedList<Object>();
				      while (res.next()) {
				         //System.out.println("    "+res.getString("TABLE_SCHEM") + ", "+res.getString("TABLE_NAME") + ", "+res.getString("COLUMN_NAME") + ", "+res.getString("TYPE_NAME") + ", "+res.getInt("COLUMN_SIZE")+ ", "+res.getString("NULLABLE")); 
				         if(mapData!=null  &&  mapData.size()>0){
				        	 String key=(String) mapData.get(res.getString("COLUMN_NAME"));
				        	 if(key!=null) continue; 
				         } else{
				        	 doSetBeanInVectorLanq(list_desLanque,detailBean,res, listDataTrie);
				        	 }
				         }
				      doSetBeanInVectorLanq_nameList(list_desLanque, detailBean,listDataTrie);
				      res.close();
				      con.close();
				      setObjectValueModel(getRequest().getParameter("nameList"),listDataTrie);
			  }
			
			  setObjectValueModel("mapDataPrimaryKey" ,mapDataPrimaryKey);
			  setObjectValueModel("mapDataForeinKey" ,mapDataForeinKey);
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
	
	public List doSetBeanInVectorLanq(List list_desLangue,WebRootFolderBean detailBean,ResultSet res,List   listDataTrie ){
		
		 
		try {
			 for (int i = 0; i < list_desLangue.size(); i++) {
				 
			 GLangueBean  beanLangue     =  (GLangueBean) list_desLangue.get(i);
			 WebRootFolderBean   beanIns =  new  WebRootFolderBean();
    		 String id1=detailBean.getSousmod_id()+"/"+res.getString("COLUMN_NAME");
    		 beanIns.setId_entite(id1+"£"+beanLangue.getLang_id());
	         beanIns.getPk_web_root().setLang_id(beanLangue.getLang_id());
	         beanIns.getPk_web_root().setEnt_id(detailBean.getSousmod_id()+"/"+res.getString("COLUMN_NAME"));
	         
	         SousModuleBean sBean= new SousModuleBean();
	         sBean.setSousmod_id(BigDecimal.valueOf( Double.parseDouble(detailBean.getSousmod_id())));
	         SousModuleBean  beanS_mod=(serviceSousModule.getSousModuleList(sBean)).get(0);
	         
	         beanIns.setEnt_libelle(res.getString("COLUMN_NAME")+"-"+beanLangue.getLang_id());
	         beanIns.setType_lib_id("0002");
	         beanIns.setTo_check("");
	         beanIns.setEnt_abrv(res.getString("COLUMN_NAME")+"-"+beanLangue.getLang_id());
	         
	         
	         beanIns.setSousmod_id(beanS_mod.getSousmod_id().toString());
	         beanIns.setMod_id(beanS_mod.getModuleBean().getMod_id().toString());
	         
	         
	         ModuleBean mBeans= new ModuleBean();
	         mBeans.setMod_id(beanS_mod.getModuleBean().getMod_id());
	         ModuleBean beanMdell=   (serviceModule.getSomeModules(mBeans)).get(0);
	        
	         beanIns.setSpack_id(beanMdell.getSousPackBean().getSpack_id().toString());
	         SousPackageBean beanSearch= new SousPackageBean();
	         beanSearch.setSpack_id(beanMdell.getSousPackBean().getSpack_id());
	         SousPackageBean sousPackBean =   (serviceSousPackageSys.getSousPackList(beanSearch)).get(0);
	         beanIns.setPack_id(sousPackBean.getPackageSys().getPack_id().toString());
	         
	         
	         beanIns.setTable_schem(res.getString("TABLE_SCHEM")!=null?res.getString("TABLE_SCHEM"):"");
	         beanIns.setTable_name(res.getString("TABLE_NAME")!=null?res.getString("TABLE_NAME"):"");
	         beanIns.setColumn_name(res.getString("COLUMN_NAME")!=null?res.getString("COLUMN_NAME"):"");
	         beanIns.setType_name(res.getString("TYPE_NAME")!=null?res.getString("TYPE_NAME"):"");
	         beanIns.setColumn_size(String.valueOf(res.getInt("COLUMN_SIZE"))!=null?String.valueOf(res.getInt("COLUMN_SIZE")):"");
	         listDataTrie.add(beanIns);
			  
			 }
	         
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listDataTrie;
		
	}
	
	
public List doSetBeanInVectorLanq_nameList(List list_desLangue,WebRootFolderBean detailBean,List   listDataTrie) throws Exception{
		
		 
		try {
			 for (int i = 0; i < list_desLangue.size(); i++) {
				 
			  GLangueBean  beanLangue=  (GLangueBean) list_desLangue.get(i);
	         WebRootFolderBean   beanInxxs = new  WebRootFolderBean();
    		 String idh=detailBean.getSousmod_id()+"/list-"+detailBean.getSousmod_id();
    		 beanInxxs.setId_entite(idh+"£"+beanLangue.getLang_id());
	         beanInxxs.getPk_web_root().setLang_id(beanLangue.getLang_id());
	         beanInxxs.getPk_web_root().setEnt_id(idh);
	         beanInxxs.setEnt_libelle("list-"+detailBean.getTable_name()+"-"+beanLangue.getLang_id());
	         beanInxxs.setType_lib_id("0002");
	         beanInxxs.setTo_check("");
	         beanInxxs.setEnt_abrv("list-"+detailBean.getTable_name()+"-"+beanLangue.getLang_id());
	         beanInxxs.setSousmod_id(detailBean.getSousmod_id());
	         beanInxxs.setMod_id(detailBean.getMod_id());
	         beanInxxs.setPack_id(detailBean.getPack_id());
	         beanInxxs.setSpack_id(detailBean.getSpack_id());
	         beanInxxs.setTable_schem(detailBean.getTable_schem()!=null?detailBean.getTable_schem():"");
	         beanInxxs.setTable_name(detailBean.getTable_name()!=null?detailBean.getTable_name():"");
	         beanInxxs.setColumn_name("");
	         beanInxxs.setType_name("");
	         beanInxxs.setColumn_size("5000");
	         listDataTrie.add(beanInxxs);
			 }
	         
			
		} catch (Exception e) {
			throw e;
		}
		return listDataTrie;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doUpdateEditableTable() throws Throwable, Throwable{
			 
			List  listOfmyData=(List) getObjectValueModel("listAttributeEntite");
			String name_column =  getRequest().getParameter("name_column")==null?"":getRequest().getParameter("name_column");
			String id = getRequest().getParameter("id")==null?"":getRequest().getParameter("id");
			int f=id.indexOf("£");
			String id1=id.substring(0, f);
			String id2=id.substring(f+1);
			String value = getRequest().getParameter("value");
			for (int i = 0; i < listOfmyData.size(); i++) {
			  WebRootFolderBean  bean=(WebRootFolderBean) listOfmyData.get(i);
			  
			  
				 if(  bean.getPk_web_root().getEnt_id().equals(id1) &&  bean.getPk_web_root().getLang_id().equals(id2)  )
					{
						Field field = bean.getClass().getDeclaredField(name_column);
						field.setAccessible(true);
						field.set(bean, value==null?"c":value);
						getResponse().getWriter().print(value==null?"c":value);
						return null ;
					}
				 
				 
				 
				}
			getResponse().getWriter().print("Error - Object cannot be found");
			return null ;
	 
		
	 
	}

	
	 

 
	 
	public ModelAndView doAddData_Files(WebRootFolderBean detailBean) throws Throwable {
		
		//Les fichier JSP
		boolean testeExiste=false; 
		try {
			   List listAttributeEntite= (List) getObjectValueModel("listAttributeEntite");
			   SousModuleBean beanUpdate =new SousModuleBean();
			   beanUpdate.setSousmod_id(BigDecimal.valueOf(Integer.parseInt(detailBean.getSousmod_id())));
			   serviceSousModule.doUpdateSousModule(beanUpdate);
			   serviceWebRootFolder.doCreateRowData(listAttributeEntite); 
			    List listSousModuleForCree=(List) getObjectValueModel("listSousModuleForCree");
			   int zizou=listSousModuleForCree.size();
			   for (int i = 0; i < zizou; i++) {
				   SousModuleBean bFolderBean=(SousModuleBean) listSousModuleForCree.get(i);
				   if(String.valueOf(bFolderBean.getSousmod_id()).equals(detailBean.getSousmod_id())){
					   listSousModuleForCree.remove(i);
					   i--;
					   zizou--;
				   }
			    }
			     setObjectValueModel("listSousModuleForCree",listSousModuleForCree);
			     testeExiste=true;     
			if(testeExiste){
				List listform=new ArrayList();
				List listfilter=new ArrayList();
				List listgrid=new ArrayList();
				
				List listGridGeneral=new ArrayList();
				
				for (int i = 0; i < listAttributeEntite.size(); i++) {
					WebRootFolderBean bFolderBean=(WebRootFolderBean) listAttributeEntite.get(i);
					if(bFolderBean.getPk_web_root().getLang_id().equals("fr")){
					if(bFolderBean.getTo_check_form().equals("yes")) listform.add(bFolderBean);
					if(bFolderBean.getTo_check_filter().equals("yes")) listfilter.add(bFolderBean);
					if(bFolderBean.getTo_check_list().equals("yes")) listgrid.add(bFolderBean);
					
					listGridGeneral.add(bFolderBean);
					}
				}
				detailBean.setListAttributeEntite(listGridGeneral);
				detailBean.setListform(listform);
				detailBean.setListfilter(listfilter);
				detailBean.setListgrid(listgrid);
				 
				
				String monChemininit=detailBean.getCheminProject().replace('\\', '/');
				String monChemin=monChemininit+"/WebRoot";
				String Cheminsrc=monChemininit+"/src";
				
				String cheminfolderSrc_jsp=detailBean.getSousmod_action().replaceAll("root.action", "");
				String pathPage=cheminfolderSrc_jsp.substring(1).replace('/', '.');
				String[] lesFoldo=cheminfolderSrc_jsp.split("/");
				String repertoir=monChemin;
				
				String repertoirSRC=Cheminsrc;
				
				
				for (int i = 0; i < lesFoldo.length; i++) {
					  if(lesFoldo[i].equals("")) continue;
					  repertoir=repertoir+"/"+lesFoldo[i];
						 File file = new File(repertoir);
						 if(file.exists()){
							 continue;
						 }else{
							 file.mkdir();
							 if(i==lesFoldo.length-1){
								 formView.doWriteFileFomEntite(repertoir+"/"+"Form"+lesFoldo[i]+".jsp",listform);
								 filterView.doWriteFileFilterEntite(repertoir+"/"+"Filter"+lesFoldo[i]+".jsp",listfilter);
								 listView.doWriteFileListEntite(repertoir+"/"+"List"+lesFoldo[i]+".jsp",listgrid);
							 testeExiste=true;
							 }
						 }
				   }
				
				//les Package
				 
				for (int i = 0; i < lesFoldo.length; i++) {
					  if(lesFoldo[i].equals("")) continue;
					  repertoirSRC=repertoirSRC+"/"+lesFoldo[i];
						 File file = new File(repertoirSRC);
						 if(file.exists()){
							 continue;
						 }else{
							 file.mkdir();
							 if(i==lesFoldo.length-1){
								 File fileweb = new File(repertoirSRC+"/web") ;fileweb.mkdir();
								 File filetemplate = new File(repertoirSRC+"/template") ;filetemplate.mkdir();
								 File fileservice = new File(repertoirSRC+"/service") ;fileservice.mkdir();
								 File filemodel = new File(repertoirSRC+"/model") ;filemodel.mkdir();
								 File filedao = new File(repertoirSRC+"/dao") ;filedao.mkdir();
								 
								 webcontroller.doWriteFile(pathPage,repertoirSRC+"/web"+"/"+lesFoldo[i]+"Controller.java");
								 webActionManager.doWriteFile(pathPage,repertoirSRC+"/web"+"/"+lesFoldo[i]+"ActionManager.java",detailBean);
								 templateEntite.doWriteFile(pathPage,repertoirSRC+"/template"+"/"+lesFoldo[i]+"Template.java",detailBean);
								 serviceEntite.doWriteFile(pathPage,repertoirSRC+"/service"+"/"+lesFoldo[i]+"Service.java");
								 daoEntite.doWriteFile(pathPage,repertoirSRC+"/dao"+"/"+lesFoldo[i]+"DAO.java",detailBean);
								 
								  HashMap  mapDataPrimaryKey= (HashMap) getObjectValueModel("mapDataPrimaryKey");
								  HashMap  mapDataForeinKey= (HashMap) getObjectValueModel("mapDataForeinKey");
								  
								  ModelAndView model=getModel();
								  if( mapDataPrimaryKey!=null &&  (mapDataPrimaryKey.size() ==0 || mapDataPrimaryKey.size() ==1))
								   modelEntite.doWriteFile(pathPage,repertoirSRC+"/model"+"/"+lesFoldo[i]+"Bean.java",detailBean,model);
								  if(mapDataPrimaryKey!=null &&  mapDataPrimaryKey.size()>1){
									  modelEntite.doWriteFile(pathPage,repertoirSRC+"/model"+"/"+lesFoldo[i]+"Bean.java",detailBean,model);
									  modelEntite.doWriteFilePkEntite(pathPage,repertoirSRC+"/model"+"/Pk"+lesFoldo[i]+".java",detailBean,model);
								  }
								  
							 }
						 }
				   } 
				  
				   
				  throwNewException("Ajout du dossier effectuer avec sucee " );
			}else{
				 throwNewException("Le dossier existe déjà " );
			}
		     removeObjectModel(FORM_BEAN);
		    
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	 

	public ModelAndView doRetourToHome(WebRootFolderBean pack) {
		return getHome();

	}

	 

 
	

	


	

}
