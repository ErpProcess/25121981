package ERP.Process.Commerciale.GrpTarifPrimitiv.dao;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.Process.Commerciale.GrpTarifPrimitiv.template.GrpTarifPrimitivTemplate;
@Repository
public class GrpTarifPrimitivDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<GrpTarifPrimitivBean> doFindListGrpTarifPrimitiv(GrpTarifPrimitivBean beanSearch) throws Exception {
		
		    String requette=" select  bean   FROM    GrpTarifPrimitivBean    bean    WHERE     1=1       ";
		    
		    
			 if(  beanSearch.getGrp_prim_trf_id()!=null )  
				    requette+="   AND   bean.grp_prim_trf_id = "+beanSearch.getGrp_prim_trf_id()+"       ";    
			 if( !StringUtils.isEmpty(beanSearch.getGrp_trf_lib()) )  
				    requette+="   AND   bean.grp_trf_lib = '"+beanSearch.getGrp_trf_lib()+"'        ";  
			        requette+="   order by bean.ordre";
						return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveGrpTarifPrimitiv(GrpTarifPrimitivBean beanSave) throws Exception {
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
	public Boolean doUpdateGrpTarifPrimitiv(GrpTarifPrimitivBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((GrpTarifPrimitivBean) getObjectValueModel(FORM_BEAN), beanUpdate, GrpTarifPrimitivTemplate.id_entite);
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
	public Boolean doDeleteGrpTarifPrimitiv(GrpTarifPrimitivBean beanDelete)  throws Exception  {
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
