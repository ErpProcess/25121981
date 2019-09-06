package ERP.Process.Commerciale.Vente.ProcedureVente.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Det_Fact_ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Detail_mvt_vente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.MvtVente_articleBean;
import ERP.Process.Commerciale.Vente.Facture_client.template.Facture_clientTemplate;
import ERP.Process.Commerciale.Vente.FournitureVente.model.FournitureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.dao.ProcedureVenteDAO;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.DetProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.template.ProcedureVenteTemplate;
import ERP.Process.Commerciale.Vente.Service.model.ServiceBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessFormatNbr;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessNumber;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao.NumSequentielDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;


@Service
public class ProcedureVenteService  extends GenericWeb  {
	
	private ProcedureVenteDAO daoProcedureVente;
	@Autowired
	public void setDaoProcedureVente(ProcedureVenteDAO daoProcedureVente) {
		this.daoProcedureVente = daoProcedureVente;
	}
	
	
	private NumSequentielDAO daoNumSequentiel;
	@Autowired
	public void setDaoNumSequentiel(NumSequentielDAO daoNumSequentiel) {
		this.daoNumSequentiel = daoNumSequentiel;
	}
	
	
	
	@Transactional(readOnly=true)
	public List<ProcedureVenteBean> doFetchDatafromServer(ProcedureVenteBean beanSearch) throws Exception {
		return daoProcedureVente.doFindListProcedureVente(beanSearch);
	}
	
	@Transactional(readOnly=true)
	public List<DetProcedureVenteBean> doFetch_detDatafromServer(ProcedureVenteBean beanSearch) throws Exception {
		return daoProcedureVente.doFindDetaille_ListProcedureVente(beanSearch);
	}
	
	
	@Transactional(readOnly=true)
	public List<DetProcedureVenteBean> doFindDetailleListProcedureVenteEdition(ProcedureVenteBean beanSearch) throws Exception {
		return daoProcedureVente.doFindDetailleListProcedureVenteEdition(beanSearch);
	}
	
	 
	public void doRetourModeOrigin( String entite, String mode , String idRow ) throws Exception {
		 daoProcedureVente.doRetourModeOrigin(entite, mode, idRow);
	}
	
	
	
	
	@Transactional
	public void doCreateRowData(ProcedureVenteBean detailBean, FournitureVenteBean    fVenteBean , ServiceBean    service) throws Exception {
		  daoNumSequentiel.getNumSeqSimple(detailBean,"vente_id");
	      daoProcedureVente.doSaveProcedureVente(detailBean,fVenteBean,service);
	}
	
	@Transactional
	public void doDecrementeValue( String code_num_attribute) throws Exception {
		  daoNumSequentiel.doDecrementeNumSeq(code_num_attribute);
	}
	
	@Transactional
	public void  doUpdateRowData(ProcedureVenteBean updateBean)  throws Exception {
		daoProcedureVente.doUpdateProcedureVente(updateBean); 
	}
	
	@Transactional
	public void  doCorrigerProcedureVente(ProcedureVenteBean updateBean, FournitureVenteBean    fVenteBean , ServiceBean    service)  throws Exception {
			   daoProcedureVente.doCorrigerProcedureVente(updateBean) ;
			   daoProcedureVente.doUpdateProcedureVente(updateBean); 
			   daoProcedureVente.doConfirmerVente_article(updateBean,fVenteBean,service);
	}
	
	
	
	
	@Transactional
	public void  doConfirmRowData(ProcedureVenteBean updateBean, FournitureVenteBean    fVenteBean , ServiceBean    service)  throws Exception {
		 daoProcedureVente.doConfirmerVente_article(updateBean,fVenteBean,service);
	}
	
	
	@Transactional
	public void doDeleteRowData(ProcedureVenteBean deleteBean) throws Exception {
		   daoProcedureVente.doCorrigerProcedureVente(deleteBean) ;
		   daoProcedureVente.doDeleteProcedureVente(deleteBean) ;
	}



	public void doCharger_la_facture(ProcedureVenteBean detailBean) throws Exception {
		
		try {
			 
		    
			 List <DetProcedureVenteBean> listOfmyData=(List) getObjectValueModel(ProcedureVenteTemplate.LIST_EDITABLE_VENTE);
		     Double avance_montant_vente=new Double(0);
		     List<Det_Fact_ClientBean> liste_detaille_facture= new ArrayList<Det_Fact_ClientBean>();
		     List listMvt_Vente= new ArrayList();
				 for (int i = 0; i < listOfmyData.size(); i++) {
					 DetProcedureVenteBean detProVente = (DetProcedureVenteBean) listOfmyData.get(i);
					 List list_detail_mvt_vente= new ArrayList();
					 TVABean tvaBean=detProVente.getTarif().getTvaBean();
					 Detail_mvt_vente_articleBean detMvt = new Detail_mvt_vente_articleBean();
					 detMvt.getPk().getMvt_vente().setMvt_vente_id("");
					 detMvt.getPk().setVente(detProVente.getPk().getVente());
					 detMvt.getPk().setDocument_com_id(detProVente.getPk().getVente().getVente_id()) ;
					 detMvt.getPk().getNat_mvt().setNature_mvt_id("ve");
					 detMvt.setTarif(detProVente.getTarif());
					 
					 detMvt.getPk().setFkcode_barre(detProVente.getPk().getFkcode_barre());
					 detMvt.setQuantite(detProVente.getQuantite());
					 detMvt.setMontant_ht_vente(detProVente.getMontant_ht_vente());
					 detMvt.setMontant_tva_vente(detProVente.getMontant_tva_vente());
					 detMvt.setObservation("");
					 list_detail_mvt_vente.add(detMvt);
					 
					
					 MvtVente_articleBean  mvt_article= new MvtVente_articleBean();
					 mvt_article.setFkcode_barre(detProVente.getPk().getFkcode_barre());
					 mvt_article.setTvaBean(tvaBean);
					 mvt_article.setQuantite(detProVente.getQuantite());
					 mvt_article.setMontant_ht_vente(detProVente.getMontant_ht_vente());
					 mvt_article.setMontant_tva_vente(detProVente.getMontant_tva_vente());
					 mvt_article.setList_detail_mvt_vente(list_detail_mvt_vente);
					 listMvt_Vente.add(mvt_article);
					
				}
				 
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
		     daoProcedureVente.doSaveFacture(detailBean,liste_detaille_facture);
		     
			} catch (Exception e) {
				throw e;
				 
			}
	}
	
	
}
