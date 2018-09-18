package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.web.ActionAuthentificationManager;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.model.ModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Module.service.ModuleService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.model.PackageSysBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.PackageSys.service.PackageSysService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model.SmfonctionModel;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.service.SmfonctionService;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.model.SousPackageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousPack.service.SousPackageService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.model.PrivilegeBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.service.PrivilegeService;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Privilege.template.PrivilegeTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.service.ProfileService;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessUtil;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
import ERP.eXpertSoft.wfsi.jqueryoR.datatables.controller.AjaxDataTablesUtility;

import com.google.gson.JsonIOException;

public class PrivilegeActionManager extends PrivilegeTemplate {
	
	
	 
	private static final long serialVersionUID = 9066861297215005611L;


	private PrivilegeService servicePrivilege;
	
	
	private SmfonctionService   serviceSmfonction;
	@Autowired
	public void setServiceSmfonction(SmfonctionService serviceSmfonction) {
		this.serviceSmfonction = serviceSmfonction;
	}
	
	
	
	private SousPackageService   serviceSousPackage;
	@Autowired
	public void setServiceSousPackage(SousPackageService serviceSousPackage) {
		this.serviceSousPackage = serviceSousPackage;
	}
	
	
	private ModuleService   serviceModule;
	@Autowired
	public void setServiceModule(ModuleService serviceModule) {
		this.serviceModule = serviceModule;
	}
	
	
	private ProfileService   serviceProfile;
	@Autowired
	public void setServiceProfile(ProfileService serviceProfile) {
		this.serviceProfile = serviceProfile;
	}
	
