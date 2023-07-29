package ERP.Process.Commerciale.Vente.Devis.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Vente.Devis.model.DetDevisBean;
import ERP.Process.Commerciale.Vente.Devis.model.DevisBean;
import ERP.Process.Commerciale.Vente.Devis.template.DevisTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
@Repository
public class DevisDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<DevisBean> doFindListDevis(DevisBean beanSearch) throws Exception {
		
		  String requette=" select  bean   FROM    DevisBean    bean    WHERE     1=1       ";
		    
		 if( !StringUtils.isEmpty(beanSearch.getDevis_id()) )  
			    requette+="   AND   bean.devis_id = '"+beanSearch.getDevis_id()+"'        "; 
		 
		 if( !StringUtils.isEmpty(beanSearch.getClient().getClt_id()) )  
			    requette+="   AND   bean.client.clt_id = '"+beanSearch.getClient().getClt_id()+"'        "; 
 
		 if(  beanSearch.getDev_date()!=null ) 
		 	    requette += "   AND  bean.dev_date >= '"+ProcessDate.getStringFormatDate(beanSearch.getDev_date())+"'        ";
		 
		 if(  beanSearch.getDev_date2()!=null ) 
		 	    requette += "   AND  bean.dev_date <= '"+ProcessDate.getStringFormatDate(beanSearch.getDev_date2())+"'        ";
		 
