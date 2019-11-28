package ERP.Process.Commerciale.Vente.Facture_client.template;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


 public class Facture_clientTemplate   extends   TemplateGeneric   {
 
	 private static final long serialVersionUID = -5948260163415336210L;
	 
public  Facture_clientTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Facture_client/root.action";
public static final   String ID_SOUS_MODULE    ="172";
public static final   String ID_SOUS_MODULE_AVOIR    ="197";
public static Object  NAME_MODEL_BEAN  = new Facture_clientBean();
public static Object  NAME_TEMPLATE    = new Facture_clientTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "facture_client";
public static String  entite_detaille  = "det_facture_client";
public static String  schema           = "vente";
public static String  id_entite        = "fact_clt_id";
public static String[] id_entite_detaille = new String[] { "pk.mvtVente.mvt_vente_id"  ,"pk.fkcode_barre.pk.code_barre","pk.factclient.fact_clt_id"  };


 

/********************************************************************Data Grid********************************************************************/

public static String  LIST_DATA_VENTE   = "procedurevente_list_facture";
public static String  NAME_GRID_VENTE   = "ProcedureVente-Grid";
public static String  LIST_DATA         = "Facture-List";
public static String  NAME_GRID         = "Facture-Grid";
public static String LIST_DES_TVA       = "list_des_tva_facture";
public static String  LIST_CPT_BANK  = "list_cpt_bank_fact";
public static String  LIST_MODE_REGLMENT   = "list_mode_reglment_facture";

public static String [][] MapfieldBean  = new String[][]{  { "fact_date", "15" } , { "fact_clt_id", "20" } ,{ "client.clt_lib", "30" },{ "total_facture", "30" }, { "retenuSource", "30" } ,{ "etat_reg.data_libelle", "30" }};


//0=Left, 1=Centre, 2=Right
//Field descriptor #80 I
public static final int NORMAL = 0;

// Field descriptor #80 I
public static final int BOLD = 1;

// Field descriptor #80 I
public static final int ITALIC = 2;

public static JSONObject propertieField = new JSONObject("{"+
                   "\"fact_date\": {\"title\": \"fact_date\",\"width\": 15 ,\"type\":\"String\", \"size\": 10 , \"align\": 0 },"+
                   "\"fact_clt_id\": {\"title\": \"fact_clt_id\",\"width\": 20 ,\"type\":\"String\", \"size\": 10 , \"align\": 0 },"+
                   "\"client.clt_lib\": {\"title\": \"clt_lib\",\"width\": 30 ,\"type\":\"String\", \"size\": 10 , \"align\": 0 },"+
                   "\"total_facture\": {\"title\": \"total_facture\",\"width\": 30 ,\"type\":\"montant3\", \"size\": 10 , \"align\": 2 },"+
                   "\"retenuSource\": {\"title\": \"retenuSource\",\"width\": 30 ,\"type\":\"montant3\", \"size\": 10 , \"align\": 2 },"+
                   "\"etat_reg.data_libelle\": {\"title\": \"etat_reg.data_libelle\",\"width\": 30 ,\"type\":\"String\", \"size\": 10 , \"align\": 0 },"+
  "}"
 );


//public static void main(String[] args) {
//	
//	String  data  ="{"+
//            "\"fact_date\": {\"title\": \"fact_date\",\"width\": 15 ,\"type\":\"String\", \"size\": 10 , \"align\": 0 },"+
//            "\"fact_clt_id\": {\"title\": \"fact_clt_id\",\"width\": 20 ,\"type\":\"String\", \"size\": 10 , \"align\": 0 },"+
//            "\"client.clt_lib\": {\"title\": \"clt_lib\",\"width\": 30 ,\"type\":\"String\", \"size\": 10 , \"align\": 0 },"+
//            "\"total_facture\": {\"title\": \"total_facture\",\"width\": 30 ,\"type\":\"montant3\", \"size\": 10 , \"align\": 2 },"+
//            "\"etat_reg.data_libelle\": {\"title\": \"etat_reg.data_libelle\",\"width\": 30 ,\"type\":\"String\", \"size\": 10 , \"align\": 0 },"+
//"}";
//	    JSONObject jsonObject = new JSONObject(data);
//	    
//	     
//	    JSONObject val = (JSONObject) jsonObject.get( "fact_date" );
//	    int valee = (int) val.get( "align" );
//		 System.out.println( val + ", " + valee);
// 
////	for (Iterator iterator = jsonObject.keys(); iterator.hasNext();) {
////	  String cle = (String) iterator.next();
////	  Object val = jsonObject.get( cle );
////	  System.out.println( cle + ", " + val);
////	}
//	
//	 
//}
 

public static String [][] MapfieldBean_detaille  = 
	new String[][]{
	          { "pk.fkcode_barre.pk.code_barre", "30" }, 
	          { "pk.fkcode_barre.designation_libelle", "100" },
	          { "quantite", "20" },
	          { "tarif_unit_vente", "30" },
	          { "montant_ht_vente", "50" }
	          //{ "montant_tva_vente", "50" }
	          };
public static String [][] MapfieldBean_ModelKobbi  = 
	new String[][]{
	         
	          { "pk.fkcode_barre.designation_libelle", "90" },
	          { "quantite", "35" },
	          { "nbrBoxes", "30" }, 
	          { "tarif_unit_vente", "30" },
	          { "montant_ttc_vente", "40" }
	          //{ "montant_tva_vente", "50" }
	          };
/********************************************************************View Jsp********************************************************************/

public static String  FILTER_VIEW_VENTE     = "FilterProcedureVente";
public static String  LIST_VIEW_VENTE       = "ListProcedureVente";
public static String  BEAN_TOTAL_FACTURE_CLIENT       = "bean_total_facture_client";

public static String    LIST_DATA_DET_FACT        = "list_detaille_fac_client";
public static String    LIST_DATA_DET_MVT_VENTE   = "list_detaille_mvt_vente";

public static String    LIST_DATA_DET_FACT_MVT_VENTE   = "list_detaille_fac_client_mvt_vente";
public static String    LIST_DATA_DET_FACT_CLONE   = "list_detaille_fac_client_clone";
public static String   LIST_DEPOT_STOCK   = "list_depot_facture";
public static String   LIST_CLIENT_VENTE   = "list_client_for_facture";

/************************************************************************************************************************************************/
public static String FORM_VIEW          = "FormFacture";
public static String FORM_VIEW_VENTE    = "FormProcedureVente";
public static String FILTER_VIEW        = "FilterFacture";
public static String LIST_VIEW          = "ListFacture";

/************************************************************************************************************************************************/

public static boolean i$_ACT_INIT_SERVLET     = false;
public static boolean i$_ACT_VERIF_LIST     = false;

public static boolean i$_ACT_FETCH_AJAX_VENTE = false;
public static boolean i$_ACT_GEN_FACTURE      = false;

public static boolean i$_ACT_PRINT_RETENUE_ALASOURCE      = false;

 

public static boolean i$_ACT_CALCUL_TOTAL     = false;
public static boolean i$_ACT_CALCUL_TOTAL_GRID= false;


public static boolean i$_ACT_SELECT_ROW       = false;
public static boolean i$_ACT_CONFIRM          = false;
public static boolean i$_ACT_ENVOYER          = false;
public static boolean i$_ACT_RETOUR_TO_LIST_VE = false;
public static boolean i$_RESET_APRES_FACT = false;
public static boolean i$_ACT_IMPRIMER_EXPORT_KB = false;

 



public static ModelAndView getViewConsult_Vente(String isPage) {

	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setBtValid(TRUE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtAide(FALSE);
	twebBean.setBtPrevious(TRUE);
	twebBean.setBtfirst(TRUE);
	twebBean.setBtNext(TRUE);
	twebBean.setBtLast(TRUE);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	twebBean.setIdReadonly(true);
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST_VE");
	twebBean.setBtRetour(FALSE);
	return Get_Model_ROOT(isPage, twebBean);
}


public static ModelAndView getViewFilterAjax_Vente(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_AJAX_VENTE");
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
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST_VE");
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
	twebBean.setAct_doRetour("i$_RESET_APRES_FACT");
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
