package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.util;

 
 
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class PackageSysTemplate   extends   TemplateGeneric   {
	
	                                        
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/GestionDesMenus/PackageSys/root.action";
	public static Object  NAME_MODEL_BEAN  = new PackageSysBean();
	public static Object  NAME_TEMPLATE    = new PackageSysTemplate();
	public static boolean is_Spoored       = true;
	public static String  entites          = "package";
	public static String  schema           = "Administration";
	public static String  id_entite        = "pack_id";
	
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "PackageSys-List";
	public static String  NAME_GRID         = "PackageSys-Grid";
	public static String [][] MapfieldBean  = new String[][]{{"pack_id","30"},{"pack_libelle","30"},{"pack_ordre","30" }};
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormPackageSys";
	public static String FILTER_VIEW        = "FilterPackageSys";
	public static String LIST_VIEW          = "ListPackageSys";
	
	
	/********************************************************************Action Entite***************************************************************/
	 
}
