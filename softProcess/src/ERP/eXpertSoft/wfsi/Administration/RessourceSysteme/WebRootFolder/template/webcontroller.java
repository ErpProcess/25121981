package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class webcontroller {
 

	public static void doWriteFile(String pathpackage, String nameFile) {

		try {
			File fichier = new File(nameFile);
			if (!fichier.exists())
				fichier.createNewFile();
			PrintWriter out = new PrintWriter(new FileWriter(fichier));
			String[] tablaeau=pathpackage.split("\\.");
			String getNameEntite=tablaeau[tablaeau.length-1];
			out.write("package " + pathpackage+"web;");
			out.println();
			
			out.write("import java.text.SimpleDateFormat; " );
			out.println();
			
			out.write("import java.util.Date;" );
			out.println();
			out.write("import javax.servlet.http.HttpServletRequest;" );
			out.println();
			out.write("import javax.servlet.http.HttpServletResponse;" );
			out.println();
			out.write("import org.springframework.beans.propertyeditors.CustomDateEditor;" );
			out.println();
			out.write("import org.springframework.stereotype.Controller;" );
			out.println();
			out.write("import org.springframework.web.bind.WebDataBinder;" );
			out.println();
			out.write("import org.springframework.web.bind.annotation.InitBinder;" );
			out.println();
			out.write("import org.springframework.web.bind.annotation.RequestMapping;" );
			out.println();
			out.write("import org.springframework.web.servlet.ModelAndView;" );
			out.println();
			
			 out.write("import "+pathpackage+"model."+getNameEntite+"Bean;");out.println() ;
		     out.write("import "+pathpackage+"template."+getNameEntite+"Template;");out.println() ;
		
		
			out.write("@Controller" );
			out.println();
			out.write("public class "+getNameEntite+"Controller  extends "+getNameEntite+"ActionManager   {" );
			out.println();
			out.write("  	@InitBinder" );
			out.println();
			out.write("  	public void initBinder(WebDataBinder binder) {" );
			out.println();
			out.write("  	   SimpleDateFormat dateFormat = new SimpleDateFormat(\"dd/MM/yyyy\"); " );
			out.println();
			out.write("  	   dateFormat.setLenient(false); " );
			out.println();
			out.write("  	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); " );
			out.println();
			out.write("  	 } " );
			out.println();
			out.write("    @RequestMapping(value = ROOT) " );
			out.println();
			out.write("    public ModelAndView doControlAction("+getNameEntite+"Bean detailBean,HttpServletRequest request,HttpServletResponse response  )throws  Throwable {  " );
			out.println();
			out.write("  	    try { ");
			out.println();
			out.write("           ModelAndView model=doInitGenericAction(request,response,new "+getNameEntite+"Template());" );out.println();
			out.write("		          if (i$_ACT_ADD)                   return      doAddData(detailBean);" );       out.println();   
			out.write("		          if (i$_ACT_AJAX_FETCH)            return      doFetchData(detailBean);" );    out.println();
			out.write("		          if (i$_ACT_UPDATE)                return      doUpdateData(detailBean);" );    out.println();   
			out.write("		          if (i$_ACT_DELETE)                return      doDeleteData(detailBean);" );      out.println();
			out.write("	          return model;" );out.println();
			
			out.write("  	   } finally { ");
			out.println();
			out.write("  	      destroyThreadLocal(); ");
			out.println();
			out.write("        }");out.println();
			
			out.write("    }");out.println();
			out.write("  }");out.println();
			out.close();
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

}
