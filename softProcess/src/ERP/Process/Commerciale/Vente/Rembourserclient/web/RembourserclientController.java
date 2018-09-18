package ERP.Process.Commerciale.Vente.Rembourserclient.web;
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
 
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.Process.Commerciale.Vente.Rembourserclient.template.RembourserclientTemplate;
@Controller
public class RembourserclientController  extends RembourserclientActionManager   {
   
	private static final long serialVersionUID = -8750648667851434323L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(ReglementFactCltBean detailBean,Facture_clientBean factureBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
  	    try { 
           ModelAndView model=doInitGenericAction(request,response,new RembourserclientTemplate());
	           if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();  
	           if (i$_ACT_FETCH_AJAX_FACT )      return      doFetchDataFacture(factureBean);  
	           if (i$_ACT_SELECT_ROW)            return      doGetRowBean();
	           if (i$_ACT_LOAD_GRID_ECHEAN)      return      doLoadGridEcheance( detailBean );  
	           if (i$_ACT_ADD)                   return      doAddData(detailBean);
	           if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
	        
	           if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
	           if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	           if (i$_ACT_CONFIRM)               return      doUpdateData(detailBean);
	           if (i$_ACT_ANNULER)               return      doUpdateData(detailBean);
	          return model;
  	   } finally { 
  	      destroyThreadLocal(); 
        }
    }
  }
