package ERP.Process.Commerciale.Caracteristique.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Caracteristique.dao.CaracteristiqueDAO;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.Process.Commerciale.Caracteristique.template.CaracteristiqueTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class CaracteristiqueService  extends GenericWeb  {
	private CaracteristiqueDAO daoCaracteristique;
	@Autowired
	public void setDaoCaracteristique(CaracteristiqueDAO daoCaracteristique) {
		this.daoCaracteristique = daoCaracteristique;
	}
	@Transactional(readOnly=true)
	public List<CaracteristiqueBean> dofetchDatafromServer(CaracteristiqueBean beanSearch) throws Exception {
		return daoCaracteristique.doFindListCaracteristique(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<CaracteristiqueBean> dofetch_detail_carac(CaracteristiqueBean beanSearch) throws Exception {
		return daoCaracteristique.doFindListCaracteristique(beanSearch);
	}
	
	
	@Transactional
	public Boolean doSaveCaracteristique(CaracteristiqueBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoCaracteristique.doSaveCaracteristique(insertBean)){
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
	public Boolean  doUpdateCaracteristique(CaracteristiqueBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoCaracteristique.doUpdateCaracteristique(updateBean)){
			 
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
	public Boolean doDeleteCaracteristique(CaracteristiqueBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoCaracteristique.doDeleteCaracteristique(deleteBean)){
			   
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
