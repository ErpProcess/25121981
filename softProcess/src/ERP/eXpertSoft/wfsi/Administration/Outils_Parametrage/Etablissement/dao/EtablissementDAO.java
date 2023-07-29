package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.dao;

import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.template.EtablissementTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
 
@Repository
public class EtablissementDAO extends  GenericWeb  {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<EtablissementBean> doFindListEtablissement(EtablissementBean beanSearch) {
		
		String requette="   FROM    EtablissementBean bean      WHERE     1=1   ";
		 
			if(!StringUtils.isEmpty(beanSearch.getPk_etab().getEtab_id()) )    
				requette+="   AND  bean.pk_etab.etab_id='"+beanSearch.getPk_etab().getEtab_id()+"'    ";
			
			if(!StringUtils.isEmpty(beanSearch.getEtab_lib()) )    
				requette+="   AND  bean.etab_lib='"+beanSearch.getEtab_lib()+"'        ";
			
			if(!StringUtils.isEmpty(beanSearch.getPk_etab().getSoc_bean().getSoc_id()) )    
				requette+="   AND  bean.pk_etab.soc_bean.soc_id='"+beanSearch.getPk_etab().getSoc_bean().getSoc_id()+"'        ";
			
			if(!StringUtils.isEmpty(beanSearch.getType().getData_id()) )    
				requette+="   AND  bean.type.data_id='"+beanSearch.getType().getData_id()+"'        ";
			
			 
		
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
		
	}
	 
	public Boolean saveEtablissement(EtablissementBean beanSave) {

		try {
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			this.saveTrace(beanSave);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateEtablissement(EtablissementBean beanUpdate) {

		try {
			//this.setIdBean((EtablissementBean) getObjectValueModel(FORM_BEAN), beanUpdate, EtablissementTemplate.idEntite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			//this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeleteEtablissement(EtablissementBean beanDelete) {

		try {
			hibernateTemplate.delete(beanDelete);
			this.saveTrace(beanDelete);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	 

}
