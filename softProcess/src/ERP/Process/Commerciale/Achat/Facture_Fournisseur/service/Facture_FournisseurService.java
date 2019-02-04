package ERP.Process.Commerciale.Achat.Facture_Fournisseur.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.dao.Facture_FournisseurDAO;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Det_Fact_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Detail_mvt_achat_factureBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.template.Facture_FournisseurTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class Facture_FournisseurService  extends GenericWeb  {
	
	
	private Facture_FournisseurDAO daoFacture_Fournisseur;
	@Autowired
	public void setDaoFacture_Fournisseur(Facture_FournisseurDAO daoFacture_Fournisseur) {
		this.daoFacture_Fournisseur = daoFacture_Fournisseur;
	}
	
	
	@Transactional(readOnly=true)
	public List<Facture_FournisseurBean> doFetchDatafromServer(Facture_FournisseurBean beanSearch) throws Exception {
		return daoFacture_Fournisseur.doFindListFacture_Fournisseur(beanSearch);
	}
	
	
	@Transactional(readOnly=true)
	public List<Det_Fact_FournisseurBean> doFetchDetailfromServer(Facture_FournisseurBean beanSearch) throws Exception {
		return daoFacture_Fournisseur.doFetchDetailfromServer(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<Detail_mvt_achat_factureBean> doFindList_detaille_mvt_achat(String  lesMvt_achat) throws Exception {
		return daoFacture_Fournisseur.doFindList_detaille_mvt_achat(   lesMvt_achat);
	}
	
	
	
	@Transactional
	public Boolean doCreateRowDatafileFacturefrs(FileFactureFournisseur insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoFacture_Fournisseur.doSaveFileFacture_Fournisseur(insertBean)){
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
	public Boolean doCreateRowData(Facture_FournisseurBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoFacture_Fournisseur.doSaveFacture_Fournisseur(insertBean)){
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
	public Boolean  doUpdateRowData(Facture_FournisseurBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoFacture_Fournisseur.doUpdateFacture_Fournisseur(updateBean)){
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
	public Boolean doDeleteRowData(Facture_FournisseurBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoFacture_Fournisseur.doDeleteFacture_Fournisseur(deleteBean)){
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
