package ERP.Process.Commerciale.FamilleArticle.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ERP.Process.Commerciale.FamilleArticle.dao.FamilleArticleDAO;
import ERP.Process.Commerciale.FamilleArticle.model.FamilleBean;
import ERP.Process.Commerciale.FamilleArticle.template.FamilleArticleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Service
public class FamilleArticleService  extends GenericWeb  {
	
	
	private FamilleArticleDAO daoFamilleArticle;
	 
	@Autowired
	public void setDaoFamilleArticle(FamilleArticleDAO daoFamilleArticle) {
		this.daoFamilleArticle = daoFamilleArticle;
	}
	@Transactional(readOnly=true)
	public List<FamilleBean> dofetchDatafromServer(FamilleBean beanSearch){
		return daoFamilleArticle.doFindListFamilleArticle(beanSearch);
	}
	
	
	@Transactional
	public Boolean doCreateRowData(FamilleBean insertBean){
		 try {
			     
		       if(daoFamilleArticle.saveFamilleArticle(insertBean)){
		       return true;
		       }else{
		    	return false;  
		       }
		 } catch (Exception e) { 
			 return false;
		}
	}
	@Transactional
	public Boolean doUpdateRowData(FamilleBean updateBean){
		if(daoFamilleArticle.updateFamilleArticle(updateBean)){
			String indexo = (String) getObjectValueModel(INDEX_ROW);
			List list = (List) getObjectValueModel(FamilleArticleTemplate.LIST_DATA);
			list.remove(Integer.parseInt(indexo));
			list.add(updateBean);
			setObjectValueModel(FamilleArticleTemplate.LIST_DATA, list);
			return true;
		}else{
			return false;
		}
	}
	@Transactional
	public Boolean doDeleteRowData(FamilleBean deleteBean){
		if(daoFamilleArticle.dodeleteFamilleArticle(deleteBean)){
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
