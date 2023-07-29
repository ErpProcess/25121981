package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.dao;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model.SmfonctionModel;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.template.SmfonctionTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
@Repository
public class SmfonctionDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<SmfonctionModel> doFindListSmfonction(SmfonctionModel beanSearch) {
		 String requette=" select  distinct bean FROM SmfonctionModel bean WHERE 1=1 ";
		 
		 if(beanSearch.getPk().getSoumBean().getSousmod_id()!=null)   
			 
			 requette+="   AND  bean.pk.soumBean.sousmod_id="+beanSearch.getPk().getSoumBean().getSousmod_id()+"    ";
		     requette+="    order by    bean.pk.soumBean.sousmod_id    ";
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public Boolean saveSmfonction(SmfonctionModel beanSave) throws Exception {
		boolean re=false;
		try {
			List listDataToInsert=(List) getObjectValueModel("listGridEditable_smfction");
			List ListOrigine=(List) getObjectValueModel("ListOrigine");
			
			//this.hibernateTemplate.deleteAll(ListOrigine);

			for (int i = 0; i < listDataToInsert.size(); i++) {
				SmfonctionModel beanIns=(SmfonctionModel) listDataToInsert.get(i);
				if(beanIns.getTo_check().equals("yes"))
				this.hibernateTemplate.saveOrUpdate(beanIns);
			}
			

			re=  true;
		} catch (Exception ex) {
			throw ex;
			
		}
		return re;
	}
	public Boolean updateSmfonction(SmfonctionModel beanUpdate) {
		try {
			setIdBean((SmfonctionModel) getObjectValueModel(FORM_BEAN), beanUpdate, SmfonctionTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public Boolean doDeleteSmfonction(SmfonctionModel beanDelete) throws Exception {
		boolean re=false;
		try {
			List listDataToInsert=(List) getObjectValueModel("list_Grid_Editable_For_Annuler");
			for (int i = 0; i < listDataToInsert.size(); i++) {
				SmfonctionModel beanIns=(SmfonctionModel) listDataToInsert.get(i);
				if(beanIns.getTo_check().equals("yes")){
					hibernateTemplate.bulkUpdate(" DELETE FROM PrivilegeBean b    where    " +
							"      b.pkPriv.smfonctionmodel.pk.soumBean.sousmod_id="+beanIns.getPk().getSoumBean().getSousmod_id()+"    " +
							" and  b.pkPriv.smfonctionmodel.pk.fcBean.fct_id="+beanIns.getPk().getFcBean().getFct_id()+"   ");
					this.hibernateTemplate.delete(beanIns);
					
				}
				
			} 
			
			 

			re=  true;
		} catch (Exception ex) {
			throw ex;
			
		}
		return re;
	}
}
