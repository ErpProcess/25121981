package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.commons.lang.StringUtils;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.framework_dev.processClass.NombreEuro;


public class ProcessFormatNbr  extends GenericWeb  {
	
	public ProcessFormatNbr() {
		super();
		 
	}
	
     


	public static double  FormatDouble_Troischiffre(Double decimal) throws Exception{
		
		try{
			  BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			  if(decimal==null)decimal=new Double(0);
		      DecimalFormat decimForm=new DecimalFormat(bs.getPatternDecimalFormat());
		      DecimalFormatSymbols decimFromatSymb=decimForm.getDecimalFormatSymbols();
	          decimFromatSymb.setDecimalSeparator('.');
	          decimForm.setDecimalFormatSymbols(decimFromatSymb);
	          double formatDecimal = new Double( decimForm.format(decimal)).doubleValue();
	          return (double)  Math.round(formatDecimal * Math.pow(10,3)) / Math.pow(10,3);
	          
	      }
	      catch(Exception e){
	    	  throw e;
	      }
	}
	
    public static double  FormatDouble_ParameterChiffre(Double decimal,String pattern) throws Exception{
		
    	
		try{
			  BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			  if(decimal==null)decimal=new Double(0);
			 
		      DecimalFormat decimForm=new DecimalFormat(pattern);
		      DecimalFormatSymbols decimFromatSymb=decimForm.getDecimalFormatSymbols();
	          decimFromatSymb.setDecimalSeparator('.');
	          decimForm.setDecimalFormatSymbols(decimFromatSymb);
	          double formatDecimal = new Double( decimForm.format(decimal)).doubleValue();
	          if(pattern.equals("0.000")){
	            return (double)  Math.round(formatDecimal * Math.pow(10,3)) / Math.pow(10,3);
	          }else if(pattern.equals("0.00")){
	            return (double)  Math.round(formatDecimal * Math.pow(10,2)) / Math.pow(10,2);
	          }else{
	            return (double)  Math.round(formatDecimal * Math.pow(10,1)) / Math.pow(10,1);
	          }
	      }
	      catch(Exception e){
	    	  throw e;
	      }
	}
	
   public static double  FormatDouble_Deuxchiffre(Double decimal) throws Exception{
		
		try{
			  if(decimal==null)decimal=new Double(0);
		      DecimalFormat decimForm=new DecimalFormat("0.00");
		      DecimalFormatSymbols decimFromatSymb=decimForm.getDecimalFormatSymbols();
	          decimFromatSymb.setDecimalSeparator('.');
	          decimForm.setDecimalFormatSymbols(decimFromatSymb);
	          double formatDecimal = new Double( decimForm.format(decimal)).doubleValue();
	          return (double)  Math.round(formatDecimal * Math.pow(10,2)) / Math.pow(10,2);
	          
	      }
	      catch(Exception e){
	    	  throw e;
	      }
	}
	
   public static double  FormatDouble_Unchiffre(Double decimal) throws Exception{
		
		try{
			  if(decimal==null)decimal=new Double(0);
		      DecimalFormat decimForm=new DecimalFormat("0.0");
		      DecimalFormatSymbols decimFromatSymb=decimForm.getDecimalFormatSymbols();
	          decimFromatSymb.setDecimalSeparator('.');
	          decimForm.setDecimalFormatSymbols(decimFromatSymb);
	          double formatDecimal = new Double( decimForm.format(decimal)).doubleValue();
	          return (double)  Math.round(formatDecimal * Math.pow(10,3)) / Math.pow(10,3);
	          
	      }
	      catch(Exception e){
	    	  throw e;
	      }
	}
	
	
	public static double  FormatDouble_Decimal(Double decimal) throws Exception{
		
		 
		
		
	      try{
	    	  BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
	    	  
	    	  if(decimal==null)decimal=new Double(0);
		      DecimalFormat decimForm=new DecimalFormat(bs.getPatternDecimalFormat());
		      DecimalFormatSymbols decimFromatSymb=decimForm.getDecimalFormatSymbols();
		      
	          decimFromatSymb.setDecimalSeparator('.');
	          decimForm.setDecimalFormatSymbols(decimFromatSymb);
	          double formatDecimal = new Double( decimForm.format(decimal)).doubleValue();
	          double valdecimal= (double)Math.round(formatDecimal * Math.pow(10,3)) / Math.pow(10,3);
	          
	          return valdecimal*1000;
	          
	      }
	      catch(Exception e){
	    	  throw e;
	      }
	}
	
