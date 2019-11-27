package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TimeOut.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
 
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.web.ActionAuthentificationManager;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.MessageBean;

 




@Controller
public class TimeOutController extends ActionTimeOutManager    {

	
	
	    @RequestMapping(value = ROOT )
	    @ExceptionHandler(Exception.class)
		public ModelAndView doDefineUrlAction(SpoorBean detailBean , HttpServletRequest httpRequest, HttpServletResponse httpResponse)
		throws ServletException,IOException, Throwable {
		   
		 ModelAndView mode= new ModelAndView(); 
		 MessageBean error       = new MessageBean();
		 error.setMessage("sessionOufet");
		 mode.addObject(MESSAGERROR, "sessionOufet");
		     mode.setViewName(getRequest().getContextPath()+ActionAuthentificationManager.PATH_SLACH+"index");
		     return mode;

	}

	

}
