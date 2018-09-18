package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.exportExcel;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LectureFichierExcel {

	
	public static void main(String[] args) {
		Workbook workbook = null;
		try {
			/* Récupération du classeur Excel (en lecture) */
			workbook = Workbook.getWorkbook(new File("D:\\exemple.xls"));
			
			/* Un fichier excel est composé de plusieurs feuilles, on y accède de la manière suivante*/
			Sheet sheet = workbook.getSheet(0);
			
			/* On accède aux cellules avec la méthode getCell(indiceColonne, indiceLigne) */
			Cell a1 = sheet.getCell(0,0); 
			
			/* On peut également le faire avec getCell(nomCellule) */
			Cell c5 = sheet.getCell("C5");
			
			/* On peut récupérer le contenu d'une cellule en utilisant la méthode getContents() */
			String contenuA1= a1.getContents();
			String contenuC5 = c5.getContents();
			
			System.out.println(contenuA1);
			System.out.println(contenuC5);
		} 
		catch (BiffException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if(workbook!=null){
				/* On ferme le worbook pour libérer la mémoire */
				workbook.close(); 
			}
		}
	}

}
