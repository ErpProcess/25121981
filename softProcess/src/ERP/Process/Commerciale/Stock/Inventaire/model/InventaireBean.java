package ERP.Process.Commerciale.Stock.Inventaire.model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.TypeInventaire.model.TypeInventaireBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "inventaire", schema = "stock")
public class InventaireBean extends GenericBean {

 
 
	private static final long serialVersionUID = 1980422241500209121L;



	@EmbeddedId
	private PkInventaire pk = new PkInventaire();

	

	@ManyToOne
	@JoinColumn(name = "type_inv_id", insertable = true, updatable = true)
	private TypeInventaireBean typeInventaire = new TypeInventaireBean();
	
	
	@OneToMany(fetch=FetchType.LAZY  )
	@JoinColumns({
			 @JoinColumn(name = "inv_date", insertable = true, updatable = false,referencedColumnName="inv_date"),
			 @JoinColumn(name = "inv_id", insertable = true, updatable = false,referencedColumnName="inv_id"),
		     @JoinColumn(name = "depot_id", insertable = true, updatable = false,referencedColumnName="depot_id")
	 })
	private Set <DetInventaireBean>  listdetail_Inve  =  new TreeSet <DetInventaireBean>();
	

	@Column
	private String inv_libellle = "";

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	@Column
	private String usr_cre = "";
	@Column
	Date date_cre;
	
	
	@Column
	private String usr_mod = "";
	
	
	@Transient
	private String condition_Select_dateMax_Inventaire = "";
	
	
	@Transient
	private String condition_etat_entite = "";
	
	
	@Column
	Date date_mod;
	
	
	
	@ManyToOne 
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean  modeBean =  new FonctionBean();

	 

	public TypeInventaireBean getTypeInventaire() {
		return typeInventaire;
	}

	public void setTypeInventaire(TypeInventaireBean typeInventaire) {
		this.typeInventaire = typeInventaire;
	}

	public void setInv_libellle(String inv_libellle) {
		this.inv_libellle = inv_libellle;
	}

	public String getInv_libellle() {
		return inv_libellle;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public PkInventaire getPk() {
		return pk;
	}

	public void setPk(PkInventaire pk) {
		this.pk = pk;
	}

	 

	public String getCondition_Select_dateMax_Inventaire() {
		return condition_Select_dateMax_Inventaire;
	}

	public void setCondition_Select_dateMax_Inventaire(
			String condition_Select_dateMax_Inventaire) {
		this.condition_Select_dateMax_Inventaire = condition_Select_dateMax_Inventaire;
	}

	public Set<DetInventaireBean> getListdetail_Inve() {
		return listdetail_Inve;
	}

	public void setListdetail_Inve(Set<DetInventaireBean> listdetail_Inve) {
		this.listdetail_Inve = listdetail_Inve;
	}

	public String getCondition_etat_entite() {
		return condition_etat_entite;
	}

	public void setCondition_etat_entite(String condition_etat_entite) {
		this.condition_etat_entite = condition_etat_entite;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

}
