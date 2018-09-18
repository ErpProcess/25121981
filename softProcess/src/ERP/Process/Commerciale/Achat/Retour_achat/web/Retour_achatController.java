package ERP.Process.Commerciale.Achat.Retour_achat.web;
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

import ERP.Process.Commerciale.Achat.Retour_achat.model.Retour_achatBean;
import ERP.Process.Commerciale.Achat.Retour_achat.template.Retour_achatTemplate;
@Controller
public class Retour_achatController  extends Retour_achatActionManager   {
     
	private static final long serialVersionUID = 2273885249670870262L;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	

	@RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Retour_achatBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
		
           ModelAndView model=doInitGenericAction(request,response,new Retour_achatTemplate());
           
           
           
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
                  if (i$_ACT_FETCH_AJAX_ACHAT )     return      doFetch_Achat_Data(detailBean); 
                  if (i$_ACT_SELECT_ROW)            return      doSelectRow();
                  if (i$_ACT_RETOUR_TO_LIST_ACHAT)  return      doRetourToList_Achat();    
                  if (i$_ACT_RESET_FORM)            return      doResetForm();      
                  if (i$_ACT_ACTUALISER_TABLE )     return      doActualiser_GRID();
                  if (i$_ACT_PRINT_PDF)             return      doPrintPDF();
   			      if (i$_ACT_EXPORT_XLS)            return      doExportXls();
                  
                  
   			     if (i$_ACT_CALCUL_TOTAL)           return      doCalculer_Total();
   			   
               
                  
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          if (i$_ACT_VALIDER)               return      doValiderData();
		          
		          if (i$_ACT_ANNULER)               return      doAnnulerData();
		          
		          if (i$_ACT_VERIFIER)              return      doVerificationData();
		          
		          
		          if (i$_ACT_APPLIQUER)             return      doAppliquerData();
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
	          return model;
             }
     }