	private PackageSysService servicePackageSys;
	@Autowired
	public void setServicePackageSys(PackageSysService servicePackageSys) {
		this.servicePackageSys = servicePackageSys;
	}
	
	
	@Autowired
	public void setServicePrivilege(PrivilegeService servicePrivilege) {
		this.servicePrivilege = servicePrivilege;
	}
	

	
	public ModelAndView doInitServletAction() {

		setObjectValueModel(FORM_BEAN, getObjectValueModel(MODEL_BEAN));
		setObjectValueModel(SEARCH_BEAN, getObjectValueModel(MODEL_BEAN));
		removeObjectModel(  "listInitPrivilege_origine"  );
		removeObjectModel("listGridEditable_Package");
		removeObjectModel("map_Package"); 
		removeObjectModel("map_SousPackage" );
		removeObjectModel("map_Module");
		removeObjectModel("map_sousModule_fction");
		removeObjectModel((String) getObjectValueModel(NAME_LIST_G));
		BeanSession bs =(BeanSession)getObjectValueModel(BEAN_SESSION);
		try {
			setObjectValueModel("listProfilFor_privilege", serviceProfile.dofetchDatafromServer(new ProfileBean()));
			if (bs.getFct_id().equals("6")) {
				return getViewInitAffect("FormInitPrivilege");
			} else {
				return getViewFilterAjax((String) getObjectValueModel("FILTER_VIEW"));
			}
			
		    
		} catch (Exception e) {
			displayException(e);
			return getHome();
		}

	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doInitAffectation_privilege(PrivilegeBean searchBean ) {

		setObjectValueModel(FORM_BEAN, searchBean);
		 
		try {
			List<PrivilegeBean> listInitPrivilege_origine=servicePrivilege.dofetchDatafromServer(searchBean);
			setObjectValueModel(  "listInitPrivilege_origine"  , listInitPrivilege_origine);
			
			HashMap   map_Package= new HashMap();
			HashMap   map_SousPackage= new HashMap();
			HashMap   map_Module= new HashMap();
			HashMap   map_sousModule_fction= new HashMap();
			
			if(listInitPrivilege_origine!=null &&  listInitPrivilege_origine.size()>0){
				
				for (PrivilegeBean beanPriv:listInitPrivilege_origine) {
					
					Vector Vect_PAck=(Vector) map_Package.get(beanPriv.getPackBean().getPack_id());
					if(Vect_PAck==null)Vect_PAck= new Vector();
					Vect_PAck.add(beanPriv);
					map_Package.put(beanPriv.getPackBean().getPack_id(), Vect_PAck);
					
					Vector VectioSouPAck=(Vector) map_SousPackage.get(beanPriv.getSpackBean().getSpack_id());
					if(VectioSouPAck==null)VectioSouPAck= new Vector();
					VectioSouPAck.add(beanPriv);
					map_SousPackage.put(beanPriv.getSpackBean().getSpack_id(), VectioSouPAck);
					
					Vector Vectio_Module=(Vector) map_Module.get(beanPriv.getModBean().getMod_id());
					if(Vectio_Module==null)Vectio_Module= new Vector();
					Vectio_Module.add(beanPriv);
					map_Module.put(beanPriv.getModBean().getMod_id(), Vectio_Module);
					
					String  thEKey=String.valueOf(beanPriv.getPkPriv().getSmfonctionmodel().getPk().getSoumBean().getSousmod_id())+"£"+
					String.valueOf(beanPriv.getPkPriv().getSmfonctionmodel().getPk().getFcBean().getFct_id());
					
					Vector vect_fct_SOusModule=(Vector) map_sousModule_fction.get(thEKey);
					if(vect_fct_SOusModule==null)vect_fct_SOusModule= new Vector();
					vect_fct_SOusModule.add(beanPriv);
					map_sousModule_fction.put(thEKey, vect_fct_SOusModule);
				}
			} 
			
			setObjectValueModel("map_Package", map_Package); 
			setObjectValueModel("map_SousPackage", map_SousPackage);
			setObjectValueModel("map_Module", map_Module);
			setObjectValueModel("map_sousModule_fction", map_sousModule_fction);
			
			List <PackageSysBean> list_des_package=servicePackageSys.getPackageSysList(new PackageSysBean());
			for(PackageSysBean packageSysBean:list_des_package){
				if(map_Package.get( packageSysBean.getPack_id())!=null)
					packageSysBean.setTo_check("checked")	;
			}
			ProcessUtil.CollectionSortAsc(list_des_package, "pack_id" );
			setObjectValueModel("listGridEditable_Package",list_des_package);
			return getViewAdd("FormPrivilege");
			
			
		    
		} catch (Exception e) {
			displayException(e);
			 return getViewInitAffect("FormInitPrivilege");
		}

	}
	
	

	@SuppressWarnings("unchecked")
	public ModelAndView doFetchData(PrivilegeBean searchBean) throws Throwable {
		try {
			List listDataSrv = servicePrivilege.dofetchDatafromServer(searchBean);
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
	@SuppressWarnings("unchecked")
	public ModelAndView doLoadSou_Packge() throws Throwable {
		
		
		try {
			 
			List <PackageSysBean> listData_sou_Packge=(List) getObjectValueModel("listGridEditable_Package");
			HashMap  mapPackEmpty=new HashMap();
			HashMap  mapPackNotEmpty=new HashMap();
			
			for(PackageSysBean bIncrem:listData_sou_Packge){
				if(bIncrem.getTo_check().equals("checked"))
					mapPackNotEmpty.put(bIncrem.getPack_id(), bIncrem);
				else
					mapPackEmpty.put(bIncrem.getPack_id(), bIncrem);
			}
			
			 if(mapPackNotEmpty.size()==0){
				 getResponse().getWriter().print("false");
			 }else{
				 HashMap map_SouPAck= (HashMap) getObjectValueModel("map_SousPackage");
				 List <SousPackageBean>listSouPack_Origine= serviceSousPackage.getSousPackList(new SousPackageBean());
				 List listJdida= new ArrayList();
				 for(SousPackageBean beanPack:listSouPack_Origine){
					 if( mapPackNotEmpty.get(beanPack.getPackageSys().getPack_id()) != null){
						 if(map_SouPAck.get( beanPack.getSpack_id())!=null) beanPack.setTo_check("checked")	;
						 listJdida.add(beanPack);
					 }
				 }
				 setObjectValueModel("listGridEditable_SousmapPackage", listJdida);
				 getResponse().getWriter().print("true");
				 if(listJdida.size()==0)
				 getResponse().getWriter().print("false");
			 }
				 
		} catch (Exception e) {
		}
		return null;
	}
	
	
	public ModelAndView doLoadModulefrom_spack() throws Throwable {
		
		
		try {
			 
			List <SousPackageBean> listData_sou_Packge=(List) getObjectValueModel("listGridEditable_SousmapPackage");
			HashMap  mapPackEmpty=new HashMap();
			HashMap  mapPackNotEmpty=new HashMap();
			
			for(SousPackageBean bIncrem:listData_sou_Packge){
				if(bIncrem.getTo_check().equals("checked"))
					mapPackNotEmpty.put(bIncrem.getSpack_id(), bIncrem);
				else
					mapPackEmpty.put(bIncrem.getSpack_id(), bIncrem);
			}
			 if(mapPackNotEmpty.size()==0){
				 getResponse().getWriter().print("false");
			 }else{
				 
				  HashMap  map_module=(HashMap) getObjectValueModel("map_Module");
					
					
				 List <ModuleBean>listr= serviceModule.dofetchDatafromServer(new  ModuleBean());
				 List listJdida= new ArrayList();
				 for(ModuleBean beanModuleBean:listr){
					 if( mapPackNotEmpty.get(beanModuleBean.getSousPackBean().getSpack_id()) != null){
						 
						 if(map_module.get( beanModuleBean.getMod_id())!=null) beanModuleBean.setTo_check("checked")	;
		 
						 listJdida.add(beanModuleBean);
					 }
				 }
				 setObjectValueModel("listGridEditable_Module_desouPak", listJdida);
				 getResponse().getWriter().print("true");
				 if(listJdida.size()==0)
				 getResponse().getWriter().print("false");
			 }
				 
		} catch (Exception e) {
			//displayException(e);
		}
		return null;
	}
	
	
	public ModelAndView doLoadFct_sous_Module() throws Throwable {
		
		
		try {
			 
			List <ModuleBean> listData_ModuleBean=(List) getObjectValueModel("listGridEditable_Module_desouPak");
			HashMap  mapPackEmpty=new HashMap();
			HashMap  mapPackNotEmpty=new HashMap();
			
			for(ModuleBean bIncrem:listData_ModuleBean){
				if(bIncrem.getTo_check().equals("checked"))
					mapPackNotEmpty.put(bIncrem.getMod_id(), bIncrem);
				else
					mapPackEmpty.put(bIncrem.getMod_id(), bIncrem);
			}
			 if(mapPackNotEmpty.size()==0){
				 getResponse().getWriter().print("false");
			 }else{
				 
			  HashMap  mpa_Module_fction	= (HashMap) getObjectValueModel("map_sousModule_fction");
				 
				 List <SmfonctionModel>listr= serviceSmfonction.dofetchDatafromServer(new SmfonctionModel());
				 List listJdida= new ArrayList();
				 for(SmfonctionModel beanSmfonction:listr){
					 String  idComposer=String.valueOf(beanSmfonction.getPk().getSoumBean().getSousmod_id())+"£"+String.valueOf(beanSmfonction.getPk().getFcBean().getFct_id());
					 beanSmfonction.setId_entite(idComposer);
					 if( mapPackNotEmpty.get(beanSmfonction.getPk().getSoumBean().getModuleBean().getMod_id()) != null){
						 
						 String  thEKey=String.valueOf(beanSmfonction.getPk().getSoumBean().getSousmod_id())+"£"+
							String.valueOf(beanSmfonction.getPk().getFcBean().getFct_id());
						 
						 if(mpa_Module_fction.get(thEKey)!=null) beanSmfonction.setTo_check("checked")	;
						 
						 listJdida.add(beanSmfonction);
					 }
				 }
				 setObjectValueModel("listGridEditable_Fct_smoduleSystem", listJdida);
				 getResponse().getWriter().print("true");
				 if(listJdida.size()==0)
				 getResponse().getWriter().print("false");
			 }
				 
		} catch (Exception e) {
			//displayException(e);
		}
		return null;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView doAddData(PrivilegeBean detailBean) throws Throwable {
		try {
			
			 
			
			List <SmfonctionModel> listGridEditable_Fct_smod=(List) getObjectValueModel("listGridEditable_Fct_smoduleSystem");
			
			List <PrivilegeBean>lisDataToInsert= new ArrayList<PrivilegeBean>();
			
			for (SmfonctionModel beanSmfonction:   listGridEditable_Fct_smod ) {
				
				if(beanSmfonction.getTo_check()!=null  &&  beanSmfonction.getTo_check().equals("checked")){
					PrivilegeBean  bPrivilegeBean= new  PrivilegeBean();
					bPrivilegeBean.getPkPriv().getPfrBean().setPrf_id(detailBean.getPkPriv().getPfrBean().getPrf_id());
					bPrivilegeBean.getPkPriv().getSmfonctionmodel().setPk(beanSmfonction.getPk());
					
					BigDecimal  mod_id=bPrivilegeBean.getPkPriv().getSmfonctionmodel().getPk().getSoumBean().getModuleBean().getMod_id();
					bPrivilegeBean.getModBean().setMod_id(mod_id);
					
					BigDecimal  spack_id=bPrivilegeBean.getPkPriv().getSmfonctionmodel().getPk().getSoumBean().getModuleBean().getSousPackBean().getSpack_id();
					bPrivilegeBean.getSpackBean().setSpack_id(spack_id); 
					
					
					BigDecimal  pack_id=bPrivilegeBean.getPkPriv().getSmfonctionmodel().getPk().getSoumBean().getModuleBean().getSousPackBean().getPackageSys().getPack_id();
					bPrivilegeBean.getPackBean().setPack_id(pack_id);
					lisDataToInsert.add(bPrivilegeBean);
					
					
				}
			}
			 servicePrivilege.doCreateRowData(lisDataToInsert);
			 removeObjectModel(   "listInitPrivilege_origine"  );
			 throwNewException("Insertion Reussit");
			 
		} catch (Exception e) {
			displayException(e);
		}
		return getViewInitAffect("FormInitPrivilege");
	}

	public ModelAndView doUpdateData(PrivilegeBean beanUpBean) {
		try {
			servicePrivilege.doUpdateRowData(beanUpBean);
			throwNewException("Update Reussit");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}

	public ModelAndView doDeleteData(PrivilegeBean beanDelBean) {
		try {
			servicePrivilege.doDeleteRowData(beanDelBean);
			throwNewException("Reussite delete");
		} catch (Exception e) {
			displayException(e);
		}
		return getViewList_Ajax(FILTER_VIEW);
	}
}
