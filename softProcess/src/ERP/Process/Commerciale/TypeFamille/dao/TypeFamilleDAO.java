package ERP.Process.Commerciale.TypeFamille.dao;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.TypeFamille.model.TypeFamilleBean;
import ERP.Process.Commerciale.TypeFamille.template.TypeFamilleTemplate;
@Repository
public class TypeFamilleDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<TypeFamilleBean> doFindListTypeFamille(TypeFamilleBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    TypeFamilleBean    bean    WHERE     1=1       ";
		 if( !StringUtils.isEmpty(beanSearch.getTypefam_id()) )  
			    requette+="   AND   bean.typefam_id = '"+beanSearch.getTypefam_id()+"'        "   ; 
			 
		 if( !StringUtils.isEmpty(beanSearch.getTypefam_lib()) )  
			    requette+="   AND   bean.typefam_lib = '"+beanSearch.getTypefam_lib()+"'        "  ;  
	 
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveTypeFamille(TypeFamilleBean beanSave) throws Exception {
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
	public Boolean doUpdateTypeFamille(TypeFamilleBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((TypeFamilleBean) getObjectValueModel(FORM_BEAN), beanUpdate, TypeFamilleTemplate.id_entite);
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
	public Boolean doDeleteTypeFamille(TypeFamilleBean beanDelete)  throws Exception  {
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
