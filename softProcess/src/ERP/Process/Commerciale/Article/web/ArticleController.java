package ERP.Process.Commerciale.Article.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.model.ClientArticleBean;
import ERP.Process.Commerciale.Article.model.LieuxArticleBean;
import ERP.Process.Commerciale.Article.template.ArticleTemplate;

@Controller
public class ArticleController extends ArticleActionManager {
 
	private static final long serialVersionUID = 1895928513364445575L;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	   dateFormat.setLenient(false);
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@RequestMapping(value = ROOT)
	public ModelAndView doControlAction(ArticleBean detailBean,LieuxArticleBean lieux,ClientArticleBean clBean,HttpServletRequest request, HttpServletResponse response)throws Exception {
		ModelAndView model = doInitGenericAction(request, response,new ArticleTemplate());
		
		
		if (i$_ACT_INIT_SERVLET )      return doInitServletAction();
		if (i$_ACT_UPLOADER)           return      uploadFile();
		if (i$_ACT_AJAX_FETCH)         return doFetchData(detailBean); 
		if (i$_ACT_SELECT_ROW)         return doSelectRowArticle(); 
		if (i$_ACT_LOAD_ETAB )         return doFetchDataEtablissement(detailBean); 
		
		if (i$_ACT_LOAD_ETAB_P_CLT )   return doFetchDataEtablissement_Produit_client(clBean);
		if (i$_ACT_LOAD_ARTICLE_CLT )  return doFetchData_articleFrom_Etab_Produit_client(clBean);
		if (i$_ACT_LOAD_REFE_CLT )     return doFetchData_RefFrom_article_client(clBean);
		
		
		 
		if (i$_ACT_ADD)                return doAddData  (detailBean);
		if (i$_ACT_ADD_BY_FILE)        return doAddDataByFile  (detailBean);
		 
		if (i$_ACT_UPDATE)             return doUpdateData(detailBean);
		if (i$_ACT_UPDATE_SITUATION)   return doUpdateDataSituation(detailBean);
		if (i$_ACT_DELETE)             return doDeleteData(detailBean);
		 
		/*************************************************************lieu de produit**************************************************************/
		if (i$_ACT_GET_LIEUX )         return doFetchlieux_produit(lieux);
		if (i$_ACT_LIEUX_ARTICLE)      return doAddAffectLieux(detailBean);
		if (i$_ACT_CANCEL_AFFECT)      return doAddAffectLieux(null);
		
		/***********************************************************produit client ********************************************************************/
		
		if (i$_ACT_GET_ARTICLE_CLIENT )       return doFetchProduit_client(clBean);
		if (i$_ACT_CLT_ARTICLE)               return doAddAffectProduit_client(clBean);
		if (i$_ACT_CANCEL_AFFECT_CLIENT)      return doAddAffectProduit_client(null);
		
		/***************************************************************************************************************************************/
		
		
		
		return model;
	}
}
