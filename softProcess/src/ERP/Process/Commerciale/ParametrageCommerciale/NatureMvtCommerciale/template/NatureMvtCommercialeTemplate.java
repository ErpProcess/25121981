package ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.template;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class NatureMvtCommercialeTemplate   extends   TemplateGeneric   {
public  NatureMvtCommercialeTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/ParametrageCommerciale/NatureMvtCommerciale/root.action";
public static Object  NAME_MODEL_BEAN  = new NatureMvtCommercialeBean();
public static Object  NAME_TEMPLATE    = new NatureMvtCommercialeTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "nature_mvt_commerciale";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "nature_mvt_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "NatureMvtCommerciale-List";
public static String  NAME_GRID         = "NatureMvtCommerciale-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "nature_mvt_id", "30" },{ "nature_mvt_libelle", "30" },{ "nature_mvt_ordre", "30" },{ "nature_operation", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormNatureMvtCommerciale";
public static String FILTER_VIEW        = "FilterNatureMvtCommerciale";
public static String LIST_VIEW          = "ListNatureMvtCommerciale";
}
