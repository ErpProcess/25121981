package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.dao.PrivilegeDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.model.PrivilegeBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.template.PrivilegeTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class PrivilegeService  extends GenericWeb  {
	private PrivilegeDAO daoPrivilege;
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoPrivilege(PrivilegeDAO daoPrivilege) {
		this.daoPrivilege = daoPrivilege;
	}
	
	
	@Transactional
	public List<PrivilegeBean> dofetchDatafromServer(PrivilegeBean beanSearch){
		return daoPrivilege.doFindListPrivilege(beanSearch);
	}
	@Transactional
	public void doCreateRowData(List lisDataToInsert) throws Throwable {
		 try {
			    //daoNumSequentiel.getNumSeqSimple(insertBean,"usr_id",4);
		     daoPrivilege.savePrivilege(lisDataToInsert);
		        
		 } catch (Exception ex) { 
			 ex.printStackTrace();
		}
	}
	@Transactional
	public Boolean doUpdateRowData(PrivilegeBean updateBean){
		if(daoPrivilege.updatePrivilege(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(PrivilegeTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(PrivilegeTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
	}
	@Transactional
	public Boolean doDeleteRowData(PrivilegeBean deleteBean){
		if(daoPrivilege.dodeletePrivilege(deleteBean)){
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
