package ERP.Process.Commerciale.TarificationPrtvArticle.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.template.TarificationPrtvArticleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
 
@Repository
public class TarificationPrtvArticleDAO extends  GenericWeb    {
	
	
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<TarificationPrtvArticleBean> doFindListTarificationPrtvArticle(TarificationPrtvArticleBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    TarificationPrtvArticleBean    bean    WHERE     1=1       ";
		  
		    
		    if( !StringUtils.isEmpty(beanSearch.getTarif_prim_id()) )  
			    requette+="   AND   bean.tarif_prim_id = '"+beanSearch.getTarif_prim_id()+"'        ";
		    
			 if(   beanSearch.getDate_prim_trf() != null )  
				    requette+="   AND   bean.date_prim_trf = "+ProcessDate.getStringFormatDate( beanSearch.getDate_prim_trf())+"       ";
			 
			 if( !StringUtils.isEmpty(beanSearch.getFkCode_barre().getPk().getCode_barre()) )  
				    requette+="   AND   bean.fkCode_barre.pk.code_barre = '"+beanSearch.getFkCode_barre().getPk().getCode_barre()+"'        ";
			 
			 if(  beanSearch.getTarif_unit_article()!=null )  
				    requette+="   AND   bean.tarif_unit_article ="+beanSearch.getTarif_unit_article()+"      ";    
			 
			 if( beanSearch.getGroupe().getGrp_prim_trf_id()!= null  )  
				    requette+="   AND   bean.groupe.grp_prim_trf_id ="+beanSearch.getGroupe().getGrp_prim_trf_id()+"        ";

			 if(  !StringUtils.isEmpty(beanSearch.getCondition_etat_achat() )  )  
				 requette+=""+beanSearch.getCondition_etat_achat();
		    
			return   hibernateTemplate.find(requette);
	}
	
	
			@SuppressWarnings("unchecked")
			public HashMap doFindListTarificationPrtvArticle_ACHAT_DateMax(TarificationPrtvArticleBean beanSearch) throws Exception {
				
			 
				
				
				 HashMap mapresul  =  new HashMap();
				 
			     String requette="   select  bean   FROM    TarificationPrtvArticleBean    bean    WHERE     1=1       ";
			  
				    requette+="    AND      bean.groupe.grp_prim_trf_id="+beanSearch.getGroupe().getGrp_prim_trf_id()+"   " ;
				    
				    
				    if( !StringUtils.isEmpty(beanSearch.getFkCode_barre().getPk().getCode_barre()) )  
					    requette+="   AND   bean.fkCode_barre.pk.code_barre = '"+beanSearch.getFkCode_barre().getPk().getCode_barre()+"'        ";
				    
				   
				    
				    requette+=     setSocieteEtabForArticle("bean.fkCode_barre", false);
				            
				    requette+="    AND      bean.date_prim_trf IN ( select  MAX(BA.date_prim_trf)  from  TarificationPrtvArticleBean BA           " +
				    		"       where   bean.fkCode_barre.pk.code_barre =  BA.fkCode_barre.pk.code_barre                                      " +
				    		
				    		"         AND   bean.groupe.grp_prim_trf_id=BA.groupe.grp_prim_trf_id                                                 "+
				    		"         AND   BA.date_prim_trf  <= '"+ProcessDate.getStringFormatDate(beanSearch.getDate_prim_trf())+"'   )         "; 
				    
				    List<TarificationPrtvArticleBean>  list_TarificationPrtvArticle= hibernateTemplate.find(requette);
				    HashMap mapTarif  =	ProcessUtil.getHashMap_code_bean(list_TarificationPrtvArticle, "fkCode_barre.pk.code_barre");
				    List list_des_reference= new ArrayList();
				    for (int i = 0; i < list_TarificationPrtvArticle.size(); i++) {
				    	TarificationPrtvArticleBean tPrtvArticleBean =list_TarificationPrtvArticle.get(i);
				    	list_des_reference.add(tPrtvArticleBean.getFkCode_barre());
					}
				    mapresul.put("mapTarif", mapTarif);
				    mapresul.put("list_des_reference", list_des_reference);
				    mapresul.put("list_TarificationPrtvArticle", list_TarificationPrtvArticle);
				return mapresul;
		}
	
			
			@SuppressWarnings("unchecked")
			public List<TarificationPrtvArticleBean> doFindDateMaxTarificationPrtvArticleVente(TarificationPrtvArticleBean beanSearch) throws Exception {
				
			    String requette=" select  bean   FROM    TarificationPrtvArticleBean    bean    WHERE     1=1       ";
			 
			 
//			 //if( !StringUtils.isEmpty(beanSearch.getPk().getFkCode_barre().getPk().getCode_barre()) )  
//				    requette+="   AND   bean.pk.fkCode_barre.pk.code_barre = '"+beanSearch.getPk().getFkCode_barre().getPk().getCode_barre()+"'        ";
//			 
//			 if( !StringUtils.isEmpty(beanSearch.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()) )  
//				    requette+="   AND   bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id = '"+beanSearch.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'        ";
//			 
//			   
			 
			   
				    requette+="    AND      bean.pk.typ_trfBean.type_trf_id  IS NOT NULL      AND    " +
				    
				    		"               bean.pk.date_trf IN ( select  MAX(BA.pk.date_trf)  from  TarificationPrtvArticleBean BA           " +
				    		"       where   bean.pk.fkCode_barre.pk.code_barre =  BA.pk.fkCode_barre.pk.code_barre   " +
				    		"         AND   BA.pk.typ_trfBean.type_trf_id    IS NOT NULL   AND  " +
				    		"               BA.pk.date_trf  <= '"+BDateTime.getDateActuel_system()+"'      )      " ;
			 
				return   hibernateTemplate.find(requette);
		}
	
	
	public Boolean doSaveTarificationPrtvArticle(TarificationPrtvArticleBean beanSave) throws Exception {
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
	public Boolean doUpdateTarificationPrtvArticle(TarificationPrtvArticleBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
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
	public Boolean doDeleteTarificationPrtvArticle(TarificationPrtvArticleBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
			this.setBeanTrace(beanDelete);
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
