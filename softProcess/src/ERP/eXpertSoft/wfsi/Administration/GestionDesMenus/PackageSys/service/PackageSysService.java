package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.dao.PackageSysDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.util.PackageSysTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;



/**
 * Contact Service
 * 
 */
@Service
public class PackageSysService extends GenericWeb  {
	
	private PackageSysDAO packageSysDAO;

	@Transactional
	public Boolean CreatePackSystem(PackageSysBean packageSys){
		if(packageSysDAO.savePackageSys(packageSys)){
			// this.saveTrace(packageSys);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	 
	 
	public Boolean UpdatePackSystem(PackageSysBean packageSys){
		if(packageSysDAO.updatePackageSys(packageSys)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(PackageSysTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(Integer.parseInt(indexo),packageSys);
			setObjectValueModel(PackageSysTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean DeletePackSystem(PackageSysBean packageSys){
		if(packageSysDAO.deletePackageSys(packageSys)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(PackageSysTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			setObjectValueModel(PackageSysTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	
	/**
	 * Get all packageSyss
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<PackageSysBean> getPackageSysList(PackageSysBean beanpSys){
		return packageSysDAO.getPackageSyss(beanpSys);
	}
	
	 
	
	@Autowired
	public void setPackageSysDAO(PackageSysDAO packageSysDAO) {
		this.packageSysDAO = packageSysDAO;
	}



	
}
