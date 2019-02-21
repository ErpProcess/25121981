package ERP.Process.Commerciale.Achat.Regachat.web;
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

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Achat.Regachat.model.RegachatBean;
import ERP.Process.Commerciale.Achat.Regachat.template.RegachatTemplate;
@Controller
public class RegachatController  extends RegachatActionManager   {
   
	private static final long serialVersionUID = -2615510266069223555L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(RegachatBean detailBean,Facture_FournisseurBean searchBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
  	    try { 
           ModelAndView model=doInitGenericAction(request,response,new RegachatTemplate());
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();  
                  
                  if (i$_ACT_FETCH_AJAX_FACT )      return      doFetchFacture_fournisseur(searchBean);  
                  
                  if (i$_ACT_DELETE_ROW )           return      doDeleteRowEditable(detailBean); 

	              if (i$_ACT_ADD_ROW )              return      doAddRowEditable(detailBean);
		          
		          if (I$_ACT_CHEKED_UNCHEKED )      return      doChekedUnCheked();
                  
                  
		          if (i$_ACT_SELECT_ROW)            return      doGetRowBeanReg();
                  
                  
                  if (i$_ACT_LOAD_GRID_ECHEAN)      return      doLoadGridEcheance( detailBean );  
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
		          if (i$_ACT_CONFIRM)                return     doUpdateData(detailBean);
		          
		          
	          return model;
  	   } finally { 
  	      destroyThreadLocal(); 
        }
    }
  }
