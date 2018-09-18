package ERP.Process.Commerciale.Type_tarification.template;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Type_tarificationTemplate   extends   TemplateGeneric   {
/**
	 * 
	 */
	private static final long serialVersionUID = 7051148958900107801L;
public  Type_tarificationTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Type_tarification/root.action";
public static Object  NAME_MODEL_BEAN  = new Type_tarificationBean();
 
public static final   String ID_SOUS_MODULE    = "129";

public static Object  NAME_TEMPLATE    = new Type_tarificationTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "type_tarification";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "type_trf_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Type_tarification-List";
public static String  NAME_GRID         = "Type_tarification-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "type_trf_id", "30" },{ "type_trf_lib", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormType_tarification";
public static String FILTER_VIEW        = "FilterType_tarification";
public static String LIST_VIEW          = "ListType_tarification";
}
