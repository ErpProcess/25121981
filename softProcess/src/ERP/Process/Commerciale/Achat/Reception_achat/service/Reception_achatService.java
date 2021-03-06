package ERP.Process.Commerciale.Achat.Reception_achat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Achat.Reception_achat.dao.Reception_achatDAO;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class Reception_achatService  extends GenericWeb  {
	private Reception_achatDAO daoReception_achat;
	@Autowired
	public void setDaoReception_achat(Reception_achatDAO daoReception_achat) {
		this.daoReception_achat = daoReception_achat;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	@Transactional(readOnly=true)
	public List<Reception_achatBean> doFetchDatafromServer(Reception_achatBean beanSearch) throws Exception {
		Double totgrid= new Double(0);
		List <Reception_achatBean >listDataSrv =daoReception_achat.doFindListReception_achat(beanSearch);
		for (int i = 0; i < listDataSrv.size(); i++) {
			Reception_achatBean  reBean	=(Reception_achatBean) listDataSrv.get(i);
			if(reBean.getDem_achat()!=null)
				reBean.setDemande_id(reBean.getDem_achat().getDem_achat_id());
			totgrid=ProcessNumber.addition(totgrid,  ProcessFormatNbr.FormatDouble_Troischiffre(reBean.getAchat_mnt_total()) );
		}
		setObjectValueModel("totGrid", totgrid);
		return listDataSrv;
	}
	
	
	@Transactional(readOnly=true)
	public List<Det_reception_achatBean> doFetchDetailAchat(Reception_achatBean beanSearch) throws Exception {
		List <Det_reception_achatBean >listDataSrv =daoReception_achat.doFindList_detail_Reception_achat(beanSearch);
		return listDataSrv;
	}
	
	
	
	
	@Transactional
	public Boolean doCreateRowData(Reception_achatBean insertBean) throws Exception {
		 boolean result = false;
		 try {
			     daoNumSequentiel.getNumSeqSimple(insertBean,"achat_id");
		       if(daoReception_achat.doSaveReception_achat(insertBean)){
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
	public void  doUpdateRowData(Reception_achatBean updateBean)  throws Exception {
	   daoReception_achat.doUpdateReception_achat(updateBean) ; 
	}
	
	@Transactional
	public void  doCorrigerRowData(Reception_achatBean updateBean)  throws Exception {
	   daoReception_achat.doCorrigerAchatArticle(updateBean);
	   daoReception_achat.doUpdateReception_achat(updateBean) ; 
	   daoReception_achat.doValiderReception_article(updateBean) ; 
	}
	
	@Transactional
	public Boolean  doStockRowData(Reception_achatBean updateBean)  throws Exception {
	 return daoReception_achat.doValiderReception_article(updateBean) ; 
	}
	
	@Transactional
	public void  doAnnulerRowData(Reception_achatBean updateBean)  throws Exception {
		  daoReception_achat.doExcuterTransactionForAchat(updateBean) ;
	}
	
	@Transactional
	public void  doTransfererRowData(Reception_achatBean updateBean)  throws Exception {
		daoReception_achat.doExcuterTransactionForAchat(updateBean);
	}
	
	
	@Transactional
	public void doDeleteRowData(Reception_achatBean deleteBean) throws Exception {
		daoReception_achat.doCorrigerAchatArticle(deleteBean);
		daoReception_achat.doDeleteReception_achat(deleteBean);
	}
}
