package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.dao;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.template.DeviseTemplate;
@Repository
public class DeviseDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<DeviseBean> doFindListDevise(DeviseBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    DeviseBean    bean    WHERE     1=1       ";
    
      if( !StringUtils.isEmpty(beanSearch.getDev_abrv()) )  
	      requette+="   AND   bean.dev_abrv = '"+beanSearch.getDev_abrv()+"'        ";    
      
      if(  beanSearch.getDev_id()!=null )  
	      requette+="   AND   bean.dev_id ="+beanSearch.getDev_id()+"        ";    
      
      
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveDevise(DeviseBean beanSave) throws Exception {
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
	public Boolean doUpdateDevise(DeviseBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((DeviseBean) getObjectValueModel(FORM_BEAN), beanUpdate, DeviseTemplate.id_entite);
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
	public Boolean doDeleteDevise(DeviseBean beanDelete)  throws Exception  {
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
