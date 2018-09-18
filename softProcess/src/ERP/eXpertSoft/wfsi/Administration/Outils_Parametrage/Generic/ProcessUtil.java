package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
 
 
import java.util.List;
import java.util.Set;

public class ProcessUtil {

	public static void optimsedate(Object beantrie) throws Exception {
		try {
			for (Field fiel1 : beantrie.getClass().getDeclaredFields()) {
				fiel1.setAccessible(true);
				String className = fiel1.get(beantrie).getClass().getName();
				Object obj = fiel1.get(beantrie);
				if (className.equals("java.sql.Date") || className.equals("java.util.Date")) {
					System.out.println(fiel1.getName() + "sssssssssss" + obj);
					//fiel1.set(beantrie,  new SimpleDateFormat("dd/MM/yyyy").format(obj));
				}
			}
		} catch (Exception e) {
			throw e;
		}

	}

	

 
	
	public static Object cloneObject(Object obj) throws Exception {
		try {
			
			 
				Object clone = obj.getClass().newInstance();
			
			
			 
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (field.get(obj) == null || Modifier.isFinal(field.getModifiers()) 
						|| field.getType().equals(Collection.class) 
						|| field.getType().equals(ArrayList.class)
						|| field.getType().equals(List.class)
						|| field.getType().equals(Set.class)) {
					continue;
				}
				 else 
				
				if (field.getType().isPrimitive() || field.getType().equals(String.class) ||
						( field.getType().getSuperclass()!=null &&  field.getType().getSuperclass().equals(Number.class))
						|| field.getType().equals(Boolean.class)  
						|| field.getType().equals(Date.class) 
						|| field.getType().equals(java.sql.Date.class)
						|| field.getType().equals(java.sql.Timestamp.class)
						|| field.getType().equals(java.sql.Time.class)
						|| field.getType().equals(byte[].class)
						
				) {
					field.set(clone, field.get(obj));
				} else {
					
					Object childObj = field.get(obj);
					if (childObj == obj) {
						field.set(clone, clone); 
					} else {
						field.set(clone, cloneObject(field.get(obj)));
					}
					
				}
			}
			return clone;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static  Boolean isEmpty(Object source) {
		
		if(source==null) return true; else  return false;
		
	}
	 

	@SuppressWarnings("unchecked")
	public static <T> List<T> cloneList(List<T> source) {
		
		try {
			List<T> dest = new ArrayList<T>();
			for (T item : source) {
				if (item.getClass().isPrimitive() || item.getClass().equals(String.class) 
						||
						( item.getClass().getSuperclass()!=null &&  item.getClass().getSuperclass().equals(Number.class))
						|| item.getClass().equals(Boolean.class)  
						|| item.getClass().equals(Date.class) 
						|| item.getClass().equals(java.sql.Date.class)
						|| item.getClass().equals(java.sql.Timestamp.class)
						|| item.getClass().equals(java.sql.Time.class)
						|| item.getClass().equals(byte[].class)
				) {
					dest.add(item);
				}else{
					dest.add((T) cloneObject(item));
				}
				
			}
			return dest;
		 } catch (Exception e) {
			throw new RuntimeException("List cloning unsupported", e);
		}
		
		
	}

	public static  HashMap getHashMap_code_bean(List listData,String code )throws Exception {
		 
		HashMap  mapRetourn= new HashMap();
		try {
			for (int i = 0; i < listData.size(); i++) {
				Object beantrie=(Object) listData.get(i);
				Object key= GenericWeb.getValueOject_with_name_field(beantrie, code);
				mapRetourn.put(key, beantrie);
			}
			
		} catch (Exception e) {
			throw  e;
		}
		return mapRetourn;
	}
	
	public static  HashMap getHashMap_Key_List_FromList(List listData,String id)throws Exception {
		 
		HashMap  mapRetourn= new HashMap();
		try {
			for (int i = 0; i < listData.size(); i++) {
				Object beantrie=(Object) listData.get(i);
				Object key= GenericWeb.getValueOject_with_name_field(beantrie, id);
				List lis_el=(List) mapRetourn.get(key);
				if(lis_el==null)lis_el= new ArrayList();
				lis_el.add(beantrie);
				mapRetourn.put(key, lis_el);
			}
			
		} catch (Exception e) {
			throw  e;
		}
		return mapRetourn;
	}
	
	
	public static  HashMap getHashMap_code_libelle(List listData,String code ,String libelle)throws Exception {
		 
		HashMap  mapRetourn= new HashMap();
		try {
			for (int i = 0; i < listData.size(); i++) {
				Object beantrie=(Object) listData.get(i);
				Object key=   GenericWeb.getValueOject_with_name_field(beantrie, code);
				Object value= GenericWeb.getValueOject_with_name_field(beantrie, libelle);
				mapRetourn.put(key, value);
			}
			
		} catch (Exception e) {
			throw  e;
		}
		return mapRetourn;
	}
	
	@SuppressWarnings("unchecked")
	public static void CollectionSortAsc(List listData, final String lesColun) {

		Collections.sort(listData, new Comparator<Object>() {
			public int compare(Object bean, Object bean2) {

				try {
					if (lesColun.indexOf(".") > 0) {

						final String[] lesColunmooo = lesColun.split("\\.");
						Object object = bean;
						for (int k = 0; k < lesColunmooo.length; k++) {
							Field fieldo = object.getClass().getDeclaredField(
									lesColunmooo[k]);
							fieldo.setAccessible(true);
							Object obj = fieldo.get(object);
							object = obj;
						}

						String element1 = String.valueOf(object).toLowerCase();
						element1 = element1 != null ? element1 : "";

						Object object2 = bean2;
						for (int k = 0; k < lesColunmooo.length; k++) {
							Field fieldo = object2.getClass().getDeclaredField(
									lesColunmooo[k]);
							fieldo.setAccessible(true);
							Object obj = fieldo.get(object2);
							object2 = obj;

						}
						String element11 = String.valueOf(object2)
								.toLowerCase();
						element11 = element11 != null ? element11 : "";
						return String.valueOf(element1).compareTo(
								String.valueOf(element11)) * 1;

					} else {
						Field field = bean.getClass()
								.getDeclaredField(lesColun);
						field.setAccessible(true);
						String element = ((String) String.valueOf(field
								.get(bean))).toLowerCase();
						element = element != null ? element : "";
						Field field2 = bean2.getClass().getDeclaredField(
								lesColun);
						field2.setAccessible(true);
						String element2 = ((String) String.valueOf(field2
								.get(bean2))).toLowerCase();
						element2 = element2 != null ? element2 : "";
						return String.valueOf(element).compareTo(
								String.valueOf(element2)) * 1;

					}

				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				return 0;

			}
		});
		
		 
		

	}
	
	 

}
