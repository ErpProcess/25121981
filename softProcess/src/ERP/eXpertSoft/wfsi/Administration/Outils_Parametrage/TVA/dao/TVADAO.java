package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.dao;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.template.TVATemplate;
@Repository
public class TVADAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<TVABean> doFindListTVA(TVABean beanSearch) throws Exception {
		     String requette=" select  bean   FROM    TVABean    bean    WHERE     1=1       ";
 
			 if( !StringUtils.isEmpty(beanSearch.getTva_abrv()) )  
				    requette+="   AND   bean.tva_abrv = '"+beanSearch.getTva_abrv()+"'        "; 
			 
			        requette+="   ORDER BY  bean.tva_value ASC     ";
			 
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveTVA(TVABean beanSave) throws Exception {
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
	public Boolean doUpdateTVA(TVABean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((TVABean) getObjectValueModel(FORM_BEAN), beanUpdate, TVATemplate.id_entite);
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
	public Boolean doDeleteTVA(TVABean beanDelete)  throws Exception  {
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
