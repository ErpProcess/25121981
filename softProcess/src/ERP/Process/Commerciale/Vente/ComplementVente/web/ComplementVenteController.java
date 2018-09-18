package ERP.Process.Commerciale.Vente.ComplementVente.web;
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
import ERP.Process.Commerciale.Vente.ComplementVente.model.ComplementVenteBean;
import ERP.Process.Commerciale.Vente.ComplementVente.template.ComplementVenteTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
@Controller
public class ComplementVenteController  extends ComplementVenteActionManager   {
 
	private static final long serialVersionUID = 3169829822066024210L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(ProcedureVenteBean detailBeanVente,ComplementVenteBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
  	    try { 
           ModelAndView model=doInitGenericAction(request,response,new ComplementVenteTemplate());
           if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();
           if (i$_ACT_FETCH_AJAX_VENTE )     return      doFetchDataVente(detailBeanVente);
           
           if (i$_ACT_LOAD_SELECT )          return      doLoadSelectList();
           
           
           if (i$_ACT_SELECT_ROW)            return         doGetRowBeanComplement();  
           if (i$_ACT_ACTUALISER_TABLE)      return         doActualiser_GRID();
	          if (i$_ACT_ADD)                   return      doAddData(detailBean);
	          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
	          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
	          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          if (i$_ACT_CONFIRM)               return      doConfirmStockData(detailBean);
	          return model;
  	   } finally { 
  	      destroyThreadLocal(); 
        }
    }
  }
