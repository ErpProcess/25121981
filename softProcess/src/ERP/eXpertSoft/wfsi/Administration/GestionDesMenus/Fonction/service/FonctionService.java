package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.service;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.dao.FonctionDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.template.FonctionTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;


@Service
public class FonctionService  extends GenericWeb {
	
	private FonctionDAO daoFonction;
	
	@Autowired
	public void setDaoFonction(FonctionDAO daoFonction) {
		this.daoFonction = daoFonction;
	}

	@Transactional
	public Boolean CreateFonction(FonctionBean beanFonction){
		if(daoFonction.saveFonction(beanFonction))
			return true;
		else
			return false;
			
	}
	
	 
	@Transactional(readOnly=true)
	public List<FonctionBean> getFonctionList(FonctionBean searchBean){
		return daoFonction.getFonctions( searchBean);
	}
	
	
	@Transactional
	public Boolean doUpdateRowData(FonctionBean updateBean){
		if(daoFonction.updateFonction(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(FonctionTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(Integer.parseInt(indexo),updateBean);
			setObjectValueModel(FonctionTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(FonctionBean deleteBean){
		if(daoFonction.dodeleteFonction(deleteBean)){
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
