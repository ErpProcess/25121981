package ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.dao;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.model.NatureMvtCommercialeBean;
import ERP.Process.Commerciale.ParametrageCommerciale.NatureMvtCommerciale.template.NatureMvtCommercialeTemplate;
@Repository
public class NatureMvtCommercialeDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<NatureMvtCommercialeBean> doFindListNatureMvtCommerciale(NatureMvtCommercialeBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    NatureMvtCommercialeBean    bean    WHERE     1=1       ";
 if( !StringUtils.isEmpty(beanSearch.getNature_mvt_id()) )  
	    requette+="   AND   bean.nature_mvt_id = '"+beanSearch.getNature_mvt_id()+"'        ";    
 if( !StringUtils.isEmpty(beanSearch.getNature_mvt_libelle()) )  
	    requette+="   AND   bean.nature_mvt_libelle = '"+beanSearch.getNature_mvt_libelle()+"'        ";    
 if(  (beanSearch.getNature_mvt_ordre()!= null) )  
	    requette+="   AND   bean.nature_mvt_ordre = '"+beanSearch.getNature_mvt_ordre()+"'        ";    
 if( !StringUtils.isEmpty(beanSearch.getEtab_id()) )  
	    requette+="   AND   bean.etab_id = '"+beanSearch.getEtab_id()+"'        ";    
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveNatureMvtCommerciale(NatureMvtCommercialeBean beanSave) throws Exception {
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
	public Boolean doUpdateNatureMvtCommerciale(NatureMvtCommercialeBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((NatureMvtCommercialeBean) getObjectValueModel(FORM_BEAN), beanUpdate, NatureMvtCommercialeTemplate.id_entite);
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
	public Boolean doDeleteNatureMvtCommerciale(NatureMvtCommercialeBean beanDelete)  throws Exception  {
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
