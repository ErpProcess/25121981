package ERP.Process.Commerciale.Vente.Devis.web;
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
import ERP.Process.Commerciale.Vente.Devis.model.DevisBean;
import ERP.Process.Commerciale.Vente.Devis.template.DevisTemplate;
@Controller
public class DevisController  extends DevisActionManager   {
  	 
	 
 
	private static final long serialVersionUID = 2484129540525572024L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  	     dateFormat.setLenient(false); 
  	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(DevisBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable { 
    	
    	try{
           ModelAndView model=doInitGenericAction(request,response,new DevisTemplate());
           
              if (i$_ACT_INIT_SERVLET )          return      doInitServletAction(); 
              
              if (i$_ACT_RESET_FORM)             return      doResetFormDev(); 
           
              if (i$_ACT_ADD_ROW )               return      doAdd_row_Editable();  
              
              if (i$_ACT_LOAD_DETAIL)            return      doFetchSuivant(detailBean);  
              
             // if (i$_ACT_LOAD_TARIF_CLIENT )     return      doFetchArticleSuivantTarif( );  
              
           
               if (i$_ACT_DELETE_ROW )           return      doDelete_row_Editable();  
           
               if (i$_ACT_Cheked_unCheked )      return      doCheked_unCheked();
           
               if (i$_ACT_ACTUALISER_LIST_CORR ) return      doActualiser_List(); 
               
               if (i$_ACT_ACTUALISER_TABLE )     return      doActualiser_GRID();
              
              
           
	           if (i$_ACT_ADD)                    return      doAddData(detailBean);
	          
	           if (i$_ACT_CONFIRM)                return      doConfirmData(detailBean);
	          
	         // if (i$_ACT_ANNULER)               return      doValiderDemande_Achat(detailBean);
	          
	          
	          
	          if (i$_ACT_AJAX_FETCH)              return      doFetchData(detailBean);
	          
	           if (i$_ACT_SELECT_ROW)             return      doSelectRow(); 
	          
	           if(ACT_NAVIGATE)                   return      getViewConsult_Pdf_ex(FORM_VIEW);
	          
	        // if (i$_ACT_LOAD_DETAILLE_CONSULT)  return      doLoad_detCommande_cons(); 
	          
	          
	         
	          if (i$_ACT_CALCUL_TOTAL)            return      doCalculer_Total(detailBean);
	          
	          
              if (i$_ACT_PRINT_PDF_DETAILLE)      return      doPrintPDF_detaille();

              if (i$_ACT_EXPORT_XLS_DETAILLE)     return      doExportXls_detaille();
	          
	          if (i$_ACT_UPDATE)                  return      doUpdateData(detailBean);
	            
	          if (i$_ACT_DELETE)                  return      doDeleteData(detailBean);
	          
	          return model;
		    } finally {
				destroyThreadLocal();
			}
             }
     }
