package ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

public class AjaxDataTablesUtility  extends GenericWeb {
	
	HttpServletRequest httpRequest; 
	HttpServletResponse httpResponse;
	 
	
	public AjaxDataTablesUtility(HttpServletRequest httpRequest,HttpServletResponse httpResponse) {
		this.httpRequest = httpRequest;
		this.httpResponse = httpResponse;
	}

	public static JQueryDataTableParamModel getIniParam(HttpServletRequest request)
	{
		if(getRequest().getParameter("sEcho")!=null && getRequest().getParameter("sEcho")!= "")
		{
			JQueryDataTableParamModel param = new JQueryDataTableParamModel();
			 
			param.sEcho = "1";//1
			 
			
			  
			//System.out.println("sEcho-----kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk----:"+sxd);
			 
			    
			param.sSearch = getRequest().getParameter("sSearch");//""
			param.sColumns = getRequest().getParameter("sColumns");//"pack_id,glangbean.lang_libelle,pack_libelle,pack_ordre"
			//param.sWColumns = getRequest().getParameter("aoColumns");//"pack_id,glangbean.lang_libelle,pack_libelle,pack_ordre"
			
			
			 
			param.iDisplayStart = Integer.parseInt( getRequest().getParameter("iDisplayStart") );// 0
			param.iDisplayLength = Integer.parseInt( getRequest().getParameter("iDisplayLength") );//5
			param.iColumns = Integer.parseInt( getRequest().getParameter("iColumns") );// nombre de colunne
			param.iSortingCols = Integer.parseInt( getRequest().getParameter("iSortingCols") );//1
			param.iSortColumnIndex = Integer.parseInt(getRequest().getParameter("iSortCol_0"));//0
			param.sSortDirection = getRequest().getParameter("sSortDir_0");//desc
			param.bDestroy =  true;//false ;
			setObjectValueModel(jQUERYdataTABLEPARAM,param);  
			return param;
		}else
			return null;
	}
	
	
	public static JQueryDataTableParamModel getParam(HttpServletRequest request)
	{
		if(getRequest().getParameter("sEcho")!=null && getRequest().getParameter("sEcho")!= "")
		{
			JQueryDataTableParamModel param = new JQueryDataTableParamModel();
			 
			param.sEcho = getRequest().getParameter("sEcho"); 
			 
			
			  
			 
			if(getRequest().getParameter("sEcho").equals("1")){
			}
			  
			    
			param.sSearch = getRequest().getParameter("sSearch");//""
			param.sColumns = getRequest().getParameter("sColumns");//"pack_id,glangbean.lang_libelle,pack_libelle,pack_ordre"
			param.iDisplayStart = Integer.parseInt( getRequest().getParameter("iDisplayStart") );// 0
			param.iDisplayLength = Integer.parseInt( getRequest().getParameter("iDisplayLength") );//5
			param.iColumns = Integer.parseInt( getRequest().getParameter("iColumns") );// nombre de colunne
			param.iSortingCols = Integer.parseInt( getRequest().getParameter("iSortingCols") );//1
			param.iSortColumnIndex = Integer.parseInt(getRequest().getParameter("iSortCol_0"));//0
			param.sSortDirection = getRequest().getParameter("sSortDir_0");//desc
			param.bDestroy =  true;//false ;
		   
			return param;
		}else
			return null;
	}
	
	
	public static JQueryDataTableParamModel getParamBAck(HttpServletRequest request)
	{
		if(getRequest().getParameter("sEcho")!=null && getRequest().getParameter("sEcho")!= "")
		{
			
			JQueryDataTableParamModel CC=(JQueryDataTableParamModel) getObjectValueModel("aParam");
			JQueryDataTableParamModel param = new JQueryDataTableParamModel();
			 
			param.sEcho = getRequest().getParameter("sEcho"); 
			 
			param.sSearch = getRequest().getParameter("sSearch");//""
			param.sColumns = getRequest().getParameter("sColumns");//"pack_id,glangbean.lang_libelle,pack_libelle,pack_ordre"
			param.iDisplayStart = Integer.parseInt( String.valueOf(CC.iDisplayStart) );// 0
			param.iDisplayLength = Integer.parseInt( String.valueOf(CC.iDisplayLength) );//5
			param.iColumns = Integer.parseInt( getRequest().getParameter("iColumns") );// nombre de colunne
			param.iSortingCols = Integer.parseInt( getRequest().getParameter("iSortingCols") );//1
			param.iSortColumnIndex = Integer.parseInt(getRequest().getParameter("iSortCol_0"));//0
			param.sSortDirection = getRequest().getParameter("sSortDir_0");//desc
			param.bDestroy =  true;//false ;
		   
			return param;
		}else
			return null;
	}
	
	
	public static JQueryDataTableParamModel getInitParam(HttpServletRequest request,List listdata)
	{
		if(getRequest().getParameter("sEcho")!=null && getRequest().getParameter("sEcho")!= "")
		{
			JQueryDataTableParamModel param = new JQueryDataTableParamModel();
			param.sEcho = getRequest().getParameter("sEcho");
			param.sSearch = getRequest().getParameter("sSearch");
			param.sColumns = getRequest().getParameter("sColumns");
			param.iDisplayStart = Integer.parseInt( getRequest().getParameter("iDisplayStart") );
			param.iDisplayLength = Integer.parseInt( getRequest().getParameter("iDisplayLength") );
			param.iColumns = Integer.parseInt( getRequest().getParameter("iColumns") );
			param.iSortingCols = Integer.parseInt( getRequest().getParameter("iSortingCols") );
			param.iSortColumnIndex = Integer.parseInt(getRequest().getParameter("iSortCol_0"));
			param.sSortDirection = getRequest().getParameter("sSortDir_0");
			param.bDestroy =  Boolean.getBoolean(getRequest().getParameter("bDestroy")) ;
			param.listDataAjax=listdata;
			
			return param;
		}else{
			JQueryDataTableParamModel param = new JQueryDataTableParamModel();
			param.sEcho = "0";
			param.sSearch = "";
			param.sColumns = "lang_id,lang_libelle,lang_obs,lang_abrv";
			param.iDisplayStart = Integer.parseInt( "0" );
			param.iDisplayLength = Integer.parseInt( "7" );
			param.iColumns = Integer.parseInt( "4" );
			param.iSortingCols = Integer.parseInt( "1" );
			param.iSortColumnIndex = Integer.parseInt("0");
			param.sSortDirection = "desc";
			param.bDestroy = false;
			param.listDataAjax=listdata;
			return param;
		}
			 
	}

 
	
public static  List JQueryDefinePlaguinDataTable(JQueryDataTableParamModel param,List listData ,JsonObject jsonResponse,JsonArray data) 
  throws Exception, Throwable  {
        
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
					    	  
					    	if(object==null) continue;
					    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
							  fieldo.setAccessible(true);
							  Object obj=fieldo.get(object);
							  object=obj;
							  
						}
		    		 
