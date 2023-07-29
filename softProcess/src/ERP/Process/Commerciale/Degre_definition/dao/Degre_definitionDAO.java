package ERP.Process.Commerciale.Degre_definition.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.Process.Commerciale.Degre_definition.model.Degre_definitionBean;
import ERP.Process.Commerciale.Degre_definition.template.Degre_definitionTemplate;
@Repository
public class Degre_definitionDAO extends  GenericWeb    {
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/* @Autowired
	 private SessionFactory sessionFactorys;

	 public void setSessionFactorys(SessionFactory sessionFactorys) {
	     this.sessionFactorys = sessionFactorys;
	 }
	*/
	
	
	@SuppressWarnings("unchecked")
	public List<Degre_definitionBean> doFindListDegre_definition(Degre_definitionBean beanSearch) throws Exception {
		
		   
		    String requette="   SELECT  m  from Degre_definitionBean m  WHERE  1=1  ";
		    	     
		    
		     if(!StringUtils.isEmpty(beanSearch.getPkBean().getArt_Bean().getPk_article().getAr_id()))
		    	requette+="   AND    m.pkBean.art_Bean.pk_article.ar_id ='"+beanSearch.getPkBean().getArt_Bean().getPk_article().getAr_id()+"'  ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getPkBean().getCarac_Bean().getCarac_id()))
		    	requette+="   AND    m.pkBean.carac_Bean.carac_id ='"+beanSearch.getPkBean().getCarac_Bean().getCarac_id()+"'     ";
		    
		        requette+="   ORDER BY  m.ordre  "; 
		        
		     List list_deg_def = hibernateTemplate.find(requette);
		     
			return  list_deg_def;
	}
	public Boolean doSaveDegre_definition(Degre_definitionBean beanSave) throws Exception {
	     boolean result=false; 
		try {
			this.setBeanTrace(beanSave);
			List <CaracteristiqueBean>listCar=(List) getObjectValueModel(Degre_definitionTemplate.LIST_DES_CARCTERISTIQUE);
			 
			for (CaracteristiqueBean  bean:listCar) {
				
				if( !StringUtils.isEmpty(bean.getTo_check())   ){
					Degre_definitionBean bean2 =new Degre_definitionBean();
					bean2.setOrdre(bean.getOrdre());
					bean2.getPkBean().getArt_Bean().getPk_article().setAr_id(beanSave.getPkBean().getArt_Bean().getPk_article().getAr_id());
					bean2.getPkBean().getCarac_Bean().setCarac_id(bean.getCarac_id());
					this.setPk_Soc_Etab(bean2, "pkBean.art_Bean.pk_article.etabBean.pk_etab.soc_bean.soc_id", "pkBean.art_Bean.pk_article.etabBean.pk_etab.etab_id");
					this.hibernateTemplate.save(bean2);
					this.saveTraceVersion1(bean2, Degre_definitionTemplate.MapfieldBean, Degre_definitionTemplate.id_entite_degre_def, Degre_definitionTemplate.entites);
				}
				
			}
			
			
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	public Boolean doUpdateDegre_definition(Degre_definitionBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((Degre_definitionBean) getObjectValueModel(FORM_BEAN), beanUpdate, Degre_definitionTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
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
	public Boolean doDeleteDegre_definition(Degre_definitionBean beanDelete)  throws Exception  {
	    boolean result=false; 
		try {
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
