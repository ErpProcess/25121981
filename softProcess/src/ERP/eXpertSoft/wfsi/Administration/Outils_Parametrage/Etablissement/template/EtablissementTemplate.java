package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.template;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.template.SocieteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class EtablissementTemplate   extends   TemplateGeneric   { 
	
	public EtablissementTemplate() {
		super();
	}
	public static final  String ROOT     = "/ERP/eXpertSoft/wfsi/Administration/Outils_Parametrage/Etablissement/root.action";
	public static   String LIST_DATA     = "EtablissementList";
	public static   String NAME_GRID     = "EtablissementGrid";
	public static   String FORM_VIEW     = "FormEtablissement";
	public static   String FILTER_VIEW   = "FilterEtablissement";
	public static   String LIST_VIEW     = "ListEtablissement";
	public static boolean i$_ACT_INIT_SERVLET = false;
	public static   Object NAME_MODEL_BEAN  = new EtablissementBean();
	
	public static boolean  isSpoored = true;
	public static String   idEntite = "etab_id";
	public static   Object NAME_TEMPLATE  = new EtablissementTemplate();
}
