package ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import jquery.datatables.controller.DataEditableTablesParamUtility;
import jquery.datatables.controller.EditableDataTableRequestParam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.DynamicClass;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.util.dataTableTemplate;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ActionDataTablesManager extends dataTableTemplate {

	public ActionDataTablesManager() {
		super();
		 
	}

	public static void doInitLoadingDataTableAjax(List listdata) throws Throwable, Throwable {

		try {
			JsonObject jsonResponse = new JsonObject();
			JsonArray data = new JsonArray();
			JQueryDataTableParamModel parameter = AjaxDataTablesUtility.getInitParam(getRequest(), listdata);
			AjaxDataTablesUtility.JQueryDefinePlaguinDataTable(parameter, listdata, jsonResponse, data);
			jsonResponse.add(JSON_DATA_RESPONSE, data);
			getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(jsonResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public static ModelAndView doLoadingDataTableAjax() throws Throwable, Throwable {

		try {
			
			JsonObject jsonResponse = new JsonObject();
			JsonArray data = new JsonArray();
			String nameVect    = (String) getObjectValueModel(NAME_LIST_G);
			JQueryDataTableParamModel 	parameter = AjaxDataTablesUtility.getParam(getRequest());
			setObjectValueModel("aParam",parameter);
			setObjectValueModel("ajsonResponse",jsonResponse);
			setObjectValueModel("ajsondata",data);
			List listOfmyData_Brute        = (List) getObjectValueModel(DATA_LIST_AJAX+""+nameVect);
			List listOfmyDataTwo = AjaxDataTablesUtility.JQueryDefinePlaguinDataTable(parameter, listOfmyData_Brute,jsonResponse, data);
			setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), listOfmyDataTwo);
			setObjectValueModel(DATA_LIST_AJAX,listOfmyDataTwo);
			jsonResponse.add(JSON_DATA_RESPONSE, data);
			getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(jsonResponse.toString());
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static ModelAndView doLoadingDataTablBAck() throws Throwable, Throwable {

		try {
			
			JsonObject jsonResponse = new JsonObject();
			JsonArray data = new JsonArray();
			String nameVect           = (String) getObjectValueModel(NAME_LIST_G);
			JQueryDataTableParamModel 	parameter = AjaxDataTablesUtility.getParam(getRequest());
			JQueryDataTableParamModel 	para =(JQueryDataTableParamModel) getObjectValueModel("aParam");
			List listOfmyData_Brute        = (List) getObjectValueModel(DATA_LIST_AJAX+""+nameVect);
			List listOfmyDataTwo = AjaxDataTablesUtility.JQueryDefinePlaguinDataTable(parameter, listOfmyData_Brute,jsonResponse, data); /* *///
			 setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), listOfmyDataTwo);
			 
			setObjectValueModel(DATA_LIST_AJAX,listOfmyDataTwo);
			jsonResponse.add(JSON_DATA_RESPONSE, data);
			
			jsonResponse.addProperty("iaa", new Integer(para.iDisplayStart));
			 jsonResponse.addProperty("ibb", new Integer(para.iDisplayLength));
			 
			
			getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(jsonResponse.toString());
			
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doUpdateEditableTable() throws Exception {

		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType(HTML_CONTENT_TYPE);
		try {
			 ActionDataTablesManager  aTablesManager= new ActionDataTablesManager();
			 aTablesManager.doTraiterColonne();
		} catch (Exception e) {
			getResponse().setStatus(500);
			out.println(e.getMessage());
			out.close();
		}
		return null;
	}

	public void doTraiterColonne() throws Exception {

		try {

			String sNameList   = getRequest().getParameter("sNameList")   == null ? "" : getRequest().getParameter("sNameList");
			String sNameId     = getRequest().getParameter("sNameId")     == null ? "" : getRequest().getParameter("sNameId");
			String name_column = getRequest().getParameter("sNameColumn") == null ? "" : getRequest().getParameter("sNameColumn");
			String sValueId    = getRequest().getParameter("sValueId")    == null ? "" : getRequest().getParameter("sValueId");
			String value       = getRequest().getParameter("sDataValue")  == null ? "" : getRequest().getParameter("sDataValue");

			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvalue+"+value);
			
			List listOfmyData = (List) getObjectValueModel(sNameList);
			DynamicClass dynamicClass = new DynamicClass();

			for (int i = 0; i < listOfmyData.size(); i++) {

				String typefiledid = "";
				Object bean = (Object) listOfmyData.get(i);
				Object element;
				if (sNameId.indexOf(".") > 0) {
					final String[] lesColunmooo = sNameId.split("\\.");
					Object object = bean;
					for (int k = 0; k < lesColunmooo.length; k++) {
						Field fieldo = object.getClass().getDeclaredField(lesColunmooo[k]);
						fieldo.setAccessible(true);
						Object obj = fieldo.get(object);
						object = obj;
						if (k == lesColunmooo.length - 1)
							typefiledid = fieldo.getType().getName();
					}
					element = object;
				} else {
					Field field = bean.getClass().getDeclaredField(sNameId);
					field.setAccessible(true);
					element = field.get(bean);
					typefiledid = field.getType().getName();
				}
				Object obNew = dynamicClass.castDynamicClassX(typefiledid, sValueId);

				if (element.equals(obNew)) {
					Object objetToReturn = null;
					if (name_column.indexOf(".") > 0) {
						final String[] lesColunmooo = name_column.split("\\.");
						Object object = bean;
						for (int k = 0; k < lesColunmooo.length; k++) {
							Field fieldo = object.getClass().getDeclaredField(lesColunmooo[k]);
							fieldo.setAccessible(true);

							if (k == lesColunmooo.length - 1) {
								objetToReturn = dynamicClass.castDynamicClassX(fieldo.getType().getName(), value);
								fieldo.set(object, objetToReturn);
							} else {
								Object obj = fieldo.get(object);
								object = obj;
							}
						}

					} else {
						Field field = bean.getClass().getDeclaredField(name_column);
						field.setAccessible(true);
						objetToReturn = dynamicClass.castDynamicClassX(field.getType().getName(), value);
						field.set(bean, objetToReturn);
					}
					getResponse().getWriter().print(objetToReturn);
					break;
				}
			}

		} catch (Exception e) {
			throw e;
		}

	}

	@SuppressWarnings("unchecked")
	public static ModelAndView doLoadingEditableDataTableAjax() throws Throwable, Throwable {

		try {
			JsonObject jsonResponse = new JsonObject();
			JsonArray data = new JsonArray();
			String nameVector = getRequest().getParameter("nameList");
			//System.out.println("String nameVector=getRequest().getParameter " + nameVector);
			
			//String type_param = getRequest().getParameter("type_param")==null?"":getRequest().getParameter("type_param");
			
			 
			//if( type_param.equals("") || type_param.equals("false") )
			EditableDataTableRequestParam  parameter = DataEditableTablesParamUtility.getParam(getRequest());
			//else
			 // parameter =(EditableDataTableRequestParam) getObjectValueModel(EditableDataTableParam); 
			
			setObjectValueModel("Colunm" + nameVector, parameter.sColumns);
			List listOfmyData = (List) getObjectValueModel(nameVector);
			List listOfmyDataTwo = DataEditableTablesParamUtility.doGetAzizWild3asoul(parameter, listOfmyData,jsonResponse, data, getRequest(), getResponse());
			setObjectValueModel("IndeXo"+nameVector, listOfmyDataTwo);
			//setObjectValueModel(nameVector, listOfmyDataTwo);
			jsonResponse.add(JSON_DATA_RESPONSE, data);
			getResponse().setContentType(JSON_CONTENT_TYPE);
			getResponse().getWriter().print(jsonResponse.toString());
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	 

	@SuppressWarnings("unchecked")
	public static ModelAndView doDelete_Row_EditableDataTableAjax() throws Throwable, Throwable {

		String nameVector = getRequest().getParameter("nameList");
		String id = getRequest().getParameter("id");
		List listOfmyData = (List) getObjectValueModel(nameVector);
		String LesColmns = (String) getObjectValueModel("Colunm" + nameVector);
		final String[] lesColunm = LesColmns.split(",");

		for (int i = 0; i < listOfmyData.size(); i++) {
			Object beantrie = (Object) listOfmyData.get(i);

			String element = "";
			if (lesColunm[0].indexOf(".") > 0) {

				final String[] lesColunmooo = lesColunm[0].split("\\.");

				Object object = beantrie;

				for (int k = 0; k < lesColunmooo.length; k++) {

					Field fieldo = object.getClass().getDeclaredField(lesColunmooo[k]);
					fieldo.setAccessible(true);
					Object obj = fieldo.get(object);
					object = obj;

				}

				element = String.valueOf(object).toLowerCase();

			} else {
				Field field = beantrie.getClass().getDeclaredField(lesColunm[0]);
				field.setAccessible(true);
				element = ((String) String.valueOf(field.get(beantrie))).toLowerCase();
			}

			if (element.toLowerCase().equals(id)) {

				listOfmyData.remove(i);
				break;

			} else {
				//getResponse().getWriter().print("Error - company cannot be found"); 
			}

		}

		return null;

	}

	@SuppressWarnings("unchecked")
	public static ModelAndView doAdd_row_EditableDataTableAjax() throws Throwable, Throwable {

		try {
			JsonObject jsonResponse = new JsonObject();
			JsonArray data = new JsonArray();
			String nameVector = getRequest().getParameter("nameList"); 
			final EditableDataTableRequestParam parameter = DataEditableTablesParamUtility.getParam(getRequest());

			List listOfmyData = (List) getObjectValueModel(nameVector);

			final String[] lesColunm = parameter.sColumns.split(",");

			Object newObject = getObjectValueModel(MODEL_BEAN);

			for (int i = 0; i < lesColunm.length; i++) {

				if (lesColunm[i].indexOf(".") > 0) {

					final String[] lesColunmooo = lesColunm[i].split("\\.");

					Object object = newObject;

					for (int k = 0; k < lesColunmooo.length; k++) {

						Field fieldo = object.getClass().getDeclaredField(lesColunmooo[k]);
						fieldo.setAccessible(true);
						if (k == lesColunmooo.length - 1) {
							fieldo.set(object, "");
						}
						Object obj = fieldo.get(object);
						object = obj;

					}

				} else {
					Field field = newObject.getClass().getDeclaredField(lesColunm[i]);
					field.setAccessible(true);
					field.set(newObject, "");

				}
			}
			listOfmyData.add(newObject);

			List listOfmyDataTwo = DataEditableTablesParamUtility.doGetAzizWild3asoul(parameter, listOfmyData,
					jsonResponse, data, getRequest(), getResponse());
			setObjectValueModel("IndeXo" + nameVector, listOfmyDataTwo);
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

	
	
public static ModelAndView doTeste_List( ) throws Exception{
		
		try {
	 
			String sNameList   = getRequest().getParameter("nameList")   == null ? "" : getRequest().getParameter("nameList");
			String sNameId     = getRequest().getParameter("NameId")     == null ? "" : getRequest().getParameter("NameId");
			
			List listOData=(List) getObjectValueModel(sNameList);
			 
			 String message="";
		     if(listOData==null || listOData.size()==0)
			   message=" list vide xxxxxxxxxxxxxxxxxxxxxxxxxxxx ";
		     
			  getResponse().setContentType(HTML_CONTENT_TYPE);
			  getResponse().getWriter().print(message);
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}


	
	@SuppressWarnings("unchecked")
	public static ModelAndView doUpdateEditableDataTableAjax() throws Throwable, Throwable {

		String nameVector = getRequest().getParameter("nameList");
		String id = getRequest().getParameter("id");
		//int columnId = Integer.parseInt(getRequest().getParameter("columnId"));
		int columnPosition = Integer.parseInt(getRequest().getParameter("columnPosition"));
		//int rowId = Integer.parseInt(getRequest().getParameter("rowId"));
		String value = getRequest().getParameter("value");
		//String columnName = getRequest().getParameter("columnName");
		List listOfmyData = (List) getObjectValueModel(nameVector);
		String LesColmns = (String) getObjectValueModel("Colunm" + nameVector);
		final String[] lesColunm = LesColmns.split(",");

		for (int i = 0; i < listOfmyData.size(); i++) {
			Object beantrie = (Object) listOfmyData.get(i);

			String element = "";
			if (lesColunm[0].indexOf(".") > 0) {
				final String[] lesColunmooo = lesColunm[0].split("\\.");
				Object object = beantrie;
				for (int k = 0; k < lesColunmooo.length; k++) {
					Field fieldo = object.getClass().getDeclaredField(lesColunmooo[k]);
					fieldo.setAccessible(true);
					Object obj = fieldo.get(object);
					object = obj;
				}
				element = String.valueOf(object).toLowerCase();
			} else {
				Field field = beantrie.getClass().getDeclaredField(lesColunm[0]);
				field.setAccessible(true);
				element = ((String) String.valueOf(field.get(beantrie))).toLowerCase();
			}

			if (element.toLowerCase().equals(id)) {

				if (lesColunm[columnPosition].indexOf(".") > 0) {
					final String[] lesColunmooo = lesColunm[columnPosition].split("\\.");
					Object object = beantrie;
					for (int k = 0; k < lesColunmooo.length; k++) {
						Field fieldo = object.getClass().getDeclaredField(lesColunmooo[k]);
						fieldo.setAccessible(true);
						Object obj = fieldo.get(object);
						object = obj;
						if (k == lesColunmooo.length - 1) {
							fieldo.set(object, value);
						}
					}
				} else {
					Field field = beantrie.getClass().getDeclaredField(lesColunm[columnPosition]);
					field.setAccessible(true);
					field.set(beantrie, value);
				}
				getResponse().getWriter().print(value);
			} else {
				//getResponse().getWriter().print("Error - company cannot be found"); 
			}

		}

		//		for(GlibelleBean glibelleBean: listOfmyData)
		//		{
		//			if(glibelleBean.getIdLiblleBean().getLang_id().toLowerCase().equals(id))
		//			{
		//				switch (columnPosition)
		//	            {
		//	                case 0:
		//	                	glibelleBean.getIdLiblleBean().setLang_id(value);
		//	                    break;
		//	                case 1:
		//	                	glibelleBean.setLanglibelle(value);
		//	                    break;
		//	                case 2:
		//	                	glibelleBean.setLib_libelle(value);
		//	                case 3:
		//	                	glibelleBean.setLib_abrv(value);
		//	                    break;
		//	                default:
		//	                    break;
		//	            }
		//				getResponse().getWriter().print(value);
		//				return null;
		//				 
		//			}
		//		}
		//getResponse().getWriter().print("Error - company cannot be found");

		return null;

	}

	private static final long		serialVersionUID	= 1L;

 

	public ModelAndView doLoadAutocompleteList() throws ServletException, IOException, SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

		PrintWriter out = getResponse().getWriter();
		getResponse().setContentType("text/html");
		getResponse().setHeader("Cache-control", "no-cache, no-store");
		getResponse().setHeader("Pragma", "no-cache");
		getResponse().setHeader("Expires", "-1");

		String nameListAjax = getRequest().getParameter("nameListAutocompletAjax");
		List listData = (List) getObjectValueModel(nameListAjax);
		String query = getRequest().getParameter("term");
		String typeSearch = getRequest().getParameter("typeSearch");
		String fcode = getRequest().getParameter("fieldcode");
		String flabel = getRequest().getParameter("fieldlabel");
		try {
			Collection<JSONObject> items = doRenderListJson(listData, query, typeSearch, fcode, flabel);
			out.println(items.toString());
			//out.close();
		} catch (Exception e) {
			//getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			out.println(e.getMessage());
			out.close();
		}
		return null;

	}

	public ModelAndView doLoadSelectList() throws ServletException, IOException, SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

		getResponse().setContentType("text/html");
		getResponse().setHeader("Cache-control", "no-cache, no-store");
		getResponse().setHeader("Pragma", "no-cache");
		getResponse().setHeader("Expires", "-1");

		String nameListAjax = getRequest().getParameter("nameList_select");
		List listData = (List) getObjectValueModel(nameListAjax);
		String fcode = getRequest().getParameter("fieldcode");
		String flabel = getRequest().getParameter("fieldlabel");
		try {
			doRenderListJson_Select(listData, fcode, flabel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static ModelAndView doRenderListJson_Select(List listData, String fcode, String flabel) throws IOException {
		JSONArray items = new JSONArray();
		JSONObject json = new JSONObject();
		try {
			items= doTraiterJsonSelect(listData, fcode, flabel);
			json.put("myliste", items);
			getResponse().setContentType("application/json");
			getResponse().getWriter().write(json.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static  JSONArray  doTraiterJsonSelect(List listData, String fcode, String flabel) throws IOException {

		JSONArray items = new JSONArray();
		 

		try {
			for (int i = 0; i < listData.size(); i++) {
				Object bean = (Object) listData.get(i);
				String elementChoiSi = "";
				JSONObject jsonObj = new JSONObject();
				Class<?> c = bean.getClass();
				if (fcode.indexOf(".") > 0) {
					final String[] lesColunmooo = fcode.split("\\.");
					Object object = bean;
					for (int k = 0; k < lesColunmooo.length; k++) {
						Field fieldo = object.getClass().getDeclaredField(lesColunmooo[k]);
						fieldo.setAccessible(true);
						Object obj = fieldo.get(object);
						object = obj;
					}
					String Viaelement = String.valueOf(object);
					Viaelement = Viaelement != null ? Viaelement : "";
					jsonObj.put("keyx", Viaelement);
				} else {

					Field fcodo = c.getDeclaredField(fcode);
					fcodo.setAccessible(true);
					Object valueOfFieldcodo = fcodo.get(bean);
					String elementcodo = String.valueOf(valueOfFieldcodo);
					jsonObj.put("keyx", elementcodo);
				}

				Field fESD = c.getDeclaredField(flabel);
				fESD.setAccessible(true);
				Object valueOfField = fESD.get(bean);
				elementChoiSi = String.valueOf(valueOfField);
				jsonObj.put("valuex", elementChoiSi);
				items.put(jsonObj);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}

	public static Collection<JSONObject> doRenderListJson(List listData, String query, String typeSearch, String fcode,
			String flabel) throws IOException {

		Collection<JSONObject> items = new ArrayList<JSONObject>();
		/*JSONObject  jsonObjInit = new JSONObject ();
		  jsonObjInit.put(flabel,"Libelle");
		  jsonObjInit.put(fcode,"Code");
		  items.add(jsonObjInit);*/
		try {
			for (int i = 0; i < listData.size(); i++) {
				Object bean = (Object) listData.get(i);
				String elementChoiSi = "";
				JSONObject jsonObj = new JSONObject();
				if (typeSearch != null && typeSearch.equals("bylabel")) {
					
					    Object Viaelement=	 GenericWeb.getValueOject_with_name_field(bean, fcode);
						Viaelement = Viaelement != null ? Viaelement : "";
						jsonObj.put(fcode, Viaelement);
						
						Object Viaelem=	 GenericWeb.getValueOject_with_name_field(bean, flabel);
						Viaelem = Viaelem != null ? Viaelem : "";
						elementChoiSi = String.valueOf(Viaelem);
						jsonObj.put(flabel, elementChoiSi);

				}

				if (typeSearch != null && typeSearch.equals("byId")) {
					Object ViaelemeU=	 GenericWeb.getValueOject_with_name_field(bean, fcode);
					ViaelemeU = ViaelemeU != null ? ViaelemeU : "";
					elementChoiSi = String.valueOf(ViaelemeU);
					jsonObj.put(fcode, elementChoiSi);
					
					Object valueOfFieldlibo=	 GenericWeb.getValueOject_with_name_field(bean, flabel);
					valueOfFieldlibo = valueOfFieldlibo != null ? valueOfFieldlibo : "";
					 
					jsonObj.put(flabel, valueOfFieldlibo);
				}
				if (elementChoiSi.toLowerCase().startsWith(query.toLowerCase())) {
					items.add(jsonObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return items;
	}

 

}
