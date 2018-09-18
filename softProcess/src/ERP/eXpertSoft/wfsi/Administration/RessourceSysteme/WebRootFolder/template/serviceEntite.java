package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class serviceEntite {
	
	


	public static void doWriteFile(String pathpackage,String  nameFile){
		
		try {
			 File fichier = new File(nameFile);
			 if (! fichier.exists()) 
				 fichier.createNewFile();
				PrintWriter out = new PrintWriter(new FileWriter(fichier));
				String[] tablaeau=pathpackage.split("\\.");
				String getNameEntite=tablaeau[tablaeau.length-1];
				out.write("package " + pathpackage+"service;");
				out.println();
			 out.write("import org.springframework.beans.factory.annotation.Autowired; ") ;  out.println() ; 
			 out.write("import org.springframework.stereotype.Service;") ;  out.println() ; 
			 out.write("import java.util.List;") ;  out.println() ; 
			
			 out.write("import org.springframework.transaction.annotation.Transactional;") ;  out.println() ; 

			 out.write("import "+pathpackage+"dao."+getNameEntite+"DAO;") ;  out.println() ; 
			 out.write("import "+pathpackage+"model."+getNameEntite+"Bean;");out.println() ;
		     out.write("import "+pathpackage+"template."+getNameEntite+"Template;");out.println() ;
			 
			 
			 out.write("import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;") ;  out.println() ; 
			 //out.write("import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;") ;  out.println() ; 

			  
			  
			 out.write("@Service") ;  out.println() ;
			 out.write("public class "+getNameEntite+"Service  extends GenericWeb  {") ;  out.println() ;
			 	 
			 out.write("	private "+getNameEntite+"DAO dao"+getNameEntite+";") ;  out.println() ;
			 	
			 //out.write("	private NumSequentielDAO daoNumSequentiel;") ;  out.println() ;
			 	 
			 	
			 	
			 out.write("	@Autowired") ;  out.println() ;
			 out.write("	public void setDao"+getNameEntite+"("+getNameEntite+"DAO dao"+getNameEntite+") {") ;  out.println() ;
			 out.write("		this.dao"+getNameEntite+" = dao"+getNameEntite+";") ;  out.println() ;
			 out.write("	}") ;  out.println() ;
			 	
			/* out.write("	@Autowired") ;  out.println() ;
			 out.write("	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {") ;  out.println() ;
			 out.write("		this.daoNumSequentiel = daoNumSequentiel;") ;  out.println() ;
			 out.write("	}") ;  out.println() ;*/
			 	
			 out.write("	@Transactional(readOnly=true)") ;  out.println() ;
			 out.write("	public List<"+getNameEntite+"Bean> doFetchDatafromServer("+getNameEntite+"Bean beanSearch) throws Exception {") ;  out.println() ;
			 out.write("		return dao"+getNameEntite+".doFindList"+getNameEntite+"(beanSearch);") ;  out.println() ;
			 out.write("	}") ;  out.println() ;
			 	
			 	 
			 out.write("	@Transactional") ;  out.println() ;
			 out.write("	public Boolean doCreateRowData("+getNameEntite+"Bean insertBean) throws Exception {") ;  out.println() ;
			 out.write("		 boolean result = false;") ;  out.println() ;
			 out.write("		 try {") ;  out.println() ;
			 out.write("		       if(dao"+getNameEntite+".doSave"+getNameEntite+"(insertBean)){") ;  out.println() ;
			 out.write("		       result = true;") ;  out.println() ;
			 out.write("		       }else{") ;  out.println() ;
			 out.write("		    	result = false;  ") ;  out.println() ;
			 out.write("		       }") ;  out.println() ;		       
			 out.write("		 } catch (Exception e) { ") ;  out.println() ;
			 out.write("			 result = false;") ;  out.println() ;
			 out.write("			 throw e;") ;  out.println() ;
			 out.write("		}") ;  out.println() ;
			 
			 out.write("		return result; ") ;  out.println() ;
			 
			 out.write("	}") ;  out.println() ;
			 
			 
			 
			 	
			 out.write("	@Transactional") ;  out.println() ;
			 out.write("	public Boolean  doUpdateRowData("+getNameEntite+"Bean updateBean)  throws Exception {") ;  out.println() ;
			 out.write("		 boolean result = false;") ;  out.println() ;
			 out.write("		 try {") ;  out.println() ;
			 out.write("		if(dao"+getNameEntite+".doUpdate"+getNameEntite+"(updateBean)){") ;  out.println() ;
			 out.write("		    result = true;") ;  out.println() ;
			 out.write("		       }else{") ;  out.println() ;
			 out.write("		    	result = false;  ") ;  out.println() ;
			 out.write("		     }") ;  out.println() ;		       
			 out.write("		 } catch (Exception e) { ") ;  out.println() ;
			 out.write("			 result = false;") ;  out.println() ;
			 out.write("			 throw e;") ;  out.println() ;
			 out.write("		}") ;  out.println() ;
			 out.write("	 return result; ") ;  out.println() ;
			 out.write("	}") ;  out.println() ;
			 	
			 	
			 out.write("	@Transactional") ;  out.println() ;
			 out.write("	public Boolean doDeleteRowData("+getNameEntite+"Bean deleteBean) throws Exception {") ;  out.println() ;
			 out.write("		 boolean result = false;") ;  out.println() ;
			 out.write("		 try {") ;  out.println() ;
			 out.write("		    if(dao"+getNameEntite+".doDelete"+getNameEntite+"(deleteBean)){") ;  out.println() ;
			 
			 out.write("		        result = true;") ;  out.println() ;
			 out.write("		      }else{") ;  out.println() ;
			 out.write("		    	result = false;  ") ;  out.println() ;
			 out.write("		     }") ;  out.println() ;		       
			 out.write("		 } catch (Exception e) { ") ;  out.println() ;
			 out.write("			 result = false;") ;  out.println() ;
			 out.write("			 throw e;") ;  out.println() ;
			 out.write("		}") ;  out.println() ;
			 out.write("   return result; ") ;  out.println() ;
			 out.write("	}") ;  out.println() ;
 
			 out.write("}") ;  out.println() ;



			 
			 
			 
			 
			 
			 
			 
			 


		 
			 out.close() ;  
			} catch (Exception e) {
				e.getStackTrace();
			}
		
	}

}
