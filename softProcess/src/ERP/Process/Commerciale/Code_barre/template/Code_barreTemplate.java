package ERP.Process.Commerciale.Code_barre.template;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Code_barreTemplate   extends   TemplateGeneric   {
public  Code_barreTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Code_barre/root.action";
public static Object  NAME_MODEL_BEAN  = new Code_barreBean();
public static Object  NAME_TEMPLATE    = new Code_barreTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "code_barre";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "pk.code_barre";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Code_barre-List";
public static String  NAME_GRID         = "Code_barre-Grid";
public static String [][] MapfieldBean  = new String[][]{{"pk.code_barre", "30" },{"pk.ar_bean.pk_article.ar_id", "30" },{"designation", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormCode_barre";
public static String FORM_INIT_VIEW     = "FormInitCode_barre";
public static String FILTER_VIEW        = "FilterCode_barre";
public static String LIST_VIEW          = "ListCode_barre";

public static String LIST_DES_CARAC_FOR_ARTICLE = "List_des_carac_for_article";
public static String LIST_COD_BARR_MASTER = "list_cod_barr_master";
public static String LIST_DES_ARTICLE = "list_des_article";

public static String SELECT_LIST_CARAC_DET_CODE_BARRE = "select_list_carac_det_code_barre";



public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_LOAD_CODE_BARR = false;
public static boolean i$_ACT_UPDATE_LIST_CARACT = false;
public static boolean i$_ACT_LOAD_CARACTER_ARTICLE = false;
public static boolean i$_ACT_SELECT_ROW = false;




}
