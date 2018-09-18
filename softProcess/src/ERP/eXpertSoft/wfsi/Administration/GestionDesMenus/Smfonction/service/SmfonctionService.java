package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.dao.SmfonctionDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model.SmfonctionModel;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.template.SmfonctionTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;

@Service
public class SmfonctionService extends GenericWeb {
	private SmfonctionDAO daoSmfonction;
	private NumSequentielDAO daoNumSequentiel;

	@Autowired
	public void setDaoSmfonction(SmfonctionDAO daoSmfonction) {
		this.daoSmfonction = daoSmfonction;
	}

	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}

	@Transactional 
	public List<SmfonctionModel> dofetchDatafromServer( SmfonctionModel modelSearch) {
		return daoSmfonction.doFindListSmfonction(modelSearch);
	}

	@Transactional
	public Boolean doCreateRowData(SmfonctionModel insertModel) throws Exception{
		boolean res=false;
		try {
			if (daoSmfonction.saveSmfonction(insertModel)) {
				res=true;
			} else {
				res=false;
			}
		} catch (Exception e) {
			throw e;
			
		}
		return res;
	}

	@Transactional
	public Boolean doUpdateRowData(SmfonctionModel updateModel) {
		if (daoSmfonction.updateSmfonction(updateModel)) {
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(SmfonctionTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateModel);
			setObjectValueModel(SmfonctionTemplate.LIST_DATA, list);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public Boolean doDeleteRowData(SmfonctionModel deleteModel) throws Exception {
		boolean rsul=false;
		try {
			daoSmfonction.doDeleteSmfonction(deleteModel);
			rsul=true;
		} catch (Exception e) {
			rsul=false;
			throw e;
		}
		return rsul;
		     
		 
	}
}
