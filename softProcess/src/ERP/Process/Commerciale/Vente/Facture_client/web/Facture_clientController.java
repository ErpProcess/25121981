package ERP.Process.Commerciale.Vente.Facture_client.web;
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
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
@Controller
public class Facture_clientController  extends Facture_clientActionManager   {
   
	private static final long serialVersionUID = 882838797679721964L;
	@InitBinder
  	public void initBinder(WebDataBinder binder) {
  	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
  	   dateFormat.setLenient(false); 
  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
  	 } 
    @RequestMapping(value = ROOT) 
    public ModelAndView doControlAction(Facture_clientBean detailBean,ProcedureVenteBean venteBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {
    	
    	try{
    		
           ModelAndView model=doInitGenericAction(request,response,new Facture_clientTemplate());
           BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
           
                  if (i$_ACT_INIT_SERVLET )         return      doInitServletAction();  
		         
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          
		          
		          if (i$_RESET_APRES_FACT)          return      doResetForm_apres_facture();
		        
		          
		          if (i$_ACT_FETCH_AJAX_VENTE)      return      doFetchDataVente(venteBean);
		          
		          if (i$_ACT_SELECT_ROW)            return      doGet_Row_Bean();   
		          
		          if (i$_ACT_GEN_FACTURE)           return      doGenererInterfaceFacture(detailBean);
		          
		          if (i$_ACT_RETOUR_TO_LIST_VE)     return      doRetourToList_Vente();
		          
		          if (i$_ACT_CALCUL_TOTAL)          return      doCalculer_Total(detailBean);
		          
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          
		          if (ACT_NAVIGATE)  				return      getViewConsult_Pdf_ex(FORM_VIEW);
		          
		         
		          
		           //if (i$_ACT_PRINT_PDF_DETAILLE)    return      doPrintPDF_detaille();
		          
		          if (i$_ACT_PRINT_PDF_DETAILLE &&  bs.getSoc_id().equals("6"))     return      doPrintFactureModelKobbi();
		          if (i$_ACT_PRINT_PDF_DETAILLE &&  bs.getSoc_id().equals("10"))    return      doPrintFactureSPL();
		          
		          if (i$_ACT_PRINT_PDF_DETAILLE && !bs.getSoc_id().equals("6") && !bs.getSoc_id().equals("10") )     return      doPrintPDF_detaille();
		          if (i$_ACT_IMPRIMER_EXPORT_KB &&  bs.getSoc_id().equals("6"))     return      doPrintExportManchaKobbi();
		          
		          
		          if (i$_ACT_CALCUL_TOTAL_GRID)      return      doCalculerTotalGrid(detailBean);


	              //if (i$_ACT_EXPORT_XLS_DETAILLE)   return      doPrintPDF_detaille();
	              
		          if (i$_ACT_CONFIRM)               return      doUpdateData(detailBean);
		          
		          //if (i$_ACT_ENVOYER)               return      doDeleteData(detailBean);
		          
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
		          return model;
	          
			    } finally {
					destroyThreadLocal();
				}
             }
     }
