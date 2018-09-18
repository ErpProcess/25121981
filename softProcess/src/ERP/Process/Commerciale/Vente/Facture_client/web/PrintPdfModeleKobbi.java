package ERP.Process.Commerciale.Vente.Facture_client.web;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.service.EntiteAdminService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.GeneratePdf;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf.TableHeaderNormale;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

import com.itextpdf.text.BaseColor;
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
	        	   Entite_etat_commercialeBean beanSearBean = (Entite_etat_commercialeBean) list_mod_reglement.get(i);
				
	        	   String key = "";
	        	   if(denBean.getMode().getData_id().equals(beanSearBean.getData_id())) {
	        		   key = "X";
	        	   }
				   cell = new PdfPCell(new Phrase(key,GeneratePdf.Bold_10_times_roman));
		           cell.setColspan(10);
		           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		           cell.setBorder(cell.NO_BORDER);
		           table_des_tva.addCell(cell);
		           
		           cell = new PdfPCell(new Phrase(beanSearBean.getData_libelle(),GeneratePdf.Bold_10_times_roman));
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
	        	    if(i==2) {titre="IBAN:"; value=denBean.getCpt_bank().getCptbanribrib();} 
	        	    if(i==3) {titre="BIC:";  value=denBean.getCpt_bank().getCptbanribrs();}
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
			    
			PdfPCell cell = new PdfPCell(new Phrase("Invoice N#",GeneratePdf.FONT_12_bold));
		    cell.setColspan(15);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase(" "+denBean.getFact_ref_id(),GeneratePdf.FONT_12_normal));
		    cell.setColspan(25);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    
		    cell = new PdfPCell(new Phrase("",GeneratePdf.FONT_12_bold));
		    cell.setColspan(18);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    
		    cell = new PdfPCell(new Phrase("" ,GeneratePdf.FONT_12_normal));
		    cell.setColspan(42);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		     
		   
		    
		    cell = new PdfPCell(new Phrase("Place,Date  Tunis",GeneratePdf.FONT_12_bold));
		    cell.setColspan(25);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    
		    Date date = sdf.parse(ProcessDate.getCurrentTimeStamp(denBean.getFact_date()));

		    String month_name = month_date.format(date);
		    
		    cell = new PdfPCell(new Phrase(","+month_name ,GeneratePdf.FONT_12_normal));
		    cell.setColspan(75);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader.addCell(cell);
		    
		    PdfPTable tableTopHeader2 = new PdfPTable(100);
		    tableTopHeader2.setWidthPercentage(85);
		    
		    cell = new PdfPCell(new Phrase("  ",GeneratePdf.FONT_12_normal));
		    cell.setColspan(51);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		   
		    
		    Font FONT_12_bold = new Font(Font.getFamily("TIMES_ROMAN"), 12, Font.BOLD|  Font.UNDERLINE);
		    cell = new PdfPCell(new Phrase("Billing To",FONT_12_bold));
		    cell.setColspan(49);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("  ",GeneratePdf.FONT_12_normal));
		    cell.setColspan(51);
		    cell.setFixedHeight(20f);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		     
		    
		    cell = new PdfPCell(new Phrase(denBean.getClient().getClt_lib(),GeneratePdf.FONT_12_normal));
		    cell.setColspan(49);

		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    
		    cell = new PdfPCell(new Phrase("  " ,GeneratePdf.FONT_12_normal));
		    cell.setColspan(51);

		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		     
		    
		    cell = new PdfPCell(new Phrase(denBean.getClient().getClt_adr(),GeneratePdf.FONT_12_normal));
		    cell.setColspan(49);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorder(cell.NO_BORDER);
		    tableTopHeader2.addCell(cell);
		    
		    
		    cell = new PdfPCell(new Phrase( "  "  ,GeneratePdf.FONT_12_normal));
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
		
		
	  private   void doWrite_Data_Table(Facture_clientBean    denBean, List lisData, Document document,int poucentage,String[][] mapFieldBean) throws Exception, SecurityException {
		  
		    PdfPTable table = new PdfPTable(mapFieldBean.length);
			String pattern ="0.000";
	    	if( denBean.getDevise().getDev_id().intValue()==191  ||  denBean.getDevise().getDev_id().intValue()==192   ){
					pattern ="0.00";
			} 
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
					        if(j==mapFieldBean.length-3  ){
					        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.Bold_10_times_roman));
						        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						        cell.setPaddingBottom(5);
						        cell.setBorderWidth(0.1f);
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
						        table.addCell(cell);
						        continue;
					        }
					        if(j==mapFieldBean.length-1 || j==mapFieldBean.length-2){
					        	Double elm=Double.valueOf(String.valueOf(obj));
					        	cell = new PdfPCell(new Phrase(ProcessFormatNbr.FormatDouble_To_String_PatternChiffre(elm,pattern),GeneratePdf.Bold_10_times_roman));
						        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						        cell.setPaddingBottom(5);
						        cell.setBorderWidth(0.1f);
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
						        
					        }else{
					        	cell = new PdfPCell(new Phrase(String.valueOf(obj),GeneratePdf.Bold_10_times_roman));
						        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						        cell.setPaddingBottom(5);
						        cell.setBorderWidth(0.1f);
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(GeneratePdf.colorLigne);
					        }
					        table.addCell(cell);
					    }   
				   
	         }
			  
			  
	         
 
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
	        	   }else {
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

}
