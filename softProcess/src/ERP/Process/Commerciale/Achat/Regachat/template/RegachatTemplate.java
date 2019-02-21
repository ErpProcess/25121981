package ERP.Process.Commerciale.Achat.Regachat.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Regachat.model.RegachatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class RegachatTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = -1151369282688539346L;
public  RegachatTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Achat/Regachat/root.action";
public static final   String ID_SOUS_MODULE                  ="233";
public static final   String ID_DELAIS_PAIEMENT                  ="234";

 
public static Object  NAME_MODEL_BEAN  = new RegachatBean();
public static Object  NAME_TEMPLATE    = new RegachatTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "reglement_fact_frs";
public static String  schema           = "achat";
public static String  id_entite        = "reg_frs_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Regachat-List";
public static String  NAME_GRID         = "Regachat-Grid";

public static String  LIST_DATA_FACTURE = "Facture-List";
public static String  NAME_GRID_FACTURE = "Facture-Grid";


public static String [][] MapfieldBean  = new String[][]{
	 { "reg_frs_id", "30" },
	 { "fact_frs.fact_frs_id", "30" },
	 { "fact_frs.frs.frsref", "30" },
	 { "reg_date", "30" },
	 { "reg_nbr_echeance", "15" },
	 { "montant_facture", "30" },
	 { "montant_avance", "30" },
	 { "montant_restant", "30" },
	 { "nature.data_libelle", "15" }};


  
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormRegachat";
public static String FORM_VIEW_EDIT     = "FormEditRegachat";
public static String FILTER_VIEW        = "FilterRegachat";
public static String LIST_VIEW          = "ListRegachat";

public static String FILTER_VIEW_FACTURE = "FilterFacture_Fournisseur";
public static String LIST_VIEW_FACTURE   = "ListFacture_Fournisseur";


public static String  LIST_MODE_REGLMENT   = "list_mode_reglment";

public static String  LIST_NATURE_REGLEMENT= "list_nature_reglement";



public static String  LIST_DES_ECHEANCES= "list_des_echeances";
public static String  LIST_DES_ECHEANCES_ORIGINE= "list_des_echeancesOrigine";


public static String  LIST_ETAT_ECH_REGLMENT   = "list_etat_reglment_ech";

 
 
public static boolean i$_ACT_ADD_ROW = false;
public static boolean i$_ACT_DELETE_ROW = false;
public static boolean I$_ACT_CHEKED_UNCHEKED = false;
public static boolean i$_ACT_ACTUALISER_TABLE= false;


public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_FETCH_AJAX_FACT = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_LOAD_GRID_ECHEAN= false;

public static ModelAndView getViewFilterFacture(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_AJAX_FACT");
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
	twebBean.setFctdoValid("ControlDisplayTableE()");
	twebBean.setFctdoReset("doResetAjaxData()");
	return Get_Model_ROOT(isPage, twebBean);
}


}
