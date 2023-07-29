package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.dao;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.model.PrivilegeBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.template.PrivilegeTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Repository
public class PrivilegeDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	
	
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	 
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public List<PrivilegeBean> doFindListPrivilege(PrivilegeBean beanSearch) {
		
		 String requette=" select  b   FROM    PrivilegeBean b    WHERE     1=1       ";
		 
		 if(!StringUtils.isEmpty(beanSearch.getPkPriv().getPfrBean().getPrf_id()) )   
			 requette+="    AND    b.pkPriv.pfrBean.prf_id='"+beanSearch.getPkPriv().getPfrBean().getPrf_id()+"'   ";
		 
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public void savePrivilege(List lisDataToInsert) throws Throwable {
		try {
			//this.setBeanSession_TraceValueInto_Cur_Bean(beanSave);
			List listInitPrivilege_origine=(List) getObjectValueModel( "listInitPrivilege_origine" );
			 
			hibernateTemplate.deleteAll(listInitPrivilege_origine);
			hibernateTemplate.flush();
			hibernateTemplate.clear();
			hibernateTemplate.saveOrUpdateAll(lisDataToInsert);
			 
			//this.saveTrace(beanSave);
			 
		} catch (Exception ex) {
			ex.printStackTrace();
		 
		}
	}
	public Boolean updatePrivilege(PrivilegeBean beanUpdate) {
		try {
			setIdBean((PrivilegeBean) getObjectValueModel(FORM_BEAN), beanUpdate, PrivilegeTemplate.id_entite);
			//this.setUpdateValueFieldTraceOject(beanUpdate);
			//this.hibernateTemplate.update(beanUpdate);
			//this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public Boolean dodeletePrivilege(PrivilegeBean beanDelete) {
		try {
			//hibernateTemplate.delete(beanDelete);
	        this.saveTrace(beanDelete);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
