package ERP.Process.Commerciale.Code_barre.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

 

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.template.Code_barreTemplate;
@Controller
public class Code_barreController  extends Code_barreActionManager   {
 
	private static final long serialVersionUID = 7667474254927210745L;

	@RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Code_barreBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new Code_barreTemplate());
           
                  if (i$_ACT_INIT_SERVLET )          return      doInitServletAction();
                  if (i$_ACT_LOAD_CARACTER_ARTICLE ) return      doFetchDataCaracteristique();
                  if (i$_ACT_UPDATE_LIST_CARACT )    return      doUpdateListCaracteristique();
                  if (i$_ACT_LOAD_CODE_BARR )        return      doGenerer_code_barre(detailBean);
                  if (i$_ACT_SELECT_ROW)             return      doSelectRow();  
		          if (i$_ACT_ADD)                    return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)             return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                 return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                 return      doDeleteData(detailBean);
	          return model;
             }
     }
