package ERP.Process.Commerciale.Achat.Reception_achat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Demande_Achat.model.Demande_achatBean;
import ERP.Process.Commerciale.Fournisseur.model.FournisseurBean;
import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "reception_achat", schema = "achat")
public class Reception_achatBean extends  GenericBean{
 
	private static final long serialVersionUID = 6466769670171738250L;
 
	@Id
	@Column
	private String achat_id = "";
	
	
	@Column
	private  Date achat_date;
	

	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean depot = new DepotStockageBean();
	
	
	@ManyToOne 
	@JoinColumn(name = "dem_achat_id", insertable = true, updatable = true)
	private Demande_achatBean dem_achat = new Demande_achatBean ();
	
	@Column
	private Double avance_montant_achat;
	
	
	@Column
	private String achat_libelle = "";
	 
	
	
	@Transient
	private  String condition_etat_achat="";
	
	
	@Transient
	private  String total_mnt_ht="";
	
	
	@Transient
	private  String total_mnt_tva="";
	
	
	@Transient
	private  String total_mnt_gen="";
	
	
	@Transient
	private  String total_quantite="";
	
	
	@Transient
	private  String achat_date2="";
	 
	
	@Transient
	private  String demande_id="";
	
	
	@ManyToOne 
	@JoinColumn(name = "frs_id", insertable = true, updatable = true)
	private FournisseurBean      frsBean =  new FournisseurBean();
	
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	
	 
	 
	@ManyToOne 
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean  modeBean =  new FonctionBean();
	
	@ManyToOne 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise  ;
	
	
	@Column
	private java.sql.Time time_cre ;
	
	
	@Column
	private java.sql.Time time_mod ;
	
	@Column
	private String achat_obs = "";
	 
	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";

	@Transient
	private String indx_row = "";
	
	@Transient
	private String indx_row_next = "";
	
	@Transient
	private String to_check = "";
	
	@Transient
	private String id_entite = "";


	public String getIndx_row() {
		return indx_row;
	}

	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}

	public String getIndx_row_next() {
		return indx_row_next;
	}

	public void setIndx_row_next(String indx_row_next) {
		this.indx_row_next = indx_row_next;
	}

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	public String getId_entite() {
		return id_entite;
	}

	public void setId_entite(String id_entite) {
		this.id_entite = id_entite;
	}

	public void setAchat_libelle(String achat_libelle) {
		this.achat_libelle = achat_libelle;
	}

	public String getAchat_libelle() {
		return achat_libelle;
	}

  
 

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	public void setAchat_obs(String achat_obs) {
		this.achat_obs = achat_obs;
	}

	public String getAchat_obs() {
		return achat_obs;
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

	 

	public FournisseurBean getFrsBean() {
		return frsBean;
	}

	public void setFrsBean(FournisseurBean frsBean) {
		this.frsBean = frsBean;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	 

	 

	public String getTotal_mnt_ht() {
		return total_mnt_ht;
	}

	public void setTotal_mnt_ht(String total_mnt_ht) {
		this.total_mnt_ht = total_mnt_ht;
	}

	public String getTotal_mnt_tva() {
		return total_mnt_tva;
	}

	public void setTotal_mnt_tva(String total_mnt_tva) {
		this.total_mnt_tva = total_mnt_tva;
	}

	public String getTotal_mnt_gen() {
		return total_mnt_gen;
	}

	public void setTotal_mnt_gen(String total_mnt_gen) {
		this.total_mnt_gen = total_mnt_gen;
	}

	public String getTotal_quantite() {
		return total_quantite;
	}

	public void setTotal_quantite(String total_quantite) {
		this.total_quantite = total_quantite;
	}

	 

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}

	public String getCondition_etat_achat() {
		return condition_etat_achat;
	}

	public void setCondition_etat_achat(String condition_etat_achat) {
		this.condition_etat_achat = condition_etat_achat;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public Demande_achatBean getDem_achat() {
		return dem_achat;
	}

	public void setDem_achat(Demande_achatBean dem_achat) {
		this.dem_achat = dem_achat;
	}

	public String getDemande_id() {
		return demande_id;
	}

	public void setDemande_id(String demande_id) {
		this.demande_id = demande_id;
	}

	public String getAchat_date2() {
		return achat_date2;
	}

	public void setAchat_date2(String achat_date2) {
		this.achat_date2 = achat_date2;
	}

	public Double getAvance_montant_achat() {
		return avance_montant_achat;
	}

	public void setAvance_montant_achat(Double avance_montant_achat) {
		this.avance_montant_achat = avance_montant_achat;
	}

	public String getAchat_id() {
		return achat_id;
	}

	public void setAchat_id(String achat_id) {
		this.achat_id = achat_id;
	}

	public Date getAchat_date() {
		return achat_date;
	}

	public void setAchat_date(Date achat_date) {
		this.achat_date = achat_date;
	}

	public DepotStockageBean getDepot() {
		return depot;
	}

	public void setDepot(DepotStockageBean depot) {
		this.depot = depot;
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

	 

	 

	 

	 
}
