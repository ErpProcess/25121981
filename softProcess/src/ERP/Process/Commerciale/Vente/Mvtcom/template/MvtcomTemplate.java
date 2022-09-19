package ERP.Process.Commerciale.Vente.Mvtcom.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.Mvtcom.model.MvtcomBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class MvtcomTemplate   extends   TemplateGeneric   {
public  MvtcomTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Mvtcom/root.action";
public static Object  NAME_MODEL_BEAN  = new MvtcomBean();
public static Object  NAME_TEMPLATE    = new MvtcomTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "mvtcom";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Mvtcom-List";
public static String  NAME_GRID         = "Mvtcom-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "id", "30" },{ "name", "30" },{ "", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormMvtcom";
public static String FILTER_VIEW        = "FilterMvtcom";
public static String LIST_VIEW          = "ListMvtcom";
public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_ADD_MVT = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_LOAD_FILE_BASE64 = false;


public static ModelAndView getViewAddMvt(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_ADD");
	twebBean.setAct_doReset("i$_ACT_RESET_FORM");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
	twebBean.setLibelleAction(BTN_ADD);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(FALSE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(FALSE);
	twebBean.setFctdoValid("validerMvt()");
	twebBean.setFctdoReset("doResetAjaxMvt()");
	
	return Get_Model_ROOT(isPage, twebBean);
}


}
