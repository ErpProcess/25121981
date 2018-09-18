package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.template;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model.PaysvilleposteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class PaysvilleposteTemplate   extends   TemplateGeneric   { 
	
	public PaysvilleposteTemplate() {
		super();
	}
	
	public static  final  String ROOT      = "ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Paysvilleposte/root.action";
	
 
	public static  String LIST_DATA        = "";
	public static  String NAME_GRID        = "";
	public static  String FORM_VIEW        = "";
	public static  String FILTER_VIEW      = "FilterUtilisateur";
	public static  String LIST_VIEW        = "ListData_entite_simple";
	public static  Object NAME_MODEL_BEAN  = new PaysvilleposteBean();
}
