package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.dao.SousPackageDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.util.SousPackageTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
 


/**
 * Contact Service
 * 
 */
@Service
public class SousPackageService extends GenericWeb {
	
	private SousPackageDAO daoSousPackage;
	
	@Autowired
	public void setDaoSousPackage(SousPackageDAO daoSousPackage) {
		this.daoSousPackage = daoSousPackage;
	}
 

	
	
	@Transactional
	public void CreatePackSystem(SousPackageBean SousPack){
		daoSousPackage.saveSousPackage(SousPack);
	}
	
	/**
	 * Get all SousPacks
	 * @return
	 */
	@Transactional
	public List<SousPackageBean> getSousPackList(SousPackageBean beanSearch){
		return daoSousPackage.getSousPacks(beanSearch);
	}
	
	@Transactional
	public List<SousPackageBean> getSousPackListByCritaria(SousPackageBean beanSearch){
		return daoSousPackage.getSousPacksByCriteara(beanSearch);
	}
	
	
	@Transactional
	public List<SousPackageBean> getSousPackListVi(SousPackageBean beanSearch){
		return daoSousPackage.getSousPackListVi(beanSearch);
	}
	
	
	
	@Transactional
	public Boolean doUpdateRowData(SousPackageBean updateBean){
		if(daoSousPackage.updateSousPackage(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(SousPackageTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(Integer.parseInt(indexo),updateBean);
			//setObjectValueModel(SousPackageTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(SousPackageBean deleteBean){
		if(daoSousPackage.dodeleteSousPackage(deleteBean)){
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
