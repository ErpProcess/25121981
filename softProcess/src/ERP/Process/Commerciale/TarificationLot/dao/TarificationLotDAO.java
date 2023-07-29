package ERP.Process.Commerciale.TarificationLot.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.Process.Commerciale.TarificationLot.template.TarificationLotTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
@Repository
public class TarificationLotDAO extends  GenericWeb    {
	@Autowired
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TarificationLotBean> doFindListTarificationLot(TarificationLotBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List<TarificationLotBean>   lisf = new ArrayList<TarificationLotBean>();
		try {
			
	 
		    String requette=" select  bean   FROM    TarificationLotBean    bean    WHERE     1=1       ";
		    
			  if( !StringUtils.isEmpty(beanSearch.getPk().getLot().getPk().getNum_serie()) )  
				    requette+="   AND   bean.pk.lot.pk.num_serie = '"+beanSearch.getPk().getLot().getPk().getNum_serie()+"'        ";   
			  
			  if(  beanSearch.getPk().getLot().getPk().getDepot().getDepot_id()!= null )  
				    requette+="   AND   bean.pk.lot.pk.depot.depot_id = "+beanSearch.getPk().getLot().getPk().getDepot().getDepot_id()+"       ";
			 
			  if( !StringUtils.isEmpty(beanSearch.getPk().getVente().getTarif_vente_id()) )  
				    requette+="   AND   bean.pk.vente.tarif_vente_id = '"+beanSearch.getPk().getVente().getTarif_vente_id()+"'        ";
			 
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

	 
	public Boolean doSaveTarificationLot(TarificationLotBean beanSave) throws Exception {
	     boolean result=false; 
	     Session session =  openSessionHibernate(sessionFactory);
		try {
			 
			 TarificationBean vente =beanSave.getPk().getVente();
			 daoNumSequentiel.getNumSeqSimple(vente,"tarif_vente_id");
			 HashMap   mapArticle           =  (HashMap) getObjectValueModel("mapArticle");
		     Code_barreBean  fkCode_barre   =(Code_barreBean) mapArticle.get(beanSave.getPk().getVente().getFkCode_barre().getPk().getCode_barre());
		     vente.setFkCode_barre(fkCode_barre);
		     vente.setDepot(beanSave.getPk().getLot().getPk().getDepot());
		     HashMap   mapSe  =  (HashMap) getObjectValueModel("mapSerie"  );
			 SerieArticletBean   sss =(SerieArticletBean) mapSe.get(beanSave.getPk().getLot().getPk().getNum_serie());
		     vente.setCout(sss.getTarif());
		     
			 setBeanTrace(vente);
			 beanSave.getPk().setVente(vente);
			 vente.setTarif_lot(true);
			 session.save(vente);
			 
			 setBeanTrace(beanSave);
			 session.save(beanSave);
 			 result=true;
			 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     result=false; 
		     throw e;  
		 } finally {  
			  session.close();  
		 }  
		 
	    return result;
	}
	public Boolean doUpdateTarificationLot(TarificationLotBean beanUpdate)  throws Exception { 
	    boolean result=false; 
	    Session session =  openSessionHibernate(sessionFactory);
		try {
			setIdBean((TarificationLotBean) getObjectValueModel(FORM_BEAN), beanUpdate, TarificationLotTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result=true;
			 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     result=false; 
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
	    return result;
	}
	
	public Boolean doConfirmTarificationLot(TarificationLotBean beanUpdate)  throws Exception { 
	    boolean result=false; 
	    Session session =  openSessionHibernate(sessionFactory);
		try {
			TarificationLotBean tBean = (TarificationLotBean) getObjectValueModel(FORM_BEAN);
			beanUpdate.setPk(tBean.getPk());
			TarificationBean  tarif=beanUpdate.getPk().getVente();
			setObjectValueModel(ORIGINAL_FORM_BEAN, ProcessUtil.cloneObject(tarif));
			this.setUpdateValueFieldTraceOject(tarif);
			tarif.setTarif_lot(true);
			session.update(tarif);
			 result=true;
			 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     result=false; 
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
	    return result;
	}
	
	public Boolean doDeleteTarificationLot(TarificationLotBean beanDelete)  throws Exception  {
	    boolean result=false; 
	    Session session =  openSessionHibernate(sessionFactory);
		try {
			session.delete(beanDelete);
	        this.saveTrace(beanDelete);
			result=true;
			 commitTransaction(session);
		 } catch (Exception e) {  
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     result=false; 
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
	    return result;
	}
}
