package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.template;


import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class SousModuleTemplate   extends   TemplateGeneric   { 
	
	public SousModuleTemplate() {
		super();
	}
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/GestionDesMenus/SousModule/root.action";
	public static Object  NAME_MODEL_BEAN  = new SousModuleBean();
	public static Object  NAME_TEMPLATE    = new SousModuleTemplate();
	public static boolean is_Spoored       = true;
	public static String  entites          = "sousmodule";
	public static String  schema           = "Administration";
	public static String  id_entite        = "sousmod_id";
	
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "SousModule-List";
	public static String  NAME_GRID         = "SousModule-Grid";
	public static String [][] MapfieldBean  = new String[][]{{"sousmod_id","30"},{"sousmod_libelle","30"},{"sousmod_action","30" } };
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormSousModule";
	public static String FILTER_VIEW        = "FilterSousModule";
	public static String LIST_VIEW          = "ListSousModule";
	
	
	/********************************************************************Action Entite***************************************************************/
	public static boolean i$_ACT_INIT_SERVLET = false;
}
