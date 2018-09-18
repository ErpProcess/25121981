package ERP.Process.Commerciale.Stock.TypeInventaire.template;
import ERP.Process.Commerciale.Stock.TypeInventaire.model.TypeInventaireBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class TypeInventaireTemplate   extends   TemplateGeneric   {
public  TypeInventaireTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/TypeInventaire/root.action";
public static Object  NAME_MODEL_BEAN  = new TypeInventaireBean();
public static Object  NAME_TEMPLATE    = new TypeInventaireTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "type_inventaire";
public static String  schema           = "stock";
public static String  id_entite        = "type_inv_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "TypeInventaire-List";
public static String  NAME_GRID         = "TypeInventaire-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "type_inv_id", "30" },{ "type_inv_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTypeInventaire";
public static String FILTER_VIEW        = "FilterTypeInventaire";
public static String LIST_VIEW          = "ListTypeInventaire";
}
