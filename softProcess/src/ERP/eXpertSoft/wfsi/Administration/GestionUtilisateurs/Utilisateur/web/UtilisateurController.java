package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.web;

 
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

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.template.UtilisateurTemplate;

 

/**
 * Controller - Spring
 * 
 * @author Abdelmoula http://M2A-Process.com  
 *          
 */
@Controller
public class UtilisateurController  extends ActionUtilisateurManager   {
		/**
	 * 
	 */
	private static final long serialVersionUID = -5756589515553163465L;
		@InitBinder
		public void initBinder(WebDataBinder binder) {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		   dateFormat.setLenient(false);
		   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		}
	    @RequestMapping(value = ROOT)
		public ModelAndView doControlAction(UtilisateurBean detailBean,HttpServletRequest request,HttpServletResponse response  )
		throws  Throwable {
	    ModelAndView model=doInitGenericAction(request,response,new UtilisateurTemplate());
	       if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
	       if (i$_ACT_LOAD_ETAB )            return      doFetchDataEtablissement(detailBean); 
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
		   if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);    
		   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
		   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);      
		return model;
}
}
