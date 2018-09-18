package ERP.Process.Commerciale.Achat.Facture_Fournisseur.web;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.template.Facture_FournisseurTemplate;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
@Controller
public class Facture_FournisseurController  extends Facture_FournisseurActionManager   {
    
	private static final long serialVersionUID = -5772256829211151899L;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = ROOT) 
    public ModelAndView doControlAction( Facture_FournisseurBean detailBean,
    		                             Reception_achatBean     recepBean, HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  
           ModelAndView model=doInitGenericAction(request,response,new Facture_FournisseurTemplate());
           
                  if (i$_ACT_INIT_SERVLET)          return      doInitServletAction();
                  
                  if (i$_ACT_UPLOADER)              return      uploadFile();
                  
                  if (i$_ACT_AFFICHE_DOC)           return      dislayFile();
                  
                  if (i$_ACT_FETCH_TO_FACTURE)      return      doFetchData_To_Fact(recepBean);
                  
                  if (i$_ACT_GEN_FACTURE)           return      doGenererInterfaceFacture(recepBean);
                  
                  if (i$_ACT_RETOUR_TO_LIST_ACHAT)  return      doRetourToList_achat();
                  
                  if (i$_ACT_SELECT_ROW)            return      doSelectRowBean();
                  
                  if (i$_ACT_CALCUL_TOTAL)          return      doCalculer_Total(detailBean);
                  
		          if (i$_ACT_ADD)                   return      doAddData(detailBean);
		          
		          if (ACT_NAVIGATE)  				return      getViewConsult_Pdf_ex(FORM_VIEW);
		          
		          if (i$_ACT_PRINT_PDF_DETAILLE)    return      doPrintPDF_detaille();
		          
		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);
		          
		          if (i$_ACT_CONFIRM)               return      doUpdateData(detailBean);
		          
		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);
		          
	          return model;
             }
     }
