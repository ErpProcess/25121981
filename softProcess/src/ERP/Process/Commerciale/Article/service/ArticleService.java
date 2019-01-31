package ERP.Process.Commerciale.Article.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Article.dao.ArticleDAO;
import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.model.ClientArticleBean;
import ERP.Process.Commerciale.Article.model.LieuxArticleBean;
import ERP.Process.Commerciale.Article.model.PrixArticleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class ArticleService  extends GenericWeb  {
	
	
	private ArticleDAO daoArticle;
	
	@Autowired
	public void setDaoArticle(ArticleDAO daoArticle) {
		this.daoArticle = daoArticle;
	}
	@Transactional(readOnly=true)
	public List<ArticleBean> dofetchDatafromServer(ArticleBean searchBean) throws Exception{
		return daoArticle.doFindListArticle(searchBean);
	}
	
	@Transactional(readOnly=true)
	public List<LieuxArticleBean> doFindLieuxArcticle(LieuxArticleBean searchBean) throws Exception{
		return daoArticle.doFindLieuxArcticle(searchBean);
		
	}
	
	@Transactional(readOnly=true)
	public List<ClientArticleBean> doFindProduitClient(ClientArticleBean searchBean) throws Exception{
		return daoArticle.doFindProduitClient(searchBean);
		
	}
	
	@Transactional(readOnly=true)
	public List<PrixArticleBean> doFindDate_MAX_PrixArcticle(ArticleBean searchBean) throws Exception{
		return daoArticle.doFindDate_MAX_PrixArcticle(searchBean);
		
	}
	
	
	
	@Transactional
	public boolean doCreateRowData(ArticleBean insertBean)throws Exception{
		 return daoArticle.doSaveArticle(insertBean);
	}
	
	@Transactional
	public boolean doCreateRowDataFromFile(ArticleBean insertBean)throws Exception{
		List<ArticleBean> listDesArticleLoader   =  (List) getObjectValueModel("listDesArticleLoader" );
		boolean result = false;
		for (ArticleBean articleBean : listDesArticleLoader) {
			daoArticle.doSaveArticleFromFile(insertBean,articleBean);
			result = true;
		}
		 return result;
	}
	
	@Transactional
	public boolean doAffectlieuxarticle(List lisf)  throws Exception  {
		 return daoArticle.doAffectLieux(lisf); 
	}
	
	
	@Transactional
	public boolean doAffect_client_article(ClientArticleBean detailBean ,List lisf)  throws Exception  {
			boolean  result=daoArticle.doAffect_client_article(  detailBean ,lisf);
		       
		 return result;
	}
	
	
	
	@Transactional
	public Boolean doUpdateRowData(ArticleBean updateBean) throws Exception{
		
		  boolean result = false;
		try {
			result=daoArticle.doUpdateArticle(updateBean);
		} catch (Exception e) {
			throw e;
		}
		 return result;
	}
	@Transactional
	public Boolean doDeleteRowData(ArticleBean deleteBean) throws Exception{
		 boolean result = false;
		 try{
		    result=daoArticle.doDeleteArticle(deleteBean);
			
		} catch (Exception e) {
			throw e;
		}
	 return result;
	}
}
