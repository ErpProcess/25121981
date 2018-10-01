package ERP.Process.Commerciale.Vente.FournitureVente.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;


@JsonAutoDetect
@Entity
@Table(name = "fourniture_vente", schema = "vente")
public class FournitureVenteBean extends GenericBean {
 
	private static final long serialVersionUID = -6200453994514272036L;
	@Id
	@Column
	private String frn_ve_id = "";
	@Column
	private String frn_ve_libelle = "";
	
	 
	
	@ManyToOne
	@JoinColumn(name = "vente_id", insertable = true, updatable = true)
	private ProcedureVenteBean venteFrn = new ProcedureVenteBean();
	
	@Column
	private Date frn_ve_date;
	


	@Column
	private String frn_ve_obs = "";
	
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	 
 
	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";
	 
	@Column
	private java.sql.Time time_cre;
	@Column
	private java.sql.Time time_mod;
	
	
	
	@ManyToOne
	@JoinColumn(name = "clt_id", insertable = true, updatable = true)
	private ClientBean client = new ClientBean();
	
	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean depot = new DepotStockageBean();
	
	
	@ManyToOne 
	@JoinColumn(name = "cmd_id", insertable = true, updatable = true)
	private CommandeclientBean  commande= new CommandeclientBean ();

	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();
	 
	@Column
	private Date date_confirm;
	@Column
	private java.sql.Time time_confirm;
	@Column
	private String usr_confirm = "";
	@Column
	private Double frn_ve_remise  ;
	@Column
	private Double frn_ve_mnt_ht ;
	@Column
	private Double frn_ve_mnt_tva  ;
	@Column
	private Double frn_ve_mnt_total ;
	@Column
	private Double frn_ve_remise_alacaisse  ;
	@Column
	private Double frn_ve_benefice_vente  ;
	@Column
	private Integer dev_id;

	public Date getFrn_ve_date() {
		return frn_ve_date;
	}

	public void setFrn_ve_date(Date frn_ve_date) {
		this.frn_ve_date = frn_ve_date;
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

	public Date getDate_confirm() {
		return date_confirm;
	}

	public void setDate_confirm(Date date_confirm) {
		this.date_confirm = date_confirm;
	}

	public void setFrn_ve_id(String frn_ve_id) {
		this.frn_ve_id = frn_ve_id;
	}

	public String getFrn_ve_id() {
		return frn_ve_id;
	}

	public void setFrn_ve_libelle(String frn_ve_libelle) {
		this.frn_ve_libelle = frn_ve_libelle;
	}

	public String getFrn_ve_libelle() {
		return frn_ve_libelle;
	}

	 

	public void setFrn_ve_obs(String frn_ve_obs) {
		this.frn_ve_obs = frn_ve_obs;
	}

	public String getFrn_ve_obs() {
		return frn_ve_obs;
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

	 
	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	 

	public void setTime_confirm(java.sql.Time time_confirm) {
		this.time_confirm = time_confirm;
	}

	public java.sql.Time getTime_confirm() {
		return time_confirm;
	}

	public void setUsr_confirm(String usr_confirm) {
		this.usr_confirm = usr_confirm;
	}

	public String getUsr_confirm() {
		return usr_confirm;
	}

	 

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public ClientBean getClient() {
		return client;
	}

	public void setClient(ClientBean client) {
		this.client = client;
	}

	public DepotStockageBean getDepot() {
		return depot;
	}

	public void setDepot(DepotStockageBean depot) {
		this.depot = depot;
	}

	public CommandeclientBean getCommande() {
		return commande;
	}

	public void setCommande(CommandeclientBean commande) {
		this.commande = commande;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public Double getFrn_ve_remise() {
		return frn_ve_remise;
	}

	public void setFrn_ve_remise(Double frn_ve_remise) {
		this.frn_ve_remise = frn_ve_remise;
	}

	public Double getFrn_ve_mnt_ht() {
		return frn_ve_mnt_ht;
	}

	public void setFrn_ve_mnt_ht(Double frn_ve_mnt_ht) {
		this.frn_ve_mnt_ht = frn_ve_mnt_ht;
	}

	public Double getFrn_ve_mnt_tva() {
		return frn_ve_mnt_tva;
	}

	public void setFrn_ve_mnt_tva(Double frn_ve_mnt_tva) {
		this.frn_ve_mnt_tva = frn_ve_mnt_tva;
	}

	public Double getFrn_ve_mnt_total() {
		return frn_ve_mnt_total;
	}

	public void setFrn_ve_mnt_total(Double frn_ve_mnt_total) {
		this.frn_ve_mnt_total = frn_ve_mnt_total;
	}

	public Double getFrn_ve_remise_alacaisse() {
		return frn_ve_remise_alacaisse;
	}

	public void setFrn_ve_remise_alacaisse(Double frn_ve_remise_alacaisse) {
		this.frn_ve_remise_alacaisse = frn_ve_remise_alacaisse;
	}

	public Double getFrn_ve_benefice_vente() {
		return frn_ve_benefice_vente;
	}

	public void setFrn_ve_benefice_vente(Double frn_ve_benefice_vente) {
		this.frn_ve_benefice_vente = frn_ve_benefice_vente;
	}

	public void setDev_id(Integer dev_id) {
		this.dev_id = dev_id;
	}

	public Integer getDev_id() {
		return dev_id;
	}
	
	public ProcedureVenteBean getVenteFrn() {
		return venteFrn;
	}

	public void setVenteFrn(ProcedureVenteBean venteFrn) {
		this.venteFrn = venteFrn;
	}
}
