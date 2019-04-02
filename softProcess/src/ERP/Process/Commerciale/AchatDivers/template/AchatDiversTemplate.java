package ERP.Process.Commerciale.AchatDivers.template;
import ERP.Process.Commerciale.AchatDivers.model.AchatDiversBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class AchatDiversTemplate   extends   TemplateGeneric   {
public  AchatDiversTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/AchatDivers/root.action";
public static Object  NAME_MODEL_BEAN  = new AchatDiversBean();
public static Object  NAME_TEMPLATE    = new AchatDiversTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "achat_non_stocker";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "ach_non_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "AchatDivers-List";
public static String  NAME_GRID         = "AchatDivers-Grid";
public static String [][] MapfieldBean  = new String[][]{ { "date_achat", "20" },{ "libelle_achat", "70" },{ "prix_achat", "20" },{ "observation", "50" } };
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormAchatDivers";
public static String FILTER_VIEW        = "FilterAchatDivers";
public static String LIST_VIEW          = "ListAchatDivers";

public static boolean i$_ACT_LOAD_TOT = false;


}
