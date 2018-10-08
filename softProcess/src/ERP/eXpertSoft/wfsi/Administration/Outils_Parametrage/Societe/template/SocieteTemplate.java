package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.template;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class SocieteTemplate   extends   TemplateGeneric   { 
	
	public SocieteTemplate() {
		super();
	}
	public static final  String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Societe/root.action";
	public static   String LIST_DATA      = "SocieteList";
	public static   String NAME_GRID      = "SocieteGrid";
	public static   String FORM_VIEW      = "FormSociete";
	public static   String FILTER_VIEW    = "FilterSociete";
	public static   String LIST_VIEW      = "ListSociete";
	public static   String idEntite       = "soc_id";
	public static   Object NAME_TEMPLATE  = new SocieteTemplate();
	public static   Object NAME_MODEL_BEAN= new SocieteBean();
	
	/******************************************************************************************************************************/
	public static boolean  isSpoored = true;
	public static boolean  i$_ACT_INIT_SERVLET = false;
	public static boolean i$_ACT_SELECT_ROW = false;
}
