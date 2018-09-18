package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.template.DeviseTemplate;
@Controller
public class DeviseController  extends DeviseActionManager   {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2342569882520842072L;

	@RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(DeviseBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new DeviseTemplate());
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
             }
     }
