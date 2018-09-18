package ERP.Process.Commerciale.Vente.Commandeclient.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.DetCmdCltBean;
import ERP.Process.Commerciale.Vente.Commandeclient.template.CommandeclientTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.hibernate.HibernateUtil;

@Repository
public class CommandeclientDAO   extends  GenericWeb   {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    

	@SuppressWarnings("unchecked")
	public List<CommandeclientBean> doFindListCommandeclient( CommandeclientBean beanSearch) throws Exception {
		List<CommandeclientBean>   lisf = new ArrayList<CommandeclientBean>();
		Session session =  openSessionHibernate(sessionFactory);
		try {
		String requette = " select  bean   FROM    CommandeclientBean    bean    WHERE     1=1       ";
		if (!StringUtils.isEmpty(beanSearch.getCmd_id()))
			requette += "   AND   bean.cmd_id = '" +beanSearch.getCmd_id()+"'        ";
		if (!StringUtils.isEmpty(beanSearch.getCmd_libelle()))
			requette += "   AND   bean.cmd_libelle = '"+ beanSearch.getCmd_libelle()+"'        ";

		if (!StringUtils.isEmpty(beanSearch.getClient().getClt_id()))
			requette += "   AND   bean.client.clt_id = '"+ beanSearch.getClient().getClt_id() +"'        ";

		if (beanSearch.getCmd_date() != null)
			requette += "   AND  bean.cmd_date >= '"+ ProcessDate.getStringFormatDate(beanSearch.getCmd_date())
					+ "'        ";

		if (beanSearch.getCmd_date2() != null)
			requette += "   AND  bean.cmd_date <= '"+ ProcessDate.getStringFormatDate(beanSearch.getCmd_date2())+ "'        ";

		if (!StringUtils.isEmpty(beanSearch.getCondition_select_mode()))
			requette += "" + beanSearch.getCondition_select_mode();
		
		 lisf= session.createQuery(requette).list();
				 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		 return  lisf;
				
			}
	
	
	@SuppressWarnings("unchecked")
	public List<DetCmdCltBean> doFindList_detaille_Commandeclient( CommandeclientBean beanSearch) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		List<DetCmdCltBean>   lisf = new ArrayList<DetCmdCltBean>();
		try {
		String requette = " select  bean   FROM    DetCmdCltBean    bean    WHERE     1=1       ";
		
		if (!StringUtils.isEmpty(beanSearch.getCmd_id()))
			requette += "   AND   bean.pk.cmd.cmd_id = '"+beanSearch.getCmd_id()+"'        ";
		 lisf= session.createQuery(requette).list();
		 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		 return  lisf;
 
	}
	
	
	
	
	

	public Boolean doSaveData(CommandeclientBean beanSave) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		boolean result = false;
		try {
			 
			setBeanTrace(beanSave);
			List listOfmyData = (List) getObjectValueModel(CommandeclientTemplate.LIST_EDITABLE_CMD_CLT);
			
			
			CommandeclientBean  beanTotal  =(CommandeclientBean) getObjectValueModel(CommandeclientTemplate.BEAN_TOTAL  );
			beanSave.setAvance_montant_cmd(beanTotal.getAvance_montant_cmd());
			beanSave.setCommande_remise(beanTotal.getCommande_remise());
			beanSave.setCommande_mnt_ht(beanTotal.getCommande_mnt_ht());
			beanSave.setCommande_mnt_tva(beanTotal.getCommande_mnt_tva());
			beanSave.setCommande_mnt_total(beanTotal.getCommande_mnt_total());
			
		 
			  
			session.save(beanSave);
			saveTrace(beanSave);
			// this.saveTraceVersion1( beanSave ,
			// Reception_achatTemplate.MapfieldBean ,
			// Reception_achatTemplate.id_entite_achat ,
			// Reception_achatTemplate.entites);

			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetCmdCltBean detBean = (DetCmdCltBean) listOfmyData.get(i);
				if (detBean.getQuantite() == null) {
					continue;
				}
				if (detBean.getQuantite() == 0 || detBean.getQuantite() < 0) {
					continue;
				}
				detBean.getPk().setCmd(beanSave);
				session.save(detBean);
				result_detaille = true;
			}
			if (!result_detaille)
				GenericWeb.throwNewException("err_inser_deaill");

			GenericWeb.saveTraceVersion_Master_detailles(listOfmyData,
					CommandeclientTemplate.MapfieldBean_detaille,
					CommandeclientTemplate.id_entite_detaille,
					CommandeclientTemplate.entites_detaille);

			// if( !StringUtils.isBlank(
			// beanSave.getDem_achat().getDem_achat_id()) )
			// this.hibernateTemplate.bulkUpdate(" UPDATE Demande_achatBean b
			// set b.modeBean.fct_id="+bs.getFct_id()+" where
			// b.dem_achat_id='"+beanSave.getDem_achat().getDem_achat_id()+"'
			// ");

			  result = true;
			 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     result = false;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		 
		return result;

	}

