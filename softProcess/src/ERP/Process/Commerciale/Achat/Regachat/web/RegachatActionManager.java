package ERP.Process.Commerciale.Achat.Regachat.web;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.Facture_FournisseurBean;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.service.Facture_FournisseurService;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.template.Facture_FournisseurTemplate;
import ERP.Process.Commerciale.Achat.Reception_achat.template.Reception_achatTemplate;
import ERP.Process.Commerciale.Achat.Regachat.model.EcheanceRegFrsBean;
import ERP.Process.Commerciale.Achat.Regachat.model.RegachatBean;
import ERP.Process.Commerciale.Achat.Regachat.service.RegachatService;
import ERP.Process.Commerciale.Achat.Regachat.template.RegachatTemplate;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Entite_etat_commerciale.service.Entite_etat_commercialeService;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Fournisseur.service.FournisseurService;
import ERP.Process.Commerciale.Fournisseur.template.FournisseurTemplate;
import ERP.Process.Commerciale.Vente.ReglementFactClt.template.ReglementFactCltTemplate;
 
 
 
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericActionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
public class RegachatActionManager extends RegachatTemplate {
    
	private static final long serialVersionUID = 4568764718122261222L;
	private RegachatService  serviceRegachat;
	@Autowired
	public void setServiceRegachat(RegachatService serviceRegachat) {
	    this.serviceRegachat = serviceRegachat;
	} 
	
	@Autowired
	FournisseurService     serviceFournisseur;
	
	@Autowired Entite_etat_commercialeService   serviceEntite_etat_commerciale;
	
	
	private Facture_FournisseurService serviceFacture_Fournisseur;

	@Autowired
	public void setServiceFacture_Fournisseur(
			Facture_FournisseurService serviceFacture_Fournisseur) {
		this.serviceFacture_Fournisseur = serviceFacture_Fournisseur;
	}
	
	
	
