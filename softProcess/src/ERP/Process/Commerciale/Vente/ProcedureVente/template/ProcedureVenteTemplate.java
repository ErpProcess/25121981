package ERP.Process.Commerciale.Vente.ProcedureVente.template;
import java.util.HashMap;

import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class ProcedureVenteTemplate   extends   TemplateGeneric   {
	 
	 
  private static final long serialVersionUID = -3364852529975915078L;
  public  ProcedureVenteTemplate() {
	super();
  }
  
/*******************************************************************Config Entite*****************************************************************/

public static final   String ROOT              = "/ERP/Process/Commerciale/Vente/ProcedureVente/root.action";
public static final   String ID_SOUS_MODULE    = "166";

public static Object  NAME_MODEL_BEAN  = new ProcedureVenteBean();
public static Object  NAME_TEMPLATE    = new ProcedureVenteTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "op_vente";
public static String  entite_detaille          = "det_op_vente";
public static String  schema           = "vente";
public static String  id_entite        = "vente_id";
public static String[] id_entite_pricipale     = new String[] { "vente_id"   };
public static String[] id_entite_detaille = new String[] { "pk.vente.vente_id"  ,"pk.fkcode_barre.pk.code_barre" };

/********************************************************************Data Grid********************************************************************/ 

public static String MAP_TARIFICATION    = "mapTarification_op_vente";
public static String MAP_LIST_TARIFICA   = "map_toget_list_tarifica";
public static String MAP_CLIENT_BEN      = "mapclientBen";
public static String NAME_GRID_DEM       = "Commandeclient-Grid";
public static String NAME_GRID           = "ProcedureVente-Grid";
public static String LIST_DATA           = "ProcedureVente-List";


public static String LIST_DES_TVA          = "list_des_tva_vente";


public static String LIST_DATA_DEM         = "Commandeclient-List";

public static String LIST_EDITABLE_VENTE   = "list_editable_proVente";

public static String MAP_DERIVER_VENTE    = "map_deriver_vente";

public static String LIST_EDITABLE_VENTE_CLONE    = "list_editable_proVente_clone";

public static String LIST_EDITABLE_FOURNITURE_VENTE    = "list_editable_fournitureVente";
public static String LIST_EDITABLE_PRESTATION          = "list_editable_prestation";
public static String LIST_EDITABLE_PRESTATION_CLONE          = "list_editable_prestation_clone";

public static String LIST_EDITABLE_FOURNITURE_VENTE_CLONE    = "list_editable_fournitureVenteClone";



public static String LIST_ARTICLE_VENTE            = "list_article_proVente";
public static String LIST_ARTICLE_VENTE_ORIGINE    = "list_article_proVente_origine";
public static String LIST_ARTICLE_VENTE_GRID       = "list_article_proVente_grid";

public static String LIST_FOURNISSEUR_RECEP_ACHAT       = "list_fournisseur_recep_achat";

public static String LIST_DEPOT_STOCK          = "list_depot_stock";
public static String  LIST_CLIENT_VENTE   = "list_client_for_vente";
 
public static String  LIST_SERIE_PROVENTE   = "list_serie_proVente";
public static String  ARTICLO   = "artcloios";


public static String LIST_LOT_ARTICLE    = "list_Lot_article";
public static String LIST_DES_UNITES     = "list_des_unites";

public static String BEAN_TOTAL         = "beanTotalVente";


public static String LIST_ARTICLE_VENTE_FOURNITURE    = "list_article_vente_fourniture";
public static String LIST_ARTICLE_VENTE_FOURNITURE_ORIGINE    = "list_article_vente_fourniture_origine";
public static String LIST_ARTICLE_VENTE_FOURNITURE_GRID    = "list_article_vente_fourniture_grid";
public static String MAP_CODBARRE_FOURNITURE    = "mapCodBarreFourniture";
public static String MAP_CODBARRE_SERVICE    = "mapCodBarreService";

public static String MAP_TARIFICATION_FOURNITURE    = "map_tarification_fourniture";
public static String MAP_LIST_TARIFICA_FOURNITURE    = "map_list_tarifica_fourniture";


public static String MAP_TARIFICATION_SERVICE     = "map_tarification_service";
public static String MAP_LIST_TARIFICA_SERVICE    = "map_list_tarifica_service";

 

 
 
public static String LIST_ARTICLE_VENTE_SERVICE    = "list_article_vente_service";
public static String LIST_ARTICLE_VENTE_SERVICE_ORIGINE    = "list_article_vente_service_origine";
public static String LIST_ARTICLE_VENTE_SERVICE_GRID    = "list_article_vente_service_grid";

 


public static String [][] MapfieldBean  = new String[][]{
	{  "vente_id", "30" }
	,{ "vente_date", "30" },
	 { "time_cre", "30" },
	 { "client.clt_lib", "30" },
	 { "depot.depot_libelle", "30" }
	
	};
public static String [][] MapfieldBean_detaille  = 
	new String[][]{
	          { "pk.fkcode_barre.pk.code_barre", "30" }, 
	          { "pk.fkcode_barre.designation_libelle", "100" },
	          { "quantite", "20" },
	          { "tarif.tarif_unit_vente", "30" },
	          { "taux_remise_ligne", "20" },
	          { "montant_ht_vente", "50" }
	          };
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormProcedureVente";
public static String FORM_VIEW_CREER    = "FormProcedureVente_creer";
public static String FORM_VIEW_SERVIR   = "FormProcedureVente_servir";
public static String FILTER_VIEW        = "FilterProcedureVente";
public static String LIST_VIEW          = "ListProcedureVente";

 
public static String QTE_STOCK          = "qte_stock";

public static String FORM_VIEW_DEM      = "FormCltR_achat_with_demande";
 
  
public static String FILTER_VIEW_CMD     = "FilterCommandeclient";
public static String  LIST_VIEW_SERVIR   = "ListCommandeclient";

/********************************************************************Hidden action********************************************************************/

 
public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_RESET_FORM = false;
public static boolean i$_ACT_CORRIGER = false;

public static boolean i$_ACT_CALCUL_TOTAL = false;
public static boolean i$_ACT_CALCUL_TOTAL_FOURNITURE = false;

public static boolean i$_ACT_STOCKER = false;
public static boolean i$_ACT_PRINT_PDF = false;
public static boolean i$_ACT_ANNULER = false;
public static boolean i$_ACT_TRANSFERER= false;
public static boolean i$_ACT_RECEPTION= false;
public static boolean i$_ACT_EXPORT_XLS = false;
public static boolean i$_ACT_ADD_ROW = false;
public static boolean i$_ACT_DELETE_ROW = false;
public static boolean i$_ACT_DELETE_ROW_FOURNIURE = false;
public static boolean i$_ACT_DELETE_ROW_SERVICE = false;
public static boolean i$_ACT_Cheked_unCheked = false;
public static boolean i$_ACT_ACTUALISER_TABLE= false;
public static boolean i$_ACT_ACTUALISER_TABLE_FOUNITURE= false;
public static boolean i$_ACT_ACTUALISER_TABLE_PRESTATION= false;


public static boolean i$_ACT_ADD_ROW_FOURNIURE = false;
public static boolean i$_ACT_ADD_ROW_PRESTATION = false;




public static boolean i$_ACT_ACTUALISER_METHODE= false;

public static boolean i$_ACT_LOAD_TARIF_CLIENT= false;

public static boolean i$_ACT_LOAD_LOT_ARTICLE = false;

public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_VALIDER = false;
public static boolean i$_ACT_ACTUALISER_LIST_CORR = false;
public static boolean i$_ACT_PRINT_DETAILLE = false;
public static boolean i$_ACT_PRINT_FACTURE_FROM_VENTE = false;

public static boolean i$_ACT_EXPORT_DETAILLE = false;
public static boolean i$_ACT_SERVIR= false;
public static boolean i$_ACT_FETCH_AJAX_SERVIR= false;
public static boolean i$_ACT_RETOUR_LIST_SERVIR= false;

public static boolean i$_ACT_GET_STOCK= false;
public static boolean i$_ACT_GET_STOCK_FOURNITURE= false;

public static boolean i$_ACT_LOAD_SERIE= false;
public static boolean i$_ACT_FACTURER= false;


public static ModelAndView getViewValider(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(submit);
	twebBean.setLibelleAction(BTN_STOCK);
	twebBean.setAct_doValid("i$_ACT_STOCKER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);
}




public static ModelAndView getViewServir_commande(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(button);
	twebBean.setLibelleAction(BTN_VLD_SERVICE);
	twebBean.setAct_doValid("i$_ACT_SERVIR");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_LIST_SERVIR");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);	
}


public static ModelAndView getViewContremander_commande(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(button);
	twebBean.setLibelleAction(BTN_RET_SERVICE);
	twebBean.setAct_doValid("i$_ACT_CONREMANDER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_LIST_SERVIR");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);	
}

public static ModelAndView getViewModif_Srv(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(button);
	twebBean.setLibelleAction(BTN_UPDATE);
	twebBean.setAct_doValid("i$_ACT_UPDATE");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);	
}




public static ModelAndView getViewAnnuler(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(submit);
	twebBean.setLibelleAction("Valider annulation");
	twebBean.setAct_doValid("i$_ACT_ANNULER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);
}


public static ModelAndView getView_reception(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(submit);
	twebBean.setLibelleAction("Valider reception");
	twebBean.setAct_doValid("i$_ACT_RECEPTION");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);
}




public static ModelAndView getViewTransferer(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(submit);
	twebBean.setLibelleAction("Transferer vers stock");
	twebBean.setAct_doValid("i$_ACT_TRANSFERER");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);
}



public static ModelAndView getViewFilterAjax_Servir(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setAct_doValid("i$_ACT_FETCH_AJAX_SERVIR");
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
