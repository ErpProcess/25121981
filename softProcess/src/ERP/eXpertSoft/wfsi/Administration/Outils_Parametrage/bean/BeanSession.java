package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Societe.model.SocieteBean;

public class BeanSession {
	
	private String  pack_id = "";	 
	private String  pack_libelle = "";	
	
	private String  spack_id = "";	
	private String  spack_libelle = "";	
	
	private String  mod_id = "";
	private String  mod_libelle = "";
	
	private String  sousmod_id = "";
	private String  sousmod_libelle = "";
	private String  sousmod_libelle_title = "";
	private String  prefix_sousmod_libelle_title = "-";
	
	

	private String  indexModule = "";
	private String  indexSousModule = "";
	
	private String  view_smfct_action = ""; 
	
	private String  usr_id = "";
	private String  usr_nom = "";
	private String  usr_pre = "";
	
	private String  fct_id = "";
	private String  lang_id = "";
	private String  fct_libelle = "";
	
	private String  soc_id = "";
	private String  soc_lib = "";
	private String  etab_id = "";
	private String  etab_central = "";
	private String  etab_central_lib = "";
	private String  etab_lib = "";
	
	
	private String  prf_id = "";
	private String  prf_libelle = "";
	
	private String  data_action = "";
	
	private String  home = "";
	
	private String  patternDecimalFormat  = "";
	
	
	 
	public static BDateTime bDateTime = new BDateTime();
	
	private SocieteBean  societe      = new SocieteBean();
	
	private EtablissementBean etablissement = new EtablissementBean();
	
 
	

	public String getFct_id() {
		return fct_id;
	}

	public void setFct_id(String fct_id) {
		this.fct_id = fct_id;
	}

	public String getFct_libelle() {
		return fct_libelle;
	}

	public void setFct_libelle(String fct_libelle) {
		this.fct_libelle = fct_libelle;
	}

	public String getPack_id() {
		return pack_id;
	}

	public void setPack_id(String pack_id) {
		this.pack_id = pack_id;
	}

	public String getSpack_id() {
		return spack_id;
	}

	public void setSpack_id(String spack_id) {
		this.spack_id = spack_id;
	}

	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public String getSousmod_id() {
		return sousmod_id;
	}

	public void setSousmod_id(String sousmod_id) {
		this.sousmod_id = sousmod_id;
	}

	public String getMod_libelle() {
		return mod_libelle;
	}

	public void setMod_libelle(String mod_libelle) {
		this.mod_libelle = mod_libelle;
	}

	public String getSousmod_libelle() {
		return sousmod_libelle;
	}

	public void setSousmod_libelle(String sousmod_libelle) {
		this.sousmod_libelle = sousmod_libelle;
	}

	public String getIndexModule() {
		return indexModule;
	}

	public void setIndexModule(String indexModule) {
		this.indexModule = indexModule;
	}

	public String getIndexSousModule() {
		return indexSousModule;
	}

	public void setIndexSousModule(String indexSousModule) {
		this.indexSousModule = indexSousModule;
	}

	public String getPack_libelle() {
		return pack_libelle;
	}

	public void setPack_libelle(String pack_libelle) {
		this.pack_libelle = pack_libelle;
	}

	public String getSpack_libelle() {
		return spack_libelle;
	}

	public void setSpack_libelle(String spack_libelle) {
		this.spack_libelle = spack_libelle;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsr_nom() {
		return usr_nom;
	}

	public void setUsr_nom(String usr_nom) {
		this.usr_nom = usr_nom;
	}

	public String getUsr_pre() {
		return usr_pre;
	}

	public void setUsr_pre(String usr_pre) {
		this.usr_pre = usr_pre;
	}

	public java.util.Date getDateUtil() {
		try {
			return   new SimpleDateFormat("yyyy-MM-dd").parse(bDateTime.getDateActuelF3());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public java.sql.Date getDateSql() {
		try {
			return   (Date) new SimpleDateFormat("yyyy-MM-dd").parse(bDateTime.getDateActuelF3());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	 
	public java.util.Date getTimeUtil() {
		try {
		return  new SimpleDateFormat("HH:mm:ss").parse(bDateTime.getHeurActuel());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public java.sql.Date getTimeSql() {
		try {
		return  (Date) new SimpleDateFormat("HH:mm:ss").parse(bDateTime.getHeurActuel());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getStringDate() {
			return  bDateTime.getDateActuel_system();
		
	}
	 
	public String getStringTime() {
		return  bDateTime.getHeurActuel();
		
	}

	public String getLang_id() {
		return lang_id;
	}

	public void setLang_id(String lang_id) {
		this.lang_id = lang_id;
	}

	public String getSoc_id() {
		return soc_id;
	}

	public void setSoc_id(String soc_id) {
		this.soc_id = soc_id;
	}

	public String getEtab_id() {
		return etab_id;
	}

	public void setEtab_id(String etab_id) {
		this.etab_id = etab_id;
	}

	public static BDateTime getBDateTime() {
		return bDateTime;
	}

	public static void setBDateTime(BDateTime dateTime) {
		bDateTime = dateTime;
	}

	public String getPrf_id() {
		return prf_id;
	}

	public void setPrf_id(String prf_id) {
		this.prf_id = prf_id;
	}

	public String getPrf_libelle() {
		return prf_libelle;
	}

	public void setPrf_libelle(String prf_libelle) {
		this.prf_libelle = prf_libelle;
	}

	public String getSoc_lib() {
		return soc_lib;
	}

	public void setSoc_lib(String soc_lib) {
		this.soc_lib = soc_lib;
	}

	public String getEtab_lib() {
		return etab_lib;
	}

	public void setEtab_lib(String etab_lib) {
		this.etab_lib = etab_lib;
	}

	public String getView_smfct_action() {
		return view_smfct_action;
	}

	public void setView_smfct_action(String view_smfct_action) {
		this.view_smfct_action = view_smfct_action;
	}

	public String getSousmod_libelle_title() {
		return sousmod_libelle_title;
	}

	public void setSousmod_libelle_title(String sousmod_libelle_title) {
		this.sousmod_libelle_title = sousmod_libelle_title;
	}

	public String getData_action() {
		return data_action;
	}

	public void setData_action(String data_action) {
		this.data_action = data_action;
	}

	public String getEtab_central() {
		return etab_central;
	}

	public void setEtab_central(String etab_central) {
		this.etab_central = etab_central;
	}

	public String getEtab_central_lib() {
		return etab_central_lib;
	}

	public void setEtab_central_lib(String etab_central_lib) {
		this.etab_central_lib = etab_central_lib;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getPatternDecimalFormat() {
		return patternDecimalFormat;
	}

	public void setPatternDecimalFormat(String patternDecimalFormat) {
		this.patternDecimalFormat = patternDecimalFormat;
	}

	public SocieteBean getSociete() {
		return societe;
	}

	public void setSociete(SocieteBean societe) {
		this.societe = societe;
	}

	public EtablissementBean getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(EtablissementBean etablissement) {
		this.etablissement = etablissement;
	}

	 
	public String getPrefix_sousmod_libelle_title() {
		return prefix_sousmod_libelle_title;
	}

	public void setPrefix_sousmod_libelle_title(String prefix_sousmod_libelle_title) {
		this.prefix_sousmod_libelle_title = prefix_sousmod_libelle_title;
	}
	
 
	 
	
	
}
