package ERP.Process.Commerciale.Vente.ReglementFactClt.model;

import java.sql.Time;
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

import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model.ModeReglementBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "reglement_fact_client", schema = "vente")
public class ReglementFactCltBean extends GenericBean {


 

	/**
	 * 
	 */
	private static final long serialVersionUID = -7870550959727110742L;

	@Id
	@Column
	private String reg_id = "";

	@ManyToOne
	@JoinColumn(name = "fact_clt_id", insertable = true, updatable = false)
	private Facture_clientBean factclient = new Facture_clientBean();

 
	
 
	
	
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "mod_reg_id", insertable = true, updatable = true)
	private ModeReglementBean   modReg = new ModeReglementBean();
	
	
	@ManyToOne
	@JoinColumn(name = "reg_type", insertable = true, updatable = true)
	private Entite_etat_commercialeBean reg_type = new Entite_etat_commercialeBean();
	
	
	@Transient
	private Date echeanDate;
	
	
	@Transient
	private String etatRegHeader;
	
	@Transient
	private String modeRegHeader;
	
	
	@Transient
	private String pieceNumHeader;
	
	

	
	
	
	@Transient
	private Double echeanMontant;
	
	
	@Transient
	private  String condition_juste_echeance="";
	
	
	@Transient
	private  String select_avoir="";
	
	
	@Column
	private Date reg_date;
	
	
	@Transient
	private Date reg_date2;
	
	
	@Column
	private Integer reg_nbr_echeance;
	
	@Column
	private Double montant_facture;
	
	@Column
	private Double montant_avance;
	
	@Column
	private Double montant_recu;
	
	@Column
	private Double montant_restant;

	@Column
	private java.sql.Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private java.sql.Date date_mod;
	@Column
	private String usr_mod = "";
	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	@Column
	private Time time_cre  ;
	@Column
	private Time time_mod  ;
	@Column
	private String num_piece = "";
	 
	
	
	@ManyToOne
	@JoinColumn(name = "reg_nature", insertable = true, updatable = true)
	private Entite_etat_commercialeBean nature = new Entite_etat_commercialeBean();

	
	public void setReg_nbr_echeance(Integer reg_nbr_echeance) {
		this.reg_nbr_echeance = reg_nbr_echeance;
	}

	public Integer getReg_nbr_echeance() {
		return reg_nbr_echeance;
	}

	 

	public Date getReg_date2() {
		return reg_date2;
	}

	public void setReg_date2(Date reg_date2) {
		this.reg_date2 = reg_date2;
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

	 

	public Time getTime_cre() {
		return time_cre;
	}

	public void setTime_cre(Time time_cre) {
		this.time_cre = time_cre;
	}

	public Time getTime_mod() {
		return time_mod;
	}

	public void setTime_mod(Time time_mod) {
		this.time_mod = time_mod;
	}

	public void setNum_piece(String num_piece) {
		this.num_piece = num_piece;
	}

	public String getNum_piece() {
		return num_piece;
	}

	 

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public Facture_clientBean getFactclient() {
		return factclient;
	}

	public void setFactclient(Facture_clientBean factclient) {
		this.factclient = factclient;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public Double getMontant_facture() {
		return montant_facture;
	}

	public void setMontant_facture(Double montant_facture) {
		this.montant_facture = montant_facture;
	}

	public Double getMontant_avance() {
		return montant_avance;
	}

	public void setMontant_avance(Double montant_avance) {
		this.montant_avance = montant_avance;
	}

	public Double getMontant_restant() {
		return montant_restant;
	}

	public void setMontant_restant(Double montant_restant) {
		this.montant_restant = montant_restant;
	}

	 
	public String getCondition_juste_echeance() {
		return condition_juste_echeance;
	}

	public void setCondition_juste_echeance(String condition_juste_echeance) {
		this.condition_juste_echeance = condition_juste_echeance;
	}

	public Entite_etat_commercialeBean getNature() {
		return nature;
	}

	public void setNature(Entite_etat_commercialeBean nature) {
		this.nature = nature;
	}

	public String getSelect_avoir() {
		return select_avoir;
	}

	public void setSelect_avoir(String select_avoir) {
		this.select_avoir = select_avoir;
	}

	public Entite_etat_commercialeBean getReg_type() {
		return reg_type;
	}

	public void setReg_type(Entite_etat_commercialeBean reg_type) {
		this.reg_type = reg_type;
	}

	public Double getMontant_recu() {
		return montant_recu;
	}

	public void setMontant_recu(Double montant_recu) {
		this.montant_recu = montant_recu;
	}

	public Date getEcheanDate() {
		return echeanDate;
	}

	public void setEcheanDate(Date echeanDate) {
		this.echeanDate = echeanDate;
	}

	public Double getEcheanMontant() {
		return echeanMontant;
	}

	public void setEcheanMontant(Double echeanMontant) {
		this.echeanMontant = echeanMontant;
	}

	public String getEtatRegHeader() {
		return etatRegHeader;
	}

	public void setEtatRegHeader(String etatRegHeader) {
		this.etatRegHeader = etatRegHeader;
	}

	public String getPieceNumHeader() {
		return pieceNumHeader;
	}

	public void setPieceNumHeader(String pieceNumHeader) {
		this.pieceNumHeader = pieceNumHeader;
	}

	public String getModeRegHeader() {
		return modeRegHeader;
	}

	public void setModeRegHeader(String modeRegHeader) {
		this.modeRegHeader = modeRegHeader;
	}

	public ModeReglementBean getModReg() {
		return modReg;
	}

	public void setModReg(ModeReglementBean modReg) {
		this.modReg = modReg;
	}
 
	 
}
