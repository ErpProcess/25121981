package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DynamicClass {

	@SuppressWarnings("unchecked")
	public Object castDynamicClassX(String className, String value) {
		Class<?> dynamicClass;
		Object object = null ;
		try {
			 boolean inter=true;
			 if(value==null)value="";
			
			 if( Date.class.isAssignableFrom(Class.forName(className))  &&  value.equals("")){
				 object=null;
				 inter=false;
			 }
			 
			 if( Date.class.isAssignableFrom(Class.forName(className))  &&  !value.equals("")){
          	    String pattern = "dd/MM/yyyy";
          	    SimpleDateFormat format = new SimpleDateFormat(pattern);
          	    Date dateDDDD = format.parse(value);
          	    inter=false;
          	    object=dateDDDD;
			 }
			 
			if( Number.class.isAssignableFrom(Class.forName(className))  &&  value.equals("")){
				 object="0";
			}
			if(inter){	 
			value = value.replaceAll(" ", "");
			if(value.equals("")) return "";
			dynamicClass = Class.forName(className);
			Constructor<?> cons = (Constructor<?>) dynamicClass.getConstructor(new Class<?>[] { String.class });
			object = (Object) cons.newInstance(new Object[] { value });
			}
			
			
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Object castDynamicClassObject_with_field( Field fieldo, Object objectIn) throws Exception {
		Class<?> dynamicClass;
		
		String className=fieldo.get(objectIn).getClass().getName();
		Object value=fieldo.get(objectIn);
		try {
			  if(value== null) return "";
			  
			  if(  className.equals("java.sql.Time")  ){
				  
					 //System.out.println( className+"***************"+ fieldo.getName()+ "first>>>>>>:"+fieldo.get(objectIn));
	           	   String date=ProcessDate.getTime((Date) fieldo.get(objectIn));
	           	   return date;
				  
			 
				  
		      }else if(className.equals("java.sql.Date")  || className.equals("java.util.Date") || className.equals("java.sql.Timestamp")  ){
            	  
            	   //System.out.println( className+"***************"+ fieldo.getName()+ "first>>>>>>:"+fieldo.get(objectIn));
            	 
            	   String date=ProcessDate.getCurrentTimeStamp((Date) fieldo.get(objectIn));
            	   //return fieldo.get(objectIn);
            	   //((String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(value))); 
            	   // return ((String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(value)));
            	   //System.out.println(  fieldo.getName()+ "first>>>>>>:"+fieldo.get(objectIn));
            	   //DateFormat parserS = new SimpleDateFormat("MM/dd/yyyy"); 
            	   //Date dateFr = (Date) parserS.parse(date);
            	   
            	    String pattern = "dd/MM/yyyy";
            	    SimpleDateFormat format = new SimpleDateFormat(pattern);
            	    Date dateDDDD = format.parse(date);
            	    //fieldo.setAccessible(true);
             	    //fieldo.set(objectIn,dateDDDD);
            	   
            	   
            	    //System.out.println(format.format(new Date()));
            	 
            	   
            	 
            	  // System.out.println(   "----dateFr>>>>>>:"+dateFr);
            	   return date;
				 
              }else if( className.equals("java.math.BigDecimal")  ){
            	  BigDecimal uuu=(BigDecimal) value;
            	  return value==null?"":uuu.toString();
              }else if(className.equals("java.lang.Integer")){	
            	  
            	  Integer uuu=(Integer) value;
            	  return value==null?"":Integer.toString(uuu);
            	  
			  }else{
				 dynamicClass = Class.forName(className); 
				 Constructor<?> cons = (Constructor<?>) dynamicClass.getConstructor(new Class<?>[] { String.class });
				 Object object = (Object) cons.newInstance(new Object[] { String.valueOf(value) });
				 return object;
			 }
		} catch (Exception e) {
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Object castDynamicClassObject(String className, Object value) throws Exception {
		Class<?> dynamicClass;
		try {
			  if(value== null) return "";
              if(className.equals("java.sql.Date")  || className.equals("java.util.Date") ){
            	  
            	  DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            	  String cunvertCurrentDate=(String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(value));
            	  Date date = new Date();
            	  date = df.parse(cunvertCurrentDate);
            	  
			  return  new SimpleDateFormat("dd/MM/yyyy").format(value) ;
				 
              }else if( className.equals("java.lang.Double")  ){
            	  
            	  Double uuu=(Double) value;
            	  return value==null?"":uuu;
            	  
              }else if( className.equals("java.math.BigDecimal")  ){
            	  
            	  BigDecimal uuu=(BigDecimal) value;
            	  return value==null?"":uuu;
            	  
              }else if(className.equals("java.lang.Integer")){	
            	  Integer uuu=(Integer) value;
            	  return value==null?"":uuu;
            	  
			  }else{
				 dynamicClass = Class.forName(className); 
				 Constructor<?> cons = (Constructor<?>) dynamicClass.getConstructor(new Class<?>[] { String.class });
				 Object object = (Object) cons.newInstance(new Object[] { String.valueOf(value) });
				 return object;
			 }
		} catch (Exception e) {
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Object castDynamicClassObjectV2(String className, Object value) throws Exception {
		Class<?> dynamicClass;
		try {
			  if(value== null) return "";
              if(className.equals("java.sql.Date")  || className.equals("java.util.Date") ){
            	  
            	  DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            	  String cunvertCurrentDate=(String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(value));
            	  Date date = new Date();
            	  date = df.parse(cunvertCurrentDate);
            	  
			  return  new SimpleDateFormat("dd/MM/yyyy").format(value) ;
				 
              }else if( className.equals("java.lang.Double")  ){
            	  
            	  Double uuu=(Double) value;
            	  return value==null?"":uuu;
            	  
              }else if( className.equals("java.math.BigDecimal")  ){
            	  
            	  BigDecimal uuu=(BigDecimal) value;
            	  return value==null?"":uuu;
            	  
              }else if(className.equals("java.lang.Integer")){	
            	  Integer uuu=(Integer) value;
            	  return value==null?"":uuu;
            	  
			  }else{
				 dynamicClass = Class.forName(className); 
				 Constructor<?> cons = (Constructor<?>) dynamicClass.getConstructor(new Class<?>[] { String.class });
				 Object object = (Object) cons.newInstance(new Object[] { String.valueOf(value) });
				 return object;
			 }
		} catch (Exception e) {
			throw e;
		}
	}
}
