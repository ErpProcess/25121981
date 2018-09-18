package ERP.Process.Commerciale.Vente.Commandeclient.web;
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
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.template.CommandeclientTemplate;
@Controller
public class CommandeclientController  extends CommandeclientActionManager   {
   
	private static final long serialVersionUID = 3472785353280959360L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(CommandeclientBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable { 
    	
    	try{
    		
           ModelAndView model=doInitGenericAction(request,response,new CommandeclientTemplate());
           
           
                  if (i$_ACT_INIT_SERVLET)          return      doInitServletAction();
                  
                  if (i$_ACT_LOAD_TARIF_CLT )       return      doFetchForTarif(detailBean); 
                  
                 
                  if (i$_ACT_ACTUALISER_LIST_CORR ) return      doActualiser_List(); 
                  
                 
                  
                  if (i$_ACT_SELECT_ROW)            return      doSelectRow(); 
                  
                  if(ACT_NAVIGATE)                  return      getViewConsult_Pdf_ex(FORM_VIEW);
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          
		          if (i$_ACT_CONFIRM)               return      doConfirmData(detailBean);
		          
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          
		         
		          
		          if (i$_ACT_PRINT_PDF_DETAILLE)    return      doPrintPDF_detaille(); 
		          
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
		          
		          
		          
		          if (i$_ACT_ADD_ROW )              return      doAdd_row_Editable( detailBean );  
		          if (i$_ACT_ACTUALISER_TABLE )     return      doActualiser_GRID(detailBean );
                  if (i$_ACT_DELETE_ROW )           return      doDelete_row_Editable(); 
                  if (i$_ACT_CHEKED_UNCHEKED )      return      doCheked_unCheked();
                  if (i$_ACT_CALCUL_TOTAL)          return      doCalculer_Total(detailBean);
                  
		          
	          return model;
	          
    } finally {
		destroyThreadLocal();
	}
    
    
    
             }
     }
