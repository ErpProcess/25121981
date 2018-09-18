package ERP.Process.Commerciale.Vente.ComplementVente.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.ComplementVente.model.ComplementVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class ComplementVenteTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 5902626509182476895L;


public  ComplementVenteTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/ComplementVente/root.action";
public static Object  NAME_MODEL_BEAN  = new ComplementVenteBean();
public static Object  NAME_TEMPLATE    = new ComplementVenteTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "complement_vente";
public static String  schema           = "vente";
public static String  id_entite        = "comp_vente_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "ComplementVente-List";
public static String  NAME_GRID         = "ComplementVente-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "comp_vente_id", "30" },{ "vente.vente_id", "30" },{ "comp_vente_date", "30" },{ "time_cre", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormComplementVente";
public static String FILTER_VIEW        = "FilterComplementVente";
public static String FORM_EDIT_VIEW     = "FormEditComplementVente";
public static String LIST_VIEW          = "ListComplementVente";

public static String FILTER_VIEW_VENTE       = "FilterProcedureVente";
public static String LIST_VIEW_VENTE         = "ListProcedureVente";
public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_FETCH_AJAX_VENTE = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_LOAD_SELECT = false;


public static String  LIST_EDITABLE_COMPLEMNT_VENTE_ORIGINE = "list_editable_ComplementVente_Origine";
public static String  LIST_EDITABLE_COMPLEMNT_VENTE         = "list_editable_ComplementVente";

public static String  LIST_INCIDENT_DET_VENTE            = "list_Incident_det_vente";
public static String  LIST_INCIDENT_DET_VENTE_ORIGINE    = "list_Incident_det_vente_origine";


public static String  LIST_DATA_VENTE   = "Vente-List";


public static String  NAME_GRID_VENTE   = "Vente-Grid";
public static String LIST_DEPOT_STOCK          = "list_depot_stock";
public static String LIST_CLIENT_VENTE         = "list_client_for_vente";

public static String LIST_CAUSE_RETOUR  = "list_cause_retour";
public static String LIST_SENS_STOCK  = "list_sens_stock";


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
