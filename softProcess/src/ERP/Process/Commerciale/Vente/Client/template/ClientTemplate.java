package ERP.Process.Commerciale.Vente.Client.template;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class ClientTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 5082090384687845336L;
public  ClientTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Client/root.action";

public static String  ID_SOUS_MODULE   = "67";
public static Object  NAME_MODEL_BEAN  = new ClientBean();
public static Object  NAME_TEMPLATE    = new ClientTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "client";
public static String  schema           = "vente";
public static String  id_entite        = "clt_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Client-List";
public static String  LIST_TYPE_TARIF_CLIENT  = "list_type_tarif_client";
public static String  LIST_CPT_BANK  = "list_cpt_bank_reg_fact";

public static String  NAME_GRID         = "Client-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "clt_lib", "30" },{ "clt_adr", "30" },{ "clt_tel", "30" },{ "clt_fax", "30" },{ "clt_email", "30" },{ "clt_numcpt", "30" },{ "clt_typ", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormClient";
public static String FILTER_VIEW        = "FilterClient";
public static String LIST_VIEW          = "ListClient";

/********************************************************************View Jsp********************************************************************/
public static boolean i$_ACT_INIT_SERVLET = false;


}
