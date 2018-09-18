package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.template;


import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class FonctionTemplate   extends   TemplateGeneric   { 
	
	public FonctionTemplate() {
		super();
	}
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/GestionDesMenus/Fonction/root.action";
	public static Object  NAME_MODEL_BEAN  = new FonctionBean();
	public static Object  NAME_TEMPLATE    = new FonctionTemplate();
	public static boolean is_Spoored       = true;
	public static String  entites          = "fonction";
	public static String  schema           = "Administration";
	public static String  id_entite        = "fct_id";
	
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "Fonction-List";
	public static String  NAME_GRID         = "Fonction-Grid";
	public static String [][] MapfieldBean  = new String[][]{{"fct_id","30"},{"fct_libelle","30"},{"fct_obs","30" } };
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormFonction";
	public static String FILTER_VIEW        = "FilterFonction";
	public static String LIST_VIEW          = "ListFonction";
	/********************************************************************Action Entite***************************************************************/
}
