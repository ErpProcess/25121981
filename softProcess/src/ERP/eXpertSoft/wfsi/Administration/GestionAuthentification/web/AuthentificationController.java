package ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ERP.eXpertSoft.wfsi.Administration.GestionAuthentification.template.AuthentificationTemplate;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;


@Controller
public class AuthentificationController extends ActionAuthentificationManager       {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521568854052550083L;

	@RequestMapping(value = ROOT)
	public ModelAndView doDefineRootUrl(UtilisateurBean utilisateur,HttpServletRequest initrequest, 
			HttpServletResponse initresponse) throws NoSuchFieldException, ServletException ,IOException,Throwable { 
		
		
		   this.doInitGenericActionAuthentification( initrequest , initresponse , new AuthentificationTemplate());
		
		   
		   
			  
		  if (i$_ACT_LOAD_APP)           return doLoadApplication(utilisateur);
		  
		  
		  if (i$_ACT_LOAD_APPX)          return doLoadAppl(utilisateur); 
		 
		  //if (i$_ACT_LOAD_APPTWO)        return doLoadApplTwos(utilisateur); 
		    
		  
		  if (i$_ACT_INIT_COMPTE)        return doInitPwd(utilisateur); 
		  if (i$_ACT_UPDATE_COMPTE)      return doUpdatePwd(utilisateur); 
	      if (i$_ACT_EXIST_APP)          return doQuitterApplication(); 
		  if (i$_ACT_LOAD_Mod_SM)        return doLoadModuleAndSousMod(); 
		  if (i$_ACT_GET_SOC)            return getSociete(); 
		  if (i$_ACT_GET_LANG)           return getLangSystem(); 
		  if (i$_ACT_SetterLangueSystem) return SetterLangSystem(); 
		  if (i$_ACT_GET_USER)           return getUserName(); 
		  if (i$_ACT_SET_TIME_OUT)       return getTimeOut();  
		 
	
		 return getTimeOut();
	}

}
