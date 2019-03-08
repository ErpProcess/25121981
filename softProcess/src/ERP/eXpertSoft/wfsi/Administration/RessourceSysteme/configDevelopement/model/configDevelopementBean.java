package ERP.eXpertSoft.wfsi.Administration.RessourceSysteme.configDevelopement.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model.ProfileBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
 

@JsonAutoDetect
@Entity
@Table(name = "config_developement", schema = "administration")
public class configDevelopementBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer config_id;

	
	
	 

	@Column
	private String user_list = "";
	
	
	@Column
	private String api_action = "";
	
	
	@ManyToOne
	@JoinColumn(name = "fct_id", insertable = true, updatable = true)
	private FonctionBean modeBean   ;
	
	
	@Column
	private String json_properties  ;
	
	
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	

	public Integer getConfig_id() {
		return config_id;
	}

	public void setConfig_id(Integer config_id) {
		this.config_id = config_id;
	}

	@ManyToOne
	@JoinColumn(name = "prf_id" , insertable = true , updatable = true ,referencedColumnName="prf_id")
	private ProfileBean profile = new ProfileBean();

	
	@ManyToOne
	@JoinColumn(name = "sousmod_id" , insertable = true , updatable = true ,referencedColumnName="sousmod_id")
	private SousModuleBean sousMod = new SousModuleBean();
	
	
	
 

	 

	 
	public SousModuleBean getSousMod() {
		return sousMod;
	}

	public void setSousMod(SousModuleBean sousMod) {
		this.sousMod = sousMod;
	}

	public void setUser_list(String user_list) {
		this.user_list = user_list;
	}

	public String getUser_list() {
		return user_list;
	}

	 
	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

 
	public ProfileBean getProfile() {
		return profile;
	}

	public void setProfile(ProfileBean profile) {
		this.profile = profile;
	}
	

	public String getApi_action() {
		return api_action;
	}

	public void setApi_action(String api_action) {
		this.api_action = api_action;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	 

	public String getJson_properties() {
		return json_properties;
	}

	public void setJson_properties(String json_properties) {
		this.json_properties = json_properties;
	}
 

 
}
