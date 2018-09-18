package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.NumSequentielBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.template.NumSequentielTemplate;
 

/**
 * Controller - Spring
 * 
 * @author Abdelmoula http://M2A-Process.com  
 *          
 */
@Controller
public class NumSequentielController  extends ActionNumSequentielManager   {

	    @RequestMapping(value = ROOT)
		public ModelAndView doControlAction(NumSequentielBean detailBean,HttpServletRequest request,HttpServletResponse response  )
		throws  Throwable {
	    ModelAndView model=doInitGenericAction(request,response,new NumSequentielTemplate());
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
		   if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);    
		   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
		   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);      
		return model;
}
}
