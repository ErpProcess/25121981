package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TimeOut.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.service.SpoorService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TimeOut.util.TimeOutTemplate;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.model.JQueryDataTableParamModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class ActionTimeOutManager extends TimeOutTemplate {
	
	 private SpoorService serviceSpoor;

	@Autowired
	public void setSpoorService(SpoorService serviceSpoor) {
		this.serviceSpoor = serviceSpoor;
	}

	 


 
	
	
	 
  
	public ModelAndView doAddData(SpoorBean beanInsert ) {

	try {
		if (serviceSpoor.CreateRowData(beanInsert)) 
			 
		 throwNewException("Insertion Reussit");
		 else  
		 throwNewException("Echec d'insertion"); 
		 
		 
	  } catch (Exception e) {
		displayException(e); 
	  }
		 return getViewAdd(PAGE_FORM);
	}
	
	 
	
	

 


	public ActionTimeOutManager() {
		super();
	}

	 

 
	 

}
