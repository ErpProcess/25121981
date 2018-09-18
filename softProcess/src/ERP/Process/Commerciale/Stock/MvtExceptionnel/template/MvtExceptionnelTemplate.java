package ERP.Process.Commerciale.Stock.MvtExceptionnel.template;
import ERP.Process.Commerciale.Stock.MvtExceptionnel.model.MvtExceptionnelBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class MvtExceptionnelTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = -6828108481176735407L;
public  MvtExceptionnelTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/MvtExceptionnel/root.action";
public static Object  NAME_MODEL_BEAN  = new MvtExceptionnelBean();
public static Object  NAME_TEMPLATE    = new MvtExceptionnelTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "mvt_exceptionnel";
public static String  schema           = "stock";
public static String  id_entite        = "mvt_excep_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "MvtExceptionnel-List";
public static String  NAME_GRID         = "MvtExceptionnel-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "mvt_excep_date", "30" },{ "mode_op", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormMvtExceptionnel";
public static String FILTER_VIEW        = "FilterMvtExceptionnel";
public static String LIST_VIEW          = "ListMvtExceptionnel";
}
