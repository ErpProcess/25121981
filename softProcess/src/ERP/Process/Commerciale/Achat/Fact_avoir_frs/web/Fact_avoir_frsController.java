package ERP.Process.Commerciale.Achat.Fact_avoir_frs.web;
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
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.model.Fact_avoir_frsBean;
import ERP.Process.Commerciale.Achat.Fact_avoir_frs.template.Fact_avoir_frsTemplate;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
@Controller
public class Fact_avoir_frsController  extends Fact_avoir_frsActionManager   {
   
	private static final long serialVersionUID = 3909647743988606370L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Fact_avoir_frsBean detailBean,Facture_FournisseurBean fact_frs,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
  	    try { 
           ModelAndView model=doInitGenericAction(request,response,new Fact_avoir_frsTemplate());
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();
                  if (i$_ACT_FETCH_FACTURE_DOIT)    return      doFetchDataFactureDoit(fact_frs);
                  if (i$_ACT_SELECT_ROW)            return      doGet_RowBean();  
                  
                  if (i$_ACT_LOAD_AVOIR_VENTE)      return      doLoad_avoir_survente(detailBean);
                  if (i$_ACT_CALCUL_TOTAL)          return      doCalculer_Total(fact_frs);
                  
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          return model;
  	   } finally { 
  	      destroyThreadLocal(); 
        }
    }
  }
