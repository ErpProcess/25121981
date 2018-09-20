package jquery.datatables.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jquery.datatables.model.Company;
import jquery.datatables.model.DataRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.DynamicClass;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.util.dataTableTemplate;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class DataEditableTablesParamUtility extends dataTableTemplate  {
	
	public static EditableDataTableRequestParam getParam(HttpServletRequest request){
		
	 
		 if(request.getParameter("sEcho")!=null && request.getParameter("sEcho")!= ""    )
		{
			EditableDataTableRequestParam param = new EditableDataTableRequestParam();
			param.sEcho = request.getParameter("sEcho");
			param.sSearchKeyword = request.getParameter("sSearch");
			param.bRegexKeyword = Boolean.parseBoolean(request.getParameter("bRegex"));
			param.sColumns = request.getParameter("sColumns");
			param.iDisplayStart = Integer.parseInt( request.getParameter("iDisplayStart") );
			param.iDisplayLength = Integer.parseInt( request.getParameter("iDisplayLength") );
			param.iColumns = Integer.parseInt( request.getParameter("iColumns") );
			param.sSearch = new String[param.iColumns];
			param.bSearchable = new boolean[param.iColumns];
			param.bSortable = new boolean[param.iColumns];
			param.bRegex = new boolean[param.iColumns];
			for(int i=0; i<param.iColumns; i++){
				param.sSearch[i] = request.getParameter("sSearch_"+i);
				param.bSearchable[i] = Boolean.parseBoolean(request.getParameter("bSearchable_"+i));
				param.bSortable[i] = Boolean.parseBoolean(request.getParameter("bSortable_"+i));
				param.bRegex[i] = Boolean.parseBoolean(request.getParameter("bRegex_"+i));
			}
			
			param.iSortingCols = Integer.parseInt( request.getParameter("iSortingCols") );
			param.sSortDir = new String[param.iSortingCols];
			param.iSortCol = new int[param.iSortingCols];
			param.sNameList=request.getParameter("nameList");
			param.sNameId=request.getParameter("sNameId");
			for(int i=0; i<param.iSortingCols; i++){
				param.sSortDir[i] = request.getParameter("sSortDir_"+i);
				param.iSortCol[i] = Integer.parseInt(request.getParameter("iSortCol_"+i));
			}
			//setObjectValueModel(EditableDataTableParam,param); 
			
			return param;
		}else{
			return null;
		}
			
	}
	
public static  List EditableJQueryDefinePlaguinDataTable(EditableDataTableRequestParam param,List listData 
		,JsonObject jsonResponse,JsonArray data) throws Exception, Throwable  {
        
		String sEcho = param.sEcho;
		int iTotalRecords;  
		int iTotalDisplayRecords;  
		iTotalRecords = listData.size();
		List <Object> listDataTrie = new ArrayList<Object>();
		
		 
	 
		final String[] lesColunm = param.sColumns.split(",");
		for (int i = 0; i < listData.size(); i++) {
			Object bean =(Object) listData.get(i);
			boolean testeExist=false;
			for (int j = 0; j < lesColunm.length; j++) {
				
				if(lesColunm[j].indexOf(".")>0){
					
					final String[] lesColunmooo = lesColunm[j].split("\\.");
					
					 Object object=bean;
					
					    for (int k = 0; k < lesColunmooo.length; k++) {
					    	  
					    	
					    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
							  fieldo.setAccessible(true);
							  Object obj=fieldo.get(object);
							  object=obj;
							  
						}
		    		 
		    		String  element1 =  String.valueOf(object).toLowerCase();
		    		element1=element1!=null?element1:"";
		    		if(element1.contains(param.sSearchKeyword.toLowerCase()))testeExist=true;
					
				}else{
					Field field = bean.getClass().getDeclaredField(lesColunm[j]);
		    		field.setAccessible(true);
		    		String  element =((String)   String.valueOf(field.get(bean))).toLowerCase();
		    		element=element!=null?element:"";
		    		if(element.contains(param.sSearchKeyword.toLowerCase()))testeExist=true;
				}
	    		
			}
			if(testeExist){
				listDataTrie.add(bean); // add company that matches given search criterion
			}
		}
		iTotalDisplayRecords = listDataTrie.size(); // number of companies that match search criterion should be returned
		final int sortColumnIndex = param.iSortingCols; 
		final int sortDirection = param.sSortDir.equals("asc") ? -1 : 1;
		 
		Collections.sort(listDataTrie, new Comparator<Object>(){
			public int compare(Object bean, Object bean2) {
				
				  
				try {
					if(lesColunm[sortColumnIndex].indexOf(".")>0){
						
						final String[] lesColunmooo = lesColunm[sortColumnIndex].split("\\.");
						
						 Object object=bean;
						    for (int k = 0; k < lesColunmooo.length; k++) {
						    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
								  fieldo.setAccessible(true);
								  Object obj=fieldo.get(object);
								  object=obj;
								  
							}
						    
			    		String  element1 =  String.valueOf(object).toLowerCase();
			    		element1=element1!=null?element1:"";
			    		
			    		
			    		Object object2=bean2;
					    for (int k = 0; k < lesColunmooo.length; k++) {
					    	  Field fieldo=object2.getClass().getDeclaredField(lesColunmooo[k]);
							  fieldo.setAccessible(true);
							  Object obj=fieldo.get(object2);
							  object2=obj;
							  
						}
		    		  String  element11 =  String.valueOf(object2).toLowerCase();
		    		  element11=element11!=null?element11:"";
		    		 return String.valueOf(element1).compareTo( String.valueOf(element11)) * sortDirection;
						
					}else{
						
						Field field = bean.getClass().getDeclaredField(lesColunm[sortColumnIndex]);
						field.setAccessible(true);
						String	element = ((String)   String.valueOf(field.get(bean))).toLowerCase();
						element=element!=null?element:"";
						Field	field2 = bean2.getClass().getDeclaredField(lesColunm[sortColumnIndex]);
			    		field2.setAccessible(true);
			    		String	element2 = ((String)   String.valueOf(field2.get(bean2))).toLowerCase();
			    		element2=element2!=null?element2:"";
			    		return String.valueOf(element).compareTo( String.valueOf(element2)) * sortDirection;
		    		
					}
		    		
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
	    	
				 return 0;
				 
	    		
	    		
	    		 
	    		
			 
			}
		}); 
		/**/
		if(listDataTrie.size()< param.iDisplayStart + param.iDisplayLength) {
			listDataTrie = listDataTrie.subList(param.iDisplayStart, listDataTrie.size());
		} else {
			listDataTrie = listDataTrie.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
		}
			jsonResponse.addProperty("sEcho", sEcho);
			jsonResponse.addProperty("iTotalRecords", iTotalRecords);
			jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
			
			 
				
			 
				for (int i = 0; i < listDataTrie.size(); i++) {
					Object beantrie =(Object) listDataTrie.get(i);
					
				JsonArray row = new JsonArray();
				
				
				for (int j = 0; j < lesColunm.length; j++) {
					
					if(lesColunm[j].indexOf(".")>0){
						
						final String[] lesColunmooo = lesColunm[j].split("\\.");
						
						 Object object=beantrie;
						
						    for (int k = 0; k < lesColunmooo.length; k++) {
						    	  
						    	
						    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
								  fieldo.setAccessible(true);
								  Object obj=fieldo.get(object);
								  object=obj;
								  
							}
			    		 
			    		String  element1 =  String.valueOf(object).toLowerCase();
			    		row.add(new JsonPrimitive(element1!=null?element1:""));
						
					}else{
		    		Field field = beantrie.getClass().getDeclaredField(lesColunm[j]);
		    		field.setAccessible(true);
		    		String  element =((String)String.valueOf(field.get(beantrie))).toLowerCase();
		    		row.add(new JsonPrimitive(element!=null?element:""));
					}
		    		 
				}
				
				
				 
				data.add(row);
			}
				
			 
				
	 return listDataTrie;
	}

public static  List  doGetAzizWild3asoul(final EditableDataTableRequestParam param,List listData 
		,JsonObject jsonResponse,JsonArray data
		,HttpServletRequest request, HttpServletResponse response) throws Exception, Throwable {

	
	final DynamicClass  dynamicClass= new DynamicClass();
	
	String sEcho = param.sEcho;
	int iTotalRecords; // total number of records (unfiltered)
	int iTotalDisplayRecords;//value will be set when code filters companies by keyword
	 
	iTotalRecords = listData.size();
	
	List<Object> listDataTrie = new LinkedList<Object>();
	 
	 
	final String[] lesColunm = param.sColumns.split(",");
	for (int i = 0; i < listData.size(); i++) {
		Object bean =(Object) listData.get(i);
		boolean testeExist=false;
		for (int j = 0; j < lesColunm.length; j++) {
			
			if(lesColunm[j].indexOf(".")>0){
				
				final String[] lesColunmooo = lesColunm[j].split("\\.");
				
				 Object object=bean;
				
				    for (int k = 0; k < lesColunmooo.length; k++) {
				    	
				    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
						  fieldo.setAccessible(true);
						  Object obj=fieldo.get(object);
						  object=obj;
						  if(object==null) {  object=""; break;}
						  
					}
	    		 
	    		String  element1 =  String.valueOf(object);
	    		element1=element1!=null?element1:"";
	    		if(element1.contains(param.sSearchKeyword))testeExist=true;
				
			}else{
				Field field = bean.getClass().getDeclaredField(lesColunm[j]);
	    		field.setAccessible(true);
	    		String  element =((String)   String.valueOf(field.get(bean)));
	    		element=element!=null?element:"";
	    		if(element.contains(param.sSearchKeyword))testeExist=true;
			}
    		
		}
		if(testeExist){
			listDataTrie.add(bean); // add company that matches given search criterion
		}
	}
	
	
	
	
	
	
	iTotalDisplayRecords = listDataTrie.size();//Number of companies that matches search criterion should be returned
	 
	final int sortColumnIndex = param.iSortingCols; 
	final int sortDirection = param.sSortDir[0].equals("asc") ? -1 : 1;
	
	 
	
	
	Collections.sort(listDataTrie, new Comparator<Object>(){
		public int compare(Object bean, Object bean2) {
		  
			try {
				if(lesColunm[sortColumnIndex].indexOf(".")>0){
					
					final String[] lesColunmooo = lesColunm[sortColumnIndex].split("\\.");
					
					 Object object=bean;
					    for (int k = 0; k < lesColunmooo.length; k++) {
					    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
							  fieldo.setAccessible(true);
							  Object obj=fieldo.get(object);
							  object=obj;
						}
					    
					  
		    		String  element1 =  String.valueOf(object);
		    		element1=element1!=null?element1:"";
		    		
		    		
		    		Object object2=bean2;
				    for (int k = 0; k < lesColunmooo.length; k++) {
				    	  Field fieldo=object2.getClass().getDeclaredField(lesColunmooo[k]);
						  fieldo.setAccessible(true);
						  Object obj=fieldo.get(object2);
						  object2=obj;
					}
				    
	    		  String  element11 =  String.valueOf(object2);
	    		  element11=element11!=null?element11:"";
	    		  
	    		 return String.valueOf(element1).compareTo( String.valueOf(element11)) * sortDirection;
					
				}else{
					
					Field field = bean.getClass().getDeclaredField(lesColunm[sortColumnIndex]);
					field.setAccessible(true);
					Object objToReturn =dynamicClass.castDynamicClassObject(field.getType().getName(), field.get(bean));
				 
					Field	field2 = bean2.getClass().getDeclaredField(lesColunm[sortColumnIndex]);
		    		field2.setAccessible(true);
		    		Object objToReturn2 =dynamicClass.castDynamicClassObject(field2.getType().getName(), field2.get(bean2));
		    		
		    		
		    		if(isNumeric(String.valueOf(objToReturn))){
		    			
		    			Double el1=Double.parseDouble(String.valueOf(objToReturn));
		    			Double el2=Double.parseDouble(String.valueOf(objToReturn2));
		    			return    el1.compareTo(el2) * sortDirection;
		    		}else{
		    			String el1=String.valueOf(objToReturn);
		    			String el2=String.valueOf(objToReturn2);
		    			return    el1.compareTo(el2) * sortDirection;
		    		}
		  
	    		
				}
	    		
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				try {
					throw e;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
    	
			 return 0;
			
			 
		}
	});
	
	if(listDataTrie.size()< param.iDisplayStart + param.iDisplayLength)
		listDataTrie = listDataTrie.subList(param.iDisplayStart, listDataTrie.size());
	else
		listDataTrie = listDataTrie.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);

	
	jsonResponse.addProperty("sEcho", sEcho);
	jsonResponse.addProperty("iTotalRecords", iTotalRecords);
	jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
	
	Integer debuInde= new Integer(param.iDisplayStart);
	int  debuIndex=debuInde.intValue();
	 
		for (int i = 0; i < listDataTrie.size(); i++) {
			Object beantrie =(Object) listDataTrie.get(i);
			JsonArray row = new JsonArray();
			int debuIndexSuivant=debuIndex+1;
			 
				Field fielK = beantrie.getClass().getDeclaredField("indx_row");
				fielK.setAccessible(true);
				String  eleindexo =((String)String.valueOf(debuIndex));
				fielK.set(beantrie, eleindexo);
				
				Field fielnext = beantrie.getClass().getDeclaredField("indx_row_next"); 
				fielnext.setAccessible(true);
				String  eleindfielnext =((String)String.valueOf(debuIndexSuivant));
				fielnext.set(beantrie, eleindfielnext);
		 
		for (int j = 0; j < lesColunm.length; j++) {
			 Object objj=GenericWeb.getValueOject_with_name_field(beantrie, lesColunm[j]);
			 row.add(new JsonPrimitive( String.valueOf(objj) ));
		}
		
		debuIndex++;
		debuIndexSuivant++;
		data.add(row);
	}
	 return listDataTrie;

}
	
public static boolean isNumeric(String str)  
{  
  try  
  {  
    double d = Double.parseDouble(str);  
  }  
  catch(NumberFormatException nfe)  
  {  
    return false;  
  }  
  return true;  
}
	
}
