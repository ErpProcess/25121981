package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.template;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class DeviseTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = -4964245032281464514L;
	
public  DeviseTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Devise/root.action";
public static Object  NAME_MODEL_BEAN  = new DeviseBean();
public static Object  NAME_TEMPLATE    = new DeviseTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "devise";
public static String  schema           = "administration";
public static String  id_entite        = "dev_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Devise-List";
public static String  NAME_GRID         = "Devise-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "dev_abrv", "30" },{ "dev_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormDevise";
public static String FILTER_VIEW        = "FilterDevise";
public static String LIST_VIEW          = "ListDevise";



}
