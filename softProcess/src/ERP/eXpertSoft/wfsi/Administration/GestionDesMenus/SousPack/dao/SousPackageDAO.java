package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.util.SousPackageTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
 
@Repository
public class SousPackageDAO  extends  GenericWeb {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<SousPackageBean> getSousPacks(SousPackageBean beanSearch) {
        String  reqString="  from  SousPackageBean  uta     where   1=1   ";	
        
        if(beanSearch.getSpack_id()!=null){
        	  reqString+="    and  uta.spack_id="+beanSearch.getSpack_id()+"";
        }
        
        if( !StringUtils.isEmpty(beanSearch.getSpack_libelle())    ){
      	  reqString+="    and    uta.spack_libelle='"+beanSearch.getSpack_libelle()+"'";
      }
        
		List lustgetSousPacks= hibernateTemplate.find(reqString );
		return  lustgetSousPacks;
	}
	
	 
	
	 
	public List<SousPackageBean> getSousPacksByCriteara(SousPackageBean beanSearch ) {
		List lll= hibernateTemplate.find(" " +
				"  from  SousPackageBean  uta     where   1=1   and    cast(uta.packageSys.pack_id as string) ='"+String.valueOf(beanSearch.getPackageSys().getPack_id())+"'    " +
				"" +
				"     " );
		return  lll;
	}
	
	
	public List<SousPackageBean> getSousPackListVi(SousPackageBean beanSearch ) {
		List lll= hibernateTemplate.find(" " +
				"    from  SousPackageBean  uta     where    1=1    and    uta.packageSys.pack_id ="+beanSearch.getPackageSys().getPack_id()+"    " );
		return  lll;
	}
	
	
	
	
	 
	
	
	public Boolean saveSousPackage(SousPackageBean beanSave) {

		try {
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			this.saveTrace(beanSave);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateSousPackage(SousPackageBean beanUpdate) {

		try {
			this.setIdBean((SousPackageBean) getObjectValueModel(FORM_BEAN), beanUpdate, SousPackageTemplate.id_entite);
			this.hibernateTemplate.update(beanUpdate);
			this.hibernateTemplate.bulkUpdate(" update  EntiteAdminBean  e  set  " +
					"    e.pack_id='"+beanUpdate.getPackageSys().getPack_id()+"'  where  e.spack_id='"+beanUpdate.getSpack_id()+"' "   );
			
			this.hibernateTemplate.bulkUpdate(" update  PrivilegeBean  e  set  " +
					"    e.packBean.pack_id="+beanUpdate.getPackageSys().getPack_id()+"  where  e.spackBean.spack_id="+beanUpdate.getSpack_id()+" "   );
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeleteSousPackage(SousPackageBean beanDelete) {

		try {
			this.setIdBean((SousPackageBean) getObjectValueModel(FORM_BEAN), beanDelete, SousPackageTemplate.id_entite);
			this.hibernateTemplate.delete(beanDelete);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
	 
	 
 

}
