package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

public class webActionManager {
	
	


	public static void doWriteFile(String pathpackage,String  nameFile,WebRootFolderBean detailBean){
		
		try {
			File fichier = new File(nameFile);
			 if (! fichier.exists()) 
				 fichier.createNewFile();
			 PrintWriter out = new PrintWriter(new FileWriter(fichier)) ; 
				String[] tablaeau=pathpackage.split("\\.");
				String getNameEntite=tablaeau[tablaeau.length-1];
				out.write("package " + pathpackage+"web;");
				out.println();
			 
			 out.write("import java.lang.reflect.Field; ") ;  
			 out.println() ; 
			 out.write("import java.util.ArrayList; ") ;  
			 out.println() ; 
			 out.write("import java.util.Collection; ") ;  
			 out.println() ; 
			 out.write("import java.util.List; ") ;  
			 out.println() ; 
			 out.write("import org.json.JSONException; ") ;  
			 out.println() ; 
			 out.write("import org.json.JSONObject; ") ;  
			 out.println() ; 
			 out.write("import org.springframework.beans.factory.annotation.Autowired; ") ;  
			 out.println() ; 
			 
			 out.write("import org.springframework.web.servlet.ModelAndView;  ") ;  
			 out.println() ; 
		 
			 out.write("import "+pathpackage+"model."+getNameEntite+"Bean;");out.println() ;
		     out.write("import "+pathpackage+"template."+getNameEntite+"Template;");out.println() ;
		     out.write("import "+pathpackage+"service."+getNameEntite+"Service;");out.println() ;
			 
			 out.println() ; 
			 out.write("import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; ") ;  
			 out.println() ; 
			 out.write("import com.google.gson.JsonIOException;") ;  
			 out.println() ; 
			 out.write("public class "+getNameEntite+"ActionManager extends "+getNameEntite+"Template {") ;  
			 out.println() ; 
			 out.write("     private "+getNameEntite+"Service  service"+getNameEntite+";") ;  
			 out.println() ;
			 
			 out.write("@Autowired") ;  
			 out.println() ; 
			 out.write("public void setService"+getNameEntite+"("+getNameEntite+"Service service"+getNameEntite+") {") ;  
			 out.println() ; 
			 out.write("    this.service"+getNameEntite+" = service"+getNameEntite+";") ;  
			 out.println() ; 
			 out.write("} ") ;  
			 out.println() ; 
			 out.write("@SuppressWarnings(\"unchecked\") ") ; out.println() ;   
			 out.write("public ModelAndView doFetchData("+getNameEntite+"Bean searchBean)throws Throwable {") ; out.println() ;   
		     out.write("		try {") ; out.println() ;   
			 out.write("			List listDataSrv = service"+getNameEntite+".doFetchDatafromServer(searchBean);") ; out.println() ;   
			   
			 out.write("			setObjectValueModel(SEARCH_BEAN, searchBean);") ; out.println() ;   
			 out.write("			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);") ; out.println() ;   
			 out.write(" 		} catch (Exception e) {") ; out.println() ;  
			 out.write(" 			 getResponse().setStatus(500);") ; out.println() ;   
			
		     out.write(" 			 e.printStackTrace();") ; out.println() ;   
			 out.write(" 			 getResponse().setContentType(HTML_CONTENT_TYPE);") ; out.println() ;   
			 out.write(" 			 getResponse().getWriter().print(e.getMessage());") ; out.println() ;   
		     out.write("}") ; out.println() ;   
		     out.write("return null;") ; out.println() ;   
		     out.write("}") ; out.println() ;   
			
		   
		     
		     
		     
		     out.write("public ModelAndView doAddData("+getNameEntite+"Bean detailBean) throws Throwable {") ; out.println() ; 
		     out.write("     try {") ; out.println() ;  
		     out.write("			setObjectValueModel(FORM_BEAN, detailBean);") ; out.println() ;  
		     out.write("            service"+getNameEntite+".doCreateRowData(detailBean);") ; out.println() ;  
		     out.write("            removeObjectModel(FORM_BEAN);") ; out.println() ;  
		     out.write("            throwNewException(\"ins01\");") ; out.println() ;  
		     out.write("          } catch (Exception e) {") ; out.println() ;  
		     out.write("            displayException(e);") ; out.println() ;  
		     out.write("          }") ; out.println() ;  
		     out.write("        return getViewAdd(FORM_VIEW);") ; out.println() ;   		
		     out.write("	}") ; out.println() ;   					
			 		     
			 		     
		     out.write("public ModelAndView doUpdateData("+getNameEntite+"Bean beanUpBean) {	 ") ; out.println() ;   		 		
		     out.write("	 	try {") ; out.println() ;   				
		     out.write("	 service"+getNameEntite+".doUpdateRowData(beanUpBean); ") ; out.println() ; 
		     
		     out.write("			update_row_from_list(LIST_DATA, beanUpBean); ") ;  out.println() ;
			 
			 
			 
		     out.write("	 throwNewException(\"mod01\");") ; out.println() ;   		
		     out.write("	 	} catch (Exception e) {") ; out.println() ;   	
		     out.write("	 	displayException(e);") ; out.println() ;   	

		     out.write("	 }") ; out.println() ;  
		     out.write("	return getViewList_Ajax(FILTER_VIEW);") ; out.println() ;  
		     out.write("		}") ; out.println() ;  	
			 		
		     out.write("public ModelAndView doDeleteData("+getNameEntite+"Bean beanDelBean) {") ; out.println() ;  
		     out.write("    try {") ; out.println() ;  		 
		     out.write("     service"+getNameEntite+".doDeleteRowData(beanDelBean);") ; out.println() ; 
		     
		     
		     out.write("			    remove_row_from_list(LIST_DATA);") ;  out.println() ;
			 
			 
			 
		     out.write("     throwNewException(\"sup01\");") ; out.println() ; 
		     out.write("       } catch (Exception e) {") ; out.println() ; 
		     out.write("       displayException(e);") ; out.println() ; 
		     out.write("       }") ; out.println() ; 
		     out.write("    return getViewList_Ajax(FILTER_VIEW);") ; out.println() ; 
		     out.write("   }") ; out.println() ;  
		     out.write(" }") ; out.println() ;  
			  
			 out.close() ;  
			} catch (Exception e) {
				e.getStackTrace();
			}
		
	}

}
