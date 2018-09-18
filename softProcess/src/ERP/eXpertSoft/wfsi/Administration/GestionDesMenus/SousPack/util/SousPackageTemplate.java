package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.util;

 
 
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;


public class SousPackageTemplate   extends   TemplateGeneric   {
	
	                                        
	
	/*******************************************************************Config Entite*****************************************************************/
	public static final   String ROOT      = "/ERP/eXpertSoft/wfsi/Administration/GestionDesMenus/SousPack/root.action";
	public static Object  NAME_MODEL_BEAN  = new  SousPackageBean();
	public static Object  NAME_TEMPLATE    = new SousPackageTemplate();
	public static boolean is_Spoored       = true;
	public static String  entites          = "souspackage";
	public static String  schema           = "Administration";
	public static String  id_entite        = "spack_id";
	
	
	/********************************************************************Data Grid********************************************************************/
	public static String  LIST_DATA         = "SousPack-List";
	public static String  NAME_GRID         = "SousPack-Grid";
	
	
	 
	
	
	public static String [][] MapfieldBean  = new String[][]{{"packageSys.pack_libelle","30"},{"spack_id","30"},{"spack_libelle","30" },{"spack_ordre","30" }};
	                                        
	/********************************************************************View Jsp********************************************************************/
	public static String FORM_VIEW          = "FormSousPackageSys";
	public static String FILTER_VIEW        = "FilterSousPackageSys";
	public static String LIST_VIEW          = "ListSousPackageSys";
	/********************************************************************Action Entite***************************************************************/
	 
}
