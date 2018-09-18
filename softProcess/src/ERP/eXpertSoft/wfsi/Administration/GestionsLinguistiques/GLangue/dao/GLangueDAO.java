package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;

/**
 * Contact DAO class.
 * 
 */
@Repository
public     class GLangueDAO  extends  GenericWeb   {

	private HibernateTemplate hibernateTemplate;
	
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<GLangueBean> doFindListGLangue(GLangueBean searchBean) {
		
		   String requette=" from GLangueBean bean  where 1=1  ";
		
		if( !StringUtils.isEmpty(searchBean.getLang_id()) )            requette+="  AND  bean.lang_id='"+searchBean.getLang_id()+"' ";  
		
		if( !StringUtils.isEmpty(searchBean.getLang_not_null()) )      requette+="  AND  bean.lang_id !=''   ";  
		
		if( !StringUtils.isEmpty(searchBean.getLang_abrv()) )          requette+="  AND  bean.lang_abrv='"+searchBean.getLang_abrv()+"' ";
		
		if(!StringUtils.isEmpty(searchBean.getLang_libelle()))         requette+="  AND  bean.lang_libelle  LIKE  '%"+searchBean.getLang_libelle()+"%' ";
		 
		
		requette+= "   ORDER  By  lang_ordre ";
		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
 
	
	@SuppressWarnings("unchecked")
	public List<GLangueBean> doFindALLListGLangue(GLangueBean searchBean) {
		
		   String requette=" from GLangueBean bean  where   bean.lang_id not in ('')  ";
		requette+= "   ORDER  By  lang_ordre ";
		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public Boolean saveGLangue(GLangueBean beanSave) {

		try {
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			//this.saveTrace(beanSave);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateGLangue(GLangueBean beanUpdate) {

		try {
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeleteGLangue(GLangueBean beanDelete) {

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