	@SuppressWarnings("unchecked")
	public Boolean doUpdateBean(CommandeclientBean beanUpdate) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
		boolean result = false;
		try {
			List <DetCmdCltBean>listOrigin = (List) getObjectValueModel(CommandeclientTemplate.LIST_EDITABLE_CMD_CLT_ORIGIN);
			 
			for (DetCmdCltBean   be:listOrigin) {
				session.delete(be);
			}
			session.flush();
			session.clear();
			
			List listOfmyData = (List) getObjectValueModel(CommandeclientTemplate.LIST_EDITABLE_CMD_CLT);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			boolean result_detaille = false;
			for (int i = 0; i < listOfmyData.size(); i++) {
				DetCmdCltBean detBean = (DetCmdCltBean) listOfmyData.get(i);
				if (detBean.getQuantite() == null) {
					continue;
				}
				if (detBean.getQuantite() == 0 || detBean.getQuantite() < 0) {
					continue;
				}
				detBean.getPk().setCmd(beanUpdate); 
				session.save(detBean);
				result_detaille = true;
			}
			if (!result_detaille)
				throwNewException("err_inser_deaill");
			  this.saveTraceVersion_Master_detailles(listOfmyData,
			  CommandeclientTemplate.MapfieldBean_detaille,
			  CommandeclientTemplate.id_entite_detaille,
			  CommandeclientTemplate.entites_detaille);
			  
			  
			  CommandeclientBean  beanTotal  =(CommandeclientBean) getObjectValueModel(CommandeclientTemplate.BEAN_TOTAL  );
			  beanUpdate.setAvance_montant_cmd(beanTotal.getAvance_montant_cmd());
				beanUpdate.setCommande_remise(beanTotal.getCommande_remise());
				beanUpdate.setCommande_mnt_ht(beanTotal.getCommande_mnt_ht());
				beanUpdate.setCommande_mnt_tva(beanTotal.getCommande_mnt_tva());
				beanUpdate.setCommande_mnt_total(beanTotal.getCommande_mnt_total());
				
			  session.saveOrUpdate(beanUpdate);
			  this.saveTrace(beanUpdate);
			 

			result = true;
	 
			 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     result = false;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;

	}

	public Boolean doDeleteBean(CommandeclientBean beanDeleteX) throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
 
			CommandeclientBean beanReCEP = (CommandeclientBean) getObjectValueModel(FORM_BEAN);

			List<DetCmdCltBean> List_det = new ArrayList<DetCmdCltBean>(beanReCEP.getList_detaille());

			session.delete(List_det);
			  this.saveTraceVersion_Master_detailles(List_det,
					  CommandeclientTemplate.MapfieldBean_detaille,
					  CommandeclientTemplate.id_entite_detaille,
					  CommandeclientTemplate.entites_detaille);
			  session.flush();
			  session.delete(beanReCEP);
			this.saveTrace(beanReCEP);

			 

			 
			result = true;
			 
			 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     result = false;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}

	public Boolean doExcuterTransaction(CommandeclientBean beanchange)
			throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			this.setUpdateValueFieldTraceOject(beanchange);
			session.saveOrUpdate(beanchange);
			saveTrace(beanchange);
			result = true; 
			commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     result = false;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}

}
