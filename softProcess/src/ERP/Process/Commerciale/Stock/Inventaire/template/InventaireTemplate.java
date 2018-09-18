package ERP.Process.Commerciale.Stock.Inventaire.template;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.Inventaire.model.InventaireBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;
 public class InventaireTemplate   extends   TemplateGeneric   {
public  InventaireTemplate() {
	super();
}
/*******************************************************************Config Entite*****************************************************************/
public static final   String ROOT      = "/ERP/Process/Commerciale/Stock/Inventaire/root.action";
public static Object  NAME_MODEL_BEAN  = new InventaireBean();
public static Object  NAME_TEMPLATE    = new InventaireTemplate();
public static boolean is_Spoored       = true;
public static String  entites          = "inventaire";
public static String  schema           = "stock";
public static String  id_entite        = "inv_id";
public static String[]id_entite_composite     = new String[] { "pk.inv_id","pk.inv_date","pk.depot_stocks.depot_id"   };


public static String   entites_detaille = "det_inventaire";
public static String[] id_entite_detail = new String[] { "pk.inventaire.pk.inv_id" ,"pk.inventaire.pk.depot_stocks.depot_id","pk.fkCode_barre.pk.code_barre" };
 
/********************************************************************Data Grid********************************************************************/ 
public static String  LIST_DATA         = "Inventaire-List";
public static String  NAME_GRID         = "Inventaire-Grid";
public static String [][] MapfieldBean  = new String[][]{{ "pk.inv_id", "30" },{ "pk.inv_date", "30" },{ "typeInventaire.type_inv_id", "30" },{ "pk.depot_stocks.depot_libelle", "30" }};
/********************************************************************View Jsp********************************************************************/


public static String [][] MapfieldBean_detaille  = 
	new String[][]{
	          { "pk.fkCode_barre.pk.code_barre", "30" }, 
	          { "pk.fkCode_barre.designation_libelle", "100" },
	          { "pk.quantite", "20" },
	          { "prix_unit_achat", "30" },
	          { "pk.montant_ht", "50" }
	          };




public static String FORM_VIEW          = "FormInventaire";
public static String FILTER_VIEW        = "FilterInventaire";
public static String LIST_VIEW          = "ListInventaire";


public static String LIST_DEPOT_STOCK          = "list_depot_stock";
public static String LIST_TYPEINVENTAIRE          = "list_TypeInventaire";
public static String   LIST_DATA_FOR_INVENTAIRE="list_data_for_inventaire";



public static boolean i$_ACT_UPDATE_COLONNE_TABLE = false;
public static boolean i$_ACT_LOAD_LOT_ARTICLE = false;
public static boolean i$_ACT_ACTUALISER_TABLE = false;


public static boolean i$_ACT_SELECT_ROW = false;

public static boolean i$_ACT_INIT_SERVLET = false;
}
