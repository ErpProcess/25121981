package ERP.Process.Commerciale.Code_barre.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Article.service.ArticleService;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.Process.Commerciale.Code_barre.model.Det_code_barre;
import ERP.Process.Commerciale.Code_barre.service.Code_barreService;
import ERP.Process.Commerciale.Code_barre.template.Code_barreTemplate;
import ERP.Process.Commerciale.Degre_definition.model.Degre_definitionBean;
import ERP.Process.Commerciale.Degre_definition.service.Degre_definitionService;
import ERP.Process.Commerciale.DetailCaracteristique.model.DetailCaracteristiqueBean;
import ERP.Process.Commerciale.DetailCaracteristique.service.DetailCaracteristiqueService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.service.EtablissementService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class Code_barreActionManager extends Code_barreTemplate {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = -1261504932058156242L;



	private Code_barreService serviceCode_barre;
		
			@Autowired
			public void setServiceCode_barre(Code_barreService serviceCode_barre) {
				this.serviceCode_barre = serviceCode_barre;
		}
	
	  @Autowired
	  private Degre_definitionService    serviceDegre_definition;
	  
	  private EtablissementService        serviceEtablissement;
		@Autowired
		public void setServiceEtablissement(EtablissementService serviceEtablissement) {
			this.serviceEtablissement = serviceEtablissement;
		}
	  
	  @Autowired
	  private DetailCaracteristiqueService   serviceDetailCaracteristique;
	  
	  @Autowired
	  private  ArticleService      serviceArticle;
	
	public    ModelAndView doInitServletAction() {

		
		
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 setObjectValueModel(LIST_COD_BARR_MASTER, new ArrayList());
			 setObjectValueModel(LIST_DES_CARAC_FOR_ARTICLE, new ArrayList());
			 ArticleBean   articlBeanSearch = new ArticleBean();
			 
			 
		     articlBeanSearch.setCondition_personnalised_list_degre(" IN  ( SELECT  dDef.pkBean.art_Bean.pk_article.ar_id   from  Degre_definitionBean dDef  where    "
		     		+ " dDef.pkBean.carac_Bean.carac_id !='Carc$'   )       ");
			 EtablissementBean beanEtab=new EtablissementBean();
		        
		        beanEtab.getPk_etab().setSoc_bean(bs.getSociete());
		        List   list_des_etab = serviceEtablissement.dofetchDatafromServer(beanEtab);
		        setObjectValueModel("list_des_etab", list_des_etab);
			 if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")   ) {
					articlBeanSearch.setCondition_personnalised_list(" NOT IN  ( SELECT  cBar.pk.ar_bean.pk_article.ar_id   from  Code_barreBean cBar  )  ");
					setObjectValueModel(LIST_DES_ARTICLE, serviceArticle.dofetchDatafromServer(articlBeanSearch));
					return getViewAdd(FORM_INIT_VIEW);
				} else {
					articlBeanSearch.setCondition_personnalised_list(" IN  ( SELECT  c.pk.ar_bean.pk_article.ar_id   from  Code_barreBean c  )       ");
					setObjectValueModel(LIST_DES_ARTICLE, serviceArticle.dofetchDatafromServer(articlBeanSearch));
					return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
				}
			 
			 

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchDataCaracteristique() throws Throwable {
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		try {
			String   TYPE_LOAD= getRequest().getParameter("TYPE_LOAD")==null?"":getRequest().getParameter("TYPE_LOAD");
			String   ID_AR= getRequest().getParameter("ID_AR")==null?"":getRequest().getParameter("ID_AR");
			Degre_definitionBean bistiqueBean= new Degre_definitionBean();
			if(StringUtils.equals(TYPE_LOAD, "C")){
				 bistiqueBean.getPkBean().getArt_Bean().getPk_article().setAr_id(ID_AR);
			}else{
				 bistiqueBean.getPkBean().getArt_Bean().getPk_article().setAr_id(ID_AR);
			}
			
			List <Degre_definitionBean> list_degre= serviceDegre_definition.doFetchDatafromServer(bistiqueBean);
			setObjectValueModel(LIST_DES_CARAC_FOR_ARTICLE,list_degre);
			JSONObject json      = new JSONObject();
			JSONArray  listData = new JSONArray();
			for (Degre_definitionBean b_deg:list_degre) {
				 DetailCaracteristiqueBean beanSearch = new DetailCaracteristiqueBean();
				 beanSearch.getPk_det_carac().getBean_carac().setCarac_id(b_deg.getCarac_Bean().getCarac_id()) ;
				 List <DetailCaracteristiqueBean> List_det = (List)serviceDetailCaracteristique.doFetchDatafromServer(beanSearch);
				 b_deg.getCarac_Bean().setList_detcarac(new HashSet( List_det));
				       JSONObject  element = new JSONObject();
				       element.put("code",b_deg.getCarac_Bean().getCarac_id());
					   element.put("libelle",b_deg.getCarac_Bean().getCarac_libelle());
					 
					   JSONArray  listdetail = new JSONArray();
					 for (DetailCaracteristiqueBean besn:List_det) {
						   JSONObject  el_json = new JSONObject();
						   el_json.put("coded",besn.getPk_det_carac().getDet_carac_id());
						   el_json.put("libelled",besn.getDet_carac_libelle());
						   listdetail.put(el_json);
					  }
					  element.put("lis_det", listdetail);
				
				 
				
				
				 listData.put(element);
			}
			
		 
				 
				 
				 
				  json.put("myliste", listData);
				  getResponse().setContentType("application/json");      
				  getResponse().getWriter().write(json.toString());
			    
			
			 
		} catch (JsonIOException e) {
			getResponse().setStatus(500);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	public    ModelAndView doUpdateListCaracteristique() throws Exception {

		 
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		 
		try {
			String id_elment=getRequest().getParameter("id_elment")==null?"":getRequest().getParameter("id_elment");
			String l_value  =getRequest().getParameter("l_value")==null?"":getRequest().getParameter("l_value");
			
			String[] tab_elmt=id_elment.split("£");
			
			List  list_des_carac_for_article =(List) getObjectValueModel(LIST_DES_CARAC_FOR_ARTICLE);
			for (int i = 0; i < list_des_carac_for_article.size(); i++) {
				Degre_definitionBean caBean=(Degre_definitionBean) list_des_carac_for_article.get(i);
				if( caBean.getCarac_Bean().getCarac_id().equals(tab_elmt[0])){
					caBean.setIs_checked(l_value);
					List<DetailCaracteristiqueBean> list_detail = new ArrayList<DetailCaracteristiqueBean>(caBean.getCarac_Bean().getList_detcarac());
					for (DetailCaracteristiqueBean  beandet:list_detail) {
						if(beandet.getPk_det_carac().getDet_carac_id().equals(tab_elmt[1]))
							beandet.setIs_checked(l_value);
					}
				}
				
			}
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;

	}
	
	 
	
	@SuppressWarnings("unchecked")
	public ModelAndView doGenerer_code_barre  (Code_barreBean searchBean) throws Throwable {
		try {
			    setObjectValueModel(LIST_COD_BARR_MASTER, new ArrayList<Code_barreBean>());
				List list_cod_barr_master= new ArrayList<Code_barreBean>();
				List  list_des_carac_for_article =(List) getObjectValueModel(LIST_DES_CARAC_FOR_ARTICLE);
				List list_Niveau= new ArrayList();
				for (int j = 0; j < list_des_carac_for_article.size(); j++) {
					Degre_definitionBean   caBean=(Degre_definitionBean) list_des_carac_for_article.get(j);
					if(caBean.getIs_checked().equals("checked")){
						List<DetailCaracteristiqueBean> list_detail = new ArrayList<DetailCaracteristiqueBean>(caBean.getCarac_Bean().getList_detcarac());
						List lis_det= new ArrayList();
						for ( int m = 0; m < list_detail.size(); m++) {
							DetailCaracteristiqueBean  beandet= list_detail.get(m);
							if(beandet.getIs_checked().equals("checked")){
								lis_det.add(caBean.getArt_Bean().getPk_article().getAr_id()+"£"+caBean.getCarac_Bean().getCarac_id()+"£"+beandet.getPk_det_carac().getDet_carac_id());
							}
						}
						list_Niveau.add(lis_det);
					}
				}
				
			     
				
			    
				 List listfinal= new ArrayList();
			     if(list_Niveau.size()==0){
			    	 throwNewException("Cochez  o moin");
			    	 
			     }else if(list_Niveau.size()==1) {
			    	 List list_7achti_bih=(List) list_Niveau.get(0);
			    	 for (int i = 0; i < list_7achti_bih.size(); i++) {
				    	 String master_barre=(String) list_7achti_bih.get(i);
				    	
				    	 Code_barreBean  bean = new Code_barreBean();
				    	 bean.getPk().setCode_barre(master_barre.replace("£", ""));
				    	 bean.setDesignation(master_barre);
				    	
				    	 
				         Det_code_barre beanCod=  new Det_code_barre();
				         String[] det_cod_bar=master_barre.split("£");
				    	 beanCod.getPk_det_codBare().getBean_cod_bar().getPk().setCode_barre(master_barre.replace("£", ""));
				    	 
				         beanCod.getPk_det_codBare().getBean_cod_bar().getPk().getAr_bean().getPk_article().setAr_id(det_cod_bar[0]);
				         bean.getPk().getAr_bean().getPk_article().setAr_id(det_cod_bar[0]);
				    	 beanCod.getPk_det_codBare().getBean_det_carac().getPk_det_carac().getBean_carac().setCarac_id(det_cod_bar[1]);
				    	 beanCod.getPk_det_codBare().getBean_det_carac().getPk_det_carac().setDet_carac_id(det_cod_bar[2]);
				    	 List  list_detail_cod_bar = new ArrayList<Det_code_barre>();
				    	 list_detail_cod_bar.add(beanCod);
				    	 
				    	 bean.setList_detail_cod_bar(list_detail_cod_bar);
				    	 list_cod_barr_master.add(bean);
					}
			    	 
			     }else{
			    	 List list_7achti_bih=(List) list_Niveau.get(0);
			    	 int f=1;
				    
				     while(f<list_Niveau.size()){
				    	 List  vDetail=(List) list_Niveau.get(f);
				    	 list_7achti_bih= ProcessUtil.cloneList(list_7achti_bih);
				    	 listfinal= new ArrayList();
					     for (int h = 0; h < list_7achti_bih.size(); h++) {
					    	 String property=(String) list_7achti_bih.get(h);
					    	 for (int n = 0; n < vDetail.size(); n++) {
					    		 String Proper_add=(String) vDetail.get(n);
					    		 listfinal.add(property+"¤"+Proper_add);
							 }
					     }
					     f++;
					     list_7achti_bih=listfinal;
				     }
				     
				     for (int i = 0; i < list_7achti_bih.size(); i++) {
				    	 String master_cod_bar=(String) list_7achti_bih.get(i);
				    	 
				    	 Code_barreBean  bean = new Code_barreBean();
				    	 bean.getPk().setCode_barre(master_cod_bar.replace("£", ""));
				    	 bean.setDesignation(master_cod_bar);
				    	 
				    	 String[] detail_cod_bar=master_cod_bar.split("¤");
				    	 List  list_detail_cod_bar = new ArrayList<Det_code_barre>();
				    	 
				    	 for (int j = 0; j < detail_cod_bar.length; j++) {
				    		 Det_code_barre beanCod=  new Det_code_barre();
					         String[] ligne_det_cod_bar=detail_cod_bar[j].split("£");
					    	 beanCod.getPk_det_codBare().getBean_cod_bar().getPk().setCode_barre(master_cod_bar.replace("£", ""));
					        
					         
					         beanCod.getPk_det_codBare().getBean_cod_bar().getPk().getAr_bean().getPk_article().setAr_id(ligne_det_cod_bar[0]);
					         
					         bean.getPk().getAr_bean().getPk_article().setAr_id(ligne_det_cod_bar[0]);
					    	 beanCod.getPk_det_codBare().getBean_det_carac().getPk_det_carac().getBean_carac().setCarac_id(ligne_det_cod_bar[1]);
					    	 beanCod.getPk_det_codBare().getBean_det_carac().getPk_det_carac().setDet_carac_id(ligne_det_cod_bar[2]);
					    	 list_detail_cod_bar.add(beanCod);
						}
				    	 bean.setList_detail_cod_bar(list_detail_cod_bar);
				    	 list_cod_barr_master.add(bean);
				    	
					}
			     }
			     doTransformerDesignation(list_cod_barr_master,searchBean);
			 
			
			 setObjectValueModel(LIST_COD_BARR_MASTER, list_cod_barr_master);
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}
	
	
	 


	private void doTransformerDesignation(List list_cod_barr_master,Code_barreBean cBddean)
			throws Exception {

		try {
			String cod=getRequest().getParameter("ID_AR");
			String lib=getRequest().getParameter("ID_LIB");
			
			 
			
           
            List list_degre=(List) getObjectValueModel(LIST_DES_CARAC_FOR_ARTICLE);
            String condition="";
            for (int i = 0; i < list_degre.size(); i++) {
            	Degre_definitionBean dBean= (Degre_definitionBean) list_degre.get(i);
            	condition+="'"+dBean.getPkBean().getCarac_Bean().getCarac_id()+"',";
			}
            condition=condition.length()>0?condition.substring(0, condition.length()-1):"";
            DetailCaracteristiqueBean  beanSearch= new DetailCaracteristiqueBean();
            beanSearch.setMap_data_list("  AND  bean.pk_det_carac.bean_carac.carac_id in ("+condition+")   ");
            List  <DetailCaracteristiqueBean> list_det_ca=serviceDetailCaracteristique.doFetchDatafromServer(beanSearch);
            HashMap  maplib= new HashMap();
            for (int i = 0; i < list_det_ca.size(); i++) {
            	DetailCaracteristiqueBean bean=   list_det_ca.get(i);	
            	String  key=cod;
            	key+=bean.getPk_det_carac().getBean_carac().getCarac_id();
            	key+=bean.getPk_det_carac().getDet_carac_id();
            	maplib.put(key, bean);
			}
           
            
			for (int i = 0; i < list_cod_barr_master.size(); i++) {
				Code_barreBean bean = (Code_barreBean) list_cod_barr_master.get(i);
				
				String getDesignation=bean.getDesignation();
				String designation_libelle=lib;
				if(getDesignation.indexOf("¤")>-1){
					String  new_ode_barre_Compose="";
					String[] EE=getDesignation.split("¤");
					for (int j = 0; j < EE.length; j++) {
						
						if( j == 0){
							new_ode_barre_Compose=EE[j].replace("£", "");
						}else{
							String[] part_caracteristik=EE[j].split("£");
							for (int k = 1; k < part_caracteristik.length; k++) {
								new_ode_barre_Compose=new_ode_barre_Compose+part_caracteristik[k];
							}
						}
						 DetailCaracteristiqueBean beanww=(DetailCaracteristiqueBean) maplib.get(EE[j].replace("£", ""));
						 if(beanww==null)
							 designation_libelle=designation_libelle+"  "+"eurreur";
						   else
						 designation_libelle=designation_libelle+"  "+beanww.getDet_carac_libelle();
					}
					
					 bean.getPk().setCode_barre(new_ode_barre_Compose);
					
					
				}else{
					   DetailCaracteristiqueBean beanww=(DetailCaracteristiqueBean) maplib.get(getDesignation.replace("£", ""));
					   if(beanww==null)
						 designation_libelle=designation_libelle+"  "+"eurreur";
					   else
					    designation_libelle=designation_libelle+"  "+beanww.getDet_carac_libelle();
					   
					    String  new_ode_barre= bean.getPk().getCode_barre().replace("£", "");
					    bean.getPk().setCode_barre(new_ode_barre);
				}
				 System.out.println(designation_libelle);
				 bean.setDesignation_libelle(designation_libelle);
				 
				
			}

		} catch (Exception e) {
			throw e;
		}

	}


	public    ModelAndView doSelectRow() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			Code_barreBean rowBean = (Code_barreBean) getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			
			List select_list_carac_det_code_barre=serviceCode_barre.doFetchData_det_cod_bar_fromServer(rowBean);
			
			setObjectValueModel(SELECT_LIST_CARAC_DET_CODE_BARRE, select_list_carac_det_code_barre);
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("2"))
				return getViewConsult(FORM_VIEW);
			if (bs.getFct_id().equals("3"))
				return getViewUpdate((String) getObjectValueModel("FORM_VIEW"));
			if (bs.getFct_id().equals("4"))
				return getViewDelete((String) getObjectValueModel("FORM_VIEW"));
		} catch (Exception e) {
			displayException(e);
			return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
		}
		return getHome();

	}
	

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(Code_barreBean searchBean) throws Throwable {
		try {
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			 
			
		    
		        //searchBeanx.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().setEtab_id(bs.getEtab_id());
		    searchBean.getPk().getAr_bean().getPk_article().getEtabBean().getPk_etab().getSoc_bean().setSoc_id(bs.getSoc_id());
		        
			List listDataSrv = serviceCode_barre.doFetchDatafromServerNew(searchBean);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (JsonIOException e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;
	}

	public ModelAndView doAddData(Code_barreBean detailBean) throws Throwable {
		try {
			serviceCode_barre.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			throwNewException("ins01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_INIT_VIEW);
	}

	public ModelAndView doUpdateData(Code_barreBean beanUpBean) {
		try {
			serviceCode_barre.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(Code_barreBean beanDelBean) {
		try {
			serviceCode_barre.doDeleteRowData(beanDelBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
