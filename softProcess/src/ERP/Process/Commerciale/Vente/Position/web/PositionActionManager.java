package ERP.Process.Commerciale.Vente.Position.web;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Vente.Position.service.PositionService;
import ERP.Process.Commerciale.Vente.Position.template.PositionTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
public class PositionActionManager extends PositionTemplate {
	
	
  
	private static final long serialVersionUID = -7844638496101618202L;
	
	
	private PositionService  servicePosition;
	@Autowired
	public void setServicePosition(PositionService servicePosition) {
	    this.servicePosition = servicePosition;
	} 
	
	public    ModelAndView doInitServletAction() {
		 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			setObjectValueModel("sesOrigine", ProcessUtil.cloneObject(bs));
			if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
				return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
			} else {
				return getViewFilterAjax(FORM_LINK_VIEW);
			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
public   ModelAndView doPosition(ProcedureVenteBean bean ) throws Exception{
		
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			getResponse().getWriter().print("");
			getResponse().setContentType(HTML_CONTENT_TYPE);
			bs.setSousmod_libelle_title("  chiffre affaire par Produit");
			setObjectValueModel(FORM_BEAN, bean);
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return getViewFilterViewLoad(LIST_PAR_PRODUIT);
	 
	}
		public static  ModelAndView doRetourToFormLinked() {
			removeObjectModel(FORM_BEAN);
			return getViewFilterAjax(FORM_LINK_VIEW);
		}

	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(ProcedureVenteBean searchBean)throws Throwable {
			try {
				
				
				List <DetProcedureVenteBean> listDataSrv = servicePosition.doFetchDatafromServer(searchBean);
				List flistfinal = new ArrayList();
				
				if(searchBean.getNext_Focus().equals("ListPositionParProduit")){
					HashMap  map = new HashMap();
					HashMap  mapProduit = new HashMap();
					for (DetProcedureVenteBean bean:listDataSrv) {
						List listdata=(List) map.get(bean.getPk().getFkcode_barre().getPk().getCode_barre());
						if(listdata== null) {
							listdata= new ArrayList();
							mapProduit.put(bean.getPk().getFkcode_barre().getPk().getCode_barre(), bean.getPk().getFkcode_barre());
							}
						listdata.add(bean);
						map.put(bean.getPk().getFkcode_barre().getPk().getCode_barre(), listdata);
					}
					
					Set sffd=map.keySet();
					for (Iterator itera = sffd.iterator(); itera.hasNext();) {
						String produit = (String) itera.next();
						List <DetProcedureVenteBean>lis_with_date=(List) map.get(produit);
						// ProcessUtil.CollectionSortAsc(lis_with_date, "pk.vente.date_vente");
						 for (DetProcedureVenteBean beanDate:listDataSrv) {
							 DetProcedureVenteBean  beanS = new DetProcedureVenteBean();
							 beanS.getPk().setFkcode_barre((Code_barreBean) mapProduit.get(produit));
							 beanS.setPk(beanDate.getPk());
							 flistfinal.add(beanS);
						  }
						
					} 
					
					
				}
				
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
	public ModelAndView doAddData(ProcedureVenteBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            servicePosition.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(ProcedureVenteBean beanUpBean) {	 
		 	try {
		 servicePosition.doUpdateRowData(beanUpBean); 
				update_row_from_list(LIST_DATA, beanUpBean); 
		 throwNewException("mod01");
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(ProcedureVenteBean beanDelBean) {
	    try {
	     servicePosition.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