		 if( !StringUtils.isEmpty(beanSearch.getCondition_select_mode()) )  
			  requette   += ""+beanSearch.getCondition_select_mode() ;
		 
 
		return   hibernateTemplate.find(requette);
		
	}
	public Boolean doSaveDevis(DevisBean beanSave) throws Exception {
		
		boolean result = false;
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			this.setBeanTrace(beanSave);
			List listOfmyData=(List) getObjectValueModel( DevisTemplate.LIST_EDITABLE_DEVIS);
			DevisBean  reBean=(DevisBean) getObjectValueModel(DevisTemplate.BEAN_TOTAL);
			beanSave.setDev_remise(reBean.getDev_remise());
			beanSave.setDev_mnt_ht(reBean.getDev_mnt_ht());
			beanSave.setDev_mnt_tva(reBean.getDev_mnt_tva());
			beanSave.setDev_mnt_total(reBean.getDev_mnt_total());
			this.hibernateTemplate.save(beanSave);
			
			//this.saveTraceVersion1( beanSave  , Reception_achatTemplate.MapfieldBean  , Reception_achatTemplate.id_entite_achat  , Reception_achatTemplate.entites);
			
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetDevisBean detBean=(DetDevisBean) listOfmyData.get(i);
				if( detBean.getQuantite()==null) { continue; }
				if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
				detBean.getPk().setDevis(beanSave);
				this.hibernateTemplate.save(detBean);
				result_detaille=true;
			}
			if(!result_detaille)throwNewException("err_inser_deaill");
				
		//	this.saveTraceVersion_Master_detailles(listOfmyData, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			 
			//if( !StringUtils.isEmpty( beanSave.getDem_achat().getDem_achat_id()) )
			//this.hibernateTemplate.bulkUpdate(" UPDATE  Demande_achatBean b  set  b.modeBean.fct_id="+bs.getFct_id()+"   where   b.dem_achat_id='"+beanSave.getDem_achat().getDem_achat_id()+"' ");
			
			result = true;
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
		
	}
	 
	
	@SuppressWarnings("unchecked")
	public Boolean doUpdateDevis(DevisBean beanUpdate) throws Exception {
	 
		
		
		
		boolean result = false;
		try {
			  
			  BeanSession bs   = (BeanSession) getObjectValueModel(BEAN_SESSION);
			 List list_clone  = (List) getObjectValueModel(DevisTemplate.LIST_EDITABLE_DEVIS_ORIGIN);
			 this.hibernateTemplate.deleteAll(list_clone);
			 this.hibernateTemplate.flush();
		     this.hibernateTemplate.clear();
			 List listOfmyData     = (List) getObjectValueModel(DevisTemplate.LIST_EDITABLE_DEVIS);
			  
			 
			 
			
			 
			 boolean result_detaille = false;
				for (int i = 0; i < listOfmyData.size(); i++) {
					DetDevisBean detBean=(DetDevisBean) listOfmyData.get(i);
				    if( detBean.getQuantite()==null) { continue; }
					if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
					detBean.getPk().setDevis(beanUpdate);
					this.hibernateTemplate.save(detBean);
					result_detaille=true;
				}
			  if(!result_detaille)throwNewException("err_inser_deaill");	
			 //this.saveTraceVersion_Master_detailles(listOfmyData, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
			 this.setUpdateValueFieldTraceOject(beanUpdate);
			  
			 
			    DevisBean  reBean=(DevisBean) getObjectValueModel(DevisTemplate.BEAN_TOTAL);
			    beanUpdate.setDev_remise(reBean.getDev_remise());
			    beanUpdate.setDev_mnt_ht(reBean.getDev_mnt_ht());
			    beanUpdate.setDev_mnt_tva(reBean.getDev_mnt_tva());
			    beanUpdate.setDev_mnt_total(reBean.getDev_mnt_total());
			    this.hibernateTemplate.saveOrUpdate(beanUpdate);
			    //this.saveTraceVersion1(beanUpdate, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
			    result = true;
		 
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
		
		
		
		
		
		
		
	}
	 

public Boolean doDeleteDevis(DevisBean beanDeleteX)
	throws Exception {
boolean result = false;
try {
	
	 DevisBean  beanReCEP=(DevisBean) getObjectValueModel(FORM_BEAN); 
	 
	 List <DetDevisBean>List_det= new ArrayList<DetDevisBean>(beanReCEP.getList_detaille());
	
	
	 hibernateTemplate.deleteAll(List_det);
	 //this.saveTraceVersion_Master_detailles(List_det_recep_achat, Reception_achatTemplate.MapfieldBean_detaille, Reception_achatTemplate.id_entite_det_achat, Reception_achatTemplate.entites_detaille);
	 hibernateTemplate.flush();
	 hibernateTemplate.delete(beanReCEP);
	 /*this.saveTraceVersion1  (beanDelete,
			                 Reception_achatTemplate.MapfieldBean, 
			                 Reception_achatTemplate.id_entite_achat, 
			                 Reception_achatTemplate.entites      );*/
	 
	/* if(  beanDelete.getDem_achat()!=null )
			this.hibernateTemplate.bulkUpdate(" UPDATE  Demande_achatBean b  set 
			 b.modeBean.fct_id="+GenericActionBean.Fn_Valider+"   where 
			   b.dem_achat_id='"+beanDelete.getDem_achat().getDem_achat_id()+"'   ");*/
	 
	 
	
	result = true;
	
} catch (Exception ex) {
	result = false;
	this.hibernateTemplate.clear();
	throw ex;
}
return result;
}
	
public Boolean doExcuterTransactionForAchat( 	DevisBean  beanchange ) throws Exception {
		boolean result = false;
		try {
			 
			    this.setUpdateValueFieldTraceOject(beanchange);
			    DevisBean  reBean=(DevisBean) getObjectValueModel(DevisTemplate.BEAN_TOTAL);
			    beanchange.setDev_remise(reBean.getDev_remise());
			    beanchange.setDev_mnt_ht(reBean.getDev_mnt_ht());
			    beanchange.setDev_mnt_tva(reBean.getDev_mnt_tva());
			    beanchange.setDev_mnt_total(reBean.getDev_mnt_total());
			    this.hibernateTemplate.saveOrUpdate(beanchange);
			    //this.saveTraceVersion1(beanAnnul, Reception_achatTemplate.MapfieldBean, Reception_achatTemplate.id_entite_achat, Reception_achatTemplate.entites);
			    result = true;
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
	}
	
	
}
