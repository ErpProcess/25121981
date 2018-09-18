package ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.util.dataTableTemplate;

@Controller
public class dataTablesController extends ActionDataTablesManager {

 
   
	private static final long serialVersionUID = -7190290695798934023L;

	@RequestMapping(value = ROOT)
	public ModelAndView doDefineUrlAction(PackageSysBean detailBean,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException, Throwable, IllegalArgumentException,
			ServletException, NoSuchFieldException, IllegalAccessException {

		try{
			
		this.doInitParameterAajxComposant(httpRequest, httpResponse,new dataTableTemplate());
		
	 
		   /***************** version 1 - AUTOCOMPLETE_AJAX ********/
		   if (i$_ACT_LOAD_AUTOCOMPLETE_AJAX)           return       doLoadAutocompleteList(); 
		   
		   
		   if (i$_ACT_LOAD_SELECT)                      return       doLoadSelectList();  
		    
		   
		   
		   
		   
		   /***************** version 1 - LOAD_DATA_TABLE_AJAX ********/
		   if (i$_ACT_LOAD_DATA_TABLE_AJAX)            return       doLoadingDataTableAjax(); 
		   
		   
		   
		   if (i$_ACT_LOAD_DATA_BACK)                  return       doLoadingDataTablBAck(); 
		   
		   
		   /***************** version 1 - LOAD_EDITABLE_TABLE_AJAX ********/
		   if (i$_ACT_LOAD_EDITABLE_TABLE_AJAX)        return       doLoadingEditableDataTableAjax();
		   
		   
		   
		   /***************** version 1 ********/
		   if (i$_ACT_UPDATE_EDITABLE_TABLE_AJAX)      return       doUpdateEditableDataTableAjax(); 
		   
		   
		   
		   /*********************** version 2 ********************/
		   if (i$_ACT_UPDATE_EDITABLE)                 return       doUpdateEditableTable();

		   
		   
		   /*********************** version 3 ********************/
		  // if (i$_ACT_UPDATE_EDITABLE_COMPOSITE_ID)    return       doUpdateEditableTable_Composite_id();


		   
		   
		   if (i$_ACT_DELETE_ROW_EDITABLE_TABLE_AJAX)  return       doDelete_Row_EditableDataTableAjax();
		   
		   if (i$_ACT_ADD_ROW_EDITABLE_TABLE_AJAX)     return       doAdd_row_EditableDataTableAjax();
		   
		   
		   if (i$_ACT_VERIF_LIST)     return       doTeste_List();
		   
		 
		return null;
		
			} finally {
				destroyThreadLocal();
			}

	}

 

}
