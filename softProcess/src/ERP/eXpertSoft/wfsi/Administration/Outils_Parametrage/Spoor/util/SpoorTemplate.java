package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.util;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class SpoorTemplate   extends   TemplateGeneric   {
	
	public SpoorTemplate() {
		super();
	}
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Spoor/root.action";
	public static Object  NAME_MODEL_BEAN  = new SpoorBean();
	public static Object  NAME_TEMPLATE    = new SpoorTemplate();
	public static boolean is_Spoored       = true;
	public static String  entites          = "spoor";
	public static String  id_entite        = "sp_id";
	
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "SpoorList";
	public static String  NAME_GRID         = "SpoorGrid";
	public static String [][] MapfieldBean  = new String[][]{{"usr_id","30"},{"usr_nom","30"},{"usr_pre","30" },{"usr_mail","30"},{"usr_adr", "30" },{"usr_etat","30" }};
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormSpoor";
	public static String FILTER_VIEW        = "FilterSpoor";
	public static String LIST_VIEW          = "ListSpoor";
	
	/********************************************************************Action Entite***************************************************************/
	public static boolean i$_ACT_AJAX_FETCH_PAR_MODULE    = false;
	public static boolean i$_ACT_AJAX_GET_DONNE_FORMULAIRE    = false;
	
}
