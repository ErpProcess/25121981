package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.template.SousModuleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.WebRootFolder.model.WebRootFolderBean;

 
@Repository
public class SousModuleDAO   extends GenericWeb  {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<SousModuleBean> getSousModules(SousModuleBean searchBean ) {
		try
        {
			String  requette="  from SousModuleBean bean  where 1=1  "; 
			if(searchBean.getSousmod_id()!=null)   
				  requette+="   AND  bean.sousmod_id="+searchBean.getSousmod_id()+"    ";
			
			
            return hibernateTemplate.find(requette);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
 
	}
	
	@SuppressWarnings("unchecked")
	public List<SousModuleBean> doFindListWebRootFolder(SousModuleBean beanSearch) {
		
		 String requette=" select  b   FROM    SousModuleBean b    WHERE   (  b.jsp_folder is  null or   b.jsp_folder=''  )     ";
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
		
	}
	
	
	 
	
	public Boolean updateWebRootFolder(SousModuleBean beanUpdate) {

		try {
			//setIdBean((WebRootFolderBean) getObjectValueModel(FORM_BEAN), beanUpdate, WebRootFolderTemplate.id_entite);
			//this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.bulkUpdate("update  SousModuleBean sm   set  sm.jsp_folder='X'   where   sm.sousmod_id="+beanUpdate.getSousmod_id()+"    ");
			//this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<SousModuleBean> getSousModulesById(String id) {
		try
        {
            return hibernateTemplate.find(" from SousModuleBean bsmod where 1=1   AND   bsmod.moduleBean.mod_id="+id+"   " );
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
 
	}
	
 
	
	public Boolean saveModule(SousModuleBean beanSave) {

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

	public Boolean updateSousModule(SousModuleBean beanUpdate) {

		try {
			this.setIdBean((SousModuleBean) getObjectValueModel(FORM_BEAN), beanUpdate, SousModuleTemplate.id_entite);
			SousModuleBean souBeanorGIne=(SousModuleBean) getObjectValueModel(FORM_BEAN);
			beanUpdate.setListFction(souBeanorGIne.getListFction());
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			
			this.hibernateTemplate.bulkUpdate(" update  EntiteAdminBean  e  set  " +
					"    e.mod_id='"+beanUpdate.getModuleBean().getMod_id()+"'  where  e.sousmod_id='"+beanUpdate.getSousmod_id()+"' "   );
			
			this.hibernateTemplate.bulkUpdate(" update  PrivilegeBean  e  set  " +
					"     e.modBean.mod_id="+beanUpdate.getModuleBean().getMod_id()+"   where  e.pkPriv.smfonctionmodel.pk.soumBean.sousmod_id="+beanUpdate.getSousmod_id()+" "   );
			
			
			this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeleteSousModule(SousModuleBean beanDelete) {

		try {
			this.setIdBean((SousModuleBean) getObjectValueModel(FORM_BEAN), beanDelete, SousModuleTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanDelete);
			this.hibernateTemplate.delete(beanDelete);
			this.saveTrace(beanDelete);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	 
	
	 

	public Boolean saveSousModule(SousModuleBean sousModuleBean){
		
		try
        {
			hibernateTemplate.save(sousModuleBean);
			return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
		
		
		 
	}

}
