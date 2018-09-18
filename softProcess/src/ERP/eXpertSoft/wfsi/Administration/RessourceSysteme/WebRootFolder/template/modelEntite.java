package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;



import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;

public class modelEntite {
	
	
	public static String doDectectTypeClasString(WebRootFolderBean  bFolderBean ) throws Exception{
		
		 
		String  val_retournee="String";
		try {
			
			if(bFolderBean.getType_name()==null || bFolderBean.getType_name().equals("")) 
				val_retournee= "";
			
			if(bFolderBean.getType_name().equals("character") ||  bFolderBean.getType_name().equals("varchar") 
					||  bFolderBean.getType_name().equals("longvarchar") )
				val_retournee="String";
			
			if(bFolderBean.getType_name().equals("numeric") ||  bFolderBean.getType_name().equals("decimal")  )
				val_retournee="BigDecimal";
			if(bFolderBean.getType_name().equals("numeric") ||  bFolderBean.getType_name().equals("decimal")  )
				val_retournee="BigDecimal";
			
			if(bFolderBean.getType_name().equals("bit") ||  bFolderBean.getType_name().equals("boolean")  )
				val_retournee="Boolean";
			
			if(bFolderBean.getType_name().equals("tinyint") || 
					bFolderBean.getType_name().equals("smallint")  ||  bFolderBean.getType_name().equals("integer")  ||  bFolderBean.getType_name().equals("int4") )
				val_retournee="Integer";
			
			if(bFolderBean.getType_name().equals("bigint")    )
				val_retournee="Long";
			
			if(bFolderBean.getType_name().equals("real")    )
				val_retournee="Float";
			
			if(bFolderBean.getType_name().equals("float")    )
				val_retournee="Double";
			
			if(bFolderBean.getType_name().equals("double precision")    )
				val_retournee="Double";
			
			if(bFolderBean.getType_name().equals("date")    )
				val_retournee="java.sql.Date";
			
			if(bFolderBean.getType_name().equals("time")    )
				val_retournee="java.sql.Time";
			
			if(bFolderBean.getType_name().equals("timestamp")    )
				val_retournee="java.sql.Timestamp";
			
			
		} catch (Exception e) {
			throw e;
		}
		 
	 
		
		return val_retournee;
		
	}

