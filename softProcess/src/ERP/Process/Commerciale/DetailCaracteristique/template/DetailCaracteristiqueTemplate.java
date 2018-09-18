package ERP.Process.Commerciale.DetailCaracteristique.template;
import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class DetailCaracteristiqueTemplate   extends   TemplateGeneric   {
public  DetailCaracteristiqueTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/DetailCaracteristique/root.action";
public static Object  NAME_MODEL_BEAN  = new DetailCaracteristiqueBean();
public static Object  NAME_TEMPLATE    = new DetailCaracteristiqueTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "det_caracteristique";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "";

public static String[] id_entite_detaille = new String[] {
	"pk_det_carac.bean_carac.carac_id", "pk_det_carac.det_carac_id" };

 

/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "DetailCaracteristique-List";
public static String  NAME_GRID         = "DetailCaracteristique-Grid";
public static String [][] MapfieldBean  = new String[][]{{"pk_det_carac.bean_carac.carac_id", "30" },
	{"pk_det_carac.det_carac_id", "30" },
	{"det_carac_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormDetailCaracteristique";
public static String FILTER_VIEW        = "FilterDetailCaracteristique";
public static String LIST_VIEW          = "ListDetailCaracteristique";

public static String LIST_CARACTERISTIQUE = "list_Carac_for_det_car";

public static boolean i$_ACT_INIT_SERVLET = false;
}
