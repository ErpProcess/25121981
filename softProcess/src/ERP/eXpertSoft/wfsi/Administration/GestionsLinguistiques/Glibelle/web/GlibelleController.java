package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.util.GLibelleTemplate;




@Controller
public class GlibelleController extends ActionGlibelleManager     {

	
	
	 
	
	@RequestMapping(value = ROOT)
	
	public ModelAndView doDefineUrlAction(GlibelleBean detailBean , HttpServletRequest httpRequest, HttpServletResponse httpResponse,HttpSession  httpSession)
	throws IOException, Throwable {

		 ModelAndView model= doInitGenericAction(httpRequest, httpResponse,new GLibelleTemplate());
	   if (i$_ACT_INIT_SERVLET)          return      doInitServletAction();   
	   if (i$_ACT_SELECT_ROW)            return      doSelectRow();   
	   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
	   if (i$_ACT_AJAX_FETCH)            return      doFetchDataAjax(detailBean);    
	   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
	   if (i$_ACT_DELETE)                return      doDeleteData(detailBean); 
	   
	   if (i$_ACT_UPDATE_GRID)                return      doupdate();
	   
	   
	   
	   if (i$_ACT_ADD_ROW_EDITABLE_TABLE_AJAX)                return      doAdd_row_editable_table_ajax();          
	   
	return model;

}

	

}
