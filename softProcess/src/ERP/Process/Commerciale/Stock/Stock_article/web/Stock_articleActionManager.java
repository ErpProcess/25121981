package ERP.Process.Commerciale.Stock.Stock_article.web;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.service.DepotStockageService;
 
import ERP.Process.Commerciale.Stock.DocumentLot.service.DocumentLotService;
import ERP.Process.Commerciale.Stock.Stock_article.model.IncidentStock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.service.Stock_articleService;
import ERP.Process.Commerciale.Stock.Stock_article.template.Stock_articleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
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

public class Stock_articleActionManager extends Stock_articleTemplate {

 
	private Stock_articleService serviceStock_article;
	@Autowired
	public void setServiceStock_article(
			Stock_articleService serviceStock_article) {
		this.serviceStock_article = serviceStock_article;
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

		
		try {
			removeObjectModel(FORM_BEAN );
			removeObjectModel(SEARCH_BEAN );
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			doLoadingLibelleOtherSModule(ID_SOUS_MODULE);
			setObjectValueModel(LIST_DEPOT_STOCK , serviceDepotStockage.doFetchDatafromServer(DepotStockageBean.class.newInstance()));
		 
			Code_barreBean searchBeanx=new Code_barreBean();
	        searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
		    searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
			
			setObjectValueModel(LIST_ARTICLE_CODE_BARRE , serviceCode_barre.doFetchDatafromServerNew(searchBeanx));
			
			
			if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
				return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
			} else {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(Stock_articleBean searchBean)
			throws Throwable {
		try {
			List listDataSrv = serviceStock_article.doFetchDatafromServer(searchBean);
			 for (int i = 0; i < listDataSrv.size(); i++) {
				 Stock_articleBean sBean =(Stock_articleBean) listDataSrv.get(i);
				 String pattern ="0.000";
				 if(sBean.getDevise()!=null  &&   sBean.getDevise().getDev_id()!=null){
					if( sBean.getDevise().getDev_id().intValue()==191  ||  sBean.getDevise().getDev_id().intValue()==192   ){
						pattern ="0.00";
					}
				 }
				 
				 Double mnt_ttc_recept = ProcessNumber.addition(ProcessFormatNbr.FormatDouble_ParameterChiffre(sBean.getMnt_tva_recept(),pattern), 
						 ProcessFormatNbr.FormatDouble_ParameterChiffre(sBean.getMnt_ht_recept(),pattern));
				 sBean.setMnt_ttc_recept(ProcessFormatNbr.FormatDouble_ParameterChiffre(mnt_ttc_recept,pattern) );
					 
				 Double mnt_ttc_vendu = ProcessNumber.addition(ProcessFormatNbr.FormatDouble_ParameterChiffre(sBean.getMnt_tva_vente(),pattern),
						 ProcessFormatNbr.FormatDouble_ParameterChiffre(sBean.getMnt_ht_vente(),pattern));
				 sBean.setMnt_ttc_vendu(ProcessFormatNbr.FormatDouble_ParameterChiffre(mnt_ttc_vendu,pattern));
				 
//				 if( StringUtils.isEmpty(sBean.getStock_article_id())  ) continue;
//				 List listDataSrvS =serviceStock_article.doFetchDataIncident(sBean);
//				 if(listDataSrvS==null || listDataSrvS.size()==0) continue;
//				 IncidentStock_articleBean  sBeanS =(IncidentStock_articleBean) listDataSrvS.get(0);
//				 sBean.setQuantite_retour(sBeanS.getQuantite_incident());
			}
			
			
			 
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (JsonIOException e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	public ModelAndView doAddData(Stock_articleBean detailBean)
			throws Throwable {
		try {
			serviceStock_article.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}

	public ModelAndView doUpdateData(Stock_articleBean beanUpBean) {
		try {
			serviceStock_article.doUpdateRowData(beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(Stock_articleBean beanDelBean) {
		try {
			serviceStock_article.doDeleteRowData(beanDelBean);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
	
	public    ModelAndView doPrintPDFStock() {
		 
		List   lisData                         =    (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G)) ;
	 
		GeneratePdf  genpdf= new GeneratePdf();
		
		 String [][] mapFieldBean  = new String[][]{
			{ "pk.date_stock", "15" },
			{ "pk.fkCode_barre.designation_libelle", "40" },  
			{ "quantite_recept", "15" },{ "mnt_ttc_recept", "30" },
			
			{ "quantite_vendu", "15" },{ "mnt_ttc_vendu", "30" },
			{ "cout_unitaire_moyen_pondere", "30" } ,
			{ "solde_stock", "15" } 
			};
		 JSONObject propertieField = 
				       new JSONObject("{"+
	                   "\"cout_unitaire_moyen_pondere\": {\"title\": \"CUMP\",\"width\": 15 ,\"type\":\"montant3\", \"size\": 10 , \"align\": 2 },"+
	                   "\"mnt_ttc_recept\": {\"title\": \"fact_clt_id\",\"width\": 20 ,\"type\":\"montant3\", \"size\": 10 , \"align\": 2 },"+
	                   "\"mnt_ttc_vendu\": {\"title\": \"clt_lib\",\"width\": 30 ,\"type\":\"montant3\", \"size\": 10 , \"align\": 2 },"+
	                   "\"quantite_recept\": {\"title\": \"quantite_recept\" ,\"type\":\"integer\", \"size\": 10 , \"align\": 1 },"+
	                   "\"quantite_vendu\": {\"title\": \"quantite_vendu\" ,\"type\":\"integer\", \"size\": 10 , \"align\": 1 },"+
	                   "\"solde_stock\": {\"title\": \"solde_stock\" ,\"type\":\"integer\", \"size\": 10 , \"align\": 1 },"+
	                     "}");
		try { 
			    File file = new File(getRequest().getRealPath("/")+"/temp/"+(String)getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
		        FileOutputStream fs = new FileOutputStream(file);
		        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
		        Document document=GeneratePdf.doGenerateDocumentFormat();
		        PdfPTable table = new PdfPTable(mapFieldBean.length);
		        String    title =(String)getObjectValueModel("list-"+Stock_articleTemplate.ID_SOUS_MODULE) ; 
		        setObjectValueModel("propertieField",propertieField);
		        genpdf.doWriteHeaderDocument_PDF(document,fs,mapFieldBean,bSession);
			    genpdf.doWriteTitle_Table(document,title);
			    genpdf.doWrite_Header_Table(table,mapFieldBean);
			    genpdf.doWrite_Data_Table(lisData,table,mapFieldBean);
		        document.add(table);
		        document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			removeObjectModel("propertieField");
			getResponse().getWriter().write((String) getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
		} catch (Throwable e) {
			displayException((Exception) e);
		} 
	return null;

}
	
	    void doWrite_Data_Table(List lisData, PdfPTable table, String[][] mapFieldBean) throws Exception   {
			  for(int i=0; i < lisData.size(); i++ ){
				  Stock_articleBean bean =(Stock_articleBean) lisData.get(i);
				  String pattern ="0.000";
				  if(bean.getDevise()!=null  &&   bean.getDevise().getDev_id()!=null){
					if( bean.getDevise().getDev_id().intValue()==191  ||  bean.getDevise().getDev_id().intValue()==192   ){
						pattern ="0.00";
					}
				  }
				    
				 for(int j = 0; j < mapFieldBean.length; j++){
					 
					        PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.REDFONT));
					       
					        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
					        String myObject=String.valueOf(obj);
					        if( (j==4 || j==6)  &&  obj instanceof Double ){
					           myObject=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(((Double)obj), pattern) ;
					           cell = new PdfPCell(new Phrase(myObject,GeneratePdf.REDFONT)); 
					           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					         } else{
					        	 cell = new PdfPCell(new Phrase(myObject,GeneratePdf.REDFONT)); 
					        	 cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
					         }
					       
					        cell.setPaddingBottom(5);
					        cell.setBorderWidth(0.1f);
					        cell.setBorderColor(WebColors.getRGBColor("#787878"));
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        table.addCell(cell);
						   
					    }   
				   
	       }
			
		}
	
	
	
	
}
