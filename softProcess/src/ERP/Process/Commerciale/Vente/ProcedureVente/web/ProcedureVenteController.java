package ERP.Process.Commerciale.Vente.ProcedureVente.web;
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
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;

@Controller
public class ProcedureVenteController  extends ProcedureVenteActionManager   {
	
 
	private static final long serialVersionUID = 2451281583185784946L;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction( ProcedureVenteBean bean,DetProcedureVenteBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {
    	
    	try{
    		
           ModelAndView model=doInitGenericAction(request,response,new ProcedureVenteTemplate());
           
		          if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
		          
		          if (i$_ACT_RESET_FORM)            return      doResetFormVente();   
		           
		          if (i$_ACT_DELETE_ROW )           return      doDelete_row_Editable(); 
		          
		          if (i$_ACT_DELETE_ROW_FOURNIURE ) return      doDeleteRowFourniture(); 

	              if (i$_ACT_ADD_ROW_FOURNIURE )    return      doAdd_row_Fourniture(bean);
	              
	              if (i$_ACT_ADD_ROW_PRESTATION )    return     doAdd_row_Prestation(bean);
	              
		          if ( i$_ACT_ACTUALISER_TABLE )    return      doActualiser_GRID(bean);
		          
		          if ( i$_ACT_ACTUALISER_TABLE_FOUNITURE )     return      doActualiserGridFourniture(bean);
		          
		          if ( i$_ACT_ACTUALISER_TABLE_PRESTATION )    return      doActualiserGridPrestation(bean);
		          
		          
		          if (i$_ACT_Cheked_unCheked )      return      doCheked_unCheked();
		          
		           
		          if (i$_ACT_ACTUALISER_LIST_CORR ) return      doActualiser_List(); 
		          
		          if (i$_ACT_LOAD_TARIF_CLIENT )    return      doFetchArticleSuivantTarif( bean );  
		          
		          if (i$_ACT_GET_STOCK )            return      doGet_Stock(bean);
		          
		          if (i$_ACT_GET_STOCK_FOURNITURE ) return      doGet_Stock_Founiture(bean);
		          
		          
		          if (i$_ACT_LOAD_SERIE )           return      doFetchDataSerie(bean);
		          
		          if ( i$_ACT_ACTUALISER_METHODE )  return      doActualiser_methode();
           
                  if (i$_ACT_FETCH_AJAX_SERVIR)     return      doFetchData_Commande(bean);
                 
                  if (i$_ACT_RETOUR_LIST_SERVIR)    return      doRetourToList_SERVIR();
		          
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(bean);
		          
		          if (i$_ACT_SELECT_ROW)            return      doSelect_detaille_Row();
		          
		          if (ACT_NAVIGATE)                 return      getViewConsult_Pdf_ex(FORM_VIEW);
		          
		          if (i$_ACT_ADD)                   return      doAddData(bean);
		          
		    	  if (i$_ACT_COMMIT)                return      doCommitData(bean);
		    	  
		    	  if (i$_ACT_FACTURER)              return      doFacturerData(bean);
		    	  
		    	  
		          
		          if (i$_ACT_UPDATE)                return      doUpdateData(bean);
		          
		          if (i$_ACT_DELETE)                return      doDeleteData(bean);
		          
		          if (i$_ACT_PRINT_PDF)             return      doPrintPDF_Action();
		          
		          if (i$_ACT_EXPORT_XLS)            return      doExportXls_achat();
		         
		          if (i$_ACT_SERVIR)                return      doServirData(bean);
		          
		          if (i$_ACT_RETOUR_LIST_SERVIR)    return      doRetourToList_SERVIR();
		          
		         
		          
		          
		          if (i$_ACT_PRINT_PDF_DETAILLE)    return      doPrintPDF_detaille(); 
		          
		    	  if (i$_ACT_EXPORT_DETAILLE)       return      doExportXls_detaille();
		    	  
		    	  if (i$_ACT_CONFIRM)               return      doConfirmData(bean);
		    
		          
		          if (i$_ACT_ADD_ROW )              return      doAdd_row_Editable(bean); 
		            
		          if (i$_ACT_CALCUL_TOTAL)          return      doCalculerTotal(bean);
		          
		          if (i$_ACT_CALCUL_TOTAL_FOURNITURE)          return      doCalculerTotalFourniture();
		    	  
		          
		    	  
		    	  //if (i$_ACT_TRANSFERER)          return      doTRansferer_Data(detailBean);
		    	  
		    	  //if (i$_ACT_RECEPTION)           return      doRecep_Data(detailBean);
		    	  
		    	  //if (i$_ACT_STOCKER)             return      doStockerData(detailBean);
		    	  
		    	  //if (i$_ACT_ANNULER)             return      doAnnulerData(detailBean);
		    	  
		    	 
		          
	          return model;
	          
		    } finally {
				destroyThreadLocal();
			}
    
    
             }
     }
