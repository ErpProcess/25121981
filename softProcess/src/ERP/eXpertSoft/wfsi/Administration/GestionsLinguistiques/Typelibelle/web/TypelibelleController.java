package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.model.TypelibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Typelibelle.util.TypelibelleTemplate;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;




@Controller
public class TypelibelleController extends ActionTypelibelleManager     {

	
	
	 
	
	@RequestMapping(value = ROOT)
	
	public ModelAndView doDefineUrlAction(TypelibelleBean detailBean , HttpServletRequest httpRequest, HttpServletResponse httpResponse,HttpSession  httpSession )
	throws IOException, Throwable {

	this.doInitGenericAction(httpRequest, httpResponse,new TypelibelleTemplate());
	
	             
	
	   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
	
	   if (i$_ACT_AJAX_FETCH)            return      doFetchDataAjax(detailBean);    
	
	   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
	
	   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);       
	
	 
	
	return null;

}

	

}
