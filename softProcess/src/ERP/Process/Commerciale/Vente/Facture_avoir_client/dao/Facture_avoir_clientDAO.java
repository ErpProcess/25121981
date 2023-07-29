package ERP.Process.Commerciale.Vente.Facture_avoir_client.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Vente.Facture_avoir_client.model.Facture_avoir_clientBean;
import ERP.Process.Commerciale.Vente.Facture_avoir_client.template.Facture_avoir_clientTemplate;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Detail_mvt_vente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.MvtVente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.DetRetourVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
 
@Repository
public class Facture_avoir_clientDAO extends  GenericWeb    {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Facture_avoir_clientBean> doFindListFactureAvoir(Facture_avoir_clientBean beanSearch) throws Exception {
	 Session session =  openSessionHibernate(sessionFactory); 
	 List list_data= new ArrayList();
	try{
		 String requette=" select  bean   FROM    Facture_avoir_clientBean    bean    WHERE     1=1       ";
		 
		 if( !StringUtils.isEmpty(beanSearch.getFactclient().getFact_clt_id()) )  
			    requette+="   AND   bean.factclient.fact_clt_id = '"+beanSearch.getFactclient().getFact_clt_id()+"'        ";   
		 
		 if( !StringUtils.isEmpty(beanSearch.getAvoir_id()) )  
			    requette+="   AND   bean.avoir_id = '"+beanSearch.getAvoir_id()+"'        ";   
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getFactclient().getClient().getClt_id()) )  
			    requette+="   AND   bean.factclient.client.clt_id = '"+beanSearch.getFactclient().getClient().getClt_id()+"'        ";   
		 
		 
		 
		 if( beanSearch.getAvoir_date()!=null )  
			    requette+="   AND   bean.avoir_date = '"+beanSearch.getAvoir_date()+"'        ";  
		 
		 if( !StringUtils.isEmpty(beanSearch.getCondition_select_mode()) )  
			    requette+="   "+beanSearch.getCondition_select_mode();  
		 
