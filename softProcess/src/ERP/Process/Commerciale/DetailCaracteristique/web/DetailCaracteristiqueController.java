package ERP.Process.Commerciale.DetailCaracteristique.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
import ERP.Process.Commerciale.DetailCaracteristique.template.DetailCaracteristiqueTemplate;
@Controller
public class DetailCaracteristiqueController  extends DetailCaracteristiqueActionManager   {
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(DetailCaracteristiqueBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new DetailCaracteristiqueTemplate());
           
                  if (i$_ACT_INIT_SERVLET)          return      doInitServletAction();
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
             }
     }
