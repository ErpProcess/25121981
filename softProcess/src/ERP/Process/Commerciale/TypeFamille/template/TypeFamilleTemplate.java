package ERP.Process.Commerciale.TypeFamille.template;
import ERP.Process.Commerciale.TypeFamille.model.TypeFamilleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class TypeFamilleTemplate   extends   TemplateGeneric   {
public  TypeFamilleTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT       = "/ERP/Process/Commerciale/TypeFamille/root.action";
public static Object  NAME_MODEL_BEAN   = new TypeFamilleBean();
public static Object  NAME_TEMPLATE     = new TypeFamilleTemplate();
public static boolean is_Spoored        = true;
public static String  entites           = "typefamille";
public static String  schema            = "gestioncommerciale";
public static String  id_entite         = "typefam_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "TypeFamille-List";
public static String  NAME_GRID         = "TypeFamille-Grid";
public static String [][] MapfieldBean  = new String[][]{{"typefam_id", "30" },{"typefam_lib", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTypeFamille";
public static String FILTER_VIEW        = "FilterTypeFamille";
public static String LIST_VIEW          = "ListTypeFamille";
}
