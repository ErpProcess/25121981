package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OderBy {

	 public static void printAL(Object[] fields) {  
         for(int i=0; i < fields.length; i++) {  
                 System.out.println(fields[i]);  
         }  
 }  
 public static void main(String[] s) {  
         ArrayList al = new ArrayList();  
         try {  
                 BufferedReader br = new BufferedReader(new FileReader("orderby.txt"));  
                 String line = br.readLine();  
                 while( line != null ) {  
                         StringTokenizer stk = new StringTokenizer(line,",");  
                         al.add(new field(stk.nextToken().trim(),stk.nextToken().trim(),stk.nextToken().trim()));  
                         line = br.readLine();  
                 }  
         } catch(Exception e) {  
                 e.printStackTrace();  
         }  
         Object[] fields = al.toArray();  
         System.out.println("Data before sorting");  
         printAL(fields);  
         Arrays.sort(fields,new OrderByComparator());  
         System.out.println("***************************************");  
         System.out.println("Data after sorting");  
         printAL(fields);  
 }  
}  

