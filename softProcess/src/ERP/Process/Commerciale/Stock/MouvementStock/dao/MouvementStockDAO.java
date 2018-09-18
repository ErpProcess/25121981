package ERP.Process.Commerciale.Stock.MouvementStock.dao;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.MouvementStock.template.MouvementStockTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;



@Repository
public class MouvementStockDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<MouvementSerieBean> doFindListMouvementStock(MouvementSerieBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    MouvementSerieBean    bean    WHERE     1=1       ";
		   
		 if(  beanSearch.getDate_mvt_serie()!=null )  
			    requette+="   AND   bean.date_mvt_serie = '"+ProcessDate.getStringFormatDate(beanSearch.getDate_mvt_serie())+"'        ";
			    
		  
		 if( !StringUtils.isEmpty(beanSearch.getPk().getDocument_com_id()) )  
			    requette+="   AND   bean.pk.document_com_id = '"+beanSearch.getPk().getDocument_com_id()+"'        ";   
		 
		 
		  if(   !StringUtils.isEmpty((beanSearch.getPk().getSerieBean().getPk().getNum_serie()) )) 
			    requette+="   AND   bean.pk.serieBean.pk.num_serie = '"+beanSearch.getPk().getSerieBean().getPk().getNum_serie()+"'       ";  
		 
		  if(   beanSearch.getPk().getSerieBean().getPk().getDepot().getDepot_id() !=null  ) 
			    requette+="   AND   bean.pk.serieBean.pk.depot.depot_id = "+beanSearch.getPk().getSerieBean().getPk().getDepot().getDepot_id()+"       ";
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getPk().getSerieBean().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()) )  
			    requette+="   AND   bean.pk.serieBean.fkCode_barre.pk.ar_bean.pk_article.ar_id = '"+beanSearch.getPk().getSerieBean().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'        ";
		 
		 if( !StringUtils.isEmpty(beanSearch.getPk().getSerieBean().getFkCode_barre().getPk().getCode_barre()) )  
			    requette+="   AND   bean.pk.serieBean.fkCode_barre.pk.code_barre = '"+beanSearch.getPk().getSerieBean().getFkCode_barre().getPk().getCode_barre()+"'        ";  
		 
		 if(  (beanSearch.getQuantite_operation()!=null) )  
			    requette+="   AND   bean.quantite_operation = "+beanSearch.getQuantite_operation()+"        ";  
		 
		 if( !StringUtils.isEmpty(beanSearch.getPk().getNat_mvt().getNature_mvt_id()) )  
			    requette+="   AND   bean.pk.nat_mvt.nature_mvt_id = '"+beanSearch.getPk().getNat_mvt().getNature_mvt_id()+"'        "; 
		 
		 
			return   hibernateTemplate.find(requette);
			
			
			
	}
	public Boolean doSaveMouvementStock(MouvementSerieBean beanSave) throws Exception {
	     boolean result=false; 
		try {
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			this.saveTrace(beanSave);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doUpdateMouvementStock(MouvementSerieBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((MouvementSerieBean) getObjectValueModel(FORM_BEAN), beanUpdate, MouvementStockTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doDeleteMouvementStock(MouvementSerieBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
			hibernateTemplate.delete(beanDelete);
	        this.saveTrace(beanDelete);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
}
