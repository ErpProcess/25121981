package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import jxl.Cell;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class WriteExcel extends GenericWeb  {

  private WritableCellFormat timesBoldUnderline;
  private WritableCellFormat times;
  private String inputFile;
  
public void setOutputFile(String inputFile) {
  this.inputFile = inputFile;
  }

    public void doExportExcel( List       lisData,	String [][]    mapFieldBean ) throws ServletException, IOException {
	  try {
		  
		    WorkbookSettings wbSettings = new WorkbookSettings();
		    wbSettings.setLocale(new Locale("en", "EN"));
		    BeanSession bSe = (BeanSession) getObjectValueModel(BEAN_SESSION);
		    String title= (String) getObjectValueModel("list-"+getObjectValueModel(ENTITES));
		    if(title==null || title.equals(""))  title="Listes des "+bSe.getSousmod_libelle();
		    getResponse().setHeader("Content-Disposition", "attachment; filename="+title+".xls");
		    WritableWorkbook workbook = Workbook.createWorkbook(getResponse().getOutputStream(), wbSettings);
		    workbook.createSheet("Report", 0);
		    WritableSheet excelSheet = workbook.getSheet(0);
		    String    titleHead =(String)getObjectValueModel(LIST_PDF_EXCEL) ;
	        if(titleHead==null)
		         titleHead =(String)getObjectValueModel("list-"+bSe.getSousmod_libelle()) ;
		    if(titleHead==null)
		    	titleHead = (String)getObjectValueModel("list-"+bSe.getSousmod_id()) ;
		    if(titleHead==null)titleHead="";
		    setObjectValueModel("titleHead",titleHead) ;
		    createTitleMap(excelSheet,mapFieldBean);
		    creatheaderMap(excelSheet,mapFieldBean);
		    createContentWithList(excelSheet,lisData,mapFieldBean);
		    workbook.write();
		    workbook.close();
		    
	  } 
	  catch (Exception e){
	   throw new ServletException("Exception in Excel Sample Servlet", e);
	  } 
	  
	 }
    
    public void doExportExcel55() throws ServletException, IOException {
  	  OutputStream out = null;
  	  try {
  	   getResponse().setContentType("application/vnd.ms-excel");
  	   getResponse().setHeader("Content-Disposition", "attachment; filename=sampleName.xls");
  	   WritableWorkbook workbook = Workbook.createWorkbook(getResponse().getOutputStream());
  	  workbook.createSheet("Report", 0);
      WritableSheet excelSheet = workbook.getSheet(0);
      createTitle(excelSheet);
      createContent(excelSheet);
      workbook.write();
      workbook.close();
  	  } 
  	  catch (Exception e){
  	   throw new ServletException("Exception in Excel Sample Servlet", e);
  	  } 
  	  finally{
  	   if (out != null)
  	    out.close();
  	  }
  	 }

  public void write() throws IOException, WriteException {
    
    WorkbookSettings wbSettings = new WorkbookSettings();
    wbSettings.setLocale(new Locale("en", "EN"));
    getResponse().setHeader("Content-Disposition", "attachment; filename=sampleName.xls");
    WritableWorkbook workbook = Workbook.createWorkbook(getResponse().getOutputStream(), wbSettings);
    workbook.createSheet("Report", 0);
    WritableSheet excelSheet = workbook.getSheet(0);
    createTitle(excelSheet);
    creatheader(excelSheet);
    createContent(excelSheet);
    workbook.write();
    workbook.close();
  }

  public void createTitleMap(WritableSheet sheet,	String [][]    mapFieldBean)
      throws WriteException {
	  
	  
    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
    times = new WritableCellFormat(times10pt);
    times.setWrap(true);

    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,UnderlineStyle.SINGLE);
    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
    timesBoldUnderline.setWrap(true);

    CellView cv = new CellView();
    cv.setFormat(times);
    cv.setFormat(timesBoldUnderline);
    cv.setAutosize(true);
     
     
    String titleHead= (String) getObjectValueModel("titleHead") ;
    
    if(titleHead==null)titleHead="";
    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
    cellFont.setColour(Colour.AQUA);
    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
    cellFormat.setBackground(Colour.WHITE);
    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    cellFormat.setAlignment(Alignment.CENTRE);
    sheet.mergeCells(1, 1, 1+mapFieldBean.length-1, 1);
    sheet.addCell(new Label(1, 1, titleHead, cellFormat));  
    
    

  }
  
  private void createTitle(WritableSheet sheet)
	      throws WriteException {
		  
		  
	    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
	    times = new WritableCellFormat(times10pt);
	    times.setWrap(true);

	    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,UnderlineStyle.SINGLE);
	    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
	    timesBoldUnderline.setWrap(true);

	    CellView cv = new CellView();
	    cv.setFormat(times);
	    cv.setFormat(timesBoldUnderline);
	    cv.setAutosize(true);

	    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
	    cellFont.setColour(Colour.AQUA);
	    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	    cellFormat.setBackground(Colour.WHITE);
	    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	    cellFormat.setAlignment(Alignment.CENTRE);
	    sheet.mergeCells(6, 1, 13, 1);
	    sheet.addCell(new Label(6, 1, "Liste Utilisateur", cellFormat));  
	    
	    

	  }
  
  public void creatheaderMap(WritableSheet sheet,	String [][]    mapFieldBean)
	      throws WriteException {
	    // Lets create a times font
	    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
	    // Define the cell format
	    times = new WritableCellFormat(times10pt);
	    // Lets automatically wrap the cells
	    times.setWrap(true);

	    // create create a bold font with unterlines
	    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
	        UnderlineStyle.SINGLE);
	    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
	    // Lets automatically wrap the cells
	    timesBoldUnderline.setWrap(true);

	    CellView cv = new CellView();
	    cv.setFormat(times);
	    cv.setFormat(timesBoldUnderline);
	    cv.setAutosize(true);
	    
	    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
	    cellFont.setColour(Colour.BLUE);
	    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	    cellFormat.setBackground(Colour.GRAY_25);
	    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	    cellFormat.setAlignment(Alignment.CENTRE);
	    
	   
	    int colonne=1;
	    
	    for(int i = 0; i < mapFieldBean.length; i++){
        	Integer siz=Integer.parseInt(mapFieldBean[i][1]);
		    sheet.setColumnView(colonne, (int)siz );
		    String lib="";
		    
		    if(mapFieldBean[i][0].indexOf(".")>0) {
		    	
				  String[] lesColunmoooUk = mapFieldBean[i][0].split("\\.");
				  lib=(String) getObjectValueModel(lesColunmoooUk[lesColunmoooUk.length-1]);
		    }else{
		    	lib=(String) getObjectValueModel(mapFieldBean[i][0]);
		    }
		    if(mapFieldBean[i][0]!=null && mapFieldBean[i][0].startsWith("modeBean")) lib=(String) getObjectValueModel("_mode");
		       
		    sheet.addCell(new Label(colonne++, 4, lib , cellFormat));
		 
		}
	    
	    
       
	    

	  }
  private void creatheader(WritableSheet sheet)
	      throws WriteException {
	    // Lets create a times font
	    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
	    // Define the cell format
	    times = new WritableCellFormat(times10pt);
	    // Lets automatically wrap the cells
	    times.setWrap(true);

	    // create create a bold font with unterlines
	    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
	        UnderlineStyle.SINGLE);
	    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
	    // Lets automatically wrap the cells
	    timesBoldUnderline.setWrap(true);

	    CellView cv = new CellView();
	    cv.setFormat(times);
	    cv.setFormat(timesBoldUnderline);
	    cv.setAutosize(true);
	    
	    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
	    cellFont.setColour(Colour.BLUE);
	    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	    cellFormat.setBackground(Colour.GRAY_25);
	    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	    cellFormat.setAlignment(Alignment.CENTRE);
	    
	    sheet.addCell(new Label(6, 4, "code", cellFormat)); 
	    sheet.addCell(new Label(7, 4, "libelle", cellFormat)); 
	    sheet.addCell(new Label(8, 4, "adresse", cellFormat)); 
	    sheet.addCell(new Label(9, 4, "pays", cellFormat));
	    sheet.addCell(new Label(10, 4, "ville", cellFormat));
	    sheet.addCell(new Label(11, 4, "societe", cellFormat));
	    sheet.addCell(new Label(12, 4, "etabli", cellFormat));
	    sheet.addCell(new Label(13, 4, "obsrv", cellFormat));
	    
	   

	  }

  private void createContent(WritableSheet sheet) throws WriteException,
      RowsExceededException {
    // Write a few number
    for (int i = 5; i < 10; i++) {
      addNumber(sheet, 7, i, i + 10);
      addNumber(sheet,8, i, i * i);
    }
    
    
    
    // Lets calculate the sum of it
  /*  StringBuffer buf = new StringBuffer();
    buf.append("SUM(H6:H10)");
    Formula f = new Formula(7, 10, buf.toString());
    sheet.addCell(f);
    buf = new StringBuffer();
    buf.append("SUM(I6:I10)");
    f = new Formula(8, 10, buf.toString());
    sheet.addCell(f);*/

   
  }
  
  public void createContentWithList(WritableSheet sheet,List lisData,	String [][]    mapFieldBean) throws WriteException,
  RowsExceededException {
 
		try {
	  int ligne=5;
	  
 
 
for(int i=0; i < lisData.size(); i++ ){
	   Object bean = (Object) lisData.get(i);
	   
	int colonne=1;
	 
	  for(int k = 0; k < mapFieldBean.length; k++){
			 
			    
		        WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
			    cellFont.setColour(Colour.BLACK);
			    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
			    cellFormat.setBackground(Colour.WHITE);
			    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			    cellFormat.setAlignment(Alignment.LEFT);
			    
			    
			    // int col = 2;
			    //int widthInChars = 3;
			    Integer siz=Integer.parseInt(mapFieldBean[k][1]);
			    sheet.setColumnView(colonne, (int)siz );
			    // sheet.addCell(new Label(col, 1, "A", cellFormat));  
			       Object obj =	 GenericWeb.getValueOject_with_name_field(bean, mapFieldBean[k][0]);
	 			 
	 			  sheet.addCell(new Label(colonne++,ligne, String.valueOf(obj), cellFormat)); 
			  
			    
		    }   
		   ligne++;
}
		} catch (Exception e) {
			e.printStackTrace();
		}  

}
  
  
  

  
  

  private void addCaption(WritableSheet sheet, int column, int row, String s)
      throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, timesBoldUnderline);
    
    
    
    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
    cellFont.setColour(Colour.BLUE);
    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
    cellFormat.setBackground(Colour.WHITE);
    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    cellFormat.setAlignment(Alignment.CENTRE);
    
   // col = 4;
    //widthInChars = 20;
    sheet.mergeCells(6, 1, 13, 1);
    //sheet.setColumnView(col, widthInChars);
    sheet.addCell(new Label(6, 1, s, cellFormat));  
    
  }
  
  private void addCaptionheader(WritableSheet sheet, int column, int row, String s)
	      throws RowsExceededException, WriteException {
	    
	    
	    
	    
	    
	    
	  }

  private void addNumber(WritableSheet sheet, int column, int row,
      Integer integer) throws WriteException, RowsExceededException {
    Number number;
    number = new Number(column, row, integer, times);
    sheet.addCell(number);
  }

  private void addLabel(WritableSheet sheet, int column, int row, String s)
      throws WriteException, RowsExceededException {
    Label label;
    label = new Label(column, row, s, times);
    sheet.addCell(label);
  }

 /* public static void main(String[] args) throws WriteException, IOException {
    WriteExcel test = new WriteExcel();
    test.setOutputFile("c:/temp/lars.xls");
    test.write();
    System.out
        .println("Please check the result file under c:/temp/lars.xls ");
  }*/
} 