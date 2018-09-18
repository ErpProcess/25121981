package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.web;

 
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

import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.WebRootFolderTemplate;


@Controller
public class WebRootFolderController  extends ActionWebRootFolderManager   {
	 
	private static final long serialVersionUID = -5378953436206101169L;
		@InitBinder
		public void initBinder(WebDataBinder binder) {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   dateFormat.setLenient(false);
		   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		}
	    @RequestMapping(value = ROOT)
		public ModelAndView doControlAction(WebRootFolderBean detailBean,HttpServletRequest request,HttpServletResponse response  )
		throws  Throwable {
	    ModelAndView model=doInitGenericAction(request,response,new WebRootFolderTemplate());
	       if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
	       if (i$_ACT_LOAD_TAB_SCHEM)	     return      getTAbleAnd_Schema(); 
		   if (i$_ACT_PREPARER_ENTITE)	     return      doPreparerEntite(detailBean);
		   if (i$_ACT_UPDATE_EDITABLE_TABLE) return      doUpdateEditableTable();
		   if (i$_ACT_ADD)                   return      doAddData_Files(detailBean);            
		return model;
}
}
