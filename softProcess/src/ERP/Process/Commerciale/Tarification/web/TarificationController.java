package ERP.Process.Commerciale.Tarification.web;

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

import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.template.TarificationTemplate;

 
@Controller
public class TarificationController extends TarificationActionManager {
	
 
 
	private static final long serialVersionUID = -3562103767492775683L;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	   dateFormat.setLenient(false);
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value = ROOT)
	public ModelAndView doControlAction(TarificationBean detailBean, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ModelAndView model = doInitGenericAction(request, response, new TarificationTemplate());

		if (i$_ACT_INIT_SERVLET)     return  doInitServletAction();
		
		if (i$_ACT_RESET_FORM)       return  doResetForm();    
		
		if (i$_ACT_GET_INFO_ARTICLE) return  doLoadInfo_article(detailBean);
		
		if (i$_ACT_GET_INFO_COUT)    return  doLoadCout(detailBean);
		
		if (i$_ACT_GET_INFO_VENTE)   return  doLoadInfoVente(detailBean); 
		
		if (i$_ACT_LOAD_DEPOT_LOT)   return  doLoadDepotFromLot(detailBean);
		
		if (i$_ACT_LOAD_COD_BARRE)   return  doLoadAutocompleteList();
		
		if (i$_ACT_LOAD_SUIVANT)     return  doLoadSuivant(detailBean);
		
		if (i$_ACT_UPDATE_EDITABLE)  return  doUpdateEditableTable();
		
		if (i$_ACT_SELECT_ROW)       return  doGetRowBeanTarif();
		 
		if (i$_ACT_CONFIRM)          return  doConfirmData(detailBean);
		 
		if (i$_ACT_ADD)              return  doAddData(detailBean);
		
		if (i$_ACT_AJAX_FETCH)       return  doFetchData(detailBean);
		
		if (i$_ACT_PRINT_PDF)        return  doPrintPDF_Tarif();
		   
		if (i$_ACT_EXPORT_XLS)       return  doExport_Xls();
		
		if (i$_ACT_UPDATE)           return  doUpdateData(detailBean);
		
		if (i$_ACT_DELETE)           return  doDeleteData(detailBean);
		
		if (i$_ACT_VERIF_LIST)       return  doTeste_Insert_Prix(detailBean);
		
		return model;
	}
}
