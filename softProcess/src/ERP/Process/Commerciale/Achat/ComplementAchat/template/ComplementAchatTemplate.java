package ERP.Process.Commerciale.Achat.ComplementAchat.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.ComplementAchat.model.ComplementAchatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class ComplementAchatTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 4105649538324425135L;
public  ComplementAchatTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Achat/ComplementAchat/root.action";
public static Object  NAME_MODEL_BEAN  = new ComplementAchatBean();

public static String  ID_SOUS_MODULE   ="159";
public static String  ID_SOUS_MODULE_STOCK   ="165";

public static Object  NAME_TEMPLATE     = new ComplementAchatTemplate();
public static boolean is_Spoored        = true;
public static String  entites           = "complement_achat_frs";
public static String  schema            = "achat";
public static String  id_entite         = "complet_id";
public static String  BEAN_TOTAL         = "beanTotal";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "ComplementAchat-List";
public static String  NAME_GRID         = "ComplementAchat-Grid";


public static String  LIST_DATA_ACHAT   = "recep_achat_List";
public static String  NAME_GRID_ACHAT   = "recep_achat_Grid";



public static String LIST_DEPOT_STOCK                   = "list_depot_stock";
public static String LIST_FOURNISSEUR_RECEP_ACHAT       = "list_fournisseur_recep_achat";
public static String LIST_EDITABLE_COMP_ACHAT           = "list_editable_comp_achat";
public static String LIST_EDITABLE_COMP_ACHAT_ORG       = "list_editable_comp_achat_org";
public static String MAP_TARIFICATION                   = "mapTarification";
public static String LIST_ARTICLE_RECP_ACHAT            = "list_article_recp_achat";
public static String LIST_ARTICLE_RECP_ACHAT_ORIGINE    = "list_article_recp_achat_origine";
public static String LIST_ARTICLE_RECP_ACHAT_GRID       = "list_article_recp_achat_grid";
public static String LIST_LOT_ARTICLE          = "list_Lot_article";
 
public static String [][] MapfieldBean  = new String[][]{{ "complet_id", "30" },{ "achat.pk.achat_id", "30" },{ "complet_date", "30" },{ "achat.pk.achat_date", "30" }, { "achat.pk.depot.depot_libelle", "30" },{ "modeBean.fct_libelle", "30" },{ "time_cre", "30" }};




/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormComplementAchat";
public static String FORM_VIEW_EDIT     = "FormComplementAchat_Edit";

public static String FILTER_VIEW        = "FilterComplementAchat";
public static String LIST_VIEW          = "ListComplementAchat";

public static String FILTER_VIEW_ACHAT  = "FilterReception_achat";
public static String LIST_VIEW_ACHAT    = "ListReception_achat";



public static boolean i$_ACT_INIT_SERVLET     = false;
public static boolean i$_ACT_ADD_ROW 	      = false;
public static boolean i$_ACT_FETCH_AJAX_ACHAT = false;
public static boolean i$_ACT_SELECT_ROW       = false;
public static boolean i$_ACT_DELETE_ROW        = false;
public static boolean i$_ACT_ACTUALISER_TABLE  = false;
public static boolean i$_ACT_LOAD_LOT_ARTICLE  = false;
public static boolean i$_ACT_Cheked_unCheked   = false;
public static boolean i$_ACT_PRINT_PDF         = false;
public static boolean i$_ACT_EXPORT_XLS        = false;
public static boolean i$_ACT_CALCUL_TOTAL      = false;
public static boolean i$_ACT_RETOUR_TO_LIST_ACHAT = false;
public static boolean i$_ACT_TRANSFER          = false;
public static boolean i$_ACT_ANNULER           = false;
public static boolean i$_ACT_RECEPTION          = false;
public static boolean i$_ACT_CONSERV            = false;
 
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
public static ModelAndView getViewAddComplement(String isPage) {
	
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
 



}
