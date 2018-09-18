package ERP.Process.Commerciale.Stock.DocumentLot.web;
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
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.template.DocumentLotTemplate;
@Controller
public class DocumentLotController  extends DocumentLotActionManager   {

 
	private static final long serialVersionUID = -5087924424409595532L;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(SerieArticletBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable { 
    	
           ModelAndView model=doInitGenericAction(request,response,new DocumentLotTemplate());
           
                  if (i$_ACT_INIT_SERVLET )         return      doIniServletAction();
                  if (i$_ACT_RESET_FORM)            return      doReset_Form();
                  
                  
                  
                  if (i$_ACT_SELECT_ROW)            return      doSelect_RowBean(); 
                  if (i$_ACT_DO_GET_DETAIL_LOT)     return      doSelect_Detail_lot(); 
                  if (test_ACT_CONSULT_INTO)        return      doGET_test_consult();
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		         
		         
		           
		           
		           
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          if (i$_ACT_CANCEL_UPDATE)         return      doCancelUpdate(); 
		          if (i$_ACT_CALCUL_TOTAL)          return      doCalculer_Total();
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
		          
		          if (i$_ACT_GET_LOT_ARTICLE)       return      doFetchLotData(detailBean); 
		          if (i$_ACT_ADD_CHOIX)             return      doAddChoix(detailBean);
		          if (i$_ACT_RESET_FORM_CHOIX)      return      doReset_Form_choix();
                  if (i$_ACT_SUPP_CHOIX_LOT)        return      doSUPP_choixLotData(detailBean);
                  if (i$_ACT_VERIF_LIST)            return      doTeste_List_saisie( detailBean);
		          
		           
	          return model;
             }
     }


