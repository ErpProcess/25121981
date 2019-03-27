package ERP.Process.Commerciale.Vente.ProcedureVente.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Commandeclient.model.CommandeclientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model.DeviseBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "op_vente", schema = "vente")
public class ProcedureVenteBean extends GenericBean {

	private static final long serialVersionUID = -6205104821131104860L;
 
	@Id
	@Column
	private String vente_id = "";

	@Column
	private Date vente_date;
	
	
	@Transient
	private String condition_select_mode="";
	
	@Transient
	private String condition_de_prix="";
	
	@Transient
	private String choixPanel="";
	
	
	
	@Transient
	private String condition_select_vente_non_confirmer="";
	
	@Transient
	private String				next_Focus			    = "";
	
	@Transient
	private Boolean fifo=true;
	
	
	@Column
	private Double avance_montant_vente;
	
	
	@Column
	private Double marge_benefice_vente;
	
	
	@Transient
	private Date vente_date2;
	
	@Transient
	private Double quantite_stock;
	
	@Transient
	private Double quantite_stock_fourniture;
	
	
	
	@Transient
	private Double quantiteX;
	
	
	
	
	@Transient
	private String code_barreX="";
	
	
	@Transient
	private Double quantiteFourniture;
	
	
	@Transient
	private Double quantiteService;
	
	
	
	
	@Transient
	private String code_barreFurniture="";
	
	
	
	@Transient
	private Boolean isVente = false;
	
	@Transient
	private Boolean isVentePrestation = false;
	
	
	
	
	@Transient
	private String code_barreService="";
	
	
	 
	
	@Column
	private Double vente_remise;
	
	
	
	@Column
	private Double taux_remise_alacaisse;
	
	@Column
	private Double vente_remise_alacaisse;
	

	@Column
	private Double vente_mnt_ht;

	@Column
	private Double vente_mnt_tva;
	
	@Transient
	private Double vente_mnt_net_a_payer;

	@Column
	private Double vente_mnt_total;
	
	
	
	
	@Transient
	private Double montant_timbre_fiscal;
	
	@Column
	private Double montant_vente_recu;
	
	
	@Transient
	private Double montant_vente_rendu;
	
	
	@Transient
	private String fam_id;

	@Column
	private String vente_obs = "";
	
	@Column
	private String liv_id = "";
	 

	@Column
	private String vente_libelle = "";

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	 
	
	@ManyToOne 
	@JoinColumn(name = "cmd_id", insertable = true, updatable = true)
	private CommandeclientBean  commande= new CommandeclientBean ();

	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	@ManyToOne
	@JoinColumn(name = "clt_id", insertable = true, updatable = true)
	private ClientBean client = new ClientBean();
	
	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean depot = new DepotStockageBean();
	
	
	@Transient
	private String				indx_row			= "";
	@Transient
	private String				indx_row_next		= "";
	@Transient
	private String				to_check			= "";
	
	
	
	public Boolean getIsVentePrestation() {
		return isVentePrestation;
	}

	public void setIsVentePrestation(Boolean isVentePrestation) {
		this.isVentePrestation = isVentePrestation;
	}

	@Column
	private java.sql.Time time_cre;

	@Column
	private java.sql.Time time_mod;

	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";
	
	
	@Column
	private String usr_confirm = "";
	
	@Column
	private java.sql.Date date_confirm;
	
	
	