		    		String  element1 = object==null?"":String.valueOf(object).toLowerCase();
		    		element1=element1!=null?element1:"";
		    		if(element1.contains(param.sSearch.toLowerCase()))testeExist=true;
					
				}else{
					Field field = bean.getClass().getDeclaredField(lesColunm[j]);
		    		field.setAccessible(true);
		    		String  element =((String)   String.valueOf(field.get(bean))).toLowerCase();
		    		element=element!=null?element:"";
		    		if(element.contains(param.sSearch.toLowerCase()))testeExist=true;
				}
	    		
			}
			if(testeExist){
				listDataTrie.add(bean); // add company that matches given search criterion
			}
		}
		iTotalDisplayRecords = listDataTrie.size();  
		
		
		if( !sEcho.equals("0") &&  !sEcho.equals("1")  &&  !sEcho.equals("2") ){
			
		 
		final int sortColumnIndex = param.iSortColumnIndex;
		final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;
		 
		 
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
		
		}
		 
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
					  Object obj=	 GenericWeb.getValueOject_with_name_field(beantrie, lesColunm[j]);
					  row.add(new JsonPrimitive(String.valueOf(obj!=null?obj:"")));
					}
					data.add(row);
			}	
	 return listDataTrie;
	}
	

	


public static  List JQueryBACKDataTable(JQueryDataTableParamModel param,List listData ,JsonObject jsonResponse,JsonArray data) 
throws Exception, Throwable  {
      
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
		    		if(element1.contains(param.sSearch.toLowerCase()))testeExist=true;
					
				}else{
					Field field = bean.getClass().getDeclaredField(lesColunm[j]);
		    		field.setAccessible(true);
		    		String  element =((String)   String.valueOf(field.get(bean))).toLowerCase();
		    		element=element!=null?element:"";
		    		if(element.contains(param.sSearch.toLowerCase()))testeExist=true;
				}
	    		
			}
			if(testeExist){
				listDataTrie.add(bean); // add company that matches given search criterion
			}
		}
		iTotalDisplayRecords = listDataTrie.size();  
		final int sortColumnIndex = param.iSortColumnIndex;
		final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;
		 
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
		 
		System.out.println(" param.iDisplayStart  "+  param.iDisplayStart );
		
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
					  Object obj=	 GenericWeb.getValueOject_with_name_field(beantrie, lesColunm[j]);
					  row.add(new JsonPrimitive(String.valueOf(obj!=null?obj:"")));
					}
					data.add(row);
			}	
	 return listDataTrie;
	}



