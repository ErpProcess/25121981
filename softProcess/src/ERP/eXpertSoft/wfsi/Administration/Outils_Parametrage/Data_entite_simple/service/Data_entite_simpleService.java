package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.service;

 
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.dao.Data_entite_simpleDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.model.Data_entite_simpleBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.template.Data_entite_simpleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;


 
 
@Service
public class Data_entite_simpleService  extends GenericWeb  {
	 
	private Data_entite_simpleDAO daoData_entite_simple;
	 
	@Transactional(readOnly=true)
	public List<Data_entite_simpleBean> dofetchDatafromServer(Data_entite_simpleBean beanSearch){
		return daoData_entite_simple.doFindListData_entite_simple(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<Data_entite_simpleBean> dofetchDataByCodeEntite(String code_entite){
		
		Data_entite_simpleBean beanSearch =new  Data_entite_simpleBean();
		beanSearch.setCode_entite(code_entite);
		return daoData_entite_simple.doFindListData_entite_simple(beanSearch);
	}
	
	 
	@Transactional
	public Boolean doCreateRowData(Data_entite_simpleBean insertBean){
		 try {
		       if(daoData_entite_simple.saveData_entite_simple(insertBean)){
		       return true;
		       }else{
		    	return false;  
		       }
		       
		 } catch (Exception e) { 
			 return false;
		}
	}
	
	 
	
	@Transactional
	public Boolean doUpdateRowData(Data_entite_simpleBean updateBean){
		if(daoData_entite_simple.updateData_entite_simple(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(Data_entite_simpleTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(Data_entite_simpleTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
			
	}
	
	
	@Transactional
	public Boolean doDeleteRowData(Data_entite_simpleBean deleteBean){
		if(daoData_entite_simple.dodeleteData_entite_simple(deleteBean)){
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
	public void setdaoData_entite_simple(Data_entite_simpleDAO daoData_entite_simple) {
		this.daoData_entite_simple = daoData_entite_simple;
	}

	 
	 
	
	
	
}
