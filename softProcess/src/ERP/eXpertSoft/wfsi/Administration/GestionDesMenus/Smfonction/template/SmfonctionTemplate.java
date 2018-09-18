package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.template;

import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model.SmfonctionModel;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;

public class SmfonctionTemplate extends TemplateGeneric {
	public SmfonctionTemplate() {
		super();
	}

	/*******************************************************************Config Entite*****************************************************************/
	public static final String ROOT = "/ERP/eXpertSoft/wfsi/Administration/GestionDesMenus/Smfonction/root.action";
	public static Object NAME_MODEL_BEAN = new SmfonctionModel();
	public static Object NAME_TEMPLATE = new SmfonctionTemplate();
	public static boolean is_Spoored = false;
	public static String entites = "smfction";
	public static String schema = "administration";
	public static String id_entite = "";
	/********************************************************************Data Grid********************************************************************/
	public static String LIST_DATA = "Smfonction-List";
	public static String NAME_GRID = "Smfonction-Grid";
	public static String[][] MapfieldBean = new String[][] {
			{ "sousmod_id", "30" }, { "fct_id", "30" },
			{ "smfct_action", "30" } };
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW = "FormSmfonction";
	
	public static String FORM_VIEW_ANNULER = "FormSmfonction_for_annuler";
	
	 
	public static String FILTER_VIEW = "FilterSmfonction";
	public static String LIST_VIEW = "ListSmfonction";
	
	/********************************************************************Action Entite********************************************************************/
	public static boolean i$_ACT_INIT_SERVLET = false;
	public static boolean i$_ACT_SELECT_ROW = false;
	public static boolean i$_ACT_UPDATE_EDITABLE_TABLE_AJAX = false;
	
	public static ModelAndView getView_Afectation(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();

		twebBean.setLibelleAction(BTN_VALID);
		twebBean.setAct_doValid("i$_ACT_ADD");
		twebBean.setAct_doReset("i$_ACT_CANCEL_UPDATE");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setFctdoValid("doValidAction()");
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		return Get_Model_ROOT(isPage, twebBean);

	}
	
}
