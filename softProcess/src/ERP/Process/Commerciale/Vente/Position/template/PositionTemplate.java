package ERP.Process.Commerciale.Vente.Position.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class PositionTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 4140707264831783914L;
public  PositionTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Position/root.action";
public static Object  NAME_MODEL_BEAN  = new ProcedureVenteBean();
public static Object  NAME_TEMPLATE    = new PositionTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "op_vente";
public static String  schema           = "vente";
public static String  id_entite        = "vente_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Position-List";
public static String  NAME_GRID         = "Position-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "vente_id", "30" },{ "vente_date", "30" }   };
/********************************************************************View Jsp********************************************************************/


 
public static String FORM_LINK_VIEW     = "FormLinkPosition";
public static String FORM_VIEW          = "FormPosition";
public static String FILTER_VIEW        = "FilterPosition";
public static String LIST_VIEW          = "ListPosition";
public static String LIST_PAR_PRODUIT   = "ListPositionParProduit";

public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_LOAD_VIEW    = false;
public static boolean i$_ACT_RETOUR_TO_LinKED  = false;



public static ModelAndView getViewFilterViewLoad(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_AJAX");
	twebBean.setAct_doReset("i$_ACT_RESET_FILTER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LinKED");
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
