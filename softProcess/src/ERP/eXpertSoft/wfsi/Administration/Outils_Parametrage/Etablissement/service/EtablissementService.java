package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.service;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.dao.EtablissementDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.template.EtablissementTemplate;

 
 
@Service
public class EtablissementService  extends GenericWeb  {
	 
	private EtablissementDAO daoEtablissement;
	
	private NumSequentielDAO daoNumSequentiel;
	 
	
	
	@Autowired
	public void setDaoEtablissement(EtablissementDAO daoEtablissement) {
		this.daoEtablissement = daoEtablissement;
	}
	
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	@Transactional(readOnly=true)
	public List<EtablissementBean> dofetchDatafromServer(EtablissementBean beanSearch){
		return daoEtablissement.doFindListEtablissement(beanSearch);
	}
	
	 
	@Transactional
	public Boolean doCreateRowData(EtablissementBean insertBean){
		 try {
			    daoNumSequentiel.getNumSeqSimple(insertBean,"pk_etab.etab_id");
		       if(daoEtablissement.saveEtablissement(insertBean)){
		       return true;
		       }else{
		    	return false;  
		       }
		       
		 } catch (Exception e) { 
			 return false;
		}
	}
	
	@Transactional
	public Boolean doUpdateRowData(EtablissementBean updateBean){
		if(daoEtablissement.updateEtablissement(updateBean)){
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(EtablissementBean deleteBean){
		if(daoEtablissement.dodeleteEtablissement(deleteBean)){
			return true;
		}else{
			return false;
		}
			
	}
 
	 
	 
	
 
	

	 
	 
	
	
	
}
