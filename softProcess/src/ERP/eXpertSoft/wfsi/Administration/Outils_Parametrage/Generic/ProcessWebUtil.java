package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;

public class ProcessWebUtil extends GenericWeb {

	void doCharger_Usr_and_Date(String fNameGen, Object ojexct, Field fiel1) throws Exception {

		try {

			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);

			if (fNameGen != null && fNameGen.equals("mode_op")) {
				BigDecimal FC = new BigDecimal(bs.getFct_id());
				fiel1.set(ojexct, FC);
			}
			if (fNameGen != null && fNameGen.equals("usr_cre")) {
				
				
				
				fiel1.set(ojexct, bs.getUsr_id() != null ? bs.getUsr_id() : "");
			}
			if (fNameGen != null && fNameGen.equals("date_cre")) {
				fiel1.set(ojexct, BDateTime.getDatesql());
			}
			if (fNameGen != null && fNameGen.equals("time_cre")) {
				fiel1.set(ojexct, BDateTime.getTime());
			}
			 
			if (fNameGen != null && fNameGen.equals("modeBean")) {
			 
				  Field fieldMode=ojexct.getClass().getDeclaredField("modeBean");
				  fieldMode.setAccessible(true);
				  Object obj_Into_Mode=fieldMode.get(ojexct);
				  
				  Field fieldFct=obj_Into_Mode.getClass().getDeclaredField("fct_id");
				  fieldFct.setAccessible(true);
				  BigDecimal FC = new BigDecimal(bs.getFct_id());
				  fieldFct.set(obj_Into_Mode, FC);
			}
			
			
		} catch (Exception e) {
			throw e;
		}

	}
	
	void doCharger_Etablissement_Societe_version2(String fNameGen, Object ojexct, Field fiel1) throws Exception {

		try {

			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
				
			 if(fNameGen!=null && ( fNameGen.equals("fk_etab_Bean") || fNameGen.equals("etablissment")   )){
			      Field fieldo=ojexct.getClass().getDeclaredField(fNameGen);
				  fieldo.setAccessible(true);
				  Object obj_first=fieldo.get(ojexct);
				  
				  Field field_pk_etab=obj_first.getClass().getDeclaredField("pk_etab");
				  field_pk_etab.setAccessible(true);
				  Object obj_pk_etab=field_pk_etab.get(obj_first);
				  
				  Field fieldoEtab=obj_pk_etab.getClass().getDeclaredField("etab_id");
				  fieldoEtab.setAccessible(true);
				  fieldoEtab.set(obj_pk_etab, bs.getEtab_id());
				  
				  
				  
				  Field fieldSoc_bean=obj_pk_etab.getClass().getDeclaredField("soc_bean");
				  fieldSoc_bean.setAccessible(true);
				  Object obj_Into_soc=fieldSoc_bean.get(obj_pk_etab);
				  
				  Field fieldsoc=obj_Into_soc.getClass().getDeclaredField("soc_id");
				  fieldsoc.setAccessible(true);
				  fieldsoc.set(obj_Into_soc, bs.getSoc_id());
		     }
		   
		} catch (Exception e) {
			throw e;
		}

	}
	
	
	public static String  doChargerSociete_Fetch( String alias    ) throws Exception {

		String requette="";
		try {
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 requette       = requette + "            AND   "+alias+".pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'     ";
		} catch (Exception e) {
			throw e;
		}
		return requette;

	}
	
	
	String  doCharger_Etablissement_Societe_Fetch( String alias  , boolean inclure_central  ) throws Exception {

		String requette="";
		
		try {
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 if(!inclure_central){
					  requette  = requette + "   AND   "+alias+".pk_etab.etab_id='"+bs.getEtab_id()+"' ";
			 }
			 if(inclure_central){
					  requette  = requette + "   AND   "+alias+".pk_etab.etab_id in ( '"+bs.getEtab_id()+"' , '"+bs.getEtab_central()+"'  ) ";
			 }
			          requette  = requette + "   AND   "+alias+".pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'     ";
			 
//			 if(GenericWeb.getBeanSession().getUsr_id().equals("1111")) 
//			    requette="";
		   
		} catch (Exception e) {
			throw e;
		}
		return requette;

	}
	
	String  doCharger_Societe_etabCentral_Fetch( String alias  , boolean inclure_central  ) throws Exception {

		String requette="";
		
		try {
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 if(inclure_central){
					  requette  = requette + "   AND   "+alias+".pk_etab.etab_id in ( '"+bs.getEtab_id()+"' , '"+bs.getEtab_central()+"'  ) ";
			 }
			 requette  = requette + "            AND   "+alias+".pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'     ";
//			 if(GenericWeb.getBeanSession().getUsr_id().equals("1111")) 
//				    requette="";
		} catch (Exception e) {
			throw e;
		}
		return requette;

	}
	
	
	
	
	public static String  doChargerSociete_Article(String alias  ) throws Exception {

		String requette="";
		
		try {
			   BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			   requette  = requette + "   AND  "+alias+".pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"' ";
		       
		} catch (Exception e) {
			throw e;
		}
		return requette;

	}
	
	
	
	String  doCharger_Etablissement_Societe_Article(String alias  , boolean inclure_central  ) throws Exception {

		String requette="";
		
		try {
			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 if(!inclure_central)
			  requette   = requette + "   AND  "+alias+".pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+bs.getEtab_id()+"' ";
			 if(inclure_central) 
			   requette  = requette + "   AND  "+alias+".pk.ar_bean.pk_article.etabBean.pk_etab.etab_id  in ( '"+bs.getEtab_id()+"' , '"+bs.getEtab_central()+"'  ) ";
			 
			   requette  = requette + "   AND  "+alias+".pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"' ";
		   
		} catch (Exception e) {
			throw e;
		}
		return requette;

	}
	
	  
	
	
	
	void doCharger_Etablissement_Societe_version1(String fNameGen, Object ojexct, Field fiel1) throws Exception {

		try {

			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);

			 if(fNameGen!=null && fNameGen.equals("etabBean") ){
			      Field fieldo=ojexct.getClass().getDeclaredField("etabBean");
				  fieldo.setAccessible(true);
				  Object obj=fieldo.get(ojexct);
				  Field fieldo2=obj.getClass().getDeclaredField("etab_id");
				  fieldo2.setAccessible(true);
				  //Object obj2=fieldo2.get(obj);
				  fieldo2.set(obj, bs.getEtab_id());
		   }
		   
		   if(fNameGen!=null && fNameGen.equals("socBean") ){
			      Field fieldo=ojexct.getClass().getDeclaredField("socBean");
				  fieldo.setAccessible(true);
				  Object obj=fieldo.get(ojexct);
				  Field fieldo2=obj.getClass().getDeclaredField("soc_id");
				  fieldo2.setAccessible(true);
				  //Object obj2=fieldo2.get(obj);
				  fieldo2.set(obj, bs.getSoc_id());
			     //fiel1.set(ojexct, bs.getEtab_id());
		   }
		   
		   
		} catch (Exception e) {
			throw e;
		}

	}

	public ProcessWebUtil() {
		super();
	}

	public void doCharger_Societe_id(String nameGen, Object ojexct, Field fiel1) throws Exception {
		try {

			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);

			 if(nameGen!=null && nameGen.equals("beanEtabFsociete") ){
			      Field fieldo=ojexct.getClass().getDeclaredField("beanEtabFsociete");
				  fieldo.setAccessible(true);
				  Object obj_first=fieldo.get(ojexct);
				  
				  Field field_pk_etab=obj_first.getClass().getDeclaredField("pk_etab");
				  field_pk_etab.setAccessible(true);
				  Object obj_pk_etab=field_pk_etab.get(obj_first);
				  
				  Field fieldSoc_bean=obj_pk_etab.getClass().getDeclaredField("soc_bean");
				  fieldSoc_bean.setAccessible(true);
				  Object obj_Into_soc=fieldSoc_bean.get(obj_pk_etab);
				  
				  Field fieldsoc=obj_Into_soc.getClass().getDeclaredField("soc_id");
				  fieldsoc.setAccessible(true);
				  fieldsoc.set(obj_Into_soc, bs.getSoc_id());
		     }
		   
		} catch (Exception e) {
			throw e;
		}
		
	}

}
