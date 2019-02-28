package ERP.Process.Commerciale.Vente.Commandeclient.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.ResponsableLieu.model.ResponsableLieuBean;
import ERP.Process.Commerciale.Stock.ResponsableLieu.service.ResponsableLieuService;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.service.TarificationService;
import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.DetCmdCltBean;
import ERP.Process.Commerciale.Vente.Commandeclient.service.CommandeclientService;
import ERP.Process.Commerciale.Vente.Commandeclient.template.CommandeclientTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;

 
 
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.service.TVAService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.framework_dev.JQuery_datatables_Version1.web.ActionDataTablesManager;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class CommandeclientActionManager extends CommandeclientTemplate {

	private static final long serialVersionUID = 1293714076486196477L;
	
	private CommandeclientService serviceCommandeclient;

	@Autowired
	public void setServiceCommandeclient( CommandeclientService serviceCommandeclient ) {
		this.serviceCommandeclient = serviceCommandeclient;
	}
	private DocumentLotService serviceDocumentLot;

	@Autowired
	public void setServiceDocumentLot(DocumentLotService serviceDocumentLot) {
		this.serviceDocumentLot = serviceDocumentLot;
	}
	private ClientDAO daoClient;
	@Autowired
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}
	
	private TVAService   serviceTVA;
	@Autowired
	public void setServiceTva(TVAService serviceTVA) {
		this.serviceTVA = serviceTVA;
	}
	
	private ResponsableLieuService  serviceResponsableLieu;
	@Autowired
	public void setServiceResponsableLieu(ResponsableLieuService serviceResponsableLieu) {
	    this.serviceResponsableLieu = serviceResponsableLieu;
	} 
	
	private TarificationService serviceTarification;
	@Autowired
	public void setTarificationService(TarificationService serviceTarification) {
		this.serviceTarification = serviceTarification;
	}
	
	public    TarificationDAO daoTarification;
	@Autowired
	public void setDaoTarification(TarificationDAO daoTarification) {
		this.daoTarification = daoTarification;
	}

	@Autowired Code_barreService      serviceCode_barre;

	public ModelAndView doInitServletAction() {

       
		try {
			
			 setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			 removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
				
				
			 doLoadingLibelleOtherSModule("5");
			 doLoadingLibelleOtherSModule("62");
			 doLoadingLibelleOtherSModule("75");
			 doLoadingLibelleOtherSModule(CommandeclientTemplate.ID_SOUS_MODULE);
			 setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+CommandeclientTemplate.ID_SOUS_MODULE));
			 setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+CommandeclientTemplate.ID_SOUS_MODULE)) ;
			
		  
			 List list_des_tva= serviceTVA.doFetchDatafromServer(TVABean.class.newInstance());
			 setObjectValueModel(LIST_DES_TVA, list_des_tva);
			 
			
			 List list_client_devis= daoClient.doFindListClient(ClientBean.class.newInstance());
			 setObjectValueModel(LIST_CLIENT_CMD_CLT, list_client_devis);
			 
			 HashMap  mapclientBen = ProcessUtil.getHashMap_code_bean(list_client_devis, "clt_id");
			 setObjectValueModel(MAP_CLIENT_BEN, mapclientBen);
			
			 ResponsableLieuBean  resLieu =  new ResponsableLieuBean();
			 resLieu.getPk().getUsr().setUsr_id(bs.getUsr_id());
			 List <ResponsableLieuBean> listReslieux=serviceResponsableLieu.doFetchDatafromServer(resLieu);
			 if(listReslieux==null ||  listReslieux.size()==0)
				 throwNewException(" Utilisateur non affecté ");
			 List  listLieux= new ArrayList();
			 
			 DepotStockageBean  depot = null;
			 for(ResponsableLieuBean res:listReslieux){
				  if(depot==null) depot=res.getPk().getDepot();
				 listLieux.add(res.getPk().getDepot());
			 }
			 setObjectValueModel(LIST_DEPOT_STOCK_CMD , listLieux);
			 
			 
			List  <DetCmdCltBean>listGridCmd_client=  new  ArrayList<DetCmdCltBean>();
			setObjectValueModel(LIST_EDITABLE_CMD_CLT, listGridCmd_client);
			bs.setSousmod_libelle_title(bs.getSousmod_libelle());
		 

			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
				CommandeclientBean    devBean = new CommandeclientBean();
				devBean.setCmd_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()) );
				setObjectValueModel(FORM_BEAN, devBean);
				return getViewAdd(FORM_VIEW_EDIT);
			} else if(bs.getFct_id().equals(Fn_Contremander)){   
				
				
				bs.setSousmod_libelle_title((String)getObjectValueModel("cmd_frs"));
				setObjectValueModel(SEARCH_BEAN, new CommandeclientBean());
				return getViewFilterAjax(FILTER_VIEW);
				
			} else {
				
				setObjectValueModel(SEARCH_BEAN, new CommandeclientBean());
				return getViewFilterAjax(FILTER_VIEW);

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	@SuppressWarnings("unchecked")
	public   ModelAndView doAdd_row_Editable(CommandeclientBean detailBean ) throws Exception{
		  
		
		
		try {
			List   listOfmyData =(List) getObjectValueModel(LIST_EDITABLE_CMD_CLT);
			HashMap  map        = (HashMap) getObjectValueModel("mapclientBen");
			ClientBean   beancl =(ClientBean) map.get(detailBean.getClient().getClt_id());
			List list_article_dem_achatOrigine =(List) getObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE_ORIGINE);
			Double getTaux_remise_alacaisse = detailBean.getTaux_remise_alacaisse()==null?new Double(0):detailBean.getTaux_remise_alacaisse();
			HashMap  mapdA=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			if(mapdA.get(detailBean.getCode_barreX())!=null)  throw new Exception("Existe déjà");
			
			
			HashMap  MAP_ARTICLE=ProcessUtil.getHashMap_code_bean(list_article_dem_achatOrigine, "pk.code_barre");
			Code_barreBean  cBean=(Code_barreBean) MAP_ARTICLE.get(detailBean.getCode_barreX());
			HashMap  mapCodBarre =(HashMap) getObjectValueModel("mapCodBarre"  );
			Code_barreBean bCode_barreBean= (Code_barreBean) mapCodBarre.get(detailBean.getCode_barreX());
			
			
			
			
			DetCmdCltBean beanLigne= new DetCmdCltBean();
			beanLigne.getPk().setFkcode_barre(cBean);
			beanLigne.setQuantite(detailBean.getQuantiteX());
 			
			
		 
			
			
			TarificationBean ss= definitionTarification_devente(  detailBean,bCode_barreBean,beancl);
	    	if(ss!=null){
	    	
	    		beanLigne.setTarif(ss);
	    		//beanLigne.setQuantite_en_stock(quantite_en_stock);
	    		/*****************************************le cout ********************************************/
	    		Double qtex=ProcessFormatNbr.FormatDouble_Troischiffre(detailBean.getQuantiteX());
	    		Double getQuantiteX       = qtex==null?new Double(0):qtex;
	    		Double                 qte=ProcessFormatNbr.FormatDouble_Troischiffre(getQuantiteX);
	    		Double  cout              = ss.getCout()==null?new Double(0):ss.getCout().getTarif_unit_article();
	    		Double	Prixcout          = cout==null?new Double(0):cout;
	    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
	    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
	    		
	    		/*****************************************  setInfo  ********************************************/
	    		
	    		String libelle_desi=beanLigne.getPk().getFkcode_barre().getDesignation_libelle();
	    		 
	    		
	    		String groupe=beanLigne.getTarif().getGroupe().getType_trf_lib();
				String lot=beanLigne.getTarif().getTarif_lot()!=null && beanLigne.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
				String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
				beanLigne.setInfo(libelle_desi+natureprix);
	    		Double priUnitvente=ProcessFormatNbr.FormatDouble_Troischiffre(ss.getTarif_unit_vente());
	    		  
	    		
	    		
	    		/*****************************************Prix Unit Brute reel********************************************/
	    		
	    		Double montant_ht_vente_brute=ProcessNumber.PRODUIT(priUnitvente, qte);
	    		montant_ht_vente_brute=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente_brute);
	    		beanLigne.setMontant_ht_cmd_reel(montant_ht_vente_brute);
	    		 
	    		/*******************************************Remise********************************************************/
	    		
	    		Double taux_remiseligne     = ss.getTaux_remise()==null?new Double(0):ss.getTaux_remise();
				Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
				                    tot_taux=ProcessFormatNbr.FormatDouble_Troischiffre(tot_taux);
				beanLigne.setTaux_remise_ligne(tot_taux);
				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_cmd_reel(), tot_taux);
				beanLigne.setMontant_remise_ligne(ProcessFormatNbr.FormatDouble_Troischiffre(montant_Remise_Ligne));

    		    /*******************************************montant_ht_vente *********************************************/   
    		    
    		    Double montant_ht_vente=ProcessNumber.SOUSTRACTION(montant_ht_vente_brute, montant_Remise_Ligne);
    		    montant_ht_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente);
    		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_vente));
	    		
	    		/*********************************************montant_tva_vente ******************************************/
	    		 
	    		Double montant_tva_vente=ProcessNumber.Pourcentage(montant_ht_vente, ss.getTvaBean().getTva_value());
	    		montant_tva_vente=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_vente);
	    		beanLigne.setMontant_tva_vente(montant_tva_vente);
	    		
	    		/*********************************************montant_ttc_vente *******************************************/
	    		
	    		Double  montant_ttc_vente=ProcessNumber.addition(montant_ht_vente, montant_tva_vente);
	    		beanLigne.setMontant_ttc_cmd(montant_ttc_vente);
	    		
	    		/*********************************************montant_benefice *******************************************/
	    		/*if(le_cout.doubleValue()>0){
	    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_vente, le_cout);
	    		montant_benefice          =ProcessFormatNbr.FormatDouble_Troischiffre(montant_benefice);
	    		beanLigne.setMontant_benefice(montant_benefice);
	    		}*/
	    		/**********************************************************************************************************/
	    		 
	    		
	    	}
	    	
			listOfmyData.add(beanLigne);
			setObjectValueModel(LIST_EDITABLE_CMD_CLT,listOfmyData);
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_dem_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_dem_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_dem_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE_GRID,list_article_dem_achatGrid);
					setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE     ,list_article_dem_achat);
				}
				
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
	private TarificationBean definitionTarification_devente( CommandeclientBean detailBean ,Code_barreBean bCode_barreBean , ClientBean beancl ) throws Exception{
		 
		TarificationBean  ss  = null; 
		 try {
			
		   HashMap  mapTarification=(HashMap) getObjectValueModel(MAP_TARIFICATION);
		   ss          = (TarificationBean) mapTarification.get(detailBean.getCode_barreX()+"µ"+beancl.getTyp_trfBean().getType_trf_id());
	     if(bCode_barreBean.getPk().getAr_bean().getMode()!=null 
	    		 &&  bCode_barreBean.getPk().getAr_bean().getMode().getData_id().equals("pl")){
	    	 SerieArticletBean searchBean  = new SerieArticletBean();
	    	 searchBean.setCondition_list_article("  AND  bean.quantite > 0   AND  ( bean.serie_bloque  is null  or  bean.serie_bloque = false ) " +
	    						"                    AND  bean.selected='X' ");
	    	 searchBean.setFkCode_barre(bCode_barreBean);
	    	 searchBean.getPk().setDepot(detailBean.getDepot());
	    	 List<SerieArticletBean> list_DataSrv              = serviceDocumentLot.doFetchDatafromServer(searchBean);
	    	 String  Lot= "" ;
	    	 Double qt= new Double(0);
	    	 for (int i = 0; i < list_DataSrv.size(); i++) {
	    		 SerieArticletBean sBean =  list_DataSrv.get(i);
	    		 qt=ProcessNumber.addition(qt, sBean.getQuantite());
	    		 Lot=sBean.getPk().getNum_serie();
			 }
	    	 if(qt.doubleValue()<detailBean.getQuantiteX().doubleValue())
			    	throwNewException("cette quantité n'est pas disponible en Lot choisis");
	    	 
	    	 
        	 TarificationBean search2= new TarificationBean();
        	 search2.setDernierPrix(true);
        	 search2.setDate_trf3(detailBean.getCmd_date());
        	 search2.setFkCode_barre(bCode_barreBean);
        	 search2.setGroupe(beancl.getTyp_trfBean());
        	 search2.setTarif_lot(true);
        	 search2.setDepot(detailBean.getDepot());
        	 search2.setNum_serie(Lot);
             List<TarificationBean> listDatafro2  = serviceTarification.doFetchDatafromServer(search2);
             if(listDatafro2!=null  && listDatafro2.size()>0 && listDatafro2.size()>1){
            	 throwNewException("Il ya deux prix de lot  dans la même journée pour cet article. Veuillez Vérifier les Tarifs de vente ");
             }
	         if(listDatafro2!=null  && listDatafro2.size()>0 && listDatafro2.size()==1){
	        	 ss=listDatafro2.get(0);
	        	String groupe=ss.getGroupe().getType_trf_lib();
				String lot=ss.getTarif_lot()!=null && ss.getTarif_lot().equals(true)?" * de lot   ":"";
	         }else{
	        	 throwNewException("Manque Tarification de Lot pour cette article"); 
	         }
	     }else {
	     }
	     
	     
		
		if(ss==null)  
		{ 
			ss  =(TarificationBean) mapTarification.get(detailBean.getCode_barreX()+"µ"+GROUPE_TARIF_VENTE_PUBLIC);
		}
		if(ss==null)  throwNewException("Manque Tarification pour cette article");
		
		 } catch (Exception e) {
				throw e;
			}
		 
		return ss;
	}
	@SuppressWarnings("unchecked")
	public   ModelAndView doDelete_row_Editable( ) throws Exception{
		  
		
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_CMD_CLT);
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				
				DetCmdCltBean newBean= (DetCmdCltBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
			}
			if(!del) throw new Exception ((String) getObjectValueModel("_cochezAumoin"));
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			List list_article_achatOrigine =(List) getObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE_ORIGINE);
			List list_article_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
				}
				
			}
			setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE_GRID,list_article_achatGrid);
			setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE,list_article_dem_achat);
			 
			getResponse().setContentType("text");
			 
			} catch (Exception e) {
					getResponse().setContentType(HTML_CONTENT_TYPE);
					PrintWriter out = getResponse().getWriter();
					out.println(e.getMessage());
				    out.close();
			}
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public   ModelAndView doCheked_unCheked( ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_CMD_CLT);
			String to_check=getRequest().getParameter("statucheked")==null?"":getRequest().getParameter("statucheked");
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetCmdCltBean newBean =(DetCmdCltBean) listOfmyData.get(i);
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
	
	@SuppressWarnings("unchecked")
	public   ModelAndView doActualiser_List( ) throws Exception{
		  
		
		
		try {
			
			List listOfmyData=(List) getObjectValueModel(LIST_EDITABLE_CMD_CLT);
			
			List list_article_recp_achat_origine =(List) getObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE_ORIGINE);
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetCmdCltBean  achatBean= (DetCmdCltBean) listOfmyData.get(i);
				String ar_id=(String) getValueObjSimpleFromList
				(achatBean.getPk().getFkcode_barre().getPk().getCode_barre(), list_article_recp_achat_origine, "pk.code_barre", "pk.ar_bean.pk_article.ar_id");
				achatBean.getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().setAr_id(ar_id);
			}
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk.fkcode_barre.pk.code_barre");
			List list_article_achatGrid = new ArrayList();
			List list_article_achat = new ArrayList();
			for (int i = 0; i < list_article_recp_achat_origine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_recp_achat_origine.get(i);
				
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_achatGrid.add(bean);
					list_article_achat.add(bean);
					setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE_GRID,list_article_achatGrid);
					setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE,list_article_achat);
				}
				
			}
			getResponse().setContentType("text");
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
	@SuppressWarnings("unchecked")
	public   ModelAndView doActualiser_GRID( CommandeclientBean detailBean ) throws Exception{
			
			try {
				Double getTaux_remise_alacaisse = detailBean.getTaux_remise_alacaisse()==null?new Double(0):detailBean.getTaux_remise_alacaisse();

				 
				List listOData=(List) getObjectValueModel(LIST_EDITABLE_CMD_CLT);
				JsonObject data = new JsonObject();
				 
				CommandeclientBean    cmdbean=(CommandeclientBean) getObjectValueModel(FORM_BEAN); 
				for (int i = 0; i < listOData.size(); i++) {
					DetCmdCltBean beanLigne= (DetCmdCltBean) listOData.get(i);
					
					
					data.addProperty("erreur"+beanLigne.getPk().getFkcode_barre().getPk().getCode_barre(),"");
					TarificationBean  tarif  = beanLigne.getTarif();  
					
					if(tarif==null)  throwNewException("Manque Tarification pour cette article");
					
			    	if(tarif!=null){
			    		if( beanLigne.getQuantite() == null  ) continue;
			    		if( beanLigne.getQuantite() < 0 ) {
			    			beanLigne.setQuantite(new Double(0));
			    			String err=" Il existe un ou plusieurs quantité(s) inférieur a zéro ";
			    			data.addProperty("erreur"+beanLigne.getPk().getFkcode_barre().getPk().getCode_barre(),err);
			    			data.addProperty("Qte"+beanLigne.getPk().getFkcode_barre().getPk().getCode_barre(),"0");
			    			data.addProperty(beanLigne.getPk().getFkcode_barre().getPk().getCode_barre(),"0");
			    			continue;
			    			
			    		}
			    		 
			    		 
			    		String lib=beanLigne.getPk().getFkcode_barre().getDesignation_libelle();
						String groupe=beanLigne.getTarif().getGroupe().getType_trf_lib();
						String lot=beanLigne.getTarif().getTarif_lot()!=null && beanLigne.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
						String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
						beanLigne.setInfo(lib+natureprix);
						/*****************************************le cout ********************************************/
			    		Double getQuantiteX       = beanLigne.getQuantite()==null?new Double(0):beanLigne.getQuantite();
			    		Double                 qte=ProcessFormatNbr.FormatDouble_Troischiffre(getQuantiteX);
			    		Double  cout              = beanLigne.getTarif().getCout()==null?new Double(0):beanLigne.getTarif().getCout().getTarif_unit_article();
			    		Double	Prixcout          = cout==null?new Double(0):cout;
			    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
			    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
			    		
			    		/*****************************************  setInfo  ********************************************/
			    		
			    		 
			    		Double priUnitcmd=ProcessFormatNbr.FormatDouble_Troischiffre(beanLigne.getTarif().getTarif_unit_vente());
			    		  
			    	 
			    		   
			    		  
			    		
			    		/*****************************************Prix Unit Brute reel********************************************/
			    		
			    		Double montant_ht_cmd_brute=ProcessNumber.PRODUIT(priUnitcmd, qte);
			    		montant_ht_cmd_brute=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_cmd_brute);
			    		beanLigne.setMontant_ht_cmd_reel(montant_ht_cmd_brute);
			    		 
			    		/*******************************************Remise********************************************************/
			    		
			    		Double taux_remiseligne     = beanLigne.getTarif().getTaux_remise()==null?new Double(0):beanLigne.getTarif().getTaux_remise();
						Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
						                    tot_taux=ProcessFormatNbr.FormatDouble_Troischiffre(tot_taux);
						beanLigne.setTaux_remise_ligne(tot_taux);
						Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_cmd_reel(), tot_taux);
						beanLigne.setMontant_remise_ligne(ProcessFormatNbr.FormatDouble_Troischiffre(montant_Remise_Ligne));
 
		    		    /*******************************************montant_ht_cmd *********************************************/   
		    		    
		    		    Double montant_ht_cmd=ProcessNumber.SOUSTRACTION(montant_ht_cmd_brute, montant_Remise_Ligne);
		    		    montant_ht_cmd=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_cmd);
		    		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_cmd));
 			    		
			    		/*********************************************montant_tva_cmd ******************************************/
			    		 
			    		Double montant_tva_cmd=ProcessNumber.Pourcentage(montant_ht_cmd, beanLigne.getTarif().getTvaBean().getTva_value());
			    		montant_tva_cmd=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_cmd);
			    		beanLigne.setMontant_tva_vente(montant_tva_cmd);
			    		
			    		/*********************************************montant_ttc_cmd *******************************************/
			    		
			    		Double  montant_ttc_cmd=ProcessNumber.addition(montant_ht_cmd, montant_tva_cmd);
			    		beanLigne.setMontant_ttc_cmd(montant_ttc_cmd);
		    		            
			    		Double number=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_cmd);  
			    		String Elm=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(number)  ; 
			    		data.addProperty(beanLigne.getPk().getFkcode_barre().getPk().getCode_barre(),Elm);
			    	}
				}
				
				 setObjectValueModel(LIST_EDITABLE_CMD_CLT,listOData);
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
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchForTarif(CommandeclientBean searchBean)throws Exception { 
		
		
		PrintWriter out = getResponse().getWriter();
	    try {
		
        getResponse().setContentType(HTML_CONTENT_TYPE);
     
        
        
        chargerInformationForTarif(searchBean);
        
        
         
 
        } catch (Exception e) {
        	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
        return null;
		
		
	
	}

	private void chargerInformationForTarif(CommandeclientBean searchBean) throws Exception {
		 try {
			    HashMap  mapclient = (HashMap)getObjectValueModel( MAP_CLIENT_BEN);
		        ClientBean  ben=(ClientBean) mapclient.get(searchBean.getClient().getClt_id());
		        
		        TarificationBean tBean = new TarificationBean();
		        tBean.setDate_trf(searchBean.getCmd_date());
		      
		       
		        List    list_Tarification_vente  =   daoTarification.doFind_DateMax_TarificationVente(tBean);
		   	 	HashMap mapTarification_vente    =   serviceTarification.doTraiTerListTarif_vente(list_Tarification_vente);
		   	 	HashMap mapTarific_vente         =   ProcessUtil.getHashMap_Key_List_FromList(list_Tarification_vente, "groupe.type_trf_id");
		   	
		   	 
		   	 	setObjectValueModel(MAP_TARIFICATION,  mapTarification_vente);
		   	 	setObjectValueModel(MAP_TOGET_LIST_TARIFICA, mapTarific_vente);
		        
		        
		        HashMap  mapTypeTarif = (HashMap)getObjectValueModel(MAP_TOGET_LIST_TARIFICA);
		        
		        List listTarif=(List)mapTypeTarif.get(String.valueOf(ben.getTyp_trfBean().getType_trf_id()));
		        List listTarif_public=(List)mapTypeTarif.get(GROUPE_TARIF_VENTE_PUBLIC);//public
		        
		        if(listTarif== null ) listTarif= new ArrayList();
		        if(listTarif_public== null ) listTarif_public= new ArrayList();
		        
		       
		        HashMap  mapDeclient  = new HashMap();
		        List  fetchDatafromSe = new ArrayList();
		        for (int i = 0; i < listTarif.size(); i++) {
		        	TarificationBean  ta=(TarificationBean)listTarif.get(i);
		        	fetchDatafromSe.add(ta.getFkCode_barre());
		        	String Key=ta.getFkCode_barre().getPk().getCode_barre();
		        	String Client=(String) mapDeclient.get(Key);
		        	if(Client==null){
		        		mapDeclient.put(Key, "existe");
		        	}
		        }
		        
		        for (int i = 0; i < listTarif_public.size(); i++) {
		        	TarificationBean  ta=(TarificationBean)listTarif_public.get(i);
		        	String Key=ta.getFkCode_barre().getPk().getCode_barre();
		        	String Client=(String) mapDeclient.get(Key);
		        	if(Client==null){
		        		fetchDatafromSe.add(ta.getFkCode_barre());
		        	}
		        }
		        
		        
		        
		        
			 
		        setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE        , fetchDatafromSe);
				setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE_ORIGINE, ProcessUtil.cloneList(fetchDatafromSe));
				setObjectValueModel(LIST_ARTICLE_CMD_CLT_VENTE_GRID   , ProcessUtil.cloneList(fetchDatafromSe));
				HashMap mapCodBarre=(HashMap) ProcessUtil.getHashMap_code_bean(fetchDatafromSe, "pk.code_barre"); 
				setObjectValueModel("mapCodBarre", mapCodBarre);
		} catch (Exception e) {
			throw e;
		}
		
	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(CommandeclientBean searchBean)
			throws Throwable {
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			
			String  cond="     AND   bean.cmd_id  not in ('') " ;
			
			if (bs.getFct_id().equals(Fn_Confirmer) || bs.getFct_id().equals(Fn_Modifier) || bs.getFct_id().equals(Fn_Supprimer)) {
				   cond+="  AND  bean.modeBean.fct_id  in ( '"+Fn_Créer+"' , '"+Fn_Modifier+"')  " ;
			}
			 
			searchBean.setCondition_select_mode(cond);

			List listDataSrv = serviceCommandeclient.doFetchDatafromServer(searchBean);
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

	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculer_Total(CommandeclientBean   detailBean ) throws Exception {
		
		 
		 
		try {
			CommandeclientBean   rowBean = (CommandeclientBean) getObjectValueModel(FORM_BEAN);
			List <DetCmdCltBean >List_detaille= new ArrayList<DetCmdCltBean>();
			BeanSession bs                =(BeanSession)getObjectValueModel(BEAN_SESSION);
			Double remise_ala_caisse    			  =  new Double(0);
			Double totalre_mise          			  =  new Double(0);
			 
			
			Double getTaux_remise_alacaisse = detailBean.getTaux_remise_alacaisse()==null?new Double(0):detailBean.getTaux_remise_alacaisse();
			Double getAvance_montant_cmd  = detailBean.getAvance_montant_cmd()==null?new Double(0):detailBean.getAvance_montant_cmd();
			Double avance                   = ProcessFormatNbr.FormatDouble_Troischiffre(getAvance_montant_cmd);
			 
			
			if(bs.getFct_id().equals(Fn_Créer) ||   bs.getFct_id().equals(Fn_Modifier) ){
				 List_detaille=(List<DetCmdCltBean>) getObjectValueModel(LIST_EDITABLE_CMD_CLT);
				 setObjectValueModel(FORM_BEAN,detailBean);
					
					
			}else{
				  List_detaille=serviceCommandeclient.doFetchDetailleDatafromServer(rowBean);
				  setObjectValueModel(LIST_EDITABLE_CMD_CLT, List_detaille);
				  setObjectValueModel(LIST_EDITABLE_CMD_CLT_ORIGIN, ProcessUtil.cloneList(List_detaille) );
				  getTaux_remise_alacaisse = rowBean.getTaux_remise_alacaisse()==null?new Double(0):rowBean.getTaux_remise_alacaisse();
				  getAvance_montant_cmd  = rowBean.getAvance_montant_cmd()==null?new Double(0):rowBean.getAvance_montant_cmd();
				  avance                   = ProcessFormatNbr.FormatDouble_Troischiffre(getAvance_montant_cmd);
				  
				
			}
			
			       
			Double tot_ht_brute =new Double(0);
			Double tot_ht_net    =new Double(0);
			Double tot_tva=new Double(0);
			Double tot_qte=new Double(0);
			 
			
			
			HashMap   map_des_Tva = new HashMap();
			for (int i = 0; i < List_detaille.size(); i++) {
				DetCmdCltBean  beanLigne=List_detaille.get(i);
				
				String lib=beanLigne.getPk().getFkcode_barre().getDesignation_libelle();
				String groupe=beanLigne.getTarif().getGroupe().getType_trf_lib();
				String lot=beanLigne.getTarif().getTarif_lot()!=null && beanLigne.getTarif().getTarif_lot().equals(true)?" * de lot   ":"";
				String natureprix="<br>"+" <p style='color:red;margin-left:20%;font-size:8px;'># Prix "+lot+" * "+groupe+"</p>";
				beanLigne.setInfo(lib+natureprix);
				/*****************************************le cout ********************************************/
	    		Double getQuantiteX       = beanLigne.getQuantite()==null?new Double(0):beanLigne.getQuantite();
	    		Double                 qte=ProcessFormatNbr.FormatDouble_Troischiffre(getQuantiteX);
	    		Double  cout              = beanLigne.getTarif().getCout()==null?new Double(0):beanLigne.getTarif().getCout().getTarif_unit_article();
	    		Double	Prixcout          = cout==null?new Double(0):cout;
	    		Double le_cout            = ProcessNumber.PRODUIT(Prixcout, qte);
	    		le_cout=ProcessFormatNbr.FormatDouble_Troischiffre(le_cout);
	    		
	    		/*****************************************  setInfo  ********************************************/
	    		
	    		 
	    		Double priUnitcmd=ProcessFormatNbr.FormatDouble_Troischiffre(beanLigne.getTarif().getTarif_unit_vente());
	    		  
	    	 
	    		   
	    		  
	    		
	    		/*****************************************Prix Unit Brute reel********************************************/
	    		
	    		Double montant_ht_cmd_brute=ProcessNumber.PRODUIT(priUnitcmd, qte);
	    		montant_ht_cmd_brute=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_cmd_brute);
	    		beanLigne.setMontant_ht_cmd_reel(montant_ht_cmd_brute);
	    		tot_qte     =ProcessNumber.addition(tot_qte, qte);
				tot_ht_brute=ProcessNumber.addition(tot_ht_brute, beanLigne.getMontant_ht_cmd_reel());
	    		 
	    		/*******************************************Remise********************************************************/
	    		
	    		Double taux_remiseligne     = beanLigne.getTarif().getTaux_remise()==null?new Double(0):beanLigne.getTarif().getTaux_remise();
				Double tot_taux             = ProcessNumber.addition(getTaux_remise_alacaisse, taux_remiseligne); 
				                    tot_taux=ProcessFormatNbr.FormatDouble_Troischiffre(tot_taux);
				beanLigne.setTaux_remise_ligne(tot_taux);
				Double montant_Remise_Ligne  = ProcessNumber.Pourcentage(beanLigne.getMontant_ht_cmd_reel(), tot_taux);
				beanLigne.setMontant_remise_ligne(ProcessFormatNbr.FormatDouble_Troischiffre(montant_Remise_Ligne));
				totalre_mise=ProcessNumber.addition(totalre_mise, montant_Remise_Ligne);

    		    /*******************************************montant_ht_cmd *********************************************/   
    		    
    		    Double montant_ht_cmd=ProcessNumber.SOUSTRACTION(montant_ht_cmd_brute, montant_Remise_Ligne);
    		    montant_ht_cmd=ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_cmd);
    		    beanLigne.setMontant_ht_vente(ProcessFormatNbr.FormatDouble_Troischiffre(montant_ht_cmd));
    		    tot_ht_net=ProcessNumber.addition(tot_ht_net, beanLigne.getMontant_ht_vente());
	    		
	    		/*********************************************montant_tva_cmd ******************************************/
	    		 
	    		Double montant_tva_cmd=ProcessNumber.Pourcentage(montant_ht_cmd, beanLigne.getTarif().getTvaBean().getTva_value());
	    		montant_tva_cmd=ProcessFormatNbr.FormatDouble_Troischiffre(montant_tva_cmd);
	    		beanLigne.setMontant_tva_vente(montant_tva_cmd);
	    		tot_tva=ProcessNumber.addition(tot_tva, beanLigne.getMontant_tva_vente());
	    		
	    		/*********************************************montant_ttc_cmd *******************************************/
	    		
	    		Double  montant_ttc_cmd=ProcessNumber.addition(montant_ht_cmd, montant_tva_cmd);
	    		beanLigne.setMontant_ttc_cmd(montant_ttc_cmd);
	    		
	    		/*********************************************montant_benefice *******************************************/
	    		/*if(le_cout.doubleValue()>0){
	    		Double	 montant_benefice = ProcessNumber.SOUSTRACTION(montant_ht_cmd, le_cout);
	    		montant_benefice          =ProcessFormatNbr.FormatDouble_Troischiffre(montant_benefice);
	    		beanLigne.setMontant_benefice(montant_benefice);
	    		marge_benefice_cmd=ProcessNumber.addition(marge_benefice_cmd, montant_benefice);
	    		}*/
	    		/**********************************************************************************************************/
				 
				
				List  listTva = (List) map_des_Tva.get(beanLigne.getTarif().getTvaBean().getTva_libelle());
				if(listTva==null)listTva= new ArrayList();
				listTva.add(beanLigne);
				map_des_Tva.put(beanLigne.getTarif().getTvaBean().getTva_libelle(), listTva);
				
				/**********************************************************************************************************/
			}
			
			CommandeclientBean    beanTotal = new CommandeclientBean();
			 beanTotal.setAvance_montant_cmd(avance)  ; 
			 //beanTotal.setMarge_benefice_cmd(ProcessFormatNbr.FormatDouble_Troischiffre(marge_benefice_cmd));
			 JSONObject json        = new JSONObject();
			 JSONArray  listDataTva = new JSONArray();
			 JSONArray  list_total  = new JSONArray();
			  
			 JSONObject  el_zero = new JSONObject();
			 el_zero.put("td1","0");
			 el_zero.put("value1","");
			 listDataTva.put(el_zero);
				 
				 /*********************************************************ENTETE***************************************************/
			 el_zero = new JSONObject();
			 el_zero.put("td1","0");
			 el_zero.put("value1","Taux");
			 el_zero.put("td2","1");
			 el_zero.put("value2","Base");
			 el_zero.put("td3","2");
			 el_zero.put("value3","Montant");
			 listDataTva.put(el_zero);
				 
				 /********************************************************************************************************************/
				 
				 HashMap   mapTvaImpression = new HashMap();
				 
				 
				 Double    total_leTva  = new Double(0);
				 List <TVABean> list_des_tva=  (List) getObjectValueModel(LIST_DES_TVA);
				 Double le_Ht_Net  = new Double(0);
				 Double le_Ht_Reel  = new Double(0);
				 for (int j = 0; j < list_des_tva.size(); j++) {
					 TVABean beanTva=list_des_tva.get(j);
					 
					 if(map_des_Tva.get(beanTva.getTva_libelle())!=null){
						 List listTva  =(List) map_des_Tva.get(beanTva.getTva_libelle());
						 String  tva   = beanTva.getTva_libelle();
						  	
						 Double le_Ht_netLigne  = new Double(0);
					     Double leTva  = new Double(0);
					 	 for (int i = 0; i < listTva.size(); i++) {
					 		DetCmdCltBean  bean=(DetCmdCltBean) listTva.get(i);
					 		le_Ht_netLigne=ProcessNumber.addition(le_Ht_netLigne, bean.getMontant_ht_vente());
							le_Ht_Net=ProcessNumber.addition(le_Ht_Net, bean.getMontant_ht_vente());
							le_Ht_Reel=ProcessNumber.addition(le_Ht_Reel, bean.getMontant_ht_cmd_reel());
							leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_vente());
						 }
					 	 total_leTva=  ProcessNumber.addition(total_leTva, leTva);
						 
						 JSONObject  element = new JSONObject();
						 element.put("td1","0");
						 element.put("value1",String.valueOf(tva));
					     
					     String base=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(le_Ht_netLigne);
					     
						 element.put("td2","1");
						 element.put("value2",base);
					     
						 element.put("td3","2");
						 String tvaB=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(leTva);
						  
						 element.put("value3",tvaB);
						 listDataTva.put(element);
						 mapTvaImpression.put(tva, base+"£"+tvaB);
					 }
				 }
			 
			 json.put("list_tva", listDataTva);
			 setObjectValueModel("mapTvaImpression", mapTvaImpression);
			
			  
			 JSONObject  element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Brut.H.T");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(le_Ht_Reel));
			 list_total.put(element);
			 
			 if( detailBean.getTaux_remise_alacaisse()!=null  &&  le_Ht_Reel.doubleValue() >0 ){
				 remise_ala_caisse  = ProcessNumber.Pourcentage(le_Ht_Reel, detailBean.getTaux_remise_alacaisse());
				 remise_ala_caisse  = ProcessFormatNbr.FormatDouble_Troischiffre(remise_ala_caisse);
			 }
			 beanTotal.setCmd_remise_alacaisse(remise_ala_caisse);
			 totalre_mise  = ProcessFormatNbr.FormatDouble_Troischiffre(totalre_mise);
			 beanTotal.setCommande_remise(totalre_mise); 
			 json.put("commande_remise",  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totalre_mise));	
			 json.put("cmd_remise_alacaisse",  ProcessFormatNbr.FormatDouble_To_String_Troischiffre(remise_ala_caisse));
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Remise");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totalre_mise));
			 list_total.put(element);
			
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Net.H.T");
			 element.put("td2","5");
			 Double ht_apres_remise =  ProcessNumber.SOUSTRACTION(le_Ht_Reel, totalre_mise)  ;
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(ht_apres_remise));
			 list_total.put(element);
			 beanTotal.setCommande_mnt_ht(ProcessFormatNbr.FormatDouble_Troischiffre(ht_apres_remise));
			 
			
			 
			 
			 
			
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1"," Total TVA");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_leTva));
			 beanTotal.setCommande_mnt_tva(ProcessFormatNbr.FormatDouble_Troischiffre(total_leTva));
			 list_total.put(element);
			 
			 
			 double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
			 beanTotal.setCommande_mnt_total(ProcessFormatNbr.FormatDouble_Troischiffre(total_mnt_gen));
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Total TTC");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mnt_gen));
			 json.put("cmd_mnt_total",   ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mnt_gen));
			 list_total.put(element);
			  
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Avance");
			 element.put("td2","5");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(avance));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","4");
			 element.put("value1","Net à payer");
			 element.put("td2","5");
			 Double net_a_payer=ProcessNumber.SOUSTRACTION(total_mnt_gen, avance);
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(net_a_payer));
			 beanTotal.setCmd_mnt_net_a_payer(ProcessFormatNbr.FormatDouble_Troischiffre(net_a_payer));
			 json.put("cmd_mnt_net_a_payer",   ProcessFormatNbr.FormatDouble_To_String_Troischiffre(net_a_payer));
			 list_total.put(element);
			 
			 
			  
			 
			 
			 json.put("list_total", list_total);
			 setObjectValueModel("list_total", list_total);
			 setObjectValueModel(BEAN_TOTAL, beanTotal);
			 getResponse().setContentType(JSON_CONTENT_TYPE);      
			 getResponse().getWriter().write(json.toString());
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
		
		 
		
	}
	
	 

	public    ModelAndView doPrintPDF_detaille() throws Exception   {
		 
		List   lisData=  (List) getObjectValueModel(LIST_EDITABLE_CMD_CLT) ;
		CommandeclientBean    denBean= (CommandeclientBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+LIST_EDITABLE_CMD_CLT+getRequest().getSession().getId()+".pdf");
	    FileOutputStream fs = new FileOutputStream(file);
	    GeneratePdf  genpdf= new GeneratePdf();
		try {
			 Document document=GeneratePdf.doGenerateDocumentFormat();
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	        genpdf.doWriteHeaderDocument_PDF(document,fs,MapfieldBean_detaille,bSession);
	        
	        doWriteEntete(document,denBean);
	        doWrite_Header_ContentTable(document,96,MapfieldBean_detaille);
	        doWrite_Data_Table (lisData,document,96,MapfieldBean_detaille);
	        doWrite_Tva_Total_Piece(lisData,document);  
	        
	        document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write(LIST_EDITABLE_CMD_CLT+getRequest().getSession().getId()+".pdf");
		} catch (Exception e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

     }
	
	  public   void doWrite_Data_Table(List lisData, Document document,int poucentage,String[][] mapFieldBean) throws Exception, SecurityException {
		  
		    PdfPTable table = new PdfPTable(mapFieldBean.length);
		    int PaddingBottom=5;
	        int[] columnWidths = new int[mapFieldBean.length] ;
	        for(int i = 0; i < mapFieldBean.length; i++){
	      	columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
			}
	        table.setWidthPercentage(poucentage);
	        table.setWidths(columnWidths);
		  
			  for(int i=0; i < lisData.size(); i++ ){
				   Object bean = (Object) lisData.get(i);
				    
				 for(int j = 0; j < mapFieldBean.length; j++){
					 
					        PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.REDFONT));
					 
					        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
					        
					        if(  j==mapFieldBean.length-3){
					        	
					        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.REDFONT));
						        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						        cell.setPaddingBottom(5);
						        cell.setBorderWidth(0.1f);
						        cell.setBorderColor(WebColors.getRGBColor("#787878"));
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
						        table.addCell(cell);
						        continue;
					        }
					        if(j==mapFieldBean.length-1 || j==mapFieldBean.length-2){
					        	Double elm=Double.valueOf(String.valueOf(obj));
					        	
					        	cell = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(elm) ,GeneratePdf.REDFONT));
						        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						        cell.setPaddingBottom(5);
						        cell.setBorderWidth(0.1f);
						        cell.setBorderColor(WebColors.getRGBColor("#787878"));
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
						        
					        }else{
					        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.REDFONT));
						        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						        cell.setPaddingBottom(5);
						        cell.setBorderWidth(0.1f);
						        cell.setBorderColor(WebColors.getRGBColor("#787878"));
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        }
					        table.addCell(cell);
					    }   
				   
	           }
			  
			   /********************************************************************************************************/
	           int sizelist=lisData.size();
	           int toolha=sizelist*20;
	           int resul=380 - toolha;
	           float toul_contenu_tab=Float.valueOf(String.valueOf(resul));
	           /********************************************************************************************************/
	            
	           for(int j = 0; j < mapFieldBean.length; j++){
			            PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.REDFONT));
				        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				        cell.setPaddingBottom(5);
				        cell.setBorderWidth(0.1f);
				        cell.setFixedHeight(toul_contenu_tab);
				        cell.setBorderColor(WebColors.getRGBColor("#787878"));
				        cell.setBackgroundColor(BaseColor.WHITE);
				        cell.setBorder(cell.LEFT+cell.RIGHT);
			            table.addCell(cell);
			    }   
			  document.add(table);
		}
	  
	  
	  public void doWrite_Header_ContentTable(Document document,int poucentage, String[][] mapFieldBean) throws Exception {
		
		  
		  PdfPTable table = new PdfPTable(mapFieldBean.length);
	    int PaddingBottom=5;
        int[] columnWidths = new int[mapFieldBean.length] ;
        for(int i = 0; i < mapFieldBean.length; i++){
      	columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
		}
        table.setWidthPercentage(poucentage);
        table.setWidths(columnWidths);
      for(int i = 0; i < mapFieldBean.length; i++){
    	     
    	     String titrehead="";
    	     
    	     if(mapFieldBean[i][0].indexOf(".")>0){
				 final String[] lesColunmooo = mapFieldBean[i][0].split("\\.");
				 String getel=lesColunmooo[lesColunmooo.length-1];
				 
				 titrehead=(String) getObjectValueModel(getel);
				 if(getel.startsWith("code_barre")   ) titrehead=(String) getObjectValueModel("_code_barre");
				 
    	     }else{
    	    	 titrehead=(String) getObjectValueModel(mapFieldBean[i][0]);
    	    	 
    	     } 
    	     
    	     if(mapFieldBean[i][0].startsWith("modeBean"))  titrehead=(String) getObjectValueModel("_mode");
    	     
    	      
    	     
    	    
    	     
    	    PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":titrehead  ,GeneratePdf.SMALLBOLD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setPaddingBottom(PaddingBottom);
		    cell.setBackgroundColor(GeneratePdf.colorHeader);
		    cell.setBorderWidth(1f);
		    table.addCell(cell);
		}
      document.add(table);
}
	
	
	
	public void doWriteEntete(Document document, CommandeclientBean   denBean) throws Exception {
	PdfPTable tableTopHeader = new PdfPTable(100);
	tableTopHeader.setWidthPercentage(96);
	 
	    
	PdfPCell cell = new PdfPCell(new Phrase("Facture Pro Forma N°",GeneratePdf.FONT_12_bold));
    cell.setColspan(24);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(" : "+denBean.getCmd_id(),GeneratePdf.FONT_12_normal));
    cell.setColspan(23);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase(" Client ",GeneratePdf.FONT_12_bold));
    cell.setColspan(13);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.LEFT);
    tableTopHeader.addCell(cell);
    
    String getClient=denBean.getCmd_libelle().equals("")?denBean.getClient().getClt_lib():denBean.getCmd_libelle();
    cell = new PdfPCell(new Phrase(" : "+getClient ,GeneratePdf.FONT_12_normal));
    cell.setColspan(40);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    
     
    
    
    cell = new PdfPCell(new Phrase("Date",GeneratePdf.FONT_12_bold));
    cell.setColspan(24);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(denBean.getCmd_date()) ,GeneratePdf.FONT_12_normal));
    cell.setColspan(23);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase(" Adresse ",GeneratePdf.FONT_12_bold));
    cell.setColspan(13);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_adr() ,GeneratePdf.FONT_12_normal));
    cell.setColspan(40);
    cell.setFixedHeight(40f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase("   ",GeneratePdf.FONT_12_bold));
    cell.setColspan(47);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
     
    
    
    
    cell = new PdfPCell(new Phrase(" Matricule fiscal :",GeneratePdf.FONT_12_bold));
    cell.setColspan(18);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT+cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( "   "+denBean.getClient().getClt_obs() ,GeneratePdf.FONT_12_normal));
    cell.setColspan(35);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT +cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    
    
    
    
    
    
    cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_normal));
    cell.setColspan(100);
    cell.setFixedHeight(30f);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    document.add(tableTopHeader);
		
	}
	
	
	  public void doWrite_Tva_Total_Piece(List   lisData,Document document) throws Exception {
		
		try {
			 
           
           PdfPTable table_des_tva = new PdfPTable(100);
           table_des_tva.setWidthPercentage(96);
           
           
           
           
           /******************************************* Entete  tableau des tva *************************************/
           PdfPCell cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
           cell.setColspan(100);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Taux",GeneratePdf.FONT_12_bold));
           cell.setColspan(10);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Base",GeneratePdf.FONT_12_bold));
           cell.setColspan(21);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Montant",GeneratePdf.FONT_12_bold));
           cell.setColspan(23);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
           cell.setColspan(49);
           cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.NO_BORDER);
           table_des_tva.addCell(cell);
           /********************************************************************************************************/
           
           
           
           
           HashMap  mapTvaImpression= (HashMap) getObjectValueModel("mapTvaImpression");
           
           Set sss_mapTvaImpression=mapTvaImpression.keySet();
           
           for (Iterator iter = sss_mapTvaImpression.iterator(); iter.hasNext();) {
			
        	   String key = (String ) iter.next();
			
			   String elmme=  (String) mapTvaImpression.get(key);
			   String[] ligne=   elmme.split("£");
			
			   cell = new PdfPCell(new Phrase(key,GeneratePdf.FONT_12_bold));
	           cell.setColspan(10);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[0],GeneratePdf.FONT_12_bold));
	           cell.setColspan(21);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[1],GeneratePdf.FONT_12_bold));
	           cell.setColspan(23);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
	           cell.setColspan(49);
	           cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	           cell.setBorder(cell.NO_BORDER);
	           table_des_tva.addCell(cell);
		   } 
           
           JSONArray  list_total  =  (JSONArray) getObjectValueModel("list_total");
           
           
           PdfPTable table_total = new PdfPTable(100);
           table_total.setWidthPercentage(96);
           
           /****************************************************************************************************************/
           
            
           int  init=20;
           int size=mapTvaImpression.size();
           int space=size*20;
           space=space+init;
           space=space*-1;
           
           /****************************************************************************************************************/
           
           
           table_des_tva.setSpacingAfter(Float.valueOf(String.valueOf(space)));
           for (int i = 0; i < list_total.length(); ++i) {
        	    JSONObject rec = list_total.getJSONObject(i);
        	    String titre = rec.getString("value1"); 
        	    String value = rec.getString("value2");
        	    
        	    cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_bold));
                cell.setColspan(55);
                cell.setFixedHeight(20f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.NO_BORDER);
                table_total.addCell(cell);
                
                
                cell = new PdfPCell(new Phrase(titre,GeneratePdf.FONT_12_bold));
                cell.setColspan(18);
                cell.setFixedHeight(20f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
                table_total.addCell(cell);
                
                cell = new PdfPCell(new Phrase(value ,GeneratePdf.FONT_12_normal));
                cell.setColspan(27);
                cell.setFixedHeight(20f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
                table_total.addCell(cell);
        	}
           document.add(table_des_tva);
           document.add(table_total);
          
		} catch (Exception e) {
		}
	
	}


	public ModelAndView doAddData(CommandeclientBean detailBean)
			throws Throwable {
		try {
			setObjectValueModel(FORM_BEAN, detailBean);
			serviceCommandeclient.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			setObjectValueModel(LIST_EDITABLE_CMD_CLT, new  ArrayList<DetCmdCltBean>());
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW_EDIT);
	}
	
	
	
