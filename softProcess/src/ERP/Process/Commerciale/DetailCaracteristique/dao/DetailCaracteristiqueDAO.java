package ERP.Process.Commerciale.DetailCaracteristique.dao;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
import ERP.Process.Commerciale.DetailCaracteristique.template.DetailCaracteristiqueTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Repository
public class DetailCaracteristiqueDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<DetailCaracteristiqueBean> doFindListDetailCaracteristique(DetailCaracteristiqueBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    DetailCaracteristiqueBean    bean    WHERE     1=1       ";
		    
		    
            if(!StringUtils.isEmpty(beanSearch.getPk_det_carac().getDet_carac_id())  )   
            	requette+="   AND  bean.pk_det_carac.det_carac_id='"+beanSearch.getPk_det_carac().getDet_carac_id()+"'  ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getPk_det_carac().getBean_carac().getCarac_id())  )
		    	requette+="   AND  bean.pk_det_carac.bean_carac.carac_id='"+beanSearch.getPk_det_carac().getBean_carac().getCarac_id()+"'  ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getDet_carac_libelle())  ) 
		    	requette+="   AND  bean.det_carac_libelle='"+beanSearch.getDet_carac_libelle()+"'  ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getMap_data_list())  ) 
		    	requette+="     "+beanSearch.getMap_data_list()+" ";
		    
		    
		    
			return   hibernateTemplate.find(requette);
	}
	public Boolean doSaveDetailCaracteristique(DetailCaracteristiqueBean beanSave) throws Exception {
	     boolean result=false; 
		try {
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			this.saveTraceVersion1(beanSave, DetailCaracteristiqueTemplate.MapfieldBean, DetailCaracteristiqueTemplate.id_entite_detaille, DetailCaracteristiqueTemplate.entites);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doUpdateDetailCaracteristique(DetailCaracteristiqueBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			
			DetailCaracteristiqueBean bCaracteristiqueBean=(DetailCaracteristiqueBean) getObjectValueModel(FORM_BEAN);
			beanUpdate.getPk_det_carac().setDet_carac_id(bCaracteristiqueBean.getPk_det_carac().getDet_carac_id());
			beanUpdate.getPk_det_carac().getBean_carac().setCarac_id(bCaracteristiqueBean.getPk_det_carac().getBean_carac().getCarac_id());
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTraceVersion1(beanUpdate, DetailCaracteristiqueTemplate.MapfieldBean, DetailCaracteristiqueTemplate.id_entite_detaille, DetailCaracteristiqueTemplate.entites);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doDeleteDetailCaracteristique(DetailCaracteristiqueBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
			hibernateTemplate.delete(beanDelete);
			this.saveTraceVersion1(beanDelete, DetailCaracteristiqueTemplate.MapfieldBean, DetailCaracteristiqueTemplate.id_entite_detaille, DetailCaracteristiqueTemplate.entites);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
}
