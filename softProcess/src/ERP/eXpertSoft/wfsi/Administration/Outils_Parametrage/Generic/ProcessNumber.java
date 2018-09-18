package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;



public class ProcessNumber {
	
	
	public static <T extends Number> double addition (T one, T two) throws Exception{
		try {
			  if(one==null)one= (T) new Double(0);
			  if(two==null)one= (T) new Double(0);
			
			 return one.doubleValue() + two.doubleValue(); 
			
		} catch (Exception e) {
			throw e;
		}
	   
	}
	
	
	public static <T extends Number> double PRODUIT (T one, T two) throws Exception{
		try {
			
			 if(one==null)one= (T) new Double(0);
			  if(two==null)one= (T) new Double(0);
			  
			 return one.doubleValue() * two.doubleValue(); 
			
		} catch (Exception e) {
			throw e;
		}
	   
	}
	
	public static <T extends Number> double SOUSTRACTION (T one, T two) throws Exception{
		try {
			
			 if(one==null)one= (T) new Double(0);
			  if(two==null)one= (T) new Double(0);
			 
			 return one.doubleValue() - two.doubleValue(); 
			
		} catch (Exception e) {
			throw e;
		}
	   
	}
	
	public static <T extends Number> double DIVISION (T one, T two) throws Exception{
		try {
			
			
			 if(one==null)one= (T) new Double(1);
			  if(two==null)one= (T) new Double(1);
			 
			 return one.doubleValue() / two.doubleValue(); 
			
		} catch (Exception e) {
			throw e;
		}
	   
	}
	
	public static <T extends Number> double Pourcentage (T one, T pourcentage) throws Exception{
		try {
			 Double val1=PRODUIT(one, pourcentage);
			 val1=ProcessFormatNbr.FormatDouble_Troischiffre(val1);
			 Double val2=DIVISION(val1, 100);
			 val2=ProcessFormatNbr.FormatDouble_Troischiffre(val2);
			 return val2;
			
		} catch (Exception e) {
			throw e;
		}
	   
	}
	
	public static double doMath(Double one, Double two, char op ) throws Exception{

		try {
	    switch(op){
	    case '+':
	        return  addition(one, two);  
	    case '-':
	        return  SOUSTRACTION(one, two)  ;
	    case '*':
	        return PRODUIT(one, two);
	       // break;
	    case '/':
	        return DIVISION(one, two);
	        //break;
	    default:
	        return 0;
	    }
	    
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}  
	}
	

}
