package ERP.Process.Commerciale.Vente.Facture_comp_client.template;
import ERP.Process.Commerciale.Vente.Facture_comp_client.model.Facture_comp_clientBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Facture_comp_clientTemplate   extends   TemplateGeneric   {
public  Facture_comp_clientTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Facture_comp_client/root.action";
public static Object  NAME_MODEL_BEAN  = new Facture_comp_clientBean();
public static Object  NAME_TEMPLATE    = new Facture_comp_clientTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "facture_comp_client";
public static String  schema           = "vente";
public static String  id_entite        = "fact_comp_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Facture_comp_client-List";
public static String  NAME_GRID         = "Facture_comp_client-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "fact_comp_id", "30" },{ "fact_comp_obs", "30" },{ "fact_comp_libelle", "30" },{ "mode_op", "30" },{ "time_cre", "30" },{ "fact_clt_id", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormFacture_comp_client";
public static String FILTER_VIEW        = "FilterFacture_comp_client";
public static String LIST_VIEW          = "ListFacture_comp_client";
}