	@Column
	private java.sql.Time time_confirm;
	
	
 
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "dev_id", insertable = true, updatable = true)
	private DeviseBean  devise ;
	
	
	@ManyToOne
	@JoinColumn(name = "fact_clt_id", insertable = true, updatable = false, nullable=true)
	private Facture_clientBean   factclient ;

	 

	public Facture_clientBean getFactclient() {
		return factclient;
	}

	public void setFactclient(Facture_clientBean factclient) {
		this.factclient = factclient;
	}

	public DeviseBean getDevise() {
		return devise;
	}

	public void setDevise(DeviseBean devise) {
		this.devise = devise;
	}

	public String getVente_id() {
		return vente_id;
	}

	public void setVente_id(String vente_id) {
		this.vente_id = vente_id;
	}

	public Date getVente_date() {
		return vente_date;
	}

	public void setVente_date(Date vente_date) {
		this.vente_date = vente_date;
	}

	public String getVente_obs() {
		return vente_obs;
	}

	public void setVente_obs(String vente_obs) {
		this.vente_obs = vente_obs;
	}

	public String getVente_libelle() {
		return vente_libelle;
	}

	public void setVente_libelle(String vente_libelle) {
		this.vente_libelle = vente_libelle;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	 

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
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

 

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	 

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public CommandeclientBean getCommande() {
		return commande;
	}

	public void setCommande(CommandeclientBean commande) {
		this.commande = commande;
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

	public Date getVente_date2() {
		return vente_date2;
	}

	public void setVente_date2(Date vente_date2) {
		this.vente_date2 = vente_date2;
	}

	public Double getAvance_montant_vente() {
		return avance_montant_vente;
	}

	public void setAvance_montant_vente(Double avance_montant_vente) {
		this.avance_montant_vente = avance_montant_vente;
	}

	public String getCondition_select_mode() {
		return condition_select_mode;
	}

	public void setCondition_select_mode(String condition_select_mode) {
		this.condition_select_mode = condition_select_mode;
	}

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

	public Double getQuantite_stock() {
		return quantite_stock;
	}

	public void setQuantite_stock(Double quantite_stock) {
		this.quantite_stock = quantite_stock;
	}

	public Double getQuantiteX() {
		return quantiteX;
	}

	public void setQuantiteX(Double quantiteX) {
		this.quantiteX = quantiteX;
	}

	public String getCode_barreX() {
		return code_barreX;
	}

	public void setCode_barreX(String code_barreX) {
		this.code_barreX = code_barreX;
	}

	public Boolean getFifo() {
		return fifo;
	}

	public void setFifo(Boolean fifo) {
		this.fifo = fifo;
	}

	public String getCondition_select_vente_non_confirmer() {
		return condition_select_vente_non_confirmer;
	}

	public void setCondition_select_vente_non_confirmer(
			String condition_select_vente_non_confirmer) {
		this.condition_select_vente_non_confirmer = condition_select_vente_non_confirmer;
	}

	public java.sql.Date getDate_confirm() {
		return date_confirm;
	}

	public void setDate_confirm(java.sql.Date date_confirm) {
		this.date_confirm = date_confirm;
	}

	public java.sql.Time getTime_confirm() {
		return time_confirm;
	}

	public void setTime_confirm(java.sql.Time time_confirm) {
		this.time_confirm = time_confirm;
	}

	public String getUsr_confirm() {
		return usr_confirm;
	}

	public void setUsr_confirm(String usr_confirm) {
		this.usr_confirm = usr_confirm;
	}

	public Double getVente_remise() {
		return vente_remise;
	}

	public void setVente_remise(Double vente_remise) {
		this.vente_remise = vente_remise;
	}

	public Double getVente_mnt_ht() {
		return vente_mnt_ht;
	}

	public void setVente_mnt_ht(Double vente_mnt_ht) {
		this.vente_mnt_ht = vente_mnt_ht;
	}

	public Double getVente_mnt_tva() {
		return vente_mnt_tva;
	}

	public void setVente_mnt_tva(Double vente_mnt_tva) {
		this.vente_mnt_tva = vente_mnt_tva;
	}

	public Double getVente_mnt_total() {
		return vente_mnt_total;
	}

	public void setVente_mnt_total(Double vente_mnt_total) {
		this.vente_mnt_total = vente_mnt_total;
	}

	 

	 

	public String getLiv_id() {
		return liv_id;
	}

	public void setLiv_id(String liv_id) {
		this.liv_id = liv_id;
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

	public Double getVente_remise_alacaisse() {
		return vente_remise_alacaisse;
	}

	public void setVente_remise_alacaisse(Double vente_remise_alacaisse) {
		this.vente_remise_alacaisse = vente_remise_alacaisse;
	}

	public Double getTaux_remise_alacaisse() {
		return taux_remise_alacaisse;
	}

	public void setTaux_remise_alacaisse(Double taux_remise_alacaisse) {
		this.taux_remise_alacaisse = taux_remise_alacaisse;
	}

	public String getCondition_de_prix() {
		return condition_de_prix;
	}

	public void setCondition_de_prix(String condition_de_prix) {
		this.condition_de_prix = condition_de_prix;
	}

	public String getNext_Focus() {
		return next_Focus;
	}

	public void setNext_Focus(String next_Focus) {
		this.next_Focus = next_Focus;
	}

	public Double getMarge_benefice_vente() {
		return marge_benefice_vente;
	}

	public void setMarge_benefice_vente(Double marge_benefice_vente) {
		this.marge_benefice_vente = marge_benefice_vente;
	}

	public Double getVente_mnt_net_a_payer() {
		return vente_mnt_net_a_payer;
	}

	public void setVente_mnt_net_a_payer(Double vente_mnt_net_a_payer) {
		this.vente_mnt_net_a_payer = vente_mnt_net_a_payer;
	}

	public Double getMontant_vente_recu() {
		return montant_vente_recu;
	}

	public void setMontant_vente_recu(Double montant_vente_recu) {
		this.montant_vente_recu = montant_vente_recu;
	}

	public Double getMontant_vente_rendu() {
		return montant_vente_rendu;
	}

	public void setMontant_vente_rendu(Double montant_vente_rendu) {
		this.montant_vente_rendu = montant_vente_rendu;
	}

	public Double getMontant_timbre_fiscal() {
		return montant_timbre_fiscal;
	}

	public void setMontant_timbre_fiscal(Double montant_timbre_fiscal) {
		this.montant_timbre_fiscal = montant_timbre_fiscal;
	}

	public Double getQuantiteFourniture() {
		return quantiteFourniture;
	}

	public void setQuantiteFourniture(Double quantiteFourniture) {
		this.quantiteFourniture = quantiteFourniture;
	}

	public String getCode_barreFurniture() {
		return code_barreFurniture;
	}

	public void setCode_barreFurniture(String code_barreFurniture) {
		this.code_barreFurniture = code_barreFurniture;
	}

	public Double getQuantite_stock_fourniture() {
		return quantite_stock_fourniture;
	}

	public void setQuantite_stock_fourniture(Double quantite_stock_fourniture) {
		this.quantite_stock_fourniture = quantite_stock_fourniture;
	}

	public String getCode_barreService() {
		return code_barreService;
	}

	public void setCode_barreService(String code_barreService) {
		this.code_barreService = code_barreService;
	}

	public Double getQuantiteService() {
		return quantiteService;
	}

	public void setQuantiteService(Double quantiteService) {
		this.quantiteService = quantiteService;
	}

	public String getChoixPanel() {
		return choixPanel;
	}

	public void setChoixPanel(String choixPanel) {
		this.choixPanel = choixPanel;
	}

	public Boolean getIsVente() {
		return isVente;
	}

	public void setIsVente(Boolean isVente) {
		this.isVente = isVente;
	}

	public String getFam_id() {
		return fam_id;
	}

	public void setFam_id(String fam_id) {
		this.fam_id = fam_id;
	}

	 

	 

}
