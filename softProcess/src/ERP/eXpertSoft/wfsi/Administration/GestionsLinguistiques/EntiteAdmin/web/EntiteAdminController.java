package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.util.EntiteAdminTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.util.GLibelleTemplate;




@Controller
public class EntiteAdminController extends ActionEntiteAdminManager     {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5023502952108162934L;

	@RequestMapping(value = ROOT)
	
	public ModelAndView doDefineUrlAction(EntiteAdminBean detailBean , HttpServletRequest httpRequest, HttpServletResponse httpResponse)
	throws IOException, Throwable {
		 ModelAndView model= doInitGenericAction(httpRequest, httpResponse,new EntiteAdminTemplate());
		 /******************************************************************************************************************/
	   if (i$_ACT_INIT_SERVLET)	                 return      doInitServletAction(); 
	   if (i$_ACT_LOAD_SOUSPACK)	             return      doFetchDataSousPackage(); 
	   if (i$_ACT_LOAD_MODULE)	                 return      doFetchDataModule();
	   if (i$_ACT_LOAD_SOUS_MODULE)	             return      doFetchDataSOUSModule();
	   if (i$_ACT_PREPARER_LIB_ENTITE)	         return      doPreparerEntiteToInsert(detailBean);
	   if (i$_ACT_LOAD_TAB_SCHEM)	             return      getTAbleAnd_Schema(); 
	   if (i$_ACT_UPDATE_EDITABLE_TABLE_AJAX)	 return      doUpdateEditableDataTableAjax();
       if (i$_ACT_RESET_FORM)                    return      doResetForm(); 
       if (i$_ACT_GET_ID_COLUNMS)                return      doLoadColumnsFromEntite(); 
	   
	   /******************************************************************************************************************/
	   
	   if (i$_ACT_ADD)                   		return      doAddData(detailBean); 
	   if (i$_ACT_ADD_WIDTH_LIST)               return      doAddData_With_List(detailBean); 
	   
	   if (i$_ACT_AJAX_FETCH)            		return      doFetchDataAjax(detailBean);
	   if (i$_ACT_SELECT_ROW)                   return      doSelectRow(); 
	   if (i$_ACT_UPDATE)                		return      doUpdateData(detailBean);       
	   if (i$_ACT_DELETE)                		return      doDeleteData(detailBean); 
	return model;

}

	

}
