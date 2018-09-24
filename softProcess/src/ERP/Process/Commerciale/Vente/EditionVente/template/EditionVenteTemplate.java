package ERP.Process.Commerciale.Vente.EditionVente.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.EditionVente.model.EditionVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class EditionVenteTemplate   extends   TemplateGeneric   {
public  EditionVenteTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/EditionVente/root.action";
public static Object  NAME_MODEL_BEAN  = new EditionVenteBean();
public static Object  NAME_TEMPLATE    = new EditionVenteTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "edition_vente";
public static String  schema           = "vente";
public static String  id_entite        = "edition_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "EditionVente-List";
public static String  NAME_GRID         = "EditionVente-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "edition_id", "30" },{ "edition_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormEditionVente";
public static String FILTER_VIEW        = "FilterEditionVente";
public static String LIST_VIEW          = "ListEditionVente";

public static ModelAndView getViewFilterEdition(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_AJAX");
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
	twebBean.setFctdoValid("doDisplayTableData()");
	twebBean.setFctdoReset("doResetAjaxData()");
	return Get_Model_ROOT(isPage, twebBean);
}
}
