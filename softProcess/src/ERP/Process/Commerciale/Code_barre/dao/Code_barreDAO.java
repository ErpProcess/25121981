package ERP.Process.Commerciale.Code_barre.dao;

import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.model.Det_code_barre;
import ERP.Process.Commerciale.Code_barre.template.Code_barreTemplate;

@Repository
public class Code_barreDAO extends GenericWeb {
	
	
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<Code_barreBean> doFindArticleCode_barreVersion(ArticleBean beanSearch)
			throws Exception {
		 
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		String requette = " select  bean   FROM    Code_barreBean    bean    WHERE     1=1       ";
		
		if( !StringUtils.isEmpty(beanSearch.getPk_article().getAr_id()) )                
			requette+="  AND  bean.pk.ar_bean.pk_article.ar_id='"+beanSearch.getPk_article().getAr_id()+"' ";  
		
		if( !StringUtils.isEmpty(beanSearch.getArcodbar()) )                
			requette+="  AND  bean.pk.code_barre='"+beanSearch.getArcodbar()+"' ";  
		
		if( !StringUtils.isEmpty(beanSearch.getAr_libelle()) )            
			requette+="  AND  bean.designation_libelle  LIKE  '%"+beanSearch.getAr_libelle()+"%'   ";  
		
		if( !StringUtils.isEmpty(beanSearch.getFam_art().getFam_id()) )   
			requette+="  AND  bean.pk.ar_bean.fam_art.fam_id='"+beanSearch.getFam_art().getFam_id()+"' ";
		
		if(!StringUtils.isEmpty(beanSearch.getBean_sitcod().getData_id())) 
			requette+="     AND  bean.pk.ar_bean.bean_sitcod.data_id='"+beanSearch.getBean_sitcod().getData_id()+"' ";
		
		
		if(!StringUtils.isEmpty(beanSearch.getPk_article().getEtabBean().getPk_etab().getEtab_id()))
			requette += "    AND  bean.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+beanSearch.getPk_article().getEtabBean().getPk_etab().getEtab_id()+"' ";
		
		
		if(!StringUtils.isEmpty(beanSearch.getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()))
		    requette +="   AND  bean.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beanSearch.getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"' ";
		 
		  
		 
			
			
		
		return hibernateTemplate.find(requette);
		
	}

	@SuppressWarnings("unchecked")
	public List<Code_barreBean> doFindListCode_barreVersion(Code_barreBean beanSearch)
			throws Exception {
		

		
		String requette = " select  bean   FROM    Code_barreBean    bean    WHERE     1=1       ";
		
		if(!StringUtils.isEmpty(beanSearch.getPk().getCode_barre()))
		requette += "  AND   bean.pk.code_barre ='"+beanSearch.getPk().getCode_barre()+"'";
		
		if(!StringUtils.isEmpty(beanSearch.getPk().getAr_bean().getPk_article().getAr_id()))
			requette += "  AND   bean.pk.ar_bean.pk_article.ar_id ='"+beanSearch.getPk().getAr_bean().getPk_article().getAr_id()+"'";
		
		
		if(!StringUtils.isEmpty(beanSearch.getDesignation_libelle()))
			requette += "  AND   bean.designation_libelle  like '%"+beanSearch.getDesignation_libelle()+"%'";
		
		if(!StringUtils.isEmpty(beanSearch.getCondition_article_seulement_vente()))
			requette += beanSearch.getCondition_article_seulement_vente();
		
		if(!StringUtils.isEmpty(beanSearch.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()))
			requette += "    AND  bean.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+beanSearch.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"' ";
		
		if(!StringUtils.isEmpty(beanSearch.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()))
		    requette +="   AND  bean.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beanSearch.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"' ";
		
		 
			
		return hibernateTemplate.find(requette);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Code_barreBean> doFindList_detail_Code_barre(Code_barreBean beanSearch)
			throws Exception {
		
		 
		
		 
		String requette = " select  detBean   FROM   Det_code_barre detBean    WHERE     1=1       ";
		
		requette += "  AND  pk_det_codBare.bean_cod_bar.pk.code_barre ='"+beanSearch.getPk().getCode_barre()+"'";
		
		return hibernateTemplate.find(requette);
	}

	public Boolean doSaveCode_barre(Code_barreBean beanSave) throws Exception {
		boolean result = false;
		try {
			// this.setBeanSession_TraceValueInto_Cur_Bean(beanSave);
			List<Code_barreBean> list_cod_barr_master = (List) getObjectValueModel(Code_barreTemplate.LIST_COD_BARR_MASTER);
			for (Code_barreBean beanIns : list_cod_barr_master) {
				setPk_Soc_Etab(
						beanIns,
						"pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id",
						"pk.ar_bean.pk_article.etabBean.pk_etab.etab_id");
				this.hibernateTemplate.save(beanIns);

				List<Det_code_barre> lis_det = beanIns.getList_detail_cod_bar();
				int inc = 0;
				for (Det_code_barre bean_det_Ins : lis_det) {
					bean_det_Ins.setNum_ligne(new Integer(inc++));
					setPk_Soc_Etab(
							bean_det_Ins,
							"pk_det_codBare.bean_cod_bar.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id",
							"pk_det_codBare.bean_cod_bar.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id");
					bean_det_Ins.getPk_det_codBare().getBean_cod_bar().getPk().setCode_barre(beanIns.getPk().getCode_barre());
					this.hibernateTemplate.save(bean_det_Ins);
				}

			}
			// this.saveTrace(beanSave);
			result = true;
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
	}

	public Boolean doUpdateCode_barre(Code_barreBean beanUpdate)
			throws Exception {
		boolean result = false;
		try {
			Code_barreBean ref =(Code_barreBean)getObjectValueModel(FORM_BEAN);
			beanUpdate.getPk().setAr_bean(ref.getPk().getAr_bean());
			this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result = true;
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
	}

	public Boolean doDeleteCode_barre(Code_barreBean beanDelete)
			throws Exception {
		boolean result = false;
		try {
			
			Code_barreBean ref =(Code_barreBean)getObjectValueModel(FORM_BEAN);
			beanDelete.getPk().setAr_bean(ref.getPk().getAr_bean());
			hibernateTemplate.delete(beanDelete);
			this.saveTrace(beanDelete);
			result = true;
		} catch (Exception ex) {
			result = false;
			this.hibernateTemplate.clear();
			throw ex;
		}
		return result;
	}
}
