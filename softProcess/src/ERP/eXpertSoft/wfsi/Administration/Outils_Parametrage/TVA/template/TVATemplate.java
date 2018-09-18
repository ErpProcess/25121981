package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.template;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class TVATemplate   extends   TemplateGeneric   {
public  TVATemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/TVA/root.action";
public static Object  NAME_MODEL_BEAN  = new TVABean();
public static Object  NAME_TEMPLATE    = new TVATemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "tva";
public static String  schema           = "administration";
public static String  id_entite        = "tva_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "TVA-List";
public static String  NAME_GRID         = "TVA-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "tva_abrv", "30" },{ "tva_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormTVA";
public static String FILTER_VIEW        = "FilterTVA";
public static String LIST_VIEW          = "ListTVA";
}
