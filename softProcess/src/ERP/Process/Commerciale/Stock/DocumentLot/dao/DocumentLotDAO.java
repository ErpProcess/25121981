package ERP.Process.Commerciale.Stock.DocumentLot.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Stock.DocumentLot.model.SerieArticletBean;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationLot.model.TarificationLotBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessWebUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
@Repository
public class DocumentLotDAO extends  GenericWeb    {
	 
	@Autowired
	private SessionFactory sessionFactory;
    public void setSessionFactoryTwo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}  
	     
	     @SuppressWarnings("unchecked")
	     public List<SerieArticletBean  > doFindListDocumentLot(SerieArticletBean beanSearch) throws Exception {
	    	 Session session =  openSessionHibernate(sessionFactory);
	 		List<SerieArticletBean>   lisf = new ArrayList<SerieArticletBean>();
	 		try {
	 			 BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);	
				String requette=" select  bean   FROM    SerieArticletBean    bean    WHERE     1=1       ";
				    
		 if( !StringUtils.isEmpty(beanSearch.getPk().getNum_serie()) )  
			    requette+="   AND   bean.pk.num_serie ='"+beanSearch.getPk().getNum_serie()+"'            "; 
		 
		 if(  beanSearch.getPk().getDepot().getDepot_id()!=null )  
			    requette+="   AND   bean.pk.depot.depot_id ="+beanSearch.getPk().getDepot().getDepot_id()+"           "; 
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getFkCode_barre().getPk().getCode_barre()) )  
			    requette+="   AND   bean.fkCode_barre.pk.code_barre='"+beanSearch.getFkCode_barre().getPk().getCode_barre()+"'            "; 
		 
		 
		 
		 if(  (beanSearch.getDate_fabrication()!=null) )  
			    requette+="   AND   bean.date_fabrication = '"+beanSearch.getDate_fabrication()+"'        "; 
		 
		 
		 if(  (beanSearch.getDate_utilisation()!=null) )  
			    requette+="   AND   bean.date_utilisation = '"+beanSearch.getDate_utilisation()+"'        ";  
		 
		 
		 
		 if(  (beanSearch.getDate_peremption()!=null) )  
			    requette+="   AND   bean.date_peremption = '"+beanSearch.getDate_peremption()+"'        "; 
		 
		 
		 if(  (beanSearch.getSerie_bloque()!=null) )  
			    requette+="   AND   bean.serie_bloque = "+beanSearch.getSerie_bloque()+"        ";
		 
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getCondition_list_article()) ) 
			 requette+=""+beanSearch.getCondition_list_article();
		 
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getCondition_etat_serie()) ) 
			 requette+=""+beanSearch.getCondition_etat_serie();
		 
		 
		     requette+="   AND   bean.pk.depot.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"'"  ; 
		 
		 
		  
		 
		    // requette+=this.setSocieteEtabFetch(beanSearch, "bean.fk_etab_Bean", false);
		     
		     
		    
			 if( !StringUtils.isEmpty(beanSearch.getCondition_fifo_lifo()) ) 
				 requette+=""+beanSearch.getCondition_fifo_lifo();
		 
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
	     public List<MouvementSerieBean > doFindList_detailleDocumentLot(SerieArticletBean beanSearch) throws Exception {
	    	 
	    	 
	    	 Session session =  openSessionHibernate(sessionFactory);
		 		List<MouvementSerieBean>   lisf = new ArrayList<MouvementSerieBean>();
		 		try {
		 			
			     String requette="   SELECT  bean  FROM    MouvementSerieBean    bean    WHERE     1=1       ";
			 
				  if( !StringUtils.isEmpty(beanSearch.getCondition_list_article()) ) 
					   requette+=""+beanSearch.getCondition_list_article();
			 
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

		 
			
			
	public Boolean doSaveDocumentLot(SerieArticletBean beanSave) throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			//this.setBeanTrace(beanSave);
			session.save(beanSave);
			//this.saveTrace(beanSave);
			result = true;
			 commitTransaction(session);
		 } catch (Exception e) {  
			 result = false;
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
	
	
	public Boolean doSaveDocumentLot_choix(SerieArticletBean beanSave,List list_des_lots_for_select) throws Exception {
		boolean result  =  false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
 			
			 BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION); 
			 List <Code_barreBean> list_des_articles=(List<Code_barreBean>) getObjectValueModel("list_des_articles"  );
			 HashMap mapdd=ProcessUtil.getHashMap_code_bean(list_des_articles, "pk.code_barre");
			 Code_barreBean  beann=(Code_barreBean) mapdd.get(beanSave.getFkCode_barre().getPk().getCode_barre());
			
			if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier)){
			 List list_des_lots_for_select_modif =(List) getObjectValueModel("list_des_lots_for_select_modif" );
			 for (int h = 0; h < list_des_lots_for_select_modif.size(); h++) {
					SerieArticletBean  serBean = 	(SerieArticletBean) list_des_lots_for_select_modif.get(h);
					session.createQuery(" UPDATE  SerieArticletBean b  set  b.selected=null , b.serie_ordre=null " +
						  		" where          b.pk.num_serie='"+serBean.getPk().getNum_serie()+"'  " +
						  				" and    b.pk.depot.depot_id="+serBean.getPk().getDepot().getDepot_id()+" ").executeUpdate();
				}
			}
			/*****************************************************choi prix de lot************************************************************/
			HashMap  maplot= new HashMap();
			boolean lotchoisi=false;
			if(beanSave.getFkCode_barre().getPk().getAr_bean().getMode()!=null  &&  
					beanSave.getFkCode_barre().getPk().getAr_bean().getMode().getData_id().equals("pl")){
				int kio=0;
				for (int i = 0; i < list_des_lots_for_select.size(); i++) {
					SerieArticletBean  serBean = 	(SerieArticletBean) list_des_lots_for_select.get(i);
					if(serBean.getTo_check().equals("checked")){
						lotchoisi=true;
						String truU=(String) maplot.get(serBean.getNvente().getTarif_unit_vente());
						if(truU==null &&  kio==0){
							kio++;
							maplot.put(serBean.getNvente().getTarif_unit_vente(), "yes");
							continue;
						}
						if(truU==null &&  kio>0){
							throwNewException(" Les Lots choisis ont des prix différents ");	
						}
					}
				}
			} 
			if(!lotchoisi &&
					(beanSave.getFkCode_barre().getPk().getAr_bean().getMode()!=null  &&  
							beanSave.getFkCode_barre().getPk().getAr_bean().getMode().getData_id().equals("pl"))	
					){
				throwNewException(" Aucun prix de Lots choisis pour cet article  ");	
			}
			/*************************************************choi lot arbit ou par selection************************************************************/
			 
			boolean lotnat_choisi=false;
			if(beanSave.getFkCode_barre().getPk().getAr_bean().getChoix()!=null  &&  
					beanSave.getFkCode_barre().getPk().getAr_bean().getChoix().getData_id().equals("sel")){
				int kio=0;
				for (int i = 0; i < list_des_lots_for_select.size(); i++) {
					SerieArticletBean  serBean = 	(SerieArticletBean) list_des_lots_for_select.get(i);
					if(serBean.getTo_check().equals("checked")){
						lotnat_choisi=true;
					}
				}
			} 
			if(!lotnat_choisi &&
					
					(beanSave.getFkCode_barre().getPk().getAr_bean().getChoix()!=null  &&  
							beanSave.getFkCode_barre().getPk().getAr_bean().getChoix().getData_id().equals("sel"))
							
			){
				throwNewException(" Aucun Lots côchez pour le mode de selection  ");	
			}
			
			/**********************************************************update le mode du choix prix*********************************************************/
			 session.createQuery(" UPDATE  ArticleBean b  set  b.mode.data_id='"+beanSave.getFkCode_barre().getPk().getAr_bean().getMode().getData_id()+"'   " +
				  		" where          b.pk_article.ar_id='"+beann.getPk().getAr_bean().getPk_article().getAr_id()+"'  " +
				  				" and    b.pk_article.etabBean.pk_etab.etab_id='"+beann.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'" +
				  				" and    b.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beann.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'" +
				  						" ").executeUpdate();
			 /***********************************************************************************************************************/
			 
			 /**********************************************************update le mode de selection*********************************************************/
			 session.createQuery(" UPDATE  ArticleBean b  set  b.choix.data_id='"+beanSave.getFkCode_barre().getPk().getAr_bean().getChoix().getData_id()+"'   " +
				  		" where          b.pk_article.ar_id='"+beann.getPk().getAr_bean().getPk_article().getAr_id()+"'  " +
				  				" and    b.pk_article.etabBean.pk_etab.etab_id='"+beann.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'" +
				  				" and    b.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beann.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'" +
				  						" ").executeUpdate();
			 /***********************************************************************************************************************/
			 
			 
			for (int i = 0; i < list_des_lots_for_select.size(); i++) {
				SerieArticletBean  serBean = 	(SerieArticletBean) list_des_lots_for_select.get(i);
				if(serBean.getTo_check().equals("checked")){
		    session.createQuery("  UPDATE    SerieArticletBean b  set  b.selected='X' , b.serie_ordre="+serBean.getSerie_ordre()+" " +
					  		"       where    b.pk.num_serie='"+serBean.getPk().getNum_serie()+"'  " +
					  				" and    b.pk.depot.depot_id="+serBean.getPk().getDepot().getDepot_id()+" ").executeUpdate();
			if( beanSave.getFkCode_barre().getPk().getAr_bean().getMode()!=null                
				&& beanSave.getFkCode_barre().getPk().getAr_bean().getMode().getData_id().equals("pl"))
			       {
						TarificationBean  saveTarifLot = new TarificationBean();
						saveTarifLot.setDate_trf(BDateTime.getDateActuel());
						saveTarifLot.setFkCode_barre(serBean.getFkCode_barre());
						saveTarifLot.setCout(serBean.getVente().getCout());
						saveTarifLot.setTarif_lot(true);
						saveTarifLot.setTvaBean(serBean.getFkCode_barre().getPk().getAr_bean().getTva());
						saveTarifLot.setNum_serie(serBean.getPk().getNum_serie());
						saveTarifLot.setDepot(serBean.getPk().getDepot());
						saveTarifLot.setTarif_unit_vente(serBean.getNvente().getTarif_unit_vente());
						saveTarifLot.setGroupe(beanSave.getVente().getGroupe());
						daoNumSequentiel.getNumSeqSimple(saveTarifLot , "tarif_vente_id" , session );
						setBeanTrace(saveTarifLot);
						session.save(saveTarifLot);
					}
				}
			}
			
			 
			result = true;
			commitTransaction(session);
			 
			
		 } catch (Exception e) {  
			 result = false;
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
	
	
	public Boolean doSupp_choixLot(SerieArticletBean beanSave,List list_des_lots_for_select) throws Exception {
		boolean result  =  false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
		 
			 List <Code_barreBean> list_des_articles=(List<Code_barreBean>) getObjectValueModel("list_des_articles"  );
			 HashMap mapdd=ProcessUtil.getHashMap_code_bean(list_des_articles, "pk.code_barre");
			 Code_barreBean  beann=(Code_barreBean) mapdd.get(beanSave.getFkCode_barre().getPk().getCode_barre());
			 
			for (int i = 0; i < list_des_lots_for_select.size(); i++) {
				SerieArticletBean  serBean = 	(SerieArticletBean) list_des_lots_for_select.get(i);
				 
				 
					session.createQuery(" UPDATE  SerieArticletBean b  set  b.selected=null  , b.serie_ordre=null " +
					  		" where          b.pk.num_serie='"+serBean.getPk().getNum_serie()+"'  " +
					  				" and    b.pk.depot.depot_id="+serBean.getPk().getDepot().getDepot_id()+" ").executeUpdate();
				  
			}
			
			/**********************************************************update le mode du choix prix*********************************************************/
			 session.createQuery(" UPDATE  ArticleBean b  set  b.mode.data_id='pd'   " +
				  		" where          b.pk_article.ar_id='"+beann.getPk().getAr_bean().getPk_article().getAr_id()+"'  " +
				  				" and    b.pk_article.etabBean.pk_etab.etab_id='"+beann.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'" +
				  				" and    b.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beann.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'" +
				  						" ").executeUpdate();
			 /***********************************************************************************************************************/
			 
			 /**********************************************************update le mode de selection*********************************************************/
			 session.createQuery(" UPDATE  ArticleBean b  set  b.choix.data_id='arb'   " +
				  		" where          b.pk_article.ar_id='"+beann.getPk().getAr_bean().getPk_article().getAr_id()+"'  " +
				  				" and    b.pk_article.etabBean.pk_etab.etab_id='"+beann.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'" +
				  				" and    b.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beann.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'" +
				  						" ").executeUpdate();
			 /***********************************************************************************************************************/
			 
			 
				result = true;
				commitTransaction(session);
			 
			
		 } catch (Exception e) {  
			 result = false;
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
	
	
	public Boolean doUpdateDocumentLot(SerieArticletBean beanUpdate)  throws Exception { 
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			
			if(bs.getFct_id().equals(GenericActionBean.Fn_Etat))
				  session.createQuery(" UPDATE  SerieArticletBean b  set  b.serie_bloque="+beanUpdate.getSerie_bloque()+"  " +
					  		" where   b.pk.num_serie='"+beanUpdate.getPk().getNum_serie()+"' " +
					  		" AND     b.pk.depot.depot_id="+beanUpdate.getPk().getDepot().getDepot_id()+"   ").executeUpdate();
			else
			session.update(beanUpdate);
			//this.saveTrace(beanUpdate);
			result = true;
			 commitTransaction(session);
		 } catch (Exception e) {  
			 result = false;
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
	
	public Boolean doDeleteDocumentLot(SerieArticletBean beanDelete)  throws Exception  {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
	 
			session.delete(beanDelete);
	        this.saveTrace(beanDelete);
	    	result = true;
			 commitTransaction(session);
		 } catch (Exception e) {  
			 result = false;
		     if (sessionIsTrue(session)) 
		    	 rollbackTransaction(session) ;
		     throw e;  
		 } finally {  
			 session.close();  
		 }  
		return result;
	}
	
 
}
