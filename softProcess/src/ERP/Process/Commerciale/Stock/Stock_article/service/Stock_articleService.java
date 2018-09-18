package ERP.Process.Commerciale.Stock.Stock_article.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.Stock_article.dao.Stock_articleDAO;
import ERP.Process.Commerciale.Stock.Stock_article.model.IncidentStock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.template.Stock_articleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Stock_articleService  extends GenericWeb  {
	
	
	private Stock_articleDAO daoStock_article;
	@Autowired
	public void setDaoStock_article(Stock_articleDAO daoStock_article) {
		this.daoStock_article = daoStock_article;
	}
	
	@Transactional(readOnly=true)
	public List<Stock_articleBean> doFetchDatafromServer(Stock_articleBean beanSearch) throws Exception {
		return daoStock_article.doFindListStock_article(beanSearch);
	}
	
	
	@Transactional(readOnly=true)
	public List<IncidentStock_articleBean> doFetchDataIncident(Stock_articleBean beanSearch) throws Exception {
		return daoStock_article.doFindListStock_Incident(beanSearch);
	}
	
	@Transactional
	public Boolean doCreateRowData(Stock_articleBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoStock_article.doSaveStock_article(insertBean)){
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
	public Boolean  doUpdateRowData(Stock_articleBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoStock_article.doUpdateStock_article(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(Stock_articleTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(Stock_articleTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(Stock_articleBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoStock_article.doDeleteStock_article(deleteBean)){
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
