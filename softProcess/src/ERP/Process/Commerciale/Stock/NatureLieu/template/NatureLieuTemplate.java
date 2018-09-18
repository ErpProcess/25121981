package ERP.Process.Commerciale.Stock.NatureLieu.template;
import ERP.Process.Commerciale.Stock.NatureLieu.model.NatureLieuBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class NatureLieuTemplate   extends   TemplateGeneric   {
public  NatureLieuTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/NatureLieu/root.action";
public static Object  NAME_MODEL_BEAN  = new NatureLieuBean();
public static Object  NAME_TEMPLATE    = new NatureLieuTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "nature_lieu";
public static String  schema           = "stock";
public static String  id_entite        = "nat_lieu_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "NatureLieu-List";
public static String  NAME_GRID         = "NatureLieu-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "nat_lieu_id", "30" },{ "nat_lieu_libelle", "30" },{ "ordre", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormNatureLieu";
public static String FILTER_VIEW        = "FilterNatureLieu";
public static String LIST_VIEW          = "ListNatureLieu";
}
