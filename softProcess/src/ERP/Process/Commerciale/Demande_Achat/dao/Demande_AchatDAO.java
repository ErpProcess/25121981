package ERP.Process.Commerciale.Demande_Achat.dao;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.Process.Commerciale.Achat.Retour_achat.model.Retour_achatBean;
import ERP.Process.Commerciale.Achat.Retour_achat.template.Retour_achatTemplate;
import ERP.Process.Commerciale.Demande_Achat.model.Demande_achatBean;
import ERP.Process.Commerciale.Demande_Achat.model.Det_demande_AchatBean;
import ERP.Process.Commerciale.Demande_Achat.template.Demande_AchatTemplate;
@Repository
public class Demande_AchatDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<Demande_achatBean> doFindListDemande_Achat(Demande_achatBean beanSearch) throws Exception {
		
		
		
		
		
		
		
		    String requette=" select  bean   FROM    Demande_achatBean    bean    WHERE     1=1       ";
		    
			if (!StringUtils.isEmpty(beanSearch.getDem_achat_id()))
				   requette += "   AND   bean.dem_achat_id = '"+beanSearch.getDem_achat_id()+"'        ";
		    
		    if (beanSearch.getDem_date() != null) 
		    	requette += "   AND  bean.dem_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getDem_date())+"'        ";
		    
		if (   ( beanSearch.getDem_date2() != null ) ) 
		    	requette += "   AND  bean.dem_date <= '"+ProcessDate.getStringFormatDate(beanSearch.getDem_date2())+"'        ";
		
		
		
	
		if (!StringUtils.isEmpty(beanSearch.getFrsBean().getFrs_id()))
			requette += "   AND   bean.frsBean.frs_id = '" + beanSearch.getFrsBean().getFrs_id()+ "'        ";
		 
		
		
	 
		    
		    
		    
		    if(StringUtils.isNotEmpty(beanSearch.getCondition_etat()))
		    	   requette+="              "+beanSearch.getCondition_etat() ;
		    	
			return   hibernateTemplate.find(requette);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Det_demande_AchatBean> doFindList_detaille_Demande_Achat(Demande_achatBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    Det_demande_AchatBean    bean    WHERE     1=1   " +
		    		"      AND      bean.pk_det_dem_achat.dem_achatBean.dem_achat_id='"+beanSearch.getDem_achat_id()+"'    ";
			return   hibernateTemplate.find(requette);
	}
	
	
	@SuppressWarnings("unchecked")
	public Boolean saveDemande_Achat(Demande_achatBean beanSave) throws Exception {
	     boolean result=false; 
		try {
			setPk_Soc_Etab(beanSave, "etab_bean.pk_etab.soc_bean.soc_id", "etab_bean.pk_etab.etab_id");
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			this.saveTrace(beanSave);
			List <Det_demande_AchatBean> listData= (List) getObjectValueModel("listGridEditable_demande_achat");
			int num_ligne=0;
			int sizeTT=listData.size();
			for (int i = 0; i < sizeTT; i++) {
				Det_demande_AchatBean bean=listData.get(i);
				if(bean.getTo_check()!=null  &&  bean.getTo_check().equals("checked")){
					listData.remove(i);
					sizeTT--;
					i--;
					continue;
				}
				this.setBeanTrace(bean);
				bean.setNum_ligne(num_ligne++);
				bean.setSitcod("900");
				setPk_Soc_Etab(bean, "pk_det_dem_achat.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id", "pk_det_dem_achat.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id");
				bean.getPk_det_dem_achat().getDem_achatBean().setDem_achat_id(beanSave.getDem_achat_id());
				this.hibernateTemplate.save(bean);
			}
			
			this.saveTraceVersion_Master_detailles(listData, 
					Demande_AchatTemplate.Mapfield_det_achat, 
					Demande_AchatTemplate.id_entite_detaille_demande, 
					Demande_AchatTemplate.entite_det_dem_achat);
			
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	
	
	public Boolean ValiderDemande_Achat(Demande_achatBean beanddUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			
			Demande_achatBean  beanUpdates=(Demande_achatBean) getObjectValueModel(FORM_BEAN);
			beanddUpdate=beanUpdates;
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 if(bs.getFct_id().equals(GenericActionBean.Fn_Contremander)){
				 beanddUpdate.getModeBean().setFct_id(new BigDecimal("3"));
				 beanddUpdate.getModeBean().setFct_libelle("Modifier");
	 			}
			this.setUpdateValueFieldTraceOject(beanddUpdate);
			this.hibernateTemplate.update(beanddUpdate);
			this.saveTrace(beanddUpdate);
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
			
	public Boolean updateDemande_Achat(Demande_achatBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((Demande_achatBean) getObjectValueModel(FORM_BEAN), beanUpdate, Demande_AchatTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			setPk_Soc_Etab(beanUpdate, "etab_bean.pk_etab.soc_bean.soc_id", "etab_bean.pk_etab.etab_id");
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			
			
			
			List listToSupprimer = (List) getObjectValueModel("listGridEditable_demande_achatToSupprimer" );
			this.hibernateTemplate.deleteAll(listToSupprimer);
			this.hibernateTemplate.flush();
			this.hibernateTemplate.clear();
			List <Det_demande_AchatBean> listData= (List) getObjectValueModel("listGridEditable_demande_achat");
			int num_ligne=0;
			int sizeTT=listData.size();
			for (int i = 0; i < sizeTT; i++) {
				Det_demande_AchatBean bean=listData.get(i);
				if(bean.getTo_check()!=null  &&  bean.getTo_check().equals("checked")){
					listData.remove(i);
					sizeTT--;
					i--;
					continue;
				}
				bean.setNum_ligne(num_ligne++);
				bean.setSitcod("900");
				setPk_Soc_Etab(bean, "pk_det_dem_achat.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id", "pk_det_dem_achat.fkCode_barre.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id");
				bean.getPk_det_dem_achat().getDem_achatBean().setDem_achat_id(beanUpdate.getDem_achat_id());
				this.hibernateTemplate.save(bean);
			}
			
			this.saveTraceVersion_Master_detailles(listData, 
					Demande_AchatTemplate.Mapfield_det_achat, 
					Demande_AchatTemplate.id_entite_detaille_demande, 
					Demande_AchatTemplate.entite_det_dem_achat);
			result=true;
			
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	@SuppressWarnings("unchecked")
	public Boolean dodeleteDemande_Achat(Demande_achatBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
			
	 
			
			
			
			Demande_achatBean   rowBean = (Demande_achatBean) getObjectValueModel(FORM_BEAN);
			List  listGridEditable_demande_achat= (List) getObjectValueModel("listGridEditable_demande_achatToSupprimer");
			hibernateTemplate.deleteAll(listGridEditable_demande_achat);
			hibernateTemplate.delete(rowBean);
	        this.saveTrace(rowBean);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
}
