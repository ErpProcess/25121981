package ERP.Process.Commerciale.Stock.NatureLieu.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.NatureLieu.dao.NatureLieuDAO;
import ERP.Process.Commerciale.Stock.NatureLieu.model.NatureLieuBean;
import ERP.Process.Commerciale.Stock.NatureLieu.template.NatureLieuTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class NatureLieuService  extends GenericWeb  {
	private NatureLieuDAO daoNatureLieu;
	@Autowired
	public void setDaoNatureLieu(NatureLieuDAO daoNatureLieu) {
		this.daoNatureLieu = daoNatureLieu;
	}
	@Transactional(readOnly=true)
	public List<NatureLieuBean> doFetchDatafromServer(NatureLieuBean beanSearch) throws Exception {
		return daoNatureLieu.doFindListNatureLieu(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(NatureLieuBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoNatureLieu.doSaveNatureLieu(insertBean)){
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
	public Boolean  doUpdateRowData(NatureLieuBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoNatureLieu.doUpdateNatureLieu(updateBean)){
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
	public Boolean doDeleteRowData(NatureLieuBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoNatureLieu.doDeleteNatureLieu(deleteBean)){
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
