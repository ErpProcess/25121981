package ERP.Process.Commerciale.FamilleArticle.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.Process.Commerciale.FamilleArticle.template.FamilleArticleTemplate;

@Controller
public class FamilleArticleController extends FamilleArticleActionManager {
	@RequestMapping(value = ROOT)
	public ModelAndView doControlAction(FamilleBean detailBean,
			HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		ModelAndView model = doInitGenericAction(request, response,
				new FamilleArticleTemplate());
		
		 if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();  
		if (i$_ACT_ADD)
			return doAddData(detailBean);
		if (i$_ACT_AJAX_FETCH)
			return doFetchData(detailBean);
		if (i$_ACT_UPDATE)
			return doUpdateData(detailBean);
		if (i$_ACT_DELETE)
			return doDeleteData(detailBean);
		return model;
	}
}
