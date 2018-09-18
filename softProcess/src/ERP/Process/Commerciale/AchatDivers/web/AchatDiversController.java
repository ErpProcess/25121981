package ERP.Process.Commerciale.AchatDivers.web;
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
import ERP.Process.Commerciale.AchatDivers.model.AchatDiversBean;
import ERP.Process.Commerciale.AchatDivers.template.AchatDiversTemplate;
@Controller
public class AchatDiversController  extends AchatDiversActionManager   {
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	   dateFormat.setLenient(false);
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(AchatDiversBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new AchatDiversTemplate());
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_LOAD_TOT)              return      doloadTOT();
		          
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
             }
     }
