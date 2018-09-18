package ERP.Process.Commerciale.Vente.Facture_avoir_client.web;
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
 
import ERP.Process.Commerciale.Vente.Facture_avoir_client.model.Facture_avoir_clientBean;
import ERP.Process.Commerciale.Vente.Facture_avoir_client.template.Facture_avoir_clientTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
 
@Controller
public class Facture_avoir_clientController  extends Facture_avoir_clientActionManager   {
   
	private static final long serialVersionUID = 1747249500303036622L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Facture_clientBean detailBean,Facture_avoir_clientBean avoirBean ,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
  	    try { 
           ModelAndView model=doInitGenericAction(request,response,new Facture_avoir_clientTemplate());
           
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();  
                  
                  if (i$_ACT_FETCH_FACTURE_DOIT)    return      doFetchDataFactureDoit(detailBean);
                  
                  if (i$_ACT_SELECT_ROW)            return      doGet_RowBean();    
                  
                  if (i$_ACT_LOAD_AVOIR_VENTE)      return      doLoad_avoir_survente(avoirBean);
                  if (i$_ACT_CALCUL_TOTAL)          return      doCalculer_Total(detailBean);
		          if (i$_ACT_ADD)                   return      doAddData(avoirBean); 
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(avoirBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
  	   } finally { 
  	      destroyThreadLocal(); 
        }
    }
  }
