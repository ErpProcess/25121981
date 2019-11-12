package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.web.ActionAuthentificationManager;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.MessageBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel.WriteExcel;


public class TemplateGeneric extends GenericActionBean {
	
 
	private static final long serialVersionUID = -6781871635009797460L;
	
	public static boolean ACT_NAVIGATE = false;

	public TemplateGeneric() {
		super();
	}
	public        ModelAndView  doForwardActionGeneric()  {
		  try {
			    ACT_NAVIGATE = false;
			   
		       if (i$_ACT_INIT_SERVLET )         return      doInit_ServletAction(); 
		       
		       
		       
		       if (i$_ACT_RESET_FORM)            return      doResetForm();                  
				
			   if (i$_ACT_RESET_FILTER)          return      doResetFilterView();            
			
			   if (i$_ACT_RETOUR_TO_HOME)        return      getHome();   
			
			   if (i$_ACT_RETOUR_TO_LIST)        return      doRetourToList();               
			
			   if (i$_ACT_RETOUR_TO_FILTER)      return      doRetourToFilterView(); 
			   
			   if (i$_ACT_CANCEL_UPDATE)         return      doCancelUpdate(); 
			
			   if (i$_ACT_SELECT_ROW)            return      doGetRowBean();                  
			
			   if (i$_ACT_GET_FIRST)             return      doGetFirst();                   
			
			   if (i$_ACT_GET_PREVIOUS)          return      doGetPrevious();                
			
			   if (i$_ACT_GET_NEXT)              return      doGetNext();                    
			
			   if (i$_ACT_GET_LAST)              return      doGetLast(); 
			   
			   if (i$_ACT_SET_TIME_OUT)          return      getTimeOut(); 
			   
			   if (i$_ACT_PRINT_PDF)             return      doPrintPDF();
			   
			   if (i$_ACT_CHANGE_FORMAT_PRINT)   return      doChangePrintFormat();
			   
			   
			   if (i$_ACT_EXPORT_XLS)            return      doExportXls();
			   
			   if (i$_ACT_PRINT_FILE_PDF)        return      doPrintFilePDF();
			   
			   if (i$_ACT_DELETE_FILE_PDF)       return      doDeleteFilePDF();
			   
			  
			   
		   } catch (Exception e) {
		     displayException((Exception) e);
		 }
		     return getTimeOut();
	  }
   public static ModelAndView Get_Model_generic(String isPage,MenuActionBean twebBean) {
			String path_view = (String) getObjectValueModel(PATH_JSP);
			setObjectViewName(getObjectValueModel(CONTEXT_PATH) + path_view+ isPage);
			setObjectValueModel(TEMP_MENU_ACTION_GEN, twebBean);
			return getModel();
		}	 
	public static ModelAndView Get_Model_ROOT(String isPage,MenuActionBean twebBean) {
		String path_view = (String) getObjectValueModel(ROOT);
		path_view = path_view.substring(1).replaceAll("root.action", "");
		setObjectViewName(getObjectValueModel(CONTEXT_PATH) + path_view+ isPage);
		setObjectValueModel(TEMP_MENU_ACTION_GEN, twebBean);
		return getModel();
	}

