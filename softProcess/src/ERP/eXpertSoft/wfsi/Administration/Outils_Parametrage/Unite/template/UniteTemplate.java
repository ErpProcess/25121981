package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.template;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class UniteTemplate   extends   TemplateGeneric   {
public  UniteTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Unite/root.action";
public static Object  NAME_MODEL_BEAN  = new UniteBean();
public static Object  NAME_TEMPLATE    = new UniteTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "unite";
public static String  schema           = "administration";
public static String  id_entite        = "unite_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Unite-List";
public static String  NAME_GRID         = "Unite-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "unite_id", "30" },{ "unite_lib", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormUnite";
public static String FILTER_VIEW        = "FilterUnite";
public static String LIST_VIEW          = "ListUnite";


public static boolean i$_ACT_INIT_SERVLET = false;

}
