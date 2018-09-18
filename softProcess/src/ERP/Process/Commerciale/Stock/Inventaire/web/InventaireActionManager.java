package ERP.Process.Commerciale.Stock.Inventaire.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.Inventaire.model.DetInventaireBean;
import ERP.Process.Commerciale.Stock.Inventaire.model.InventaireBean;
import ERP.Process.Commerciale.Stock.Inventaire.service.InventaireService;
import ERP.Process.Commerciale.Stock.Inventaire.template.InventaireTemplate;
import ERP.Process.Commerciale.Stock.TypeInventaire.model.TypeInventaireBean;
import ERP.Process.Commerciale.Stock.TypeInventaire.service.TypeInventaireService;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.service.TarificationService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonObject;

public class InventaireActionManager extends InventaireTemplate {
	
	 
	
	 
	private static final long serialVersionUID = 3790015261948557751L;
	
	public    TarificationDAO daoTarification;
	@Autowired
	public void setDaoTarification(TarificationDAO daoTarification) {
		this.daoTarification = daoTarification;
	}
 
	
	private DocumentLotService serviceDocumentLot;

	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
		this.serviceDocumentLot = serviceDocumentLot;
	}
	private InventaireService serviceInventaire;
	@Autowired
	public void setServiceInventaire(InventaireService serviceInventaire) {
		this.serviceInventaire = serviceInventaire;
	}
	
	
	public   Code_barreService serviceCode_barre;
	
	@Autowired
	public void setServiceCode_barre(Code_barreService serviceCode_barre) {
		this.serviceCode_barre = serviceCode_barre;
	}
	
	
	public    TarificationService	serviceTarification;
	@Autowired
	public void setServiceTarification(TarificationService serviceTarification) {
		this.serviceTarification = serviceTarification;
	}
	 
	
 
	private     TypeInventaireService    serviceTypeInventaire;
	@Autowired
	public void setServiceTypeInventaire(TypeInventaireService serviceTypeInventaire) {
		this.serviceTypeInventaire = serviceTypeInventaire;
	}
	
	
    private      DepotStockageService       serviceDepotStockage;
	@Autowired
	public void setServiceServiceDepotStockage(DepotStockageService serviceDepotStockage) {
		this.serviceDepotStockage = serviceDepotStockage;
	}
	
	
	
	public    ModelAndView doInitServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		try {
			
			List <TypeInventaireBean>list_TypeInventaire=serviceTypeInventaire.doFetchDatafromServer(TypeInventaireBean.class.newInstance());
			setObjectValueModel(LIST_TYPEINVENTAIRE, list_TypeInventaire);
			setObjectValueModel(LIST_DEPOT_STOCK, serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
			
			
			
			
			 HashMap   mapresul=null;//daoTarification.doFindListTarification_ACHAT_DateMax(TarificationBean.class.newInstance());
			 List list_Tarification=  (List) (mapresul.get("list1")== null ? new ArrayList(): mapresul.get("list1"));  
			 List list_data_for_inventaire=(List) (mapresul.get("list2")== null ? new ArrayList(): mapresul.get("list2"));  
			 
			 
			 
			HashMap mapTarification=ProcessUtil.getHashMap_code_bean(list_Tarification , "pk.fkCode_barre.pk.code_barre");
			setObjectValueModel("map_tarification", mapTarification);
			
			
			List <DetInventaireBean>ListInve= new ArrayList<DetInventaireBean>();
			
			
		    for (int i = 0; i < list_data_for_inventaire.size(); i++) {
		    	Code_barreBean code_barreBean  =(Code_barreBean) list_data_for_inventaire.get(i);
		    	DetInventaireBean detInv= new DetInventaireBean();
		    	detInv.getPk().setFkCode_barre(code_barreBean);
		    	TarificationBean  ss  =(TarificationBean) mapTarification.get(code_barreBean.getPk().getCode_barre());
		    	if(ss!=null){
		    	/*	detInv.setDate_trf(ss.getPk().getDate_trf());
		    		detInv.setTyp_trfBean(ss.getPk().getTyp_trfBean());
		    		detInv.setPrix_unit_achat(ss.getPk().getPrix_unit_achat());
		    		detInv.setPrix_unit_vente(ss.getPk().getPrix_unit_vente());*/
		    		ListInve.add(detInv);
		    	}
		    	 
		    	
			}
			setObjectValueModel(LIST_DATA_FOR_INVENTAIRE, ListInve);
			if (bs.getFct_id().equals(Fn_Nouveau) || bs.getFct_id().equals(Fn_Créer)  ) {
				return getViewAdd(FORM_VIEW);
			} else {
				return getViewFilterAjax(FILTER_VIEW);
			}
			
			

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
public static ModelAndView doActualiser_GRID( ) throws Exception{
		
		try {
			
			HashMap  mapTarification=(HashMap) getObjectValueModel("map_tarification");
			List list_data_for_inventaire=   (List) getObjectValueModel(LIST_DATA_FOR_INVENTAIRE);
			JsonObject data = new JsonObject();
			 
		  
			for (int i = 0; i < list_data_for_inventaire.size(); i++) {
				DetInventaireBean  newBean= (DetInventaireBean) list_data_for_inventaire.get(i);
				TarificationBean  ss  =(TarificationBean) mapTarification.get(newBean.getPk().getFkCode_barre().getPk().getCode_barre());
		    	if(ss!=null){
		    		/*newBean.setDate_trf(ss.getPk().getDate_trf());
		    		newBean.setTyp_trfBean(ss.getPk().getTyp_trfBean());
		    		newBean.setPrix_unit_achat(ss.getPk().getPrix_unit_achat());
		    		newBean.setPrix_unit_vente(ss.getPk().getPrix_unit_vente());
		    		newBean.setTvaBean(ss.getPk().getTvaBean());
		    		//System.out.println("-------"+ss.getPk().getPrix_unit_achat());
		    		double priUnitachat=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getPk().getPrix_unit_achat());
		    		//System.out.println("-------"+newBean.getPk().getQuantite());
		    		double qte=ProcessFormatNbr.FormatDouble_Troischiffre(newBean.getPk().getQuantite());
		    		double montant_ht_achat=ProcessNumber.PRODUIT(priUnitachat, qte);
		    		newBean.getPk().setMontant_ht(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat));
		    		
		    		Double montant_tva_achat=ProcessNumber.Pourcentage(montant_ht_achat, ss.getPk().getTvaBean().getTva_id());
	    		           montant_tva_achat=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_achat);
	    		           newBean.getPk().setMontant_tva(montant_tva_achat);
		    		 Double number=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_achat);          
		    		 String Elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(number)  ;     
		    		 data.addProperty(newBean.getPk().getFkCode_barre().getPk().getCode_barre(),Elm);*/
	    				         
		    	}
		     	 
			}
			  getResponse().setContentType(JSON_CONTENT_TYPE);
			  getResponse().getWriter().print(data.toString());
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
 
	
	 
	public    ModelAndView doSelect_RowBean() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			InventaireBean rowBean = (InventaireBean) getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			setObjectValueModel(FORM_BEAN, rowBean);
			
			List<DetInventaireBean> list_data_for_inventaire= serviceInventaire.doFindList_det_Inventaire(rowBean);
			setObjectValueModel(LIST_DATA_FOR_INVENTAIRE, list_data_for_inventaire);
			
			if (bs.getFct_id().equals(Fn_Consulter) )
				return getViewConsult(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Valider))
				return getViewValider(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Modifier))
				return getViewUpdate(FORM_VIEW);
			if (bs.getFct_id().equals(Fn_Supprimer))
				return getViewDelete(FORM_VIEW);
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(InventaireBean searchBean) throws Throwable {
		try {
	         BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			
			if(bs.getFct_id().equals(Fn_Supprimer) || bs.getFct_id().equals(Fn_Annuler)  ){
				searchBean.setCondition_etat_entite("  AND  bean.modeBean.fct_id not in ('"+Fn_Valider+"','"+Fn_Transférer+"','"+Fn_reception+"')   ");
			}
			
			
			if(bs.getFct_id().equals(Fn_Modifier) ){
				searchBean.setCondition_etat_entite("  AND  bean.modeBean.fct_id not in ('"+Fn_Valider+"','"+Fn_Annuler+"','"+Fn_Transférer+"','"+Fn_reception+"')   ");
			}
			
			 
			if(bs.getFct_id().equals(Fn_Valider) ){
			  searchBean.setCondition_etat_entite("  AND  bean.modeBean.fct_id  not in ('"+Fn_Valider+"')   ");
			}
			
			
			 
			
			
			List listDataSrv = serviceInventaire.doFetchDatafromServer(searchBean);
			setObjectValueModel(LIST_DATA, listDataSrv);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	public ModelAndView doAddData(InventaireBean detailBean) throws Throwable {
		try {
			serviceInventaire.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doValiderData(InventaireBean detail_Bean) {
		try {
			serviceInventaire.doValiderRowData(detail_Bean);
			 
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(InventaireTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			throwNewException("validaTion Ok !");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	
	public ModelAndView doUpdateData(InventaireBean beanUpBean) {
		try {
			serviceInventaire.doUpdateRowData(beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(InventaireBean beanDelBean) {
		try {
			serviceInventaire.doDeleteRowData(beanDelBean);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
