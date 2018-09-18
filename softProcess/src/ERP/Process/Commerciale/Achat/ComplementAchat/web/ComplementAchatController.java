package ERP.Process.Commerciale.Achat.ComplementAchat.web;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ERP.Process.Commerciale.Achat.ComplementAchat.model.ComplementAchatBean;
import ERP.Process.Commerciale.Achat.ComplementAchat.model.Det_complment_achatBean;
import ERP.Process.Commerciale.Achat.ComplementAchat.template.ComplementAchatTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.DoubleEditor;



@Controller
public class ComplementAchatController  extends ComplementAchatActionManager   {
    
	 
	private static final long serialVersionUID = 7795182425296985202L;
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    
	    binder.registerCustomEditor(double.class, new DoubleEditor());   
        
        
        
	}

	@RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(ComplementAchatBean detailBean,Det_complment_achatBean  beandet,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
		
		
           ModelAndView model=doInitGenericAction(request,response,new ComplementAchatTemplate());
           
           
           if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
           
           if (i$_ACT_FETCH_AJAX_ACHAT )     return      doFetch_Achat_Data(detailBean); 
           
           if (i$_ACT_SELECT_ROW)            return      doSelectRow();
           
           if (i$_ACT_RETOUR_TO_LIST_ACHAT)  return      doRetourToList_Achat(); 
       	 
           if (i$_ACT_ADD_ROW )              return      doAdd_row_EditableDataTableAjax( beandet); 
           
 
           
           if (i$_ACT_RESET_FORM)            return      doResetForm();   
           
           if (i$_ACT_ACTUALISER_TABLE )     return      doActualiser_GRID();
           
           if (i$_ACT_PRINT_PDF)             return      doPrintPDF();
           
		   if (i$_ACT_EXPORT_XLS)            return      doExportXls();
		   
		   if (i$_ACT_CALCUL_TOTAL)          return      doCalculer_Total();
           
	       if (i$_ACT_ADD)                   return      doAddData(detailBean);
	       
	       if (i$_ACT_TRANSFER)              return      doTransferData();
	       
	       if (i$_ACT_ANNULER)               return      doAnnulerData();
	       
	       if (i$_ACT_RECEPTION)             return      doReceptionData();
	       
	       if (i$_ACT_CONSERV)               return      doConserverData();
	       
	       if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
	       
	       if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
	       
	       if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
	          
	          return model; 
	          
             }
     }
