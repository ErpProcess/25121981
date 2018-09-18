package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.template.UtilisateurTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
 
@Repository
public class UtilisateurDAO extends  GenericWeb    {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<UtilisateurBean> doFindListUtilisateur(UtilisateurBean beanSearch) {
		
		 String requette=" select  b   FROM    UtilisateurBean b    WHERE     1=1       ";
		
		/*String requette="  SELECT  b.usr_id, b.usr_nom, b.usr_pre,   to_char(b.usr_date_naiss,'MM-DD-YYYY')    "+
		"    from UtilisateurBean b   WHERE     1=1 ";*/

		 
			if(!StringUtils.isEmpty(beanSearch.getUsr_login()) )   requette+="   AND  b.usr_login='"+beanSearch.getUsr_login()+"'    ";
			
			if(!StringUtils.isEmpty(beanSearch.getUsr_pwd()) )  requette+="   AND  b.usr_pwd='"+beanSearch.getUsr_pwd()+"'        ";
		
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
		
	}
	 
 

	 
	public Boolean saveUtilisateur(UtilisateurBean beanSave) {

		try {
			this.setBeanSession_TraceValueInto_Cur_Beanv_1(beanSave);
			this.hibernateTemplate.save(beanSave);
			//this.saveTrace(beanSave);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateUtilisateur(UtilisateurBean beanUpdate) throws Exception {

		try {
			this.setIdBean((UtilisateurBean) getObjectValueModel(FORM_BEAN), beanUpdate, UtilisateurTemplate.id_entite);
			
			
			 
			 
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			throw ex;
		}
		 

	}

	public Boolean dodeleteUtilisateur(UtilisateurBean beanDelete) {

		try {
			this.setIdBean((UtilisateurBean) getObjectValueModel(FORM_BEAN), beanDelete, UtilisateurTemplate.id_entite);
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
