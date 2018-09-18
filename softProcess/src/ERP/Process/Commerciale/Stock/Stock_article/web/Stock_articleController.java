package ERP.Process.Commerciale.Stock.Stock_article.web;
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

import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.template.Stock_articleTemplate;

@Controller
public class Stock_articleController  extends Stock_articleActionManager   {
     
	
	private static final long serialVersionUID = 3784926575309699792L;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Stock_articleBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new Stock_articleTemplate());
           
                  if (i$_ACT_INIT_SERVLET )         return      doIniServletAction();  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          if (i$_ACT_PRINT_PDF)             return      doPrintPDFStock();
	          return model;
             }
     }
