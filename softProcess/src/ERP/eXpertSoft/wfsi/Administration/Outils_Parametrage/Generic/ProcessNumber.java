package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;

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


	public static Double getMontantTvaByMontantHT(Double montantHTva, TVABean tvaBean, DeviseBean devise) throws Exception {
		Double   tvaValue          = ProcessNumber.DIVISION(tvaBean.getTva_value(), 100) ;
		Double valeur_de_laTva     = ProcessNumber.PRODUIT(montantHTva,tvaValue);
		return valeur_de_laTva;
	}
	
	public static Double getMontantHTByMontantTTC(Double montantTTC, TVABean tvaBean, DeviseBean devise) throws Exception {
		Double   tvaValue        = ProcessNumber.DIVISION(tvaBean.getTva_value(), 100) ;
		Double   MntUnitTTC      = ProcessNumber.addition(1.000, tvaValue) ;
		Double   montantHT       = ProcessNumber.DIVISION(montantTTC,MntUnitTTC);
		return montantHT;
	}
	

}
