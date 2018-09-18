package ERP.Process.Commerciale.Vente.Livraison.template;
import ERP.Process.Commerciale.Vente.Livraison.model.LivraisonBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class LivraisonTemplate   extends   TemplateGeneric   {
/**
	 * 
	 */
	private static final long serialVersionUID = -5715091924784274356L;
public  LivraisonTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Vente/Livraison/root.action";
public static Object  NAME_MODEL_BEAN  = new LivraisonBean();
public static Object  NAME_TEMPLATE    = new LivraisonTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "livraison_commande";
public static String  schema           = "vente";
public static String  id_entite        = "liv_id";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Livraison-List";
public static String  NAME_GRID         = "Livraison-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "liv_id", "30" },
	{ "liv_libelle", "30" },{ "vente.vente_id", "30" },{ "liv_date", "30" } , { "montant_livraison", "30" } ,{ "trans.trans_libelle", "30" }
	   };


 


/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormLivraison";
public static String FILTER_VIEW        = "FilterLivraison";
public static String LIST_VIEW          = "ListLivraison";


public static String LIST_TRANSPORT_LIV          = "list_transport_liv";
public static String LIST_VENTE_A_LIVRER          = "list_vente_a_livrer";



}
