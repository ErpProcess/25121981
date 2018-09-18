package ERP.Process.Commerciale.AchatDivers.dao;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.AchatDivers.model.AchatDiversBean;
import ERP.Process.Commerciale.AchatDivers.template.AchatDiversTemplate;
@Repository
public class AchatDiversDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<AchatDiversBean> doFindListAchatDivers(AchatDiversBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    AchatDiversBean    bean    WHERE     1=1       ";
			 if(  beanSearch.getAch_non_id()!=null )  
				    requette+="   AND   bean.ach_non_id = "+beanSearch.getAch_non_id()+"       ";    
			 if( !StringUtils.isEmpty(beanSearch.getLibelle_achat()) )  
				    requette+="   AND   bean.libelle_achat = '"+beanSearch.getLibelle_achat()+"'        ";    
			 if(  beanSearch.getPrix_achat() != null)  
				    requette+="   AND   bean.prix_achat = "+beanSearch.getPrix_achat()+"        ";    
			 if( !StringUtils.isEmpty(beanSearch.getObservation()) )  
				    requette+="   AND   bean.observation = '"+beanSearch.getObservation()+"'        "; 
			 if(  beanSearch.getDate_achat()!=null)  
				    requette+="   AND   bean.date_achat = '"+beanSearch.getDate_achat()+"'        "; 
			 
			   requette+="   ORDER BY   bean.date_achat         "; 
						return   hibernateTemplate.find(requette);
	}
	
	public Boolean doSaveAchatDivers(AchatDiversBean beanSave) throws Exception {
	     boolean result=false; 
		try {
			//this.setBeanSession_TraceValueInto_Cur_Bean(beanSave);
			this.setPk_Soc_Etab(beanSave, "soc_id", "etab_id");
			this.hibernateTemplate.save(beanSave);
			//this.saveTrace(beanSave);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doUpdateAchatDivers(AchatDiversBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((AchatDiversBean) getObjectValueModel(FORM_BEAN), beanUpdate, AchatDiversTemplate.id_entite);
			this.setPk_Soc_Etab(beanUpdate, "soc_id", "etab_id");
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			//this.saveTrace(beanUpdate);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doDeleteAchatDivers(AchatDiversBean beanDelete)  throws Exception  {
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
