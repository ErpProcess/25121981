package ERP.Process.Commerciale.Demande_Achat.template;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Demande_Achat.model.Demande_achatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


 public class Demande_AchatTemplate   extends   TemplateGeneric   {
public  Demande_AchatTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Demande_Achat/root.action";
public static Object  NAME_MODEL_BEAN  = new Demande_achatBean();
public static Object  NAME_TEMPLATE    = new Demande_AchatTemplate();
public static final   String ID_SOUS_MODULE    ="57";
public static boolean is_Spoored       = true;
public static String  entites          = "demande_achat";
public static String  entite_det_dem_achat  = "det_demande_achat";
public static String  schema           = "gestioncommerciale";
public static String  id_entite        = "dem_achat_id";
public static String  []id_entite_detaille_demande    
= new String []{"pk_det_dem_achat.dem_achatBean.dem_achat_id",
	            "pk_det_dem_achat.fkCode_barre.pk.ar_bean.pk_article.ar_id", 
	            "pk_det_dem_achat.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id",
	            "pk_det_dem_achat.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id",
	            "pk_det_dem_achat.fkCode_barre.pk.code_barre"};



/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Demande_Achat-List";
public static String  NAME_GRID         = "Demande_Achat-Grid";
public static String [][] MapfieldBean  = new String[][]{{"dem_achat_id", "30" },
	                                                     {"dem_date", "30" },
	                                                     {"frsBean.frsref", "30" },
	                                                     {"modeBean.fct_libelle", "30" }};
public static String [][] Mapfield_det_achat  = new String[][]{
																{    "pk_det_dem_achat.fkCode_barre.pk.code_barre"   ,  "30"   }, 
																{    "pk_det_dem_achat.fkCode_barre.designation_libelle"   ,"30"  },       
																{    "quantite"      ,"10"     },   
																{    "observation"   ,"20"   }
                                                               };
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormDemande_Achat";
public static String FILTER_VIEW        = "FilterDemande_Achat";
public static String LIST_VIEW          = "ListDemande_Achat";
public static String FORM_VIEW_CONSULT  = "FormConsultDemande_Achat";
public static String LIST_DES_UNITES    = "list_des_unites";

public static String BON_COMMANDE_FRS   = " Bon de Commande Fournisseur ";

public static String MAP_FOURNISSEUR    = "map_fournisseur";



public static boolean i$_ACT_ADD_ROW = false;
public static boolean i$_ACT_SELECT_ROW = false;
public static boolean i$_ACT_VALIDER = false;


public static boolean i$_ACT_ANNULER = false;
public static boolean i$_ACT_ACTUALISER_LIST_CORR = false;
public static boolean i$_ACT_DELETE_ROW = false;
public static boolean i$_ACT_LOAD_TARIF = false;
public static boolean i$_ACT_INIT_SERVLET = false;




public static boolean i$_ACT_Cheked_unCheked = false;
public static boolean i$_ACT_LOAD_DETAILLE_CONSULT = false;



public static boolean i$_ACT_PRINT_PDF_detaille_demande_achat = false;

public static boolean i$_ACT_EXPORT_XLS_detaille_demande_achat = false;


public static ModelAndView getViewConsult(String isPage) {

	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setLibelleAction(BTN_SEARCH);
	twebBean.setBtValid(TRUE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
    String index=(String) getObjectValueModel(INDEX_ROW);
    List lis= (List) getObjectValueModel((String)getObjectValueModel(NAME_LIST_G));
    index=index==null?"0":index;
	if(index!=null && Integer.parseInt(index)==0){
	twebBean.setBtPrevious(TRUE);
	twebBean.setBtfirst(TRUE);
	twebBean.setBtNext(FALSE);
	twebBean.setBtLast(FALSE);
	}else if(index!=null &&  Integer.parseInt(index)==lis.size()-1){
		twebBean.setBtPrevious(FALSE);
		twebBean.setBtfirst(FALSE);
		twebBean.setBtNext(TRUE);
		twebBean.setBtLast(TRUE);
	}else{
		twebBean.setBtPrevious(FALSE);
		twebBean.setBtfirst(FALSE);
		twebBean.setBtNext(FALSE);
		twebBean.setBtLast(FALSE);
	}
	twebBean.setBtTrace(FALSE);
	twebBean.setBtAide(FALSE);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	twebBean.setIdReadonly(true);
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtRetour(FALSE);
	twebBean.setFctdoExcel("doExcel_detaille_demande_achat()");
	twebBean.setFctdoPdf("doPdf_detaille_demande_achat()");
	 
	twebBean.setBtExcel(FALSE);
	twebBean.setBtPdf(FALSE);
	
	return Get_Model_ROOT(isPage, twebBean);
}



}
