package ERP.Process.Commerciale.Caracteristique.template;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class CaracteristiqueTemplate   extends   TemplateGeneric   {
public  CaracteristiqueTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Caracteristique/root.action";
public static Object  NAME_MODEL_BEAN  = new CaracteristiqueBean();
public static Object  NAME_TEMPLATE    = new CaracteristiqueTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "caracteristique";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "carac_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Caracteristique-List";
public static String  NAME_GRID         = "Caracteristique-Grid";
public static String [][] MapfieldBean  = new String[][]{  {"carac_id", "30" },{"carac_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTypeCaracteristique";
public static String FILTER_VIEW        = "FilterTypeCaracteristique";
public static String LIST_VIEW          = "ListTypeCaracteristique";
}
 
 
