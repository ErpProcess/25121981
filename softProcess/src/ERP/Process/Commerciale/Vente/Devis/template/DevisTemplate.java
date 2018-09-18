package ERP.Process.Commerciale.Vente.Devis.template;
import ERP.Process.Commerciale.Vente.Devis.model.DevisBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class DevisTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 5823835486287320702L;

public  DevisTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Devis/root.action";
public static String  ID_SOUS_MODULE   = "167";
public static Object  NAME_MODEL_BEAN  = new DevisBean();
public static Object  NAME_TEMPLATE    = new DevisTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "devis";
public static String  schema           = "vente";
public static String  id_entite        = "devis_id";
/********************************************************************Data Grid********************************************************************/
public static String BEAN_TOTAL         = "beanTotalDevis";
public static String MAP_TARIFICATION          = "mapTarificationVente";
public static String MAP_TOGET_LIST_TARIFICA          = "map_toget_list_tarifica";
public static String MAP_CLIENT_BEN          = "mapclientBen";



public static String  NAME_GRID         = "Devis-Grid";
public static String  LIST_DATA         = "Devis-List";

public static String  LIST_CLIENT_DEVIS   = "list_client_devis";
public static String  LIST_DES_TVA   = "list_des_tva";

public static String  LIST_EDITABLE_DEVIS = "listGridEditableDevis";
public static String  LIST_EDITABLE_DEVIS_ORIGIN = "listGridEditableDevisOrg";
public static String  LIST_ARTICLE_DEVIS_VENTE            = "list_article_devVente";
public static String  LIST_ARTICLE_DEVIS_VENTE_ORIGINE    = "list_article_DEV_VENTE_origine";
public static String  LIST_ARTICLE_DEVIS_VENTE_GRID       = "list_article_dev_ven_grid";


public static String [][] MapfieldBean  = new String[][]{
	{ "devis_id", "30" },
	{ "dev_date", "20" },
	{ "time_cre", "20" },
	{ "client.clt_lib", "40" },
	{ "dev_lib", "20" },
	{ "modeBean.fct_libelle", "15" }
	};


   


public static String [][] MapfieldBean_detaille  = 
	new String[][]{
	          { "pk.code_barre.pk.code_barre", "30" }, 
	          { "pk.code_barre.designation_libelle", "100" },
	          { "quantite", "20" },
	          { "tarif.tarif_unit_vente", "30" },
	          { "montant_ht_vente", "50" }
	          };



/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormDevis";
public static String FORM_VIEW_EDIT     = "FormDevis_edit";
public static String FILTER_VIEW        = "FilterDevis";
public static String LIST_VIEW          = "ListDevis";

   

public static boolean i$_ACT_ADD_ROW = false;
public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_RESET_FORM = false;

public static boolean i$_ACT_LOAD_TARIF_CLIENT = false;


public static boolean i$_ACT_LOAD_DETAIL = false;


public static boolean i$_ACT_CALCUL_TOTAL = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_VALIDER = false;
public static boolean i$_ACT_CONFIRM = false;

public static boolean i$_ACT_ANNULER = false;
public static boolean i$_ACT_ACTUALISER_LIST_CORR = false;
public static boolean i$_ACT_ACTUALISER_TABLE = false;

public static boolean i$_ACT_DELETE_ROW = false;
public static boolean i$_ACT_Cheked_unCheked = false;

public static boolean i$_ACT_LOAD_DETAILLE_CONSULT = false;



public static boolean i$_ACT_PRINT_PDF_DETAILLE = false;

public static boolean i$_ACT_EXPORT_XLS_DETAILLE = false;


 





}
