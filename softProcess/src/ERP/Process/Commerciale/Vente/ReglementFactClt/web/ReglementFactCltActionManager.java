package ERP.Process.Commerciale.Vente.ReglementFactClt.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.service.ModeReglementService;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.service.Facture_clientService;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
 
 
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.EcheanceRegCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.model.ReglementFactCltBean;
import ERP.Process.Commerciale.Vente.ReglementFactClt.service.ReglementFactCltService;
import ERP.Process.Commerciale.Vente.ReglementFactClt.template.ReglementFactCltTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

public class ReglementFactCltActionManager extends ReglementFactCltTemplate {

	private static final long serialVersionUID = 8784235234682398201L;
	private ReglementFactCltService serviceReglementFactClt;

	@Autowired
	public void setServiceReglementFactClt(
			ReglementFactCltService serviceReglementFactClt) {
		this.serviceReglementFactClt = serviceReglementFactClt;
	}

	private Facture_clientService serviceFacture;
	@Autowired
	public void setServiceFacture(Facture_clientService serviceFacture) {
		this.serviceFacture = serviceFacture;
	}

	private ClientDAO daoClient;

	@Autowired
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}

	@Autowired
	Entite_etat_commercialeService serviceEntite_etat_commerciale;
	
	
	  private ModeReglementService  serviceModeReglement;
	  @Autowired
	  public void setServiceModeReglement(ModeReglementService serviceModeReglement) {
	      this.serviceModeReglement = serviceModeReglement;
	  } 
	
	public    ModelAndView doInitServletAction() {
		 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			doLoadingLibelleOtherSModule(ProcedureVenteTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(Facture_clientTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(ID_SOUS_MODULE);
			
		 
			 
			setObjectValueModel(LIST_MODE_REGLMENT,serviceModeReglement.doFetchDatafromServer(ModeReglementBean.class.newInstance()));
			
			
			Entite_etat_commercialeBean beanEch = new Entite_etat_commercialeBean();
			beanEch.setCode_entite("etat_reg_ech");
			setObjectValueModel(LIST_ETAT_ECH_REGLMENT,serviceEntite_etat_commerciale.dofetchDatafromServer(beanEch));
			
 
			Entite_etat_commercialeBean beanSn = new Entite_etat_commercialeBean();
			beanSn.setCode_entite("reg_nature");
			setObjectValueModel(LIST_NATURE_REGLEMENT,serviceEntite_etat_commerciale.dofetchDatafromServer(beanSn));
			
			List list_client_d= daoClient.doFindListClient(ClientBean.class.newInstance());
			setObjectValueModel(Facture_clientTemplate.LIST_CLIENT_VENTE, list_client_d);
			setObjectValueModel( LIST_DES_ECHEANCES, new ArrayList<EcheanceRegCltBean>() );
			
			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
				
				setObjectValueModel(MAP_FIELD_BEAN, Facture_clientTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
				setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_FACT");
				setObjectValueModel(FORM_BEAN, new Facture_clientBean());
				setObjectValueModel(SEARCH_BEAN, new Facture_clientBean());
				
				return getViewFilterFacture(FILTER_VIEW_FACTURE);
				
				 
			} else {
				
				return getViewFilterAjax( FILTER_VIEW );

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchDataFacture(Facture_clientBean searchBean)throws Throwable {
			try {
				
				BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 
			    
			    searchBean.setSelect_many_facture("  AND  bean.typefact.type_fact_id in ('1','3')       AND   bean.etat_reg.data_id='fnon'     " ); 
			    
			    if(bs.getSousmod_id().equals(ReglementFactCltTemplate.ID_SOUS_MODULE_REMBOURCEMENT))
			    searchBean.setSelect_many_facture("  AND  bean.typefact.type_fact_id in ('2')         AND   bean.etat_reg.data_id='fnon'    " );
				    
			    
			    setObjectValueModel(SEARCH_BEAN, searchBean);
				setObjectValueModel(MAP_FIELD_BEAN, Facture_clientTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
				setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_FACT");
				List listDataSrv = serviceFacture.doFetchDatafromServer(searchBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public   ModelAndView doDeleteRowEditable( ReglementFactCltBean searchBean  ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_DES_ECHEANCES);
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				EcheanceRegCltBean newBean= (EcheanceRegCltBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
			}
			if(!del) throw new Exception ((String) getObjectValueModel("_cochezAumoin"));
			getResponse().setContentType(JSON_CONTENT_TYPE);
			JSONObject data    =doCalculerEcheanceList(searchBean);
			getResponse().getWriter().print(data.toString());
			
			
			} catch (Exception e) {
					getResponse().setContentType(HTML_CONTENT_TYPE);
					PrintWriter out = getResponse().getWriter();
					out.println(e.getMessage());
				    out.close();
			}
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public   ModelAndView doChekedUnCheked( ) throws Exception{
		 
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_DES_ECHEANCES);
			String to_check=getRequest().getParameter("statucheked")==null?"":getRequest().getParameter("statucheked");
			for (int i = 0; i < listOfmyData.size(); i++) {
				EcheanceRegCltBean newBean =(EcheanceRegCltBean) listOfmyData.get(i);
				newBean.setTo_check(to_check);
			}
			getResponse().setContentType(HTML_CONTENT_TYPE);
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
 
	 
	public   ModelAndView doAddRowEditable( ReglementFactCltBean detailBean  ) throws Exception{
		 
		
		try {
			getResponse().setContentType(JSON_CONTENT_TYPE);
			List  <EcheanceRegCltBean> listEcheanceReg     = (List) getObjectValueModel( LIST_DES_ECHEANCES );
			
			for (int i = 0; i < listEcheanceReg.size(); i++) {
				EcheanceRegCltBean echeanceRegCltBean =listEcheanceReg.get(i);
				 if(echeanceRegCltBean.getPk().getEchean_date().compareTo(detailBean.getEcheanDate())==0)
					 throwNewException(" Date existant ");
 			}
			 
			EcheanceRegCltBean beanLigne= new EcheanceRegCltBean();
			beanLigne.setEchean_montant(detailBean.getEcheanMontant());
			beanLigne.getPk().setEchean_date(detailBean.getEcheanDate());
			Entite_etat_commercialeBean  etatRegEch = new Entite_etat_commercialeBean();
			etatRegEch.setCode_entite("etat_reg_ech");
			etatRegEch.setData_id(detailBean.getEtatRegHeader());
			beanLigne.setEtatRegEch(etatRegEch);
			
			ModeReglementBean    echMode = new ModeReglementBean();
		    if( !StringUtils.isBlank(detailBean.getModeRegHeader())   ) {
		    	echMode.setMod_id( Integer.parseInt(detailBean.getModeRegHeader()) );
		    }
		
			beanLigne.setEchMode(echMode);
			beanLigne.setNum_piece_ech(detailBean.getPieceNumHeader());
			listEcheanceReg.add(beanLigne);
			
			JSONObject data        = new JSONObject();
			Double montantRecu= new Double(0);
			Double montantRestant= new Double(0);
			
			Double montantFacture = detailBean.getMontant_facture()!=null ? 
					ProcessFormatNbr.FormatDouble_Troischiffre(detailBean.getMontant_facture()): new Double(0)	;
			
			Double montantAvance = detailBean.getMontant_avance()!=null ? 
					ProcessFormatNbr.FormatDouble_Troischiffre(detailBean.getMontant_avance()): new Double(0)	;
					
			for (int i = 0; i < listEcheanceReg.size(); i++) {
				EcheanceRegCltBean echeanceRegCltBean =listEcheanceReg.get(i);
				if(echeanceRegCltBean.getEchean_montant()!=null && 
						(echeanceRegCltBean.getEtatRegEch()!=null  &&  echeanceRegCltBean.getEtatRegEch().getData_id().equals("echOui") ) )
				montantRecu=ProcessNumber.addition(montantRecu, 
				ProcessFormatNbr.FormatDouble_Troischiffre(echeanceRegCltBean.getEchean_montant())		); 
			}
		
			
			
			Double sommAvanceRecu= ProcessNumber.addition(montantAvance, montantRecu);  
			montantRestant=ProcessNumber.SOUSTRACTION(montantFacture, sommAvanceRecu);
			data.put("montantRecu",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(montantRecu));
			data.put("montantRestant",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(montantRestant));
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
	
	
	
	public      ModelAndView doLoadGridEcheance(ReglementFactCltBean searchBean ) throws Exception{
		
		try {
			getResponse().setContentType(JSON_CONTENT_TYPE);
			JSONObject data    =doCalculerEcheanceList(searchBean);
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
	
	
	public     ModelAndView doRetourToFilterFacture() {
		setObjectValueModel(MAP_FIELD_BEAN, Facture_clientTemplate.MapfieldBean);
		setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
		setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
		setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
		
		List  listDataTrie=(List) getObjectValueModel((String)getObjectValueModel(NAME_LIST_G)  );
		setObjectValueModel(DATA_LIST_AJAX,listDataTrie);
		setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_FACT");
		
		
		return getViewFilterFacture(FILTER_VIEW_FACTURE);
	}
	 
	
	
public   ModelAndView doActualiserGrid( ReglementFactCltBean searchBean ) throws Exception{
		
	try {
		getResponse().setContentType(JSON_CONTENT_TYPE);
		JSONObject data    =doCalculerEcheanceList(searchBean);
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
	 
	private JSONObject doCalculerEcheanceList(ReglementFactCltBean searchBean) throws Exception {
		JSONObject data        = new JSONObject();
		try {
			List  <EcheanceRegCltBean> listEcheanceReg     = (List) getObjectValueModel( LIST_DES_ECHEANCES );
			Double montantRecu= new Double(0);
			Double montantRestant= new Double(0);
			Double montantFacture = searchBean.getMontant_facture();
			Double montantAvance = searchBean.getMontant_avance();
			for (int i = 0; i < listEcheanceReg.size(); i++) {
				EcheanceRegCltBean echeanceRegCltBean =listEcheanceReg.get(i);
				if(echeanceRegCltBean.getEchean_montant()!=null && 
						(echeanceRegCltBean.getEtatRegEch()!=null  &&  echeanceRegCltBean.getEtatRegEch().getData_id().equals("echOui") ) )
				montantRecu=ProcessNumber.addition(montantRecu, echeanceRegCltBean.getEchean_montant()); 
			}
			Double sommAvanceRecu= ProcessNumber.addition(montantAvance, montantRecu);  
			montantRestant=ProcessNumber.SOUSTRACTION(montantFacture, sommAvanceRecu);
			data.put("montantRecu",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(montantRecu));
			data.put("montantRestant",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(montantRestant));	
		} catch (Exception e) {
			throw e;
		}
		return data;
		
}

	public   ModelAndView doLoadSelectList() throws Exception{
		
		List listDataEtatEch   = (List) getObjectValueModel(LIST_ETAT_ECH_REGLMENT);
		List listDataModeReg = (List) getObjectValueModel(LIST_MODE_REGLMENT);
		JSONObject json = new JSONObject();
		try {
			JSONArray items1  =  ActionDataTablesManager.doTraiterJsonSelect(listDataEtatEch  , "data_id"            , "data_libelle");
			JSONArray items2 =  ActionDataTablesManager.doTraiterJsonSelect(listDataModeReg , "mod_id"           , "mod_libelle");
			json.put("etatEchList", items1);
			json.put("modeRegList", items2);
			getResponse().setContentType("application/json");
			getResponse().getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

}
	
	public    ModelAndView doGetRowBean() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			
			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau) ){
				setObjectValueModel(MAP_FIELD_BEAN, Facture_clientTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
				setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
				Facture_clientBean beanFact = (Facture_clientBean) getIndexFromDataGrid_v1(LIST_DATA_FACTURE);
				ReglementFactCltBean   rowBean =  new ReglementFactCltBean();
				rowBean.setReg_date(BDateTime.getDateActuel());
				rowBean.setFactclient(beanFact);
				setObjectValueModel(FORM_BEAN, rowBean);
				return getViewAdd_reg(FORM_EDIT_VIEW);
			}else{
				ReglementFactCltBean beanRegFact = (ReglementFactCltBean) getIndexFromDataGrid_v1(LIST_DATA);
				List<EcheanceRegCltBean> list_des_echeances = serviceReglementFactClt.doFetchDataEcheanceReglementfromServer(beanRegFact);
				setObjectValueModel(LIST_DES_ECHEANCES, list_des_echeances);
				setObjectValueModel(LIST_DES_ECHEANCES_ORIGINE, ProcessUtil.cloneList(list_des_echeances) );
				setObjectValueModel(FORM_BEAN, beanRegFact);
			}
			
			if (bs.getFct_id().equals("2"))
				return getViewConsult(FORM_VIEW);
			if (bs.getFct_id().equals("3"))
				return getViewUpdate(FORM_EDIT_VIEW);
			if (bs.getFct_id().equals("4"))
				return getViewDelete(FORM_VIEW);
			if (bs.getFct_id().equals(Fn_Confirmer) ||  bs.getFct_id().equals(Fn_Régler) )
				return getViewConfirm(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Annuler))
				return getViewAnnuler(FORM_VIEW);
			
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}
	
	
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(ReglementFactCltBean searchBean)throws Throwable {
			try {
				BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
				if(bs.getSousmod_id().equals(ID_SOUS_MODULE_ECHENACE)){
					searchBean.setCondition_juste_echeance("  AND   bean.reg_nbr_echeance is not null   and    bean.reg_nbr_echeance   > 0  " +
							"     ");
				}
				
				if(bs.getSousmod_id().equals(ID_SOUS_MODULE_REMBOURCEMENT)){
					searchBean.setSelect_avoir("     AND   bean.factclient.typefact.type_fact_id  = 2  ");
				}
				List listDataSrv = serviceReglementFactClt.doFetchDatafromServer(searchBean);
				 setObjectValueModel("listRegGlobal" ,listDataSrv);
				
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
	public ModelAndView doAddData(ReglementFactCltBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
				List  <EcheanceRegCltBean>list_des_echeances=(List) getObjectValueModel( ReglementFactCltTemplate.LIST_DES_ECHEANCES );
	            serviceReglementFactClt.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	
	
	
	public ModelAndView doCalculerTotal() throws Exception {
			
			try {
				 List <ReglementFactCltBean> regList   = (List) getObjectValueModel("listRegGlobal");
				  
				 Double totMntFacture=new Double(0);
				 Double totMntAvance=new Double(0);
				 Double totMntRecu=new Double(0);
				 Double totMntRestant=new Double(0);
				 for (int i = 0; i < regList.size(); i++) {
					 ReglementFactCltBean  bean=regList.get(i);
					 totMntFacture=ProcessNumber.addition(totMntFacture,ProcessFormatNbr.FormatDouble_Troischiffre(bean.getMontant_facture()) );
					 totMntRecu=ProcessNumber.addition(totMntRecu, ProcessFormatNbr.FormatDouble_Troischiffre(bean.getMontant_recu()));
					 totMntAvance=ProcessNumber.addition(totMntAvance, ProcessFormatNbr.FormatDouble_Troischiffre(bean.getMontant_avance()));
					 totMntRestant=ProcessNumber.addition(totMntRestant,ProcessFormatNbr.FormatDouble_Troischiffre( bean.getMontant_restant()));
				 }
				JsonObject data = new JsonObject();
				data.addProperty("totMntFacture",  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totMntFacture) );
				data.addProperty("totMntAvance",   ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totMntAvance) );
			    data.addProperty("totMntRecu",     ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totMntRecu) );
			    data.addProperty("totMntRestant",  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totMntRestant) );
			    getResponse().setContentType(JSON_CONTENT_TYPE);
				getResponse().getWriter().print(data.toString());
				
			} catch (Exception e) {
				getResponse().setContentType(HTML_CONTENT_TYPE);
				getResponse().getWriter().print(e.getMessage());
			}
			return null;
		}
	public ModelAndView doUpdateData(ReglementFactCltBean beanUpBean) {	 
		 	try {
		 		
		 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		 serviceReglementFactClt.doUpdateRowData(beanUpBean); 
		
		 
		 if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier)){
			update_row_from_list(LIST_DATA, beanUpBean); 
		    throwNewException("mod01");
		 } else{
			 update_row_from_list(LIST_DATA, beanUpBean); 
			 throwNewException("ins01");
		 }	 
			 
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(ReglementFactCltBean beanDelBean) {
	    try {
	     serviceReglementFactClt.doDeleteRowData(beanDelBean);
 remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