public    ModelAndView doSelectRow () throws Exception {

		
		
	 
		try {

			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			removeObjectModel(FORM_BEAN);
			CommandeclientBean rowBeans = (CommandeclientBean ) getIndexFromDataGrid_v1(LIST_DATA);
			List list_detail = serviceCommandeclient.doFetchDetailleDatafromServer(rowBeans);
			
			HashMap map_reception = ProcessUtil.getHashMap_code_bean(list_detail,"pk.fkcode_barre.pk.code_barre");
			setObjectValueModel(FORM_BEAN, rowBeans);

			setObjectValueModel(LIST_EDITABLE_CMD_CLT, list_detail);
			setObjectValueModel(LIST_EDITABLE_CMD_CLT_ORIGIN, ProcessUtil.cloneList(list_detail));

			if (bs.getFct_id().equals(Fn_Consulter))
				return getViewConsult_Pdf_ex(FORM_VIEW);

			if (bs.getFct_id().equals(Fn_Confirmer))
				return getViewConfirm(FORM_VIEW);

			if (bs.getFct_id().equals(Fn_Modifier))
				return getViewUpdate(FORM_VIEW_EDIT);

			if (bs.getFct_id().equals(Fn_Supprimer))
				return getViewDelete(FORM_VIEW);

		} catch (Exception e) {
			displayException(e);

		}
		return getViewFilterAjax(FILTER_VIEW);
		
} 

public ModelAndView doConfirmData(CommandeclientBean beanUpBean) {
	try {
	 
		serviceCommandeclient.doExcuterRowData (beanUpBean);
		remove_row_from_list(LIST_DATA);
		throwNewException("ins01");
	} catch (Exception e) {
		displayException(e);
	}
	return getViewList_Ajax(FILTER_VIEW);
}

	public ModelAndView doUpdateData(CommandeclientBean beanUpBean) {
		try {
			serviceCommandeclient.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(CommandeclientBean beanDelBean) {
		try {
			serviceCommandeclient.doDeleteRowData(beanDelBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
