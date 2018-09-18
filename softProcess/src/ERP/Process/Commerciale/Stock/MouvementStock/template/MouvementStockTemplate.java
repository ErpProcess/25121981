package ERP.Process.Commerciale.Stock.MouvementStock.template;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class MouvementStockTemplate   extends   TemplateGeneric   {
public  MouvementStockTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/MouvementStock/root.action";
public static Object  NAME_MODEL_BEAN  = new MouvementSerieBean();
public static Object  NAME_TEMPLATE    = new MouvementStockTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "mvt_stock_article";
public static String  schema           = "stock";
public static String  id_entite        = "mvt_stock_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "MouvementStock-List";
public static String  NAME_GRID         = "MouvementStock-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "pk.date_mvt_stock", "30" },{ "pk.document_com_id", "30" }   };
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormMouvementStock";
public static String FILTER_VIEW        = "FilterMouvementStock";
public static String LIST_VIEW          = "ListMouvementStock";


public static boolean i$_ACT_INIT_SERVLET = false;


}
