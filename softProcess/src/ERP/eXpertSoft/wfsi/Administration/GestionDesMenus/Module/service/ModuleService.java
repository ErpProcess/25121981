package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.service;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.dao.ModuleDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.template.ModuleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
 


/**
 * Contact Service
 * 
 */
@Service
public class ModuleService extends  GenericWeb {
	
	private ModuleDAO moduleDAO;
	 

	
	
	@Transactional
	public Boolean CreatePackSystem(ModuleBean moduleBean){
		if(moduleDAO.saveModule(moduleBean))
			return true;
		else
			return false;
			
	}
	
	 
	@Transactional(readOnly=true)
	public List<ModuleBean> getModuleList(){
		return moduleDAO.getModules();
	}
	
	
	@Transactional(readOnly=true)
	public List<ModuleBean> getSomeModules(ModuleBean mBean ){
		return moduleDAO.getSomeModules(mBean);
	}
	
	 
	@Transactional(readOnly=true)
	public List<ModuleBean> dofetchDatafromServer(ModuleBean beanSearch){
		return moduleDAO.doFindListModule(beanSearch);
	}
	
	@Transactional
	public Boolean doUpdateRowData(ModuleBean updateBean){
		if(moduleDAO.updateModule(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(ModuleTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(Integer.parseInt(indexo),updateBean);
			//setObjectValueModel(ModuleTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(ModuleBean deleteBean){
		if(moduleDAO.dodeleteModule(deleteBean)){
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
	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	 
	 
	
}
