package ERP.Process.Commerciale.Achat.Facture_Fournisseur.template;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Facture_FournisseurTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 4130003037628401731L;

public  Facture_FournisseurTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Achat/Facture_Fournisseur/root.action";

public static String  ID_SOUS_MODULE          = "155";
public static Object  NAME_MODEL_BEAN  = new Facture_FournisseurBean();
public static Object  NAME_TEMPLATE    = new Facture_FournisseurTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "facture_fournisseur";
public static String  schema           = "achat";
public static String  id_entite        = "fact_frs_id";
public static String   entite_detaille  = "det_facture_client";
public static String[] id_entite_detaille = new String[] { "pk.facture_frs.fact_frs_id"  ,"pk.fkCode_barre.pk.code_barre"   };
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Facture_Fournisseur-List";
public static String  NAME_GRID         = "Facture_Fournisseur-Grid";

public static String  LIST_VIEW_ACHAT_FACT         = "ListReception_achat";

public static String  LIST_FOURNISSEUR_FACTURE_FRS         = "list_fournisseur_facture_frs";




public static String  LIST_DATA_ACHAT_FACT         = "List_data_achat_fact";
public static String  NAME_GRID_ACHAT_FACT         = "name_grid_achat_fact";

public static String  LIST_DATA_DET_FACT         = "list_data_detaille_fact_frs";
public static String  LIST_DATA_DET_MVT_ACHAT         = "list_data_det_mvt_achat";


public static String  BEAN_TOTAL_FACTURE_FRS         = "bean_total_facture_frs";
public static String LIST_DES_TVA       = "list_des_tva_facture_frs";



public static String [][] MapfieldBean  = new String[][]{{ "fact_frs_id", "30" },{ "frs.frs_id", "30" },{ "fact_date", "30" },{ "fact_date_edition", "30" },{ "modeBean.fct_libelle", "30" }};



public static String [][] MapfieldBean_detaille  = 
	new String[][]{
	          { "pk.fkCode_barre.pk.code_barre", "30" }, 
	          { "pk.fkCode_barre.designation_libelle", "100" },
	          { "quantite", "20" },
	          { "tarif_unit_achat", "30" },
	          { "montant_ht_achat", "50" }
	          //{ "montant_tva_vente", "50" }
	          };


/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormFacture_Fournisseur";
public static String FILTER_VIEW        = "FilterFacture_Fournisseur";
public static String LIST_VIEW          = "ListFacture_Fournisseur";
public static String FILTER_VIEW_RECEP_ACHAT        = "FilterReception_achat";

public static boolean i$_ACT_UPLOADER = false;
public static boolean i$_ACT_AFFICHE_DOC = false;
public static boolean i$_ACT_CALCUL_TOTAL_GRID= false;


public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_FETCH_TO_FACTURE = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_GEN_FACTURE = false;
public static boolean i$_ACT_RETOUR_TO_LIST_ACHAT = false;



public static boolean i$_ACT_FETCH_AJAX_ACHAT= false;

public static ModelAndView getViewEnoyer(String isPage) { 
	
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_CONFIRM");
	twebBean.setAct_doReset("");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setLibelleAction(BTN_VALID);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(FALSE);
	
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	

	
	return Get_Model_ROOT(isPage, twebBean);
}

public static ModelAndView getViewFilterAjax_Achat(String isPage) {
	 
	
	
	
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_TO_FACTURE");
	twebBean.setAct_doReset("i$_ACT_RESET_FILTER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
	twebBean.setIconAction("Search.png");
	twebBean.setLibelleCommit("Etablir facture");
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
	twebBean.setFctdoValid("DisplayTableE()");
	twebBean.setAct_doCommit("i$_ACT_GEN_FACTURE");
	twebBean.setFctdoCommit("doCommitAction()");
	twebBean.setFctdoReset("doResetAjaxEE()");
	return Get_Model_ROOT(isPage, twebBean);
}

public static ModelAndView getViewPreparerAdd(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_ADD");
	twebBean.setAct_doReset("");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST_ACHAT");
	twebBean.setLibelleAction(BTN_ADD);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtExcel(FALSE);
	twebBean.setBtPdf(FALSE);
	twebBean.setAct_doExcel("i$_ACT_EXPORT_XLS_DETAILLE");
	twebBean.setAct_doPdf("i$_ACT_PRINT_PDF_DETAILLE");
	twebBean.setBtNavigation(FALSE);
	return Get_Model_ROOT(isPage, twebBean);
}

public static ModelAndView getViewAdd_after_save(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("");
	twebBean.setAct_doReset("");
	twebBean.setAct_doRetour("");
	twebBean.setLibelleAction(BTN_ADD);
	twebBean.setBtAide(TRUE);
	twebBean.setBtTrace(TRUE);
	twebBean.setBtValid(TRUE);
	twebBean.setBtExcel(TRUE);
	twebBean.setBtPdf(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setAct_doExcel("i$_ACT_EXPORT_XLS_DETAILLE");
	twebBean.setAct_doPdf("i$_ACT_PRINT_PDF_DETAILLE");
	twebBean.setBtNavigation(FALSE);
	return Get_Model_ROOT(isPage, twebBean);
}


}
