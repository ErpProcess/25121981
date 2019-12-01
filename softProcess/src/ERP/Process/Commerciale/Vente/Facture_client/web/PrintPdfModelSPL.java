package ERP.Process.Commerciale.Vente.Facture_client.web;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.json.JSONArray;
import org.json.JSONObject;
 
import org.springframework.web.servlet.ModelAndView;

 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.TableHeaderNormale;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.web.configDevelopementActionManager;
import groovy.lang.GroovyShell;
import jdk.nashorn.internal.parser.JSONParser;


public class PrintPdfModelSPL   extends GenericWeb  {
	
	public static void main(String[] args) {
		 
		GroovyShell shell = new GroovyShell();
		
		Object value = shell.evaluate("for (x=0; x<5; x++){ System.out.println(x);}; return x");
		System.out.println(value);
	 
	}
	
	public    ModelAndView doPrintPDF_detaille() throws Exception   {
		 
		List   lisData=  (List) getObjectValueModel(Facture_clientTemplate.LIST_DATA_DET_FACT) ;
		Facture_clientBean    denBean= (Facture_clientBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+Facture_clientTemplate.LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
	    FileOutputStream fs = new FileOutputStream(file);
	    String [][] mapfieldBeanDetaille  = 
	    		new String[][]{
 
	    		          { "pk.fkcode_barre.designation_libelle", "100" },
	    		          { "quantite", "20" },
	    		          { "tarif_unit_vente", "30" },
	    		          { "montant_ht_vente", "50" }
	    		          //{ "montant_tva_vente", "50" }
	    		          };
	    
		try {
			 
			
			Document document=GeneratePdf.doGenerateDocumentFormat();
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	        doWriteHeaderDocument_PDF_NOT_PASY(document,fs,mapfieldBeanDetaille,bSession);
	        doWriteEntete(document,denBean); 
	        doWrite_Header_ContentTable(document,96,mapfieldBeanDetaille);
	        doWrite_Data_Table (denBean,lisData,document,96,mapfieldBeanDetaille);
	        doWrite_Tva_Total_Piece(lisData,document);  ;  
	        
	        Facture_clientBean  reBean= (Facture_clientBean) getObjectValueModel(Facture_clientTemplate.BEAN_TOTAL_FACTURE_CLIENT);
	        String affich_mont =ProcessFormatNbr.convertNumberToLetterDT( reBean.getTotal_facture() );
	        
	        PdfPTable tabletitle = new PdfPTable(100);
		    PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.Normal_10_times_roman));
	        cell.setColspan(100);
	        cell.setFixedHeight(10f);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Arrêtée la présente facture à la somme de :",GeneratePdf.Normal_10_times_roman));
	        cell.setColspan(100);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase(affich_mont,GeneratePdf.Bold_10_times_roman));
	        cell.setColspan(100);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase("",GeneratePdf.Normal_10_times_roman));
	        cell.setColspan(100);
	        cell.setFixedHeight(10f);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        
	        
	        
	        
	        document.add(tabletitle);
	        
	       
	        document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write(Facture_clientTemplate.LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
		} catch (Exception e) {
			displayException((Exception) e);
		} 
	return null;

     }
	
	
	public    ModelAndView doPrintRetenuSource(Facture_clientBean fBean, double pourcentage ) throws Exception   {
		 
		 
	 
		File file = new File(getRequest().getRealPath("/")+"/temp/"+Facture_clientTemplate.LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
	    FileOutputStream fs = new FileOutputStream(file);
	    String [][] mapfieldBeanDetaille  = 
	    		new String[][]{
 
	    		          { "pk.fkcode_barre.designation_libelle", "100" },
	    		          { "quantite", "20" },
	    		          { "tarif_unit_vente", "30" },
	    		          { "montant_ht_vente", "50" }
	    		          //{ "montant_tva_vente", "50" }
	    		          };
	    
		try {
			 
			
			Document document=GeneratePdf.doGenerateDocumentFormat();
	        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
	        doWriteHeaderDocumentRetenuSource(document,fs,mapfieldBeanDetaille,bSession,fBean,pourcentage);
	        String affich_mont ="Sfax le "+ ProcessDate.getStringFormatDate(fBean.getFact_date()) ;
	        
	        PdfPTable tabletitle = new PdfPTable(100);
		    PdfPCell cell = new PdfPCell(new Phrase("",GeneratePdf.Normal_10_times_roman));
	        cell.setColspan(100);
	        cell.setFixedHeight(10f);
	       
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("je soussigné,certifie exacts les renseignements figurant sur le présent certificat et m'expose\n\r aux sanctions prévues par la loi pour toute inexactitude.",GeneratePdf.Bold_10_times_roman));
	        cell.setColspan(100);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setPaddingLeft(-5f);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase(affich_mont,GeneratePdf.Bold_10_times_roman));
	        cell.setColspan(100);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        cell = new PdfPCell(new Phrase("",GeneratePdf.Normal_10_times_roman));
	        cell.setColspan(100);
	        cell.setFixedHeight(10f);
	        cell.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        
	        
	        
	        
	        
	        
	        document.add(tabletitle);
	        
	       
	        document.close();
			getResponse().setContentType("text");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setStatus(200);
			getResponse().getWriter().write(Facture_clientTemplate.LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
		} catch (Exception e) {
			displayException((Exception) e);
		} 
	return null;

     }
	
	public  void doWriteHeaderDocumentRetenuSource(Document document, 
			FileOutputStream fs ,String [][] mapFieldBean,BeanSession bSession,
			Facture_clientBean fBean, double pourcentage) throws Exception {
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
        tableheader.setWidthPercentage(100);
		
	     
	    JSONObject doc = configDevelopementActionManager.doLoadingConfigPrintDocumentRetenue();		
	    cellheder = new PdfPCell(new Phrase( doc.getString("entete").toUpperCase()  ,new Font(Font.getFamily("TIMES_ROMAN"), 9,  Font.BOLD)));
	    cellheder.setColspan(100);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setPaddingLeft(28f);
	    cellheder.setPaddingBottom(5f);
	    cellheder.setPaddingTop(31f);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    
	    cellheder = new PdfPCell(new Phrase("     CIRTIFICAT DE RETENUE D'IMPOT \n\r     SUR LE REVENU OU D'IMPOT SUR LES SOCIETES".toUpperCase(),new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
	    cellheder.setColspan(100);
	    cellheder.setPaddingBottom(10f);
	    cellheder.setPaddingLeft(28f);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    
	    cellheder = new PdfPCell(new Phrase(" ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL)));
	    cellheder.setColspan(60);
	    cellheder.setPaddingBottom(10f);
	    cellheder.setPaddingLeft(28f);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    
	    cellheder = new PdfPCell(new Phrase(" Retenue effectuée le "+ ProcessDate.getStringFormatDate(fBean.getFact_date())  ,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
	    cellheder.setColspan(40);
	    cellheder.setPaddingBottom(10f);
	    cellheder.setPaddingLeft(28f);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	     
	    cellheder = new PdfPCell(new Phrase(" A- Dénomination de l'organisme payeur : ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
	    cellheder.setColspan(100);
	    cellheder.setPaddingTop(20f);
	    cellheder.setPaddingBottom(5f);
	    cellheder.setPaddingLeft(28f);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    
	    PdfPTable tableFirst = new PdfPTable(100);
        PdfPCell cellFirst;
        tableFirst.setWidthPercentage(90);
        
        cellFirst = new PdfPCell(new Phrase(" Identification Fiscal: ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(100);
        cellFirst.setPaddingLeft(2f);
        cellFirst.setPaddingBottom(5f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(" M fiscal ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(40);
        cellFirst.setPaddingLeft(2f);
        cellFirst.setPaddingBottom(5f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.RIGHT+cellFirst.LEFT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(" TVA ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(2f);
        cellFirst.setPaddingBottom(5f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.RIGHT+cellFirst.LEFT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(" C.C ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(2f);
        cellFirst.setPaddingBottom(5f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.RIGHT+cellFirst.LEFT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(" Nbre Etab secondaire ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(30);
        cellFirst.setPaddingLeft(2f);
        cellFirst.setPaddingBottom(5f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.RIGHT+cellFirst.LEFT);
        tableFirst.addCell(cellFirst);
        
        
        cellFirst = new PdfPCell(new Phrase(fBean.getClient().getClt_obs()
        		.replaceAll(" ", "").replaceAll("/", "").replaceAll("", "             ").trim()
        		,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(100);
        cellFirst.setPaddingLeft(2f);
        cellFirst.setPaddingBottom(5f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
   
        
     
        
        
        cellFirst = new PdfPCell(new Phrase(" Nom,prénom ou raison sociale: "+fBean.getClient().getClt_lib()
        		+"\n\r"+" Adresse:"+ fBean.getClient().getClt_adr(),new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(100);
        cellFirst.setPaddingLeft(5f);
        cellFirst.setPaddingTop(20f);
        cellFirst.setPaddingBottom(30f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase("B - RETENUES EFFECTUEES SUR :\n\r"+  
        		"FACTURE N° : "+fBean.getFact_clt_id(),new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(100);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(10f);
        cellFirst.setPaddingBottom(30f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellFirst.setBorder(cellFirst.NO_BORDER);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase("",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(55);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.NO_BORDER);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase("MONTANT",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase("RETENUE",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase("A PAYER",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase("Retenue sur marché 1,5%",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(55);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(fBean.getTotal_facture()) ,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(
        		ProcessFormatNbr.FormatDouble_To_String_Troischiffre(ProcessNumber.Pourcentage(fBean.getTotal_facture(), pourcentage) )
        		,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(

        		
        		ProcessFormatNbr.FormatDouble_To_String_Troischiffre(
        				
        				ProcessNumber.SOUSTRACTION(fBean.getTotal_facture(), ProcessNumber.Pourcentage(fBean.getTotal_facture(), pourcentage))
        				 
        				
        				)
        		
        		,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase("Total GENERAL",new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.BOLD)));
        cellFirst.setColspan(55);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_Troischiffre(fBean.getTotal_facture()) ,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(
        		ProcessFormatNbr.FormatDouble_To_String_Troischiffre(ProcessNumber.Pourcentage(fBean.getTotal_facture(), pourcentage) )
        		,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
        cellFirst = new PdfPCell(new Phrase(

        		
        		ProcessFormatNbr.FormatDouble_To_String_Troischiffre(
        				
        				ProcessNumber.SOUSTRACTION(fBean.getTotal_facture(), ProcessNumber.Pourcentage(fBean.getTotal_facture(), pourcentage))
        				 
        				
        				)
        		
        		,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellFirst.setColspan(15);
        cellFirst.setPaddingLeft(1f);
        cellFirst.setPaddingTop(2f);
        cellFirst.setPaddingBottom(2f);
        cellFirst.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellFirst.setBorder(cellFirst.TOP+cellFirst.BOTTOM+cellFirst.LEFT+cellFirst.RIGHT);
        tableFirst.addCell(cellFirst);
        
   
	    PdfPTable tableBeneficaire = new PdfPTable(100);
        PdfPCell cellBeneficaire; 
        tableBeneficaire.setWidthPercentage(90);
        
        cellBeneficaire = new PdfPCell(new Phrase("C - BENEFICIAIRE: ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellBeneficaire.setColspan(100);
        cellBeneficaire.setPaddingLeft(2f);
        cellBeneficaire.setPaddingBottom(5f);
        cellBeneficaire.setPaddingTop(35f);
        cellBeneficaire.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellBeneficaire.setBorder(cellBeneficaire.NO_BORDER  );
        tableBeneficaire.addCell(cellBeneficaire);
        
        cellBeneficaire = new PdfPCell(new Phrase(" M fiscal ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellBeneficaire.setColspan(40);
        cellBeneficaire.setPaddingLeft(2f);
        cellBeneficaire.setPaddingBottom(5f);
        cellBeneficaire.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellBeneficaire.setBorder(cellBeneficaire.TOP+cellBeneficaire.BOTTOM+cellBeneficaire.LEFT+cellBeneficaire.RIGHT);
        tableBeneficaire.addCell(cellBeneficaire);
        
        cellBeneficaire = new PdfPCell(new Phrase(" TVA ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellBeneficaire.setColspan(15);
        cellBeneficaire.setPaddingLeft(2f);
        cellBeneficaire.setPaddingBottom(5f);
        cellBeneficaire.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellBeneficaire.setBorder(cellBeneficaire.TOP+cellBeneficaire.BOTTOM+cellBeneficaire.LEFT+cellBeneficaire.RIGHT);
        tableBeneficaire.addCell(cellBeneficaire);
        
        cellBeneficaire = new PdfPCell(new Phrase(" C.C ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellBeneficaire.setColspan(15);
        cellBeneficaire.setPaddingLeft(2f);
        cellBeneficaire.setPaddingBottom(5f);
        cellBeneficaire.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellBeneficaire.setBorder(cellBeneficaire.TOP+cellBeneficaire.BOTTOM+cellBeneficaire.LEFT+cellBeneficaire.RIGHT);
        tableBeneficaire.addCell(cellBeneficaire);
        
        cellBeneficaire = new PdfPCell(new Phrase(" Nbre Etab secondaire ",new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellBeneficaire.setColspan(30);
        cellBeneficaire.setPaddingLeft(2f);
        cellBeneficaire.setPaddingBottom(5f);
        cellBeneficaire.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellBeneficaire.setBorder(cellBeneficaire.TOP+cellBeneficaire.BOTTOM+cellBeneficaire.LEFT+cellBeneficaire.RIGHT);
        tableBeneficaire.addCell(cellBeneficaire);
        
        
        cellBeneficaire = new PdfPCell(new Phrase(bs.getSociete().getMatricule_fiscale()
        		.replaceAll(" ", "").replaceAll("/", "").replaceAll("", "             ").trim(),new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellBeneficaire.setColspan(100);
        cellBeneficaire.setPaddingLeft(2f);
        cellBeneficaire.setPaddingBottom(5f);
        cellBeneficaire.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellBeneficaire.setBorder(cellBeneficaire.TOP+cellBeneficaire.BOTTOM+cellBeneficaire.LEFT+cellBeneficaire.RIGHT);
        tableBeneficaire.addCell(cellBeneficaire);
        
       
        
       
       
        
        
        cellBeneficaire = new PdfPCell(new Phrase(" Nom,prénom ou raison sociale: "+bs.getSociete().getSoc_lib()
        		+"\n\r"+" Adresse:"+ bs.getSociete().getAdresse(),new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD)));
        cellBeneficaire.setColspan(100);
        cellBeneficaire.setPaddingLeft(5f);
        cellBeneficaire.setPaddingTop(20f);
        cellBeneficaire.setPaddingBottom(30f);
        cellBeneficaire.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellBeneficaire.setBorder(cellBeneficaire.TOP+cellBeneficaire.BOTTOM+cellBeneficaire.LEFT+cellBeneficaire.RIGHT);
        tableBeneficaire.addCell(cellBeneficaire);
	    document.add(tableheader);
	    document.add(tableFirst);
	    document.add(tableBeneficaire);
	    
	
}
	
	
	public  void doWriteHeaderDocument_PDF_NOT_PASY(Document document, FileOutputStream fs ,String [][] mapFieldBean,BeanSession bSession) throws Exception {
	    PdfWriter writer = PdfWriter.getInstance(document,  fs);
	    TableHeaderNormale event = new TableHeaderNormale(bSession);
        //writer.setPageEvent(event);
        Facture_clientBean    denBean= (Facture_clientBean) getObjectValueModel(FORM_BEAN) ;
       
        BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
        
        document.addCreator(bs.getPack_libelle());
	    document.addAuthor(bs.getMod_libelle());
	    document.addTitle(bs.getFct_libelle()+"/"+bs.getSousmod_libelle_title());
	    document.open();
        
        PdfPTable tableheader = new PdfPTable(100);
        PdfPCell cellheder;
        tableheader.setWidthPercentage(100);
        
        String relativeWebPath = "/img/logoGen.jpg";
        String absoluteDiskPath =  getRequest().getServletContext().getRealPath(relativeWebPath);
        Image companyLogo =null;
        if(bs.getSociete().getMyFile()!=null) {
        	 companyLogo = Image.getInstance(bs.getSociete().getMyFile().getFile_byte());
        }else {
        	 companyLogo = Image.getInstance(absoluteDiskPath);
        }
        	
         
		companyLogo.scalePercent(60); 
		cellheder = new PdfPCell(); 
		cellheder.setBorder(3);
		cellheder.addElement(new Chunk(companyLogo, 10, -77));
		cellheder.setPaddingRight(50f);
		cellheder.setColspan(10);
		cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellheder.setBorder(cellheder.NO_BORDER);
		tableheader.addCell(cellheder);

		
	    cellheder = new PdfPCell(new Phrase("",GeneratePdf.Normal_10_times_roman));
	    cellheder.setColspan(4);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setPaddingLeft(20f);
	    cellheder.setPaddingBottom(40f);
	    cellheder.setPaddingTop(15f);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    JSONObject doc = configDevelopementActionManager.doLoadingConfigPrintDocument();		
	    cellheder = new PdfPCell(new Phrase( doc.getString("entete").toUpperCase()  ,new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL)));
	    cellheder.setColspan(86);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setPaddingLeft(28f);
	    cellheder.setPaddingBottom(5f);
	    cellheder.setPaddingTop(31f);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    
	    cellheder = new PdfPCell(new Phrase("",GeneratePdf.Normal_10_times_roman));
	    cellheder.setColspan(19);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
	    
	    cellheder = new PdfPCell(new Phrase("Virement bancaire:".toUpperCase(),new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL)));
	    cellheder.setColspan(17);
	    cellheder.setPaddingBottom(10f);
	    cellheder.setPaddingLeft(-1f);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
        
        
	    cellheder = new PdfPCell(new Phrase(denBean.getCpt_bank().getCptbanribrs()+" / "+denBean.getCpt_bank().getCptbanribrib(),GeneratePdf.Normal_10_times_roman));
	    cellheder.setColspan(64);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setPaddingBottom(10f);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    tableheader.addCell(cellheder);
        
	    document.add(tableheader);
	
}
	
	public   void doWriteEntete(Document document, Facture_clientBean denBean) throws Exception {
		PdfPTable tableTopHeader = new PdfPTable(100);
		tableTopHeader.setWidthPercentage(96);
		 
		    
		PdfPCell cell = new PdfPCell(new Phrase(" Facture N° ",GeneratePdf.Bold_10_times_roman));
	    cell.setColspan(13);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(" : "+denBean.getFact_clt_id(),GeneratePdf.Bold_10_times_roman));
	    cell.setColspan(27);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    cell = new PdfPCell(new Phrase(" Client ",GeneratePdf.Bold_10_times_roman));
	    cell.setColspan(18);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.TOP+cell.LEFT);
	    tableTopHeader.addCell(cell);
	    
	    
	    cell = new PdfPCell(new Phrase(" : "+denBean.getClient().getClt_lib() ,GeneratePdf.Normal_10_times_roman));
	    cell.setColspan(42);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.TOP+cell.RIGHT);
	    tableTopHeader.addCell(cell);
	    
	     
	    
	    
	    cell = new PdfPCell(new Phrase(" Date ",GeneratePdf.Bold_10_times_roman));
	    cell.setColspan(13);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    Date  dateFact =denBean.getFact_date_edition()!=null?denBean.getFact_date_edition():denBean.getFact_date();
	    
	    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(dateFact) ,GeneratePdf.Normal_10_times_roman));
	    cell.setColspan(27);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    
	    cell = new PdfPCell(new Phrase(" Adresse ",GeneratePdf.Bold_10_times_roman));
	    cell.setColspan(18);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.LEFT);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_adr() ,GeneratePdf.Normal_10_times_roman));
	    cell.setColspan(42);
	    cell.setFixedHeight(40f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.RIGHT);
	    tableTopHeader.addCell(cell);
	    
	    
	    
	    cell = new PdfPCell(new Phrase("   ",GeneratePdf.Bold_10_times_roman));
	    cell.setColspan(12);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(  "   " ,GeneratePdf.Normal_10_times_roman));
	    cell.setColspan(28);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    
	    cell = new PdfPCell(new Phrase(" Matricule fiscal ",GeneratePdf.Bold_10_times_roman));
	    cell.setColspan(18);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.LEFT+cell.BOTTOM);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_obs() ,GeneratePdf.Normal_10_times_roman));
	    cell.setColspan(42);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.RIGHT +cell.BOTTOM);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.Normal_10_times_roman));
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
		    
		    table.addCell(cell);
		}
      document.add(table);
}
	
	
  public   void doWrite_Data_Table(Facture_clientBean    denBean, List lisData, Document document,int poucentage,String[][] mapFieldBean) throws Exception, SecurityException {
	  
	    PdfPTable table = new PdfPTable(mapFieldBean.length);
		String pattern ="0.000";
    	if( denBean.getDevise().getDev_id().intValue()==191  ||  denBean.getDevise().getDev_id().intValue()==192   ){
				pattern ="0.00";
		} 
	  
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
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        table.addCell(cell);
					        continue;
				        }
				        if(j==mapFieldBean.length-1 || j==mapFieldBean.length-2){
				        	Double elm=Double.valueOf(String.valueOf(obj));
				        	cell = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(elm,pattern),GeneratePdf.REDFONT));
					        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					        cell.setPaddingBottom(5);
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        
				        }else{
				        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.REDFONT));
					        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					        cell.setPaddingBottom(5);
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
			        cell.setFixedHeight(toul_contenu_tab);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        cell.setBorder(cell.LEFT+cell.RIGHT);
		            table.addCell(cell);
		    }   
		  document.add(table);
	}
	public void doWriteEntete(Document document, CommandeclientBean   denBean) throws Exception {
	PdfPTable tableTopHeader = new PdfPTable(100);
	tableTopHeader.setWidthPercentage(96);
	 
	    
	PdfPCell cell = new PdfPCell(new Phrase("Facture Pro Forma N°",GeneratePdf.Bold_10_times_roman));
    cell.setColspan(24);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(" : "+denBean.getCmd_id(),GeneratePdf.Normal_10_times_roman));
    cell.setColspan(23);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase(" Client ",GeneratePdf.Bold_10_times_roman));
    cell.setColspan(13);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.LEFT);
    tableTopHeader.addCell(cell);
    
    String getClient=denBean.getCmd_libelle().equals("")?denBean.getClient().getClt_lib():denBean.getCmd_libelle();
    cell = new PdfPCell(new Phrase(" : "+getClient ,GeneratePdf.Normal_10_times_roman));
    cell.setColspan(40);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.TOP+cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    
     
    
    
    cell = new PdfPCell(new Phrase("Date",GeneratePdf.Bold_10_times_roman));
    cell.setColspan(24);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(denBean.getCmd_date()) ,GeneratePdf.Normal_10_times_roman));
    cell.setColspan(23);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase(" Adresse ",GeneratePdf.Bold_10_times_roman));
    cell.setColspan(13);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_adr() ,GeneratePdf.Normal_10_times_roman));
    cell.setColspan(40);
    cell.setFixedHeight(40f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT);
    tableTopHeader.addCell(cell);
    
    
    
    cell = new PdfPCell(new Phrase("   ",GeneratePdf.Bold_10_times_roman));
    cell.setColspan(47);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.NO_BORDER);
    tableTopHeader.addCell(cell);
    
     
    
    
    
    cell = new PdfPCell(new Phrase(" Matricule fiscal :",GeneratePdf.Bold_10_times_roman));
    cell.setColspan(18);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.LEFT+cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    cell = new PdfPCell(new Phrase( "   "+denBean.getClient().getClt_obs() ,GeneratePdf.Normal_10_times_roman));
    cell.setColspan(35);
    cell.setFixedHeight(20f);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(cell.RIGHT +cell.BOTTOM);
    tableTopHeader.addCell(cell);
    
    
    
    
    
    
    
    cell = new PdfPCell(new Phrase("",GeneratePdf.Normal_10_times_roman));
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
           PdfPCell cell = new PdfPCell(new Phrase( "" ,GeneratePdf.Normal_10_times_roman));
           cell.setColspan(100);
           cell.setFixedHeight(10f);
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           cell.setBorder(cell.TOP);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Taux",GeneratePdf.Bold_10_times_roman));
           cell.setColspan(10);
          // cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Base",GeneratePdf.Bold_10_times_roman));
           cell.setColspan(21);
           //cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase("Montant",GeneratePdf.Bold_10_times_roman));
           cell.setColspan(23);
           //cell.setFixedHeight(20f);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
           table_des_tva.addCell(cell);
           
           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.Normal_10_times_roman));
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
			
			   cell = new PdfPCell(new Phrase(key,GeneratePdf.Bold_10_times_roman));
	           cell.setColspan(10);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[0],GeneratePdf.Bold_10_times_roman));
	           cell.setColspan(21);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase(ligne[1],GeneratePdf.Bold_10_times_roman));
	           cell.setColspan(23);
	           //cell.setFixedHeight(20f);
	           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	           cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
	           table_des_tva.addCell(cell);
	           
	           cell = new PdfPCell(new Phrase( "" ,GeneratePdf.Normal_10_times_roman));
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
           
            
           int  init=6;
           int size=mapTvaImpression.size();
           int space=size*20;
           space=space+init;
           space=space*-1;
           
           /****************************************************************************************************************/
           
           
           table_des_tva.setSpacingAfter(Float.valueOf(String.valueOf(space)));
           for (int i = 0; i < 6; ++i) {
        	    JSONObject rec = list_total.getJSONObject(i);
        	    String titre = rec.getString("value1"); 
        	    String value = rec.getString("value2");
        	    
        	    cell = new PdfPCell(new Phrase("",GeneratePdf.Bold_10_times_roman));
                cell.setColspan(55);
                cell.setFixedHeight(17f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.NO_BORDER);
                table_total.addCell(cell);
                
                
                cell = new PdfPCell(new Phrase(titre,GeneratePdf.Bold_10_times_roman));
                cell.setColspan(18);
                cell.setFixedHeight(17f);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(cell.TOP+cell.BOTTOM+cell.LEFT+cell.RIGHT);
                table_total.addCell(cell);
                
                cell = new PdfPCell(new Phrase(value ,GeneratePdf.Normal_10_times_roman));
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

}
