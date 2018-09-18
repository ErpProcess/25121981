package ERP.Process.Commerciale.TarificationPrtvArticle.service;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.TarificationPrtvArticle.dao.TarificationPrtvArticleDAO;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class TarificationPrtvArticleService  extends GenericWeb  {
	private TarificationPrtvArticleDAO daoTarificationPrtvArticle;
	@Autowired
	public void setDaoTarificationPrtvArticle(TarificationPrtvArticleDAO daoTarificationPrtvArticle) {
		this.daoTarificationPrtvArticle = daoTarificationPrtvArticle;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	@Transactional(readOnly=true)
	public List<TarificationPrtvArticleBean> doFetchDatafromServer(TarificationPrtvArticleBean beanSearch) throws Exception {
		return daoTarificationPrtvArticle.doFindListTarificationPrtvArticle(beanSearch);
	}
	
	 
	public HashMap doTraiTerListTarif_vente(List    list_TarificationPrtvArticle_vente ) throws Exception {
		
		
		HashMap mapTRF= new HashMap();
		try {
			
			for (int i = 0; i < list_TarificationPrtvArticle_vente.size(); i++) {
				TarificationPrtvArticleBean beantrie=(TarificationPrtvArticleBean) list_TarificationPrtvArticle_vente.get(i);
				String  key="";/*beantrie.getPk().getFkCode_barre().getPk().getCode_barre()+"µ";
				        key=key+beantrie.getPk().getTyp_trfBean().getType_trf_id();*/
				        TarificationPrtvArticleBean  dssdf=(TarificationPrtvArticleBean) mapTRF.get(key);
				        if(dssdf==null){
				        	mapTRF.put(key, beantrie);
				        }
			}
			
			
		} catch (Exception e) {
			throw e;
		}
		return mapTRF;
	}
	
	@Transactional
	public Boolean doCreateRowData(TarificationPrtvArticleBean insertBean) throws Exception {
		 boolean result = false;
		 try {
			     daoNumSequentiel.getNumSeqSimple(insertBean,"tarif_prim_id");
		       if(daoTarificationPrtvArticle.doSaveTarificationPrtvArticle(insertBean)){
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
	public Boolean  doUpdateRowData(TarificationPrtvArticleBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTarificationPrtvArticle.doUpdateTarificationPrtvArticle(updateBean)){
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
	public Boolean doDeleteRowData(TarificationPrtvArticleBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTarificationPrtvArticle.doDeleteTarificationPrtvArticle(deleteBean)){
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
