package ERP.Process.Commerciale.Article.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.model.ClientArticleBean;
import ERP.Process.Commerciale.Article.model.LieuxArticleBean;
import ERP.Process.Commerciale.Article.model.PrixArticleBean;
import ERP.Process.Commerciale.Article.template.ArticleTemplate;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.model.Det_code_barre;
import ERP.Process.Commerciale.Degre_definition.model.Degre_definitionBean;
import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
import ERP.Process.Commerciale.GrpTarifPrimitiv.model.GrpTarifPrimitivBean;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Tarification.model.TarificationBean;
import ERP.Process.Commerciale.TarificationPrtvArticle.model.TarificationPrtvArticleBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
@Repository
public class ArticleDAO extends  GenericWeb    {
	
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
	public List<ArticleBean> doFindListArticle(ArticleBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List<ArticleBean>   lisf = new ArrayList<ArticleBean>();
		try {
			  BeanSession bs    =(BeanSession)getObjectValueModel(BEAN_SESSION);
		   String requette=" SELECT  b1   FROM    ArticleBean  b1     WHERE     1=1       ";
		 
			if( !StringUtils.isEmpty(beanSearch.getPk_article().getAr_id()) )                
				requette+="  AND  b1.pk_article.ar_id='"+beanSearch.getPk_article().getAr_id()+"' ";  
			
			if( !StringUtils.isEmpty(beanSearch.getArcodbar()) )                
				requette+="  AND  b1.arcodbar='"+beanSearch.getArcodbar()+"' ";  
			
			
			if( !StringUtils.isEmpty(beanSearch.getAr_libelle()) )            
				requette+="  AND  b1.ar_libelle  LIKE  '%"+beanSearch.getAr_libelle()+"%'   ";  
			
			if( !StringUtils.isEmpty(beanSearch.getFam_art().getFam_id()) )   
				requette+="  AND  b1.fam_art.fam_id='"+beanSearch.getFam_art().getFam_id()+"' ";
			
			if(!StringUtils.isEmpty(beanSearch.getBean_sitcod().getData_id())) 
				requette+="     AND  b1.bean_sitcod.data_id='"+beanSearch.getBean_sitcod().getData_id()+"' ";
			
			if( !StringUtils.isEmpty(beanSearch.getCondition_personnalised_list_degre()) )  
				   
				requette+="  AND  b1.pk_article.ar_id   "+beanSearch.getCondition_personnalised_list_degre()+"       " ;
			
				
			if( !StringUtils.isEmpty(beanSearch.getCondition_personnalised_list()) )  
				   
				requette+="  AND  b1.pk_article.ar_id   "+beanSearch.getCondition_personnalised_list()+"       " +
						"     ";
			
			//if( !StringUtils.isEmpty(beanSearch.getPk_article().getEtabBean().getPk_etab().getEtab_id()) )   
				requette+="  AND  b1.pk_article.etabBean.pk_etab.etab_id='"+bs.getEtab_id()+"' ";
			
			//if( !StringUtils.isEmpty(beanSearch.getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()) )   
				requette+="  AND  b1.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+bs.getSoc_id()+"' ";
		 
			
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
	public List<LieuxArticleBean> doFindLieuxArcticle(LieuxArticleBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List<LieuxArticleBean>   lisf = new ArrayList<LieuxArticleBean>();
		try {
		    String requette="  select  bean   FROM    LieuxArticleBean  bean    WHERE   1=1  " ;
		    
		    if(!StringUtils.isEmpty(beanSearch.getPk().getRef().getPk().getCode_barre()))
		    	 requette+="         AND    bean.pk.ref.pk.code_barre='"+beanSearch.getPk().getRef().getPk().getCode_barre()+"'      ";
		    
		    if( beanSearch.getPk().getLieu().getDepot_id()!=null)
		    	 requette+="         AND    bean.pk.lieu.depot_id="+beanSearch.getPk().getLieu().getDepot_id()+"      ";
		    
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
	public List<ClientArticleBean> doFindProduitClient(ClientArticleBean beanSearch) throws Exception {
		
		Session session =  openSessionHibernate(sessionFactory);
		List<ClientArticleBean>   lisf = new ArrayList<ClientArticleBean>();
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		    String requette=" select  bean   FROM    ClientArticleBean  bean    WHERE    " +
		    		"     1=1        ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getPk().getRef().getPk().getCode_barre()))
		    	 requette+="         AND    bean.pk.ref.pk.code_barre='"+beanSearch.getPk().getRef().getPk().getCode_barre()+"'      ";
		    
		    if( !StringUtils.isEmpty(beanSearch.getPk().getClient().getClt_id()))
		    	 requette+="         AND    bean.pk.client.clt_id='"+beanSearch.getPk().getClient().getClt_id()+"'      ";
		    
		    if(!StringUtils.isEmpty(beanSearch.getPk().getRef().getPk().getCode_barre()))
		    	 requette+="         AND    bean.pk.ref.pk.code_barre='"+beanSearch.getPk().getRef().getPk().getCode_barre()+"'      ";
		    
		    
		    if(!StringUtils.isEmpty(beanSearch.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()))
		    	 requette+="    AND    bean.pk.ref.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id =  " +
	  		     " '"+beanSearch.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'   "; 
		    
	        if(!StringUtils.isEmpty(beanSearch.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()))
		    	 requette+="    AND    bean.pk.ref.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id =  " +
	  		     " '"+beanSearch.getPk().getRef().getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'   "; 
		    
		    
		                    
                 
                 
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
	public List<PrixArticleBean> doFindDate_MAX_PrixArcticle(ArticleBean beanSearch) throws Exception {
		 
		 Session session =  openSessionHibernate(sessionFactory);
			List<PrixArticleBean>   lisf = new ArrayList<PrixArticleBean>();
			try {
		    String   requette=" select   bean     FROM    PrixArticleBean  bean    WHERE       " +
		    		"                         bean.pk_prix_ar.date_prix  IN (  select  MAX(g.pk_prix_ar.date_prix)   " +
		    		"                         from    PrixArticleBean g    where  g.pk_prix_ar.ar_bean.ar_id=bean.pk_prix_ar.ar_bean.ar_id)       ";
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
	
	public Boolean doSaveArticle(ArticleBean beanSave)throws Exception  {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			this.setBeanModeUsrDate(beanSave);
			BeanSession bs      = (BeanSession)getObjectValueModel(BEAN_SESSION);
			beanSave.getPk_article().getEtabBean().getPk_etab().setSoc_bean(bs.getSociete());
			daoNumSequentiel.getNumSeqSimple(beanSave,"pk_article.ar_id");
			 
			session.save(beanSave);
			if(  beanSave.getBean_artyp().getData_id().equals("US") || 
			     beanSave.getBean_artyp().getData_id().equals("UA") ){
				
				CaracteristiqueBean   bCar= new CaracteristiqueBean();
				bCar.setCarac_id("Carc$");
				DetailCaracteristiqueBean  dBean= new DetailCaracteristiqueBean();
				dBean.getPk_det_carac().setDet_carac_id(beanSave.getBean_artyp().getData_id());
				dBean.getPk_det_carac().setBean_carac(bCar);
				 
				
				Degre_definitionBean degBean = new Degre_definitionBean();
				degBean.getPkBean().setArt_Bean(beanSave);
				degBean.getPkBean().setCarac_Bean(bCar);
				this.setBeanModeUsrDate(degBean);
				session.save(degBean);
				
				
				 
				Code_barreBean   bCode_barreBean =new Code_barreBean();
				bCode_barreBean.getPk().setAr_bean(beanSave);
				
				if( beanSave.getBean_artyp().getData_id().equals("US")  ){
				   bCode_barreBean.getPk().setCode_barre(beanSave.getArcodbar());
				}
				if( beanSave.getBean_artyp().getData_id().equals("UA")  ){
					bCode_barreBean.getPk().setCode_barre(beanSave.getPk_article().getAr_id());	
					beanSave.setArcodbar(beanSave.getPk_article().getAr_id());
				}
				
				bCode_barreBean.setDesignation(beanSave.getAr_libelle());//(DESIGNATION_DATA);
				bCode_barreBean.setDesignation_libelle(beanSave.getAr_libelle());//(DESIGNATION_DATA);
				this.setBeanModeUsrDate(bCode_barreBean);
				session.save(bCode_barreBean);
				
				
				Det_code_barre  det_code_barre=  new Det_code_barre();
				det_code_barre.getPk_det_codBare().setBean_cod_bar(bCode_barreBean);
				det_code_barre.getPk_det_codBare().setBean_det_carac(dBean);
				this.setBeanModeUsrDate(det_code_barre);
				det_code_barre.setNum_ligne(new Integer(0));
				session.save(det_code_barre);
				if(beanSave.getPrix_achat()!=null) {
					TarificationPrtvArticleBean beanSaveTarifAchat  = new TarificationPrtvArticleBean();
					daoNumSequentiel.getNumSeqSimple(beanSaveTarifAchat,"tarif_prim_id");
					beanSaveTarifAchat.setFkCode_barre(bCode_barreBean);
					beanSaveTarifAchat.setTarif_unit_article(beanSave.getPrix_achat());
					beanSaveTarifAchat.setTarif_unit_ttc(beanSave.getPrix_achatttc());
					beanSaveTarifAchat.setDevise(bs.getSociete().getDevise());
					beanSaveTarifAchat.setTvaBean(beanSave.getTva());
					beanSaveTarifAchat.setMode_cal(beanSave.getBean_mode_cal());
					beanSaveTarifAchat.setDate_prim_trf(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()) );
					beanSaveTarifAchat.getGroupe().setGrp_prim_trf_id( Integer.parseInt(GROUPE_TARIF_ACHAT_STANDAR)   );
					setBeanTrace(beanSaveTarifAchat);
					session.save(beanSaveTarifAchat);
				}
				
				if(beanSave.getPrix_vente()!=null) {
					TarificationBean beanSaveTarifVente = new TarificationBean();
					daoNumSequentiel.getNumSeqSimple(beanSaveTarifVente,"tarif_vente_id");
					beanSaveTarifVente.setFkCode_barre(bCode_barreBean);
					beanSaveTarifVente.setTarif_unit_vente(beanSave.getPrix_vente());
					beanSaveTarifVente.setTarif_unit_vente_tt(beanSave.getPrix_ventettc());
					beanSaveTarifVente.setDevise(bs.getSociete().getDevise());
					beanSaveTarifVente.setTvaBean(beanSave.getTva());
					beanSaveTarifVente.setBean_cal(beanSave.getBean_mode_cal());
					beanSaveTarifVente.setDate_trf(ProcessDate.convert_String_to_Date(BDateTime.getDateActuel_system()) );
					beanSaveTarifVente.getGroupe().setType_trf_id(  Integer.parseInt(GROUPE_TARIF_VENTE_PUBLIC)   );
					setBeanTrace(beanSaveTarifVente);
					session.save(beanSaveTarifVente);
				}
				
//				if(beanSave.getDepot_id()!=null) {
//					LieuxArticleBean lieuxArticleBean = new LieuxArticleBean();
//					lieuxArticleBean.getPk().setRef(bCode_barreBean);
//					List listDepotStockageInit=(List) getObjectValueModel("listDepotStockageInit" );
//					
//					HashMap  mapDepo=ProcessUtil.getHashMap_code_bean(listDepotStockageInit, "depot_id");
//					 
//					lieuxArticleBean.getPk().setLieu((DepotStockageBean) mapDepo.get(String.valueOf(beanSave.getDepot_id())));
//					  
//					setBeanTrace(lieuxArticleBean);
//					session.save(lieuxArticleBean);
//				}
				
				if(beanSave.getClt_id()!=null) {
					ClientArticleBean clientArticleBean = new ClientArticleBean();
					List listClientInit=(List) getObjectValueModel("listClientInit"  );
					HashMap  mapClt=ProcessUtil.getHashMap_code_bean(listClientInit, "clt_id");
					clientArticleBean.getPk().setRef(bCode_barreBean);
					clientArticleBean.getPk().setClient( (ClientBean) mapClt.get(beanSave.getClt_id()) );
					setBeanTrace(clientArticleBean);
					session.save(clientArticleBean);
				}
			}
				
			//this.saveTraceVersion1(beanSave, ArticleTemplate.Mapfieldtrace, ArticleTemplate.id_entite_Article,ArticleTemplate.entites);
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
	
	
	
	public Boolean doAffect_client_article(  ClientArticleBean detailBean ,List lisf) throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			for (int i = 0; i < lisf.size(); i++) {
				ClientArticleBean    dzrfzer = (ClientArticleBean) lisf.get(i);
				if(dzrfzer.getTo_check().length()>0)
				session.saveOrUpdate(dzrfzer);
				else
				session.delete(dzrfzer);
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
	
	
	
	public Boolean doAffectLieux(  List lisf) throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			for (int i = 0; i < lisf.size(); i++) {
				LieuxArticleBean    dzrfzer = (LieuxArticleBean) lisf.get(i);
				if(dzrfzer.getTo_check().length()>0)
				session.saveOrUpdate(dzrfzer);
				else
				session.delete(dzrfzer);
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
	
	
	 
	public Boolean doUpdateArticle(ArticleBean beanUpdate) throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			String sssss=(String) getObjectValueModel("moda");
			this.setUpdateValueFieldTraceOject(beanUpdate);
			if(StringUtils.isNotEmpty(sssss) && sssss.equals("tout")){
			ArticleBean Ar	= (ArticleBean) getObjectValueModel(ORIGINAL_FORM_BEAN);
			beanUpdate.setBean_sitcod(Ar.getBean_sitcod());
			BeanSession bs      = (BeanSession)getObjectValueModel(BEAN_SESSION);
			beanUpdate.getPk_article().getEtabBean().getPk_etab().setSoc_bean(bs.getSociete());
			session.update(beanUpdate); 
			}else{
				 session.createQuery("  update  ArticleBean  b  set  b.bean_sitcod.data_id='"+beanUpdate.getBean_sitcod().getData_id()+"'   " +
						
						"                where    b.pk_article.ar_id='"+beanUpdate.getPk_article().getAr_id()+"'  " +
								"           and   b.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beanUpdate.getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"' " +
										"   and   b.pk_article.etabBean.pk_etab.etab_id='"+beanUpdate.getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  ").executeUpdate();
			}
			this.saveTraceVersion1(beanUpdate, ArticleTemplate.Mapfieldtrace, ArticleTemplate.id_entite_Article,ArticleTemplate.entites);
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
	
	public Boolean doDeleteArticle(ArticleBean beanDelete) throws Exception {
		boolean result = false;
		Session session =  openSessionHibernate(sessionFactory);
		try {
			this.setBeanModeUsrDate(beanDelete);
			List <Code_barreBean> list_codBarr = session.createQuery(" select  bar  from  Code_barreBean  bar " +
					"                   where     bar.pk.code_barre='"+beanDelete.getArcodbar()+"'   AND   bar.pk.ar_bean.pk_article.ar_id='"+beanDelete.getPk_article().getAr_id()+"'   " +
					"                       and   bar.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+beanDelete.getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'   " +
					"                       and   bar.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beanDelete.getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  ").list();
			String faza="";
			for(Code_barreBean nbe:list_codBarr){
				faza=faza+"'"+nbe.getPk().getCode_barre()+"',";
			} 
			
			 
			if(faza.length()>0){ 
				faza=faza.substring(0, faza.length()-1);
				
				 session.createQuery("  delete   from   Degre_definitionBean  barx " +
							"                         where         " +
							"                                   barx.pkBean.art_Bean.pk_article.ar_id='"+beanDelete.getPk_article().getAr_id()+"'  " +
							"                             and   barx.pkBean.art_Bean.pk_article.etabBean.pk_etab.etab_id='"+beanDelete.getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  " +
							"                             and   barx.pkBean.art_Bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beanDelete.getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  " +
							"                                                           " ).executeUpdate();
				 
				 
				 
				 session.createQuery("  delete   from   Det_code_barre  det " + 
					"                         where     det.pk_det_codBare.bean_cod_bar.pk.code_barre in ("+faza+")   " +
					"                             and   det.pk_det_codBare.bean_cod_bar.pk.ar_bean.pk_article.ar_id='"+beanDelete.getPk_article().getAr_id()+"'  " +
					"                             and   det.pk_det_codBare.bean_cod_bar.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+beanDelete.getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  " +
					"                             and   det.pk_det_codBare.bean_cod_bar.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beanDelete.getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  " +
					"                                                           " ).executeUpdate();
				  
				  
				 session.createQuery("  delete   from   Code_barreBean  bar " +
					"                         where     bar.pk.code_barre in ("+faza+")   " +
					"                             and   bar.pk.ar_bean.pk_article.ar_id='"+beanDelete.getPk_article().getAr_id()+"'  " +
					"                             and   bar.pk.ar_bean.pk_article.etabBean.pk_etab.etab_id='"+beanDelete.getPk_article().getEtabBean().getPk_etab().getEtab_id()+"'  " +
					"                             and   bar.pk.ar_bean.pk_article.etabBean.pk_etab.soc_bean.soc_id='"+beanDelete.getPk_article().getEtabBean().getPk_etab().getSoc_bean().getSoc_id()+"'  " +
					"                                                           " ).executeUpdate();
				 
			}
			session.flush();
			session.clear(); 
			session.delete(beanDelete);
			 
			//this.saveTraceVersion1(beanDelete, ArticleTemplate.Mapfieldtrace, ArticleTemplate.id_entite_Article,ArticleTemplate.entites);
			result= true;
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
