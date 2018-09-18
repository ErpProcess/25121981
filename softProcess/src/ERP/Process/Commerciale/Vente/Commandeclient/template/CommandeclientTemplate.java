package ERP.Process.Commerciale.Vente.Commandeclient.template;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;

public class CommandeclientTemplate   extends   TemplateGeneric   {
 
 private static final long serialVersionUID = -5551463519671389196L;
	
public  CommandeclientTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/


public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Commandeclient/root.action";
public static final   String ID_SOUS_MODULE    ="170";
public static Object  NAME_MODEL_BEAN   = new CommandeclientBean();
public static Object  NAME_TEMPLATE     = new CommandeclientTemplate();
public static boolean is_Spoored        = true;
public static String  entites           = "commande_client";
public static String  schema            = "vente";
public static String  id_entite         = "cmd_id";


/********************************************************************Data Grid********************************************************************/ 


public static String  LIST_DATA         = "Commandeclient-List";
public static String  NAME_GRID         = "Commandeclient-Grid";
public static String BEAN_TOTAL         = "beanTotalCommande";
public static String  LIST_CLIENT_CMD_CLT   = "list_client_cmd_clt";
public static String  LIST_EDITABLE_CMD_CLT = "listGridEditableCmd";
public static String  LIST_EDITABLE_CMD_CLT_ORIGIN = "listGridEditableCmdOrg";
public static String  LIST_ARTICLE_CMD_CLT_VENTE            = "list_article_CmdVente";
public static String  LIST_ARTICLE_CMD_CLT_VENTE_ORIGINE    = "list_article_CmdVente_origine";
public static String  LIST_ARTICLE_CMD_CLT_VENTE_GRID       = "list_article_CmdVente_grid";


public static String MAP_TARIFICATION          = "mapTarificationCmdVente";
public static String MAP_TOGET_LIST_TARIFICA   = "map_toget_list_tarifica";
public static String MAP_CLIENT_BEN            = "mapclientBen";

public static String LIST_DES_TVA            = "list_des_tva_cmd";

public static String LIST_DEPOT_STOCK_CMD        = "list_depot_stock_cmd";

public static String [][] MapfieldBean  = new String[][]{{ "cmd_id", "30" },{ "cmd_date", "30" },{ "client.clt_lib", "30" }};

public static String  entites_detaille = "det_cmd_client";
public static String[] id_entite_detaille = new String[] { "pk.cmd.cmd_id"  ,"pk.fkcode_barre.pk.code_barre" };
public static String [][] MapfieldBean_detaille  = 
	new String[][]{
	          { "pk.fkcode_barre.pk.code_barre", "30" }, 
	          { "pk.fkcode_barre.designation_libelle", "100" },
	          { "quantite", "20" },
	          { "tarif.tarif_unit_vente", "30" },
	          { "montant_ht_vente", "50" }
	          };  
	          
	          
	         
/********************************************************************View Jsp********************************************************************/


public static String FORM_VIEW          = "FormCommandeclient";
public static String FORM_VIEW_EDIT     = "FormCommandeclient_edit";
public static String FILTER_VIEW        = "FilterCommandeclient";
public static String LIST_VIEW          = "ListCommandeclient";


/********************************************************************HIDDEN ACT VIEW********************************************************/

public static boolean i$_ACT_INIT_SERVLET         = false;
public static boolean i$_ACT_LOAD_TARIF_CLT       = false;
public static boolean i$_ACT_ADD_ROW              = false;
public static boolean i$_ACT_DELETE_ROW           = false;
public static boolean i$_ACT_CHEKED_UNCHEKED      = false;
public static boolean i$_ACT_ACTUALISER_LIST_CORR = false;
public static boolean i$_ACT_ACTUALISER_TABLE     = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_CALCUL_TOTAL = false;


}