	public static String convertDoubleToIntString(Double valeur) throws Exception {
		try {
			String value = "";
			if (valeur != null) {
				int iddd = Integer.valueOf(valeur.intValue());
				value = String.valueOf(iddd);
			}
			return value;

		} catch (Exception e) {
			throw e;
		}

	}

	public static String  addPourcentage(String valeur) throws Exception{
	      try{
	    	  String valuer=!StringUtils.isEmpty(valeur)? valeur :"0";
	    	  double doub=Double.valueOf(valuer).doubleValue();
	    	  if(doub>0)
	          return valeur+"%";
	    	  else
	    	  return "";   
	      }
	      catch(Exception e){
	    	  throw e;
	      }
	}
	
	
	public static String  Convert_using_Double_toString(double number) throws Exception{
	      try{
	    	 
	    	  String numberAsString = Double.toString(number);
	          return numberAsString;
	      }
	      catch(Exception e){
	    	  throw e;
	      }
	}
	
	
	
	
	public static String  FormatDouble_To_String_Troischiffre(double number) throws Exception{
	     
	      try{
	    	    BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
	    	    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	    		decimalFormatSymbols.setDecimalSeparator('.');
	    		decimalFormatSymbols.setGroupingSeparator(' ');
	    		DecimalFormat decimalFormat = new DecimalFormat("#,##"+bs.getPatternDecimalFormat(), decimalFormatSymbols);
	    		Double numberTrchifffre= FormatDouble_Troischiffre(number);
	    		return (decimalFormat.format(numberTrchifffre)); //1,237,516.25
	    		
	    		
	      }catch(Exception e){
	    	  throw e;
	      }
	 
		
	}
	
	
	public static String  FormatDouble_To_String_PatternChiffre(double number,String pattern) throws Exception{
	     
	      try{
	    	    if(number==0) return "";
	    	    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	    		decimalFormatSymbols.setDecimalSeparator('.');
	    		decimalFormatSymbols.setGroupingSeparator(' ');
	    		DecimalFormat decimalFormat = new DecimalFormat("#,##"+pattern, decimalFormatSymbols);
	    		Double numberTrchifffre= FormatDouble_Troischiffre(number);
	    		return (decimalFormat.format(numberTrchifffre)); //1,237,516.25
	    		
	      }catch(Exception e){
	    	  throw e;
	      }
	}
	
	
	public static String  FormatDouble_To_String_PatternChiffrePrefixes(double number,DeviseBean devise,boolean prefix, boolean suffix) throws Exception{
	     
	      try{
	    	   if(devise==null) return "0";
	    	    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	    		decimalFormatSymbols.setDecimalSeparator('.');
	    		decimalFormatSymbols.setGroupingSeparator(' ');
	    		DecimalFormat decimalFormat = new DecimalFormat("#,##"+devise.getChiffre_pattern(), decimalFormatSymbols);
	    		Double numberTrchifffre= FormatDouble_Troischiffre(number);
	    		if(prefix)  return devise.getSymbole_monetaire()+" "+(decimalFormat.format(numberTrchifffre));  
	    		else if(suffix)  return (decimalFormat.format(numberTrchifffre))+" "+devise.getSymbole_monetaire();  
	    		else return (decimalFormat.format(numberTrchifffre)); //1,237,516.25
	      }catch(Exception e){
	    	  throw e;
	      }
	}
	
