package ERP.Process.Commerciale.Demande_Achat.web;
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

import ERP.Process.Commerciale.Demande_Achat.model.Demande_achatBean;
import ERP.Process.Commerciale.Demande_Achat.template.Demande_AchatTemplate;
@Controller
public class Demande_AchatController  extends Demande_AchatActionManager   {
	
 
	private static final long serialVersionUID = -7598502829816861520L;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Demande_achatBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
    	
           ModelAndView model=doInitGenericAction(request,response,new Demande_AchatTemplate());
           
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
                  
                  if (i$_ACT_ADD_ROW )              return      doAdd_row_EditableDataTableAjax();  
                  
                  if (i$_ACT_DELETE_ROW )           return      doDelete_row_EditableDataTableAjax();  
                  
                  if (i$_ACT_Cheked_unCheked )      return      doCheked_unCheked();
                  
                  if (i$_ACT_ACTUALISER_LIST_CORR ) return      doActualiser_List();  
                  
                  if (i$_ACT_LOAD_TARIF )           return      doFetchArticleSuivantTarif( detailBean );  
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          
		          if (i$_ACT_VALIDER)               return      doValiderDemande_Achat(detailBean);
		          
		          if (i$_ACT_ANNULER)               return      doValiderDemande_Achat(detailBean);
		          
		          
		          
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          
		          if (i$_ACT_SELECT_ROW)            return      doSelectRow(); 
		          
		          
		          
		          if (i$_ACT_LOAD_DETAILLE_CONSULT) return     doLoad_detCommande_cons(); 
		          
		          
		          if (ACT_NAVIGATE)                 return     getViewConsult(FORM_VIEW_CONSULT);
		          
		          
		          
	  if (i$_ACT_PRINT_PDF_detaille_demande_achat)  return      doPrintPDF_detaille_demande_achat();
	  
	  if (i$_ACT_EXPORT_XLS_detaille_demande_achat) return      doExportXls_detaille_demande_achat();
		          
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
	          return model;
             }
     }