	public static void doWriteFile(String pathpackage,String  nameFile,WebRootFolderBean detailBean, ModelAndView model ){
		
		try {
			 File fichier = new File(nameFile);
			 if (! fichier.exists()) 
				 fichier.createNewFile();
			    PrintWriter out = new PrintWriter(new FileWriter(fichier));
				String[] tablaeau=pathpackage.split("\\.");
				String getNameEntite=tablaeau[tablaeau.length-1];
				out.write("package " + pathpackage+"model;");
				out.println();
				
				out.write("import java.math.BigDecimal;");out.println() ;

				out.write("import javax.persistence.Column;");out.println() ;
				out.write("import javax.persistence.Entity;");out.println() ;
				out.write("import javax.persistence.Id;");out.println() ;
				out.write("import javax.persistence.Table;");out.println() ;
				out.write("import org.codehaus.jackson.annotate.JsonAutoDetect;");out.println() ;
				out.write("import javax.persistence.EmbeddedId;");out.println() ;
				out.write("import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;");out.println() ;
				
				
				
				
				out.write("@JsonAutoDetect");out.println() ;
				out.write("@Entity");out.println() ;
				out.write("@Table(name = \""+detailBean.getTable_name()+"\", schema = \""+detailBean.getTable_schem()+"\")");out.println() ;
				out.write("public class  "+getNameEntite+"Bean   extends  GenericBean {");
				out.println() ;
				
				
				HashMap  mapDataPrimaryKey= (HashMap) model.getModel().get("mapDataPrimaryKey");
				HashMap  mapDataForeinKey= (HashMap) model.getModel().get("mapDataForeinKey");
				 
				
				 for (int i = 0; i < detailBean.getListAttributeEntite().size(); i++) {
						WebRootFolderBean bFolderBean=(WebRootFolderBean) detailBean.getListAttributeEntite().get(i);
						if(bFolderBean.getColumn_name()==null || bFolderBean.getColumn_name().equals(""))continue;
						
						String  type_Class =doDectectTypeClasString(bFolderBean);
						String deuxGill=type_Class.equals("String")?" =\"\"; ":";";
						
						if( i==0  ){
						//	if( i==0 &&  mapDataPrimaryKey!=null &&  (mapDataPrimaryKey.size()==1 || mapDataPrimaryKey.size()== 0 )){		
						out.write("@Id");out.println() ;
						out.write(" @Column	private "+type_Class+"  "+bFolderBean.getColumn_name()+""+deuxGill);out.println() ;
						continue;
						} 
						
						if( i==0 && mapDataPrimaryKey!=null &&  mapDataPrimaryKey.size()>1 ){
							out.write("@EmbeddedId");out.println() ; 
							out.write(" private  Pk"+getNameEntite+"  pk =  new   Pk"+getNameEntite+"();");out.println() ;
							continue;	
						}
						
						out.write(" @Column	private "+type_Class+"  "+bFolderBean.getColumn_name()+""+deuxGill);out.println() ;
					}
				 
				    if(   mapDataPrimaryKey!=null &&  mapDataPrimaryKey.size()>1 ){
				    out.write("	public void setPk (  Pk"+getNameEntite+"  pk ) {");out.println() ;
				    out.write("		this.pk=pk ; ");out.println() ;
				    out.write("	}");out.println() ;

				    out.write("	public  Pk"+getNameEntite+" getPk() {");out.println() ;
				    out.write("		return  pk;lllll");out.println() ;
				    out.write("	}");out.println() ;
				    }
				    
				    
				  
				 
				 for (int i = 0; i < detailBean.getListAttributeEntite().size(); i++) {
						WebRootFolderBean bFolderBean=(WebRootFolderBean) detailBean.getListAttributeEntite().get(i);
						if(bFolderBean.getColumn_name()==null || bFolderBean.getColumn_name().equals(""))continue;
						String  type_Class =doDectectTypeClasString(bFolderBean);
						
						//if( i==0 &&  mapDataPrimaryKey!=null &&   (mapDataPrimaryKey.size()==1 || mapDataPrimaryKey.size()== 0 ) ) continue;
						
						    out.write("	public void set"+StringUtils.capitalize(bFolderBean.getColumn_name()) +" ("+type_Class+"  "+bFolderBean.getColumn_name()+") {");out.println() ;
						    out.write("		this."+bFolderBean.getColumn_name()+" = "+bFolderBean.getColumn_name()+";");out.println() ;
						    out.write("	}");out.println() ;

						    out.write("	public "+type_Class+" get"+StringUtils.capitalize(bFolderBean.getColumn_name())+"() {");out.println() ;
						    out.write("		return "+bFolderBean.getColumn_name()+";");out.println() ;
						    out.write("	}");out.println() ;
					}
				  
				  out.write("	}");out.println() ;
			   

			   
			 
			 out.close() ;  
			} catch (Exception e) {
				e.getStackTrace();
			}
		
	}
	
public static void doWriteFilePkEntite(String pathpackage,String  nameFile,WebRootFolderBean detailBean, ModelAndView model ){
		
		try {
			 File fichier = new File(nameFile);
			 if (! fichier.exists()) 
				 fichier.createNewFile();
			    PrintWriter out = new PrintWriter(new FileWriter(fichier));
				String[] tablaeau=pathpackage.split("\\.");
				String getNameEntite=tablaeau[tablaeau.length-1];
				out.write("package " + pathpackage+".model;");
				out.println();
				
				out.write("import java.math.BigDecimal;");out.println() ;
				out.write("import javax.persistence.Column;");out.println() ;
				out.write("import javax.persistence.Entity;");out.println() ;
				out.write("import javax.persistence.Id;");out.println() ;
				out.write("import javax.persistence.Table;");out.println() ;
				out.write("import javax.persistence.Embeddable;");out.println() ;
				out.write("import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;");out.println() ;
				
				
				
				
				out.write("@JsonAutoDetect");out.println() ;
				out.write("@Embeddable ");out.println() ;
				out.write("public class  Pk"+getNameEntite+"  extends  GenericBean {");
				out.println() ;
				
				
				HashMap  mapDataPrimaryKey = (HashMap) model.getModel().get("mapDataPrimaryKey");
				HashMap  mapDataForeinKey  = (HashMap) model.getModel().get("mapDataForeinKey");
				 
				
				 for (int i = 0; i < detailBean.getListAttributeEntite().size(); i++) {
						WebRootFolderBean bFolderBean=(WebRootFolderBean) detailBean.getListAttributeEntite().get(i);
						if(bFolderBean.getColumn_name()==null || bFolderBean.getColumn_name().equals(""))continue;
						
						String  type_Class =doDectectTypeClasString(bFolderBean);
						String deuxGill=type_Class.equals("String")?" =\"\"; ":";";
						 
						 
						if(   mapDataPrimaryKey!=null &&  mapDataPrimaryKey.get(bFolderBean.getColumn_name())!=null ){
						out.write(" @Column	private "+type_Class+"  "+bFolderBean.getColumn_name()+""+deuxGill);out.println() ;
						}
					}
				 
				    
				    
				    
				  
				 
				 for (int i = 0; i < detailBean.getListAttributeEntite().size(); i++) {
						WebRootFolderBean bFolderBean=(WebRootFolderBean) detailBean.getListAttributeEntite().get(i);
						if(bFolderBean.getColumn_name()==null || bFolderBean.getColumn_name().equals(""))continue;
						String  type_Class =doDectectTypeClasString(bFolderBean);
						
						if(   mapDataPrimaryKey!=null &&  mapDataPrimaryKey.get(bFolderBean.getColumn_name())!=null ){
						
						    out.write("	public void set"+StringUtils.capitalize(bFolderBean.getColumn_name()) +" ("+type_Class+"  "+bFolderBean.getColumn_name()+") {");out.println() ;
						    out.write("		this."+bFolderBean.getColumn_name()+" = "+bFolderBean.getColumn_name()+";");out.println() ;
						    out.write("	}");out.println() ;

						    out.write("	public "+type_Class+" get"+StringUtils.capitalize(bFolderBean.getColumn_name())+"() {");out.println() ;
						    out.write("		return "+bFolderBean.getColumn_name()+";");out.println() ;
						    out.write("	}");out.println() ;
						}
					}
				  
				  out.write("	}");out.println() ;
			   

			   
			 
			 out.close() ;  
			} catch (Exception e) {
				e.getStackTrace();
			}
		
	}

}
