package ERP.Process.Commerciale.Vente.Mvtcom.web;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.xmlbeans.impl.util.Base64;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.dao.Facture_FournisseurDAO;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.Process.Commerciale.Vente.Mvtcom.model.MvtcomBean;
import ERP.Process.Commerciale.Vente.Mvtcom.template.MvtcomTemplate;
import ERP.Process.Commerciale.Vente.Mvtcom.service.MvtcomService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;
 

import com.google.gson.JsonIOException;

public class MvtcomActionManager extends MvtcomTemplate {
	
	
	private MvtcomService serviceMvtcom;
	@Autowired
	public void setServiceMvtcom(MvtcomService serviceMvtcom) {
		this.serviceMvtcom = serviceMvtcom;
	}
	
	private Facture_FournisseurDAO daoFacture_Fournisseur;
	@Autowired
	public void setDaoFacture_Fournisseur(Facture_FournisseurDAO daoFacture_Fournisseur) {
		this.daoFacture_Fournisseur = daoFacture_Fournisseur;
	}
	
	
	public    ModelAndView doInitServletAction() {
		 
		try {
			setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
			setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
			removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
			BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
			
			if (bs.getFct_id().equals("1") || bs.getFct_id().equals("5")  ) {
				return getViewAddMvt(FORM_VIEW);
			} else {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));

			}

		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(MvtcomBean searchBean) throws Throwable {
		try {
			List listDataSrv = serviceMvtcom.doFetchDatafromServer(searchBean);
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
	
	 public static Map<String, Object> toMap(JSONObject jsonobj)  throws JSONException {
	        Map<String, Object> map = new HashMap<String, Object>();
	        Iterator<String> keys = jsonobj.keys();
	        while(keys.hasNext()) {
	            String key = keys.next();
	            Object value = jsonobj.get(key);
	            /*if (value instanceof JSONArray) {
	                value = toList((JSONArray) value);
	            } else if (value instanceof JSONObject) {
	                value = toMap((JSONObject) value);
	            }   */
	            map.put(key, String.valueOf(value));
	        }   return map;
	    }
	    public static List<Object> toList(JSONArray array) throws JSONException {
	        List<Object> list = new ArrayList<Object>();
	        for(int i = 0; i < array.length(); i++) {
	            Object value = array.get(i);
	            if (value instanceof JSONArray) {
	                value = toList((JSONArray) value);
	            }
	            else if (value instanceof JSONObject) {
	                value = toMap((JSONObject) value);
	            }
	            list.add(value);
	        }   return list;
	}

	public ModelAndView doAddData(MvtcomBean detailBean) throws Throwable {
		
		String base64Encoded ="";
		try {
			base64Encoded = loadFile();
			setObjectValueModel(FORM_BEAN, detailBean);
			serviceMvtcom.doCreateRowData(detailBean);
			removeObjectModel(FORM_BEAN);
			//throwNewException("ins01");
			getResponse().setContentType(HTML_CONTENT_TYPE);
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			PrintWriter out = getResponse().getWriter();
			out.println(e.getMessage());
		    out.close();
		}
		removeObjectModel("MultipartFile");
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().getWriter().write(base64Encoded);
		return null;
		
	}
	
	public ModelAndView loadFileMvt() throws Throwable {
		
		String base64Encoded ="";
		try {
			 
				List listData=daoFacture_Fournisseur.findImageFile( new BigDecimal(getRequest().getParameter("file_id"))  );
				
				if(listData!=null  && listData.size()>0) {
					
					FileFactureFournisseur  Azs= (FileFactureFournisseur) listData.get(0);
			            byte[] bytes = Azs.getFile_byte();
//			            user.setFileName(fileData.getOriginalFilename());
//			            user.setImageFile(bytes);
			            byte[] encodeBase64 = Base64.encode(bytes);
			              base64Encoded = new String(encodeBase64, "UTF-8");
		
				}
			getResponse().setContentType(HTML_CONTENT_TYPE);
		} catch (Exception e) {
			getResponse().setStatus(500);
			getResponse().setContentType(HTML_CONTENT_TYPE);
			PrintWriter out = getResponse().getWriter();
			out.println(e.getMessage());
		    out.close();
		}
		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().getWriter().write(base64Encoded);
		return null;
		
	}
	
	
	public    ModelAndView doGetRowMvt() {

		try {
			removeObjectModel(FORM_BEAN);
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			MvtcomBean rowBean = (MvtcomBean) getIndexFromDataGrid_v1((String) getObjectValueModel(NAME_LIST_G));
			Map<String, Object>  map=  toMap(  new JSONObject(rowBean.getAdditional_info()));
			rowBean.setMap(map); 
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

	private String loadFile() {
		  String  chargement = "Chargment succ�s";
			try {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) getRequest();
				MultipartFile multipartFile = multiRequest.getFile("file");
			 
				if(multipartFile==null){
					chargement= "�chec de chargment";
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
				chargement= "�chec de chargment";
				e.printStackTrace();
			}
			 
		return chargement;
	}


	public ModelAndView doUpdateData(MvtcomBean beanUpBean) {
		try {
			serviceMvtcom.doUpdateRowData(beanUpBean);
			update_row_from_list(LIST_DATA, beanUpBean);
			throwNewException("mod01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(MvtcomBean beanDelBean) {
		try {
			serviceMvtcom.doDeleteRowData(beanDelBean);
			remove_row_from_list(LIST_DATA);
			throwNewException("sup01");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