		 if( beanSearch.getModeBean().getFct_id()!=null )  
			  requette+="   AND  bean.modeBean.fct_id  = "+beanSearch.getModeBean().getFct_id()+"        ";  
		 
	 
		 
	 
		  
		  
		  
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
	
	
	@SuppressWarnings("unchecked")
	public List<Facture_clientBean> doFindListFactureclient(Facture_clientBean beanSearch) throws Exception {
	 Session session =  openSessionHibernate(sessionFactory); 
	 List list_data= new ArrayList();
	try{
		 String requette=" select  bean   FROM    Facture_clientBean    bean    WHERE     1=1       ";
		 if( !StringUtils.isEmpty(beanSearch.getFact_clt_id()) )  
			    requette+="   AND   bean.fact_clt_id = '"+beanSearch.getFact_clt_id()+"'        ";    
		 if( !StringUtils.isEmpty(beanSearch.getClient().getClt_id()) )  
			    requette+="   AND   bean.client.clt_id = '"+beanSearch.getClient().getClt_id()+"'        ";   
		 
		 
			    requette+="   AND   bean.typefact.type_fact_id  != 2    "; 
		 
		 
		 if( beanSearch.getFact_date()!=null )  
			    requette+="   AND   bean.fact_date = '"+beanSearch.getFact_date()+"'        ";  
		 
		 if( !StringUtils.isEmpty(beanSearch.getCondition_select_mode()) )  
			    requette+="   "+beanSearch.getCondition_select_mode();  
		 
		 if( beanSearch.getModeBean().getFct_id()!=null )  
			  requette+="     AND  bean.modeBean.fct_id  = "+beanSearch.getModeBean().getFct_id()+"        ";  
		 
	 
		 
		  requette +=this.setSocieteEtabFetch(beanSearch,"bean.etablissment", false);
		  
		  
		  
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
	
	
	@SuppressWarnings("unchecked")
	public List<Facture_avoir_clientBean> doFindListFacture_avoir_client(Facture_clientBean beanSearch) throws Exception {
	Session session =  openSessionHibernate(sessionFactory);
	List list_data= new ArrayList();
	try{
			      String requette=" select  bean   FROM    Facture_avoir_clientBean    bean    WHERE     1=1       ";
			      
			      if(! beanSearch.getSelect_many_facture().equals("")){
			    	  requette+="       "+beanSearch.getSelect_many_facture();
			      }
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
	public Boolean doSaveFacture_avoir_client(Facture_avoir_clientBean beanSave) throws Exception {
		Session session =  openSessionHibernate(sessionFactory);
	     boolean result=false; 
		try {
			     BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			    
			     List <DetRetourVenteBean >  det_list_avoirfacture =   (List)  getObjectValueModel( Facture_avoir_clientTemplate.LIST_RETOURVENTE_RESULTAT);
			     Double avance_montant_vente=new Double(0);
			     HashMap  map_article= new HashMap();
			     HashMap  map_detail_codBarre= new HashMap();
			     
			    	 
			    	 for (DetRetourVenteBean beand:det_list_avoirfacture) {
			    		 String keyString  =    beand.getPk().getDetv().getPk().getFkcode_barre().getPk().getCode_barre()+"�"+
			    		                        beand.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"�"+
			    		                        beand.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"�"+
			    		                        beand.getPk().getDetv().getPk().getFkcode_barre().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"�";
			    		List list= (List) map_article.get(keyString);
			    		if(list==null)list= new ArrayList();
			    		list.add(beand);
			    		map_article.put(keyString, list);
			    		if(map_detail_codBarre.get(keyString)==null){
			    			map_detail_codBarre.put(keyString, beand.getPk().getDetv().getPk().getFkcode_barre());
			    		}
			    		
			    	 }
			     
			     
			     
			     Set mapio_map_article=map_article.keySet();
			     List listMvt_Vente= new ArrayList();
			     for (Iterator itera = mapio_map_article.iterator(); itera.hasNext();) {
					 String article = (String) itera.next();
					
					 List listMvt=(List) map_article.get(article);
					 MvtVente_articleBean  mvt_article= new MvtVente_articleBean();
					 
					 Code_barreBean  fkcode_barre=(Code_barreBean) map_detail_codBarre.get(article);
					 mvt_article.setFkcode_barre(fkcode_barre);
					 
					 Double  quantiteGen	= new Double(0);
					 Double  mnt_ht		    = new Double(0);
					 Double  mnt_tva	    = new Double(0);
					 List list_detail_mvt_vente= new ArrayList();
					 TVABean  tvaBean	= new TVABean();
					 for (int i = 0; i < listMvt.size(); i++) {
						 DetRetourVenteBean detProVente = (DetRetourVenteBean) listMvt.get(i);
						 tvaBean=detProVente.getPk().getDetv().getTarif().getTvaBean();
						 
						 Detail_mvt_vente_articleBean detMvt = new Detail_mvt_vente_articleBean();
						
						 detMvt.getPk().getMvt_vente().setMvt_vente_id("");
						 detMvt.getPk().setVente(detProVente.getPk().getDetv().getPk().getVente());
						 detMvt.getPk().setDocument_com_id(detProVente.getPk().getR_vente().getRet_vente_id()) ;
						 detMvt.getPk().getNat_mvt().setNature_mvt_id("r-ve");
						 detMvt.setTarif(detProVente.getPk().getDetv().getTarif());
						 
						 detMvt.getPk().setFkcode_barre(detProVente.getPk().getDetv().getPk().getFkcode_barre());
						 detMvt.setQuantite(detProVente.getQuantite_retourne());
						 detMvt.setMontant_ht_vente(detProVente.getMontant_ht_retour());
						 detMvt.setMontant_tva_vente(detProVente.getMontant_tva_retour());
						 detMvt.setObservation(detProVente.getObservation());
						 quantiteGen=ProcessNumber.addition(quantiteGen, detProVente.getQuantite_retourne());
						 mnt_ht=ProcessNumber.addition(mnt_ht, detProVente.getMontant_ht_retour());
						 mnt_tva=ProcessNumber.addition(mnt_tva, detProVente.getMontant_tva_retour());
						 list_detail_mvt_vente.add(detMvt);
					}
					mvt_article.setTvaBean(tvaBean);
					mvt_article.setQuantite(quantiteGen);
					mvt_article.setMontant_ht_vente(mnt_ht);
					mvt_article.setMontant_tva_vente(mnt_tva);
					mvt_article.setList_detail_mvt_vente(list_detail_mvt_vente);
					listMvt_Vente.add(mvt_article);
				 }
			     
                  
			     
			     List<Det_Fact_ClientBean> liste_detaille_facture= new ArrayList<Det_Fact_ClientBean>();
			     
			     for (int i = 0; i < listMvt_Vente.size(); i++) {
			    	 
			    	 MvtVente_articleBean  mvt_article= (MvtVente_articleBean) listMvt_Vente.get(i);
			    	 Det_Fact_ClientBean  beanDetaille= new Det_Fact_ClientBean();
			    	 beanDetaille.getPk().setMvtVente(mvt_article);
			    	 beanDetaille.getPk().setFkcode_barre(mvt_article.getFkcode_barre());
			    	 beanDetaille.setQuantite(mvt_article.getQuantite());
			    	 Double mntht=ProcessFormatNbr.FormatDouble_Troischiffre(mvt_article.getMontant_ht_vente());
			    	 Double mnttva=ProcessFormatNbr.FormatDouble_Troischiffre(mvt_article.getMontant_tva_vente());
			    	 Double tarif_unit_vente=ProcessNumber.DIVISION(mntht, mvt_article.getQuantite());
			    	 beanDetaille.setMontant_ht_vente(mntht);
			    	 beanDetaille.setMontant_tva_vente(mnttva);
			    	 beanDetaille.setTarif_unit_vente(tarif_unit_vente);
			    	 beanDetaille.setTvaBean(mvt_article.getTvaBean());
			    	 beanDetaille.setObservation("");
			    	 liste_detaille_facture.add(beanDetaille);
				 }
			     	Facture_clientBean newBean = new Facture_clientBean();
			     	newBean.setAvance_montant_vente(new Double(0));
			     	newBean.setFact_date(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()));
			     	newBean.setClient(beanSave.getFactclient().getClient());
			        setBeanTrace(newBean);
			        newBean.getTypefact().setType_fact_id(new Integer(2));
					daoNumSequentiel.getNumSeqSimple(newBean,"fact_clt_id",session,"AV");
					session.save(newBean);
					beanSave.setAvoir_id(newBean.getFact_clt_id());
					
					setBeanTrace(beanSave);
					session.save(beanSave);
					//saveTrace(beanSave);
				 
					boolean result_detaille = false;
					for (int i = 0; i < liste_detaille_facture.size(); i++) {
						Det_Fact_ClientBean detBean=(Det_Fact_ClientBean) liste_detaille_facture.get(i);
						
						if( detBean.getQuantite()==null) { continue; }
						if( detBean.getQuantite()==0 ||  detBean.getQuantite()<0) { continue;}
						
						MvtVente_articleBean  mvt_vente = detBean.getPk().getMvtVente();
						daoNumSequentiel.getNumSeqSimple(mvt_vente,"mvt_vente_id",session);
						//mvt_vente.setFact(beanSave);
						//mvt_vente.setTypef(beanSave.getTypefact());
						setBeanTrace(mvt_vente);
						session.save(mvt_vente);
						List lisdet=mvt_vente.getList_detail_mvt_vente();
						for (int j = 0; j < lisdet.size(); j++) {
							Detail_mvt_vente_articleBean  bDetail_mvt_vente = (Detail_mvt_vente_articleBean) lisdet.get(j);
							bDetail_mvt_vente.getPk().setMvt_vente(mvt_vente);
							session.save(bDetail_mvt_vente);
							session.createQuery( " UPDATE  ProcedureVenteBean b  set  " +
									"              b.modeBean.fct_id="+GenericActionBean.Fn_Facturer+"   " +
											"      where   b.vente_id='"+bDetail_mvt_vente.getPk().getVente().getVente_id()+"' ").executeUpdate();
						}
						detBean.getPk().setFactclient(newBean);
						detBean.getPk().setMvtVente(mvt_vente);
						session.save(detBean);
						result_detaille=true;
					}
					
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
	public Boolean doUpdateFacture_avoir_client(Facture_clientBean beanUpdate)  throws Exception { 
Session session =  openSessionHibernate(sessionFactory);
	    boolean result=false; 
		try {
			setIdBean((Facture_clientBean) getObjectValueModel(FORM_BEAN), beanUpdate, Facture_avoir_clientTemplate.id_entite);
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
	public Boolean doDeleteFacture_avoir_client(Facture_clientBean beanDelete)  throws Exception  {
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
