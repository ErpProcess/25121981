package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.web;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.dao.Facture_FournisseurDAO;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Data_entite_simple.service.Data_entite_simpleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.service.DeviseService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.service.NumSequentielService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model.PaysvilleposteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.service.PaysvilleposteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.service.SocieteService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.template.SocieteTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.JsonResponse;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class ActionSocieteManager extends SocieteTemplate {

	private SocieteService  serviceSociete;
	
	private NumSequentielService  serviceNumSequentiel;
	
	private Data_entite_simpleService  serviceData_entite_simple;
	private PaysvilleposteService   servicePaysvilleposte;
	
	private GLangueService   serviceGLangue;
	
	private DeviseService     serviceDevise;
	@Autowired
	public void setServiceDevise(DeviseService serviceDevise) {
		this.serviceDevise = serviceDevise;
	}
	
	private Facture_FournisseurDAO daoFacture_Fournisseur;
	@Autowired
	public void setDaoFacture_Fournisseur(Facture_FournisseurDAO daoFacture_Fournisseur) {
		this.daoFacture_Fournisseur = daoFacture_Fournisseur;
	}
	
	@Autowired
	public void setServiceNumSequentiel(NumSequentielService serviceNumSequentiel) {
		this.serviceNumSequentiel = serviceNumSequentiel;
	}

	@Autowired
	public void setServiceSociete(SocieteService serviceSociete) {
		this.serviceSociete = serviceSociete;
	}
	
	@Autowired
	public void setServiceData_entite_simple(Data_entite_simpleService serviceData_entite_simple) {
		this.serviceData_entite_simple = serviceData_entite_simple;
	}
	
	
	public ModelAndView doInitServletAction() {
		 
		
		
		
		
		
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			setObjectValueModel("listUsr_cath", serviceData_entite_simple.dofetchDataByCodeEntite("USR_CATH"));
			setObjectValueModel("listUsr_piece", serviceData_entite_simple.dofetchDataByCodeEntite("typ_piece"));
			setObjectValueModel("listUsr_sexe",serviceData_entite_simple.dofetchDataByCodeEntite("sexe_usr"));
			setObjectValueModel("listUsr_civil",serviceData_entite_simple.dofetchDataByCodeEntite("situ_usr"));
			setObjectValueModel("listUsr_etat",serviceData_entite_simple.dofetchDataByCodeEntite("ETAT_USR"));
			setObjectValueModel("listUsr_type",serviceData_entite_simple.dofetchDataByCodeEntite("type_usr"));
			setObjectValueModel("listCountry", servicePaysvilleposte.dofetchDatafromServer(new PaysvilleposteBean()));
			setObjectValueModel("listLanG", serviceGLangue.dofetchDatafromServer(new GLangueBean()));
			setObjectValueModel("list_devise", serviceDevise.doFetchDatafromServer(new DeviseBean()));
			if (bs.getFct_id().equals("1")) {
				return getViewAdd((String) getObjectValueModel("FORM_VIEW"));
			} else {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
	public ModelAndView deleteFile() throws Exception {
        String  chargement= "delete_file_true";
		try {
			setObjectValueModel("MultipartFile", null);
		} catch (Exception e) {
			chargement= " échec delete File  ";
			e.printStackTrace();
		}
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().getWriter().write(chargement);

		return null;
	}
	
	
	public ModelAndView uploadFile() throws Exception {
        String  chargement= " Chargment du fichier effectué avec succès  ";
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) getRequest();
			MultipartFile multipartFile = multiRequest.getFile("file");
		 
			if(multipartFile==null){
				chargement= " échec de chargment  ";
				setObjectValueModel("MultipartFile", null);
			}else{
				
			}
			
			String mime_content_type = multipartFile.getContentType();
			String filename = multipartFile.getOriginalFilename();
			byte[] bytes = multipartFile.getBytes();
			FileFactureFournisseur insertBean = new FileFactureFournisseur();
			
			String doc_id_interne =getRequest().getParameter("doc_id_interne");
			insertBean.setMime_content_type(mime_content_type);
			insertBean.setDoc_id_interne(doc_id_interne);
			insertBean.setFile_byte(bytes);
			byte[] encodeBase64 = Base64.encode(bytes);
	        String base64Encoded = new String(encodeBase64, "UTF-8");
	        //insertBean.setFact_frs_id(base64Encoded);
	        
	        chargement=base64Encoded;
	        		
			insertBean.setFile_name(filename);
			insertBean.setMultipartFile(multipartFile);
			setObjectValueModel("MultipartFile", insertBean);
			//serviceFacture_Fournisseur.doCreateRowDatafileFacturefrs(insertBean);
			/*InputStream input = multipartFile.getInputStream();
			File source = new File("D://" + filename);
			multipartFile.transferTo(source); */
		} catch (Exception e) {
			chargement= " échec de chargment  ";
			e.printStackTrace();
		}
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().getWriter().write(chargement);

		return null;
	}
	
	 
	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(SocieteBean searchBean)throws Throwable {
		try {
			List listDataSrv = serviceSociete.dofetchDatafromServer(searchBean);
			setObjectValueModel(LIST_DATA, listDataSrv);
			setObjectValueModel(SEARCH_BEAN, searchBean);
			AjaxDataTablesUtility.doInitJQueryGrid(listDataSrv);
		} catch (JsonIOException e) {
			e.printStackTrace();
			getResponse().setContentType(HTML_CONTENT_TYPE);
			getResponse().getWriter().print(e.getMessage());
		}
		return null;

	}
	

	public ModelAndView doAddData(SocieteBean beans) throws Throwable {
		try {
			 String dataSocieteLng_ar= getRequest().getParameter("dataSocieteLng_ar");
			 String dataSocieteLng_en= getRequest().getParameter("dataSocieteLng_en");
			 JSONObject json        = new JSONObject();
			 json.put("ar",  convertStringToHashMap(dataSocieteLng_ar));
			 json.put("en", convertStringToHashMap(dataSocieteLng_en));
			 String data_societe_langue=json.toString();
			 beans.setData_societe_langue(data_societe_langue);
			 
	 
				
			 serviceSociete.doCreateRowData(beans);
		     removeObjectModel(FORM_BEAN);
		     throwNewException("Insertion Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewAdd(FORM_VIEW);
	}
	
	
	public    ModelAndView doGetRowDataBean() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			SocieteBean rowBean = (SocieteBean) getIndexFromDataGrid_v1("SocieteList");
			
			JSONObject jsonObj = new JSONObject(rowBean.getData_societe_langue());
			
			HashMap<String, Object> yourHashMap = new Gson().fromJson(jsonObj.toString(), HashMap.class);

			Map<String,Object> yearMap = yourHashMap; //toMap(jsonObj);
			rowBean.setMaplang(yearMap);
			
			if(rowBean.getFile_id() !=null) {
				List listData=daoFacture_Fournisseur.findImageFile(rowBean.getFile_id());
				if(listData!=null  && listData.size()>0) {
					
					FileFactureFournisseur  Azs= (FileFactureFournisseur) listData.get(0);
					
					if (  rowBean.getMyFile()   != null) {                
			            byte[] bytes = rowBean.getMyFile().getFile_byte();
//			            user.setFileName(fileData.getOriginalFilename());
//			            user.setImageFile(bytes);
			            byte[] encodeBase64 = Base64.encode(bytes);
			            String base64Encoded = new String(encodeBase64, "UTF-8");
			            Azs.setFact_frs_id(base64Encoded);
			        }
					rowBean.setMyFile(Azs);
				}
			}
			
			
			setObjectValueModel(FORM_BEAN, rowBean);
			if (bs.getFct_id().equals("2"))
				return getViewConsult((String) getObjectValueModel("FORM_VIEW"));
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

	public ModelAndView doRetourToHome(SocieteBean pack) {
		return getHome();

	}

	public ModelAndView doUpdateData(SocieteBean beanUp) {
		try {
			 
			 String dataSocieteLng_ar= getRequest().getParameter("dataSocieteLng_ar");
			 String dataSocieteLng_en= getRequest().getParameter("dataSocieteLng_en");
			 JSONObject json        = new JSONObject();
			 json.put("ar",  convertStringToHashMap(dataSocieteLng_ar) );
			 json.put("en", convertStringToHashMap(dataSocieteLng_en) );
			 String data_societe_langue=json.toString();
			 beanUp.setData_societe_langue(data_societe_langue);
			 
			  
			 serviceSociete.doUpdateRowData(beanUp);
			 update_row_from_list(LIST_DATA, beanUp)  ;
			 throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);
	}
	
	
//	public static void main (String[] args){
//		
//		 
//		
//		
//		String nssss="&#1578;&#1608;&#1606;&#1587;";
//		String qsqs=" شركة القبي فيش";
//		Document  sss= Jsoup.parse(nssss);
//		String text = sss.body().text();
//	 
//		  
//       System.out.print(text);
//		}
		 
	
	 

	public ModelAndView doDeleteData(SocieteBean beanDelBean) {

		SocieteBean pSysBean = (SocieteBean) getObjectValueModel(FORM_BEAN);
		try {
			serviceSociete.doDeleteRowData(pSysBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("Reussite delete");
			
		} catch (Exception e) {
			displayException(e);
		}

		return getViewList_Ajax(FILTER_VIEW);

	}
	@Autowired
	public void setServicePaysvilleposte(PaysvilleposteService servicePaysvilleposte) {
		this.servicePaysvilleposte = servicePaysvilleposte;
	}

	@Autowired
	public void setServiceGLangue(GLangueService serviceGLangue) {
		this.serviceGLangue = serviceGLangue;
	}


	

}
