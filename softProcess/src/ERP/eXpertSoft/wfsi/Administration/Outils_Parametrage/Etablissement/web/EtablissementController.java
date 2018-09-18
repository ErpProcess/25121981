package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.template.EtablissementTemplate;

 

/**
 * Controller - Spring
 * 
 * @author Abdelmoula http://M2A-Process.com  
 *          
 */
@Controller
public class EtablissementController  extends ActionEtablissementManager   {

	 @RequestMapping(value = ROOT)
		public ModelAndView doControlAction(EtablissementBean detailBean,HttpServletRequest request,HttpServletResponse response  )
		throws  Throwable {
	    ModelAndView model=doInitGenericAction(request,response,new EtablissementTemplate());
	       if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();  
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
		   if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);    
		   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
		   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);      
		return model;
}
}
