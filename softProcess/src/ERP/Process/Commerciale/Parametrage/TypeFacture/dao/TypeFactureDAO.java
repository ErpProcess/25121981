package ERP.Process.Commerciale.Parametrage.TypeFacture.dao;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Parametrage.TypeFacture.model.TypeFactureBean;
import ERP.Process.Commerciale.Parametrage.TypeFacture.template.TypeFactureTemplate;
@Repository
public class TypeFactureDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<TypeFactureBean> doFindListTypeFacture(TypeFactureBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    TypeFactureBean    bean    WHERE     1=1       ";
			 if(  beanSearch.getType_fact_id() !=null )  
				    requette+="   AND   bean.type_fact_id = '"+beanSearch.getType_fact_id()+"'        ";    
			 if( !StringUtils.isEmpty(beanSearch.getType_fact_lib()) )  
				    requette+="   AND   bean.type_fact_lib = '"+beanSearch.getType_fact_lib()+"'        ";    
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveTypeFacture(TypeFactureBean beanSave) throws Exception {
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
	public Boolean doUpdateTypeFacture(TypeFactureBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((TypeFactureBean) getObjectValueModel(FORM_BEAN), beanUpdate, TypeFactureTemplate.id_entite);
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
	public Boolean doDeleteTypeFacture(TypeFactureBean beanDelete)  throws Exception  {
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
