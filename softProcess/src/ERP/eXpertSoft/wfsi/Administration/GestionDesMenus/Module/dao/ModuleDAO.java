package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.template.ModuleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
 



 

/**
 * Contact DAO class.
 * 
 */
@Repository
public class ModuleDAO extends  GenericWeb {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<ModuleBean> getModules() {
		
		try
        {
            return hibernateTemplate.find(" from ModuleBean uta where 1=1 " );
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
 
	}
	
	@SuppressWarnings("unchecked")
	public List<ModuleBean> getSomeModules(ModuleBean mBean) {
		
		String requette="";
		try
        {
			
			
			
			   requette=" FROM  ModuleBean module  WHERE  1=1    ";
			
			if(mBean.getSousPackBean().getSpack_id()!=null )
				requette+="  AND  module.sousPackBean.spack_id="+mBean.getSousPackBean().getSpack_id()+"   ";
			
			if(mBean.getMod_id()!=null )
				requette+="  AND  module.mod_id="+mBean.getMod_id()+"   ";
			
            return hibernateTemplate.find(requette);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
 
	}
	
	
	 
 
	 
	 
 
	
	
	@SuppressWarnings("unchecked")
	public List<ModuleBean> doFindListModule(ModuleBean beanSearch) {
		
		 String requette=" select  b   FROM    ModuleBean b    WHERE     1=1       ";
		
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
		
	}
	 
 

	 
	public Boolean saveModule(ModuleBean beanSave) {

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

	public Boolean updateModule(ModuleBean beanUpdate) {

		try {
			this.setIdBean((ModuleBean) getObjectValueModel(FORM_BEAN), beanUpdate, ModuleTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.hibernateTemplate.bulkUpdate(" update  EntiteAdminBean  e  set  " +
					"    e.spack_id='"+beanUpdate.getSousPackBean().getSpack_id()+"'  where  e.mod_id='"+beanUpdate.getMod_id()+"' "   );
			
			this.hibernateTemplate.bulkUpdate(" update  PrivilegeBean  e  set  " +
					"    e.spackBean.spack_id="+beanUpdate.getSousPackBean().getSpack_id()+"   where  e.modBean.mod_id="+beanUpdate.getMod_id()+" "   );
			this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeleteModule(ModuleBean beanDelete) {

		try {
			this.setIdBean((ModuleBean) getObjectValueModel(FORM_BEAN), beanDelete, ModuleTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanDelete);
			this.hibernateTemplate.delete(beanDelete);
			this.saveTrace(beanDelete);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}


}
