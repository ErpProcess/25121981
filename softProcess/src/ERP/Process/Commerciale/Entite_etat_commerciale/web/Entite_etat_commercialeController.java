package ERP.Process.Commerciale.Entite_etat_commerciale.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.template.Entite_etat_commercialeTemplate;
@Controller
public class Entite_etat_commercialeController  extends Entite_etat_commercialeActionManager   {
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Entite_etat_commercialeBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new Entite_etat_commercialeTemplate());
           
           
                  if (i$_ACT_INIT_SERVLET )         return      doInit_ServletAction();
                  
                  if (i$_ACT_ADD_ROW )              return      doAdd_row_EditableDataTableAjax();  
                  if (i$_ACT_DELETE_ROW )           return      doDelete_row_EditableDataTableAjax();  
                  if (i$_ACT_Cheked_unCheked )      return      doCheked_unCheked();
                  
                  if (i$_ACT_SELECT_ROW)            return      doSelect_Row();
                 
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
             }
     }
