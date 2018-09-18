package ERP.Process.Commerciale.GrpTarifPrimitiv.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.GrpTarifPrimitiv.dao.GrpTarifPrimitivDAO;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.Process.Commerciale.GrpTarifPrimitiv.template.GrpTarifPrimitivTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class GrpTarifPrimitivService  extends GenericWeb  {
	private GrpTarifPrimitivDAO daoGrpTarifPrimitiv;
	@Autowired
	public void setDaoGrpTarifPrimitiv(GrpTarifPrimitivDAO daoGrpTarifPrimitiv) {
		this.daoGrpTarifPrimitiv = daoGrpTarifPrimitiv;
	}
	@Transactional(readOnly=true)
	public List<GrpTarifPrimitivBean> doFetchDatafromServer(GrpTarifPrimitivBean beanSearch) throws Exception {
		return daoGrpTarifPrimitiv.doFindListGrpTarifPrimitiv(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(GrpTarifPrimitivBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoGrpTarifPrimitiv.doSaveGrpTarifPrimitiv(insertBean)){
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
	public Boolean  doUpdateRowData(GrpTarifPrimitivBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoGrpTarifPrimitiv.doUpdateGrpTarifPrimitiv(updateBean)){
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
	public Boolean doDeleteRowData(GrpTarifPrimitivBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoGrpTarifPrimitiv.doDeleteGrpTarifPrimitiv(deleteBean)){
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
