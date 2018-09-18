package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;

 


public class WebRootFolderTemplate   extends   TemplateGeneric   { 
	
	public WebRootFolderTemplate() {
		super();
	}
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/RessourceSysteme/WebRootFolder/root.action";
	public static Object  NAME_MODEL_BEAN  = new WebRootFolderBean();
	public static Object  NAME_TEMPLATE    = new WebRootFolderTemplate();
	public static boolean is_Spoored       = false;
	public static String  entites          = "WebRootFolder";
	public static String  schema           = "Administration";
	public static String  id_entite        = "usr_id";
	
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "WebRootFolder-List";
	public static String  NAME_GRID         = "Utilisateur-Grid";
	public static String [][] MapfieldBean  = new String[][]{{"usr_id","30"},{"usr_nom","30"},{"usr_pre","30" },{"usr_mail","30"},{"usr_adr", "30" },{"usr_etat","30" }};
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormWebRootFolder";
	public static String FILTER_VIEW        = "FilterWebRootFolder";
	public static String LIST_VIEW          = "ListWebRootFolder";
	
	
	/********************************************************************Action Entite***************************************************************/
	public static boolean i$_ACT_INIT_SERVLET = false;
	public static boolean i$_ACT_LOAD_ETAB    = false;
	public static boolean i$_ACT_LOAD_TAB_SCHEM    = false;
	public static boolean i$_ACT_PREPARER_ENTITE   = false;
	public static boolean i$_ACT_UPDATE_EDITABLE_TABLE   = false;
	
	
	
 
}
