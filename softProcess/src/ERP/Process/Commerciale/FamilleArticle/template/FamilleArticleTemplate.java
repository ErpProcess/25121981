package ERP.Process.Commerciale.FamilleArticle.template;
import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class FamilleArticleTemplate   extends   TemplateGeneric   {
public  FamilleArticleTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/FamilleArticle/root.action";
public static Object  NAME_MODEL_BEAN  = new FamilleBean();
public static Object  NAME_TEMPLATE    = new FamilleArticleTemplate();
public static boolean is_Spoored       = false;
public static String  entites          = "famille_article";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "fam_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "FamilleArticle-List";
public static String  TYPE_FAMILLE_ARTICLE         = "typeFamilleArticle";

public static String  NAME_GRID         = "FamilleArticle-Grid";
public static String [][] MapfieldBean  = new String[][]{{"fam_id", "30" },{"fam_lib", "30" },{"fam_ordre", "30" },{"fam_type", "30" },{"tvacod", "30" },{"sitcod", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormFamilleArticle";
public static String FILTER_VIEW        = "FilterFamilleArticle";
public static String LIST_VIEW          = "ListFamilleArticle";

public static boolean i$_ACT_INIT_SERVLET = false;

}