	public static  ModelAndView doInit_ServletAction() {
	 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			
			if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
				return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
			} else {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	


	public  static   ModelAndView doResetFilterView() {
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		return getViewFilter((String) getObjectValueModel("FILTER_VIEW"));
	}

	public  static  ModelAndView doRetourToFilterView() {
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		return getViewFilter((String) getObjectValueModel("FILTER_VIEW"));
	}
	
	public  static  ModelAndView doCancelUpdate() {
		Object  obj=getObjectValueModel(ORIGINAL_FORM_BEAN);
		setObjectValueModel(FORM_BEAN, obj);
		return getViewUpdate((String) getObjectValueModel("FORM_VIEW"));
	}

	public static  ModelAndView doRetourToList() {
		removeObjectModel(FORM_BEAN);
		return getViewList_Ajax((String) getObjectValueModel("FILTER_VIEW"));
	}

	public static  ModelAndView doResetForm() {
		removeObjectModel(FORM_BEAN);
		return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
	}
	
	

	public    ModelAndView doGetRowBean() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Object rowBean = getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("2"))
				return getViewConsult((String) getObjectValueModel("FORM_VIEW"));
			if (bs.getFct_id().equals("3"))
				return getViewUpdate((String) getObjectValueModel("FORM_VIEW"));
			if (bs.getFct_id().equals("4"))
				return getViewDelete((String) getObjectValueModel("FORM_VIEW"));
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}

	public    ModelAndView doGetFirst() {
		try {
			ACT_NAVIGATE = true;
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List listData = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			int index = Integer.parseInt(indexo);
			Object detailBean = listData.get(0);
			setObjectValueModel(INDEX_ROW, String.valueOf(0));
			setObjectValueModel(FORM_BEAN, detailBean);
		} catch (Exception e) {
			displayException(e);
		}
		return getViewConsult((String) getObjectValueModel("FORM_VIEW"));

	}

	public static  ModelAndView doGetPrevious() {

		try {
			ACT_NAVIGATE = true;
			int finich = 0;
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List listData = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			int index = Integer.parseInt(indexo);

			if (index == 0)
				finich = 0;
			else
				finich = index - 1;

			Object detailBean =  listData.get(finich);
			setObjectValueModel(INDEX_ROW, String.valueOf(finich));
			setObjectValueModel(FORM_BEAN, detailBean);
		} catch (Exception e) {
			displayException(e);
		}
		return getViewConsult((String)getObjectValueModel("FORM_VIEW"));

	}

	public static  ModelAndView doGetNext() {
		try {
			ACT_NAVIGATE = true;
			int finich = 0;
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List listData = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			//Object objCurrent=getObjectValueModel((String) getObjectValueModel(MODEL_BEAN));
			int index = Integer.parseInt(indexo);

			if (index == listData.size() - 1)
				finich = listData.size() - 1;
			else
				finich = index + 1;

			Object detailBean = listData.get(finich);
			setObjectValueModel(INDEX_ROW, String.valueOf(finich));
			setObjectValueModel(FORM_BEAN, detailBean);
		} catch (Exception e) {
			displayException(e);
		}
		return getViewConsult((String) getObjectValueModel("FORM_VIEW"));

	}

	public static  ModelAndView doGetLast() {
		try {
			ACT_NAVIGATE = true;
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List listData = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			int index = Integer.parseInt(indexo);
			Object detailBean = listData.get(listData.size() - 1);
			setObjectValueModel(FORM_BEAN, detailBean);
			setObjectValueModel(INDEX_ROW, String.valueOf(listData.size() - 1));

		} catch (Exception e) {
			displayException(e);
		}
		return getViewConsult((String) getObjectValueModel("FORM_VIEW"));

	}

	
	public static  ModelAndView doPrintPDF() {
	 
			List   lisData                         =    (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G)) ;
			String [][]    mapFieldBean            =    (String[][]) getObjectValueModel(MAP_FIELD_BEAN) ;
			GeneratePdf  genpdf= new GeneratePdf();
			try {
				genpdf.createGenericPdfDocument(lisData,mapFieldBean);
				getResponse().setContentType("text");
				getResponse().setHeader("Cache-Control", "no-cache");
				getResponse().setStatus(200);
				getResponse().getWriter().write((String) getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
			} catch (Throwable e) {
				displayException((Exception) e);
			} 
		return null;

	}
	
