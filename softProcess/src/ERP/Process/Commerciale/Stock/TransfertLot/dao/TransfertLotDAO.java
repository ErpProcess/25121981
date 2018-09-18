package ERP.Process.Commerciale.Stock.TransfertLot.dao;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Stock.TransfertLot.model.TransfertLotBean;
import ERP.Process.Commerciale.Stock.TransfertLot.template.TransfertLotTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
@Repository
public class TransfertLotDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<TransfertLotBean> doFindListTransfertLot(TransfertLotBean beanSearch) throws Exception {
		  Session session =  openSessionHibernate(sessionFactory);
		  List list_data= new ArrayList();
		  try{
		    String requette=" select  bean   FROM    TransfertLotBean    bean    WHERE     1=1       ";
 			   if( !StringUtils.isEmpty(beanSearch.getPk().getTransfert_id()) )  
	    			requette+="   AND   bean.pk.transfert_id ='"+beanSearch.getPk().getTransfert_id()+"'        ";    
 			   
 			   if( !StringUtils.isEmpty(beanSearch.getPk().getLot().getPk().getNum_serie()) )  
	    			requette+="   AND   bean.pk.lot.pk.num_serie ='"+beanSearch.getPk().getLot().getPk().getNum_serie()+"'        ";  
 			   
 			   if(  beanSearch.getPk().getLot().getPk().getDepot().getDepot_id() !=null )  
	    			requette+="   AND   bean.pk.lot.pk.depot.depot_id="+beanSearch.getPk().getLot().getPk().getDepot().getDepot_id()+"       ";   
 			   
 			   if(  beanSearch.getPk().getRecep().getDepot_id()!=null )  
	    			requette+="   AND   bean.pk.recep.depot_id =  "+beanSearch.getPk().getRecep().getDepot_id()+"        ";  
 			   
 			   if( beanSearch.getDate_transfert()!=null)  
	    			requette+="   AND   bean.date_transfert = '"+beanSearch.getDate_transfert()+"'        ";    
 			   if(  beanSearch.getQuantite_transfert() != null )  
	    			requette+="   AND   bean.quantite_transfert = "+beanSearch.getQuantite_transfert()+"      ";    
				  		list_data= session.createQuery(requette).list();
				        commitTransaction(session);
			 } catch (Exception e) {  
			     if (sessionIsTrue(session)) 
			    	 rollbackTransaction(session) ;
				     throw e;  
				 } finally {  
				 session.close();  
			 }  
			 return  list_data;
	 }  
			public Boolean doSaveTransfertLot(TransfertLotBean beanSave) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
			     boolean result=false; 
				try {
					this.setBeanTrace(beanSave);
					session.save(beanSave);
					this.saveTrace(beanSave);
					result=true;
					commitTransaction(session);
				 } catch (Exception ex) {
					 result=false;
					 rollbackTransaction(session) ;
				     throw ex;
				 } finally {  
					 session.close();  
				 } 
			      return result;
			}
			public Boolean doUpdateTransfertLot(TransfertLotBean beanUpdate)  throws Exception { 
		Session session =  openSessionHibernate(sessionFactory);
			    boolean result=false; 
				try {
					setIdBean((TransfertLotBean) getObjectValueModel(FORM_BEAN), beanUpdate, TransfertLotTemplate.id_entite);
					this.setUpdateValueFieldTraceOject(beanUpdate);
					session.update(beanUpdate);
					this.saveTrace(beanUpdate);
					result=true;
					commitTransaction(session);
				 } catch (Exception ex) {
					 result=false;
					 rollbackTransaction(session) ;
				     throw ex;
				 } finally {  
					 session.close();  
				 } 
			      return result;
			}
			public Boolean doDeleteTransfertLot(TransfertLotBean beanDelete)  throws Exception  {
		Session session =  openSessionHibernate(sessionFactory);
			    boolean result=false; 
				try {
					session.delete(beanDelete);
			        this.saveTrace(beanDelete);
					result=true;
					commitTransaction(session);
				 } catch (Exception ex) {
					 result=false;
					 rollbackTransaction(session) ;
				     throw ex;
				 } finally {  
					 session.close();  
				 } 
			      return result;
			}
}
