package ERP.Process.Commerciale.Vente.Client.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Vente.Client.dao.ClientDAO;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Service
public class ClientService  extends GenericWeb  {
	private ClientDAO daoClient;
	@Autowired
	public void setDaoClient(ClientDAO daoClient) {
		this.daoClient = daoClient;
	}
	@Transactional 
	public List<ClientBean> doFetchDatafromServer(ClientBean beanSearch) throws Exception {
		return daoClient.doFindListClient(beanSearch);
	}
	@Transactional
	public Boolean doCreateRowData(ClientBean insertBean) throws Exception {
		return daoClient.doSaveClient(insertBean); 
	}
	@Transactional
	public Boolean  doUpdateRowData(ClientBean updateBean)  throws Exception {
	 return daoClient.doUpdateClient(updateBean); 
	}
	@Transactional
	public Boolean doDeleteRowData(ClientBean deleteBean) throws Exception {
     return daoClient.doDeleteClient(deleteBean); 
	 }
}
