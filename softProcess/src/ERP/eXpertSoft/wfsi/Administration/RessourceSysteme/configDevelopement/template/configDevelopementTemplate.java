package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.template;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.model.configDevelopementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class configDevelopementTemplate   extends   TemplateGeneric   {
public  configDevelopementTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/RessourceSysteme/configDevelopement/root.action";
public static Object  NAME_MODEL_BEAN  = new configDevelopementBean();
public static Object  NAME_TEMPLATE    = new configDevelopementTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "config_developement";
public static String  schema           = "administration";
public static String  id_entite        = "config_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "configDevelopement-List";
public static String  NAME_GRID         = "configDevelopement-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "config_id", "30" },{ "soc_id", "30" },{ "etab_id", "30" },{ "sousmod_id", "30" },{ "user_list", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormconfigDevelopement";
public static String FILTER_VIEW        = "FilterconfigDevelopement";
public static String LIST_VIEW          = "ListconfigDevelopement";

public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_LOAD_ETAB = false;



}
