package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model.SmfonctionModel;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.template.SmfonctionTemplate;
@Controller
public class SmfonctionController  extends SmfonctionActionManager   {
  	 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(SmfonctionModel detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new SmfonctionTemplate());
                  if (i$_ACT_INIT_SERVLET )               return doInitServletAction();  
		          if (i$_ACT_AJAX_FETCH)                  return doFetchData(detailBean);
		          if (i$_ACT_SELECT_ROW)                  return doSelectRowSmfctmod(); 
		          if (i$_ACT_UPDATE_EDITABLE_TABLE_AJAX)  return doUpdateEditableTable();
		          if (i$_ACT_ADD)                         return doValider_AffectationData(detailBean);
		          if (i$_ACT_DELETE)                      return doDeleteData(detailBean);
	          return model;
             }
     }
