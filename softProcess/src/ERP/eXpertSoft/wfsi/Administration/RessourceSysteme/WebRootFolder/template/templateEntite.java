package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;

public class templateEntite {
	
	


	public static void doWriteFile(String pathpackage,String  nameFile,WebRootFolderBean detailBean){
		
		try {
			 File fichier = new File(nameFile);
			 if (! fichier.exists()) 
				 fichier.createNewFile();
			    PrintWriter out = new PrintWriter(new FileWriter(fichier)) ; 
				String[] tablaeau=pathpackage.split("\\.");
				String getNameEntite=tablaeau[tablaeau.length-1];
				out.write("package " + pathpackage+"template;");
				out.println();
			 
				out.write("import "+pathpackage+"model."+getNameEntite+"Bean;");out.println() ;
				
			    out.write("import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.TemplateGeneric;");out.println();


			    out.write(" public class "+getNameEntite+"Template   extends   TemplateGeneric   {");out.println(); 
			 	
			    out.write("public  "+getNameEntite+"Template() {");out.println();
			    out.write("	super();");out.println();
			    out.write("}");out.println();
			 	
			    out.write("/*******************************************************************Config Entite*****************************************************************/");out.println();
			    out.write("public static final   String ROOT      = \""+detailBean.getSousmod_action()+"\";");out.println();
			    out.write("public static Object  NAME_MODEL_BEAN  = new "+getNameEntite+"Bean();");out.println();
			    out.write("public static Object  NAME_TEMPLATE    = new "+getNameEntite+"Template();");out.println();
			    out.write("public static boolean is_Spoored       = true;");out.println();
			    out.write("public static String  entites          = \""+detailBean.getTable_name()+"\";");out.println();
			    out.write("public static String  schema           = \""+detailBean.getTable_schem()+"\";");out.println();
			    
			    
				WebRootFolderBean bFolderBean_id_entite=(WebRootFolderBean) detailBean.getListAttributeEntite().get(0);
					 
			    
			    out.write("public static String  id_entite        = \""+bFolderBean_id_entite.getColumn_name()+"\";");out.println();
			 	
			 	
			    out.write("/********************************************************************Data Grid********************************************************************/ ");out.println();
			    out.write("public static String  LIST_DATA         = \""+getNameEntite+"-List\";");out.println();
			    out.write("public static String  NAME_GRID         = \""+getNameEntite+"-Grid\";");out.println();
			    String dataReporting="";
			    for (int i = 0; i < detailBean.getListgrid().size(); i++) {
					WebRootFolderBean bFolderBean=(WebRootFolderBean) detailBean.getListgrid().get(i);
						dataReporting=dataReporting+"{ \""+bFolderBean.getColumn_name()+"\", \"30\" },";
				}
			    String dataReportingFi=!dataReporting.equals("")?dataReporting.substring(0, dataReporting.length()-1):"";
			    out.write("public static String [][] MapfieldBean  = new String[][]{"+dataReportingFi+"};");out.println();
			 	                                        
			    out.write("/********************************************************************View Jsp********************************************************************/");out.println();
			    out.write("public static String FORM_VIEW          = \"Form"+getNameEntite+"\";");out.println();
			    out.write("public static String FILTER_VIEW        = \"Filter"+getNameEntite+"\";");out.println();
			    out.write("public static String LIST_VIEW          = \"List"+getNameEntite+"\";");out.println();
			  
			    out.write("}");out.println();
			 out.close() ;  
			} catch (Exception e) {
				e.getStackTrace();
			}
		
	}

}
