package ERP.Process.Commerciale.Vente.EditionVente.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Vente.EditionVente.model.EditionVenteBean;
import ERP.Process.Commerciale.Vente.EditionVente.model.EtatDepenseProduit;
import ERP.Process.Commerciale.Vente.EditionVente.model.EtatVenteProduit;
import ERP.Process.Commerciale.Vente.EditionVente.service.EditionVenteService;
import ERP.Process.Commerciale.Vente.EditionVente.template.EditionVenteTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.service.Facture_clientService;
import ERP.Process.Commerciale.Vente.Facture_client.web.PrintPdfModeleKobbi;
import ERP.Process.Commerciale.Vente.FournitureVente.model.DetFournitureVenteBean;
import ERP.Process.Commerciale.Vente.FournitureVente.service.FournitureVenteService;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.service.ProcedureVenteService;
import ERP.Process.Commerciale.Vente.Service.model.DetServiceBean;
import ERP.Process.Commerciale.Vente.Service.service.ServiceService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

public class EditionVenteActionManager extends EditionVenteTemplate {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3724800781494483407L;
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
	
	private ProcedureVenteService  serviceProcedureVente;
	@Autowired
	public void setServiceProcedureVente(ProcedureVenteService serviceProcedureVente) {
	    this.serviceProcedureVente = serviceProcedureVente;
	}
	private ServiceService serviceService;
	@Autowired
	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}
	private FournitureVenteService  serviceFournitureVente; 
	@Autowired
	public void setServiceFournitureVente(FournitureVenteService serviceFournitureVente) {
	    this.serviceFournitureVente = serviceFournitureVente;
	} 

	public   ModelAndView doInitServletAction() {

		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			bs.setSousmod_libelle_title("");
			bs.setPrefix_sousmod_libelle_title("");
			setObjectValueModel("listEditionVente",  new ArrayList<>());
			setObjectValueModel("listEditionDepense", new ArrayList<>());
		    return getViewFilterEdition(FILTER_VIEW);

			 

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}

	public ModelAndView doPrintDataModelKobbi() throws Throwable {
		PrintPdfModeleKobbi pModeleKobbi = new PrintPdfModeleKobbi();
		try {
			
			EditionVenteBean searchBean = (EditionVenteBean) getObjectValueModel(SEARCH_BEAN) ;
			
		    if(ifFonctionEqual(  Fn_État_des_ventes)) {
			pModeleKobbi.printEtatVenteExportKobbi(searchBean);
		    }
		    
		    if(ifFonctionEqual( Fn_État_des_dépenses)) {
		    pModeleKobbi.printEtatDepensesProduitsKobbi(searchBean);
		    }
		    
		} catch (Exception e) {
			displayException(e);
		}
		return null;
	}
	
	
 
	public ModelAndView doFetchData(EditionVenteBean searchBean) throws Throwable {
		
		try {
			
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		     setObjectValueModel(SEARCH_BEAN, searchBean);
			 JSONObject json      = new JSONObject();
			
             if(ifFonctionEqual( Fn_État_des_ventes)) {
            	 List <Det_Fact_ClientBean> listEditionVente =  serviceFacture.doFindByCriteriaList_detaille_Facture(searchBean);
                 Collections.sort(listEditionVente, new Comparator<Det_Fact_ClientBean>() {
         				@Override
         				public int compare(Det_Fact_ClientBean o1, Det_Fact_ClientBean o2) {
         					return o1.getPk().getFactclient().getFact_date().compareTo( o2.getPk().getFactclient().getFact_date()  );
         				}
         		  });
                 setObjectValueModel("listEditionVente",  listEditionVente);
            	 JSONArray  listcolonne    = doWriteHeaderGridDataEtatVenteProduit();
            	 json.put("listcolonne", listcolonne); 
            	 json.put("nameColIdGrid", "pk.fkcode_barre.pk.code_barre"); 
            	 json.put("list", "listEditionVente"); 
            	 
                 
			}
            
            if(ifFonctionEqual(Fn_État_des_dépenses)) {
            	ProcedureVenteBean beanSearch  = new ProcedureVenteBean();
            	beanSearch.setVente_date(searchBean.getDate_debut());
            	beanSearch.setVente_date2(searchBean.getDate_fin());
            	List<DetProcedureVenteBean>    listDetVente    =serviceProcedureVente.doFindDetailleListProcedureVenteEdition(beanSearch);
            	List<DetFournitureVenteBean>   listDetFouniture=serviceFournitureVente.doFindDetailFournitureEdition(beanSearch) ;
            	List<DetServiceBean>           listDetService  =serviceService.doFetchDetailfromServer(beanSearch);
  		
  		        HashMap  mapDepenseProduit = doBuildDepenseVente(listDetVente);
  		        List <EtatDepenseProduit> listDepenseProduit = (List) mapDepenseProduit.get("list");
            	HashMap  mapDataImpressionFournit            = doBuildDepenseFourniture(listDetFouniture);
            	HashMap  mapDataImpressionService            = doBuildDepenseService(listDetService);
            	HashMap  ligneTotal      = doTraiterListFinaldepense(listDepenseProduit, mapDataImpressionFournit, mapDataImpressionService);
            	
                setObjectValueModel("listEditionDepense",  listDepenseProduit);
                setObjectValueModel("ligneTotal",  ligneTotal);
                JSONArray  listcolonne    = doWriteHeaderGridDataEtatDepenseProduit();
                json.put("listcolonne", listcolonne); 
                json.put("nameColIdGrid", "date"); 
                json.put("list", "listEditionDepense"); 
			}
            
		
			
			getResponse().setContentType(JSON_CONTENT_TYPE);      
			getResponse().getWriter().write(json.toString());
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	private HashMap  doTraiterListFinaldepense(List listEditionDepense, HashMap mapDataImpressionFourniture, HashMap mapDataImpressionService) throws Throwable {
		
		   HashMap  ligneTotal = new HashMap();
	       Double totQteFish = new Double(0);
	       Double totQteBox = new Double(0);
	       Double totAchatFish = new Double(0);
	       Double totAchatBox = new Double(0);
	       Double totTransport = new Double(0);
	       Double totTransit= new Double(0);
	       Double totMoeuvre= new Double(0);
	       Double totEmbal= new Double(0);
	       Double totGlassScot= new Double(0);
	       Double totDouane= new Double(0);
	       Double totChambreCom= new Double(0);
	       Double totTranAea= new Double(0);
	       Double totTotal= new Double(0);
	       
		 for(int i=0; i < listEditionDepense.size(); i++ ){
			 
		       EtatDepenseProduit bean = (EtatDepenseProduit) listEditionDepense.get(i); 
		       
		       HashMap mapDataImFournitureQte     =(HashMap) mapDataImpressionFourniture.get("qte"+bean.getDate()+bean.getInvoice()+bean.getClient());
		       HashMap mapDataImFournitureMnt     =(HashMap) mapDataImpressionFourniture.get("mnt"+bean.getDate()+bean.getInvoice()+bean.getClient());
		       HashMap mapDataImFournitureFam     =(HashMap) mapDataImpressionFourniture.get("famille"+bean.getDate()+bean.getInvoice()+bean.getClient());
		       
		       HashMap mapDataImpressionSrv       =(HashMap) mapDataImpressionService.get(bean.getDate()+bean.getInvoice()+bean.getClient());
		       Double totligne= new Double(0);
		       totligne= ProcessNumber.addition(totligne, bean.getPrixtotFish()!=null?bean.getPrixtotFish():new Double(0));
		       
		   
		       totQteFish= ProcessNumber.addition(totQteFish, bean.getQteFish()!=null?bean.getQteFish():new Double(0));
		       totAchatFish= ProcessNumber.addition(totAchatFish, bean.getPrixtotFish()!=null?bean.getPrixtotFish():new Double(0));
		       
		       
		       
			   String qteBoxSt="";
			   if(mapDataImFournitureQte!=null) {
				   Double  qteBox=(Double) mapDataImFournitureQte.get("60200002");
				   if(qteBox!=null) {
					   bean.setNbrBox(qteBox);
					   totQteBox= ProcessNumber.addition(totQteBox, qteBox);
					   qteBoxSt=ProcessFormatNbr.convertDoubleToIntString(qteBox);
				   }
			   }
			   String mntBoxSt="";
			   if(mapDataImFournitureMnt!=null) {
				   Double  mnt=(Double) mapDataImFournitureMnt.get("60200002");
				   if(mnt!=null) {  
					   bean.setPrixtotPoly(mnt);
				       totligne= ProcessNumber.addition(totligne, mnt);
				       totAchatBox= ProcessNumber.addition(totAchatBox, mnt);
					   mntBoxSt=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(mnt, "0.000");
				   }
			   }
			   
			   String mntGace="";
			   Double Glassmnt= new Double(0);
			   if(mapDataImFournitureMnt!=null) {
				    Glassmnt=(Double) mapDataImFournitureMnt.get("60200019");
				   if(Glassmnt!=null) {
					   mntGace=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(Glassmnt, "0.000");
				   }else {
					   Glassmnt= new Double(0); 
				   }
				   totGlassScot= ProcessNumber.addition(totGlassScot, Glassmnt);
				   totligne= ProcessNumber.addition(totligne, Glassmnt);
			   }
			   
			   String mntscot="";
			   Double mntscotD= new Double(0);
			   if(mapDataImFournitureMnt!=null) {
				      mntscotD=(Double) mapDataImFournitureMnt.get("60200004"); 
				   if(mntscotD!=null) {
					   mntscot=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(mntscotD, "0.000");
				   }else {
					   mntscotD= new Double(0);
				   }
				   totGlassScot= ProcessNumber.addition(totGlassScot, mntscotD);
				   totligne= ProcessNumber.addition(totligne, mntscotD);
			   }
			   Double scot_glace=ProcessNumber.addition(Glassmnt, mntscotD);
			   bean.setScot_glace(scot_glace);
			   
			   
			   String transport="";
			   if(mapDataImpressionSrv!=null) {
				     Double trsprt=(Double) mapDataImpressionSrv.get("60200016");
				   if(trsprt!=null) {bean.setTrsprt(trsprt);
				   totligne= ProcessNumber.addition(totligne, trsprt);
				   totTransport= ProcessNumber.addition(totTransport, trsprt);
				   transport=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(trsprt, "0.000");
				   }
			   }
			   String transits="";
			   if(mapDataImpressionSrv!=null) {
				     Double transit=(Double) mapDataImpressionSrv.get("60200003");
				   if(transit!=null) {bean.setTransit(transit);
				   totligne= ProcessNumber.addition(totligne, transit);
				   totTransit= ProcessNumber.addition(totTransit, transit);
				   transits=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(transit, "0.000");
				   }
			   }
			   
			   String meouv="";
			   if(mapDataImpressionSrv!=null) {
				     Double mnt=(Double) mapDataImpressionSrv.get("60200015");
				   if(mnt!=null) {bean.setMdOuevre(mnt);
				   totligne= ProcessNumber.addition(totligne, mnt);
				   totMoeuvre= ProcessNumber.addition(totMoeuvre, mnt);
				   meouv=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(mnt, "0.000");
				   }
			   }
			   
			   String emb="";
			   if(mapDataImFournitureFam!=null) {
				     Double mnt=(Double) mapDataImFournitureFam.get("emb");
				   if(mnt!=null) {
				   bean.setEmbal(mnt);
				   totligne= ProcessNumber.addition(totligne, mnt);
				   totEmbal= ProcessNumber.addition(totEmbal, mnt);
				   emb=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(mnt, "0.000");
				   }
			   }
			   
			   String chambreCom="";
			   if(mapDataImpressionSrv!=null) {
				     Double mnt=(Double) mapDataImpressionSrv.get("60200018");
				   if(mnt!=null) {bean.setChambreCom(mnt);
				   totligne= ProcessNumber.addition(totligne, mnt);
				   totChambreCom= ProcessNumber.addition(totChambreCom, mnt);
				   chambreCom=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(mnt, "0.000");
				   }
			   }
			   
			   
			   String transAren="";
			   if(mapDataImpressionSrv!=null) {
				     Double mnt=(Double) mapDataImpressionSrv.get("60200017");
				   if(mnt!=null) {bean.setTransAe(mnt);
				   totligne= ProcessNumber.addition(totligne, mnt);
				   totTranAea= ProcessNumber.addition(totTranAea, mnt);
				   transAren=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(mnt, "0.000");
				   }
			   }
			   
			   String douane="";
			   if(mapDataImpressionSrv!=null) {
				     Double mnt=(Double) mapDataImpressionSrv.get("60200023");
				   if(mnt!=null) {bean.setDouane(mnt);
				   totligne= ProcessNumber.addition(totligne, mnt);
				   totDouane= ProcessNumber.addition(totDouane, mnt);
				   douane=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(mnt, "0.000");
				   }
			   }
			   totTotal= ProcessNumber.addition(totTotal, totligne);
			   bean.setTotal( totligne!=null ? ProcessFormatNbr.FormatDouble_Troischiffre(totligne)  :new Double(0));
		 }
		 
		  
		   
	       ligneTotal.put("totQteFish",totQteFish);
	       ligneTotal.put("totQteBox",totQteBox);
	       ligneTotal.put("totAchatFish",totAchatFish);
	       ligneTotal.put("totAchatBox",totAchatBox);
	       ligneTotal.put("totTransport",totTransport);
	       ligneTotal.put("totTransit",totTransit);
	       ligneTotal.put("totMoeuvre",totMoeuvre); 
	       ligneTotal.put("totEmbal",totEmbal);
	       ligneTotal.put("totGlassScot",totGlassScot);
	       ligneTotal.put("totDouane",totDouane);
	       ligneTotal.put("totChambreCom",totChambreCom);
	       ligneTotal.put("totTranAea",totTranAea);
	       ligneTotal.put("totTotal",totTotal);
		 
	       return  ligneTotal;
		
	}
		    	
	private HashMap   doBuildDepenseVente(List<DetProcedureVenteBean> listDetVente) throws Exception {
	    List<EtatDepenseProduit> list_etat_edition = new ArrayList<EtatDepenseProduit>();
	       HashMap  mapDateFacture     = new HashMap();
		   HashMap  mapDate            = new HashMap();
	       Double   totGenAchatFish    = new Double(0);
	       Double   totGenQteAchatFish = new Double(0);
	       HashMap  mapReturn          = new HashMap();
	    
		try {
		   
		       DeviseBean  devise = new DeviseBean();
		       
		       for (int i = 0; i < listDetVente.size(); i++) {
					DetProcedureVenteBean dBean= listDetVente.get(i);
					Date dateFact=dBean.getPk().getVente().getFactclient().getFact_date();
					List listPardate=(List) mapDate.get(dateFact);
					if(listPardate==null) {listPardate= new ArrayList();  }
					listPardate.add(dBean);
					mapDate.put(dateFact, listPardate);
				}
		       
				Set set_mapdate= mapDate.keySet();
				for (Iterator iterr = set_mapdate.iterator(); iterr.hasNext();) {
					Date dateFact = (Date) iterr.next();
					List listMapDate=(List) mapDate.get(dateFact);
					boolean isrowSpanDate=true;
					
					HashMap  mapInvoiceClient=  new HashMap();
					for (int i = 0; i < listMapDate.size(); i++) {
						DetProcedureVenteBean dBean  =(DetProcedureVenteBean) listMapDate.get(i);
						String  key =dBean.getPk().getVente().getFactclient().getFact_ref_id()+"²µ²"+dBean.getPk().getVente().getFactclient().getClient().getClt_lib();
						List listInvoice=(List) mapInvoiceClient.get(key);
						if(listInvoice==null) {listInvoice= new ArrayList();  }
						listInvoice.add(dBean);
						mapInvoiceClient.put(key, listInvoice);
					}
					
					Set mapInvoiceClientSet = mapInvoiceClient.keySet(); 
					for (Iterator iterInvo = mapInvoiceClientSet.iterator(); iterInvo.hasNext();) {
						String iClientnvoice = (String) iterInvo.next();
						String[] element=iClientnvoice.split("²µ²");
//						boolean isrowSpanDetailFacture=true;
						List listInvoiceClient=(List) mapInvoiceClient.get(iClientnvoice);
						Double totAchatFishParFacture= new Double(0);
						Double totQteFishParFacture= new Double(0);
						for (int i = 0; i < listInvoiceClient.size(); i++) {
							DetProcedureVenteBean dBean  =(DetProcedureVenteBean) listInvoiceClient.get(i);
							 devise = new DeviseBean();
						     if(dBean.getMvt_stock()!=null  &&  dBean.getMvt_stock().getCout_unitaire_moyen_pondere()!=null  ) {
						    	 devise =dBean.getMvt_stock().getDevise();
						    	 Double prixUnitAchatFishTTC=ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getMvt_stock().getCout_unitaire_moyen_pondere(), devise.getChiffre_pattern());
						    	 Double PrixTot=ProcessNumber.PRODUIT(prixUnitAchatFishTTC, dBean.getQuantite());
						    	 
						    	 totAchatFishParFacture =ProcessNumber.addition(totAchatFishParFacture,   ProcessFormatNbr.FormatDouble_ParameterChiffre(PrixTot, devise.getChiffre_pattern())  );
						    	 totGenAchatFish      =ProcessNumber.addition(totGenAchatFish,   ProcessFormatNbr.FormatDouble_ParameterChiffre(PrixTot, devise.getChiffre_pattern())  );
						     }
						    totQteFishParFacture   =ProcessNumber.addition(totQteFishParFacture,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getQuantite(), devise.getChiffre_pattern())  );
						    totGenQteAchatFish     =ProcessNumber.addition(totGenQteAchatFish,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getQuantite(), devise.getChiffre_pattern())  );
//							isrowSpanDetailFacture=false;
						}
						EtatDepenseProduit    etatBean  =  new EtatDepenseProduit();
						etatBean.setDevise(devise);
					    etatBean.setIsrowSpanDate(isrowSpanDate);
					    etatBean.setRowSpanDate(mapInvoiceClientSet.size());
//					    etatBean.setIsrowSpanDetFact(isrowSpanDetailFacture);
					    //etatBean.setRowSpanDetFacture(listInvoiceClient.size());
						etatBean.setDate(dateFact);
						etatBean.setInvoice(element[0]);
						etatBean.setClient(element[1]);
//						etatBean.setClientId(dBean.getPk().getVente().getClient().getClt_id());
//						etatBean.setDescription(dBean.getPk().getFkcode_barre().getDesignation_libelle());
						etatBean.setQteFish(ProcessFormatNbr.FormatDouble_ParameterChiffre(totQteFishParFacture, devise.getChiffre_pattern())  ); 
						etatBean.setPrixtotFish(ProcessFormatNbr.FormatDouble_ParameterChiffre(totAchatFishParFacture, devise.getChiffre_pattern())  );
						list_etat_edition.add(etatBean);
						isrowSpanDate=false;
					}
				}

		} catch (Exception e) {
			throw e;
		}
		
		 
	          mapReturn.put("list", list_etat_edition);
	          mapReturn.put("totGenAchatFish", totGenAchatFish);
	          mapReturn.put("totGenQteAchatFish", totGenQteAchatFish);
	       
		return mapReturn;
	}
	
	private HashMap doBuildDepenseFourniture(List<DetFournitureVenteBean> listDetVente) throws Exception {
	   
		  HashMap  mapDataImpressionFourniture= new HashMap();
		  
		try {
	
			   HashMap  mapDate       = new HashMap();
		       DeviseBean  devise = new DeviseBean();
		       for (int i = 0; i < listDetVente.size(); i++) {
		    	    DetFournitureVenteBean dBean= listDetVente.get(i);
					Date dateFact=dBean.getFourniture().getVenteFrn().getFactclient().getFact_date();
					List listPardate=(List) mapDate.get(dateFact);
					if(listPardate==null) {listPardate= new ArrayList();  }
					listPardate.add(dBean);
					mapDate.put(dateFact, listPardate);
				}
				Set set_mapdate= mapDate.keySet();
			
				for (Iterator iterr = set_mapdate.iterator(); iterr.hasNext();) {
					Date dateFact = (Date) iterr.next();

					List listmapDate=(List) mapDate.get(dateFact);
					HashMap  mapInvoiceClient=  new HashMap();
					for (int i = 0; i < listmapDate.size(); i++) {
						DetFournitureVenteBean dBean  =(DetFournitureVenteBean) listmapDate.get(i);
						String  key =dBean.getFourniture().getVenteFrn().getFactclient().getFact_ref_id()+"²µ²"+dBean.getFourniture().getVenteFrn().getFactclient().getClient().getClt_lib();
						List listInvoice=(List) mapInvoiceClient.get(key);
						if(listInvoice==null) {listInvoice= new ArrayList();  }
						listInvoice.add(dBean);
						mapInvoiceClient.put(key, listInvoice);
					}
					
					Set mapInvoiceClientSet = mapInvoiceClient.keySet(); 
					
					for (Iterator iterInvo = mapInvoiceClientSet.iterator(); iterInvo.hasNext();) {
						String iClientnvoice = (String) iterInvo.next();
						String[] element=iClientnvoice.split("²µ²");
						 
						List listInvoiceClient=(List) mapInvoiceClient.get(iClientnvoice);
						 
						HashMap mapProduitQte= new HashMap();
						HashMap mapProduitMnt= new HashMap();
						HashMap mapFamilleproduit= new HashMap();
						
						for (int i = 0; i < listInvoiceClient.size(); i++) {
							DetFournitureVenteBean dBean  =(DetFournitureVenteBean) listInvoiceClient.get(i);
						    
						    String key=dBean.getFkcode_barre().getPk().getCode_barre();
						    String keyFamille=dBean.getFkcode_barre().getPk().getAr_bean().getFam_art().getFam_id();
						    Double qteProduit=(Double) mapProduitQte.get(key);
						    if(qteProduit==null )qteProduit= new Double(0);
						    qteProduit=ProcessNumber.addition(qteProduit, dBean.getQuantite());
						    mapProduitQte.put(key, qteProduit);
						   
						    Double mntProduit=(Double) mapProduitMnt.get(key);
						    if(mntProduit==null )mntProduit= new Double(0);
						    Double prixTotLigne= new Double(0);
						    
						    if(dBean.getMvt_stock()!=null  &&  dBean.getMvt_stock().getCout_unitaire_moyen_pondere()!=null  ) {
						    	 devise =dBean.getMvt_stock().getDevise();
						    	 Double prixUnitProduit=ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getMvt_stock().getCout_unitaire_moyen_pondere(), devise.getChiffre_pattern());
						    	 prixTotLigne=ProcessNumber.PRODUIT(prixUnitProduit, dBean.getQuantite());
						    } 
						    
						    mntProduit=ProcessNumber.addition(mntProduit, prixTotLigne);
						    mapProduitMnt.put(key, mntProduit);
						    
						    Double mntFamilleProduit=(Double) mapFamilleproduit.get(keyFamille);
						    if(mntFamilleProduit==null )mntFamilleProduit= new Double(0);
						    Double prixtotLigneFamille= new Double(0);
						    if(dBean.getMvt_stock()!=null  &&  dBean.getMvt_stock().getCout_unitaire_moyen_pondere()!=null  ) {
						    	 devise =dBean.getMvt_stock().getDevise();
						    	 Double prixUnitFamille=ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getMvt_stock().getCout_unitaire_moyen_pondere(), devise.getChiffre_pattern());
						    	 prixtotLigneFamille=ProcessNumber.PRODUIT(prixUnitFamille, dBean.getQuantite());
						     } 
						    mntFamilleProduit=ProcessNumber.addition(mntFamilleProduit, prixtotLigneFamille);
						    mapFamilleproduit.put(keyFamille, mntFamilleProduit);
						}
						mapDataImpressionFourniture.put("qte"+dateFact+element[0]+element[1], mapProduitQte);
						mapDataImpressionFourniture.put("mnt"+dateFact+element[0]+element[1], mapProduitMnt);
						mapDataImpressionFourniture.put("famille"+dateFact+element[0]+element[1], mapFamilleproduit);
					}
				}

				

		} catch (Exception e) {
			throw e;
		}
		return mapDataImpressionFourniture;
	}
	
	
	private HashMap  doBuildDepenseService(List<DetServiceBean> listDetVente) throws Exception {
	   
	    HashMap  mapDataImpressionService= new HashMap();
		try {
			  
			   HashMap  mapDate       = new HashMap();
		       DeviseBean  devise = new DeviseBean();
		       for (int i = 0; i < listDetVente.size(); i++) {
		    	    DetServiceBean dBean= listDetVente.get(i);
					devise=dBean.getService().getVenteSrv().getFactclient().getDevise();
					Date dateFact=dBean.getService().getVenteSrv().getFactclient().getFact_date();
					List listPardate=(List) mapDate.get(dateFact);
					if(listPardate==null) {listPardate= new ArrayList();  }
					listPardate.add(dBean);
					mapDate.put(dateFact, listPardate);
				}
				Set set_mapdate= mapDate.keySet();
			
				for (Iterator iterr = set_mapdate.iterator(); iterr.hasNext();) {
					Date dateFact = (Date) iterr.next();
					List listmapDate=(List) mapDate.get(dateFact);
					
					
					
					HashMap  mapInvoiceClient=  new HashMap();
					for (int i = 0; i < listmapDate.size(); i++) {
						DetServiceBean dBean  =(DetServiceBean) listmapDate.get(i);
						String  key =dBean.getService().getVenteSrv().getFactclient().getFact_ref_id()+"²µ²"+dBean.getService().getVenteSrv().getFactclient().getClient().getClt_lib();
						List listInvoice=(List) mapInvoiceClient.get(key);
						if(listInvoice==null) {listInvoice= new ArrayList();  }
						listInvoice.add(dBean);
						mapInvoiceClient.put(key, listInvoice);
					}
					
					Set mapInvoiceClientSet = mapInvoiceClient.keySet(); 
	 
					
					for (Iterator iterInvo = mapInvoiceClientSet.iterator(); iterInvo.hasNext();) {
						String iClientnvoice = (String) iterInvo.next();
						String[] element=iClientnvoice.split("²µ²");
						 
						List listInvoiceClient=(List) mapInvoiceClient.get(iClientnvoice);
						
						 
						HashMap mapProduitQte= new HashMap();
						HashMap mapProduitMnt= new HashMap();
						for (int i = 0; i < listInvoiceClient.size(); i++) {
							DetServiceBean dBean  =(DetServiceBean) listInvoiceClient.get(i);
						    
						    String key=dBean.getFkcode_barre().getPk().getCode_barre();
						    Double qteProduit=(Double) mapProduitQte.get(key);
						    if(qteProduit==null )qteProduit= new Double(0);
						    qteProduit=ProcessNumber.addition(qteProduit, dBean.getQuantite());
						    mapProduitQte.put(key, qteProduit);
						   
						    Double mntProduit=(Double) mapProduitMnt.get(key);
						    if(mntProduit==null )mntProduit= new Double(0);
						    Double prixTotLigne= new Double(0);
						    if(dBean.getTarifVente()!=null     ) {
						    	 devise =dBean.getTarifVente().getDevise();
						    	 Double prixUnitProduit=ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getTarifVente().getTarif_unit_vente_tt(), devise.getChiffre_pattern());
						    	 prixTotLigne=ProcessNumber.PRODUIT(prixUnitProduit, dBean.getQuantite());
						     }
						    mntProduit=ProcessNumber.addition(mntProduit, prixTotLigne);
						    mapProduitMnt.put(key, mntProduit);
						}
						mapDataImpressionService.put(dateFact+element[0]+element[1], mapProduitMnt);
					}
				}

				

		} catch (Exception e) {
			throw e;
		}
		return mapDataImpressionService;
	}



