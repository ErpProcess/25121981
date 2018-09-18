package ERP.Process.Commerciale.Code_barre.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Code_barre.dao.Code_barreDAO;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.template.Code_barreTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Code_barreService  extends GenericWeb  {
	
	
	private Code_barreDAO daoCode_barre;
	
	
	@Autowired
	public void setDaoCode_barre(Code_barreDAO daoCode_barre) {
		this.daoCode_barre = daoCode_barre;
	}
	@Transactional(readOnly=true)
	public List<Code_barreBean> doFetchDatafromServerNew(Code_barreBean beanSearch) throws Exception {
		 List<Code_barreBean> list_DatafromServer = daoCode_barre.doFindListCode_barreVersion(beanSearch);
		return  list_DatafromServer;
	}
	
	
	@Transactional(readOnly=true)
	public List<Code_barreBean> doFetchDatafromServer_article(ArticleBean searchBean) throws Exception {
		
		 List<Code_barreBean> list_DatafromServer = daoCode_barre.doFindArticleCode_barreVersion(searchBean);
			 
		return  list_DatafromServer;
	}
	
	
	@Transactional(readOnly=true)
	public List<Code_barreBean> doFetchData_det_cod_bar_fromServer(Code_barreBean beanSearch) throws Exception {
		return daoCode_barre.doFindList_detail_Code_barre(beanSearch);
	}
	
	
	
	@Transactional
	public Boolean doCreateRowData(Code_barreBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoCode_barre.doSaveCode_barre(insertBean)){
		       result = true;
		       }else{
		    	result = false;  
		       }
		 } catch (Exception e) { 
			 result = false;
			 throw e;
		}
		return result; 
	}
	@Transactional
	public Boolean  doUpdateRowData(Code_barreBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoCode_barre.doUpdateCode_barre(updateBean)){
		    result = true;
		       }else{
		    	result = false;  
		     }
		 } catch (Exception e) { 
			 result = false;
			 throw e;
		}
	 return result; 
	}
	@Transactional
	public Boolean doDeleteRowData(Code_barreBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoCode_barre.doDeleteCode_barre(deleteBean)){
			    String indexo = (String) getObjectValueModel(INDEX_ROW);
			    List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			    list.remove(Integer.parseInt(indexo));
			    setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), list);
		        result = true;
		      }else{
		    	result = false;  
		     }
		 } catch (Exception e) { 
			 result = false;
			 throw e;
		}
   return result; 
	}
}
