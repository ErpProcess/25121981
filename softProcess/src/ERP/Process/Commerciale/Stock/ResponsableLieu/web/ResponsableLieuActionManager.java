package ERP.Process.Commerciale.Stock.ResponsableLieu.web;
import java.lang.reflect.Field; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import org.json.JSONException; 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.servlet.ModelAndView;  

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.ResponsableLieu.model.ResponsableLieuBean;
import ERP.Process.Commerciale.Stock.ResponsableLieu.template.ResponsableLieuTemplate;
import ERP.Process.Commerciale.Stock.ResponsableLieu.service.ResponsableLieuService;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.service.UtilisateurService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.template.UtilisateurTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility; 
import com.google.gson.JsonIOException;
public class ResponsableLieuActionManager extends ResponsableLieuTemplate {
    
	private static final long serialVersionUID = 9109577368090391794L;
	private ResponsableLieuService  serviceResponsableLieu;
	@Autowired
	public void setServiceResponsableLieu(ResponsableLieuService serviceResponsableLieu) {
	    this.serviceResponsableLieu = serviceResponsableLieu;
	} 
	
	private      DepotStockageService       serviceDepotStockage;
	@Autowired
	public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
		this.serviceDepotStockage = serviceDepotStockage;
	}
	
	private UtilisateurService          serviceUtilisateur;
	@Autowired
	public void setServiceUtilisateur(UtilisateurService serviceUtilisateur) {
		this.serviceUtilisateur = serviceUtilisateur;
	}
	
	public    ModelAndView doInitServletAction() {
		 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			List <UtilisateurBean> list_uti= serviceUtilisateur.dofetchDatafromServer(UtilisateurBean.class.newInstance()) ;
			for (UtilisateurBean bb:list_uti) {
				bb.setNom_prenom(bb.getUsr_nom()+"   "+bb.getUsr_pre());
			}
			
			setObjectValueModel(UtilisateurTemplate.LIST_DATA      , list_uti);
			setObjectValueModel(LIST_DEPOT_STOCK               , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			
			if (bs.getFct_id().equals(Fn_Affecter)   ) {
				return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
			} else {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(ResponsableLieuBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceResponsableLieu.doFetchDatafromServer(searchBean);
				setObjectValueModel(SEARCH_BEAN, searchBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	public ModelAndView doAddData(ResponsableLieuBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceResponsableLieu.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(ResponsableLieuBean beanUpBean) {	 
		 	try {
		 serviceResponsableLieu.doUpdateRowData(beanUpBean); 
				update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(ResponsableLieuBean beanDelBean) {
	    try {
	     serviceResponsableLieu.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   } 
 }