private JSONArray doWriteHeaderGridDataEtatVenteProduit() throws Exception {
	
		try {
			
			 JSONArray   listcol   = new JSONArray();
        	 JSONObject  element   = new JSONObject();
			 element.put("sTitle","Date");
			 element.put("sName","pk.factclient.fact_date");
			 element.put("sWidth","5%" );
			 element.put("bSortable","false" );
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
			 return listcol ;
			
		} catch (Exception e) {
			throw e;
		}
	}


private JSONArray doWriteHeaderGridDataEtatDepenseProduit() throws Exception {
	
	try {
		
		 JSONArray   listcol   = new JSONArray();
    	 JSONObject  element   = new JSONObject();
		 element.put("sTitle","Date");
		 element.put("sName","date");
		 element.put("sWidth","5%" );
		 element.put("bSortable","false" );
		 listcol.put(element);
		 
		 
		 element = new JSONObject();
		 element.put("sTitle","Invoice");
		 element.put("sName","invoice");
		 element.put("sWidth","5%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","Client");
		 element.put("sName","client");
		 element.put("sWidth","25%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
//		 element = new JSONObject();
//		 element.put("sTitle","Description");
//		 element.put("sName","description");
//		 element.put("sWidth","25%" );
//		 element.put("bSortable","true" );
//		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","Qté");
		 element.put("sName","qteFish");
		 element.put("sWidth","5%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","Achat fish");
		 element.put("sName","prixtotFish");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","nbrBox");
		 element.put("sName","nbrBox");
		 element.put("sWidth","5%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 
		 element = new JSONObject();
		 element.put("sTitle","prixtotPoly");
		 element.put("sName","prixtotPoly");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","trsprt");
		 element.put("sName","trsprt");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 
		 element = new JSONObject();
		 element.put("sTitle","transit");
		 element.put("sName","transit");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","mdOuevre");
		 element.put("sName","mdOuevre");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","Embal");
		 element.put("sName","Embal");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","scot_glace");
		 element.put("sName","scot_glace");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
			
		 element = new JSONObject();
		 element.put("sTitle","douane");
		 element.put("sName","douane");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
		 
		 element = new JSONObject();
		 element.put("sTitle","chambreCom");
		 element.put("sName","chambreCom");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
			
		 
		 element = new JSONObject();
		 element.put("sTitle","transAe");
		 element.put("sName","transAe");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
			
		 element = new JSONObject();
		 element.put("sTitle","total");
		 element.put("sClass","alignRight" );
		 element.put("formatMnt3","oui" );
		 element.put("sName","total");
		 element.put("sWidth","10%" );
		 element.put("bSortable","true" );
		 listcol.put(element);
			
		 
		
		 return listcol ;
		
	} catch (Exception e) {
		throw e;
	}
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
