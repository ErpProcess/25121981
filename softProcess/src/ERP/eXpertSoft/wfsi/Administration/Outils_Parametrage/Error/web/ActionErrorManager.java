package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

 
 
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.model.ErrorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.service.ErrorService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Error.util.ErrorTemplate;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

public class ActionErrorManager extends ErrorTemplate {
	
	private ErrorService erService;
	

	@Autowired
	public void setErrorService(ErrorService erService) {
		this.erService = erService;
	}
 
	
	 
	 
 
	
 

 
	
 

	public ModelAndView doAddData(ErrorBean beanInsert ) {

	try {
		
	
	 
		if (erService.CreateRowData(beanInsert)) 
			 
		 throwNewException("Insertion Reussit");
		 else  
		 throwNewException("Echec d'insertion"); 
		 
		 
	  } catch (Exception e) {
		displayException(e); 
	  }
		 return getViewAdd(PAGE_FORM);
	}

 

	


	 

 
	 

}
