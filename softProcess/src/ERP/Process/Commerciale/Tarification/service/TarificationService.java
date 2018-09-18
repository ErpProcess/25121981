package ERP.Process.Commerciale.Tarification.service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Tarification.dao.TarificationDAO;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.Tarification.template.TarificationTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;


@Service
public class TarificationService  extends GenericWeb  {
	
	private TarificationDAO daoTarification;
	@Autowired
	public void setDaoTarification(TarificationDAO daoTarification) {
		this.daoTarification = daoTarification;
	}
	@Transactional(readOnly=true)
	public List<TarificationBean> doFetchDatafromServer(TarificationBean beanSearch) throws Exception {
		return daoTarification.doFindListTarification(beanSearch);
	}
	
	public String doTesterTarificationInsert(TarificationBean beanSave) throws Exception {
		
		String message="";
		boolean inter=false;
		beanSave.setDate_trf2(beanSave.getDate_trf());
		List list_tarif = daoTarification.doFindListTarification(beanSave);
		 
		
		if(list_tarif!=null &&  list_tarif.size()>0    ){
			/*****************************************************Par lot**************************************************************/
			TarificationBean tarif=(TarificationBean) list_tarif.get(0);
			
			if( !StringUtils.isEmpty(beanSave.getNum_serie()) &&  (beanSave.getDepot()!=null &&   beanSave.getDepot().getDepot_id()!=null)  ){
				inter=true;
				message="  Il ya une duplication de Tarification de lot  pour l'article : "+tarif.getFkCode_barre().getPk().getCode_barre()+" / "+tarif.getFkCode_barre().getDesignation_libelle();
				message+=" Date :"+ProcessDate.getStringFormatDate(tarif.getDate_trf())+"<br>";
				message+=" Groupe :"+tarif.getGroupe().getType_trf_lib()+"<br>";
				message+=" Lot :"+tarif.getNum_serie()+"<br>";
				message+=" Lieu :"+tarif.getDepot().getDepot_libelle()+"<br>";
				
			}
			
			/*****************************************************Par Lieux**************************************************************/
            if( StringUtils.isEmpty(beanSave.getNum_serie()) &&  (beanSave.getDepot()!=null &&   beanSave.getDepot().getDepot_id()!=null)  ){
            	inter=true;
				message=" Il ya une duplication de Tarification de lieu  pour l'article : "+tarif.getFkCode_barre().getPk().getCode_barre()+" / "+tarif.getFkCode_barre().getDesignation_libelle();
				message+=" Date :"+ProcessDate.getStringFormatDate(tarif.getDate_trf())+"<br>";
				message+=" Groupe :"+tarif.getGroupe().getType_trf_lib()+"<br>";
				message+=" Lieu :"+tarif.getDepot().getDepot_libelle()+"<br>";
			}
            
            /*****************************************************Par Produit**************************************************************/
            if( StringUtils.isEmpty(beanSave.getNum_serie()) &&  (beanSave.getDepot()==null ||  beanSave.getDepot().getDepot_id()==null)  ){
            	inter=true;
				message=" Il ya une duplication de Tarification pour l'article : "+tarif.getFkCode_barre().getPk().getCode_barre()+" / "+tarif.getFkCode_barre().getDesignation_libelle();
				message+=" Date :"+ProcessDate.getStringFormatDate(tarif.getDate_trf())+"<br>";
				message+=" Groupe :"+tarif.getGroupe().getType_trf_lib()+"<br>";
			}
            
            if(!inter){
            	message+="  cette tarification n'est pas vérifier ";
            }
            
		}else{
			 message="";
		}
		return message;
	}
	
	
	@Transactional(readOnly=true)
	public String doFetchTarif(String beanSearch) throws Exception {
		return daoTarification.doFetchTarif(beanSearch);
	}
	
	 
	public HashMap doTraiTerListTarif_vente(List    list_Tarification_vente ) throws Exception {
		
		HashMap mapTRF= new HashMap();
		
		try {
			
			for (int i = 0; i < list_Tarification_vente.size(); i++) {
				TarificationBean beantrie=(TarificationBean) list_Tarification_vente.get(i);
				 String  key=beantrie.getFkCode_barre().getPk().getCode_barre()+"µ";
				         key=key+beantrie.getGroupe().getType_trf_id();
				      TarificationBean  dssdf=(TarificationBean) mapTRF.get(key);
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
	public Boolean doCreateRowData(TarificationBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTarification.doSaveTarification(insertBean)){
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
	public Boolean doConfirmRowData(TarificationBean insertBean) throws Exception {
		 boolean result = false;
		 try {
		       if(daoTarification.doConfirmRowData(insertBean)){
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
	public Boolean  doUpdateRowData(TarificationBean updateBean)  throws Exception {
		 boolean result = false;
		 try {
		if(daoTarification.doUpdateTarification(updateBean)){
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
	public Boolean doDeleteRowData(TarificationBean deleteBean) throws Exception {
		 boolean result = false;
		 try {
		    if(daoTarification.doDeleteTarification(deleteBean)){
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
