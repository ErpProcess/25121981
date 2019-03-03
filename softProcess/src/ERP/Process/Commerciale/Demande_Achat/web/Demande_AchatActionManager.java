package ERP.Process.Commerciale.Demande_Achat.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Article.service.ArticleService;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Demande_Achat.model.Demande_achatBean;
import ERP.Process.Commerciale.Demande_Achat.model.Det_demande_AchatBean;
import ERP.Process.Commerciale.Demande_Achat.service.Demande_AchatService;
import ERP.Process.Commerciale.Demande_Achat.template.Demande_AchatTemplate;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.dao.UniteDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel.WriteExcel;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class Demande_AchatActionManager extends Demande_AchatTemplate {
	
	 
	private static final long serialVersionUID = 3092340142271646112L;

	@Autowired Demande_AchatService   serviceDemande_Achat;
	 
	@Autowired ArticleService         serviceArticle;
	
	@Autowired FournisseurService     serviceFournisseur;
	
	@Autowired Code_barreService      serviceCode_barre;
	
	
	
	
	private UniteDAO daoUnite;
	@Autowired
	public void setDaoUnite(UniteDAO daoUnite) {
		this.daoUnite = daoUnite;
	}
	
	
	public   ModelAndView doInitServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		 
			
		
		 
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			doLoadingLibelleOtherSModule("5");
			doLoadingLibelleOtherSModule("62");
			doLoadingLibelleOtherSModule("75");
			doLoadingLibelleOtherSModule(Demande_AchatTemplate.ID_SOUS_MODULE);
			setObjectValueModel("list-"+getObjectValueModel(ENTITES), getObjectValueModel("list-"+Demande_AchatTemplate.ID_SOUS_MODULE));
			setObjectValueModel(LIST_PDF_EXCEL,getObjectValueModel("list-"+Demande_AchatTemplate.ID_SOUS_MODULE)) ;
			setObjectValueModel(LIST_DES_UNITES  , daoUnite.doFindListUnite(UniteBean.class.newInstance()));
			
			
		    Code_barreBean searchBeanx=new Code_barreBean();
	        searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
		    searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
			
			 
			//searchBean.setEtablissment(searchBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id());
			List fetchDatafromSe         = serviceCode_barre.doFetchDatafromServerNew(searchBeanx);
			setObjectValueModel("list_article_dem_achat"       , fetchDatafromSe);
			setObjectValueModel("list_article_dem_achatOrigine", fetchDatafromSe);
			setObjectValueModel("list_article_dem_achatGrid"   , fetchDatafromSe);
			
			
			HashMap mapCodBarre=(HashMap) ProcessUtil.getHashMap_code_bean(fetchDatafromSe, "pk.code_barre");
			setObjectValueModel("mapCodBarre", mapCodBarre);
			
			List list_fournisseur_d_achat= serviceFournisseur.dofetchDatafromServer(new FournisseurBean());
			setObjectValueModel("list_fournisseur_d_achat", list_fournisseur_d_achat);
			HashMap map_fournisseur=(HashMap) ProcessUtil.getHashMap_code_bean(list_fournisseur_d_achat, "frs_id");
			setObjectValueModel(MAP_FOURNISSEUR, map_fournisseur);
			
			
	 
			List  <Det_demande_AchatBean>listGridEditable_demande_achat=  new  ArrayList<Det_demande_AchatBean>();
 
			setObjectValueModel("listGridEditable_demande_achat", listGridEditable_demande_achat);
			
			bs.setSousmod_libelle_title(bs.getSousmod_libelle());

			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
				
				Demande_achatBean  demBean = new Demande_achatBean();
				demBean.setDem_date( ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()) );
				
				setObjectValueModel(FORM_BEAN, demBean);
				
				return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
			} else if(bs.getFct_id().equals(Fn_Contremander)){   
				
				
				bs.setSousmod_libelle_title((String)getObjectValueModel("cmd_frs"));
				setObjectValueModel(SEARCH_BEAN, new Demande_achatBean());
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
				
			} else {
				
				setObjectValueModel(SEARCH_BEAN, new Demande_achatBean());
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doActualiser_List( ) throws Exception{
		  
		
		
		try {
			List listOfmyData=(List) getObjectValueModel("listGridEditable_demande_achat");
			List list_article_dem_achatOrigine =(List) getObjectValueModel("list_article_dem_achatOrigine");
			
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_demande_AchatBean  dAchatBean= (Det_demande_AchatBean) listOfmyData.get(i);
				
				String ar_id=(String) getValueObjSimpleFromList
				(dAchatBean.getPk_det_dem_achat().getFkCode_barre().getPk().getCode_barre(), list_article_dem_achatOrigine, "pk.code_barre", "pk.ar_bean.pk_article.ar_id");
				dAchatBean.getPk_det_dem_achat().getFkCode_barre().getPk().getAr_bean().getPk_article().setAr_id(ar_id);
				
				//System.out.println("codebArr:>"+dAchatBean.getPk_det_dem_achat().getFkCode_barre().getPk().getCode_barre()+":::::ar_id>"+ar_id);
			 
				
			}
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk_det_dem_achat.fkCode_barre.pk.code_barre");
			
		
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_dem_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_dem_achatOrigine.get(i);
				
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_dem_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
					setObjectValueModel("list_article_dem_achatGrid",list_article_dem_achatGrid);
					setObjectValueModel("list_article_dem_achat",list_article_dem_achat);
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
	public ModelAndView doFetchArticleSuivantTarif(    Demande_achatBean searchBean   )throws Exception { 
		
		 PrintWriter out = getResponse().getWriter();
	     getResponse().setContentType(HTML_CONTENT_TYPE);
	    try {
	    	chargerInformationForTarif(searchBean);
	    } catch (Exception e) {
	    	getResponse().setStatus(500);
			out.println(e.getMessage());
		    out.close();
		}
	    return null;
	}
	
	
	private void chargerInformationForTarif(Demande_achatBean searchBean ) throws Exception {
		 /*   try {
		     
		     HashMap  mapFournisseur = (HashMap)getObjectValueModel(MAP_FOURNISSEUR);
		     FournisseurBean  ben=(FournisseurBean) mapFournisseur.get(searchBean.getFrsBean().getFrs_id());
		    
		     TarificationPrtvArticleBean tBean = new TarificationPrtvArticleBean();
		     tBean.setDate_prim_trf(searchBean.getDem_date());
		     //tBean.setDepot(searchBean.getDepot());
		    
		  List    list_Tarification_vente  =   daoTarification.doFind_DateMax_TarificationVente(tBean);
			 HashMap mapTarification_vente    =   serviceTarification.doTraiTerListTarif_vente(list_Tarification_vente);
			 HashMap mapTarific_vente         =   getHashMap_Key_List_FromList(list_Tarification_vente, "groupe.type_trf_id");
			
			 
			 setObjectValueModel(MAP_TARIFICATION,  mapTarification_vente);
			  setObjectValueModel(MAP_LIST_TARIFICA, mapTarific_vente);
		    
		    
			HashMap  mapTypeTarif = (HashMap)getObjectValueModel(MAP_LIST_TARIFICA);
		    List listTarif=(List)mapTypeTarif.get(String.valueOf(ben.getTyp_trfBean().getType_trf_id()));
		    List listTarif_public=(List)mapTypeTarif.get(GROUPE_TARIF_VENTE_PUBLIC); 
		    
		    if(listTarif==null)           listTarif= new ArrayList();
		    if(listTarif_public==null)    listTarif_public= new ArrayList();
		    
		    HashMap  mapDeclient   = new HashMap();
		    List  fetchDatafromSe  = new ArrayList();
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
				
		    
		 
		    
		    
		     setObjectValueModel(LIST_ARTICLE_VENTE        , fetchDatafromSe);
			 setObjectValueModel(LIST_ARTICLE_VENTE_ORIGINE, ProcessUtil.cloneList(fetchDatafromSe));
			 setObjectValueModel(LIST_ARTICLE_VENTE_GRID   , ProcessUtil.cloneList(fetchDatafromSe));
			 HashMap mapCodBarre=(HashMap) getHashMap_code_bean(fetchDatafromSe, "pk.code_barre"); 
			 setObjectValueModel("mapCodBarre", mapCodBarre);
		} catch (Exception e) {
			throw e;
		}
		*/
	}
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doCheked_unCheked( ) throws Exception{
		
		try {
			List listOfmyData=(List) getObjectValueModel("listGridEditable_demande_achat");
			 
			
			String to_check=getRequest().getParameter("statucheked")==null?"":getRequest().getParameter("statucheked");
	 
			for (int i = 0; i < listOfmyData.size(); i++) {
				Det_demande_AchatBean newBean =(Det_demande_AchatBean) listOfmyData.get(i);
				newBean.setTo_check(to_check);
			}
			getResponse().setContentType(HTML_CONTENT_TYPE);
			} catch (JsonIOException e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;
	 
	}
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doAdd_row_EditableDataTableAjax( ) throws Exception{
		  
		
		
		try {
		 
			String nameVector  =getRequest().getParameter("nameList");
			List listOfmyData  =(List) getObjectValueModel(nameVector);
			String code_barre  =getRequest().getParameter("ar_id")==null?"":getRequest().getParameter("ar_id");
			String designation =getRequest().getParameter("designation")==null?"":getRequest().getParameter("designation");
			String quantite    =getRequest().getParameter("quantite")==null?"":getRequest().getParameter("quantite");
			//String unite       =getRequest().getParameter("unite")==null?"":getRequest().getParameter("unite");
			String observation =getRequest().getParameter("observation")==null?"":getRequest().getParameter("observation");
			 
			 
			List list_article_dem_achatOrigine =(List) getObjectValueModel("list_article_dem_achatOrigine");
			
			HashMap  mapdA=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk_det_dem_achat.fkCode_barre.pk.code_barre");
			if(mapdA.get(code_barre)!=null)
				throw new Exception("Existe déjà");
			
			Det_demande_AchatBean newBean= new Det_demande_AchatBean();
			HashMap  mapCodBarre  =(HashMap) getObjectValueModel("mapCodBarre"  );
			Code_barreBean  beancodeBarr=(Code_barreBean) mapCodBarre.get(code_barre);
			 
			newBean.getPk_det_dem_achat().setFkCode_barre(beancodeBarr);//.getPk().getAr_bean().getPk_article().setAr_id(beancodeBarr.getPk().getAr_bean().get);
		 
			newBean.setQuantite(new Double(quantite));
			newBean.setUnitBean(beancodeBarr.getPk().getAr_bean().getUnitBean());
			newBean.setObservation(observation);
			listOfmyData.add(newBean);
			
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk_det_dem_achat.fkCode_barre.pk.code_barre");
			
			
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_dem_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_dem_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_dem_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
					setObjectValueModel("list_article_dem_achatGrid",list_article_dem_achatGrid);
					setObjectValueModel("list_article_dem_achat",list_article_dem_achat);
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
	
	
	@SuppressWarnings("unchecked")
	public static ModelAndView doDelete_row_EditableDataTableAjax( ) throws Exception{
		  
		
		
		try {
		 
			 
			List listOfmyData=(List) getObjectValueModel("listGridEditable_demande_achat");
			int sizefinal=listOfmyData.size();
			boolean  del=false;
			for (int i = 0; i < sizefinal; i++) {
				
				Det_demande_AchatBean newBean= (Det_demande_AchatBean) listOfmyData.get(i);
				if(newBean.getTo_check()!=null  &&  newBean.getTo_check().equals("checked")){
					listOfmyData.remove(i);
					sizefinal--;
					i--;
					del=true;
				}
				
			}
			if(!del) throw new Exception ("Cochez au moin une ligne");
			HashMap  mapd=ProcessUtil.getHashMap_code_bean(listOfmyData, "pk_det_dem_achat.fkCode_barre.pk.code_barre");
			List list_article_dem_achatOrigine =(List) getObjectValueModel("list_article_dem_achatOrigine");
			List list_article_dem_achatGrid = new ArrayList();
			List list_article_dem_achat = new ArrayList();
			for (int i = 0; i < list_article_dem_achatOrigine.size(); i++) {
				Code_barreBean    bean  =(Code_barreBean) list_article_dem_achatOrigine.get(i);
				if(mapd.get(bean.getPk().getCode_barre())==null){
					list_article_dem_achatGrid.add(bean);
					list_article_dem_achat.add(bean);
				}
				
			}
			setObjectValueModel("list_article_dem_achatGrid",list_article_dem_achatGrid);
			setObjectValueModel("list_article_dem_achat",list_article_dem_achat);
			 
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

 
	public ModelAndView doFetchData(Demande_achatBean searchBean) throws Throwable {
		
		try {
			  BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			
			    String  isMaycondtion ="   AND  bean.dem_achat_id   not in ('')   ";
			    
			     
			    
			    if ( bs.getFct_id().equals("4") ) 
			    	   isMaycondtion +="   AND  bean.modeBean.fct_id  in ('1','5','3')    ";
			    
			    
			    if ( bs.getFct_id().equals("10") || bs.getFct_id().equals("3") ) 
			    	   isMaycondtion +="   AND  bean.modeBean.fct_id  in ('1','5','3','"+Fn_Contremander+"')    ";
			    
			    
			    
			    if (bs.getFct_id().equals(Fn_Contremander)  ) 
			    	   isMaycondtion +="   AND  bean.modeBean.fct_id  in ('"+Fn_Valider+"' )    ";
			    
			    
			    	
			    searchBean.setCondition_etat("   "+isMaycondtion);	
			 
				
			List  listDataSrv = serviceDemande_Achat.dofetchDatafromServer(searchBean);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (Exception e) {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	 
	public     ModelAndView doSelectRow() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Demande_achatBean rowBean = (Demande_achatBean) getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			
			List listGridEditable_demande_achat=serviceDemande_Achat.dofetch_detaille_demande_achat(rowBean);
			List listGridEditable_demande_achatToSupprimer=serviceDemande_Achat.dofetch_detaille_demande_achat(rowBean);
			
			 
			 
			
			setObjectValueModel("listGridEditable_demande_achat", listGridEditable_demande_achat);
			setObjectValueModel("listGridEditable_demande_achatToSupprimer", listGridEditable_demande_achatToSupprimer);
			
			//rowBean.setAuto_Load_Edit_Grid("true");
			setObjectValueModel(FORM_BEAN, rowBean);
			 
			if (bs.getFct_id().equals("2")    ){
				return getViewConsult(FORM_VIEW_CONSULT);
			}
			
			if (bs.getFct_id().equals("3"))
				return getViewUpdate(FORM_VIEW);
			
			if (bs.getFct_id().equals("10"))
				return getViewValider(FORM_VIEW_CONSULT);
			 
			
			if (bs.getFct_id().equals("4"))
				return getViewDelete(FORM_VIEW_CONSULT);
			
			
		    if(bs.getFct_id().equals(Fn_Contremander))   
		    	
		    	return getViewAnnuler(FORM_VIEW_CONSULT);
		    
			
		} catch (Exception e) {
			displayException(e);
			
		}
		return getViewFilterAjax(FILTER_VIEW);

	}
	
	
	public     ModelAndView doLoad_detCommande_cons()throws Exception   { 

		try {
			
			Demande_achatBean rowBean = (Demande_achatBean) getObjectValueModel(FORM_BEAN);
			
			List listGridEditable_demande_achat=serviceDemande_Achat.dofetch_detaille_demande_achat(rowBean);
			List listGridEditable_demande_achatToSupprimer=new ArrayList(ProcessUtil.cloneList(listGridEditable_demande_achat));
			 
			setObjectValueModel("listGridEditable_demande_achat", listGridEditable_demande_achat);
			setObjectValueModel("listGridEditable_demande_achatToSupprimer", listGridEditable_demande_achatToSupprimer);
			
			 getResponse().setContentType(HTML_CONTENT_TYPE);
			 getResponse().getWriter().print("TRUE");
			} catch (Exception e) {
				getResponse().setStatus(500);
				getResponse().setContentType(HTML_CONTENT_TYPE);
				PrintWriter out = getResponse().getWriter();
				out.println(e.getMessage());
			    out.close();
			}
			return null;

	}
	
	
		public static  ModelAndView doPrintPDF_detaille_demande_achat() throws Exception   {
			 
			List   lisData=  (List) getObjectValueModel("listGridEditable_demande_achat") ;
			Demande_achatBean  denBean= (Demande_achatBean) getObjectValueModel(FORM_BEAN) ;
			File file = new File(getRequest().getRealPath("/")+"/temp/"+"listGridEditable_demande_achat"+getRequest().getSession().getId()+".pdf");
		    FileOutputStream fs = new FileOutputStream(file);
		    GeneratePdf  genpdf= new GeneratePdf();
			try {
			 
				Document document=GeneratePdf.doGenerateDocumentFormat();
		        PdfPTable table = new PdfPTable(Mapfield_det_achat.length);
		        //String title= (String) getObjectValueModel("list-"+getObjectValueModel(ENTITES));
		        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
		        genpdf.doWriteHeaderDocument_PDF(document,fs,bSession);
		        doWriteEntete_demande_achat(document,denBean);
		        genpdf.doWriteTitle_Table(document,BON_COMMANDE_FRS);
		        genpdf.doWrite_Header_Table(table,Mapfield_det_achat);
		        genpdf.doWrite_Data_Table(lisData,table,Mapfield_det_achat);
		        document.add(table);
		        document.close();
				getResponse().setContentType("text");
				getResponse().setHeader("Cache-Control", "no-cache");
				getResponse().setStatus(200);
				getResponse().getWriter().write("listGridEditable_demande_achat"+getRequest().getSession().getId()+".pdf");
			} catch (Exception e) {
				displayException((Exception) e);
			} 
			
		 
		return null;
	
	     }
		
 
	 
	
	private static void doWriteEntete_demande_achat(Document document,
				Demande_achatBean denBean) throws Exception {
		PdfPTable tabletitle = new PdfPTable(100);
		
		
	    PdfPCell cell = new PdfPCell(new Phrase("Date demande",GeneratePdf.FONT_12_bold));
        cell.setColspan(20);
        cell.setFixedHeight(20f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(cell.NO_BORDER);
        tabletitle.addCell(cell);
        
        cell = new PdfPCell(new Phrase(((String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(denBean.getDem_date()))) ,GeneratePdf.FONT_12_normal));
        cell.setColspan(80);
        cell.setFixedHeight(20f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(cell.NO_BORDER);
        tabletitle.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase("Fournisseur",GeneratePdf.FONT_12_bold));
        cell.setColspan(20);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(cell.NO_BORDER);
        tabletitle.addCell(cell);
        
        cell = new PdfPCell(new Phrase(denBean.getFrsBean().getFrsref() ,GeneratePdf.FONT_12_normal));
        cell.setColspan(80);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(cell.NO_BORDER);
        tabletitle.addCell(cell);
        
        
        document.add(tabletitle);
			
		}




	@SuppressWarnings("unchecked")
	public    ModelAndView doExportXls_detaille_demande_achat() {
		try {

			    List   lisData=  (List) getObjectValueModel("listGridEditable_demande_achat") ;
			    WorkbookSettings wbSettings = new WorkbookSettings();
			    WriteExcel  dbexp= new WriteExcel();
			    wbSettings.setLocale(new Locale("en", "EN"));
			    BeanSession bSe = (BeanSession) getObjectValueModel(BEAN_SESSION);
			    String title= (String) getObjectValueModel(" list - des articles commandés  ");
			    if(title==null || title.equals(""))  title="Listes des "+bSe.getSousmod_libelle();
			    getResponse().setHeader("Content-Disposition", "attachment; filename="+title+".xls");
			    WritableWorkbook workbook = Workbook.createWorkbook(getResponse().getOutputStream(), wbSettings);
			    workbook.createSheet("Report", 0);
			    WritableSheet excelSheet = workbook.getSheet(0);
			    dbexp.createTitleMap(excelSheet,Mapfield_det_achat);
			    dbexp.creatheaderMap(excelSheet,Mapfield_det_achat);
			    dbexp.createContentWithList(excelSheet,lisData,Mapfield_det_achat);
			    workbook.write();
			    workbook.close();
			
			 
		} catch (Exception e) {
			displayException(e);
		}
		return null;

	}

	public ModelAndView doAddData(Demande_achatBean detailBean)
			throws Throwable {
		try {
			
			if(((List)(getObjectValueModel("listGridEditable_demande_achat"))).size()==0)
				throwNewException("Veuillez Saisie detaille demande");
			
			serviceDemande_Achat.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			setObjectValueModel("listGridEditable_demande_achat",new ArrayList<Det_demande_AchatBean>());
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(Demande_achatBean beanUpBean) {
		try {
			serviceDemande_Achat.doUpdateRowData(beanUpBean);
			 update_row_from_list(LIST_DATA, beanUpBean);
            removeObjectModel("listGridEditable_demande_achat");
            removeObjectModel("listGridEditable_demande_achatToSupprimer" );
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	
	public ModelAndView doValiderDemande_Achat(Demande_achatBean beanUpBean) {
		try {
			serviceDemande_Achat.doValiderDemande_Achat(beanUpBean);
		    remove_row_from_list(LIST_DATA);
            removeObjectModel("listGridEditable_demande_achat");
            removeObjectModel("listGridEditable_demande_achatToSupprimer" );
            BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
            if(bs.getFct_id().equals(GenericActionBean.Fn_Contremander)){
            	throwNewException("Commande retournée ");
	 			}else{
	 				throwNewException("val01");
	 			}
            
			throwNewException("val01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	
	 
	
	

	public ModelAndView doDeleteData(Demande_achatBean beanDelBean) {
		try {
			serviceDemande_Achat.doDeleteRowData(beanDelBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
