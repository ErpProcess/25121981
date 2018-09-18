package ERP.Process.Commerciale.Degre_definition.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Degre_definition.model.Degre_definitionBean;
import ERP.Process.Commerciale.Degre_definition.template.Degre_definitionTemplate;
@Controller
public class Degre_definitionController  extends Degre_definitionActionManager   {
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Degre_definitionBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new Degre_definitionTemplate());
           
                  if (i$_ACT_INIT_SERVLET)          return      doInitServletAction();
                  if (i$_ACT_LOAD_CRACTERISTIQUE)   return      doFetchDataCaracteristique(detailBean);
                  if (i$_ACT_SELECT_ROW)            return      doSelect_Row();        
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
             }
     }
