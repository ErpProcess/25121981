package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.util;

import javax.persistence.Column;

import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class EntiteAdminTemplate   extends   TemplateGeneric   {
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT       = "/ERP/eXpertSoft/wfsi/Administration/GestionsLinguistiques/EntiteAdmin/root.action";
	public static Object  NAME_MODEL_BEAN   = new EntiteAdminBean();
	public static Object  NAME_TEMPLATE     = new EntiteAdminTemplate();
	public static boolean is_Spoored        = true;
	public static String  entites           = "entite_admin";
	public static String  schema            = "Administration";
	public static String  id_entite         = "ent_id";
	
	 
 
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "List-EntiteAdmin";
	public static String  NAME_GRID         = "Grid-EntiteAdmin";
	public static String [][] MapfieldBean  = new String[][]{{"ent_libelle","30"},{"ent_abrv","30"},{"type_lib_id","30" },{"table_schem","30"},{"table_name", "30" },{"column_name","30" }};
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormEntiteAdmin";
	public static String FILTER_VIEW        = "FilterEntiteAdmin";
	public static String LIST_VIEW          = "ListEntiteAdmin";
	public static String PAGE_FORM_Update   = "FormUpdateEntiteAdmin";
	public static String FORM_INIT_CREE_ENTITEADMIN   = "formInitCree_EntiteAdmin";
	
	public static String FORM_UNIQUE_CREE_ENTITEADMIN   = "formCreeUniqueEntiteAdmin";
	
	 
	
	/********************************************************************Action Entite***************************************************************/
	public static boolean i$_ACT_INIT_SERVLET = false;
	public static boolean i$_ACT_LOAD_ETAB    = false;
	public static boolean i$_ACT_ADD_ROW_EDITABLE_TABLE_AJAX=false;
	public static boolean i$_ACT_LOAD_SOUSPACK=false;
	public static boolean i$_ACT_LOAD_MODULE=false;
	public static boolean i$_ACT_LOAD_SOUS_MODULE=false;
	public static boolean i$_ACT_PREPARER_LIB_ENTITE=false;
	public static boolean i$_ACT_UPDATE_EDITABLE_TABLE_AJAX=false;
	public static boolean i$_ACT_LOAD_TAB_SCHEM=false;
	public static boolean i$_ACT_RESET_FORM=false;
	public static boolean i$_ACT_GET_ID_COLUNMS  =false;
	public static boolean i$_Act_FETCH_LIST_FOR_UPDATE=false;
	
	public static boolean i$_ACT_SELECT_ROW = false;
	public static boolean i$_ACT_ADD_WIDTH_LIST = false;
	
	
	
	
	
	
	
	
	
	public static ModelAndView getViewInit_cree(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
	
		twebBean.setAct_doValid("i$_Act_Preparer_Lib_Entite");
		twebBean.setFctdoValid("loadDataFromServer()");
		
		twebBean.setCommit("false");
		twebBean.setFctdoCommit("doCommitAction()");
		
		
		twebBean.setAct_doReset("i$_ACT_RESET_FORM");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setIconAction("Search.png");
		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		twebBean.setBtNavigation(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	
	
}
