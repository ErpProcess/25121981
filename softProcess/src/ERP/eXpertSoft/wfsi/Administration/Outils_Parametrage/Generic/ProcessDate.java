package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProcessDate {

	/*public static void main(String[] args) throws ParseException {
	    java.util.Date fecha = new java.util.Date("Sat Jan 30 00:00:00 CET 2016");
	    //DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.);
	    Date date;
	    date = (Date)formatter.parse(fecha.toString());
	    System.out.println(date);        

	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    String formatedDate = cal.get(Calendar.DATE) + "/" + 
	            (cal.get(Calendar.MONTH) + 1) + 
	            "/" +         cal.get(Calendar.YEAR);
	    System.out.println("formatedDate : " + formatedDate);
	}*/

	public ProcessDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static String getCurrentTimeStamp(Date dat) throws Exception {
		
		
		
		try {
			
			 String ELmm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dat);
			 String annee=ELmm.substring(0, 4);
			 String mois=ELmm.substring(5, 7);
			 String jour=ELmm.substring(8, 10);
			 String mayDate=jour+"/"+mois+"/"+annee;
			 
			 //System.out.println(" ------------ Annne :"+ELmm.substring(0, 4)+   "-------------------------");
			// System.out.println(" ------------ mois  :"+ELmm.substring(5, 7)+   "-------------------------");
			// System.out.println(" ------------ jour  :"+ELmm.substring(8, 10)+  "-------------------------");
			
			return mayDate;
		    //return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dat);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static String getStringFormatDate(Date dat) throws Exception {
		try {
			 if(dat==null) return "";
			 String ELmm=new SimpleDateFormat("dd/MM/yyyy").format(dat);
		     return ELmm;
			
		} catch (Exception e) {
			throw e;
		}
	    //return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dat);
	}
	
	public static String getStringFormatDateRTL(Date dat) throws Exception {
		try {
			 if(dat==null) return "";
			 String ELmm=new SimpleDateFormat("yyyy/MM/dd").format(dat);
		     return ELmm;
			
		} catch (Exception e) {
			throw e;
		}
	    //return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dat);
	}
	
	
	
	public static boolean isStrictementSuperieur(Date date_bord_1 ,Date date_bord_2) throws Exception {
		try {
		 
			      
             int result = date_bord_1.compareTo(date_bord_2) ; 
             
             //if(  (result==0 ||  result==1)  &&  (result2==0 ||  result2==-1)   ){

             if(  result==1 )
            	 return true;
             else
            	 return false;
			
		} catch (Exception e) {
		 
			throw e;
		}
	}
	
	public static boolean isStrictementInferieur(Date date_bord_1 ,Date date_bord_2) throws Exception {
		try {
		 
			      
             int result = date_bord_1.compareTo(date_bord_2) ; 
             
             //if(  (result==0 ||  result==1)  &&  (result2==0 ||  result2==-1)   ){

             if(  result==-1 )
            	 return true;
             else
            	 return false;
			
		} catch (Exception e) {
		 
			throw e;
		}
	}
	
	
	public static String getTime(Date dat) {
		
		 String mayTime=new SimpleDateFormat("HH:mm:ss").format(dat);
		 
		 
				
		return mayTime;
	    //return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dat);
	}
	 
	
	public static Date ConverteDate() throws ParseException {
		    java.util.Date fecha = new java.util.Date("Mon Dec 15 00:00:00 CST 2014");
		    DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		    Date date;
		    date = (Date)formatter.parse(fecha.toString());
		    System.out.println(date);        
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    String formatedDate = cal.get(Calendar.DATE) + "/" +(cal.get(Calendar.MONTH) + 1) + "/" +cal.get(Calendar.YEAR);
		    System.out.println("formatedDate : " + formatedDate);
		    return null;
	}
	
	
	public static Date convert_String_to_Date(String  dateInString) throws ParseException {
		  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		  dateInString =  dateInString==null || dateInString.equals("")?"07/06/1750":dateInString;

		try {

			Date date = formatter.parse(dateInString);
			System.out.println(date);
			System.out.println(formatter.format(date));
			return date;

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
 
	  
	
	 
	 
	}

}

