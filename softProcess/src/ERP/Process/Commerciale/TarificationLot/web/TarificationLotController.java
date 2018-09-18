package ERP.Process.Commerciale.TarificationLot.web;
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
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.Process.Commerciale.TarificationLot.template.TarificationLotTemplate;
@Controller
public class TarificationLotController  extends TarificationLotActionManager   {
	 
	private static final long serialVersionUID = -8989366441008426795L;
	
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(TarificationLotBean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new TarificationLotTemplate());
           

		           if (i$_ACT_INIT_SERVLET)     	return  doInitServletAction();
		   		
		   		  if (i$_ACT_RESET_FORM)        	return  doResetForm();    
		   		 
		   		  if (i$_ACT_GET_INFO_ARTICLE)  	return  doLoadInfo_article(detailBean);
		   		
		   		  if (i$_ACT_GET_INFO_COUT)     	return  doLoadCout(detailBean);
		   		
		   		  if (i$_ACT_LOAD_DEPOT_LOT)    	return  doLoadDepotFromLot(detailBean);
		   		 
		   		  if (i$_ACT_LOAD_COD_BARRE)    	return  doLoadAutocompleteList();
		   		
		   		  if (i$_ACT_LOAD_SUIVANT)      	return  doLoadSuivant(detailBean);
		   		  
		    	  if (i$_ACT_SELECT_ROW)            return  doGetRowBeanTarif();               
   		
		          if (i$_ACT_ADD)                   return  doAddData(detailBean);
		          
		          if (i$_ACT_CONFIRM)               return  doConfirmData(detailBean);
		          
		          if (i$_ACT_AJAX_FETCH)            return  doFetchData(detailBean);
		          
		          if (i$_ACT_UPDATE)                return  doUpdateData(detailBean);
		          
		          if (i$_ACT_DELETE)                return  doDeleteData(detailBean);
		          
	          return model;
             }
     }
