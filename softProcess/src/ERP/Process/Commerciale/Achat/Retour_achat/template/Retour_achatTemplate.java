package ERP.Process.Commerciale.Achat.Retour_achat.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Retour_achat.model.Retour_achatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Retour_achatTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = -5268575488539170836L;
public  Retour_achatTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Achat/Retour_achat/root.action";
public static final   String  ID_SOUS_MODULE   = "119";
public static final   String  ID_SOUS_MODULE_STOCK   = "164";

 
public static Object  NAME_MODEL_BEAN  = new Retour_achatBean();
public static Object  NAME_TEMPLATE    = new Retour_achatTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "retour_achat";
public static String  schema           = "achat";
public static String  id_entite        = "retour_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_VIEW         = "ListRetour_achat";
public static String  LIST_DATA         = "Retour_achat-List";
public static String  NAME_GRID         = "Retour_achat-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "retour_date", "20" },
	                                                     { "retour_id", "20" },
	                                                     { "achat.depot.depot_libelle", "20" },
	                                                     { "achat.frsBean.frsref", "20" },
	                                                     { "achat.achat_date", "20" },
	                                                     { "achat.achat_id", "20" },
	                                                     { "modeBean.fct_libelle", "20" },
	                                                     };

 



/********************************************************************View Jsp********************************************************************/

public static String FILTER_VIEW_ACHAT        = "FilterReception_achat";
public static String LIST_VIEW_ACHAT          = "ListReception_achat";


 
public static String FORM_VIEW_EDIT     = "FormRetour_achat_Edit";
public static String FORM_VIEW          = "FormRetour_achat";
public static String FILTER_VIEW        = "FilterRetour_achat";


public static String LIST_DEPOT_STOCK          = "list_depot_stock";
public static String LIST_FOURNISSEUR_RECEP_ACHAT       = "list_fournisseur_recep_achat";
public static String LIST_EDITABLE_RETOUR_ACHAT          = "list_editable_retour_achat";
public static String LIST_EDITABLE_RETOUR_ACHAT_ORG          = "list_editable_retour_achat_org";
public static String BEAN_TOTAL                         = "beanTotal";
public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_ADD_ROW = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_DELETE_ROW = false;
public static boolean i$_ACT_ACTUALISER_TABLE= false;
public static boolean i$_ACT_LOAD_LOT_ARTICLE = false;
public static boolean i$_ACT_Cheked_unCheked = false;
public static boolean i$_ACT_PRINT_PDF = false;
public static boolean i$_ACT_EXPORT_XLS = false;
public static boolean i$_ACT_CALCUL_TOTAL = false;
public static boolean i$_ACT_RETOUR_TO_LIST_ACHAT = false;
public static boolean i$_ACT_VALIDER = false;
public static boolean i$_ACT_ANNULER = false;
public static boolean i$_ACT_VERIFIER = false;
public static boolean i$_ACT_APPLIQUER = false;
public static boolean i$_ACT_RESET_FORM = false;


public static String  LIST_DATA_ACHAT         = "recep_achat_List";
public static String  NAME_GRID_ACHAT         = "recep_achat_Grid";
public static String  FORM_BEAN_ACHAT         = "detailBean_a";

 


public static boolean i$_ACT_FETCH_AJAX_ACHAT = false;
public static ModelAndView getViewFilter_achat(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_AJAX_ACHAT");
	twebBean.setAct_doReset("i$_ACT_RESET_FILTER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
	twebBean.setIconAction("Search.png");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(FALSE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(FALSE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(FALSE);
	twebBean.setBtExcel(TRUE);
	twebBean.setBtPdf(TRUE);
	twebBean.setTypebtn(button);
	twebBean.setFctdoValid("ControlDisplayTableE()");
	twebBean.setFctdoReset("doResetAjaxData()");
	return Get_Model_ROOT(isPage, twebBean);
}

public static ModelAndView getViewAdd_retour(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_ADD");
	twebBean.setAct_doReset("i$_ACT_RESET_FORM");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST_ACHAT");
	twebBean.setLibelleAction(BTN_ADD);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(FALSE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(FALSE);
	return Get_Model_ROOT(isPage, twebBean);
}
 
public static ModelAndView getViewVerifier(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(submit);
	twebBean.setLibelleAction("Valider-Vérification");
	twebBean.setAct_doValid("i$_ACT_VERIFIER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);
}

public static ModelAndView getViewAppliquer(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();

	twebBean.setTypebtn(submit);
	twebBean.setLibelleAction("Appliquer-Stock");
	twebBean.setAct_doValid("i$_ACT_APPLIQUER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);
}




}
