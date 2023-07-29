package ERP.Process.Commerciale.FamilleArticle.dao;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.Process.Commerciale.FamilleArticle.template.FamilleArticleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Repository
public class FamilleArticleDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<FamilleBean> doFindListFamilleArticle(FamilleBean beanSearch) {
		
		try {
			 String requette=" select  b   FROM    FamilleBean  b    WHERE     1=1       ";
			 
			 if( !StringUtils.isEmpty(beanSearch.getConditionDeSelection()) ){
				 requette+="  "+beanSearch.getConditionDeSelection();
			 }
			 requette +=this.setSocieteEtabFetch(beanSearch,"b.fk_etab_Bean", false);
			  
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public Boolean saveFamilleArticle(FamilleBean beanSave) {
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
	public Boolean updateFamilleArticle(FamilleBean beanUpdate) {
		try {
			setIdBean((FamilleBean) getObjectValueModel(FORM_BEAN), beanUpdate, FamilleArticleTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public Boolean dodeleteFamilleArticle(FamilleBean beanDelete) {
		try {
			hibernateTemplate.delete(beanDelete);
	        //this.saveTrace(beanDelete);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
