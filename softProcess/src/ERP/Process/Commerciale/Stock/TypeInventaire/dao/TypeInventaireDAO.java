package ERP.Process.Commerciale.Stock.TypeInventaire.dao;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Stock.TypeInventaire.model.TypeInventaireBean;
import ERP.Process.Commerciale.Stock.TypeInventaire.template.TypeInventaireTemplate;
@Repository
public class TypeInventaireDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
			@SuppressWarnings("unchecked")
			public List<TypeInventaireBean> doFindListTypeInventaire(TypeInventaireBean beanSearch) throws Exception {
				    String requette=" select  bean   FROM    TypeInventaireBean    bean    WHERE     1=1       ";
		     if(  beanSearch.getType_inv_id()!=null )  
			    requette+="   AND   bean.type_inv_id = "+beanSearch.getType_inv_id()+"      ";    
		     if( !StringUtils.isEmpty(beanSearch.getType_inv_libelle()) )  
			    requette+="   AND   bean.type_inv_libelle = '"+beanSearch.getType_inv_libelle()+"'        ";    
					return   hibernateTemplate.find(requette);
			  }
	public Boolean doSaveTypeInventaire(TypeInventaireBean beanSave) throws Exception {
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
	public Boolean doUpdateTypeInventaire(TypeInventaireBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((TypeInventaireBean) getObjectValueModel(FORM_BEAN), beanUpdate, TypeInventaireTemplate.id_entite);
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
	public Boolean doDeleteTypeInventaire(TypeInventaireBean beanDelete)  throws Exception  {
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
