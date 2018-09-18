package ERP.Process.Commerciale.GrpTarifPrimitiv.template;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class GrpTarifPrimitivTemplate   extends   TemplateGeneric   {
public  GrpTarifPrimitivTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/GrpTarifPrimitiv/root.action";
public static Object  NAME_MODEL_BEAN  = new GrpTarifPrimitivBean();
public static Object  NAME_TEMPLATE    = new GrpTarifPrimitivTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "grp_tarif_primitiv";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "grp_prim_trf_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "GrpTarifPrimitiv-List";
public static String  NAME_GRID         = "GrpTarifPrimitiv-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "grp_prim_trf_id", "30" },{ "grp_trf_lib", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormGrpTarifPrimitiv";
public static String FILTER_VIEW        = "FilterGrpTarifPrimitiv";
public static String LIST_VIEW          = "ListGrpTarifPrimitiv";
}
