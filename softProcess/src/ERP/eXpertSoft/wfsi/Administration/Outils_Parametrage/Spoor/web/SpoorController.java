package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
 
import org.springframework.web.servlet.ModelAndView;

 

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.util.SpoorTemplate;



@Controller
public class SpoorController extends ActionSpoorManager    {

	
	
	    @RequestMapping(value = ROOT )
		public ModelAndView doDefineUrlAction(SpoorBean detailBean , HttpServletRequest httpRequest, HttpServletResponse httpResponse)
		throws ServletException,IOException, Throwable {

		this.doInitGenericAction(httpRequest, httpResponse,  new SpoorTemplate() );
		
		   
		   if (i$_ACT_AJAX_GET_DONNE_FORMULAIRE ) return     doFetchdata_formulaire_TraceParModule();   
		
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
		
		   if (i$_ACT_AJAX_FETCH)            return      doFetchDataAjax(detailBean);   
		   
		   if (i$_ACT_AJAX_FETCH_PAR_MODULE) return      doFetchTraceParModule(detailBean);   
		
		   
		   
		 
		
		return null;

	}

	

}
