package ERP.Process.Commerciale.Degre_definition.template;
import ERP.Process.Commerciale.Degre_definition.model.Degre_definitionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Degre_definitionTemplate   extends   TemplateGeneric   {
public  Degre_definitionTemplate() {
	super();
}                                         
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Degre_definition/root.action";
public static Object  NAME_MODEL_BEAN  = new Degre_definitionBean();
public static Object  NAME_TEMPLATE    = new Degre_definitionTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "degre_definition";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "";
public static String[] id_entite_degre_def = new String[] {
	"pkBean.art_Bean.pk_article.ar_id", "pkBean.carac_Bean.carac_id" };
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Degre_definition-List";
public static String  NAME_GRID         = "Degre_definition-Grid";
public static String [][] MapfieldBean  = new String[][]{{"pkBean.art_Bean.pk_article.ar_id", "30" },{"pkBean.art_Bean.ar_libelle", "30" },{"pkBean.carac_Bean.carac_id", "30" },{"pkBean.carac_Bean.carac_libelle", "30" },{"ordre", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormDegre_definition";
public static String FORM_VIEW_CONSULT  = "FormConsultDegre_definition";
public static String FILTER_VIEW        = "FilterDegre_definition";
public static String LIST_VIEW          = "ListDegre_definition";

public static String LIST_DES_ARTICLE = "list_des_article";

public static String LIST_DES_CARCTERISTIQUE = "list_des_carcteristique";

public static boolean i$_ACT_INIT_SERVLET = false;

public static boolean i$_ACT_LOAD_CRACTERISTIQUE = false;

public static boolean i$_ACT_SELECT_ROW = false;


}