	public    ModelAndView doInitServletAction() {
		 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			doLoadingLibelleOtherSModule(Reception_achatTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(Facture_FournisseurTemplate.ID_SOUS_MODULE);
			doLoadingLibelleOtherSModule(FournisseurTemplate.ID_SOUS_MODULE);
			
			 
			doLoadingLibelleOtherSModule(ID_SOUS_MODULE);
			List listfournisseur= serviceFournisseur.dofetchDatafromServer(FournisseurBean.class.newInstance());
			setObjectValueModel(Facture_FournisseurTemplate.LIST_FOURNISSEUR_FACTURE_FRS , listfournisseur);
			doLoadingLibelleOtherSModule(Reception_achatTemplate.ID_SOUS_MODULE);
			
			Entite_etat_commercialeBean beanSearBean = new Entite_etat_commercialeBean();
			beanSearBean.setCode_entite("reg_mod");
			setObjectValueModel(LIST_MODE_REGLMENT,serviceEntite_etat_commerciale.dofetchDatafromServer(beanSearBean));
			
 
			Entite_etat_commercialeBean beanSn = new Entite_etat_commercialeBean();
			beanSn.setCode_entite("reg_nature");
			setObjectValueModel(LIST_NATURE_REGLEMENT,serviceEntite_etat_commerciale.dofetchDatafromServer(beanSn));
			
			
			
			
			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau)  ) {
				
				setObjectValueModel(MAP_FIELD_BEAN, Facture_FournisseurTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
				setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_FACT");
				setObjectValueModel(FORM_BEAN, new Facture_FournisseurBean());
				setObjectValueModel(SEARCH_BEAN, new Facture_FournisseurBean());
				
				return getViewFilterFacture(FILTER_VIEW_FACTURE);
				
				 
			} else {
				
				return getViewFilterAjax( FILTER_VIEW );

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchData(RegachatBean searchBean)throws Throwable {
			try {
				BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
				
				
				if(  bs.getSousmod_id().equals(ID_DELAIS_PAIEMENT) ){
					
					
					searchBean.setCondition_juste_echeance("  " +
							"   AND   bean.reg_nbr_echeance is not null       " +
							"   AND   bean.reg_nbr_echeance   > 0             " +
							"   ");
					
					
				   if( bs.getFct_id().equals(Fn_Régler) ||   bs.getFct_id().equals(Fn_Rectifier)    ||   bs.getFct_id().equals(Fn_Annuler)    ){
						searchBean.setCondition_mode(  "    " +
								"   AND  bean.modeBean.fct_id  in ('"+Fn_Confirmer+"', '"+Fn_Rectifier+"' )  ");
				   }
				   
				   if( bs.getFct_id().equals(Fn_Consulter)      ){
						searchBean.setCondition_mode(  "    " +
								"   AND  bean.modeBean.fct_id  in ('"+Fn_Confirmer+"', '"+Fn_Rectifier+"' ,  '"+Fn_Régler+"'  )  ");
				   }
					
				    
				   
					
					
				}else{
					
					if( bs.getFct_id().equals(Fn_Confirmer)   ||  bs.getFct_id().equals(Fn_Supprimer) ||  bs.getFct_id().equals(Fn_Modifier)  ){
						searchBean.setCondition_mode(  "    " +
								"   AND  bean.modeBean.fct_id  in ('"+Fn_Créer+"', '"+Fn_Modifier+"' )  ");
					}
					
				}
				
				 
				
				
			 
				 
				List listDataSrv = serviceRegachat.doFetchDatafromServer(searchBean);
				setObjectValueModel(SEARCH_BEAN, searchBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	
	
	@SuppressWarnings("unchecked") 
	public ModelAndView doFetchFacture_fournisseur(Facture_FournisseurBean searchBean)throws Throwable {
			try {
				
				setObjectValueModel(MAP_FIELD_BEAN, Facture_FournisseurTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
				setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
				setObjectValueModel(ACT_FETCH_AJAX_GLOBAL, "i$_ACT_FETCH_AJAX_FACT");
				 
				
			    searchBean.setCondition_select_mode("  AND   bean.etat_reg.data_id='fnon'  AND    bean.typefact.type_fact_id in ('1','3')    ");
			    
			    
			    
			    //BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 
			    
			   // searchBean.setSelect_many_facture("  AND  bean.modeBean.fct_id    in ('"+Fn_Confirmer+"')  AND   bean.typefact.type_fact_id in ('1','3')     AND   bean.etat_reg.data_id='fnon'     " ); 
			    
			    //if(bs.getSousmod_id().equals(ReglementFactCltTemplate.ID_SOUS_MODULE_REMBOURCEMENT))
			    //searchBean.setSelect_many_facture("  AND  bean.modeBean.fct_id    in ('"+Fn_Confirmer+"')     AND   bean.typefact.type_fact_id in ('2')         AND   bean.etat_reg.data_id='fnon'    " );
			    
			    
				List listDataSrv = serviceFacture_Fournisseur.doFetchDatafromServer(searchBean);
				setObjectValueModel(SEARCH_BEAN, searchBean);
				AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
	 		} catch (Exception e) {
	 			 getResponse().setStatus(500);
	 			 e.printStackTrace();
	 			 getResponse().setContentType(HTML_CONTENT_TYPE);
	 			 getResponse().getWriter().print(e.getMessage());
	}
	return null;
	}
	
	public    ModelAndView doGetRowBeanReg() {

		try {
			 
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			if (bs.getFct_id().equals(Fn_Créer) || bs.getFct_id().equals(Fn_Nouveau) ){
				setObjectValueModel(MAP_FIELD_BEAN, Facture_FournisseurTemplate.MapfieldBean);
				setObjectValueModel(LIST_VIEW_G, LIST_VIEW_FACTURE);
				setObjectValueModel(NAME_LIST_G ,LIST_DATA_FACTURE); 
				setObjectValueModel(NAME_GRID_G, NAME_GRID_FACTURE);
				Facture_FournisseurBean beanFact = (Facture_FournisseurBean) getIndexFromDataGrid_v1(LIST_DATA_FACTURE);
				RegachatBean   rowBean =  new RegachatBean();
				rowBean.setFact_frs(beanFact);
				setObjectValueModel(FORM_BEAN, rowBean);
				return getViewAdd(FORM_VIEW_EDIT);
			}else{
				RegachatBean beanRegFact = (RegachatBean) getIndexFromDataGrid_v1(LIST_DATA);
				List<EcheanceRegFrsBean> list_des_echeances =   serviceRegachat.doFindListEcheanceReglement(beanRegFact);
				setObjectValueModel(LIST_DES_ECHEANCES, list_des_echeances);
				setObjectValueModel(LIST_DES_ECHEANCES_ORIGINE, ProcessUtil.cloneList(list_des_echeances) );
				setObjectValueModel(FORM_BEAN, beanRegFact);
			}
			
			if (bs.getFct_id().equals("2"))
				return getViewConsult(FORM_VIEW);
			
			if (bs.getFct_id().equals("3") ||  bs.getFct_id().equals(Fn_Rectifier)  )
				return getViewUpdate(FORM_VIEW_EDIT);
			
			if (bs.getFct_id().equals("4"))
				return getViewDelete(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Confirmer) ||  bs.getFct_id().equals(Fn_Régler) )
				return getViewConfirm(FORM_VIEW);
			
			if (bs.getFct_id().equals(Fn_Annuler))
				return getViewDelete(FORM_VIEW);
			
			
			
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}
	
	
	protected    ModelAndView doLoadGridEcheance(RegachatBean searchBean ) {
		
		try {
			getResponse().setContentType(HTML_CONTENT_TYPE);
			List <EcheanceRegFrsBean> list_des_echeances  = new ArrayList<EcheanceRegFrsBean>();
			List  <EcheanceRegFrsBean>list_des=(List) getObjectValueModel( LIST_DES_ECHEANCES );
			
			if(list_des!=null  &&  list_des.size()>0) {
				list_des_echeances=list_des;
				
				if( searchBean.getReg_nbr_echeance().intValue() > list_des_echeances.size()){
					
					int dif=searchBean.getReg_nbr_echeance().intValue()-list_des_echeances.size();
					for (int i = 0; i < dif; i++) {
						list_des_echeances.add(new EcheanceRegFrsBean());
					}
					
					
					
				}
				
				if( searchBean.getReg_nbr_echeance().intValue() < list_des_echeances.size()){
					int dif=list_des_echeances.size()-searchBean.getReg_nbr_echeance().intValue();
					
					for (int i = 0; i < dif; i++) {
						list_des_echeances.remove(i);
						dif--;
						i--;
					}
				}
				
				 
			}else{
				if(searchBean.getReg_nbr_echeance()!=null  &&  searchBean.getReg_nbr_echeance()>0){
					for (int i = 0; i < searchBean.getReg_nbr_echeance(); i++) {
						list_des_echeances.add(new EcheanceRegFrsBean() );
					}
				}
				
			}
		
			setObjectValueModel(LIST_DES_ECHEANCES, list_des_echeances);
			
			if(list_des_echeances.size()>0){
				 getResponse().getWriter().write("oui");
			}else{
				 getResponse().getWriter().write("non");
			}
			  
			  
			 
			 
		} catch (Exception e) {
			displayException(e);
		}
		return null;
	}
	
	
	public ModelAndView doAddData(RegachatBean detailBean) throws Throwable {
	     try {
				setObjectValueModel(FORM_BEAN, detailBean);
	            serviceRegachat.doCreateRowData(detailBean);
	            removeObjectModel(FORM_BEAN);
	            throwNewException("ins01");
	          } catch (Exception e) {
	            displayException(e);
	          }
	        return getViewAdd(FORM_VIEW);
		}
	public ModelAndView doUpdateData(RegachatBean beanUpBean) {	 
		 	try {
		 		BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
					
		        serviceRegachat.doUpdateRowData(beanUpBean); 
				
				
				 if(bs.getFct_id().equals(GenericActionBean.Fn_Modifier)){
						update_row_from_list(LIST_DATA, beanUpBean); 
					    throwNewException("mod01");
				 } else{
					 remove_row_from_list(LIST_DATA); 
						 throwNewException("ins01");
			     }	 
				
				
		         
		 	} catch (Exception e) {
		 	displayException(e);
		 }
		return getViewList_Ajax(FILTER_VIEW);
			}
	public ModelAndView doDeleteData(RegachatBean beanDelBean) {
	    try {
	     serviceRegachat.doDeleteRowData(beanDelBean);
				    remove_row_from_list(LIST_DATA);
	     throwNewException("sup01");
	       } catch (Exception e) {
	       displayException(e);
	       }
	    return getViewList_Ajax(FILTER_VIEW);
	   }
 }
