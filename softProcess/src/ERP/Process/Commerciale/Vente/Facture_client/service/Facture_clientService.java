package ERP.Process.Commerciale.Vente.Facture_client.service;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Vente.Facture_avoir_client.dao.Facture_avoir_clientDAO;
import ERP.Process.Commerciale.Vente.Facture_avoir_client.model.Facture_avoir_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.dao.Facture_clientDAO;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.MvtVente_articleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
@Service
public class Facture_clientService  extends GenericWeb  {
	private Facture_clientDAO daoFacture;
	@Autowired
	public void setDaoFacture(Facture_clientDAO daoFacture) {
		this.daoFacture = daoFacture;
	}
	
	
	private Facture_avoir_clientDAO daoFacture_avoir_client;
	@Autowired
	public void setDaoFacture_avoir_client(Facture_avoir_clientDAO daoFacture_avoir_client) {
		this.daoFacture_avoir_client = daoFacture_avoir_client;
	}
	
	
	
	@Transactional(readOnly=true)
	public List<Facture_clientBean> doFetchDatafromServer(Facture_clientBean beanSearch) throws Exception {
		
	
		List listFacture=daoFacture.doFindListFacture(beanSearch);
	
		if(listFacture!=null  &&  listFacture.size()>0){
			String list="";
			for (int i = 0; i < listFacture.size(); i++) {
				Facture_clientBean bean =(Facture_clientBean) listFacture.get(i);
				if(bean.getTypefact().getType_fact_id().intValue()==2){
					list=list+"'"+bean.getFact_clt_id()+"',";
				}
			}
			if(list.length()>0){
				 
				Facture_clientBean  beanfact= new Facture_clientBean();
				beanfact.setSelect_many_facture("   AND   bean.avoir_id in ("+list.substring(0, list.length()-1)+") ");
				List listFactu=daoFacture_avoir_client.doFindListFacture_avoir_client(beanfact);
				HashMap  mapAvoir=  ProcessUtil.getHashMap_code_bean(listFactu, "avoir_id");
				
				for (int i = 0; i < listFacture.size(); i++) {
					Facture_clientBean bean =(Facture_clientBean) listFacture.get(i);
					if(bean.getTypefact().getType_fact_id().intValue()==2){
						Facture_avoir_clientBean sssss=(Facture_avoir_clientBean) (mapAvoir.get(bean.getFact_clt_id())==null?new Facture_avoir_clientBean():mapAvoir.get(bean.getFact_clt_id()));
						bean.setAvoir_and_reference(bean.getTypefact().getType_fact_lib()+" "+sssss.getFactclient().getFact_clt_id()) ;
					}else{
						bean.setAvoir_and_reference(bean.getTypefact().getType_fact_lib()) ;
					}
					
				}
			}
		
		}
	
		
		return  listFacture;
		
		
	}
	
	@Transactional(readOnly=true)
	public List<Det_Fact_ClientBean> doFetchDetaillefromServer(Facture_clientBean beanSearch) throws Exception {
		return daoFacture.doFindList_detaille_Facture(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<MvtVente_articleBean> doFetchDetailleMvt_fact_vente(Facture_clientBean beanSearch) throws Exception {
		return daoFacture.doFindList_detaille_mvt_fca_vente(beanSearch);
	}
	
	
	
	@Transactional
	public Boolean doCreateRowData(Facture_clientBean insertBean) throws Exception {
		 boolean result = false;
		 try {
			 
			  
		       if(daoFacture.doSaveFacture(insertBean)){
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
	public Boolean  doUpdateRowData(Facture_clientBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoFacture.doExcuterTransaction(updateBean)){
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
	public Boolean doDeleteRowData(Facture_clientBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoFacture.doDeleteFacture(deleteBean)){
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
