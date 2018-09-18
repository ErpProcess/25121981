package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.template;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class UtilisateurTemplate   extends   TemplateGeneric   { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6252764024124181889L;
	public UtilisateurTemplate() {
		super();
	}
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/GestionUtilisateurs/Utilisateur/root.action";
	public static Object  NAME_MODEL_BEAN  = new UtilisateurBean();
	public static Object  NAME_TEMPLATE    = new UtilisateurTemplate();
	public static boolean is_Spoored       = true;
	public static String  entites          = "utilisateur";
	public static String  schema           = "Administration";
	public static String  id_entite        = "usr_id";
	
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "Utilisateur-List";
	public static String  NAME_GRID         = "Utilisateur-Grid";
	public static String [][] MapfieldBean  = new String[][]{{"usr_id","30"},{"usr_nom","30"},{"usr_pre","30" },{"usr_mail","30"},{"usr_adr", "30" },{"usr_etat","30" }};
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormUtilisateur";
	public static String FILTER_VIEW        = "FilterUtilisateur";
	public static String LIST_VIEW          = "ListUtilisateur";
	
	
	/********************************************************************Action Entite***************************************************************/
	public static boolean i$_ACT_INIT_SERVLET = false;
	public static boolean i$_ACT_LOAD_ETAB    = false;
 
}
