package ERP.Process.Commerciale.Stock.Stock_article.template;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class Stock_articleTemplate   extends   TemplateGeneric   {
 
	private static final long serialVersionUID = 6732951780683607040L;
public  Stock_articleTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/Stock_article/root.action";
public static final   String ID_SOUS_MODULE    ="152";
public static Object  NAME_MODEL_BEAN  = new Stock_articleBean();
public static Object  NAME_TEMPLATE    = new Stock_articleTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "stock_article";
public static String  schema           = "stock";
public static String  id_entite        = "date_stock";
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Stock_article-List";
public static String  NAME_GRID         = "Stock_article-Grid";
public static String [][] MapfieldBean  = new String[][]{
	{ "pk.date_stock", "30" },{"pk.fkCode_barre.pk.code_barre", "30" },
	{ "pk.fkCode_barre.designation_libelle", "30" },  
	{ "quantite_recept", "30" },{ "mnt_ttc_recept", "30" },
	
	{ "quantite_vendu", "30" },{ "mnt_ttc_vendu", "30" },
	{ "quantite_retour", "30" },{ "quantite_perte", "30" },
	{ "solde_stock", "30" } 
	};
 
 
/********************************************************************View Jsp********************************************************************/
public static String FORM_VIEW          = "FormStock_article";
public static String FILTER_VIEW        = "FilterStock_article";
public static String FILTER_VIEW_PAR_DATE        = "FilterStock_articleParDate";
 
public static String LIST_VIEW          = "ListStock_article";


public static String LIST_DEPOT_STOCK          = "list_depot_stock";
public static String LIST_ARTICLE_CODE_BARRE          = "list_article_code_barre";


public static String LIST_LOT          = "list_Lot";

public static boolean i$_ACT_INIT_SERVLET = false;
public static boolean i$_ACT_PRINT_PDF = false;
}
