package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.dao;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.template.ProfileTemplate;
@Repository
public class ProfileDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<ProfileBean> doFindListProfile(ProfileBean beanSearch) {
		 String requette=" select  b   FROM    ProfileBean b    WHERE     1=1       ";
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public Boolean saveProfile(ProfileBean beanSave) {
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
	public Boolean updateProfile(ProfileBean beanUpdate) {
		try {
			setIdBean((ProfileBean) getObjectValueModel(FORM_BEAN), beanUpdate, ProfileTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			long yourmilliseconds = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
			Date resultdate = new Date(yourmilliseconds);
			System.out.println("ssssssssssss"+sdf.format(resultdate));
			
			System.out.println("wwwwwwwwwwwwwwww"+yourmilliseconds);
			 
			
			long elapsed = System.currentTimeMillis();
			DateFormat df = new SimpleDateFormat("HH:mm:ss");

			System.out.println("xxxxxxxxxxxxxxxx"+df.format(new Date(elapsed)));
			
			
			 
			
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public Boolean dodeleteProfile(ProfileBean beanDelete) {
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
