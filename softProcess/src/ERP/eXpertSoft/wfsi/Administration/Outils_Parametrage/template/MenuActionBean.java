package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template;


 

 

public class MenuActionBean {
	
	private String title = "";
	
	private String act_doValid = "";
	private String act_doReset = "";
	private String act_doRetour = "";
	private String act_doCommit = "";
	private String act_doRollback= "";
	
	private String toolbarBttn= "block";
	
	
	 

	private String btfirst= "true";
	private String btPrevious= "true";
	private String btNext= "true";
	private String btLast= "true";
	
	private String commit= "true";
	private String fctdoCommit  ="doCommitAction()";
	
	 
	
	private String fctdoRollback="doRollbackAction()";
	private String rollback= "true";
	
	
	private String btAide= "true";
	private String btTrace= "true";

	private String btPdf= "true";
	private String btExcel= "true";
	
	

	
	

	private String btValid= "false";
	private String btReset= "false";
	private String btRetour= "false";
	
	
	private String btNavigation= "false";
	private String toolbar= "none";

	
	private String typebtn= "submit";
 
	
	
	private String act_doSelectRow = "";
	private String doDefineJsp = "";
	private String libelleAction = "";
	private String libelleCommit = "Commettre";
	private String libelleRollback  = "r.back";
	
	 
	
	
	
	private String iconAction = "Check.png"; 
	private String hiddenAction = "";
	private String url = "";
	private String url_Load_Ajax = "";
	
	
	private String act_doFirst = "";
	private String act_doPrev = "";
	private String act_doNext = "";
	private String act_doLast = "";
 
	
	
	
	private String displayiii ="";
	private String displaybtValide="";
	private String displaybtReset="";
	private String displaybtRetour="";
	
	private String fctdoValid ="doValidAction()";
	private String fctdoReset ="doResetAction()";
	private String fctdoRetour ="doRetourAction()";
	
	
	private String fctdoExcel  = "doGetExcel()";
	private String fctdoPdf    = "doGetPdf()";
	private String act_doPdf   = "i$_ACT_PRINT_PDF";
	private String act_doExcel = "i$_ACT_EXPORT_XLS";
	
	
	
	private String fctdoFisrt ="doGetFirst()";
	private String fctdoLast ="doGetLast()";
	private String fctdoPrevieus ="doGetPrevieu()";
	private String fctdoNext ="doGetNext()";
	private boolean readonly =false;
	private boolean disabled =false;
	private boolean idReadonly =false;
	
	
	private String urlAjax ="";
	
	private String tabStyleBt [];
	
	
	public String getCommit() {
		return commit;
	}
	public void setCommit(String commit) {
		this.commit = commit;
	}
	public String getRollback() {
		return rollback;
	}
	public void setRollback(String rollback) {
		this.rollback = rollback;
	}
	
	
	
