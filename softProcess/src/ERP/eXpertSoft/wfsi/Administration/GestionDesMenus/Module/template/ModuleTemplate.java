package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.template;


import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class ModuleTemplate   extends   TemplateGeneric   { 
	
	public ModuleTemplate() {
		super();
	}
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/GestionDesMenus/Module/root.action";
	public static Object  NAME_MODEL_BEAN  = new ModuleBean();
	public static Object  NAME_TEMPLATE    = new ModuleTemplate();
	public static boolean is_Spoored       = true;
	public static String  entites          = "Module";
	public static String  schema           = "Administration";
	public static String  id_entite        = "mod_id";
	
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "Module-List";
	public static String  NAME_GRID         = "Module-Grid";
	public static String [][] MapfieldBean  = new String[][]{{"mod_id","30"},{"mod_libelle","30"},{"mod_obs","30" } };
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormModule";
	public static String FILTER_VIEW        = "FilterModule";
	public static String LIST_VIEW          = "ListModule";
	
	
	/********************************************************************Action Entite***************************************************************/
	public static boolean i$_ACT_INIT_SERVLET = false;
}
