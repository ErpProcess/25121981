package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.service;

 
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonPrimitive;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.dao.SpoorDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;

 


 



/**
 * Contact Service
 * 
 */
@Service
public class SpoorService {
	
	private SpoorDAO dAOSpoor;

	@Transactional
	public Boolean CreateRowData(SpoorBean beanSpoor){
		try {
			dAOSpoor.saveSpoor(beanSpoor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
	
	
	   @Transactional
		public void doCreateNewSpoorVersion1(Object  ojeTrace ,String[][] mapFieldBean, String []id_Entite, String table) throws Exception{
			try {
				
				 Object  beanTemplate =GenericWeb.getObjectValueModel(GenericWeb.NAME_TEMPLATE);
				 
				 Field field_is_Spoored = beanTemplate.getClass().getDeclaredField(GenericWeb.IS_SPOORED);
			     field_is_Spoored.setAccessible(true);
			     boolean is_Spoored= (Boolean) field_is_Spoored.get(beanTemplate);
			     
			     if(!is_Spoored) return;
			     
				 BeanSession  bs=(BeanSession) GenericWeb.getObjectValueModel(GenericWeb.BEAN_SESSION);
				 
				  
			       
				 SpoorBean sBean= new SpoorBean();
				 sBean.getSpoorPKBean().setSp_date(BDateTime.getDatesql());
				 sBean.getSpoorPKBean().setSp_time(BDateTime.getTime());
				 sBean.getSpoorPKBean().setUsr_login(bs.getUsr_id());
				 String  sp_id=dAOSpoor.findmaxinteger();
				 sBean.getSpoorPKBean().setSp_id(Integer.parseInt(sp_id));
				 sBean.setSous_mod(bs.getSousmod_id());
				 sBean.setFct_id(bs.getFct_id());
				 sBean.setEntite((String) table);
				 
				 String  lOp_id="";
				 String  lescolonne="";
				 for (int p = 0; p < id_Entite.length;p++) {
					 
					  Object element=	 GenericWeb.getValueOject_with_name_field(ojeTrace, id_Entite[p]);
					  lOp_id=lOp_id+element+"£";
			 		  lescolonne=lescolonne+id_Entite[p]+"£";
				 }
				 sBean.setOp_id(lOp_id.length()>0?lOp_id.substring(0, lOp_id.length()-1):""); 
				 sBean.setColonne(lescolonne.length()>0?lescolonne.substring(0, lescolonne.length()-1):"");
				 String detailOp="";
				   for(int i = 0; i < mapFieldBean.length; i++){
					   Object element=	 GenericWeb.getValueOject_with_name_field(ojeTrace, mapFieldBean[i][0]);
					   detailOp=detailOp+mapFieldBean[i][0]+":"+element+"£";
	              }
				 sBean.setDetail_op(detailOp.length()>0?detailOp.substring(0, detailOp.length()-1):"");
				 sBean.setMod_id(bs.getMod_id());
				 sBean.setPack_id(bs.getPack_id());
				 sBean.setSpack_id(bs.getSpack_id());
				 dAOSpoor.saveSpoor(sBean);
			} catch (Exception e) {
				throw  e;
			 
			}	

	}
	   
	   
	   @Transactional
		public void doCreateNewSpoorVersion_Master_detailles(Object  ojeTrace ,String[][] mapFieldBean, String []id_Entite, String table) throws Exception{
			try {
				
				 Object  beanTemplate =GenericWeb.getObjectValueModel(GenericWeb.NAME_TEMPLATE);
				 BeanSession  bs=(BeanSession) GenericWeb.getObjectValueModel(GenericWeb.BEAN_SESSION);
			       
				 SpoorBean sBean= new SpoorBean();
				 sBean.getSpoorPKBean().setSp_date(BDateTime.getDatesql());
				 sBean.getSpoorPKBean().setSp_time(BDateTime.getTime());
				 sBean.getSpoorPKBean().setUsr_login(bs.getUsr_id());
				 String  sp_id=dAOSpoor.findmaxinteger();
				 sBean.getSpoorPKBean().setSp_id(Integer.parseInt(sp_id));
				 sBean.setSous_mod(bs.getSousmod_id());
				 sBean.setFct_id(bs.getFct_id());
				 sBean.setEntite((String) table);
				 String  lOp_id="";
				 String  lescolonne="";
				 for (int p = 0; p < id_Entite.length;p++) {
					   if(id_Entite[p].indexOf(".")>0){
							
							final String[] lesColunmooo = id_Entite[p].split("\\.");
							
							 Object object=ojeTrace;
							
							    for (int k = 0; k < lesColunmooo.length; k++) {
							    	  
							    	 Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
							    	 fieldo.setAccessible(true);
							    	 if(k==lesColunmooo.length-1){
										  String  element1="";
								 			if( fieldo.get(object) == null ){
								 				element1="";
								 			}else{
								 				 if(fieldo.get(object).getClass().getName().equals("java.sql.Date") ||   fieldo.get(object).getClass().getName().equals("java.util.Date")  ){
													 System.out.println(" ------------ date   :   "+fieldo.get(object)+   "    -------------------------");
													 element1 =((String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(fieldo.get(object)))).toLowerCase(); 
												 }else{
													 element1 =((String)String.valueOf(fieldo.get(object))); 
												 }
								 			}
								 			lOp_id=lOp_id+element1+"£";
								 			lescolonne=lescolonne+lesColunmooo[k]+"£";
									  }else{
										  Object obj=fieldo.get(object);
										  object=obj;
									  }
									  
								}
					   }else{
						   
					   
			        	 
					     Field fielxxd = ojeTrace.getClass().getDeclaredField(id_Entite[p]);
					     fielxxd.setAccessible(true);
						
						 String  element="";
				 			if( fielxxd.get(ojeTrace) == null ){
				 				element="";
				 			}else{
				 				 if(fielxxd.get(ojeTrace).getClass().getName().equals("java.sql.Date")  ||   fielxxd.get(ojeTrace).getClass().getName().equals("java.util.Date") ){
									 element =((String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(fielxxd.get(ojeTrace))));
								 }else{
									 element =((String)String.valueOf(fielxxd.get(ojeTrace)));
								 }
				 			}
				 			
				 			lOp_id=lOp_id+element+"£";
				 			lescolonne=lescolonne+id_Entite[p]+"£";
					   } 
					 
					 
				 }
				 sBean.setOp_id(lOp_id.length()>0?lOp_id.substring(0, lOp_id.length()-1):""); 
				 sBean.setColonne(lescolonne.length()>0?lescolonne.substring(0, lescolonne.length()-1):"");
				 String detailOp="";
				 
				   for(int i = 0; i < mapFieldBean.length; i++){
			        	 
					   if(mapFieldBean[i][0].indexOf(".")>0){
							
							final String[] lesColunmooo = mapFieldBean[i][0].split("\\.");
							
							 Object object=ojeTrace;
							
							    for (int k = 0; k < lesColunmooo.length; k++) {
							    	  
							    	 Field fieldo=object.getClass().getDeclaredField(lesColunmooo[k]);
							    	 fieldo.setAccessible(true);
							    	 if(k==lesColunmooo.length-1){
										  String  element1="";
								 			if( fieldo.get(object) == null ){
								 				element1="";
								 			}else{
								 				 if(fieldo.get(object).getClass().getName().equals("java.sql.Date") ||   fieldo.get(object).getClass().getName().equals("java.util.Date") ){
													 System.out.println(" ------------ date   :   "+fieldo.get(object)+   "    -------------------------");
													 element1 =((String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(fieldo.get(object)))).toLowerCase(); 
												 }else{
													 element1 =((String)String.valueOf(fieldo.get(object))); 
												 }
								 			}
								 			
								 			 detailOp=detailOp+lesColunmooo[k]+"¤"+element1+"£";
									  }else{
										  Object obj=fieldo.get(object);
										  object=obj;
									  }
									  
								}
					   }else{
						   
					   
			        	 
					     Field fielxxd = ojeTrace.getClass().getDeclaredField(mapFieldBean[i][0]);
					     fielxxd.setAccessible(true);
						
						 String  element="";
				 			if( fielxxd.get(ojeTrace) == null ){
				 				element="";
				 			}else{
				 				 if(fielxxd.get(ojeTrace).getClass().getName().equals("java.sql.Date")  ||   fielxxd.get(ojeTrace).getClass().getName().equals("java.util.Date")  ){
									 element =((String)String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(fielxxd.get(ojeTrace))));
								 }else{
									 element =((String)String.valueOf(fielxxd.get(ojeTrace)));
								 }
				 			}
				 			 detailOp=detailOp+mapFieldBean[i][0]+"¤"+element+"£";
					   }
	              }
				 sBean.setDetail_op(detailOp.length()>0?detailOp.substring(0, detailOp.length()-1):"");
				 sBean.setMod_id(bs.getMod_id());
				 sBean.setPack_id(bs.getPack_id());
				 sBean.setSpack_id(bs.getSpack_id());
				 dAOSpoor.saveSpoor(sBean);
			} catch (Exception e) {
				throw  e;
			 
			}	

	}
		
	   @Transactional
		public void doCreateNewSpoor(Object  ojeTrace ) throws Exception{
			try {
				
				 Object  beanTemplate =GenericWeb.getObjectValueModel(GenericWeb.NAME_TEMPLATE);
				 
				 Field field_is_Spoored = beanTemplate.getClass().getDeclaredField(GenericWeb.IS_SPOORED);
			     field_is_Spoored.setAccessible(true);
			     boolean is_Spoored= (Boolean) field_is_Spoored.get(beanTemplate);
			     
			     if(!is_Spoored) return;
			     
				 BeanSession  bs=(BeanSession) GenericWeb.getObjectValueModel(GenericWeb.BEAN_SESSION);
				 
				 
				 Field fieldEntite = beanTemplate.getClass().getDeclaredField(GenericWeb.ENTITES);
				 fieldEntite.setAccessible(true);
			     Object entite= fieldEntite.get(beanTemplate);
			     
			    
			     Field ID_ENTITE_Entite = beanTemplate.getClass().getDeclaredField(GenericWeb.ID_ENTITE);
			     ID_ENTITE_Entite.setAccessible(true);
			     String IDentite= (String) ID_ENTITE_Entite.get(beanTemplate);
			     
			     
			     Field IMAP_FIELD_BEAN_Entite = beanTemplate.getClass().getDeclaredField(GenericWeb.MAP_FIELD_BEAN);
			     IMAP_FIELD_BEAN_Entite.setAccessible(true);
			     String[ ][ ] mapFieldBean= (String[][]) IMAP_FIELD_BEAN_Entite.get(beanTemplate);
			     
			    
			       
				 SpoorBean sBean= new SpoorBean();
				 sBean.getSpoorPKBean().setSp_date(BDateTime.getDatesql());
				 sBean.getSpoorPKBean().setSp_time(BDateTime.getTime());
				 sBean.getSpoorPKBean().setUsr_login(bs.getUsr_id());
				 String  sp_id=dAOSpoor.findmaxinteger();
				 sBean.getSpoorPKBean().setSp_id(Integer.parseInt(sp_id));
				 sBean.setSous_mod(bs.getSousmod_id());
				 sBean.setFct_id(bs.getFct_id());
				 sBean.setEntite( String.valueOf(entite) );
				 Object element_id=	 GenericWeb.getValueOject_with_name_field(ojeTrace, IDentite);
				 sBean.setOp_id((String)element_id); 
				 sBean.setColonne((String)IDentite);
				 String detailOp="";
				   for(int i = 0; i < mapFieldBean.length; i++){
					   Object element_det=	 GenericWeb.getValueOject_with_name_field(ojeTrace, mapFieldBean[i][0]);
					   detailOp=detailOp+mapFieldBean[i][0]+":"+element_det+"£";
	              }
				 sBean.setDetail_op(detailOp.length()>0?detailOp.substring(0, detailOp.length()-1):"");
				 sBean.setMod_id(bs.getMod_id());
				 sBean.setPack_id(bs.getPack_id());
				 sBean.setSpack_id(bs.getSpack_id());
				 dAOSpoor.saveSpoor(sBean);
			} catch (Exception e) {
				throw  e;
			 
			}	

	}
	@Transactional
	public Boolean UpdateRowData(SpoorBean SpoorBean){
		if(dAOSpoor.updateSpoor(SpoorBean))
			return true;
		else
			return false;
			
	}
	
	
	@Transactional
	public Boolean DeleteRowData(SpoorBean SpoorBean){
		if(dAOSpoor.deleteSpoor(SpoorBean))
			return true;
		else
			return false;
			
	}
	/**
	 * Get all packageSyss
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<SpoorBean> getListDataServer(SpoorBean SpoorBean){
		return dAOSpoor.getSpoor(SpoorBean);
	}
	
	 
	
	@Autowired
	public void setSpoorDAO(SpoorDAO dAOSpoor) {
		this.dAOSpoor = dAOSpoor;
	}



	
}
