package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.service;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.dao.SousModuleDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.template.SousModuleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

 
 

 
 
@Service
public class SousModuleService  extends GenericWeb {
	
	private SousModuleDAO sousModuleDAO;
	
	
	@Transactional
	public Boolean CreateSousModel(SousModuleBean sousModuleBean){
		if(sousModuleDAO.saveSousModule(sousModuleBean))
			return true;
		else
			return false;
			
	}
	
	 
	@Transactional 
	public List<SousModuleBean> getSousModuleList(SousModuleBean searchBean){
		return sousModuleDAO.getSousModules(searchBean);
	}
	
	@Transactional 
	public List<SousModuleBean> getSousModuleListById(String id){
		return sousModuleDAO.getSousModulesById(id);
	}
	
	
	@Transactional 
	public Boolean doUpdateSousModule(SousModuleBean beanUpdate){
		return sousModuleDAO.updateWebRootFolder(beanUpdate);
	}
	
	
	
	@Transactional(readOnly=true)
	public List<SousModuleBean> dofetchDatafromServer(SousModuleBean beanSearch){
		return sousModuleDAO.doFindListWebRootFolder(beanSearch);
	}
	 
	
	
	@Transactional
	public Boolean doUpdateRowData(SousModuleBean updateBean){
		if(sousModuleDAO.updateSousModule(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(SousModuleTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(Integer.parseInt(indexo),updateBean);
			setObjectValueModel(SousModuleTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(SousModuleBean deleteBean){
		if(sousModuleDAO.dodeleteSousModule(deleteBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
			list.remove(Integer.parseInt(indexo));
			setObjectValueModel((String) getObjectValueModel(NAME_LIST_G), list);
			return true;
		}else{
			return false;
		}
			
	}
 

	
	@Autowired
	public void setSousModuleDAO(SousModuleDAO sousModuleDAO) {
		this.sousModuleDAO = sousModuleDAO;
	}
	
	 
	
	 
	

	 
	 
	
}