	public String getDisplaybtValide() {
		return displaybtValide;
	}
	public void setDisplaybtValide(String displaybtValide) {
		this.displaybtValide = displaybtValide;
	}
	public String getDisplaybtReset() {
		return displaybtReset;
	}
	public void setDisplaybtReset(String displaybtReset) {
		this.displaybtReset = displaybtReset;
	}
	public String getDisplaybtRetour() {
		return displaybtRetour;
	}
	public void setDisplaybtRetour(String displaybtRetour) {
		this.displaybtRetour = displaybtRetour;
	}
	public String getDisplayiii() {
		return displayiii;
	}
	public void setDisplayiii(String displayiii) {
		this.displayiii = displayiii;
	}
	public String getDoDefineJsp() {
		return doDefineJsp;
	}
	public void setDoDefineJsp(String doDefineJsp) {
		this.doDefineJsp = doDefineJsp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	 
	 
	public String getAct_doValid() {
		return act_doValid;
	}
	public void setAct_doValid(String act_doValid) {
		this.act_doValid = act_doValid;
	}
	public String getAct_doReset() {
		return act_doReset;
	}
	public void setAct_doReset(String act_doReset) {
		this.act_doReset = act_doReset;
	}
	public String getAct_doRetour() {
		return act_doRetour;
	}
	public void setAct_doRetour(String act_doRetour) {
		this.act_doRetour = act_doRetour;
	}
	public String getAct_doSelectRow() {
		return act_doSelectRow;
	}
	public void setAct_doSelectRow(String act_doSelectRow) {
		this.act_doSelectRow = act_doSelectRow;
	}
	public String getLibelleAction() {
		return libelleAction;
	}
	public void setLibelleAction(String libelleAction) {
		this.libelleAction = libelleAction;
	}
	public String[] getTabStyleBt() {
		return tabStyleBt;
	}
	public void setTabStyleBt(String[] tabStyleBt) {
		this.tabStyleBt = tabStyleBt;
	}
	public String getFctdoValid() {
		return fctdoValid;
	}
	public void setFctdoValid(String fctdoValid) {
		this.fctdoValid = fctdoValid;
	}
	public String getFctdoReset() {
		return fctdoReset;
	}
	public void setFctdoReset(String fctdoReset) {
		this.fctdoReset = fctdoReset;
	}
	public String getFctdoRetour() {
		return fctdoRetour;
	}
	public void setFctdoRetour(String fctdoRetour) {
		this.fctdoRetour = fctdoRetour;
	}
	public String getFctdoExcel() {
		return fctdoExcel;
	}
	public void setFctdoExcel(String fctdoExcel) {
		this.fctdoExcel = fctdoExcel;
	}
	public String getFctdoPdf() {
		return fctdoPdf;
	}
	public void setFctdoPdf(String fctdoPdf) {
		this.fctdoPdf = fctdoPdf;
	}
	public String getFctdoFisrt() {
		return fctdoFisrt;
	}
	public void setFctdoFisrt(String fctdoFisrt) {
		this.fctdoFisrt = fctdoFisrt;
	}
	public String getFctdoLast() {
		return fctdoLast;
	}
	public void setFctdoLast(String fctdoLast) {
		this.fctdoLast = fctdoLast;
	}
	public String getFctdoPrevieus() {
		return fctdoPrevieus;
	}
	public void setFctdoPrevieus(String fctdoPrevieus) {
		this.fctdoPrevieus = fctdoPrevieus;
	}
	public String getFctdoNext() {
		return fctdoNext;
	}
	public void setFctdoNext(String fctdoNext) {
		this.fctdoNext = fctdoNext;
	}
	public String getDoFirst() {
		return act_doFirst;
	}
	public void setDoFirst(String doFirst) {
		this.act_doFirst = doFirst;
	}
	public String getDoPrev() {
		return act_doPrev;
	}
	public void setDoPrev(String doPrev) {
		this.act_doPrev = doPrev;
	}
	public String getDoNext() {
		return act_doNext;
	}
	public void setDoNext(String doNext) {
		this.act_doNext = doNext;
	}
	public String getDoLast() {
		return act_doLast;
	}
	public void setDoLast(String doLast) {
		this.act_doLast = doLast;
	}
	 
	 
	public String getHiddenAction() {
		return hiddenAction;
	}
	public void setHiddenAction(String hiddenAction) {
		this.hiddenAction = hiddenAction;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAct_doFirst() {
		return act_doFirst;
	}
	public void setAct_doFirst(String act_doFirst) {
		this.act_doFirst = act_doFirst;
	}
	public String getAct_doPrev() {
		return act_doPrev;
	}
	public void setAct_doPrev(String act_doPrev) {
		this.act_doPrev = act_doPrev;
	}
	public String getAct_doNext() {
		return act_doNext;
	}
	public void setAct_doNext(String act_doNext) {
		this.act_doNext = act_doNext;
	}
	public String getAct_doLast() {
		return act_doLast;
	}
	public void setAct_doLast(String act_doLast) {
		this.act_doLast = act_doLast;
	}
	public String getToolbarBttn() {
		return toolbarBttn;
	}
	public void setToolbarBttn(String toolbarBttn) {
		this.toolbarBttn = toolbarBttn;
	}
	public String getBtfirst() {
		return btfirst;
	}
	public void setBtfirst(String btfirst) {
		this.btfirst = btfirst;
	}
	public String getBtPrevious() {
		return btPrevious;
	}
	public void setBtPrevious(String btPrevious) {
		this.btPrevious = btPrevious;
	}
	public String getBtNext() {
		return btNext;
	}
	public void setBtNext(String btNext) {
		this.btNext = btNext;
	}
	public String getBtLast() {
		return btLast;
	}
	public void setBtLast(String btLast) {
		this.btLast = btLast;
	}
	public String getBtAide() {
		return btAide;
	}
	public void setBtAide(String btAide) {
		this.btAide = btAide;
	}
	public String getBtTrace() {
		return btTrace;
	}
	public void setBtTrace(String btTrace) {
		this.btTrace = btTrace;
	}
	public String getBtPdf() {
		return btPdf;
	}
	public void setBtPdf(String btPdf) {
		this.btPdf = btPdf;
	}
	public String getBtExcel() {
		return btExcel;
	}
	public void setBtExcel(String btExcel) {
		this.btExcel = btExcel;
	}
	public String getBtValid() {
		return btValid;
	}
	public void setBtValid(String btValid) {
		this.btValid = btValid;
	}
	public String getBtReset() {
		return btReset;
	}
	public void setBtReset(String btReset) {
		this.btReset = btReset;
	}
	public String getBtRetour() {
		return btRetour;
	}
	public void setBtRetour(String btRetour) {
		this.btRetour = btRetour;
	}
	public String getBtNavigation() {
		return btNavigation;
	}
	public void setBtNavigation(String btNavigation) {
		this.btNavigation = btNavigation;
	}
	public String getToolbar() {
		return toolbar;
	}
	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}
	public String getUrl_Load_Ajax() {
		return url_Load_Ajax;
	}
	public void setUrl_Load_Ajax(String url_Load_Ajax) {
		this.url_Load_Ajax = url_Load_Ajax;
	}
	public String getTypebtn() {
		return typebtn;
	}
	public void setTypebtn(String typebtn) {
		this.typebtn = typebtn;
	}
	 
	public String getUrlAjax() {
		return urlAjax;
	}
	public void setUrlAjax(String urlAjax) {
		this.urlAjax = urlAjax;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getIconAction() {
		return iconAction;
	}
	public void setIconAction(String iconAction) {
		this.iconAction = iconAction;
	}
	public String getFctdoCommit() {
		return fctdoCommit;
	}
	public void setFctdoCommit(String fctdoCommit) {
		this.fctdoCommit = fctdoCommit;
	}
	public String getFctdoRollback() {
		return fctdoRollback;
	}
	public void setFctdoRollback(String fctdoRollback) {
		this.fctdoRollback = fctdoRollback;
	}
	public boolean isIdReadonly() {
		return idReadonly;
	}
	public void setIdReadonly(boolean idReadonly) {
		this.idReadonly = idReadonly;
	}
	public String getAct_doPdf() {
		return act_doPdf;
	}
	public void setAct_doPdf(String act_doPdf) {
		this.act_doPdf = act_doPdf;
	}
	public String getAct_doExcel() {
		return act_doExcel;
	}
	public void setAct_doExcel(String act_doExcel) {
		this.act_doExcel = act_doExcel;
	}
	public String getLibelleCommit() {
		return libelleCommit;
	}
	public void setLibelleCommit(String libelleCommit) {
		this.libelleCommit = libelleCommit;
	}
	public String getLibelleRollback() {
		return libelleRollback;
	}
	public void setLibelleRollback(String libelleRollback) {
		this.libelleRollback = libelleRollback;
	}
	public String getAct_doCommit() {
		return act_doCommit;
	}
	public void setAct_doCommit(String act_doCommit) {
		this.act_doCommit = act_doCommit;
	}
	public String getAct_doRollback() {
		return act_doRollback;
	}
	public void setAct_doRollback(String act_doRollback) {
		this.act_doRollback = act_doRollback;
	}
	 
 
}
