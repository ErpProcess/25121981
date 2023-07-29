package ERP.Process.Commerciale.Stock.DepotStockage.dao;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.NumberUtils;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessWebUtil;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.template.DepotStockageTemplate;
@Repository
public class DepotStockageDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<DepotStockageBean> doFindListDepotStockage(DepotStockageBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    DepotStockageBean    bean    WHERE     1=1       ";
		 if(  beanSearch.getDepot_id()!=null)  
			    requette+="   AND   bean.depot_id = "+beanSearch.getDepot_id()+"       ";  
		 
		 if(  beanSearch.getNature().getNat_lieu_id()!=null)  
			    requette+="   AND   bean.nature.nat_lieu_id = "+beanSearch.getNature().getNat_lieu_id()+"       ";  
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getDepot_libelle()) )  
			       requette+="   AND   bean.depot_libelle = '"+beanSearch.getDepot_libelle()+"'        ";
		 
		 if (!StringUtils.isEmpty(beanSearch.getFk_etab_Bean().getPk_etab().getEtab_id()))
			  requette     +="    AND   bean.fk_etab_Bean.pk_etab.etab_id='"+beanSearch.getFk_etab_Bean().getPk_etab().getEtab_id()+"'     ";
		 
		 
		        requette   += ProcessWebUtil.doChargerSociete_Fetch("bean.fk_etab_Bean");
		 
					return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveDepotStockage(DepotStockageBean beanSave) throws Exception {
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
	public Boolean doUpdateDepotStockage(DepotStockageBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((DepotStockageBean) getObjectValueModel(FORM_BEAN), beanUpdate, DepotStockageTemplate.id_entite);
			setSociete_id(beanUpdate, "beanEtabFsociete.pk_etab.soc_bean.soc_id");
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
	public Boolean doDeleteDepotStockage(DepotStockageBean beanDelete)  throws Exception  {
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