public static ModelAndView doChangePrintFormat( ) throws Exception{
		
		try {
			  String formatPrint   = getRequest().getParameter("newFormat")   == null ? "" : getRequest().getParameter("newFormat");
			  BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			  bs.setFormatPrint(formatPrint);
			  
			  if(formatPrint=="paysage") {
				  bs.setFormatPrintIcon("&#9668;&#9658;");
			  }else {
				  bs.setFormatPrintIcon("&#9650;&#9660;");
				  
			  }
			  setObjectValueModel(BEAN_SESSION, bs);
			  getResponse().setContentType(HTML_CONTENT_TYPE);
			  getResponse().getWriter().print(formatPrint);
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
	public static  ModelAndView doExportXls() {
		try {

			List       lisData         =  (List)    getObjectValueModel((String) getObjectValueModel(NAME_LIST_G)) ;
			String [][]    mapFieldBean    = (String[][]) getObjectValueModel(MAP_FIELD_BEAN) ;
			
			WriteExcel  dbexp= new WriteExcel();
			dbexp.doExportExcel(lisData,mapFieldBean);
		} catch (Exception e) {
			displayException(e);
		}
		return null;

	}
	
	public static  ModelAndView doDeleteFilePDF() {
		 
		 
		try {
			
		 	String thePathfile=getRequest().getParameter("pathFileToDelete");
			File file = new File(getRequest().getRealPath("/")+"/temp/"+thePathfile);
			if(file.delete()){
				System.out.println(file.getName() + " is deleted!");
			}else{
				System.out.println("Delete operation is failed.");
			} 
			
 
			
			
		} catch (Throwable e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

}
	
	
	public static  ModelAndView doPrintFilePDF() {
		 
		 
		try {
	 
			
	 	String thePathfile=getRequest().getParameter("pathFileToDelete");
			if(Desktop.isDesktopSupported()){
				if(Desktop.getDesktop().isSupported(java.awt.Desktop.Action.PRINT)){
				try {
					java.awt.Desktop.getDesktop().print(new File(getRequest().getRealPath("/")+"/temp/"+thePathfile));
				} catch (IOException ex) {
				    
				}
				} else {
					 
				}
			}else {
				 
			} 
			
		 
			
		} catch (Throwable e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

}
	
	
	
	
	 
	public static ModelAndView getViewIndexxss() {
		ModelAndView mode = new ModelAndView();
		MessageBean error = new MessageBean();
		if (getModel() == null){
			error.setMessage("Votre Session a été abondanée, Identifiez vous de nouveau !");
		}else{
			//session.invalidate();
			error.setMessage("");
		}
		mode.addObject(MESSAGERROR, error.getMessage());
		mode.setViewName(getRequest().getContextPath() + ActionAuthentificationManager.PATH + "Login");
		return mode;
	}

	
	public static ModelAndView  getTimeOut() {
		
		if (getModel() == null){
			MessageBean error = new MessageBean();
			ModelAndView mode = new ModelAndView();
			error.setMessage("Votre Session a été abondanée, Identifiez vous de nouveau ");
			mode.addObject(MESSAGERROR, error.getMessage());
			mode.setViewName(getRequest().getContextPath() + ActionAuthentificationManager.PATH + "next");
			return mode;
		}else{
			ModelAndView mod=getModel();
			mod.setViewName(getRequest().getContextPath() + ActionAuthentificationManager.PATH + "next");
			return mod;
		}
		
		
	}
	
	public static ModelAndView getHome() {
		
		
		if (getModel() == null){
			MessageBean error = new MessageBean();
			ModelAndView mode = new ModelAndView();
			error.setMessage("Votre Session a été abondanée, Identifiez vous de nouveau !");
			//getSession().invalidate();
			mode.addObject(MESSAGERROR, error.getMessage());
			return null;
		}else{
			MenuActionBean twebBean = new MenuActionBean();
			twebBean.setToolbarBttn(NONE);
			removeObjectModel(FORM_BEAN);
			setObjectViewName(getObjectValueModel(CONTEXT_PATH) + "Aceuil/Aceuil");
			setObjectValueModel(MENU_ACTION_DISPLAY, twebBean);
			return getModel();
		}
		
		
		
	}

	public static ModelAndView getViewAdd(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setAct_doValid("i$_ACT_ADD");
		twebBean.setAct_doReset("i$_ACT_RESET_FORM");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setLibelleAction(BTN_ADD);
		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		twebBean.setBtNavigation(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	 
	public static ModelAndView getViewFilter(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setAct_doValid("i$_ACT_FETCH_AJAX");
		twebBean.setAct_doReset("i$_ACT_RESET_FILTER");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
	}

	public static ModelAndView getViewFilterAjax(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setAct_doValid("i$_ACT_FETCH_AJAX");
		twebBean.setAct_doReset("i$_ACT_RESET_FILTER");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setIconAction("Search.png");
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		twebBean.setBtNavigation(FALSE);
		twebBean.setBtExcel(TRUE);
		twebBean.setBtPdf(TRUE);
		twebBean.setTypebtn(button);
		twebBean.setFctdoValid("ControlDisplayTableE()");
		twebBean.setFctdoReset("doResetAjaxData()");
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	public static ModelAndView getViewFilterFacture(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setAct_doValid("i$_ACT_FETCH_AJAX_VENTE");
		twebBean.setAct_doReset("i$_ACT_RESET_FILTER");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setIconAction("Search.png");
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		twebBean.setBtNavigation(FALSE);
		twebBean.setBtExcel(TRUE);
		twebBean.setBtPdf(TRUE);
		twebBean.setTypebtn(button);
		twebBean.setFctdoValid("ControlDisplayTableE()");
		twebBean.setFctdoReset("doResetAjaxData()");
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	
	public static ModelAndView getViewAdd_Commit(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setAct_doValid("i$_ACT_ADD");
		twebBean.setAct_doCommit("i$_ACT_COMMIT");
		twebBean.setAct_doReset("i$_ACT_RESET_FORM");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setLibelleAction(BTN_ADD);
		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);
		twebBean.setBtValid(TRUE);
		twebBean.setBtReset(TRUE);
		twebBean.setBtRetour(TRUE);
		twebBean.setCommit(FALSE);
		twebBean.setBtNavigation(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	
	public static ModelAndView getViewAfterAdd(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setAct_doValid("i$_ACT_ADD");
		twebBean.setAct_doCommit("i$_ACT_COMMIT");
		twebBean.setAct_doReset("i$_ACT_RESET_FORM");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");
		twebBean.setLibelleAction(BTN_ADD);
		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);
		twebBean.setBtValid(TRUE);
		twebBean.setBtReset(TRUE);
		twebBean.setBtRetour(TRUE);
		twebBean.setCommit(TRUE);
		twebBean.setBtNavigation(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
	}

	public static ModelAndView getViewList_Ajax(String isPage) {

		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setAct_doValid("i$_ACT_FETCH_AJAX");
		twebBean.setAct_doReset("i$_ACT_RESET_FILTER");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_HOME");

		twebBean.setBtValid(TRUE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);

		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);

		twebBean.setBtNavigation(FALSE);
		twebBean.setBtExcel(FALSE);
		twebBean.setBtPdf(FALSE);
		twebBean.setTypebtn(button);
		twebBean.setFctdoValid("ControlDisplayTableE()");
		twebBean.setFctdoReset("doResetAjaxData()");
		return Get_Model_ROOT(isPage, twebBean);
	}

	public static ModelAndView getViewListAjax(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();

		twebBean.setAct_doValid("i$_ACT_FETCH_AJAX");
		twebBean.setAct_doSelectRow("i$_ACT_SELECT_ROW");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_FILTER");

		twebBean.setBtValid(TRUE);
		twebBean.setBtReset(TRUE);
		twebBean.setBtRetour(FALSE);
		twebBean.setFctdoValid("ControlDisplayTableE()");
		twebBean.setFctdoReset("doResetAjaxData()");
		return Get_Model_ROOT(isPage, twebBean);
	}

	public static ModelAndView getViewConsult(String isPage) {

		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setBtValid(TRUE);
		twebBean.setBtReset(TRUE);
		twebBean.setBtRetour(FALSE);
        String index=(String) getObjectValueModel(INDEX_ROW);
        List lis= (List) getObjectValueModel((String)getObjectValueModel(NAME_LIST_G));
        index=index==null?"0":index;
		if(index!=null && Integer.parseInt(index)==0){
		twebBean.setBtPrevious(TRUE);
		twebBean.setBtfirst(TRUE);
		twebBean.setBtNext(FALSE);
		twebBean.setBtLast(FALSE);
		}else if(index!=null &&  Integer.parseInt(index)==lis.size()-1){
			twebBean.setBtPrevious(FALSE);
			twebBean.setBtfirst(FALSE);
			twebBean.setBtNext(TRUE);
			twebBean.setBtLast(TRUE);
		}else{
			twebBean.setBtPrevious(FALSE);
			twebBean.setBtfirst(FALSE);
			twebBean.setBtNext(FALSE);
			twebBean.setBtLast(FALSE);
		}
		twebBean.setBtTrace(FALSE);
		twebBean.setBtAide(FALSE);
		twebBean.setDisabled(true); 
		twebBean.setReadonly(true);
		twebBean.setIdReadonly(true);
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setBtRetour(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	public   ModelAndView getViewConsult_Pdf_ex(String isPage) {

		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setLibelleAction(BTN_SEARCH);
		twebBean.setBtValid(TRUE);
		twebBean.setBtReset(TRUE);
		twebBean.setBtRetour(FALSE);
	    String index=(String) getObjectValueModel(INDEX_ROW);
	    List lis= (List) getObjectValueModel((String)getObjectValueModel(NAME_LIST_G));
	    index=index==null?"0":index;
		if(index!=null && Integer.parseInt(index)==0){
		twebBean.setBtPrevious(TRUE);
		twebBean.setBtfirst(TRUE);
		twebBean.setBtNext(FALSE);
		twebBean.setBtLast(FALSE);
		}else if(index!=null &&  Integer.parseInt(index)==lis.size()-1){
			twebBean.setBtPrevious(FALSE);
			twebBean.setBtfirst(FALSE);
			twebBean.setBtNext(TRUE);
			twebBean.setBtLast(TRUE);
		}else{
			twebBean.setBtPrevious(FALSE);
			twebBean.setBtfirst(FALSE);
			twebBean.setBtNext(FALSE);
			twebBean.setBtLast(FALSE);
		}
		twebBean.setBtTrace(FALSE);
		twebBean.setBtAide(FALSE);
		twebBean.setDisabled(true); 
		twebBean.setReadonly(true);
		twebBean.setIdReadonly(true);
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setBtRetour(FALSE);
		twebBean.setAct_doExcel("i$_ACT_EXPORT_XLS_DETAILLE");
		twebBean.setAct_doPdf("i$_ACT_PRINT_PDF_DETAILLE");
		twebBean.setBtExcel(FALSE);
		twebBean.setBtPdf(FALSE);
		
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	
	public static ModelAndView getViewUpdate(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();

		twebBean.setLibelleAction(BTN_UPDATE);
		twebBean.setAct_doValid("i$_ACT_UPDATE");
		twebBean.setAct_doReset("i$_ACT_CANCEL_UPDATE");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setFctdoValid("doValidAction()");

		 
		twebBean.setIdReadonly(true);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		return Get_Model_ROOT(isPage, twebBean);

	}
	
	
	public static ModelAndView getViewCorriger(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();

		twebBean.setLibelleAction(BTN_UPDATE);
		twebBean.setAct_doValid("i$_ACT_CORRIGER");
		twebBean.setAct_doReset("i$_ACT_CANCEL_UPDATE");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setFctdoValid("doValidAction()");

		 
		twebBean.setIdReadonly(true);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(FALSE);
		twebBean.setBtRetour(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
	}

	public static ModelAndView getViewDelete(String isPage) {
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setTypebtn(submit);
		twebBean.setLibelleAction(BTN_DELETE);
		twebBean.setAct_doValid("i$_ACT_DELETE");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(TRUE);
		twebBean.setBtRetour(FALSE);
		twebBean.setIdReadonly(true);
		twebBean.setDisabled(true); 
		twebBean.setReadonly(true);
		return Get_Model_ROOT(isPage, twebBean);
	}
	
	
	
	
	
	
	
public static ModelAndView getViewAnnuler(String isPage) {
		
		MenuActionBean twebBean = new MenuActionBean();
		twebBean.setAct_doValid("i$_ACT_ANNULER");
		twebBean.setAct_doReset("");
		twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
		twebBean.setLibelleAction("Valider-annulation");
		twebBean.setBtAide(FALSE);
		twebBean.setBtTrace(FALSE);
		twebBean.setBtValid(FALSE);
		twebBean.setBtReset(TRUE);
		twebBean.setBtRetour(FALSE);
		twebBean.setBtNavigation(FALSE);
		return Get_Model_ROOT(isPage, twebBean);
		
		
	}

public static ModelAndView getViewValider(String isPage) { 
	
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_VALIDER");
	twebBean.setAct_doReset("");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setLibelleAction(BTN_VALID);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(FALSE);
	return Get_Model_ROOT(isPage, twebBean);
}
public static ModelAndView getViewTransfer(String isPage) {
	
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_TRANSFER");
	twebBean.setAct_doReset("");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setLibelleAction(BTN_VALID);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(FALSE);
	return Get_Model_ROOT(isPage, twebBean);
}
public static ModelAndView getViewReception(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setTypebtn(submit);
	twebBean.setLibelleAction("Valider-Réception");
	twebBean.setAct_doValid("i$_ACT_RECEPTION");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);
}

public static ModelAndView getViewConservData(String isPage) {
	MenuActionBean twebBean = new MenuActionBean();

	twebBean.setTypebtn(submit);
	twebBean.setLibelleAction("Appliquer-Stock");
	twebBean.setAct_doValid("i$_ACT_CONSERV");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	return Get_Model_ROOT(isPage, twebBean);
}

public static ModelAndView getViewConfirm(String isPage) { 
	
	MenuActionBean twebBean = new MenuActionBean();
	twebBean.setAct_doValid("i$_ACT_CONFIRM");
	twebBean.setAct_doReset("");
	twebBean.setAct_doRetour("i$_ACT_RETOUR_TO_LIST");
	twebBean.setLibelleAction(BTN_VALID);
	twebBean.setBtAide(FALSE);
	twebBean.setBtTrace(FALSE);
	twebBean.setBtValid(FALSE);
	twebBean.setBtReset(TRUE);
	twebBean.setBtRetour(FALSE);
	twebBean.setBtNavigation(FALSE);
	
	twebBean.setIdReadonly(true);
	twebBean.setDisabled(true); 
	twebBean.setReadonly(true);
	

	
	return Get_Model_ROOT(isPage, twebBean);
}


}
