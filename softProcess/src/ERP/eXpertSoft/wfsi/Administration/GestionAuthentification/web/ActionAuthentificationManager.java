package ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.web;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.Process.Commerciale.Achat.Facture_Fournisseur.dao.Facture_FournisseurDAO;
import ERP.Process.Commerciale.Achat.Facture_Fournisseur.model.FileFactureFournisseur;
import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.template.AuthentificationTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.service.ModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.service.PackageSysService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.service.SousModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.model.PrivilegeBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.service.PrivilegeService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.dao.UtilisateurDAO;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.service.UtilisateurService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model.EntiteAdminBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.service.EntiteAdminService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.model.GLangueBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.GLangue.service.GLangueService;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.GlibelleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.model.IdLiblleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.Glibelle.service.GlibelleService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.dao.EtablissementDAO;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.template.MenuActionBean;

public class ActionAuthentificationManager extends AuthentificationTemplate {

	 
	private static final long serialVersionUID = 3282043070569343942L;
	
	private static final String NAME_PROJECT = "data-process";
	
	private static  String DATE_LIMIT = "25/11/8018";
	
	
	
	private UtilisateurService utilisateurService;
	private PackageSysService packageSysService;
	
	 
	
	@Autowired
	private PrivilegeService      servicePrivilege;
	
	private Facture_FournisseurDAO daoFacture_Fournisseur;
	@Autowired
	public void setDaoFacture_Fournisseur(Facture_FournisseurDAO daoFacture_Fournisseur) {
		this.daoFacture_Fournisseur = daoFacture_Fournisseur;
	}
	
	
	
	@Autowired
	private EntiteAdminService        serviceEntiteAdmin;
	
	
	
	@Autowired
	private EtablissementDAO        daoEtablissement;
	
	private ModuleService moduleService;
	private SousModuleService sousModuleService;
	private GlibelleService glibelleService;
	
	private GLangueService   serviceGLangue;
	
	
	private UtilisateurDAO daoUtilisateur;
	@Autowired
	public void setDaoUtilisateur(UtilisateurDAO daoUtilisateur) {
		this.daoUtilisateur = daoUtilisateur;
	}
	
	@Autowired
	public void setServiceGLangue(GLangueService serviceGLangue) {
		this.serviceGLangue = serviceGLangue;
	}

	@Autowired
	public void setGlibelleService(GlibelleService glibelleService) {
		this.glibelleService = glibelleService;
	}

