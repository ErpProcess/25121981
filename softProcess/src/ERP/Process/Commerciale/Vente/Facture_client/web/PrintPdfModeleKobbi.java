package ERP.Process.Commerciale.Vente.Facture_client.web;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
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
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

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

public class PrintPdfModeleKobbi  extends GenericWeb {

	public PrintPdfModeleKobbi() {
		super();
	}
//	
//	ALTER TABLE vente.client
//	  ADD COLUMN clt_exonorer boolean DEFAULT false;
//	  ALTER TABLE vente.deriver_operation_vente
//	  RENAME TO deriver_det_op_vente;
//	  
//	CREATE TABLE vente.deriver_det_fact_vente(
//	  drv_det_fact_id serial NOT NULL,
//	  ar_id character varying(15) NOT NULL,
//	  code_barre character varying(250) NOT NULL,
//	  etab_id character varying(15) NOT NULL,
//	  soc_id character varying(15) NOT NULL,
//	  quantite double precision,
//	  tarif_unit_vente character varying(50),
//	  date_cre date,
//	  usr_cre character varying(5),
//	  date_mod date,
//	  usr_mod character varying(5),
//	  montant_tva_vente double precision,
//	  montant_ht_vente double precision,
//	  drv_coef double precision,
//	  drv_oper character varying(1),
//	  CONSTRAINT "pk -deriver_det_fact_vente" PRIMARY KEY (drv_det_fact_id),
//	  CONSTRAINT fk_code_barre_det_operation_vente FOREIGN KEY (code_barre, ar_id, etab_id, soc_id)
//	      REFERENCES gestioncommerciale.code_barre (code_barre, ar_id, etab_id, soc_id) MATCH SIMPLE
//	      ON UPDATE NO ACTION ON DELETE NO ACTION
	
	
	
