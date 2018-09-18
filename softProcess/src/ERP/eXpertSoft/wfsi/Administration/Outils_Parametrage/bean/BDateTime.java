package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean;

import java.sql.Time;
import java.util.Date;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
 

 

public class BDateTime {
	
	static String process_date_heur = "dd/MM/yyyy H:mm:ss"; 
	static String process_date = "dd/MM/yyyy"; 
	static String process_heur = "H:mm:ss"; 
  
	public  static String getDateActuel_system() {
		 java.text.SimpleDateFormat formater_Dat       = new java.text.SimpleDateFormat( process_date );
		 java.util.Date today    = new java.util.Date();
		return   formater_Dat.format( today );
	}
	
	public  static Date getDateActuel() {
		 String dateInString="";
		 Date ddd=null;
		try {
		 java.text.SimpleDateFormat formater_Dat       = new java.text.SimpleDateFormat( process_date );
		 java.util.Date today    = new java.util.Date();
		 dateInString= formater_Dat.format( today );
		 ddd=ProcessDate.convert_String_to_Date(dateInString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  ddd;
	}
 
	
	public  static String getDateheurActuel() {
		java.text.SimpleDateFormat formater_date_heur = new java.text.SimpleDateFormat( process_date_heur );
		 java.util.Date today    = new java.util.Date();
		return   formater_date_heur.format( today );
	}
	
	
	public  static String getHeurActuel() {
		java.text.SimpleDateFormat formater_heur      = new java.text.SimpleDateFormat( process_heur );
		 java.util.Date today    = new java.util.Date();
		return  formater_heur.format( today );
	}
  
 
	public  static Time getTime() {
		 java.util.Date today    = new java.util.Date();
		 String heure = new java.text.SimpleDateFormat( "H" ).format(today);
		 String minutes = new java.text.SimpleDateFormat( "mm" ).format(today);
		 String secondes = new java.text.SimpleDateFormat( "ss" ).format(today);
		 return java.sql.Time.valueOf(heure+":"+minutes+":"+secondes);
	}
	 
	public  static java.sql.Date getDatesql() {
		 java.util.Date today    = new java.util.Date();
		 return new java.sql.Date(today.getTime());
	}

	public  static String getTime_String() {
		 java.util.Date today    = new java.util.Date();
		 String heure = new java.text.SimpleDateFormat( "H" ).format(today);
		 String minutes = new java.text.SimpleDateFormat( "mm" ).format(today);
		 String secondes = new java.text.SimpleDateFormat( "ss" ).format(today);
		 return  heure+":"+minutes+":"+secondes;
	}

	public  static String getHeureString() {
		 java.util.Date today    = new java.util.Date();
		 return new java.text.SimpleDateFormat( "H" ).format(today);
	}
	
	public  static String getMinutesString() {
		 java.util.Date today    = new java.util.Date();
		 return new java.text.SimpleDateFormat( "mm" ).format(today);
	}
	
	public  static String getSecondesString() {
		 java.util.Date today    = new java.util.Date();
		 return new java.text.SimpleDateFormat( "ss" ).format(today);
	}
	
	public  static String getJourString() {
		 java.util.Date today    = new java.util.Date();
		 return new java.text.SimpleDateFormat( "dd" ).format(today);
	}
	 
	public  static String getMoisString() {
		 java.util.Date today    = new java.util.Date();
		 return new java.text.SimpleDateFormat( "MM" ).format(today);
	}
	
	public  static String getAnneeString() {
		 java.util.Date today    = new java.util.Date();
		 return new java.text.SimpleDateFormat( "yyyy" ).format(today);
	}
	 
	public  static String getDateActuelF1() {
		 return getJourString().concat("-".concat(getMoisString().concat("-".concat(getAnneeString()))));
	}
	
	public  static String getDateActuelF3() {
		 return getAnneeString().concat("-".concat(getMoisString().concat("-".concat(getJourString()))));
	}
	
 
	
	  public static void main(String[] args) {
		String format = "dd/MM/yyyy H:mm:ss"; 
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 
	}

	public BDateTime() {
		super();
		 
	}

	
}
