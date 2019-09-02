package ERP.Process.Commerciale.Achat.Reception_achat.web;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
@Controller
public class Reception_achatController  extends Reception_achatActionManager   {
	 
	private static final long serialVersionUID = 1L;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction( ModelMap  mod , Reception_achatBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {
    	
    	try {
			
	
    	  ModelAndView model=doInitGenericAction(request,response,new Reception_achatTemplate());
           
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
                  
                  if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
                  
                  if (i$_ACT_SELECT_ROW)            return      doSelectDetailleRow();
                 
                  if (i$_ACT_RESET_FORM)            return      doResetFormEdit();  
                  
                  
                  if (i$_ACT_LOAD_TARIF )           return      doFetchArticleSuivantTarif(detailBean );  
                  
                  if (i$_ACT_ADD_ROW )              return      doAdd_row_EditableDataTableAjax();  
                  
                  if (i$_ACT_DELETE_ROW )           return      doDelete_row_EditableDataTableAjax();  
                  
                  if (i$_ACT_Cheked_unCheked )      return      doCheked_unCheked();
                  
                  if (i$_ACT_ACTUALISER_LIST_CORR ) return      doActualiser_List(); 
                  
                  if (i$_ACT_ACTUALISER_TABLE )     return      doActualiser_GRID();
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          
		          if (i$_ACT_FETCH_AJAX_SERVIR)     return      doFetchData_for_servir_demande(detailBean);
		          
		        
		          
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          
		          if (i$_ACT_CORRIGER)              return      doCorrigerData(detailBean);
		          
		          
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
		          if (i$_ACT_PRINT_PDF)             return      doPrintPDF_Action();
		          
		          if (i$_ACT_EXPORT_XLS)            return      doExportXls_achat();
		         
		          if (i$_ACT_SERVIR)                return      doServirData(detailBean);
		          
		          if (i$_ACT_RETOUR_TO_LIST_OF_SERVIR) 
		        	                                return      doRetourToList_SERVIR(); 
		          
		          if (i$_ACT_CALCUL_TOTAL)          return      doCalculer_Total();
		          
		          
		          if (i$_ACT_PRINT_PDF_DETAILLE_RECEPTION_ACHAT ) 
		        	                                return      doPrintPDF_detaille_Reception_achat();
		          
		    	  if (i$_ACT_EXPORT_XLS_DETAILLE_RECEPTION_ACHAT) 
		    		                                return      doExportXls_detaille_reception_achat();
		    	  
		    	  if (i$_ACT_TRANSFERER)            return      doTRansferer_Data(detailBean);
		    	  
		    	  if (i$_ACT_RECEPTION)             return      doRecep_Data(detailBean);
		    	  
		    	  if (i$_ACT_STOCKER  )             return      doStockerData(detailBean);
		    	  
		     
		      	  if (i$_ACT_COMMIT)                return      doCommitData(detailBean);
		    	  
		    	  
		    	  if (i$_ACT_ANNULER)               return      doAnnulerData(detailBean);
		    	 
		    	  
	          return model;
	          
    	} finally {
			destroyThreadLocal();
		}
	          
             }
     }
