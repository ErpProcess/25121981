package ERP.Process.Commerciale.Tarification.template;

import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;

public class TarificationTemplate extends TemplateGeneric {
	 
	private static final long serialVersionUID = 3163149852985011356L;


	public TarificationTemplate() {
		super();
	}

	/**
	 * *****************************************************************Config
	 * Entite****************************************************************
	 */
	public static final String	ROOT						= "/ERP/Process/Commerciale/Tarification/root.action";
	public static final   String ID_SOUS_MODULE    ="128";
	
	 
	public static Object		NAME_MODEL_BEAN				= new TarificationBean();
	public static Object		NAME_TEMPLATE				= new TarificationTemplate();
	public static boolean		is_Spoored					= true;
	public static String		entites						= "tarification_vente_article";
	public static String		schema						= "gestioncommerciale";
	public static String		id_entite					= "tarif_vente_id";
	/**
	 * ******************************************************************Data
	 * Grid*******************************************************************
	 */
	public static String		LIST_DATA					= "Tarification-List";
	public static String		NAME_GRID					= "Tarification-Grid";
	public static String[][]	MapfieldBean				= new String[][] { { "date_trf", "18" },
			{ "fkCode_barre.pk.code_barre", "30" },{ "fkCode_barre.designation_libelle", "30" }  ,
	
			{ "tarif_unit_vente", "30" }, { "taux_remise", "15" }, { "tvaBean.tva_libelle", "10" }  };
	
	 
	
	/**
	 * ******************************************************************View
	 * Jsp*******************************************************************
	 */
	public static String		FORM_VIEW					= "FormTarification";
	public static String		FILTER_VIEW					= "FilterTarification";
	public static String		LIST_VIEW					= "ListTarification";

	public static String		FORM_INIT_ADD_TARIFICATION	= "Form_Init_add_Tarification";
	public static String		FILTER_TO_ADD_TARIFICATION	= "Filter_To_add_Tarification";

	public static String		LIST_ARTICLE_TARIFICATION	= "list_article_tarification";

	public static String		LIST_TYLIST					= "list_tyList";
	public static String		LIST_TVLIST					= "list_tvList";

	public static boolean		i$_ACT_INIT_SERVLET			= false;
	public static boolean		i$_ACT_LOAD_COD_BARRE		= false;
	public static boolean		i$_ACT_GET_INFO_ARTICLE		= false;
	public static boolean		i$_ACT_GET_INFO_COUT	= false;
	public static boolean		i$_ACT_LOAD_DEPOT_LOT		= false;
	public static boolean		i$_ACT_LOAD_SUIVANT			= false;
	public static boolean		i$_ACT_UPDATE_EDITABLE		= false;
	public static boolean       i$_ACT_SELECT_ROW = false;
	
	public static boolean       i$_ACT_GET_INFO_VENTE = false;

	
	public static boolean       i$_ACT_PRINT_PDF = false;
	
	
	public static boolean i$_ACT_EXPORT_XLS = false;

	
	public static String LIST_LOT_ARTICLE     = "list_lot_article_fetch";
	
	public static String		BEAN_CODEBARRE_ARTICL					= "BeancodebarreArticl";
	 

	public static ModelAndView getViewFilter_to_Create_Tarif(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setAct_doValid("i$_ACT_FETCH_COD_BARRE");
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
		twebBean.setFctdoValid("doDisplayTableCodeBarre()");
		twebBean.setFctdoReset("doResetAjaxData()");
		return Get_Model_ROOT(isPage, twebBean);
	}

}
