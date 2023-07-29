package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.dao;

import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

 
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.template.WebRootFolderTemplate;
 
@Repository
public class WebRootFolderDAO extends  GenericWeb    {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<WebRootFolderBean> doFindListWebRootFolder(WebRootFolderBean beanSearch) {
		
		String requette = "  FROM WebRootFolderBean  bean where 1=1    ";
		
		if (beanSearch.getEnt_libelle() != null  && !beanSearch.getEnt_libelle().equals("")) {
			requette += "  AND  bean.ent_libelle  LIKE  '"+beanSearch.getEnt_libelle()+"'   ";
		}

		if (beanSearch.getPk_web_root().getEnt_id() != null  && !beanSearch.getPk_web_root().getEnt_id().equals("")) {
			requette += "  AND  bean.pk_web_root.ent_id  ='"+beanSearch.getPk_web_root().getEnt_id()+"'    ";
		}

		if (beanSearch.getPk_web_root().getLang_id() != null  && !beanSearch.getPk_web_root().getLang_id().equals("")) {
			requette += "  AND  bean.pk_web_root.lang_id  ='"+beanSearch.getPk_web_root().getLang_id()+"'  ";
		}
		
		if (beanSearch.getSousmod_id() != null  && !beanSearch.getSousmod_id().equals("")) {
			requette += "  AND  bean.sousmod_id  = '"+beanSearch.getSousmod_id()+"'   ";
		}
		
		if (beanSearch.getChaineColumn()!= null  && !beanSearch.getChaineColumn().equals("")) {
			requette += "  AND  bean.column_name  not in ( "+beanSearch.getChaineColumn()+"   )    ";
		}

		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
		
	}
	 
 

	 
	public Boolean saveWebRootFolder(List  listForInsert) throws Exception {

		
		 boolean  res=false;
		try {
			 //this.setBeanSession_TraceValueInto_Cur_Bean(beanSave);
			 
			 this.hibernateTemplate.saveOrUpdateAll(listForInsert);
			 
			 res=true;
			//this.saveTrace(beanSave);
			 
		} catch (Exception ex) {
			 this.hibernateTemplate.clear();
			 res=false;
			 throw ex;
			
		}
		return res;

	}

	

	public Boolean dodeleteWebRootFolder(WebRootFolderBean beanDelete) {

		try {
			hibernateTemplate.delete(beanDelete);
			this.saveTrace(beanDelete);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	 

}
