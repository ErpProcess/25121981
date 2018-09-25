package ERP.Process.Commerciale.Vente.EditionVente.web;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import ERP.Process.Commerciale.Vente.EditionVente.model.EditionVenteBean;
import ERP.Process.Commerciale.Vente.EditionVente.template.EditionVenteTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.service.Facture_clientService;
import ERP.Process.Commerciale.Vente.EditionVente.service.EditionVenteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
import com.google.gson.JsonIOException;

public class EditionVenteActionManager extends EditionVenteTemplate {
	
	
	private EditionVenteService serviceEditionVente;
	@Autowired
	public void setServiceEditionVente(EditionVenteService serviceEditionVente) {
		this.serviceEditionVente = serviceEditionVente;
	}
	
	
	private Facture_clientService serviceFacture;
	@Autowired
	public void setServiceFacture(Facture_clientService serviceFacture) {
		this.serviceFacture = serviceFacture;
	}

	public   ModelAndView doInitServletAction() {

		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			bs.setSousmod_libelle_title("");

		 
		    return getViewFilterEdition(FILTER_VIEW);

			 

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(EditionVenteBean searchBean) throws Throwable {
		try {
			
		 
			List<Det_Fact_ClientBean> listEditionVente=  serviceFacture.doFindByCriteriaList_detaille_Facture(searchBean);
			setObjectValueModel("listEditionVente",  listEditionVente);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			
			 JSONObject json      = new JSONObject();
			 JSONArray  listcol   = new JSONArray();
			 JSONObject  element  = new JSONObject();
			 element.put("sTitle","Date");
			 element.put("sName","pk.factclient.fact_date");
			 element.put("sWidth","5%" );
			 element.put("bSortable","true" );
			 listcol.put(element);
			 
			 element = new JSONObject();
			 element.put("sTitle","Invoice");
			 element.put("sName","pk.factclient.fact_ref_id");
			 element.put("sWidth","5%" );
			 element.put("bSortable","true" );
			 listcol.put(element);
			 
			 element = new JSONObject();
			 element.put("sTitle","Client");
			 element.put("sName","pk.factclient.client.clt_lib");
			 element.put("sWidth","25%" );
			 element.put("bSortable","true" );
			 listcol.put(element);
			 
			 element = new JSONObject();
			 element.put("sTitle","Description");
			 element.put("sName","pk.fkcode_barre.designation_libelle");
			 element.put("sWidth","25%" );
			 element.put("bSortable","true" );
			 listcol.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("sTitle","Qté");
			 element.put("sName","quantite");
			 element.put("sWidth","5%" );
			 element.put("bSortable","true" );
			 listcol.put(element);
			 
			 element = new JSONObject();
			 element.put("sTitle","N/box");
			 element.put("sName","nbrBoxes");
			 element.put("sWidth","10%" );
			 element.put("bSortable","true" );
			 listcol.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("sTitle","prix/Kg");
			 element.put("sName","tarif_unit_vente");
			 element.put("formatMnt2","oui");
			 element.put("sWidth","15%" );
			 element.put("sClass","alignCenter" );
			 element.put("bSortable","true" );
			 listcol.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("sTitle","Total");
			 element.put("formatMnt2","oui");
			 element.put("sName","montant_ttc_vente");
			 element.put("sWidth","15%" );
			 element.put("sClass","alignRight" );
			 element.put("bSortable","true" );
			 listcol.put(element);
			 
 
			 
			 json.put("listcolonne", listcol);
			 getResponse().setContentType(JSON_CONTENT_TYPE);      
			 getResponse().getWriter().write(json.toString());
			 
			//List listDataSrv = serviceEditionVente.doFetchDatafromServer(searchBean);
			//AjaxDataTablesUtility.doInitJQueryGrid(listdetail);
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	public ModelAndView doAddData(EditionVenteBean detailBean) throws Throwable {
		try {
			setObjectValueModel(FORM_BEAN, detailBean);
			serviceEditionVente.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(EditionVenteBean beanUpBean) {
		try {
			serviceEditionVente.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(EditionVenteBean beanDelBean) {
		try {
			serviceEditionVente.doDeleteRowData(beanDelBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
