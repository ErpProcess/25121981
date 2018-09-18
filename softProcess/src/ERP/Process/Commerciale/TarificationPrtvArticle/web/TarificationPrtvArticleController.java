package ERP.Process.Commerciale.TarificationPrtvArticle.web;

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

import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.template.TarificationPrtvArticleTemplate;

 
@Controller
public class TarificationPrtvArticleController extends TarificationPrtvArticleActionManager {
	
 
 
	private static final long serialVersionUID = -3562103767492775683L;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	   dateFormat.setLenient(false);
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value = ROOT)
	public ModelAndView doControlAction(TarificationPrtvArticleBean detailBean, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ModelAndView model = doInitGenericAction(request, response, new TarificationPrtvArticleTemplate());

		if (i$_ACT_INIT_SERVLET)   return doInitServletAction();
		
		if (i$_ACT_RESET_FORM)     return doResetForm();    
		  
		if (i$_ACT_SELECT_ROW)     return doGet_Row_Bean();  
		 
		 
		if (i$_ACT_ADD)            return doAddData(detailBean);
		
		if (i$_ACT_AJAX_FETCH)     return doFetchData(detailBean);
		
		if (i$_ACT_UPDATE)         return doUpdateData(detailBean);
		
		if (i$_ACT_CALCULATE_PU)   return doCalculate_PU(detailBean);
		
		if (i$_ACT_CONFIRM)        return doConfirmData(detailBean);
		
		if (i$_ACT_DELETE)         return doDeleteData(detailBean);
		return model;
	}
}
