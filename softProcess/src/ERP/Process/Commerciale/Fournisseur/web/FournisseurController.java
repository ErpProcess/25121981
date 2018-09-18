package ERP.Process.Commerciale.Fournisseur.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.template.FournisseurTemplate;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;

@Controller
public class FournisseurController extends FournisseurActionManager {
	 
	private static final long serialVersionUID = -6672484701782629646L;

	@RequestMapping(value = ROOT)
	public ModelAndView doControlAction(FournisseurBean detailBean,HttpServletRequest request, HttpServletResponse response)throws Exception {
		 ModelAndView model = doInitGenericAction(request, response,new FournisseurTemplate());
		 
		
		 
		if (i$_ACT_INIT_SERVLET )  return      doInitServletAction();  
		if (i$_ACT_ADD)        return doAddData(detailBean);
		if (i$_ACT_AJAX_FETCH) return doFetchData(detailBean);
		if (i$_ACT_UPDATE)     return doUpdateData(detailBean);
		if (i$_ACT_DELETE)     return doDeleteData(detailBean);
		return model;
	}
}
