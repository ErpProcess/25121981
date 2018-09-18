package ERP.Process.Commerciale.Vente.FournitureVente.template;
import ERP.Process.Commerciale.Vente.FournitureVente.model.FournitureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class FournitureVenteTemplate   extends   TemplateGeneric   {
public  FournitureVenteTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/FournitureVente/root.action";
public static Object  NAME_MODEL_BEAN  = new FournitureVenteBean();
public static Object  NAME_TEMPLATE    = new FournitureVenteTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "fourniture_vente";
public static String  schema           = "vente";
public static String  id_entite        = "frn_ve_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "FournitureVente-List";
public static String  NAME_GRID         = "FournitureVente-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "frn_ve_id", "30" },{ "vente_id", "30" },{ "frn_ve_date", "30" },{ "clt_id", "30" },{ "mode_op", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormFournitureVente";
public static String FILTER_VIEW        = "FilterFournitureVente";
public static String LIST_VIEW          = "ListFournitureVente";
}
