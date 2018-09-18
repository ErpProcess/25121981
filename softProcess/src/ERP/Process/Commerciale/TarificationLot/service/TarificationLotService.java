package ERP.Process.Commerciale.TarificationLot.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.TarificationLot.dao.TarificationLotDAO;
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;


@Service
public class TarificationLotService  extends GenericWeb  {
	private TarificationLotDAO daoTarificationLot;
	@Autowired
	public void setDaoTarificationLot(TarificationLotDAO daoTarificationLot) {
		this.daoTarificationLot = daoTarificationLot;
	}
	@Transactional(readOnly=true)
	public List<TarificationLotBean> doFetchDatafromServer(TarificationLotBean beanSearch) throws Exception {
		return daoTarificationLot.doFindListTarificationLot(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(TarificationLotBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTarificationLot.doSaveTarificationLot(insertBean)){
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
	public Boolean  doUpdateRowData(TarificationLotBean updateBean)  throws Exception {
	 return daoTarificationLot.doUpdateTarificationLot(updateBean); 
	}
	
	@Transactional
	public Boolean  doconfirmRowData(TarificationLotBean updateBean)  throws Exception {
	 return daoTarificationLot.doConfirmTarificationLot(updateBean); 
	}
	@Transactional
	public Boolean doDeleteRowData(TarificationLotBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTarificationLot.doDeleteTarificationLot(deleteBean)){
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
