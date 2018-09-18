package ERP.Process.Commerciale.Entite_etat_commerciale.template;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Entite_etat_commercialeTemplate   extends   TemplateGeneric   {
public  Entite_etat_commercialeTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Entite_etat_commerciale/root.action";
public static Object  NAME_MODEL_BEAN  = new Entite_etat_commercialeBean();
public static Object  NAME_TEMPLATE    = new Entite_etat_commercialeTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "entite_etat_commerciale";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "";
public static String  []id_entite_Tab     = new String []{"data_id"};
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "List-Entite_etat_commerciale";
public static String  NAME_GRID         = "Grid-Entite_etat_commerciale";
public static String[][] MAP_CRITERE_DE_RECHERCHE = new String[][] { { "code_entite", "1-30" },{ "libelle_entite", "1-30" },   { "data_id", "2-30" } };
public static String [][] MapfieldBean  = new String[][]{{"code_entite", "30" },{"libelle_entite", "30" },{"data_id", "30" },{"data_libelle", "30" },{"data_ordre", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormEntite_etat_commerciale";
public static String FILTER_VIEW        = "FilterEntite_etat_commerciale";
public static String LIST_VIEW          = "ListEntite_etat_commerciale";

public static String FORM_CONSULT_VIEW  = "FormConsultEntite_etat_commerciale";


public static String  LIST_DETAIL_ENTITE_FOR_CREE                 = "list_detail_entite_for_cree";
public static String  LIST_DETAIL_ENTITE_FOR_CREE_TO_SUPP         = "list_detail_entite_for_cree_toSupp";

public static boolean i$_ACT_ADD_ROW = false;
public static boolean i$_ACT_DELETE_ROW = false;
public static boolean i$_ACT_Cheked_unCheked = false;
public static boolean i$_ACT_SELECT_ROW = false;



}
