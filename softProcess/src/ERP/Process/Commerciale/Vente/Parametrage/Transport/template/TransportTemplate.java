package ERP.Process.Commerciale.Vente.Parametrage.Transport.template;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.model.TransportBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class TransportTemplate   extends   TemplateGeneric   {
public  TransportTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Parametrage/Transport/root.action";
public static final   String ID_SOUS_MODULE      ="224";

public static Object  NAME_MODEL_BEAN  = new TransportBean();
public static Object  NAME_TEMPLATE    = new TransportTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "transporteur";
public static String  schema           = "vente";
public static String  id_entite        = "trans_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Transport-List";
public static String  NAME_GRID         = "Transport-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "trans_id", "30" },{ "trans_libelle", "30" },{ "trans_type", "30" },{ "trans_obs", "30" },{ "modeBean.fct_libelle", "30" },{ "time_cre", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTransport";
public static String FILTER_VIEW        = "FilterTransport";
public static String LIST_VIEW          = "ListTransport";
}
