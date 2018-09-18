package ERP.Process.Commerciale.Vente.Service.template;
import ERP.Process.Commerciale.Vente.Service.model.ServiceBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class ServiceTemplate   extends   TemplateGeneric   {
public  ServiceTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Service/root.action";
public static Object  NAME_MODEL_BEAN  = new ServiceBean();
public static Object  NAME_TEMPLATE    = new ServiceTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "service";
public static String  schema           = "vente";
public static String  id_entite        = "srv_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Service-List";
public static String  NAME_GRID         = "Service-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "srv_id", "30" },{ "srv_libelle", "30" },{ "vente_id", "30" },{ "srv_date", "30" },{ "clt_id", "30" },{ "depot_id", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormService";
public static String FILTER_VIEW        = "FilterService";
public static String LIST_VIEW          = "ListService";
}
