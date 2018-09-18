package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.GenerationPdf;
 

 

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

 
 
public class TableHeaderPaysage extends PdfPageEventHelper {
     
	
		 private BeanSession bSession;
	     
	     public TableHeaderPaysage(BeanSession bSession) {
	     	this.bSession=bSession;
		}
        String header;
        PdfTemplate total;
        
       
        public void setHeader(String header) {
            this.header = header;
        }

       
        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }
        
       
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(3);
            BDateTime bTime = new BDateTime();
            try {
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(830);
                table.setWidthPercentage(100f);
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(10);
                table.getDefaultCell().setBorder(Rectangle.TOP);
                table.addCell(header);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(String.format("Editer Par : "+bSession.getUsr_nom()+"  "+bSession.getUsr_pre()+" Le "+bTime.getDateActuelF1()+"  A  "+bTime.getHeurActuel()+"      Page %d de", writer.getPageNumber()));
                PdfPCell cell = new PdfPCell(Image.getInstance(total));
                cell.setBorder(Rectangle.TOP);
                cell.setPaddingBottom(20f);
                table.addCell(cell);
                table.writeSelectedRows(0, -1, 5, 25, writer.getDirectContent());
            }
            catch(DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
        
        /**
         * Fills out the total number of pages before the document is closed.
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
            
            
         
            
            
            
        }
    }

 