package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import ERP.Process.Commerciale.Vente.Devis.model.DevisBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;

import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileOutputStream;

import javax.swing.border.Border;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.objectweb.asm.tree.analysis.Interpreter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 




public class GeneratePdf extends  GenericWeb {


	 private BaseFont bfBold;
	 private BaseFont bf;
	 private int pageNumber = 0;
	 
//	 public static final Font CATFONT = new Font(Font.getFamily("TIMES_ROMAN"), 18, Font.BOLD);
//	  
//	 
//		
//	 public static final Font Normal_10_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.NORMAL);
//	 public static final Font Bold_10_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.BOLD);
//	 
//
//		
//	 public static final Font Normal_9_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL);
//	 public static final Font Bold_9_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD);
//	 
//	 public static final Font Bold_8_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 8, Font.BOLD);
//	 
//	 public static final Font Normal_11_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 11, Font.NORMAL);
//	 public static final Font Bold_11_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 11, Font.BOLD);
//	  
//	 /** Times Roman 16 Bold */
//	 public static final Font SUBFONT = new Font(Font.getFamily("TIMES_ROMAN"), 16, Font.BOLD);
//	 
//	 public static final Font TIMES_ROMAN_14_BOLD = new Font(Font.getFamily("TIMES_ROMAN"), 14, Font.BOLD);
//	 
//	 public static final Font FONT_12_bold = new Font(Font.getFamily("TIMES_ROMAN"), 12, Font.BOLD);
//	 
//	 
//	 
//	 public static final Font FONT_12_normal = new Font(Font.getFamily("TIMES_ROMAN"), 12, Font.NORMAL);
//	  
//	 /** Times Roman 12 Bold */
//	 public static final Font REDFONT = new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.NORMAL);
//	 public static final Font SMALLBOLD = new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.BOLD);
//	
//	 
//	 public static   BaseColor colorLigne = WebColors.getRGBColor("#f8fcfc");
//	 
//	 public static   BaseColor colorHeader = WebColors.getRGBColor("#F0F0F0"); 
	 /***********************************************************************************************************************************************/
	 
	 
	 
	  
	 
	
	 public static final Font Normal_10_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL);
	 public static final Font Bold_10_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD);
	 

		
	 public static final Font Normal_9_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL);
	 public static final Font Bold_9_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD);
	 
	 public static final Font Bold_8_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 8, Font.BOLD);
	 
	 public static final Font Normal_11_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL);
	 public static final Font Bold_11_times_roman = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD);
	  

	 
	 public static final Font FONT_12_bold = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD);
	 
	 
	 
	 public static final Font FONT_12_normal = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL);
	  
	 /** Times Roman 12 Bold */
	 public static final Font REDFONT = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.NORMAL);
	 public static final Font SMALLBOLD = new Font(Font.getFamily("TIMES_ROMAN"), 9, Font.BOLD);
	
	 
	 public static   BaseColor colorLigne = WebColors.getRGBColor("#f8fcfc");
	 
	 public static   BaseColor colorHeader = WebColors.getRGBColor("#F0F0F0"); 

	 
	 
	 
	  public void createPdfWithcellwithcolSapnRowSapn(String filename)
		        throws IOException, DocumentException {
		    	// step 1
		        Document document = new Document();
		        
		    
		        // step 2
		       // PdfWriter.getInstance(document, new FileOutputStream(filename));
		       PdfWriter.getInstance(document,  getResponse().getOutputStream());
		       
		        // step 3
		        document.open();
		        // step 4
		        document.add(createFirstTableColSpanRowSapn());
		        // step 5
		        document.close();
		    }
	  
	 
 

	  public void createPdfWithcellPaysage(  List  lisData,String [][] mapFieldBean  )
		        throws Exception {
		        File file = new File(getRequest().getRealPath("/")+"/temp/"+(String)getObjectValueModel(NAME_LIST_G)+getRequest().getSession().getId()+".pdf");
		        FileOutputStream fs = new FileOutputStream(file);
		        BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);
		        String [][]    map_critere_de_recherche=    (String[][]) getObjectValueModel(MAP_CRITERE_DE_RECHERCHE) ;
		        Object searchBean=getObjectValueModel(SEARCH_BEAN);
		       
		   try {
			   
			    Document document=GeneratePdf.doGenerateDocumentFormat();
		        PdfPTable table = new PdfPTable(mapFieldBean.length);
		        String    title= (String)getObjectValueModel("list-"+bSession.getSousmod_id()) ; 
		        if(title==null)
		             title =(String)getObjectValueModel("list-"+bSession.getSousmod_libelle()) ;
		        if(title==null)title="";
			    doWriteHeaderDocument_PDF(document,fs,mapFieldBean,bSession);
			    if(map_critere_de_recherche!=null && map_critere_de_recherche.length>0)
			    doWriteCritere_de_recherche_Table(document, searchBean,map_critere_de_recherche);
			    doWriteTitle_Table(document,title);
			    doWrite_Header_Table(table,mapFieldBean);
		        doWrite_Data_Table(lisData,table,mapFieldBean);
		        document.add(table);
		        document.close();
		        
		   } catch (Exception e) {
				throw  e;
			}
		       
		    } 
	  
	  public void doWrite_Data_Table_with_carc(List lisData, PdfPTable table,
				String[][] mapFieldBean) throws Exception, SecurityException {
		  
		  
			  for(int i=0; i < lisData.size(); i++ ){
				   Object bean = (Object) lisData.get(i);
				    
				 for(int j = 0; j < mapFieldBean.length; j++){
					 
					        PdfPCell cell = new PdfPCell(new Phrase("",REDFONT));
					 
					        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
					  
					        cell = new PdfPCell(new Phrase(String.valueOf(obj),REDFONT));
					        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					        cell.setPaddingBottom(5);
					        
					        cell.setBorderColor(WebColors.getRGBColor("#787878"));
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(colorLigne);
					        table.addCell(cell);
						   
					    }   
				   
				   
				   
	       }
			
		}
	  
	  
	  
	  public void doWrite_Data_Table(List lisData, PdfPTable table, String[][] mapFieldBean) throws Exception, SecurityException {
		  
		  JSONObject  jSONObject=    (JSONObject) getObjectValueModel("propertieField") ;
		  for(int i=0; i < lisData.size(); i++ ){
			   Object bean = (Object) lisData.get(i);
			 for(int j = 0; j < mapFieldBean.length; j++){
				        PdfPCell cell = new PdfPCell(new Phrase("",REDFONT));
				        Object obj=	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[j][0]);
				        
				        if(jSONObject!=null &&  jSONObject.has(mapFieldBean[j][0]) ) {
				        	JSONObject propertie = (JSONObject) jSONObject.get(mapFieldBean[j][0]);
				        	String valueData=String.valueOf(obj);
				        	if(propertie.getString("type").equals("montant3") &&  !StringUtils.isEmpty(valueData) ) {
				        		Double value = Double.parseDouble(valueData);
				        		valueData=ProcessFormatNbr.FormatDouble_To_String_Troischiffre(value);
				        	}
				        	if(propertie.getString("type").equals("integer") &&  !StringUtils.isEmpty(valueData) ) {
				        		Double value = Double.parseDouble(valueData);
				        		Integer iva = value.intValue();
				        		valueData=String.valueOf( iva);
				        	}
					        cell = new PdfPCell(new Phrase(valueData,REDFONT));
					        cell.setHorizontalAlignment(propertie.getInt("align"));
					        cell.setPaddingBottom(5);
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(colorLigne);
					        table.addCell(cell);
				        }else {
				        	cell = new PdfPCell(new Phrase(String.valueOf(obj),REDFONT));
					        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					        cell.setPaddingBottom(5);
					        cell.setBackgroundColor(BaseColor.WHITE);
					        if(i%2==0)
					        cell.setBackgroundColor(colorLigne);
					        table.addCell(cell);
				        }
				        
				        
					   
				    }   
			   
       }
		
	}




	public void doWrite_Header_Table(PdfPTable table, String[][] mapFieldBean) throws Exception {
		
		    int PaddingBottom=5;
	        int[] columnWidths = new int[mapFieldBean.length] ;
            for(int i = 0; i < mapFieldBean.length; i++){
          	columnWidths[i]= Integer.parseInt(mapFieldBean[i][1])   ;
			}
	        table.setWidthPercentage(90);
            table.setWidths(columnWidths);
            JSONObject  jSONObject=    (JSONObject) getObjectValueModel("propertieField") ;
          for(int i = 0; i < mapFieldBean.length; i++){
        	     String titrehead="";
        	     if(mapFieldBean[i][0].indexOf(".")>0){
					 final String[] lesColunmooo = mapFieldBean[i][0].split("\\.");
					 String getel=lesColunmooo[lesColunmooo.length-1];
					 titrehead=(String) getObjectValueModel(getel);
					 if(titrehead==null)  titrehead=(String) getObjectValueModel(mapFieldBean[i][0]);
					 if(titrehead==null)  titrehead="";
        	     }else{
        	    	 titrehead=(String) getObjectValueModel(mapFieldBean[i][0]);
        	     } 
        	     
        	    if(mapFieldBean[i][0].startsWith("modeBean"))  titrehead=(String) getObjectValueModel("_mode");
        	    String  title=titrehead==null?"-":titrehead ;
        	   
        	    
        	    if(jSONObject!=null &&  jSONObject.has(mapFieldBean[i][0]) &&  title.equals("-") ) {
        	    	 JSONObject propertie = (JSONObject) jSONObject.get(mapFieldBean[i][0]);
        	    	 title=propertie.getString("title")==null?"-":propertie.getString("title"); 
        	    }
        	    		
        	    PdfPCell cell = new PdfPCell(new Phrase( title ,new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.BOLD)));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setPaddingBottom(PaddingBottom);
			    cell.setBackgroundColor(colorHeader);
			    table.addCell(cell);
			}
		
	}

	
	public void doWrite_Header_Table_WidthPercentage(PdfPTable table,int poucentage, String[][] mapFieldBean) throws Exception {
		
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
    	     }else{
    	    	 titrehead=(String) getObjectValueModel(mapFieldBean[i][0]);
    	     } 
    	     
    	     if(mapFieldBean[i][0].startsWith("modeBean"))  titrehead=(String) getObjectValueModel("_mode");
    	    PdfPCell cell = new PdfPCell(new Phrase( titrehead==null?"-":titrehead  ,SMALLBOLD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setPaddingBottom(PaddingBottom);
		    cell.setBackgroundColor(colorHeader);
		    cell.setBorderWidth(1f);
		    table.addCell(cell);
		}
	
}



	public void doWriteTitle_Table(Document document, String title) throws Exception {
		    PdfPTable tabletitle = new PdfPTable(100);
		    PdfPCell cell = new PdfPCell(new Phrase(title,   Bold_10_times_roman));
	        cell.setColspan(100);
	        cell.setFixedHeight(50f);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(cell.NO_BORDER);
	        tabletitle.addCell(cell);
	        document.add(tabletitle);
	}
	
		public void doWriteCritere_de_recherche_Table(Document document,Object searchBean, String [][]    map_critere_de_recherche) throws Exception {
			
			int nbr_ligne_In_Critere=0;
			int nbr_Colounm_In_Critere=0;
			
			HashMap  mapcri= new HashMap();
			for(int i = 0; i < map_critere_de_recherche.length; i++){
			  String elment=map_critere_de_recherche[i][1];
			  String[] tabElt=elment.split("-");
			  
			  if(Integer.parseInt(tabElt[0])> nbr_ligne_In_Critere){
				  nbr_ligne_In_Critere=Integer.parseInt(tabElt[0]);
			  }
			  Vector vectligne=(Vector) ((Vector) mapcri.get(tabElt[0])==null?new Vector():mapcri.get(tabElt[0]));
			  vectligne.add(map_critere_de_recherche[i][0]);
			  mapcri.put(tabElt[0],vectligne);
			 
			  if(vectligne.size()>nbr_Colounm_In_Critere){
				 nbr_Colounm_In_Critere=vectligne.size();
			   }
			}
			
			    int nbr_Colounm_In_Critere_lab=nbr_Colounm_In_Critere*2;
			
			    PdfPTable table = new PdfPTable(nbr_Colounm_In_Critere_lab);
			
			    int[] columnWidths = new int[nbr_Colounm_In_Critere_lab] ;
			   
	            for(int i = 0; i < nbr_Colounm_In_Critere_lab; i++){
	            //String[] tabElt=map_critere_de_recherche[i][1].split("-");
	            	if ( (i % 2) == 0 )
	            	 	columnWidths[i]= 7  ;
	            	else
	            		columnWidths[i]= 20  ;
	            		
	          	
				}
		        table.setWidthPercentage(80);
	            table.setWidths(columnWidths);
	            
	            int PaddingBottom=5;
	            
				for (int j = 1; j <= nbr_ligne_In_Critere; j++) {
					
					Vector  vectmafil=(Vector) mapcri.get(String.valueOf(j));
					
					for (int i = 0; i < vectmafil.size(); i++) {
						    String elment =(String) vectmafil.get(i);
						    
						    String titrehead="";
						    if(elment.indexOf(".")>0){
								 final String[] lesColunmooo = elment.split("\\.");
								 String getel=lesColunmooo[lesColunmooo.length-1];
								 titrehead=(String) getObjectValueModel(getel);
			        	     }else{
			        	    	 titrehead=(String) getObjectValueModel(elment);
			        	     }
			        	    PdfPCell cell = new PdfPCell(new Phrase( titrehead+":"  ,SMALLBOLD));
						    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						    cell.setPaddingBottom(PaddingBottom);
						    //cell.setBackgroundColor(colorHeader);
						    cell.setBorderWidth(0f);
						    table.addCell(cell);
						    
						    String resl=(String) getValueOject_with_name_field(searchBean, elment);
						    cell = new PdfPCell(new Phrase( resl  ,FONT_12_normal));
						    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						    cell.setPaddingBottom(PaddingBottom);
						    //cell.setBackgroundColor(colorHeader);
						    cell.setBorderWidth(0f);
						    table.addCell(cell);
					}
					
					if(vectmafil.size()<nbr_Colounm_In_Critere_lab){
						int lesreste=nbr_Colounm_In_Critere_lab-vectmafil.size();
						for (int i = 0; i < lesreste; i++) {
							    PdfPCell cell = new PdfPCell(new Phrase( ""  ,SMALLBOLD));
							    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							    cell.setPaddingBottom(PaddingBottom);
							    //cell.setBackgroundColor(colorHeader);
							    cell.setBorderWidth(0f);
							    table.addCell(cell);
							    table.addCell(cell);
						}
					}
					
					
				}
				
       	     /*
          for(int i = 0; i < map_critere_de_recherche.length; i++){
        	     
        	     String titrehead="";
        	     if(map_critere_de_recherche[i][0].indexOf(".")>0){
					 final String[] lesColunmooo = map_critere_de_recherche[i][0].split("\\.");
					 String getel=lesColunmooo[lesColunmooo.length-1];
					 titrehead=(String) getObjectValueModel(getel);
        	     }else{
        	    	 titrehead=(String) getObjectValueModel(map_critere_de_recherche[i][0]);
        	     }
        	    PdfPCell cell = new PdfPCell(new Phrase( titrehead  ,SMALLBOLD));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setPaddingBottom(PaddingBottom);
			    cell.setBackgroundColor(colorHeader);
			    cell.setBorderWidth(0f);
			    table.addCell(cell);
			}*/
          document.add(table);
	}




	 
	
	public static  Document  doGenerateDocumentFormat() {
		
		  Document document =null;
		  BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION); 
			 
		 if(bSession.getFormatPrint().equals("portrait")) {
			 document = new Document(PageSize.A4, 5, 5, 5, 25);
					         } else {
					        	
					        	document = new Document(PageSize.A4.rotate(), 5, 5, 20, 40);
		  }
			 return 	document;

	}

	
	public static  void  doGeneratePdfWriterFormat(Document document, FileOutputStream fs) throws Exception {
		
		   PdfWriter writer = PdfWriter.getInstance(document,  fs);
		   BeanSession bSession= (BeanSession) getObjectValueModel(BEAN_SESSION);  
		  if(bSession.getFormatPrint().equals("portrait")) {
		    	 TableHeaderNormale event = new TableHeaderNormale(bSession);
	           writer.setPageEvent(event);
	       } else {
	           TableHeaderPaysage event = new TableHeaderPaysage(bSession);
			     writer.setPageEvent(event); 
		    }

	}
	
	public static  void  doGenerateEnteteDocument(Document document, FileOutputStream fs , int espaceImage , int espaceText ,  String textEntete) throws Exception {
		
		BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
        
        document.addCreator(bs.getPack_libelle());
	    document.addAuthor(bs.getMod_libelle());
	    document.addTitle(bs.getFct_libelle()+"/"+bs.getSousmod_libelle_title());
	    document.open();
	    if(bs.getFormatPrint().equals("portrait")) {
	    	espaceImage=espaceImage+5;
	    	espaceText=espaceText-5;
         }  
        
        PdfPTable tableheader = new PdfPTable(90);
        tableheader.setWidthPercentage(90);
        String absoluteDiskPath =  getRequest().getServletContext().getRealPath("/img/logoGen.jpg");
        Image companyLogo =null;
        if(bs.getSociete().getMyFile()!=null) {
        	  companyLogo = Image.getInstance(bs.getSociete().getMyFile().getFile_byte());
        }else {
        	 companyLogo = Image.getInstance(absoluteDiskPath);
        }
        PdfPCell cellheder = new PdfPCell(companyLogo, true);
		cellheder.setColspan(espaceImage);
		cellheder.setPaddingLeft(5f);
		cellheder.setPaddingTop(16f);
		cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellheder.setBorder(cellheder.NO_BORDER);
		//cellheder.setBorder(cellheder.TOP+cellheder.LEFT+cellheder.RIGHT+cellheder.BOTTOM);
		tableheader.addCell(cellheder);
		

	    
	    cellheder = new PdfPCell(new Phrase(textEntete,Normal_10_times_roman));
	    cellheder.setColspan(espaceText);
	    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellheder.setPaddingLeft(20f);
	    cellheder.setPaddingBottom(40f);
	    cellheder.setPaddingTop(16f);
	    cellheder.setBorder(cellheder.NO_BORDER);
	    //cellheder.setBorder(cellheder.TOP+cellheder.LEFT+cellheder.RIGHT+cellheder.BOTTOM);
	    tableheader.addCell(cellheder);
	    document.add(tableheader);

	}
	
	 

	public  void doWriteHeaderDocument_PDF(Document document, FileOutputStream fs ,String [][] mapFieldBean,BeanSession bs) throws Exception {
		doGeneratePdfWriterFormat(document, fs);
		String textEntete=bs.getSoc_lib()+"\n\r"+bs.getEtab_lib()+"\n\r"+bs.getPrf_libelle();
		doGenerateEnteteDocument(document, fs, 10, 80, textEntete);
	
}
	
	
	public static void doWriteEntete_Front_dev_vente_facture(Document document, DevisBean denBean,String LibelleEntete) throws Exception {
		PdfPTable tableTopHeader = new PdfPTable(100);
		tableTopHeader.setWidthPercentage(92);
		 
		    
		PdfPCell cell = new PdfPCell(new Phrase(" "+LibelleEntete+" ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(12);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(" : "+denBean.getDevis_id(),GeneratePdf.FONT_12_normal));
	    cell.setColspan(28);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    
	    cell = new PdfPCell(new Phrase(" Client ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(18);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.TOP+cell.LEFT);
	    tableTopHeader.addCell(cell);
	    
	    
	    cell = new PdfPCell(new Phrase(" : "+denBean.getClient().getClt_lib() ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(42);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.TOP+cell.RIGHT);
	    tableTopHeader.addCell(cell);
	    
	    
	    
	    
	     
	    
	    
	    cell = new PdfPCell(new Phrase(" Date ",GeneratePdf.FONT_12_bold));
	    cell.setColspan(12);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.NO_BORDER);
	    tableTopHeader.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase(  " : "+ProcessDate.getCurrentTimeStamp(denBean.getDev_date()) ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(28);
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
	    
	    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_adr() ,GeneratePdf.FONT_12_normal));
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
	    
	    cell = new PdfPCell(new Phrase( " : "+denBean.getClient().getClt_obs() ,GeneratePdf.FONT_12_normal));
	    cell.setColspan(42);
	    cell.setFixedHeight(20f);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setBorder(cell.RIGHT +cell.BOTTOM);
	    tableTopHeader.addCell(cell);
	    document.add(tableTopHeader);
			
		}


	public void createPdfWithcellSimple(  List   lisData,HashMap    mapFieldBean  )
		        throws IOException, DocumentException, Throwable  {
		         
		        Document document = new Document(PageSize.A4, 5, 5, 5, 5);
		        PdfWriter writer = PdfWriter.getInstance(document,  getResponse().getOutputStream());
		        TableHeader event = new TableHeader(new BeanSession());
		        writer.setPageEvent(event);
		     
		     
		        
		        document.addCreator("Binod by Demo.java");
			    document.addAuthor("Binod Suman");
			    document.addTitle("First PDF By Binod");
			    document.open();
		        
		        float widthval=5;
		        PdfPTable table = new PdfPTable(3);
		        table.setWidthPercentage(90);
		        PdfPCell cell;
		        
		        PdfPTable tableheader = new PdfPTable(100);
		        PdfPCell cellheder;
		        
		        tableheader.setWidthPercentage(100);
		        
		        Image companyLogo = Image.getInstance("c:/images/sigle_ibs.jpg");
				companyLogo.scalePercent(50);
				cellheder = new PdfPCell(); 
				cellheder.addElement(new Chunk(companyLogo, 0, 0, true));
				cellheder.setColspan(10);
				cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellheder.setBorder(cellheder.NO_BORDER);
				tableheader.addCell(cellheder);
			        
			    cellheder = new PdfPCell(new Phrase("Company Name"+"\n\r"+"Address Line 1"+"\n\r"+"City, State - ZipCode",Normal_10_times_roman));
			    cellheder.setColspan(90);
			    cellheder.setHorizontalAlignment(Element.ALIGN_LEFT);
			    cellheder.setPaddingLeft(20f);
			    cellheder.setBorder(cellheder.NO_BORDER);
			    tableheader.addCell(cellheder);
			    document.add(tableheader);
			    
			    
			
			 
			 
//		        cell = new PdfPCell(new Phrase("Liste Des Utilisateurs",SUBFONT));
//		        cell.setColspan(3);
//		        cell.setFixedHeight(40f);
//		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		        cell.setBorder(cell.NO_BORDER);
//		        table.addCell(cell);
		        
		        
		     
		        
		 	   Set coll=mapFieldBean.keySet();
		        for (Iterator iter = coll.iterator(); iter.hasNext();) {
					String  title = (String) iter.next();
					
				    cell = new PdfPCell(new Phrase(title,SMALLBOLD));
			        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell.setPaddingBottom(widthval);
			        cell.setBackgroundColor(colorHeader);
			        cell.setBorderWidth(1f);
			        cell.setColspan(1);
			        table.addCell(cell);
					 
				    }
		        
		    
		        for(int i=0; i < lisData.size(); i++ ){
					   Object bean = (Object) lisData.get(i);
					   
					
						 Set data=mapFieldBean.keySet();
						 
						   for (Iterator iterr = data.iterator(); iterr.hasNext();) {
							String  title = (String) iterr.next();
							  Field field = bean.getClass().getDeclaredField(title);
						       field.setAccessible(true);
						       cell = new PdfPCell(new Phrase(  (String)field.get(bean),REDFONT));
						        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						        cell.setPaddingBottom(widthval);
						        
						        cell.setBorderColor(WebColors.getRGBColor("#787878"));
						        cell.setBackgroundColor(BaseColor.WHITE);
						        if(i%2==0)
						        cell.setBackgroundColor(colorLigne);
						        cell.setColspan(1);
						        table.addCell(cell);
							   
						    }   
					   
					   
					   
		        }
		      
		        document.add(table);
		        document.close();
		       
		    } 
	  
	  public static PdfPTable createFirstTableColSpanRowSapn() {
	    	// a table with three columns
	        PdfPTable table = new PdfPTable(3);
	        // the cell object
	        PdfPCell cell;
	        // we add a cell with colspan 3
	        
	        cell = new PdfPCell(new Phrase("titresdsfffffffffffff"));
	        cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder( cell.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(""));
	        cell.setColspan(3);
	        cell.setFixedHeight(72f);
	        cell.setBorder( cell.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Cell with colspan 3"));
	        cell.setColspan(3);
	        table.addCell(cell);
	        // now we add a cell with rowspan 2
	        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
	        cell.setRowspan(2);
	        table.addCell(cell);
	        // we add the four remaining cells with addCell()
	        table.addCell("row 1; cell 1");
	        table.addCell("row 1; cell 2");
	        table.addCell("row 2; cell 1");
	        table.addCell("row 2; cell 2");
	        return table;
	    }
	 
	 public    void createPDFSimple (  List   lisData,HashMap    mapFieldBean  ){
		 
		  Document doc = new Document();
		  PdfWriter docWriter = null;
		  initializeFonts();
		 
		  try {
		   docWriter = PdfWriter.getInstance(doc , getResponse().getOutputStream());
		   String p_header_seg1="Content-disposition";
		   String p_header_seg2="attachment ; filename=teste.pdf";
	       
		   doc.addAuthor("betterThanZero");
		   doc.addCreationDate();
		   doc.addProducer();
		   doc.addCreator("MySampleCode.com");
		   doc.addTitle("Invoice");
		   doc.setPageSize(PageSize.A4);
		
		   doc.open();
		   PdfContentByte cb = docWriter.getDirectContent();
		    
		   boolean beginPage = true;
		   int y = 0;
		    
		   for(int i=0; i < lisData.size(); i++ ){
			   Object bean = (Object) lisData.get(i);
		    if(beginPage){
		     beginPage = false;
		     generateLayoutSimple(doc, cb);
		     generateHeaderTable(doc, cb,mapFieldBean);
		     y = 620; 
		    }
		    generateDetailSimpleWithLigne(doc,bean,mapFieldBean, cb, i, y);
		    y = y - 15;
		    if(y < 50){
		     printPageNumber(cb);
		     doc.newPage();
		     beginPage = true;
		    }
		   }
		   printPageNumber(cb);
		 
		  }
		  catch (DocumentException dex)
		  {
		   dex.printStackTrace();
		  }
		  catch (Exception ex)
		  {
		   ex.printStackTrace();
		  }
		  finally
		  {
		   if (doc != null)
		   {
		    doc.close();
		   }
		   if (docWriter != null)
		   {
		    PdfAction jAction = PdfAction.javaScript("this.print(true);\r", docWriter);
			docWriter.addJavaScript(jAction);
		    docWriter.close();
		   }
		  }
		 }
	 
	/* public static void main(String[] args) {
	 
	  String pdfFilename = "";
	  GeneratePdf generateInvoice = new GeneratePdf();
	  String viva="fffff.pdf";
	  if (viva.length() < 1)
	  {
	   System.err.println("Usage: java "+ generateInvoice.getClass().getName()+"PDF_Filename");
	   System.exit(1);
	  }
	  pdfFilename = viva.trim();
	  generateInvoice.createPDF(pdfFilename);
	 
	 }*/
	 
	 public    void createPDF (  List   lisData,HashMap   mapFieldBean  ){
	 
	  Document doc = new Document();
	  PdfWriter docWriter = null;
	  initializeFonts();
	 
	  try {
	  // String path = "C:/" + pdfFilename;
		 
	   docWriter = PdfWriter.getInstance(doc , getResponse().getOutputStream());
	   
	   String p_header_seg1="Content-disposition";
       //String p_header_seg2="attachment ; filename="+fileName.trim()+".xls";
	   String p_header_seg2="attachment ; filename=teste.pdf";
       //getResponse().setHeader(p_header_seg1, "no-cache");
       
	   doc.addAuthor("betterThanZero");
	   doc.addCreationDate();
	   doc.addProducer();
	   doc.addCreator("MySampleCode.com");
	   doc.addTitle("Invoice");
	   doc.setPageSize(PageSize.LETTER);
	   doc.open();
	   PdfContentByte cb = docWriter.getDirectContent();
	    
	   boolean beginPage = true;
	   int y = 0;
	    
	   for(int i=0; i < lisData.size(); i++ ){
		   Object bean = (Object) lisData.get(i);
	    if(beginPage){
	     beginPage = false;
	     generateLayout(doc, cb); 
	     generateHeader(doc, cb);
	     y = 615; 
	    }
	    generateDetail(doc,bean,mapFieldBean, cb, i, y);
	    y = y - 15;
	    if(y < 50){
	     printPageNumber(cb);
	     doc.newPage();
	     beginPage = true;
	    }
	   }
	   printPageNumber(cb);
	 
	  }
	  catch (DocumentException dex)
	  {
	   dex.printStackTrace();
	  }
	  catch (Exception ex)
	  {
	   ex.printStackTrace();
	  }
	  finally
	  {
	   if (doc != null)
	   {
	    doc.close();
	   }
	   if (docWriter != null)
	   {
	    PdfAction jAction = PdfAction.javaScript("this.print(true);\r", docWriter);
		docWriter.addJavaScript(jAction);
	    docWriter.close();
	   }
	  }
	 }
	 
	 private void generateLayoutSimple(Document doc, PdfContentByte cb)  {
		 
		  try {
		 
		   cb.setLineWidth(1f);
		   
		   
		   Image companyLogo = Image.getInstance("c:/images/sigle_ibs.jpg");
		   companyLogo.setAbsolutePosition(25,700);
		   companyLogo.scalePercent(50);
		   doc.add(companyLogo);
		   createHeadings(cb,100,750,"Company Name");
		   createHeadings(cb,100,735,"Address Line 1");
		   createHeadings(cb,100,720,"Address Line 2");
		   createHeadings(cb,100,705,"City, State - ZipCode");
		   cb.beginText();
		   cb.setFontAndSize(bfBold, 14);
		   cb.setTextMatrix(220,670);
		   cb.showText("Liste  Utilisateur");
		   cb.endText(); 
		  }
		 
		  catch (DocumentException dex){
		   dex.printStackTrace();
		  }
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
	 
	 private void generateLayout(Document doc, PdfContentByte cb)  {
	 
	  try {
	 
	   cb.setLineWidth(1f);
	 
	   // Invoice Header box layout
	   cb.rectangle(420,700,150,60);
	   cb.moveTo(420,720);
	   cb.lineTo(570,720);
	   cb.moveTo(420,740);
	   cb.lineTo(570,740);
	   cb.moveTo(480,700);
	   cb.lineTo(480,760);
	   cb.stroke();
	 
	   // Invoice Header box Text Headings 
	   createHeadings(cb,422,743,"Account No.");
	   createHeadings(cb,422,723,"Invoice No.");
	   createHeadings(cb,422,703,"Invoice Date");
	 
	   // Invoice Detail box layout 
	   cb.rectangle(20,50,550,600);
	   cb.moveTo(20,630);
	   cb.lineTo(570,630);
	   cb.moveTo(50,50);
	   cb.lineTo(50,650);
	   cb.moveTo(150,50);
	   cb.lineTo(150,650);
	   cb.moveTo(430,50);
	   cb.lineTo(430,650);
	   cb.moveTo(500,50);
	   cb.lineTo(500,650);
	   cb.stroke();
	 
	   // Invoice Detail box Text Headings 
	   createHeadings(cb,22,633,"Qty");
	   createHeadings(cb,52,633,"Item Number");
	   createHeadings(cb,152,633,"Item Description");
	   createHeadings(cb,432,633,"Price");
	   createHeadings(cb,502,633,"Ext Price");
	 
	   //add the images
	   Image companyLogo = Image.getInstance("c:/images/sigle_ibs.jpg");
	   companyLogo.setAbsolutePosition(25,700);
	   companyLogo.scalePercent(25);
	   doc.add(companyLogo);
	 
	  }
	 
	  catch (DocumentException dex){
	   dex.printStackTrace();
	  }
	  catch (Exception ex){
	   ex.printStackTrace();
	  }
	 
	 }
	  
	 private void generateHeader(Document doc, PdfContentByte cb)  {
	 
	  try {
	 
	   createHeadings(cb,200,750,"Company Name");
	   createHeadings(cb,200,735,"Address Line 1");
	   createHeadings(cb,200,720,"Address Line 2");
	   createHeadings(cb,200,705,"City, State - ZipCode");
	   createHeadings(cb,200,690,"Country");
	   createHeadings(cb,482,743,"ABC0001");
	   createHeadings(cb,482,723,"123456");
	   createHeadings(cb,482,703,"09/26/2012");
	 
	  }
	 
	  catch (Exception ex){
	   ex.printStackTrace();
	  }
	 
	 }
	  
	 private void generateHeaderSimple(Document doc, PdfContentByte cb)  {
		 
		  try {
		 
		   createHeadings(cb,100,750,"Company Name");
		   createHeadings(cb,100,735,"Address Line 1");
		   createHeadings(cb,100,720,"Address Line 2");
		   createHeadings(cb,100,705,"City, State - ZipCode");
		   //createHeadings(cb,100,690,"Country");
		  }
		 
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
	 
	 
	 
	private void generateHeaderTable(Document doc, PdfContentByte cb,HashMap    mapFieldBean)  {
		 
		  try {
			  //Ligne horizental top
			   cb.moveTo(20,630);
			   cb.lineTo(570,630);
			   
			   
			   //Ligne horizental bottom
			   cb.moveTo(20,650);
			   cb.lineTo(570,650);
			   
			   //Ligne vertical right
			   cb.moveTo(570,630);
			   cb.lineTo(570,650);
			   
			   //Ligne vertical left
			   cb.moveTo(20,630);
			   cb.lineTo(20,650);
			   Set coll=mapFieldBean.keySet();
			   for (Iterator iter = coll.iterator(); iter.hasNext();) {
				String  title = (String) iter.next();
				
					//lignevertcal
				  cb.moveTo(Float.parseFloat((String) mapFieldBean.get(title)),630);
				  cb.lineTo(Float.parseFloat((String) mapFieldBean.get(title)),650);
				  createHeadingsSimple(cb,Float.parseFloat((String) mapFieldBean.get(title))+2,639,title);
				 
			    }
			  
	 
			 
		  }
		 
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
	 
	 private void generateDetail(Document doc,Object bean,HashMap    mapFieldBean, PdfContentByte cb, int index, int y)  {
	  DecimalFormat df = new DecimalFormat("0.00");
	   
	  try {
	 /*
	   createContent(cb,48,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
	   createContent(cb,52,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
	   createContent(cb,152,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
	   createContent(cb,498,y, df.format(10),PdfContentByte.ALIGN_RIGHT);
	   createContent(cb,568,y, df.format(10*y*index),PdfContentByte.ALIGN_RIGHT);
	    */
		   
		 
		  
		
		 Set coll=mapFieldBean.keySet();
		   for (Iterator iter = coll.iterator(); iter.hasNext();) {
			String  title = (String) iter.next();
			  Field field = bean.getClass().getDeclaredField(title);
		       field.setAccessible(true);
			   createContent(cb,Float.parseFloat((String) mapFieldBean.get(title))+4,y,(String)field.get(bean),PdfContentByte.ALIGN_LEFT);
			 
		    }
		
		
		
		
		   //createContent(cb,48,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
		   //createContent(cb,52,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
		   //createContent(cb,152,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
		   //createContent(cb,498,y, df.format(10),PdfContentByte.ALIGN_RIGHT);
		   //createContent(cb,568,y, df.format(10*y*index),PdfContentByte.ALIGN_RIGHT);  
		   
		   
		    
		   
		  
		  
		  
	  }
	 
	  catch (Exception ex){
	   ex.printStackTrace();
	  }
	 
	 }
	 
	 private void generateDetailSimple(Document doc,Object bean,HashMap    mapFieldBean, PdfContentByte cb, int index, int y)  {
		  DecimalFormat df = new DecimalFormat("0.00");
		   
		  try {
		 
			 Set coll=mapFieldBean.keySet();
			   for (Iterator iter = coll.iterator(); iter.hasNext();) {
				String  title = (String) iter.next();
				  Field field = bean.getClass().getDeclaredField(title);
			       field.setAccessible(true);
				   createContent(cb,Float.parseFloat((String) mapFieldBean.get(title))+4,y,(String)field.get(bean),PdfContentByte.ALIGN_LEFT);
				 
			    }
		 
		  }
		 
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
	 
	 private void generateDetailSimpleWithLigne(Document doc,Object bean,HashMap    mapFieldBean, PdfContentByte cb, int index, int y)  {
		  DecimalFormat df = new DecimalFormat("0.00");
		   
		  try {
			 
			 Set coll=mapFieldBean.keySet();
			 
			   for (Iterator iter = coll.iterator(); iter.hasNext();) {
				String  title = (String) iter.next();
				  Field field = bean.getClass().getDeclaredField(title);
			       field.setAccessible(true);
			       cb.moveTo(Float.parseFloat((String) mapFieldBean.get(title)),y);
				   cb.lineTo(Float.parseFloat((String) mapFieldBean.get(title)),y+30);
				   createContent(cb,Float.parseFloat((String) mapFieldBean.get(title))+4,y,(String)field.get(bean),PdfContentByte.ALIGN_LEFT);
				   
			    }
			   cb.moveTo(570,y);
			   cb.lineTo(570,y+20);
			   
			   cb.moveTo(20,y-5);
			   cb.lineTo(570,y-5);
			   cb.stroke();
		  }
		 
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
	 
	 private void createHeadings(PdfContentByte cb, float x, float y, String text){
	 
	  cb.beginText();
	  cb.setFontAndSize(bfBold, 8);
	  cb.setTextMatrix(x,y);
	  cb.showText(text.trim());
	  cb.endText(); 
	 
	 }
	 
	 private void createHeadingsSimple(PdfContentByte cb, float x, float y, String text){
		 
		  cb.beginText();
		  cb.setFontAndSize(bfBold, 8);
		  cb.setTextMatrix(x,y);
		  cb.showText(text.trim());
		  //cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text, x, y, 0);
		  cb.endText(); 
		 
		 }
	  
	 private void printPageNumber(PdfContentByte cb){
	 
	 
	  cb.beginText();
	  cb.setFontAndSize(bfBold, 8);
	  cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
	  cb.endText(); 
	   
	  pageNumber++;
	   
	 }
	  
	 private void createContent(PdfContentByte cb, float x, float y, String text, int align){
	 
	 
	  cb.beginText();
	  cb.setFontAndSize(bf, 8);  
	  cb.showTextAligned(align, text , x , y, 0);
	  cb.endText(); 
	 
	 }
	 
	 public   void initializeFonts(){
	 
	 
	  try {
	   bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	   bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	 
	  } catch (DocumentException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 
	 
	 }
	 
	}
