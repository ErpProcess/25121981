package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.IdEntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;

/**
 * Contact DAO class.
 * 
 */
@Repository
public class EntiteAdminDAO  {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<EntiteAdminBean> getListEntiteAdmin(EntiteAdminBean beanSearch) {

		String requette = "  FROM EntiteAdminBean  bean where 1=1    ";
		
			if (beanSearch.getEnt_libelle() != null  && !beanSearch.getEnt_libelle().equals("")) {
				requette += "  AND  bean.ent_libelle  LIKE  '"+beanSearch.getEnt_libelle()+"'   ";
			}
	
			if (beanSearch.getPk_entite_admin().getEnt_id() != null  && !beanSearch.getPk_entite_admin().getEnt_id().equals("")) {
				requette += "  AND  bean.pk_entite_admin.ent_id  ='"+beanSearch.getPk_entite_admin().getEnt_id()+"'    ";
			}
	
			if (beanSearch.getPk_entite_admin().getLang_id() != null  && !beanSearch.getPk_entite_admin().getLang_id().equals("")) {
				requette += "  AND  bean.pk_entite_admin.lang_id  ='"+beanSearch.getPk_entite_admin().getLang_id()+"'  ";
			}
			
			if (beanSearch.getSousmod_id() != null  && !beanSearch.getSousmod_id().equals("")) {
				requette += "  AND  bean.sousmod_id  = '"+beanSearch.getSousmod_id()+"'   ";
			}
			
			if (beanSearch.getChaineColumn()!= null  && !beanSearch.getChaineColumn().equals("")) {
				requette += "  AND  bean.column_name  not in ( "+beanSearch.getChaineColumn()+"   )    ";
			}

		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<EntiteAdminBean> getLabelLibelle(EntiteAdminBean beanSearch) {

		String requette = "  from EntiteAdminBean bean  where 1=1  ";
		 

		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public Boolean saveEntiteAdmin_with_List(List listLibbelle) {

		try {

			for (int i = 0; i < listLibbelle.size(); i++) {
				EntiteAdminBean bean = (EntiteAdminBean) listLibbelle.get(i);
					hibernateTemplate.save(bean);
			}

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
	
	
	public Boolean saveEntiteAdmin(EntiteAdminBean bean,List listLangueEntite  ) throws Exception {

		try {
			EntiteAdminBean beanEncour= (EntiteAdminBean) ProcessUtil.cloneObject(bean);
			IdEntiteAdminBean copi_IdEntiteAdminBean= (IdEntiteAdminBean) ProcessUtil.cloneObject(bean.getPk_entite_admin());
				for (int i = 0; i < listLangueEntite.size(); i++) {
					GLangueBean  bean2= (GLangueBean) listLangueEntite.get(i) ;
					
					if(!bean2.getLang_id().equals( bean.getPk_entite_admin().getLang_id())){
						  
						  beanEncour.getPk_entite_admin().setEnt_id(bean.getSousmod_id()+"/"+copi_IdEntiteAdminBean.getEnt_id());
						  beanEncour.getPk_entite_admin().setLang_id(bean2.getLang_id());
						  beanEncour.setEnt_libelle(bean.getEnt_libelle()+"-"+bean2.getLang_id());
						  EntiteAdminBean beanSreach  = new EntiteAdminBean();
						  beanSreach.getPk_entite_admin().setEnt_id(bean.getSousmod_id()+"/"+copi_IdEntiteAdminBean.getEnt_id());
						  List listddd =getListEntiteAdmin(beanSreach);
						  if(listddd==null ||  listddd.size()==0) 
						  hibernateTemplate.save(beanEncour);
						  
					}
				}
				
				  bean.getPk_entite_admin().setEnt_id(bean.getSousmod_id()+"/"+bean.getPk_entite_admin().getEnt_id());
				  List listddd =getListEntiteAdmin(bean);
				  if(listddd==null ||  listddd.size()==0) 
				  hibernateTemplate.save(bean);
			return true;
		} catch (Exception ex) {
			throw ex;
		}

	}
	

	public Boolean saveEntiteAdminColunm(EntiteAdminBean bean) {

		try {

			hibernateTemplate.save(bean);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateEntiteAdmin(EntiteAdminBean beanUp) throws Exception {

		try {
			hibernateTemplate.bulkUpdate(" " +
					"  UPDATE  EntiteAdminBean e  set   e.ent_libelle='"+GenericWeb.getString(beanUp.getEnt_libelle())+"' , e.ent_abrv='"+GenericWeb.getString(beanUp.getEnt_abrv())+"'  " +
							"  where      " +
					"                       e.pk_entite_admin.ent_id='"+beanUp.getPk_entite_admin().getEnt_id()+"'       " +
							"        and    e.pk_entite_admin.lang_id='"+beanUp.getPk_entite_admin().getLang_id()+"'     ");
			 
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
			 
		}
return true;
	}

	public Boolean deleteEntiteAdmin(EntiteAdminBean beanDel) {

		try {
			hibernateTemplate.delete(beanDel);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}
