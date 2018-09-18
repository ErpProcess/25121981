package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;

public class formView {
	
	
	public static void doWriteFileFomEntite(String  nameFile,List listAttributeEntite){
		try {
		 File fichier = new File(nameFile);
		 if (! fichier.exists()) 
			 fichier.createNewFile();
		 PrintWriter out = new PrintWriter(new FileWriter(fichier)) ; 
		 out.write(" <%@include file=\"/Aceuil/esProcess.jsp\" %>  ") ;  
		 out.println() ; 
		 //out.write(" <jsp:include page=\"/Aceuil/header.jsp\"  ></jsp:include>  ") ;  
		 //out.println() ;
		 out.write("  <ext:body  >  ") ;  
		 out.println() ;
		 out.write("  <ext:panel  border=\"false\"    bodyStyle=\"background: none;\"      renderTo=\"ThePageJsp\"   >  ") ;   
		 out.println() ;
		 out.write(" <table class=\"tableStyleContent\"  cellpadding=\"5\" cellspacing=\"10\"  id=\"tblData\"     >  ") ;  
		 out.println() ;
	
		 HashMap  mapcolon= new HashMap();
		 for (int i = 0; i < listAttributeEntite.size(); i++) {
			 
			    int ko=i+1;
				WebRootFolderBean bFolderBean=(WebRootFolderBean) listAttributeEntite.get(ko);
				mapcolon.put(String.valueOf(i), bFolderBean.getColumn_name());
				if(ko==listAttributeEntite.size()-1)break;
		 }
		 
 
		 for (int i = 0; i < listAttributeEntite.size(); i++) {
				WebRootFolderBean bFolderBean=(WebRootFolderBean) listAttributeEntite.get(i);
				 out.write("   <tr>  ") ;  
				 out.println() ;
				 out.write("   <td width=\"7%\"><label>${"+bFolderBean.getColumn_name()+"}</label></td>  ") ;  
				 out.println() ;
				 out.write("   <td width=\"93%\"  >  ") ;  
				 out.println() ;
				 
				 String  vAR="";
				 
				 String resUl=(String) (mapcolon.get(String.valueOf(i))==null?"btValidx":mapcolon.get(String.valueOf(i)));
				 if(i==0)
					 vAR=" nextElement=\""+resUl+"\"    autofocus   required  ";
				 else
					 vAR=" nextElement=\""+resUl+"\"           ";
					 
				 String  maxlenght="   maxlength=\""+bFolderBean.getColumn_size()+"\"    ";
				 
				 String  dah="text";
				 if(bFolderBean.getType_name().equals("date"))
					 dah="datepicker";
				  
				 
				 out.write("   <input id=\""+bFolderBean.getColumn_name()+"\" name=\""+bFolderBean.getColumn_name()+"\"     type=\""+dah+"\"    size=\""+bFolderBean.getColumn_size()+"\"    "+maxlenght+"    value=\"${detailBean."+bFolderBean.getColumn_name()+"}\"   "+vAR+"   />  ") ;  
				 out.println() ;
				 out.write("  </td>  ") ;  
				 out.println() ;
				 out.write("   </tr>   ") ;
				 out.println() ;
			}
		 
		
		
		 out.write(" </table>   ") ;  
		 out.println() ;
		 out.write("</ext:panel>") ;  
		 out.println() ;
		 out.write("</ext:body>") ;  
		 out.println() ; 
		 out.close() ;  
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public formView() {
		super();
	}

}
