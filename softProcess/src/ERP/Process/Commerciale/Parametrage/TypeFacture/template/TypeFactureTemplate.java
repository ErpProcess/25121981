package ERP.Process.Commerciale.Parametrage.TypeFacture.template;
import ERP.Process.Commerciale.Parametrage.TypeFacture.model.TypeFactureBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class TypeFactureTemplate   extends   TemplateGeneric   {
public  TypeFactureTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Parametrage/TypeFacture/root.action";
public static Object  NAME_MODEL_BEAN  = new TypeFactureBean();
public static Object  NAME_TEMPLATE    = new TypeFactureTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "type_facture";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "type_fact_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "TypeFacture-List";
public static String  NAME_GRID         = "TypeFacture-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "type_fact_id", "30" },{ "type_fact_lib", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTypeFacture";
public static String FILTER_VIEW        = "FilterTypeFacture";
public static String LIST_VIEW          = "ListTypeFacture";
}
