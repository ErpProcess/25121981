package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.dao;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.model.UniteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Unite.template.UniteTemplate;
@Repository
public class UniteDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<UniteBean> doFindListUnite(UniteBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    UniteBean    bean    WHERE     1=1       ";
   if( beanSearch.getUnite_id()!=null)  
	    requette+="   AND   bean.unite_id = "+beanSearch.getUnite_id()+"       ";    
 if( !StringUtils.isEmpty(beanSearch.getUnite_lib()) )  
	    requette+="   AND   bean.unite_lib = '"+beanSearch.getUnite_lib()+"'        ";    
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveUnite(UniteBean beanSave) throws Exception {
	     boolean result=false; 
		try {
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			this.saveTrace(beanSave);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doUpdateUnite(UniteBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((UniteBean) getObjectValueModel(FORM_BEAN), beanUpdate, UniteTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doDeleteUnite(UniteBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
			hibernateTemplate.delete(beanDelete);
	        this.saveTrace(beanDelete);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
}
