package ERP.Process.Commerciale.Vente.Facture_avoir_client.template;
 
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Facture_avoir_clientTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 1323666205211797756L;

public  Facture_avoir_clientTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Facture_avoir_client/root.action";
public static Object  NAME_MODEL_BEAN  = new Facture_clientBean();
public static Object  NAME_TEMPLATE    = new Facture_avoir_clientTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "facture_avoir_client";
public static String  schema           = "vente";
public static String  id_entite        = "avoir_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Facture_avoir_client-List";
public static String  NAME_GRID         = "Facture_avoir_client-Grid";

public static String  LIST_DATA_FACT    = "Fact_client-List";
public static String  NAME_GRID_FACT    = "Fact_client-Grid";
public static String FILTER_VIEW_FACTURE= "FilterFacture";
public static String LIST_VIEW_FACTURE  = "ListFacture";



public static String [][] MapfieldBean  = new String[][]{{ "fact_clt_id", "30" },{ "client.clt_id", "30" },{ "client.clt_lib", "30" },{ "fact_date", "30" },{ "fact_date_edition", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormFacture_avoir_client";
public static String FORM_VIEW_EDIT          = "FormFacture_avoir_clientEdit";
 



public static String FILTER_VIEW        = "FilterFacture_avoir_client";
public static String LIST_VIEW          = "ListFacture_avoir_client";



public static String   LIST_DEPOT_STOCK    = "list_depot_facture";
public static String   LIST_AVOIR_TYPE     = "list_avoir_type";
public static String   LIST_CLIENT_VENTE   = "list_client_for_facture";
public static String   LIST_RETOURVENTE_RESULTAT   = "list_REtourVente_resultat";
public static String  BEAN_TOTAL_FACTURE_CLIENT       = "bean_total_facture_client";

public static String    LIST_DATA_DET_FACT        = "list_detaille_fac_client";
public static String    LIST_DATA_DET_FACT_CLONE  = "list_detaille_fac_client_clone";
public static String    LIST_DATA_DET_MVT_VENTE   = "list_detaille_mvt_vente";


public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_FETCH_FACTURE_DOIT = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_LOAD_AVOIR_VENTE = false;

public static String LIST_DES_TVA       = "list_des_tva_facture_avoir";




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
