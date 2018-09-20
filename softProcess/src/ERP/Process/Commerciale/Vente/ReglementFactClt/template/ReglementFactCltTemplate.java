package ERP.Process.Commerciale.Vente.ReglementFactClt.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
public class ReglementFactCltTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = -919128720958865473L;

public  ReglementFactCltTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/ReglementFactClt/root.action";
public static final   String ID_SOUS_MODULE                  ="195";
public static final   String ID_SOUS_MODULE_ECHENACE         ="196";
public static final   String ID_SOUS_MODULE_REMBOURCEMENT    ="205";

public static Object  NAME_MODEL_BEAN  = new ReglementFactCltBean();
public static Object  NAME_TEMPLATE    = new ReglementFactCltTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "reglement_fact_client";
public static String  schema           = "vente";
public static String  id_entite        = "reg_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "ReglementFactClt-List";
public static String  NAME_GRID         = "ReglementFactClt-Grid";




public static String [][] MapfieldBean  = new String[][]{{ "reg_id", "30" },{ "factclient.fact_clt_id", "30" },{ "modReg.mod_libelle", "30" },{ "reg_date", "30" },{ "reg_nbr_echeance", "30" },{ "montant_facture", "30" },{ "montant_avance", "30" },{ "montant_restant", "30" },{ "num_piece", "30" },{ "nature.data_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/



public static String FORM_VIEW          = "FormReglementFactClt";
public static String FORM_EDIT_VIEW     = "FormEditReglementFactClt";
public static String FILTER_VIEW        = "FilterReglementFactClt";
public static String LIST_VIEW          = "ListReglementFactClt";

public static String  LIST_DATA_FACTURE = "Facture-List";
public static String  NAME_GRID_FACTURE = "Facture-Grid";
public static String FILTER_VIEW_FACTURE = "FilterFacture";
public static String LIST_VIEW_FACTURE   = "ListFacture";


public static String  LIST_MODE_REGLMENT   = "list_mode_reglment";

public static String  LIST_ETAT_ECH_REGLMENT   = "list_etat_reglment_ech";

public static String  LIST_NATURE_REGLEMENT= "list_nature_reglement";



public static String  LIST_DES_ECHEANCES= "list_des_echeances";
public static String  LIST_DES_ECHEANCES_ORIGINE= "list_des_echeancesOrigine";
public static boolean i$_ACT_ADD_ROW = false;
public static boolean i$_ACT_DELETE_ROW = false;
public static boolean I$_ACT_CHEKED_UNCHEKED = false;
public static boolean i$_ACT_ACTUALISER_TABLE= false;

public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_FETCH_AJAX_FACT = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_LOAD_GRID_ECHEAN= false;
public static boolean i$_ACT_RETOUR_TO_LIST_FACT= false;


public static ModelAndView getViewAdd_reg(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_ADD");
	twebBean.setAct_doReset("i$_ACT_RESET_FORM");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST_FACT");
	twebBean.setLibelleAction(BTN_ADD);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(FALSE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(FALSE);
	return Get_Model_ROOT(isPage, twebBean);
}

}
