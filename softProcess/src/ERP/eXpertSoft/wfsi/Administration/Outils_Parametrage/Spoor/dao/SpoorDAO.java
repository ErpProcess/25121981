package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model.SpoorBean;

/**
 * Contact DAO class.
 * 
 */
@Repository
public   class SpoorDAO  extends  GenericWeb implements ISpoorDAO {

	private HibernateTemplate hibernateTemplate;
	
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<SpoorBean> getSpoor(SpoorBean beanSearch) {
		
		   String requette=" select bean    from   SpoorBean bean    " +
		   		"   where    " +
		   		"     1=1   ";
		   
			if(!StringUtils.isEmpty(beanSearch.getSous_mod()) )   
				requette+="   AND   bean.sous_mod='"+beanSearch.getSous_mod()+"'    ";
			
			if(!StringUtils.isEmpty(beanSearch.getSpoorPKBean().getUsr_login()) )  
				requette+="   AND   bean.spoorPKBean.usr_login='"+beanSearch.getSpoorPKBean().getUsr_login()+"'    ";
			
			if(!StringUtils.isEmpty(beanSearch.getFct_id()) )  
				requette+="   AND   bean.fct_id='"+beanSearch.getFct_id()+"'    ";
			
			if(!StringUtils.isEmpty(beanSearch.getOp_id()) )  
				requette+="   AND   bean.op_id='"+beanSearch.getOp_id()+"'    ";
		
		
		try {
			return hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
 

	public Boolean saveSpoor(SpoorBean beanSave) {

		try {
			hibernateTemplate.save(beanSave);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
	
	
	public String findmaxinteger() {

		try {
			
			
			List list=hibernateTemplate.find(" select max( bea.spoorPKBean.sp_id)   from  SpoorBean  bea ");
				Integer into=(Integer) list.get(0);
				int t=into!=null?into+1:0;
			return String.valueOf(t);
			 
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	
	public Boolean saveNewTrace(SpoorBean gspBean) {

		try {
			hibernateTemplate.save(gspBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
	
	 

	public Boolean updateSpoor(SpoorBean gLangueBean) {

		try {
			hibernateTemplate.update(gLangueBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean deleteSpoor(SpoorBean gLangueBean) {

		try {
			hibernateTemplate.delete(gLangueBean);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}
