package ERP.Process.Commerciale.Vente.RetourVente.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class RetourVenteTemplate   extends   TemplateGeneric   {
 
	 private static final long serialVersionUID = 3245946804236635105L;

public  RetourVenteTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/RetourVente/root.action";
public static final   String ID_SOUS_MODULE      ="174";
public static final   String ID_SOUS_MODULE_RETOUR_APRES_VENTE      ="230";

public static Object  NAME_MODEL_BEAN  = new RetourVenteBean();
public static Object  NAME_TEMPLATE    = new RetourVenteTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "retour_vente";
public static String  schema           = "vente";
public static String  id_entite        = "ret_vente_id";
/********************************************************************Data Grid********************************************************************/

public static String  LIST_DATA_VENTE   = "Vente-List";


public static String  NAME_GRID_VENTE   = "Vente-Grid";


public static String  LIST_DATA         = "RetourVente-List";
public static String  NAME_GRID         = "RetourVente-Grid";
public static String LIST_DES_TVA            = "list_des_tva__retour_vente";


public static String  LIST_EDITABLE_RETOUR_VENTE_ORIGINE = "list_editable_RetourVente_Origine";
public static String  LIST_EDITABLE_RETOUR_VENTE         = "list_editable_RetourVente";

public static String  LIST_INCIDENT_DET_VENTE            = "list_Incident_det_vente";
public static String  LIST_INCIDENT_DET_VENTE_ORIGINE    = "list_Incident_det_vente_origine";


public static String LIST_DEPOT_STOCK          = "list_depot_stock";
public static String  LIST_CLIENT_VENTE   = "list_client_for_vente";
public static String BEAN_TOTAL         = "beanTotal_R_Vente";

public static String [][] MapfieldBean  = new String[][]{{ "ret_vente_id", "30" },{ "vente.vente_id", "30" },{ "ret_vente_date", "30" },{ "modeBean.fct_libelle", "30" }};





 


/********************************************************************View Jsp********************************************************************/

public static String FILTER_VIEW_VENTE       = "FilterProcedureVente";
public static String LIST_VIEW_VENTE         = "ListProcedureVente";
public static String FORM_VIEW          = "FormRetourVente";
public static String FORM_EDIT_VIEW     = "FormEditRetourVente";
public static String FILTER_VIEW        = "FilterRetourVente";
public static String FILTER_VIEW_AFFIRMER        = "FilterRetourVente_affirmer";

public static String LIST_VIEW          = "ListRetourVente";

public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_PDF_AFFIRMER = false;

public static boolean i$_ACT_FETCH_AJAX_VENTE = false;
public static boolean i$_ACT_CALCUL_TOTAL_AFFIR = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_LOAD_SELECT = false;




public static String LIST_CAUSE_RETOUR  = "list_cause_retour";
public static String LIST_SRC_CAUSE_RETOUR  = "list_source_cause_retour";
public static String LIST_SENS_STOCK  = "list_sens_stock";
public static String LIST_FOURNISSEUR_R_VENTE  = "list_fournisseurs";



public static ModelAndView getViewAffirmer(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_VALID);
	twebBean.setAct_doValid("i$_ACT_UPDATE");
	twebBean.setAct_doReset("i$_ACT_CANCEL_UPDATE");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setFctdoValid("doValidAction()");
	twebBean.setIdReadonly(true);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(FALSE);
	twebBean.setBtRetour(FALSE);
	return Get_Model_ROOT(isPage, twebBean);
}


public static ModelAndView getViewFilterAjaxAffirmer(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_AJAX");
	twebBean.setAct_doReset("i$_ACT_RESET_FILTER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
	twebBean.setAct_doPdf("i$_ACT_PDF_AFFIRMER");
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



public static ModelAndView getViewFilter_vente(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_AJAX_VENTE");
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

}
