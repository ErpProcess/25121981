package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.web;

 
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.template.ModuleTemplate;

 

 

/**
 * Controller - Spring
 * 
 * @author Abdelmoula http://M2A-Process.com  
 *          
 */
@Controller
public class ModuleController  extends ActionModuleManager   {
		@InitBinder
		public void initBinder(WebDataBinder binder) {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   dateFormat.setLenient(false);
		   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		}
	    @RequestMapping(value = ROOT)
		public ModelAndView doControlAction(ModuleBean detailBean,HttpServletRequest request,HttpServletResponse response  )
		throws  Throwable {
	    ModelAndView model=doInitGenericAction(request,response,new ModuleTemplate());
	       if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
		   if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);    
		   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
		   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);      
		return model;
}
}
