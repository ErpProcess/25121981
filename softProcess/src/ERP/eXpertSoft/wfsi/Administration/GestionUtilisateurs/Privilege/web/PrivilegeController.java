package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.model.PrivilegeBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.template.PrivilegeTemplate;


@Controller
public class PrivilegeController  extends PrivilegeActionManager   {
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(PrivilegeBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new PrivilegeTemplate());
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
                  if (i$_INIT_AFFECT)               return      doInitAffectation_privilege(detailBean); 
                  if (i$_ACT_LOAD_SOUSPACKAGE)      return      doLoadSou_Packge();
                  if (i$_ACT_LOAD_MODUL)            return      doLoadModulefrom_spack();
                  if (i$_ACT_LOAD_F_S_MODUL)        return      doLoadFct_sous_Module();
                  
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
             }
     }