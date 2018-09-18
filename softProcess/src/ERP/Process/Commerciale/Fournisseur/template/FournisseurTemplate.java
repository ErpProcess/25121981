package ERP.Process.Commerciale.Fournisseur.template;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class FournisseurTemplate   extends   TemplateGeneric   {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public  FournisseurTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Fournisseur/root.action";
public static Object  NAME_MODEL_BEAN  = new FournisseurBean();
public static Object  NAME_TEMPLATE    = new FournisseurTemplate();
public static final   String ID_SOUS_MODULE    ="62";
public static boolean is_Spoored       = true;
public static String  entites          = "fournisseur";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "frs_id";
public static String		LIST_GRP_TARIFI_PRTV		= "listGrpTarificationPrtvArticle";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Fournisseur-List";
public static String  NAME_GRID         = "Fournisseur-Grid";
public static String [][] MapfieldBean  = new String[][]{{"frs_id", "30" },{"frsref", "30" },{"frstel", "30" },{"bancod", "30" },{"frstyp", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormFournisseur";
public static String FILTER_VIEW        = "FilterFournisseur";
public static String LIST_VIEW          = "ListFournisseur";
}
