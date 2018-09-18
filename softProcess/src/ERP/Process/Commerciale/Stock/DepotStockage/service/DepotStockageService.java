package ERP.Process.Commerciale.Stock.DepotStockage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.Stock.DepotStockage.dao.DepotStockageDAO;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.DepotStockage.template.DepotStockageTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;

@Service
public class DepotStockageService extends GenericWeb {
	

	public DepotStockageService() {
		super();
		// TODO Auto-generated constructor stub
	}
	private DepotStockageDAO daoDepotStockage;
	@Autowired
	public void setDaoDepotStockage(DepotStockageDAO daoDepotStockage) {
		this.daoDepotStockage = daoDepotStockage;
	}

	@Transactional(readOnly = true)
	public List<DepotStockageBean> doFetchDatafromServer(
			DepotStockageBean beanSearch) throws Exception {
		return daoDepotStockage.doFindListDepotStockage(beanSearch);
	}

	@Transactional
	public Boolean doCreateRowData(DepotStockageBean insertBean)
			throws Exception {
		boolean result = false;
		try {
			if (daoDepotStockage.doSaveDepotStockage(insertBean)) {
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
	public Boolean doUpdateRowData(DepotStockageBean updateBean)
			throws Exception {
		boolean result = false;
		try {
			if (daoDepotStockage.doUpdateDepotStockage(updateBean)) {
				String indexo = (String) getObjectValueModel(INDEX_ROW);
				List list = (List) getObjectValueModel(DepotStockageTemplate.LIST_DATA);
				list.remove(Integer.parseInt(indexo));
				list.add(updateBean);
				setObjectValueModel(DepotStockageTemplate.LIST_DATA, list);
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
	public Boolean doDeleteRowData(DepotStockageBean deleteBean)
			throws Exception {
		boolean result = false;
		try {
			if (daoDepotStockage.doDeleteDepotStockage(deleteBean)) {
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