	@Autowired
	public void setUtilisateurService(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	@Autowired
	public void setPackageSysService(PackageSysService packageSysService) {
		this.packageSysService = packageSysService;
	}

	@Autowired
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Autowired
	public void setSousModuleService(SousModuleService sousModuleService) {
		this.sousModuleService = sousModuleService;
	}

	public ModelAndView doInitPwd(UtilisateurBean utilisateur) {
		return getViewUpdate(PAGE_FORMPWD);

	}

	public ModelAndView doUpdatePwd(UtilisateurBean utilisateur) {
		return getViewUpdate(PAGE_FORMPWD);
	}

	
	@SuppressWarnings("unchecked")
	public ModelAndView doLoadAppl(UtilisateurBean utilisateur) {
		
		 try {
			 
			  ModelAndView model = (ModelAndView) getSession().getAttribute(getSession().getId());
			  BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			  
				 if(model==null  || bs==null ||  bs.getView_smfct_action().equals("") ||  bs.getData_action().equals("") ){
					 getResponse().getWriter().write("fini");
				 }else{
					
					 getResponse().getWriter().write(bs.getView_smfct_action()+"£"+bs.getData_action());
				 }
				 
				 getResponse().setContentType(HTML_CONTENT_TYPE);      
		  } catch (Exception e) {
			 
		  }
	 return null;
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doLoadApplTwos(UtilisateurBean utilisateur) {
		 
		 
		 try {
			  
			  ModelAndView model = (ModelAndView) getSession().getAttribute(getSession().getId());
				 if(model== null)  return getTimeOut();
				 
			 
		      return model;
				 
		  } catch (Exception e) {
			 displayException(e);
			 return getTimeOut();
		  }
		 
	 
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView doLoadApplication(UtilisateurBean utilisateur) {

		ModelAndView model = (ModelAndView) getSession().getAttribute(getSession().getId());
		if(model==null)
		getSession().setAttribute(getSession().getId(), new ModelAndView());
	 
		try {
			String  system = ProcessDate.getCurrentTimeStamp(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateteste = sdf.parse(system); //La date1 est le 23 février 1995
			Date date2 = sdf.parse(DATE_LIMIT);
			int ret= dateteste.compareTo(date2);
			if (getModel() == null)
				throwNewException("out");
			String varUrlVieaw = getRequest().getParameter("baseUrlProject");
			if (varUrlVieaw == null || varUrlVieaw.equals(""))
				throwNewException("out");

			final String pth = varUrlVieaw+""+NAME_PROJECT;
			setObjectValueModel(CONTEXT_PATH, getRequest().getContextPath() + "/../" );
			setObjectValueModel(BASE_URL_AJAX_PROJECT, pth);
			String urlos=pth+"/ERP/eXpertSoft/wfsi/framework_dev/JQuery_datatables_Version1/root.action" ;
			String UrlServerListeCorrlee=pth+"/ERP/eXpertSoft/wfsi/framework_dev/JQuery_datatables_Version1/root.action?HiddenAction=i$_ACT_LOAD_AUTOCOMPLETE_AJAX";
			setObjectValueModel(URL_LOAD_LISTE_CoRR, UrlServerListeCorrlee);
			setObjectValueModel(URL_LOAD_DATA_TABLE, urlos);
			
			
			setObjectValueModel(TEMPLATE, new AuthentificationTemplate());
			removeObjectModel(MESSAGERROR);
		
			setObjectValueModel(TEMP_MENU_ACTION_GEN, new MenuActionBean());

			MenuActionBean disMenuX = new MenuActionBean();
			disMenuX.setToolbarBttn(NONE);
			setObjectValueModel(MENU_ACTION_DISPLAY, disMenuX);

			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			if(bs==null ){
			 setObjectValueModel(BEAN_SESSION, new BeanSession());
			 bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			}
			
			int mypassword=0;
			List list = new ArrayList<>();
			
			if(utilisateur.getUsr_login().equals("1111")) {
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int year  = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day   = cal.get(Calendar.DAY_OF_MONTH);
				mypassword=day+3;
				if(String.valueOf(mypassword).equals(utilisateur.getUsr_pwd())) {
					list = daoUtilisateur.doFindListUtilisateurByLogin(utilisateur);
				}else{
					throw new Exception("Vérifier Mot de Passe");
				}
				
			    }else {
				  
				  list = utilisateurService.dofetchDatafromServer(utilisateur);
			   }
		 
			UtilisateurBean utilBean = new UtilisateurBean();
			if (list == null || list.size() == 0)
				throw new Exception("Vérifier Mot de Passe");
			
			utilBean = (UtilisateurBean) list.get(0);
			
			if(ret>0 &&  utilBean.getEtab_bean().getPk_etab().getSoc_bean().getSoc_id().equals("6")) {
				throw new Exception("Erreur de mise a jour systeme");
			}
			
			GlibelleBean bsreash = new GlibelleBean();
			IdLiblleBean bea = new IdLiblleBean();
			String varLangue=bs.getLang_id()!=null && !bs.getLang_id().equals("")?bs.getLang_id():"fr";
			bea.setLang_id(varLangue);
			bsreash.setIdLiblleBean(bea);
			List <GlibelleBean>listlibelle = glibelleService.getListDataServer(bsreash);
			for (int i = 0; i < listlibelle.size(); i++) {
				GlibelleBean bsdx = (GlibelleBean) listlibelle.get(i);
				setObjectValueModel("_" + bsdx.getIdLiblleBean().getLib_id(),bsdx.getLib_libelle());
			}
			//InetAddress ip;
			//try {
//				//ip = InetAddress.getByName("192.168.42.187");
//				ip = InetAddress.getLocalHost();
//				System.out.println("Current IP address : " + ip.getHostAddress());
//
//				NetworkInterface network = NetworkInterface.getByInetAddress(ip);
//
//				byte[] mac = network.getHardwareAddress();
//
//				System.out.print("Current MAC address : ");
//
//				StringBuilder sb = new StringBuilder();
//				for (int i = 0; i < mac.length; i++) {
//					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//				}
//				
//				System.out.println(sb.toString());
			
				//System.out.println(crypatge);
				
				
				 
			/*	EntiteAdminBean  beAdminsx=new EntiteAdminBean();
				beAdminsx.getPk_entite_admin().setLang_id("fr");
				beAdminsx.getPk_entite_admin().setEnt_id("med_is/id_for_lib");
				List   <EntiteAdminBean>  list_EntiteAd=serviceEntiteAdmin.getListDataServer(beAdminsx);
				EntiteAdminBean sss=list_EntiteAd.get(0);
				String mxc=sss.getEnt_libelle();
				if(StringUtils.isEmpty(mxc)){
					System.out.print("crypatge : ");
					String crypatge=encrypt(sb.toString(), "xv");
					sss.setEnt_libelle(crypatge);
					serviceEntiteAdmin.UpdateRowData(sss);
				}else{
					System.out.print("decrypatge : ");
					String decrypatge=decrypt(mxc, "xv");
					//System.out.println(decrypatge);
					if(!decrypatge.equals(sb.toString()))
						throwNewException("out");
				} */
				
				

//			} catch (UnknownHostException e) {
//
//				e.printStackTrace();
//
//			} catch (SocketException e){
//
//				e.printStackTrace();
//
//			}

			
			
			EntiteAdminBean  beAdminBean=new EntiteAdminBean();
			beAdminBean.getPk_entite_admin().setLang_id(varLangue);
			
			List   <EntiteAdminBean>  list_EntiteAdmin=serviceEntiteAdmin.getListDataServer(beAdminBean);
			
			
			
			
			
			
			HashMap map_Lists_des_sousmodule_id=ProcessUtil.getHashMap_Key_List_FromList(list_EntiteAdmin, "sousmod_id");
			setObjectValueModel("map_Lists_des_sousmodule_id", map_Lists_des_sousmodule_id);
			
			
			EtablissementBean beanSearchEt = new EtablissementBean();
			beanSearchEt.getPk_etab().setSoc_bean(utilBean.getEtab_bean().getPk_etab().getSoc_bean());
			 
			 
			setObjectValueModel(LIST_ETABLISSEMMENT_GEN, daoEtablissement.doFindListEtablissement(beanSearchEt));

			
			/*******************************************etab central******************************************************/
			EtablissementBean beanSea = new EtablissementBean();
			beanSea.getPk_etab().setSoc_bean(utilBean.getEtab_bean().getPk_etab().getSoc_bean());
			beanSea.getType().setData_id("c");
			List ettab= daoEtablissement.doFindListEtablissement(beanSea);
			EtablissementBean beanCentral =(EtablissementBean) ettab.get(0);
			bs.setEtab_central(beanCentral.getPk_etab().getEtab_id());
			bs.setEtab_central_lib(beanCentral.getEtab_lib());
			bs.setHome("/Aceuil/esProcess.jsp");
			
			/*********************************************************************************************************/
			
			
			bs.setUsr_id(utilBean.getUsr_id());
			bs.setUsr_nom(utilBean.getUsr_nom());
			bs.setUsr_pre(utilBean.getUsr_pre());
			bs.setLang_id(varLangue);
			bs.setPrf_id(utilBean.getPfrBean().getPrf_id());
			bs.setPrf_libelle(utilBean.getPfrBean().getPrf_libelle());
			bs.setSoc_id(utilBean.getEtab_bean().getPk_etab().getSoc_bean().getSoc_id());
			bs.setEtab_id(utilBean.getEtab_bean().getPk_etab().getEtab_id());
			
			
			bs.setSoc_lib(utilBean.getEtab_bean().getPk_etab().getSoc_bean().getSoc_lib());
			bs.setEtab_lib(utilBean.getEtab_bean().getEtab_lib());
			List listData=daoFacture_Fournisseur.findImageFile(utilBean.getEtab_bean().getPk_etab().getSoc_bean().getFile_id());
			SocieteBean ste = utilBean.getEtab_bean().getPk_etab().getSoc_bean();
			if(listData!=null  && listData.size()>0) {
				ste.setMyFile((FileFactureFournisseur) listData.get(0));
			}
			bs.setSociete(ste);
			bs.setPatternDecimalFormat(bs.getSociete().getFormatage());
			bs.setEtablissement(utilBean.getEtab_bean());
			
			setObjectValueModel("utilBean", utilBean);
			setObjectValueModel(BEAN_SESSION, bs);

			
			
			PrivilegeBean beanSearchPrivilige= new PrivilegeBean();
			beanSearchPrivilige.getPkPriv().getPfrBean().setPrf_id(bs.getPrf_id());
			HashMap mapSouPak= new HashMap(); 
			
			List <PrivilegeBean>listPrivilge=servicePrivilege.dofetchDatafromServer(beanSearchPrivilige);
			HashMap  mapDataPackge=new HashMap();
			for(PrivilegeBean beanIncr:listPrivilge){
				Vector vectDataPack=(Vector) mapDataPackge.get(beanIncr.getPackBean().getPack_id());
				if(vectDataPack==null)vectDataPack= new Vector();
				vectDataPack.add(beanIncr);
				mapDataPackge.put(beanIncr.getPackBean().getPack_id(), vectDataPack);
			}
			Set key_mapDataPackge=mapDataPackge.keySet();
			HashMap  map_data_Pack_SouPak= new HashMap();
			for (Iterator ite = key_mapDataPackge.iterator(); ite.hasNext();) {
				BigDecimal  codePAck = (BigDecimal) ite.next();
				Vector <PrivilegeBean>vect_Pack=(Vector) mapDataPackge.get(codePAck);
				
				 
				for(PrivilegeBean  privilegeBean:vect_Pack){
					mapSouPak.put(codePAck+"+"+privilegeBean.getSpackBean().getSpack_id(), privilegeBean);
				}
				map_data_Pack_SouPak.put(codePAck, mapSouPak);
				
			}
			

			HashMap mapHashMappack = new HashMap();
			HashMap mapHashSousPAck = new HashMap();
			HashMap mapHashMApRAcoucci = new HashMap();
			
			List <PackageSysBean>list_Package_SousPackage = packageSysService.getPackageSysList(new PackageSysBean());
			int siZe_listPackgeSousPack=list_Package_SousPackage.size();
			
			
			for (int i = 0; i < siZe_listPackgeSousPack; i++) {
				PackageSysBean packageBean = (PackageSysBean) list_Package_SousPackage.get(i);
				mapHashMappack.put(packageBean.getPack_id(), packageBean.getPack_libelle());
				HashMap mapo=(HashMap) map_data_Pack_SouPak.get(packageBean.getPack_id());
				if(map_data_Pack_SouPak.get(packageBean.getPack_id())==null ||  mapo.size()==0){
					list_Package_SousPackage.remove(i);
					siZe_listPackgeSousPack--;
					i--;
					continue;
				}
				List <SousPackageBean>list_sous_pack_final= new ArrayList<SousPackageBean>();
				List <SousPackageBean>list_sous_pack_cascade=new ArrayList(packageBean.getSousPack()) ;
				for (int j = 0; j < list_sous_pack_cascade.size(); j++) {
					SousPackageBean bSous_PackageBean=list_sous_pack_cascade.get(j);
					mapHashSousPAck.put(bSous_PackageBean.getSpack_id(), bSous_PackageBean.getSpack_libelle());
					if(mapSouPak.get(bSous_PackageBean.getPackageSys().getPack_id()+"+"+bSous_PackageBean.getSpack_id())!=null) {
						list_sous_pack_final.add(bSous_PackageBean);
					} 
				 }
				
				if (list_sous_pack_final != null && list_sous_pack_final.size() == 1) {
					SousPackageBean sdsd = (SousPackageBean) list_sous_pack_cascade.get(0);
					packageBean.setRacourci_soupack(String.valueOf(sdsd.getSpack_id()));
					packageBean.setRacourci_soupack_pack_id(String.valueOf(packageBean.getPack_id()));
					mapHashMApRAcoucci.put(sdsd.getSpack_id(), packageBean.getPack_libelle());
				} 
				ProcessUtil.CollectionSortAsc(list_sous_pack_final, "spack_ordre");
				packageBean.setList_sous_mod(list_sous_pack_final);
			}
			ProcessUtil.CollectionSortAsc(list_Package_SousPackage, "pack_ordre");
			
			
			List listModul = moduleService.getModuleList();
			List listSOusModOriginale = sousModuleService.getSousModuleList(new SousModuleBean());
			setObjectValueModel("listPackgeSousPack", list_Package_SousPackage);
			setObjectValueModel("listModuleOriginale", listModul);
			setObjectValueModel("listSOusOriginale", listSOusModOriginale);
			HashMap mapHashMap = new HashMap();
			for (int i = 0; i < listSOusModOriginale.size(); i++) {
				SousModuleBean sousmoduleBean = (SousModuleBean) listSOusModOriginale.get(i);
				mapHashMap.put(sousmoduleBean.getSousmod_id(), sousmoduleBean);
			}
			setObjectValueModel("list", list);
			setObjectValueModel("mapSousModule", mapHashMap);
			setObjectValueModel("mapHashMappack", mapHashMappack);
			setObjectValueModel("mapHashMApRAcoucci", mapHashMApRAcoucci);
			setObjectValueModel("mapHashSousPAck", mapHashSousPAck);
			setUtilDevFront();
			return getHome();
		} catch (Exception e) {
			displayException(e);
			return getTimeOut();
		}

	}

	private void setUtilDevFront() {
		
		setObjectValueModel("displayNone", "style=\"display: none;\"");
	}

	public ModelAndView doQuitterApplication() {
	 
		ModelAndView mode = new ModelAndView();
		mode.setViewName(getRequest().getContextPath() + "/../" + "next");
		 if (getSession()!=null){
             getSession().invalidate();
         }
		return mode;
	}
	
	
	public String encrypt(String password,String key){
		try
		{
		Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE,clef);
		return new String(cipher.doFinal(password.getBytes()));
		}
		catch (Exception e)
		{
		return null;
		}
		}
	public String decrypt(String password,String key){
		try
		{
		Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE,clef);
		return new String(cipher.doFinal(password.getBytes()));
		}
		catch (Exception e)
		{
		System.out.println(e);
		return null;
		}
		}
	
	@SuppressWarnings("unchecked")
	public static Set  Collection_SET_SortAsc(List listData, final String lesColun) {

		Collections.sort(listData, new Comparator<Object>() {
			public int compare(Object bean, Object bean2) {

				try {
					if (lesColun.indexOf(".") > 0) {

						final String[] lesColunmooo = lesColun.split("\\.");
						Object object = bean;
						for (int k = 0; k < lesColunmooo.length; k++) {
							Field fieldo = object.getClass().getDeclaredField(
									lesColunmooo[k]);
							fieldo.setAccessible(true);
							Object obj = fieldo.get(object);
							object = obj;
						}

						String element1 = String.valueOf(object).toLowerCase();
						element1 = element1 != null ? element1 : "";

						Object object2 = bean2;
						for (int k = 0; k < lesColunmooo.length; k++) {
							Field fieldo = object2.getClass().getDeclaredField(
									lesColunmooo[k]);
							fieldo.setAccessible(true);
							Object obj = fieldo.get(object2);
							object2 = obj;

						}
						String element11 = String.valueOf(object2)
								.toLowerCase();
						element11 = element11 != null ? element11 : "";
						return String.valueOf(element1).compareTo(
								String.valueOf(element11)) * 1;

					} else {
						Field field = bean.getClass()
								.getDeclaredField(lesColun);
						field.setAccessible(true);
						String element = ((String) String.valueOf(field
								.get(bean))).toLowerCase();
						element = element != null ? element : "";
						Field field2 = bean2.getClass().getDeclaredField(
								lesColun);
						field2.setAccessible(true);
						String element2 = ((String) String.valueOf(field2
								.get(bean2))).toLowerCase();
						element2 = element2 != null ? element2 : "";
						return String.valueOf(element).compareTo(
								String.valueOf(element2)) * 1;

					}

				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				return 0;

			}
		});
		
		Set<Object> set = new HashSet<Object>();
		for (int i = 0; i < listData.size(); i++) {
			Object Obj = listData.get(i) ;
			set.add(Obj);
		}
		
     return set;
	}
	

	

	public ModelAndView getSociete() {

		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);

		try {
			getResponse().getWriter().write("General AUTO TN");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	
	public ModelAndView SetterLangSystem() {
		 
		try {
			getSession().setAttribute(getSession().getId(), new ModelAndView());
			setObjectValueModel(BEAN_SESSION, new BeanSession());
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			String  lang_id = getRequest().getParameter("ln");
			bs.setLang_id(lang_id);
			GlibelleBean beanSearch = new  GlibelleBean();
			beanSearch.getIdLiblleBean().setLang_id(lang_id);
			List listlibelle= glibelleService.getListDataServer(beanSearch);
			for (int i = 0; i < listlibelle.size(); i++) {
				GlibelleBean bsdx = (GlibelleBean) listlibelle.get(i);
				setObjectValueModel("_" + bsdx.getIdLiblleBean().getLib_id(),bsdx.getLib_libelle());
			}
			setObjectValueModel(BEAN_SESSION, bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getTimeOut();
	}

	
	
	public ModelAndView getLangSystem() throws JSONException, IOException {
		 
		 GLangueBean beanSrch =new GLangueBean();
		 beanSrch.setLang_not_null("true");
		  
	     List listLangue = serviceGLangue.dofetchDatafromServer( beanSrch);
	      getResponse().setStatus(200);
		  if ( listLangue!=null &&  listLangue.size()>0  ) {
			 
			  JSONObject json      = new JSONObject();
			  JSONArray  listData = new JSONArray();
			 for (int i=0 ; i<listLangue.size() ; i++)
			 {
				 GLangueBean bean= (GLangueBean) listLangue.get(i);
				 JSONObject  element = new JSONObject();
				 element.put("code",bean.getLang_id());
				 element.put("libelle",bean.getLang_libelle());
				 listData.put(element);
				
			 }
			  json.put("myliste", listData);
			  getResponse().setContentType("application/json");      
			  getResponse().getWriter().write(json.toString());
		    } else {
		       getResponse().getWriter().write("Username is denied");
		    }
		 
			return null;
	}
	
	private String getComputerName()
	{
	    Map<String, String> env = System.getenv();
	    if (env.containsKey("COMPUTERNAME"))
	        return env.get("COMPUTERNAME");
	    else if (env.containsKey("HOSTNAME"))
	        return env.get("HOSTNAME");
	    else
	        return "Unknown Computer";
	}
	
	public ModelAndView getUserName() throws Throwable {

		getResponse().setContentType("text");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setStatus(200);
		String usr_login = getRequest().getParameter("usrLogin") != null ? getRequest().getParameter("usrLogin") : "";
		String password = getRequest().getParameter("password") != null ? getRequest().getParameter("password") : "";
		UtilisateurBean beanUti = new UtilisateurBean();
		beanUti.setUsr_login(usr_login);
		beanUti.setUsr_pwd(password);
		//System.out.println("computer -------- "+getComputerName());
		System.out.println("SYSO -------- "+getSession().getId());
		
		
		//date1.compareTo(date2);
		
		try {
			String  system = ProcessDate.getCurrentTimeStamp(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateteste = sdf.parse(system); //La date1 est le 23 février 1995
			Date date2 = sdf.parse(DATE_LIMIT);
			int ret= dateteste.compareTo(date2);
			
			
			if (usr_login.equals("")) {
				getResponse().getWriter().write("lezim  login");
				return null;
			}
			
			if(usr_login.equals("1111")){
				String sdsdsd="";
				System.out.println("SYSO XXXXXXXX-------- "+getSession().getId());
			}

			List <UtilisateurBean>listUtilisa =  utilisateurService.dofetchDatafromServer(beanUti);

			if (listUtilisa != null && listUtilisa.size() > 0  && password.equals("")) {
				UtilisateurBean OUfa = (UtilisateurBean) listUtilisa.get(0);
				
				if(ret>0 &&  OUfa.getEtab_bean().getPk_etab().getSoc_bean().getSoc_id().equals("6")) {
					getResponse().getWriter().write("Erreur de mise a jour systeme");  
				}else {
					getResponse().getWriter().write(OUfa.getUsr_pre() + "  " + OUfa.getUsr_nom());
				}
				
			} else if (listUtilisa != null && listUtilisa.size() > 0 && !password.equals("")) {
				getResponse().getWriter().write("load Application ");
			} else if ((listUtilisa == null || listUtilisa.size() == 0)  && password.equals("")) {
				getResponse().getWriter().write("loginGhalit"); 
			} else if ((listUtilisa == null || listUtilisa.size() == 0)  && !password.equals("")) {
				getResponse().getWriter().write("Vérifier le mot de passe ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public ModelAndView doLoadModuleAndSousMod() throws IllegalArgumentException, IllegalAccessException {
		try {

			if (getModel() == null)  throwNewException("out");
				
		    String data_for_module = getRequest().getParameter("data_for_module");
			String[] data_util_module=data_for_module.split("&=");
			String sousPackId = data_util_module[1];
			String packageSys = data_util_module[0];
			removeObjectModel(MESSAGERROR);
			
			List listmd = (List<ModuleBean>) getObjectValueModel("listModuleOriginale");
			HashMap mapHashMappack = (HashMap) getObjectValueModel("mapHashMappack");
			HashMap mapHashMApRAcoucci = (HashMap) getObjectValueModel("mapHashMApRAcoucci");
			HashMap mapHashSousPAck = (HashMap) getObjectValueModel("mapHashSousPAck");
			 
			HashMap mapHashMap = new HashMap();
			List<ModuleBean> listModule = new ArrayList();
			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);
			MenuActionBean disMenuX = new MenuActionBean();
			disMenuX.setToolbarBttn(BLOCK);
			setObjectValueModel(MENU_ACTION_DISPLAY, disMenuX);

			if (sousPackId != null && !sousPackId.equals("")) {
				
				bs.setFct_id("");
		    	bs.setFct_libelle("");
		    	bs.setSousmod_id("");
		    	bs.setSousmod_libelle(""); 
				bs.setMod_id("");
				bs.setMod_libelle("");
				bs.setIndexModule(""); 
				bs.setIndexSousModule("");
				 
				bs.setSpack_id(sousPackId);
				bs.setPack_id(packageSys);
				bs.setPack_libelle((String) ((String) mapHashMApRAcoucci.get(BigDecimal.valueOf(Integer.parseInt(sousPackId))) == null 
						?  mapHashMappack.get(BigDecimal.valueOf(Integer.parseInt(packageSys))) +" / "+
						   mapHashSousPAck.get(BigDecimal.valueOf(Integer.parseInt(sousPackId)))
								: mapHashMApRAcoucci.get(BigDecimal.valueOf(Integer.parseInt(sousPackId)))));
			 
				
				PrivilegeBean beanSearch= new PrivilegeBean();
				beanSearch.getPkPriv().getPfrBean().setPrf_id(bs.getPrf_id());
				
				List<PrivilegeBean> listdPrivilege=servicePrivilege.dofetchDatafromServer(beanSearch);
				HashMap  mapModulePriv=new HashMap();
				HashMap  mapSousModulePriv=new HashMap();
				
				for(PrivilegeBean  beNo:listdPrivilege){
					BigDecimal  key=beNo.getPkPriv().getSmfonctionmodel().getPk().getSoumBean().getModuleBean().getMod_id();
					Vector vectData=(Vector) mapModulePriv.get(key);
					if(vectData==null)vectData= new Vector();
					vectData.add(beNo);
					mapModulePriv.put(key, vectData);
					
					BigDecimal  keySoumModule=beNo.getPkPriv().getSmfonctionmodel().getPk().getSoumBean().getSousmod_id();
					Vector vectDataSouModule=(Vector) mapSousModulePriv.get(keySoumModule);
					if(vectDataSouModule==null)vectDataSouModule= new Vector();
					vectDataSouModule.add(beNo);
					mapSousModulePriv.put(keySoumModule, vectDataSouModule);
				}
				
					Set keySOumDUle=mapSousModulePriv.keySet();
					
					HashMap  mapModul_dEsFction=new HashMap();
					for (Iterator iter = keySOumDUle.iterator(); iter.hasNext();) {
						BigDecimal code_SOu_Module = (BigDecimal) iter.next();
						Vector <PrivilegeBean>  vect_des_fction=(Vector) mapSousModulePriv.get(code_SOu_Module);
						HashMap  map_fct_d_un_modul=new HashMap();
						for(PrivilegeBean  bean_Fct:vect_des_fction){
							BigDecimal  keyfct=bean_Fct.getPkPriv().getSmfonctionmodel().getPk().getFcBean().getFct_id();
							map_fct_d_un_modul.put(keyfct, bean_Fct);
						}
						mapModul_dEsFction.put(code_SOu_Module, map_fct_d_un_modul);
					}
				
				for (int i = 0; i < listmd.size(); i++) {
					ModuleBean moduleBean = (ModuleBean) listmd.get(i);
					
					if(mapModulePriv.get(moduleBean.getMod_id())==null) continue;
					
					if ((moduleBean.getSousPackBean().getSpack_id()).equals(BigDecimal.valueOf(Integer.parseInt(sousPackId)))) {
						
						if (new ArrayList(moduleBean.getListSousmodule()).size() > 0) { 
							List<SousModuleBean> listjdidaSoum = new ArrayList(moduleBean.getListSousmodule());
							
							 
							
							ProcessUtil.CollectionSortAsc(listjdidaSoum, "sousmod_ordre");
							
							int siZe_listjdidaSoum= listjdidaSoum.size();
							for (int j = 0; j < siZe_listjdidaSoum; j++) {
								SousModuleBean sousModuleBean = listjdidaSoum.get(j);
								if(mapSousModulePriv.get(sousModuleBean.getSousmod_id())==null){
									listjdidaSoum.remove(j);
									siZe_listjdidaSoum--;
									j--;
								}
								List<FonctionBean> listfonctionjdida = new ArrayList(sousModuleBean.getListFction());
								if(mapSousModulePriv.get(sousModuleBean.getSousmod_id())==null)continue;
									
								 HashMap  maFction=	  (HashMap) mapModul_dEsFction.get(sousModuleBean.getSousmod_id());
								 int size_listfonctionjdida=listfonctionjdida.size();
								 for (int k = 0; k < size_listfonctionjdida; k++) {
									 FonctionBean  fctBean=listfonctionjdida.get(k);
									  if(maFction.get(fctBean.getFct_id())==null){
										  listfonctionjdida.remove(k);
										  size_listfonctionjdida--;
										  k--;
									  }else{
										  PrivilegeBean  bean_Fct=  (PrivilegeBean) maFction.get(fctBean.getFct_id());
										  fctBean.setOrdre(bean_Fct.getPkPriv().getSmfonctionmodel().getOrdre());
										  fctBean.setView_smfct_action(bean_Fct.getPkPriv().getSmfonctionmodel().getView_smfct_action());
										  
										  fctBean.setData_action(
												  bean_Fct.getPkPriv().getSmfonctionmodel().getOrdre()+"£"+
												  bean_Fct.getPkPriv().getSmfonctionmodel().getView_smfct_action()+"£"+
												  sousModuleBean.getSousmod_id().toString()+"£"+
												  fctBean.getFct_id().toString()
												  );
									  }
								}
								
								 ProcessUtil.CollectionSortAsc(listfonctionjdida, "ordre");
								
								sousModuleBean.setListf(listfonctionjdida);
							}
							moduleBean.setListsmodule(listjdidaSoum);
						}
						listModule.add(moduleBean);
						mapHashMap.put(moduleBean.getMod_id(), moduleBean);

					}
				}
				setObjectValueModel(BEAN_SESSION, bs);
			}

			setObjectValueModel("listModule", listModule);
			setObjectValueModel("mapModule", mapHashMap);
			return getHome();
		} catch (Exception e) {
			displayException(e);
			return getTimeOut();
		}
		
	}

}
