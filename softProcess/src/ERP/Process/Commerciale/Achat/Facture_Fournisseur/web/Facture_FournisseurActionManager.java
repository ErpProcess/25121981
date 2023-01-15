package ERP.Process.Commerciale.Achat.Facture_Fournisseur.web;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Det_Fact_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Detail_mvt_achat_factureBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Mvt_achat_factureBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.service.Facture_FournisseurService;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.template.Facture_FournisseurTemplate;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.service.Reception_achatService;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
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
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class Facture_FournisseurActionManager extends Facture_FournisseurTemplate {

 
	private static final long serialVersionUID = 2401073907911649351L;


	private Facture_FournisseurService serviceFacture_Fournisseur;

	@Autowired
	public void setServiceFacture_Fournisseur(
			Facture_FournisseurService serviceFacture_Fournisseur) {
		this.serviceFacture_Fournisseur = serviceFacture_Fournisseur;
	}
	
	@Autowired
	FournisseurService   serviceFournisseur;
	
	
	private TVAService   serviceTVA;
	@Autowired
	public void setServiceTva(TVAService serviceTVA) {
		this.serviceTVA = serviceTVA;
	}
	
	
	private Reception_achatService   serviceReception_achat;

	@Autowired
	public void setServiceFacture_Fournisseur(
			Reception_achatService serviceReception_achat) {
		this.serviceReception_achat = serviceReception_achat;
	}

 
	 public ModelAndView uploadFile() throws Exception {
        String  chargement= " Chargment du fichier effectué avec succès  ";
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) getRequest();
			MultipartFile multipartFile = multiRequest.getFile("file");
		 
			if(multipartFile==null){
				chargement= " échec de chargment  ";
				setObjectValueModel("MultipartFile", null);
			}else{
				
			}
			
			String mime_content_type = multipartFile.getContentType();
			String filename = multipartFile.getOriginalFilename();
			byte[] bytes = multipartFile.getBytes();
			FileFactureFournisseur insertBean = new FileFactureFournisseur();
			
			String doc_id_interne =getRequest().getParameter("doc_id_interne");
			insertBean.setMime_content_type(mime_content_type);
			insertBean.setDoc_id_interne(doc_id_interne);
			insertBean.setFile_byte(bytes);
			insertBean.setFile_name(filename);
			insertBean.setMultipartFile(multipartFile);
			setObjectValueModel("MultipartFile", insertBean);
			//serviceFacture_Fournisseur.doCreateRowDatafileFacturefrs(insertBean);
			/*InputStream input = multipartFile.getInputStream();
			File source = new File("D://" + filename);
			multipartFile.transferTo(source); */
		} catch (Exception e) {
			chargement= " échec de chargment  ";
			e.printStackTrace();
		}
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().getWriter().write(chargement);

		return null;
	}
	 
	 
	 public ModelAndView dislayFile() throws Exception {
	        Facture_FournisseurBean form_bean =(Facture_FournisseurBean)getObjectValueModel(FORM_BEAN);
	        getResponse().setContentType(form_bean.getMyFile().getMime_content_type());
	        BufferedOutputStream fos1 = new BufferedOutputStream(
	        getResponse().getOutputStream());
	        fos1.write(form_bean.getMyFile().getFile_byte());
	        fos1.flush();
	        fos1.close();
			return null;
		}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(Facture_FournisseurBean searchBean)
			throws Throwable {
		try {
			
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if (bs.getFct_id().equals(Fn_Envoyer) ||  bs.getFct_id().equals(Fn_Annuler) ){
				searchBean.setCondition_select_mode("  AND  bean.modeBean.fct_id    in ('"+Fn_Generer+"')   ");
			} 
			
			List listDataSrv = serviceFacture_Fournisseur.doFetchDatafromServer(searchBean);
			
			Double totGridfrs = new Double(0);
			for (int i = 0; i < listDataSrv.size(); i++) {
				Facture_FournisseurBean  reBean	=(Facture_FournisseurBean) listDataSrv.get(i);
				totGridfrs=ProcessNumber.addition(totGridfrs,  ProcessFormatNbr.FormatDouble_Troischiffre(reBean.getTotal_facture()) );
			}
			setObjectValueModel("totGridfrs", totGridfrs);
			
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

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData_To_Fact(Reception_achatBean searchBean) throws Throwable {
		try {
		 
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if(bs.getFct_id().equals(Fn_Generer) ){
				searchBean.setCondition_etat_achat("  AND  bean.modeBean.fct_id  in ('"+Fn_Conserver+"','"+Fn_Confirmer+"')   ");
			 }
			List listDataSrv = serviceReception_achat.doFetchDatafromServer(searchBean);
			
			
			for (int i = 0; i < listDataSrv.size(); i++) {
				Reception_achatBean pBean	=(Reception_achatBean) listDataSrv.get(i);
				pBean.setTo_check("checked");
			}
			setObjectValueModel("list_Brute_Achat", ProcessUtil.cloneList(listDataSrv) );
			setObjectValueModel(SEARCH_BEAN, searchBean);
			setObjectValueModel(LIST_DATA_ACHAT_FACT, listDataSrv);
			//AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (JsonIOException e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	

	public    ModelAndView doRetourToList_achat() {
		
		setObjectValueModel(MAP_FIELD_BEAN, Reception_achatTemplate.MapfieldBean);
		setObjectValueModel(LIST_VIEW_G, LIST_VIEW_ACHAT_FACT);
		setObjectValueModel(NAME_LIST_G ,LIST_DATA_ACHAT_FACT); 
		setObjectValueModel(NAME_GRID_G, NAME_GRID_ACHAT_FACT);
		List  listDataTrie=(List) getObjectValueModel((String)getObjectValueModel(NAME_LIST_G)  );
		setObjectValueModel(DATA_LIST_AJAX,listDataTrie);
		setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_TO_FACTURE");
	 
		
		return getViewFilterAjax_Achat(FILTER_VIEW_RECEP_ACHAT);
	}
	
	
	@SuppressWarnings("unchecked")
	public    ModelAndView doGenererInterfaceFacture(Reception_achatBean    searchBean ) {

		try {
			 
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		     List <Reception_achatBean   >  list_des_achat =   (List)  getObjectValueModel(LIST_DATA_ACHAT_FACT);
		     Double avance_montant_achat=new Double(0);
		     
		     Facture_FournisseurBean newBean = new Facture_FournisseurBean();
		     
		     List<Det_Fact_FournisseurBean> liste_detaille_facture = new ArrayList<Det_Fact_FournisseurBean>();
		     
		     HashMap  map_article= new HashMap();
		     
		     HashMap  map_detail_codBarre= new HashMap();
		     
		     String les_reception="";
		     for (Reception_achatBean  bean:list_des_achat) {
		    	 if(  StringUtils.isEmpty(bean.getTo_check()) ) continue;
		    	 
		    	 Double mntAvance=ProcessFormatNbr.FormatDouble_Troischiffre(bean.getAvance_montant_achat());
		    	 
		    	 les_reception=les_reception+"'"+bean.getAchat_id()+"',";
		    	 
		    	 avance_montant_achat=ProcessNumber.addition(avance_montant_achat, mntAvance);
		    	 List <Det_reception_achatBean>list_detaille=serviceReception_achat.doFetchDetailAchat(bean) ;
		    	 for (Det_reception_achatBean beand:list_detaille) {
		    		 String keyString  =    beand.getPk().getFkCode_barre().getPk().getCode_barre()+"£"+
		    		                        beand.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"£"+
		    		                        beand.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"£"+
		    		                        beand.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"£";
		    		List list= (List) map_article.get(keyString);
		    		if(list==null)list= new ArrayList();
		    		list.add(beand);
		    		map_article.put(keyString, list);
		    		if(map_detail_codBarre.get(keyString)==null){
		    			map_detail_codBarre.put(keyString, beand.getPk().getFkCode_barre());
		    		}
		    		
		    	 }
		     }
		     if(les_reception.length()>0)  les_reception=les_reception.substring(0, les_reception.length()-1);
		    	 
		     setObjectValueModel("les_reception"  ,les_reception)	 ;
		     
		     Set mapio_map_article=map_article.keySet();
		     List listMvt_Vente= new ArrayList();
		     for (Iterator itera = mapio_map_article.iterator(); itera.hasNext();) {
				 String article = (String) itera.next();
				
				
				 List listMvt=(List) map_article.get(article);
				 Mvt_achat_factureBean  mvt_article= new Mvt_achat_factureBean();
				 
				 Code_barreBean  fkcode_barre=(Code_barreBean) map_detail_codBarre.get(article);
				 mvt_article.setFkcode_barre(fkcode_barre);
				 
				 
				 Double  quantiteGen	= new Double(0);
				 Double  mnt_ht		    = new Double(0);
				 Double  mnt_tva	    = new Double(0);
				 List list_detail_mvt_vente= new ArrayList();
				 TVABean  tvaBean	= new TVABean();
				 for (int i = 0; i < listMvt.size(); i++) {
					 Det_reception_achatBean detAchat = (Det_reception_achatBean) listMvt.get(i);
					 tvaBean=detAchat.getTarif().getTvaBean();
					 
					 Detail_mvt_achat_factureBean detMvt = new Detail_mvt_achat_factureBean();
					 detMvt.getPk().getMvt_achat().setMvt_achat_id("");
					 detMvt.getPk().setDocument_com_id(detAchat.getPk().getRecepBean().getAchat_id()) ;
					 detMvt.getPk().getNat_mvt().setNature_mvt_id("ac");
					 detMvt.setTarif(detAchat.getTarif());
					 
					 detMvt.getPk().setFkcode_barre(detAchat.getPk().getFkCode_barre());
					 detMvt.setQuantite(detAchat.getQuantite());
					 detMvt.setMontant_ht_achat(detAchat.getMontant_ht_achat());
					 detMvt.setMontant_tva_achat(detAchat.getMontant_tva_achat());
					 detMvt.setObservation("");
					 quantiteGen=ProcessNumber.addition(quantiteGen, detAchat.getQuantite());
					 mnt_ht=ProcessNumber.addition(mnt_ht, detAchat.getMontant_ht_achat());
					 mnt_tva=ProcessNumber.addition(mnt_tva, detAchat.getMontant_tva_achat());
					 list_detail_mvt_vente.add(detMvt);
				}
				 
				 
				mvt_article.setTvaBean(tvaBean);
				mvt_article.setQuantite(quantiteGen);
				mvt_article.setMontant_ht_achat(mnt_ht);
				mvt_article.setMontant_tva_achat(mnt_tva);
				mvt_article.setList_detail_mvt_vente(list_detail_mvt_vente);
				listMvt_Vente.add(mvt_article);
				
			 }
		     
		     for (int i = 0; i < listMvt_Vente.size(); i++) {
		    	 
		    	 Mvt_achat_factureBean  mvt_article= (Mvt_achat_factureBean) listMvt_Vente.get(i);
		    	 Det_Fact_FournisseurBean  beanDetaille= new Det_Fact_FournisseurBean();
		    	 beanDetaille.getPk().setMvt_achat(mvt_article);
		    	 beanDetaille.getPk().setFkCode_barre(mvt_article.getFkcode_barre());
		    	 beanDetaille.setQuantite(mvt_article.getQuantite());
		    	 Double mntht=ProcessFormatNbr.FormatDouble_Troischiffre(mvt_article.getMontant_ht_achat());
		    	 Double mnttva=ProcessFormatNbr.FormatDouble_Troischiffre(mvt_article.getMontant_tva_achat());
		    	 Double tarif_unit_achat=ProcessNumber.DIVISION(mntht, mvt_article.getQuantite());
		    	 beanDetaille.setMontant_ht_achat(mntht);
		    	 beanDetaille.setMontant_tva_achat(mnttva);
		    	 beanDetaille.setTarif_unit_achat(tarif_unit_achat);
		    	 beanDetaille.setTvaBean(mvt_article.getTvaBean());
		    	 beanDetaille.setObservation("");
		    	 liste_detaille_facture.add(beanDetaille);
			 }
		     newBean.setAvance_montant_achat(ProcessFormatNbr.FormatDouble_Troischiffre(avance_montant_achat));
		     newBean.setFact_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
		     newBean.setFrs(searchBean.getFrsBean());
		     setObjectValueModel(LIST_DATA_DET_FACT,liste_detaille_facture);
		     setObjectValueModel(LIST_DATA_DET_MVT_ACHAT,listMvt_Vente);
		     setObjectValueModel(FORM_BEAN, newBean);
		     
			} catch (Exception e) {
				displayException(e);
				return getViewFilterAjax_Achat(FILTER_VIEW_RECEP_ACHAT);
			}
			
			return getViewPreparerAdd(FORM_VIEW);

	}
	
	public    ModelAndView doSelectRowBean() {

		try {
			 
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Facture_FournisseurBean rowBean = (Facture_FournisseurBean) getIndexFromDataGrid_v1(LIST_DATA);
			List List_detaille=serviceFacture_Fournisseur.doFetchDetailfromServer(rowBean);
			setObjectValueModel(LIST_DATA_DET_FACT, List_detaille);
			setObjectValueModel(FORM_BEAN, rowBean);
			
			if (bs.getFct_id().equals(Fn_Consulter))
				return getViewConsult_Pdf_ex(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Envoyer))
				return getViewEnoyer(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Annuler))
				return getViewDelete(FORM_VIEW);
			
			
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax(FILTER_VIEW);
		}
		return getViewFilterAjax(FILTER_VIEW);

	}

	public ModelAndView doInitServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
		try {

			 
			 List list_des_tva= serviceTVA.doFetchDatafromServer(TVABean.class.newInstance());
			 setObjectValueModel(LIST_DES_TVA, list_des_tva);
			 List listfournisseur= serviceFournisseur.dofetchDatafromServer(FournisseurBean.class.newInstance());
			 setObjectValueModel(LIST_FOURNISSEUR_FACTURE_FRS , listfournisseur);
			 doLoadingLibelleOtherSModule("118");
			if (bs.getFct_id().equals(Fn_Generer)) {
				 
				setObjectValueModel("LIST_VIEW", LIST_VIEW_ACHAT_FACT);
				setObjectValueModel(NAME_LIST_G, LIST_DATA_ACHAT_FACT);
				setObjectValueModel("nameGrid", NAME_GRID_ACHAT_FACT);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL,"i$_ACT_FETCH_TO_FACTURE");
				setObjectValueModel(FORM_BEAN, new Reception_achatBean());
				setObjectValueModel(SEARCH_BEAN, new Reception_achatBean());
				return getViewFilterAjax_Achat(FILTER_VIEW_RECEP_ACHAT);
			}

			if (bs.getFct_id().equals(Fn_Nouveau) || bs.getFct_id().equals(Fn_Creer)
					 ) {
				return getViewAdd(FORM_VIEW);
			} else {
				return getViewFilterAjax(FILTER_VIEW);

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}

	public ModelAndView doAddData(Facture_FournisseurBean detailBean)
			throws Throwable {
		boolean insert=false;
		try {
			serviceFacture_Fournisseur.doCreateRowData(detailBean);
			setObjectValueModel(FORM_BEAN, new Facture_FournisseurBean() );
            throwNewException("ins01");
          } catch (Exception e) {
        	  if(e.getMessage().equals("ins01"))  insert=true;
            displayException(e);
          }
          
          if(insert)
          return getViewAdd_after_save(FORM_VIEW);
          else
           return getViewPreparerAdd(FORM_VIEW);  
          
	}
	
	public    ModelAndView doPrintPDF_detaille() throws Exception   {
		 
		List   lisData=  (List) getObjectValueModel(LIST_DATA_DET_FACT) ;
		Facture_FournisseurBean   denBean= (Facture_FournisseurBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
	    FileOutputStream fs = new FileOutputStream(file);
	    GeneratePdf  genpdf= new GeneratePdf();
		try {
			
		
			Document document=GeneratePdf.doGenerateDocumentFormat();
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	        genpdf.doWriteHeaderDocument_PDF(document,fs,bSession);
	        
	        doWriteEntete(document,denBean); 
	        doWrite_Header_ContentTable(document,96,MapfieldBean_detaille);
	        doWrite_Data_Table (lisData,document,96,MapfieldBean_detaille);
	        doWrite_Tva_Total_Piece(lisData,document);  ;  
	        
	          
	        
	        
	        Facture_FournisseurBean  reBean= (Facture_FournisseurBean) getObjectValueModel(BEAN_TOTAL_FACTURE_FRS);
	        String affich_mont =ProcessFormatNbr.convertNumberToLetterEURO( reBean.getTotal_facture() );
	        
	        PdfPTable tabletitle = new PdfPTable(100);
		    PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_normal));
	        cell.setColspan(100);
	        cell.setFixedHeight(10f);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Arrêtée la présente facture à la somme de :",GeneratePdf.FONT_12_normal));
	        cell.setColspan(100);
	    
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase(affich_mont,GeneratePdf.FONT_12_normal));
	        cell.setColspan(100);
	        
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        
	        
	        document.add(tabletitle);
	        
	       
	        document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write(LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
		} catch (Exception e) {
			displayException((Exception) e);
		} 
		
	 
	return null;

     }
	 
	
	 
	
	 public   void doWriteEntete(Document document, Facture_FournisseurBean denBean) throws Exception {
		PdfPTable tableTopHeader = new PdfPTable(100);
		tableTopHeader.setWidthPercentage(96);
		 
		    
		PdfPCell cell = new PdfPCell(new Phrase(" Facture N° ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(13);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(" : "+denBean.getFact_frs_id(),GeneratePdf.FONT_12_normal));
	    cell.setColspan(27);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    cell = new PdfPCell(new Phrase(" Fournisseur ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(18);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.TOP+cell.LEFT);
	    tableTopHeader.addCell(cell);
	    
	    
	    cell = new PdfPCell(new Phrase(" : "+denBean.getFrs().getFrsref() ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(42);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.TOP+cell.RIGHT);
	    tableTopHeader.addCell(cell);
	    
	     
	    
	    
	    cell = new PdfPCell(new Phrase(" Date ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(13);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(denBean.getFact_date()) ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(27);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    
	    cell = new PdfPCell(new Phrase(" Adresse ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(18);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.LEFT);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase( " : "+denBean.getFrs().getFrsadr() ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(42);
	    cell.setFixedHeight(40f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.RIGHT);
	    tableTopHeader.addCell(cell);
	    
	    
	    
	    cell = new PdfPCell(new Phrase("   ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(12);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(  "   " ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(28);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    
	    cell = new PdfPCell(new Phrase(" Matricule fiscal ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(18);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.LEFT+cell.BOTTOM);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase( " : "+denBean.getFrs().getFrsobs(),GeneratePdf.FONT_12_normal));
	    cell.setColspan(42);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.RIGHT +cell.BOTTOM);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(100);
	    cell.setFixedHeight(10f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    document.add(tableTopHeader);
			
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
				        if(j==mapFieldBean.length-3  ){
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
         int resul=350 - toolha;
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
  
  
	 
	
	
	  public void doWrite_Tva_Total_Piece(List   lisData,Document document) throws Exception {
		
		try {
			 
           
           PdfPTable table_des_tva = new PdfPTable(100);
           table_des_tva.setWidthPercentage(96);
           
           
           
           
           /******************************************* Entete  tableau des tva *************************************/
           PdfPCell cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
           cell.setColspan(100);
           cell.setFixedHeight(10f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Taux",GeneratePdf.FONT_12_bold));
           cell.setColspan(10);
          // cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Base",GeneratePdf.FONT_12_bold));
           cell.setColspan(21);
           //cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Montant",GeneratePdf.FONT_12_bold));
           cell.setColspan(23);
           //cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
           cell.setColspan(49);
           //cell.setFixedHeight(20f);
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
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[0],GeneratePdf.FONT_12_bold));
	           cell.setColspan(21);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[1],GeneratePdf.FONT_12_bold));
	           cell.setColspan(23);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
	           cell.setColspan(49);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	           cell.setBorder(cell.NO_BORDER);
	           table_des_tva.addCell(cell);
		   } 
           
           JSONArray  list_total  =  (JSONArray) getObjectValueModel("list_total");
           
           
           PdfPTable table_total = new PdfPTable(100);
           table_total.setWidthPercentage(96);
           
           /****************************************************************************************************************/
           
            
           int  init=9;
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
                cell.setFixedHeight(17f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.NO_BORDER);
                table_total.addCell(cell);
                
                
                cell = new PdfPCell(new Phrase(titre,GeneratePdf.FONT_12_bold));
                cell.setColspan(18);
                cell.setFixedHeight(17f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
                table_total.addCell(cell);
                
                cell = new PdfPCell(new Phrase(value ,GeneratePdf.FONT_12_normal));
                cell.setColspan(27);
                cell.setFixedHeight(17f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
                table_total.addCell(cell);
        	}
           document.add(table_des_tva);
           document.add(table_total);
          
		} catch (Exception e) {
		}
	
	}

	  
	  @SuppressWarnings("unchecked")
		public ModelAndView doCalculerTotalGrid( Facture_FournisseurBean detailBean ) throws Exception {
			
			try {
				Double totGridfrs= (Double) getObjectValueModel("totGridfrs");
				getResponse().getWriter().print(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(totGridfrs));
				//removeObjectModel("totGridfrs");
			} catch (Exception e) {
				getResponse().setContentType(HTML_CONTENT_TYPE);
				getResponse().getWriter().print(e.getMessage());
			}
			return null;
		}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doCalculer_Total(Facture_FournisseurBean detadilBean ) throws Exception {
		
		try {
			 
			Facture_FournisseurBean   rowBean = (Facture_FournisseurBean) getObjectValueModel(FORM_BEAN);
			List <Det_Fact_FournisseurBean >List_detaille= new ArrayList<Det_Fact_FournisseurBean>();
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			if(bs.getFct_id().equals(Fn_Generer)   ){
				 List_detaille=(List<Det_Fact_FournisseurBean>) getObjectValueModel(LIST_DATA_DET_FACT);
			}else{
				List_detaille=serviceFacture_Fournisseur.doFetchDetailfromServer(rowBean);
			}
			
			
			setObjectValueModel(LIST_DATA_DET_FACT, List_detaille);
			//setObjectValueModel(LIST_DATA_DET_FACT_CLONE, ProcessUtil.cloneList(List_detaille) );  
			Double avance=	ProcessFormatNbr.FormatDouble_Troischiffre(rowBean.getAvance_montant_achat());
			Double remise=ProcessFormatNbr.FormatDouble_Troischiffre(detadilBean.getFacture_remise());
			Double timbre=ProcessFormatNbr.FormatDouble_Troischiffre(bs.getSociete().getMontant_timbre_fiscal());
			
		
			 
			
			
			double tot_ht=0;
			double tot_tva=0;
			Double tot_qte=new Double(0);
			
			HashMap   mapTva = new HashMap();
			for (int i = 0; i < List_detaille.size(); i++) {
				Det_Fact_FournisseurBean  bean=List_detaille.get(i);
				tot_ht=ProcessNumber.addition(tot_ht, bean.getMontant_ht_achat());
				tot_tva=ProcessNumber.addition(tot_tva, bean.getMontant_tva_achat());
				tot_qte=ProcessNumber.addition(tot_qte, bean.getQuantite());
				
				List  listTva = (List) mapTva.get(bean.getTvaBean().getTva_libelle());
				if(listTva==null)listTva= new ArrayList();
				listTva.add(bean);
				mapTva.put(bean.getTvaBean().getTva_libelle(), listTva);
				
			}
			Facture_FournisseurBean    reBean = new Facture_FournisseurBean();
			 
			
			 reBean.setFacture_remise(remise);
			 reBean.setAvance_montant_achat(avance)  ; 
			 
			     
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
				 
				 HashMap   mapTvabidan = new HashMap();
				 Double    total_leTva  = new Double(0);
				 List <TVABean> list_des_tva=  (List) getObjectValueModel(LIST_DES_TVA);
				 Double le_Ht_brute  = new Double(0);
				 for (int j = 0; j < list_des_tva.size(); j++) {
					 TVABean beanTva=list_des_tva.get(j);
					 
					 if(mapTva.get(beanTva.getTva_libelle())!=null){
						 List listTva  =(List) mapTva.get(beanTva.getTva_libelle());
						 String  tva   = beanTva.getTva_libelle();
						  	
						 Double le_Ht  = new Double(0);
					     Double leTva  = new Double(0);
					    
					     
					     
					 	 for (int i = 0; i < listTva.size(); i++) {
					 		Det_Fact_FournisseurBean  bean=(Det_Fact_FournisseurBean) listTva.get(i);
							le_Ht=ProcessNumber.addition(le_Ht, bean.getMontant_ht_achat());
							le_Ht_brute=ProcessNumber.addition(le_Ht_brute, bean.getMontant_ht_achat());
							leTva=ProcessNumber.addition(leTva, bean.getMontant_tva_achat());
						 }
					 	 
					 	 
					 	
					  
					 	 
					 	 
					 	if(mapTvabidan.get(reBean.getFacture_remise())==null &&  reBean.getFacture_remise().doubleValue()> 0  ){
					 		
					 		if(reBean.getFacture_remise().doubleValue() < le_Ht.doubleValue() ){
					 			le_Ht=ProcessNumber.SOUSTRACTION(le_Ht, reBean.getFacture_remise().doubleValue());
						 		String prec=tva.replace("%", "");
						 		leTva=ProcessNumber.Pourcentage(le_Ht, new Double(prec));
						 		mapTvabidan.put(reBean.getFacture_remise(),"true");
						      }
					 		
					 	}
					 	total_leTva=  ProcessNumber.addition(total_leTva, leTva);
					 	  
						 
						 JSONObject  element = new JSONObject();
						 element.put("td1","0");
						 element.put("value1",String.valueOf(tva));
					     
					     String base=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(le_Ht);
					     
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
			 element.put("td1","2");
			 element.put("value1","Brut.H.T");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(le_Ht_brute));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Remise");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(remise));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Net.H.T");
			 element.put("td2","3");
			 double ht_apres_remise=  ProcessNumber.SOUSTRACTION(le_Ht_brute, remise)  ;
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(ht_apres_remise));
			 list_total.put(element);
			 reBean.setTotal_ht_fact(ProcessFormatNbr.FormatDouble_Troischiffre(ht_apres_remise));
			 
			
			 
			 
			 
			 reBean.setTotal_tva_fact(ProcessFormatNbr.FormatDouble_Troischiffre(total_leTva));
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Total TVA");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_leTva));
			 list_total.put(element);
			 
			 
			 
			  
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Timbre");
			 element.put("td2","3");
			 
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(timbre));
			 list_total.put(element);
			  
			 
			 double total_mnt_gen=ProcessNumber.addition(ht_apres_remise, total_leTva);
			 total_mnt_gen=ProcessNumber.addition(total_mnt_gen,timbre);
			 reBean.setTotal_facture(ProcessFormatNbr.FormatDouble_Troischiffre(total_mnt_gen));
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Total TTC");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(total_mnt_gen));
			 list_total.put(element);
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Avance");
			 element.put("td2","3");
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(avance));
			 list_total.put(element);
			 
			 
			 element = new JSONObject();
			 element.put("td1","2");
			 element.put("value1","Net à payer");
			 element.put("td2","3");
			 double net_a_payer=ProcessNumber.SOUSTRACTION(total_mnt_gen, avance);
			 element.put("value2",ProcessFormatNbr.FormatDouble_To_String_Troischiffre(net_a_payer));
			 list_total.put(element);
			 reBean.setNet_a_payer(ProcessFormatNbr.FormatDouble_Troischiffre(net_a_payer));
			 
			 
			 
			 json.put("list_total", list_total);
			 setObjectValueModel("list_total", list_total);
			 setObjectValueModel(BEAN_TOTAL_FACTURE_FRS, reBean);
			 getResponse().setContentType(JSON_CONTENT_TYPE);      
			 getResponse().getWriter().write(json.toString());
			
			 
			
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	
	public ModelAndView doUpdateData(Facture_FournisseurBean beanUpBean) {
		try {
			 serviceFacture_Fournisseur.doUpdateRowData(beanUpBean);
			 removeObjectModel(FORM_BEAN);
			 removeObjectModel(SEARCH_BEAN);
			 remove_row_from_list(LIST_DATA); 
			 throwNewException("fact_envoyer");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(Facture_FournisseurBean beanDelBean) {
		try {
			serviceFacture_Fournisseur.doDeleteRowData(beanDelBean);
			removeObjectModel(FORM_BEAN);
			removeObjectModel(SEARCH_BEAN);
			remove_row_from_list(LIST_DATA); 
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
