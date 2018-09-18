package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.dao.ProfileDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.template.ProfileTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class ProfileService  extends GenericWeb  {
	private ProfileDAO daoProfile;
	private NumSequentielDAO daoNumSequentiel;
	
	
	@Autowired
	public void setDaoProfile(ProfileDAO daoProfile) {
		this.daoProfile = daoProfile;
	}
	
	
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	
	@Transactional(readOnly=true)
	public List<ProfileBean> dofetchDatafromServer(ProfileBean beanSearch){
		return daoProfile.doFindListProfile(beanSearch);
	}
	
	
	@Transactional
	public Boolean doCreateRowData(ProfileBean insertBean){
		 try {
			    daoNumSequentiel.getNumSeqSimple(insertBean,"prf_id");
		       if(daoProfile.saveProfile(insertBean)){
		       return true;
		       }else{
		    	return false;  
		       }
		 } catch (Exception e) { 
			 return false;
		}
	}
	@Transactional
	public Boolean doUpdateRowData(ProfileBean updateBean){
		if(daoProfile.updateProfile(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(ProfileTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(ProfileTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
	}
	@Transactional
	public Boolean doDeleteRowData(ProfileBean deleteBean){
		if(daoProfile.dodeleteProfile(deleteBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			list.remove(Integer.parseInt(indexo));
			setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), list);
			return true;
		}else{
			return false;
		}
	}
}
