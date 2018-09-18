package ERP.Process.Commerciale.Fournisseur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Fournisseur.dao.FournisseurDAO;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.template.FournisseurTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

@Service
public class FournisseurService extends GenericWeb {
	private FournisseurDAO daoFrournisseur;

	@Autowired
	public void setDaoFrournisseur(FournisseurDAO daoFrournisseur) {
		this.daoFrournisseur = daoFrournisseur;
	}

	@Transactional(readOnly = true)
	public List<FournisseurBean> dofetchDatafromServer( FournisseurBean beanSearch) throws Exception {
		return daoFrournisseur.doFindListFrournisseur(beanSearch);
	}

	@Transactional
	public Boolean doCreateRowData(FournisseurBean insertBean)
			throws Exception {
		boolean result = false;
		try {
			if (daoFrournisseur.doSaveFrournisseur(insertBean)) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			result = false;
			throw e;
		}
		return result;
	}

	@Transactional
	public Boolean doUpdateRowData(FournisseurBean updateBean)
			throws Exception {
		boolean result = false;
		try {
			if (daoFrournisseur.doUpdateFrournisseur(updateBean)) {
				String indexo = (String) getObjectValueModel(INDEX_ROW);
				List list = (List) getObjectValueModel(FournisseurTemplate.LIST_DATA);
				list.remove(Integer.parseInt(indexo));
				list.add(updateBean);
				setObjectValueModel(FournisseurTemplate.LIST_DATA, list);
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			result = false;
			throw e;
		}
		return result;
	}

	@Transactional
	public Boolean doDeleteRowData(FournisseurBean deleteBean)
			throws Exception {
		boolean result = false;
		try {
			if (daoFrournisseur.doDeleteFrournisseur(deleteBean)) {
				String indexo = (String) getObjectValueModel(INDEX_ROW);
				List list = (List) getObjectValueModel((String) getObjectValueModel(NAME_LIST_G));
				list.remove(Integer.parseInt(indexo));
				setObjectValueModel((String) getObjectValueModel(NAME_LIST_G),
						list);
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			result = false;
			throw e;
		}
		return result;
	}
}
