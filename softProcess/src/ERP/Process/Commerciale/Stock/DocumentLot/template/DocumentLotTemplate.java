package ERP.Process.Commerciale.Stock.DocumentLot.template;
 
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class DocumentLotTemplate   extends   TemplateGeneric   {
 
 
	private static final long serialVersionUID = 3780605084807874921L;
public  DocumentLotTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT       = "/ERP/Process/Commerciale/Stock/DocumentLot/root.action";

public static final   String ID_SOUS_MODULE      ="150";
public static final   String ID_SOUS_MODULE_METHODE      ="258";

 
public static Object  NAME_MODEL_BEAN   = new SerieArticletBean();
public static Object  NAME_TEMPLATE     = new DocumentLotTemplate();
public static boolean is_Spoored        = true;
public static String  entites           = "document_lot";
public static String  schema            = "stock";
public static String  id_entite         = "num_lot";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "DocumentLot-List";
public static String  NAME_GRID         = "DocumentLot-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "pk.num_serie", "30" },{ "date_fabrication", "30" },{ "date_utilisation", "30" },{ "date_peremption", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormDocumentLot";
public static String FORM_VIEW_CONSULT  = "FormConsultDocumentLot";

public static String FILTER_VIEW        = "FilterDocumentLot";
public static String FILTER_CHOIX_VIEW  = "FilterChoixDocumentLot";
public static String FORM_CHOIX_VIEW    = "FormChoixDocumentLot";
 
public static String LIST_VIEW          = "ListDocumentLot";
public static String BEAN_TOTAL         = "beanTotal_lot";

public static String LIST_EDITABLE_ARTICLE_LOT_ORIGIN          = "list_editable_article_lot_ORg";
public static String LIST_DETAILLE_MVT_LOT          		   = "list_detaille_mvt_lot";
public static String LIST_DETAILLE_MVT_LOT_CLONE               = "list_detaille_mvt_lot_clone";

public static boolean i$_ACT_DO_GET_DETAIL_LOT = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean test_ACT_CONSULT_INTO = false;
public static boolean i$_ACT_CALCUL_TOTAL = false;
public static boolean i$_ACT_GET_LOT_ARTICLE = false;
public static boolean i$_ACT_RESET_FORM_CHOIX = false;

public static boolean i$_ACT_ADD_CHOIX = false;

public static boolean i$_ACT_SUPP_CHOIX_LOT = false;



 


public static ModelAndView getViewChoix(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_ADD_CHOIX");
	twebBean.setAct_doReset("i$_ACT_RESET_FORM_CHOIX");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
	twebBean.setLibelleAction(BTN_ADD+"-Choix");
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(TRUE);
	twebBean.setBtReset(FALSE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(TRUE);
	return Get_Model_ROOT(isPage, twebBean);
}
}
