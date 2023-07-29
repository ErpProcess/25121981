package ERP.Process.Commerciale.Caracteristique.dao;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.Process.Commerciale.Caracteristique.template.CaracteristiqueTemplate;
import ERP.Process.Commerciale.DetailCaracteristique.dao.DetailCaracteristiqueDAO;
import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
@Repository
public class CaracteristiqueDAO extends  GenericWeb    {
	
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Autowired
	DetailCaracteristiqueDAO daoDetailCaracteristique;
	
	@SuppressWarnings("unchecked")
	public List<CaracteristiqueBean> doFindListCaracteristique(CaracteristiqueBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    CaracteristiqueBean    bean    WHERE     1=1       ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getCarac_id())  )      requette+="   AND  bean.carac_id='"+beanSearch.getCarac_id()+"'  ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getCarac_libelle())  ) requette+="   AND  bean.carac_libelle='"+beanSearch.getCarac_libelle()+"'  ";
		    
		    requette+="   AND  bean.carac_id !='Carc$'  ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getArticle_id_caracteristique())  )     
		    	requette+="     AND   bean.carac_id  IN ( SELECT  B.pkBean.carac_Bean.carac_id  " +
		    			"             from  Degre_definitionBean B   where   " +
		    			"             B.pkBean.art_Bean.pk_article.ar_id='"+beanSearch.getArticle_id_caracteristique()+"'     )  ";
		    
			return   hibernateTemplate.find(requette);
	}
	
	 
	
	public Boolean doSaveCaracteristique(CaracteristiqueBean beanSave) throws Exception {
	     boolean result=false; 
		try {
			this.setBeanTrace(beanSave);
			setPk_Soc_Etab(beanSave, "etab_bean.pk_etab.soc_bean.soc_id", "etab_bean.pk_etab.etab_id");
			this.hibernateTemplate.save(beanSave);
			this.saveTrace(beanSave);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doUpdateCaracteristique(CaracteristiqueBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((CaracteristiqueBean) getObjectValueModel(FORM_BEAN), beanUpdate, CaracteristiqueTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			setPk_Soc_Etab(beanUpdate, "etab_bean.pk_etab.soc_bean.soc_id", "etab_bean.pk_etab.etab_id");
			this.hibernateTemplate.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doDeleteCaracteristique(CaracteristiqueBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
			DetailCaracteristiqueBean d_list= new DetailCaracteristiqueBean();
			d_list.getPk_det_carac().getBean_carac().setCarac_id(beanDelete.getCarac_id());
			List  detil=daoDetailCaracteristique.doFindListDetailCaracteristique(d_list);
			hibernateTemplate.deleteAll(detil);
			setPk_Soc_Etab(beanDelete, "etab_bean.pk_etab.soc_bean.soc_id", "etab_bean.pk_etab.etab_id");
			hibernateTemplate.flush();
			hibernateTemplate.clear();
			hibernateTemplate.delete(beanDelete);
	        this.saveTrace(beanDelete);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
}
