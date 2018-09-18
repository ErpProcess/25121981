package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;





 

   public class TemplateWeb  extends   GenericActionBean           {
	 
	   public static  ModelAndView  getTimeOut() {
		   setObjectViewName(CONTEXT_PATH+"index");
		   return getModel();
	   }
	   
	 
	   
	 
	
	
	public static ModelAndView  getView(MenuActionBean twebBean) {
		setObjectValueModel(TEMP_MENU_ACTION_GEN, twebBean);
		return getModel();
	}
	
	
	 
	
	 
	
	 
	 
	
	
	
	
}
