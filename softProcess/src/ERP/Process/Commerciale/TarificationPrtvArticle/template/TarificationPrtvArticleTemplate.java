package ERP.Process.Commerciale.TarificationPrtvArticle.template;

import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;

public class TarificationPrtvArticleTemplate extends TemplateGeneric {
 
 
	private static final long serialVersionUID = 8608527753036586318L;


	public TarificationPrtvArticleTemplate() {
		super();
	}

	/**
	 * *****************************************************************Config Entite****************************************************************
	 */
	public static final String	ROOT						= "/ERP/Process/Commerciale/TarificationPrtvArticle/root.action";
	public static Object		NAME_MODEL_BEAN				= new TarificationPrtvArticleBean();
	public static Object		NAME_TEMPLATE				= new TarificationPrtvArticleTemplate();
	public static boolean		is_Spoored					= true;
	public static String		entites						= "TarificationPrtvArticle_article";
	public static String		schema						= "gestioncommerciale";
	public static String		id_entite					= "tarif_prim_id";
	public static final   String ID_SOUS_MODULE      ="183";
	/**
	 * ******************************************************************Data Grid*******************************************************************
	 */
	public static String		LIST_DATA					= "TarificationPrtvArticle-List";
	public static String		NAME_GRID					= "TarificationPrtvArticle-Grid";
	public static String[][]	MapfieldBean				= new String[][] { { "tarif_prim_id", "30" },{ "date_prim_trf", "30" },
			{ "fkCode_barre.pk.code_barre", "30" }, { "tarif_unit_article", "30" },
			{ "groupe.grp_trf_lib", "30" }, { "taux_remise", "30" }, 
			{ "tvaBean.tva_libelle", "30" } };
	
	
	
	  
	/**
	 * ******************************************************************View Jsp*******************************************************************
	 */
	public static String		FORM_VIEW					= "FormTarificationPrtvArticle";
	public static String		FILTER_VIEW					= "FilterTarificationPrtvArticle";
	public static String		LIST_VIEW					= "ListTarificationPrtvArticle";

	public static String		FORM_INIT_ADD_TarificationPrtvArticle	= "Form_Init_add_TarificationPrtvArticle";
	public static String		FILTER_TO_ADD_TarificationPrtvArticle	= "Filter_To_add_TarificationPrtvArticle";

	public static String		LIST_ARTICLE_TARIF_PRTV_ARTICLE	= "list_article_TarificationPrtvArticle";
	
	
	public static String		LIST_GRP_TARIFI_PRTV		= "listGrpTarificationPrtvArticle";

	public static String		LIST_TVA_LIST				= "list_tva_List";
	public static String		MAP_ARTICLE				    = "map_articlo";
	

	public static boolean		i$_ACT_INIT_SERVLET			= false;
	public static boolean		i$_ACT_SELECT_ROW			= false;
	public static boolean		i$_ACT_CONFIRM			= false;
	
	
	
	public static boolean		i$_ACT_LOAD_COD_BARRE		= false;
	public static boolean		i$_ACT_LOAD_SUIVANT			= false;
	public static boolean		i$_ACT_UPDATE_EDITABLE		= false;
	
	public static boolean		i$_ACT_CALCULATE_PU		= false;
	
	
	
	public static String		BEAN_CODEBARRE_ARTICL       = "BeancodebarreArticl";
	 

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
