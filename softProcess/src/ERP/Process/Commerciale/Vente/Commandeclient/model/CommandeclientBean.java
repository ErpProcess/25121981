package ERP.Process.Commerciale.Vente.Commandeclient.model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.DepotStockage.model.DepotStockageBean;
import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "commande_client", schema = "vente")
public class CommandeclientBean extends GenericBean {
 
 
	private static final long serialVersionUID = -797145854049271121L;

	@Id
	@Column
	private String cmd_id = "";
	
	@Column
	private String cmd_libelle = "";
	
	@Column
	private Date cmd_date;
	
	@Transient
	private Date cmd_date2;
	
	@Transient
	private String condition_select_mode="";
	 
	@Transient
	private Double quantiteX;
	
	@Transient
	private String code_barreX="";
	
	
	@Transient
	private Double cmd_mnt_net_a_payer;
	
	
	@Column
	private Double avance_montant_cmd;
 
	
	@Column
	private Double commande_remise;
	
	@Column
	private Double taux_remise_alacaisse;
	
	@Column
	private Double cmd_remise_alacaisse;
	
	
	@Column
	private Double commande_mnt_ht;

	@Column
	private Double commande_mnt_tva;

	@Column
	private Double commande_mnt_total;
	
	
	 
	
 
	  
	 
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();
	
	@Column
	private String cmd_obs = "";
	
	 
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = "cmd_id", insertable = true, updatable = false, referencedColumnName = "cmd_id")
	private Set <DetCmdCltBean>  list_detaille  =  new TreeSet <DetCmdCltBean>();

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean etablissment = new EtablissementBean();
	

	@ManyToOne
	@JoinColumn(name = "clt_id", insertable = true, updatable = true)
	private ClientBean client = new ClientBean();
	
	@ManyToOne
	@JoinColumn(name = "depot_id", insertable = true, updatable = true)
	private DepotStockageBean depot = new DepotStockageBean();
	
	@Transient
	private  String total_mnt_ht="";
	
	
	@Transient
	private  String total_mnt_tva="";
	
	
	@Transient
	private  String total_mnt_gen="";
	
	
	@Transient
	private  String total_quantite="";
	
	
	 
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Time time_cre;
	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private java.sql.Time time_mod;
	@Column
	private java.sql.Date date_mod;

	public void setCmd_libelle(String cmd_libelle) {
		this.cmd_libelle = cmd_libelle;
	}

	public String getCmd_libelle() {
		return cmd_libelle;
	}

 
	 

	 

	public void setCmd_obs(String cmd_obs) {
		this.cmd_obs = cmd_obs;
	}

	public String getCmd_obs() {
		return cmd_obs;
	}

	 

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setDate_cre(java.sql.Date date_cre) {
		this.date_cre = date_cre;
	}

	public java.sql.Date getDate_cre() {
		return date_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	public void setDate_mod(java.sql.Date date_mod) {
		this.date_mod = date_mod;
	}

	public java.sql.Date getDate_mod() {
		return date_mod;
	}

	public String getCmd_id() {
		return cmd_id;
	}

	public void setCmd_id(String cmd_id) {
		this.cmd_id = cmd_id;
	}

	public Date getCmd_date2() {
		return cmd_date2;
	}

	public void setCmd_date2(Date cmd_date2) {
		this.cmd_date2 = cmd_date2;
	}

	public Date getCmd_date() {
		return cmd_date;
	}

	public void setCmd_date(Date cmd_date) {
		this.cmd_date = cmd_date;
	}

	public String getCondition_select_mode() {
		return condition_select_mode;
	}

	public void setCondition_select_mode(String condition_select_mode) {
		this.condition_select_mode = condition_select_mode;
	}

	 

	public EtablissementBean getEtablissment() {
		return etablissment;
	}

	public void setEtablissment(EtablissementBean etablissment) {
		this.etablissment = etablissment;
	}

	public ClientBean getClient() {
		return client;
	}

	public void setClient(ClientBean client) {
		this.client = client;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public Set<DetCmdCltBean> getList_detaille() {
		return list_detaille;
	}

	public void setList_detaille(Set<DetCmdCltBean> list_detaille) {
		this.list_detaille = list_detaille;
	}

	 

	public Double getAvance_montant_cmd() {
		return avance_montant_cmd;
	}

	public void setAvance_montant_cmd(Double avance_montant_cmd) {
		this.avance_montant_cmd = avance_montant_cmd;
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

	public Double getCommande_remise() {
		return commande_remise;
	}

	public void setCommande_remise(Double commande_remise) {
		this.commande_remise = commande_remise;
	}

	public Double getCommande_mnt_ht() {
		return commande_mnt_ht;
	}

	public void setCommande_mnt_ht(Double commande_mnt_ht) {
		this.commande_mnt_ht = commande_mnt_ht;
	}

	public Double getCommande_mnt_tva() {
		return commande_mnt_tva;
	}

	public void setCommande_mnt_tva(Double commande_mnt_tva) {
		this.commande_mnt_tva = commande_mnt_tva;
	}

	public Double getCommande_mnt_total() {
		return commande_mnt_total;
	}

	public void setCommande_mnt_total(Double commande_mnt_total) {
		this.commande_mnt_total = commande_mnt_total;
	}

	public DepotStockageBean getDepot() {
		return depot;
	}

	public void setDepot(DepotStockageBean depot) {
		this.depot = depot;
	}

	public Double getTaux_remise_alacaisse() {
		return taux_remise_alacaisse;
	}

	public void setTaux_remise_alacaisse(Double taux_remise_alacaisse) {
		this.taux_remise_alacaisse = taux_remise_alacaisse;
	}

	public Double getCmd_remise_alacaisse() {
		return cmd_remise_alacaisse;
	}

	public void setCmd_remise_alacaisse(Double cmd_remise_alacaisse) {
		this.cmd_remise_alacaisse = cmd_remise_alacaisse;
	}

	public Double getCmd_mnt_net_a_payer() {
		return cmd_mnt_net_a_payer;
	}

	public void setCmd_mnt_net_a_payer(Double cmd_mnt_net_a_payer) {
		this.cmd_mnt_net_a_payer = cmd_mnt_net_a_payer;
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

	 
}
