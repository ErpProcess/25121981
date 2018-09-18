package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean;
import java.util.*;  
import java.io.*;  

public class OrderByComparator  implements Comparator    {

	
	  
	  
	  
	        public int compare(Object o1,Object o2) {  
	                field f1 = (field)o1;  
	                field f2 = (field)o2;  
	                int i = f1.getLname().compareTo(f2.getLname());  
	                int j = f1.getFname().compareTo(f2.getFname());  
	                int k = f1.getMi().compareTo(f2.getMi());  
	                int retStatus=0;  
	                if ( i < 0 ) {  
	                        retStatus = -1;  
	                } else if ( i > 0 ) {  
	                        retStatus = 1;  
	                } else {  
	                        if ( j < 0 ) {  
	                                retStatus = -1;  
	                        } else if ( j > 0 ) {  
	                                retStatus = 1;  
	                        } else {  
	                                if ( k < 0 ) {  
	                                        retStatus = -1;  
	                                } else if( k > 0 ){  
	                                        retStatus = 1;  
	                                } else {  
	                                        retStatus = 0;  
	                                }  
	                        }  
	                }  
	                return retStatus;  
	        }  
	}  
	
	 
