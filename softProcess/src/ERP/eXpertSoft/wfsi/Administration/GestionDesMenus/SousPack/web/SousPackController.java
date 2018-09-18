package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.util.SousPackageTemplate;

 
@Controller
public class SousPackController   extends SousPackageActionManager  {

	@RequestMapping(value = ROOT)
	public ModelAndView doDefineUrlAction(SousPackageBean detailBean , HttpServletRequest httpRequest, HttpServletResponse httpResponse,HttpSession httpSession)
			throws IOException, Throwable {

		 ModelAndView model= doInitGenericAction(httpRequest, httpResponse,  new SousPackageTemplate());
		   if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
		   if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);    
		   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
		   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);       

		
		   return model;

	}

}
