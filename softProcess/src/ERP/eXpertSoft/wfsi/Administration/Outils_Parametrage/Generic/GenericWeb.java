package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.web.ActionAuthentificationManager;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.service.EntiteAdminService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.service.GlibelleService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.web.ActionGlibelleManager;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.service.SpoorService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.web.ActionSpoorManager;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.MessageBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.model.configDevelopementBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.service.configDevelopementService;

@Controller
public class GenericWeb       {
	 
	
	private  static final ThreadLocal<HttpServletRequest>  requestThread     = new ThreadLocal<HttpServletRequest>();
	private  static final ThreadLocal<HttpServletResponse> responseThread    = new ThreadLocal<HttpServletResponse>();
	private  static final ThreadLocal<HttpSession>         sessionThread     = new ThreadLocal<HttpSession>();

	public  static HttpServletRequest getRequest() {
            return requestThread.get();
    }
	
	public static HttpServletResponse getResponse() {
	        return responseThread.get();
	}
	
	
	public static HttpSession getSession() {
        return sessionThread.get();
    }
	
	 
    public static void destroyThreadLocal() {
    	if(requestThread!=null)  requestThread.remove();
    	if(responseThread!=null) responseThread.remove();
    	if(sessionThread!=null)  sessionThread.remove();
    }

   
	
	public GenericWeb() {
		super();
	}
 
	 
	public static  String JSON_DATA_RESPONSE    = "aaData";
	public static  String INDEX_ROW             = "indexRow";
	public static  String INDEX_ROW_PAGING_G    = "indexRowPaGing";
	public static  String INDEX_ROW_TO_ROMV     = "indexRowRem";
	public static  String JSON_CONTENT_TYPE     = "application/Json";
	public static  String HTML_CONTENT_TYPE     = "text/html";
	public static  String PARAM_HIDDEN          = "?HiddenAction=";
	public static  String DISPLAY               = "display";
	public static  String TEMP_MENU_ACTION_GEN  = "tml";
	
	public static  String HIDDEN_ACTION         = "HIDDEN_ACTION_GEN";
	
	
	public static  String MENU_ACTION_DISPLAY   ="disMenuX";
	
	public static  String TEMP_CHEMIN_TITLE     ="tmlx";

	
	public static  String BEAN_SESSION          = "bs";
	public static  BDateTime  bDateTime         = new BDateTime();
	public static final String FALSE            = "false";
	public static final String TRUE             = "true";
	public static final String CREATE           ="Creation";
	public static final String UPDATE           ="Modifier";
	public static final String DELETE           ="Supprimer";
	public static final String CONSULT          ="Consulter";
	public static final String SEARCH           ="Chercher";
	public static final String BLOCK            ="block";
	public static final String NONE             ="none";
	public static final String submit           ="submit";
	public static final String button           ="button";
	public static final String READONLY         ="readonly";
	public static final String DISBLED          ="disabled";
	public static final String BTN_ADD          ="Enregistrer";
	public static final String BTN_SEARCH       ="Chercher";
	public static final String BTN_UPDATE       ="Valider";
	
	public static final String BTN_STOCK        ="Valider-Stocker";
	public static final String BTN_VLD_SERVICE  ="Valider-Service";
	public static final String BTN_RET_SERVICE  ="Valider-Retour";
	
	public static final String BTN_VALID        ="Valider";
	public static final String BTN_DELETE       ="Supprimer";
	public static final String PAGE_ACEUIL      ="Aceuil/Aceuil";
	public static       String LIST_DATA_AJAX   ="listdataTrie";
	public static       String MESSAGERROR      = "messageError";
	public static       String CUST_MESSAGERROR = "custmessageError";
	public static       String HiddenActionX     ="";
	public static       String CONTEXT_PATH     = "context_path";
	public static       String BASE_URL_AJAX_PROJECT  = "baseAjaxUrl";
	public static       String URL_LOAD_DATA_TABLE    = "urlloadDataTableAjax";
	public static       String URL_LOAD_LISTE_CoRR    = "urlloadListeAuto";
	
	public static       String TEMPLATE                     = "LaTemplateEncours";
	public static       String jQUERYdataTABLEPARAM         = "jquerydatatableparam";
	public static       String EditableDataTableParam       = "EditDataTableParam";
	 
	public static final String FETCH              = "fetch";
	public static final String GO_BACK            = "go_back";


	public static    String ROOT                  = "path";
	public static    String PATH_JSP              = "view";
	public static    String NAME_LIST_G           = "nameList";
	public static    String NAME_GRID_G           = "nameGrid";
	public static    String LIST_VIEW_G           = "LIST_VIEW";
	 
	public static    String LIST_PDF_EXCEL        = "list_pdf_excel";
	
	
	
	public static    String ACT_FETCH_AJAX_GLOBAL = "action_fetch_ajax_global";
	
	public static    String DATA_LIST_AJAX        = "dataListAajx";
	public static    String S_echo                = "sEcoo";
	
	public  static   String FORM_BEAN             = "detailBean";
	public  static   String ORIGINAL_FORM_BEAN    = "orgDetailBean";
	public  static   String SEARCH_BEAN           = "searchBean";
	public  static   String MODEL_BEAN            = "model_bean";
	public  static   String NAME_TEMPLATE         = "NAME_TEMPLATE";
	public  static   String MAP_FIELD_BEAN        = "MapfieldBean"; 
	public  static   String VECT_LIBELLE_SOUS_MODULE  = "vect_libelle_sous_module";
	public  static   String MAP_LIBELLE_ENTITE_EN_COURS  = "map_libelle_entite_en_cours";
	
	
	public  static   String MAP_CRITERE_DE_RECHERCHE        = "map_critere_de_recherche";
	
	
	public  static   String IS_SPOORED            = "is_Spoored";
	public  static   String ENTITES               = "entites";
	public  static   String SCHEMA                = "schema";
	public  static   String ID_ENTITE             = "id_entite";
	
	public  static   String GROUPE_TARIF_VENTE_PUBLIC             = "131";
	public  static   String GROUPE_TARIF_ACHAT_STANDAR             = "182";

	
	 
	

	
	
	
    private static    GlibelleService  serviceGlibellee;
    public  static    SpoorService     serviceSpoor;
    
    public  static    ActionSpoorManager   actionSpoorManager = new  ActionSpoorManager() ;
     
     
    public     EntiteAdminService        serviceEntiteAdmin;
    @Autowired
	public void setEntiteAdminService(EntiteAdminService serviceEntiteAdmin) {
		this.serviceEntiteAdmin = serviceEntiteAdmin;
	}
    
   
    
    public static boolean ifFonctionEqual(String fonction) {
    	
    BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
		if( bSession.getFct_id().equals(fonction))  return true;
		else
		return false;
		
	}
    
    public static BeanSession getBeanSession() {
        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
        return bSession;
     }
    
    public Map convertStringToHashMap(String strData){
		
		Map<String, String> myMap = new HashMap<String, String>();
		if(StringUtils.isBlank(strData)) return myMap;
		String[] pairs = strData.split(",");
		 for (int i=0;i<pairs.length;i++) {
		     String pair = pairs[i];
		     String[] keyValue = pair.split(":");
		     myMap.put(keyValue[0],  keyValue[1] );
		 }
		 return myMap;
	}
    
    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	  }

    public Session getHibernateSession( SessionFactory sessionFactory  ){
    	Session hibernateTemplate= null;
    	try {
    		  hibernateTemplate = sessionFactory.getCurrentSession();
		} catch (Exception e) {
		}
		return hibernateTemplate;
    }
    
    protected void  commitTransaction(Session session){
    	 session.getTransaction().commit();
    }
    
    protected boolean   sessionIsTrue(Session session){
    	
    	if( session!=null  &&  session.getTransaction()!=null ) 
    		return true; 
    	  else
    		return false;
   }
    
    
    
    protected void  rollbackTransaction(Session session){
    	
    	if( session!=null  &&  session.getTransaction()!=null){
    		session.clear();
    	   	session.getTransaction().rollback();
    	}
    
   }
    
    protected Session  openSessionHibernate(SessionFactory sessionFactory){
    	Session newSession =  sessionFactory.openSession();
    	newSession.beginTransaction();
    	return newSession;
      }
    

	@Autowired
	public void setSpoorService(SpoorService serviceSpoor) {
		this.serviceSpoor = serviceSpoor;
	}
	
     public static GlibelleService getServiceGlibellee() {
 		return serviceGlibellee;
 	}
	 
	 
	
	public static String get(String valueHidAct) {
		return  PARAM_HIDDEN+valueHidAct;
	}
	
	 



	 public static String getString(String stringToEdit)throws Exception{
		  try {
		   if(stringToEdit==null) return "";
		   stringToEdit = stringToEdit.replace("'", "''");
		   
		  return stringToEdit;
			} catch (Exception e) {
				throw e;
			}
	      
		}
	 
	
	
	
 
