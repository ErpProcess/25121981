package ERP.Process.Commerciale.Type_tarification.dao;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Type_tarification.model.Type_tarificationBean;
import ERP.Process.Commerciale.Type_tarification.template.Type_tarificationTemplate;
@Repository
public class Type_tarificationDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<Type_tarificationBean> doFindListType_tarification(Type_tarificationBean beanSearch) throws Exception {
		
		         String requette=" select  bean   FROM    Type_tarificationBean    bean    WHERE     1=1       ";
		         
                if(  beanSearch.getType_trf_id()!=null )  
	                    requette+="   AND   bean.type_trf_id ="+beanSearch.getType_trf_id()+"   ";   
                
                if( !StringUtils.isEmpty(beanSearch.getType_trf_lib()) )  
	                    requette+="   AND   bean.type_trf_lib ='"+beanSearch.getType_trf_lib()+"'        "; 
                
                requette+="   order by bean.ordre  "; 
                
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveType_tarification(Type_tarificationBean beanSave) throws Exception {
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
	public Boolean doUpdateType_tarification(Type_tarificationBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((Type_tarificationBean) getObjectValueModel(FORM_BEAN), beanUpdate, Type_tarificationTemplate.id_entite);
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
	public Boolean doDeleteType_tarification(Type_tarificationBean beanDelete)  throws Exception  {
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
