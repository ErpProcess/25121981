package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.template;

import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.model.PrivilegeBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;

public class PrivilegeTemplate extends TemplateGeneric {
	public PrivilegeTemplate() {
		super();
	}

	/*******************************************************************Config Entite*****************************************************************/
	public static final String ROOT = "/ERP/eXpertSoft/wfsi/Administration/GestionUtilisateurs/Privilege/root.action";
	public static Object NAME_MODEL_BEAN = new PrivilegeBean();
	public static Object NAME_TEMPLATE = new PrivilegeTemplate();
	public static boolean is_Spoored = false;
	public static String entites = "privilege";
	public static String schema = "administration";
	public static String id_entite = "";
	/********************************************************************Data Grid********************************************************************/
	public static String LIST_DATA = "Privilege-List";
	public static String NAME_GRID = "Privilege-Grid";
	public static String[][] MapfieldBean = new String[][] {
			{ "prf_id", "30" }, { "fct_id", "30" }, { "sousmod_id", "30" } };
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW = "FormPrivilege";
	public static String FILTER_VIEW = "FilterPrivilege";
	public static String LIST_VIEW = "ListPrivilege";
	
	
	public static boolean i$_ACT_INIT_SERVLET = false;
	public static boolean i$_INIT_AFFECT = false;
	public static boolean i$_ACT_LOAD_SOUSPACKAGE = false;
	public static boolean i$_ACT_LOAD_MODUL = false;
	public static boolean i$_ACT_LOAD_F_S_MODUL = false;
	
	
	
	public static ModelAndView getViewInitAffect(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setAct_doValid("i$_INIT_AFFECT");
		twebBean.setAct_doReset("i$_ACT_RESET_FORM");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		twebBean.setBtNavigation(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	
}
