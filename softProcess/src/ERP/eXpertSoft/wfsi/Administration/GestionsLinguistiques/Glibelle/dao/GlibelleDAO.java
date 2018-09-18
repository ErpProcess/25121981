package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

/**
 * Contact DAO class.
 * 
 */
@Repository
public class GlibelleDAO  extends  GenericWeb     {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<GlibelleBean> getListGlibelle(GlibelleBean beanSearch) {

		String requette = "  from GlibelleBean bean where 1=1    ";
		
		
		

		if (beanSearch.getLib_libelle() != null  && !beanSearch.getLib_libelle().equals("")) {
			requette += "  AND  bean.lib_libelle  LIKE  '"+beanSearch.getLib_libelle()+"'   ";
		}

		if (beanSearch.getIdLiblleBean().getLib_id() != null  && !beanSearch.getIdLiblleBean().getLib_id().equals("")) {
			requette += "  AND  bean.idLiblleBean.lib_id  ='"+beanSearch.getIdLiblleBean().getLib_id()+"'    ";
		}

		if (beanSearch.getIdLiblleBean().getLang_id() != null  && !beanSearch.getIdLiblleBean().getLang_id().equals("")) {
			requette += "  AND  bean.idLiblleBean.lang_id  ='"+beanSearch.getIdLiblleBean().getLang_id()+"'  ";
		}
		
		if (beanSearch.getSousmod_id() != null  && !beanSearch.getSousmod_id().equals("")) {
			requette += "  AND  bean.sousmod_id  = '"+beanSearch.getSousmod_id()+"'   ";
		}

		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	
 

	@SuppressWarnings("unchecked")
	public List<GlibelleBean> getLabelLibelle(GlibelleBean beanSearch) {

		String requette = "  from GlibelleBean bean  where 1=1  ";
		if (beanSearch.getLib_libelle() != null
				&& !beanSearch.getLib_libelle().equals("")) {
			requette += "  AND  bean.lib_libelle  LIKE  '"
					+ beanSearch.getLib_libelle() + "' ";
		}

		if (beanSearch.getIdLiblleBean().getLib_id() != null
				&& !beanSearch.getIdLiblleBean().getLib_id().equals("")) {
			requette += "  AND  bean.idLiblleBean.lib_id   LIKE  '"
					+ beanSearch.getIdLiblleBean().getLib_id() + "' ";
		}

		if (beanSearch.getIdLiblleBean().getLang_id() != null
				&& !beanSearch.getIdLiblleBean().getLang_id().equals("")) {
			requette += "  AND  bean.idLiblleBean.lang_id  ='"
					+ beanSearch.getIdLiblleBean().getLang_id() + "'  ";
		}

		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public Boolean saveGlibelle(List listLibbelle) {

		try {

			for (int i = 0; i < listLibbelle.size(); i++) {
				GlibelleBean bean = (GlibelleBean) listLibbelle.get(i);
				if (!bean.getLib_libelle().equals("")) {
					hibernateTemplate.save(bean);
				}

			}

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean saveGlibelleColunm(GlibelleBean bean) {

		try {

			hibernateTemplate.save(bean);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateGlibelle(GlibelleBean beanUp) {

		try {
			hibernateTemplate.update(beanUp);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean deleteGlibelle(GlibelleBean beanDel) {

		try {
			hibernateTemplate.delete(beanDel);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}
