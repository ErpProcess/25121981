package ERP.Process.Commerciale.Vente.ReglementFactClt.web;
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

import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.template.ReglementFactCltTemplate;
@Controller
public class ReglementFactCltController  extends ReglementFactCltActionManager   {
 
	private static final long serialVersionUID = 5935201320636312003L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(ReglementFactCltBean detailBean,Facture_clientBean factureBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
  	    try { 
           ModelAndView model=doInitGenericAction(request,response,new ReglementFactCltTemplate());
           
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction(); 
                  
                  if (i$_ACT_LOAD_SELECT )          return      doLoadSelectList(); 
                  
                  if (i$_ACT_ACTUALISER_TABLE )     return      doActualiserGrid(detailBean); 
                  
                  if (i$_ACT_FETCH_AJAX_FACT )      return      doFetchDataFacture(factureBean); 
                  
                  if (i$_ACT_SELECT_ROW)            return      doGetRowBean();
                  
                  if (i$_ACT_DELETE_ROW )           return      doDeleteRowEditable(detailBean); 

	              if (i$_ACT_ADD_ROW )              return      doAddRowEditable(detailBean);
		          
		          if (I$_ACT_CHEKED_UNCHEKED )      return      doChekedUnCheked();
                  
                  if (i$_ACT_RETOUR_TO_LIST_FACT)   return      doRetourToFilterFacture();
                  
                  if (i$_ACT_LOAD_GRID_ECHEAN)      return      doLoadGridEcheance( detailBean ); 
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          
		          
		          if (i$_ACT_CALCUL_TOTAL)          return      doCalculerTotal();
		          
		          
		          
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          
		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);
		          
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
		          if (i$_ACT_CONFIRM)               return      doUpdateData(detailBean);
		          
		          if (i$_ACT_ANNULER)               return      doUpdateData(detailBean);
		          
	          return model;
  	   } finally { 
  	      destroyThreadLocal(); 
        }
    }
	 
  }
