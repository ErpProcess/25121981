package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model.PaysvilleposteBean;
 
@Repository
public class PaysvilleposteDAO extends  GenericWeb  {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<PaysvilleposteBean> doFindListPaysvilleposte(PaysvilleposteBean searchBean) {
		
	 
		  String requette="  from   PaysvilleposteBean bean  where 1=1  ";
			
			if( !StringUtils.isEmpty(searchBean.getPaycod()) )      requette+="  AND  bean.paycod='"+searchBean.getPaycod()+"' ";  
			
			if( !StringUtils.isEmpty(searchBean.getPaylib()) )      requette+="  AND  bean.paylib='"+searchBean.getPaylib()+"' ";
			
			if(!StringUtils.isEmpty(searchBean.getProvince()))   requette+="     AND  bean.province='"+searchBean.getProvince()+"'     ";
			 
			
			 requette+= "   ORDER  By  bean.paycod ";
			try {
				return hibernateTemplate.find(requette);
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} 
		
	}
	
 
	 
	public Boolean savePaysvilleposte(PaysvilleposteBean beanSave) {

		try {
			//this.setCreValueFieldTraceOject(beanSave);
			this.hibernateTemplate.save(beanSave);
			//this.saveTrace(beanSave,"usr_id");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updatePaysvilleposte(PaysvilleposteBean beanUpdate) {

		try {
			//this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			//this.saveTrace(beanUpdate, "usr_id");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeletePaysvilleposte(PaysvilleposteBean beanDelete) {

		try {
			hibernateTemplate.delete(beanDelete);
			//this.saveTrace(beanDelete, "usr_id");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	 

}
