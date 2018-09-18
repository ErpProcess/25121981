package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.template;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.NumSequentielBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class NumSequentielTemplate   extends   TemplateGeneric   { 
	
	public NumSequentielTemplate() {
		super();
	}
	
	public static  final  String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/NumSequentiel/root.action";
	public static  String LIST_DATA        = "UtilisateurList";
	public static  String NAME_GRID        = "UtilisateurGrid";
	public static  String FORM_VIEW        = "FormUtilisateur";
	public static  String FILTER_VIEW      = "FilterUtilisateur";
	public static  String LIST_VIEW        = "ListUtilisateur";
	public static  Object NAME_MODEL_BEAN  = new NumSequentielBean();
}
