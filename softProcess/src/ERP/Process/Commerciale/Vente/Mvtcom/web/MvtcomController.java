package ERP.Process.Commerciale.Vente.Mvtcom.web;
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
import ERP.Process.Commerciale.Vente.Mvtcom.model.MvtcomBean;
import ERP.Process.Commerciale.Vente.Mvtcom.template.MvtcomTemplate;
@Controller
public class MvtcomController  extends MvtcomActionManager   {
  	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(MvtcomBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
  	    try { 
           ModelAndView model=doInitGenericAction(request,response,new MvtcomTemplate());
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_SELECT_ROW)            return      doGetRowMvt();
		          if (i$_ACT_LOAD_FILE_BASE64)      return      loadFileMvt();
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
  	   } finally { 
  	      destroyThreadLocal(); 
        }
    }
  }
