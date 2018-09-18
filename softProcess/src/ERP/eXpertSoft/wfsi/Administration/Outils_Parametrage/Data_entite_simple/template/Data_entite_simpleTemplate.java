package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.template;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;



public class Data_entite_simpleTemplate   extends   TemplateGeneric   { 
	
	public Data_entite_simpleTemplate() {
		super();
	}
	
	public static  final  String ROOT      = "ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Data_entite_simple/root.action";
	
 
	public static  String LIST_DATA        = "";
	public static  String NAME_GRID        = "";
	public static  String FORM_VIEW        = "";
	public static  String FILTER_VIEW      = "FilterUtilisateur";
	public static  String LIST_VIEW        = "ListData_entite_simple";
	public static  Object NAME_MODEL_BEAN  = new Data_entite_simpleBean();
}
