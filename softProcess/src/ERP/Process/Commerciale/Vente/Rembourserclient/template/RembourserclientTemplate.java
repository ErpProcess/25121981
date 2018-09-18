package ERP.Process.Commerciale.Vente.Rembourserclient.template;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class RembourserclientTemplate   extends   TemplateGeneric   {
/**
	 * 
	 */
	private static final long serialVersionUID = -6704636410455359102L;
public  RembourserclientTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Rembourserclient/root.action";
public static final   String ID_SOUS_MODULE_REGLE_MENT       ="195";
public static final   String ID_SOUS_MODULE_ECHENACE         ="196";
public static final   String ID_SOUS_MODULE_REMBOURCEMENT    ="205";

public static Object  NAME_MODEL_BEAN  = new ReglementFactCltBean();
public static Object  NAME_TEMPLATE    = new RembourserclientTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "reglement_fact_client";
public static String  schema           = "vente";
public static String  id_entite        = "reg_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "ReglementFactClt-List";
public static String  NAME_GRID         = "ReglementFactClt-Grid";

public static String  LIST_DATA_FACTURE = "Facture-List";
public static String  NAME_GRID_FACTURE = "Facture-Grid";


public static String [][] MapfieldBean  = new String[][]{{ "reg_id", "30" },{ "factclient.fact_clt_id", "30" },{ "mode.data_libelle", "30" },{ "reg_date", "30" },{ "reg_nbr_echeance", "30" },{ "montant_facture", "30" },{ "montant_avance", "30" },{ "montant_restant", "30" },{ "num_piece", "30" },{ "nature.data_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormReglementFactClt";
public static String FORM_EDIT_VIEW     = "FormEditReglementFactClt";
public static String FILTER_VIEW        = "FilterReglementFactClt";
public static String LIST_VIEW          = "ListReglementFactClt";

public static String FILTER_VIEW_FACTURE = "FilterFacture";
public static String LIST_VIEW_FACTURE   = "ListFacture";


public static String  LIST_MODE_REGLMENT   = "list_mode_reglment";

public static String  LIST_NATURE_REGLEMENT= "list_nature_reglement";



public static String  LIST_DES_ECHEANCES= "list_des_echeances";
public static String  LIST_DES_ECHEANCES_ORIGINE= "list_des_echeancesOrigine";


public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_FETCH_AJAX_FACT = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_LOAD_GRID_ECHEAN= false;

}
