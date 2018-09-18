package ERP.Process.Commerciale.Stock.MouvementStock.web;
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

import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.MouvementStock.template.MouvementStockTemplate;
@Controller
public class MouvementStockController  extends MouvementStockActionManager   {
 
	private static final long serialVersionUID = -647024715273499418L;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(MouvementSerieBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new MouvementStockTemplate());
                  if (i$_ACT_INIT_SERVLET)          return      doIniServletAction();
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
             }
     }
