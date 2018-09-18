package ERP.Process.Commerciale.Achat.Reception_achat.template;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Reception_achatTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = -2406632637800613737L;


public  Reception_achatTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Achat/Reception_achat/root.action";
public static Object  NAME_MODEL_BEAN  = new Reception_achatBean();
public static Object  NAME_TEMPLATE    = new Reception_achatTemplate();
public static boolean is_Spoored       = true;
public static final   String ID_SOUS_MODULE    ="118";
public static String  entites          = "reception_achat";

public static String  schema           = "achat";
public static String  id_entite        = "achat_id";
public static String  entites_detaille = "det_reception_achat";
public static String[] id_entite_achat     = new String[] { "achat_id"  };
public static String[] id_entite_det_achat = new String[] { "pk.recepBean.achat_id"  ,"pk.fkCode_barre.pk.code_barre" };



/********************************************************************MapfieldBean********************************************************************/ 




public static String [][] MapfieldBean  = new String[][]{
	{ "achat_id", "30" }
	,{ "achat_date", "30" },
	 { "time_cre", "30" },
	 { "frsBean.frsref", "30" },
	 { "depot.depot_libelle", "30" }
	
	};
public static String [][] MapfieldBean_detaille  = 
	new String[][]{
	          { "pk.fkCode_barre.pk.code_barre", "30" }, 
	          { "pk.fkCode_barre.designation_libelle", "100" },
	          { "quantite", "20" },
	          { "tarif.tarif_unit_article", "30" }, 
	          { "tarif.tvaBean.tva_libelle", "10" },
	          { "montant_ht_achat", "50" }
	          };
/********************************************************************Data Grid********************************************************************/ 


public static  String JSON_DATA_TOTAL    = "beanTotal";
public static String BEAN_TOTAL          = "beanTotal";
public static String MAP_TARIFICATION    = "mapTarification";
public  static  String FORM_BEAN_DEMANDE  = "detailBeandm";


public static String  NAME_GRID         = "Reception_achat-Grid";
public static String  NAME_GRID_DEM     = "Demande_Achat-Grid";
public static String  LIST_DATA         = "Reception_achat-List";



public static String  LIST_DATA_DEM         = "Demande_Achat-List";
public static String LIST_DEPOT_STOCK          = "list_depot_stock";
public static String HASHMAP_FRS          = "hashmap_frs";

public static String LIST_FOURNISSEUR_RECEP_ACHAT       = "list_fournisseur_recep_achat";

public static String LIST_LOT_ARTICLE          = "list_Lot_article";
public static String LIST_DES_UNITES          = "list_des_unites";

public static String LIST_EDITABLE_RECEP_ACHAT          = "list_editable_recep_achat";
public static String LIST_EDITABLE_RECEP_ACHAT_CLONE    = "list_editable_recep_achat_clone";

public static String LIST_ARTICLE_RECP_ACHAT            = "list_article_recp_achat";
public static String LIST_ARTICLE_RECP_ACHAT_ORIGINE    = "list_article_recp_achat_origine";
public static String LIST_ARTICLE_RECP_ACHAT_GRID       = "list_article_recp_achat_grid";






/********************************************************************View Jsp********************************************************************/


   
 



public static String FORM_VIEW_EDIT     = "FormReception_achat";
public static String FORM_SERVIR_DEMANDE_ACHAT   = "FormServir_demande_achat";
public static String FORM_VIEW_DEM      = "FormCltR_achat_with_demande";
public static String FORM_VIEW          = "FormConsultReception_achat";
public static String FILTER_VIEW        = "FilterReception_achat";
public static String FILTER_VIEW_CMD    = "FilterDemande_Achat";
public static String  LIST_VIEW_SERVIR   = "ListDemande_Achat";
public static String  LIST_VIEW          = "ListReception_achat";


/********************************************************************Hidden action*******************************************************************/

public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_CALCUL_TOTAL = false;
public static boolean i$_ACT_STOCKER = false;
public static boolean i$_ACT_PRINT_PDF = false;
public static boolean i$_ACT_ANNULER = false;
public static boolean i$_ACT_TRANSFERER= false;
public static boolean i$_ACT_RECEPTION= false;
public static boolean i$_ACT_EXPORT_XLS = false;
public static boolean i$_ACT_ADD_ROW = false;
public static boolean i$_ACT_LOAD_TARIF = false;

public static boolean i$_ACT_DELETE_ROW = false;
public static boolean i$_ACT_ACTUALISER_TABLE= false;
public static boolean i$_ACT_LOAD_LOT_ARTICLE = false;
public static boolean i$_ACT_Cheked_unCheked = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_VALIDER = false;
public static boolean i$_ACT_SERVIR= false;
public static boolean i$_ACT_RETOUR_TO_LIST_OF_SERVIR= false;
public static boolean i$_ACT_ACTUALISER_LIST_CORR = false;
public static boolean i$_ACT_PRINT_PDF_DETAILLE_RECEPTION_ACHAT = false;
public static boolean i$_ACT_EXPORT_XLS_DETAILLE_RECEPTION_ACHAT = false;
public static boolean i$_ACT_FETCH_AJAX_SERVIR= false;



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







public static ModelAndView getViewServir_demande(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(button);
	twebBean.setLibelleAction(BTN_VLD_SERVICE);
	twebBean.setAct_doValid("i$_ACT_SERVIR");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST_OF_SERVIR");
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


 


public static ModelAndView getViewConsultA(String isPage) {

	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setBtValid(TRUE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
    String index=(String) getObjectValueModel(INDEX_ROW);
    List lis= (List) getObjectValueModel((String)getObjectValueModel(NAME_LIST_G));
    index=index==null?"0":index;
	if(index!=null && Integer.parseInt(index)==0){
	twebBean.setBtPrevious(TRUE);
	twebBean.setBtfirst(TRUE);
	twebBean.setBtNext(FALSE);
	twebBean.setBtLast(FALSE);
	}else if(index!=null &&  Integer.parseInt(index)==lis.size()-1){
		twebBean.setBtPrevious(FALSE);
		twebBean.setBtfirst(FALSE);
		twebBean.setBtNext(TRUE);
		twebBean.setBtLast(TRUE);
	}else{
		twebBean.setBtPrevious(FALSE);
		twebBean.setBtfirst(FALSE);
		twebBean.setBtNext(FALSE);
		twebBean.setBtLast(FALSE);
	}
	twebBean.setBtTrace(FALSE);
	twebBean.setBtAide(FALSE);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	twebBean.setIdReadonly(true);
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtRetour(FALSE);
	twebBean.setFctdoExcel("doExcel_detaille_demande_achat()");
	twebBean.setFctdoPdf("doPdf_detaille_demande_achat()");
	twebBean.setBtExcel(FALSE);
	twebBean.setBtPdf(FALSE);
	
	return Get_Model_ROOT(isPage, twebBean);
}


}
