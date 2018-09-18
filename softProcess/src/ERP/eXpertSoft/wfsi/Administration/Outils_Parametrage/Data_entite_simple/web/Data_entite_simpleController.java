package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.template.Data_entite_simpleTemplate;

@Controller
public class Data_entite_simpleController  extends Data_entite_simpleManager   {

	    @RequestMapping(value = ROOT)
		public ModelAndView doControlAction(Data_entite_simpleBean detailBean,HttpServletRequest request,HttpServletResponse response  )
		throws  Throwable {
	    ModelAndView model=doInitGenericAction(request,response,new Data_entite_simpleTemplate());
		   if (i$_ACT_ADD)                   return      doAddData(detailBean);          
		   if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);    
		   if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);       
		   if (i$_ACT_DELETE)                return      doDeleteData(detailBean);      
		return model;
}
}
