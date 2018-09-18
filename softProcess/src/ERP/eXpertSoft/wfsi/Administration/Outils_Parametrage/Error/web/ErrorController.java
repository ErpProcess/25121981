package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.model.ErrorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.util.ErrorTemplate;

 



@Controller
public class ErrorController extends ActionErrorManager    {

	
	
	    @RequestMapping(value = ROOT)
	    @ExceptionHandler(Exception.class)
		public ModelAndView doDefineUrlAction(ErrorBean detailBean , HttpServletRequest httpRequest, HttpServletResponse httpResponse)
		throws ServletException,IOException, Throwable {

		this.doInitGenericAction(httpRequest, httpResponse,  new ErrorTemplate() );
		 
		
		return null;

	}

	

}
