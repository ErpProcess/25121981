package ERP.Process.Commerciale.Stock.Inventaire.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Stock.Inventaire.dao.InventaireDAO;
import ERP.Process.Commerciale.Stock.Inventaire.model.DetInventaireBean;
import ERP.Process.Commerciale.Stock.Inventaire.model.InventaireBean;
import ERP.Process.Commerciale.Stock.Inventaire.template.InventaireTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class InventaireService  extends GenericWeb  {
	private InventaireDAO daoInventaire;
	@Autowired
	public void setDaoInventaire(InventaireDAO daoInventaire) {
		this.daoInventaire = daoInventaire;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	@Transactional(readOnly=true)
	public List<InventaireBean> doFetchDatafromServer(InventaireBean beanSearch) throws Exception {
		return  daoInventaire.doFindListInventaire(beanSearch);
	}
	
	
	@Transactional(readOnly=true)
	public List <DetInventaireBean> doFindList_det_Inventaire(InventaireBean beanSearch) throws Exception {
		return  daoInventaire.doFindList_det_Inventaire(beanSearch);
	}
	
	
	
	
	@Transactional
	public Boolean doCreateRowData(InventaireBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       daoNumSequentiel.getNumSeqSimple(insertBean,"pk.inv_id");
		       if(daoInventaire.doSaveInventaire(insertBean)){
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
	public Boolean  doValiderRowData(InventaireBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoInventaire.doValiderTransactionInventaire(updateBean)){
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
	public Boolean  doUpdateRowData(InventaireBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoInventaire.doUpdateInventaire(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(InventaireTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(InventaireTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(InventaireBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoInventaire.doDeleteInventaire(deleteBean)){
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
