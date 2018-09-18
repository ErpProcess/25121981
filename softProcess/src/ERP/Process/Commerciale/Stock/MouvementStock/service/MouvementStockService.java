package ERP.Process.Commerciale.Stock.MouvementStock.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.MouvementStock.dao.MouvementStockDAO;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.MouvementStock.template.MouvementStockTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class MouvementStockService  extends GenericWeb  {
	private MouvementStockDAO daoMouvementStock;
	@Autowired
	public void setDaoMouvementStock(MouvementStockDAO daoMouvementStock) {
		this.daoMouvementStock = daoMouvementStock;
	}
	@Transactional(readOnly=true)
	public List<MouvementSerieBean> doFetchDatafromServer(MouvementSerieBean beanSearch) throws Exception {
		return daoMouvementStock.doFindListMouvementStock(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(MouvementSerieBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoMouvementStock.doSaveMouvementStock(insertBean)){
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
	public Boolean  doUpdateRowData(MouvementSerieBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoMouvementStock.doUpdateMouvementStock(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(MouvementStockTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(MouvementStockTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(MouvementSerieBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoMouvementStock.doDeleteMouvementStock(deleteBean)){
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
