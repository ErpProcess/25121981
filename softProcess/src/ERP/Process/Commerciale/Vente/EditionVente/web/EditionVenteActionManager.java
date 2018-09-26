package ERP.Process.Commerciale.Vente.EditionVente.web;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import ERP.Process.Commerciale.Vente.EditionVente.model.EditionVenteBean;
import ERP.Process.Commerciale.Vente.EditionVente.template.EditionVenteTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.service.Facture_clientService;
import ERP.Process.Commerciale.Vente.EditionVente.service.EditionVenteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
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
			bs.setPrefix_sousmod_libelle_title("");

		 
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
	
	
//	@SuppressWarnings("unchecked")
//	public ModelAndView doCalculerTotal(Facture_clientBean detailBean ) throws Exception {
//		try {
//			BeanSession bs                = (BeanSession)getObjectValueModel(BEAN_SESSION);
//			Facture_clientBean   rowBean  = (Facture_clientBean) getObjectValueModel(FORM_BEAN);
//			
//			List <Det_Fact_ClientBean >List_detaille= (List) getObjectValueModel("listEditionVente");
//			String pattern ="0.00";
////	    	if( rowBean.getDevise().getDev_id().intValue()==191  ||  rowBean.getDevise().getDev_id().intValue()==192   ){
////					pattern ="0.00";
////			} 
////			
////			Double getAvance_montant_vente  = detailBean.getAvance_montant_vente()==null?new Double(0):detailBean.getAvance_montant_vente();
////			Double avance                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(getAvance_montant_vente,pattern);
////			Double timbre                   = ProcessFormatNbr.FormatDouble_ParameterChiffre(bs.getSociete().getMontant_timbre_fiscal(),pattern);
//			 
//			
////			setObjectValueModel(LIST_DATA_DET_FACT, List_detaille);
////			setObjectValueModel(LIST_DATA_DET_FACT_CLONE, ProcessUtil.cloneList(List_detaille) );  
//			
//			
//			Double totalre_mise          			  =  new Double(0);
//			Double tot_ht_brute =new Double(0);
//			Double tot_ht_net    =new Double(0);
//			Double tot_tva=new Double(0);
//			Double tot_qte=new Double(0);
//			
//			
//			HashMap   map_des_Tva = new HashMap();
//			for (int i = 0; i < List_detaille.size(); i++) {
//				Det_Fact_ClientBean  beanLigne=List_detaille.get(i);
//				
//				 
//				/*****************************************le cout ********************************************/
//	    		Double getQuantiteX       = beanLigne.getQuantite()==null?new Double(0):beanLigne.getQuantite();
//	    		Double                 qte= ProcessFormatNbr.FormatDouble_ParameterChiffre(getQuantiteX,pattern);
//	    		/*Double  cout              = beanLigne.getTarif().getCout()==null?new Double(0):beanLigne.getTarif().getCout().getTarif_unit_article(,pattern);
//	    		Double	Prixcout          = cout==null?new Double(0):cout;
//	    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
//	    		le_cout					  = ProcessFormatNbr.FormatDouble_ParameterChiffre(le_cout);*/
//	    		
//	    		/*****************************************  setInfo  ********************************************/
//	    		
//	    		/*****************************************  setInfo  ********************************************/
//	    		Double priUnitvente=ProcessFormatNbr.FormatDouble_ParameterChiffre(beanLigne.getTarif_unit_vente(),pattern);
//	    		/*****************************************Prix Unit Brute reel********************************************/
//	    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
//	    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_ht_vente_brute,pattern);
//	    		beanLigne.setMontant_ht_vente_reel(montant_ht_vente_brute);
//	    		tot_qte     =ProcessNumber.addition(tot_qte, qte);
//				tot_ht_brute=ProcessNumber.addition(tot_ht_brute, montant_ht_vente_brute);
//	    		 
//	    		/*******************************************Remise********************************************************/
//				 
//				Double taux_remiseligne     = beanLigne.getTaux_remise_ligne()==null?new Double(0):beanLigne.getTaux_remise_ligne();
//				taux_remiseligne=ProcessFormatNbr.FormatDouble_ParameterChiffre(taux_remiseligne,pattern);
//				beanLigne.setTaux_remise_ligne(taux_remiseligne);
//				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_vente_reel(), taux_remiseligne);
//				beanLigne.setMontant_remise_ligne(ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_Remise_Ligne,pattern));
//				totalre_mise=ProcessNumber.addition(totalre_mise, montant_Remise_Ligne);
// 
//    		    /*******************************************montant_ht_vente *********************************************/   
//    		    
//    		    tot_ht_net=ProcessNumber.addition(tot_ht_net, beanLigne.getMontant_ht_vente());
//	    		
//	    		/*********************************************montant_tva_vente ******************************************/
//	    		 
//	    		tot_tva=ProcessNumber.addition(tot_tva, beanLigne.getMontant_tva_vente());
//	    		
//	    		/*********************************************montant_ttc_vente *******************************************/
//	    		Double  montant_ttc_vente=ProcessNumber.addition(beanLigne.getMontant_ht_vente(), beanLigne.getMontant_tva_vente());
//	    		beanLigne.setMontant_ttc_vente(montant_ttc_vente);
//	    		
//	    		/*********************************************montant_benefice *******************************************/
//	    		/*if(le_cout.doubleValue()>0){
//	    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
//	    		montant_benefice          =ProcessFormatNbr.FormatDouble_ParameterChiffre(montant_benefice);
//	    		beanLigne.setMontant_benefice(montant_benefice);
//	    		marge_benefice_vente=ProcessNumber.addition(marge_benefice_vente, montant_benefice);*/
//	    		//}
//	    		/**********************************************************************************************************/
//				 
//				
//				List  listTva = (List) map_des_Tva.get(beanLigne.getTvaBean().getTva_libelle());
//				if(listTva==null)listTva= new ArrayList();
//				listTva.add(beanLigne);
//				map_des_Tva.put(beanLigne.getTvaBean().getTva_libelle(), listTva);
//				
//				/**********************************************************************************************************/
//			}
//			
//			Facture_clientBean    beanTotal = new Facture_clientBean();
//			 beanTotal.setAvance_montant_vente(avance)  ; 
//			 //beanTotal.setMarge_benefice_vente(ProcessFormatNbr.FormatDouble_ParameterChiffre(marge_benefice_vente));
//			 JSONObject json        = new JSONObject();
//			 JSONArray  listDataTva = new JSONArray();
//			 JSONArray  list_total  = new JSONArray();
//			  
//			 JSONObject  el_zero = new JSONObject();
//			 el_zero.put("td1","0");
//			 el_zero.put("value1","");
//			 listDataTva.put(el_zero);
//				 
//				 /*********************************************************ENTETE***************************************************/
//			 el_zero = new JSONObject();
//			 el_zero.put("td1","0");
//			 el_zero.put("value1","Taux");
//			 el_zero.put("td2","1");
//			 el_zero.put("value2","Base");
//			 el_zero.put("td3","2");
//			 el_zero.put("value3","Montant");
//			 listDataTva.put(el_zero);
//				 
//				 /********************************************************************************************************************/
//				 
//				 HashMap   mapTvaImpression = new HashMap();
//				 
//				 
//				 Double    total_leTva  = new Double(0);
//				 List <TVABean> list_des_tva=  (List) getObjectValueModel(LIST_DES_TVA);
//				 Double le_Ht_Net  = new Double(0);
//				 Double le_Ht_Reel  = new Double(0);
//				 for (int j = 0; j < list_des_tva.size(); j++) {
//					 TVABean beanTva=list_des_tva.get(j);
//					 
//					 if(map_des_Tva.get(beanTva.getTva_libelle())!=null){
//						 List listTva  =(List) map_des_Tva.get(beanTva.getTva_libelle());
//						 String  tva   = beanTva.getTva_libelle();
//						  	
//						 Double le_Ht_netLigne  = new Double(0);
//					     Double leTva  = new Double(0);
//					 	 for (int i = 0; i < listTva.size(); i++) {
//					 		Det_Fact_ClientBean  bean=(Det_Fact_ClientBean) listTva.get(i);
//					 		le_Ht_netLigne=ProcessNumber.addition(le_Ht_netLigne, bean.getMontant_ht_vente());
//							le_Ht_Net=ProcessNumber.addition(le_Ht_Net, bean.getMontant_ht_vente());
//							le_Ht_Reel=ProcessNumber.addition(le_Ht_Reel, bean.getMontant_ht_vente_reel());
//							leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_vente());
//						 }
//					 	 total_leTva=  ProcessNumber.addition(total_leTva, leTva);
//						 
//						 JSONObject  element = new JSONObject();
//						 element.put("td1","0");
//						 element.put("value1",String.valueOf(tva));
//					     
//					     String base=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(le_Ht_netLigne,pattern);
//					     
//						 element.put("td2","1");
//						 element.put("value2",base);
//					     
//						 element.put("td3","2");
//						 String tvaB=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(leTva,pattern);
//						  
//						 element.put("value3",tvaB);
//						 listDataTva.put(element);
//						 mapTvaImpression.put(tva, base+"£"+tvaB);
//					 }
//				 }
//			 
//			 json.put("list_tva", listDataTva);
//			 setObjectValueModel("mapTvaImpression", mapTvaImpression);
//			
//			  
//			 JSONObject  element = new JSONObject();
//			 element.put("td1","4");
//			 element.put("value1","Brut.H.T");
//			 element.put("td2","5");
//			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(le_Ht_Reel,pattern));
//			 list_total.put(element);
//			 
//			 /*if( detailBean.getTaux_remise_alacaisse()!=null  &&  le_Ht_Reel.doubleValue() >0 ){
//				 remise_ala_caisse  = ProcessNumber.Pourcentage(le_Ht_Reel, detailBean.getTaux_remise_alacaisse());
//				 remise_ala_caisse  = ProcessFormatNbr.FormatDouble_ParameterChiffre(remise_ala_caisse);
//			 }*/
//			 //beanTotal.setVente_remise_alacaisse(remise_ala_caisse);
//			 totalre_mise  = ProcessFormatNbr.FormatDouble_ParameterChiffre(totalre_mise,pattern);
//			 beanTotal.setFacture_remise(totalre_mise); 
//			 json.put("vente_remise",  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(totalre_mise,pattern));	
//			 element = new JSONObject();
//			 element.put("td1","4");
//			 element.put("value1","Remise");
//			 element.put("td2","5");
//			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(totalre_mise, pattern));
//			 list_total.put(element);
//			
//			 
//			 element = new JSONObject();
//			 element.put("td1","4");
//			 element.put("value1","Net.H.T");
//			 element.put("td2","5");
//			 Double ht_apres_remise =  ProcessNumber.SOUSTRACTION(le_Ht_Reel, totalre_mise)  ;
//			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(ht_apres_remise,pattern));
//			 list_total.put(element);
//			 beanTotal.setTotal_ht_fact(ProcessFormatNbr.FormatDouble_ParameterChiffre(ht_apres_remise,pattern));
//			 
//			
//			 
//			 
//			 
//			
//			 element = new JSONObject();
//			 element.put("td1","4");
//			 element.put("value1"," Total TVA");
//			 element.put("td2","5");
//			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(total_leTva,pattern));
//			 beanTotal.setTotal_tva_fact(ProcessFormatNbr.FormatDouble_ParameterChiffre(total_leTva,pattern));
//			 list_total.put(element);
//			 
//			 
//			 Double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
//			 element = new JSONObject();
//			 element.put("td1","4");
//			 element.put("value1","Timbre");
//			 element.put("td2","5");
//			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(timbre,pattern));
//			 list_total.put(element);
//			 total_mnt_gen=ProcessNumber.addition(total_mnt_gen,timbre);
//			 beanTotal.setMnt_timb_fisc(timbre);
//		
//			 
//			 element = new JSONObject();
//			 element.put("td1","4");
//			 element.put("value1","Total TTC");
//			 element.put("td2","5");
//			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(total_mnt_gen,pattern));
//			 json.put("vente_mnt_total",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(total_mnt_gen,pattern));
//			 beanTotal.setTotal_facture(ProcessFormatNbr.FormatDouble_ParameterChiffre(total_mnt_gen,pattern));
//			 list_total.put(element);
//			  
//			 
//			 element = new JSONObject();
//			 element.put("td1","4");
//			 element.put("value1","Avance");
//			 element.put("td2","5");
//			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(avance,pattern));
//			 list_total.put(element);
//			 
//			 
//			 element = new JSONObject();
//			 element.put("td1","4");
//			 element.put("value1","Net à payer");
//			 element.put("td2","5");
//			 Double net_a_payer=ProcessNumber.SOUSTRACTION(total_mnt_gen, avance);
//			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(net_a_payer,pattern));
//			 beanTotal.setNet_a_payer(ProcessFormatNbr.FormatDouble_ParameterChiffre(net_a_payer,pattern));
//			 json.put("vente_mnt_net_a_payer",   ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(net_a_payer,pattern));
//			 list_total.put(element);
//			   
//			 
//			 json.put("list_total", list_total);
//			 setObjectValueModel("list_total", list_total);
//			 setObjectValueModel(BEAN_TOTAL_FACTURE_CLIENT, beanTotal);
//			 getResponse().setContentType(JSON_CONTENT_TYPE);      
//			 getResponse().getWriter().write(json.toString());
//			
//		} catch (Exception e) {
//			getResponse().setContentType(HTML_CONTENT_TYPE);
//			getResponse().getWriter().print(e.getMessage());
//		}
//		return null;
//	
//		 
//		 
//	}

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
