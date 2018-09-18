package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;

public class listView {
	
	


	public static void doWriteFileListEntite(String  nameFile,List listAttributeEntite){
		
		try {
			 File fichier = new File(nameFile);
			 if (! fichier.exists()) 
				 fichier.createNewFile();
			 PrintWriter out = new PrintWriter(new FileWriter(fichier)) ; 
			 out.write("<script >  ") ;  
			 out.println() ;
			 out.write(" var mapColumsbean=[  ") ;  
			 out.println() ;
			 for (int i = 0; i < listAttributeEntite.size(); i++) {
					WebRootFolderBean bFolderBean=(WebRootFolderBean) listAttributeEntite.get(i);
					 out.write("  { \"sTitle\": \"${"+bFolderBean.getColumn_name()+"}\"       ,\"sName\": \""+bFolderBean.getColumn_name()+"\"                     ,\"sWidth\": \"10%\"   , \"bSortable\": \"true\"   },  ") ;  
					 out.println() ;
				}
			 out.write("  ];") ;  
			 out.println() ;
			 out.write("</script> ") ;  
			 out.println() ;
			 out.write(" <jsp:include  page=\"${context_path}/dataGridSetting/dataGridConfig-v1.jsp\" />   ") ;  
			 out.println() ;
			 out.close() ;  
			} catch (Exception e) {
				e.getStackTrace();
			}
			 
		
	}

}
