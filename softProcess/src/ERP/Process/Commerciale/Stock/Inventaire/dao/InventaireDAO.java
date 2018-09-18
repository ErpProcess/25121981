package ERP.Process.Commerciale.Stock.Inventaire.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Det_reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.model.Reception_achatBean;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Stock.Inventaire.model.DetInventaireBean;
import ERP.Process.Commerciale.Stock.Inventaire.model.InventaireBean;
import ERP.Process.Commerciale.Stock.Inventaire.template.InventaireTemplate;
import ERP.Process.Commerciale.Stock.MouvementStock.model.MouvementSerieBean;
import ERP.Process.Commerciale.Stock.Stock_article.dao.Stock_articleDAO;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
@Repository
public class InventaireDAO extends  GenericWeb    {
	
	
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	private Stock_articleDAO daoStock_article;
	@Autowired
	public void setDaoStock_article(Stock_articleDAO daoStock_article) {
		this.daoStock_article = daoStock_article;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<InventaireBean> doFindListInventaire(InventaireBean beanSearch) throws Exception {
		    String requette=" select  bean   FROM    InventaireBean    bean    WHERE     1=1       ";
		    
		    
		 if( !StringUtils.isEmpty(beanSearch.getPk().getInv_id()) )  
			    requette+="   AND   bean.pk.inv_id = '"+beanSearch.getPk().getInv_id()+"'        "; 
		 
		 if( !StringUtils.isEmpty(beanSearch.getInv_libellle()) )  
			    requette+="   AND   bean.inv_libellle = '"+beanSearch.getInv_libellle()+"'        ";
		 
		 if(  beanSearch.getPk().getInv_date()!=null )  
			    requette+="   AND   bean.inv_date = "+beanSearch.getPk().getInv_date()+"        ";    
		 
		 if(  beanSearch.getTypeInventaire().getType_inv_id()!=null   )  
			    requette+="   AND   bean.typeInventaire.type_inv_id = "+beanSearch.getTypeInventaire().getType_inv_id()+"        ";
		 
		 
		 if( !StringUtils.isEmpty(beanSearch.getCondition_etat_entite() )   )  
			    requette+="    "+beanSearch.getCondition_etat_entite() +"        ";
		 
		 
	       return   hibernateTemplate.find(requette);
	}
	
	 
	
	 
	
	 
	public List<DetInventaireBean> doFindList_det_Inventaire(InventaireBean beanToSelect) throws Exception {
		
		    String requette="   SELECT  bean   FROM  DetInventaireBean    bean    WHERE     1=1        ";
		    
		    if(  beanToSelect.getPk().getInv_date()!=null )  
			    requette+="   AND   bean.pk.inventaire.pk.inv_date ='"+ProcessDate.getStringFormatDate(beanToSelect.getPk().getInv_date())+"'        "; 
		    
		    if( beanToSelect.getPk().getDepot_stocks().getDepot_id()!=null )  
			    requette+="   AND   bean.pk.inventaire.pk.depot_stocks.depot_id = "+beanToSelect.getPk().getDepot_stocks().getDepot_id()+"        "; 
			    
		    /*if( beanToSelect.getModeBean().getFct_id()!=null )  
		    	 
			    requette+="   AND   bean.pk.inventaire.pk.modeBean.fct_id = "+beanToSelect.getModeBean().getFct_id()+"        ";*/
		    
		    
		   if(  !StringUtils.isEmpty(beanToSelect.getCondition_Select_dateMax_Inventaire())  )  
			     requette+="      "+beanToSelect.getCondition_Select_dateMax_Inventaire() ;
		   
		   
					return   hibernateTemplate.find(requette);
	}
	
	public Boolean doSaveInventaire(InventaireBean beanSave) throws Exception {
	     boolean result=false; 
		try {
			this.setBeanTrace(beanSave);
			this.hibernateTemplate.save(beanSave);
			this.saveTraceVersion1(beanSave, InventaireTemplate.MapfieldBean, InventaireTemplate.id_entite_composite, InventaireTemplate.entites);
			
			List  ListInve=(List) getObjectValueModel(InventaireTemplate.LIST_DATA_FOR_INVENTAIRE);
			List list_detail_trace= new ArrayList();
			for (int i = 0; i < ListInve.size(); i++) {
				DetInventaireBean dBean = (DetInventaireBean) ListInve.get(i);
				if(StringUtils.isEmpty(dBean.getTo_check())) continue;
				dBean.getPk().setInventaire(beanSave);
				dBean.getPk().getNature_mvt().setNature_mvt_id("iv");
				this.hibernateTemplate.save(dBean);
				list_detail_trace.add(dBean);
			}
			
			this.saveTraceVersion_Master_detailles(list_detail_trace, InventaireTemplate.MapfieldBean_detaille, InventaireTemplate.id_entite_detail, InventaireTemplate.entites_detaille);
			result=true;
		} catch (Exception ex) {
			result=false;
			this.hibernateTemplate.clear();
			throw ex;
		}
	    return result;
	}
	
	 public Boolean doValiderTransactionInventaire(InventaireBean beanUpd) throws Exception {
			boolean result = false;
			/*try {
				BeanSession bs =(BeanSession)getObjectValueModel(SESSION_BEAN);
				InventaireBean   beanUpdate=(InventaireBean) getObjectValueModel(FORM_BEAN);
				this.setUpdateValueFieldTraceOject(beanUpdate);
				this.hibernateTemplate.saveOrUpdate(beanUpdate);
				//this.saveTraceVersion1(beanUpdate,InventaireTemplate.MapfieldBean, InventaireTemplate.id_entite_composite, InventaireTemplate.entites);
				 
				     List listOfmyData=(List) getObjectValueModel(InventaireTemplate.LIST_DATA_FOR_INVENTAIRE);
				 
					for (int i = 0; i < listOfmyData.size(); i++) {
						
						DetInventaireBean detBean=(DetInventaireBean) listOfmyData.get(i);
						
						detBean.getPk().setInventaire(beanUpdate);
						detBean.getPk().getNature_mvt().setNature_mvt_id("iv");
						MouvementSerieBean  mStockBean = new MouvementSerieBean();
						mStockBean.getDepot().setDepot_id(beanUpdate.getPk().getDepot_stocks().getDepot_id());
						mStockBean.getPk().setDate_mvt_stock(beanUpdate.getPk().getInv_date());
						mStockBean.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
						mStockBean.getPk().setDocument_com_id(beanUpdate.getPk().getInv_id());
						mStockBean.getNat_mvtcom().setNature_mvt_id("iv");
						mStockBean.setMontant_tva(detBean.getPk().getMontant_tva());
						mStockBean.setMontant_ht(detBean.getPk().getMontant_ht());
						mStockBean.setQuantite(new Double(detBean.getPk().getQuantite()));
						mStockBean.getPk().getLot().setNum_lot(detBean.getPk().getLot().getNum_lot());
						mStockBean.getEta().getPk_etab().setEtab_id(bs.getEtab_id());
						mStockBean.getEta().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
						this.hibernateTemplate.save(mStockBean);
						
						 
						
					 
							 Stock_articleBean    stock = new Stock_articleBean();
							 stock.getPk().setDate_stock(beanUpdate.getPk().getInv_date());
							 stock.getPk().setFkCode_barre(detBean.getPk().getFkCode_barre());
							 stock.getPk().getDepot().setDepot_id(beanUpdate.getPk().getDepot_stocks().getDepot_id());
							 stock.getPk().setLot(detBean.getPk().getLot());
							 
							 stock.setQuantite_init(detBean.getPk().getQuantite());
							 stock.setSolde_stock(detBean.getPk().getQuantite());
							 
							 stock.setDate_trf(detBean.getDate_trf());
							 stock.getTyp_trfBean().setType_trf_id(detBean.getTyp_trfBean().getType_trf_id());
							 stock.setTvaBean(detBean.getTvaBean());
							 stock.setPrix_unit_achat(detBean.getPrix_unit_achat());
							 stock.setPrix_unit_vente(detBean.getPrix_unit_vente());
							 stock.setMnt_tva_entre(detBean.getPk().getMontant_tva());
							 stock.setMnt_ht_entre(detBean.getPk().getMontant_ht());
							 
							 
							 stock.getEta().getPk_etab().setEtab_id(bs.getEtab_id());
							 stock.getEta().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
								
							 this.hibernateTemplate.save(stock);	 
							 
						  
					 
					}
			     
				 result = true;
			 
				 
			} catch (Exception ex) {
				result = false;
				this.hibernateTemplate.clear();
				throw ex;
			}*/
			return result;
		}
	 
	 
	public Boolean doUpdateInventaire(InventaireBean beanUpdate)  throws Exception { 
	    boolean result=false; 
		try {
			setIdBean((InventaireBean) getObjectValueModel(FORM_BEAN), beanUpdate, InventaireTemplate.id_entite);
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
	public Boolean doDeleteInventaire(InventaireBean beanDelete)  throws Exception  {
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
