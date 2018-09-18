package ERP.Process.Commerciale.Stock.DepotStockage.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.template.DepotStockageTemplate;
@Controller
public class DepotStockageController  extends DepotStockageActionManager   {
    
	private static final long serialVersionUID = -1342117600614696632L;

	@RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(DepotStockageBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new DepotStockageTemplate());
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
             }
     }
