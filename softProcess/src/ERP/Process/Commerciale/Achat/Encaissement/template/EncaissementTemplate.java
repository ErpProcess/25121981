package ERP.Process.Commerciale.Achat.Encaissement.template;
import ERP.Process.Commerciale.Achat.Regachat.model.RegachatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class EncaissementTemplate   extends   TemplateGeneric   {
public  EncaissementTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Achat/Encaissement/root.action";
public static Object  NAME_MODEL_BEAN  = new RegachatBean();
public static Object  NAME_TEMPLATE    = new EncaissementTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "reglement_fact_frs";
public static String  schema           = "achat";
public static String  id_entite        = "reg_frs_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Encaissement-List";
public static String  NAME_GRID         = "Encaissement-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "reg_frs_id", "30" },{ "fact_frs_id", "30" },{ "reg_mod", "30" },{ "reg_date", "30" },{ "reg_nbr_echeance", "30" },{ "montant_facture", "30" },{ "montant_avance", "30" },{ "time_cre", "30" },{ "num_piece", "30" },{ "reg_nature", "30" },{ "reg_type", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormEncaissement";
public static String FILTER_VIEW        = "FilterEncaissement";
public static String LIST_VIEW          = "ListEncaissement";
}
