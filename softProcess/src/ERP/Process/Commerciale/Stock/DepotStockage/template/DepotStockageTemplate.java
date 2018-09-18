package ERP.Process.Commerciale.Stock.DepotStockage.template;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class DepotStockageTemplate   extends   TemplateGeneric   {
public  DepotStockageTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/DepotStockage/root.action";
public static Object  NAME_MODEL_BEAN  = new DepotStockageBean();
public static Object  NAME_TEMPLATE    = new DepotStockageTemplate();
public static final   String ID_SOUS_MODULE    ="140";


public static boolean is_Spoored       = true;
public static String  entites          = "depot_stockage";
public static String  schema           = "stock";
public static String  id_entite        = "depot_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "DepotStockage-List";
public static String  NAME_GRID         = "DepotStockage-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "depot_id", "30" },{ "depot_libelle", "30" },{ "depot_ordre", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormDepotStockage";
public static String FILTER_VIEW        = "FilterDepotStockage";
public static String LIST_VIEW          = "ListDepotStockage";

public static boolean i$_ACT_INIT_SERVLET = false;

}