public static   void    setValueOject_with_name_field(Object beantrie,String id,Object valuEs) throws Exception{
		
		 
		try {
		
		if(id.indexOf(".")>0){
			final String[] lesColunmooo = id.split("\\.");
			 Object object=beantrie;
			    for (int k = 0; k < lesColunmooo.length; k++) {
			    	   
					  if(k==lesColunmooo.length-1){
						  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
						  fieldo.setAccessible(true);
						  fieldo.set(object,valuEs==null?"":valuEs) ;
					  }else{
						  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
						  fieldo.setAccessible(true);
						  Object obj=fieldo.get(object);
						  object=obj;
						  
					  }
					  
				}
			 
		}else{
		 Field field = beantrie.getClass().getDeclaredField(id);
		 field.setAccessible(true);
		 field.set(beantrie,valuEs==null?"":valuEs) ;
		}
		
		} catch (Exception e) {
		 throw  e;
		}
		 
		
	}

	public   static Object  getValueOject_with_name_field(Object beantrie,String id) throws Exception{
		
		Object elmTOreturn=null;
		DynamicClass dClass= new DynamicClass();
		try {
		
		if(id.indexOf(".")>0){
			final String[] lesColunmooo = id.split("\\.");
			 Object object=beantrie;
			    for (int k = 0; k < lesColunmooo.length; k++) {
			    	if(object==null) { elmTOreturn="";break;}
					  if(k==lesColunmooo.length-1){
						  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
						  fieldo.setAccessible(true);
						  
						  if(fieldo.get(object)==null) 
							elmTOreturn="";
							   else
				 		    elmTOreturn=dClass.castDynamicClassObject_with_field(fieldo,object);
					  }else{
						  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
						  fieldo.setAccessible(true);
						  Object obj=fieldo.get(object);
						  object=obj;
						  
					  }
					  
				}
			 
		}else{
		 Field field = beantrie.getClass().getDeclaredField(id);
		 field.setAccessible(true);
		 if(field.get(beantrie)==null) 
			 elmTOreturn="";
		   else
		     elmTOreturn=dClass.castDynamicClassObject_with_field(field,beantrie);
		}
		
		} catch (Exception e) {
		 throw  e;
		}
		return elmTOreturn;
		
	}
	
	public static  Object getValueObjSimpleFromList(Object id_toSearch,List listData,String id, String field_r)throws Exception {
		Object objet_re = null;
		try {
			for (int i = 0; i < listData.size(); i++) {
				Object beantrie=(Object) listData.get(i);
				Object elmnt=getValueOject_with_name_field(beantrie, id);
				 if(elmnt.equals(id_toSearch)){
	    			 objet_re=getValueOject_with_name_field(beantrie, field_r);
	    			 break;
				 }
			}
		} catch (Exception e) {
			throw  e;
		}
		
		return objet_re;
	}
	
	
	
	
	
	
	
	
	public static Object getIndexFromDataGrid_v1(String nameVect) throws Exception{
		  try {
		  
		  
		    
		  String  theIdRow=getRequest().getParameter("theIdRow");
		  int index = Integer.parseInt(theIdRow);
		 
		  
		  
		  List listOfmyData_Brute       = (List) getObjectValueModel(DATA_LIST_AJAX+""+nameVect);
		  List   listPagin              = (List) getObjectValueModel(nameVect);
		  Object rowBean                = (Object) listPagin.get(index);
		  
		   
		  int indexBrute=listOfmyData_Brute.indexOf(rowBean);
		  setObjectValueModel(INDEX_ROW_TO_ROMV, String.valueOf(indexBrute));
		  setObjectValueModel(INDEX_ROW, String.valueOf(index));
		  setObjectValueModel(ORIGINAL_FORM_BEAN, ProcessUtil.cloneObject(rowBean) );
		  
		  
		  return rowBean;
			} catch (Exception e) {
				 throw e;
			}
	      
		}
	  
	    protected void remove_row_from_list(String nameVect) {  
		 try {
			    String  indexBrute               =(String) getObjectValueModel(INDEX_ROW_TO_ROMV);
				if(indexBrute==null)             throwNewException("Index ligne Table est Null");
				List    listOfmyData_Brute       = (List) getObjectValueModel(DATA_LIST_AJAX+""+nameVect);
				listOfmyData_Brute.remove(Integer.parseInt(indexBrute));
				 
			} catch (Exception e) {
				displayException(e);
			}
		}
	    
	    protected void update_row_from_list(String nameVect,Object obj) {  
			 try {
				    String  indexBrute               =(String) getObjectValueModel(INDEX_ROW_TO_ROMV);
					if(indexBrute==null)             throwNewException("Index ligne Table est Null");
					List    listOfmyData_Brute       = (List) getObjectValueModel(DATA_LIST_AJAX+""+nameVect);
					listOfmyData_Brute.remove(Integer.parseInt(indexBrute));
					listOfmyData_Brute.add(Integer.parseInt(indexBrute), obj);
				} catch (Exception e) {
					displayException(e);
				}
			}
	    
	    
	  
	  public static  void removeObjectModel(String  key){
		  try {
			  ModelAndView  modelRemveObj=getModel();
			  if(modelRemveObj!=null &&  modelRemveObj.getModel().get(key)!=null){
				modelRemveObj.getModel().remove(key);
			    getSession().setAttribute(getSession().getId(), modelRemveObj);
			  } 
		  } catch (Exception e) {
			}
		}
	  
	  public static  Object getObjectValueModel(String  key){
		  try {
		  Object obj= new Object();
		  ModelAndView  modelGetObj=getModel();
		  
		 
		  if(modelGetObj!=null &&  modelGetObj.getModel().get(key)!=null){
			 obj= modelGetObj.getModel().get(key);
				return obj; 
			}else{
				return null;
			}
			
		  } catch (Exception e) {
			  
			  return null;
			}
		}
	  
       public static  void setObjectValueModel(String  key,Object value){
    	   try {
    		   ModelAndView  modelSetObj=getModel();
    		   if(modelSetObj!=null ){
    		   modelSetObj.addObject(key, value);
    		   getSession().setAttribute(getSession().getId(), modelSetObj);
    		   }
    	   } catch (Exception e) {
    		   
   		}
		}
       
       public static  void setObjectViewName(String  viewName){
    	   try {
    		   
    		   ModelAndView  modelSetViewObj=getModel();
    		   if(modelSetViewObj!=null ){
    		   modelSetViewObj.setViewName(viewName);
    		   getSession().setAttribute(getSession().getId(), modelSetViewObj);
    		   }
    	   } catch (Exception e) {
    		   displayException(e);
   		}
 		}
       
       public static  ModelAndView getModel( ){
    	   
    	   if(getRequest()==null ) return null;
    	   HttpSession hSession=getRequest().getSession(false);
    	   if(hSession==null)  return null;
    	   
    	  
    	   
    	   ModelAndView  model=(ModelAndView) hSession.getAttribute(hSession.getId());
  		  if(model==null)  
  			return null;
  		   else
  		  return model;
    	   
  		}
	  
       
	  
	  
	  public static  BDateTime sys_date(){
		  return  bDateTime;
			 
		}
	  
	 
	  
	
	  public static  void throwNewException(String  key) throws Exception{
			 System.out.println(" ------------ throw new Exception  : "+key+   "    -------------------------");
				throw new Exception(key);
		}
	  
	   
	  
	  public    void setIdBean(Object obj1,Object obj2,String att) throws Exception{
		  
		   Field field = obj1.getClass().getDeclaredField(att);
	       field.setAccessible(true);
	       Object value= field.get(obj1);
	       
	       Field field2 = obj2.getClass().getDeclaredField(att);
	       field2.setAccessible(true);
	       field2.set(obj2, value); 
	       
				 
		}
	  
	  
	  
	  public static  String searchActionGeneric(Object object_First,String HiddenAction ) throws Exception{
		  String  fieldName="";
		  
		 
		  
		  if(object_First==null)
			  return "";
		  
		  for (Field field : object_First .getClass().getDeclaredFields()) {
		         field.setAccessible(true); // You might want to set modifier to public first.
		         Object value= field.get(object_First);
		         if (value != null &&  value.equals(HiddenAction)) {
		             fieldName=field.getName();
		             System.out.println(" ------------ Generic The fieldName  : "+fieldName+   "    -------------------------");
		             break;
		         }
		     }
		   for (Field fields : object_First .getClass().getDeclaredFields()) {
		    	 fields.setAccessible(true); // You might want to set modifier to public first.
		         String fName=fields.getName();
		         if(fName!=null && fName.startsWith("IS_") && fields.getType().getName().equals("boolean"))
		        	 fields.set(fName, false);
		     }
		   
		   if(!fieldName.equals("")){
				  String booleanAct="IS_"+fieldName;
				  Field fields =object_First.getClass().getDeclaredField(booleanAct);
				  fields.setAccessible(true);
				  String fName=fields.getName();
				  fields.set(fName, true);
			  }
		 
			   
		  
		  return  fieldName;
	 } 
	  
	  
	  public static    boolean searchBooleanActionGeneric(  Object object_Fir , String  HiddenAction ) throws Exception{
			boolean  resul=false;
			
			 
			  
			 for (Field fiel1 : object_Fir .getClass().getDeclaredFields()) {
				   fiel1.setAccessible(true);  
				   String fNameGen=fiel1.getName();
				   if(fNameGen!=null && fNameGen.equals(HiddenAction) && fiel1.getType().getName().equals("boolean")){
					   fiel1.set(fNameGen, true);
				   resul=true;
				   }
				   if(fNameGen!=null &&   fNameGen.startsWith("i$_")  && !fNameGen.equals(HiddenAction) && fiel1.getType().getName().equals("boolean"))
				   {
					   fiel1.set(fNameGen, false);
				   }
				}
		  
		    return  resul;
	 } 
	  
  
	  
	  
	  public static  boolean  searchActionBooleanInTemplate(Object beanTempNotGeneric,   GenericActionBean  GenBean  , String  HiddenAction ) throws Exception{
		  
			
		  
		  boolean resultat=false;
		  if(beanTempNotGeneric==null) return false;
		  
		   for (Field fields : beanTempNotGeneric .getClass().getDeclaredFields()) {
		    	 fields.setAccessible(true);  
		         String fName=fields.getName();
		         if(fName!=null && fName.startsWith("i$_") && fields.getType().getName().equals("boolean"))
		        	 fields.set(fName, false);
		     }
		   
		   for (Field fiel1 : beanTempNotGeneric .getClass().getDeclaredFields()) {
			   fiel1.setAccessible(true);  
			   String fNamenotGen=fiel1.getName();
			   if(fNamenotGen!=null && fNamenotGen.equals(HiddenAction) && fiel1.getType().getName().equals("boolean")){
				   fiel1.set(fNamenotGen, true);
				   resultat=true;
			   }

			   if(fNamenotGen!=null &&   fNamenotGen.startsWith("i$_")  && !fNamenotGen.equals(HiddenAction) && fiel1.getType().getName().equals("boolean"))
			   {
				   fiel1.set(fNamenotGen, false);
			   }
			   
			}
		     
			  for (Field fieldsGen : GenBean.getClass().getDeclaredFields()) {
				  fieldsGen.setAccessible(true);  
			      String fname=fieldsGen.getName();
			      if(fname!=null && fname.startsWith("i$_") && fieldsGen.getType().getName().equals("boolean"))
			      fieldsGen.set(fname, false);
			  }
			 
		  
		  return  resultat;
	 } 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  public    void  loadPathActionView  (Object objetout,HttpServletRequest req ) throws Exception{
		   
		  
		  try {
			
		
	    	     removeObjectModel(NAME_TEMPLATE);
	    	     
	    	      
	    	     removeObjectModel(ACT_FETCH_AJAX_GLOBAL);
	    	     removeObjectModel(VECT_LIBELLE_SOUS_MODULE);
	    	     
	    	     
	    	     
	    	     removeObjectModel(IS_SPOORED);
	    	     removeObjectModel(SCHEMA);
	    	     
	    	     removeObjectModel(ID_ENTITE);
	    	     removeObjectModel(MODEL_BEAN);
	        	 removeObjectModel(ROOT);
	        	 
	        	 
	        	 
	        	 
	        	 
	        	 
	        		 removeObjectModel(ENTITES);
	        		 removeObjectModel(NAME_LIST_G);
		        	 removeObjectModel(NAME_GRID_G);
		        	 removeObjectModel(MAP_FIELD_BEAN);
		        	 removeObjectModel("propertieField");
		        	 
		    	     removeObjectModel(MAP_CRITERE_DE_RECHERCHE);
		    	     removeObjectModel(ACT_FETCH_AJAX_GLOBAL);
	        	 
	        	
	        	 
	        	 removeObjectModel("FORM_VIEW");
	        	 removeObjectModel("FILTER_VIEW");
	        	 removeObjectModel("LIST_VIEW");
	        	 
	        	 System.out.println(" ------------ ObjectPricipal  : "+ System.identityHashCode(objetout)+   "    -------------------------");
	        	 
		  if(objetout!=null) {
			   objetout=objetout.getClass().newInstance();
			   setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_AJAX_FETCH");
			  
			        
			        
		  for (Field field : objetout.getClass().getDeclaredFields()) {
		         field.setAccessible(true); // You might want to set modifier to public first.
		         Object value= field.get(objetout);
		         String nameAttribute=field.getName();
		         
		         
		          
		          
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("NAME_TEMPLATE")){
		    	     Object objBean = (Object) value;
		    	     setObjectValueModel(NAME_TEMPLATE, objBean);
		    	     System.out.println(" ------------ NAME_TEMPLATE  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("MapfieldBean")){
		    	     Object objBean = (Object) value;
		    	       setObjectValueModel(MAP_FIELD_BEAN, objBean);
		    	     //System.out.println(" ------------ FIELD_BEAN  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("propertieField")){
		    	       Object objBean = (Object) value;
		    	       setObjectValueModel("propertieField", objBean);
		    	     //System.out.println(" ------------ FIELD_BEAN  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("MAP_CRITERE_DE_RECHERCHE")){
		    	     Object objBean = (Object) value;
		    	     setObjectValueModel(MAP_CRITERE_DE_RECHERCHE, objBean);
		    	     //System.out.println(" ------------ MAP_CRITERE_DE_RECHERCHE  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         
		         
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("is_Spoored")){
		    	     Object objBean = (Object) value;
		    	     setObjectValueModel(IS_SPOORED, objBean);
		    	     //System.out.println(" ------------ IS_SPOORED  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("schema")){
		    	     Object objBean = (Object) value;
		    	     setObjectValueModel(SCHEMA, objBean);
		    	     //System.out.println(" ------------ SCHEMA  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("entites")){
		    	     Object objBean = (Object) value;
		    	     setObjectValueModel(ENTITES, objBean);
		    	     //System.out.println(" ------------ ENTITES  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("id_entite")){
		    	     Object objBean = (Object) value;
		    	     setObjectValueModel(ID_ENTITE, objBean);
		    	     //System.out.println(" ------------ ID_ENTITE  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("NAME_MODEL_BEAN")){
		    	     Object objBean = (Object) value;
		    	     setObjectValueModel(MODEL_BEAN, objBean);
		    	     //System.out.println(" ------------ BEAN  :"+objBean.getClass().getSimpleName()+"            -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("ROOT")){
		        	 String PATH_SERVLET = (String) value;
		        	 setObjectValueModel(ROOT, PATH_SERVLET);
		        	System.out.println(" ------------ ROOT  : "+PATH_SERVLET+   "    -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("LIST_DATA")){
		         setObjectValueModel(NAME_LIST_G, (String) value);
		        	// System.out.println(" ------------ List  :   "+(String) value+   "    -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("NAME_GRID")){
		        	 setObjectValueModel(NAME_GRID_G, (String) value);
		        	 //System.out.println(" ------------ Grid  :   "+(String) value+   "    -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("FORM_VIEW")){
		        	 setObjectValueModel("FORM_VIEW", (String) value);
		        	// System.out.println(" ------------ FORM_VIEW  :   "+(String) value+   "    -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("FILTER_VIEW")){
		        	 setObjectValueModel("FILTER_VIEW", (String) value);
		        	 System.out.println(" ------------ FILTER_VIEW  :   "+(String) value+   "    -------------------------");
		         }
		         
		         if(nameAttribute!=null  &&  nameAttribute.equals("LIST_VIEW")){
		        	 setObjectValueModel("LIST_VIEW", (String) value);
		        	// System.out.println(" ------------ LIST_VIEW  :   "+(String) value+   "    -------------------------");
		         }
		         
		         
		      }
		  }
		  
		  } catch (Exception e) {
				// TODO: handle exception
			}
		  
	} 
	  
	  
    
    
 
    
    
    public   void setBeanTrace(Object ojexct) throws Exception{
    	ProcessWebUtil pWebUtil= new ProcessWebUtil();
		 try {
			 for (Field fiel1 : ojexct .getClass().getDeclaredFields()) {
				   fiel1.setAccessible(true);  
				   String fNameGen=fiel1.getName();
				   pWebUtil.doCharger_Usr_and_Date(fNameGen, ojexct, fiel1);
				   pWebUtil.doCharger_Etablissement_Societe_version1(fNameGen, ojexct, fiel1);
				   pWebUtil.doCharger_Etablissement_Societe_version2(fNameGen, ojexct, fiel1);
				   pWebUtil.doCharger_Societe_id(fNameGen, ojexct, fiel1);
				}
		      } catch (Exception e) {
		    	  throw e;
			}
  	 } 
    
    
    public   void setBeanModeUsrDate(Object ojexct) throws Exception{
    	ProcessWebUtil pWebUtil= new ProcessWebUtil();
		 try {
			 for (Field fiel1 : ojexct .getClass().getDeclaredFields()) {
				   fiel1.setAccessible(true);  
				   String fNameGen=fiel1.getName();
				   pWebUtil.doCharger_Usr_and_Date(fNameGen, ojexct, fiel1);
				}
		      } catch (Exception e) {
		    	  throw e;
			}
  	 } 
    
   
    

     public    String setSocieteEtabFetch(Object ojexct,String alias, boolean inclure_central ) throws Exception{
    	
    	 ProcessWebUtil pWebUtil= new ProcessWebUtil();
    	 String resultat="";
		 try {
			   String resl=pWebUtil.doCharger_Etablissement_Societe_Fetch(alias,inclure_central);
			   resultat= resultat +"  "+resl;
			 
		      } catch (Exception e) {
		    	  throw e;
			}
		      return  resultat;
      
  	 } 
     
     public    String setSocieteEtabCentralFetch(Object ojexct , String alias , boolean inclure_Etabcentral ) throws Exception{
     	
    	 ProcessWebUtil pWebUtil= new ProcessWebUtil();
    	 String resultat="";
		 try {
			   String resl=pWebUtil.doCharger_Societe_etabCentral_Fetch(alias,inclure_Etabcentral);
			   resultat= resultat +"  "+resl;
			 
		      } catch (Exception e) {
		    	  throw e;
			}
		      return  resultat;
      
  	 } 
    
  public    String setSocieteEtabForArticle(String alias, boolean inclure_central ) throws Exception{
	
	 ProcessWebUtil pWebUtil= new ProcessWebUtil();
	 String resultat="";
	 try {
		       String resl =pWebUtil.doCharger_Etablissement_Societe_Article(alias,inclure_central);
			   resultat   = resultat +"  "+resl;
	      } catch (Exception e) {
	    	  throw e;
		}
	      return  resultat;
	 } 
  
  
     public    String setModeOperation( String alias, String mode ) throws Exception{
		 String resultat="";
		 try {
			       String Sresultat= "  AND   "+alias+".fct_id="+mode+"   "  ;
				   resultat   = resultat +"  "+Sresultat;
		      } catch (Exception e) {
		    	  throw e;
			}
		      return  resultat;
		 } 


     public    String setModeOperationConfig( String alias  ) throws Exception{
		 String resultat="";
		 try {
				BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				 String Sresultat="";
				if(bs.getFct_id().equals(GenericActionBean.Fn_Confirmer)){
					   Sresultat+= "  AND   "+alias+".fct_id  in ('"+GenericActionBean.Fn_Creer+"', "  +
					                   "                              '"+GenericActionBean.Fn_Nouveau+"', " + 
					                   "                              '"+GenericActionBean.Fn_Modifier+"', " +
					                   "                              '"+GenericActionBean.Fn_Servir+"'    )  ";
					 
				 }
				
				if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier)){
					   Sresultat+= "  AND   "+alias+".fct_id  in ('"+GenericActionBean.Fn_Creer+"', "  +
					                   "                              '"+GenericActionBean.Fn_Nouveau+"', " + 
					                   "                              '"+GenericActionBean.Fn_Modifier+"', " +
					                   "                              '"+GenericActionBean.Fn_Servir+"'    )  ";
					 
				 }
				if(bs.getFct_id().equals(GenericActionBean.Fn_Annuler)){
					   Sresultat+= "  AND   "+alias+".fct_id  in ('"+GenericActionBean.Fn_Creer+"', "  +
					                   "                              '"+GenericActionBean.Fn_Nouveau+"', " + 
					                   "                              '"+GenericActionBean.Fn_Modifier+"', " +
					                   "                              '"+GenericActionBean.Fn_Servir+"'    ) ";
					 
				 }
			      
				   resultat   = resultat+"  "+Sresultat;
		      } catch (Exception e) {
		    	  throw e;
			}
		      return  resultat;
		 } 



   public    void setPk_Soc_Etab(Object ojexct,String societe,String etablissment) throws Exception{
    	
    	BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		 try {
			 GenericWeb.setValueOject_with_name_field(ojexct, societe, bs.getSoc_id());
			 GenericWeb.setValueOject_with_name_field(ojexct, etablissment, bs.getEtab_id());
		 } catch (Exception e) {
			 throw e ;
			}
      
  	 } 
   
   
   public    void setSociete_id(Object ojexct,String societe) throws Exception{
   	
   	BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		 try {
			 GenericWeb.setValueOject_with_name_field(ojexct, societe, bs.getSoc_id());
		 } catch (Exception e) {
			 throw e ;
			}
     
 	 } 
    
 public    void setBeanSession_TraceValueInto_Cur_Beanv_1(Object ojexct) throws Exception{
    	
    	BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		
		 try {
			 
			 for (Field fiel1 : ojexct .getClass().getDeclaredFields()) {
				   fiel1.setAccessible(true);  
				   String fNameGen=fiel1.getName();
				   if(fNameGen!=null && fNameGen.equals("usr_cre") ){
					   fiel1.set(ojexct, bs.getUsr_id()!=null?bs.getUsr_id():"");
				   }
				   if(fNameGen!=null && fNameGen.equals("date_cre") ){
					   fiel1.set(ojexct, BDateTime.getDatesql());
				   }
				   if(fNameGen!=null && fNameGen.equals("time_cre") ){
					   fiel1.set(ojexct, BDateTime.getTime());
				   }
				   
				   
				   
				   
				}
			 
        
      
		 } catch (Exception e) {
			}
      
  	 } 
    
 public   void setUpdateValueFieldTraceOject(Object objUpdate) throws Exception{
    
	    Object  objInsert =getObjectValueModel(ORIGINAL_FORM_BEAN);
	    if(objInsert==null)objInsert =getObjectValueModel(FORM_BEAN);
	    BeanSession bs    =(BeanSession)getObjectValueModel(BEAN_SESSION);
		try {
			boolean testeusr_cre =false;
			boolean testedate_cre =false;
			boolean testetime_cre =false;
			ProcessWebUtil  pUtil= new ProcessWebUtil();
			 
			 for (Field fiel1 : objInsert .getClass().getDeclaredFields()) {
				   fiel1.setAccessible(true);  
				   String fname=fiel1.getName();
				   if(fname!=null && fname.equals("usr_cre") ){
					    testeusr_cre =true;
					    Object valueCreInit= fiel1.get(objInsert);
					    Field fieldUsrcre = objUpdate.getClass().getDeclaredField("usr_cre");
						fieldUsrcre.setAccessible(true);
						fieldUsrcre.set(objUpdate, valueCreInit!=null?valueCreInit:""); 
				   }
				   if(fname!=null && fname.equals("date_cre") ){
					   testedate_cre =true;
					   Object valueDateInit= fiel1.get(objInsert);
					   Field fieldateCre = objUpdate.getClass().getDeclaredField("date_cre");
					   fieldateCre.setAccessible(true);
					   fieldateCre.set(objUpdate, valueDateInit!=null?valueDateInit:BDateTime.getDatesql());
					   
				   }
				   if(fname!=null && fname.equals("time_cre") ){
					   testetime_cre =true;
					   Object valuetime_cre= fiel1.get(objInsert);
					   Field fieltime_cre = objUpdate.getClass().getDeclaredField("time_cre");
					   fieltime_cre.setAccessible(true);
					   fieltime_cre.set(objUpdate, valuetime_cre!=null?valuetime_cre:BDateTime.getTime()); 
				   }
				   
				  
				   
				   if(fname!=null && fname.equals("etabBean") ){
					      Field fieldo=objInsert.getClass().getDeclaredField("etabBean");
						  fieldo.setAccessible(true);
						  Object obj=fieldo.get(objInsert);
						  
						  Field fieldo2=obj.getClass().getDeclaredField("etab_id");
						  fieldo2.setAccessible(true);
						  Object obj_final=fieldo2.get(obj);
						  
						  Field fiel_up=objUpdate.getClass().getDeclaredField("etabBean");
						  fiel_up.setAccessible(true);
						  Object objTwoo=fiel_up.get(objUpdate);
						  
						  Field fieldoTwoo=objTwoo.getClass().getDeclaredField("etab_id");
						  fieldoTwoo.setAccessible(true);
						  fieldoTwoo.set(objTwoo, obj_final);
						  
				   }
				   
				   if(fname!=null && fname.equals("socBean") ){
					      Field fieldo=objInsert.getClass().getDeclaredField("socBean");
						  fieldo.setAccessible(true);
						  Object obj=fieldo.get(objInsert);
						  
						  Field fieldo2=obj.getClass().getDeclaredField("soc_id");
						  fieldo2.setAccessible(true);
						  Object obj_final=fieldo2.get(obj);
						  
						  Field fiel_up=objUpdate.getClass().getDeclaredField("socBean");
						  fiel_up.setAccessible(true);
						  Object objTwoo=fiel_up.get(objUpdate);
						  
						  Field fieldoTwoo=objTwoo.getClass().getDeclaredField("soc_id");
						  fieldoTwoo.setAccessible(true);
						  fieldoTwoo.set(objTwoo, obj_final); 
				   }
				   
				   if(fname!=null && fname.equals("modeBean") ){
					    
					   
					      Field fieldo=objUpdate.getClass().getDeclaredField("modeBean");
						  fieldo.setAccessible(true);
						  Object obj=fieldo.get(objUpdate);
						  
						  Field fieldo2=obj.getClass().getDeclaredField("fct_id");
						  fieldo2.setAccessible(true);
						  fieldo2.set(obj, new BigDecimal (bs.getFct_id()));
						  
						  Field fieldoLibelle=obj.getClass().getDeclaredField("fct_libelle");
						  fieldoLibelle.setAccessible(true);
						  fieldoLibelle.set(obj,  bs.getFct_libelle());
						  
				   }
				   
				   if(fname!=null && fname.equals("mode_op") ){
					   if(bs.getFct_id()==null) continue;
					    Field fieldusrMod = objUpdate.getClass().getDeclaredField("mode_op");
						fieldusrMod.setAccessible(true);
						BigDecimal FC= new BigDecimal(bs.getFct_id());
						fieldusrMod.set(objUpdate, FC);
				   }
				   
				}	
			 
				 for (Field fiel1 : objUpdate .getClass().getDeclaredFields()) {
					   fiel1.setAccessible(true);  
					   String fname=fiel1.getName();
					   pUtil.doCharger_Etablissement_Societe_version2(fname, objUpdate, fiel1);
				 }
			 
			    
		        if(testeusr_cre){
				Field fieldusrMod = objUpdate.getClass().getDeclaredField("usr_mod");
				fieldusrMod.setAccessible(true);
				fieldusrMod.set(objUpdate, bs.getUsr_id()!=null?bs.getUsr_id():"");
		        }
		        if(testedate_cre){
		        Field fieldateMod = objUpdate.getClass().getDeclaredField("date_mod");
		        fieldateMod.setAccessible(true);
		        fieldateMod.set(objUpdate, BDateTime.getDatesql());
		        }
		        if(testetime_cre){
		        Field fieltime_mod = objUpdate.getClass().getDeclaredField("time_mod");
		        fieltime_mod.setAccessible(true);
		        fieltime_mod.set(objUpdate, BDateTime.getTime());
		        }
		        
		        
		         
		        
		    	 
		} catch (Exception e) {
			throw e;
		}
  	 } 
    
    
    
    
    
  
	  
	
 protected void  doInitGenericActionAuthentification( HttpServletRequest req , HttpServletResponse res ,Object templateEncours ) throws IOException,ServletException {
	  try {
		  
		 GenericActionBean WXc=new GenericActionBean();
		 this.getSessionAuth(req,res);
	     this.loadPathActionView(templateEncours,req);
	     String HiddenAction= getHidenString(req);
	     this.getBeanAction(templateEncours, HiddenAction, WXc);
		  
		  
	     } catch (Exception e) {
	    	 displayException(  e);
	     }
  }
    
	 
   
   private String getHidenString( HttpServletRequest req  ){
	   return req.getParameter("HiddenAction")!=null?req.getParameter("HiddenAction"):"";
   }
   
    
	public  ModelAndView   doInitGenericAction( HttpServletRequest req  , HttpServletResponse res  ,Object tempEncours ) throws IOException,ServletException {
		 
		
		 GenericActionBean genericactionbean=new GenericActionBean();
		 TemplateGeneric   temGeneric= new TemplateGeneric();
		 try {
			 tempEncours=tempEncours.getClass().newInstance();
			 String            HiddenAction     = getHidenString(req);
			 System.out.println(" ------------ Hidden_Action  : "+ HiddenAction+   "    -------------------------");
		     this.getSession(req,res);
		     this.loadPathActionView(tempEncours,req);
		     this.getBeanAction(tempEncours,HiddenAction , genericactionbean);
		     this.doIniServlet(tempEncours,HiddenAction);
		     
		     } catch (Exception e) {
		    	 displayException(e);
		     }
		     return   temGeneric.doForwardActionGeneric();
	   }
	 
		 public void getBeanAction(Object templateEncours, String  HiddenAction , GenericActionBean  genBean) throws Exception{
			 
		     if(  HiddenAction==null  || HiddenAction.equals("") )   
		    	 throwNewException("out");
		     
			 boolean  resulHid  =searchActionBooleanInTemplate(templateEncours,genBean,HiddenAction)? true:searchBooleanActionGeneric(genBean,HiddenAction );
			 //System.out.println(" ------------ Boolean Action  : "+String.valueOf(resulHid)+   "-------------------------");
		     if(!resulHid)  pbHidAction(templateEncours);
		 }

	
	public void getSession(HttpServletRequest req  , HttpServletResponse res  ) throws Exception{
		
		   
           
             
		    if (req != null && requestThread.get() != null) {
	            /*throw new IllegalStateException("HttpServletRequestHolder.bind() called for a "
	                    + "thread that already has a request associated with it. It's likely that the request "
	                    + "was not correctly removed from the thread before it is put back into the thread pool.");*/
		    	 requestThread.remove();
		    	 sessionThread.remove();
		    	 System.out.println(" ------------ requestThread     existe    -------------------------");
		    	
	        }
	        if (res != null && responseThread.get() != null) {
	            /*throw new IllegalStateException("HttpServletRequestHolder.bind() called for a "
	                    + "thread that already has a request associated with it. It's likely that the request "
	                    + "was not correctly removed from the thread before it is put back into the thread pool.");*/
	        	 responseThread.remove();
	        	 System.out.println(" ------------ responseThread     existe    -------------------------");
	        }
	    
	         requestThread.set(req);
	    	 sessionThread.set(req.getSession(false));
	    	 responseThread.set(res);
		  
		 System.out.println(" ------------ sessionThreadPuis     : "+ getSession().getId()+   "    -------------------------");
		 //System.out.println(" ------------ response_Id      : "+ System.identityHashCode(response)+   "    -------------------------");
		// System.out.println(" ------------ session_HAcCode  : "+ System.identityHashCode(session)+   "    -------------------------");
		 if(getModel()==null)    throwNewException("out");
	}

	 
		
	   
	 
	
	
	
	 
	public void getSessionAuth(HttpServletRequest req  , HttpServletResponse res  ) throws Exception{
		
		 if (req != null && requestThread.get() != null) {
	            /*throw new IllegalStateException("HttpServletRequestHolder.bind() called for a "
	                    + "thread that already has a request associated with it. It's likely that the request "
	                    + "was not correctly removed from the thread before it is put back into the thread pool.");*/
		    	 requestThread.remove();
		    	 sessionThread.remove();
		    	 System.out.println(" ------------ requestThread     existe    -------------------------");
		    	
	        }
	        if (res != null && responseThread.get() != null) {
	            /*throw new IllegalStateException("HttpServletRequestHolder.bind() called for a "
	                    + "thread that already has a request associated with it. It's likely that the request "
	                    + "was not correctly removed from the thread before it is put back into the thread pool.");*/
	        	 responseThread.remove();
	        	 System.out.println(" ------------ responseThread     existe    -------------------------");
	        }
	        
		 requestThread.set(req);
	     responseThread.set(res);
	     sessionThread.set(req.getSession(false));
	      
	     
	     
		// System.out.println(" ------------ Session       : "+session.getId()+   "    -------------------------");
		 //if(session==null) throwNewException("out");
	}
	
	  
	
	  protected void  doInitParameterAajxComposant( HttpServletRequest req , HttpServletResponse res ,Object templateEnco) throws IOException,ServletException {
		  try {
			     GenericActionBean WXcWCCCC=new GenericActionBean();
			     this.getSession(req,res);
			     String  HiddenAction   = getHidenString(req);
				 this.getBeanAction(templateEnco,HiddenAction,  WXcWCCCC);
		     } catch (Exception e) {
		    	 displayException((Exception) e);
		     }
	   }
	 



	public   void  doIniServlet   ( Object tempSubModu ,  String  HiddenAction  ) throws NoSuchFieldException,ServletException {
		 
	 	   String indexlismod_id="";
		   String IndexSoumod="";
		 
		   try {
			   setObjectValueModel(CONTEXT_PATH, getRequest().getContextPath()+ ActionAuthentificationManager.PATH_SLACH);
			   removeObjectModel(MESSAGERROR);
			   removeObjectModel(CUST_MESSAGERROR);
	    if( HiddenAction.equals("i$_ACT_INIT_SERVLET")){
	    	removeObjectModel(FORM_BEAN);
	    	setObjectValueModel(HIDDEN_ACTION, HiddenAction);
	    	setObjectValueModel("totalList", null);
	    	BeanSession  bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
	    	String  data_action= getRequest().getParameter("data_action")==null?"":getRequest().getParameter("data_action");
	    	bs.setData_action(data_action);
	    	String []tabCoposer = data_action.split(",");
	    	
	    	//var data =""+fcId+"�"+fcLib+"�"+view_smfct_action+"�"+smodId+"�"+smodlib+"�"+indexLisSouMod+"�"+ixlismodul;
	    	
	    
	    	bs.setFct_id(tabCoposer[0]);//(getRequest().getParameter("fct_id")!=null?getRequest().getParameter("fct_id"):"");
	    	
	    	bs.setFct_libelle(tabCoposer[1]);//(getRequest().getParameter("fct_libelle")!=null?getRequest().getParameter("fct_libelle"):"");
	    	bs.setView_smfct_action(tabCoposer[2]);
	    	bs.setSousmod_id(tabCoposer[3]);//(getRequest().getParameter("sousmod_id")!=null?getRequest().getParameter("sousmod_id"):"");
	    	bs.setSousmod_libelle(tabCoposer[4]);//(getRequest().getParameter("sousmod_libelle")!=null?getRequest().getParameter("sousmod_libelle"):""); 
	    	bs.setSousmod_libelle_title(tabCoposer[4]);
	    
	    	IndexSoumod=tabCoposer[5] ;//getRequest().getParameter("IndexSoumod");
	    	indexlismod_id= tabCoposer[6] ;//getRequest().getParameter("indexlismod_id");
	    	List listModule=(List) getObjectValueModel("listModule");
			ModuleBean modulebean=(ModuleBean) listModule.get(Integer.parseInt(indexlismod_id));
			List listSousModule = new ArrayList(modulebean.getListsmodule());
			SousModuleBean  sousmodulebean=(SousModuleBean) listSousModule.get(Integer.parseInt(IndexSoumod));
			
			List listFonc=sousmodulebean.getListf();
			
			setObjectValueModel("listDesFonction", listFonc );
			 
			setObjectValueModel("sousModulebean", sousmodulebean);
			 
			
			bs.setMod_id(String.valueOf(modulebean.getMod_id()));
			bs.setMod_libelle(modulebean.getMod_libelle());
			bs.setIndexModule(indexlismod_id); 
			bs.setIndexSousModule(IndexSoumod);
			setObjectValueModel(BEAN_SESSION, bs);
	
			setObjectValueModel(DATA_LIST_AJAX,new ArrayList());
			 
		
			MenuActionBean disMenuX = new  MenuActionBean();
			disMenuX.setToolbarBttn(BLOCK); 
			setObjectValueModel(MENU_ACTION_DISPLAY,disMenuX);
			
			 
			MenuActionBean temChemin= new MenuActionBean(); 
			temChemin.setTitle(sousmodulebean.getSousmod_libelle());
			temChemin.setUrl(tabCoposer[2]);
			String var=(String) getObjectValueModel(BASE_URL_AJAX_PROJECT);
			temChemin.setUrlAjax(var+temChemin.getUrl());
			setObjectValueModel(TEMP_CHEMIN_TITLE,temChemin);
		  	
			 this.doloadingLibelleSousModule();
			 //this.doLoadingConfigDeveloppement();
		  	
	    	}
	  
		      } catch (Exception e) {
		    	 
		    	try {
		    		 displayException(e); 
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				}
		     
				 
	    }
	 
	}
	  
	  
	 
	   

		public static    void doDefineSomeAction(Object beanTempNotGeneric,String nameact) throws Exception{
	        String HiddenAction= nameact;
	        boolean  resulHid=searchActionBooleanInTemplate(beanTempNotGeneric, new GenericActionBean(),HiddenAction)? true:searchBooleanActionGeneric(new GenericActionBean(),HiddenAction);
		     if(!resulHid)  throwNewException("out");
	  	  } 
	    
	    public  static void pbHidAction(Object obj){
	    	
	    	 try {
				doDefineSomeAction(obj,"i$_ACT_SET_TIME_OUT");
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    }

	    public   static   void doGetExceptionAjax(Exception e){
			
			    MessageBean error       = new MessageBean();
			   
			  try {
				    Throwable t = e.getCause();               
		            SQLException ex = (SQLException) t.getCause();
				    String  codeerr="";
				    if(ex.getSQLState()==null){
					       codeerr="out"; 
				       }else{
				    	   codeerr=ex.getSQLState().replace('"', ';'); 
				       }
				     
					 if(codeerr.equals("out")){
						 Object obj= getObjectValueModel(NAME_TEMPLATE);
						 pbHidAction(obj);
					 }else{
						 GlibelleBean beanSearch = new  GlibelleBean();
						 BeanSession  bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
						 beanSearch.getIdLiblleBean().setLib_id(codeerr);
						 beanSearch.getIdLiblleBean().setLang_id(bs.getLang_id());
						 List hhhh=ActionGlibelleManager.serviceGlibelle.getListDataServer(beanSearch);
							 if(hhhh!= null &&  hhhh.size()>0){
								 error.setMessage(((GlibelleBean)hhhh.get(0)).getLib_libelle());
							 }else{
								 Vector <String> vectError=new Vector<String>();
								 while(ex != null){
				                 while(t != null) {
				                     t = t.getCause();
				                 }
				                 System.out.println("SQLException="+ex.getLocalizedMessage());
				                 vectError.add(ex.getLocalizedMessage());
				                 ex = ex.getNextException();
				                }
								 if(vectError.size()>0){
				                	 error.setMessage(codeerr!=null?vectError.get(vectError.size()-1):" Veuillez Contacter Votre Administrateur Syst�me ! "); 
				                 } 
								
							 }
						 String ee=	 error.getMessage()!=null?error.getMessage():"";
						 if(!ee.equals("")&&  ee.indexOf("\n")>-1){
						 String[] RRRR=ee.split("\n");
						 setObjectValueModel(MESSAGERROR,RRRR[0] +"<br>"+ RRRR[1] );
						 }else{
							 setObjectValueModel(MESSAGERROR,ee); 
						 }
					 }
						 
			  } catch (Exception eq) {
				  String  codeerr="";
				    if(e.getMessage()==null){
					       codeerr="out"; 
				       }else{
				    	   codeerr=e.getMessage().replace('"', ';'); 
				       }
				    	   
				     
					 
					 
					 if(codeerr.equals("out")){
						 Object tempSubModule= getObjectValueModel(NAME_TEMPLATE);
						pbHidAction(tempSubModule);
					 }else{
						 GlibelleBean beanSearch = new  GlibelleBean();
						 BeanSession  bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
						 beanSearch.getIdLiblleBean().setLib_id(codeerr);
						 beanSearch.getIdLiblleBean().setLang_id(bs.getLang_id());
						 List hhhh=ActionGlibelleManager.serviceGlibelle.getListDataServer(beanSearch);
							 if(hhhh!= null &&  hhhh.size()>0){
								 error.setMessage(((GlibelleBean)hhhh.get(0)).getLib_libelle());
							 }else{
								 error.setMessage(codeerr!=null?codeerr:" Veuillez Contacter Votre Administrateur Syst�me ! ");
							 }
						 setObjectValueModel(MESSAGERROR, error.getMessage()!=null?error.getMessage():"Veuillez contacter votre administrateur syst�me !");
					 }
				} 
					 
					 
		}
	    public static String getStackTrace(final Throwable throwable) {
	        final StringWriter sw = new StringWriter();
	        final PrintWriter pw = new PrintWriter(sw, true);
	        throwable.printStackTrace(pw);
	        return sw.getBuffer().toString();
	   }
	    
	    public static Throwable getRootCause(final Throwable throwable) {
	        final List<Throwable> list = getThrowableList(throwable);
	        return list.size() < 2 ? null : (Throwable)list.get(list.size() - 1);
	    }

	    public static List<Throwable> getThrowableList(Throwable throwable) {
	        final List<Throwable> list = new ArrayList<Throwable>();
	        while (throwable != null && list.contains(throwable) == false) {
	            list.add(throwable);
	            throwable = ExceptionUtils.getCause(throwable);
	        }
	        return list;
	    }
    public   static   void displayException(Exception e , String content_type  )  { 
    	
    	e.printStackTrace();
    	//getResponse().setStatus(sc);
		getResponse().setContentType(content_type);
		try {
			getResponse().getWriter().print(e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
   }
    
	public   static   JsonObject   displayException(Exception e){
		
		 MessageBean error       = new MessageBean();
		 String  codeerr="";
		 JsonObject errorObject = new JsonObject();
		  try {
			     BeanSession  bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			     String name_entite=bs.getSousmod_libelle_title();
				 String name_fct=bs.getFct_libelle();
			     Throwable  ex=getRootCause(e.getCause());
			     if(ex!=null &&  ex  instanceof  PSQLException) {
			    	 PSQLException  exff=  (PSQLException) ex;
			    	 errorObject.addProperty("SQLState", exff.getSQLState());
			    	 codeerr=exff.getMessage();
			     }else {
			    	 codeerr=ex==null?e.getMessage():ex.toString();
			     }
			    
				 if(codeerr.equals("out")){
				    Object tempSubModule= getObjectValueModel(NAME_TEMPLATE);
					pbHidAction(tempSubModule);
				 
				 }else{
					 GlibelleBean beanSearch = new  GlibelleBean();
					
					 beanSearch.getIdLiblleBean().setLib_id(codeerr);
					 beanSearch.getIdLiblleBean().setLang_id(bs.getLang_id());
					 
					 if(codeerr.equals("invo_fct")){
						 String mSg= name_entite+"  "+name_fct +" avec succ�s";
						 error.setMessage(mSg);
					 }else {
					    List list_message=ActionGlibelleManager.serviceGlibelle.getListDataServer(beanSearch);
					       if(list_message!= null &&  list_message.size()>0){
							 error.setMessage(((GlibelleBean)list_message.get(0)).getLib_libelle());
					       }else{
						     getStackTrace(e);
						     e.printStackTrace();
					         if( codeerr  !=null  &&  codeerr.length()>0) error.setMessage(codeerr); else error.setMessage("Veuillez Contacter Votre Administrateur Syst�me ! "); 
			               }
							
				     }
						 
						 
					 String ee=	 error.getMessage()!=null?error.getMessage():"";
					 if(!ee.equals("")&&  ee.indexOf("\n")>-1){
					 String[] RRRR=ee.split("\n");
					 setObjectValueModel(MESSAGERROR,RRRR[0] +"<br>"+ RRRR[1] );
					 }else{
					  setObjectValueModel(MESSAGERROR,ee); 
					 }
				 }
					 
		  } catch (Exception eq) {
			     codeerr="";
			    if(e.getMessage()==null){
				       codeerr="out"; 
			       }else{
			    	   codeerr=e.getMessage().replace('"', ';'); 
			       }
			    	   
			     
				 
				 
				 if(codeerr.equals("out")){
					 Object tempSubModule= getObjectValueModel(NAME_TEMPLATE);
					pbHidAction(tempSubModule);
				 }else{
					 GlibelleBean beanSearch = new  GlibelleBean();
					 BeanSession  bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
					 beanSearch.getIdLiblleBean().setLib_id(codeerr);
					 beanSearch.getIdLiblleBean().setLang_id(bs.getLang_id());
					 List hhhh=ActionGlibelleManager.serviceGlibelle.getListDataServer(beanSearch);
						 if(hhhh!= null &&  hhhh.size()>0){
							 error.setMessage(((GlibelleBean)hhhh.get(0)).getLib_libelle());
						 }else{
						 
						     String name_entite=bs.getSousmod_libelle_title();
							 String name_fct=bs.getFct_libelle();
							 
							   codeerr= " �chec de l'op�ration "+name_fct +"  "+name_entite +"  "+codeerr;
							  
							 error.setMessage(codeerr!=null?codeerr:" Veuillez Contacter Votre Administrateur Syst�me ! ");
						 }
					 setObjectValueModel(MESSAGERROR, error.getMessage()!=null?error.getMessage():"Veuillez contacter votre administrateur syst�me !");
				 }
			}  
		  return errorObject;
	}
	
	
	 public  void TransfertError (Exception e){
		 String Message =(String) getObjectValueModel(MESSAGERROR);
		 setObjectValueModel(MESSAGERROR , "" );
		 setObjectValueModel(CUST_MESSAGERROR , Message );
	 }
	
 
	
	
	
	public static     void createFileSousModule(Object tempSubModule){
		
		try {
			
			 Field field = tempSubModule.getClass().getDeclaredField("ROOT");
		     field.setAccessible(true);
		     Object value= field.get(tempSubModule);
			 int viva=((String) value).indexOf("root.action");
			 String lod=((String) value).substring(0, viva);
			  System.out.println(lod);
			  String biba=lod.replace('/', '\\');
			  System.out.println(biba);
		      File file = new File("d:\\Zizou\\eXpertSoft\\WebRoot"+biba+"newfile.jsp");
		      if (file.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists.");
		      }
	 
	    	} catch (IOException ss) {
		      ss.printStackTrace();
		} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
				 
				 
	}
	
	public static  void doLoadingLibelleOtherSModule(String  code_other_sModule) throws Exception{
		   
	    try {
		    
		     BeanSession  bs=(BeanSession)    getObjectValueModel(BEAN_SESSION);
		    
		     Vector <String> vect_libelle_sous_module= ( Vector ) getObjectValueModel(VECT_LIBELLE_SOUS_MODULE);
			     if(vect_libelle_sous_module==null){
			    	 vect_libelle_sous_module = new Vector<String>();
			     }
		     
		        if( bs!=null  &&  !vect_libelle_sous_module.contains(bs.getSousmod_id())){
		          vect_libelle_sous_module.add(bs.getSousmod_id());
		        }
		        
		        if( code_other_sModule!=null  &&  !vect_libelle_sous_module.contains(code_other_sModule)){
			          vect_libelle_sous_module.add(code_other_sModule);
			       }
		        
		        setObjectValueModel(VECT_LIBELLE_SOUS_MODULE, vect_libelle_sous_module);	
		     
		    	HashMap  mapOrigin=(HashMap) getObjectValueModel(MAP_LIBELLE_ENTITE_EN_COURS);
		          
		     
			     HashMap   map_Lists_des_sousmodule_id=    (HashMap) getObjectValueModel("map_Lists_des_sousmodule_id");
			     
			     for (int w = 0; w < vect_libelle_sous_module.size(); w++) {
			    	 
					     String sou_mod=(String) vect_libelle_sous_module.get(w);
					
						 List listlibelle=  (List) map_Lists_des_sousmodule_id.get(sou_mod); 
					     if(listlibelle!=null)
					     for (int i = 0; i < listlibelle.size(); i++) {
								EntiteAdminBean   bsdx = (EntiteAdminBean) listlibelle.get(i);
							    int df=bsdx.getPk_entite_admin().getEnt_id().indexOf("/");
							    if( mapOrigin==null || mapOrigin.get(bsdx.getPk_entite_admin().getEnt_id().substring(df+1))==null)
								setObjectValueModel(bsdx.getPk_entite_admin().getEnt_id().substring(df+1),bsdx.getEnt_libelle());
								 
						 }
			 
			     
			     
			     
			     
			     
		     }
		} catch (Exception e) {
			throw e;
		} 
				 
				 
	}
	
	
	@SuppressWarnings("unchecked")
	public void doloadingLibelleSousModule() throws Exception{
	   
	    try {
		    
		     BeanSession  bs=(BeanSession)    getObjectValueModel(BEAN_SESSION);
		     Vector <String> vect_libelle_sous_module= ( Vector)getObjectValueModel(VECT_LIBELLE_SOUS_MODULE);
			 if(vect_libelle_sous_module==null){
			    	 vect_libelle_sous_module = new Vector<String>();
			 }
		     
		        if( bs!=null  &&  !vect_libelle_sous_module.contains(bs.getSousmod_id())){
		          vect_libelle_sous_module.add(bs.getSousmod_id());
		        }
		        
		        setObjectValueModel(VECT_LIBELLE_SOUS_MODULE, vect_libelle_sous_module);	
		        HashMap mapO= new HashMap();
		     
			     HashMap   map_Lists_des_sousmodule_id=    (HashMap) getObjectValueModel("map_Lists_des_sousmodule_id");
			     
			     for (int w = 0; w < vect_libelle_sous_module.size(); w++) {
			    	 
					     String sou_mod=(String) vect_libelle_sous_module.get(w);
					
						 List listlibelle=  (List) map_Lists_des_sousmodule_id.get(sou_mod); 
					     if(listlibelle!=null)
					     for (int i = 0; i < listlibelle.size(); i++) {
								EntiteAdminBean   bsdx = (EntiteAdminBean) listlibelle.get(i);
							    int df=bsdx.getPk_entite_admin().getEnt_id().indexOf("/");
								setObjectValueModel(bsdx.getPk_entite_admin().getEnt_id().substring(df+1),bsdx.getEnt_libelle());
								mapO.put(bsdx.getPk_entite_admin().getEnt_id().substring(df+1),bsdx.getEnt_libelle());
								setObjectValueModel(MAP_LIBELLE_ENTITE_EN_COURS,mapO);
						 }
		     }
		} catch (Exception e) {
			throw e;
		} 
				 
				 
	}
	  
	 


 

	public    void saveTrace(Object  beanSave ) throws Exception{
		
		 try {
		   serviceSpoor.doCreateNewSpoor(beanSave);
		   
		   } catch (Exception eq) {
			   throw eq;
				
	      }
		}
	
	public    void saveTraceVersion1(Object  beanSave, String[][] MapfieldBean, String []id_Entite, String table ) throws Exception{
		
		 try {
		   serviceSpoor.doCreateNewSpoorVersion1(beanSave,MapfieldBean,id_Entite,table); 
		   
		   } catch (Exception eq) {
			   throw eq;
				
	      }
		}
	
	public static    void saveTraceVersion_Master_detailles(
			List   listData, 
			String[][] MapfieldBean, 
			String []id_Entite, 
			String table ) throws Exception{
		
		 try {
			 for (int i = 0; i < listData.size(); i++) {
				 Object beanSave=listData.get(i);
				 serviceSpoor.doCreateNewSpoorVersion_Master_detailles(beanSave,MapfieldBean,id_Entite,table); 
			}
		   } catch (Exception eq) {
			   throw eq;
				
	      }
		}
	 
 


	  @Autowired
	public static void setActionSpoorManager(ActionSpoorManager actionSpoorManager) {
		GenericWeb.actionSpoorManager = actionSpoorManager;
	}



 



	
	 
}

	
 
