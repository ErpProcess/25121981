package ERP.Process.Commerciale.Stock.MouvementStock.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.service.NatureMvtCommercialeService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.MouvementStock.service.MouvementStockService;
import ERP.Process.Commerciale.Stock.MouvementStock.template.MouvementStockTemplate;
import ERP.Process.Commerciale.Stock.Stock_article.template.Stock_articleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
public class MouvementStockActionManager extends MouvementStockTemplate {
	
	
 
	private static final long serialVersionUID = -688296710543400420L;
	
	private MouvementStockService  serviceMouvementStock;
	@Autowired
	public void setServiceMouvementStock(MouvementStockService serviceMouvementStock) {
	    this.serviceMouvementStock = serviceMouvementStock;
	} 


	private NatureMvtCommercialeService    serviceNatureMvtCommerciale;
	@Autowired
	public void setServiceNatureMvtCommerciale(NatureMvtCommercialeService serviceNatureMvtCommerciale) {
	    this.serviceNatureMvtCommerciale = serviceNatureMvtCommerciale;
	} 

	 private      DepotStockageService       serviceDepotStockage;
		@Autowired
		public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
			this.serviceDepotStockage = serviceDepotStockage;
		}
		public   Code_barreService serviceCode_barre;
		@Autowired
		public void setServiceCode_barre(Code_barreService serviceCode_barre) {
			this.serviceCode_barre = serviceCode_barre;
		}
	

 public    ModelAndView doIniServletAction() {

	removeObjectModel(FORM_BEAN );
	removeObjectModel(SEARCH_BEAN );
	removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
	BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
	
	
	try {
		
		 
		
		Code_barreBean searchBeanx=new Code_barreBean();
        searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
	    searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
		
		setObjectValueModel("list_mvt_commerciale_Stock",serviceNatureMvtCommerciale.doFetchDatafromServer(NatureMvtCommercialeBean.class.newInstance()));
		setObjectValueModel(Stock_articleTemplate.LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
		setObjectValueModel(Stock_articleTemplate.LIST_ARTICLE_CODE_BARRE , serviceCode_barre.doFetchDatafromServerNew(searchBeanx));
		
		if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
			return getViewAdd(FORM_VIEW);
		} else {
			return getViewFilterAjax(FILTER_VIEW);

		}

	} catch (Exception e) {
		displayException(e);
		return getHome();
	}

}



@SuppressWarnings("unchecked") 
public ModelAndView doFetchData(MouvementSerieBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceMouvementStock.doFetchDatafromServer(searchBean);
			setObjectValueModel(LIST_DATA, listDataSrv);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
 		} catch (JsonIOException e) {
 			e.printStackTrace();
 			getResponse().setContentType(HTML_CONTENT_TYPE);
 			getResponse().getWriter().print(e.getMessage());
}
return null;
}
public ModelAndView doAddData(MouvementSerieBean detailBean) throws Throwable {
     try {
            serviceMouvementStock.doCreateRowData(detailBean);
            removeObjectModel(FORM_BEAN);
            throwNewException("ins01");
          } catch (Exception e) {
            displayException(e);
          }
        return getViewAdd(FORM_VIEW);
	}
public ModelAndView doUpdateData(MouvementSerieBean beanUpBean) {	 
	 	try {
	 serviceMouvementStock.doUpdateRowData(beanUpBean); 
	 throwNewException("mod01");
	 	} catch (Exception e) {
	 	displayException(e);
	 }
	return getViewList_Ajax(FILTER_VIEW);
		}
public ModelAndView doDeleteData(MouvementSerieBean beanDelBean) {
    try {
     serviceMouvementStock.doDeleteRowData(beanDelBean);
     throwNewException("sup01");
       } catch (Exception e) {
       displayException(e);
       }
    return getViewList_Ajax(FILTER_VIEW);
   }
 }
