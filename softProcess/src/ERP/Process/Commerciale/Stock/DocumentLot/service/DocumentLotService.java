package ERP.Process.Commerciale.Stock.DocumentLot.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.DocumentLot.dao.DocumentLotDAO;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
 
import ERP.Process.Commerciale.Stock.DocumentLot.template.DocumentLotTemplate;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class DocumentLotService  extends GenericWeb  {
	private DocumentLotDAO daoDocumentLot;
	@Autowired
	public void setDaoDocumentLot(DocumentLotDAO daoDocumentLot) {
		this.daoDocumentLot = daoDocumentLot;
	}
	
	
	@Transactional(readOnly=true)
	public List<SerieArticletBean> doFetchDatafromServer(SerieArticletBean beanSearch) throws Exception {
		return daoDocumentLot.doFindListDocumentLot(beanSearch);
	}
	
	
	
	@Transactional(readOnly=true)
	public List<MouvementSerieBean> doFetch_detailleLotfromServer(SerieArticletBean beanSearch) throws Exception {
		return daoDocumentLot.doFindList_detailleDocumentLot(beanSearch);
	}
	
	
	
	
	@Transactional
	public Boolean doCreateRowData(SerieArticletBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoDocumentLot.doSaveDocumentLot(insertBean)){
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
	public Boolean doAddChoixRowData(SerieArticletBean insertBean,List list_des_lots_for_select) throws Exception {
		 boolean result = false;
		 try {
		       if(daoDocumentLot.doSaveDocumentLot_choix(insertBean,list_des_lots_for_select)){
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
	public Boolean doSuppChoixRowData(SerieArticletBean insertBean,List list_des_lots_for_select) throws Exception {
		 boolean result = false;
		 try {
		       if(daoDocumentLot.doSupp_choixLot(insertBean,list_des_lots_for_select)){
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
	public Boolean  doUpdateRowData(SerieArticletBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoDocumentLot.doUpdateDocumentLot(updateBean)){
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
	public Boolean doDeleteRowData(SerieArticletBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoDocumentLot.doDeleteDocumentLot(deleteBean)){
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
