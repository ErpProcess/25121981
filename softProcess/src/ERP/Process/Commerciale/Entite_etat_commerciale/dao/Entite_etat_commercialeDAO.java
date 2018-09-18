package ERP.Process.Commerciale.Entite_etat_commerciale.dao;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.template.Entite_etat_commercialeTemplate;
@Repository
public class Entite_etat_commercialeDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<Entite_etat_commercialeBean> doFindListEntite_etat_commerciale(Entite_etat_commercialeBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    Entite_etat_commercialeBean    bean    WHERE     1=1       ";
		    requette+=StringUtils.isEmpty(beanSearch.getCode_entite())?"": " AND  bean.code_entite='"+beanSearch.getCode_entite()+"'  order by bean.data_ordre asc  ";
			return   hibernateTemplate.find(requette);
	}  
	 
	@SuppressWarnings("unchecked")
	public Boolean doSaveEntite_etat_commerciale(Entite_etat_commercialeBean beanSave) throws Exception {
	      
	    boolean result=false; 
		try {
			List <Entite_etat_commercialeBean> listData= (List) getObjectValueModel(Entite_etat_commercialeTemplate.LIST_DETAIL_ENTITE_FOR_CREE);
			for (Entite_etat_commercialeBean bean:listData) {
				this.setBeanTrace(bean);
				bean.setCode_entite(beanSave.getCode_entite());
				bean.setLibelle_entite(beanSave.getLibelle_entite());
				bean.setObs_entite(beanSave.getObs_entite());
				this.hibernateTemplate.save(bean);
			}
			
			this.saveTraceVersion_Master_detailles(listData, 
					Entite_etat_commercialeTemplate.MapfieldBean, 
					Entite_etat_commercialeTemplate.id_entite_Tab, 
					Entite_etat_commercialeTemplate.entites);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
		
	}
	public Boolean doUpdateEntite_etat_commerciale(Entite_etat_commercialeBean beanUpdate)  throws Exception { 
		boolean result=false; 
		try {
			List <Entite_etat_commercialeBean> listData= (List) getObjectValueModel(Entite_etat_commercialeTemplate.LIST_DETAIL_ENTITE_FOR_CREE);
			for (Entite_etat_commercialeBean bean:listData) {
				this.setBeanTrace(bean);
				bean.setCode_entite(beanUpdate.getCode_entite());
				bean.setLibelle_entite(beanUpdate.getLibelle_entite());
				bean.setObs_entite(beanUpdate.getObs_entite());
			}
			this.hibernateTemplate.saveOrUpdateAll(listData);
			this.saveTraceVersion_Master_detailles(listData, 
					Entite_etat_commercialeTemplate.MapfieldBean, 
					Entite_etat_commercialeTemplate.id_entite_Tab, 
					Entite_etat_commercialeTemplate.entites);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
	}
	public Boolean doDeleteEntite_etat_commerciale(Entite_etat_commercialeBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
			List listtOSupp=(List) getObjectValueModel(Entite_etat_commercialeTemplate.LIST_DETAIL_ENTITE_FOR_CREE_TO_SUPP);
			hibernateTemplate.deleteAll(listtOSupp);
	        //this.saveTrace(beanDelete);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
}
