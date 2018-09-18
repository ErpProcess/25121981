package ERP.Process.Commerciale.TarificationLot.template;
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class TarificationLotTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = -7517131416782567808L;

public  TarificationLotTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/TarificationLot/root.action";
public static Object  NAME_MODEL_BEAN  = new TarificationLotBean();
public static Object  NAME_TEMPLATE    = new TarificationLotTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "tarification_vente_serie";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "num_serie";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "TarificationLot-List";
public static String  NAME_GRID         = "TarificationLot-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "num_serie", "30" },{ "depot_id", "30" },{ "tarif_vente_id", "30" },{ "", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTarificationLot";
public static String FORM_VIEW_EDIT     = "FormTarificationLotEdit";
 
public static String FILTER_VIEW        = "FilterTarificationLot";
public static String LIST_VIEW          = "ListTarificationLot";


public static String LIST_LOT_ARTICLE     = "list_lot_article";


public static boolean		i$_ACT_INIT_SERVLET			= false;
public static boolean		i$_ACT_SELECT_ROW			= false;
public static boolean		i$_ACT_LOAD_COD_BARRE		= false;
public static boolean		i$_ACT_GET_INFO_ARTICLE		= false;
public static boolean		i$_ACT_GET_INFO_COUT	    = false;
public static boolean		i$_ACT_LOAD_DEPOT_LOT		= false;
public static boolean		i$_ACT_LOAD_SUIVANT			= false;
public static boolean		i$_ACT_UPDATE_EDITABLE		= false;
public static String		LIST_ARTICLE_TARIFICATION	= "list_article_tarification";

public static String		LIST_TYLIST					= "list_tyList";
public static String		LIST_TVLIST					= "list_tvList";

public static String LIST_LOT_ARTICLE_FETCH     = "list_lot_article_fetch";

}
