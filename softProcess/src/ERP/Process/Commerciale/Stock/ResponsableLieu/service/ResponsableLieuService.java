package ERP.Process.Commerciale.Stock.ResponsableLieu.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.ResponsableLieu.dao.ResponsableLieuDAO;
import ERP.Process.Commerciale.Stock.ResponsableLieu.model.ResponsableLieuBean;
import ERP.Process.Commerciale.Stock.ResponsableLieu.template.ResponsableLieuTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class ResponsableLieuService  extends GenericWeb  {
	private ResponsableLieuDAO daoResponsableLieu;
	@Autowired
	public void setDaoResponsableLieu(ResponsableLieuDAO daoResponsableLieu) {
		this.daoResponsableLieu = daoResponsableLieu;
	}
	@Transactional(readOnly=true)
	public List<ResponsableLieuBean> doFetchDatafromServer(ResponsableLieuBean beanSearch) throws Exception {
		return daoResponsableLieu.doFindListResponsableLieu(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(ResponsableLieuBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoResponsableLieu.doSaveResponsableLieu(insertBean)){
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
	public Boolean  doUpdateRowData(ResponsableLieuBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoResponsableLieu.doUpdateResponsableLieu(updateBean)){
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
	public Boolean doDeleteRowData(ResponsableLieuBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoResponsableLieu.doDeleteResponsableLieu(deleteBean)){
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
