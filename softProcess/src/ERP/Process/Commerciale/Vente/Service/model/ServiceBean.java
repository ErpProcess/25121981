package ERP.Process.Commerciale.Vente.Service.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "service", schema = "vente")
public class ServiceBean extends GenericBean {

	private static final long serialVersionUID = -2606519225318302337L;
	@Id
	@Column
	private String srv_id = "";
	@Column
	private String srv_libelle = "";
	 
	
	@ManyToOne
	@JoinColumn(name = "vente_id", insertable = true, updatable = true)
	private ProcedureVenteBean venteSrv ;
	
	
	@Column
	private Date srv_date;
	@Column
	private String srv_obs = "";

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	@ManyToOne
	@JoinColumn(name = "clt_id", insertable = true, updatable = true)
	private ClientBean client = new ClientBean();

	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean depot = new DepotStockageBean();

	@ManyToOne
	@JoinColumn(name = "cmd_id", insertable = true, updatable = true)
	private CommandeclientBean commande = new CommandeclientBean();

	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";

	@Column
	private java.sql.Time time_cre;
	@Column
	private java.sql.Time time_mod;

	@Column
	private java.sql.Date date_confirm;
	@Column
	private java.sql.Time time_confirm;
	@Column
	private String usr_confirm = "";
	@Column
	private Double srv_remise;
	@Column
	private Double srv_mnt_ht;
	@Column
	private Double srv_mnt_tva;
	@Column
	private Double srv_mnt_total;
	@Column
	private Double srv_remise_alacaisse;
	@Column
	private Double srv_benefice_vente;
	
	 

	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  deviseSrv ;
	
	

	public DeviseBean getDeviseSrv() {
		return deviseSrv;
	}

	public void setDeviseSrv(DeviseBean deviseSrv) {
		this.deviseSrv = deviseSrv;
	}

	public void setSrv_id(String srv_id) {
		this.srv_id = srv_id;
	}

	public String getSrv_id() {
		return srv_id;
	}

	public void setSrv_libelle(String srv_libelle) {
		this.srv_libelle = srv_libelle;
	}

	public String getSrv_libelle() {
		return srv_libelle;
	}

	 

	public ProcedureVenteBean getVenteSrv() {
		return venteSrv;
	}

	public void setVenteSrv(ProcedureVenteBean venteSrv) {
		this.venteSrv = venteSrv;
	}

	public void setSrv_obs(String srv_obs) {
		this.srv_obs = srv_obs;
	}

	public String getSrv_obs() {
		return srv_obs;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
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

	public void setDate_confirm(java.sql.Date date_confirm) {
		this.date_confirm = date_confirm;
	}

	public java.sql.Date getDate_confirm() {
		return date_confirm;
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

	 

	public Date getSrv_date() {
		return srv_date;
	}

	public void setSrv_date(Date srv_date) {
		this.srv_date = srv_date;
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

	public Double getSrv_remise() {
		return srv_remise;
	}

	public void setSrv_remise(Double srv_remise) {
		this.srv_remise = srv_remise;
	}

	public Double getSrv_mnt_ht() {
		return srv_mnt_ht;
	}

	public void setSrv_mnt_ht(Double srv_mnt_ht) {
		this.srv_mnt_ht = srv_mnt_ht;
	}

	public Double getSrv_mnt_tva() {
		return srv_mnt_tva;
	}

	public void setSrv_mnt_tva(Double srv_mnt_tva) {
		this.srv_mnt_tva = srv_mnt_tva;
	}

	public Double getSrv_mnt_total() {
		return srv_mnt_total;
	}

	public void setSrv_mnt_total(Double srv_mnt_total) {
		this.srv_mnt_total = srv_mnt_total;
	}

	public Double getSrv_remise_alacaisse() {
		return srv_remise_alacaisse;
	}

	public void setSrv_remise_alacaisse(Double srv_remise_alacaisse) {
		this.srv_remise_alacaisse = srv_remise_alacaisse;
	}

	public Double getSrv_benefice_vente() {
		return srv_benefice_vente;
	}

	public void setSrv_benefice_vente(Double srv_benefice_vente) {
		this.srv_benefice_vente = srv_benefice_vente;
	}
	
	
}
