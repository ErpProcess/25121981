package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.util.PackageSysTemplate;

@Controller
public class PackageSysController extends ActionPackageSysManager {

	@RequestMapping(value = ROOT)
	public ModelAndView doDefineUrlAction(PackageSysBean detailBean , HttpServletRequest httpRequest, HttpServletResponse httpResponse,HttpSession httpSession)
			throws IOException, Throwable {

		 ModelAndView model= doInitGenericAction(httpRequest, httpResponse,  new PackageSysTemplate());
		
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
		   if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);    
		   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
		   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);       

		
		   return model;

	}

	 

}
