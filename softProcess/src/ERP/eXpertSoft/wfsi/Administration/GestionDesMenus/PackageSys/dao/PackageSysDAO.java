package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean; 
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.util.PackageSysTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.template.UtilisateurTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

/**
 * Contact DAO class.
 * 
 */
@Repository
public   class PackageSysDAO  extends  GenericWeb {

	private HibernateTemplate hibernateTemplate;
	
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<PackageSysBean> getPackageSyss(PackageSysBean beanpSys) {
		
		String requette=" from PackageSysBean bean where 1=1      ";
		
		if(beanpSys.getPack_libelle()!=null && !beanpSys.getPack_libelle().equals("") ){
			requette+="  AND   bean.pack_libelle  LIKE  '"+beanpSys.getPack_libelle()+"%' ";
		}
		
		if(beanpSys.getPack_id()!=null  ){
			requette+="  AND   bean.pack_id  ="+beanpSys.getPack_id()+" ";
		}
		
		requette+="     ORDER  by  bean.pack_ordre   ";
		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	 
 

	public Boolean savePackageSys(PackageSysBean packageSys) {

		
		try {
			//List<Integer> list=hibernateTemplate.find(" select max(pack_id)  from  PackageSysBean ");
			//Integer integdef=list.get(0);
			
			//packageSys.setPack_id(BigDecimal.valueOf(integdef+1));
			hibernateTemplate.save(packageSys);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updatePackageSys(PackageSysBean packageSysUpd) {

		try {
			setIdBean((PackageSysBean) getObjectValueModel(FORM_BEAN), packageSysUpd, PackageSysTemplate.id_entite);
			hibernateTemplate.update(packageSysUpd);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean deletePackageSys(PackageSysBean delBEan) {

		try {
			setIdBean((PackageSysBean) getObjectValueModel(FORM_BEAN), delBEan, PackageSysTemplate.id_entite);
			hibernateTemplate.delete(delBEan);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
