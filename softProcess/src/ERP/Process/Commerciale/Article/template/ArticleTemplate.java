package ERP.Process.Commerciale.Article.template;

import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;

public class ArticleTemplate extends TemplateGeneric {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5670775055408916699L;

	public ArticleTemplate() {
		super();
	}

	/*******************************************************************Config Entite*****************************************************************/
	public static final String ROOT = "/ERP/Process/Commerciale/Article/root.action";
	public static final   String ID_SOUS_MODULE      ="52";
	public static final   String ID_SOUS_MODULE_PRESTATION      ="277";
	 
	
	public static Object NAME_MODEL_BEAN = new ArticleBean();
	public static Object NAME_TEMPLATE   = new ArticleTemplate();
	public static boolean is_Spoored     = true;
	public static String entites         = "article";
	public static String schema          = "GestionCommerciale";
	public static String id_entite       = "ar_id";
	public static String[] id_entite_prixArtcile = new String[] {
			"pk_prix_ar.ar_bean.ar_id", "pk_prix_ar.date_prix" };
	public static String[] id_entite_Article = new String[] { 
		"pk_article.ar_id" ,"pk_article.etabBean.pk_etab.soc_bean.soc_id", "pk_article.etabBean.pk_etab.etab_id"};
	public static String entite_prixArticle = "prix_article";
	/********************************************************************Data Grid********************************************************************/
	public static String LIST_DATA = "Article-List";
	public static String NAME_GRID = "Article-Grid";
	
	public static String[][] MapfieldBean = new String[][] { 
		    { "pk_article.ar_id", "30" },
			{ "fam_art.fam_lib", "30" }, 
			{ "arcodbar", "30" },
			{ "ar_libelle", "30" },
			{ "prix_ar", "30" } , 
			{ "bean_sitcod.data_libelle", "30" },
			{ "unitBean.unite_lib", "30" }
			 };
	
	
	 
	
	//public static String[][] MAP_CRITERE_DE_RECHERCHE = new String[][] { { "pk_article.ar_id", "1-30" },{ "ar_libelle", "1-30" },   { "bean_artyp.data_id", "2-30" } };
	
	
	 
	
	public static String[][] Mapfieldtrace = new String[][] {
			{ "pk_article.ar_id", "30" }, { "fam_art.fam_id", "30" },
			{ "bean_sitcod.data_id", "30" }, { "ar_libelle", "30" },
			{ "arcodbar", "30" }, { "bean_artyp.data_id", "30" }, { "ar_obs", "30" } };
	
	public static String[][] Mapfieldtrace_prix = new String[][] {
			{ "pk_prix_ar.ar_bean.ar_id", "30" },
			{ "pk_prix_ar.date_prix", "30" }, { "prix_ar", "30" } };

	/******************************************************************** View Jsp ********************************************************************/
	public static String FORM_VIEW = "FormArticle";
	public static String FORM_SERVICE = "FormService";
	public static String FORM_VIEW_MODIFIER = "FormModifierArticle";
	 
	public static String FILTER_VIEW = "FilterArticle";
	public static String LIST_VIEW = "ListArticle";

	public static String FORM_PRIX_PRIX = "FormPrixArticle";
	public static String FORM_VIEW_LIEUX = "FormLieuxArticle"; 
	public static String FORM_VIEW_CLIENT = "FormArticleClient"; 
	public static String FORM_SITUATION_ARTICLE = "form_situation_article";

	public static boolean i$_ACT_INIT_SERVLET = false;
	
	public static boolean i$_ACT_GET_LIEUX = false;

	public static boolean i$_ACT_GET_ARTICLE_CLIENT = false;
	
	
	
	public static boolean i$_ACT_SELECT_ROW = false;
 
	public static boolean i$_ACT_UPDATE_SITUATION = false;
	public static boolean i$_ACT_LOAD_ETAB    = false;
	public static boolean i$_ACT_LOAD_ETAB_P_CLT    = false;
	public static boolean i$_ACT_LOAD_ARTICLE_CLT    = false;
	public static boolean i$_ACT_LOAD_REFE_CLT    = false;
	
	public static boolean i$_ACT_LIEUX_ARTICLE = false;
	
	public static boolean i$_ACT_CLT_ARTICLE = false;
	
	
	
	public static boolean i$_ACT_CANCEL_AFFECT = false;
	public static boolean i$_ACT_CANCEL_AFFECT_CLIENT = false;
	
 

	public static ModelAndView getViewPrix_ar(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();

		twebBean.setLibelleAction(BTN_VALID);
		twebBean.setAct_doValid("i$_ACT_INSERT_PRIX");
		twebBean.setAct_doReset("i$_ACT_CANCEL_INSERT_PRIX");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setFctdoValid("doValidAction()");

		twebBean.setIdReadonly(true);
		twebBean.setBtValid(FALSE);

		twebBean.setBtExcel(TRUE);
		twebBean.setBtPdf(TRUE);

		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		twebBean.setDisabled(true);
		twebBean.setReadonly(true);
		return Get_Model_ROOT(isPage, twebBean);

	}
	public static ModelAndView getViewLieux(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();

		twebBean.setLibelleAction(BTN_VALID);
		twebBean.setAct_doValid("i$_ACT_LIEUX_ARTICLE");
		twebBean.setAct_doReset("i$_ACT_CANCEL_AFFECT");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setFctdoValid("doValidAction()");
		twebBean.setIdReadonly(true);
		twebBean.setReadonly(false);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		return Get_Model_ROOT(isPage, twebBean);

	}
	
	public static ModelAndView getViewProduit_client(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();

		twebBean.setLibelleAction(BTN_VALID);
		twebBean.setAct_doValid("i$_ACT_CLT_ARTICLE");
		twebBean.setAct_doReset("i$_ACT_CANCEL_AFFECT_CLIENT");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setFctdoValid("doValidAction()");
		twebBean.setIdReadonly(true);
		twebBean.setReadonly(false);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		return Get_Model_ROOT(isPage, twebBean);

	}
	
	
	public static ModelAndView getViewSituation(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();

		twebBean.setLibelleAction(BTN_VALID);
		twebBean.setAct_doValid("i$_ACT_UPDATE_SITUATION");
		twebBean.setAct_doReset("i$_ACT_CANCEL_UPDATE");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setFctdoValid("doValidAction()");
		twebBean.setIdReadonly(true);
		twebBean.setReadonly(true);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		return Get_Model_ROOT(isPage, twebBean);

	}

}
