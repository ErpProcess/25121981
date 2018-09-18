package ERP.Process.Commerciale.Achat.Fact_avoir_frs.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Fact_avoir_frs.model.Fact_avoir_frsBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Fact_avoir_frsTemplate   extends   TemplateGeneric   {
/**
	 * 
	 */
	private static final long serialVersionUID = 3771553357900546608L;




public  Fact_avoir_frsTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Achat/Fact_avoir_frs/root.action";
public static Object  NAME_MODEL_BEAN  = new Fact_avoir_frsBean();
public static Object  NAME_TEMPLATE    = new Fact_avoir_frsTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "fact_avoir_frs";
public static String  schema           = "achat";
public static String  id_entite        = "avoir_frs_id";

public static String    LIST_DATA_DET_FACT        = "list_detaille_fac_frs";
public static String    LIST_DATA_DET_FACT_CLONE  = "list_detaille_fac_frs_clone";

/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Fact_avoir_frs-List";
public static String  NAME_GRID         = "Fact_avoir_frs-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "avoir_frs_id", "30" },{ "avoir_frs_date", "30" },{ "modeBean.fct_libelle", "30" },{ "time_cre", "30" },{ "factfrs.fact_frs_id", "30" },{ "typeAvoir.type_avoir_id", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormFact_avoir_frs";
public static String FORM_VIEW_EDIT     = "FormEditFact_avoir_frs";
public static String FILTER_VIEW        = "FilterFact_avoir_frs";
public static String LIST_VIEW          = "ListFact_avoir_frs";

public static String  LIST_DATA_FACT    = "Fact_frs-List";
public static String  NAME_GRID_FACT    = "Fact_frs-Grid";
public static String  FILTER_VIEW_FACTURE= "FilterFacture_Fournisseur";
public static String  LIST_VIEW_FACTURE  = "ListFacture_Fournisseur";


public static String   LIST_DEPOT_STOCK    = "list_depot_facture_frs";
public static String   LIST_AVOIR_TYPE     = "list_avoir_type_frs";
public static String   LIST_FRS_ACHAT   = "list_frs_for_facture";
public static String   LIST_RETOUR_FRS_RESULTAT   = "list_REtour_frs_resultat";
public static String   BEAN_TOTAL_FACTURE_FRS     = "bean_total_facture_frs";


public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_FETCH_FACTURE_DOIT = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_LOAD_AVOIR_VENTE = false;

public static String LIST_DES_TVA       = "list_des_tva_fact_avoir_frs";




public static ModelAndView getView_FilterFacture_doit(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_FACTURE_DOIT");
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
