package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.template;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.CompteBancaire.model.CompteBancaireBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class CompteBancaireTemplate   extends   TemplateGeneric   {
public  CompteBancaireTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/

public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/RessourceSysteme/CompteBancaire/root.action";


public static Object  NAME_MODEL_BEAN  = new CompteBancaireBean();
public static Object  NAME_TEMPLATE    = new CompteBancaireTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "compte_bancaire";
public static String  schema           = "administration";
public static String  id_entite        = "cptbanribrib";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "CompteBancaire-List";
public static String  NAME_GRID         = "CompteBancaire-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "cptbanribrib", "30" },{ "cptbanribrs", "30" },{ "cptbanadr", "30" },{ "bancod", "30" },{ "cptbanjoucod", "30" },{ "cptbancptcom", "30" }};
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormCompteBancaire";
public static String FILTER_VIEW        = "FilterCompteBancaire";
public static String LIST_VIEW          = "ListCompteBancaire";
}
