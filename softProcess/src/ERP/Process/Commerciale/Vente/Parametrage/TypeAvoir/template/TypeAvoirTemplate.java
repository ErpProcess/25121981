package ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.template;
import ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model.TypeAvoirBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
public class TypeAvoirTemplate   extends   TemplateGeneric   {
 
	 
  private static final long serialVersionUID = -2990339670772489142L;
	
public  TypeAvoirTemplate() {
		super();
  }
/*******************************************************************Config Entite*****************************************************************/
  
public static final   String ROOT       = "/ERP/Process/Commerciale/Vente/Parametrage/TypeAvoir/root.action";

public static Object  NAME_MODEL_BEAN   = new TypeAvoirBean();
public static Object  NAME_TEMPLATE     = new TypeAvoirTemplate();
public static boolean is_Spoored        = true;
public static String  entites           = "type_avoir_facture";
public static String  schema            = "vente";
public static String  id_entite         = "type_avoir_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "TypeAvoir-List";
public static String  NAME_GRID         = "TypeAvoir-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "type_avoir_id", "30" },{ "type_avoir_lib", "30" },{ "type_avoir_res", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTypeAvoir";
public static String FILTER_VIEW        = "FilterTypeAvoir";
public static String LIST_VIEW          = "ListTypeAvoir";
}
