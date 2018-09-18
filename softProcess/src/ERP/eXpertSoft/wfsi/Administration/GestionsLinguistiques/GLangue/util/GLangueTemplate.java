package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.util;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class GLangueTemplate   extends   TemplateGeneric   { 
	
	public GLangueTemplate() {
		super();
	}
	public static final  String ROOT    = "/ERP/eXpertSoft/wfsi/Administration/GestionsLinguistiques/GLangue/root.action";
	public static   String LIST_DATA        = "langueList";
	public static   String NAME_GRID        = "langueGrid";
	public static  String FORM_VIEW     = "FormLangue";
	public static  String FILTER_VIEW   = "FilterLangue";
	public static  String LIST_VIEW     = "ListLangue";
	public static   Object NAME_MODEL_BEAN  = new GLangueBean();
}
