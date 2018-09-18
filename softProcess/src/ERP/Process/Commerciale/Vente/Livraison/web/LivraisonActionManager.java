package ERP.Process.Commerciale.Vente.Livraison.web;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.Commandeclient.template.CommandeclientTemplate;
import ERP.Process.Commerciale.Vente.Livraison.model.LivraisonBean;
import ERP.Process.Commerciale.Vente.Livraison.service.LivraisonService;
import ERP.Process.Commerciale.Vente.Livraison.template.LivraisonTemplate;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.model.TransportBean;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.service.TransportService;
import ERP.Process.Commerciale.Vente.Parametrage.Transport.template.TransportTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.service.ProcedureVenteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
public class LivraisonActionManager extends LivraisonTemplate {
	
 
	private static final long serialVersionUID = -8509385385453483963L;
	private LivraisonService  serviceLivraison;
	@Autowired
	public void setServiceLivraison(LivraisonService serviceLivraison) {
	    this.serviceLivraison = serviceLivraison;
	} 
	
	private ProcedureVenteService    serviceVente;
	@Autowired
	public void setServiceVente(ProcedureVenteService serviceVente) {
	    this.serviceVente = serviceVente;
	} 
	
	
	private TransportService  serviceTransport;
	@Autowired
	public void setServiceTransport(TransportService serviceTransport) {
	    this.serviceTransport = serviceTransport;
	} 
	
	
	public    ModelAndView doInitServletAction() {
		 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			doLoadingLibelleOtherSModule(TransportTemplate.ID_SOUS_MODULE);
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			
			setObjectValueModel(LIST_TRANSPORT_LIV,serviceTransport.doFetchDatafromServer(new TransportBean()));
			 
			ProcedureVenteBean beanSearch  =  new ProcedureVenteBean();
			beanSearch.setCondition_select_mode("  AND  bean.modeBean.fct_id in ('"+Fn_Facturer+"')   AND   bean.liv_id is null  ");
			List list_des_vente=serviceVente.doFetchDatafromServer(beanSearch);
			setObjectValueModel(LIST_VENTE_A_LIVRER,list_des_vente);
			
			
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
	
	
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(LivraisonBean searchBean)throws Throwable {
			try {
				List listDataSrv = serviceLivraison.doFetchDatafromServer(searchBean);
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
	public ModelAndView doAddData(LivraisonBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceLivraison.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(LivraisonBean beanUpBean) {	 
		 	try {
		 serviceLivraison.doUpdateRowData(beanUpBean); 
				update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(LivraisonBean beanDelBean) {
	    try {
	     serviceLivraison.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
