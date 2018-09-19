package ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.template;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class ModeReglementTemplate   extends   TemplateGeneric   {
public  ModeReglementTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/ParametrageCommerciale/ModeReglement/root.action";
public static Object  NAME_MODEL_BEAN  = new ModeReglementBean();
public static Object  NAME_TEMPLATE    = new ModeReglementTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "mode_reglement";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "mod_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "ModeReglement-List";
public static String  NAME_GRID         = "ModeReglement-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "mod_id", "30" },{ "mod_libelle", "30" },{ "mod_ordre", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormModeReglement";
public static String FILTER_VIEW        = "FilterModeReglement";
public static String LIST_VIEW          = "ListModeReglement";
}
