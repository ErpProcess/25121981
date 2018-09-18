package ERP.Process.Commerciale.Stock.ResponsableLieu.template;
import ERP.Process.Commerciale.Stock.ResponsableLieu.model.ResponsableLieuBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class ResponsableLieuTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 8619121035533384709L;

public  ResponsableLieuTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/ResponsableLieu/root.action";
public static Object  NAME_MODEL_BEAN  = new ResponsableLieuBean();
public static Object  NAME_TEMPLATE    = new ResponsableLieuTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "affect_depot_usr";
public static String  schema           = "stock";
public static String  id_entite        = "pk.depot.depot_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "ResponsableLieu-List";
public static String  NAME_GRID         = "ResponsableLieu-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "pk.depot.depot_libelle", "30" },{ "pk.usr.usr_id", "30" },{ "ordre", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormResponsableLieu";
public static String FILTER_VIEW        = "FilterResponsableLieu";
public static String LIST_VIEW          = "ListResponsableLieu";


public static String LIST_DEPOT_STOCK          = "list_depot_stock_aff";
 
public static boolean i$_ACT_INIT_SERVLET = false;
}