public static  void doInitJQueryGrid(List listData )  throws Exception  {
	   
	    JQueryDataTableParamModel param=AjaxDataTablesUtility.getIniParam(getRequest());
	    String nameVect    = (String) getObjectValueModel(NAME_LIST_G);
	    
	    String sEcho = param.sEcho;
	    setObjectValueModel(DATA_LIST_AJAX+nameVect,listData);
	    JsonObject jsonResponse = new JsonObject();
	    JsonArray data = new JsonArray();
	    
 
	     
		
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
					    	if(object==null) { object="";break;}
					    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
							  fieldo.setAccessible(true);
							  Object obj=fieldo.get(object);
							  object=obj;
						}
		    		 
		    		String  element1 =  String.valueOf(object).toLowerCase();
		    		element1=element1!=null?element1:"";
		    		if(element1.contains(param.sSearch.toLowerCase()))testeExist=true;
					
				}else{
					Field field = bean.getClass().getDeclaredField(lesColunm[j]);
		    		field.setAccessible(true);
		    		String  element =((String)   String.valueOf(field.get(bean))).toLowerCase();
		    		element=element!=null?element:"";
		    		if(element.contains(param.sSearch.toLowerCase()))testeExist=true;
				}
	    		
			}
			if(testeExist){
				listDataTrie.add(bean); // add company that matches given search criterion
			}
		}
		
		
		iTotalDisplayRecords = listDataTrie.size(); // number of companies that match search criterion should be returned
		
		
		
		if(!sEcho.equals("0") &&  !sEcho.equals("1")){
			
		  final int sortColumnIndex = param.iSortColumnIndex;
		  final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;
		 
	
		Collections.sort(listDataTrie, new Comparator<Object>(){
			public int compare(Object bean, Object bean2) {
				
				  
				try {
					if(lesColunm[sortColumnIndex].indexOf(".")>0){
						
						final String[] lesColunmooo = lesColunm[sortColumnIndex].split("\\.");
						
						 Object object=bean;
						    for (int k = 0; k < lesColunmooo.length; k++) {
						    	
						    	if(object==null) { object="";break;}
						    	
						    	
						    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
								  fieldo.setAccessible(true);
								  Object obj=fieldo.get(object);
								  object=obj;
								  
							}
						    
			    		String  element1 =  String.valueOf(object).toLowerCase();
			    		element1=element1!=null?element1:"";
			    		
			    		
			    		Object object2=bean2;
					    for (int k = 0; k < lesColunmooo.length; k++) {
					    	if(object2==null) { object2="";break;}
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
		
		}
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
					  Object obj=	 GenericWeb.getValueOject_with_name_field(beantrie, lesColunm[j]);
					  row.add(new JsonPrimitive(String.valueOf(obj!=null?obj:"")));
					}
					data.add(row);
			}
			setObjectValueModel((String)getObjectValueModel(NAME_LIST_G), listDataTrie);
			jsonResponse.add(JSON_DATA_RESPONSE, data);
			getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(jsonResponse.toString());
	}

public static void getCurrentTimeStamp(Date dat) {
	
	 String ELmm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dat);
	 //System.out.println(" ------------ Annne   :   "+ELmm.substring(0, 3)+   "    -------------------------");
	 //System.out.println(" ------------ mois   :   "+ELmm.substring(5, 6)+   "    -------------------------");
	 //System.out.println(" ------------ jour   :   "+ELmm.substring(8, 9)+   "    -------------------------");
    //return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dat);
}
	
}
