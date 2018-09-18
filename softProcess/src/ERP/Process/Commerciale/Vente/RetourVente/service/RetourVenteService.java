package ERP.Process.Commerciale.Vente.RetourVente.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Stock.MouvementStock.model.Incident_mvt_serieBean;
import ERP.Process.Commerciale.Vente.RetourVente.dao.RetourVenteDAO;
import ERP.Process.Commerciale.Vente.RetourVente.model.DetRetourVenteBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.RetourVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class RetourVenteService  extends GenericWeb  {
	private RetourVenteDAO daoRetourVente;
	@Autowired
	public void setDaoRetourVente(RetourVenteDAO daoRetourVente) {
		this.daoRetourVente = daoRetourVente;
	}
	@Transactional(readOnly=true)
	public List<RetourVenteBean> doFetchDatafromServer(RetourVenteBean beanSearch) throws Exception {
		return daoRetourVente.doFindListRetourVente(beanSearch);
	}
	
	
	@Transactional(readOnly=true)
	public List<DetRetourVenteBean> doFetchDetaillefromServer(RetourVenteBean beanSearch) throws Exception {
		return daoRetourVente.doFindList_detaille_RetourVente(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<DetRetourVenteBean> doFetchDetaillefromServer(DetRetourVenteBean beanSearch) throws Exception {
		return daoRetourVente.doFindList_detaille_RetourVente(beanSearch);
	}
	
	 
	
	@Transactional(readOnly=true)
	public List<Incident_mvt_serieBean> doFindList_detaille_Incident_serie(String List_re_vente_id) throws Exception {
		return daoRetourVente.doFindList_detaille_Incident_serie(List_re_vente_id);
	}
	
	
	
	
	
	@Transactional
	public Boolean doCreateRowData(RetourVenteBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoRetourVente.doSaveRetourVente(insertBean)){
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
	public Boolean  doUpdateRowData(RetourVenteBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoRetourVente.doUpdateRetourVente(updateBean)){
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
	public Boolean  doConfirmStockRowData(RetourVenteBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoRetourVente.doConfirmerRetourVente_article(updateBean)){
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
	public Boolean doDeleteRowData(RetourVenteBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoRetourVente.doDeleteRetourVente(deleteBean)){
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