	public static String  FormatDouble_To_String_DeuxChiffre(double number) throws Exception{
	     
	      try{
	    	   
	    	    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	    		decimalFormatSymbols.setDecimalSeparator('.');
	    		decimalFormatSymbols.setGroupingSeparator(' ');
	    		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", decimalFormatSymbols);
	    		Double numberTrchifffre= FormatDouble_Deuxchiffre(number);
	    		return (decimalFormat.format(numberTrchifffre)); //1,237,516.25
	    		
	      }catch(Exception e){
	    	  throw e;
	      }
	 
		
	}
	
	
	public static String  FormatDouble_To_String_UnChiffre(double number) throws Exception{
	     
	      try{
	    	   
	    	    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	    		decimalFormatSymbols.setDecimalSeparator('.');
	    		decimalFormatSymbols.setGroupingSeparator(' ');
	    		DecimalFormat decimalFormat = new DecimalFormat("#,##0.0", decimalFormatSymbols);
	    		Double numberTrchifffre= FormatDouble_Unchiffre(number);
	    		return (decimalFormat.format(numberTrchifffre)); //1,237,516.25
	    		
	      }catch(Exception e){
	    	  throw e;
	      }
	 
		
	}
	 
	
	
	public static Double  ConvertStringToDouble(String Montant) throws Exception{
	     
	      try{
	    	  if(Montant==null || Montant.equals(""))Montant="0";
	    	    Montant=Montant.replace(",", "");
	    	    Montant=Montant.replace(" ", "");
	    	    Double double_val = Double.valueOf(Montant);
	    		return double_val;  
	      }
	      catch(Exception e){
	    	  throw e;
	      }
	 
		
	}
	
	
	public static String  convertNumberToLetterDT(double number) throws Exception{ 
		String s="";
		try {
			String ma_lettre = Nombre.CALCULATE.getValue(number,"euro");
			System.out.println(ma_lettre);	
			s = ma_lettre;
			s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
		} catch (Exception e) {
			throw e;
		}
		return  s;
	  }
	
	
	public static String  convertNumberToLetterEURO(double number) throws Exception{ 
		String s="";
		try {
			String ma_lettre = NombreEuro.CALCULATE.getValue(number,"euro");
			System.out.println(ma_lettre);	
			s = ma_lettre;
			s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
		} catch (Exception e) {
			throw e;
		}
		return  s;
	  }
	  

		public static void main(String[] args) {
			
			double decimal = 7.27467;
			System.out.println("The test number: "+decimal);
			int decimalPlaces = 3;	// the scale for the decimal
			
			// use of BigDecimal class
			BigDecimal bd = new BigDecimal(decimal);

			// set the scale and round up if >= 0.5
			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_UP);
			double bigDecimal = bd.doubleValue();
			System.out.println("BigDecimal rounded in 3rd decimal: "+bigDecimal);
			
			// use of DecimalFormat
			DecimalFormat decFormat = new DecimalFormat("#.00");
			double formatDecimal = new Double(decFormat.format(decimal)).doubleValue();
			System.out.println("DecimalFormat rounded in 2nd decimal: "+formatDecimal);
			
			System.out.println("--------------------------------------");
			
			DecimalFormat numFormat;
			String number;
			
			// 2 digits before decimal point and then 2 digits (rounded)
			numFormat = new DecimalFormat("000.##");
			number = numFormat.format(-15.567);
			System.out.println("1. DecimalFormat with .: " + number);
			
			// string '$' in front of the number
			numFormat = new DecimalFormat("'$'00.####");
			number = numFormat.format(15.567);
			System.out.println("2. DecimalFormat with '$': " + number);
			
			// use of , to group numbers
			numFormat = new DecimalFormat("#,###,###");
			number = numFormat.format(1556789);
			System.out.println("3. DecimalFormat with ,: " + number);

			// use of % for percentage
			numFormat = new DecimalFormat("%");
			number = numFormat.format(0.15);
			System.out.println("4. DecimalFormat with percentage: " + number);
			
			// 2 digits before decimal point and 2 digits after
			numFormat = new DecimalFormat("00.00");
			number = numFormat.format(-15.567);
			System.out.println("5. DecimalFormat with 4 digits: " + number);
			
			// left part of decimal number
			numFormat = new DecimalFormat("##");
			number = numFormat.format(156.567);
			System.out.println("6. DecimalFormat with no decimal part: " + number);
			
			// 5 or less digits in the decimal part
			numFormat = new DecimalFormat(".#####");
			number = numFormat.format(1890.567);
			System.out.println("7. DecimalFormat with 5 or less digits (in decimal part): " + number);
			
			// string 'JCG' in front of the number
			numFormat = new DecimalFormat("'JCG'000.#");
			number = numFormat.format(15.567);
			System.out.println("8. DecimalFormat with 'JCG': " + number);
		}

	}