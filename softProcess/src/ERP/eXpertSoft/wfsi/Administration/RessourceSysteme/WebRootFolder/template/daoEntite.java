package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.springframework.util.StringUtils;

import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;

public class daoEntite {
	
	


	public static void doWriteFile(String pathpackage,String  nameFile,WebRootFolderBean detailBean){
		
		try {
			 File fichier = new File(nameFile);
			 if (! fichier.exists()) 
				 fichier.createNewFile();
			    PrintWriter out = new PrintWriter(new FileWriter(fichier));
				String[] tablaeau=pathpackage.split("\\.");
				String getNameEntite=tablaeau[tablaeau.length-1];
				out.write("package " + pathpackage+"dao;");
				out.println();
				
				 
				out.write("import java.util.List;");out.println() ;
				out.write("import java.util.ArrayList;");out.println() ;
				out.write("import org.springframework.util.StringUtils;");out.println() ;
				out.write("import org.hibernate.SessionFactory;");out.println() ;
				out.write("import org.hibernate.Session;");out.println() ;
				out.write("import org.springframework.beans.factory.annotation.Autowired;");out.println() ;
				out.write("import org.springframework.orm.hibernate5.HibernateTemplate;");out.println() ;
				out.write("import org.springframework.stereotype.Repository;");out.println() ;
				out.write("import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;");out.println() ;
				out.write("import "+pathpackage+"model."+getNameEntite+"Bean;");out.println() ;
				out.write("import "+pathpackage+"template."+getNameEntite+"Template;");out.println() ;
				 
				out.write("@Repository");out.println() ;
				out.write("public class "+getNameEntite+"DAO extends  GenericWeb    {");out.println() ;
					
				out.write("	private SessionFactory sessionFactory;");out.println() ;
				
				
				 

				out.write("	@Autowired");out.println() ;
				out.write("	public void setSessionFactory (SessionFactory sessionFactory) {");out.println() ;
				out.write("		 this.sessionFactory = sessionFactory;");out.println() ;
				out.write("	}");out.println() ;
					
					 
				out.write("	@SuppressWarnings(\"unchecked\")");out.println() ;
				out.write("	public List<"+getNameEntite+"Bean> doFindList"+getNameEntite+"("+getNameEntite+"Bean beanSearch) throws Exception {");out.println() ;
				
				
				out.write("		  Session session =  openSessionHibernate(sessionFactory);");out.println() ;
				out.write("		  List list_data= new ArrayList();");out.println() ;
				out.write("		  try{");out.println() ;
				
				out.write("		    String requette=\" select  bean   FROM    "+getNameEntite+"Bean    bean    WHERE     1=1       \";");out.println() ;
				
			
				
				for (int i = 0; i < detailBean.getListfilter().size(); i++) {
					WebRootFolderBean bFolderBean=(WebRootFolderBean) detailBean.getListAttributeEntite().get(i);
					if(bFolderBean.getColumn_name()==null || bFolderBean.getColumn_name().equals(""))continue;
						out.write(" 			   if( !StringUtils.isEmpty(beanSearch.get"+StringUtils.capitalize(bFolderBean.getColumn_name())+"()) )  ") ;out.println() ;
						out.write("	    			requette+=\"   AND   bean."+bFolderBean.getColumn_name()+" = '\"+beanSearch.get"+StringUtils.capitalize(bFolderBean.getColumn_name())+"()+\"'        \";    ") ;out.println() ;
				}
				
			 
					 
				out.write("				  		list_data= session.createQuery(requette).list();");out.println() ;
			    out.write("				        commitTransaction(session);");out.println() ;
			    out.write("			 } catch (Exception e) {  ");out.println() ;
				out.write("			     if (sessionIsTrue(session)) ");out.println() ;
			    out.write("			    	 rollbackTransaction(session) ;");out.println() ;
			    out.write("				     throw e;  ");out.println() ;
				out.write("				 } finally {  ");out.println() ;
				out.write("				 session.close();  ");out.println() ;
				out.write("			 }  ");out.println() ;
				out.write("			 return  list_data;");out.println() ;
				out.write("	 }  ");out.println() ;
					 
				out.write("	public Boolean doSave"+getNameEntite+"("+getNameEntite+"Bean beanSave) throws Exception {");out.println() ;
				out.write("Session session =  openSessionHibernate(sessionFactory);");out.println() ;
				out.write("	     boolean result=false; ");out.println() ;
				out.write("		try {");out.println() ;
				out.write("			this.setBeanTrace(beanSave);");out.println() ;
				out.write("			session.save(beanSave);");out.println() ;
				out.write("			this.saveTrace(beanSave);");out.println() ;
				out.write("			result=true;");out.println() ;
				out.write("			commitTransaction(session);");out.println() ;
				out.write("		 } catch (Exception ex) {");out.println() ;
				out.write("			 result=false;");out.println() ;
				out.write("			 rollbackTransaction(session) ;");out.println() ;
				out.write("		     throw ex;");out.println() ;
				out.write("		 } finally {  ");out.println() ;
				out.write("			 session.close();  ");out.println() ;
				out.write("		 } ");out.println() ;
				out.write("	      return result;");out.println() ;
				out.write("	}");out.println() ;

				out.write("	public Boolean doUpdate"+getNameEntite+"("+getNameEntite+"Bean beanUpdate)  throws Exception { ");out.println() ;
				out.write("Session session =  openSessionHibernate(sessionFactory);");out.println() ;
				out.write("	    boolean result=false; ");out.println() ;
				out.write("		try {");out.println() ;
				out.write("			setIdBean(("+getNameEntite+"Bean) getObjectValueModel(FORM_BEAN), beanUpdate, "+getNameEntite+"Template.id_entite);");out.println() ;
				out.write("			this.setUpdateValueFieldTraceOject(beanUpdate);");out.println() ;
				out.write("			session.update(beanUpdate);");out.println() ;
				out.write("			this.saveTrace(beanUpdate);");out.println() ;
				out.write("			result=true;");out.println() ;
				out.write("			commitTransaction(session);");out.println() ;
				out.write("		 } catch (Exception ex) {");out.println() ;
				out.write("			 result=false;");out.println() ;
				out.write("			 rollbackTransaction(session) ;");out.println() ;
				out.write("		     throw ex;");out.println() ;
				out.write("		 } finally {  ");out.println() ;
				out.write("			 session.close();  ");out.println() ;
				out.write("		 } ");out.println() ;
				out.write("	      return result;");out.println() ;
				out.write("	}");out.println() ;

				out.write("	public Boolean doDelete"+getNameEntite+"("+getNameEntite+"Bean beanDelete)  throws Exception  {");out.println() ;
				out.write("Session session =  openSessionHibernate(sessionFactory);");out.println() ;
				out.write("	    boolean result=false; ");out.println() ;
				out.write("		try {");out.println() ;
				out.write("			session.delete(beanDelete);");out.println() ;
				out.write("	        this.saveTrace(beanDelete);");out.println() ;
				out.write("			result=true;");out.println() ;
				out.write("			commitTransaction(session);");out.println() ;
				out.write("		 } catch (Exception ex) {");out.println() ;
				out.write("			 result=false;");out.println() ;
				out.write("			 rollbackTransaction(session) ;");out.println() ;
				out.write("		     throw ex;");out.println() ;
				out.write("		 } finally {  ");out.println() ;
				out.write("			 session.close();  ");out.println() ;
				out.write("		 } ");out.println() ;
				out.write("	      return result;");out.println() ;
				out.write("	}");out.println() ;

					 

				out.write("}");out.println() ;

				
				
 
			 out.close() ;  
			} catch (Exception e) {
				e.getStackTrace();
			}
		
	}

}
