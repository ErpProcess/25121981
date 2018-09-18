package ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.template;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Nature_incident_mvt_retourTemplate   extends   TemplateGeneric   {
/**
	 * 
	 */
	private static final long serialVersionUID = 7150589423374786774L;
public  Nature_incident_mvt_retourTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Parametrage/Nature_incident_mvt_retour/root.action";


public static Object  NAME_MODEL_BEAN  = new Nature_incident_mvt_retourBean();
public static Object  NAME_TEMPLATE    = new Nature_incident_mvt_retourTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "nature_incident_mvt_com";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "nature_incident_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Nature_incident_mvt_retour-List";
public static String  NAME_GRID         = "Nature_incident_mvt_retour-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "nature_incident_id", "30" },{ "nature_incident_lib", "30" },{ "nature_incident_sense", "30" },{ "nature_incident_type", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormNature_incident_mvt_retour";
public static String FILTER_VIEW        = "FilterNature_incident_mvt_retour";
public static String LIST_VIEW          = "ListNature_incident_mvt_retour";
}
