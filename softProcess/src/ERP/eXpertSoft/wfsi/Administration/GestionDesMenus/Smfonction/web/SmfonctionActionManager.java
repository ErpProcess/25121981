package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.web;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jquery.datatables.controller.EditableDataTableRequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.service.FonctionService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model.SmfonctionModel;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.service.SmfonctionService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.template.SmfonctionTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.service.SousModuleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.DynamicClass;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class SmfonctionActionManager extends SmfonctionTemplate {
	
	private SmfonctionService   serviceSmfonction;
	private FonctionService     serviceFonction;
	private SousModuleService   serviceSousModule;
	
	@Autowired
	public void setServiceSousModule(SousModuleService serviceSousModule) {
		this.serviceSousModule = serviceSousModule;
	}
	
	@Autowired
	public void setServiceFonction(FonctionService serviceFonction) {
		this.serviceFonction = serviceFonction;
	}
	
	@Autowired
	public void setServiceSmfonction(SmfonctionService serviceSmfonction) {
		this.serviceSmfonction = serviceSmfonction;
	}

	public ModelAndView doInitServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		setObjectValueModel("listSousModuleForAfection", serviceSousModule.getSousModuleList(new SousModuleBean()));
		BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		try {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(SmfonctionModel searchModel)
			throws Throwable {
		try {
			SousModuleBean smsearch=new SousModuleBean();
			smsearch.setSousmod_id(searchModel.getPk().getSoumBean().getSousmod_id());
			List lisSOumodule=serviceSousModule.getSousModuleList(smsearch);
			setObjectValueModel(LIST_DATA,lisSOumodule);
			setObjectValueModel(SEARCH_BEAN, searchModel);
			AjaxDataTablesUtility.doInitJQueryGrid(lisSOumodule);
		} catch (JsonIOException e) {
			displayException(e, HTML_CONTENT_TYPE);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public    ModelAndView doSelectRowSmfctmod() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			SousModuleBean rowBean = (SousModuleBean) getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));

			
			SmfonctionModel searchModel=new SmfonctionModel();
			searchModel.getPk().getSoumBean().setSousmod_id(rowBean.getSousmod_id());
			
			
			List ListOrigine= serviceSmfonction.dofetchDatafromServer(searchModel);
			setObjectValueModel("ListOrigine", ListOrigine);
			
			List ListOrigine_for_supp=ProcessUtil.cloneList(ListOrigine);
			 
			
			
			setObjectValueModel("list_Grid_Editable_For_Annuler", ListOrigine_for_supp);
			HashMap mapdataToo=new HashMap();
			for (int i = 0; i < ListOrigine.size(); i++) {
				SmfonctionModel bSmfonctionModel=(SmfonctionModel) ListOrigine.get(i) ;
				if(bSmfonctionModel.getPk().getSoumBean().getSousmod_id().equals(rowBean.getSousmod_id())){
					bSmfonctionModel.setTo_check("yes");
					mapdataToo.put(bSmfonctionModel.getPk().getFcBean().getFct_id(), bSmfonctionModel);
				}
			}
			
			
			
			
			
			
			
			List list_fct=serviceFonction.getFonctionList(new FonctionBean());
			List listGridEditable= new ArrayList();
			
			
			
			
			for (int i = 0; i < list_fct.size(); i++) {
				FonctionBean bFonctionBean=(FonctionBean) list_fct.get(i);
				SmfonctionModel beanInset=(SmfonctionModel) mapdataToo.get(bFonctionBean.getFct_id());
				if(beanInset== null){
					SmfonctionModel beanIns= new 	SmfonctionModel();
					beanIns.setTo_check("no");
					beanIns.getPk().getFcBean().setFct_id(bFonctionBean.getFct_id());
					beanIns.getPk().getSoumBean().setSousmod_id(rowBean.getSousmod_id());
					beanIns.getPk().getFcBean().setFct_libelle(bFonctionBean.getFct_libelle());
					beanIns.setOrdre(new Integer(0));
					listGridEditable.add(beanIns);
				}else{
					Integer ordre=beanInset.getOrdre()==null?new Integer(0):beanInset.getOrdre();
					beanInset.setOrdre(ordre);
					listGridEditable.add(beanInset);
				}
			}
			 
			
			
			setObjectValueModel("listGridEditable_smfction", listGridEditable);
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("6"))
				return getView_Afectation(FORM_VIEW);
			else
				return getViewDelete(FORM_VIEW_ANNULER);
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax(FILTER_VIEW);
		}
		 
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doUpdateEditableTable() throws Throwable, Throwable{
			
			String sNameList   = getRequest().getParameter("sNameList")  ==null?"":getRequest().getParameter("sNameList");
			String sNameId     = getRequest().getParameter("sNameId")    ==null?"":getRequest().getParameter("sNameId");
			String name_column = getRequest().getParameter("sNameColumn")==null?"":getRequest().getParameter("sNameColumn");
			String sValueId    = getRequest().getParameter("sValueId")   ==null?"":getRequest().getParameter("sValueId");
			String value       = getRequest().getParameter("sDataValue") ==null?"":getRequest().getParameter("sDataValue");
			List  listOfmyData=(List) getObjectValueModel(sNameList);
			DynamicClass  dynamicClass= new DynamicClass();
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				
				String typefiledid="";
				Object  bean=(Object) listOfmyData.get(i);
					Object element;
					if(sNameId.indexOf(".")>0){
						final String[] lesColunmooo = sNameId.split("\\.");
						 Object object=bean;
						    for (int k = 0; k < lesColunmooo.length; k++) {
						    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
								  fieldo.setAccessible(true);
								  Object obj=fieldo.get(object);
								  object=obj;
								  if(k==lesColunmooo.length-1)typefiledid=fieldo.getType().getName();
								  
							}
			    		   element =object;
					}else{
					Field field = bean.getClass().getDeclaredField(sNameId);
					field.setAccessible(true);
					element = field.get(bean);
					typefiledid=field.getType().getName();
					}
				     Object obNew=dynamicClass.castDynamicClassX(typefiledid, sValueId);
					if( element.equals(obNew)){
						Object  objetToReturn =null;
						if(name_column.indexOf(".")>0){
							final String[] lesColunmooo = name_column.split("\\.");
							 Object object=bean;
							    for (int k = 0; k < lesColunmooo.length; k++) {
							    	  Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
									  fieldo.setAccessible(true);
									  Object obj=fieldo.get(object);
									  object=obj;
									  if(k==lesColunmooo.length-1){
										  objetToReturn=dynamicClass.castDynamicClassX(fieldo.getType().getName(), value);
										  fieldo.set(object, objetToReturn); 
									  }
								}
							    
						}else{
							Field field = bean.getClass().getDeclaredField(name_column);
							field.setAccessible(true);
							objetToReturn=dynamicClass.castDynamicClassX(field.getType().getName(), value);
							field.set(bean, objetToReturn);
						}
							getResponse().getWriter().print(objetToReturn);
							return null ;
				     }
				}
			getResponse().getWriter().print("Error - Object cannot be found");
			return null ;
	}

	public ModelAndView doValider_AffectationData(SmfonctionModel detailModel) throws Exception {
		try {
			serviceSmfonction.doCreateRowData(detailModel);
			throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	
	public ModelAndView doDeleteData(SmfonctionModel beanDelBean) {
		try {
			serviceSmfonction.doDeleteRowData(beanDelBean);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	 

 
}
