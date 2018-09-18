package ERP.Process.Commerciale.Stock.TransfertLot.template;
import ERP.Process.Commerciale.Stock.TransfertLot.model.TransfertLotBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class TransfertLotTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = -1292214100764772470L;

public  TransfertLotTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/TransfertLot/root.action";
public static Object  NAME_MODEL_BEAN  = new TransfertLotBean();
public static Object  NAME_TEMPLATE    = new TransfertLotTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "transfert_serie_lieu";
public static String  schema           = "stock";
public static String  id_entite        = "transfert_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "TransfertLot-List";
public static String  NAME_GRID         = "TransfertLot-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "transfert_id", "30" },{ "num_serie", "30" },{ "depot_id_emet", "30" },{ "date_transfert", "30" },{ "quantite_transfert", "30" },{ "etat_de_transfert", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTransfertLot";
public static String FILTER_VIEW        = "FilterTransfertLot";
public static String LIST_VIEW          = "ListTransfertLot";


public static String  LIST_ARTICLE_DISPONIBLE   = "list_article_disponible";
public static String  LIST_LIEUX_DISPONIBLE   = "list_article_disponible";

public static boolean i$_ACT_INIT_SERVLET = false;


}
