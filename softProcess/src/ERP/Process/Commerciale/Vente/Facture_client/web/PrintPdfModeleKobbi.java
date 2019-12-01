package ERP.Process.Commerciale.Vente.Facture_client.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.EditionVente.model.EditionVenteBean;
import ERP.Process.Commerciale.Vente.EditionVente.model.EtatDepenseProduit;
import ERP.Process.Commerciale.Vente.EditionVente.model.EtatVenteProduit;
import ERP.Process.Commerciale.Vente.EditionVente.template.EditionVenteTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.service.EntiteAdminService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.TableHeaderNormale;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.TableHeaderPaysage;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PrintPdfModeleKobbi  extends GenericWeb {

	public PrintPdfModeleKobbi() {
		super();
	}
	
	public void printfacture( ) throws Exception{
		
		try {
		List   lisData=  (List) getObjectValueModel(Facture_clientTemplate.LIST_DATA_DET_FACT) ;
		Facture_clientBean    denBean= (Facture_clientBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+Facture_clientTemplate.LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
		BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	    FileOutputStream fs = new FileOutputStream(file);
	    Document document=GeneratePdf.doGenerateDocumentFormat();
		String varLangue="kb";
		EntiteAdminBean  beAdminBean=new EntiteAdminBean();
		beAdminBean.getPk_entite_admin().setLang_id(varLangue);
		List   <EntiteAdminBean>  list_EntiteAdmin=serviceEntiteAdmin.getListDataServer(beAdminBean);
		for (int i = 0; i < list_EntiteAdmin.size(); i++) {
			EntiteAdminBean bsdx = (EntiteAdminBean) list_EntiteAdmin.get(i); 
			   int df=bsdx.getPk_entite_admin().getEnt_id().indexOf("/");
				setObjectValueModel("kb_"+bsdx.getPk_entite_admin().getEnt_id().substring(df+1),bsdx.getEnt_libelle());
		}
	  
	    doWriteHeaderDocument_PDF_NOT_PASY(document,73,fs,Facture_clientTemplate.MapfieldBean_ModelKobbi,bSession);
	    doWriteEnteteKobbi(document,73,denBean); 
	    doWrite_Header_ContentTable(document,73,Facture_clientTemplate.MapfieldBean_ModelKobbi);
	    doWrite_Data_Table (denBean,lisData,document,73,Facture_clientTemplate.MapfieldBean_ModelKobbi);
	    doWrite_mode_pay_banc(denBean,lisData,73,document);  ;  
	    /*****************************************************************************************************/
	    document.close();
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		getResponse().getWriter().write(Facture_clientTemplate.LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
		
		} catch (Exception e) {
			throw e;
		} 
	}
	
	
	public void printExportManchafacture( ) throws Exception{
		
		try {
		List   lisData=  (List) getObjectValueModel(Facture_clientTemplate.LIST_DATA_DET_FACT) ;
		Facture_clientBean    denBean= (Facture_clientBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+"certificat_origine_"+denBean.getClient().getClt_id()+""+getRequest().getSession().getId()+".pdf");
		BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	    FileOutputStream fs = new FileOutputStream(file);
		Document document = new Document(PageSize.A4, 5, 5, 5, 25);
		String varLangue="kb";
		EntiteAdminBean  beAdminBean=new EntiteAdminBean();
		beAdminBean.getPk_entite_admin().setLang_id(varLangue);
		List   <EntiteAdminBean>  list_EntiteAdmin=serviceEntiteAdmin.getListDataServer(beAdminBean);
		for (int i = 0; i < list_EntiteAdmin.size(); i++) {
			EntiteAdminBean bsdx = (EntiteAdminBean) list_EntiteAdmin.get(i); 
			   int df=bsdx.getPk_entite_admin().getEnt_id().indexOf("/");
				setObjectValueModel("kb_"+bsdx.getPk_entite_admin().getEnt_id().substring(df+1),bsdx.getEnt_libelle());
		}
	  
		doWriteHeaderDocumentMancha(document,73,fs,Facture_clientTemplate.MapfieldBean_ModelKobbi,bSession);
	    doWriteEnteteKobbiMancha(lisData,document,73,denBean); 
	
	    
	    
	    //doWrite_Data_Table (denBean,lisData,document,73,Facture_clientTemplate.MapfieldBean_ModelKobbi);
	    //doWrite_mode_pay_banc(denBean,lisData,73,document);  ;  
	    /*****************************************************************************************************/
	    document.close();
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		getResponse().getWriter().write("certificat_origine_"+denBean.getClient().getClt_id()+""+getRequest().getSession().getId()+".pdf");
		
		} catch (Exception e) {
			throw e;
		} 
	}
	
	private String htmlEncode(final String string) {
		  final StringBuffer stringBuffer = new StringBuffer();
		  for (int i = 0; i < string.length(); i++) {
		    final Character character = string.charAt(i);
		    if (CharUtils.isAscii(character)) {
		      // Encode common HTML equivalent characters
		      stringBuffer.append(
		          StringEscapeUtils.escapeHtml(character.toString()));
		    } else {
		      // Why isn't this done in escapeHtml4()?
		      stringBuffer.append(
		          String.format("&#x%x;",
		              Character.codePointAt(string, i)));
		    }
		  }
		  return stringBuffer.toString();
		}
	
//	public static void main (String[] args) throws DocumentException, IOException{
//		
//  		 
//		Document document = new Document(PageSize.A4, 5, 5, 5, 25);
//		
//		String nssss="&#1578;&#1608;&#1606;&#1587;";
//		String qsqs=" شركة القبي فيش";
//		org.jsoup.nodes.Document  sss= Jsoup.parse(nssss);
//		String text = sss.body().text();
//	 
//		BaseFont bf = BaseFont.createFont(
//			    "c://windows/fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//			Font font = new Font(bf, 8);
//			PdfPTable table = new PdfPTable(1);
//			PdfPCell  cell = new PdfPCell(new Phrase("\u062D\u064A\u0633\u0648", font));
//			cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
//			table.addCell(cell);
//			document.add(table);
//			
//     
//		}
	
	 private   void doWriteEnteteKobbiMancha(List   lisData,Document document,int poucentage, Facture_clientBean denBean) throws Exception {
			PdfPTable tableTopHeader = new PdfPTable(100);
			tableTopHeader.setWidthPercentage(poucentage);
		    BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		    BaseFont bf = BaseFont.createFont("C://Fonts/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font arialuniArab = new Font(bf, 10);
		    
			 
            SocieteBean ste=bs.getSociete();
		    
		    JSONObject societeObj = new JSONObject(ste.getData_societe_langue());
		    JSONObject societeData = (JSONObject) societeObj.get("ar");
		 
		    String societe=societeData.getString("soc_lib");
		    String adresse=societeData.getString("adresse");
		    String pays=societeData.getString("pays");
			
			
			
			PdfPCell cell     = new PdfPCell(new Phrase(" "+Jsoup.parse(societe).body().text(),arialuniArab));
		    cell.setColspan(60);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    cell     = new PdfPCell(new Phrase(" "+Jsoup.parse(societe).body().text(),arialuniArab));
		    cell.setColspan(40);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
	 
		    
		  
		    
		    
		    
		    cell = new PdfPCell(new Phrase(" "+Jsoup.parse(adresse).body().text(),arialuniArab));
		    cell.setColspan(100);
		    cell.setFixedHeight(20f);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    
		    
		    
		    PdfPTable tableTopHeader2 = new PdfPTable(100);
		    tableTopHeader2.setWidthPercentage(73);
		    cell = new PdfPCell(new Phrase("  ",GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(51);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		   
		    
		    Font FONT_12_bold = new Font(Font.getFamily("TIMES_ROMAN"), 11, Font.BOLD|  Font.UNDERLINE);
		    cell = new PdfPCell(new Phrase(" ",FONT_12_bold));
		    cell.setColspan(49);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("  ",GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(51);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		     
		    JSONObject jsonclient = new JSONObject(denBean.getClient().getData_client_langue());
		    JSONObject j_arab = (JSONObject) jsonclient.get("ar");
		    String clt_lib=j_arab.getString("ligne0");
		    cell = new PdfPCell(new Phrase(Jsoup.parse(clt_lib).body().text(),arialuniArab));
		    cell.setColspan(100);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    cell = new PdfPCell(new Phrase(Jsoup.parse(pays).body().text(),arialuniArab));
		    cell.setColspan(40);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    String adresse1=j_arab.getString("ligne1");
		    cell = new PdfPCell(new Phrase(Jsoup.parse(adresse1).body().text(),arialuniArab));
		    cell.setColspan(60);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		  
		    
		 
		    String adresse2=j_arab.getString("ligne2");
		    cell = new PdfPCell(new Phrase(Jsoup.parse(adresse2).body().text(),arialuniArab));
		    cell.setColspan(100);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(100);
		    cell.setFixedHeight(35f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    String ligne4=j_arab.getString("ligne4");
		    cell = new PdfPCell(new Phrase(Jsoup.parse(ligne4).body().text(),arialuniArab));
		    cell.setColspan(40);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorder(cell.NO_BORDER);
		    cell.setFixedHeight(80f);
		    tableTopHeader2.addCell(cell);
		    
		    
		    String ligne3=j_arab.getString("ligne3");
		    cell = new PdfPCell(new Phrase(Jsoup.parse(ligne3).body().text(),arialuniArab));
		    cell.setColspan(60);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    cell.setFixedHeight(80f);
		    tableTopHeader2.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(100);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    tableTopHeader2.addCell(cell);
		    
		    Double qteBox= new Double(0);
		    for(int i=0; i < lisData.size(); i++ ){
			    Det_Fact_ClientBean bean = (Det_Fact_ClientBean) lisData.get(i);
			    qteBox=ProcessNumber.addition(qteBox,  bean.getNbrBoxes());
	         }
		     
		    String QteBox="";
		    if(qteBox!=null && qteBox>0) {
		    	QteBox=ProcessFormatNbr.convertDoubleToIntString(qteBox);
		    }
		    
		    String ligne1=j_arab.getString("ligne1");
		    cell = new PdfPCell(new Phrase(Jsoup.parse("عدد الطرود").body().text()+" : "+QteBox+" "+Jsoup.parse("طرد").body().text(),arialuniArab));
		    cell.setColspan(100);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    cell.setFixedHeight(30f);
		    tableTopHeader2.addCell(cell);
		    
		    
		    cell = new PdfPCell(new Phrase(Jsoup.parse("تحتوي على").body().text()+" :",arialuniArab));
		    cell.setColspan(100);
		    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    cell.setFixedHeight(30f);
		    tableTopHeader2.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(100);
		    cell.setFixedHeight(2f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    for(int i=0; i < lisData.size(); i++ ){
				    Det_Fact_ClientBean bean = (Det_Fact_ClientBean) lisData.get(i);

				    JSONObject articleObj = new JSONObject(bean.getPk().getFkcode_barre().getPk().getAr_bean().getData_article_langue());
				    JSONObject articleData = (JSONObject) articleObj.get("ar");
				 
				    String produit=articleData.getString("produit");
				    String unite=articleData.getString("unite");
				    String Qte="";
				    if(bean.getQuantite()!=null && bean.getQuantite().doubleValue()>0) {
				    	Qte=ProcessFormatNbr.convertDoubleToIntString(bean.getQuantite());
				    }
				    
				    cell = new PdfPCell(new Phrase("    - "+Qte+"  "+Jsoup.parse(unite).body().text()+"  "+Jsoup.parse(produit).body().text(),arialuniArab));
				    cell.setColspan(100);
				    cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
				    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cell.setBorder(cell.NO_BORDER);
				    tableTopHeader2.addCell(cell);
				    
		    }
		 
		    //unite:كلغ,produit:وراطة 400-600 غ , box:طرد,ligne1:عدد الطرود,ligne2:تحتوي على,
		     
		    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(100);
		    cell.setFixedHeight(5f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
	
		    PdfPTable tableTopHeader3 = new PdfPTable(100);
		    tableTopHeader3.setWidthPercentage(85);
		    PdfPCell cellSummary = new PdfPCell(new Phrase( denBean.getObservation()+""+""+Jsoup.parse("كلغ").body().text()+"                        "+denBean.getFact_clt_id(),arialuniArab));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cellSummary.setPaddingBottom(3);
	        cellSummary.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        
	        cellSummary = new PdfPCell(new Phrase(Jsoup.parse("بتاريخ").body().text(),arialuniArab));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cellSummary.setPaddingBottom(3);
	        cellSummary.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        

	        
	        cellSummary = new PdfPCell(new Phrase( ProcessDate.getStringFormatDateRTL(denBean.getFact_date()) ,arialuniArab));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cellSummary.setPaddingBottom(3);
	        cellSummary.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        
	        
	        cellSummary = new PdfPCell(new Phrase( " " ,GeneratePdf.Normal_10_times_roman));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cellSummary.setPaddingBottom(70);
	        cellSummary.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        
	        
	        cellSummary = new PdfPCell(new Phrase(Jsoup.parse("صفاقس ـ  تونس").body().text() ,arialuniArab));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cellSummary.setPaddingBottom(3);
	        cellSummary.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        
	        cellSummary = new PdfPCell(new Phrase("    " ,arialuniArab));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cellSummary.setPaddingBottom(10);
	        cellSummary.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        
	        cellSummary = new PdfPCell(new Phrase(ProcessDate.getStringFormatDateRTL(denBean.getFact_date())  ,arialuniArab));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cellSummary.setPaddingBottom(3);
	        cellSummary.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        
	        cellSummary = new PdfPCell(new Phrase(ProcessDate.getStringFormatDateRTL(denBean.getFact_date())  ,arialuniArab));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cellSummary.setVerticalAlignment(Element.ALIGN_BOTTOM);
	        cellSummary.setFixedHeight(75f);
	        cellSummary.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        
	        
	        document.add(tableTopHeader);
		    document.add(tableTopHeader2);
		    document.add(tableTopHeader3);

	}
	
	public void printEtatFactureVenteExportKobbi( EditionVenteBean searchBean ) throws Exception{
		
		try {
		List   listEditionVente=  (List) getObjectValueModel("listEditionVente") ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+"listEditionVente"+getRequest().getSession().getId()+".pdf");
		BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	    FileOutputStream fs = new FileOutputStream(file);
	    Document document = new Document(PageSize.A4.rotate(), 5, 5, 20, 40);
		doWriteHeaderDocumentEditionvente(document,97,fs,bSession);
		doWriteEnteteEtatVente(document,73,searchBean );
		doWriteHeaderEtatEditionVente(document,97,EditionVenteTemplate.MapfieldEtatDeVente);
		doWriteDataTableEditionVenteKobbi (listEditionVente,document,97,EditionVenteTemplate.MapfieldEtatDeVente);
	    //doWrite_mode_pay_banc(denBean,lisData,73,document);  ;  
	    /*****************************************************************************************************/
	    document.close();
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		getResponse().getWriter().write("listEditionVente"+getRequest().getSession().getId()+".pdf");
		
		} catch (Exception e) {
			throw e;
		} 
	}
	
public void printEtatVenteExport( EditionVenteBean searchBean ) throws Exception{
		
		try {
		List   listEditionVente=  (List) getObjectValueModel("listEditionVente") ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+"listEditionVente"+getRequest().getSession().getId()+".pdf");
		BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	    FileOutputStream fs = new FileOutputStream(file);
	    Document document = GeneratePdf.doGenerateDocumentFormat();
	    GeneratePdf.doWriteHeaderDocument_PDF(document, fs, bSession);      
		doWriteEnteteEtatVente(document,73,searchBean );
	    String [][] mapfieldEtatDeVente  = new String[][]{{ "date", "12" }, { "invoice", "12" },
			{ "client", "33" },{ "description", "35" },
			{ "qte", "10" },
			{ "prixUnit", "20" },{ "total", "20" },
		    };
		   
		doWriteHeaderEtatEditionVente(document,97,mapfieldEtatDeVente);
		//doWriteDataTableEditionVenteKobbi (listEditionVente,document,97,mapfieldEtatDeVente);
		doWriteDataTableEditionVente(listEditionVente,document,97,mapfieldEtatDeVente);
	    //doWrite_mode_pay_banc(denBean,lisData,73,document);  ;  
	    /*****************************************************************************************************/
	    document.close();
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		getResponse().getWriter().write("listEditionVente"+getRequest().getSession().getId()+".pdf");
		
		} catch (Exception e) {
			throw e;
		} 
	}
	
	private  void doWriteHeaderDocumentEditionvente(Document document,int poucentage, FileOutputStream fs ,BeanSession bSession) throws Exception {
		
		    
		    
		    PdfWriter writer = PdfWriter.getInstance(document,  fs);
		    TableHeaderNormale event = new TableHeaderNormale(bSession);
	        BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
	        document.addCreator(bs.getPack_libelle());
		    document.addAuthor(bs.getMod_libelle());
		    document.addTitle(bs.getFct_libelle()+"/"+bs.getSousmod_libelle_title());
		    document.open();
	        
	        PdfPTable tableheader = new PdfPTable(100);
	        PdfPCell cellheder;
	        tableheader.setWidthPercentage(100);
	        Image companyLogo = Image.getInstance(bs.getSociete().getMyFile().getFile_byte());
			companyLogo.scalePercent(50);
			cellheder = new PdfPCell(); 
			cellheder.setBorder(3);
			cellheder.addElement(new Chunk(companyLogo, 10, -50));
			cellheder.setColspan(10);
			cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellheder.setBorder(cellheder.NO_BORDER);
			tableheader.addCell(cellheder);
		    cellheder = new PdfPCell(new Phrase(bs.getSoc_lib()+"\n\r"+bs.getEtab_lib()+"\n\r"+bs.getPrf_libelle(),GeneratePdf.Normal_10_times_roman));
		    cellheder.setColspan(90);
		    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cellheder.setPaddingLeft(20f);
		    cellheder.setPaddingBottom(15f);
		    cellheder.setPaddingTop(15f);
		    cellheder.setBorder(cellheder.NO_BORDER);
		    tableheader.addCell(cellheder);
		    document.add(tableheader);
	
}
	
	  private void doWrite_mode_pay_banc(Facture_clientBean    denBean,List   lisData,int poucentage,Document document) throws Exception {
			
			try {
				 
	           
	           PdfPTable table_des_tva = new PdfPTable(100);
	           table_des_tva.setWidthPercentage(poucentage);
	           
	           
	           
	           
	           /******************************************* Entete  tableau des tva *************************************/
	           PdfPCell cell = new PdfPCell(new Phrase( "" ,GeneratePdf.FONT_12_normal));
	           cell.setColspan(100);
	           cell.setFixedHeight(30f);
	           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	           cell.setBorder(cell.NO_BORDER);
	           table_des_tva.addCell(cell);
	           
	            
	           Font FONT_12_bold = new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.BOLD | Font.UNDERLINE);
	   	       cell = new PdfPCell(new Phrase("Payment Choice:"/*affich_mont*/,FONT_12_bold));
	   	       cell.setColspan(100);
	   	       cell.setFixedHeight(25f);
	   	       cell.setVerticalAlignment(Element.ALIGN_LEFT);
	   	       cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	   	       cell.setBorder(cell.NO_BORDER);
	   	       table_des_tva.addCell(cell);
	           /********************************************************************************************************/
			 
	           List  list_mod_reglement= (List) getObjectValueModel(Facture_clientTemplate.LIST_MODE_REGLMENT);
	           
	           for (int i = 0; i < list_mod_reglement.size(); i++) {
	        	   ModeReglementBean   beanSearBean = (ModeReglementBean) list_mod_reglement.get(i);
				
	        	   String key = "";
	        	   if(denBean.getModReg().getMod_id().intValue() == beanSearBean.getMod_id().intValue()  ) {
	        		   key = "X";
	        	   }
				   cell = new PdfPCell(new Phrase(key,GeneratePdf.Bold_10_times_roman));
		           cell.setColspan(10);
		           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		           cell.setBorder(cell.NO_BORDER);
		           table_des_tva.addCell(cell);
		           
		           cell = new PdfPCell(new Phrase(beanSearBean.getMod_libelle(),GeneratePdf.Bold_10_times_roman));
		           cell.setColspan(90);
		           //cell.setFixedHeight(20f);
		           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		           cell.setBorder(cell.NO_BORDER);
		           table_des_tva.addCell(cell);
		           
		           
			   } 
	           
	           JSONArray  list_total  =  (JSONArray) getObjectValueModel("list_total");
	           
	           
	           PdfPTable table_total = new PdfPTable(100);
	           table_total.setWidthPercentage(73);
	           
	           /****************************************************************************************************************/
	           
	            
	           int  init=13;
	           int size=list_mod_reglement.size();
	           int space=size*20;
	           space=space+init;
	           space=space*-1;
	           
	           /****************************************************************************************************************/
	           
	           
	           table_des_tva.setSpacingAfter(Float.valueOf(String.valueOf(space)));
	           for (int i = 0; i < list_total.length(); ++i) {
	        	    JSONObject rec = list_total.getJSONObject(i);
	        	    String titre = rec.getString("value1"); 
	        	    String value = rec.getString("value2");
	        	    if(i>4) continue;
	        	    if(i==0) {titre=""; value="";}
	        	    if(i==1) {titre=""; value="Account Number";}
	        	    if(i==2) {titre="IBAN:"; value=denBean.getCpt_bank().getCptbanribrs();} 
	        	    if(i==3) {titre="BIC:";  value=denBean.getCpt_bank().getCptbanribrib();  }
	        	    if(i==4) {titre="";  value=denBean.getClient().getClt_method_export();}
	        	    
	        	    cell = new PdfPCell(new Phrase("",GeneratePdf.Bold_10_times_roman));
	                cell.setColspan(45);
	                cell.setFixedHeight(17f);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                cell.setBorder(cell.NO_BORDER);
	                table_total.addCell(cell);
	                
	                
	                cell = new PdfPCell(new Phrase(titre,GeneratePdf.Bold_10_times_roman));
	                cell.setColspan(10);
	                cell.setFixedHeight(17f);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                cell.setBorder(cell.NO_BORDER);
	                table_total.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(value ,GeneratePdf.Bold_10_times_roman));
	                cell.setColspan(45);
	                cell.setFixedHeight(17f);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                cell.setBorder(cell.NO_BORDER);
	                table_total.addCell(cell);
	        	}
	           document.add(table_des_tva);
	           document.add(table_total);
	          
			} catch (Exception e) {
			}
		
		}
	private  void doWriteHeaderDocument_PDF_NOT_PASY(Document document,int poucentage, FileOutputStream fs ,String [][] mapFieldBean,BeanSession bSession) throws Exception {
	    PdfWriter writer = PdfWriter.getInstance(document,  fs);
	    TableHeaderNormale event = new TableHeaderNormale(bSession);
        //writer.setPageEvent(event);
        
       
        BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
        
        document.addCreator(bs.getPack_libelle());
	    document.addAuthor(bs.getMod_libelle());
	    document.addTitle(bs.getFct_libelle()+"/"+bs.getSousmod_libelle_title());
	    document.open();
        
        PdfPTable tableheader = new PdfPTable(100);
        PdfPCell cellheder;
        tableheader.setWidthPercentage(poucentage);
        
        Image companyLogo = Image.getInstance("c:/images/sigle_ibs.jpg");
		companyLogo.scalePercent(50);
		cellheder = new PdfPCell(); 
		cellheder.setBorder(3);
		//cellheder.addElement(new Chunk(companyLogo, 10, -50));
		
		cellheder.setColspan(10);
		cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellheder.setBorder(cellheder.NO_BORDER);
		tableheader.addCell(cellheder);
		//bs.getSoc_lib()+"\n\r"+bs.getEtab_lib()+"\n\r"+bs.getPrf_libelle()
		
	    cellheder = new PdfPCell(new Phrase("                            ",GeneratePdf.Normal_10_times_roman));
	    cellheder.setColspan(90);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setPaddingLeft(28f);
	    cellheder.setPaddingBottom(200f);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    document.add(tableheader);
	
}
	
	private  void doWriteHeaderDocumentMancha(Document document,int poucentage, FileOutputStream fs ,String [][] mapFieldBean,BeanSession bSession) throws Exception {
	    PdfWriter writer = PdfWriter.getInstance(document,  fs);
	    TableHeaderNormale event = new TableHeaderNormale(bSession);
        //writer.setPageEvent(event);
        
       
        BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
        
        document.addCreator(bs.getPack_libelle());
	    document.addAuthor(bs.getMod_libelle());
	    document.addTitle(bs.getFct_libelle()+"/"+bs.getSousmod_libelle_title());
	    document.open();
        
        PdfPTable tableheader = new PdfPTable(100);
        PdfPCell cellheder;
        tableheader.setWidthPercentage(poucentage);
        
        Image companyLogo = Image.getInstance("c:/images/sigle_ibs.jpg");
		companyLogo.scalePercent(50);
		cellheder = new PdfPCell(); 
		cellheder.setBorder(3);
		//cellheder.addElement(new Chunk(companyLogo, 10, -50));
		
		cellheder.setColspan(10);
		cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellheder.setBorder(cellheder.NO_BORDER);
		tableheader.addCell(cellheder);
		//bs.getSoc_lib()+"\n\r"+bs.getEtab_lib()+"\n\r"+bs.getPrf_libelle()
		
	    cellheder = new PdfPCell(new Phrase("                            ",GeneratePdf.Normal_10_times_roman));
	    cellheder.setColspan(90);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setPaddingLeft(28f);
	    cellheder.setPaddingBottom(156f);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    document.add(tableheader);
	
}
	
	private   void doWriteEnteteKobbi(Document document,int poucentage, Facture_clientBean denBean) throws Exception {
			PdfPTable tableTopHeader = new PdfPTable(100);
			tableTopHeader.setWidthPercentage(poucentage);
			    
			PdfPCell cell = new PdfPCell(new Phrase("Invoice N#",GeneratePdf.Bold_11_times_roman));
		    cell.setColspan(15);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase(" "+denBean.getFact_clt_id(),GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(25);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    
		    cell = new PdfPCell(new Phrase("",GeneratePdf.Bold_11_times_roman));
		    cell.setColspan(18);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    
		    cell = new PdfPCell(new Phrase("" ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(42);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		     
		   
		    
		    cell = new PdfPCell(new Phrase("Place,  Date  Tunis",GeneratePdf.Bold_11_times_roman));
		    cell.setColspan(25);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    
		    Date date = sdf.parse(ProcessDate.getCurrentTimeStamp(denBean.getFact_date()));

		    String month_name = month_date.format(date);
		    
		    cell = new PdfPCell(new Phrase(","+month_name ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(75);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    PdfPTable tableTopHeader2 = new PdfPTable(100);
		    tableTopHeader2.setWidthPercentage(85);
		    
		    cell = new PdfPCell(new Phrase("  ",GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(51);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		   
		    
		    Font FONT_12_bold = new Font(Font.getFamily("TIMES_ROMAN"), 11, Font.BOLD|  Font.UNDERLINE);
		    cell = new PdfPCell(new Phrase("Billing To",FONT_12_bold));
		    cell.setColspan(49);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("  ",GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(51);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		     
		    
		    cell = new PdfPCell(new Phrase(denBean.getClient().getClt_lib(),GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(49);

		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    
		    cell = new PdfPCell(new Phrase("  " ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(51);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    cell = new PdfPCell(new Phrase(denBean.getClient().getClt_adr(),GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(49);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    
		    cell = new PdfPCell(new Phrase("  " ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(51);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    cell = new PdfPCell(new Phrase(denBean.getClient().getClt_region(),GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(49);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("  " ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(51);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    cell = new PdfPCell(new Phrase(denBean.getClient().getClt_pays(),GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(49);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    
		    
		    
		    
		    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.Normal_11_times_roman));
		    cell.setColspan(100);
		    cell.setFixedHeight(5f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    PdfPTable tableTopHeader3 = new PdfPTable(100);
		    tableTopHeader3.setWidthPercentage(73);
		    PdfPCell cellSummary = new PdfPCell(new Phrase( "Summary table:" ,GeneratePdf.Normal_10_times_roman));
	        cellSummary.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cellSummary.setPaddingBottom(3);
	        cellSummary.setColspan(100);
	        cellSummary.setBorder(cellSummary.NO_BORDER);
	        tableTopHeader3.addCell(cellSummary);
	        document.add(tableTopHeader);
		    document.add(tableTopHeader2);
		    document.add(tableTopHeader3);

	}

	private void doWriteEnteteEtatVente(Document document, int poucentage, EditionVenteBean searchBean)
			throws Exception {
		PdfPTable tableTopHeader2 = new PdfPTable(100);
		tableTopHeader2.setWidthPercentage(85);
		Font FONT_12_bold = new Font(Font.getFamily("TIMES_ROMAN"), 13, Font.BOLD);
		PdfPCell cell = new PdfPCell(
				new Phrase("Etat des Ventes  " + ProcessDate.getStringFormatDate(searchBean.getDate_debut()) + " - "
						+ ProcessDate.getStringFormatDate(searchBean.getDate_fin()), FONT_12_bold));
		cell.setColspan(100);
		cell.setPaddingBottom(20f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(cell.NO_BORDER);
		tableTopHeader2.addCell(cell);
		document.add(tableTopHeader2);
	}
	


	private void doWriteEnteteEtatDepense(Document document, int poucentage, EditionVenteBean searchBean)
			throws Exception {
		PdfPTable tableTopHeader2 = new PdfPTable(100);
		tableTopHeader2.setWidthPercentage(85);
		Font FONT_12_bold = new Font(Font.getFamily("TIMES_ROMAN"), 13, Font.BOLD);
		PdfPCell cell = new PdfPCell(
				new Phrase("ETAT des Dépenses " + ProcessDate.getStringFormatDate(searchBean.getDate_debut()) + " - "
						+ ProcessDate.getStringFormatDate(searchBean.getDate_fin()), FONT_12_bold));
		cell.setColspan(100);
		cell.setPaddingBottom(20f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(cell.NO_BORDER);
		tableTopHeader2.addCell(cell);
		document.add(tableTopHeader2);
	}

	  private void doWrite_Header_ContentTable(Document document,int poucentage, String[][] mapFieldBean) throws Exception {
			
			  
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
					 titrehead=(String) getObjectValueModel("kb_"+getel);
					 
	    	     }else{
	    	    	 titrehead=(String) getObjectValueModel("kb_"+mapFieldBean[i][0]);
	    	     } 
	    	   
	    	    PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":titrehead  ,GeneratePdf.Bold_10_times_roman));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setPaddingBottom(PaddingBottom);
			    cell.setBackgroundColor(GeneratePdf.colorHeader);
			    cell.setBorderWidth(1f);
			    table.addCell(cell);
			}
	      document.add(table);
	}
	  
	  private void doWriteHeaderEtatEditionVente(Document document,int poucentage, String[][] mapFieldBean) throws Exception {
			
		  BeanSession bs= (BeanSession) getObjectValueModel(BEAN_SESSION);
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
					 titrehead=getel;
					 
	    	     }else{
	    	    	 titrehead=mapFieldBean[i][0];
	    	     } 
	    	     if(titrehead !=null &&  titrehead.equals("invoice") && bs.getSoc_id().equals("10"))   titrehead="Vente";
	    	     if(titrehead !=null &&  titrehead.equals("prixUnit") && bs.getSoc_id().equals("10"))  titrehead="PrixUnit HT";
	    	     if(titrehead !=null &&  titrehead.equals("total") && bs.getSoc_id().equals("10"))     titrehead="PrixUnit TTC";


	    	   
	    	    PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":   WordUtils.capitalize(titrehead) ,GeneratePdf.Bold_10_times_roman));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setPaddingBottom(PaddingBottom);
			    cell.setBackgroundColor(GeneratePdf.colorHeader);
			    cell.setBorderWidth(1f);
			    table.addCell(cell);
			}
	      document.add(table);
	}
	  
	  private void doWriteHeaderEtatEditionDepense(Document document,int poucentage, String[][] mapFieldBean) throws Exception {
			
		  
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
					 titrehead=getel;
					 
	    	     }else{
	    	    	 titrehead=mapFieldBean[i][0];
	    	     } 
	    	   
	    	    PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":   WordUtils.capitalize(titrehead) ,GeneratePdf.Bold_8_times_roman));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setPaddingBottom(PaddingBottom);
			    cell.setBackgroundColor(GeneratePdf.colorHeader);
			    cell.setBorderWidth(1f);
			    table.addCell(cell);
			}
	      document.add(table);
	}
		
	  private   void doWriteDataTableEditionVenteKobbi(List lisData, Document document,int poucentage,String[][] mapFieldBean) throws Exception, SecurityException {
		  
		    PdfPTable table = new PdfPTable(mapFieldBean.length);
	        int[] columnWidths = new int[mapFieldBean.length] ;
	        for(int i = 0; i < mapFieldBean.length; i++){
	    	 columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
			}
	       table.setWidthPercentage(poucentage);
	       table.setWidths(columnWidths);
	      
	       HashMap  mapDate= new HashMap();
	       Double totGenfacture= new Double(0);
	       Double totGenQte= new Double(0);
	       Double totGenNbrBox= new Double(0);
	       
	       List<EtatVenteProduit> list_etat_edition = new ArrayList<EtatVenteProduit>();
	       DeviseBean  devise = new DeviseBean();
			for (int i = 0; i < lisData.size(); i++) {
				Det_Fact_ClientBean dBean  =(Det_Fact_ClientBean) lisData.get(i);
				devise=dBean.getPk().getFactclient().getDevise();
				Date dateFact=dBean.getPk().getFactclient().getFact_date();
				List listPardate=(List) mapDate.get(dateFact);
				if(listPardate==null) {listPardate= new ArrayList();  }
				listPardate.add(dBean);
				mapDate.put(dateFact, listPardate);
			}
			Set set_mapdate= mapDate.keySet();
		
			for (Iterator iterr = set_mapdate.iterator(); iterr.hasNext();) {
				Date dateFact = (Date) iterr.next();
				boolean isrowSpanDate=true;
				List listmapDate=(List) mapDate.get(dateFact);
				
				
				
				HashMap  mapInvoiceClient=  new HashMap();
				for (int i = 0; i < listmapDate.size(); i++) {
					Det_Fact_ClientBean dBean  =(Det_Fact_ClientBean) listmapDate.get(i);
					String  key =dBean.getPk().getFactclient().getFact_clt_id()+"²µ²"+dBean.getPk().getFactclient().getClient().getClt_lib();
					List listInvoice=(List) mapInvoiceClient.get(key);
					if(listInvoice==null) {listInvoice= new ArrayList();  }
					listInvoice.add(dBean);
					mapInvoiceClient.put(key, listInvoice);
				}
				
				Set mapInvoiceClientSet = mapInvoiceClient.keySet(); 
				
				for (Iterator iterInvo = mapInvoiceClientSet.iterator(); iterInvo.hasNext();) {
					String iClientnvoice = (String) iterInvo.next();
					String[] element=iClientnvoice.split("²µ²");
					boolean isrowSpanDetailFacture=true;
					List listInvoiceClient=(List) mapInvoiceClient.get(iClientnvoice);
					Double totfacture= new Double(0);
					for (int i = 0; i < listInvoiceClient.size(); i++) {
					    Det_Fact_ClientBean dBean  =(Det_Fact_ClientBean) listInvoiceClient.get(i);	
					    EtatVenteProduit  etatBean  =  new EtatVenteProduit();
					    totfacture =ProcessNumber.addition(totfacture,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getPk().getFactclient().getTotal_facture(), devise.getChiffre_pattern())  );
					    
					    totGenfacture =ProcessNumber.addition(totGenfacture,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getPk().getFactclient().getTotal_facture(), devise.getChiffre_pattern())  );
					    totGenQte =ProcessNumber.addition(totGenQte,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getQuantite(), devise.getChiffre_pattern())  );
					    totGenNbrBox =ProcessNumber.addition(totGenNbrBox,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getNbrBoxes(), devise.getChiffre_pattern())  );
					    
					    
					    etatBean.setIsrowSpanDate(isrowSpanDate);
					    etatBean.setRowSpanDate(listmapDate.size());
					    
					    etatBean.setIsrowSpanDetFact(isrowSpanDetailFacture);
					    etatBean.setRowSpanDetFacture(listInvoiceClient.size());
					    
						etatBean.setDate(dateFact);
						etatBean.setInvoice(element[0]);
						etatBean.setClient(element[1]);
						etatBean.setDescription(dBean.getPk().getFkcode_barre().getDesignation_libelle());
						etatBean.setQte(dBean.getQuantite());
						etatBean.setNbrBox(dBean.getNbrBoxes());
						etatBean.setPrixUnit(dBean.getTarif_unit_vente());
						list_etat_edition.add(etatBean);
						
						if(i==listInvoiceClient.size()-1) {
							 int position=list_etat_edition.size() - listInvoiceClient.size();
							 list_etat_edition.get(position).setTotal(ProcessFormatNbr.FormatDouble_ParameterChiffre(totfacture, devise.getChiffre_pattern())  ); 
						}
						isrowSpanDate=false;
						isrowSpanDetailFacture=false;
					}
				}
			}

			  
			
			    Collections.sort(list_etat_edition, new Comparator<EtatVenteProduit>() {
					@Override
					public int compare(EtatVenteProduit o1, EtatVenteProduit o2) {
						return o1.getDate().compareTo( o2.getDate() );
					}
				});
			  
			
			
		    for(int i=0; i < list_etat_edition.size(); i++ ){
		    	EtatVenteProduit bean = (EtatVenteProduit) list_etat_edition.get(i); 
		    	
		    	if(bean.isIsrowSpanDate()) {
		    	PdfPCell cell = new PdfPCell(new Phrase( ProcessDate.getStringFormatDate(bean.getDate()),GeneratePdf.Bold_10_times_roman));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        cell.setRowspan(bean.getRowSpanDate());
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setPadding(5f);
		        cell.setPaddingBottom(7f);
		        if(i%2==0)
		        cell.setBackgroundColor(GeneratePdf.colorLigne);
		        table.addCell(cell);
		    	}
		    	
		    	
		    	if(bean.isIsrowSpanDetFact()) {
		    	PdfPCell cell  = new PdfPCell(new Phrase( bean.getInvoice(),GeneratePdf.Bold_10_times_roman));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setPadding(5f);
		        cell.setRowspan(bean.getRowSpanDetFacture());
		        cell.setPaddingBottom(7f);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        if(i%2==0)
		        cell.setBackgroundColor(GeneratePdf.colorLigne);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase( bean.getClient(),GeneratePdf.Bold_10_times_roman));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setRowspan(bean.getRowSpanDetFacture());
		        cell.setPadding(5f);
		        cell.setPaddingBottom(7f);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        if(i%2==0)
		        cell.setBackgroundColor(GeneratePdf.colorLigne);
		        table.addCell(cell);
		    	}
		        
		         
			
				
		    	PdfPCell cell = new PdfPCell(new Phrase( bean.getDescription(),GeneratePdf.Bold_10_times_roman));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setPadding(5f);
		        cell.setPaddingBottom(7f);
			    cell.setBackgroundColor(BaseColor.WHITE);
			    if(i%2==0)
			    cell.setBackgroundColor(GeneratePdf.colorLigne);
			    table.addCell(cell);

			        
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.convertDoubleToIntString(bean.getQte())   ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(BaseColor.WHITE);
			   if(i%2==0)
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
			   
			    
			   
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(bean.getPrixUnit(), devise, true, false)  ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(BaseColor.WHITE);
			   if(i%2==0)
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
				if(bean.isIsrowSpanDetFact()) {
				   cell = new PdfPCell(new Phrase(   ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(bean.getTotal(), devise, true, false)   ,GeneratePdf.Bold_10_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(5f);
			       cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPaddingBottom(7f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				}
	        }
		    
		    
		   
		    	PdfPCell cell = new PdfPCell(new Phrase( "Total",GeneratePdf.Bold_10_times_roman));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        cell.setColspan(4);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setPadding(5f);
		        cell.setPaddingBottom(7f);
		        cell.setBackgroundColor(GeneratePdf.colorLigne);
		        table.addCell(cell);
		    	 
		   
			      
			        
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.convertDoubleToIntString(totGenQte)   ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
			   
			   
			   cell = new PdfPCell(new Phrase("" ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
			 
			   cell = new PdfPCell(new Phrase(   ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(totGenfacture, devise, true, false)   ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   cell.setPadding(5f);
			   cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
			 
		    
		    
	
		    document.add(table);
		}
	  
	  
	  
	  private   void doWriteDataTableEditionVente(List lisData, Document document,int poucentage,String[][] mapFieldBean) throws Exception, SecurityException {
		  
		    PdfPTable table = new PdfPTable(mapFieldBean.length);
	        int[] columnWidths = new int[mapFieldBean.length] ;
	        for(int i = 0; i < mapFieldBean.length; i++){
	    	 columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
			}
	       table.setWidthPercentage(poucentage);
	       table.setWidths(columnWidths);
	      
	       HashMap  mapDate= new HashMap();
	       Double totGenfacture= new Double(0);
	       Double totGenQte= new Double(0);
	       Double totGenNbrBox= new Double(0);
	       
	       List<EtatVenteProduit> list_etat_edition = new ArrayList<EtatVenteProduit>();
	       DeviseBean  devise = new DeviseBean();
			for (int i = 0; i < lisData.size(); i++) {
				DetProcedureVenteBean dBean  =(DetProcedureVenteBean) lisData.get(i);
				devise=dBean.getPk().getVente().getDevise();
				Date dateFact=dBean.getPk().getVente().getVente_date();
				List listPardate=(List) mapDate.get(dateFact);
				if(listPardate==null) {listPardate= new ArrayList();  }
				listPardate.add(dBean);
				mapDate.put(dateFact, listPardate);
			}
			Set set_mapdate= mapDate.keySet();
		
			for (Iterator iterr = set_mapdate.iterator(); iterr.hasNext();) {
				Date dateFact = (Date) iterr.next();
				boolean isrowSpanDate=true;
				List listmapDate=(List) mapDate.get(dateFact);
				
				
				
				HashMap  mapInvoiceClient=  new HashMap();
				for (int i = 0; i < listmapDate.size(); i++) {
					DetProcedureVenteBean dBean  =(DetProcedureVenteBean) listmapDate.get(i);
					String  key =dBean.getPk().getVente().getVente_id()+"²µ²"+dBean.getPk().getVente().getClient().getClt_lib();
					List listInvoice=(List) mapInvoiceClient.get(key);
					if(listInvoice==null) {listInvoice= new ArrayList();  }
					listInvoice.add(dBean);
					mapInvoiceClient.put(key, listInvoice);
				}
				
				Set mapInvoiceClientSet = mapInvoiceClient.keySet(); 
				
				for (Iterator iterInvo = mapInvoiceClientSet.iterator(); iterInvo.hasNext();) {
					String iClientnvoice = (String) iterInvo.next();
					String[] element=iClientnvoice.split("²µ²");
					boolean isrowSpanDetailFacture=true;
					List listInvoiceClient=(List) mapInvoiceClient.get(iClientnvoice);
					Double totfacture= new Double(0);
					for (int i = 0; i < listInvoiceClient.size(); i++) {
						DetProcedureVenteBean dBean  =(DetProcedureVenteBean) listInvoiceClient.get(i);	
					    EtatVenteProduit  etatBean  =  new EtatVenteProduit();
					    totfacture =ProcessNumber.addition(totfacture,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getMontant_ttc_vente(), devise.getChiffre_pattern())  );
					    
					    totGenfacture =ProcessNumber.addition(totGenfacture,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getMontant_ttc_vente(), devise.getChiffre_pattern())  );
					    totGenQte =ProcessNumber.addition(totGenQte,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getQuantite(), devise.getChiffre_pattern())  );
					   
					    
					    
					    etatBean.setIsrowSpanDate(isrowSpanDate);
					    etatBean.setRowSpanDate(listmapDate.size());
					    
					    etatBean.setIsrowSpanDetFact(isrowSpanDetailFacture);
					    etatBean.setRowSpanDetFacture(listInvoiceClient.size());
					    
						etatBean.setDate(dateFact);
						etatBean.setInvoice(element[0]);
						etatBean.setClient(element[1]);
						etatBean.setDescription(dBean.getPk().getFkcode_barre().getDesignation_libelle());
						etatBean.setQte(dBean.getQuantite());
						etatBean.setPrixUnit(dBean.getTarif().getTarif_unit_vente());
						list_etat_edition.add(etatBean);
						
						if(i==listInvoiceClient.size()-1) {
							 int position=list_etat_edition.size() - listInvoiceClient.size();
							 list_etat_edition.get(position).setTotal(ProcessFormatNbr.FormatDouble_ParameterChiffre(totfacture, devise.getChiffre_pattern())  ); 
						}
						isrowSpanDate=false;
						isrowSpanDetailFacture=false;
					}
				}
			}

			  
			
			    Collections.sort(list_etat_edition, new Comparator<EtatVenteProduit>() {
					@Override
					public int compare(EtatVenteProduit o1, EtatVenteProduit o2) {
						return o1.getDate().compareTo( o2.getDate() );
					}
				});
			  
			
			
		    for(int i=0; i < list_etat_edition.size(); i++ ){
		    	EtatVenteProduit bean = (EtatVenteProduit) list_etat_edition.get(i); 
		    	
		    	if(bean.isIsrowSpanDate()) {
		    	PdfPCell cell = new PdfPCell(new Phrase( ProcessDate.getStringFormatDate(bean.getDate()),GeneratePdf.Bold_10_times_roman));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        cell.setRowspan(bean.getRowSpanDate());
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setPadding(5f);
		        cell.setPaddingBottom(7f);
		        if(i%2==0)
		        cell.setBackgroundColor(GeneratePdf.colorLigne);
		        table.addCell(cell);
		    	}
		    	
		    	
		    	if(bean.isIsrowSpanDetFact()) {
		    	PdfPCell cell  = new PdfPCell(new Phrase( bean.getInvoice(),GeneratePdf.Bold_10_times_roman));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setPadding(5f);
		        cell.setRowspan(bean.getRowSpanDetFacture());
		        cell.setPaddingBottom(7f);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        if(i%2==0)
		        cell.setBackgroundColor(GeneratePdf.colorLigne);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase( bean.getClient(),GeneratePdf.Bold_10_times_roman));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setRowspan(bean.getRowSpanDetFacture());
		        cell.setPadding(5f);
		        cell.setPaddingBottom(7f);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        if(i%2==0)
		        cell.setBackgroundColor(GeneratePdf.colorLigne);
		        table.addCell(cell);
		    	}
		        
		         
			
				
		    	PdfPCell cell = new PdfPCell(new Phrase( bean.getDescription(),GeneratePdf.Bold_10_times_roman));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setPadding(5f);
		        cell.setPaddingBottom(7f);
			    cell.setBackgroundColor(BaseColor.WHITE);
			    if(i%2==0)
			    cell.setBackgroundColor(GeneratePdf.colorLigne);
			    table.addCell(cell);

			        
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.convertDoubleToIntString(bean.getQte())   ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(BaseColor.WHITE);
			   if(i%2==0)
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
			   
			  
			   
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(bean.getPrixUnit(), devise, true, false)  ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(BaseColor.WHITE);
			   if(i%2==0)
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
				if(bean.isIsrowSpanDetFact()) {
				   cell = new PdfPCell(new Phrase(   ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(bean.getTotal(), devise, true, false)   ,GeneratePdf.Bold_10_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(5f);
			       cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPaddingBottom(7f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				}
	        }
		    
		    
		   
		    	PdfPCell cell = new PdfPCell(new Phrase( "Total",GeneratePdf.Bold_10_times_roman));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        cell.setColspan(4);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setPadding(5f);
		        cell.setPaddingBottom(7f);
		        cell.setBackgroundColor(GeneratePdf.colorLigne);
		        table.addCell(cell);
		    	 
		   
			      
			        
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.convertDoubleToIntString(totGenQte)   ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
			   
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.convertDoubleToIntString(totGenNbrBox)   ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
			   
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(totGenfacture, devise, true, false) ,GeneratePdf.Bold_10_times_roman));
			   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		       cell.setPadding(5f);
		       cell.setPaddingBottom(7f);
			   cell.setBackgroundColor(GeneratePdf.colorLigne);
			   table.addCell(cell);
			 
			   
			 
		    
		    
	
		    document.add(table);
		}
	  
	  private   void doWrite_Data_Table(Facture_clientBean    denBean, List lisData, Document document,int poucentage,String[][] mapFieldBean) throws Exception, SecurityException {
		  
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
					 
					        PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.Bold_10_times_roman));
					 
					        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
					        
					        if(j==mapFieldBean.length-3  || j==mapFieldBean.length-4  ){
					        	
					        	
					        	String valu="";
					        	if(obj!=null   &&  !obj.equals("")) {
					        		Double D = (Double) obj;
						        	int iddd = Integer.valueOf(D.intValue());
						        	valu=String.valueOf(iddd);
					        	}
					            
					        	cell = new PdfPCell(new Phrase(  valu  ,GeneratePdf.Bold_10_times_roman));
						        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						        cell.setPaddingBottom(5);
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
						        table.addCell(cell);
						        continue;
					        }
					        if(j==mapFieldBean.length-1 || j==mapFieldBean.length-2){
					        	Double elm=Double.valueOf(String.valueOf(obj));
					        	cell = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes(elm,denBean.getDevise(),true, false),GeneratePdf.Bold_10_times_roman));
						        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						        cell.setPaddingBottom(5);
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
						        
					        }else{
					        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.Bold_10_times_roman));
						        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						        cell.setPaddingBottom(5);
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        }
					        table.addCell(cell);
					    }   
				   
	         }
			  
			  
			   Facture_clientBean    beanTotal = (Facture_clientBean) getObjectValueModel(Facture_clientTemplate.BEAN_TOTAL_FACTURE_CLIENT );
 
	         JSONArray  list_total  =  (JSONArray) getObjectValueModel("list_total");
	           for(int j = 0; j < mapFieldBean.length; j++){
	        	   String  elem="";
	        	
	        	   if(j==0){
	        		   JSONObject rec = list_total.getJSONObject(5);
	        		   elem= rec.getString("value1");
	        		   PdfPCell cell = new PdfPCell(new Phrase("Total",GeneratePdf.Bold_10_times_roman));
	        		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		   cell.setFixedHeight(17f);
				       cell.setBackgroundColor(BaseColor.WHITE);
				       cell.setBorder(cell.LEFT+cell.RIGHT+cell.BOTTOM+cell.TOP);
				       table.addCell(cell);
	        	   }
	        	   else if(j==4){
	        		   JSONObject rec = list_total.getJSONObject(5);
	        		   elem= rec.getString("value2");
	        		   PdfPCell cell = new PdfPCell(new Phrase(elem,GeneratePdf.Bold_10_times_roman));
	        		   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        		   cell.setFixedHeight(17f);
				       cell.setBackgroundColor(BaseColor.WHITE);
				       cell.setBorder(cell.LEFT+cell.RIGHT+cell.BOTTOM+cell.TOP);
				       table.addCell(cell);
	        	   }
	        	   else if(j==1){
	        		   String valu="";
			        	if(beanTotal!=null   &&   !StringUtils.isBlank(beanTotal.getTotal_quantite())  ) {
			        		Double ddd=Double.parseDouble(beanTotal.getTotal_quantite());
			        		int iddd = Integer.valueOf(ddd.intValue());
				        	valu=String.valueOf(iddd);
			        	}
	        		   PdfPCell cell = new PdfPCell(new Phrase(valu,GeneratePdf.Bold_10_times_roman));
	        		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		   cell.setFixedHeight(17f);
				       cell.setBackgroundColor(BaseColor.WHITE);
				       cell.setBorder(cell.LEFT+cell.RIGHT+cell.BOTTOM+cell.TOP);
				       table.addCell(cell);
	        	   }
	        	   else if(j==2){
	        		   String valu="";
			        	if(beanTotal!=null   && beanTotal.getTotnbrBox()!=null) {
				        	int iddd = Integer.valueOf(beanTotal.getTotnbrBox().intValue());
				        	valu=String.valueOf(iddd);
			        	}
			         
			        	
	        		   PdfPCell cell = new PdfPCell(new Phrase(valu,GeneratePdf.Bold_10_times_roman));
	        		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		   cell.setFixedHeight(17f);
				       cell.setBackgroundColor(BaseColor.WHITE);
				       cell.setBorder(cell.LEFT+cell.RIGHT+cell.BOTTOM+cell.TOP);
				       table.addCell(cell);
	        	   }
	        	   else {
	        		   PdfPCell cell = new PdfPCell(new Phrase(elem,GeneratePdf.Bold_10_times_roman));
	        		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
	        		   cell.setFixedHeight(17f);
				       cell.setBackgroundColor(BaseColor.WHITE);
				       cell.setBorder(cell.LEFT+cell.RIGHT+cell.BOTTOM+cell.TOP);
				       table.addCell(cell);
	        	   }
		           
		    }   
	
			  document.add(table);
		}
		private void doWriteEntete(Document document, CommandeclientBean   denBean) throws Exception {
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



		public void printEtatDepensesProduitsKobbi(EditionVenteBean searchBean) throws Throwable  {
			 
			try {
				String [][] MapfieldEtatDeDepense  = new String[][]{
					{ "date", "20" }, { "invoice", "20" },
					{ "client", "30" },{ "qté", "15" },
				    { "AchatFish", "20" },{ "n/Box", "13" },
					{ "Poly", "20" },{ "trsprt", "20" },
					{ "transit", "20" },{ "m/Oeuv", "20" },
					{ "Embal", "20" },{ "scot/glace", "20" },
					{ "douane", "20" },{ "ch/Com", "20" },
					{ "tran/Ae", "20" },{ "total", "20" },
					};	
				List   listEditionDepense=  (List) getObjectValueModel("listEditionDepense") ;
				
				Collections.sort(listEditionDepense, new Comparator<EtatDepenseProduit>() {
					@Override
					public int compare(EtatDepenseProduit o1, EtatDepenseProduit o2) {
						return o1.getDate().compareTo( o2.getDate() );
					}
				});
				 
             
				File file = new File(getRequest().getRealPath("/")+"/temp/"+"listEditionDepense"+getRequest().getSession().getId()+".pdf");
				BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
			    FileOutputStream fs = new FileOutputStream(file);
			    Document document = new Document(PageSize.A4.rotate(), 3, 3, 20, 40);
				doWriteHeaderDocumentEditionvente(document,97,fs,bSession);
				doWriteEnteteEtatDepense(document,73,searchBean );
				doWriteHeaderEtatEditionDepense(document,99,MapfieldEtatDeDepense);
				doWriteDataTableEditionDepenseKobbi (listEditionDepense,document,99,MapfieldEtatDeDepense);
			    //doWrite_mode_pay_banc(denBean,lisData,73,document);  ;  
			    /*****************************************************************************************************/
			    document.close();
				getResponse().setContentType("text");
				getResponse().setHeader("Cache-Control", "no-cache");
				getResponse().setStatus(200);
				getResponse().getWriter().write("listEditionDepense"+getRequest().getSession().getId()+".pdf");
				
				} catch (Exception e) {
					throw e;
				} 
		}
		private void doWriteDataTableEditionDepenseKobbi(List listEditionDepense, Document document, int poucentage,String[][] mapfieldEtatDeDepense) throws Throwable {
			
			  PdfPTable table = new PdfPTable(mapfieldEtatDeDepense.length);
		        int[] columnWidths = new int[mapfieldEtatDeDepense.length] ;
		        for(int i = 0; i < mapfieldEtatDeDepense.length; i++){
		    	 columnWidths[i]= Integer.parseInt(mapfieldEtatDeDepense[i][1])   ;
				}
		       table.setWidthPercentage(poucentage);
		       table.setWidths(columnWidths);
		        
		       HashMap  hashMapTotal = (HashMap) getObjectValueModel("ligneTotal");
		      
				
			    for(int i=0; i < listEditionDepense.size(); i++ ){
			    	EtatDepenseProduit bean = (EtatDepenseProduit) listEditionDepense.get(i); 
			    	
			    	if(bean.isIsrowSpanDate()) {
			    	PdfPCell cell = new PdfPCell(new Phrase( ProcessDate.getStringFormatDate(bean.getDate()),GeneratePdf.Bold_8_times_roman));
			        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        cell.setRowspan(bean.getRowSpanDate());
			        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        cell.setPadding(3f);
			        cell.setPaddingBottom(5f);
			        if(i%2==0)
			        cell.setBackgroundColor(GeneratePdf.colorLigne);
			        table.addCell(cell);
			    	}
			    	
			    	
			    	 
			    	PdfPCell cell  = new PdfPCell(new Phrase( bean.getInvoice(),GeneratePdf.Bold_8_times_roman));
			        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        cell.setPadding(3f);
			        //cell.setRowspan(bean.getRowSpanDetFacture());
			        cell.setPaddingBottom(5f);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        if(i%2==0)
			        cell.setBackgroundColor(GeneratePdf.colorLigne);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase( bean.getClient(),GeneratePdf.Bold_8_times_roman));
			        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        //cell.setRowspan(bean.getRowSpanDetFacture());
			        cell.setPadding(3f);
			        cell.setPaddingBottom(5f);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        if(i%2==0)
			        cell.setBackgroundColor(GeneratePdf.colorLigne);
			        table.addCell(cell);
					
			    	cell = new PdfPCell(new Phrase(  ProcessFormatNbr.convertDoubleToIntString(bean.getQteFish()) ,GeneratePdf.Bold_8_times_roman));
				    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    //cell.setRowspan(bean.getRowSpanDetFacture());
			        cell.setPadding(3f);
			        cell.setPaddingBottom(5f);
				    cell.setBackgroundColor(BaseColor.WHITE);
				    if(i%2==0)
				    cell.setBackgroundColor(GeneratePdf.colorLigne);
				    table.addCell(cell);

				        
				   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes (bean.getPrixtotFish(), bean.getDevise(), false, false)    ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   //cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   String qteBoxSt="";
				   if(bean.getNbrBox()!=null) {
					 qteBoxSt=ProcessFormatNbr.convertDoubleToIntString(bean.getNbrBox());
				   }
				   cell = new PdfPCell(new Phrase( qteBoxSt ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   //cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   String mntBoxSt="";
				   if(bean.getPrixtotPoly()!=null) {
					   mntBoxSt=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getPrixtotPoly(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( mntBoxSt ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   //cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				
					
					  String transport="";
					   if(bean.getTrsprt()!=null) {
						   transport=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getTrsprt(), "0.000")   ;
					   }
					   cell = new PdfPCell(new Phrase( transport ,GeneratePdf.Bold_8_times_roman));
					   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   //cell.setRowspan(bean.getRowSpanDetFacture());
				       cell.setPadding(3f);
				       cell.setPaddingBottom(5f);
					   cell.setBackgroundColor(BaseColor.WHITE);
					   if(i%2==0)
					   cell.setBackgroundColor(GeneratePdf.colorLigne);
					   table.addCell(cell);
					   
					   String transit="";
					   if(bean.getTransit()!=null) {
						   transit=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getTransit(), "0.000")   ;
					   }
					   cell = new PdfPCell(new Phrase( transit ,GeneratePdf.Bold_8_times_roman));
					   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   //cell.setRowspan(bean.getRowSpanDetFacture());
				       cell.setPadding(3f);
				       cell.setPaddingBottom(5f);
					   cell.setBackgroundColor(BaseColor.WHITE);
					   if(i%2==0)
					   cell.setBackgroundColor(GeneratePdf.colorLigne);
					   table.addCell(cell);
					   
					   
					
					   
					   String maiOevre="";
					   if(bean.getMdOuevre()!=null) {
						   maiOevre=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getMdOuevre(), "0.000")   ;
					   }
					   cell = new PdfPCell(new Phrase( maiOevre ,GeneratePdf.Bold_8_times_roman));
					   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   //cell.setRowspan(bean.getRowSpanDetFacture());
				       cell.setPadding(3f);
				       cell.setPaddingBottom(5f);
					   cell.setBackgroundColor(BaseColor.WHITE);
					   if(i%2==0)
					   cell.setBackgroundColor(GeneratePdf.colorLigne);
					   table.addCell(cell);
					   
					   String Emballage="";
					   if(bean.getEmbal()!=null) {
						   Emballage=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getEmbal(), "0.000")   ;
					   }
					   cell = new PdfPCell(new Phrase( Emballage ,GeneratePdf.Bold_8_times_roman));
					   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   //cell.setRowspan(bean.getRowSpanDetFacture());
				       cell.setPadding(3f);
				       cell.setPaddingBottom(5f);
					   cell.setBackgroundColor(BaseColor.WHITE);
					   if(i%2==0)
					   cell.setBackgroundColor(GeneratePdf.colorLigne);
					   table.addCell(cell);
					   
				   
				   String mntStockGace="";
				   
				   if(bean.getScot_glace()!=null) {
					   mntStockGace=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getScot_glace(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( mntStockGace ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   //cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				  
				   
				   
				   String douanne="";
				   if(bean.getDouane()!=null) {
					   douanne=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getDouane(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( douanne ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   //cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   String chamCom="";
				   if(bean.getChambreCom()!=null) {
					   chamCom=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getChambreCom(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( chamCom ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   //cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   String transarean="";
				   if(bean.getTransAe()!=null) {
					   transarean=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getTransAe(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( transarean ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   //cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   String total="";
				   if(bean.getTotal()!=null) {
					   total=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getTotal(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( total ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   //cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
			    
 
		        }
			    
			    


			    	PdfPCell cell = new PdfPCell(new Phrase( "Total",GeneratePdf.Bold_8_times_roman));
			        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        cell.setColspan(3);
			        cell.setPadding(3f);
			        cell.setPaddingBottom(5f);
			        cell.setBackgroundColor(GeneratePdf.colorLigne);
			        table.addCell(cell);
			    	
			          
				    
					
			    	cell = new PdfPCell(new Phrase(  ProcessFormatNbr.convertDoubleToIntString((Double) hashMapTotal.get("totQteFish"))  ,GeneratePdf.Bold_8_times_roman));
				    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        cell.setPadding(3f);
			        cell.setPaddingBottom(5f);
				    cell.setBackgroundColor(GeneratePdf.colorLigne);
				    table.addCell(cell);

				        
				   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totAchatFish"), "0.000")     ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   cell = new PdfPCell(new Phrase(  ProcessFormatNbr.convertDoubleToIntString((Double) hashMapTotal.get("totQteBox"))   ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				  
				   cell = new PdfPCell(new Phrase(  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totAchatBox"), "0.000")   ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   

				   
				   
	                  
				   cell = new PdfPCell(new Phrase(  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totTransport"), "0.000")   ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				  
				   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totTransit"), "0.000")   ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				  
				   cell = new PdfPCell(new Phrase(  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totMoeuvre"), "0.000")   ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   

				   
				   
				    
				   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totEmbal"), "0.000")  ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   
				   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totGlassScot"), "0.000")    ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				  
			     
			     
			       
				   
				   cell = new PdfPCell(new Phrase(  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totDouane"), "0.000")   ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   
				   cell = new PdfPCell(new Phrase(  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totChambreCom"), "0.000")   ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   
				   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totTranAea"), "0.000")   ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				  
				   cell = new PdfPCell(new Phrase(  ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totTotal"), "0.000")    ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
		
			    document.add(table);
			 
			
		}
		

}
