package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.util;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class GLibelleTemplate   extends   TemplateGeneric   {
	
	public static final  String ROOT    = "/ERP/eXpertSoft/wfsi/Administration/GestionsLinguistiques/Glibelle/root.action";
	public static   String PAGE_FORM_Update = "FormUpdateLibelle";
	
	public static  String FORM_VIEW     = "FormLibelle";
	public static  String FILTER_VIEW   = "FilterLibelle";
	public static  String LIST_VIEW     = "ListLibelle";
	
	public static   String LIST_DATA        = "libelleList";
	public static   String NAME_GRID        = "libelleGrid";
	public static   Object NAME_MODEL_BEAN  = new GlibelleBean();
	public static boolean i$_ACT_INIT_SERVLET=false;
	public static boolean i$_ACT_ADD_ROW_EDITABLE_TABLE_AJAX=false;
	
	public static boolean i$_ACT_UPDATE_GRID=false;
	
	
}
