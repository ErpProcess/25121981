package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class exportExcel extends GenericWeb {
	
	
	 public void doExportExcel() throws ServletException, IOException {
	  OutputStream out = null;
	  try {
	   getResponse().setContentType("application/vnd.ms-excel");
	   getResponse().setHeader("Content-Disposition", "attachment; filename=sampleName.xls");
	   WritableWorkbook w = Workbook.createWorkbook(getResponse().getOutputStream());
	   WritableSheet s = w.createSheet("Demo", 0);
	   s.addCell(new Label(0, 0, "Hello World"));
	   w.write();
	   w.close();
	  } 
	  catch (Exception e){
	   throw new ServletException("Exception in Excel Sample Servlet", e);
	  } 
	  finally{
	   if (out != null)
	    out.close();
	  }
	 }

	public exportExcel() {
		super();
		// TODO Auto-generated constructor stub
	}
	}
	 

 
	 
 