	public void printfacture( ) throws Exception{
		
		try {
		List   lisData=  (List) getObjectValueModel(Facture_clientTemplate.LIST_DATA_DET_FACT) ;
		Facture_clientBean    denBean= (Facture_clientBean) getObjectValueModel(FORM_BEAN) ;
		File file = new File(getRequest().getRealPath("/")+"/temp/"+Facture_clientTemplate.LIST_DATA_DET_FACT+getRequest().getSession().getId()+".pdf");
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
	
	
	public void printEtatVenteExportKobbi( EditionVenteBean searchBean ) throws Exception{
		
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
	
	private  void doWriteHeaderDocumentEditionvente(Document document,int poucentage, FileOutputStream fs ,BeanSession bSession) throws Exception {
		 PdfWriter writer = PdfWriter.getInstance(document,  fs);
	        TableHeaderPaysage event = new TableHeaderPaysage(bSession);
	        writer.setPageEvent(event);
	        
	       
	        BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
	        
	        document.addCreator(bs.getPack_libelle());
		    document.addAuthor(bs.getMod_libelle());
		    document.addTitle(bs.getFct_libelle()+"/"+bs.getSousmod_libelle_title());
		    document.open();
	        
	        PdfPTable tableheader = new PdfPTable(100);
	        PdfPCell cellheder;
	        tableheader.setWidthPercentage(100);
	        Image companyLogo = Image.getInstance("c:/images/sigle_ibs.jpg");
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
		    
		    cell = new PdfPCell(new Phrase(" "+denBean.getFact_ref_id(),GeneratePdf.Normal_11_times_roman));
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
				new Phrase("KOBBI FISH Exports " + ProcessDate.getStringFormatDate(searchBean.getDate_debut()) + " - "
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
				new Phrase("ETAT DEPENSES PRODUITS FRAIS " + ProcessDate.getStringFormatDate(searchBean.getDate_debut()) + " - "
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
					String  key =dBean.getPk().getFactclient().getFact_ref_id()+"���"+dBean.getPk().getFactclient().getClient().getClt_lib();
					List listInvoice=(List) mapInvoiceClient.get(key);
					if(listInvoice==null) {listInvoice= new ArrayList();  }
					listInvoice.add(dBean);
					mapInvoiceClient.put(key, listInvoice);
				}
				
				Set mapInvoiceClientSet = mapInvoiceClient.keySet(); 
				
				for (Iterator iterInvo = mapInvoiceClientSet.iterator(); iterInvo.hasNext();) {
					String iClientnvoice = (String) iterInvo.next();
					String[] element=iClientnvoice.split("���");
					boolean isrowSpanDetailFacture=true;
					List listInvoiceClient=(List) mapInvoiceClient.get(iClientnvoice);
					Double totfacture= new Double(0);
					for (int i = 0; i < listInvoiceClient.size(); i++) {
					    Det_Fact_ClientBean dBean  =(Det_Fact_ClientBean) listInvoiceClient.get(i);	
					    EtatVenteProduit  etatBean  =  new EtatVenteProduit();
					    totfacture =ProcessNumber.addition(totfacture,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getMontant_ttc_vente(), devise.getChiffre_pattern())  );
					    
					    totGenfacture =ProcessNumber.addition(totGenfacture,   ProcessFormatNbr.FormatDouble_ParameterChiffre(dBean.getMontant_ttc_vente(), devise.getChiffre_pattern())  );
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
						
						etatBean.setTotal(ProcessFormatNbr.FormatDouble_ParameterChiffre(totfacture, devise.getChiffre_pattern())   );
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
			   
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.convertDoubleToIntString(bean.getNbrBox())  ,GeneratePdf.Bold_10_times_roman));
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
			   
			   cell = new PdfPCell(new Phrase( ProcessFormatNbr.convertDoubleToIntString(totGenNbrBox)  ,GeneratePdf.Bold_10_times_roman));
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
		 
		    
		PdfPCell cell = new PdfPCell(new Phrase("Facture Pro Forma N�",GeneratePdf.FONT_12_bold));
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
				String [][] MapfieldEtatDeDepense  = new String[][]{{ "date", "20" }, { "invoice", "20" },
					{ "client", "30" },{ "qt�", "15" },
				    { "AchatFish", "20" },{ "n/Box", "15" },
					{ "Poly", "20" },{ "trsprt", "15" },
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
			    	
			    	
			    	if(bean.isIsrowSpanDetFact()) {
			    	PdfPCell cell  = new PdfPCell(new Phrase( bean.getInvoice(),GeneratePdf.Bold_8_times_roman));
			        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        cell.setPadding(3f);
			        cell.setRowspan(bean.getRowSpanDetFacture());
			        cell.setPaddingBottom(5f);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        if(i%2==0)
			        cell.setBackgroundColor(GeneratePdf.colorLigne);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase( bean.getClient(),GeneratePdf.Bold_8_times_roman));
			        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        cell.setRowspan(bean.getRowSpanDetFacture());
			        cell.setPadding(3f);
			        cell.setPaddingBottom(5f);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        if(i%2==0)
			        cell.setBackgroundColor(GeneratePdf.colorLigne);
			        table.addCell(cell);
					
			    	cell = new PdfPCell(new Phrase(  ProcessFormatNbr.convertDoubleToIntString(bean.getQteFish()) ,GeneratePdf.Bold_8_times_roman));
				    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    cell.setRowspan(bean.getRowSpanDetFacture());
			        cell.setPadding(3f);
			        cell.setPaddingBottom(5f);
				    cell.setBackgroundColor(BaseColor.WHITE);
				    if(i%2==0)
				    cell.setBackgroundColor(GeneratePdf.colorLigne);
				    table.addCell(cell);

				        
				   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffrePrefixes (bean.getPrixtotFish(), bean.getDevise(), false, false)    ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   cell.setRowspan(bean.getRowSpanDetFacture());
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
				   cell.setRowspan(bean.getRowSpanDetFacture());
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
				   cell.setRowspan(bean.getRowSpanDetFacture());
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
				   cell.setRowspan(bean.getRowSpanDetFacture());
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
				   cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   String transit="";
				   if(bean.getTrsprt()!=null) {
					   transit=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getTransit(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( transit ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   String maiOevre="";
				   if(bean.getTrsprt()!=null) {
					   maiOevre=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getMdOuevre(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( maiOevre ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   cell.setRowspan(bean.getRowSpanDetFacture());
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
				   cell.setRowspan(bean.getRowSpanDetFacture());
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
				   cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
				   
				   String chamCom="";
				   if(bean.getDouane()!=null) {
					   chamCom=ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(bean.getChambreCom(), "0.000")   ;
				   }
				   cell = new PdfPCell(new Phrase( douanne ,GeneratePdf.Bold_8_times_roman));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   cell.setRowspan(bean.getRowSpanDetFacture());
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
				   cell.setRowspan(bean.getRowSpanDetFacture());
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
				   cell.setRowspan(bean.getRowSpanDetFacture());
			       cell.setPadding(3f);
			       cell.setPaddingBottom(5f);
				   cell.setBackgroundColor(BaseColor.WHITE);
				   if(i%2==0)
				   cell.setBackgroundColor(GeneratePdf.colorLigne);
				   table.addCell(cell);
				   
			     }
 
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
				   
				   
				   cell = new PdfPCell(new Phrase( ProcessFormatNbr.FormatDouble_To_String_PatternChiffre((Double) hashMapTotal.get("totGlassScot"), "0.000")    ,GeneratePdf.Bold_8_times_roman));
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