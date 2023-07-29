package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.dao;

import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.template.SocieteTemplate;
 
@Repository
public class SocieteDAO extends  GenericWeb  {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<SocieteBean> doFindListSociete(SocieteBean beanSearch) {
		
		String requette="   FROM    SocieteBean bean      WHERE     1=1   ";
		 
			
			
			if( getBeanSession() !=null   && getBeanSession().getUsr_id().equals("1111") ) {
				if(!StringUtils.isEmpty(beanSearch.getSoc_id()) )   requette+="   AND  bean.soc_id='"+beanSearch.getSoc_id()+"'    ";
				
				if(!StringUtils.isEmpty(beanSearch.getSoc_lib()) )  requette+="   AND  bean.soc_lib='"+beanSearch.getSoc_lib()+"'        ";
			 }else {
				 requette+="   AND  bean.soc_id='"+getBeanSession().getSoc_id()+"'    ";
			 }
		     
		 
			
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
		
	}
	 
	public Boolean saveSociete(SocieteBean beanSave) {

		try {
			this.setBeanTrace(beanSave);
			FileFactureFournisseur insertBean =(FileFactureFournisseur) getObjectValueModel("MultipartFile");
			if(insertBean!=null){
				this.setBeanTrace(insertBean);
				this.hibernateTemplate.save(insertBean);
			}
			beanSave.setFile_id(insertBean.getFile_id());
			this.hibernateTemplate.save(beanSave);
			this.saveTrace(beanSave);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateSociete(SocieteBean beanUpdate) {

		try {
			setIdBean((SocieteBean) getObjectValueModel(FORM_BEAN), beanUpdate, SocieteTemplate.idEntite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			FileFactureFournisseur insertBean =(FileFactureFournisseur) getObjectValueModel("MultipartFile");
			if(insertBean!=null){
				this.setBeanTrace(insertBean);
				this.hibernateTemplate.save(insertBean);
				beanUpdate.setFile_id(insertBean.getFile_id());
			}
			
			this.hibernateTemplate.update(beanUpdate);
			//this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeleteSociete(SocieteBean beanDelete) {

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
