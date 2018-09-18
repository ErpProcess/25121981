package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

 
@Repository
public class Data_entite_simpleDAO extends  GenericWeb  {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<Data_entite_simpleBean> doFindListData_entite_simple(Data_entite_simpleBean searchBean) {
		
	 
		  String requette=" from   Data_entite_simpleBean bean  where 1=1  ";
			
			if( !StringUtils.isEmpty(searchBean.getCode_entite()) )      requette+="  AND  bean.code_entite='"+searchBean.getCode_entite()+"' ";  
			
			if( !StringUtils.isEmpty(searchBean.getData_id()) )      requette+="  AND  bean.data_id='"+searchBean.getData_id()+"' ";
			
			//if(!StringUtils.isEmpty(searchBean.getSoc_id()))   requette+="     AND  bean.soc_id='"+searchBean.getSoc_id()+"'     ";
			 
			
			requette+= "   ORDER  By  bean.data_ordre ";
			try {
				return hibernateTemplate.find(requette);
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} 
		
	}
	
 
	 
	public Boolean saveData_entite_simple(Data_entite_simpleBean beanSave) {

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

	public Boolean updateData_entite_simple(Data_entite_simpleBean beanUpdate) {

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

	public Boolean dodeleteData_entite_simple(Data_entite_simpleBean beanDelete) {

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
