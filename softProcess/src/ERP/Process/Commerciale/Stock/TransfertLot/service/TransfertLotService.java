package ERP.Process.Commerciale.Stock.TransfertLot.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.TransfertLot.dao.TransfertLotDAO;
import ERP.Process.Commerciale.Stock.TransfertLot.model.TransfertLotBean;
import ERP.Process.Commerciale.Stock.TransfertLot.template.TransfertLotTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class TransfertLotService  extends GenericWeb  {
	private TransfertLotDAO daoTransfertLot;
	@Autowired
	public void setDaoTransfertLot(TransfertLotDAO daoTransfertLot) {
		this.daoTransfertLot = daoTransfertLot;
	}
	@Transactional(readOnly=true)
	public List<TransfertLotBean> doFetchDatafromServer(TransfertLotBean beanSearch) throws Exception {
		return daoTransfertLot.doFindListTransfertLot(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(TransfertLotBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTransfertLot.doSaveTransfertLot(insertBean)){
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
	public Boolean  doUpdateRowData(TransfertLotBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTransfertLot.doUpdateTransfertLot(updateBean)){
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
	public Boolean doDeleteRowData(TransfertLotBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTransfertLot.doDeleteTransfertLot(deleteBean)){
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
