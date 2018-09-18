package ERP.Process.Commerciale.Vente.Parametrage.caisse.template;
import ERP.Process.Commerciale.Vente.Parametrage.caisse.model.CaisseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class caisseTemplate   extends   TemplateGeneric   {
public  caisseTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Parametrage/caisse/root.action";
public static Object  NAME_MODEL_BEAN  = new CaisseBean();
public static Object  NAME_TEMPLATE    = new caisseTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "caisse_vente";
public static String  schema           = "vente";
public static String  id_entite        = "caisse_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "caisse-List";
public static String  NAME_GRID         = "caisse-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "caisse_id", "30" },{ "caisse_libelle", "30" },{ "caisse_type", "30" },{ "modeBean.fct_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "Formcaisse";
public static String FILTER_VIEW        = "Filtercaisse";
public static String LIST_VIEW          = "Listcaisse";
}
