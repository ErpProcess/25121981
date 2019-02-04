package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.template.SocieteTemplate;
 

/**
 * Controller - Spring
 * 
 * @author Abdelmoula http://M2A-Process.com  
 *          
 */
@Controller
public class SocieteController  extends ActionSocieteManager   {

	 
	private static final long serialVersionUID = -4738351670811933666L;

	@RequestMapping(value = ROOT)
		public ModelAndView doControlAction(SocieteBean detailBean,HttpServletRequest request,HttpServletResponse response  )
		throws  Throwable {
	    ModelAndView model=doInitGenericAction(request,response,new SocieteTemplate());
	       if (i$_ACT_INIT_SERVLET)          return      doInitServletAction(); 
	       if (i$_ACT_UPLOADER)              return      uploadFile();
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);   
		   if (i$_ACT_SELECT_ROW)            return      doGetRowDataBean();             
		  
		   
		   if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);    
		   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
		   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);      
		return model;
}
}
